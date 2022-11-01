package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.RealBufferedSink;
import okio.RealBufferedSource;
import okio.Sink;
import okio.Source;
/* loaded from: classes.dex */
public final class Cache implements Closeable, Flushable {
    private static final int ENTRY_BODY = 1;
    private static final int ENTRY_COUNT = 2;
    private static final int ENTRY_METADATA = 0;
    private static final int VERSION = 201105;
    public final DiskLruCache cache;
    private int hitCount;
    public final InternalCache internalCache;
    private int networkCount;
    private int requestCount;
    public int writeAbortCount;
    public int writeSuccessCount;

    /* loaded from: classes.dex */
    public final class CacheRequestImpl implements CacheRequest {
        private Sink body;
        private Sink cacheOut;
        public boolean done;
        private final DiskLruCache.Editor editor;

        public CacheRequestImpl(final DiskLruCache.Editor editor) {
            this.editor = editor;
            Sink newSink = editor.newSink(1);
            this.cacheOut = newSink;
            this.body = new ForwardingSink(newSink) { // from class: okhttp3.Cache.CacheRequestImpl.1
                @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    synchronized (Cache.this) {
                        CacheRequestImpl cacheRequestImpl = CacheRequestImpl.this;
                        if (!cacheRequestImpl.done) {
                            cacheRequestImpl.done = true;
                            Cache.this.writeSuccessCount++;
                            super.close();
                            editor.commit();
                        }
                    }
                }
            };
        }

        @Override // okhttp3.internal.cache.CacheRequest
        public void abort() {
            synchronized (Cache.this) {
                if (!this.done) {
                    this.done = true;
                    Cache.this.writeAbortCount++;
                    Util.closeQuietly(this.cacheOut);
                    try {
                        this.editor.abort();
                    } catch (IOException unused) {
                    }
                }
            }
        }

