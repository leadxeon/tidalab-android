package okhttp3;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import okio.Timeout;
/* loaded from: classes.dex */
public final class RealCall implements Call {
    public final OkHttpClient client;
    private EventListener eventListener;
    private boolean executed;
    public final boolean forWebSocket;
    public final Request originalRequest;
    public final RetryAndFollowUpInterceptor retryAndFollowUpInterceptor;
    public final AsyncTimeout timeout;

    /* loaded from: classes.dex */
    public final class AsyncCall extends NamedRunnable {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Callback responseCallback;

        public AsyncCall(Callback callback) {
            super("OkHttp %s", RealCall.this.redactedUrl());
            this.responseCallback = callback;
        }

        @Override // okhttp3.internal.NamedRunnable
        public void execute() {
            IOException e;
            Response responseWithInterceptorChain;
            RealCall.this.timeout.enter();
            boolean z = true;
            try {
                try {
                    responseWithInterceptorChain = RealCall.this.getResponseWithInterceptorChain();
                } catch (IOException e2) {
                    e = e2;
                    z = false;
                }
                try {
                    if (RealCall.this.retryAndFollowUpInterceptor.isCanceled()) {
                        this.responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
                    } else {
                        this.responseCallback.onResponse(RealCall.this, responseWithInterceptorChain);
                    }
                } catch (IOException e3) {
                    e = e3;
                    IOException timeoutExit = RealCall.this.timeoutExit(e);
                    if (z) {
                        Platform platform = Platform.get();
                        platform.log(4, "Callback failure for " + RealCall.this.toLoggableString(), timeoutExit);
                    } else {
                        RealCall.this.eventListener.callFailed(RealCall.this, timeoutExit);
                        this.responseCallback.onFailure(RealCall.this, timeoutExit);
                    }
                }
            } finally {
                RealCall.this.client.dispatcher().finished(this);
            }
        }

        public void executeOn(ExecutorService executorService) {
            try {
                try {
                    executorService.execute(this);
                } catch (RejectedExecutionException e) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                    interruptedIOException.initCause(e);
                    RealCall.this.eventListener.callFailed(RealCall.this, interruptedIOException);
                    this.responseCallback.onFailure(RealCall.this, interruptedIOException);
                    RealCall.this.client.dispatcher().finished(this);
                }
            } catch (Throwable th) {
                RealCall.this.client.dispatcher().finished(this);
                throw th;
            }
        }

        public RealCall get() {
            return RealCall.this;
        }

        public String host() {
            return RealCall.this.originalRequest.url().host();
        }

        public Request request() {
            return RealCall.this.originalRequest;
        }
    }

    private RealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        this.client = okHttpClient;
        this.originalRequest = request;
        this.forWebSocket = z;
        this.retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(okHttpClient, z);
        AsyncTimeout asyncTimeout = new AsyncTimeout() { // from class: okhttp3.RealCall.1
            @Override // okio.AsyncTimeout
            public void timedOut() {
                RealCall.this.cancel();
            }
        };
        this.timeout = asyncTimeout;
        asyncTimeout.timeout(okHttpClient.callTimeoutMillis(), TimeUnit.MILLISECONDS);
    }

    private void captureCallStackTrace() {
        this.retryAndFollowUpInterceptor.setCallStackTrace(Platform.get().getStackTraceForCloseable("response.body().close()"));
    }

    public static RealCall newRealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        RealCall realCall = new RealCall(okHttpClient, request, z);
        realCall.eventListener = okHttpClient.eventListenerFactory().create(realCall);
        return realCall;
    }

    @Override // okhttp3.Call
    public void cancel() {
        this.retryAndFollowUpInterceptor.cancel();
    }

    @Override // okhttp3.Call
    public void enqueue(Callback callback) {
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        captureCallStackTrace();
        this.eventListener.callStart(this);
        this.client.dispatcher().enqueue(new AsyncCall(callback));
    }

    @Override // okhttp3.Call
    public Response execute() throws IOException {
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        captureCallStackTrace();
        this.timeout.enter();
        this.eventListener.callStart(this);
        try {
            try {
                this.client.dispatcher().executed(this);
                Response responseWithInterceptorChain = getResponseWithInterceptorChain();
                if (responseWithInterceptorChain != null) {
                    return responseWithInterceptorChain;
                }
                throw new IOException("Canceled");
            } catch (IOException e) {
                IOException timeoutExit = timeoutExit(e);
                this.eventListener.callFailed(this, timeoutExit);
                throw timeoutExit;
            }
        } finally {
            this.client.dispatcher().finished(this);
        }
    }

    public Response getResponseWithInterceptorChain() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.client.interceptors());
        arrayList.add(this.retryAndFollowUpInterceptor);
        arrayList.add(new BridgeInterceptor(this.client.cookieJar()));
        arrayList.add(new CacheInterceptor(this.client.internalCache()));
        arrayList.add(new ConnectInterceptor(this.client));
        if (!this.forWebSocket) {
            arrayList.addAll(this.client.networkInterceptors());
        }
        arrayList.add(new CallServerInterceptor(this.forWebSocket));
        return new RealInterceptorChain(arrayList, null, null, null, 0, this.originalRequest, this, this.eventListener, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis()).proceed(this.originalRequest);
    }

    @Override // okhttp3.Call
    public boolean isCanceled() {
        return this.retryAndFollowUpInterceptor.isCanceled();
    }

    @Override // okhttp3.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public String redactedUrl() {
        return this.originalRequest.url().redact();
    }

    @Override // okhttp3.Call
    public Request request() {
        return this.originalRequest;
    }

    public StreamAllocation streamAllocation() {
        return this.retryAndFollowUpInterceptor.streamAllocation();
    }

    @Override // okhttp3.Call
    public Timeout timeout() {
        return this.timeout;
    }

    public IOException timeoutExit(IOException iOException) {
        if (!this.timeout.exit()) {
            return iOException;
        }
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public String toLoggableString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : HttpUrl.FRAGMENT_ENCODE_SET);
        sb.append(this.forWebSocket ? "web socket" : "call");
        sb.append(" to ");
        sb.append(redactedUrl());
        return sb.toString();
    }

    @Override // okhttp3.Call
    public RealCall clone() {
        return newRealCall(this.client, this.originalRequest, this.forWebSocket);
    }
}
