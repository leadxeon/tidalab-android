package com.facebook.react.modules.vibration;

import android.annotation.SuppressLint;
import android.os.Vibrator;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = VibrationModule.NAME)
@SuppressLint({"MissingPermission"})
/* loaded from: classes.dex */
public class VibrationModule extends ReactContextBaseJavaModule {
    public static final String NAME = "Vibration";

    public VibrationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void cancel() {
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void vibrate(int i) {
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.vibrate(i);
        }
    }

    @ReactMethod
    public void vibrateByPattern(ReadableArray readableArray, int i) {
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            long[] jArr = new long[readableArray.size()];
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                jArr[i2] = readableArray.getInt(i2);
            }
            vibrator.vibrate(jArr, i);
        }
    }
}
