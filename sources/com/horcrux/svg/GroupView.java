package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Objects;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class GroupView extends RenderableView {
    public ReadableMap mFont;
    public GlyphContext mGlyphContext;

    public GroupView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        setupGlyphContext(canvas);
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
        drawGroup(canvas, paint, f);
    }

    public void drawGroup(Canvas canvas, Paint paint, float f) {
        pushGlyphContext();
        SvgView svgView = getSvgView();
        RectF rectF = new RectF();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!(childAt instanceof MaskView)) {
                if (childAt instanceof VirtualView) {
                    VirtualView virtualView = (VirtualView) childAt;
                    if (!"none".equals(virtualView.mDisplay)) {
                        boolean z = virtualView instanceof RenderableView;
                        if (z) {
                            ((RenderableView) virtualView).mergeProperties(this);
                        }
                        int saveAndSetupCanvas = virtualView.saveAndSetupCanvas(canvas, this.mCTM);
                        virtualView.render(canvas, paint, this.mOpacity * f);
                        RectF clientRect = virtualView.getClientRect();
                        if (clientRect != null) {
                            rectF.union(clientRect);
                        }
                        canvas.restoreToCount(saveAndSetupCanvas);
                        if (z) {
                            ((RenderableView) virtualView).resetProperties();
                        }
                        if (virtualView.isResponsible()) {
                            svgView.enableTouchEvents();
                        }
                    }
                } else if (childAt instanceof SvgView) {
                    SvgView svgView2 = (SvgView) childAt;
                    svgView2.drawChildren(canvas);
                    if (svgView2.mResponsible) {
                        svgView.enableTouchEvents();
                    }
                }
            }
        }
        setClientRect(rectF);
        popGlyphContext();
    }

    public void drawPath(Canvas canvas, Paint paint, float f) {
        super.draw(canvas, paint, f);
    }

    @Override // com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = ((VirtualView) this).mPath;
        if (path != null) {
            return path;
        }
        ((VirtualView) this).mPath = new Path();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!(childAt instanceof MaskView) && (childAt instanceof VirtualView)) {
                VirtualView virtualView = (VirtualView) childAt;
                ((VirtualView) this).mPath.addPath(virtualView.getPath(canvas, paint), virtualView.mMatrix);
            }
        }
        return ((VirtualView) this).mPath;
    }

    public GlyphContext getTextRootGlyphContext() {
        GroupView textRoot = getTextRoot();
        Objects.requireNonNull(textRoot);
        return textRoot.mGlyphContext;
    }

    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        int reactTagForTouch;
        VirtualView virtualView;
        int hitTest;
        if (this.mInvertible && this.mTransformInvertible) {
            float[] fArr2 = new float[2];
            this.mInvMatrix.mapPoints(fArr2, fArr);
            this.mInvTransform.mapPoints(fArr2);
            int round = Math.round(fArr2[0]);
            int round2 = Math.round(fArr2[1]);
            Path clipPath = getClipPath();
            if (clipPath != null) {
                if (this.mClipRegionPath != clipPath) {
                    this.mClipRegionPath = clipPath;
                    RectF rectF = new RectF();
                    this.mClipBounds = rectF;
                    clipPath.computeBounds(rectF, true);
                    this.mClipRegion = getRegion(clipPath, this.mClipBounds);
                }
                if (!this.mClipRegion.contains(round, round2)) {
                    return -1;
                }
            }
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = getChildAt(childCount);
                if (childAt instanceof VirtualView) {
                    if (!(childAt instanceof MaskView) && (hitTest = (virtualView = (VirtualView) childAt).hitTest(fArr2)) != -1) {
                        return (virtualView.isResponsible() || hitTest != childAt.getId()) ? hitTest : getId();
                    }
                } else if ((childAt instanceof SvgView) && (reactTagForTouch = ((SvgView) childAt).reactTagForTouch(fArr2[0], fArr2[1])) != childAt.getId()) {
                    return reactTagForTouch;
                }
            }
        }
        return -1;
    }

    public void popGlyphContext() {
        GlyphContext textRootGlyphContext = getTextRootGlyphContext();
        textRootGlyphContext.mFontContext.remove(textRootGlyphContext.mTop);
        textRootGlyphContext.mXsIndices.remove(textRootGlyphContext.mTop);
        textRootGlyphContext.mYsIndices.remove(textRootGlyphContext.mTop);
        textRootGlyphContext.mDXsIndices.remove(textRootGlyphContext.mTop);
        textRootGlyphContext.mDYsIndices.remove(textRootGlyphContext.mTop);
        textRootGlyphContext.mRsIndices.remove(textRootGlyphContext.mTop);
        int i = textRootGlyphContext.mTop - 1;
        textRootGlyphContext.mTop = i;
        int i2 = textRootGlyphContext.mXsIndex;
        int i3 = textRootGlyphContext.mYsIndex;
        int i4 = textRootGlyphContext.mDXsIndex;
        int i5 = textRootGlyphContext.mDYsIndex;
        int i6 = textRootGlyphContext.mRsIndex;
        textRootGlyphContext.topFont = textRootGlyphContext.mFontContext.get(i);
        textRootGlyphContext.mXsIndex = textRootGlyphContext.mXsIndices.get(textRootGlyphContext.mTop).intValue();
        textRootGlyphContext.mYsIndex = textRootGlyphContext.mYsIndices.get(textRootGlyphContext.mTop).intValue();
        textRootGlyphContext.mDXsIndex = textRootGlyphContext.mDXsIndices.get(textRootGlyphContext.mTop).intValue();
        textRootGlyphContext.mDYsIndex = textRootGlyphContext.mDYsIndices.get(textRootGlyphContext.mTop).intValue();
        textRootGlyphContext.mRsIndex = textRootGlyphContext.mRsIndices.get(textRootGlyphContext.mTop).intValue();
        if (i2 != textRootGlyphContext.mXsIndex) {
            textRootGlyphContext.mXsContext.remove(i2);
            textRootGlyphContext.mXs = textRootGlyphContext.mXsContext.get(textRootGlyphContext.mXsIndex);
            textRootGlyphContext.mXIndex = textRootGlyphContext.mXIndices.get(textRootGlyphContext.mXsIndex).intValue();
        }
        if (i3 != textRootGlyphContext.mYsIndex) {
            textRootGlyphContext.mYsContext.remove(i3);
            textRootGlyphContext.mYs = textRootGlyphContext.mYsContext.get(textRootGlyphContext.mYsIndex);
            textRootGlyphContext.mYIndex = textRootGlyphContext.mYIndices.get(textRootGlyphContext.mYsIndex).intValue();
        }
        if (i4 != textRootGlyphContext.mDXsIndex) {
            textRootGlyphContext.mDXsContext.remove(i4);
            textRootGlyphContext.mDXs = textRootGlyphContext.mDXsContext.get(textRootGlyphContext.mDXsIndex);
            textRootGlyphContext.mDXIndex = textRootGlyphContext.mDXIndices.get(textRootGlyphContext.mDXsIndex).intValue();
        }
        if (i5 != textRootGlyphContext.mDYsIndex) {
            textRootGlyphContext.mDYsContext.remove(i5);
            textRootGlyphContext.mDYs = textRootGlyphContext.mDYsContext.get(textRootGlyphContext.mDYsIndex);
            textRootGlyphContext.mDYIndex = textRootGlyphContext.mDYIndices.get(textRootGlyphContext.mDYsIndex).intValue();
        }
        if (i6 != textRootGlyphContext.mRsIndex) {
            textRootGlyphContext.mRsContext.remove(i6);
            textRootGlyphContext.mRs = textRootGlyphContext.mRsContext.get(textRootGlyphContext.mRsIndex);
            textRootGlyphContext.mRIndex = textRootGlyphContext.mRIndices.get(textRootGlyphContext.mRsIndex).intValue();
        }
    }

    public void pushGlyphContext() {
        GlyphContext textRootGlyphContext = getTextRootGlyphContext();
        textRootGlyphContext.pushNodeAndFont(this, this.mFont);
        textRootGlyphContext.pushIndices();
    }

    @Override // com.horcrux.svg.RenderableView
    public void resetProperties() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof RenderableView) {
                ((RenderableView) childAt).resetProperties();
            }
        }
    }

    @Override // com.horcrux.svg.VirtualView
    public void saveDefinition() {
        if (this.mName != null) {
            SvgView svgView = getSvgView();
            svgView.mDefinedTemplates.put(this.mName, this);
        }
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).saveDefinition();
            }
        }
    }

    @ReactProp(name = "font")
    public void setFont(ReadableMap readableMap) {
        this.mFont = readableMap;
        invalidate();
    }

    public void setupGlyphContext(Canvas canvas) {
        RectF rectF = new RectF(canvas.getClipBounds());
        Matrix matrix = this.mMatrix;
        if (matrix != null) {
            matrix.mapRect(rectF);
        }
        Matrix matrix2 = this.mTransform;
        if (matrix2 != null) {
            matrix2.mapRect(rectF);
        }
        this.mGlyphContext = new GlyphContext(this.mScale, rectF.width(), rectF.height());
    }

    public Path getPath(Canvas canvas, Paint paint, Region.Op op) {
        Path path;
        Path path2 = new Path();
        Path.Op valueOf = Path.Op.valueOf(op.name());
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!(childAt instanceof MaskView) && (childAt instanceof VirtualView)) {
                VirtualView virtualView = (VirtualView) childAt;
                Matrix matrix = virtualView.mMatrix;
                if (virtualView instanceof GroupView) {
                    path = ((GroupView) virtualView).getPath(canvas, paint, op);
                } else {
                    path = virtualView.getPath(canvas, paint);
                }
                path.transform(matrix);
                path2.op(path, valueOf);
            }
        }
        return path2;
    }
}
