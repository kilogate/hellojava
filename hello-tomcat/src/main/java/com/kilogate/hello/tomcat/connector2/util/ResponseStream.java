package com.kilogate.hello.tomcat.connector2.util;

import com.kilogate.hello.tomcat.connector2.connector.HttpResponse;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * ResponseStream
 *
 * @author fengquanwei
 * @create 12/09/2017 10:15 AM
 **/
public class ResponseStream extends ServletOutputStream {
    // ------------------------------ 变量 ------------------------------
    protected HttpResponse response = null;

    protected boolean closed = false; // Has this stream been closed?
    protected boolean commit = false; // Should we commit the response when we are flushed?
    protected int count = 0; // The number of bytes which have already been written to this stream.
    protected int length = -1; // The content length past which we will not write, or -1 if there is no defined content length.

    // ------------------------------ 构造函数 ------------------------------
    public ResponseStream(HttpResponse response) {
        super();
        closed = false;
        commit = false;
        count = 0;
        this.response = response;
    }

    // ------------------------------ 公有方法 ------------------------------

    public void close() throws IOException {
        if (closed) {
            throw new IOException("responseStream.close.closed");
        }
        response.flushBuffer();
        closed = true;
    }

    public void flush() throws IOException {
        if (closed) {
            throw new IOException("responseStream.flush.closed");
        }
        if (commit) {
            response.flushBuffer();
        }
    }

    public void write(int b) throws IOException {
        if (closed) {
            throw new IOException("responseStream.write.closed");
        }
        if ((length > 0) && (count >= length)) {
            throw new IOException("responseStream.write.count");
        }
        response.write(b);
        count++;
    }

    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }

    public void write(byte b[], int off, int len) throws IOException {
        if (closed) {
            throw new IOException("responseStream.write.closed");
        }

        int actual = len;
        if ((length > 0) && ((count + len) >= length)) {
            actual = length - count;
        }
        response.write(b, off, actual);
        count += actual;
        if (actual < len) {
            throw new IOException("responseStream.write.count");
        }
    }

    // ------------------------------ 属性方法 ------------------------------
    public boolean getCommit() {
        return (this.commit);
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
    }
}

