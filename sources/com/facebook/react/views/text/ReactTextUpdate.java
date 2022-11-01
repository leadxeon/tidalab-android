package com.facebook.react.views.text;

import android.text.Spannable;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class ReactTextUpdate {
    public ReadableMap mAttributedString;
    public final boolean mContainsImages;
    public final int mJsEventCounter;
    public final int mJustificationMode;
    public final float mPaddingBottom;
    public final float mPaddingLeft;
    public final float mPaddingRight;
    public final float mPaddingTop;
    public final int mSelectionEnd;
    public final int mSelectionStart;
    public final Spannable mText;
    public final int mTextAlign;
    public final int mTextBreakStrategy;

    public ReactTextUpdate(Spannable spannable, int i, boolean z, int i2, int i3, int i4) {
        this(spannable, i, z, -1.0f, -1.0f, -1.0f, -1.0f, i2, i3, i4, -1, -1);
    }

    public ReactTextUpdate(Spannable spannable, int i, boolean z, float f, float f2, float f3, float f4, int i2, int i3, int i4, int i5, int i6) {
        this.mAttributedString = null;
        this.mText = spannable;
        this.mJsEventCounter = i;
        this.mContainsImages = z;
        this.mPaddingLeft = f;
        this.mPaddingTop = f2;
        this.mPaddingRight = f3;
        this.mPaddingBottom = f4;
        this.mTextAlign = i2;
        this.mTextBreakStrategy = i3;
        this.mSelectionStart = i5;
        this.mSelectionEnd = i6;
        this.mJustificationMode = i4;
    }
}
