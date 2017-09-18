package com.kilogate.hello.tomcat.connector2.util;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * SocketInputStream
 *
 * @author fengquanwei
 * @create 12/09/2017 5:03 PM
 **/
public class SocketInputStream extends InputStream {
    // ------------------------------ 常量 ------------------------------
    private static final byte CR = (byte) '\r';
    private static final byte LF = (byte) '\n';
    private static final byte SP = (byte) ' ';
    private static final byte HT = (byte) '\t';
    private static final byte COLON = (byte) ':';
    private static final int LC_OFFSET = 'A' - 'a';

    // ------------------------------ 变量 ------------------------------
    private static StringManager stringManager = StringManager.getManager("com.kilogate.hello.tomcat");

    private InputStream input;
    private byte buffer[];
    private int count;
    private int pos;

    // ------------------------------ 构造函数 ------------------------------
    public SocketInputStream(InputStream input, int bufferSize) {
        this.input = input;
        this.buffer = new byte[bufferSize];
    }

    // ------------------------------ 公有方法 ------------------------------

    /**
     * 读请求行
     */
    public void readRequestLine(HttpRequestLine requestLine) throws IOException {
        if (requestLine.methodEnd != 0) {
            requestLine.recycle();
        }

        // 检查是否为空，跳过回车换行
        int ch = 0;
        do {
            try {
                ch = read();
            } catch (IOException e) {
                ch = -1;
            }
        } while ((ch == CR) || (ch == LF));
        if (ch == -1) {
            throw new EOFException(stringManager.getString("requestStream.readline.error"));
        }
        pos--;

        // 读取方法名
        int maxRead = requestLine.method.length;
        int readStart = pos;
        int readCount = 0;
        boolean space = false;
        while (!space) {
            // 扩充已满的缓冲区
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_METHOD_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(requestLine.method, 0, newBuffer, 0, maxRead);
                    requestLine.method = newBuffer;
                    maxRead = requestLine.method.length;
                } else {
                    throw new IOException(stringManager.getString("requestStream.readline.toolong"));
                }
            }
            processInternalBufferTail(readStart);
            if (buffer[pos] == SP) {
                space = true;
            }
            requestLine.method[readCount] = (char) buffer[pos];
            readCount++;
            pos++;
        }
        requestLine.methodEnd = readCount - 1;

        // 读取 URI
        maxRead = requestLine.uri.length;
        readStart = pos;
        readCount = 0;
        space = false;
        boolean eol = false;
        while (!space) {
            // 扩充已满的缓冲区
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_URI_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(requestLine.uri, 0, newBuffer, 0, maxRead);
                    requestLine.uri = newBuffer;
                    maxRead = requestLine.uri.length;
                } else {
                    throw new IOException(stringManager.getString("requestStream.readline.toolong"));
                }
            }
            processInternalBufferTail(readStart);
            if (buffer[pos] == SP) {
                space = true;
            } else if ((buffer[pos] == CR) || (buffer[pos] == LF)) {
                // HTTP/0.9 style request
                eol = true;
                space = true;
            }
            requestLine.uri[readCount] = (char) buffer[pos];
            readCount++;
            pos++;
        }
        requestLine.uriEnd = readCount - 1;

        // 读取协议
        maxRead = requestLine.protocol.length;
        readStart = pos;
        readCount = 0;
        while (!eol) {
            // 扩充已满的缓冲区
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_PROTOCOL_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(requestLine.protocol, 0, newBuffer, 0, maxRead);
                    requestLine.protocol = newBuffer;
                    maxRead = requestLine.protocol.length;
                } else {
                    throw new IOException(stringManager.getString("requestStream.readline.toolong"));
                }
            }
            processInternalBufferTail(readStart);
            if (buffer[pos] == CR) {
                // Skip CR.
            } else if (buffer[pos] == LF) {
                eol = true;
            } else {
                requestLine.protocol[readCount] = (char) buffer[pos];
                readCount++;
            }
            pos++;
        }
        requestLine.protocolEnd = readCount;
    }

    /**
     * 读请求头
     */
    public void readHeader(HttpHeader header) throws IOException {
        if (header.nameEnd != 0) {
            header.recycle();
        }

        // 检查是否为空，跳过回车换行
        int chr = read();
        if ((chr == CR) || (chr == LF)) {
            if (chr == CR) {
                read();
            }
            header.nameEnd = 0;
            header.valueEnd = 0;
            return;
        } else {
            pos--;
        }

        // 读取请求头名
        int maxRead = header.name.length;
        int readStart = pos;
        int readCount = 0;
        boolean colon = false;
        while (!colon) {
            extendFullBuffer(header, readCount, maxRead, HttpHeader.MAX_NAME_SIZE);
            processInternalBufferTail(readStart);
            if (buffer[pos] == COLON) {
                colon = true;
            }
            char val = (char) buffer[pos];
            if ((val >= 'A') && (val <= 'Z')) {
                val = (char) (val - LC_OFFSET);
            }
            header.name[readCount] = val;
            readCount++;
            pos++;
        }
        header.nameEnd = readCount - 1;

        // 读取请求头值
        maxRead = header.value.length;
        readStart = pos;
        readCount = 0;
        boolean eol = false;
        boolean validLine = true;
        while (validLine) {
            boolean space = true;
            // Skipping spaces Note : Only leading white spaces are removed. Trailing white spaces are not.
            while (space) {
                processInternalBufferTail(readStart);
                if ((buffer[pos] == SP) || (buffer[pos] == HT)) {
                    pos++;
                } else {
                    space = false;
                }
            }
            while (!eol) {
                extendFullBuffer(header, readCount, maxRead, HttpHeader.MAX_VALUE_SIZE);
                processInternalBufferTail(readStart);
                if (buffer[pos] == CR) {
                } else if (buffer[pos] == LF) {
                    eol = true;
                } else {
                    int ch = buffer[pos] & 0xff;
                    header.value[readCount] = (char) ch;
                    readCount++;
                }
                pos++;
            }
            int nextChr = read();
            if ((nextChr != SP) && (nextChr != HT)) {
                pos--;
                validLine = false;
            } else {
                eol = false;
                extendFullBuffer(header, readCount, maxRead, HttpHeader.MAX_VALUE_SIZE);
                header.value[readCount] = ' ';
                readCount++;
            }
        }
        header.valueEnd = readCount;
    }

    public int read() throws IOException {
        if (pos >= count) {
            pos = 0;
            count = 0;
            int read = input.read(buffer, 0, buffer.length);
            if (read > 0) {
                count = read;
            }
            if (pos >= count) {
                return -1;
            }
        }
        return buffer[pos++] & 0xff;
    }

    public int available() throws IOException {
        return (count - pos) + input.available();
    }

    public void close() throws IOException {
        if (input == null) {
            return;
        }
        input.close();
        input = null;
        buffer = null;
    }

    // ------------------------------ 私有方法 ------------------------------

    // 扩充已满缓冲区
    private void extendFullBuffer(HttpHeader header, int readCount, int maxRead, int maxSize) throws IOException {
        if (readCount >= maxRead) {
            if ((2 * maxRead) <= maxSize) {
                char[] newBuffer = new char[2 * maxRead];
                System.arraycopy(header.value, 0, newBuffer, 0, maxRead);
                header.value = newBuffer;
                maxRead = header.value.length;
            } else {
                throw new IOException(stringManager.getString("requestStream.readline.toolong"));
            }
        }
    }

    // 处理内部缓冲的尾部
    private void processInternalBufferTail(int readStart) throws IOException {
        if (pos >= count) {
            int val = read();
            if (val == -1) {
                throw new IOException(stringManager.getString("requestStream.readline.error"));
            }
            pos = 0;
            readStart = 0;
        }
    }
}
