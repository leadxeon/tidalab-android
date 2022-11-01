package com.facebook.react.modules.network;

import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
/* loaded from: classes.dex */
public final class RequestBodyUtil$1 extends RequestBody {
    public final /* synthetic */ InputStream val$inputStream;
    public final /* synthetic */ MediaType val$mediaType;

    public RequestBodyUtil$1(MediaType mediaType, InputStream inputStream) {
        this.val$mediaType = mediaType;
        this.val$inputStream = inputStream;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        try {
            return this.val$inputStream.available();
        } catch (IOException unused) {
            return 0L;
        }
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.val$mediaType;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        Source source = null;
        try {
            source = Okio.source(this.val$inputStream);
            bufferedSink.writeAll(source);
        } finally {
            Util.closeQuietly(source);
        }
    }
}
