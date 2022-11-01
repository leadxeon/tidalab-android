package com.horcrux.svg;

import android.graphics.Matrix;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.MatrixMathHelper$MatrixDecompositionContext;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.TransformHelper;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.lang.reflect.Array;
import java.util.Locale;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public class RenderableViewManager extends ViewGroupManager<VirtualView> {
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = 5.0f;
    private static final double EPSILON = 1.0E-5d;
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private final String mClassName;
    private final SVGClass svgClass;
    private static final MatrixDecompositionContext sMatrixDecompositionContext = new MatrixDecompositionContext();
    private static final double[] sTransformDecompositionArray = new double[16];
    private static final SparseArray<RenderableView> mTagToRenderableView = new SparseArray<>();
    private static final SparseArray<Runnable> mTagToRunnable = new SparseArray<>();

    /* loaded from: classes.dex */
    public static class CircleViewManager extends RenderableViewManager {
        public CircleViewManager() {
            super(SVGClass.RNSVGCircle);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "cx")
        public void setCx(CircleView circleView, Dynamic dynamic) {
            circleView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(CircleView circleView, Dynamic dynamic) {
            circleView.setCy(dynamic);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "r")
        public void setR(CircleView circleView, Dynamic dynamic) {
            circleView.setR(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public static class ClipPathViewManager extends GroupViewManager {
        public ClipPathViewManager() {
            super(SVGClass.RNSVGClipPath);
        }
    }

    /* loaded from: classes.dex */
    public static class DefsViewManager extends RenderableViewManager {
        public DefsViewManager() {
            super(SVGClass.RNSVGDefs);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }
    }

    /* loaded from: classes.dex */
    public static class EllipseViewManager extends RenderableViewManager {
        public EllipseViewManager() {
            super(SVGClass.RNSVGEllipse);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "cx")
        public void setCx(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setCy(dynamic);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "rx")
        public void setRx(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setRy(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public static class ForeignObjectManager extends GroupViewManager {
        public ForeignObjectManager() {
            super(SVGClass.RNSVGForeignObject);
        }

        @ReactProp(name = "height")
        public void setHeight(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setHeight(dynamic);
        }

        @ReactProp(name = "width")
        public void setWidth(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setY(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public static class GroupViewManager extends RenderableViewManager {
        public GroupViewManager() {
            super(SVGClass.RNSVGGroup);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "font")
        public void setFont(GroupView groupView, ReadableMap readableMap) {
            groupView.setFont(readableMap);
        }

        @ReactProp(name = "fontSize")
        public void setFontSize(GroupView groupView, Dynamic dynamic) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            int ordinal = dynamic.getType().ordinal();
            if (ordinal == 2) {
                javaOnlyMap.putDouble("fontSize", dynamic.asDouble());
            } else if (ordinal == 3) {
                javaOnlyMap.putString("fontSize", dynamic.asString());
            } else {
                return;
            }
            groupView.setFont(javaOnlyMap);
        }

        @ReactProp(name = "fontWeight")
        public void setFontWeight(GroupView groupView, Dynamic dynamic) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            int ordinal = dynamic.getType().ordinal();
            if (ordinal == 2) {
                javaOnlyMap.putDouble("fontWeight", dynamic.asDouble());
            } else if (ordinal == 3) {
                javaOnlyMap.putString("fontWeight", dynamic.asString());
            } else {
                return;
            }
            groupView.setFont(javaOnlyMap);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }

        public GroupViewManager(SVGClass sVGClass) {
            super(sVGClass);
        }
    }

    /* loaded from: classes.dex */
    public static class ImageViewManager extends RenderableViewManager {
        public ImageViewManager() {
            super(SVGClass.RNSVGImage);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "align")
        public void setAlign(ImageView imageView, String str) {
            imageView.setAlign(str);
        }

        @ReactProp(name = "height")
        public void setHeight(ImageView imageView, Dynamic dynamic) {
            imageView.setHeight(dynamic);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(ImageView imageView, int i) {
            imageView.setMeetOrSlice(i);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "src")
        public void setSrc(ImageView imageView, ReadableMap readableMap) {
            imageView.setSrc(readableMap);
        }

        @ReactProp(name = "width")
        public void setWidth(ImageView imageView, Dynamic dynamic) {
            imageView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(ImageView imageView, Dynamic dynamic) {
            imageView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(ImageView imageView, Dynamic dynamic) {
            imageView.setY(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public static class LineViewManager extends RenderableViewManager {
        public LineViewManager() {
            super(SVGClass.RNSVGLine);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "x1")
        public void setX1(LineView lineView, Dynamic dynamic) {
            lineView.setX1(dynamic);
        }

        @ReactProp(name = "x2")
        public void setX2(LineView lineView, Dynamic dynamic) {
            lineView.setX2(dynamic);
        }

        @ReactProp(name = "y1")
        public void setY1(LineView lineView, Dynamic dynamic) {
            lineView.setY1(dynamic);
        }

        @ReactProp(name = "y2")
        public void setY2(LineView lineView, Dynamic dynamic) {
            lineView.setY2(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public static class LinearGradientManager extends RenderableViewManager {
        public LinearGradientManager() {
            super(SVGClass.RNSVGLinearGradient);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "gradient")
        public void setGradient(LinearGradientView linearGradientView, ReadableArray readableArray) {
            linearGradientView.setGradient(readableArray);
        }

        @ReactProp(name = "gradientTransform")
        public void setGradientTransform(LinearGradientView linearGradientView, ReadableArray readableArray) {
            linearGradientView.setGradientTransform(readableArray);
        }

        @ReactProp(name = "gradientUnits")
        public void setGradientUnits(LinearGradientView linearGradientView, int i) {
            linearGradientView.setGradientUnits(i);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "x1")
        public void setX1(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setX1(dynamic);
        }

        @ReactProp(name = "x2")
        public void setX2(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setX2(dynamic);
        }

        @ReactProp(name = "y1")
        public void setY1(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setY1(dynamic);
        }

        @ReactProp(name = "y2")
        public void setY2(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setY2(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public static class MarkerManager extends GroupViewManager {
        public MarkerManager() {
            super(SVGClass.RNSVGMarker);
        }

        @ReactProp(name = "align")
        public void setAlign(MarkerView markerView, String str) {
            markerView.setAlign(str);
        }

        @ReactProp(name = "markerHeight")
        public void setMarkerHeight(MarkerView markerView, Dynamic dynamic) {
            markerView.setMarkerHeight(dynamic);
        }

        @ReactProp(name = "markerUnits")
        public void setMarkerUnits(MarkerView markerView, String str) {
            markerView.setMarkerUnits(str);
        }

        @ReactProp(name = "markerWidth")
        public void setMarkerWidth(MarkerView markerView, Dynamic dynamic) {
            markerView.setMarkerWidth(dynamic);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(MarkerView markerView, int i) {
            markerView.setMeetOrSlice(i);
        }

        @ReactProp(name = "minX")
        public void setMinX(MarkerView markerView, float f) {
            markerView.setMinX(f);
        }

        @ReactProp(name = "minY")
        public void setMinY(MarkerView markerView, float f) {
            markerView.setMinY(f);
        }

        @ReactProp(name = "orient")
        public void setOrient(MarkerView markerView, String str) {
            markerView.setOrient(str);
        }

        @ReactProp(name = "refX")
        public void setRefX(MarkerView markerView, Dynamic dynamic) {
            markerView.setRefX(dynamic);
        }

        @ReactProp(name = "refY")
        public void setRefY(MarkerView markerView, Dynamic dynamic) {
            markerView.setRefY(dynamic);
        }

        @ReactProp(name = "vbHeight")
        public void setVbHeight(MarkerView markerView, float f) {
            markerView.setVbHeight(f);
        }

        @ReactProp(name = "vbWidth")
        public void setVbWidth(MarkerView markerView, float f) {
            markerView.setVbWidth(f);
        }
    }

    /* loaded from: classes.dex */
    public static class MaskManager extends GroupViewManager {
        public MaskManager() {
            super(SVGClass.RNSVGMask);
        }

        @ReactProp(name = "height")
        public void setHeight(MaskView maskView, Dynamic dynamic) {
            maskView.setHeight(dynamic);
        }

        @ReactProp(name = "maskContentUnits")
        public void setMaskContentUnits(MaskView maskView, int i) {
            maskView.setMaskContentUnits(i);
        }

        @ReactProp(name = "maskTransform")
        public void setMaskTransform(MaskView maskView, ReadableArray readableArray) {
            maskView.setMaskTransform(readableArray);
        }

        @ReactProp(name = "maskUnits")
        public void setMaskUnits(MaskView maskView, int i) {
            maskView.setMaskUnits(i);
        }

        @ReactProp(name = "width")
        public void setWidth(MaskView maskView, Dynamic dynamic) {
            maskView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(MaskView maskView, Dynamic dynamic) {
            maskView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(MaskView maskView, Dynamic dynamic) {
            maskView.setY(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public static class MatrixDecompositionContext extends MatrixMathHelper$MatrixDecompositionContext {
        public final double[] perspective = new double[4];
        public final double[] scale = new double[3];
        public final double[] skew = new double[3];
        public final double[] translation = new double[3];
        public final double[] rotationDegrees = new double[3];
    }

    /* loaded from: classes.dex */
    public static class PathViewManager extends RenderableViewManager {
        public PathViewManager() {
            super(SVGClass.RNSVGPath);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "d")
        public void setD(PathView pathView, String str) {
            pathView.setD(str);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }
    }

    /* loaded from: classes.dex */
    public static class PatternManager extends GroupViewManager {
        public PatternManager() {
            super(SVGClass.RNSVGPattern);
        }

        @ReactProp(name = "align")
        public void setAlign(PatternView patternView, String str) {
            patternView.setAlign(str);
        }

        @ReactProp(name = "height")
        public void setHeight(PatternView patternView, Dynamic dynamic) {
            patternView.setHeight(dynamic);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(PatternView patternView, int i) {
            patternView.setMeetOrSlice(i);
        }

        @ReactProp(name = "minX")
        public void setMinX(PatternView patternView, float f) {
            patternView.setMinX(f);
        }

        @ReactProp(name = "minY")
        public void setMinY(PatternView patternView, float f) {
            patternView.setMinY(f);
        }

        @ReactProp(name = "patternContentUnits")
        public void setPatternContentUnits(PatternView patternView, int i) {
            patternView.setPatternContentUnits(i);
        }

        @ReactProp(name = "patternTransform")
        public void setPatternTransform(PatternView patternView, ReadableArray readableArray) {
            patternView.setPatternTransform(readableArray);
        }

        @ReactProp(name = "patternUnits")
        public void setPatternUnits(PatternView patternView, int i) {
            patternView.setPatternUnits(i);
        }

        @ReactProp(name = "vbHeight")
        public void setVbHeight(PatternView patternView, float f) {
            patternView.setVbHeight(f);
        }

        @ReactProp(name = "vbWidth")
        public void setVbWidth(PatternView patternView, float f) {
            patternView.setVbWidth(f);
        }

        @ReactProp(name = "width")
        public void setWidth(PatternView patternView, Dynamic dynamic) {
            patternView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(PatternView patternView, Dynamic dynamic) {
            patternView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(PatternView patternView, Dynamic dynamic) {
            patternView.setY(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public static class RadialGradientManager extends RenderableViewManager {
        public RadialGradientManager() {
            super(SVGClass.RNSVGRadialGradient);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "cx")
        public void setCx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setCy(dynamic);
        }

        @ReactProp(name = "fx")
        public void setFx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setFx(dynamic);
        }

        @ReactProp(name = "fy")
        public void setFy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setFy(dynamic);
        }

        @ReactProp(name = "gradient")
        public void setGradient(RadialGradientView radialGradientView, ReadableArray readableArray) {
            radialGradientView.setGradient(readableArray);
        }

        @ReactProp(name = "gradientTransform")
        public void setGradientTransform(RadialGradientView radialGradientView, ReadableArray readableArray) {
            radialGradientView.setGradientTransform(readableArray);
        }

        @ReactProp(name = "gradientUnits")
        public void setGradientUnits(RadialGradientView radialGradientView, int i) {
            radialGradientView.setGradientUnits(i);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "rx")
        public void setRx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setRy(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public static class RectViewManager extends RenderableViewManager {
        public RectViewManager() {
            super(SVGClass.RNSVGRect);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "height")
        public void setHeight(RectView rectView, Dynamic dynamic) {
            rectView.setHeight(dynamic);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "rx")
        public void setRx(RectView rectView, Dynamic dynamic) {
            rectView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(RectView rectView, Dynamic dynamic) {
            rectView.setRy(dynamic);
        }

        @ReactProp(name = "width")
        public void setWidth(RectView rectView, Dynamic dynamic) {
            rectView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(RectView rectView, Dynamic dynamic) {
            rectView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(RectView rectView, Dynamic dynamic) {
            rectView.setY(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public class RenderableShadowNode extends LayoutShadowNode {
        public RenderableShadowNode(RenderableViewManager renderableViewManager) {
        }

        @ReactPropGroup(names = {"alignSelf", "alignItems", "collapsable", "flex", "flexBasis", "flexDirection", "flexGrow", "flexShrink", "flexWrap", "justifyContent", "overflow", "alignContent", "display", "position", "right", "top", "bottom", "left", "start", "end", "width", "height", "minWidth", "maxWidth", "minHeight", "maxHeight", "margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom", "marginStart", "marginEnd", "padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "paddingStart", "paddingEnd", "borderWidth", "borderStartWidth", "borderEndWidth", "borderTopWidth", "borderBottomWidth", "borderLeftWidth", "borderRightWidth"})
        public void ignoreLayoutProps(int i, Dynamic dynamic) {
        }
    }

    /* loaded from: classes.dex */
    public enum SVGClass {
        RNSVGGroup,
        RNSVGPath,
        RNSVGText,
        RNSVGTSpan,
        RNSVGTextPath,
        RNSVGImage,
        RNSVGCircle,
        RNSVGEllipse,
        RNSVGLine,
        RNSVGRect,
        RNSVGClipPath,
        RNSVGDefs,
        RNSVGUse,
        RNSVGSymbol,
        RNSVGLinearGradient,
        RNSVGRadialGradient,
        RNSVGPattern,
        RNSVGMask,
        RNSVGMarker,
        RNSVGForeignObject
    }

    /* loaded from: classes.dex */
    public static class SymbolManager extends GroupViewManager {
        public SymbolManager() {
            super(SVGClass.RNSVGSymbol);
        }

        @ReactProp(name = "align")
        public void setAlign(SymbolView symbolView, String str) {
            symbolView.setAlign(str);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(SymbolView symbolView, int i) {
            symbolView.setMeetOrSlice(i);
        }

        @ReactProp(name = "minX")
        public void setMinX(SymbolView symbolView, float f) {
            symbolView.setMinX(f);
        }

        @ReactProp(name = "minY")
        public void setMinY(SymbolView symbolView, float f) {
            symbolView.setMinY(f);
        }

        @ReactProp(name = "vbHeight")
        public void setVbHeight(SymbolView symbolView, float f) {
            symbolView.setVbHeight(f);
        }

        @ReactProp(name = "vbWidth")
        public void setVbWidth(SymbolView symbolView, float f) {
            symbolView.setVbWidth(f);
        }
    }

    /* loaded from: classes.dex */
    public static class TSpanViewManager extends TextViewManager {
        public TSpanViewManager() {
            super(SVGClass.RNSVGTSpan);
        }

        @ReactProp(name = "content")
        public void setContent(TSpanView tSpanView, String str) {
            tSpanView.setContent(str);
        }
    }

    /* loaded from: classes.dex */
    public static class TextPathViewManager extends TextViewManager {
        public TextPathViewManager() {
            super(SVGClass.RNSVGTextPath);
        }

        @ReactProp(name = "href")
        public void setHref(TextPathView textPathView, String str) {
            textPathView.setHref(str);
        }

        @ReactProp(name = "method")
        public void setMethod(TextPathView textPathView, String str) {
            textPathView.setMethod(str);
        }

        @ReactProp(name = "midLine")
        public void setSharp(TextPathView textPathView, String str) {
            textPathView.setSharp(str);
        }

        @ReactProp(name = "side")
        public void setSide(TextPathView textPathView, String str) {
            textPathView.setSide(str);
        }

        @ReactProp(name = "spacing")
        public void setSpacing(TextPathView textPathView, String str) {
            textPathView.setSpacing(str);
        }

        @ReactProp(name = "startOffset")
        public void setStartOffset(TextPathView textPathView, Dynamic dynamic) {
            textPathView.setStartOffset(dynamic);
        }
    }

    /* loaded from: classes.dex */
    public static class TextViewManager extends GroupViewManager {
        public TextViewManager() {
            super(SVGClass.RNSVGText);
        }

        @ReactProp(name = "baselineShift")
        public void setBaselineShift(TextView textView, Dynamic dynamic) {
            textView.setBaselineShift(dynamic);
        }

        @ReactProp(name = "dx")
        public void setDeltaX(TextView textView, Dynamic dynamic) {
            textView.setDeltaX(dynamic);
        }

        @ReactProp(name = "dy")
        public void setDeltaY(TextView textView, Dynamic dynamic) {
            textView.setDeltaY(dynamic);
        }

        @ReactProp(name = "font")
        public void setFont(TextView textView, ReadableMap readableMap) {
            textView.setFont(readableMap);
        }

        @ReactProp(name = "inlineSize")
        public void setInlineSize(TextView textView, Dynamic dynamic) {
            textView.setInlineSize(dynamic);
        }

        @ReactProp(name = "lengthAdjust")
        public void setLengthAdjust(TextView textView, String str) {
            textView.setLengthAdjust(str);
        }

        @ReactProp(name = "alignmentBaseline")
        public void setMethod(TextView textView, String str) {
            textView.setMethod(str);
        }

        @ReactProp(name = "rotate")
        public void setRotate(TextView textView, Dynamic dynamic) {
            textView.setRotate(dynamic);
        }

        @ReactProp(name = "textLength")
        public void setTextLength(TextView textView, Dynamic dynamic) {
            textView.setTextLength(dynamic);
        }

        @ReactProp(name = "verticalAlign")
        public void setVerticalAlign(TextView textView, String str) {
            textView.setVerticalAlign(str);
        }

        @ReactProp(name = "x")
        public void setX(TextView textView, Dynamic dynamic) {
            textView.setPositionX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(TextView textView, Dynamic dynamic) {
            textView.setPositionY(dynamic);
        }

        public TextViewManager(SVGClass sVGClass) {
            super(sVGClass);
        }
    }

    /* loaded from: classes.dex */
    public static class UseViewManager extends RenderableViewManager {
        public UseViewManager() {
            super(SVGClass.RNSVGUse);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void addEventEmitters(ThemedReactContext themedReactContext, View view) {
            RenderableViewManager.super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ ReactShadowNode createShadowNodeInstance() {
            return RenderableViewManager.super.createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ View createViewInstance(ThemedReactContext themedReactContext) {
            return RenderableViewManager.super.createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onAfterUpdateTransaction(View view) {
            RenderableViewManager.super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
            RenderableViewManager.super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "height")
        public void setHeight(UseView useView, Dynamic dynamic) {
            useView.setHeight(dynamic);
        }

        @ReactProp(name = "href")
        public void setHref(UseView useView, String str) {
            useView.setHref(str);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(View view, float f) {
            RenderableViewManager.super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "width")
        public void setWidth(UseView useView, Dynamic dynamic) {
            useView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(UseView useView, Dynamic dynamic) {
            useView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(UseView useView, Dynamic dynamic) {
            useView.setY(dynamic);
        }
    }

    private static void decomposeMatrix() {
        MatrixDecompositionContext matrixDecompositionContext = sMatrixDecompositionContext;
        double[] dArr = matrixDecompositionContext.perspective;
        double[] dArr2 = matrixDecompositionContext.scale;
        double[] dArr3 = matrixDecompositionContext.skew;
        double[] dArr4 = matrixDecompositionContext.translation;
        double[] dArr5 = matrixDecompositionContext.rotationDegrees;
        if (!isZero(sTransformDecompositionArray[15])) {
            double[][] dArr6 = (double[][]) Array.newInstance(double.class, 4, 4);
            double[] dArr7 = new double[16];
            for (int i = 0; i < 4; i++) {
                for (int i2 = 0; i2 < 4; i2++) {
                    double[] dArr8 = sTransformDecompositionArray;
                    int i3 = (i * 4) + i2;
                    double d = dArr8[i3] / dArr8[15];
                    dArr6[i][i2] = d;
                    if (i2 == 3) {
                        d = 0.0d;
                    }
                    dArr7[i3] = d;
                }
            }
            dArr7[15] = 1.0d;
            if (!isZero(R$style.determinant(dArr7))) {
                if (!isZero(dArr6[0][3]) || !isZero(dArr6[1][3]) || !isZero(dArr6[2][3])) {
                    R$style.multiplyVectorByMatrix(new double[]{dArr6[0][3], dArr6[1][3], dArr6[2][3], dArr6[3][3]}, R$style.transpose(R$style.inverse(dArr7)), dArr);
                } else {
                    dArr[2] = 0.0d;
                    dArr[1] = 0.0d;
                    dArr[0] = 0.0d;
                    dArr[3] = 1.0d;
                }
                System.arraycopy(dArr6[3], 0, dArr4, 0, 3);
                double[][] dArr9 = (double[][]) Array.newInstance(double.class, 3, 3);
                for (int i4 = 0; i4 < 3; i4++) {
                    dArr9[i4][0] = dArr6[i4][0];
                    dArr9[i4][1] = dArr6[i4][1];
                    dArr9[i4][2] = dArr6[i4][2];
                }
                dArr2[0] = R$style.v3Length(dArr9[0]);
                dArr9[0] = R$style.v3Normalize(dArr9[0], dArr2[0]);
                dArr3[0] = R$style.v3Dot(dArr9[0], dArr9[1]);
                dArr9[1] = R$style.v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr3[0]);
                dArr3[0] = R$style.v3Dot(dArr9[0], dArr9[1]);
                dArr9[1] = R$style.v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr3[0]);
                dArr2[1] = R$style.v3Length(dArr9[1]);
                dArr9[1] = R$style.v3Normalize(dArr9[1], dArr2[1]);
                dArr3[0] = dArr3[0] / dArr2[1];
                dArr3[1] = R$style.v3Dot(dArr9[0], dArr9[2]);
                dArr9[2] = R$style.v3Combine(dArr9[2], dArr9[0], 1.0d, -dArr3[1]);
                dArr3[2] = R$style.v3Dot(dArr9[1], dArr9[2]);
                dArr9[2] = R$style.v3Combine(dArr9[2], dArr9[1], 1.0d, -dArr3[2]);
                dArr2[2] = R$style.v3Length(dArr9[2]);
                dArr9[2] = R$style.v3Normalize(dArr9[2], dArr2[2]);
                dArr3[1] = dArr3[1] / dArr2[2];
                dArr3[2] = dArr3[2] / dArr2[2];
                if (R$style.v3Dot(dArr9[0], R$style.v3Cross(dArr9[1], dArr9[2])) < 0.0d) {
                    for (int i5 = 0; i5 < 3; i5++) {
                        dArr2[i5] = dArr2[i5] * (-1.0d);
                        double[] dArr10 = dArr9[i5];
                        dArr10[0] = dArr10[0] * (-1.0d);
                        double[] dArr11 = dArr9[i5];
                        dArr11[1] = dArr11[1] * (-1.0d);
                        double[] dArr12 = dArr9[i5];
                        dArr12[2] = dArr12[2] * (-1.0d);
                    }
                }
                dArr5[0] = R$style.roundTo3Places((-Math.atan2(dArr9[2][1], dArr9[2][2])) * 57.29577951308232d);
                dArr5[1] = R$style.roundTo3Places((-Math.atan2(-dArr9[2][0], Math.sqrt((dArr9[2][2] * dArr9[2][2]) + (dArr9[2][1] * dArr9[2][1])))) * 57.29577951308232d);
                dArr5[2] = R$style.roundTo3Places((-Math.atan2(dArr9[1][0], dArr9[0][0])) * 57.29577951308232d);
            }
        }
    }

    public static RenderableView getRenderableViewByTag(int i) {
        return mTagToRenderableView.get(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateSvgView(VirtualView virtualView) {
        SvgView svgView = virtualView.getSvgView();
        if (svgView != null) {
            svgView.invalidate();
        }
        if (virtualView instanceof TextView) {
            ((TextView) virtualView).getTextContainer().clearChildCache();
        }
    }

    private static boolean isZero(double d) {
        return !Double.isNaN(d) && Math.abs(d) < EPSILON;
    }

    private static void resetTransformProperty(View view) {
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(0.0f);
    }

    public static void runWhenViewIsAvailable(int i, Runnable runnable) {
        mTagToRunnable.put(i, runnable);
    }

    public static void setRenderableView(int i, RenderableView renderableView) {
        mTagToRenderableView.put(i, renderableView);
        SparseArray<Runnable> sparseArray = mTagToRunnable;
        Runnable runnable = sparseArray.get(i);
        if (runnable != null) {
            runnable.run();
            sparseArray.delete(i);
        }
    }

    private static void setTransformProperty(View view, ReadableArray readableArray) {
        TransformHelper.processTransform(readableArray, sTransformDecompositionArray);
        decomposeMatrix();
        MatrixDecompositionContext matrixDecompositionContext = sMatrixDecompositionContext;
        view.setTranslationX(PixelUtil.toPixelFromDIP((float) matrixDecompositionContext.translation[0]));
        view.setTranslationY(PixelUtil.toPixelFromDIP((float) matrixDecompositionContext.translation[1]));
        view.setRotation((float) matrixDecompositionContext.rotationDegrees[2]);
        view.setRotationX((float) matrixDecompositionContext.rotationDegrees[0]);
        view.setRotationY((float) matrixDecompositionContext.rotationDegrees[1]);
        view.setScaleX((float) matrixDecompositionContext.scale[0]);
        view.setScaleY((float) matrixDecompositionContext.scale[1]);
        double[] dArr = matrixDecompositionContext.perspective;
        if (dArr.length > 2) {
            float f = (float) dArr[2];
            if (f == 0.0f) {
                f = 7.8125E-4f;
            }
            float f2 = (-1.0f) / f;
            float f3 = R$style.sScreenDisplayMetrics.density;
            view.setCameraDistance(f3 * f3 * f2 * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return this.mClassName;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<RenderableShadowNode> getShadowNodeClass() {
        return RenderableShadowNode.class;
    }

    @ReactProp(name = "clipPath")
    public void setClipPath(VirtualView virtualView, String str) {
        virtualView.setClipPath(str);
    }

    @ReactProp(name = "clipRule")
    public void setClipRule(VirtualView virtualView, int i) {
        virtualView.setClipRule(i);
    }

    @ReactProp(name = "display")
    public void setDisplay(VirtualView virtualView, String str) {
        virtualView.setDisplay(str);
    }

    @ReactProp(name = "fill")
    public void setFill(RenderableView renderableView, Dynamic dynamic) {
        renderableView.setFill(dynamic);
    }

    @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
    public void setFillOpacity(RenderableView renderableView, float f) {
        renderableView.setFillOpacity(f);
    }

    @ReactProp(defaultInt = 1, name = "fillRule")
    public void setFillRule(RenderableView renderableView, int i) {
        renderableView.setFillRule(i);
    }

    @ReactProp(name = "markerEnd")
    public void setMarkerEnd(VirtualView virtualView, String str) {
        virtualView.setMarkerEnd(str);
    }

    @ReactProp(name = "markerMid")
    public void setMarkerMid(VirtualView virtualView, String str) {
        virtualView.setMarkerMid(str);
    }

    @ReactProp(name = "markerStart")
    public void setMarkerStart(VirtualView virtualView, String str) {
        virtualView.setMarkerStart(str);
    }

    @ReactProp(name = "mask")
    public void setMask(VirtualView virtualView, String str) {
        virtualView.setMask(str);
    }

    @ReactProp(name = "matrix")
    public void setMatrix(VirtualView virtualView, Dynamic dynamic) {
        virtualView.setMatrix(dynamic);
    }

    @ReactProp(name = "name")
    public void setName(VirtualView virtualView, String str) {
        virtualView.setName(str);
    }

    @ReactProp(name = "onLayout")
    public void setOnLayout(VirtualView virtualView, boolean z) {
        virtualView.setOnLayout(z);
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(VirtualView virtualView, String str) {
        if (str == null) {
            virtualView.setPointerEvents(PointerEvents.AUTO);
        } else {
            virtualView.setPointerEvents(PointerEvents.valueOf(str.toUpperCase(Locale.US).replace("-", "_")));
        }
    }

    @ReactProp(name = "propList")
    public void setPropList(RenderableView renderableView, ReadableArray readableArray) {
        renderableView.setPropList(readableArray);
    }

    @ReactProp(name = "responsible")
    public void setResponsible(VirtualView virtualView, boolean z) {
        virtualView.setResponsible(z);
    }

    @ReactProp(name = "stroke")
    public void setStroke(RenderableView renderableView, Dynamic dynamic) {
        renderableView.setStroke(dynamic);
    }

    @ReactProp(name = "strokeDasharray")
    public void setStrokeDasharray(RenderableView renderableView, ReadableArray readableArray) {
        renderableView.setStrokeDasharray(readableArray);
    }

    @ReactProp(name = "strokeDashoffset")
    public void setStrokeDashoffset(RenderableView renderableView, float f) {
        renderableView.setStrokeDashoffset(f);
    }

    @ReactProp(defaultInt = 1, name = "strokeLinecap")
    public void setStrokeLinecap(RenderableView renderableView, int i) {
        renderableView.setStrokeLinecap(i);
    }

    @ReactProp(defaultInt = 1, name = "strokeLinejoin")
    public void setStrokeLinejoin(RenderableView renderableView, int i) {
        renderableView.setStrokeLinejoin(i);
    }

    @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
    public void setStrokeMiterlimit(RenderableView renderableView, float f) {
        renderableView.setStrokeMiterlimit(f);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
    public void setStrokeOpacity(RenderableView renderableView, float f) {
        renderableView.setStrokeOpacity(f);
    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWidth(RenderableView renderableView, Dynamic dynamic) {
        renderableView.setStrokeWidth(dynamic);
    }

    @ReactProp(name = "transform")
    public void setTransform(VirtualView virtualView, Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Array) {
            ReadableArray asArray = dynamic.asArray();
            if (asArray == null) {
                resetTransformProperty(virtualView);
            } else {
                setTransformProperty(virtualView, asArray);
            }
            Matrix matrix = virtualView.getMatrix();
            virtualView.mTransform = matrix;
            virtualView.mTransformInvertible = matrix.invert(virtualView.mInvTransform);
        }
    }

    @ReactProp(name = "vectorEffect")
    public void setVectorEffect(RenderableView renderableView, int i) {
        renderableView.setVectorEffect(i);
    }

    private RenderableViewManager(SVGClass sVGClass) {
        this.svgClass = sVGClass;
        this.mClassName = sVGClass.toString();
    }

    public void addEventEmitters(ThemedReactContext themedReactContext, VirtualView virtualView) {
        super.addEventEmitters(themedReactContext, (ThemedReactContext) virtualView);
        virtualView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() { // from class: com.horcrux.svg.RenderableViewManager.1
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewAdded(View view, View view2) {
                if (view instanceof VirtualView) {
                    RenderableViewManager.this.invalidateSvgView((VirtualView) view);
                }
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewRemoved(View view, View view2) {
                if (view instanceof VirtualView) {
                    RenderableViewManager.this.invalidateSvgView((VirtualView) view);
                }
            }
        });
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new RenderableShadowNode(this);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public VirtualView createViewInstance(ThemedReactContext themedReactContext) {
        switch (this.svgClass.ordinal()) {
            case 0:
                return new GroupView(themedReactContext);
            case 1:
                return new PathView(themedReactContext);
            case 2:
                return new TextView(themedReactContext);
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                return new TSpanView(themedReactContext);
            case 4:
                return new TextPathView(themedReactContext);
            case 5:
                return new ImageView(themedReactContext);
            case 6:
                return new CircleView(themedReactContext);
            case 7:
                return new EllipseView(themedReactContext);
            case 8:
                return new LineView(themedReactContext);
            case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                return new RectView(themedReactContext);
            case 10:
                return new ClipPathView(themedReactContext);
            case 11:
                return new DefsView(themedReactContext);
            case 12:
                return new UseView(themedReactContext);
            case 13:
                return new SymbolView(themedReactContext);
            case 14:
                return new LinearGradientView(themedReactContext);
            case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                return new RadialGradientView(themedReactContext);
            case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                return new PatternView(themedReactContext);
            case 17:
                return new MaskView(themedReactContext);
            case 18:
                return new MarkerView(themedReactContext);
            case 19:
                return new ForeignObjectView(themedReactContext);
            default:
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unexpected type ");
                outline13.append(this.svgClass.toString());
                throw new IllegalStateException(outline13.toString());
        }
    }

    public void onAfterUpdateTransaction(VirtualView virtualView) {
        super.onAfterUpdateTransaction((RenderableViewManager) virtualView);
        invalidateSvgView(virtualView);
    }

    public void onDropViewInstance(VirtualView virtualView) {
        super.onDropViewInstance((RenderableViewManager) virtualView);
        mTagToRenderableView.remove(virtualView.getId());
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(VirtualView virtualView, float f) {
        virtualView.setOpacity(f);
    }
}
