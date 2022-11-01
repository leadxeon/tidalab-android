package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.view.View;
import android.view.ViewParent;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.ArrayList;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class TextView extends GroupView {
    public TextProperties$AlignmentBaseline mAlignmentBaseline;
    public ArrayList<SVGLength> mDeltaX;
    public ArrayList<SVGLength> mDeltaY;
    public ArrayList<SVGLength> mPositionX;
    public ArrayList<SVGLength> mPositionY;
    public ArrayList<SVGLength> mRotate;
    public SVGLength mInlineSize = null;
    public SVGLength mTextLength = null;
    public String mBaselineShift = null;
    public int mLengthAdjust = 1;
    public double cachedAdvance = Double.NaN;

    public TextView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.VirtualView
    public void clearCache() {
        this.cachedAdvance = Double.NaN;
        super.clearCache();
    }

    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        setupGlyphContext(canvas);
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
        getGroupPath(canvas, paint);
        pushGlyphContext();
        drawGroup(canvas, paint, f);
        popGlyphContext();
    }

    public Path getGroupPath(Canvas canvas, Paint paint) {
        Path path = ((VirtualView) this).mPath;
        if (path != null) {
            return path;
        }
        pushGlyphContext();
        ((VirtualView) this).mPath = super.getPath(canvas, paint);
        popGlyphContext();
        return ((VirtualView) this).mPath;
    }

    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = ((VirtualView) this).mPath;
        if (path != null) {
            return path;
        }
        setupGlyphContext(canvas);
        return getGroupPath(canvas, paint);
    }

    public double getSubtreeTextChunksTotalAdvance(Paint paint) {
        if (!Double.isNaN(this.cachedAdvance)) {
            return this.cachedAdvance;
        }
        double d = 0.0d;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof TextView) {
                d = ((TextView) childAt).getSubtreeTextChunksTotalAdvance(paint) + d;
            }
        }
        this.cachedAdvance = d;
        return d;
    }

    public TextView getTextContainer() {
        ViewParent parent = getParent();
        TextView textView = this;
        while (parent instanceof TextView) {
            textView = (TextView) parent;
            parent = textView.getParent();
        }
        return textView;
    }

    @Override // com.horcrux.svg.VirtualView, android.view.View
    public void invalidate() {
        if (((VirtualView) this).mPath != null) {
            super.invalidate();
            getTextContainer().clearChildCache();
        }
    }

    @Override // com.horcrux.svg.GroupView
    public void pushGlyphContext() {
        boolean z = !(this instanceof TextPathView) && !(this instanceof TSpanView);
        GlyphContext textRootGlyphContext = getTextRootGlyphContext();
        ReadableMap readableMap = this.mFont;
        ArrayList<SVGLength> arrayList = this.mPositionX;
        ArrayList<SVGLength> arrayList2 = this.mPositionY;
        ArrayList<SVGLength> arrayList3 = this.mDeltaX;
        ArrayList<SVGLength> arrayList4 = this.mDeltaY;
        ArrayList<SVGLength> arrayList5 = this.mRotate;
        if (z) {
            textRootGlyphContext.mRsIndex = 0;
            textRootGlyphContext.mDYsIndex = 0;
            textRootGlyphContext.mDXsIndex = 0;
            textRootGlyphContext.mYsIndex = 0;
            textRootGlyphContext.mXsIndex = 0;
            textRootGlyphContext.mRIndex = -1;
            textRootGlyphContext.mDYIndex = -1;
            textRootGlyphContext.mDXIndex = -1;
            textRootGlyphContext.mYIndex = -1;
            textRootGlyphContext.mXIndex = -1;
            textRootGlyphContext.mDY = 0.0d;
            textRootGlyphContext.mDX = 0.0d;
            textRootGlyphContext.mY = 0.0d;
            textRootGlyphContext.mX = 0.0d;
        }
        textRootGlyphContext.pushNodeAndFont(this, readableMap);
        if (!(arrayList == null || arrayList.size() == 0)) {
            textRootGlyphContext.mXsIndex++;
            textRootGlyphContext.mXIndex = -1;
            textRootGlyphContext.mXIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray = textRootGlyphContext.getStringArrayFromReadableArray(arrayList);
            textRootGlyphContext.mXs = stringArrayFromReadableArray;
            textRootGlyphContext.mXsContext.add(stringArrayFromReadableArray);
        }
        if (!(arrayList2 == null || arrayList2.size() == 0)) {
            textRootGlyphContext.mYsIndex++;
            textRootGlyphContext.mYIndex = -1;
            textRootGlyphContext.mYIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray2 = textRootGlyphContext.getStringArrayFromReadableArray(arrayList2);
            textRootGlyphContext.mYs = stringArrayFromReadableArray2;
            textRootGlyphContext.mYsContext.add(stringArrayFromReadableArray2);
        }
        if (!(arrayList3 == null || arrayList3.size() == 0)) {
            textRootGlyphContext.mDXsIndex++;
            textRootGlyphContext.mDXIndex = -1;
            textRootGlyphContext.mDXIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray3 = textRootGlyphContext.getStringArrayFromReadableArray(arrayList3);
            textRootGlyphContext.mDXs = stringArrayFromReadableArray3;
            textRootGlyphContext.mDXsContext.add(stringArrayFromReadableArray3);
        }
        if (!(arrayList4 == null || arrayList4.size() == 0)) {
            textRootGlyphContext.mDYsIndex++;
            textRootGlyphContext.mDYIndex = -1;
            textRootGlyphContext.mDYIndices.add(-1);
            SVGLength[] stringArrayFromReadableArray4 = textRootGlyphContext.getStringArrayFromReadableArray(arrayList4);
            textRootGlyphContext.mDYs = stringArrayFromReadableArray4;
            textRootGlyphContext.mDYsContext.add(stringArrayFromReadableArray4);
        }
        if (!(arrayList5 == null || arrayList5.size() == 0)) {
            textRootGlyphContext.mRsIndex++;
            textRootGlyphContext.mRIndex = -1;
            textRootGlyphContext.mRIndices.add(-1);
            int size = arrayList5.size();
            double[] dArr = new double[size];
            for (int i = 0; i < size; i++) {
                dArr[i] = arrayList5.get(i).value;
            }
            textRootGlyphContext.mRs = dArr;
            textRootGlyphContext.mRsContext.add(dArr);
        }
        textRootGlyphContext.pushIndices();
    }

    @ReactProp(name = "baselineShift")
    public void setBaselineShift(Dynamic dynamic) {
        String str;
        int ordinal = dynamic.getType().ordinal();
        if (ordinal != 2) {
            str = ordinal != 3 ? null : dynamic.asString();
        } else {
            str = String.valueOf(dynamic.asDouble());
        }
        this.mBaselineShift = str;
        invalidate();
    }

    @ReactProp(name = "dx")
    public void setDeltaX(Dynamic dynamic) {
        this.mDeltaX = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "dy")
    public void setDeltaY(Dynamic dynamic) {
        this.mDeltaY = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "inlineSize")
    public void setInlineSize(Dynamic dynamic) {
        this.mInlineSize = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "lengthAdjust")
    public void setLengthAdjust(String str) {
        int valueOfcom$horcrux$svg$TextProperties$TextLengthAdjust;
        valueOfcom$horcrux$svg$TextProperties$TextLengthAdjust = SolverVariable$Type$r8$EnumUnboxingUtility.valueOfcom$horcrux$svg$TextProperties$TextLengthAdjust(str);
        this.mLengthAdjust = valueOfcom$horcrux$svg$TextProperties$TextLengthAdjust;
        invalidate();
    }

    @ReactProp(name = "alignmentBaseline")
    public void setMethod(String str) {
        this.mAlignmentBaseline = TextProperties$AlignmentBaseline.getEnum(str);
        invalidate();
    }

    @ReactProp(name = "x")
    public void setPositionX(Dynamic dynamic) {
        this.mPositionX = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setPositionY(Dynamic dynamic) {
        this.mPositionY = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "rotate")
    public void setRotate(Dynamic dynamic) {
        this.mRotate = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "textLength")
    public void setTextLength(Dynamic dynamic) {
        this.mTextLength = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "verticalAlign")
    public void setVerticalAlign(String str) {
        if (str != null) {
            String trim = str.trim();
            int lastIndexOf = trim.lastIndexOf(32);
            try {
                this.mAlignmentBaseline = TextProperties$AlignmentBaseline.getEnum(trim.substring(lastIndexOf));
            } catch (IllegalArgumentException unused) {
                this.mAlignmentBaseline = TextProperties$AlignmentBaseline.baseline;
            }
            try {
                this.mBaselineShift = trim.substring(0, lastIndexOf);
            } catch (IndexOutOfBoundsException unused2) {
                this.mBaselineShift = null;
            }
        } else {
            this.mAlignmentBaseline = TextProperties$AlignmentBaseline.baseline;
            this.mBaselineShift = null;
        }
        invalidate();
    }

    @Override // com.horcrux.svg.GroupView
    public Path getPath(Canvas canvas, Paint paint, Region.Op op) {
        return getPath(canvas, paint);
    }
}
