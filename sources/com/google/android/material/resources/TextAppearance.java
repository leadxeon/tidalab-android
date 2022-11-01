package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import androidx.core.content.res.ResourcesCompat;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
/* loaded from: classes.dex */
public class TextAppearance {
    public Typeface font;
    public final String fontFamily;
    public final int fontFamilyResourceId;
    public boolean fontResolved = false;
    public final boolean hasLetterSpacing;
    public final float letterSpacing;
    public final ColorStateList shadowColor;
    public final float shadowDx;
    public final float shadowDy;
    public final float shadowRadius;
    public final ColorStateList textColor;
    public float textSize;
    public final int textStyle;
    public final int typeface;

    /* renamed from: com.google.android.material.resources.TextAppearance$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 extends TextAppearanceFontCallback {
        public final /* synthetic */ TextAppearanceFontCallback val$callback;
        public final /* synthetic */ TextPaint val$textPaint;

        public AnonymousClass2(TextPaint textPaint, TextAppearanceFontCallback textAppearanceFontCallback) {
            this.val$textPaint = textPaint;
            this.val$callback = textAppearanceFontCallback;
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrievalFailed(int i) {
            this.val$callback.onFontRetrievalFailed(i);
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrieved(Typeface typeface, boolean z) {
            TextAppearance.this.updateTextPaintMeasureState(this.val$textPaint, typeface);
            this.val$callback.onFontRetrieved(typeface, z);
        }
    }

    public TextAppearance(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.TextAppearance);
        this.textSize = obtainStyledAttributes.getDimension(0, 0.0f);
        this.textColor = R$style.getColorStateList(context, obtainStyledAttributes, 3);
        R$style.getColorStateList(context, obtainStyledAttributes, 4);
        R$style.getColorStateList(context, obtainStyledAttributes, 5);
        this.textStyle = obtainStyledAttributes.getInt(2, 0);
        this.typeface = obtainStyledAttributes.getInt(1, 1);
        int i2 = !obtainStyledAttributes.hasValue(12) ? 10 : 12;
        this.fontFamilyResourceId = obtainStyledAttributes.getResourceId(i2, 0);
        this.fontFamily = obtainStyledAttributes.getString(i2);
        obtainStyledAttributes.getBoolean(14, false);
        this.shadowColor = R$style.getColorStateList(context, obtainStyledAttributes, 6);
        this.shadowDx = obtainStyledAttributes.getFloat(7, 0.0f);
        this.shadowDy = obtainStyledAttributes.getFloat(8, 0.0f);
        this.shadowRadius = obtainStyledAttributes.getFloat(9, 0.0f);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(i, R$styleable.MaterialTextAppearance);
        this.hasLetterSpacing = obtainStyledAttributes2.hasValue(0);
        this.letterSpacing = obtainStyledAttributes2.getFloat(0, 0.0f);
        obtainStyledAttributes2.recycle();
    }

    public final void createFallbackFont() {
        String str;
        if (this.font == null && (str = this.fontFamily) != null) {
            this.font = Typeface.create(str, this.textStyle);
        }
        if (this.font == null) {
            int i = this.typeface;
            if (i == 1) {
                this.font = Typeface.SANS_SERIF;
            } else if (i == 2) {
                this.font = Typeface.SERIF;
            } else if (i != 3) {
                this.font = Typeface.DEFAULT;
            } else {
                this.font = Typeface.MONOSPACE;
            }
            this.font = Typeface.create(this.font, this.textStyle);
        }
    }

    public void getFontAsync(Context context, final TextAppearanceFontCallback textAppearanceFontCallback) {
        createFallbackFont();
        int i = this.fontFamilyResourceId;
        if (i == 0) {
            this.fontResolved = true;
        }
        if (this.fontResolved) {
            textAppearanceFontCallback.onFontRetrieved(this.font, true);
            return;
        }
        try {
            ResourcesCompat.FontCallback fontCallback = new ResourcesCompat.FontCallback() { // from class: com.google.android.material.resources.TextAppearance.1
                @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                public void onFontRetrievalFailed(int i2) {
                    TextAppearance.this.fontResolved = true;
                    textAppearanceFontCallback.onFontRetrievalFailed(i2);
                }

                @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                public void onFontRetrieved(Typeface typeface) {
                    TextAppearance textAppearance = TextAppearance.this;
                    textAppearance.font = Typeface.create(typeface, textAppearance.textStyle);
                    TextAppearance textAppearance2 = TextAppearance.this;
                    textAppearance2.fontResolved = true;
                    textAppearanceFontCallback.onFontRetrieved(textAppearance2.font, false);
                }
            };
            ThreadLocal<TypedValue> threadLocal = ResourcesCompat.sTempTypedValue;
            if (context.isRestricted()) {
                fontCallback.callbackFailAsync(-4, null);
            } else {
                ResourcesCompat.loadFont(context, i, new TypedValue(), 0, fontCallback, null, false, false);
            }
        } catch (Resources.NotFoundException unused) {
            this.fontResolved = true;
            textAppearanceFontCallback.onFontRetrievalFailed(1);
        } catch (Exception e) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Error loading font ");
            outline13.append(this.fontFamily);
            Log.d("TextAppearance", outline13.toString(), e);
            this.fontResolved = true;
            textAppearanceFontCallback.onFontRetrievalFailed(-3);
        }
    }

    public void updateDrawState(Context context, TextPaint textPaint, TextAppearanceFontCallback textAppearanceFontCallback) {
        createFallbackFont();
        updateTextPaintMeasureState(textPaint, this.font);
        getFontAsync(context, new AnonymousClass2(textPaint, textAppearanceFontCallback));
        ColorStateList colorStateList = this.textColor;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f = this.shadowRadius;
        float f2 = this.shadowDx;
        float f3 = this.shadowDy;
        ColorStateList colorStateList2 = this.shadowColor;
        textPaint.setShadowLayer(f, f2, f3, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void updateTextPaintMeasureState(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int i = (~typeface.getStyle()) & this.textStyle;
        textPaint.setFakeBoldText((i & 1) != 0);
        textPaint.setTextSkewX((i & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.textSize);
        if (this.hasLetterSpacing) {
            textPaint.setLetterSpacing(this.letterSpacing);
        }
    }
}
