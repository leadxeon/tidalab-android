package com.reactnativecommunity.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Environment;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.R$dimen;
import androidx.webkit.internal.WebViewFeatureInternal;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder$Builder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.ContentSizeChangeEvent;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.scroll.OnScrollDispatchHelper;
import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.reactnativecommunity.webview.RNCWebViewModule;
import com.reactnativecommunity.webview.events.TopHttpErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingFinishEvent;
import com.reactnativecommunity.webview.events.TopLoadingProgressEvent;
import com.reactnativecommunity.webview.events.TopLoadingStartEvent;
import com.reactnativecommunity.webview.events.TopMessageEvent;
import com.reactnativecommunity.webview.events.TopRenderProcessGoneEvent;
import com.reactnativecommunity.webview.events.TopShouldStartLoadWithRequestEvent;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.HttpUrl;
import org.json.JSONException;
import org.json.JSONObject;
@ReactModule(name = "RNCWebView")
/* loaded from: classes.dex */
public class RNCWebViewManager extends SimpleViewManager<WebView> {
    public static final String BLANK_URL = "about:blank";
    public static final int COMMAND_CLEAR_CACHE = 1001;
    public static final int COMMAND_CLEAR_FORM_DATA = 1000;
    public static final int COMMAND_CLEAR_HISTORY = 1002;
    public static final int COMMAND_FOCUS = 8;
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_LOAD_URL = 7;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    public static final String HTML_ENCODING = "UTF-8";
    public static final String HTML_MIME_TYPE = "text/html";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String JAVASCRIPT_INTERFACE = "ReactNativeWebView";
    public static final String REACT_CLASS = "RNCWebView";
    public static final int SHOULD_OVERRIDE_URL_LOADING_TIMEOUT = 250;
    private static final String TAG = "RNCWebViewManager";
    public boolean mAllowsFullscreenVideo;
    public String mUserAgent;
    public String mUserAgentWithApplicationName;
    public RNCWebChromeClient mWebChromeClient;
    public WebViewConfig mWebViewConfig;

