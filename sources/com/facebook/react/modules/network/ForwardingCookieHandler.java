package com.facebook.react.modules.network;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public class ForwardingCookieHandler extends CookieHandler {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ReactContext mContext;
    public CookieManager mCookieManager;
    public final CookieSaver mCookieSaver = new CookieSaver();

    /* loaded from: classes.dex */
    public class CookieSaver {
        public final Handler mHandler;

        public CookieSaver() {
            this.mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback(ForwardingCookieHandler.this) { // from class: com.facebook.react.modules.network.ForwardingCookieHandler.CookieSaver.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    if (message.what != 1) {
                        return false;
                    }
                    CookieSaver.this.persistCookies();
                    return true;
                }
            });
        }

        public void persistCookies() {
            this.mHandler.removeMessages(1);
            ForwardingCookieHandler forwardingCookieHandler = ForwardingCookieHandler.this;
            Runnable runnable = new Runnable() { // from class: com.facebook.react.modules.network.ForwardingCookieHandler.CookieSaver.2
                @Override // java.lang.Runnable
                public void run() {
                    int i = ForwardingCookieHandler.$r8$clinit;
                    CookieManager cookieManager = ForwardingCookieHandler.this.getCookieManager();
                    if (cookieManager != null) {
                        cookieManager.flush();
                    }
                }
            };
            Objects.requireNonNull(forwardingCookieHandler);
            new GuardedAsyncTask<Void, Void>(forwardingCookieHandler, forwardingCookieHandler.mContext, runnable) { // from class: com.facebook.react.modules.network.ForwardingCookieHandler.4
                public final /* synthetic */ Runnable val$runnable;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(r2);
                    this.val$runnable = runnable;
                }

                @Override // com.facebook.react.bridge.GuardedAsyncTask
                public void doInBackgroundGuarded(Void[] voidArr) {
                    this.val$runnable.run();
                }
            }.execute(new Void[0]);
        }
    }

    public ForwardingCookieHandler(ReactContext reactContext) {
        this.mContext = reactContext;
    }

    @Override // java.net.CookieHandler
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        CookieManager cookieManager = getCookieManager();
        if (cookieManager == null) {
            return Collections.emptyMap();
        }
        String cookie = cookieManager.getCookie(uri.toString());
        if (TextUtils.isEmpty(cookie)) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("Cookie", Collections.singletonList(cookie));
    }

    public final CookieManager getCookieManager() {
        if (this.mCookieManager == null) {
            try {
                this.mCookieManager = CookieManager.getInstance();
            } catch (IllegalArgumentException unused) {
                return null;
            } catch (Exception e) {
                if (e.getMessage() != null && e.getClass().getCanonicalName().equals("android.webkit.WebViewFactory.MissingWebViewPackageException")) {
                    return null;
                }
                throw e;
            }
        }
        return this.mCookieManager;
    }

    @Override // java.net.CookieHandler
    public void put(URI uri, Map<String, List<String>> map) throws IOException {
        String uri2 = uri.toString();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                if (key.equalsIgnoreCase("Set-cookie") || key.equalsIgnoreCase("Set-cookie2")) {
                    List<String> value = entry.getValue();
                    CookieManager cookieManager = getCookieManager();
                    if (cookieManager != null) {
                        for (String str : value) {
                            CookieManager cookieManager2 = getCookieManager();
                            if (cookieManager2 != null) {
                                cookieManager2.setCookie(uri2, str, null);
                            }
                        }
                        cookieManager.flush();
                        Objects.requireNonNull(this.mCookieSaver);
                    }
                }
            }
        }
    }
}
