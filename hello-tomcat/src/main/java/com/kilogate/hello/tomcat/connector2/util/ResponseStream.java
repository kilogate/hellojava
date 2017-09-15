package com.kilogate.hello.tomcat.connector2.util;

import com.kilogate.hello.tomcat.connector2.connector.HttpResponse;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ResponseStream
 *
 * @author fengquanwei
 * @create 12/09/2017 10:15 AM
 **/
public class ResponseStream extends ServletOutputStream {
    // ------------------------------ 变量 ------------------------------
    /**
     * Has this stream been closed?
     */
    protected boolean closed = false;

    /**
     * Should we commit the response when we are flushed?
     */
    protected boolean commit = false;

    /**
     * The number of bytes which have already been written to this stream.
     */
    protected int count = 0;

    /**
     * The content length past which we will not write, or -1 if there is no defined content length.
     */
    protected int length = -1;

    /**
     * The Response with which this input stream is associated.
     */
    protected HttpResponse response = null;

    /**
     * The underlying output stream to which we should write data.
     */
    protected OutputStream stream = null;

    // ------------------------------ 构造函数 ------------------------------
    public ResponseStream(HttpResponse response) {
        super();
        closed = false;
        commit = false;
        count = 0;
        this.response = response;
    }

    // ------------------------------ 公有方法 ------------------------------
    /**
     * Close this output stream, causing any buffered data to be flushed and
     * any further output data to throw an IOException.
     */
    public void close() throws IOException {
        if (closed) {
            throw new IOException("responseStream.close.closed");
        }
        response.flushBuffer();
        closed = true;
    }

    /**
     * Flush any buffered data for this output stream, which also causes the
     * response to be committed.
     */
    public void flush() throws IOException {
        if (closed) {
            throw new IOException("responseStream.flush.closed");
        }
        if (commit) {
            response.flushBuffer();
        }
    }

    /**
     * Write the specified byte to our output stream.
     *
     * @param b The byte to be written
     * @throws IOException if an input/output error occurs
     */
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

    // ------------------------------ 包内方法 ------------------------------
    /**
     * Has this response stream been closed?
     */
    boolean closed() {
        return (this.closed);
    }

    /**
     * Reset the count of bytes written to this stream to zero.
     */
    void reset() {
        count = 0;
    }

    // ------------------------------ 属性方法 ------------------------------
    public boolean getCommit() {

        return (this.commit);

    }

    public void setCommit(boolean commit) {

        this.commit = commit;

    }
}

