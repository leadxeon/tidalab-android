package com.facebook.react.modules.debug;

import android.widget.Toast;
import androidx.recyclerview.R$dimen;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.debug.FpsDebugFrameCallback;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
@ReactModule(name = AnimationsDebugModule.NAME)
/* loaded from: classes.dex */
public class AnimationsDebugModule extends ReactContextBaseJavaModule {
    public static final String NAME = "AnimationsDebugModule";
    private final DeveloperSettings mCatalystSettings;
    private FpsDebugFrameCallback mFrameCallback;

    public AnimationsDebugModule(ReactApplicationContext reactApplicationContext, DeveloperSettings developerSettings) {
        super(reactApplicationContext);
        this.mCatalystSettings = developerSettings;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        FpsDebugFrameCallback fpsDebugFrameCallback = this.mFrameCallback;
        if (fpsDebugFrameCallback != null) {
            fpsDebugFrameCallback.stop();
            this.mFrameCallback = null;
        }
    }

    @ReactMethod
    public void startRecordingFps() {
        DeveloperSettings developerSettings = this.mCatalystSettings;
        if (developerSettings != null && developerSettings.isAnimationFpsDebugEnabled()) {
            if (this.mFrameCallback == null) {
                ReactApplicationContext reactApplicationContext = getReactApplicationContext();
                final FpsDebugFrameCallback fpsDebugFrameCallback = new FpsDebugFrameCallback(reactApplicationContext);
                this.mFrameCallback = fpsDebugFrameCallback;
                Objects.requireNonNull(fpsDebugFrameCallback);
                fpsDebugFrameCallback.mTimeToFps = new TreeMap<>();
                fpsDebugFrameCallback.mIsRecordingFpsInfoAtEachFrame = true;
                fpsDebugFrameCallback.mShouldStop = false;
                reactApplicationContext.getCatalystInstance().addBridgeIdleDebugListener(fpsDebugFrameCallback.mDidJSUpdateUiDuringFrameDetector);
                fpsDebugFrameCallback.mUIManagerModule.setViewHierarchyUpdateDebugListener(fpsDebugFrameCallback.mDidJSUpdateUiDuringFrameDetector);
                UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.debug.FpsDebugFrameCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        FpsDebugFrameCallback fpsDebugFrameCallback2 = fpsDebugFrameCallback;
                        UiThreadUtil.assertOnUiThread();
                        if (ChoreographerCompat.sInstance == null) {
                            ChoreographerCompat.sInstance = new ChoreographerCompat();
                        }
                        fpsDebugFrameCallback2.mChoreographer = ChoreographerCompat.sInstance;
                        fpsDebugFrameCallback.mChoreographer.postFrameCallback(fpsDebugFrameCallback);
                    }
                });
                return;
            }
            throw new JSApplicationCausedNativeException("Already recording FPS!");
        }
    }

    @ReactMethod
    public void stopRecordingFps(double d) {
        FpsDebugFrameCallback fpsDebugFrameCallback = this.mFrameCallback;
        if (fpsDebugFrameCallback != null) {
            fpsDebugFrameCallback.stop();
            FpsDebugFrameCallback fpsDebugFrameCallback2 = this.mFrameCallback;
            R$dimen.assertNotNull(fpsDebugFrameCallback2.mTimeToFps, "FPS was not recorded at each frame!");
            Map.Entry<Long, FpsDebugFrameCallback.FpsInfo> floorEntry = fpsDebugFrameCallback2.mTimeToFps.floorEntry(Long.valueOf((long) d));
            FpsDebugFrameCallback.FpsInfo value = floorEntry == null ? null : floorEntry.getValue();
            if (value == null) {
                Toast.makeText(getReactApplicationContext(), "Unable to get FPS info", 1);
            } else {
                Locale locale = Locale.US;
                String str = String.format(locale, "FPS: %.2f, %d frames (%d expected)", Double.valueOf(value.fps), Integer.valueOf(value.totalFrames), Integer.valueOf(value.totalExpectedFrames)) + "\n" + String.format(locale, "JS FPS: %.2f, %d frames (%d expected)", Double.valueOf(value.jsFps), Integer.valueOf(value.totalJsFrames), Integer.valueOf(value.totalExpectedFrames)) + "\nTotal Time MS: " + String.format(locale, "%d", Integer.valueOf(value.totalTimeMs));
                FLog.d("ReactNative", str);
                Toast.makeText(getReactApplicationContext(), str, 1).show();
            }
            this.mFrameCallback = null;
        }
    }
}
