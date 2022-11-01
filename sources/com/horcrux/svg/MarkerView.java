package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class MarkerView extends GroupView {
    public String mAlign;
    public SVGLength mMarkerHeight;
    public String mMarkerUnits;
    public SVGLength mMarkerWidth;
    public int mMeetOrSlice;
    public float mMinX;
    public float mMinY;
    public String mOrient;
    public SVGLength mRefX;
    public SVGLength mRefY;
    public float mVbHeight;
    public float mVbWidth;
    public Matrix markerTransform = new Matrix();

    public MarkerView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    public void saveDefinition() {
        if (this.mName != null) {
            SvgView svgView = getSvgView();
            svgView.mDefinedMarkers.put(this.mName, this);
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof VirtualView) {
                    ((VirtualView) childAt).saveDefinition();
                }
            }
        }
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
    }

    @ReactProp(name = "markerHeight")
    public void setMarkerHeight(Dynamic dynamic) {
        this.mMarkerHeight = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "markerUnits")
    public void setMarkerUnits(String str) {
        this.mMarkerUnits = str;
        invalidate();
    }

    @ReactProp(name = "markerWidth")
    public void setMarkerWidth(Dynamic dynamic) {
        this.mMarkerWidth = SVGLength.from(dynamic);
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

    @ReactProp(name = "orient")
    public void setOrient(String str) {
        this.mOrient = str;
        invalidate();
    }

    @ReactProp(name = "refX")
    public void setRefX(Dynamic dynamic) {
        this.mRefX = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "refY")
    public void setRefY(Dynamic dynamic) {
        this.mRefY = SVGLength.from(dynamic);
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
