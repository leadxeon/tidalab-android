package androidx.core.text;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
/* loaded from: classes.dex */
public class PrecomputedTextCompat implements Spannable {
    @Override // java.lang.CharSequence
    public char charAt(int i) {
        throw null;
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        throw null;
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        throw null;
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        throw null;
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 29) {
            throw null;
        }
        throw null;
    }

    @Override // java.lang.CharSequence
    public int length() {
        throw null;
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        throw null;
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        } else if (Build.VERSION.SDK_INT >= 29) {
            throw null;
        } else {
            throw null;
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        } else if (Build.VERSION.SDK_INT >= 29) {
            throw null;
        } else {
            throw null;
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        throw null;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        throw null;
    }

    /* loaded from: classes.dex */
    public static final class Params {
        public final int mBreakStrategy;
        public final int mHyphenationFrequency;
        public final TextPaint mPaint;
        public final TextDirectionHeuristic mTextDir;

        @SuppressLint({"NewApi"})
        public Params(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 29) {
                new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            }
            this.mPaint = textPaint;
            this.mTextDir = textDirectionHeuristic;
            this.mBreakStrategy = i;
            this.mHyphenationFrequency = i2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Params)) {
                return false;
            }
            Params params = (Params) obj;
            return equalsWithoutTextDirection(params) && this.mTextDir == params.mTextDir;
        }

        public boolean equalsWithoutTextDirection(Params params) {
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 && (this.mBreakStrategy != params.mBreakStrategy || this.mHyphenationFrequency != params.mHyphenationFrequency)) || this.mPaint.getTextSize() != params.mPaint.getTextSize() || this.mPaint.getTextScaleX() != params.mPaint.getTextScaleX() || this.mPaint.getTextSkewX() != params.mPaint.getTextSkewX() || this.mPaint.getLetterSpacing() != params.mPaint.getLetterSpacing() || !TextUtils.equals(this.mPaint.getFontFeatureSettings(), params.mPaint.getFontFeatureSettings()) || this.mPaint.getFlags() != params.mPaint.getFlags()) {
                return false;
            }
            if (i >= 24) {
                if (!this.mPaint.getTextLocales().equals(params.mPaint.getTextLocales())) {
                    return false;
                }
            } else if (!this.mPaint.getTextLocale().equals(params.mPaint.getTextLocale())) {
                return false;
            }
            return this.mPaint.getTypeface() == null ? params.mPaint.getTypeface() == null : this.mPaint.getTypeface().equals(params.mPaint.getTypeface());
        }

        public int hashCode() {
            if (Build.VERSION.SDK_INT >= 24) {
                return Objects.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocales(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            }
            return Objects.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("textSize=");
            outline13.append(this.mPaint.getTextSize());
            sb.append(outline13.toString());
            sb.append(", textScaleX=" + this.mPaint.getTextScaleX());
            sb.append(", textSkewX=" + this.mPaint.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            StringBuilder outline132 = GeneratedOutlineSupport.outline13(", letterSpacing=");
            outline132.append(this.mPaint.getLetterSpacing());
            sb.append(outline132.toString());
            sb.append(", elegantTextHeight=" + this.mPaint.isElegantTextHeight());
            if (i >= 24) {
                StringBuilder outline133 = GeneratedOutlineSupport.outline13(", textLocale=");
                outline133.append(this.mPaint.getTextLocales());
                sb.append(outline133.toString());
            } else {
                StringBuilder outline134 = GeneratedOutlineSupport.outline13(", textLocale=");
                outline134.append(this.mPaint.getTextLocale());
                sb.append(outline134.toString());
            }
            StringBuilder outline135 = GeneratedOutlineSupport.outline13(", typeface=");
            outline135.append(this.mPaint.getTypeface());
            sb.append(outline135.toString());
            if (i >= 26) {
                StringBuilder outline136 = GeneratedOutlineSupport.outline13(", variationSettings=");
                outline136.append(this.mPaint.getFontVariationSettings());
                sb.append(outline136.toString());
            }
            StringBuilder outline137 = GeneratedOutlineSupport.outline13(", textDir=");
            outline137.append(this.mTextDir);
            sb.append(outline137.toString());
            sb.append(", breakStrategy=" + this.mBreakStrategy);
            sb.append(", hyphenationFrequency=" + this.mHyphenationFrequency);
            sb.append("}");
            return sb.toString();
        }

        public Params(PrecomputedText.Params params) {
            this.mPaint = params.getTextPaint();
            this.mTextDir = params.getTextDirection();
            this.mBreakStrategy = params.getBreakStrategy();
            this.mHyphenationFrequency = params.getHyphenationFrequency();
            int i = Build.VERSION.SDK_INT;
        }
    }
}
