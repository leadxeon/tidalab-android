package com.swmansion.rnscreens;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptyList;
/* compiled from: RNScreensPackage.kt */
/* loaded from: classes.dex */
public final class RNScreensPackage implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return EmptyList.INSTANCE;
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager<?, ?>> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return ArraysKt___ArraysKt.listOf(new ScreenContainerViewManager(), new ScreenViewManager(), new ScreenStackViewManager(), new ScreenStackHeaderConfigViewManager(), new ScreenStackHeaderSubviewManager());
    }
}
