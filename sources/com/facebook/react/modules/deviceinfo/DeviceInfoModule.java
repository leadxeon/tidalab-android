package com.facebook.react.modules.deviceinfo;

import android.content.Context;
import androidx.recyclerview.R$dimen;
import com.facebook.react.R$style;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.HashMap;
import java.util.Map;
@ReactModule(name = DeviceInfoModule.NAME)
/* loaded from: classes.dex */
public class DeviceInfoModule extends ReactContextBaseJavaModule implements LifecycleEventListener, TurboModule {
    public static final String NAME = "DeviceInfo";
    private float mFontScale;
    private ReactApplicationContext mReactApplicationContext;

    public DeviceInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        R$style.initDisplayMetricsIfNotInitialized(reactApplicationContext);
        this.mFontScale = reactApplicationContext.getResources().getConfiguration().fontScale;
        this.mReactApplicationContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
    }

    public void emitUpdateDimensionsEvent() {
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        if (reactApplicationContext != null) {
            if (reactApplicationContext.hasActiveCatalystInstance()) {
                DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
                double d = this.mFontScale;
                R$dimen.assertNotNull(Boolean.valueOf((R$style.sWindowDisplayMetrics == null && R$style.sScreenDisplayMetrics == null) ? false : true), "DisplayMetricsHolder must be initialized with initDisplayMetricsIfNotInitialized or initDisplayMetrics");
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putMap("windowPhysicalPixels", R$style.getPhysicalPixelsNativeMap(R$style.sWindowDisplayMetrics, d));
                writableNativeMap.putMap("screenPhysicalPixels", R$style.getPhysicalPixelsNativeMap(R$style.sScreenDisplayMetrics, d));
                rCTDeviceEventEmitter.emit("didUpdateDimensions", writableNativeMap);
                return;
            }
            ReactSoftException.logSoftException(NAME, new ReactNoCrashSoftException("No active CatalystInstance, cannot emitUpdateDimensionsEvent"));
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        double d = this.mFontScale;
        R$dimen.assertNotNull(Boolean.valueOf((R$style.sWindowDisplayMetrics == null && R$style.sScreenDisplayMetrics == null) ? false : true), "DisplayMetricsHolder must be initialized with initDisplayMetricsIfNotInitialized or initDisplayMetrics");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("windowPhysicalPixels", R$style.getPhysicalPixelsMap(R$style.sWindowDisplayMetrics, d));
        hashMap2.put("screenPhysicalPixels", R$style.getPhysicalPixelsMap(R$style.sScreenDisplayMetrics, d));
        hashMap.put("Dimensions", hashMap2);
        return hashMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public void invalidate() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        if (reactApplicationContext != null) {
            float f = reactApplicationContext.getResources().getConfiguration().fontScale;
            if (this.mFontScale != f) {
                this.mFontScale = f;
                emitUpdateDimensionsEvent();
            }
        }
    }

    public DeviceInfoModule(Context context) {
        super(null);
        this.mReactApplicationContext = null;
        R$style.initDisplayMetricsIfNotInitialized(context);
        this.mFontScale = context.getResources().getConfiguration().fontScale;
    }
}
