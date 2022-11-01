package okhttp3.internal.http;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
/* loaded from: classes.dex */
public final class RealInterceptorChain implements Interceptor.Chain {
    private final Call call;
    private int calls;
    private final int connectTimeout;
    private final RealConnection connection;
    private final EventListener eventListener;
    private final HttpCodec httpCodec;
    private final int index;
    private final List<Interceptor> interceptors;
    private final int readTimeout;
    private final Request request;
    private final StreamAllocation streamAllocation;
    private final int writeTimeout;

    public RealInterceptorChain(List<Interceptor> list, StreamAllocation streamAllocation, HttpCodec httpCodec, RealConnection realConnection, int i, Request request, Call call, EventListener eventListener, int i2, int i3, int i4) {
        this.interceptors = list;
        this.connection = realConnection;
        this.streamAllocation = streamAllocation;
        this.httpCodec = httpCodec;
        this.index = i;
        this.request = request;
        this.call = call;
        this.eventListener = eventListener;
        this.connectTimeout = i2;
        this.readTimeout = i3;
        this.writeTimeout = i4;
    }

    @Override // okhttp3.Interceptor.Chain
    public Call call() {
        return this.call;
    }

    @Override // okhttp3.Interceptor.Chain
    public int connectTimeoutMillis() {
        return this.connectTimeout;
    }

    @Override // okhttp3.Interceptor.Chain
    public Connection connection() {
        return this.connection;
    }

    public EventListener eventListener() {
        return this.eventListener;
    }

    public HttpCodec httpStream() {
        return this.httpCodec;
    }

    @Override // okhttp3.Interceptor.Chain
    public Response proceed(Request request) throws IOException {
        return proceed(request, this.streamAllocation, this.httpCodec, this.connection);
    }

    @Override // okhttp3.Interceptor.Chain
    public int readTimeoutMillis() {
        return this.readTimeout;
    }

    @Override // okhttp3.Interceptor.Chain
    public Request request() {
        return this.request;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    @Override // okhttp3.Interceptor.Chain
    public Interceptor.Chain withConnectTimeout(int i, TimeUnit timeUnit) {
        return new RealInterceptorChain(this.interceptors, this.streamAllocation, this.httpCodec, this.connection, this.index, this.request, this.call, this.eventListener, Util.checkDuration("timeout", i, timeUnit), this.readTimeout, this.writeTimeout);
    }

    @Override // okhttp3.Interceptor.Chain
    public Interceptor.Chain withReadTimeout(int i, TimeUnit timeUnit) {
        return new RealInterceptorChain(this.interceptors, this.streamAllocation, this.httpCodec, this.connection, this.index, this.request, this.call, this.eventListener, this.connectTimeout, Util.checkDuration("timeout", i, timeUnit), this.writeTimeout);
    }

    @Override // okhttp3.Interceptor.Chain
    public Interceptor.Chain withWriteTimeout(int i, TimeUnit timeUnit) {
        return new RealInterceptorChain(this.interceptors, this.streamAllocation, this.httpCodec, this.connection, this.index, this.request, this.call, this.eventListener, this.connectTimeout, this.readTimeout, Util.checkDuration("timeout", i, timeUnit));
    }

    @Override // okhttp3.Interceptor.Chain
    public int writeTimeoutMillis() {
        return this.writeTimeout;
    }

    public Response proceed(Request request, StreamAllocation streamAllocation, HttpCodec httpCodec, RealConnection realConnection) throws IOException {
        if (this.index < this.interceptors.size()) {
            this.calls++;
            if (this.httpCodec != null && !this.connection.supportsUrl(request.url())) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("network interceptor ");
                outline13.append(this.interceptors.get(this.index - 1));
                outline13.append(" must retain the same host and port");
                throw new IllegalStateException(outline13.toString());
            } else if (this.httpCodec == null || this.calls <= 1) {
                RealInterceptorChain realInterceptorChain = new RealInterceptorChain(this.interceptors, streamAllocation, httpCodec, realConnection, this.index + 1, request, this.call, this.eventListener, this.connectTimeout, this.readTimeout, this.writeTimeout);
                Interceptor interceptor = this.interceptors.get(this.index);
                Response intercept = interceptor.intercept(realInterceptorChain);
                if (httpCodec != null && this.index + 1 < this.interceptors.size() && realInterceptorChain.calls != 1) {
                    throw new IllegalStateException("network interceptor " + interceptor + " must call proceed() exactly once");
                } else if (intercept == null) {
                    throw new NullPointerException("interceptor " + interceptor + " returned null");
                } else if (intercept.body() != null) {
                    return intercept;
                } else {
                    throw new IllegalStateException("interceptor " + interceptor + " returned a response with no body");
                }
            } else {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("network interceptor ");
                outline132.append(this.interceptors.get(this.index - 1));
                outline132.append(" must call proceed() exactly once");
                throw new IllegalStateException(outline132.toString());
            }
        } else {
            throw new AssertionError();
        }
    }
}
