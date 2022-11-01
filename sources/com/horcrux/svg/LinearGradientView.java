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
public class LinearGradientView extends DefinitionView {
    public static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    public ReadableArray mGradient;
    public int mGradientUnits;
    public Matrix mMatrix = null;
    public SVGLength mX1;
    public SVGLength mX2;
    public SVGLength mY1;
    public SVGLength mY2;

    public LinearGradientView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.VirtualView
    public void saveDefinition() {
        if (this.mName != null) {
            Brush brush = new Brush(1, new SVGLength[]{this.mX1, this.mY1, this.mX2, this.mY2}, this.mGradientUnits);
            brush.mColors = this.mGradient;
            Matrix matrix = this.mMatrix;
            if (matrix != null) {
                brush.mMatrix = matrix;
            }
            SvgView svgView = getSvgView();
            if (this.mGradientUnits == 2) {
                brush.mUserSpaceBoundingBox = svgView.getCanvasBounds();
            }
            svgView.mDefinedBrushes.put(this.mName, brush);
        }
    }

    @ReactProp(name = "gradient")
    public void setGradient(ReadableArray readableArray) {
        this.mGradient = readableArray;
        invalidate();
    }

    @ReactProp(name = "gradientTransform")
    public void setGradientTransform(ReadableArray readableArray) {
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

    @ReactProp(name = "gradientUnits")
    public void setGradientUnits(int i) {
        if (i == 0) {
            this.mGradientUnits = 1;
        } else if (i == 1) {
            this.mGradientUnits = 2;
        }
        invalidate();
    }

    @ReactProp(name = "x1")
    public void setX1(Dynamic dynamic) {
        this.mX1 = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "x2")
    public void setX2(Dynamic dynamic) {
        this.mX2 = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "y1")
    public void setY1(Dynamic dynamic) {
        this.mY1 = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "y2")
    public void setY2(Dynamic dynamic) {
        this.mY2 = SVGLength.from(dynamic);
        invalidate();
    }
}
