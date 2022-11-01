package com.facebook.react.modules.network;

import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeNetworkingAndroidSpec;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.StandardCharsets;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import okhttp3.Call;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.ByteString;
import okio.GzipSource;
import okio.Okio;
import okio.RealBufferedSource;
@ReactModule(name = NetworkingModule.NAME)
/* loaded from: classes.dex */
public final class NetworkingModule extends NativeNetworkingAndroidSpec {
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    public static final String NAME = "Networking";
    private static final String REQUEST_BODY_KEY_BASE64 = "base64";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String TAG = "NetworkingModule";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";
    private static CustomClientBuilder customClientBuilder;
    private final OkHttpClient mClient;
    private final ForwardingCookieHandler mCookieHandler;
    private final CookieJarContainer mCookieJarContainer;
    private final String mDefaultUserAgent;
    private final List<RequestBodyHandler> mRequestBodyHandlers;
    private final Set<Integer> mRequestIds;
    private final List<ResponseHandler> mResponseHandlers;
    private boolean mShuttingDown;
    private final List<UriHandler> mUriHandlers;

    /* loaded from: classes.dex */
    public interface CustomClientBuilder {
        void apply(OkHttpClient.Builder builder);
    }

    /* loaded from: classes.dex */
    public interface RequestBodyHandler {
        boolean supports(ReadableMap readableMap);

        RequestBody toRequestBody(ReadableMap readableMap, String str);
    }

    /* loaded from: classes.dex */
    public interface ResponseHandler {
        boolean supports(String str);

        WritableMap toResponseData(ResponseBody responseBody) throws IOException;
    }

    /* loaded from: classes.dex */
    public interface UriHandler {
        WritableMap fetch(Uri uri) throws IOException;

        boolean supports(Uri uri, String str);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, String str, OkHttpClient okHttpClient, List<NetworkInterceptorCreator> list) {
        super(reactApplicationContext);
        this.mRequestBodyHandlers = new ArrayList();
        this.mUriHandlers = new ArrayList();
        this.mResponseHandlers = new ArrayList();
        if (list != null) {
            OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
            for (NetworkInterceptorCreator networkInterceptorCreator : list) {
                newBuilder.addNetworkInterceptor(networkInterceptorCreator.create());
            }
            okHttpClient = newBuilder.build();
        }
        this.mClient = okHttpClient;
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
        this.mCookieJarContainer = (CookieJarContainer) okHttpClient.cookieJar();
        this.mShuttingDown = false;
        this.mDefaultUserAgent = str;
        this.mRequestIds = new HashSet();
    }

    private synchronized void addRequest(int i) {
        this.mRequestIds.add(Integer.valueOf(i));
    }

    private static void applyCustomBuilder(OkHttpClient.Builder builder) {
        CustomClientBuilder customClientBuilder2 = customClientBuilder;
        if (customClientBuilder2 != null) {
            customClientBuilder2.apply(builder);
        }
    }

    private synchronized void cancelAllRequests() {
        for (Integer num : this.mRequestIds) {
            cancelRequest(num.intValue());
        }
        this.mRequestIds.clear();
    }

