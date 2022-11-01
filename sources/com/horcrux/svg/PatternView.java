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
public class PatternView extends GroupView {
    public static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    public String mAlign;
    public SVGLength mH;
    public Matrix mMatrix = null;
    public int mMeetOrSlice;
    public float mMinX;
    public float mMinY;
    public int mPatternContentUnits;
    public int mPatternUnits;
    public float mVbHeight;
    public float mVbWidth;
    public SVGLength mW;
    public SVGLength mX;
    public SVGLength mY;

    public PatternView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    public void saveDefinition() {
        if (this.mName != null) {
            boolean z = false;
            Brush brush = new Brush(3, new SVGLength[]{this.mX, this.mY, this.mW, this.mH}, this.mPatternUnits);
            if (this.mPatternContentUnits == 1) {
                z = true;
            }
            brush.mUseContentObjectBoundingBoxUnits = z;
            brush.mPattern = this;
            Matrix matrix = this.mMatrix;
            if (matrix != null) {
                brush.mMatrix = matrix;
            }
            SvgView svgView = getSvgView();
            if (this.mPatternUnits == 2 || this.mPatternContentUnits == 2) {
                brush.mUserSpaceBoundingBox = svgView.getCanvasBounds();
            }
            svgView.mDefinedBrushes.put(this.mName, brush);
        }
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        this.mH = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
    }

    @ReactProp(name = "minX")
    public void setMinX(float f) {
        this.mMinX = f;
        invalidate();
    }

    @ReactProp(name = "minY")
    public void setMinY(float f) {
        this.mMinY = f;
        invalidate();
    }

    @ReactProp(name = "patternContentUnits")
    public void setPatternContentUnits(int i) {
        if (i == 0) {
            this.mPatternContentUnits = 1;
        } else if (i == 1) {
            this.mPatternContentUnits = 2;
        }
        invalidate();
    }

    @ReactProp(name = "patternTransform")
    public void setPatternTransform(ReadableArray readableArray) {
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

    @ReactProp(name = "patternUnits")
    public void setPatternUnits(int i) {
        if (i == 0) {
            this.mPatternUnits = 1;
        } else if (i == 1) {
            this.mPatternUnits = 2;
        }
        invalidate();
    }

    @ReactProp(name = "vbHeight")
    public void setVbHeight(float f) {
        this.mVbHeight = f;
        invalidate();
    }

    @ReactProp(name = "vbWidth")
    public void setVbWidth(float f) {
        this.mVbWidth = f;
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
