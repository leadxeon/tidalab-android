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
public class RadialGradientView extends DefinitionView {
    public static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    public SVGLength mCx;
    public SVGLength mCy;
    public SVGLength mFx;
    public SVGLength mFy;
    public ReadableArray mGradient;
    public int mGradientUnits;
    public Matrix mMatrix = null;
    public SVGLength mRx;
    public SVGLength mRy;

    public RadialGradientView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.VirtualView
    public void saveDefinition() {
        if (this.mName != null) {
            Brush brush = new Brush(2, new SVGLength[]{this.mFx, this.mFy, this.mRx, this.mRy, this.mCx, this.mCy}, this.mGradientUnits);
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

    @ReactProp(name = "cx")
    public void setCx(Dynamic dynamic) {
        this.mCx = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "cy")
    public void setCy(Dynamic dynamic) {
        this.mCy = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "fx")
    public void setFx(Dynamic dynamic) {
        this.mFx = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "fy")
    public void setFy(Dynamic dynamic) {
        this.mFy = SVGLength.from(dynamic);
        invalidate();
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

    @ReactProp(name = "rx")
    public void setRx(Dynamic dynamic) {
        this.mRx = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "ry")
    public void setRy(Dynamic dynamic) {
        this.mRy = SVGLength.from(dynamic);
        invalidate();
    }
}
