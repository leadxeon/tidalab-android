package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes.dex */
public abstract class NativeImagePickerIOSSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeImagePickerIOSSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void canRecordVideos(Callback callback);

    @ReactMethod
    public abstract void canUseCamera(Callback callback);

    @ReactMethod
    public abstract void clearAllPendingVideos();

    @ReactMethod
    public abstract void openCameraDialog(ReadableMap readableMap, Callback callback, Callback callback2);

    @ReactMethod
    public abstract void openSelectDialog(ReadableMap readableMap, Callback callback, Callback callback2);

    @ReactMethod
    public abstract void removePendingVideo(String str);
}
