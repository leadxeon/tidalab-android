package com.facebook.react.devsupport;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = LogBoxModule.NAME)
/* loaded from: classes.dex */
public class LogBoxModule extends ReactContextBaseJavaModule {
    public static final String NAME = "LogBox";
    private final DevSupportManager mDevSupportManager;
    private LogBoxDialog mLogBoxDialog;
    private View mReactRootView;

    public LogBoxModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager) {
        super(reactApplicationContext);
        this.mDevSupportManager = devSupportManager;
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.LogBoxModule.1
            @Override // java.lang.Runnable
            public void run() {
                if (LogBoxModule.this.mReactRootView == null) {
                    LogBoxModule logBoxModule = LogBoxModule.this;
                    logBoxModule.mReactRootView = logBoxModule.mDevSupportManager.createRootView(LogBoxModule.NAME);
                    if (LogBoxModule.this.mReactRootView == null) {
                        FLog.e("ReactNative", "Unable to launch logbox because react was unable to create the root view");
                    }
                }
            }
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void hide() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.LogBoxModule.3
            @Override // java.lang.Runnable
            public void run() {
                if (LogBoxModule.this.mLogBoxDialog != null) {
                    if (LogBoxModule.this.mReactRootView.getParent() != null) {
                        ((ViewGroup) LogBoxModule.this.mReactRootView.getParent()).removeView(LogBoxModule.this.mReactRootView);
                    }
                    LogBoxModule.this.mLogBoxDialog.dismiss();
                    LogBoxModule.this.mLogBoxDialog = null;
                }
            }
        });
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.LogBoxModule.4
            @Override // java.lang.Runnable
            public void run() {
                if (LogBoxModule.this.mReactRootView != null) {
                    LogBoxModule.this.mDevSupportManager.destroyRootView(LogBoxModule.this.mReactRootView);
                    LogBoxModule.this.mReactRootView = null;
                }
            }
        });
    }

    @ReactMethod
    public void show() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.LogBoxModule.2
            @Override // java.lang.Runnable
            public void run() {
                if (LogBoxModule.this.mLogBoxDialog == null) {
                    Activity currentActivity = LogBoxModule.this.getCurrentActivity();
                    if (currentActivity == null || currentActivity.isFinishing()) {
                        FLog.e("ReactNative", "Unable to launch logbox because react activity is not available, here is the error that logbox would've displayed: ");
                        return;
                    }
                    LogBoxModule.this.mLogBoxDialog = new LogBoxDialog(currentActivity, LogBoxModule.this.mReactRootView);
                    LogBoxModule.this.mLogBoxDialog.setCancelable(false);
                    LogBoxModule.this.mLogBoxDialog.show();
                }
            }
        });
    }
}
