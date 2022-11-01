package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.OnLayoutEvent;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.ArrayList;
import okhttp3.internal.ws.WebSocketProtocol;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public abstract class VirtualView extends ReactViewGroup {
    public static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    public ArrayList<PathElement> elements;
    public GlyphContext glyphContext;
    public RectF mBox;
    public Path mCachedClipPath;
    public RectF mClientRect;
    public RectF mClipBounds;
    public String mClipPath;
    public Region mClipRegion;
    public Path mClipRegionPath;
    public int mClipRule;
    public final ReactContext mContext;
    public String mDisplay;
    public RectF mFillBounds;
    public Path mFillPath;
    public RectF mMarkerBounds;
    public String mMarkerEnd;
    public String mMarkerMid;
    public Path mMarkerPath;
    public Region mMarkerRegion;
    public String mMarkerStart;
    public String mMask;
    public String mName;
    public boolean mOnLayout;
    public Path mPath;
    public PointerEvents mPointerEvents;
    public Region mRegion;
    public boolean mResponsible;
    public RectF mStrokeBounds;
    public Path mStrokePath;
    public Region mStrokeRegion;
    public GroupView mTextRoot;
    public SvgView svgView;
    public float mOpacity = 1.0f;
    public Matrix mCTM = new Matrix();
    public Matrix mMatrix = new Matrix();
    public Matrix mTransform = new Matrix();
    public Matrix mInvCTM = new Matrix();
    public Matrix mInvMatrix = new Matrix();
    public final Matrix mInvTransform = new Matrix();
    public boolean mInvertible = true;
    public boolean mTransformInvertible = true;
    public double fontSize = -1.0d;
    public double canvasDiagonal = -1.0d;
    public float canvasHeight = -1.0f;
    public float canvasWidth = -1.0f;
    public final float mScale = R$style.sScreenDisplayMetrics.density;

    public VirtualView(ReactContext reactContext) {
        super(reactContext);
        this.mContext = reactContext;
    }

    private double getCanvasDiagonal() {
        double d = this.canvasDiagonal;
        if (d != -1.0d) {
            return d;
        }
        double sqrt = Math.sqrt(Math.pow(getCanvasHeight(), 2.0d) + Math.pow(getCanvasWidth(), 2.0d)) * 0.7071067811865476d;
        this.canvasDiagonal = sqrt;
        return sqrt;
    }

    private float getCanvasHeight() {
        float f = this.canvasHeight;
        if (f != -1.0f) {
            return f;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasHeight = getSvgView().getCanvasBounds().height();
        } else {
            this.canvasHeight = textRoot.mGlyphContext.mHeight;
        }
        return this.canvasHeight;
    }

    private float getCanvasWidth() {
        float f = this.canvasWidth;
        if (f != -1.0f) {
            return f;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasWidth = getSvgView().getCanvasBounds().width();
        } else {
            this.canvasWidth = textRoot.mGlyphContext.mWidth;
        }
        return this.canvasWidth;
    }

    private double getFontSizeFromContext() {
        double d = this.fontSize;
        if (d != -1.0d) {
            return d;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            return 12.0d;
        }
        if (this.glyphContext == null) {
            this.glyphContext = textRoot.mGlyphContext;
        }
        double d2 = this.glyphContext.mFontSize;
        this.fontSize = d2;
        return d2;
    }

    public void clearCache() {
        this.canvasDiagonal = -1.0d;
        this.canvasHeight = -1.0f;
        this.canvasWidth = -1.0f;
        this.fontSize = -1.0d;
        this.mStrokeRegion = null;
        this.mMarkerRegion = null;
        this.mRegion = null;
        this.mPath = null;
    }

    public void clearChildCache() {
        clearCache();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).clearChildCache();
            }
        }
    }

    public final void clearParentCache() {
        VirtualView virtualView = this;
        while (true) {
            ViewParent parent = virtualView.getParent();
            if (parent instanceof VirtualView) {
                virtualView = (VirtualView) parent;
                if (virtualView.mPath != null) {
                    virtualView.clearCache();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public abstract void draw(Canvas canvas, Paint paint, float f);

    public final double fromRelativeFast(SVGLength sVGLength) {
        double d;
        switch (SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(sVGLength.unit)) {
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                d = getFontSizeFromContext();
                break;
            case 4:
                d = getFontSizeFromContext() / 2.0d;
                break;
            case 5:
            default:
                d = 1.0d;
                break;
            case 6:
                d = 35.43307d;
                break;
            case 7:
                d = 3.543307d;
                break;
            case 8:
                d = 90.0d;
                break;
            case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                d = 1.25d;
                break;
            case 10:
                d = 15.0d;
                break;
        }
        return sVGLength.value * d * this.mScale;
    }

    public RectF getClientRect() {
        return this.mClientRect;
    }

    public Path getClipPath() {
        return this.mCachedClipPath;
    }

    public GroupView getParentTextRoot() {
        ViewParent parent = getParent();
        if (!(parent instanceof VirtualView)) {
            return null;
        }
        return ((VirtualView) parent).getTextRoot();
    }

    public abstract Path getPath(Canvas canvas, Paint paint);

    public SvgView getSvgView() {
        SvgView svgView = this.svgView;
        if (svgView != null) {
            return svgView;
        }
        ViewParent parent = getParent();
        if (parent == null) {
            return null;
        }
        if (parent instanceof SvgView) {
            this.svgView = (SvgView) parent;
        } else if (parent instanceof VirtualView) {
            this.svgView = ((VirtualView) parent).getSvgView();
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("RNSVG: ");
            outline13.append(getClass().getName());
            outline13.append(" should be descendant of a SvgView.");
            FLog.e("ReactNative", outline13.toString());
        }
        return this.svgView;
    }

    public GroupView getTextRoot() {
        if (this.mTextRoot == null) {
            VirtualView virtualView = this;
            while (true) {
                if (virtualView == null) {
                    break;
                }
                if (virtualView instanceof GroupView) {
                    GroupView groupView = (GroupView) virtualView;
                    if (groupView.mGlyphContext != null) {
                        this.mTextRoot = groupView;
                        break;
                    }
                }
                ViewParent parent = virtualView.getParent();
                virtualView = !(parent instanceof VirtualView) ? null : (VirtualView) parent;
            }
        }
        return this.mTextRoot;
    }

    public abstract int hitTest(float[] fArr);

    @Override // android.view.View
    public void invalidate() {
        if (!(this instanceof RenderableView) || this.mPath != null) {
            clearCache();
            clearParentCache();
            super.invalidate();
        }
    }

    public boolean isResponsible() {
        return this.mResponsible;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        RectF rectF = this.mClientRect;
        if (rectF != null) {
            if (!(this instanceof GroupView)) {
                setLeft((int) Math.floor(rectF.left));
                setTop((int) Math.floor(this.mClientRect.top));
                setRight((int) Math.ceil(this.mClientRect.right));
                setBottom((int) Math.ceil(this.mClientRect.bottom));
            }
            setMeasuredDimension((int) Math.ceil(this.mClientRect.width()), (int) Math.ceil(this.mClientRect.height()));
        }
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        RectF rectF = this.mClientRect;
        if (rectF != null) {
            i3 = (int) Math.ceil(rectF.width());
        } else {
            i3 = ViewGroup.getDefaultSize(getSuggestedMinimumWidth(), i);
        }
        RectF rectF2 = this.mClientRect;
        if (rectF2 != null) {
            i4 = (int) Math.ceil(rectF2.height());
        } else {
            i4 = ViewGroup.getDefaultSize(getSuggestedMinimumHeight(), i2);
        }
        setMeasuredDimension(i3, i4);
    }

    public double relativeOnHeight(SVGLength sVGLength) {
        float f;
        double d;
        int i = sVGLength.unit;
        if (i == 2) {
            d = sVGLength.value;
            f = this.mScale;
        } else if (i != 3) {
            return fromRelativeFast(sVGLength);
        } else {
            d = sVGLength.value / 100.0d;
            f = getCanvasHeight();
        }
        return d * f;
    }

    public double relativeOnOther(SVGLength sVGLength) {
        double d;
        double d2;
        int i = sVGLength.unit;
        if (i == 2) {
            d2 = sVGLength.value;
            d = this.mScale;
        } else if (i != 3) {
            return fromRelativeFast(sVGLength);
        } else {
            d2 = sVGLength.value / 100.0d;
            d = getCanvasDiagonal();
        }
        return d2 * d;
    }

    public double relativeOnWidth(SVGLength sVGLength) {
        float f;
        double d;
        int i = sVGLength.unit;
        if (i == 2) {
            d = sVGLength.value;
            f = this.mScale;
        } else if (i != 3) {
            return fromRelativeFast(sVGLength);
        } else {
            d = sVGLength.value / 100.0d;
            f = getCanvasWidth();
        }
        return d * f;
    }

    public void render(Canvas canvas, Paint paint, float f) {
        draw(canvas, paint, f);
    }

    public int saveAndSetupCanvas(Canvas canvas, Matrix matrix) {
        int save = canvas.save();
        this.mCTM.setConcat(this.mMatrix, this.mTransform);
        canvas.concat(this.mCTM);
        this.mCTM.preConcat(matrix);
        this.mCTM.invert(this.mInvCTM);
        return save;
    }

    public void saveDefinition() {
        if (this.mName != null) {
            SvgView svgView = getSvgView();
            svgView.mDefinedTemplates.put(this.mName, this);
        }
    }

    public void setClientRect(RectF rectF) {
        RectF rectF2 = this.mClientRect;
        if (rectF2 == null || !rectF2.equals(rectF)) {
            this.mClientRect = rectF;
            if (rectF != null) {
                int ceil = (int) Math.ceil(rectF.width());
                int ceil2 = (int) Math.ceil(this.mClientRect.height());
                int floor = (int) Math.floor(this.mClientRect.left);
                int floor2 = (int) Math.floor(this.mClientRect.top);
                int ceil3 = (int) Math.ceil(this.mClientRect.right);
                int ceil4 = (int) Math.ceil(this.mClientRect.bottom);
                setMeasuredDimension(ceil, ceil2);
                if (!(this instanceof GroupView)) {
                    setLeft(floor);
                    setTop(floor2);
                    setRight(ceil3);
                    setBottom(ceil4);
                }
                if (this.mOnLayout) {
                    ((UIManagerModule) this.mContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(OnLayoutEvent.obtain(getId(), floor, floor2, ceil, ceil2));
                }
            }
        }
    }

    @ReactProp(name = "clipPath")
    public void setClipPath(String str) {
        this.mCachedClipPath = null;
        this.mClipPath = str;
        invalidate();
    }

    @ReactProp(defaultInt = 1, name = "clipRule")
    public void setClipRule(int i) {
        this.mClipRule = i;
        invalidate();
    }

    @ReactProp(name = "display")
    public void setDisplay(String str) {
        this.mDisplay = str;
        invalidate();
    }

    @ReactProp(name = "markerEnd")
    public void setMarkerEnd(String str) {
        this.mMarkerEnd = str;
        invalidate();
    }

    @ReactProp(name = "markerMid")
    public void setMarkerMid(String str) {
        this.mMarkerMid = str;
        invalidate();
    }

    @ReactProp(name = "markerStart")
    public void setMarkerStart(String str) {
        this.mMarkerStart = str;
        invalidate();
    }

    @ReactProp(name = "mask")
    public void setMask(String str) {
        this.mMask = str;
        invalidate();
    }

    @ReactProp(name = "matrix")
    public void setMatrix(Dynamic dynamic) {
        ReadableType type = dynamic.getType();
        if (dynamic.isNull() || !type.equals(ReadableType.Array)) {
            this.mMatrix.reset();
            this.mInvMatrix.reset();
            this.mInvertible = true;
        } else {
            ReadableArray asArray = dynamic.asArray();
            float[] fArr = sRawMatrix;
            int matrixData = PathParser.toMatrixData(asArray, fArr, this.mScale);
            if (matrixData == 6) {
                if (this.mMatrix == null) {
                    this.mMatrix = new Matrix();
                    this.mInvMatrix = new Matrix();
                }
                this.mMatrix.setValues(fArr);
                this.mInvertible = this.mMatrix.invert(this.mInvMatrix);
            } else if (matrixData != -1) {
                FLog.w("ReactNative", "RNSVG: Transform matrices must be of size 6");
            }
        }
        super.invalidate();
        clearParentCache();
    }

    @ReactProp(name = "name")
    public void setName(String str) {
        this.mName = str;
        invalidate();
    }

    @ReactProp(name = "onLayout")
    public void setOnLayout(boolean z) {
        this.mOnLayout = z;
        invalidate();
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(float f) {
        this.mOpacity = f;
        invalidate();
    }

    @Override // com.facebook.react.views.view.ReactViewGroup
    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    @ReactProp(name = "responsible")
    public void setResponsible(boolean z) {
        this.mResponsible = z;
        invalidate();
    }

    public Path getClipPath(Canvas canvas, Paint paint) {
        if (this.mClipPath != null) {
            ClipPathView clipPathView = (ClipPathView) getSvgView().mDefinedClipPaths.get(this.mClipPath);
            if (clipPathView != null) {
                Path path = this.mClipRule == 0 ? clipPathView.getPath(canvas, paint) : clipPathView.getPath(canvas, paint, Region.Op.UNION);
                path.transform(clipPathView.mMatrix);
                path.transform(clipPathView.mTransform);
                int i = this.mClipRule;
                if (i == 0) {
                    path.setFillType(Path.FillType.EVEN_ODD);
                } else if (i != 1) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("RNSVG: clipRule: ");
                    outline13.append(this.mClipRule);
                    outline13.append(" unrecognized");
                    FLog.w("ReactNative", outline13.toString());
                }
                this.mCachedClipPath = path;
            } else {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("RNSVG: Undefined clipPath: ");
                outline132.append(this.mClipPath);
                FLog.w("ReactNative", outline132.toString());
            }
        }
        return getClipPath();
    }
}
