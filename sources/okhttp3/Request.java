package okhttp3;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpMethod;
/* loaded from: classes.dex */
public final class Request {
    public final RequestBody body;
    private volatile CacheControl cacheControl;
    public final Headers headers;
    public final String method;
    public final Map<Class<?>, Object> tags;
    public final HttpUrl url;

    /* loaded from: classes.dex */
    public static class Builder {
        public RequestBody body;
        public Headers.Builder headers;
        public String method;
        public Map<Class<?>, Object> tags;
        public HttpUrl url;

        public Builder() {
            this.tags = Collections.emptyMap();
            this.method = "GET";
            this.headers = new Headers.Builder();
        }

        public Builder addHeader(String str, String str2) {
            this.headers.add(str, str2);
            return this;
        }

        public Request build() {
            if (this.url != null) {
                return new Request(this);
            }
            throw new IllegalStateException("url == null");
        }

        public Builder cacheControl(CacheControl cacheControl) {
            String cacheControl2 = cacheControl.toString();
            return cacheControl2.isEmpty() ? removeHeader("Cache-Control") : header("Cache-Control", cacheControl2);
        }

        public Builder delete(RequestBody requestBody) {
            return method("DELETE", requestBody);
        }

        public Builder get() {
            return method("GET", null);
        }

        public Builder head() {
            return method("HEAD", null);
        }

        public Builder header(String str, String str2) {
            this.headers.set(str, str2);
            return this;
        }

        public Builder headers(Headers headers) {
            this.headers = headers.newBuilder();
            return this;
        }

        public Builder method(String str, RequestBody requestBody) {
            Objects.requireNonNull(str, "method == null");
            if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            } else if (requestBody != null && !HttpMethod.permitsRequestBody(str)) {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline9("method ", str, " must not have a request body."));
            } else if (requestBody != null || !HttpMethod.requiresRequestBody(str)) {
                this.method = str;
                this.body = requestBody;
                return this;
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline9("method ", str, " must have a request body."));
            }
        }

        public Builder patch(RequestBody requestBody) {
            return method("PATCH", requestBody);
        }

        public Builder post(RequestBody requestBody) {
            return method(RNCWebViewManager.HTTP_METHOD_POST, requestBody);
        }

        public Builder put(RequestBody requestBody) {
            return method("PUT", requestBody);
        }

        public Builder removeHeader(String str) {
            this.headers.removeAll(str);
            return this;
        }

        public Builder tag(Object obj) {
            return tag(Object.class, obj);
        }

        public Builder url(HttpUrl httpUrl) {
            Objects.requireNonNull(httpUrl, "url == null");
            this.url = httpUrl;
            return this;
        }

        public Builder delete() {
            return delete(Util.EMPTY_REQUEST);
        }

        public <T> Builder tag(Class<? super T> cls, T t) {
            Objects.requireNonNull(cls, "type == null");
            if (t == null) {
                this.tags.remove(cls);
            } else {
                if (this.tags.isEmpty()) {
                    this.tags = new LinkedHashMap();
                }
                this.tags.put(cls, cls.cast(t));
            }
            return this;
        }

        public Builder url(String str) {
            Objects.requireNonNull(str, "url == null");
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("http:");
                outline13.append(str.substring(3));
                str = outline13.toString();
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("https:");
                outline132.append(str.substring(4));
                str = outline132.toString();
            }
            return url(HttpUrl.get(str));
        }

        public Builder(Request request) {
            Map<Class<?>, Object> map;
            this.tags = Collections.emptyMap();
            this.url = request.url;
            this.method = request.method;
            this.body = request.body;
            if (request.tags.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = new LinkedHashMap<>(request.tags);
            }
            this.tags = map;
            this.headers = request.headers.newBuilder();
        }

        public Builder url(URL url) {
            Objects.requireNonNull(url, "url == null");
            return url(HttpUrl.get(url.toString()));
        }
    }

    public Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers.build();
        this.body = builder.body;
        this.tags = Util.immutableMap(builder.tags);
    }

    public RequestBody body() {
        return this.body;
    }

    public CacheControl cacheControl() {
        CacheControl cacheControl = this.cacheControl;
        if (cacheControl != null) {
            return cacheControl;
        }
        CacheControl parse = CacheControl.parse(this.headers);
        this.cacheControl = parse;
        return parse;
    }

    public String header(String str) {
        return this.headers.get(str);
    }

    public Headers headers() {
        return this.headers;
    }

    public boolean isHttps() {
        return this.url.isHttps();
    }

    public String method() {
        return this.method;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public Object tag() {
        return tag(Object.class);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Request{method=");
        outline13.append(this.method);
        outline13.append(", url=");
        outline13.append(this.url);
        outline13.append(", tags=");
        outline13.append(this.tags);
        outline13.append('}');
        return outline13.toString();
    }

    public HttpUrl url() {
        return this.url;
    }

    public List<String> headers(String str) {
        return this.headers.values(str);
    }

    public <T> T tag(Class<? extends T> cls) {
        return (T) cls.cast(this.tags.get(cls));
    }
}
