package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
/* loaded from: classes.dex */
public class ARTGroupShadowNode extends ARTVirtualNode {
    public RectF mClipping;

    @Override // com.facebook.react.views.art.ARTVirtualNode
    public void draw(Canvas canvas, Paint paint, float f) {
        float f2 = f * this.mOpacity;
        if (f2 > 0.01f) {
            saveAndSetupCanvas(canvas);
            RectF rectF = this.mClipping;
            if (rectF != null) {
                float f3 = rectF.left;
                float f4 = this.mScale;
                canvas.clipRect(f3 * f4, rectF.top * f4, rectF.right * f4, rectF.bottom * f4);
            }
            for (int i = 0; i < getChildCount(); i++) {
                ARTVirtualNode aRTVirtualNode = (ARTVirtualNode) getChildAt(i);
                aRTVirtualNode.draw(canvas, paint, f2);
                aRTVirtualNode.markUpdateSeen();
            }
            canvas.restore();
        }
    }

    @ReactProp(name = "clipping")
    public void setClipping(ReadableArray readableArray) {
        float[] floatArray = R$style.toFloatArray(readableArray);
        if (floatArray == null) {
            return;
        }
        if (floatArray.length == 4) {
            this.mClipping = new RectF(floatArray[0], floatArray[1], floatArray[0] + floatArray[2], floatArray[1] + floatArray[3]);
            markUpdated();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Clipping should be array of length 4 (e.g. [x, y, width, height])");
    }
}
