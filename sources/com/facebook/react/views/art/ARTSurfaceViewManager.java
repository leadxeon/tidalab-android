package com.facebook.react.views.art;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import java.util.Objects;
@ReactModule(name = ARTSurfaceViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ARTSurfaceViewManager extends BaseViewManager<ARTSurfaceView, ARTSurfaceViewShadowNode> {
    private static final YogaMeasureFunction MEASURE_FUNCTION = new YogaMeasureFunction() { // from class: com.facebook.react.views.art.ARTSurfaceViewManager.1
        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            throw new IllegalStateException("SurfaceView should have explicit width and height set");
        }
    };
    public static final String REACT_CLASS = "ARTSurfaceView";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<ARTSurfaceViewShadowNode> getShadowNodeClass() {
        return ARTSurfaceViewShadowNode.class;
    }

    public void setBackgroundColor(ARTSurfaceView aRTSurfaceView, int i) {
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ARTSurfaceViewShadowNode createShadowNodeInstance() {
        ARTSurfaceViewShadowNode aRTSurfaceViewShadowNode = new ARTSurfaceViewShadowNode();
        aRTSurfaceViewShadowNode.mYogaNode.setMeasureFunction(MEASURE_FUNCTION);
        return aRTSurfaceViewShadowNode;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ARTSurfaceView createViewInstance(ThemedReactContext themedReactContext) {
        return new ARTSurfaceView(themedReactContext);
    }

    public void updateExtraData(ARTSurfaceView aRTSurfaceView, Object obj) {
        ARTSurfaceViewShadowNode aRTSurfaceViewShadowNode = (ARTSurfaceViewShadowNode) obj;
        Objects.requireNonNull(aRTSurfaceViewShadowNode);
        SurfaceTexture surfaceTexture = aRTSurfaceView.getSurfaceTexture();
        aRTSurfaceView.setSurfaceTextureListener(aRTSurfaceViewShadowNode);
        if (surfaceTexture != null && aRTSurfaceViewShadowNode.mSurface == null) {
            aRTSurfaceViewShadowNode.mSurface = new Surface(surfaceTexture);
            aRTSurfaceViewShadowNode.drawOutput(true);
        }
    }
}
