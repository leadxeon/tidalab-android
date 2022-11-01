package com.horcrux.svg;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.horcrux.svg.RenderableViewManager;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class SvgPackage implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new SvgViewModule(reactApplicationContext), new RNSVGRenderableManager(reactApplicationContext));
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new RenderableViewManager.GroupViewManager(), new RenderableViewManager.PathViewManager(), new RenderableViewManager.CircleViewManager(), new RenderableViewManager.EllipseViewManager(), new RenderableViewManager.LineViewManager(), new RenderableViewManager.RectViewManager(), new RenderableViewManager.TextViewManager(), new RenderableViewManager.TSpanViewManager(), new RenderableViewManager.TextPathViewManager(), new RenderableViewManager.ImageViewManager(), new RenderableViewManager.ClipPathViewManager(), new RenderableViewManager.DefsViewManager(), new RenderableViewManager.UseViewManager(), new RenderableViewManager.SymbolManager(), new RenderableViewManager.LinearGradientManager(), new RenderableViewManager.RadialGradientManager(), new RenderableViewManager.PatternManager(), new RenderableViewManager.MaskManager(), new RenderableViewManager.ForeignObjectManager(), new RenderableViewManager.MarkerManager(), new SvgViewManager());
    }
}
