package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class AppCompatTextHelper {
    public boolean mAsyncFontPending;
    public final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    public TintInfo mDrawableBottomTint;
    public TintInfo mDrawableEndTint;
    public TintInfo mDrawableLeftTint;
    public TintInfo mDrawableRightTint;
    public TintInfo mDrawableStartTint;
    public TintInfo mDrawableTint;
    public TintInfo mDrawableTopTint;
    public Typeface mFontTypeface;
    public final TextView mView;
    public int mStyle = 0;
    public int mFontWeight = -1;

    public AppCompatTextHelper(TextView textView) {
        this.mView = textView;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(textView);
    }

    public static TintInfo createTintInfo(Context context, AppCompatDrawableManager appCompatDrawableManager, int i) {
        ColorStateList tintList = appCompatDrawableManager.getTintList(context, i);
        if (tintList == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.mHasTintList = true;
        tintInfo.mTintList = tintList;
        return tintInfo;
    }

    public final void applyCompoundDrawableTint(Drawable drawable, TintInfo tintInfo) {
        if (drawable != null && tintInfo != null) {
            AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
        }
    }

    public void applyCompoundDrawablesTints() {
        if (!(this.mDrawableLeftTint == null && this.mDrawableTopTint == null && this.mDrawableRightTint == null && this.mDrawableBottomTint == null)) {
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
            applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
            applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
            applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
        }
        if (this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
            Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            applyCompoundDrawableTint(compoundDrawablesRelative[0], this.mDrawableStartTint);
            applyCompoundDrawableTint(compoundDrawablesRelative[2], this.mDrawableEndTint);
        }
    }

    public boolean isAutoSizeEnabled() {
        AppCompatTextViewAutoSizeHelper appCompatTextViewAutoSizeHelper = this.mAutoSizeTextHelper;
        return appCompatTextViewAutoSizeHelper.supportsAutoSizeText() && appCompatTextViewAutoSizeHelper.mAutoSizeTextType != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0471  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:257:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0105  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void loadFromAttributes(android.util.AttributeSet r29, int r30) {
        /*
            Method dump skipped, instructions count: 1150
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatTextHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    public void onSetTextAppearance(Context context, int i) {
        String string;
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.TextAppearance);
        TintTypedArray tintTypedArray = new TintTypedArray(context, obtainStyledAttributes);
        if (tintTypedArray.hasValue(14)) {
            this.mView.setAllCaps(tintTypedArray.getBoolean(14, false));
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 23) {
            if (tintTypedArray.hasValue(3) && (colorStateList3 = tintTypedArray.getColorStateList(3)) != null) {
                this.mView.setTextColor(colorStateList3);
            }
            if (tintTypedArray.hasValue(5) && (colorStateList2 = tintTypedArray.getColorStateList(5)) != null) {
                this.mView.setLinkTextColor(colorStateList2);
            }
            if (tintTypedArray.hasValue(4) && (colorStateList = tintTypedArray.getColorStateList(4)) != null) {
                this.mView.setHintTextColor(colorStateList);
            }
        }
        if (tintTypedArray.hasValue(0) && tintTypedArray.getDimensionPixelSize(0, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        updateTypefaceAndStyle(context, tintTypedArray);
        if (i2 >= 26 && tintTypedArray.hasValue(13) && (string = tintTypedArray.getString(13)) != null) {
            this.mView.setFontVariationSettings(string);
        }
        obtainStyledAttributes.recycle();
        Typeface typeface = this.mFontTypeface;
        if (typeface != null) {
            this.mView.setTypeface(typeface, this.mStyle);
        }
    }

    public void populateSurroundingTextIfNeeded(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
        CharSequence charSequence;
        int i = Build.VERSION.SDK_INT;
        if (i < 30 && inputConnection != null) {
            CharSequence text = textView.getText();
            if (i >= 30) {
                editorInfo.setInitialSurroundingSubText(text, 0);
                return;
            }
            Objects.requireNonNull(text);
            if (i >= 30) {
                editorInfo.setInitialSurroundingSubText(text, 0);
                return;
            }
            int i2 = editorInfo.initialSelStart;
            int i3 = editorInfo.initialSelEnd;
            int i4 = i2 > i3 ? i3 + 0 : i2 + 0;
            int i5 = i2 > i3 ? i2 - 0 : i3 + 0;
            int length = text.length();
            if (i4 < 0 || i5 > length) {
                EditorInfoCompat.setSurroundingText(editorInfo, null, 0, 0);
                return;
            }
            int i6 = editorInfo.inputType & 4095;
            if (i6 == 129 || i6 == 225 || i6 == 18) {
                EditorInfoCompat.setSurroundingText(editorInfo, null, 0, 0);
            } else if (length <= 2048) {
                EditorInfoCompat.setSurroundingText(editorInfo, text, i4, i5);
            } else {
                int i7 = i5 - i4;
                int i8 = i7 > 1024 ? 0 : i7;
                int i9 = 2048 - i8;
                int min = Math.min(text.length() - i5, i9 - Math.min(i4, (int) (i9 * 0.8d)));
                int min2 = Math.min(i4, i9 - min);
                int i10 = i4 - min2;
                if (EditorInfoCompat.isCutOnSurrogate(text, i10, 0)) {
                    i10++;
                    min2--;
                }
                if (EditorInfoCompat.isCutOnSurrogate(text, (i5 + min) - 1, 1)) {
                    min--;
                }
                int i11 = min2 + i8 + min;
                if (i8 != i7) {
                    charSequence = TextUtils.concat(text.subSequence(i10, i10 + min2), text.subSequence(i5, min + i5));
                } else {
                    charSequence = text.subSequence(i10, i11 + i10);
                }
                int i12 = min2 + 0;
                EditorInfoCompat.setSurroundingText(editorInfo, charSequence, i12, i8 + i12);
            }
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        AppCompatTextViewAutoSizeHelper appCompatTextViewAutoSizeHelper = this.mAutoSizeTextHelper;
        if (appCompatTextViewAutoSizeHelper.supportsAutoSizeText()) {
            DisplayMetrics displayMetrics = appCompatTextViewAutoSizeHelper.mContext.getResources().getDisplayMetrics();
            appCompatTextViewAutoSizeHelper.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (appCompatTextViewAutoSizeHelper.setupAutoSizeText()) {
                appCompatTextViewAutoSizeHelper.autoSizeText();
            }
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) throws IllegalArgumentException {
        AppCompatTextViewAutoSizeHelper appCompatTextViewAutoSizeHelper = this.mAutoSizeTextHelper;
        if (appCompatTextViewAutoSizeHelper.supportsAutoSizeText()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = appCompatTextViewAutoSizeHelper.mContext.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr2[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                appCompatTextViewAutoSizeHelper.mAutoSizeTextSizesInPx = appCompatTextViewAutoSizeHelper.cleanupAutoSizePresetSizes(iArr2);
                if (!appCompatTextViewAutoSizeHelper.setupAutoSizeUniformPresetSizesConfiguration()) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("None of the preset sizes is valid: ");
                    outline13.append(Arrays.toString(iArr));
                    throw new IllegalArgumentException(outline13.toString());
                }
            } else {
                appCompatTextViewAutoSizeHelper.mHasPresetAutoSizeValues = false;
            }
            if (appCompatTextViewAutoSizeHelper.setupAutoSizeText()) {
                appCompatTextViewAutoSizeHelper.autoSizeText();
            }
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i) {
        AppCompatTextViewAutoSizeHelper appCompatTextViewAutoSizeHelper = this.mAutoSizeTextHelper;
        if (!appCompatTextViewAutoSizeHelper.supportsAutoSizeText()) {
            return;
        }
        if (i == 0) {
            appCompatTextViewAutoSizeHelper.mAutoSizeTextType = 0;
            appCompatTextViewAutoSizeHelper.mAutoSizeMinTextSizeInPx = -1.0f;
            appCompatTextViewAutoSizeHelper.mAutoSizeMaxTextSizeInPx = -1.0f;
            appCompatTextViewAutoSizeHelper.mAutoSizeStepGranularityInPx = -1.0f;
            appCompatTextViewAutoSizeHelper.mAutoSizeTextSizesInPx = new int[0];
            appCompatTextViewAutoSizeHelper.mNeedsAutoSizeText = false;
        } else if (i == 1) {
            DisplayMetrics displayMetrics = appCompatTextViewAutoSizeHelper.mContext.getResources().getDisplayMetrics();
            appCompatTextViewAutoSizeHelper.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (appCompatTextViewAutoSizeHelper.setupAutoSizeText()) {
                appCompatTextViewAutoSizeHelper.autoSizeText();
            }
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("Unknown auto-size text type: ", i));
        }
    }

    public void setCompoundDrawableTintList(ColorStateList colorStateList) {
        if (this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        TintInfo tintInfo = this.mDrawableTint;
        tintInfo.mTintList = colorStateList;
        tintInfo.mHasTintList = colorStateList != null;
        this.mDrawableLeftTint = tintInfo;
        this.mDrawableTopTint = tintInfo;
        this.mDrawableRightTint = tintInfo;
        this.mDrawableBottomTint = tintInfo;
        this.mDrawableStartTint = tintInfo;
        this.mDrawableEndTint = tintInfo;
    }

    public void setCompoundDrawableTintMode(PorterDuff.Mode mode) {
        if (this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        TintInfo tintInfo = this.mDrawableTint;
        tintInfo.mTintMode = mode;
        tintInfo.mHasTintMode = mode != null;
        this.mDrawableLeftTint = tintInfo;
        this.mDrawableTopTint = tintInfo;
        this.mDrawableRightTint = tintInfo;
        this.mDrawableBottomTint = tintInfo;
        this.mDrawableStartTint = tintInfo;
        this.mDrawableEndTint = tintInfo;
    }

    public final void updateTypefaceAndStyle(Context context, TintTypedArray tintTypedArray) {
        String string;
        this.mStyle = tintTypedArray.getInt(2, this.mStyle);
        int i = Build.VERSION.SDK_INT;
        boolean z = false;
        if (i >= 28) {
            int i2 = tintTypedArray.getInt(11, -1);
            this.mFontWeight = i2;
            if (i2 != -1) {
                this.mStyle = (this.mStyle & 2) | 0;
            }
        }
        int i3 = 10;
        if (tintTypedArray.hasValue(10) || tintTypedArray.hasValue(12)) {
            this.mFontTypeface = null;
            if (tintTypedArray.hasValue(12)) {
                i3 = 12;
            }
            final int i4 = this.mFontWeight;
            final int i5 = this.mStyle;
            if (!context.isRestricted()) {
                final WeakReference weakReference = new WeakReference(this.mView);
                try {
                    Typeface font = tintTypedArray.getFont(i3, this.mStyle, new ResourcesCompat.FontCallback() { // from class: androidx.appcompat.widget.AppCompatTextHelper.1
                        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                        public void onFontRetrievalFailed(int i6) {
                        }

                        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
                        public void onFontRetrieved(Typeface typeface) {
                            int i6;
                            if (Build.VERSION.SDK_INT >= 28 && (i6 = i4) != -1) {
                                typeface = Typeface.create(typeface, i6, (i5 & 2) != 0);
                            }
                            AppCompatTextHelper appCompatTextHelper = AppCompatTextHelper.this;
                            WeakReference weakReference2 = weakReference;
                            if (appCompatTextHelper.mAsyncFontPending) {
                                appCompatTextHelper.mFontTypeface = typeface;
                                TextView textView = (TextView) weakReference2.get();
                                if (textView != null) {
                                    AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                                    if (textView.isAttachedToWindow()) {
                                        textView.post(new Runnable(appCompatTextHelper, textView, typeface, appCompatTextHelper.mStyle) { // from class: androidx.appcompat.widget.AppCompatTextHelper.2
                                            public final /* synthetic */ int val$style;
                                            public final /* synthetic */ TextView val$textView;
                                            public final /* synthetic */ Typeface val$typeface;

                                            {
                                                this.val$textView = textView;
                                                this.val$typeface = typeface;
                                                this.val$style = r4;
                                            }

                                            @Override // java.lang.Runnable
                                            public void run() {
                                                this.val$textView.setTypeface(this.val$typeface, this.val$style);
                                            }
                                        });
                                    } else {
                                        textView.setTypeface(typeface, appCompatTextHelper.mStyle);
                                    }
                                }
                            }
                        }
                    });
                    if (font != null) {
                        if (i < 28 || this.mFontWeight == -1) {
                            this.mFontTypeface = font;
                        } else {
                            this.mFontTypeface = Typeface.create(Typeface.create(font, 0), this.mFontWeight, (this.mStyle & 2) != 0);
                        }
                    }
                    this.mAsyncFontPending = this.mFontTypeface == null;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.mFontTypeface == null && (string = tintTypedArray.getString(i3)) != null) {
                if (Build.VERSION.SDK_INT < 28 || this.mFontWeight == -1) {
                    this.mFontTypeface = Typeface.create(string, this.mStyle);
                    return;
                }
                Typeface create = Typeface.create(string, 0);
                int i6 = this.mFontWeight;
                if ((this.mStyle & 2) != 0) {
                    z = true;
                }
                this.mFontTypeface = Typeface.create(create, i6, z);
            }
        } else if (tintTypedArray.hasValue(1)) {
            this.mAsyncFontPending = false;
            int i7 = tintTypedArray.getInt(1, 1);
            if (i7 == 1) {
                this.mFontTypeface = Typeface.SANS_SERIF;
            } else if (i7 == 2) {
                this.mFontTypeface = Typeface.SERIF;
            } else if (i7 == 3) {
                this.mFontTypeface = Typeface.MONOSPACE;
            }
        }
    }
}
