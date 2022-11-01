package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class CircleView extends RenderableView {
    public SVGLength mCx;
    public SVGLength mCy;
    public SVGLength mR;

    public CircleView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.addCircle((float) relativeOnWidth(this.mCx), (float) relativeOnHeight(this.mCy), (float) relativeOnOther(this.mR), Path.Direction.CW);
        return path;
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

    @ReactProp(name = "r")
    public void setR(Dynamic dynamic) {
        this.mR = SVGLength.from(dynamic);
        invalidate();
    }
}
