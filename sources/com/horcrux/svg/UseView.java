package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class UseView extends RenderableView {
    public SVGLength mH;
    public String mHref;
    public SVGLength mW;
    public SVGLength mX;
    public SVGLength mY;

    public UseView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
        if (definedTemplate == null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("`Use` element expected a pre-defined svg template as `href` prop, template named: ");
            outline13.append(this.mHref);
            outline13.append(" is not defined.");
            FLog.w("ReactNative", outline13.toString());
            return;
        }
        definedTemplate.clearCache();
        canvas.translate((float) relativeOnWidth(this.mX), (float) relativeOnHeight(this.mY));
        boolean z = definedTemplate instanceof RenderableView;
        if (z) {
            ((RenderableView) definedTemplate).mergeProperties(this);
        }
        int saveAndSetupCanvas = definedTemplate.saveAndSetupCanvas(canvas, this.mCTM);
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
        if (definedTemplate instanceof SymbolView) {
            ((SymbolView) definedTemplate).drawSymbol(canvas, paint, f, (float) relativeOnWidth(this.mW), (float) relativeOnHeight(this.mH));
        } else {
            definedTemplate.draw(canvas, paint, f * this.mOpacity);
        }
        setClientRect(definedTemplate.getClientRect());
        canvas.restoreToCount(saveAndSetupCanvas);
        if (z) {
            ((RenderableView) definedTemplate).resetProperties();
        }
    }

    @Override // com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
        if (definedTemplate == null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("`Use` element expected a pre-defined svg template as `href` prop, template named: ");
            outline13.append(this.mHref);
            outline13.append(" is not defined.");
            FLog.w("ReactNative", outline13.toString());
            return null;
        }
        Path path = definedTemplate.getPath(canvas, paint);
        Path path2 = new Path();
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) relativeOnWidth(this.mX), (float) relativeOnHeight(this.mY));
        path.transform(matrix, path2);
        return path2;
    }

    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        if (this.mInvertible && this.mTransformInvertible) {
            float[] fArr2 = new float[2];
            this.mInvMatrix.mapPoints(fArr2, fArr);
            this.mInvTransform.mapPoints(fArr2);
            VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
            if (definedTemplate == null) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("`Use` element expected a pre-defined svg template as `href` prop, template named: ");
                outline13.append(this.mHref);
                outline13.append(" is not defined.");
                FLog.w("ReactNative", outline13.toString());
                return -1;
            }
            int hitTest = definedTemplate.hitTest(fArr2);
            if (hitTest != -1) {
                return (definedTemplate.isResponsible() || hitTest != definedTemplate.getId()) ? hitTest : getId();
            }
        }
        return -1;
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        this.mH = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "href")
    public void setHref(String str) {
        this.mHref = str;
        invalidate();
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic dynamic) {
        this.mW = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "x")
    public void setX(Dynamic dynamic) {
        this.mX = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setY(Dynamic dynamic) {
        this.mY = SVGLength.from(dynamic);
        invalidate();
    }
}
