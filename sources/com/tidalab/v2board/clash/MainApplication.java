package com.tidalab.v2board.clash;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.horcrux.svg.PathParser;
import com.horcrux.svg.SvgPackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import com.tidalab.v2board.clash.common.Global;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.remote.Remote;
import com.tidalab.v2board.clash.remote.Remote$launch$1;
import com.tidalab.v2board.clash.remote.Remote$launch$2;
import com.tidalab.v2board.clash.util.ApplicationObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import org.reactnative.maskedview.RNCMaskedViewPackage;
/* compiled from: MainApplication.kt */
/* loaded from: classes.dex */
public final class MainApplication extends Application implements ReactApplication {
    public final ReactNativeHost mReactNativeHost = new ReactNativeHost() { // from class: com.tidalab.v2board.clash.MainApplication$mReactNativeHost$1
        {
            super(MainApplication.this);
        }

        @Override // com.facebook.react.ReactNativeHost
        public String getJSMainModuleName() {
            return "index";
        }

        @Override // com.facebook.react.ReactNativeHost
        public List<ReactPackage> getPackages() {
            ArrayList arrayList = new ArrayList(Arrays.asList(new MainReactPackage(), new AsyncStoragePackage(), new RNCMaskedViewPackage(), new RNDeviceInfo(), new RNGestureHandlerPackage(), new SafeAreaContextPackage(), new RNScreensPackage(), new SvgPackage(), new RNCWebViewPackage()));
            arrayList.add(new CustomPackage());
            return arrayList;
        }

        @Override // com.facebook.react.ReactNativeHost
        public boolean getUseDeveloperSupport() {
            return false;
        }
    };

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        Objects.requireNonNull(Global.INSTANCE);
        Global.application_ = this;
    }

    public final void finalize() {
        InputKt.cancel$default(Global.INSTANCE, null, 1);
    }

    @Override // com.facebook.react.ReactApplication
    public ReactNativeHost getReactNativeHost() {
        return this.mReactNativeHost;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        String currentProcessName = PathParser.getCurrentProcessName(this);
        Log.d("ClashForAndroid", "Process " + currentProcessName + " started", null);
        if (Intrinsics.areEqual(currentProcessName, getPackageName())) {
            Remote remote = Remote.INSTANCE;
            ApplicationObserver applicationObserver = ApplicationObserver.INSTANCE;
            Global global = Global.INSTANCE;
            global.getApplication().registerActivityLifecycleCallbacks(ApplicationObserver.activityObserver);
            ApplicationObserver.visibleChanged = Remote$launch$1.INSTANCE;
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            InputKt.launch$default(global, Dispatchers.IO, null, new Remote$launch$2(null), 2, null);
            return;
        }
        Intents intents = Intents.INSTANCE;
        InputKt.sendBroadcastSelf(this, new Intent(Intents.ACTION_SERVICE_RECREATED));
    }
}
