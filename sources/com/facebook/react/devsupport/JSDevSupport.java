package com.facebook.react.devsupport;

import android.view.View;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.devsupport.JSCHeapCapture;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;
@ReactModule(name = JSDevSupport.MODULE_NAME)
/* loaded from: classes.dex */
public class JSDevSupport extends ReactContextBaseJavaModule {
    public static final int ERROR_CODE_EXCEPTION = 0;
    public static final int ERROR_CODE_VIEW_NOT_FOUND = 1;
    public static final String MODULE_NAME = "JSDevSupport";
    private volatile DevSupportCallback mCurrentCallback = null;

    /* loaded from: classes.dex */
    public interface DevSupportCallback {
        void onFailure(int i, Exception exc);

        void onSuccess(String str);
    }

    /* loaded from: classes.dex */
    public interface JSDevSupportModule extends JavaScriptModule {
        void getJSHierarchy(int i);
    }

    public JSDevSupport(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public synchronized void computeDeepestJSHierarchy(View view, DevSupportCallback devSupportCallback) {
        getJSHierarchy(Integer.valueOf(((View) R$style.getDeepestLeaf(view).first).getId()).intValue(), devSupportCallback);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("ERROR_CODE_EXCEPTION", 0);
        hashMap.put("ERROR_CODE_VIEW_NOT_FOUND", 1);
        return hashMap;
    }

    public synchronized void getJSHierarchy(int i, DevSupportCallback devSupportCallback) {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        JSDevSupportModule jSDevSupportModule = null;
        if (reactApplicationContextIfActiveOrWarn != null) {
            jSDevSupportModule = (JSDevSupportModule) reactApplicationContextIfActiveOrWarn.getJSModule(JSDevSupportModule.class);
        }
        if (jSDevSupportModule == null) {
            devSupportCallback.onFailure(0, new JSCHeapCapture.CaptureException("JSDevSupport module not registered."));
            return;
        }
        this.mCurrentCallback = devSupportCallback;
        jSDevSupportModule.getJSHierarchy(i);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public synchronized void onFailure(int i, String str) {
        if (this.mCurrentCallback != null) {
            this.mCurrentCallback.onFailure(i, new RuntimeException(str));
        }
    }

    @ReactMethod
    public synchronized void onSuccess(String str) {
        if (this.mCurrentCallback != null) {
            this.mCurrentCallback.onSuccess(str);
        }
    }
}
