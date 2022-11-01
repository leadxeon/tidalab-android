package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class TextDrawableHelper {
    public WeakReference<TextDrawableDelegate> delegate;
    public TextAppearance textAppearance;
    public float textWidth;
    public final TextPaint textPaint = new TextPaint(1);
    public final TextAppearanceFontCallback fontCallback = new TextAppearanceFontCallback() { // from class: com.google.android.material.internal.TextDrawableHelper.1
        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrievalFailed(int i) {
            TextDrawableHelper textDrawableHelper = TextDrawableHelper.this;
            textDrawableHelper.textWidthDirty = true;
            TextDrawableDelegate textDrawableDelegate = textDrawableHelper.delegate.get();
            if (textDrawableDelegate != null) {
                textDrawableDelegate.onTextSizeChange();
            }
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrieved(Typeface typeface, boolean z) {
            if (!z) {
                TextDrawableHelper textDrawableHelper = TextDrawableHelper.this;
                textDrawableHelper.textWidthDirty = true;
                TextDrawableDelegate textDrawableDelegate = textDrawableHelper.delegate.get();
                if (textDrawableDelegate != null) {
                    textDrawableDelegate.onTextSizeChange();
                }
            }
        }
    };
    public boolean textWidthDirty = true;

    /* loaded from: classes.dex */
    public interface TextDrawableDelegate {
        int[] getState();

        boolean onStateChange(int[] iArr);

        void onTextSizeChange();
    }

    public TextDrawableHelper(TextDrawableDelegate textDrawableDelegate) {
        this.delegate = new WeakReference<>(null);
        this.delegate = new WeakReference<>(textDrawableDelegate);
    }

    public float getTextWidth(String str) {
        if (!this.textWidthDirty) {
            return this.textWidth;
        }
        float measureText = str == null ? 0.0f : this.textPaint.measureText((CharSequence) str, 0, str.length());
        this.textWidth = measureText;
        this.textWidthDirty = false;
        return measureText;
    }

    public void setTextAppearance(TextAppearance textAppearance, Context context) {
        if (this.textAppearance != textAppearance) {
            this.textAppearance = textAppearance;
            if (textAppearance != null) {
                TextPaint textPaint = this.textPaint;
                TextAppearanceFontCallback textAppearanceFontCallback = this.fontCallback;
                textAppearance.createFallbackFont();
                textAppearance.updateTextPaintMeasureState(textPaint, textAppearance.font);
                textAppearance.getFontAsync(context, new TextAppearance.AnonymousClass2(textPaint, textAppearanceFontCallback));
                TextDrawableDelegate textDrawableDelegate = this.delegate.get();
                if (textDrawableDelegate != null) {
                    this.textPaint.drawableState = textDrawableDelegate.getState();
                }
                textAppearance.updateDrawState(context, this.textPaint, this.fontCallback);
                this.textWidthDirty = true;
            }
            TextDrawableDelegate textDrawableDelegate2 = this.delegate.get();
            if (textDrawableDelegate2 != null) {
                textDrawableDelegate2.onTextSizeChange();
                textDrawableDelegate2.onStateChange(textDrawableDelegate2.getState());
            }
        }
    }
}
