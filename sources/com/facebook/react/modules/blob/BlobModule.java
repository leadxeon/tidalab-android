package com.facebook.react.modules.blob;

import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.ByteString;
@ReactModule(name = BlobModule.NAME)
/* loaded from: classes.dex */
public class BlobModule extends ReactContextBaseJavaModule {
    public static final String NAME = "BlobModule";
    private final Map<String, byte[]> mBlobs = new HashMap();
    private final WebSocketModule.ContentHandler mWebSocketContentHandler = new WebSocketModule.ContentHandler() { // from class: com.facebook.react.modules.blob.BlobModule.1
        @Override // com.facebook.react.modules.websocket.WebSocketModule.ContentHandler
        public void onMessage(String str, WritableMap writableMap) {
            writableMap.putString("data", str);
        }

        @Override // com.facebook.react.modules.websocket.WebSocketModule.ContentHandler
        public void onMessage(ByteString byteString, WritableMap writableMap) {
            byte[] byteArray = byteString.toByteArray();
            WritableMap createMap = Arguments.createMap();
            createMap.putString("blobId", BlobModule.this.store(byteArray));
            createMap.putInt("offset", 0);
            createMap.putInt("size", byteArray.length);
            writableMap.putMap("data", createMap);
            writableMap.putString("type", "blob");
        }
    };
    private final NetworkingModule.UriHandler mNetworkingUriHandler = new NetworkingModule.UriHandler() { // from class: com.facebook.react.modules.blob.BlobModule.2
        @Override // com.facebook.react.modules.network.NetworkingModule.UriHandler
        public WritableMap fetch(Uri uri) throws IOException {
            byte[] bytesFromUri = BlobModule.this.getBytesFromUri(uri);
            WritableMap createMap = Arguments.createMap();
            createMap.putString("blobId", BlobModule.this.store(bytesFromUri));
            createMap.putInt("offset", 0);
            createMap.putInt("size", bytesFromUri.length);
            createMap.putString("type", BlobModule.this.getMimeTypeFromUri(uri));
            createMap.putString("name", BlobModule.this.getNameFromUri(uri));
            createMap.putDouble("lastModified", BlobModule.this.getLastModifiedFromUri(uri));
            return createMap;
        }

        @Override // com.facebook.react.modules.network.NetworkingModule.UriHandler
        public boolean supports(Uri uri, String str) {
            String scheme = uri.getScheme();
            return !("http".equals(scheme) || "https".equals(scheme)) && "blob".equals(str);
        }
    };
    private final NetworkingModule.RequestBodyHandler mNetworkingRequestBodyHandler = new NetworkingModule.RequestBodyHandler() { // from class: com.facebook.react.modules.blob.BlobModule.3
        @Override // com.facebook.react.modules.network.NetworkingModule.RequestBodyHandler
        public boolean supports(ReadableMap readableMap) {
            return readableMap.hasKey("blob");
        }

        @Override // com.facebook.react.modules.network.NetworkingModule.RequestBodyHandler
        public RequestBody toRequestBody(ReadableMap readableMap, String str) {
            if (readableMap.hasKey("type") && !readableMap.getString("type").isEmpty()) {
                str = readableMap.getString("type");
            }
            if (str == null) {
                str = "application/octet-stream";
            }
            ReadableMap map = readableMap.getMap("blob");
            return RequestBody.create(MediaType.parse(str), BlobModule.this.resolve(map.getString("blobId"), map.getInt("offset"), map.getInt("size")));
        }
    };
    private final NetworkingModule.ResponseHandler mNetworkingResponseHandler = new NetworkingModule.ResponseHandler() { // from class: com.facebook.react.modules.blob.BlobModule.4
        @Override // com.facebook.react.modules.network.NetworkingModule.ResponseHandler
        public boolean supports(String str) {
            return "blob".equals(str);
        }

        @Override // com.facebook.react.modules.network.NetworkingModule.ResponseHandler
        public WritableMap toResponseData(ResponseBody responseBody) throws IOException {
            byte[] bytes = responseBody.bytes();
            WritableMap createMap = Arguments.createMap();
            createMap.putString("blobId", BlobModule.this.store(bytes));
            createMap.putInt("offset", 0);
            createMap.putInt("size", bytes.length);
            return createMap;
        }
    };

    public BlobModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getBytesFromUri(Uri uri) throws IOException {
        InputStream openInputStream = getReactApplicationContext().getContentResolver().openInputStream(uri);
        if (openInputStream != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } else {
            throw new FileNotFoundException("File not found for " + uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getLastModifiedFromUri(Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return new File(uri.toString()).lastModified();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getMimeTypeFromUri(Uri uri) {
        String fileExtensionFromUrl;
        String type = getReactApplicationContext().getContentResolver().getType(uri);
        if (type == null && (fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.getPath())) != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return type == null ? HttpUrl.FRAGMENT_ENCODE_SET : type;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNameFromUri(Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return uri.getLastPathSegment();
        }
        Cursor query = getReactApplicationContext().getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    return query.getString(0);
                }
            } finally {
                query.close();
            }
        }
        return uri.getLastPathSegment();
    }

    private WebSocketModule getWebSocketModule(String str) {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            return (WebSocketModule) reactApplicationContextIfActiveOrWarn.getNativeModule(WebSocketModule.class);
        }
        return null;
    }

