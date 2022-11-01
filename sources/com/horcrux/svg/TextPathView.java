package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class TextPathView extends TextView {
    public String mHref;
    public int mMidLine;
    public int mSide;
    public SVGLength mStartOffset;

    public TextPathView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        drawGroup(canvas, paint, f);
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        return getGroupPath(canvas, paint);
    }

    @Override // com.horcrux.svg.GroupView
    public void popGlyphContext() {
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView
    public void pushGlyphContext() {
    }

    @ReactProp(name = "href")
    public void setHref(String str) {
        this.mHref = str;
        invalidate();
    }

    @Override // com.horcrux.svg.TextView
    @ReactProp(name = "method")
    public void setMethod(String str) {
        SolverVariable$Type$r8$EnumUnboxingUtility.valueOfcom$horcrux$svg$TextProperties$TextPathMethod(str);
        invalidate();
    }

    @ReactProp(name = "midLine")
    public void setSharp(String str) {
        int valueOfcom$horcrux$svg$TextProperties$TextPathMidLine;
        valueOfcom$horcrux$svg$TextProperties$TextPathMidLine = SolverVariable$Type$r8$EnumUnboxingUtility.valueOfcom$horcrux$svg$TextProperties$TextPathMidLine(str);
        this.mMidLine = valueOfcom$horcrux$svg$TextProperties$TextPathMidLine;
        invalidate();
    }

    @ReactProp(name = "side")
    public void setSide(String str) {
        int valueOfcom$horcrux$svg$TextProperties$TextPathSide;
        valueOfcom$horcrux$svg$TextProperties$TextPathSide = SolverVariable$Type$r8$EnumUnboxingUtility.valueOfcom$horcrux$svg$TextProperties$TextPathSide(str);
        this.mSide = valueOfcom$horcrux$svg$TextProperties$TextPathSide;
        invalidate();
    }

    @ReactProp(name = "spacing")
    public void setSpacing(String str) {
        SolverVariable$Type$r8$EnumUnboxingUtility.valueOfcom$horcrux$svg$TextProperties$TextPathSpacing(str);
        invalidate();
    }

    @ReactProp(name = "startOffset")
    public void setStartOffset(Dynamic dynamic) {
        this.mStartOffset = SVGLength.from(dynamic);
        invalidate();
    }
}
