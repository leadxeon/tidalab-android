package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.annotations.ReactProp;
/* loaded from: classes.dex */
public abstract class ARTVirtualNode extends ReactShadowNodeImpl {
    public static final float[] sMatrixData = new float[9];
    public static final float[] sRawMatrix = new float[9];
    public float mOpacity = 1.0f;
    public Matrix mMatrix = new Matrix();
    public final float mScale = R$style.sWindowDisplayMetrics.density;

    public abstract void draw(Canvas canvas, Paint paint, float f);

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return true;
    }

    public final void saveAndSetupCanvas(Canvas canvas) {
        canvas.save();
        Matrix matrix = this.mMatrix;
        if (matrix != null) {
            canvas.concat(matrix);
        }
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(float f) {
        this.mOpacity = f;
        markUpdated();
    }

    @ReactProp(name = "transform")
    public void setTransform(ReadableArray readableArray) {
        if (readableArray != null) {
            float[] fArr = sMatrixData;
            int floatArray = R$style.toFloatArray(readableArray, fArr);
            if (floatArray == 6) {
                float[] fArr2 = sRawMatrix;
                fArr2[0] = fArr[0];
                fArr2[1] = fArr[2];
                float f = fArr[4];
                float f2 = this.mScale;
                fArr2[2] = f * f2;
                fArr2[3] = fArr[1];
                fArr2[4] = fArr[3];
                fArr2[5] = fArr[5] * f2;
                fArr2[6] = 0.0f;
                fArr2[7] = 0.0f;
                fArr2[8] = 1.0f;
                if (this.mMatrix == null) {
                    this.mMatrix = new Matrix();
                }
                this.mMatrix.setValues(fArr2);
            } else if (floatArray != -1) {
                throw new JSApplicationIllegalArgumentException("Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix = null;
        }
        markUpdated();
    }
}