    /* loaded from: classes.dex */
    public static class RNCWebChromeClient extends WebChromeClient implements LifecycleEventListener {
        public static final FrameLayout.LayoutParams FULLSCREEN_LAYOUT_PARAMS = new FrameLayout.LayoutParams(-1, -1, 17);
        public GeolocationPermissions.Callback geolocationPermissionCallback;
        public String geolocationPermissionOrigin;
        public List<String> grantedPermissions;
        public WebChromeClient.CustomViewCallback mCustomViewCallback;
        public ReactContext mReactContext;
        public View mVideoView;
        public View mWebView;
        public PermissionRequest permissionRequest;
        public boolean permissionsRequestShown = false;
        public List<String> pendingPermissions = new ArrayList();
        public RNCWebView.ProgressChangedFilter progressChangedFilter = null;
        @TargetApi(21)
        public PermissionListener webviewPermissionsListener = new PermissionListener() { // from class: com.reactnativecommunity.webview.-$$Lambda$RNCWebViewManager$RNCWebChromeClient$N2j2xuGHR8sppvZE_gqUqeVJ4jc
            @Override // com.facebook.react.modules.core.PermissionListener
            public final boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
                PermissionRequest permissionRequest;
                List<String> list;
                List<String> list2;
                List<String> list3;
                List<String> list4;
                GeolocationPermissions.Callback callback;
                String str;
                RNCWebViewManager.RNCWebChromeClient rNCWebChromeClient = RNCWebViewManager.RNCWebChromeClient.this;
                rNCWebChromeClient.permissionsRequestShown = false;
                boolean z = false;
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    String str2 = strArr[i2];
                    boolean z2 = iArr[i2] == 0;
                    if (!(!str2.equals("android.permission.ACCESS_FINE_LOCATION") || (callback = rNCWebChromeClient.geolocationPermissionCallback) == null || (str = rNCWebChromeClient.geolocationPermissionOrigin) == null)) {
                        if (z2) {
                            callback.invoke(str, true, false);
                        } else {
                            callback.invoke(str, false, false);
                        }
                        rNCWebChromeClient.geolocationPermissionCallback = null;
                        rNCWebChromeClient.geolocationPermissionOrigin = null;
                    }
                    if (str2.equals("android.permission.RECORD_AUDIO")) {
                        if (z2 && (list4 = rNCWebChromeClient.grantedPermissions) != null) {
                            list4.add("android.webkit.resource.AUDIO_CAPTURE");
                        }
                        z = true;
                    }
                    if (str2.equals("android.permission.CAMERA")) {
                        if (z2 && (list3 = rNCWebChromeClient.grantedPermissions) != null) {
                            list3.add("android.webkit.resource.VIDEO_CAPTURE");
                        }
                        z = true;
                    }
                    if (str2.equals("android.webkit.resource.PROTECTED_MEDIA_ID")) {
                        if (z2 && (list2 = rNCWebChromeClient.grantedPermissions) != null) {
                            list2.add("android.webkit.resource.PROTECTED_MEDIA_ID");
                        }
                        z = true;
                    }
                }
                if (!(!z || (permissionRequest = rNCWebChromeClient.permissionRequest) == null || (list = rNCWebChromeClient.grantedPermissions) == null)) {
                    permissionRequest.grant((String[]) list.toArray(new String[0]));
                    rNCWebChromeClient.permissionRequest = null;
                    rNCWebChromeClient.grantedPermissions = null;
                }
                if (rNCWebChromeClient.pendingPermissions.isEmpty()) {
                    return true;
                }
                rNCWebChromeClient.requestPermissions(rNCWebChromeClient.pendingPermissions);
                return false;
            }
        };

        public RNCWebChromeClient(ReactContext reactContext, WebView webView) {
            this.mReactContext = reactContext;
            this.mWebView = webView;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            ((WebView.WebViewTransport) message.obj).setWebView(new WebView(webView.getContext()));
            message.sendToTarget();
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            if (ContextCompat.checkSelfPermission(this.mReactContext, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                this.geolocationPermissionCallback = callback;
                this.geolocationPermissionOrigin = str;
                requestPermissions(Collections.singletonList("android.permission.ACCESS_FINE_LOCATION"));
                return;
            }
            callback.invoke(str, true, false);
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
            View view = this.mVideoView;
            if (view != null && view.getSystemUiVisibility() != 7942) {
                this.mVideoView.setSystemUiVisibility(7942);
            }
        }

        @Override // android.webkit.WebChromeClient
        @TargetApi(21)
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            this.grantedPermissions = new ArrayList();
            ArrayList arrayList = new ArrayList();
            String[] resources = permissionRequest.getResources();
            int length = resources.length;
            int i = 0;
            while (true) {
                String str = null;
                if (i >= length) {
                    break;
                }
                String str2 = resources[i];
                if (str2.equals("android.webkit.resource.AUDIO_CAPTURE")) {
                    str = "android.permission.RECORD_AUDIO";
                } else if (str2.equals("android.webkit.resource.VIDEO_CAPTURE")) {
                    str = "android.permission.CAMERA";
                } else if (str2.equals("android.webkit.resource.PROTECTED_MEDIA_ID")) {
                    str = "android.webkit.resource.PROTECTED_MEDIA_ID";
                }
                if (str != null) {
                    if (ContextCompat.checkSelfPermission(this.mReactContext, str) == 0) {
                        this.grantedPermissions.add(str2);
                    } else {
                        arrayList.add(str);
                    }
                }
                i++;
            }
            if (arrayList.isEmpty()) {
                permissionRequest.grant((String[]) this.grantedPermissions.toArray(new String[0]));
                this.grantedPermissions = null;
                return;
            }
            this.permissionRequest = permissionRequest;
            requestPermissions(arrayList);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            String url = webView.getUrl();
            if (!this.progressChangedFilter.waitingForCommandLoadUrl) {
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("target", webView.getId());
                createMap.putString(DialogModule.KEY_TITLE, webView.getTitle());
                createMap.putString("url", url);
                createMap.putBoolean("canGoBack", webView.canGoBack());
                createMap.putBoolean("canGoForward", webView.canGoForward());
                createMap.putDouble(ReactProgressBarViewManager.PROP_PROGRESS, i / 100.0f);
                ((RNCWebView) webView).dispatchEvent(webView, new TopLoadingProgressEvent(webView.getId(), createMap));
            }
        }

        @Override // android.webkit.WebChromeClient
        @TargetApi(21)
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            String[] acceptTypes = fileChooserParams.getAcceptTypes();
            boolean z = true;
            if (fileChooserParams.getMode() != 1) {
                z = false;
            }
            return RNCWebViewManager.getModule(this.mReactContext).startPhotoPickerIntent(valueCallback, acceptTypes, z);
        }

        public final synchronized void requestPermissions(List<String> list) {
            if (this.permissionsRequestShown) {
                this.pendingPermissions.addAll(list);
                return;
            }
            Activity currentActivity = this.mReactContext.getCurrentActivity();
            if (currentActivity == null) {
                throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
            } else if (currentActivity instanceof PermissionAwareActivity) {
                this.permissionsRequestShown = true;
                ((PermissionAwareActivity) currentActivity).requestPermissions((String[]) list.toArray(new String[0]), 3, this.webviewPermissionsListener);
                this.pendingPermissions.clear();
            } else {
                throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
            }
        }
    }

    /* loaded from: classes.dex */
    public static class RNCWebView extends WebView implements LifecycleEventListener {
        public String injectedJS;
        public String injectedJSBeforeContentLoaded;
        public CatalystInstance mCatalystInstance;
        public OnScrollDispatchHelper mOnScrollDispatchHelper;
        public RNCWebViewClient mRNCWebViewClient;
        public WebChromeClient mWebChromeClient;
        public String messagingModuleName;
        public ProgressChangedFilter progressChangedFilter;
        public boolean messagingEnabled = false;
        public boolean sendContentSizeChangeEvents = false;
        public boolean hasScrollEvent = false;
        public boolean nestedScrollEnabled = false;

        /* loaded from: classes.dex */
        public static class ProgressChangedFilter {
            public boolean waitingForCommandLoadUrl = false;
        }

        /* loaded from: classes.dex */
        public class RNCWebViewBridge {
            public RNCWebView mContext;

            public RNCWebViewBridge(RNCWebView rNCWebView, RNCWebView rNCWebView2) {
                this.mContext = rNCWebView2;
            }

            @JavascriptInterface
            public void postMessage(final String str) {
                final RNCWebView rNCWebView = this.mContext;
                ReactContext reactContext = (ReactContext) rNCWebView.getContext();
                if (rNCWebView.mRNCWebViewClient != null) {
                    rNCWebView.post(new Runnable() { // from class: com.reactnativecommunity.webview.RNCWebViewManager.RNCWebView.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RNCWebViewClient rNCWebViewClient = RNCWebView.this.mRNCWebViewClient;
                            if (rNCWebViewClient != null) {
                                WebView webView = rNCWebView;
                                WritableMap createWebViewEvent = rNCWebViewClient.createWebViewEvent(webView, webView.getUrl());
                                createWebViewEvent.putString("data", str);
                                RNCWebView rNCWebView2 = RNCWebView.this;
                                if (rNCWebView2.mCatalystInstance != null) {
                                    rNCWebView.sendDirectMessage("onMessage", createWebViewEvent);
                                    return;
                                }
                                WebView webView2 = rNCWebView;
                                rNCWebView2.dispatchEvent(webView2, new TopMessageEvent(webView2.getId(), createWebViewEvent));
                            }
                        }
                    });
                    return;
                }
                WritableMap createMap = Arguments.createMap();
                createMap.putString("data", str);
                if (rNCWebView.mCatalystInstance != null) {
                    rNCWebView.sendDirectMessage("onMessage", createMap);
                } else {
                    rNCWebView.dispatchEvent(rNCWebView, new TopMessageEvent(rNCWebView.getId(), createMap));
                }
            }
        }

        public RNCWebView(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            ReactContext reactContext = (ReactContext) getContext();
            if (reactContext != null) {
                this.mCatalystInstance = reactContext.getCatalystInstance();
            }
            this.progressChangedFilter = new ProgressChangedFilter();
        }

        @Override // android.webkit.WebView
        public void destroy() {
            WebChromeClient webChromeClient = this.mWebChromeClient;
            if (webChromeClient != null) {
                webChromeClient.onHideCustomView();
            }
            super.destroy();
        }

        public void dispatchEvent(WebView webView, Event event) {
            ((UIManagerModule) ((ReactContext) webView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(event);
        }

        public RNCWebViewClient getRNCWebViewClient() {
            return this.mRNCWebViewClient;
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            setWebViewClient(null);
            destroy();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
        }

        @Override // android.webkit.WebView, android.view.View
        public void onScrollChanged(int i, int i2, int i3, int i4) {
            super.onScrollChanged(i, i2, i3, i4);
            if (this.hasScrollEvent) {
                if (this.mOnScrollDispatchHelper == null) {
                    this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
                }
                if (this.mOnScrollDispatchHelper.onScrollChanged(i, i2)) {
                    int id = getId();
                    ScrollEventType scrollEventType = ScrollEventType.SCROLL;
                    OnScrollDispatchHelper onScrollDispatchHelper = this.mOnScrollDispatchHelper;
                    dispatchEvent(this, ScrollEvent.obtain(id, scrollEventType, i, i2, onScrollDispatchHelper.mXFlingVelocity, onScrollDispatchHelper.mYFlingVelocity, computeHorizontalScrollRange(), computeVerticalScrollRange(), getWidth(), getHeight()));
                }
            }
        }

        @Override // android.webkit.WebView, android.view.View
        public void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            if (this.sendContentSizeChangeEvents) {
                dispatchEvent(this, new ContentSizeChangeEvent(getId(), i, i2));
            }
        }

        @Override // android.webkit.WebView, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (this.nestedScrollEnabled) {
                requestDisallowInterceptTouchEvent(true);
            }
            return super.onTouchEvent(motionEvent);
        }

        public void sendDirectMessage(String str, WritableMap writableMap) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putMap("nativeEvent", writableMap);
            WritableNativeArray writableNativeArray = new WritableNativeArray();
            writableNativeArray.pushMap(writableNativeMap);
            this.mCatalystInstance.callFunction(this.messagingModuleName, str, writableNativeArray);
        }

        public void setBasicAuthCredential(BasicAuthCredential basicAuthCredential) {
            this.mRNCWebViewClient.basicAuthCredential = basicAuthCredential;
        }

        public void setHasScrollEvent(boolean z) {
            this.hasScrollEvent = z;
        }

        public void setIgnoreErrFailedForThisURL(String str) {
            this.mRNCWebViewClient.ignoreErrFailedForThisURL = str;
        }

        public void setInjectedJavaScript(String str) {
            this.injectedJS = str;
        }

        public void setInjectedJavaScriptBeforeContentLoaded(String str) {
            this.injectedJSBeforeContentLoaded = str;
        }

        public void setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(boolean z) {
        }

        public void setInjectedJavaScriptForMainFrameOnly(boolean z) {
        }

        @SuppressLint({"AddJavascriptInterface"})
        public void setMessagingEnabled(boolean z) {
            if (this.messagingEnabled != z) {
                this.messagingEnabled = z;
                if (z) {
                    addJavascriptInterface(new RNCWebViewBridge(this, this), RNCWebViewManager.JAVASCRIPT_INTERFACE);
                } else {
                    removeJavascriptInterface(RNCWebViewManager.JAVASCRIPT_INTERFACE);
                }
            }
        }

        public void setMessagingModuleName(String str) {
            this.messagingModuleName = str;
        }

        public void setNestedScrollEnabled(boolean z) {
            this.nestedScrollEnabled = z;
        }

        public void setSendContentSizeChangeEvents(boolean z) {
            this.sendContentSizeChangeEvents = z;
        }

        @Override // android.webkit.WebView
        public void setWebChromeClient(WebChromeClient webChromeClient) {
            this.mWebChromeClient = webChromeClient;
            super.setWebChromeClient(webChromeClient);
            if (webChromeClient instanceof RNCWebChromeClient) {
                ((RNCWebChromeClient) webChromeClient).progressChangedFilter = this.progressChangedFilter;
            }
        }

        @Override // android.webkit.WebView
        public void setWebViewClient(WebViewClient webViewClient) {
            super.setWebViewClient(webViewClient);
            if (webViewClient instanceof RNCWebViewClient) {
                RNCWebViewClient rNCWebViewClient = (RNCWebViewClient) webViewClient;
                this.mRNCWebViewClient = rNCWebViewClient;
                rNCWebViewClient.progressChangedFilter = this.progressChangedFilter;
            }
        }
    }

    public RNCWebViewManager() {
        this.mWebChromeClient = null;
        this.mAllowsFullscreenVideo = false;
        this.mUserAgent = null;
        this.mUserAgentWithApplicationName = null;
        this.mWebViewConfig = new WebViewConfig(this) { // from class: com.reactnativecommunity.webview.RNCWebViewManager.1
        };
    }

    public static RNCWebViewModule getModule(ReactContext reactContext) {
        return (RNCWebViewModule) reactContext.getNativeModule(RNCWebViewModule.class);
    }

    public RNCWebView createRNCWebViewInstance(ThemedReactContext themedReactContext) {
        return new RNCWebView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        MapBuilder$Builder builder = R$style.builder();
        builder.put("goBack", 1);
        builder.put("goForward", 2);
        builder.put("reload", 3);
        builder.put("stopLoading", 4);
        builder.put("postMessage", 5);
        builder.put("injectJavaScript", 6);
        builder.put("loadUrl", 7);
        builder.put("requestFocus", 8);
        builder.put("clearFormData", Integer.valueOf((int) COMMAND_CLEAR_FORM_DATA));
        builder.put("clearCache", 1001);
        builder.put("clearHistory", Integer.valueOf((int) COMMAND_CLEAR_HISTORY));
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        exportedCustomDirectEventTypeConstants.put("topLoadingProgress", R$style.of("registrationName", "onLoadingProgress"));
        exportedCustomDirectEventTypeConstants.put("topShouldStartLoadWithRequest", R$style.of("registrationName", "onShouldStartLoadWithRequest"));
        exportedCustomDirectEventTypeConstants.put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), R$style.of("registrationName", "onScroll"));
        exportedCustomDirectEventTypeConstants.put("topHttpError", R$style.of("registrationName", "onHttpError"));
        exportedCustomDirectEventTypeConstants.put("topRenderProcessGone", R$style.of("registrationName", "onRenderProcessGone"));
        return exportedCustomDirectEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCWebView";
    }

    @ReactProp(name = "allowFileAccess")
    public void setAllowFileAccess(WebView webView, Boolean bool) {
        webView.getSettings().setAllowFileAccess(bool != null && bool.booleanValue());
    }

    @ReactProp(name = "allowFileAccessFromFileURLs")
    public void setAllowFileAccessFromFileURLs(WebView webView, boolean z) {
        webView.getSettings().setAllowFileAccessFromFileURLs(z);
    }

    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(WebView webView, boolean z) {
        webView.getSettings().setAllowUniversalAccessFromFileURLs(z);
    }

    @ReactProp(name = "allowsFullscreenVideo")
    public void setAllowsFullscreenVideo(WebView webView, Boolean bool) {
        this.mAllowsFullscreenVideo = bool != null && bool.booleanValue();
        setupWebChromeClient((ReactContext) webView.getContext(), webView);
    }

    @ReactProp(name = "applicationNameForUserAgent")
    public void setApplicationNameForUserAgent(WebView webView, String str) {
        if (str != null) {
            this.mUserAgentWithApplicationName = GeneratedOutlineSupport.outline9(WebSettings.getDefaultUserAgent(webView.getContext()), " ", str);
        } else {
            this.mUserAgentWithApplicationName = null;
        }
        setUserAgentString(webView);
    }

    @ReactProp(name = "basicAuthCredential")
    public void setBasicAuthCredential(WebView webView, ReadableMap readableMap) {
        ((RNCWebView) webView).setBasicAuthCredential((readableMap == null || !readableMap.hasKey("username") || !readableMap.hasKey("password")) ? null : new BasicAuthCredential(readableMap.getString("username"), readableMap.getString("password")));
    }

    @ReactProp(name = "setBuiltInZoomControls")
    public void setBuiltInZoomControls(WebView webView, boolean z) {
        webView.getSettings().setBuiltInZoomControls(z);
    }

    @ReactProp(name = "cacheEnabled")
    public void setCacheEnabled(WebView webView, boolean z) {
        if (z) {
            Context context = webView.getContext();
            if (context != null) {
                webView.getSettings().setAppCachePath(context.getCacheDir().getAbsolutePath());
                webView.getSettings().setCacheMode(-1);
                webView.getSettings().setAppCacheEnabled(true);
                return;
            }
            return;
        }
        webView.getSettings().setCacheMode(2);
        webView.getSettings().setAppCacheEnabled(false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @ReactProp(name = "cacheMode")
    public void setCacheMode(WebView webView, String str) {
        char c;
        Integer num;
        switch (str.hashCode()) {
            case -2059164003:
                if (str.equals("LOAD_NO_CACHE")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1215135800:
                if (str.equals("LOAD_DEFAULT")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -873877826:
                if (str.equals("LOAD_CACHE_ELSE_NETWORK")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1548620642:
                if (str.equals("LOAD_CACHE_ONLY")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            num = 3;
        } else if (c == 1) {
            num = 1;
        } else if (c != 2) {
            num = -1;
        } else {
            num = 2;
        }
        webView.getSettings().setCacheMode(num.intValue());
    }

    @ReactProp(name = "setDisplayZoomControls")
    public void setDisplayZoomControls(WebView webView, boolean z) {
        webView.getSettings().setDisplayZoomControls(z);
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(WebView webView, boolean z) {
        webView.getSettings().setDomStorageEnabled(z);
    }

    @ReactProp(name = "forceDarkOn")
    public void setForceDarkOn(WebView webView, boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            if (R$dimen.isFeatureSupported("FORCE_DARK")) {
                int i = z ? 2 : 0;
                WebSettings settings = webView.getSettings();
                if (WebViewFeatureInternal.FORCE_DARK.isSupportedByWebView()) {
                    R$dimen.getAdapter(settings).mBoundaryInterface.setForceDark(i);
                } else {
                    throw new UnsupportedOperationException("This method is not supported by the current version of the framework and the current WebView APK");
                }
            }
            if (z && R$dimen.isFeatureSupported("FORCE_DARK_STRATEGY")) {
                WebSettings settings2 = webView.getSettings();
                if (WebViewFeatureInternal.FORCE_DARK_STRATEGY.isSupportedByWebView()) {
                    R$dimen.getAdapter(settings2).mBoundaryInterface.setForceDarkBehavior(2);
                    return;
                }
                throw new UnsupportedOperationException("This method is not supported by the current version of the framework and the current WebView APK");
            }
        }
    }

    @ReactProp(name = "geolocationEnabled")
    public void setGeolocationEnabled(WebView webView, Boolean bool) {
        webView.getSettings().setGeolocationEnabled(bool != null && bool.booleanValue());
    }

    @ReactProp(name = "androidHardwareAccelerationDisabled")
    public void setHardwareAccelerationDisabled(WebView webView, boolean z) {
        if (z) {
            webView.setLayerType(1, null);
        }
    }

    @ReactProp(name = "incognito")
    public void setIncognito(WebView webView, boolean z) {
        if (z) {
            CookieManager.getInstance().removeAllCookies(null);
            webView.getSettings().setCacheMode(2);
            webView.getSettings().setAppCacheEnabled(false);
            webView.clearHistory();
            webView.clearCache(true);
            webView.clearFormData();
            webView.getSettings().setSavePassword(false);
            webView.getSettings().setSaveFormData(false);
        }
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(WebView webView, String str) {
        ((RNCWebView) webView).setInjectedJavaScript(str);
    }

    @ReactProp(name = "injectedJavaScriptBeforeContentLoaded")
    public void setInjectedJavaScriptBeforeContentLoaded(WebView webView, String str) {
        ((RNCWebView) webView).setInjectedJavaScriptBeforeContentLoaded(str);
    }

    @ReactProp(name = "injectedJavaScriptBeforeContentLoadedForMainFrameOnly")
    public void setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(WebView webView, boolean z) {
        ((RNCWebView) webView).setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(z);
    }

    @ReactProp(name = "injectedJavaScriptForMainFrameOnly")
    public void setInjectedJavaScriptForMainFrameOnly(WebView webView, boolean z) {
        ((RNCWebView) webView).setInjectedJavaScriptForMainFrameOnly(z);
    }

    @ReactProp(name = "javaScriptCanOpenWindowsAutomatically")
    public void setJavaScriptCanOpenWindowsAutomatically(WebView webView, boolean z) {
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(z);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(WebView webView, boolean z) {
        webView.getSettings().setJavaScriptEnabled(z);
    }

    @ReactProp(name = "androidLayerType")
    public void setLayerType(WebView webView, String str) {
        str.hashCode();
        webView.setLayerType(!str.equals("hardware") ? !str.equals("software") ? 0 : 1 : 2, null);
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    @TargetApi(17)
    public void setMediaPlaybackRequiresUserAction(WebView webView, boolean z) {
        webView.getSettings().setMediaPlaybackRequiresUserGesture(z);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(WebView webView, boolean z) {
        ((RNCWebView) webView).setMessagingEnabled(z);
    }

    @ReactProp(name = "messagingModuleName")
    public void setMessagingModuleName(WebView webView, String str) {
        ((RNCWebView) webView).setMessagingModuleName(str);
    }

    @ReactProp(name = "minimumFontSize")
    public void setMinimumFontSize(WebView webView, int i) {
        webView.getSettings().setMinimumFontSize(i);
    }

    @ReactProp(name = "mixedContentMode")
    public void setMixedContentMode(WebView webView, String str) {
        if (str == null || "never".equals(str)) {
            webView.getSettings().setMixedContentMode(1);
        } else if ("always".equals(str)) {
            webView.getSettings().setMixedContentMode(0);
        } else if ("compatibility".equals(str)) {
            webView.getSettings().setMixedContentMode(2);
        }
    }

    @ReactProp(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(WebView webView, boolean z) {
        ((RNCWebView) webView).setNestedScrollEnabled(z);
    }

    @ReactProp(name = "onContentSizeChange")
    public void setOnContentSizeChange(WebView webView, boolean z) {
        ((RNCWebView) webView).setSendContentSizeChangeEvents(z);
    }

    @ReactProp(name = "onScroll")
    public void setOnScroll(WebView webView, boolean z) {
        ((RNCWebView) webView).setHasScrollEvent(z);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(WebView webView, String str) {
        char c;
        Integer num;
        int hashCode = str.hashCode();
        if (hashCode == -1414557169) {
            if (str.equals("always")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 104712844) {
            if (hashCode == 951530617 && str.equals("content")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("never")) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            num = 2;
        } else if (c != 1) {
            num = 0;
        } else {
            num = 1;
        }
        webView.setOverScrollMode(num.intValue());
    }

    @ReactProp(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(WebView webView, boolean z) {
        webView.getSettings().setSaveFormData(!z);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(WebView webView, boolean z) {
        webView.getSettings().setLoadWithOverviewMode(z);
        webView.getSettings().setUseWideViewPort(z);
    }

    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(WebView webView, boolean z) {
        webView.setHorizontalScrollBarEnabled(z);
    }

    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(WebView webView, boolean z) {
        webView.setVerticalScrollBarEnabled(z);
    }

    @ReactProp(name = "source")
    public void setSource(WebView webView, ReadableMap readableMap) {
        if (readableMap != null) {
            if (readableMap.hasKey("html")) {
                webView.loadDataWithBaseURL(readableMap.hasKey("baseUrl") ? readableMap.getString("baseUrl") : HttpUrl.FRAGMENT_ENCODE_SET, readableMap.getString("html"), HTML_MIME_TYPE, HTML_ENCODING, null);
                return;
            } else if (readableMap.hasKey("uri")) {
                String string = readableMap.getString("uri");
                String url = webView.getUrl();
                if (url != null && url.equals(string)) {
                    return;
                }
                if (!readableMap.hasKey("method") || !readableMap.getString("method").equalsIgnoreCase(HTTP_METHOD_POST)) {
                    HashMap hashMap = new HashMap();
                    if (readableMap.hasKey("headers")) {
                        ReadableMap map = readableMap.getMap("headers");
                        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                        while (keySetIterator.hasNextKey()) {
                            String nextKey = keySetIterator.nextKey();
                            if (!"user-agent".equals(nextKey.toLowerCase(Locale.ENGLISH))) {
                                hashMap.put(nextKey, map.getString(nextKey));
                            } else if (webView.getSettings() != null) {
                                webView.getSettings().setUserAgentString(map.getString(nextKey));
                            }
                        }
                    }
                    webView.loadUrl(string, hashMap);
                    return;
                }
                byte[] bArr = null;
                if (readableMap.hasKey("body")) {
                    String string2 = readableMap.getString("body");
                    try {
                        bArr = string2.getBytes(HTML_ENCODING);
                    } catch (UnsupportedEncodingException unused) {
                        bArr = string2.getBytes();
                    }
                }
                if (bArr == null) {
                    bArr = new byte[0];
                }
                webView.postUrl(string, bArr);
                return;
            }
        }
        webView.loadUrl(BLANK_URL);
    }

    @ReactProp(name = "setSupportMultipleWindows")
    public void setSupportMultipleWindows(WebView webView, boolean z) {
        webView.getSettings().setSupportMultipleWindows(z);
    }

    @ReactProp(name = "textZoom")
    public void setTextZoom(WebView webView, int i) {
        webView.getSettings().setTextZoom(i);
    }

    @ReactProp(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(WebView webView, boolean z) {
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, z);
    }

    @ReactProp(name = "urlPrefixesForDefaultIntent")
    public void setUrlPrefixesForDefaultIntent(WebView webView, ReadableArray readableArray) {
        RNCWebViewClient rNCWebViewClient = ((RNCWebView) webView).getRNCWebViewClient();
        if (rNCWebViewClient != null && readableArray != null) {
            rNCWebViewClient.mUrlPrefixesForDefaultIntent = readableArray;
        }
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(WebView webView, String str) {
        if (str != null) {
            this.mUserAgent = str;
        } else {
            this.mUserAgent = null;
        }
        setUserAgentString(webView);
    }

    public void setUserAgentString(WebView webView) {
        if (this.mUserAgent != null) {
            webView.getSettings().setUserAgentString(this.mUserAgent);
        } else if (this.mUserAgentWithApplicationName != null) {
            webView.getSettings().setUserAgentString(this.mUserAgentWithApplicationName);
        } else {
            webView.getSettings().setUserAgentString(WebSettings.getDefaultUserAgent(webView.getContext()));
        }
    }

    public void setupWebChromeClient(ReactContext reactContext, WebView webView) {
        final Activity currentActivity = reactContext.getCurrentActivity();
        if (!this.mAllowsFullscreenVideo || currentActivity == null) {
            RNCWebChromeClient rNCWebChromeClient = this.mWebChromeClient;
            if (rNCWebChromeClient != null) {
                rNCWebChromeClient.onHideCustomView();
            }
            RNCWebChromeClient rNCWebChromeClient2 = new RNCWebChromeClient(this, reactContext, webView) { // from class: com.reactnativecommunity.webview.RNCWebViewManager.4
                @Override // android.webkit.WebChromeClient
                public Bitmap getDefaultVideoPoster() {
                    return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
                }
            };
            this.mWebChromeClient = rNCWebChromeClient2;
            webView.setWebChromeClient(rNCWebChromeClient2);
            return;
        }
        final int requestedOrientation = currentActivity.getRequestedOrientation();
        RNCWebChromeClient rNCWebChromeClient3 = new RNCWebChromeClient(this, reactContext, webView) { // from class: com.reactnativecommunity.webview.RNCWebViewManager.3
            @Override // android.webkit.WebChromeClient
            public Bitmap getDefaultVideoPoster() {
                return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
            }

            @Override // android.webkit.WebChromeClient
            public void onHideCustomView() {
                if (this.mVideoView != null) {
                    ViewGroup viewGroup = (ViewGroup) this.mReactContext.getCurrentActivity().findViewById(16908290);
                    if (viewGroup.getRootView() != this.mWebView.getRootView()) {
                        this.mWebView.getRootView().setVisibility(0);
                    } else {
                        this.mWebView.setVisibility(0);
                    }
                    currentActivity.getWindow().clearFlags(512);
                    viewGroup.removeView(this.mVideoView);
                    this.mCustomViewCallback.onCustomViewHidden();
                    this.mVideoView = null;
                    this.mCustomViewCallback = null;
                    currentActivity.setRequestedOrientation(requestedOrientation);
                    this.mReactContext.removeLifecycleEventListener(this);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
                if (this.mVideoView != null) {
                    customViewCallback.onCustomViewHidden();
                    return;
                }
                this.mVideoView = view;
                this.mCustomViewCallback = customViewCallback;
                currentActivity.setRequestedOrientation(-1);
                this.mVideoView.setSystemUiVisibility(7942);
                currentActivity.getWindow().setFlags(512, 512);
                this.mVideoView.setBackgroundColor(-16777216);
                ViewGroup viewGroup = (ViewGroup) this.mReactContext.getCurrentActivity().findViewById(16908290);
                viewGroup.addView(this.mVideoView, RNCWebChromeClient.FULLSCREEN_LAYOUT_PARAMS);
                if (viewGroup.getRootView() != this.mWebView.getRootView()) {
                    this.mWebView.getRootView().setVisibility(8);
                } else {
                    this.mWebView.setVisibility(8);
                }
                this.mReactContext.addLifecycleEventListener(this);
            }
        };
        this.mWebChromeClient = rNCWebChromeClient3;
        webView.setWebChromeClient(rNCWebChromeClient3);
    }

    public void addEventEmitters(ThemedReactContext themedReactContext, WebView webView) {
        webView.setWebViewClient(new RNCWebViewClient());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @TargetApi(21)
    public WebView createViewInstance(final ThemedReactContext themedReactContext) {
        final RNCWebView createRNCWebViewInstance = createRNCWebViewInstance(themedReactContext);
        setupWebChromeClient(themedReactContext, createRNCWebViewInstance);
        themedReactContext.mReactApplicationContext.addLifecycleEventListener(createRNCWebViewInstance);
        Objects.requireNonNull((AnonymousClass1) this.mWebViewConfig);
        WebSettings settings = createRNCWebViewInstance.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        setAllowUniversalAccessFromFileURLs(createRNCWebViewInstance, false);
        setMixedContentMode(createRNCWebViewInstance, "never");
        createRNCWebViewInstance.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        createRNCWebViewInstance.setDownloadListener(new DownloadListener(this) { // from class: com.reactnativecommunity.webview.RNCWebViewManager.2
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                createRNCWebViewInstance.setIgnoreErrFailedForThisURL(str);
                RNCWebViewModule module = RNCWebViewManager.getModule(themedReactContext);
                try {
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
                    String guessFileName = URLUtil.guessFileName(str, str3, str4);
                    String outline8 = GeneratedOutlineSupport.outline8("Downloading ", guessFileName);
                    try {
                        URL url = new URL(str);
                        request.addRequestHeader("Cookie", CookieManager.getInstance().getCookie(url.getProtocol() + "://" + url.getHost()));
                    } catch (MalformedURLException e) {
                        Log.w(RNCWebViewManager.TAG, "Error getting cookie for DownloadManager", e);
                    }
                    request.addRequestHeader("User-Agent", str2);
                    request.setTitle(guessFileName);
                    request.setDescription(outline8);
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(1);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, guessFileName);
                    module.setDownloadRequest(request);
                    if (module.grantFileDownloaderPermissions()) {
                        module.downloadFile();
                    }
                } catch (IllegalArgumentException e2) {
                    Log.w(RNCWebViewManager.TAG, "Unsupported URI, aborting download", e2);
                }
            }
        });
        return createRNCWebViewInstance;
    }

    public void onDropViewInstance(WebView webView) {
        super.onDropViewInstance((RNCWebViewManager) webView);
        RNCWebView rNCWebView = (RNCWebView) webView;
        ((ThemedReactContext) webView.getContext()).mReactApplicationContext.removeLifecycleEventListener(rNCWebView);
        rNCWebView.setWebViewClient(null);
        rNCWebView.destroy();
        this.mWebChromeClient = null;
    }

    public void receiveCommand(WebView webView, int i, ReadableArray readableArray) {
        boolean z = false;
        switch (i) {
            case 1:
                webView.goBack();
                return;
            case 2:
                webView.goForward();
                return;
            case COMMAND_RELOAD /* 3 */:
                webView.reload();
                return;
            case 4:
                webView.stopLoading();
                return;
            case 5:
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("data", readableArray.getString(0));
                    ((RNCWebView) webView).evaluateJavascript("(function () {var event;var data = " + jSONObject.toString() + ";try {event = new MessageEvent('message', data);} catch (e) {event = document.createEvent('MessageEvent');event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);}document.dispatchEvent(event);})();", null);
                    return;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            case 6:
                ((RNCWebView) webView).evaluateJavascript(readableArray.getString(0), null);
                return;
            case 7:
                if (readableArray != null) {
                    ((RNCWebView) webView).progressChangedFilter.waitingForCommandLoadUrl = false;
                    webView.loadUrl(readableArray.getString(0));
                    return;
                }
                throw new RuntimeException("Arguments for loading an url are null!");
            case 8:
                webView.requestFocus();
                return;
            default:
                switch (i) {
                    case COMMAND_CLEAR_FORM_DATA /* 1000 */:
                        webView.clearFormData();
                        return;
                    case 1001:
                        if (readableArray != null && readableArray.getBoolean(0)) {
                            z = true;
                        }
                        webView.clearCache(z);
                        return;
                    case COMMAND_CLEAR_HISTORY /* 1002 */:
                        webView.clearHistory();
                        return;
                    default:
                        return;
                }
        }
    }

    public RNCWebViewManager(WebViewConfig webViewConfig) {
        this.mWebChromeClient = null;
        this.mAllowsFullscreenVideo = false;
        this.mUserAgent = null;
        this.mUserAgentWithApplicationName = null;
        this.mWebViewConfig = webViewConfig;
    }

    /* loaded from: classes.dex */
    public static class RNCWebViewClient extends WebViewClient {
        public ReadableArray mUrlPrefixesForDefaultIntent;
        public boolean mLastLoadFailed = false;
        public RNCWebView.ProgressChangedFilter progressChangedFilter = null;
        public String ignoreErrFailedForThisURL = null;
        public BasicAuthCredential basicAuthCredential = null;

        public WritableMap createWebViewEvent(WebView webView, String str) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("target", webView.getId());
            createMap.putString("url", str);
            createMap.putBoolean("loading", !this.mLastLoadFailed && webView.getProgress() != 100);
            createMap.putString(DialogModule.KEY_TITLE, webView.getTitle());
            createMap.putBoolean("canGoBack", webView.canGoBack());
            createMap.putBoolean("canGoForward", webView.canGoForward());
            return createMap;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            String str2;
            super.onPageFinished(webView, str);
            if (!this.mLastLoadFailed) {
                RNCWebView rNCWebView = (RNCWebView) webView;
                if (rNCWebView.getSettings().getJavaScriptEnabled() && (str2 = rNCWebView.injectedJS) != null && !TextUtils.isEmpty(str2)) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("(function() {\n");
                    outline13.append(rNCWebView.injectedJS);
                    outline13.append(";\n})();");
                    rNCWebView.evaluateJavascript(outline13.toString(), null);
                }
                rNCWebView.dispatchEvent(webView, new TopLoadingFinishEvent(webView.getId(), createWebViewEvent(webView, str)));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            String str2;
            super.onPageStarted(webView, str, bitmap);
            this.mLastLoadFailed = false;
            RNCWebView rNCWebView = (RNCWebView) webView;
            if (rNCWebView.getSettings().getJavaScriptEnabled() && (str2 = rNCWebView.injectedJSBeforeContentLoaded) != null && !TextUtils.isEmpty(str2)) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("(function() {\n");
                outline13.append(rNCWebView.injectedJSBeforeContentLoaded);
                outline13.append(";\n})();");
                rNCWebView.evaluateJavascript(outline13.toString(), null);
            }
            rNCWebView.dispatchEvent(webView, new TopLoadingStartEvent(webView.getId(), createWebViewEvent(webView, str)));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            String str3 = this.ignoreErrFailedForThisURL;
            if (str3 == null || !str2.equals(str3) || i != -1 || !str.equals("net::ERR_FAILED")) {
                super.onReceivedError(webView, i, str, str2);
                this.mLastLoadFailed = true;
                ((RNCWebView) webView).dispatchEvent(webView, new TopLoadingFinishEvent(webView.getId(), createWebViewEvent(webView, str2)));
                WritableMap createWebViewEvent = createWebViewEvent(webView, str2);
                createWebViewEvent.putDouble("code", i);
                createWebViewEvent.putString("description", str);
                ((RNCWebView) webView).dispatchEvent(webView, new TopLoadingErrorEvent(webView.getId(), createWebViewEvent));
                return;
            }
            this.ignoreErrFailedForThisURL = null;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            BasicAuthCredential basicAuthCredential = this.basicAuthCredential;
            if (basicAuthCredential != null) {
                httpAuthHandler.proceed(basicAuthCredential.username, basicAuthCredential.password);
            } else {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            if (webResourceRequest.isForMainFrame()) {
                WritableMap createWebViewEvent = createWebViewEvent(webView, webResourceRequest.getUrl().toString());
                createWebViewEvent.putInt("statusCode", webResourceResponse.getStatusCode());
                createWebViewEvent.putString("description", webResourceResponse.getReasonPhrase());
                ((RNCWebView) webView).dispatchEvent(webView, new TopHttpErrorEvent(webView.getId(), createWebViewEvent));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            String url = webView.getUrl();
            String url2 = sslError.getUrl();
            sslErrorHandler.cancel();
            if (!url.equalsIgnoreCase(url2)) {
                Log.w(RNCWebViewManager.TAG, "Resource blocked from loading due to SSL error. Blocked URL: " + url2);
                return;
            }
            int primaryError = sslError.getPrimaryError();
            onReceivedError(webView, primaryError, GeneratedOutlineSupport.outline8("SSL error: ", primaryError != 0 ? primaryError != 1 ? primaryError != 2 ? primaryError != 3 ? primaryError != 4 ? primaryError != 5 ? "Unknown SSL Error" : "A generic error occurred" : "The date of the certificate is invalid" : "The certificate authority is not trusted" : "Hostname mismatch" : "The certificate has expired" : "The certificate is not yet valid"), url2);
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(26)
        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            if (Build.VERSION.SDK_INT < 26) {
                return false;
            }
            super.onRenderProcessGone(webView, renderProcessGoneDetail);
            if (renderProcessGoneDetail.didCrash()) {
                Log.e(RNCWebViewManager.TAG, "The WebView rendering process crashed.");
            } else {
                Log.w(RNCWebViewManager.TAG, "The WebView rendering process was killed by the system.");
            }
            if (webView == null) {
                return true;
            }
            WritableMap createWebViewEvent = createWebViewEvent(webView, webView.getUrl());
            createWebViewEvent.putBoolean("didCrash", renderProcessGoneDetail.didCrash());
            ((RNCWebView) webView).dispatchEvent(webView, new TopRenderProcessGoneEvent(webView.getId(), createWebViewEvent));
            return true;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            AtomicReference<RNCWebViewModule.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState> atomicReference;
            Integer valueOf;
            RNCWebViewModule.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState shouldOverrideCallbackState = RNCWebViewModule.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.UNDECIDED;
            RNCWebView rNCWebView = (RNCWebView) webView;
            boolean z = false;
            if ((((ReactContext) webView.getContext()).getJavaScriptContextHolder().get() == 0) || rNCWebView.mCatalystInstance == null) {
                FLog.w(RNCWebViewManager.TAG, "Couldn't use blocking synchronous call for onShouldStartLoadWithRequest due to debugging or missing Catalyst instance, falling back to old event-and-load.");
                this.progressChangedFilter.waitingForCommandLoadUrl = true;
                ((RNCWebView) webView).dispatchEvent(webView, new TopShouldStartLoadWithRequestEvent(webView.getId(), createWebViewEvent(webView, str)));
                return true;
            }
            RNCWebViewModule.ShouldOverrideUrlLoadingLock shouldOverrideUrlLoadingLock = RNCWebViewModule.shouldOverrideUrlLoadingLock;
            synchronized (shouldOverrideUrlLoadingLock) {
                int i = shouldOverrideUrlLoadingLock.nextLockIdentifier;
                shouldOverrideUrlLoadingLock.nextLockIdentifier = i + 1;
                atomicReference = new AtomicReference<>(shouldOverrideCallbackState);
                shouldOverrideUrlLoadingLock.shouldOverrideLocks.put(Integer.valueOf(i), atomicReference);
                valueOf = Integer.valueOf(i);
            }
            int intValue = valueOf.intValue();
            WritableMap createWebViewEvent = createWebViewEvent(webView, str);
            createWebViewEvent.putInt("lockIdentifier", intValue);
            rNCWebView.sendDirectMessage("onShouldStartLoadWithRequest", createWebViewEvent);
            try {
                synchronized (atomicReference) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    while (atomicReference.get() == shouldOverrideCallbackState) {
                        if (SystemClock.elapsedRealtime() - elapsedRealtime > 250) {
                            FLog.w(RNCWebViewManager.TAG, "Did not receive response to shouldOverrideUrlLoading in time, defaulting to allow loading.");
                            RNCWebViewModule.shouldOverrideUrlLoadingLock.removeLock(Integer.valueOf(intValue));
                            return false;
                        }
                        atomicReference.wait(250L);
                    }
                    if (atomicReference.get() == RNCWebViewModule.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.SHOULD_OVERRIDE) {
                        z = true;
                    }
                    RNCWebViewModule.shouldOverrideUrlLoadingLock.removeLock(Integer.valueOf(intValue));
                    return z;
                }
            } catch (InterruptedException e) {
                FLog.e(RNCWebViewManager.TAG, "shouldOverrideUrlLoading was interrupted while waiting for result.", e);
                RNCWebViewModule.shouldOverrideUrlLoadingLock.removeLock(Integer.valueOf(intValue));
                return false;
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
        }
    }
}
