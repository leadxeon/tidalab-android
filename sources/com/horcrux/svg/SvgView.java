package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewParent;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.uimanager.ReactCompoundViewGroup;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.HashMap;
import java.util.Map;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class SvgView extends ReactViewGroup implements ReactCompoundView, ReactCompoundViewGroup {
    public String mAlign;
    public Bitmap mBitmap;
    public Canvas mCanvas;
    public int mMeetOrSlice;
    public float mMinX;
    public float mMinY;
    public float mVbHeight;
    public float mVbWidth;
    public SVGLength mbbHeight;
    public SVGLength mbbWidth;
    public Runnable toDataUrlTask = null;
    public boolean mResponsible = false;
    public final Map<String, VirtualView> mDefinedClipPaths = new HashMap();
    public final Map<String, VirtualView> mDefinedTemplates = new HashMap();
    public final Map<String, VirtualView> mDefinedMarkers = new HashMap();
    public final Map<String, VirtualView> mDefinedMasks = new HashMap();
    public final Map<String, Brush> mDefinedBrushes = new HashMap();
    public final Matrix mInvViewBoxMatrix = new Matrix();
    public boolean mInvertible = true;
    public boolean mRendered = false;
    public int mTintColor = 0;
    public final float mScale = R$style.sScreenDisplayMetrics.density;

    public SvgView(ReactContext reactContext) {
        super(reactContext);
    }

    private RectF getViewBox() {
        float f = this.mMinX;
        float f2 = this.mScale;
        float f3 = this.mMinY;
        return new RectF(f * f2, f3 * f2, (f + this.mVbWidth) * f2, (f3 + this.mVbHeight) * f2);
    }

    public final void clearChildCache() {
        if (this.mRendered) {
            this.mRendered = false;
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof VirtualView) {
                    ((VirtualView) childAt).clearChildCache();
                }
            }
        }
    }

    public synchronized void drawChildren(Canvas canvas) {
        this.mRendered = true;
        this.mCanvas = canvas;
        Matrix matrix = new Matrix();
        if (this.mAlign != null) {
            RectF viewBox = getViewBox();
            float width = canvas.getWidth();
            float height = canvas.getHeight();
            boolean z = getParent() instanceof VirtualView;
            if (z) {
                width = (float) PathParser.fromRelative(this.mbbWidth, width, 0.0d, this.mScale, 12.0d);
                height = (float) PathParser.fromRelative(this.mbbHeight, height, 0.0d, this.mScale, 12.0d);
            }
            RectF rectF = new RectF(0.0f, 0.0f, width, height);
            if (z) {
                canvas.clipRect(rectF);
            }
            matrix = PathParser.getTransform(viewBox, rectF, this.mAlign, this.mMeetOrSlice);
            this.mInvertible = matrix.invert(this.mInvViewBoxMatrix);
            canvas.concat(matrix);
        }
        Paint paint = new Paint();
        paint.setFlags(385);
        paint.setTypeface(Typeface.DEFAULT);
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).saveDefinition();
            }
        }
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt2 = getChildAt(i2);
            if (childAt2 instanceof VirtualView) {
                VirtualView virtualView = (VirtualView) childAt2;
                int saveAndSetupCanvas = virtualView.saveAndSetupCanvas(canvas, matrix);
                virtualView.render(canvas, paint, 1.0f);
                canvas.restoreToCount(saveAndSetupCanvas);
                if (virtualView.isResponsible() && !this.mResponsible) {
                    this.mResponsible = true;
                }
            }
        }
    }

    public void enableTouchEvents() {
        if (!this.mResponsible) {
            this.mResponsible = true;
        }
    }

    public Rect getCanvasBounds() {
        return this.mCanvas.getClipBounds();
    }

    public VirtualView getDefinedMarker(String str) {
        return this.mDefinedMarkers.get(str);
    }

    public VirtualView getDefinedTemplate(String str) {
        return this.mDefinedTemplates.get(str);
    }

    public final int hitTest(float f, float f2) {
        if (!this.mResponsible || !this.mInvertible) {
            return getId();
        }
        float[] fArr = {f, f2};
        this.mInvViewBoxMatrix.mapPoints(fArr);
        int i = -1;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt instanceof VirtualView) {
                i = ((VirtualView) childAt).hitTest(fArr);
            } else if (childAt instanceof SvgView) {
                i = ((SvgView) childAt).hitTest(f, f2);
            }
            if (i != -1) {
                break;
            }
        }
        return i == -1 ? getId() : i;
    }

    @Override // com.facebook.react.uimanager.ReactCompoundViewGroup
    public boolean interceptsTouchEvent(float f, float f2) {
        return true;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        ViewParent parent = getParent();
        if (!(parent instanceof VirtualView)) {
            Bitmap bitmap = this.mBitmap;
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.mBitmap = null;
        } else if (this.mRendered) {
            this.mRendered = false;
            ((VirtualView) parent).getSvgView().invalidate();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Bitmap bitmap;
        if (!(getParent() instanceof VirtualView)) {
            super.onDraw(canvas);
            if (this.mBitmap == null) {
                boolean z = true;
                this.mRendered = true;
                float width = getWidth();
                float height = getHeight();
                if (!Float.isNaN(width) && !Float.isNaN(height) && width >= 1.0f && height >= 1.0f) {
                    if (Math.log10(height) + Math.log10(width) <= 42.0d) {
                        z = false;
                    }
                }
                if (z) {
                    bitmap = null;
                } else {
                    bitmap = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
                    drawChildren(new Canvas(bitmap));
                }
                this.mBitmap = bitmap;
            }
            Bitmap bitmap2 = this.mBitmap;
            if (bitmap2 != null) {
                canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
                Runnable runnable = this.toDataUrlTask;
                if (runnable != null) {
                    runnable.run();
                    this.toDataUrlTask = null;
                }
            }
        }
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        invalidate();
    }

    @Override // com.facebook.react.uimanager.ReactCompoundView
    public int reactTagForTouch(float f, float f2) {
        return hitTest(f, f2);
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "bbHeight")
    public void setBbHeight(Dynamic dynamic) {
        this.mbbHeight = SVGLength.from(dynamic);
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "bbWidth")
    public void setBbWidth(Dynamic dynamic) {
        this.mbbWidth = SVGLength.from(dynamic);
        invalidate();
        clearChildCache();
    }

    @Override // android.view.View
    public void setId(int i) {
        super.setId(i);
        SvgViewManager.setSvgView(i, this);
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "minX")
    public void setMinX(float f) {
        this.mMinX = f;
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "minY")
    public void setMinY(float f) {
        this.mMinY = f;
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "tintColor")
    public void setTintColor(Integer num) {
        if (num == null) {
            this.mTintColor = 0;
        } else {
            this.mTintColor = num.intValue();
        }
        invalidate();
        clearChildCache();
    }

    public void setToDataUrlTask(Runnable runnable) {
        this.toDataUrlTask = runnable;
    }

    @ReactProp(name = "vbHeight")
    public void setVbHeight(float f) {
        this.mVbHeight = f;
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "vbWidth")
    public void setVbWidth(float f) {
        this.mVbWidth = f;
        invalidate();
        clearChildCache();
    }
}
