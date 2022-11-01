package com.facebook.react.modules.share;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.dialog.DialogModule;
@ReactModule(name = ShareModule.NAME)
/* loaded from: classes.dex */
public class ShareModule extends ReactContextBaseJavaModule {
    public static final String ACTION_SHARED = "sharedAction";
    public static final String ERROR_INVALID_CONTENT = "E_INVALID_CONTENT";
    public static final String ERROR_UNABLE_TO_OPEN_DIALOG = "E_UNABLE_TO_OPEN_DIALOG";
    public static final String NAME = "ShareModule";

    public ShareModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void share(ReadableMap readableMap, String str, Promise promise) {
        if (readableMap == null) {
            promise.reject(ERROR_INVALID_CONTENT, "Content cannot be null");
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setTypeAndNormalize("text/plain");
            if (readableMap.hasKey(DialogModule.KEY_TITLE)) {
                intent.putExtra("android.intent.extra.SUBJECT", readableMap.getString(DialogModule.KEY_TITLE));
            }
            if (readableMap.hasKey(DialogModule.KEY_MESSAGE)) {
                intent.putExtra("android.intent.extra.TEXT", readableMap.getString(DialogModule.KEY_MESSAGE));
            }
            Intent createChooser = Intent.createChooser(intent, str);
            createChooser.addCategory("android.intent.category.DEFAULT");
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                currentActivity.startActivity(createChooser);
            } else {
                getReactApplicationContext().startActivity(createChooser);
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putString("action", ACTION_SHARED);
            promise.resolve(createMap);
        } catch (Exception unused) {
            promise.reject(ERROR_UNABLE_TO_OPEN_DIALOG, "Failed to open share dialog");
        }
    }
}
