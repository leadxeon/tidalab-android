package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactFontManager;
import java.util.ArrayList;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class TSpanView extends TextView {
    public Path mCachedPath;
    public String mContent;
    public TextPathView textPath;
    public final ArrayList<String> emoji = new ArrayList<>();
    public final ArrayList<Matrix> emojiTransforms = new ArrayList<>();
    public final AssetManager assets = this.mContext.getResources().getAssets();

    public TSpanView(ReactContext reactContext) {
        super(reactContext);
    }

    public final void applySpacingAndFeatures(Paint paint, FontData fontData) {
        int i = Build.VERSION.SDK_INT;
        double d = fontData.letterSpacing;
        paint.setLetterSpacing((float) (d / (fontData.fontSize * this.mScale)));
        boolean z = true;
        if (!(d == 0.0d && fontData.fontVariantLigatures == 1)) {
            z = false;
        }
        if (z) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk','kern', 'hlig', 'cala', ");
            outline13.append(fontData.fontFeatureSettings);
            paint.setFontFeatureSettings(outline13.toString());
        } else {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk','kern', 'liga' 0, 'clig' 0, 'dlig' 0, 'hlig' 0, 'cala' 0, ");
            outline132.append(fontData.fontFeatureSettings);
            paint.setFontFeatureSettings(outline132.toString());
        }
        if (i >= 26) {
            StringBuilder outline133 = GeneratedOutlineSupport.outline13("'wght' ");
            outline133.append(fontData.absoluteFontWeight);
            outline133.append(fontData.fontVariationSettings);
            paint.setFontVariationSettings(outline133.toString());
        }
    }

    public final void applyTextPropertiesToPaint(Paint paint, FontData fontData) {
        boolean z = fontData.fontWeight == TextProperties$FontWeight.Bold || fontData.absoluteFontWeight >= 550;
        int i = 2;
        boolean z2 = fontData.fontStyle == 2;
        if (z && z2) {
            i = 3;
        } else if (z) {
            i = 1;
        } else if (!z2) {
            i = 0;
        }
        Typeface typeface = null;
        int i2 = fontData.absoluteFontWeight;
        String str = fontData.fontFamily;
        if (str != null && str.length() > 0) {
            String outline9 = GeneratedOutlineSupport.outline9("fonts/", str, ".otf");
            String outline92 = GeneratedOutlineSupport.outline9("fonts/", str, ".ttf");
            if (Build.VERSION.SDK_INT >= 26) {
                Typeface.Builder builder = new Typeface.Builder(this.assets, outline9);
                builder.setFontVariationSettings("'wght' " + i2 + fontData.fontVariationSettings);
                builder.setWeight(i2);
                builder.setItalic(z2);
                typeface = builder.build();
                if (typeface == null) {
                    Typeface.Builder builder2 = new Typeface.Builder(this.assets, outline92);
                    builder2.setFontVariationSettings("'wght' " + i2 + fontData.fontVariationSettings);
                    builder2.setWeight(i2);
                    builder2.setItalic(z2);
                    typeface = builder2.build();
                }
            } else {
                try {
                    try {
                        typeface = Typeface.create(Typeface.createFromAsset(this.assets, outline9), i);
                    } catch (Exception unused) {
                        typeface = Typeface.create(Typeface.createFromAsset(this.assets, outline92), i);
                    }
                } catch (Exception unused2) {
                }
            }
        }
        if (typeface == null) {
            try {
                if (ReactFontManager.sReactFontManagerInstance == null) {
                    ReactFontManager.sReactFontManagerInstance = new ReactFontManager();
                }
                typeface = ReactFontManager.sReactFontManagerInstance.getTypeface(str, i, 0, this.assets);
            } catch (Exception unused3) {
            }
        }
        if (Build.VERSION.SDK_INT >= 28) {
            typeface = Typeface.create(typeface, i2, z2);
        }
        paint.setLinearText(true);
        paint.setSubpixelText(true);
        paint.setTypeface(typeface);
        paint.setTextSize((float) (fontData.fontSize * this.mScale));
        paint.setLetterSpacing(0.0f);
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.VirtualView
    public void clearCache() {
        this.mCachedPath = null;
        super.clearCache();
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        if (this.mContent != null) {
            SVGLength sVGLength = this.mInlineSize;
            if (sVGLength == null || sVGLength.value == 0.0d) {
                int size = this.emoji.size();
                if (size > 0) {
                    applyTextPropertiesToPaint(paint, getTextRootGlyphContext().topFont);
                    for (int i = 0; i < size; i++) {
                        canvas.save();
                        canvas.concat(this.emojiTransforms.get(i));
                        canvas.drawText(this.emoji.get(i), 0.0f, 0.0f, paint);
                        canvas.restore();
                    }
                }
                drawPath(canvas, paint, f);
                return;
            }
            if (setupFillPaint(paint, this.fillOpacity * f)) {
                drawWrappedText(canvas, paint);
            }
            if (setupStrokePaint(paint, f * this.strokeOpacity)) {
                drawWrappedText(canvas, paint);
                return;
            }
            return;
        }
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
        drawGroup(canvas, paint, f);
    }

    public final void drawWrappedText(Canvas canvas, Paint paint) {
        Layout.Alignment alignment;
        StaticLayout staticLayout;
        GlyphContext textRootGlyphContext = getTextRootGlyphContext();
        pushGlyphContext();
        FontData fontData = textRootGlyphContext.topFont;
        TextPaint textPaint = new TextPaint(paint);
        applyTextPropertiesToPaint(textPaint, fontData);
        applySpacingAndFeatures(textPaint, fontData);
        double d = textRootGlyphContext.mFontSize;
        int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(fontData.textAnchor);
        if ($enumboxing$ordinal == 1) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        } else if ($enumboxing$ordinal != 2) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        } else {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        }
        SpannableString spannableString = new SpannableString(this.mContent);
        int fromRelative = (int) PathParser.fromRelative(this.mInlineSize, canvas.getWidth(), 0.0d, this.mScale, d);
        if (Build.VERSION.SDK_INT < 23) {
            staticLayout = new StaticLayout(spannableString, textPaint, fromRelative, alignment, 1.0f, 0.0f, true);
        } else {
            staticLayout = StaticLayout.Builder.obtain(spannableString, 0, spannableString.length(), textPaint, fromRelative).setAlignment(alignment).setLineSpacing(0.0f, 1.0f).setIncludePad(true).setBreakStrategy(1).setHyphenationFrequency(1).build();
        }
        int lineAscent = staticLayout.getLineAscent(0);
        popGlyphContext();
        canvas.save();
        canvas.translate((float) textRootGlyphContext.nextX(0.0d), (float) (textRootGlyphContext.nextY() + lineAscent));
        staticLayout.draw(canvas);
        canvas.restore();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01ed  */
    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Path getPath(android.graphics.Canvas r79, android.graphics.Paint r80) {
        /*
            Method dump skipped, instructions count: 1920
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TSpanView.getPath(android.graphics.Canvas, android.graphics.Paint):android.graphics.Path");
    }

    @Override // com.horcrux.svg.TextView
    public double getSubtreeTextChunksTotalAdvance(Paint paint) {
        if (!Double.isNaN(this.cachedAdvance)) {
            return this.cachedAdvance;
        }
        String str = this.mContent;
        double d = 0.0d;
        if (str == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof TextView) {
                    d = ((TextView) childAt).getSubtreeTextChunksTotalAdvance(paint) + d;
                }
            }
            this.cachedAdvance = d;
            return d;
        } else if (str.length() == 0) {
            this.cachedAdvance = 0.0d;
            return 0.0d;
        } else {
            FontData fontData = getTextRootGlyphContext().topFont;
            applyTextPropertiesToPaint(paint, fontData);
            applySpacingAndFeatures(paint, fontData);
            double measureText = paint.measureText(str);
            this.cachedAdvance = measureText;
            return measureText;
        }
    }

    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        Region region;
        if (this.mContent == null) {
            return super.hitTest(fArr);
        }
        if (((VirtualView) this).mPath != null && this.mInvertible && this.mTransformInvertible) {
            float[] fArr2 = new float[2];
            this.mInvMatrix.mapPoints(fArr2, fArr);
            this.mInvTransform.mapPoints(fArr2);
            int round = Math.round(fArr2[0]);
            int round2 = Math.round(fArr2[1]);
            initBounds();
            Region region2 = this.mRegion;
            if ((region2 != null && region2.contains(round, round2)) || ((region = this.mStrokeRegion) != null && region.contains(round, round2))) {
                if (getClipPath() == null || this.mClipRegion.contains(round, round2)) {
                    return getId();
                }
                return -1;
            }
        }
        return -1;
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.VirtualView, android.view.View
    public void invalidate() {
        this.mCachedPath = null;
        super.invalidate();
    }

    @ReactProp(name = "content")
    public void setContent(String str) {
        this.mContent = str;
        invalidate();
    }
}
