package com.reactnativecommunity.webview;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Collections;
import java.util.List;
/* compiled from: RNCWebViewPackage.kt */
/* loaded from: classes.dex */
public final class RNCWebViewPackage implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public List<RNCWebViewModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Collections.singletonList(new RNCWebViewModule(reactApplicationContext));
    }

    @Override // com.facebook.react.ReactPackage
    public List<RNCWebViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.singletonList(new RNCWebViewManager());
    }
}