    @ReactMethod
    public void addNetworkingHandler() {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            NetworkingModule networkingModule = (NetworkingModule) reactApplicationContextIfActiveOrWarn.getNativeModule(NetworkingModule.class);
            networkingModule.addUriHandler(this.mNetworkingUriHandler);
            networkingModule.addRequestBodyHandler(this.mNetworkingRequestBodyHandler);
            networkingModule.addResponseHandler(this.mNetworkingResponseHandler);
        }
    }

    @ReactMethod
    public void addWebSocketHandler(int i) {
        WebSocketModule webSocketModule = getWebSocketModule("addWebSocketHandler");
        if (webSocketModule != null) {
            webSocketModule.setContentHandler(i, this.mWebSocketContentHandler);
        }
    }

    @ReactMethod
    public void createFromParts(ReadableArray readableArray, String str) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        int i = 0;
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            ReadableMap map = readableArray.getMap(i2);
            String string = map.getString("type");
            string.hashCode();
            if (string.equals("string")) {
                byte[] bytes = map.getString("data").getBytes(Charset.forName(RNCWebViewManager.HTML_ENCODING));
                i += bytes.length;
                arrayList.add(i2, bytes);
            } else if (!string.equals("blob")) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid type for blob: ");
                outline13.append(map.getString("type"));
                throw new IllegalArgumentException(outline13.toString());
            } else {
                ReadableMap map2 = map.getMap("data");
                i = map2.getInt("size") + i;
                arrayList.add(i2, resolve(map2));
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            allocate.put((byte[]) it.next());
        }
        store(allocate.array(), str);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        Resources resources = getReactApplicationContext().getResources();
        int identifier = resources.getIdentifier("blob_provider_authority", "string", getReactApplicationContext().getPackageName());
        if (identifier == 0) {
            return null;
        }
        return R$style.of("BLOB_URI_SCHEME", "content", "BLOB_URI_HOST", resources.getString(identifier));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        int i = BlobCollector.$r8$clinit;
        reactApplicationContext.runOnJSQueueThread(new Runnable() { // from class: com.facebook.react.modules.blob.BlobCollector.1
            @Override // java.lang.Runnable
            public void run() {
                JavaScriptContextHolder javaScriptContextHolder = ReactContext.this.getJavaScriptContextHolder();
                if (javaScriptContextHolder.get() != 0) {
                    BlobCollector.nativeInstall(this, javaScriptContextHolder.get());
                }
            }
        });
    }

    @ReactMethod
    public void release(String str) {
        remove(str);
    }

    @DoNotStrip
    public void remove(String str) {
        synchronized (this.mBlobs) {
            this.mBlobs.remove(str);
        }
    }

    @ReactMethod
    public void removeWebSocketHandler(int i) {
        WebSocketModule webSocketModule = getWebSocketModule("removeWebSocketHandler");
        if (webSocketModule != null) {
            webSocketModule.setContentHandler(i, null);
        }
    }

    public byte[] resolve(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        String queryParameter = uri.getQueryParameter("offset");
        int parseInt = queryParameter != null ? Integer.parseInt(queryParameter, 10) : 0;
        String queryParameter2 = uri.getQueryParameter("size");
        return resolve(lastPathSegment, parseInt, queryParameter2 != null ? Integer.parseInt(queryParameter2, 10) : -1);
    }

    @ReactMethod
    public void sendOverSocket(ReadableMap readableMap, int i) {
        WebSocketModule webSocketModule = getWebSocketModule("sendOverSocket");
        if (webSocketModule != null) {
            byte[] resolve = resolve(readableMap.getString("blobId"), readableMap.getInt("offset"), readableMap.getInt("size"));
            if (resolve != null) {
                webSocketModule.sendBinary(ByteString.of(resolve), i);
            } else {
                webSocketModule.sendBinary((ByteString) null, i);
            }
        }
    }

    public String store(byte[] bArr) {
        String uuid = UUID.randomUUID().toString();
        store(bArr, uuid);
        return uuid;
    }

    public void store(byte[] bArr, String str) {
        synchronized (this.mBlobs) {
            this.mBlobs.put(str, bArr);
        }
    }

    public byte[] resolve(String str, int i, int i2) {
        synchronized (this.mBlobs) {
            byte[] bArr = this.mBlobs.get(str);
            if (bArr == null) {
                return null;
            }
            if (i2 == -1) {
                i2 = bArr.length - i;
            }
            if (i > 0 || i2 != bArr.length) {
                bArr = Arrays.copyOfRange(bArr, i, i2 + i);
            }
            return bArr;
        }
    }

    public byte[] resolve(ReadableMap readableMap) {
        return resolve(readableMap.getString("blobId"), readableMap.getInt("offset"), readableMap.getInt("size"));
    }
}
