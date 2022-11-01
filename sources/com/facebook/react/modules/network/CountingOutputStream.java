package com.facebook.react.modules.network;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes.dex */
public class CountingOutputStream extends FilterOutputStream {
    public long mCount = 0;

    public CountingOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ((FilterOutputStream) this).out.close();
    }
}
