package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class EllipseView extends RenderableView {
    public SVGLength mCx;
    public SVGLength mCy;
    public SVGLength mRx;
    public SVGLength mRy;

    public EllipseView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.mCx);
        double relativeOnHeight = relativeOnHeight(this.mCy);
        double relativeOnWidth2 = relativeOnWidth(this.mRx);
        double relativeOnHeight2 = relativeOnHeight(this.mRy);
        path.addOval(new RectF((float) (relativeOnWidth - relativeOnWidth2), (float) (relativeOnHeight - relativeOnHeight2), (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2)), Path.Direction.CW);
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