    private void cancelRequest(final int i) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.facebook.react.modules.network.NetworkingModule.4
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void[] voidArr) {
                OkHttpClient okHttpClient = NetworkingModule.this.mClient;
                Integer valueOf = Integer.valueOf(i);
                for (Call call : okHttpClient.dispatcher().queuedCalls()) {
                    if (valueOf.equals(call.request().tag())) {
                        call.cancel();
                        return;
                    }
                }
                for (Call call2 : okHttpClient.dispatcher().runningCalls()) {
                    if (valueOf.equals(call2.request().tag())) {
                        call2.cancel();
                        return;
                    }
                }
            }
        }.execute(new Void[0]);
    }

    private MultipartBody.Builder constructMultipartBody(ReadableArray readableArray, String str, int i) {
        MediaType mediaType;
        DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("constructMultipartBody");
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MediaType.parse(str));
        int size = readableArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ReadableMap map = readableArray.getMap(i2);
            Headers extractHeaders = extractHeaders(map.getArray("headers"), null);
            if (extractHeaders == null) {
                R$style.onRequestError(eventEmitter, i, "Missing or invalid header format for FormData part.", null);
                return null;
            }
            String str2 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
            if (str2 != null) {
                mediaType = MediaType.parse(str2);
                extractHeaders = extractHeaders.newBuilder().removeAll(CONTENT_TYPE_HEADER_NAME).build();
            } else {
                mediaType = null;
            }
            if (map.hasKey(REQUEST_BODY_KEY_STRING)) {
                builder.addPart(extractHeaders, RequestBody.create(mediaType, map.getString(REQUEST_BODY_KEY_STRING)));
            } else if (!map.hasKey(REQUEST_BODY_KEY_URI)) {
                R$style.onRequestError(eventEmitter, i, "Unrecognized FormData part.", null);
            } else if (mediaType == null) {
                R$style.onRequestError(eventEmitter, i, "Binary FormData part needs a content-type header.", null);
                return null;
            } else {
                String string = map.getString(REQUEST_BODY_KEY_URI);
                InputStream fileInputStream = R$style.getFileInputStream(getReactApplicationContext(), string);
                if (fileInputStream == null) {
                    R$style.onRequestError(eventEmitter, i, "Could not retrieve file for uri " + string, null);
                    return null;
                }
                builder.addPart(extractHeaders, new RequestBodyUtil$1(mediaType, fileInputStream));
            }
        }
        return builder;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00af, code lost:
        if (r18.hasKey(com.facebook.react.modules.network.NetworkingModule.REQUEST_BODY_KEY_STRING) == false) goto L_0x00b3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b1, code lost:
        r5 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private okhttp3.Headers extractHeaders(com.facebook.react.bridge.ReadableArray r17, com.facebook.react.bridge.ReadableMap r18) {
        /*
            r16 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            if (r0 != 0) goto L_0x0008
            return r2
        L_0x0008:
            okhttp3.Headers$Builder r3 = new okhttp3.Headers$Builder
            r3.<init>()
            int r4 = r17.size()
            r5 = 0
            r6 = 0
        L_0x0013:
            r7 = 1
            if (r6 >= r4) goto L_0x0093
            com.facebook.react.bridge.ReadableArray r8 = r0.getArray(r6)
            if (r8 == 0) goto L_0x0092
            int r9 = r8.size()
            r10 = 2
            if (r9 == r10) goto L_0x0025
            goto L_0x0092
        L_0x0025:
            java.lang.String r9 = r8.getString(r5)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            int r11 = r9.length()
            r10.<init>(r11)
            int r11 = r9.length()
            r12 = 0
            r13 = 0
        L_0x0038:
            r14 = 127(0x7f, float:1.78E-43)
            if (r12 >= r11) goto L_0x004f
            char r15 = r9.charAt(r12)
            r5 = 32
            if (r15 <= r5) goto L_0x004a
            if (r15 >= r14) goto L_0x004a
            r10.append(r15)
            goto L_0x004b
        L_0x004a:
            r13 = 1
        L_0x004b:
            int r12 = r12 + 1
            r5 = 0
            goto L_0x0038
        L_0x004f:
            if (r13 == 0) goto L_0x0055
            java.lang.String r9 = r10.toString()
        L_0x0055:
            java.lang.String r5 = r8.getString(r7)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            int r10 = r5.length()
            r8.<init>(r10)
            int r10 = r5.length()
            r11 = 0
            r12 = 0
        L_0x0068:
            if (r11 >= r10) goto L_0x0080
            char r13 = r5.charAt(r11)
            r15 = 31
            if (r13 <= r15) goto L_0x0074
            if (r13 < r14) goto L_0x0078
        L_0x0074:
            r15 = 9
            if (r13 != r15) goto L_0x007c
        L_0x0078:
            r8.append(r13)
            goto L_0x007d
        L_0x007c:
            r12 = 1
        L_0x007d:
            int r11 = r11 + 1
            goto L_0x0068
        L_0x0080:
            if (r12 == 0) goto L_0x0086
            java.lang.String r5 = r8.toString()
        L_0x0086:
            if (r9 == 0) goto L_0x0092
            if (r5 != 0) goto L_0x008b
            goto L_0x0092
        L_0x008b:
            r3.add(r9, r5)
            int r6 = r6 + 1
            r5 = 0
            goto L_0x0013
        L_0x0092:
            return r2
        L_0x0093:
            java.lang.String r0 = "user-agent"
            java.lang.String r2 = r3.get(r0)
            if (r2 != 0) goto L_0x00a5
            r2 = r16
            java.lang.String r4 = r2.mDefaultUserAgent
            if (r4 == 0) goto L_0x00a7
            r3.add(r0, r4)
            goto L_0x00a7
        L_0x00a5:
            r2 = r16
        L_0x00a7:
            if (r1 == 0) goto L_0x00b3
            java.lang.String r0 = "string"
            boolean r0 = r1.hasKey(r0)
            if (r0 == 0) goto L_0x00b3
            r5 = 1
            goto L_0x00b4
        L_0x00b3:
            r5 = 0
        L_0x00b4:
            if (r5 != 0) goto L_0x00bb
            java.lang.String r0 = "content-encoding"
            r3.removeAll(r0)
        L_0x00bb:
            okhttp3.Headers r0 = r3.build()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.network.NetworkingModule.extractHeaders(com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableMap):okhttp3.Headers");
    }

    private DeviceEventManagerModule.RCTDeviceEventEmitter getEventEmitter(String str) {
        if (getReactApplicationContextIfActiveOrWarn() != null) {
            return (DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readWithProgress(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, ResponseBody responseBody) throws IOException {
        long j;
        long j2 = -1;
        try {
            ProgressResponseBody progressResponseBody = (ProgressResponseBody) responseBody;
            j = progressResponseBody.mTotalBytesRead;
            try {
                j2 = progressResponseBody.contentLength();
            } catch (ClassCastException unused) {
            }
        } catch (ClassCastException unused2) {
            j = -1;
        }
        ProgressiveStringDecoder progressiveStringDecoder = new ProgressiveStringDecoder(responseBody.contentType() == null ? StandardCharsets.UTF_8 : responseBody.contentType().charset(StandardCharsets.UTF_8));
        InputStream byteStream = responseBody.byteStream();
        try {
            byte[] bArr = new byte[MAX_CHUNK_SIZE_BETWEEN_FLUSHES];
            while (true) {
                int read = byteStream.read(bArr);
                if (read != -1) {
                    String decodeNext = progressiveStringDecoder.decodeNext(bArr, read);
                    WritableArray createArray = Arguments.createArray();
                    createArray.pushInt(i);
                    createArray.pushString(decodeNext);
                    createArray.pushInt((int) j);
                    createArray.pushInt((int) j2);
                    if (rCTDeviceEventEmitter != null) {
                        rCTDeviceEventEmitter.emit("didReceiveNetworkIncrementalData", createArray);
                    }
                } else {
                    return;
                }
            }
        } finally {
            byteStream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void removeRequest(int i) {
        this.mRequestIds.remove(Integer.valueOf(i));
    }

    public static void setCustomClientBuilder(CustomClientBuilder customClientBuilder2) {
        customClientBuilder = customClientBuilder2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldDispatch(long j, long j2) {
        return j2 + 100000000 < j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WritableMap translateHeaders(Headers headers) {
        Bundle bundle = new Bundle();
        for (int i = 0; i < headers.size(); i++) {
            String name = headers.name(i);
            if (bundle.containsKey(name)) {
                bundle.putString(name, bundle.getString(name) + ", " + headers.value(i));
            } else {
                bundle.putString(name, headers.value(i));
            }
        }
        return Arguments.fromBundle(bundle);
    }

    private RequestBody wrapRequestBodyWithProgressEmitter(RequestBody requestBody, final DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, final int i) {
        if (requestBody == null) {
            return null;
        }
        return new ProgressRequestBody(requestBody, new ProgressListener(this) { // from class: com.facebook.react.modules.network.NetworkingModule.3
            public long last = System.nanoTime();

            @Override // com.facebook.react.modules.network.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                long nanoTime = System.nanoTime();
                if (z || NetworkingModule.shouldDispatch(nanoTime, this.last)) {
                    DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter2 = rCTDeviceEventEmitter;
                    int i2 = i;
                    WritableArray createArray = Arguments.createArray();
                    createArray.pushInt(i2);
                    createArray.pushInt((int) j);
                    createArray.pushInt((int) j2);
                    if (rCTDeviceEventEmitter2 != null) {
                        rCTDeviceEventEmitter2.emit("didSendNetworkData", createArray);
                    }
                    this.last = nanoTime;
                }
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void abortRequest(double d) {
        int i = (int) d;
        cancelRequest(i);
        removeRequest(i);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void addListener(String str) {
    }

    public void addRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.add(requestBodyHandler);
    }

    public void addResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.add(responseHandler);
    }

    public void addUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.add(uriHandler);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    @ReactMethod
    public void clearCookies(final Callback callback) {
        final ForwardingCookieHandler forwardingCookieHandler = this.mCookieHandler;
        CookieManager cookieManager = forwardingCookieHandler.getCookieManager();
        if (cookieManager != null) {
            cookieManager.removeAllCookies(new ValueCallback<Boolean>() { // from class: com.facebook.react.modules.network.ForwardingCookieHandler.2
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Boolean bool) {
                    Objects.requireNonNull(forwardingCookieHandler.mCookieSaver);
                    int i = ForwardingCookieHandler.$r8$clinit;
                    callback.invoke(bool);
                }
            });
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        this.mCookieJarContainer.setCookieJar(new JavaNetCookieJar(this.mCookieHandler));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
        cancelAllRequests();
        Objects.requireNonNull(this.mCookieHandler);
        this.mCookieJarContainer.removeCookieJar();
        this.mRequestBodyHandlers.clear();
        this.mResponseHandlers.clear();
        this.mUriHandlers.clear();
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void removeListeners(double d) {
    }

    public void removeRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.remove(requestBodyHandler);
    }

    public void removeResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.remove(responseHandler);
    }

    public void removeUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.remove(uriHandler);
    }

    @Override // com.facebook.fbreact.specs.NativeNetworkingAndroidSpec
    public void sendRequest(String str, String str2, double d, ReadableArray readableArray, ReadableMap readableMap, String str3, boolean z, double d2, boolean z2) {
        int i = (int) d;
        try {
            sendRequestInternal(str, str2, i, readableArray, readableMap, str3, z, (int) d2, z2);
        } catch (Throwable th) {
            FLog.e(TAG, "Failed to send url request: " + str2, th);
            DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("sendRequest error");
            if (eventEmitter != null) {
                R$style.onRequestError(eventEmitter, i, th.getMessage(), th);
            }
        }
    }

    public void sendRequestInternal(String str, String str2, final int i, ReadableArray readableArray, ReadableMap readableMap, final String str3, final boolean z, int i2, boolean z2) {
        RequestBodyHandler requestBodyHandler;
        RequestBody requestBody;
        final DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter("sendRequestInternal");
        try {
            Uri parse = Uri.parse(str2);
            for (UriHandler uriHandler : this.mUriHandlers) {
                if (uriHandler.supports(parse, str3)) {
                    WritableMap fetch = uriHandler.fetch(parse);
                    WritableArray createArray = Arguments.createArray();
                    createArray.pushInt(i);
                    createArray.pushMap(fetch);
                    if (eventEmitter != null) {
                        eventEmitter.emit("didReceiveNetworkData", createArray);
                    }
                    R$style.onRequestSuccess(eventEmitter, i);
                    return;
                }
            }
            try {
                Request.Builder url = new Request.Builder().url(str2);
                if (i != 0) {
                    url.tag(Integer.valueOf(i));
                }
                OkHttpClient.Builder newBuilder = this.mClient.newBuilder();
                applyCustomBuilder(newBuilder);
                if (!z2) {
                    newBuilder.cookieJar(CookieJar.NO_COOKIES);
                }
                if (z) {
                    newBuilder.addNetworkInterceptor(new Interceptor(this) { // from class: com.facebook.react.modules.network.NetworkingModule.1
                        @Override // okhttp3.Interceptor
                        public Response intercept(Interceptor.Chain chain) throws IOException {
                            Response proceed = chain.proceed(chain.request());
                            return proceed.newBuilder().body(new ProgressResponseBody(proceed.body(), new ProgressListener() { // from class: com.facebook.react.modules.network.NetworkingModule.1.1
                                public long last = System.nanoTime();

                                @Override // com.facebook.react.modules.network.ProgressListener
                                public void onProgress(long j, long j2, boolean z3) {
                                    long nanoTime = System.nanoTime();
                                    if ((z3 || NetworkingModule.shouldDispatch(nanoTime, this.last)) && !str3.equals("text")) {
                                        AnonymousClass1 r9 = AnonymousClass1.this;
                                        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter = eventEmitter;
                                        int i3 = i;
                                        WritableArray createArray2 = Arguments.createArray();
                                        createArray2.pushInt(i3);
                                        createArray2.pushInt((int) j);
                                        createArray2.pushInt((int) j2);
                                        if (rCTDeviceEventEmitter != null) {
                                            rCTDeviceEventEmitter.emit("didReceiveNetworkDataProgress", createArray2);
                                        }
                                        this.last = nanoTime;
                                    }
                                }
                            })).build();
                        }
                    });
                }
                if (i2 != this.mClient.connectTimeoutMillis()) {
                    newBuilder.connectTimeout(i2, TimeUnit.MILLISECONDS);
                }
                OkHttpClient build = newBuilder.build();
                Headers extractHeaders = extractHeaders(readableArray, readableMap);
                if (extractHeaders == null) {
                    R$style.onRequestError(eventEmitter, i, "Unrecognized headers format", null);
                    return;
                }
                String str4 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
                String str5 = extractHeaders.get(CONTENT_ENCODING_HEADER_NAME);
                url.headers(extractHeaders);
                if (readableMap != null) {
                    Iterator<RequestBodyHandler> it = this.mRequestBodyHandlers.iterator();
                    while (it.hasNext()) {
                        requestBodyHandler = it.next();
                        if (requestBodyHandler.supports(readableMap)) {
                            break;
                        }
                    }
                }
                requestBodyHandler = null;
                if (readableMap == null || str.toLowerCase().equals("get") || str.toLowerCase().equals("head")) {
                    requestBody = R$style.getEmptyBody(str);
                } else if (requestBodyHandler != null) {
                    requestBody = requestBodyHandler.toRequestBody(readableMap, str4);
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
                    if (str4 == null) {
                        R$style.onRequestError(eventEmitter, i, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    String string = readableMap.getString(REQUEST_BODY_KEY_STRING);
                    MediaType parse2 = MediaType.parse(str4);
                    if ("gzip".equalsIgnoreCase(str5)) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                            gZIPOutputStream.write(string.getBytes());
                            gZIPOutputStream.close();
                            requestBody = RequestBody.create(parse2, byteArrayOutputStream.toByteArray());
                        } catch (IOException unused) {
                            requestBody = null;
                        }
                        if (requestBody == null) {
                            R$style.onRequestError(eventEmitter, i, "Failed to gzip request body", null);
                            return;
                        }
                    } else {
                        Charset charset = StandardCharsets.UTF_8;
                        if (parse2 != null) {
                            charset = parse2.charset(charset);
                        }
                        requestBody = RequestBody.create(parse2, string.getBytes(charset));
                    }
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_BASE64)) {
                    if (str4 == null) {
                        R$style.onRequestError(eventEmitter, i, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    requestBody = RequestBody.create(MediaType.parse(str4), ByteString.decodeBase64(readableMap.getString(REQUEST_BODY_KEY_BASE64)));
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_URI)) {
                    if (str4 == null) {
                        R$style.onRequestError(eventEmitter, i, "Payload is set but no content-type header specified", null);
                        return;
                    }
                    String string2 = readableMap.getString(REQUEST_BODY_KEY_URI);
                    InputStream fileInputStream = R$style.getFileInputStream(getReactApplicationContext(), string2);
                    if (fileInputStream == null) {
                        R$style.onRequestError(eventEmitter, i, "Could not retrieve file for uri " + string2, null);
                        return;
                    }
                    requestBody = new RequestBodyUtil$1(MediaType.parse(str4), fileInputStream);
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_FORMDATA)) {
                    if (str4 == null) {
                        str4 = "multipart/form-data";
                    }
                    MultipartBody.Builder constructMultipartBody = constructMultipartBody(readableMap.getArray(REQUEST_BODY_KEY_FORMDATA), str4, i);
                    if (constructMultipartBody != null) {
                        requestBody = constructMultipartBody.build();
                    } else {
                        return;
                    }
                } else {
                    requestBody = R$style.getEmptyBody(str);
                }
                url.method(str, wrapRequestBodyWithProgressEmitter(requestBody, eventEmitter, i));
                addRequest(i);
                build.newCall(url.build()).enqueue(new okhttp3.Callback() { // from class: com.facebook.react.modules.network.NetworkingModule.2
                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException iOException) {
                        String str6;
                        if (!NetworkingModule.this.mShuttingDown) {
                            NetworkingModule.this.removeRequest(i);
                            if (iOException.getMessage() != null) {
                                str6 = iOException.getMessage();
                            } else {
                                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Error while executing request: ");
                                outline13.append(iOException.getClass().getSimpleName());
                                str6 = outline13.toString();
                            }
                            R$style.onRequestError(eventEmitter, i, str6, iOException);
                        }
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseHandler responseHandler;
                        if (!NetworkingModule.this.mShuttingDown) {
                            NetworkingModule.this.removeRequest(i);
                            DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter = eventEmitter;
                            int i3 = i;
                            int code = response.code();
                            WritableMap translateHeaders = NetworkingModule.translateHeaders(response.headers());
                            String httpUrl = response.request().url().toString();
                            WritableArray createArray2 = Arguments.createArray();
                            createArray2.pushInt(i3);
                            createArray2.pushInt(code);
                            createArray2.pushMap(translateHeaders);
                            createArray2.pushString(httpUrl);
                            if (rCTDeviceEventEmitter != null) {
                                rCTDeviceEventEmitter.emit("didReceiveNetworkResponse", createArray2);
                            }
                            try {
                                ResponseBody body = response.body();
                                if ("gzip".equalsIgnoreCase(response.header("Content-Encoding")) && body != null) {
                                    GzipSource gzipSource = new GzipSource(body.source());
                                    String header = response.header("Content-Type");
                                    MediaType parse3 = header != null ? MediaType.parse(header) : null;
                                    Logger logger = Okio.logger;
                                    body = ResponseBody.create(parse3, -1L, new RealBufferedSource(gzipSource));
                                }
                                Iterator it2 = NetworkingModule.this.mResponseHandlers.iterator();
                                do {
                                    if (it2.hasNext()) {
                                        responseHandler = (ResponseHandler) it2.next();
                                    } else if (!z || !str3.equals("text")) {
                                        String str6 = HttpUrl.FRAGMENT_ENCODE_SET;
                                        if (str3.equals("text")) {
                                            try {
                                                str6 = body.string();
                                            } catch (IOException e) {
                                                if (!response.request().method().equalsIgnoreCase("HEAD")) {
                                                    R$style.onRequestError(eventEmitter, i, e.getMessage(), e);
                                                }
                                            }
                                        } else if (str3.equals(NetworkingModule.REQUEST_BODY_KEY_BASE64)) {
                                            str6 = Base64.encodeToString(body.bytes(), 2);
                                        }
                                        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter2 = eventEmitter;
                                        int i4 = i;
                                        WritableArray createArray3 = Arguments.createArray();
                                        createArray3.pushInt(i4);
                                        createArray3.pushString(str6);
                                        if (rCTDeviceEventEmitter2 != null) {
                                            rCTDeviceEventEmitter2.emit("didReceiveNetworkData", createArray3);
                                        }
                                        R$style.onRequestSuccess(eventEmitter, i);
                                        return;
                                    } else {
                                        NetworkingModule.this.readWithProgress(eventEmitter, i, body);
                                        R$style.onRequestSuccess(eventEmitter, i);
                                        return;
                                    }
                                } while (!responseHandler.supports(str3));
                                WritableMap responseData = responseHandler.toResponseData(body);
                                DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter3 = eventEmitter;
                                int i5 = i;
                                WritableArray createArray4 = Arguments.createArray();
                                createArray4.pushInt(i5);
                                createArray4.pushMap(responseData);
                                if (rCTDeviceEventEmitter3 != null) {
                                    rCTDeviceEventEmitter3.emit("didReceiveNetworkData", createArray4);
                                }
                                R$style.onRequestSuccess(eventEmitter, i);
                            } catch (IOException e2) {
                                R$style.onRequestError(eventEmitter, i, e2.getMessage(), e2);
                            }
                        }
                    }
                });
            } catch (Exception e) {
                R$style.onRequestError(eventEmitter, i, e.getMessage(), null);
            }
        } catch (IOException e2) {
            R$style.onRequestError(eventEmitter, i, e2.getMessage(), e2);
        }
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, String str, OkHttpClient okHttpClient) {
        this(reactApplicationContext, str, okHttpClient, null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null, R$style.createClient(reactApplicationContext), null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, List<NetworkInterceptorCreator> list) {
        this(reactApplicationContext, null, R$style.createClient(reactApplicationContext), list);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, String str) {
        this(reactApplicationContext, str, R$style.createClient(reactApplicationContext), null);
    }
}
