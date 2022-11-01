package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class MaskView extends GroupView {
    public static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    public SVGLength mH;
    public Matrix mMatrix = null;
    public SVGLength mW;
    public SVGLength mX;
    public SVGLength mY;

    public MaskView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    public void saveDefinition() {
        if (this.mName != null) {
            SvgView svgView = getSvgView();
            svgView.mDefinedMasks.put(this.mName, this);
        }
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        this.mH = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "maskContentUnits")
    public void setMaskContentUnits(int i) {
        invalidate();
    }

    @ReactProp(name = "maskTransform")
    public void setMaskTransform(ReadableArray readableArray) {
        if (readableArray != null) {
            float[] fArr = sRawMatrix;
            int matrixData = PathParser.toMatrixData(readableArray, fArr, this.mScale);
            if (matrixData == 6) {
                if (this.mMatrix == null) {
                    this.mMatrix = new Matrix();
                }
                this.mMatrix.setValues(fArr);
            } else if (matrixData != -1) {
                FLog.w("ReactNative", "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix = null;
        }
        invalidate();
    }

    @ReactProp(name = "maskUnits")
    public void setMaskUnits(int i) {
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
