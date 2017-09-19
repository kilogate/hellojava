package com.kilogate.hello.tomcat.connector2.connector;

import com.kilogate.hello.tomcat.connector2.util.Enumerator;
import com.kilogate.hello.tomcat.connector2.util.ParameterMap;
import com.kilogate.hello.tomcat.connector2.util.RequestStream;
import com.kilogate.hello.tomcat.connector2.util.RequestUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * HTTP 请求
 *
 * @author fengquanwei
 * @create 12/09/2017 10:15 AM
 **/
public class HttpRequest implements HttpServletRequest {
    // ------------------------------ 变量 -----------------------------
    // 日期格式化
    protected SimpleDateFormat formats[] = {
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US),
            new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
            new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US)
    };

    // 用于返回空集合使用，不要填充任何值
    protected static ArrayList empty = new ArrayList();

    // Socket InputStream
    private InputStream input;

    // 请求头 HashMap<String, ArrayList<String>>
    protected HashMap headers = new HashMap();

    // Cookies
    protected ArrayList cookies = new ArrayList();

    // 请求参数
    protected ParameterMap parameters = null;
    // 请求参数是否已解析
    protected boolean parsed = false;

    protected HashMap attributes = new HashMap(); // The request attributes for this request.
    protected BufferedReader reader = null; // The reader that has been returned by <code>getReader</code>, if any.
    protected ServletInputStream stream = null; // The ServletInputStream that has been returned by <code>getInputStream()</code>, if any.

    private String contentType;
    private int contentLength;
    private InetAddress inetAddress;
    private String method;
    private String protocol;
    private String queryString;
    private String requestURI;
    private String serverName;
    private int serverPort;
    private Socket socket;
    private boolean requestedSessionCookie;
    private String requestedSessionId;
    private boolean requestedSessionURL;

    private String contextPath = ""; // The context path for this request.
    private String pathInfo = null;
    private String authorization = null; // The authorization credentials sent with this Request.

    // ------------------------------ 构造函数 ------------------------------

    public HttpRequest(InputStream input) {
        this.input = input;
    }

    // ------------------------------ 公有方法 ------------------------------

    public void addHeader(String name, String value) {
        name = name.toLowerCase();
        synchronized (headers) {
            ArrayList values = (ArrayList) headers.get(name);
            if (values == null) {
                values = new ArrayList();
                headers.put(name, values);
            }
            values.add(value);
        }
    }

    public void addCookie(Cookie cookie) {
        synchronized (cookies) {
            cookies.add(cookie);
        }
    }

    public ServletInputStream createInputStream() throws IOException {
        return (new RequestStream(this));
    }

    public InputStream getStream() {
        return input;
    }

    // ------------------------------ 受保护方法 ------------------------------

    /**
     * 解析参数
     */
    protected void parseParameters() {
        if (parsed) {
            return;
        }

        ParameterMap results = parameters;
        if (results == null) {
            results = new ParameterMap();
        }

        // 先解锁
        results.setLocked(false);

        // 获取字符编码方式
        String encoding = getCharacterEncoding();
        if (encoding == null) {
            encoding = "UTF-8";
        }

        // 解析查询字符串中的参数
        String queryString = getQueryString();
        try {
            RequestUtil.parseParameters(results, queryString.getBytes(encoding), encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 解析请求主体中的参数
        String contentType = getContentType();
        if (contentType == null) {
            contentType = "";
        }

        int semicolon = contentType.indexOf(';');
        if (semicolon >= 0) {
            contentType = contentType.substring(0, semicolon).trim();
        } else {
            contentType = contentType.trim();
        }

        if ("POST".equals(getMethod()) && (getContentLength() > 0) && "application/x-www-form-urlencoded".equals(contentType)) {
            try {
                int max = getContentLength();
                int len = 0;
                byte buf[] = new byte[getContentLength()];
                ServletInputStream is = getInputStream();
                while (len < max) {
                    int next = is.read(buf, len, max - len);
                    if (next < 0) {
                        break;
                    }
                    len += next;
                }
                is.close();
                if (len < max) {
                    throw new RuntimeException("Content length mismatch");
                }
                RequestUtil.parseParameters(results, buf, encoding);
            } catch (UnsupportedEncodingException ue) {
                ue.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException("Content read fail");
            }
        }

        results.setLocked(true);
        parsed = true;
        parameters = results;
    }

    // ------------------------------ 覆盖方法 ------------------------------
    @Override
    public Object getAttribute(String name) {
        synchronized (attributes) {
            return (attributes.get(name));
        }
    }

    @Override
    public Enumeration getAttributeNames() {
        synchronized (attributes) {
            return (new Enumerator(attributes.keySet()));
        }
    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public String getContextPath() {
        return contextPath;
    }

    @Override
    public Cookie[] getCookies() {
        synchronized (cookies) {
            if (cookies.size() < 1) {
                return (null);
            }
            Cookie results[] = new Cookie[cookies.size()];
            return ((Cookie[]) cookies.toArray(results));
        }
    }

    @Override
    public long getDateHeader(String name) {
        String value = getHeader(name);
        if (value == null) {
            return (-1L);
        }

        value += " ";
        for (int i = 0; i < formats.length; i++) {
            try {
                Date date = formats[i].parse(value);
                return (date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(value);
    }

    @Override
    public String getHeader(String name) {
        name = name.toLowerCase();
        synchronized (headers) {
            ArrayList values = (ArrayList) headers.get(name);
            if (values != null) {
                return ((String) values.get(0));
            } else {
                return null;
            }
        }
    }

    @Override
    public Enumeration getHeaderNames() {
        synchronized (headers) {
            return (new Enumerator(headers.keySet()));
        }
    }

    @Override
    public Enumeration getHeaders(String name) {
        name = name.toLowerCase();
        synchronized (headers) {
            ArrayList values = (ArrayList) headers.get(name);
            if (values != null) {
                return (new Enumerator(values));
            } else {
                return (new Enumerator(empty));
            }
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (reader != null) {
            throw new IllegalStateException("getInputStream has been called");
        }

        if (stream == null) {
            stream = createInputStream();
        }
        return stream;
    }

    @Override
    public int getIntHeader(String name) {
        String value = getHeader(name);
        if (value == null) {
            return (-1);
        } else {
            return (Integer.parseInt(value));
        }
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getParameter(String name) {
        parseParameters();
        String values[] = (String[]) parameters.get(name);
        if (values != null) {
            return (values[0]);
        } else {
            return null;
        }
    }

    @Override
    public Map getParameterMap() {
        parseParameters();
        return this.parameters;
    }

    @Override
    public Enumeration getParameterNames() {
        parseParameters();
        return new Enumerator(parameters.keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        parseParameters();
        String values[] = (String[]) parameters.get(name);
        if (values != null) {
            return (values);
        } else {
            return null;
        }
    }

    @Override
    public String getPathInfo() {
        return this.pathInfo;
    }

    @Override
    public String getProtocol() {
        return this.protocol;
    }

    @Override
    public String getQueryString() {
        return this.queryString;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (stream != null) {
            throw new IllegalStateException("getInputStream has been called.");
        }
        if (reader == null) {
            String encoding = getCharacterEncoding();
            if (encoding == null) {
                encoding = "UTF-8";
            }
            InputStreamReader isr = new InputStreamReader(createInputStream(), encoding);
            reader = new BufferedReader(isr);
        }
        return reader;
    }

    @Override
    public String getRequestURI() {
        return this.requestURI;
    }

    @Override
    public String getAuthType() {
        return null;
    }

    @Override
    public String getPathTranslated() {
        return null;
    }

    @Override
    public String getRemoteUser() {
        return null;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public String getRequestedSessionId() {
        return null;
    }

    @Override
    public StringBuffer getRequestURL() {
        return null;
    }

    @Override
    public String getServletPath() {
        return null;
    }

    @Override
    public HttpSession getSession(boolean b) {
        return null;
    }

    @Override
    public HttpSession getSession() {
        return null;
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return isRequestedSessionIdFromUrl();
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return false;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    // ------------------------------ 属性方法 ------------------------------

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setRequestedSessionCookie(boolean requestedSessionCookie) {
        this.requestedSessionCookie = requestedSessionCookie;
    }

    public void setRequestedSessionId(String requestedSessionId) {
        this.requestedSessionId = requestedSessionId;
    }

    public void setRequestedSessionURL(boolean requestedSessionURL) {
        this.requestedSessionURL = requestedSessionURL;
    }

    public void setContextPath(String contextPath) {
        if (contextPath == null) {
            this.contextPath = "";
        } else {
            this.contextPath = contextPath;
        }
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
