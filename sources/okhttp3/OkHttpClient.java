package okhttp3;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.proxy.NullProxySelector;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
/* loaded from: classes.dex */
public class OkHttpClient implements Cloneable, Call.Factory, WebSocket.Factory {
    public final Authenticator authenticator;
    public final Cache cache;
    public final int callTimeout;
    public final CertificateChainCleaner certificateChainCleaner;
    public final CertificatePinner certificatePinner;
    public final int connectTimeout;
    public final ConnectionPool connectionPool;
    public final List<ConnectionSpec> connectionSpecs;
    public final CookieJar cookieJar;
    public final Dispatcher dispatcher;
    public final Dns dns;
    public final EventListener.Factory eventListenerFactory;
    public final boolean followRedirects;
    public final boolean followSslRedirects;
    public final HostnameVerifier hostnameVerifier;
    public final List<Interceptor> interceptors;
    public final InternalCache internalCache;
    public final List<Interceptor> networkInterceptors;
    public final int pingInterval;
    public final List<Protocol> protocols;
    public final Proxy proxy;
    public final Authenticator proxyAuthenticator;
    public final ProxySelector proxySelector;
    public final int readTimeout;
    public final boolean retryOnConnectionFailure;
    public final SocketFactory socketFactory;
    public final SSLSocketFactory sslSocketFactory;
    public final int writeTimeout;
    public static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableList(Protocol.HTTP_2, Protocol.HTTP_1_1);
    public static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS = Util.immutableList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT);

    /* loaded from: classes.dex */
    public static final class Builder {
        public Authenticator authenticator;
        public Cache cache;
        public int callTimeout;
        public CertificateChainCleaner certificateChainCleaner;
        public CertificatePinner certificatePinner;
        public int connectTimeout;
        public ConnectionPool connectionPool;
        public List<ConnectionSpec> connectionSpecs;
        public CookieJar cookieJar;
        public Dispatcher dispatcher;
        public Dns dns;
        public EventListener.Factory eventListenerFactory;
        public boolean followRedirects;
        public boolean followSslRedirects;
        public HostnameVerifier hostnameVerifier;
        public final List<Interceptor> interceptors;
        public InternalCache internalCache;
        public final List<Interceptor> networkInterceptors;
        public int pingInterval;
        public List<Protocol> protocols;
        public Proxy proxy;
        public Authenticator proxyAuthenticator;
        public ProxySelector proxySelector;
        public int readTimeout;
        public boolean retryOnConnectionFailure;
        public SocketFactory socketFactory;
        public SSLSocketFactory sslSocketFactory;
        public int writeTimeout;

        public Builder() {
            this.interceptors = new ArrayList();
            this.networkInterceptors = new ArrayList();
            this.dispatcher = new Dispatcher();
            this.protocols = OkHttpClient.DEFAULT_PROTOCOLS;
            this.connectionSpecs = OkHttpClient.DEFAULT_CONNECTION_SPECS;
            this.eventListenerFactory = EventListener.factory(EventListener.NONE);
            ProxySelector proxySelector = ProxySelector.getDefault();
            this.proxySelector = proxySelector;
            if (proxySelector == null) {
                this.proxySelector = new NullProxySelector();
            }
            this.cookieJar = CookieJar.NO_COOKIES;
            this.socketFactory = SocketFactory.getDefault();
            this.hostnameVerifier = OkHostnameVerifier.INSTANCE;
            this.certificatePinner = CertificatePinner.DEFAULT;
            Authenticator authenticator = Authenticator.NONE;
            this.proxyAuthenticator = authenticator;
            this.authenticator = authenticator;
            this.connectionPool = new ConnectionPool();
            this.dns = Dns.SYSTEM;
            this.followSslRedirects = true;
            this.followRedirects = true;
            this.retryOnConnectionFailure = true;
            this.callTimeout = 0;
            this.connectTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
            this.pingInterval = 0;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (interceptor != null) {
                this.interceptors.add(interceptor);
                return this;
            }
            throw new IllegalArgumentException("interceptor == null");
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            if (interceptor != null) {
                this.networkInterceptors.add(interceptor);
                return this;
            }
            throw new IllegalArgumentException("interceptor == null");
        }

        public Builder authenticator(Authenticator authenticator) {
            Objects.requireNonNull(authenticator, "authenticator == null");
            this.authenticator = authenticator;
            return this;
        }

        public OkHttpClient build() {
            return new OkHttpClient(this);
        }

        public Builder cache(Cache cache) {
            this.cache = cache;
            this.internalCache = null;
            return this;
        }

        public Builder callTimeout(long j, TimeUnit timeUnit) {
            this.callTimeout = Util.checkDuration("timeout", j, timeUnit);
            return this;
        }

        public Builder certificatePinner(CertificatePinner certificatePinner) {
            Objects.requireNonNull(certificatePinner, "certificatePinner == null");
            this.certificatePinner = certificatePinner;
            return this;
        }

        public Builder connectTimeout(long j, TimeUnit timeUnit) {
            this.connectTimeout = Util.checkDuration("timeout", j, timeUnit);
            return this;
        }

        public Builder connectionPool(ConnectionPool connectionPool) {
            Objects.requireNonNull(connectionPool, "connectionPool == null");
            this.connectionPool = connectionPool;
            return this;
        }

        public Builder connectionSpecs(List<ConnectionSpec> list) {
            this.connectionSpecs = Util.immutableList(list);
            return this;
        }

        public Builder cookieJar(CookieJar cookieJar) {
            Objects.requireNonNull(cookieJar, "cookieJar == null");
            this.cookieJar = cookieJar;
            return this;
        }

        public Builder dispatcher(Dispatcher dispatcher) {
            if (dispatcher != null) {
                this.dispatcher = dispatcher;
                return this;
            }
            throw new IllegalArgumentException("dispatcher == null");
        }

        public Builder dns(Dns dns) {
            Objects.requireNonNull(dns, "dns == null");
            this.dns = dns;
            return this;
        }

        public Builder eventListener(EventListener eventListener) {
            Objects.requireNonNull(eventListener, "eventListener == null");
            this.eventListenerFactory = EventListener.factory(eventListener);
            return this;
        }

        public Builder eventListenerFactory(EventListener.Factory factory) {
            Objects.requireNonNull(factory, "eventListenerFactory == null");
            this.eventListenerFactory = factory;
            return this;
        }

        public Builder followRedirects(boolean z) {
            this.followRedirects = z;
            return this;
        }

        public Builder followSslRedirects(boolean z) {
            this.followSslRedirects = z;
            return this;
        }

        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            Objects.requireNonNull(hostnameVerifier, "hostnameVerifier == null");
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        public List<Interceptor> interceptors() {
            return this.interceptors;
        }

        public List<Interceptor> networkInterceptors() {
            return this.networkInterceptors;
        }

        public Builder pingInterval(long j, TimeUnit timeUnit) {
            this.pingInterval = Util.checkDuration("interval", j, timeUnit);
            return this;
        }

        public Builder protocols(List<Protocol> list) {
            ArrayList arrayList = new ArrayList(list);
            Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            if (!arrayList.contains(protocol) && !arrayList.contains(Protocol.HTTP_1_1)) {
                throw new IllegalArgumentException("protocols must contain h2_prior_knowledge or http/1.1: " + arrayList);
            } else if (arrayList.contains(protocol) && arrayList.size() > 1) {
                throw new IllegalArgumentException("protocols containing h2_prior_knowledge cannot use other protocols: " + arrayList);
            } else if (arrayList.contains(Protocol.HTTP_1_0)) {
                throw new IllegalArgumentException("protocols must not contain http/1.0: " + arrayList);
            } else if (!arrayList.contains(null)) {
                arrayList.remove(Protocol.SPDY_3);
                this.protocols = Collections.unmodifiableList(arrayList);
                return this;
            } else {
                throw new IllegalArgumentException("protocols must not contain null");
            }
        }

        public Builder proxy(Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        public Builder proxyAuthenticator(Authenticator authenticator) {
            Objects.requireNonNull(authenticator, "proxyAuthenticator == null");
            this.proxyAuthenticator = authenticator;
            return this;
        }

        public Builder proxySelector(ProxySelector proxySelector) {
            Objects.requireNonNull(proxySelector, "proxySelector == null");
            this.proxySelector = proxySelector;
            return this;
        }

        public Builder readTimeout(long j, TimeUnit timeUnit) {
            this.readTimeout = Util.checkDuration("timeout", j, timeUnit);
            return this;
        }

        public Builder retryOnConnectionFailure(boolean z) {
            this.retryOnConnectionFailure = z;
            return this;
        }

        public void setInternalCache(InternalCache internalCache) {
            this.internalCache = internalCache;
            this.cache = null;
        }

        public Builder socketFactory(SocketFactory socketFactory) {
            Objects.requireNonNull(socketFactory, "socketFactory == null");
            this.socketFactory = socketFactory;
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            Objects.requireNonNull(sSLSocketFactory, "sslSocketFactory == null");
            this.sslSocketFactory = sSLSocketFactory;
            this.certificateChainCleaner = Platform.get().buildCertificateChainCleaner(sSLSocketFactory);
            return this;
        }

        public Builder writeTimeout(long j, TimeUnit timeUnit) {
            this.writeTimeout = Util.checkDuration("timeout", j, timeUnit);
            return this;
        }

        @IgnoreJRERequirement
        public Builder callTimeout(Duration duration) {
            this.callTimeout = Util.checkDuration("timeout", duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        @IgnoreJRERequirement
        public Builder connectTimeout(Duration duration) {
            this.connectTimeout = Util.checkDuration("timeout", duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        @IgnoreJRERequirement
        public Builder pingInterval(Duration duration) {
            this.pingInterval = Util.checkDuration("timeout", duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        @IgnoreJRERequirement
        public Builder readTimeout(Duration duration) {
            this.readTimeout = Util.checkDuration("timeout", duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        @IgnoreJRERequirement
        public Builder writeTimeout(Duration duration) {
            this.writeTimeout = Util.checkDuration("timeout", duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            Objects.requireNonNull(sSLSocketFactory, "sslSocketFactory == null");
            Objects.requireNonNull(x509TrustManager, "trustManager == null");
            this.sslSocketFactory = sSLSocketFactory;
            this.certificateChainCleaner = CertificateChainCleaner.get(x509TrustManager);
            return this;
        }

        public Builder(OkHttpClient okHttpClient) {
            ArrayList arrayList = new ArrayList();
            this.interceptors = arrayList;
            ArrayList arrayList2 = new ArrayList();
            this.networkInterceptors = arrayList2;
            this.dispatcher = okHttpClient.dispatcher;
            this.proxy = okHttpClient.proxy;
            this.protocols = okHttpClient.protocols;
            this.connectionSpecs = okHttpClient.connectionSpecs;
            arrayList.addAll(okHttpClient.interceptors);
            arrayList2.addAll(okHttpClient.networkInterceptors);
            this.eventListenerFactory = okHttpClient.eventListenerFactory;
            this.proxySelector = okHttpClient.proxySelector;
            this.cookieJar = okHttpClient.cookieJar;
            this.internalCache = okHttpClient.internalCache;
            this.cache = okHttpClient.cache;
            this.socketFactory = okHttpClient.socketFactory;
            this.sslSocketFactory = okHttpClient.sslSocketFactory;
            this.certificateChainCleaner = okHttpClient.certificateChainCleaner;
            this.hostnameVerifier = okHttpClient.hostnameVerifier;
            this.certificatePinner = okHttpClient.certificatePinner;
            this.proxyAuthenticator = okHttpClient.proxyAuthenticator;
            this.authenticator = okHttpClient.authenticator;
            this.connectionPool = okHttpClient.connectionPool;
            this.dns = okHttpClient.dns;
            this.followSslRedirects = okHttpClient.followSslRedirects;
            this.followRedirects = okHttpClient.followRedirects;
            this.retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure;
            this.callTimeout = okHttpClient.callTimeout;
            this.connectTimeout = okHttpClient.connectTimeout;
            this.readTimeout = okHttpClient.readTimeout;
            this.writeTimeout = okHttpClient.writeTimeout;
            this.pingInterval = okHttpClient.pingInterval;
        }
    }

    static {
        Internal.instance = new Internal() { // from class: okhttp3.OkHttpClient.1
            @Override // okhttp3.internal.Internal
            public void addLenient(Headers.Builder builder, String str) {
                builder.addLenient(str);
            }

            @Override // okhttp3.internal.Internal
            public void apply(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z) {
                connectionSpec.apply(sSLSocket, z);
            }

            @Override // okhttp3.internal.Internal
            public int code(Response.Builder builder) {
                return builder.code;
            }

            @Override // okhttp3.internal.Internal
            public boolean connectionBecameIdle(ConnectionPool connectionPool, RealConnection realConnection) {
                return connectionPool.connectionBecameIdle(realConnection);
            }

            @Override // okhttp3.internal.Internal
            public Socket deduplicate(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation) {
                return connectionPool.deduplicate(address, streamAllocation);
            }

            @Override // okhttp3.internal.Internal
            public boolean equalsNonHost(Address address, Address address2) {
                return address.equalsNonHost(address2);
            }

            @Override // okhttp3.internal.Internal
            public RealConnection get(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation, Route route) {
                return connectionPool.get(address, streamAllocation, route);
            }

            @Override // okhttp3.internal.Internal
            public boolean isInvalidHttpUrlHost(IllegalArgumentException illegalArgumentException) {
                return illegalArgumentException.getMessage().startsWith(HttpUrl.Builder.INVALID_HOST);
            }

            @Override // okhttp3.internal.Internal
            public Call newWebSocketCall(OkHttpClient okHttpClient, Request request) {
                return RealCall.newRealCall(okHttpClient, request, true);
            }

            @Override // okhttp3.internal.Internal
            public void put(ConnectionPool connectionPool, RealConnection realConnection) {
                connectionPool.put(realConnection);
            }

            @Override // okhttp3.internal.Internal
            public RouteDatabase routeDatabase(ConnectionPool connectionPool) {
                return connectionPool.routeDatabase;
            }

            @Override // okhttp3.internal.Internal
            public void setCache(Builder builder, InternalCache internalCache) {
                builder.setInternalCache(internalCache);
            }

            @Override // okhttp3.internal.Internal
            public StreamAllocation streamAllocation(Call call) {
                return ((RealCall) call).streamAllocation();
            }

            @Override // okhttp3.internal.Internal
            public IOException timeoutExit(Call call, IOException iOException) {
                return ((RealCall) call).timeoutExit(iOException);
            }

            @Override // okhttp3.internal.Internal
            public void addLenient(Headers.Builder builder, String str, String str2) {
                builder.addLenient(str, str2);
            }
        };
    }

    public OkHttpClient() {
        this(new Builder());
    }

    private static SSLSocketFactory newSslSocketFactory(X509TrustManager x509TrustManager) {
        try {
            SSLContext sSLContext = Platform.get().getSSLContext();
            sSLContext.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw Util.assertionError("No System TLS", e);
        }
    }

    public Authenticator authenticator() {
        return this.authenticator;
    }

    public Cache cache() {
        return this.cache;
    }

    public int callTimeoutMillis() {
        return this.callTimeout;
    }

    public CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    public int connectTimeoutMillis() {
        return this.connectTimeout;
    }

    public ConnectionPool connectionPool() {
        return this.connectionPool;
    }

    public List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }

    public CookieJar cookieJar() {
        return this.cookieJar;
    }

    public Dispatcher dispatcher() {
        return this.dispatcher;
    }

    public Dns dns() {
        return this.dns;
    }

    public EventListener.Factory eventListenerFactory() {
        return this.eventListenerFactory;
    }

    public boolean followRedirects() {
        return this.followRedirects;
    }

    public boolean followSslRedirects() {
        return this.followSslRedirects;
    }

    public HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    public List<Interceptor> interceptors() {
        return this.interceptors;
    }

    public InternalCache internalCache() {
        Cache cache = this.cache;
        return cache != null ? cache.internalCache : this.internalCache;
    }

    public List<Interceptor> networkInterceptors() {
        return this.networkInterceptors;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    @Override // okhttp3.Call.Factory
    public Call newCall(Request request) {
        return RealCall.newRealCall(this, request, false);
    }

    @Override // okhttp3.WebSocket.Factory
    public WebSocket newWebSocket(Request request, WebSocketListener webSocketListener) {
        RealWebSocket realWebSocket = new RealWebSocket(request, webSocketListener, new Random(), this.pingInterval);
        realWebSocket.connect(this);
        return realWebSocket;
    }

    public int pingIntervalMillis() {
        return this.pingInterval;
    }

    public List<Protocol> protocols() {
        return this.protocols;
    }

    public Proxy proxy() {
        return this.proxy;
    }

    public Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    public ProxySelector proxySelector() {
        return this.proxySelector;
    }

    public int readTimeoutMillis() {
        return this.readTimeout;
    }

    public boolean retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    public SocketFactory socketFactory() {
        return this.socketFactory;
    }

    public SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }

    public int writeTimeoutMillis() {
        return this.writeTimeout;
    }

    public OkHttpClient(Builder builder) {
        boolean z;
        this.dispatcher = builder.dispatcher;
        this.proxy = builder.proxy;
        this.protocols = builder.protocols;
        List<ConnectionSpec> list = builder.connectionSpecs;
        this.connectionSpecs = list;
        this.interceptors = Util.immutableList(builder.interceptors);
        this.networkInterceptors = Util.immutableList(builder.networkInterceptors);
        this.eventListenerFactory = builder.eventListenerFactory;
        this.proxySelector = builder.proxySelector;
        this.cookieJar = builder.cookieJar;
        this.cache = builder.cache;
        this.internalCache = builder.internalCache;
        this.socketFactory = builder.socketFactory;
        loop0: while (true) {
            z = false;
            for (ConnectionSpec connectionSpec : list) {
                z = (z || connectionSpec.isTls()) ? true : z;
            }
        }
        SSLSocketFactory sSLSocketFactory = builder.sslSocketFactory;
        if (sSLSocketFactory != null || !z) {
            this.sslSocketFactory = sSLSocketFactory;
            this.certificateChainCleaner = builder.certificateChainCleaner;
        } else {
            X509TrustManager platformTrustManager = Util.platformTrustManager();
            this.sslSocketFactory = newSslSocketFactory(platformTrustManager);
            this.certificateChainCleaner = CertificateChainCleaner.get(platformTrustManager);
        }
        if (this.sslSocketFactory != null) {
            Platform.get().configureSslSocketFactory(this.sslSocketFactory);
        }
        this.hostnameVerifier = builder.hostnameVerifier;
        this.certificatePinner = builder.certificatePinner.withCertificateChainCleaner(this.certificateChainCleaner);
        this.proxyAuthenticator = builder.proxyAuthenticator;
        this.authenticator = builder.authenticator;
        this.connectionPool = builder.connectionPool;
        this.dns = builder.dns;
        this.followSslRedirects = builder.followSslRedirects;
        this.followRedirects = builder.followRedirects;
        this.retryOnConnectionFailure = builder.retryOnConnectionFailure;
        this.callTimeout = builder.callTimeout;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.pingInterval = builder.pingInterval;
        if (this.interceptors.contains(null)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Null interceptor: ");
            outline13.append(this.interceptors);
            throw new IllegalStateException(outline13.toString());
        } else if (this.networkInterceptors.contains(null)) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("Null network interceptor: ");
            outline132.append(this.networkInterceptors);
            throw new IllegalStateException(outline132.toString());
        }
    }
}