        @Override // okhttp3.internal.cache.CacheRequest
        public Sink body() {
            return this.body;
        }
    }

    /* loaded from: classes.dex */
    public static class CacheResponseBody extends ResponseBody {
        private final BufferedSource bodySource;
        private final String contentLength;
        private final String contentType;
        public final DiskLruCache.Snapshot snapshot;

        public CacheResponseBody(final DiskLruCache.Snapshot snapshot, String str, String str2) {
            this.snapshot = snapshot;
            this.contentType = str;
            this.contentLength = str2;
            ForwardingSource forwardingSource = new ForwardingSource(snapshot.getSource(1)) { // from class: okhttp3.Cache.CacheResponseBody.1
                @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    snapshot.close();
                    super.close();
                }
            };
            Logger logger = Okio.logger;
            this.bodySource = new RealBufferedSource(forwardingSource);
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            try {
                String str = this.contentLength;
                if (str != null) {
                    return Long.parseLong(str);
                }
                return -1L;
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            String str = this.contentType;
            if (str != null) {
                return MediaType.parse(str);
            }
            return null;
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return this.bodySource;
        }
    }

    public Cache(File file, long j) {
        this(file, j, FileSystem.SYSTEM);
    }

    private void abortQuietly(DiskLruCache.Editor editor) {
        if (editor != null) {
            try {
                editor.abort();
            } catch (IOException unused) {
            }
        }
    }

    public static String key(HttpUrl httpUrl) {
        return ByteString.encodeUtf8(httpUrl.toString()).digest("MD5").hex();
    }

    public static int readInt(BufferedSource bufferedSource) throws IOException {
        try {
            long readDecimalLong = bufferedSource.readDecimalLong();
            String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
            if (readDecimalLong >= 0 && readDecimalLong <= 2147483647L && readUtf8LineStrict.isEmpty()) {
                return (int) readDecimalLong;
            }
            throw new IOException("expected an int but was \"" + readDecimalLong + readUtf8LineStrict + "\"");
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.cache.close();
    }

    public void delete() throws IOException {
        this.cache.delete();
    }

    public File directory() {
        return this.cache.getDirectory();
    }

    public void evictAll() throws IOException {
        this.cache.evictAll();
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.cache.flush();
    }

    public Response get(Request request) {
        try {
            DiskLruCache.Snapshot snapshot = this.cache.get(key(request.url()));
            if (snapshot == null) {
                return null;
            }
            try {
                Entry entry = new Entry(snapshot.getSource(0));
                Response response = entry.response(snapshot);
                if (entry.matches(request, response)) {
                    return response;
                }
                Util.closeQuietly(response.body());
                return null;
            } catch (IOException unused) {
                Util.closeQuietly(snapshot);
                return null;
            }
        } catch (IOException unused2) {
        }
    }

    public synchronized int hitCount() {
        return this.hitCount;
    }

    public void initialize() throws IOException {
        this.cache.initialize();
    }

    public boolean isClosed() {
        return this.cache.isClosed();
    }

    public long maxSize() {
        return this.cache.getMaxSize();
    }

    public synchronized int networkCount() {
        return this.networkCount;
    }

    public CacheRequest put(Response response) {
        DiskLruCache.Editor editor;
        String method = response.request().method();
        if (HttpMethod.invalidatesCache(response.request().method())) {
            try {
                remove(response.request());
            } catch (IOException unused) {
            }
            return null;
        } else if (!method.equals("GET") || HttpHeaders.hasVaryAll(response)) {
            return null;
        } else {
            Entry entry = new Entry(response);
            try {
                editor = this.cache.edit(key(response.request().url()));
                if (editor == null) {
                    return null;
                }
                try {
                    entry.writeTo(editor);
                    return new CacheRequestImpl(editor);
                } catch (IOException unused2) {
                    abortQuietly(editor);
                    return null;
                }
            } catch (IOException unused3) {
                editor = null;
            }
        }
    }

    public void remove(Request request) throws IOException {
        this.cache.remove(key(request.url()));
    }

    public synchronized int requestCount() {
        return this.requestCount;
    }

    public long size() throws IOException {
        return this.cache.size();
    }

    public synchronized void trackConditionalCacheHit() {
        this.hitCount++;
    }

    public synchronized void trackResponse(CacheStrategy cacheStrategy) {
        this.requestCount++;
        if (cacheStrategy.networkRequest != null) {
            this.networkCount++;
        } else if (cacheStrategy.cacheResponse != null) {
            this.hitCount++;
        }
    }

    public void update(Response response, Response response2) {
        DiskLruCache.Editor editor;
        Entry entry = new Entry(response2);
        try {
            editor = ((CacheResponseBody) response.body()).snapshot.edit();
            if (editor != null) {
                try {
                    entry.writeTo(editor);
                    editor.commit();
                } catch (IOException unused) {
                    abortQuietly(editor);
                }
            }
        } catch (IOException unused2) {
            editor = null;
        }
    }

    public Iterator<String> urls() throws IOException {
        return new Iterator<String>() { // from class: okhttp3.Cache.2
            public boolean canRemove;
            public final Iterator<DiskLruCache.Snapshot> delegate;
            public String nextUrl;

            {
                this.delegate = Cache.this.cache.snapshots();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.nextUrl != null) {
                    return true;
                }
                this.canRemove = false;
                while (this.delegate.hasNext()) {
                    DiskLruCache.Snapshot next = this.delegate.next();
                    try {
                        Source source = next.getSource(0);
                        Logger logger = Okio.logger;
                        this.nextUrl = new RealBufferedSource(source).readUtf8LineStrict();
                        return true;
                    } catch (IOException unused) {
                    } finally {
                        next.close();
                    }
                }
                return false;
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.canRemove) {
                    this.delegate.remove();
                    return;
                }
                throw new IllegalStateException("remove() before next()");
            }

            @Override // java.util.Iterator
            public String next() {
                if (hasNext()) {
                    String str = this.nextUrl;
                    this.nextUrl = null;
                    this.canRemove = true;
                    return str;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public synchronized int writeAbortCount() {
        return this.writeAbortCount;
    }

    public synchronized int writeSuccessCount() {
        return this.writeSuccessCount;
    }

    public Cache(File file, long j, FileSystem fileSystem) {
        this.internalCache = new InternalCache() { // from class: okhttp3.Cache.1
            @Override // okhttp3.internal.cache.InternalCache
            public Response get(Request request) throws IOException {
                return Cache.this.get(request);
            }

            @Override // okhttp3.internal.cache.InternalCache
            public CacheRequest put(Response response) throws IOException {
                return Cache.this.put(response);
            }

            @Override // okhttp3.internal.cache.InternalCache
            public void remove(Request request) throws IOException {
                Cache.this.remove(request);
            }

            @Override // okhttp3.internal.cache.InternalCache
            public void trackConditionalCacheHit() {
                Cache.this.trackConditionalCacheHit();
            }

            @Override // okhttp3.internal.cache.InternalCache
            public void trackResponse(CacheStrategy cacheStrategy) {
                Cache.this.trackResponse(cacheStrategy);
            }

            @Override // okhttp3.internal.cache.InternalCache
            public void update(Response response, Response response2) {
                Cache.this.update(response, response2);
            }
        };
        this.cache = DiskLruCache.create(fileSystem, file, VERSION, 2, j);
    }

    /* loaded from: classes.dex */
    public static final class Entry {
        private final int code;
        private final Handshake handshake;
        private final String message;
        private final Protocol protocol;
        private final long receivedResponseMillis;
        private final String requestMethod;
        private final Headers responseHeaders;
        private final long sentRequestMillis;
        private final String url;
        private final Headers varyHeaders;
        private static final String SENT_MILLIS = Platform.get().getPrefix() + "-Sent-Millis";
        private static final String RECEIVED_MILLIS = Platform.get().getPrefix() + "-Received-Millis";

        public Entry(Source source) throws IOException {
            TlsVersion tlsVersion;
            try {
                Logger logger = Okio.logger;
                RealBufferedSource realBufferedSource = new RealBufferedSource(source);
                this.url = realBufferedSource.readUtf8LineStrict();
                this.requestMethod = realBufferedSource.readUtf8LineStrict();
                Headers.Builder builder = new Headers.Builder();
                int readInt = Cache.readInt(realBufferedSource);
                for (int i = 0; i < readInt; i++) {
                    builder.addLenient(realBufferedSource.readUtf8LineStrict());
                }
                this.varyHeaders = builder.build();
                StatusLine parse = StatusLine.parse(realBufferedSource.readUtf8LineStrict());
                this.protocol = parse.protocol;
                this.code = parse.code;
                this.message = parse.message;
                Headers.Builder builder2 = new Headers.Builder();
                int readInt2 = Cache.readInt(realBufferedSource);
                for (int i2 = 0; i2 < readInt2; i2++) {
                    builder2.addLenient(realBufferedSource.readUtf8LineStrict());
                }
                String str = SENT_MILLIS;
                String str2 = builder2.get(str);
                String str3 = RECEIVED_MILLIS;
                String str4 = builder2.get(str3);
                builder2.removeAll(str);
                builder2.removeAll(str3);
                long j = 0;
                this.sentRequestMillis = str2 != null ? Long.parseLong(str2) : 0L;
                this.receivedResponseMillis = str4 != null ? Long.parseLong(str4) : j;
                this.responseHeaders = builder2.build();
                if (isHttps()) {
                    String readUtf8LineStrict = realBufferedSource.readUtf8LineStrict();
                    if (readUtf8LineStrict.length() <= 0) {
                        CipherSuite forJavaName = CipherSuite.forJavaName(realBufferedSource.readUtf8LineStrict());
                        List<Certificate> readCertificateList = readCertificateList(realBufferedSource);
                        List<Certificate> readCertificateList2 = readCertificateList(realBufferedSource);
                        if (!realBufferedSource.exhausted()) {
                            tlsVersion = TlsVersion.forJavaName(realBufferedSource.readUtf8LineStrict());
                        } else {
                            tlsVersion = TlsVersion.SSL_3_0;
                        }
                        this.handshake = Handshake.get(tlsVersion, forJavaName, readCertificateList, readCertificateList2);
                    } else {
                        throw new IOException("expected \"\" but was \"" + readUtf8LineStrict + "\"");
                    }
                } else {
                    this.handshake = null;
                }
            } finally {
                source.close();
            }
        }

        private boolean isHttps() {
            return this.url.startsWith("https://");
        }

        private List<Certificate> readCertificateList(BufferedSource bufferedSource) throws IOException {
            int readInt = Cache.readInt(bufferedSource);
            if (readInt == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(readInt);
                for (int i = 0; i < readInt; i++) {
                    String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
                    Buffer buffer = new Buffer();
                    buffer.write(ByteString.decodeBase64(readUtf8LineStrict));
                    arrayList.add(instance.generateCertificate(new Buffer.AnonymousClass2()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private void writeCertList(BufferedSink bufferedSink, List<Certificate> list) throws IOException {
            try {
                bufferedSink.writeDecimalLong(list.size()).writeByte(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bufferedSink.writeUtf8(ByteString.of(list.get(i).getEncoded()).base64()).writeByte(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public boolean matches(Request request, Response response) {
            return this.url.equals(request.url().toString()) && this.requestMethod.equals(request.method()) && HttpHeaders.varyMatches(response, this.varyHeaders, request);
        }

        public Response response(DiskLruCache.Snapshot snapshot) {
            String str = this.responseHeaders.get("Content-Type");
            String str2 = this.responseHeaders.get("Content-Length");
            return new Response.Builder().request(new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build()).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(snapshot, str, str2)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
        }

        public void writeTo(DiskLruCache.Editor editor) throws IOException {
            Sink newSink = editor.newSink(0);
            Logger logger = Okio.logger;
            RealBufferedSink realBufferedSink = new RealBufferedSink(newSink);
            realBufferedSink.writeUtf8(this.url);
            realBufferedSink.writeByte(10);
            realBufferedSink.writeUtf8(this.requestMethod);
            realBufferedSink.writeByte(10);
            realBufferedSink.writeDecimalLong(this.varyHeaders.size());
            realBufferedSink.writeByte(10);
            int size = this.varyHeaders.size();
            for (int i = 0; i < size; i++) {
                realBufferedSink.writeUtf8(this.varyHeaders.name(i));
                realBufferedSink.writeUtf8(": ");
                realBufferedSink.writeUtf8(this.varyHeaders.value(i));
                realBufferedSink.writeByte(10);
            }
            realBufferedSink.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString());
            realBufferedSink.writeByte(10);
            realBufferedSink.writeDecimalLong(this.responseHeaders.size() + 2);
            realBufferedSink.writeByte(10);
            int size2 = this.responseHeaders.size();
            for (int i2 = 0; i2 < size2; i2++) {
                realBufferedSink.writeUtf8(this.responseHeaders.name(i2));
                realBufferedSink.writeUtf8(": ");
                realBufferedSink.writeUtf8(this.responseHeaders.value(i2));
                realBufferedSink.writeByte(10);
            }
            realBufferedSink.writeUtf8(SENT_MILLIS);
            realBufferedSink.writeUtf8(": ");
            realBufferedSink.writeDecimalLong(this.sentRequestMillis);
            realBufferedSink.writeByte(10);
            realBufferedSink.writeUtf8(RECEIVED_MILLIS);
            realBufferedSink.writeUtf8(": ");
            realBufferedSink.writeDecimalLong(this.receivedResponseMillis);
            realBufferedSink.writeByte(10);
            if (isHttps()) {
                realBufferedSink.writeByte(10);
                realBufferedSink.writeUtf8(this.handshake.cipherSuite().javaName());
                realBufferedSink.writeByte(10);
                writeCertList(realBufferedSink, this.handshake.peerCertificates());
                writeCertList(realBufferedSink, this.handshake.localCertificates());
                realBufferedSink.writeUtf8(this.handshake.tlsVersion().javaName());
                realBufferedSink.writeByte(10);
            }
            realBufferedSink.close();
        }

        public Entry(Response response) {
            this.url = response.request().url().toString();
            this.varyHeaders = HttpHeaders.varyHeaders(response);
            this.requestMethod = response.request().method();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.responseHeaders = response.headers();
            this.handshake = response.handshake();
            this.sentRequestMillis = response.sentRequestAtMillis();
            this.receivedResponseMillis = response.receivedResponseAtMillis();
        }
    }
}
