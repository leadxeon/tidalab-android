package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class FontData {
    public static final FontData Defaults = new FontData();
    public int absoluteFontWeight;
    public final ReadableMap fontData;
    public final String fontFamily;
    public final String fontFeatureSettings;
    public final double fontSize;
    public final int fontStyle;
    public final int fontVariantLigatures;
    public final String fontVariationSettings;
    public TextProperties$FontWeight fontWeight;
    public final double kerning;
    public final double letterSpacing;
    public final boolean manualKerning;
    public final int textAnchor;
    public final TextProperties$TextDecoration textDecoration;
    public final double wordSpacing;

    /* loaded from: classes.dex */
    public static class AbsoluteFontWeight {
        public static final TextProperties$FontWeight[] WEIGHTS;
        public static final int[] absoluteFontWeights = {400, 700, 100, 200, 300, 400, 500, 600, 700, 800, 900};

        static {
            TextProperties$FontWeight textProperties$FontWeight = TextProperties$FontWeight.w100;
            TextProperties$FontWeight textProperties$FontWeight2 = TextProperties$FontWeight.w900;
            WEIGHTS = new TextProperties$FontWeight[]{textProperties$FontWeight, textProperties$FontWeight, TextProperties$FontWeight.w200, TextProperties$FontWeight.w300, TextProperties$FontWeight.Normal, TextProperties$FontWeight.w500, TextProperties$FontWeight.w600, TextProperties$FontWeight.Bold, TextProperties$FontWeight.w800, textProperties$FontWeight2, textProperties$FontWeight2};
        }
    }

    public FontData() {
        this.fontData = null;
        this.fontFamily = HttpUrl.FRAGMENT_ENCODE_SET;
        this.fontStyle = 1;
        this.fontWeight = TextProperties$FontWeight.Normal;
        this.absoluteFontWeight = 400;
        this.fontFeatureSettings = HttpUrl.FRAGMENT_ENCODE_SET;
        this.fontVariationSettings = HttpUrl.FRAGMENT_ENCODE_SET;
        this.fontVariantLigatures = 1;
        this.textAnchor = 1;
        this.textDecoration = TextProperties$TextDecoration.None;
        this.manualKerning = false;
        this.kerning = 0.0d;
        this.fontSize = 12.0d;
        this.wordSpacing = 0.0d;
        this.letterSpacing = 0.0d;
    }

    public final void handleNumericWeight(FontData fontData, double d) {
        long round = Math.round(d);
        if (round < 1 || round > 1000) {
            setInheritedWeight(fontData);
            return;
        }
        int i = (int) round;
        this.absoluteFontWeight = i;
        this.fontWeight = AbsoluteFontWeight.WEIGHTS[Math.round(i / 100.0f)];
    }

    public final void setInheritedWeight(FontData fontData) {
        this.absoluteFontWeight = fontData.absoluteFontWeight;
        this.fontWeight = fontData.fontWeight;
    }

    public final double toAbsolute(ReadableMap readableMap, String str, double d, double d2, double d3) {
        if (readableMap.getType(str) == ReadableType.Number) {
            return readableMap.getDouble(str);
        }
        return PathParser.fromRelative(readableMap.getString(str), d3, d, d2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0060, code lost:
        if (r0 < 900) goto L_0x0081;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0074, code lost:
        if (r0 < 750) goto L_0x0076;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FontData(com.facebook.react.bridge.ReadableMap r12, com.horcrux.svg.FontData r13, double r14) {
        /*
            Method dump skipped, instructions count: 428
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.FontData.<init>(com.facebook.react.bridge.ReadableMap, com.horcrux.svg.FontData, double):void");
    }
}
