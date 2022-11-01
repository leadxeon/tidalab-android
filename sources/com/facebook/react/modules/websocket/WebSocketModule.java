package com.facebook.react.modules.websocket;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeWebSocketModuleSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
@ReactModule(hasConstants = false, name = "WebSocketModule")
/* loaded from: classes.dex */
public final class WebSocketModule extends NativeWebSocketModuleSpec {
    public static final String NAME = "WebSocketModule";
    public static final String TAG = "WebSocketModule";
    private ForwardingCookieHandler mCookieHandler;
    private final Map<Integer, WebSocket> mWebSocketConnections = new ConcurrentHashMap();
    private final Map<Integer, ContentHandler> mContentHandlers = new ConcurrentHashMap();

    /* loaded from: classes.dex */
    public interface ContentHandler {
        void onMessage(String str, WritableMap writableMap);

        void onMessage(ByteString byteString, WritableMap writableMap);
    }

    public WebSocketModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mCookieHandler = new ForwardingCookieHandler(reactApplicationContext);
    }

    private String getCookie(String str) {
        try {
            List<String> list = this.mCookieHandler.get(new URI(getDefaultOrigin(str)), new HashMap()).get("Cookie");
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
            return null;
        } catch (IOException | URISyntaxException unused) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("Unable to get cookie from ", str));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0077 A[Catch: URISyntaxException -> 0x00a3, TryCatch #0 {URISyntaxException -> 0x00a3, blocks: (B:2:0x0000, B:11:0x002c, B:14:0x0034, B:17:0x003c, B:20:0x0046, B:29:0x005a, B:32:0x0071, B:34:0x0077, B:35:0x0092), top: B:39:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0092 A[Catch: URISyntaxException -> 0x00a3, TRY_LEAVE, TryCatch #0 {URISyntaxException -> 0x00a3, blocks: (B:2:0x0000, B:11:0x002c, B:14:0x0034, B:17:0x003c, B:20:0x0046, B:29:0x005a, B:32:0x0071, B:34:0x0077, B:35:0x0092), top: B:39:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getDefaultOrigin(java.lang.String r12) {
        /*
            java.lang.String r0 = ""
            java.net.URI r1 = new java.net.URI     // Catch: URISyntaxException -> 0x00a3
            r1.<init>(r12)     // Catch: URISyntaxException -> 0x00a3
            java.lang.String r2 = r1.getScheme()     // Catch: URISyntaxException -> 0x00a3
            int r3 = r2.hashCode()     // Catch: URISyntaxException -> 0x00a3
            r4 = 3804(0xedc, float:5.33E-42)
            java.lang.String r5 = "https"
            java.lang.String r6 = "http"
            r7 = -1
            r8 = 0
            r9 = 3
            r10 = 2
            r11 = 1
            if (r3 == r4) goto L_0x0046
            r4 = 118039(0x1cd17, float:1.65408E-40)
            if (r3 == r4) goto L_0x003c
            r4 = 3213448(0x310888, float:4.503E-39)
            if (r3 == r4) goto L_0x0034
            r4 = 99617003(0x5f008eb, float:2.2572767E-35)
            if (r3 == r4) goto L_0x002c
            goto L_0x0050
        L_0x002c:
            boolean r2 = r2.equals(r5)     // Catch: URISyntaxException -> 0x00a3
            if (r2 == 0) goto L_0x0050
            r2 = 3
            goto L_0x0051
        L_0x0034:
            boolean r2 = r2.equals(r6)     // Catch: URISyntaxException -> 0x00a3
            if (r2 == 0) goto L_0x0050
            r2 = 2
            goto L_0x0051
        L_0x003c:
            java.lang.String r3 = "wss"
            boolean r2 = r2.equals(r3)     // Catch: URISyntaxException -> 0x00a3
            if (r2 == 0) goto L_0x0050
            r2 = 0
            goto L_0x0051
        L_0x0046:
            java.lang.String r3 = "ws"
            boolean r2 = r2.equals(r3)     // Catch: URISyntaxException -> 0x00a3
            if (r2 == 0) goto L_0x0050
            r2 = 1
            goto L_0x0051
        L_0x0050:
            r2 = -1
        L_0x0051:
            if (r2 == 0) goto L_0x0070
            if (r2 == r11) goto L_0x006e
            if (r2 == r10) goto L_0x005a
            if (r2 == r9) goto L_0x005a
            goto L_0x0071
        L_0x005a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: URISyntaxException -> 0x00a3
            r2.<init>()     // Catch: URISyntaxException -> 0x00a3
            r2.append(r0)     // Catch: URISyntaxException -> 0x00a3
            java.lang.String r0 = r1.getScheme()     // Catch: URISyntaxException -> 0x00a3
            r2.append(r0)     // Catch: URISyntaxException -> 0x00a3
            java.lang.String r0 = r2.toString()     // Catch: URISyntaxException -> 0x00a3
            goto L_0x0071
        L_0x006e:
            r0 = r6
            goto L_0x0071
        L_0x0070:
            r0 = r5
        L_0x0071:
            int r2 = r1.getPort()     // Catch: URISyntaxException -> 0x00a3
            if (r2 == r7) goto L_0x0092
            java.lang.String r2 = "%s://%s:%s"
            java.lang.Object[] r3 = new java.lang.Object[r9]     // Catch: URISyntaxException -> 0x00a3
            r3[r8] = r0     // Catch: URISyntaxException -> 0x00a3
            java.lang.String r0 = r1.getHost()     // Catch: URISyntaxException -> 0x00a3
            r3[r11] = r0     // Catch: URISyntaxException -> 0x00a3
            int r0 = r1.getPort()     // Catch: URISyntaxException -> 0x00a3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: URISyntaxException -> 0x00a3
            r3[r10] = r0     // Catch: URISyntaxException -> 0x00a3
            java.lang.String r12 = java.lang.String.format(r2, r3)     // Catch: URISyntaxException -> 0x00a3
            goto L_0x00a2
        L_0x0092:
            java.lang.String r2 = "%s://%s"
            java.lang.Object[] r3 = new java.lang.Object[r10]     // Catch: URISyntaxException -> 0x00a3
            r3[r8] = r0     // Catch: URISyntaxException -> 0x00a3
            java.lang.String r0 = r1.getHost()     // Catch: URISyntaxException -> 0x00a3
            r3[r11] = r0     // Catch: URISyntaxException -> 0x00a3
            java.lang.String r12 = java.lang.String.format(r2, r3)     // Catch: URISyntaxException -> 0x00a3
        L_0x00a2:
            return r12
        L_0x00a3:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unable to set "
            java.lang.String r2 = " as default origin header"
            java.lang.String r12 = com.android.tools.r8.GeneratedOutlineSupport.outline9(r1, r12, r2)
            r0.<init>(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.websocket.WebSocketModule.getDefaultOrigin(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWebSocketFailed(int i, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", i);
        createMap.putString(DialogModule.KEY_MESSAGE, str);
        sendEvent("websocketFailed", createMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContextIfActiveOrWarn.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void addListener(String str) {
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void close(double d, String str, double d2) {
        int i = (int) d2;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket != null) {
            try {
                webSocket.close((int) d, str);
                this.mWebSocketConnections.remove(Integer.valueOf(i));
                this.mContentHandlers.remove(Integer.valueOf(i));
            } catch (Exception e) {
                FLog.e("ReactNative", "Could not close WebSocket connection for id " + i, e);
            }
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void connect(String str, ReadableArray readableArray, ReadableMap readableMap, double d) {
        final int i = (int) d;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        OkHttpClient build = builder.connectTimeout(10L, timeUnit).writeTimeout(10L, timeUnit).readTimeout(0L, TimeUnit.MINUTES).build();
        Request.Builder url = new Request.Builder().tag(Integer.valueOf(i)).url(str);
        String cookie = getCookie(str);
        if (cookie != null) {
            url.addHeader("Cookie", cookie);
        }
        if (readableMap == null || !readableMap.hasKey("headers") || !readableMap.getType("headers").equals(ReadableType.Map)) {
            url.addHeader("origin", getDefaultOrigin(str));
        } else {
            ReadableMap map = readableMap.getMap("headers");
            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
            if (!map.hasKey("origin")) {
                url.addHeader("origin", getDefaultOrigin(str));
            }
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                if (ReadableType.String.equals(map.getType(nextKey))) {
                    url.addHeader(nextKey, map.getString(nextKey));
                } else {
                    FLog.w("ReactNative", "Ignoring: requested " + nextKey + ", value not a string");
                }
            }
        }
        if (readableArray != null && readableArray.size() > 0) {
            StringBuilder sb = new StringBuilder(HttpUrl.FRAGMENT_ENCODE_SET);
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                String trim = readableArray.getString(i2).trim();
                if (!trim.isEmpty() && !trim.contains(",")) {
                    sb.append(trim);
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.replace(sb.length() - 1, sb.length(), HttpUrl.FRAGMENT_ENCODE_SET);
                url.addHeader("Sec-WebSocket-Protocol", sb.toString());
            }
        }
        build.newWebSocket(url.build(), new WebSocketListener() { // from class: com.facebook.react.modules.websocket.WebSocketModule.1
            @Override // okhttp3.WebSocketListener
            public void onClosed(WebSocket webSocket, int i3, String str2) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putInt("code", i3);
                createMap.putString("reason", str2);
                WebSocketModule.this.sendEvent("websocketClosed", createMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onClosing(WebSocket webSocket, int i3, String str2) {
                webSocket.close(i3, str2);
            }

            @Override // okhttp3.WebSocketListener
            public void onFailure(WebSocket webSocket, Throwable th, Response response) {
                WebSocketModule.this.notifyWebSocketFailed(i, th.getMessage());
            }

            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, String str2) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString("type", "text");
                ContentHandler contentHandler = (ContentHandler) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(i));
                if (contentHandler != null) {
                    contentHandler.onMessage(str2, createMap);
                } else {
                    createMap.putString("data", str2);
                }
                WebSocketModule.this.sendEvent("websocketMessage", createMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onOpen(WebSocket webSocket, Response response) {
                WebSocketModule.this.mWebSocketConnections.put(Integer.valueOf(i), webSocket);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString("protocol", response.header("Sec-WebSocket-Protocol", HttpUrl.FRAGMENT_ENCODE_SET));
                WebSocketModule.this.sendEvent("websocketOpen", createMap);
            }

            @Override // okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, ByteString byteString) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("id", i);
                createMap.putString("type", "binary");
                ContentHandler contentHandler = (ContentHandler) WebSocketModule.this.mContentHandlers.get(Integer.valueOf(i));
                if (contentHandler != null) {
                    contentHandler.onMessage(byteString, createMap);
                } else {
                    createMap.putString("data", byteString.base64());
                }
                WebSocketModule.this.sendEvent("websocketMessage", createMap);
            }
        });
        build.dispatcher().executorService().shutdown();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "WebSocketModule";
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void ping(double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString(DialogModule.KEY_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.EMPTY);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void removeListeners(double d) {
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void send(String str, double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString(DialogModule.KEY_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(str);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    @Override // com.facebook.fbreact.specs.NativeWebSocketModuleSpec
    public void sendBinary(String str, double d) {
        int i = (int) d;
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString(DialogModule.KEY_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(ByteString.decodeBase64(str));
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }

    public void setContentHandler(int i, ContentHandler contentHandler) {
        if (contentHandler != null) {
            this.mContentHandlers.put(Integer.valueOf(i), contentHandler);
        } else {
            this.mContentHandlers.remove(Integer.valueOf(i));
        }
    }

    public void sendBinary(ByteString byteString, int i) {
        WebSocket webSocket = this.mWebSocketConnections.get(Integer.valueOf(i));
        if (webSocket == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", i);
            createMap.putString(DialogModule.KEY_MESSAGE, "client is null");
            sendEvent("websocketFailed", createMap);
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", i);
            createMap2.putInt("code", 0);
            createMap2.putString("reason", "client is null");
            sendEvent("websocketClosed", createMap2);
            this.mWebSocketConnections.remove(Integer.valueOf(i));
            this.mContentHandlers.remove(Integer.valueOf(i));
            return;
        }
        try {
            webSocket.send(byteString);
        } catch (Exception e) {
            notifyWebSocketFailed(i, e.getMessage());
        }
    }
}
