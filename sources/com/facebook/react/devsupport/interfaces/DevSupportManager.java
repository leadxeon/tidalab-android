package com.facebook.react.devsupport.interfaces;

import android.view.View;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
/* loaded from: classes.dex */
public interface DevSupportManager extends NativeModuleCallExceptionHandler {
    void addCustomDevOption(String str, DevOptionHandler devOptionHandler);

    View createRootView(String str);

    void destroyRootView(View view);

    DeveloperSettings getDevSettings();

    boolean getDevSupportEnabled();

    void handleReloadJS();

    void hideRedboxDialog();

    void isPackagerRunning(PackagerStatusCallback packagerStatusCallback);

    void onNewReactContextCreated(ReactContext reactContext);

    void onReactInstanceDestroyed(ReactContext reactContext);

    void setDevSupportEnabled(boolean z);

    void setFpsDebugEnabled(boolean z);

    void setHotModuleReplacementEnabled(boolean z);

    void setRemoteJSDebugEnabled(boolean z);

    void showDevOptionsDialog();

    void showNewJSError(String str, ReadableArray readableArray, int i);

    void startInspector();

    void toggleElementInspector();

    void updateJSError(String str, ReadableArray readableArray, int i);
}
