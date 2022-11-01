package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class SymbolView extends GroupView {
    public String mAlign;
    public int mMeetOrSlice;
    public float mMinX;
    public float mMinY;
    public float mVbHeight;
    public float mVbWidth;

    public SymbolView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        saveDefinition();
    }

    public void drawSymbol(Canvas canvas, Paint paint, float f, float f2, float f3) {
        if (this.mAlign != null) {
            float f4 = this.mMinX;
            float f5 = this.mScale;
            float f6 = this.mMinY;
            canvas.concat(PathParser.getTransform(new RectF(f4 * f5, f6 * f5, (f4 + this.mVbWidth) * f5, (f6 + this.mVbHeight) * f5), new RectF(0.0f, 0.0f, f2, f3), this.mAlign, this.mMeetOrSlice));
            setupGlyphContext(canvas);
            Path clipPath = getClipPath(canvas, paint);
            if (clipPath != null) {
                canvas.clipPath(clipPath);
            }
            drawGroup(canvas, paint, f);
        }
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.mAlign = str;
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
}
