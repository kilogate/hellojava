package com.kilogate.hello.tomcat.connector2.connector;

import com.kilogate.hello.tomcat.connector2.core.ServletProcessor;
import com.kilogate.hello.tomcat.connector2.core.StaticResourceProcessor;
import com.kilogate.hello.tomcat.connector2.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 处理器
 *
 * @author fengquanwei
 * @create 2017/8/30 14:54
 **/
public class HttpProcessor {
    // ------------------------------ 变量 ------------------------------
    protected static StringManager stringManager = StringManager.getManager("com.kilogate.hello.tomcat");

    private HttpRequest request;
    private HttpResponse response;

    // ------------------------------ 公有方法 ------------------------------
    public void process(Socket socket) {
        try (OutputStream output = socket.getOutputStream()) {
            SocketInputStream input = new SocketInputStream(socket.getInputStream(), 2048);
            request = new HttpRequest(input);
            response = new HttpResponse(output);

            response.setRequest(request);
            response.setHeader("Server", "Kilogate Servlet Container");

            // 解析请求行
            parseRequestLine(input);
            // 解析头部
            parseHeaders(input);

            System.out.println("收到请求：" + request.getRequestURI());

            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------ 私有方法 ------------------------------

    /**
     * 解析请求行
     */
    private void parseRequestLine(SocketInputStream input) throws IOException, ServletException {
        HttpRequestLine requestLine = new HttpRequestLine();
        input.readRequestLine(requestLine);

        // 方法
        String method = new String(requestLine.method, 0, requestLine.methodEnd);
        // 协议
        String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);

        if (method.length() < 1) {
            throw new ServletException("Missing HTTP request method");
        } else if (requestLine.uriEnd < 1) {
            throw new ServletException("Missing HTTP request URI");
        }

        request.setMethod(method);
        request.setProtocol(protocol);

        // URI 与 查询字符串
        String uri = null;
        int question = requestLine.indexOf("?");
        if (question >= 0) {
            request.setQueryString(new String(requestLine.uri, question + 1, requestLine.uriEnd - question - 1));
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            if (pos != -1) {
                pos = uri.indexOf('/', pos + 3);
                if (pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }

        // 会话标识符
        String match = ";jsessionid=";
        int semicolon = uri.indexOf(match);
        if (semicolon >= 0) {
            String rest = uri.substring(semicolon + match.length());
            int semicolon2 = rest.indexOf(';');
            if (semicolon2 >= 0) {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            } else {
                request.setRequestedSessionId(rest);
                rest = "";
            }
            request.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
        } else {
            request.setRequestedSessionId(null);
            request.setRequestedSessionURL(false);
        }

        // 纠正 URI
        String normalizedUri = normalize(uri);
        if (normalizedUri != null) {
            request.setRequestURI(normalizedUri);
        } else {
            request.setRequestURI(uri);
            throw new ServletException("Invalid URI: " + uri + "'");
        }
    }

    /**
     * 解析头部
     */
    private void parseHeaders(SocketInputStream input) throws IOException, ServletException {
        while (true) {
            HttpHeader header = new HttpHeader();

            // 读取下一个头部
            input.readHeader(header);
            if (header.nameEnd == 0) {
                if (header.valueEnd == 0) {
                    return;
                } else {
                    throw new ServletException(stringManager.getString("httpProcessor.parseHeaders.colon"));
                }
            }

            // 添加头部
            String name = new String(header.name, 0, header.nameEnd);
            String value = new String(header.value, 0, header.valueEnd);
            request.addHeader(name, value);

            // 一些头部需要设置某些属性
            if (name.equals("cookie")) { // 解析 Cookie
                Cookie cookies[] = RequestUtil.parseCookieHeader(value);
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("jsessionid")) {
                        if (!request.isRequestedSessionIdFromCookie()) {
                            request.setRequestedSessionId(cookies[i].getValue());
                            request.setRequestedSessionCookie(true);
                            request.setRequestedSessionURL(false);
                        }
                    }
                    request.addCookie(cookies[i]);
                }
            } else if (name.equals("content-length")) {
                int n = -1;
                try {
                    n = Integer.parseInt(value);
                } catch (Exception e) {
                    throw new ServletException(stringManager.getString("httpProcessor.parseHeaders.contentLength"));
                }
                request.setContentLength(n);
            } else if (name.equals("content-type")) {
                request.setContentType(value);
            }
        }
    }

    /**
     * 纠正 URI（使得路径正常化）
     */
    private String normalize(String path) {
        if (path == null) {
            return null;
        }

        String normalized = path;

        // Normalize "/%7E" and "/%7e" at the beginning to "/~"
        if (normalized.startsWith("/%7E") || normalized.startsWith("/%7e")) {
            normalized = "/~" + normalized.substring(4);
        }

        // Prevent encoding '%', '/', '.' and '\', which are special reserved characters
        if ((normalized.indexOf("%25") >= 0)
                || (normalized.indexOf("%2F") >= 0)
                || (normalized.indexOf("%2E") >= 0)
                || (normalized.indexOf("%5C") >= 0)
                || (normalized.indexOf("%2f") >= 0)
                || (normalized.indexOf("%2e") >= 0)
                || (normalized.indexOf("%5c") >= 0)) {
            return null;
        }

        if (normalized.equals("/.")) {
            return "/";
        }

        // Normalize the slashes and add leading slash if necessary
        if (normalized.indexOf('\\') >= 0) {
            normalized = normalized.replace('\\', '/');
        }
        if (!normalized.startsWith("/")) {
            normalized = "/" + normalized;
        }

        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0) {
                break;
            }
            normalized = normalized.substring(0, index) + normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0) {
                break;
            }
            normalized = normalized.substring(0, index) + normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0) {
                break;
            }
            if (index == 0) {
                return (null);  // Trying to go outside our context
            }
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
        }

        // Declare occurrences of "/..." (three or more dots) to be invalid (on some Windows platforms this walks the directory tree!!!)
        if (normalized.indexOf("/...") >= 0) {
            return null;
        }

        // Return the normalized path that we have completed
        return normalized;
    }
}
