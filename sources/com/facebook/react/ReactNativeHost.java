package com.facebook.react;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import androidx.recyclerview.R$dimen;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.jscexecutor.JSCExecutorFactory;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.soloader.SoLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class ReactNativeHost {
    public final Application mApplication;
    public ReactInstanceManager mReactInstanceManager;

    public ReactNativeHost(Application application) {
        this.mApplication = application;
    }

    public abstract String getJSMainModuleName();

    public abstract List<ReactPackage> getPackages();

    public ReactInstanceManager getReactInstanceManager() {
        JavaScriptExecutorFactory javaScriptExecutorFactory;
        if (this.mReactInstanceManager == null) {
            ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_START);
            ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_START);
            ArrayList arrayList = new ArrayList();
            Application application = this.mApplication;
            String jSMainModuleName = getJSMainModuleName();
            boolean useDeveloperSupport = getUseDeveloperSupport();
            UIImplementationProvider uIImplementationProvider = new UIImplementationProvider();
            LifecycleState lifecycleState = LifecycleState.BEFORE_CREATE;
            for (ReactPackage reactPackage : getPackages()) {
                arrayList.add(reactPackage);
            }
            R$dimen.assertNotNull("index.android.bundle");
            R$dimen.assertNotNull(application, "Application property has not been set with this builder");
            R$dimen.assertCondition(true, "JS Bundle File or Asset URL has to be provided when dev support is disabled");
            R$dimen.assertCondition(true, "Either MainModulePath or JS Bundle File needs to be provided");
            String packageName = application.getPackageName();
            String str = Build.FINGERPRINT.contains("vbox") ? Build.MODEL : Build.MODEL + " - " + Build.VERSION.RELEASE + " - API " + Build.VERSION.SDK_INT;
            Context applicationContext = application.getApplicationContext();
            try {
                boolean z = SoLoader.SYSTRACE_LIBRARY_LOADING;
                try {
                    SoLoader.init(applicationContext, 0);
                    SoLoader.loadLibrary("jscexecutor");
                    javaScriptExecutorFactory = new JSCExecutorFactory(packageName, str);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (UnsatisfiedLinkError e2) {
                if (!e2.getMessage().contains("__cxa_bad_typeid")) {
                    try {
                        javaScriptExecutorFactory = new HermesExecutorFactory();
                    } catch (UnsatisfiedLinkError e3) {
                        e3.printStackTrace();
                        throw e2;
                    }
                } else {
                    throw e2;
                }
            }
            JSBundleLoader createAssetLoader = JSBundleLoader.createAssetLoader(application, "assets://index.android.bundle", false);
            R$dimen.assertNotNull(lifecycleState, "Initial lifecycle state was not set");
            ReactInstanceManager reactInstanceManager = new ReactInstanceManager(application, null, null, javaScriptExecutorFactory, createAssetLoader, jSMainModuleName, arrayList, useDeveloperSupport, null, lifecycleState, uIImplementationProvider, null, null, false, null, 1, -1, null, null);
            ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_END);
            this.mReactInstanceManager = reactInstanceManager;
            ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_END);
        }
        return this.mReactInstanceManager;
    }

    public abstract boolean getUseDeveloperSupport();

    public boolean hasInstance() {
        return this.mReactInstanceManager != null;
    }
}
