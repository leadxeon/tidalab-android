package com.tidalab.v2board.clash;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.horcrux.svg.PathParser;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.remote.Remote;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: NativeModule.kt */
/* loaded from: classes.dex */
public final class NativeModule extends ReactContextBaseJavaModule {
    public NativeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public final void commitProfile(String str, Callback callback, Callback callback2) {
        try {
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            InputKt.runBlocking(MainDispatcherLoader.dispatcher, new NativeModule$commitProfile$1(str, null));
            callback2.invoke(Boolean.TRUE);
        } catch (Exception e) {
            callback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public final void connect() {
        Intent startClashService = InputKt.startClashService(getReactApplicationContext());
        try {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                currentActivity.startActivityForResult(startClashService, RNCWebViewManager.COMMAND_CLEAR_FORM_DATA);
            }
            InputKt.startClashService(getReactApplicationContext());
        } catch (Exception unused) {
        }
    }

    @ReactMethod
    public final void createProfile(String str, String str2, Callback callback) {
        InputKt.runBlocking$default(null, new NativeModule$createProfile$1(callback, str, str2, null), 1, null);
    }

    @ReactMethod
    public final void deleteProfile(String str) {
        InputKt.runBlocking$default(null, new NativeModule$deleteProfile$1(str, null), 1, null);
    }

    @ReactMethod
    public final void disconnect() {
        InputKt.stopClashService(getReactApplicationContext());
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "NativeModule";
    }

    @ReactMethod
    public final void goDash() {
        Activity currentActivity = getReactApplicationContext().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(DashActivity.class)));
        }
    }

    @ReactMethod
    public final void goProxy() {
        Activity currentActivity = getReactApplicationContext().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(ProxyActivity.class)));
        }
    }

    @ReactMethod
    public final void isConnect(Callback callback) {
        Remote remote = Remote.INSTANCE;
        callback.invoke(String.valueOf(Remote.broadcasts.clashRunning));
    }

    @ReactMethod
    public final void queryProfile(String str, Callback callback, Callback callback2) {
        try {
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            InputKt.runBlocking(MainDispatcherLoader.dispatcher, new NativeModule$queryProfile$1(callback2, str, null));
        } catch (Exception e) {
            callback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public final void querySelectProxy(Callback callback) {
        InputKt.runBlocking$default(null, new NativeModule$querySelectProxy$1(callback, null), 1, null);
    }

    @ReactMethod
    public final void queryTunnelStateMode(Callback callback) {
        InputKt.runBlocking$default(null, new NativeModule$queryTunnelStateMode$1(callback, null), 1, null);
    }

    @ReactMethod
    public final void selectServer(String str, String str2, Callback callback) {
        InputKt.runBlocking$default(null, new NativeModule$selectServer$1(callback, str, str2, null), 1, null);
    }

    @ReactMethod
    public final void selectTunnelStateMode(String str, String str2, Callback callback) {
        InputKt.runBlocking$default(null, new NativeModule$selectTunnelStateMode$1(str, str2, callback, null), 1, null);
    }

    @ReactMethod
    public final void show(String str, int i) {
        getReactApplicationContext();
        Toast.makeText(getReactApplicationContext(), str, i).show();
    }

    @ReactMethod
    public final void updateProfile(String str) {
        InputKt.runBlocking$default(null, new NativeModule$updateProfile$1(str, null), 1, null);
    }
}
