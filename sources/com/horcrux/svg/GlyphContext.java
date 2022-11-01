package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class GlyphContext {
    public double mDX;
    public final ArrayList<Integer> mDXIndices;
    public final ArrayList<SVGLength[]> mDXsContext;
    public int mDXsIndex;
    public double mDY;
    public final ArrayList<Integer> mDYIndices;
    public final ArrayList<SVGLength[]> mDYsContext;
    public int mDYsIndex;
    public final ArrayList<FontData> mFontContext;
    public final float mHeight;
    public final ArrayList<Integer> mRIndices;
    public final ArrayList<double[]> mRsContext;
    public int mRsIndex;
    public final float mScale;
    public int mTop;
    public final float mWidth;
    public double mX;
    public final ArrayList<Integer> mXIndices;
    public SVGLength[] mXs;
    public final ArrayList<SVGLength[]> mXsContext;
    public int mXsIndex;
    public double mY;
    public final ArrayList<Integer> mYIndices;
    public final ArrayList<SVGLength[]> mYsContext;
    public int mYsIndex;
    public final ArrayList<Integer> mXsIndices = new ArrayList<>();
    public final ArrayList<Integer> mYsIndices = new ArrayList<>();
    public final ArrayList<Integer> mDXsIndices = new ArrayList<>();
    public final ArrayList<Integer> mDYsIndices = new ArrayList<>();
    public final ArrayList<Integer> mRsIndices = new ArrayList<>();
    public double mFontSize = 12.0d;
    public FontData topFont = FontData.Defaults;
    public SVGLength[] mYs = new SVGLength[0];
    public SVGLength[] mDXs = new SVGLength[0];
    public SVGLength[] mDYs = new SVGLength[0];
    public double[] mRs = {0.0d};
    public int mXIndex = -1;
    public int mYIndex = -1;
    public int mDXIndex = -1;
    public int mDYIndex = -1;
    public int mRIndex = -1;

    public GlyphContext(float f, float f2, float f3) {
        ArrayList<FontData> arrayList = new ArrayList<>();
        this.mFontContext = arrayList;
        ArrayList<SVGLength[]> arrayList2 = new ArrayList<>();
        this.mXsContext = arrayList2;
        ArrayList<SVGLength[]> arrayList3 = new ArrayList<>();
        this.mYsContext = arrayList3;
        ArrayList<SVGLength[]> arrayList4 = new ArrayList<>();
        this.mDXsContext = arrayList4;
        ArrayList<SVGLength[]> arrayList5 = new ArrayList<>();
        this.mDYsContext = arrayList5;
        ArrayList<double[]> arrayList6 = new ArrayList<>();
        this.mRsContext = arrayList6;
        ArrayList<Integer> arrayList7 = new ArrayList<>();
        this.mXIndices = arrayList7;
        ArrayList<Integer> arrayList8 = new ArrayList<>();
        this.mYIndices = arrayList8;
        ArrayList<Integer> arrayList9 = new ArrayList<>();
        this.mDXIndices = arrayList9;
        ArrayList<Integer> arrayList10 = new ArrayList<>();
        this.mDYIndices = arrayList10;
        ArrayList<Integer> arrayList11 = new ArrayList<>();
        this.mRIndices = arrayList11;
        SVGLength[] sVGLengthArr = new SVGLength[0];
        this.mXs = sVGLengthArr;
        this.mScale = f;
        this.mWidth = f2;
        this.mHeight = f3;
        arrayList2.add(sVGLengthArr);
        arrayList3.add(this.mYs);
        arrayList4.add(this.mDXs);
        arrayList5.add(this.mDYs);
        arrayList6.add(this.mRs);
        arrayList7.add(Integer.valueOf(this.mXIndex));
        arrayList8.add(Integer.valueOf(this.mYIndex));
        arrayList9.add(Integer.valueOf(this.mDXIndex));
        arrayList10.add(Integer.valueOf(this.mDYIndex));
        arrayList11.add(Integer.valueOf(this.mRIndex));
        arrayList.add(this.topFont);
        pushIndices();
    }

    public static void incrementIndices(ArrayList<Integer> arrayList, int i) {
        while (i >= 0) {
            arrayList.set(i, Integer.valueOf(arrayList.get(i).intValue() + 1));
            i--;
        }
    }

    public final SVGLength[] getStringArrayFromReadableArray(ArrayList<SVGLength> arrayList) {
        int size = arrayList.size();
        SVGLength[] sVGLengthArr = new SVGLength[size];
        for (int i = 0; i < size; i++) {
            sVGLengthArr[i] = arrayList.get(i);
        }
        return sVGLengthArr;
    }

    public double nextX(double d) {
        incrementIndices(this.mXIndices, this.mXsIndex);
        int i = this.mXIndex + 1;
        SVGLength[] sVGLengthArr = this.mXs;
        if (i < sVGLengthArr.length) {
            this.mDX = 0.0d;
            this.mXIndex = i;
            this.mX = PathParser.fromRelative(sVGLengthArr[i], this.mWidth, 0.0d, this.mScale, this.mFontSize);
        }
        double d2 = this.mX + d;
        this.mX = d2;
        return d2;
    }

    public double nextY() {
        incrementIndices(this.mYIndices, this.mYsIndex);
        int i = this.mYIndex + 1;
        SVGLength[] sVGLengthArr = this.mYs;
        if (i < sVGLengthArr.length) {
            this.mDY = 0.0d;
            this.mYIndex = i;
            this.mY = PathParser.fromRelative(sVGLengthArr[i], this.mHeight, 0.0d, this.mScale, this.mFontSize);
        }
        return this.mY;
    }

    public final void pushIndices() {
        this.mXsIndices.add(Integer.valueOf(this.mXsIndex));
        this.mYsIndices.add(Integer.valueOf(this.mYsIndex));
        this.mDXsIndices.add(Integer.valueOf(this.mDXsIndex));
        this.mDYsIndices.add(Integer.valueOf(this.mDYsIndex));
        this.mRsIndices.add(Integer.valueOf(this.mRsIndex));
    }

    public final void pushNodeAndFont(GroupView groupView, ReadableMap readableMap) {
        FontData fontData;
        if (this.mTop > 0) {
            fontData = this.topFont;
        } else {
            GroupView parentTextRoot = groupView.getParentTextRoot();
            while (true) {
                if (parentTextRoot == null) {
                    fontData = FontData.Defaults;
                    break;
                }
                FontData fontData2 = parentTextRoot.mGlyphContext.topFont;
                if (fontData2 != FontData.Defaults) {
                    fontData = fontData2;
                    break;
                }
                parentTextRoot = parentTextRoot.getParentTextRoot();
            }
        }
        this.mTop++;
        if (readableMap == null) {
            this.mFontContext.add(fontData);
            return;
        }
        FontData fontData3 = new FontData(readableMap, fontData, this.mScale);
        this.mFontSize = fontData3.fontSize;
        this.mFontContext.add(fontData3);
        this.topFont = fontData3;
    }
}
