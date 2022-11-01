package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.text.TextDirectionHeuristicCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.android.material.R$style;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.resources.CancelableFontCallback;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.tidalab.v2board.clash.foss.R;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {
    public ValueAnimator animator;
    public MaterialShapeDrawable boxBackground;
    public int boxBackgroundColor;
    public int boxBackgroundMode;
    public int boxCollapsedPaddingTopPx;
    public final int boxLabelCutoutPaddingPx;
    public int boxStrokeColor;
    public int boxStrokeWidthDefaultPx;
    public int boxStrokeWidthFocusedPx;
    public int boxStrokeWidthPx;
    public MaterialShapeDrawable boxUnderline;
    public final CollapsingTextHelper collapsingTextHelper;
    public boolean counterEnabled;
    public int counterMaxLength;
    public int counterOverflowTextAppearance;
    public ColorStateList counterOverflowTextColor;
    public boolean counterOverflowed;
    public int counterTextAppearance;
    public ColorStateList counterTextColor;
    public TextView counterView;
    public int defaultFilledBackgroundColor;
    public ColorStateList defaultHintTextColor;
    public int defaultStrokeColor;
    public int disabledColor;
    public int disabledFilledBackgroundColor;
    public EditText editText;
    public Drawable endDummyDrawable;
    public int endDummyDrawableWidth;
    public final SparseArray<EndIconDelegate> endIconDelegates;
    public final FrameLayout endIconFrame;
    public View.OnLongClickListener endIconOnLongClickListener;
    public ColorStateList endIconTintList;
    public PorterDuff.Mode endIconTintMode;
    public final CheckableImageButton endIconView;
    public final LinearLayout endLayout;
    public View.OnLongClickListener errorIconOnLongClickListener;
    public ColorStateList errorIconTintList;
    public final CheckableImageButton errorIconView;
    public boolean expandedHintEnabled;
    public int focusedFilledBackgroundColor;
    public int focusedStrokeColor;
    public ColorStateList focusedTextColor;
    public boolean hasEndIconTintList;
    public boolean hasEndIconTintMode;
    public boolean hasStartIconTintList;
    public boolean hasStartIconTintMode;
    public CharSequence hint;
    public boolean hintAnimationEnabled;
    public boolean hintEnabled;
    public boolean hintExpanded;
    public int hoveredFilledBackgroundColor;
    public int hoveredStrokeColor;
    public boolean inDrawableStateChanged;
    public final FrameLayout inputFrame;
    public boolean isProvidingHint;
    public Drawable originalEditTextEndDrawable;
    public CharSequence originalHint;
    public boolean placeholderEnabled;
    public CharSequence placeholderText;
    public int placeholderTextAppearance;
    public ColorStateList placeholderTextColor;
    public TextView placeholderTextView;
    public CharSequence prefixText;
    public final TextView prefixTextView;
    public boolean restoringSavedState;
    public ShapeAppearanceModel shapeAppearanceModel;
    public Drawable startDummyDrawable;
    public int startDummyDrawableWidth;
    public View.OnLongClickListener startIconOnLongClickListener;
    public ColorStateList startIconTintList;
    public PorterDuff.Mode startIconTintMode;
    public final CheckableImageButton startIconView;
    public final LinearLayout startLayout;
    public ColorStateList strokeErrorColor;
    public CharSequence suffixText;
    public final TextView suffixTextView;
    public Typeface typeface;
    public final IndicatorViewController indicatorViewController = new IndicatorViewController(this);
    public final Rect tmpRect = new Rect();
    public final Rect tmpBoundsRect = new Rect();
    public final RectF tmpRectF = new RectF();
    public final LinkedHashSet<OnEditTextAttachedListener> editTextAttachedListeners = new LinkedHashSet<>();
    public int endIconMode = 0;
    public final LinkedHashSet<OnEndIconChangedListener> endIconChangedListeners = new LinkedHashSet<>();

    /* loaded from: classes.dex */
    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        public final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout textInputLayout) {
            this.layout = textInputLayout;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
            EditText editText = this.layout.getEditText();
            Editable text = editText != null ? editText.getText() : null;
            CharSequence hint = this.layout.getHint();
            CharSequence error = this.layout.getError();
            CharSequence placeholderText = this.layout.getPlaceholderText();
            int counterMaxLength = this.layout.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.layout.getCounterOverflowDescription();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = !TextUtils.isEmpty(hint);
            boolean z3 = !this.layout.hintExpanded;
            boolean z4 = !TextUtils.isEmpty(error);
            boolean z5 = z4 || !TextUtils.isEmpty(counterOverflowDescription);
            String charSequence = z2 ? hint.toString() : HttpUrl.FRAGMENT_ENCODE_SET;
            if (z) {
                accessibilityNodeInfoCompat.mInfo.setText(text);
            } else if (!TextUtils.isEmpty(charSequence)) {
                accessibilityNodeInfoCompat.mInfo.setText(charSequence);
                if (z3 && placeholderText != null) {
                    accessibilityNodeInfoCompat.mInfo.setText(charSequence + ", " + ((Object) placeholderText));
                }
            } else if (placeholderText != null) {
                accessibilityNodeInfoCompat.mInfo.setText(placeholderText);
            }
            if (!TextUtils.isEmpty(charSequence)) {
                int i = Build.VERSION.SDK_INT;
                if (i >= 26) {
                    accessibilityNodeInfoCompat.setHintText(charSequence);
                } else {
                    if (z) {
                        charSequence = ((Object) text) + ", " + charSequence;
                    }
                    accessibilityNodeInfoCompat.mInfo.setText(charSequence);
                }
                boolean z6 = !z;
                if (i >= 26) {
                    accessibilityNodeInfoCompat.mInfo.setShowingHintText(z6);
                } else {
                    accessibilityNodeInfoCompat.setBooleanProperty(4, z6);
                }
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            accessibilityNodeInfoCompat.mInfo.setMaxTextLength(counterMaxLength);
            if (z5) {
                if (!z4) {
                    error = counterOverflowDescription;
                }
                accessibilityNodeInfoCompat.mInfo.setError(error);
            }
            if (editText != null) {
                editText.setLabelFor(R.id.textinput_helper_text);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface OnEditTextAttachedListener {
        void onEditTextAttached(TextInputLayout textInputLayout);
    }

    /* loaded from: classes.dex */
    public interface OnEndIconChangedListener {
        void onEndIconChanged(TextInputLayout textInputLayout, int i);
    }

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.textfield.TextInputLayout.SavedState.1
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        public CharSequence error;
        public CharSequence helperText;
        public CharSequence hintText;
        public boolean isEndIconChecked;
        public CharSequence placeholderText;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("TextInputLayout.SavedState{");
            outline13.append(Integer.toHexString(System.identityHashCode(this)));
            outline13.append(" error=");
            outline13.append((Object) this.error);
            outline13.append(" hint=");
            outline13.append((Object) this.hintText);
            outline13.append(" helperText=");
            outline13.append((Object) this.helperText);
            outline13.append(" placeholderText=");
            outline13.append((Object) this.placeholderText);
            outline13.append("}");
            return outline13.toString();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mSuperState, i);
            TextUtils.writeToParcel(this.error, parcel, i);
            parcel.writeInt(this.isEndIconChecked ? 1 : 0);
            TextUtils.writeToParcel(this.hintText, parcel, i);
            TextUtils.writeToParcel(this.helperText, parcel, i);
            TextUtils.writeToParcel(this.placeholderText, parcel, i);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.isEndIconChecked = parcel.readInt() != 1 ? false : true;
            this.hintText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.helperText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.placeholderText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x059f  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x05c1  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x05d2  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x05e3  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x056e  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x057d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x058e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TextInputLayout(android.content.Context r32, android.util.AttributeSet r33) {
        /*
            Method dump skipped, instructions count: 1580
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private EndIconDelegate getEndIconDelegate() {
        EndIconDelegate endIconDelegate = this.endIconDelegates.get(this.endIconMode);
        return endIconDelegate != null ? endIconDelegate : this.endIconDelegates.get(0);
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.errorIconView.getVisibility() == 0) {
            return this.errorIconView;
        }
        if (!hasEndIcon() || !isEndIconVisible()) {
            return null;
        }
        return this.endIconView;
    }

    public static void recursiveSetEnabled(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup) childAt, z);
            }
        }
    }

    private void setEditText(EditText editText) {
        if (this.editText == null) {
            if (this.endIconMode != 3 && !(editText instanceof TextInputEditText)) {
                Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
            }
            this.editText = editText;
            onApplyBoxBackgroundMode();
            setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
            this.collapsingTextHelper.setTypefaces(this.editText.getTypeface());
            CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
            float textSize = this.editText.getTextSize();
            if (collapsingTextHelper.expandedTextSize != textSize) {
                collapsingTextHelper.expandedTextSize = textSize;
                collapsingTextHelper.recalculate();
            }
            int gravity = this.editText.getGravity();
            this.collapsingTextHelper.setCollapsedTextGravity((gravity & (-113)) | 48);
            CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
            if (collapsingTextHelper2.expandedTextGravity != gravity) {
                collapsingTextHelper2.expandedTextGravity = gravity;
                collapsingTextHelper2.recalculate();
            }
            this.editText.addTextChangedListener(new TextWatcher() { // from class: com.google.android.material.textfield.TextInputLayout.1
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    TextInputLayout textInputLayout = TextInputLayout.this;
                    textInputLayout.updateLabelState(!textInputLayout.restoringSavedState, false);
                    TextInputLayout textInputLayout2 = TextInputLayout.this;
                    if (textInputLayout2.counterEnabled) {
                        textInputLayout2.updateCounter(editable.length());
                    }
                    TextInputLayout textInputLayout3 = TextInputLayout.this;
                    if (textInputLayout3.placeholderEnabled) {
                        textInputLayout3.updatePlaceholderText(editable.length());
                    }
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }
            });
            if (this.defaultHintTextColor == null) {
                this.defaultHintTextColor = this.editText.getHintTextColors();
            }
            if (this.hintEnabled) {
                if (TextUtils.isEmpty(this.hint)) {
                    CharSequence hint = this.editText.getHint();
                    this.originalHint = hint;
                    setHint(hint);
                    this.editText.setHint((CharSequence) null);
                }
                this.isProvidingHint = true;
            }
            if (this.counterView != null) {
                updateCounter(this.editText.getText().length());
            }
            updateEditTextBackground();
            this.indicatorViewController.adjustIndicatorPadding();
            this.startLayout.bringToFront();
            this.endLayout.bringToFront();
            this.endIconFrame.bringToFront();
            this.errorIconView.bringToFront();
            Iterator<OnEditTextAttachedListener> it = this.editTextAttachedListeners.iterator();
            while (it.hasNext()) {
                it.next().onEditTextAttached(this);
            }
            updatePrefixTextViewPadding();
            updateSuffixTextViewPadding();
            if (!isEnabled()) {
                editText.setEnabled(false);
            }
            updateLabelState(false, true);
            return;
        }
        throw new IllegalArgumentException("We already have an EditText, can only have one");
    }

    private void setErrorIconVisible(boolean z) {
        int i = 0;
        this.errorIconView.setVisibility(z ? 0 : 8);
        FrameLayout frameLayout = this.endIconFrame;
        if (z) {
            i = 8;
        }
        frameLayout.setVisibility(i);
        updateSuffixTextViewPadding();
        if (!hasEndIcon()) {
            updateDummyDrawables();
        }
    }

    private void setHintInternal(CharSequence charSequence) {
        if (!TextUtils.equals(charSequence, this.hint)) {
            this.hint = charSequence;
            CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
            if (charSequence == null || !TextUtils.equals(collapsingTextHelper.text, charSequence)) {
                collapsingTextHelper.text = charSequence;
                collapsingTextHelper.textToDraw = null;
                Bitmap bitmap = collapsingTextHelper.expandedTitleTexture;
                if (bitmap != null) {
                    bitmap.recycle();
                    collapsingTextHelper.expandedTitleTexture = null;
                }
                collapsingTextHelper.recalculate();
            }
            if (!this.hintExpanded) {
                openCutout();
            }
        }
    }

    public static void setIconClickable(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        boolean hasOnClickListeners = checkableImageButton.hasOnClickListeners();
        boolean z = false;
        int i = 1;
        boolean z2 = onLongClickListener != null;
        if (hasOnClickListeners || z2) {
            z = true;
        }
        checkableImageButton.setFocusable(z);
        checkableImageButton.setClickable(hasOnClickListeners);
        checkableImageButton.setPressable(hasOnClickListeners);
        checkableImageButton.setLongClickable(z2);
        if (!z) {
            i = 2;
        }
        checkableImageButton.setImportantForAccessibility(i);
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.placeholderEnabled != z) {
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null);
                this.placeholderTextView = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_placeholder);
                TextView textView = this.placeholderTextView;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                textView.setAccessibilityLiveRegion(1);
                setPlaceholderTextAppearance(this.placeholderTextAppearance);
                setPlaceholderTextColor(this.placeholderTextColor);
                TextView textView2 = this.placeholderTextView;
                if (textView2 != null) {
                    this.inputFrame.addView(textView2);
                    this.placeholderTextView.setVisibility(0);
                }
            } else {
                TextView textView3 = this.placeholderTextView;
                if (textView3 != null) {
                    textView3.setVisibility(8);
                }
                this.placeholderTextView = null;
            }
            this.placeholderEnabled = z;
        }
    }

    public void addOnEditTextAttachedListener(OnEditTextAttachedListener onEditTextAttachedListener) {
        this.editTextAttachedListeners.add(onEditTextAttachedListener);
        if (this.editText != null) {
            onEditTextAttachedListener.onEditTextAttached(this);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
            this.inputFrame.addView(view, layoutParams2);
            this.inputFrame.setLayoutParams(layoutParams);
            updateInputLayoutMargins();
            setEditText((EditText) view);
            return;
        }
        super.addView(view, i, layoutParams);
    }

    public void animateToExpansionFraction(float f) {
        if (this.collapsingTextHelper.expandedFraction != f) {
            if (this.animator == null) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.animator = valueAnimator;
                valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                this.animator.setDuration(167L);
                this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.TextInputLayout.4
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        TextInputLayout.this.collapsingTextHelper.setExpansionFraction(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                    }
                });
            }
            this.animator.setFloatValues(this.collapsingTextHelper.expandedFraction, f);
            this.animator.start();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void applyBoxAttributes() {
        /*
            r6 = this;
            com.google.android.material.shape.MaterialShapeDrawable r0 = r6.boxBackground
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            com.google.android.material.shape.ShapeAppearanceModel r1 = r6.shapeAppearanceModel
            r0.setShapeAppearanceModel(r1)
            int r0 = r6.boxBackgroundMode
            r1 = 2
            r2 = -1
            r3 = 0
            r4 = 1
            if (r0 != r1) goto L_0x0021
            int r0 = r6.boxStrokeWidthPx
            if (r0 <= r2) goto L_0x001c
            int r0 = r6.boxStrokeColor
            if (r0 == 0) goto L_0x001c
            r0 = 1
            goto L_0x001d
        L_0x001c:
            r0 = 0
        L_0x001d:
            if (r0 == 0) goto L_0x0021
            r0 = 1
            goto L_0x0022
        L_0x0021:
            r0 = 0
        L_0x0022:
            if (r0 == 0) goto L_0x002e
            com.google.android.material.shape.MaterialShapeDrawable r0 = r6.boxBackground
            int r1 = r6.boxStrokeWidthPx
            float r1 = (float) r1
            int r5 = r6.boxStrokeColor
            r0.setStroke(r1, r5)
        L_0x002e:
            int r0 = r6.boxBackgroundColor
            int r1 = r6.boxBackgroundMode
            if (r1 != r4) goto L_0x0045
            r0 = 2130968786(0x7f0400d2, float:1.7546235E38)
            android.content.Context r1 = r6.getContext()
            int r0 = com.google.android.material.R$style.getColor(r1, r0, r3)
            int r1 = r6.boxBackgroundColor
            int r0 = androidx.core.graphics.ColorUtils.compositeColors(r1, r0)
        L_0x0045:
            r6.boxBackgroundColor = r0
            com.google.android.material.shape.MaterialShapeDrawable r1 = r6.boxBackground
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)
            r1.setFillColor(r0)
            int r0 = r6.endIconMode
            r1 = 3
            if (r0 != r1) goto L_0x005e
            android.widget.EditText r0 = r6.editText
            android.graphics.drawable.Drawable r0 = r0.getBackground()
            r0.invalidateSelf()
        L_0x005e:
            com.google.android.material.shape.MaterialShapeDrawable r0 = r6.boxUnderline
            if (r0 != 0) goto L_0x0063
            goto L_0x007a
        L_0x0063:
            int r1 = r6.boxStrokeWidthPx
            if (r1 <= r2) goto L_0x006c
            int r1 = r6.boxStrokeColor
            if (r1 == 0) goto L_0x006c
            r3 = 1
        L_0x006c:
            if (r3 == 0) goto L_0x0077
            int r1 = r6.boxStrokeColor
            android.content.res.ColorStateList r1 = android.content.res.ColorStateList.valueOf(r1)
            r0.setFillColor(r1)
        L_0x0077:
            r6.invalidate()
        L_0x007a:
            r6.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.applyBoxAttributes():void");
    }

    public final void applyEndIconTint() {
        applyIconTint(this.endIconView, this.hasEndIconTintList, this.endIconTintList, this.hasEndIconTintMode, this.endIconTintMode);
    }

    public final void applyIconTint(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            drawable = AppOpsManagerCompat.wrap(drawable).mutate();
            if (z) {
                drawable.setTintList(colorStateList);
            }
            if (z2) {
                drawable.setTintMode(mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public final int calculateLabelMarginTop() {
        float collapsedTextHeight;
        if (!this.hintEnabled) {
            return 0;
        }
        int i = this.boxBackgroundMode;
        if (i == 0 || i == 1) {
            collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight();
        } else if (i != 2) {
            return 0;
        } else {
            collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f;
        }
        return (int) collapsedTextHeight;
    }

    public final boolean cutoutEnabled() {
        return this.hintEnabled && !TextUtils.isEmpty(this.hint) && (this.boxBackground instanceof CutoutDrawable);
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(26)
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText = this.editText;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        if (this.originalHint != null) {
            boolean z = this.isProvidingHint;
            this.isProvidingHint = false;
            CharSequence hint = editText.getHint();
            this.editText.setHint(this.originalHint);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i);
            } finally {
                this.editText.setHint(hint);
                this.isProvidingHint = z;
            }
        } else {
            viewStructure.setAutofillId(getAutofillId());
            onProvideAutofillStructure(viewStructure, i);
            onProvideAutofillVirtualStructure(viewStructure, i);
            viewStructure.setChildCount(this.inputFrame.getChildCount());
            for (int i2 = 0; i2 < this.inputFrame.getChildCount(); i2++) {
                View childAt = this.inputFrame.getChildAt(i2);
                ViewStructure newChild = viewStructure.newChild(i2);
                childAt.dispatchProvideAutofillStructure(newChild, i);
                if (childAt == this.editText) {
                    newChild.setHint(getHint());
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.restoringSavedState = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.hintEnabled) {
            CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
            Objects.requireNonNull(collapsingTextHelper);
            int save = canvas.save();
            if (collapsingTextHelper.textToDraw != null && collapsingTextHelper.drawTitle) {
                collapsingTextHelper.textLayout.getLineLeft(0);
                collapsingTextHelper.textPaint.setTextSize(collapsingTextHelper.currentTextSize);
                float f = collapsingTextHelper.currentDrawX;
                float f2 = collapsingTextHelper.currentDrawY;
                float f3 = collapsingTextHelper.scale;
                if (f3 != 1.0f) {
                    canvas.scale(f3, f3, f, f2);
                }
                canvas.translate(f, f2);
                collapsingTextHelper.textLayout.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        MaterialShapeDrawable materialShapeDrawable = this.boxUnderline;
        if (materialShapeDrawable != null) {
            Rect bounds = materialShapeDrawable.getBounds();
            bounds.top = bounds.bottom - this.boxStrokeWidthPx;
            this.boxUnderline.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        boolean z;
        boolean z2;
        ColorStateList colorStateList;
        if (!this.inDrawableStateChanged) {
            boolean z3 = true;
            this.inDrawableStateChanged = true;
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
            if (collapsingTextHelper != null) {
                collapsingTextHelper.state = drawableState;
                ColorStateList colorStateList2 = collapsingTextHelper.collapsedTextColor;
                if ((colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = collapsingTextHelper.expandedTextColor) != null && colorStateList.isStateful())) {
                    collapsingTextHelper.recalculate();
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = z2 | false;
            } else {
                z = false;
            }
            if (this.editText != null) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (!isLaidOut() || !isEnabled()) {
                    z3 = false;
                }
                updateLabelState(z3, false);
            }
            updateEditTextBackground();
            updateTextInputBoxState();
            if (z) {
                invalidate();
            }
            this.inDrawableStateChanged = false;
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.editText;
        if (editText == null) {
            return super.getBaseline();
        }
        return calculateLabelMarginTop() + getPaddingTop() + editText.getBaseline();
    }

    public MaterialShapeDrawable getBoxBackground() {
        int i = this.boxBackgroundMode;
        if (i == 1 || i == 2) {
            return this.boxBackground;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.boxBackgroundColor;
    }

    public int getBoxBackgroundMode() {
        return this.boxBackgroundMode;
    }

    public float getBoxCornerRadiusBottomEnd() {
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        return materialShapeDrawable.drawableState.shapeAppearanceModel.bottomLeftCornerSize.getCornerSize(materialShapeDrawable.getBoundsAsRectF());
    }

    public float getBoxCornerRadiusBottomStart() {
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        return materialShapeDrawable.drawableState.shapeAppearanceModel.bottomRightCornerSize.getCornerSize(materialShapeDrawable.getBoundsAsRectF());
    }

    public float getBoxCornerRadiusTopEnd() {
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        return materialShapeDrawable.drawableState.shapeAppearanceModel.topRightCornerSize.getCornerSize(materialShapeDrawable.getBoundsAsRectF());
    }

    public float getBoxCornerRadiusTopStart() {
        return this.boxBackground.getTopLeftCornerResolvedSize();
    }

    public int getBoxStrokeColor() {
        return this.focusedStrokeColor;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.strokeErrorColor;
    }

    public int getBoxStrokeWidth() {
        return this.boxStrokeWidthDefaultPx;
    }

    public int getBoxStrokeWidthFocused() {
        return this.boxStrokeWidthFocusedPx;
    }

    public int getCounterMaxLength() {
        return this.counterMaxLength;
    }

    public CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (!this.counterEnabled || !this.counterOverflowed || (textView = this.counterView) == null) {
            return null;
        }
        return textView.getContentDescription();
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.counterTextColor;
    }

    public ColorStateList getCounterTextColor() {
        return this.counterTextColor;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.defaultHintTextColor;
    }

    public EditText getEditText() {
        return this.editText;
    }

    public CharSequence getEndIconContentDescription() {
        return this.endIconView.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.endIconView.getDrawable();
    }

    public int getEndIconMode() {
        return this.endIconMode;
    }

    public CheckableImageButton getEndIconView() {
        return this.endIconView;
    }

    public CharSequence getError() {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        if (indicatorViewController.errorEnabled) {
            return indicatorViewController.errorText;
        }
        return null;
    }

    public CharSequence getErrorContentDescription() {
        return this.indicatorViewController.errorViewContentDescription;
    }

    public int getErrorCurrentTextColors() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public Drawable getErrorIconDrawable() {
        return this.errorIconView.getDrawable();
    }

    public final int getErrorTextCurrentColor() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public CharSequence getHelperText() {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        if (indicatorViewController.helperTextEnabled) {
            return indicatorViewController.helperText;
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        TextView textView = this.indicatorViewController.helperTextView;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHint() {
        if (this.hintEnabled) {
            return this.hint;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    public final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.getCurrentCollapsedTextColor();
    }

    public ColorStateList getHintTextColor() {
        return this.focusedTextColor;
    }

    public final int getLabelLeftBoundAlightWithPrefix(int i, boolean z) {
        int compoundPaddingLeft = this.editText.getCompoundPaddingLeft() + i;
        return (this.prefixText == null || z) ? compoundPaddingLeft : (compoundPaddingLeft - this.prefixTextView.getMeasuredWidth()) + this.prefixTextView.getPaddingLeft();
    }

    public final int getLabelRightBoundAlignedWithSuffix(int i, boolean z) {
        int compoundPaddingRight = i - this.editText.getCompoundPaddingRight();
        return (this.prefixText == null || !z) ? compoundPaddingRight : compoundPaddingRight + (this.prefixTextView.getMeasuredWidth() - this.prefixTextView.getPaddingRight());
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.endIconView.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.endIconView.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.placeholderEnabled) {
            return this.placeholderText;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.placeholderTextAppearance;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.placeholderTextColor;
    }

    public CharSequence getPrefixText() {
        return this.prefixText;
    }

    public ColorStateList getPrefixTextColor() {
        return this.prefixTextView.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.prefixTextView;
    }

    public CharSequence getStartIconContentDescription() {
        return this.startIconView.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.startIconView.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.suffixText;
    }

    public ColorStateList getSuffixTextColor() {
        return this.suffixTextView.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.suffixTextView;
    }

    public Typeface getTypeface() {
        return this.typeface;
    }

    public final boolean hasEndIcon() {
        return this.endIconMode != 0;
    }

    public boolean isEndIconVisible() {
        return this.endIconFrame.getVisibility() == 0 && this.endIconView.getVisibility() == 0;
    }

    public final void onApplyBoxBackgroundMode() {
        int i = this.boxBackgroundMode;
        if (i == 0) {
            this.boxBackground = null;
            this.boxUnderline = null;
        } else if (i == 1) {
            this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.boxUnderline = new MaterialShapeDrawable();
        } else if (i == 2) {
            if (!this.hintEnabled || (this.boxBackground instanceof CutoutDrawable)) {
                this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            } else {
                this.boxBackground = new CutoutDrawable(this.shapeAppearanceModel);
            }
            this.boxUnderline = null;
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline10(new StringBuilder(), this.boxBackgroundMode, " is illegal; only @BoxBackgroundMode constants are supported."));
        }
        EditText editText = this.editText;
        if ((editText == null || this.boxBackground == null || editText.getBackground() != null || this.boxBackgroundMode == 0) ? false : true) {
            EditText editText2 = this.editText;
            MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            editText2.setBackground(materialShapeDrawable);
        }
        updateTextInputBoxState();
        if (this.boxBackgroundMode == 1) {
            if (R$style.isFontScaleAtLeast2_0(getContext())) {
                this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(R.dimen.material_font_2_0_box_collapsed_padding_top);
            } else if (R$style.isFontScaleAtLeast1_3(getContext())) {
                this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(R.dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
        if (this.editText != null && this.boxBackgroundMode == 1) {
            if (R$style.isFontScaleAtLeast2_0(getContext())) {
                EditText editText3 = this.editText;
                AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                editText3.setPaddingRelative(editText3.getPaddingStart(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_top), this.editText.getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_bottom));
            } else if (R$style.isFontScaleAtLeast1_3(getContext())) {
                EditText editText4 = this.editText;
                AtomicInteger atomicInteger3 = ViewCompat.sNextGeneratedId;
                editText4.setPaddingRelative(editText4.getPaddingStart(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_top), this.editText.getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
        if (this.boxBackgroundMode != 0) {
            updateInputLayoutMargins();
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.editText;
        if (editText != null) {
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(this, editText, rect);
            MaterialShapeDrawable materialShapeDrawable = this.boxUnderline;
            if (materialShapeDrawable != null) {
                int i7 = rect.bottom;
                materialShapeDrawable.setBounds(rect.left, i7 - this.boxStrokeWidthFocusedPx, rect.right, i7);
            }
            if (this.hintEnabled) {
                CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
                float textSize = this.editText.getTextSize();
                if (collapsingTextHelper.expandedTextSize != textSize) {
                    collapsingTextHelper.expandedTextSize = textSize;
                    collapsingTextHelper.recalculate();
                }
                int gravity = this.editText.getGravity();
                this.collapsingTextHelper.setCollapsedTextGravity((gravity & (-113)) | 48);
                CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
                if (collapsingTextHelper2.expandedTextGravity != gravity) {
                    collapsingTextHelper2.expandedTextGravity = gravity;
                    collapsingTextHelper2.recalculate();
                }
                CollapsingTextHelper collapsingTextHelper3 = this.collapsingTextHelper;
                if (this.editText != null) {
                    Rect rect2 = this.tmpBoundsRect;
                    AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                    boolean z2 = false;
                    boolean z3 = getLayoutDirection() == 1;
                    rect2.bottom = rect.bottom;
                    int i8 = this.boxBackgroundMode;
                    if (i8 == 1) {
                        rect2.left = getLabelLeftBoundAlightWithPrefix(rect.left, z3);
                        rect2.top = rect.top + this.boxCollapsedPaddingTopPx;
                        rect2.right = getLabelRightBoundAlignedWithSuffix(rect.right, z3);
                    } else if (i8 != 2) {
                        rect2.left = getLabelLeftBoundAlightWithPrefix(rect.left, z3);
                        rect2.top = getPaddingTop();
                        rect2.right = getLabelRightBoundAlignedWithSuffix(rect.right, z3);
                    } else {
                        rect2.left = this.editText.getPaddingLeft() + rect.left;
                        rect2.top = rect.top - calculateLabelMarginTop();
                        rect2.right = rect.right - this.editText.getPaddingRight();
                    }
                    Objects.requireNonNull(collapsingTextHelper3);
                    int i9 = rect2.left;
                    int i10 = rect2.top;
                    int i11 = rect2.right;
                    int i12 = rect2.bottom;
                    if (!CollapsingTextHelper.rectEquals(collapsingTextHelper3.collapsedBounds, i9, i10, i11, i12)) {
                        collapsingTextHelper3.collapsedBounds.set(i9, i10, i11, i12);
                        collapsingTextHelper3.boundsChanged = true;
                        collapsingTextHelper3.onBoundsChanged();
                    }
                    CollapsingTextHelper collapsingTextHelper4 = this.collapsingTextHelper;
                    if (this.editText != null) {
                        Rect rect3 = this.tmpBoundsRect;
                        TextPaint textPaint = collapsingTextHelper4.tmpPaint;
                        textPaint.setTextSize(collapsingTextHelper4.expandedTextSize);
                        textPaint.setTypeface(collapsingTextHelper4.expandedTypeface);
                        textPaint.setLetterSpacing(0.0f);
                        float f = -collapsingTextHelper4.tmpPaint.ascent();
                        rect3.left = this.editText.getCompoundPaddingLeft() + rect.left;
                        if (this.boxBackgroundMode == 1 && this.editText.getMinLines() <= 1) {
                            i5 = (int) (rect.centerY() - (f / 2.0f));
                        } else {
                            i5 = rect.top + this.editText.getCompoundPaddingTop();
                        }
                        rect3.top = i5;
                        rect3.right = rect.right - this.editText.getCompoundPaddingRight();
                        if (this.boxBackgroundMode == 1 && this.editText.getMinLines() <= 1) {
                            z2 = true;
                        }
                        if (z2) {
                            i6 = (int) (rect3.top + f);
                        } else {
                            i6 = rect.bottom - this.editText.getCompoundPaddingBottom();
                        }
                        rect3.bottom = i6;
                        int i13 = rect3.left;
                        int i14 = rect3.top;
                        int i15 = rect3.right;
                        if (!CollapsingTextHelper.rectEquals(collapsingTextHelper4.expandedBounds, i13, i14, i15, i6)) {
                            collapsingTextHelper4.expandedBounds.set(i13, i14, i15, i6);
                            collapsingTextHelper4.boundsChanged = true;
                            collapsingTextHelper4.onBoundsChanged();
                        }
                        this.collapsingTextHelper.recalculate();
                        if (cutoutEnabled() && !this.hintExpanded) {
                            openCutout();
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException();
                }
                throw new IllegalStateException();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        EditText editText;
        int max;
        super.onMeasure(i, i2);
        boolean z = false;
        if (this.editText != null && this.editText.getMeasuredHeight() < (max = Math.max(this.endLayout.getMeasuredHeight(), this.startLayout.getMeasuredHeight()))) {
            this.editText.setMinimumHeight(max);
            z = true;
        }
        boolean updateDummyDrawables = updateDummyDrawables();
        if (z || updateDummyDrawables) {
            this.editText.post(new Runnable() { // from class: com.google.android.material.textfield.TextInputLayout.3
                @Override // java.lang.Runnable
                public void run() {
                    TextInputLayout.this.editText.requestLayout();
                }
            });
        }
        if (!(this.placeholderTextView == null || (editText = this.editText) == null)) {
            this.placeholderTextView.setGravity(editText.getGravity());
            this.placeholderTextView.setPadding(this.editText.getCompoundPaddingLeft(), this.editText.getCompoundPaddingTop(), this.editText.getCompoundPaddingRight(), this.editText.getCompoundPaddingBottom());
        }
        updatePrefixTextViewPadding();
        updateSuffixTextViewPadding();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        setError(savedState.error);
        if (savedState.isEndIconChecked) {
            this.endIconView.post(new Runnable() { // from class: com.google.android.material.textfield.TextInputLayout.2
                @Override // java.lang.Runnable
                public void run() {
                    TextInputLayout.this.endIconView.performClick();
                    TextInputLayout.this.endIconView.jumpDrawablesToCurrentState();
                }
            });
        }
        setHint(savedState.hintText);
        setHelperText(savedState.helperText);
        setPlaceholderText(savedState.placeholderText);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.indicatorViewController.errorShouldBeShown()) {
            savedState.error = getError();
        }
        savedState.isEndIconChecked = hasEndIcon() && this.endIconView.isChecked();
        savedState.hintText = getHint();
        savedState.helperText = getHelperText();
        savedState.placeholderText = getPlaceholderText();
        return savedState;
    }

    public final void openCutout() {
        float f;
        float f2;
        int i;
        float f3;
        float f4;
        float f5;
        int i2;
        if (cutoutEnabled()) {
            RectF rectF = this.tmpRectF;
            CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
            int width = this.editText.getWidth();
            int gravity = this.editText.getGravity();
            boolean calculateIsRtl = collapsingTextHelper.calculateIsRtl(collapsingTextHelper.text);
            collapsingTextHelper.isRtl = calculateIsRtl;
            if (gravity == 17 || (gravity & 7) == 1) {
                f5 = width / 2.0f;
                f4 = collapsingTextHelper.calculateCollapsedTextWidth() / 2.0f;
            } else {
                if ((gravity & 8388613) == 8388613 || (gravity & 5) == 5) {
                    if (calculateIsRtl) {
                        i2 = collapsingTextHelper.collapsedBounds.left;
                        f = i2;
                    } else {
                        f5 = collapsingTextHelper.collapsedBounds.right;
                        f4 = collapsingTextHelper.calculateCollapsedTextWidth();
                    }
                } else if (calculateIsRtl) {
                    f5 = collapsingTextHelper.collapsedBounds.right;
                    f4 = collapsingTextHelper.calculateCollapsedTextWidth();
                } else {
                    i2 = collapsingTextHelper.collapsedBounds.left;
                    f = i2;
                }
                rectF.left = f;
                Rect rect = collapsingTextHelper.collapsedBounds;
                rectF.top = rect.top;
                if (gravity != 17 || (gravity & 7) == 1) {
                    f2 = (width / 2.0f) + (collapsingTextHelper.calculateCollapsedTextWidth() / 2.0f);
                } else if ((gravity & 8388613) == 8388613 || (gravity & 5) == 5) {
                    if (collapsingTextHelper.isRtl) {
                        f3 = collapsingTextHelper.calculateCollapsedTextWidth();
                        f2 = f3 + f;
                    } else {
                        i = rect.right;
                        f2 = i;
                    }
                } else if (collapsingTextHelper.isRtl) {
                    i = rect.right;
                    f2 = i;
                } else {
                    f3 = collapsingTextHelper.calculateCollapsedTextWidth();
                    f2 = f3 + f;
                }
                rectF.right = f2;
                float collapsedTextHeight = collapsingTextHelper.getCollapsedTextHeight() + collapsingTextHelper.collapsedBounds.top;
                rectF.bottom = collapsedTextHeight;
                float f6 = rectF.left;
                float f7 = this.boxLabelCutoutPaddingPx;
                rectF.left = f6 - f7;
                rectF.top -= f7;
                rectF.right += f7;
                rectF.bottom = collapsedTextHeight + f7;
                rectF.offset(-getPaddingLeft(), -getPaddingTop());
                CutoutDrawable cutoutDrawable = (CutoutDrawable) this.boxBackground;
                Objects.requireNonNull(cutoutDrawable);
                cutoutDrawable.setCutout(rectF.left, rectF.top, rectF.right, rectF.bottom);
            }
            f = f5 - f4;
            rectF.left = f;
            Rect rect2 = collapsingTextHelper.collapsedBounds;
            rectF.top = rect2.top;
            if (gravity != 17) {
            }
            f2 = (width / 2.0f) + (collapsingTextHelper.calculateCollapsedTextWidth() / 2.0f);
            rectF.right = f2;
            float collapsedTextHeight2 = collapsingTextHelper.getCollapsedTextHeight() + collapsingTextHelper.collapsedBounds.top;
            rectF.bottom = collapsedTextHeight2;
            float f62 = rectF.left;
            float f72 = this.boxLabelCutoutPaddingPx;
            rectF.left = f62 - f72;
            rectF.top -= f72;
            rectF.right += f72;
            rectF.bottom = collapsedTextHeight2 + f72;
            rectF.offset(-getPaddingLeft(), -getPaddingTop());
            CutoutDrawable cutoutDrawable2 = (CutoutDrawable) this.boxBackground;
            Objects.requireNonNull(cutoutDrawable2);
            cutoutDrawable2.setCutout(rectF.left, rectF.top, rectF.right, rectF.bottom);
        }
    }

    public void refreshEndIconDrawableState() {
        refreshIconDrawableState(this.endIconView, this.endIconTintList);
    }

    public final void refreshIconDrawableState(CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() != null && colorStateList != null && colorStateList.isStateful()) {
            int[] drawableState = getDrawableState();
            int[] drawableState2 = checkableImageButton.getDrawableState();
            int length = drawableState.length;
            int[] copyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
            System.arraycopy(drawableState2, 0, copyOf, length, drawableState2.length);
            int colorForState = colorStateList.getColorForState(copyOf, colorStateList.getDefaultColor());
            Drawable mutate = AppOpsManagerCompat.wrap(drawable).mutate();
            mutate.setTintList(ColorStateList.valueOf(colorForState));
            checkableImageButton.setImageDrawable(mutate);
        }
    }

    public void setBoxBackgroundColor(int i) {
        if (this.boxBackgroundColor != i) {
            this.boxBackgroundColor = i;
            this.defaultFilledBackgroundColor = i;
            this.focusedFilledBackgroundColor = i;
            this.hoveredFilledBackgroundColor = i;
            applyBoxAttributes();
        }
    }

    public void setBoxBackgroundColorResource(int i) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), i));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.defaultFilledBackgroundColor = defaultColor;
        this.boxBackgroundColor = defaultColor;
        this.disabledFilledBackgroundColor = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.focusedFilledBackgroundColor = colorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
        this.hoveredFilledBackgroundColor = colorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
        applyBoxAttributes();
    }

    public void setBoxBackgroundMode(int i) {
        if (i != this.boxBackgroundMode) {
            this.boxBackgroundMode = i;
            if (this.editText != null) {
                onApplyBoxBackgroundMode();
            }
        }
    }

    public void setBoxStrokeColor(int i) {
        if (this.focusedStrokeColor != i) {
            this.focusedStrokeColor = i;
            updateTextInputBoxState();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.defaultStrokeColor = colorStateList.getDefaultColor();
            this.disabledColor = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.hoveredStrokeColor = colorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
            this.focusedStrokeColor = colorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
        } else if (this.focusedStrokeColor != colorStateList.getDefaultColor()) {
            this.focusedStrokeColor = colorStateList.getDefaultColor();
        }
        updateTextInputBoxState();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.strokeErrorColor != colorStateList) {
            this.strokeErrorColor = colorStateList;
            updateTextInputBoxState();
        }
    }

    public void setBoxStrokeWidth(int i) {
        this.boxStrokeWidthDefaultPx = i;
        updateTextInputBoxState();
    }

    public void setBoxStrokeWidthFocused(int i) {
        this.boxStrokeWidthFocusedPx = i;
        updateTextInputBoxState();
    }

    public void setBoxStrokeWidthFocusedResource(int i) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i));
    }

    public void setBoxStrokeWidthResource(int i) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public void setCounterEnabled(boolean z) {
        if (this.counterEnabled != z) {
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null);
                this.counterView = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_counter);
                Typeface typeface = this.typeface;
                if (typeface != null) {
                    this.counterView.setTypeface(typeface);
                }
                this.counterView.setMaxLines(1);
                this.indicatorViewController.addIndicator(this.counterView, 2);
                ((ViewGroup.MarginLayoutParams) this.counterView.getLayoutParams()).setMarginStart(getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_counter_margin_start));
                updateCounterTextAppearanceAndColor();
                updateCounter();
            } else {
                this.indicatorViewController.removeIndicator(this.counterView, 2);
                this.counterView = null;
            }
            this.counterEnabled = z;
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.counterMaxLength != i) {
            if (i > 0) {
                this.counterMaxLength = i;
            } else {
                this.counterMaxLength = -1;
            }
            if (this.counterEnabled) {
                updateCounter();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.counterOverflowTextAppearance != i) {
            this.counterOverflowTextAppearance = i;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.counterOverflowTextColor != colorStateList) {
            this.counterOverflowTextColor = colorStateList;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.counterTextAppearance != i) {
            this.counterTextAppearance = i;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.counterTextColor != colorStateList) {
            this.counterTextColor = colorStateList;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.defaultHintTextColor = colorStateList;
        this.focusedTextColor = colorStateList;
        if (this.editText != null) {
            updateLabelState(false, false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        recursiveSetEnabled(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.endIconView.setActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.endIconView.setCheckable(z);
    }

    public void setEndIconContentDescription(int i) {
        setEndIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setEndIconDrawable(int i) {
        setEndIconDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setEndIconMode(int i) {
        int i2 = this.endIconMode;
        this.endIconMode = i;
        Iterator<OnEndIconChangedListener> it = this.endIconChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().onEndIconChanged(this, i2);
        }
        setEndIconVisible(i != 0);
        if (getEndIconDelegate().isBoxBackgroundModeSupported(this.boxBackgroundMode)) {
            getEndIconDelegate().initialize();
            applyEndIconTint();
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("The current box background mode ");
        outline13.append(this.boxBackgroundMode);
        outline13.append(" is not supported by the end icon mode ");
        outline13.append(i);
        throw new IllegalStateException(outline13.toString());
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        CheckableImageButton checkableImageButton = this.endIconView;
        View.OnLongClickListener onLongClickListener = this.endIconOnLongClickListener;
        checkableImageButton.setOnClickListener(onClickListener);
        setIconClickable(checkableImageButton, onLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.endIconOnLongClickListener = onLongClickListener;
        CheckableImageButton checkableImageButton = this.endIconView;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        setIconClickable(checkableImageButton, onLongClickListener);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.endIconTintList != colorStateList) {
            this.endIconTintList = colorStateList;
            this.hasEndIconTintList = true;
            applyEndIconTint();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.endIconTintMode != mode) {
            this.endIconTintMode = mode;
            this.hasEndIconTintMode = true;
            applyEndIconTint();
        }
    }

    public void setEndIconVisible(boolean z) {
        if (isEndIconVisible() != z) {
            this.endIconView.setVisibility(z ? 0 : 8);
            updateSuffixTextViewPadding();
            updateDummyDrawables();
        }
    }

    public void setError(CharSequence charSequence) {
        if (!this.indicatorViewController.errorEnabled) {
            if (!TextUtils.isEmpty(charSequence)) {
                setErrorEnabled(true);
            } else {
                return;
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            IndicatorViewController indicatorViewController = this.indicatorViewController;
            indicatorViewController.cancelCaptionAnimator();
            indicatorViewController.errorText = charSequence;
            indicatorViewController.errorView.setText(charSequence);
            int i = indicatorViewController.captionDisplayed;
            if (i != 1) {
                indicatorViewController.captionToShow = 1;
            }
            indicatorViewController.updateCaptionViewsVisibility(i, indicatorViewController.captionToShow, indicatorViewController.shouldAnimateCaptionView(indicatorViewController.errorView, charSequence));
            return;
        }
        this.indicatorViewController.hideError();
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        indicatorViewController.errorViewContentDescription = charSequence;
        TextView textView = indicatorViewController.errorView;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    public void setErrorEnabled(boolean z) {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        if (indicatorViewController.errorEnabled != z) {
            indicatorViewController.cancelCaptionAnimator();
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(indicatorViewController.context, null);
                indicatorViewController.errorView = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_error);
                indicatorViewController.errorView.setTextAlignment(5);
                Typeface typeface = indicatorViewController.typeface;
                if (typeface != null) {
                    indicatorViewController.errorView.setTypeface(typeface);
                }
                int i = indicatorViewController.errorTextAppearance;
                indicatorViewController.errorTextAppearance = i;
                TextView textView = indicatorViewController.errorView;
                if (textView != null) {
                    indicatorViewController.textInputView.setTextAppearanceCompatWithErrorFallback(textView, i);
                }
                ColorStateList colorStateList = indicatorViewController.errorViewTextColor;
                indicatorViewController.errorViewTextColor = colorStateList;
                TextView textView2 = indicatorViewController.errorView;
                if (!(textView2 == null || colorStateList == null)) {
                    textView2.setTextColor(colorStateList);
                }
                CharSequence charSequence = indicatorViewController.errorViewContentDescription;
                indicatorViewController.errorViewContentDescription = charSequence;
                TextView textView3 = indicatorViewController.errorView;
                if (textView3 != null) {
                    textView3.setContentDescription(charSequence);
                }
                indicatorViewController.errorView.setVisibility(4);
                TextView textView4 = indicatorViewController.errorView;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                textView4.setAccessibilityLiveRegion(1);
                indicatorViewController.addIndicator(indicatorViewController.errorView, 0);
            } else {
                indicatorViewController.hideError();
                indicatorViewController.removeIndicator(indicatorViewController.errorView, 0);
                indicatorViewController.errorView = null;
                indicatorViewController.textInputView.updateEditTextBackground();
                indicatorViewController.textInputView.updateTextInputBoxState();
            }
            indicatorViewController.errorEnabled = z;
        }
    }

    public void setErrorIconDrawable(int i) {
        setErrorIconDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
        refreshIconDrawableState(this.errorIconView, this.errorIconTintList);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        CheckableImageButton checkableImageButton = this.errorIconView;
        View.OnLongClickListener onLongClickListener = this.errorIconOnLongClickListener;
        checkableImageButton.setOnClickListener(onClickListener);
        setIconClickable(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.errorIconOnLongClickListener = onLongClickListener;
        CheckableImageButton checkableImageButton = this.errorIconView;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        setIconClickable(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        this.errorIconTintList = colorStateList;
        Drawable drawable = this.errorIconView.getDrawable();
        if (drawable != null) {
            drawable = AppOpsManagerCompat.wrap(drawable).mutate();
            drawable.setTintList(colorStateList);
        }
        if (this.errorIconView.getDrawable() != drawable) {
            this.errorIconView.setImageDrawable(drawable);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.errorIconView.getDrawable();
        if (drawable != null) {
            drawable = AppOpsManagerCompat.wrap(drawable).mutate();
            drawable.setTintMode(mode);
        }
        if (this.errorIconView.getDrawable() != drawable) {
            this.errorIconView.setImageDrawable(drawable);
        }
    }

    public void setErrorTextAppearance(int i) {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        indicatorViewController.errorTextAppearance = i;
        TextView textView = indicatorViewController.errorView;
        if (textView != null) {
            indicatorViewController.textInputView.setTextAppearanceCompatWithErrorFallback(textView, i);
        }
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        indicatorViewController.errorViewTextColor = colorStateList;
        TextView textView = indicatorViewController.errorView;
        if (textView != null && colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setExpandedHintEnabled(boolean z) {
        if (this.expandedHintEnabled != z) {
            this.expandedHintEnabled = z;
            updateLabelState(false, false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (!this.indicatorViewController.helperTextEnabled) {
                setHelperTextEnabled(true);
            }
            IndicatorViewController indicatorViewController = this.indicatorViewController;
            indicatorViewController.cancelCaptionAnimator();
            indicatorViewController.helperText = charSequence;
            indicatorViewController.helperTextView.setText(charSequence);
            int i = indicatorViewController.captionDisplayed;
            if (i != 2) {
                indicatorViewController.captionToShow = 2;
            }
            indicatorViewController.updateCaptionViewsVisibility(i, indicatorViewController.captionToShow, indicatorViewController.shouldAnimateCaptionView(indicatorViewController.helperTextView, charSequence));
        } else if (this.indicatorViewController.helperTextEnabled) {
            setHelperTextEnabled(false);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        indicatorViewController.helperTextViewTextColor = colorStateList;
        TextView textView = indicatorViewController.helperTextView;
        if (textView != null && colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setHelperTextEnabled(boolean z) {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        if (indicatorViewController.helperTextEnabled != z) {
            indicatorViewController.cancelCaptionAnimator();
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(indicatorViewController.context, null);
                indicatorViewController.helperTextView = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_helper_text);
                indicatorViewController.helperTextView.setTextAlignment(5);
                Typeface typeface = indicatorViewController.typeface;
                if (typeface != null) {
                    indicatorViewController.helperTextView.setTypeface(typeface);
                }
                indicatorViewController.helperTextView.setVisibility(4);
                TextView textView = indicatorViewController.helperTextView;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                textView.setAccessibilityLiveRegion(1);
                int i = indicatorViewController.helperTextTextAppearance;
                indicatorViewController.helperTextTextAppearance = i;
                TextView textView2 = indicatorViewController.helperTextView;
                if (textView2 != null) {
                    AppOpsManagerCompat.setTextAppearance(textView2, i);
                }
                ColorStateList colorStateList = indicatorViewController.helperTextViewTextColor;
                indicatorViewController.helperTextViewTextColor = colorStateList;
                TextView textView3 = indicatorViewController.helperTextView;
                if (!(textView3 == null || colorStateList == null)) {
                    textView3.setTextColor(colorStateList);
                }
                indicatorViewController.addIndicator(indicatorViewController.helperTextView, 1);
            } else {
                indicatorViewController.cancelCaptionAnimator();
                int i2 = indicatorViewController.captionDisplayed;
                if (i2 == 2) {
                    indicatorViewController.captionToShow = 0;
                }
                indicatorViewController.updateCaptionViewsVisibility(i2, indicatorViewController.captionToShow, indicatorViewController.shouldAnimateCaptionView(indicatorViewController.helperTextView, null));
                indicatorViewController.removeIndicator(indicatorViewController.helperTextView, 1);
                indicatorViewController.helperTextView = null;
                indicatorViewController.textInputView.updateEditTextBackground();
                indicatorViewController.textInputView.updateTextInputBoxState();
            }
            indicatorViewController.helperTextEnabled = z;
        }
    }

    public void setHelperTextTextAppearance(int i) {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        indicatorViewController.helperTextTextAppearance = i;
        TextView textView = indicatorViewController.helperTextView;
        if (textView != null) {
            AppOpsManagerCompat.setTextAppearance(textView, i);
        }
    }

    public void setHint(CharSequence charSequence) {
        if (this.hintEnabled) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.hintAnimationEnabled = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.hintEnabled) {
            this.hintEnabled = z;
            if (!z) {
                this.isProvidingHint = false;
                if (!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint())) {
                    this.editText.setHint(this.hint);
                }
                setHintInternal(null);
            } else {
                CharSequence hint = this.editText.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.hint)) {
                        setHint(hint);
                    }
                    this.editText.setHint((CharSequence) null);
                }
                this.isProvidingHint = true;
            }
            if (this.editText != null) {
                updateInputLayoutMargins();
            }
        }
    }

    public void setHintTextAppearance(int i) {
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        TextAppearance textAppearance = new TextAppearance(collapsingTextHelper.view.getContext(), i);
        ColorStateList colorStateList = textAppearance.textColor;
        if (colorStateList != null) {
            collapsingTextHelper.collapsedTextColor = colorStateList;
        }
        float f = textAppearance.textSize;
        if (f != 0.0f) {
            collapsingTextHelper.collapsedTextSize = f;
        }
        ColorStateList colorStateList2 = textAppearance.shadowColor;
        if (colorStateList2 != null) {
            collapsingTextHelper.collapsedShadowColor = colorStateList2;
        }
        collapsingTextHelper.collapsedShadowDx = textAppearance.shadowDx;
        collapsingTextHelper.collapsedShadowDy = textAppearance.shadowDy;
        collapsingTextHelper.collapsedShadowRadius = textAppearance.shadowRadius;
        collapsingTextHelper.collapsedLetterSpacing = textAppearance.letterSpacing;
        CancelableFontCallback cancelableFontCallback = collapsingTextHelper.collapsedFontCallback;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancelled = true;
        }
        CollapsingTextHelper.AnonymousClass1 r2 = new CollapsingTextHelper.AnonymousClass1();
        textAppearance.createFallbackFont();
        collapsingTextHelper.collapsedFontCallback = new CancelableFontCallback(r2, textAppearance.font);
        textAppearance.getFontAsync(collapsingTextHelper.view.getContext(), collapsingTextHelper.collapsedFontCallback);
        collapsingTextHelper.recalculate();
        this.focusedTextColor = this.collapsingTextHelper.collapsedTextColor;
        if (this.editText != null) {
            updateLabelState(false, false);
            updateInputLayoutMargins();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.focusedTextColor != colorStateList) {
            if (this.defaultHintTextColor == null) {
                CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
                if (collapsingTextHelper.collapsedTextColor != colorStateList) {
                    collapsingTextHelper.collapsedTextColor = colorStateList;
                    collapsingTextHelper.recalculate();
                }
            }
            this.focusedTextColor = colorStateList;
            if (this.editText != null) {
                updateLabelState(false, false);
            }
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i) {
        setPasswordVisibilityToggleContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i) {
        setPasswordVisibilityToggleDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        if (z && this.endIconMode != 1) {
            setEndIconMode(1);
        } else if (!z) {
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        this.endIconTintList = colorStateList;
        this.hasEndIconTintList = true;
        applyEndIconTint();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.endIconTintMode = mode;
        this.hasEndIconTintMode = true;
        applyEndIconTint();
    }

    public void setPlaceholderText(CharSequence charSequence) {
        int i = 0;
        if (!this.placeholderEnabled || !TextUtils.isEmpty(charSequence)) {
            if (!this.placeholderEnabled) {
                setPlaceholderTextEnabled(true);
            }
            this.placeholderText = charSequence;
        } else {
            setPlaceholderTextEnabled(false);
        }
        EditText editText = this.editText;
        if (editText != null) {
            i = editText.getText().length();
        }
        updatePlaceholderText(i);
    }

    public void setPlaceholderTextAppearance(int i) {
        this.placeholderTextAppearance = i;
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            AppOpsManagerCompat.setTextAppearance(textView, i);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.placeholderTextColor != colorStateList) {
            this.placeholderTextColor = colorStateList;
            TextView textView = this.placeholderTextView;
            if (textView != null && colorStateList != null) {
                textView.setTextColor(colorStateList);
            }
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        this.prefixText = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.prefixTextView.setText(charSequence);
        updatePrefixTextVisibility();
    }

    public void setPrefixTextAppearance(int i) {
        AppOpsManagerCompat.setTextAppearance(this.prefixTextView, i);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.prefixTextView.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z) {
        this.startIconView.setCheckable(z);
    }

    public void setStartIconContentDescription(int i) {
        setStartIconContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setStartIconDrawable(int i) {
        setStartIconDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        CheckableImageButton checkableImageButton = this.startIconView;
        View.OnLongClickListener onLongClickListener = this.startIconOnLongClickListener;
        checkableImageButton.setOnClickListener(onClickListener);
        setIconClickable(checkableImageButton, onLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.startIconOnLongClickListener = onLongClickListener;
        CheckableImageButton checkableImageButton = this.startIconView;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        setIconClickable(checkableImageButton, onLongClickListener);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        if (this.startIconTintList != colorStateList) {
            this.startIconTintList = colorStateList;
            this.hasStartIconTintList = true;
            applyIconTint(this.startIconView, true, colorStateList, this.hasStartIconTintMode, this.startIconTintMode);
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        if (this.startIconTintMode != mode) {
            this.startIconTintMode = mode;
            this.hasStartIconTintMode = true;
            applyIconTint(this.startIconView, this.hasStartIconTintList, this.startIconTintList, true, mode);
        }
    }

    public void setStartIconVisible(boolean z) {
        int i = 0;
        if ((this.startIconView.getVisibility() == 0) != z) {
            CheckableImageButton checkableImageButton = this.startIconView;
            if (!z) {
                i = 8;
            }
            checkableImageButton.setVisibility(i);
            updatePrefixTextViewPadding();
            updateDummyDrawables();
        }
    }

    public void setSuffixText(CharSequence charSequence) {
        this.suffixText = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.suffixTextView.setText(charSequence);
        updateSuffixTextVisibility();
    }

    public void setSuffixTextAppearance(int i) {
        AppOpsManagerCompat.setTextAppearance(this.suffixTextView, i);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.suffixTextView.setTextColor(colorStateList);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r3.getTextColors().getDefaultColor() == (-65281)) goto L_0x001c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setTextAppearanceCompatWithErrorFallback(android.widget.TextView r3, int r4) {
        /*
            r2 = this;
            r0 = 1
            androidx.core.app.AppOpsManagerCompat.setTextAppearance(r3, r4)     // Catch: Exception -> 0x001b
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: Exception -> 0x001b
            r1 = 23
            if (r4 < r1) goto L_0x0018
            android.content.res.ColorStateList r4 = r3.getTextColors()     // Catch: Exception -> 0x001b
            int r4 = r4.getDefaultColor()     // Catch: Exception -> 0x001b
            r1 = -65281(0xffffffffffff00ff, float:NaN)
            if (r4 != r1) goto L_0x0018
            goto L_0x001c
        L_0x0018:
            r4 = 0
            r0 = 0
            goto L_0x001c
        L_0x001b:
        L_0x001c:
            if (r0 == 0) goto L_0x0032
            r4 = 2131886413(0x7f12014d, float:1.9407404E38)
            androidx.core.app.AppOpsManagerCompat.setTextAppearance(r3, r4)
            android.content.Context r4 = r2.getContext()
            r0 = 2131099747(0x7f060063, float:1.7811856E38)
            int r4 = androidx.core.content.ContextCompat.getColor(r4, r0)
            r3.setTextColor(r4)
        L_0x0032:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.setTextAppearanceCompatWithErrorFallback(android.widget.TextView, int):void");
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate accessibilityDelegate) {
        EditText editText = this.editText;
        if (editText != null) {
            ViewCompat.setAccessibilityDelegate(editText, accessibilityDelegate);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.typeface) {
            this.typeface = typeface;
            this.collapsingTextHelper.setTypefaces(typeface);
            IndicatorViewController indicatorViewController = this.indicatorViewController;
            if (typeface != indicatorViewController.typeface) {
                indicatorViewController.typeface = typeface;
                TextView textView = indicatorViewController.errorView;
                if (textView != null) {
                    textView.setTypeface(typeface);
                }
                TextView textView2 = indicatorViewController.helperTextView;
                if (textView2 != null) {
                    textView2.setTypeface(typeface);
                }
            }
            TextView textView3 = this.counterView;
            if (textView3 != null) {
                textView3.setTypeface(typeface);
            }
        }
    }

    public final void updateCounter() {
        if (this.counterView != null) {
            EditText editText = this.editText;
            updateCounter(editText == null ? 0 : editText.getText().length());
        }
    }

    public final void updateCounterTextAppearanceAndColor() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.counterView;
        if (textView != null) {
            setTextAppearanceCompatWithErrorFallback(textView, this.counterOverflowed ? this.counterOverflowTextAppearance : this.counterTextAppearance);
            if (!this.counterOverflowed && (colorStateList2 = this.counterTextColor) != null) {
                this.counterView.setTextColor(colorStateList2);
            }
            if (this.counterOverflowed && (colorStateList = this.counterOverflowTextColor) != null) {
                this.counterView.setTextColor(colorStateList);
            }
        }
    }

    public final boolean updateDummyDrawables() {
        boolean z;
        if (this.editText == null) {
            return false;
        }
        boolean z2 = true;
        if (!(getStartIconDrawable() == null && this.prefixText == null) && this.startLayout.getMeasuredWidth() > 0) {
            int measuredWidth = this.startLayout.getMeasuredWidth() - this.editText.getPaddingLeft();
            if (this.startDummyDrawable == null || this.startDummyDrawableWidth != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.startDummyDrawable = colorDrawable;
                this.startDummyDrawableWidth = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] compoundDrawablesRelative = this.editText.getCompoundDrawablesRelative();
            Drawable drawable = compoundDrawablesRelative[0];
            Drawable drawable2 = this.startDummyDrawable;
            if (drawable != drawable2) {
                this.editText.setCompoundDrawablesRelative(drawable2, compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
                z = true;
            }
            z = false;
        } else {
            if (this.startDummyDrawable != null) {
                Drawable[] compoundDrawablesRelative2 = this.editText.getCompoundDrawablesRelative();
                this.editText.setCompoundDrawablesRelative(null, compoundDrawablesRelative2[1], compoundDrawablesRelative2[2], compoundDrawablesRelative2[3]);
                this.startDummyDrawable = null;
                z = true;
            }
            z = false;
        }
        if ((this.errorIconView.getVisibility() == 0 || ((hasEndIcon() && isEndIconVisible()) || this.suffixText != null)) && this.endLayout.getMeasuredWidth() > 0) {
            int measuredWidth2 = this.suffixTextView.getMeasuredWidth() - this.editText.getPaddingRight();
            CheckableImageButton endIconToUpdateDummyDrawable = getEndIconToUpdateDummyDrawable();
            if (endIconToUpdateDummyDrawable != null) {
                measuredWidth2 = ((ViewGroup.MarginLayoutParams) endIconToUpdateDummyDrawable.getLayoutParams()).getMarginStart() + endIconToUpdateDummyDrawable.getMeasuredWidth() + measuredWidth2;
            }
            Drawable[] compoundDrawablesRelative3 = this.editText.getCompoundDrawablesRelative();
            Drawable drawable3 = this.endDummyDrawable;
            if (drawable3 == null || this.endDummyDrawableWidth == measuredWidth2) {
                if (drawable3 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.endDummyDrawable = colorDrawable2;
                    this.endDummyDrawableWidth = measuredWidth2;
                    colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
                }
                Drawable drawable4 = compoundDrawablesRelative3[2];
                Drawable drawable5 = this.endDummyDrawable;
                if (drawable4 != drawable5) {
                    this.originalEditTextEndDrawable = compoundDrawablesRelative3[2];
                    this.editText.setCompoundDrawablesRelative(compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], drawable5, compoundDrawablesRelative3[3]);
                } else {
                    z2 = z;
                }
            } else {
                this.endDummyDrawableWidth = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                this.editText.setCompoundDrawablesRelative(compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], this.endDummyDrawable, compoundDrawablesRelative3[3]);
            }
        } else if (this.endDummyDrawable == null) {
            return z;
        } else {
            Drawable[] compoundDrawablesRelative4 = this.editText.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative4[2] == this.endDummyDrawable) {
                this.editText.setCompoundDrawablesRelative(compoundDrawablesRelative4[0], compoundDrawablesRelative4[1], this.originalEditTextEndDrawable, compoundDrawablesRelative4[3]);
            } else {
                z2 = z;
            }
            this.endDummyDrawable = null;
        }
        return z2;
    }

    public void updateEditTextBackground() {
        Drawable background;
        TextView textView;
        EditText editText = this.editText;
        if (editText != null && this.boxBackgroundMode == 0 && (background = editText.getBackground()) != null) {
            if (DrawableUtils.canSafelyMutateDrawable(background)) {
                background = background.mutate();
            }
            if (this.indicatorViewController.errorShouldBeShown()) {
                background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.indicatorViewController.getErrorViewCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            } else if (!this.counterOverflowed || (textView = this.counterView) == null) {
                AppOpsManagerCompat.clearColorFilter(background);
                this.editText.refreshDrawableState();
            } else {
                background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public final void updateInputLayoutMargins() {
        if (this.boxBackgroundMode != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
            int calculateLabelMarginTop = calculateLabelMarginTop();
            if (calculateLabelMarginTop != layoutParams.topMargin) {
                layoutParams.topMargin = calculateLabelMarginTop;
                this.inputFrame.requestLayout();
            }
        }
    }

    public final void updateLabelState(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        boolean isEnabled = isEnabled();
        EditText editText = this.editText;
        int i = 0;
        boolean z3 = editText != null && !TextUtils.isEmpty(editText.getText());
        EditText editText2 = this.editText;
        boolean z4 = editText2 != null && editText2.hasFocus();
        boolean errorShouldBeShown = this.indicatorViewController.errorShouldBeShown();
        ColorStateList colorStateList2 = this.defaultHintTextColor;
        if (colorStateList2 != null) {
            CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
            if (collapsingTextHelper.collapsedTextColor != colorStateList2) {
                collapsingTextHelper.collapsedTextColor = colorStateList2;
                collapsingTextHelper.recalculate();
            }
            CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
            ColorStateList colorStateList3 = this.defaultHintTextColor;
            if (collapsingTextHelper2.expandedTextColor != colorStateList3) {
                collapsingTextHelper2.expandedTextColor = colorStateList3;
                collapsingTextHelper2.recalculate();
            }
        }
        if (!isEnabled) {
            ColorStateList colorStateList4 = this.defaultHintTextColor;
            int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(new int[]{-16842910}, this.disabledColor) : this.disabledColor;
            this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(colorForState));
            CollapsingTextHelper collapsingTextHelper3 = this.collapsingTextHelper;
            ColorStateList valueOf = ColorStateList.valueOf(colorForState);
            if (collapsingTextHelper3.expandedTextColor != valueOf) {
                collapsingTextHelper3.expandedTextColor = valueOf;
                collapsingTextHelper3.recalculate();
            }
        } else if (errorShouldBeShown) {
            CollapsingTextHelper collapsingTextHelper4 = this.collapsingTextHelper;
            TextView textView2 = this.indicatorViewController.errorView;
            collapsingTextHelper4.setCollapsedTextColor(textView2 != null ? textView2.getTextColors() : null);
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(textView.getTextColors());
        } else if (z4 && (colorStateList = this.focusedTextColor) != null) {
            CollapsingTextHelper collapsingTextHelper5 = this.collapsingTextHelper;
            if (collapsingTextHelper5.collapsedTextColor != colorStateList) {
                collapsingTextHelper5.collapsedTextColor = colorStateList;
                collapsingTextHelper5.recalculate();
            }
        }
        if (z3 || !this.expandedHintEnabled || (isEnabled() && z4)) {
            if (z2 || this.hintExpanded) {
                ValueAnimator valueAnimator = this.animator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.animator.cancel();
                }
                if (!z || !this.hintAnimationEnabled) {
                    this.collapsingTextHelper.setExpansionFraction(1.0f);
                } else {
                    animateToExpansionFraction(1.0f);
                }
                this.hintExpanded = false;
                if (cutoutEnabled()) {
                    openCutout();
                }
                EditText editText3 = this.editText;
                if (editText3 != null) {
                    i = editText3.getText().length();
                }
                updatePlaceholderText(i);
                updatePrefixTextVisibility();
                updateSuffixTextVisibility();
            }
        } else if (z2 || !this.hintExpanded) {
            ValueAnimator valueAnimator2 = this.animator;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.animator.cancel();
            }
            if (!z || !this.hintAnimationEnabled) {
                this.collapsingTextHelper.setExpansionFraction(0.0f);
            } else {
                animateToExpansionFraction(0.0f);
            }
            if (cutoutEnabled() && (!((CutoutDrawable) this.boxBackground).cutoutBounds.isEmpty()) && cutoutEnabled()) {
                ((CutoutDrawable) this.boxBackground).setCutout(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.hintExpanded = true;
            TextView textView3 = this.placeholderTextView;
            if (textView3 != null && this.placeholderEnabled) {
                textView3.setText((CharSequence) null);
                this.placeholderTextView.setVisibility(4);
            }
            updatePrefixTextVisibility();
            updateSuffixTextVisibility();
        }
    }

    public final void updatePlaceholderText(int i) {
        if (i != 0 || this.hintExpanded) {
            TextView textView = this.placeholderTextView;
            if (textView != null && this.placeholderEnabled) {
                textView.setText((CharSequence) null);
                this.placeholderTextView.setVisibility(4);
                return;
            }
            return;
        }
        TextView textView2 = this.placeholderTextView;
        if (textView2 != null && this.placeholderEnabled) {
            textView2.setText(this.placeholderText);
            this.placeholderTextView.setVisibility(0);
            this.placeholderTextView.bringToFront();
        }
    }

    public final void updatePrefixTextViewPadding() {
        if (this.editText != null) {
            int i = 0;
            if (!(this.startIconView.getVisibility() == 0)) {
                EditText editText = this.editText;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                i = editText.getPaddingStart();
            }
            TextView textView = this.prefixTextView;
            int compoundPaddingTop = this.editText.getCompoundPaddingTop();
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding);
            int compoundPaddingBottom = this.editText.getCompoundPaddingBottom();
            AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
            textView.setPaddingRelative(i, compoundPaddingTop, dimensionPixelSize, compoundPaddingBottom);
        }
    }

    public final void updatePrefixTextVisibility() {
        this.prefixTextView.setVisibility((this.prefixText == null || this.hintExpanded) ? 8 : 0);
        updateDummyDrawables();
    }

    public final void updateStrokeErrorColor(boolean z, boolean z2) {
        int defaultColor = this.strokeErrorColor.getDefaultColor();
        int colorForState = this.strokeErrorColor.getColorForState(new int[]{16843623, 16842910}, defaultColor);
        int colorForState2 = this.strokeErrorColor.getColorForState(new int[]{16843518, 16842910}, defaultColor);
        if (z) {
            this.boxStrokeColor = colorForState2;
        } else if (z2) {
            this.boxStrokeColor = colorForState;
        } else {
            this.boxStrokeColor = defaultColor;
        }
    }

    public final void updateSuffixTextViewPadding() {
        if (this.editText != null) {
            int i = 0;
            if (!isEndIconVisible()) {
                if (!(this.errorIconView.getVisibility() == 0)) {
                    EditText editText = this.editText;
                    AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                    i = editText.getPaddingEnd();
                }
            }
            TextView textView = this.suffixTextView;
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding);
            int paddingTop = this.editText.getPaddingTop();
            int paddingBottom = this.editText.getPaddingBottom();
            AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
            textView.setPaddingRelative(dimensionPixelSize, paddingTop, i, paddingBottom);
        }
    }

    public final void updateSuffixTextVisibility() {
        int visibility = this.suffixTextView.getVisibility();
        int i = 0;
        boolean z = this.suffixText != null && !this.hintExpanded;
        TextView textView = this.suffixTextView;
        if (!z) {
            i = 8;
        }
        textView.setVisibility(i);
        if (visibility != this.suffixTextView.getVisibility()) {
            getEndIconDelegate().onSuffixVisibilityChanged(z);
        }
        updateDummyDrawables();
    }

    public void updateTextInputBoxState() {
        TextView textView;
        EditText editText;
        EditText editText2;
        if (this.boxBackground != null && this.boxBackgroundMode != 0) {
            boolean z = false;
            boolean z2 = isFocused() || ((editText2 = this.editText) != null && editText2.hasFocus());
            boolean z3 = isHovered() || ((editText = this.editText) != null && editText.isHovered());
            if (!isEnabled()) {
                this.boxStrokeColor = this.disabledColor;
            } else if (this.indicatorViewController.errorShouldBeShown()) {
                if (this.strokeErrorColor != null) {
                    updateStrokeErrorColor(z2, z3);
                } else {
                    this.boxStrokeColor = this.indicatorViewController.getErrorViewCurrentTextColor();
                }
            } else if (!this.counterOverflowed || (textView = this.counterView) == null) {
                if (z2) {
                    this.boxStrokeColor = this.focusedStrokeColor;
                } else if (z3) {
                    this.boxStrokeColor = this.hoveredStrokeColor;
                } else {
                    this.boxStrokeColor = this.defaultStrokeColor;
                }
            } else if (this.strokeErrorColor != null) {
                updateStrokeErrorColor(z2, z3);
            } else {
                this.boxStrokeColor = textView.getCurrentTextColor();
            }
            if (getErrorIconDrawable() != null) {
                IndicatorViewController indicatorViewController = this.indicatorViewController;
                if (indicatorViewController.errorEnabled && indicatorViewController.errorShouldBeShown()) {
                    z = true;
                }
            }
            setErrorIconVisible(z);
            refreshIconDrawableState(this.errorIconView, this.errorIconTintList);
            refreshIconDrawableState(this.startIconView, this.startIconTintList);
            refreshEndIconDrawableState();
            EndIconDelegate endIconDelegate = getEndIconDelegate();
            Objects.requireNonNull(endIconDelegate);
            if (endIconDelegate instanceof DropdownMenuEndIconDelegate) {
                if (!this.indicatorViewController.errorShouldBeShown() || getEndIconDrawable() == null) {
                    applyEndIconTint();
                } else {
                    Drawable mutate = AppOpsManagerCompat.wrap(getEndIconDrawable()).mutate();
                    mutate.setTint(this.indicatorViewController.getErrorViewCurrentTextColor());
                    this.endIconView.setImageDrawable(mutate);
                }
            }
            if (!z2 || !isEnabled()) {
                this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
            } else {
                this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
            }
            if (this.boxBackgroundMode == 1) {
                if (!isEnabled()) {
                    this.boxBackgroundColor = this.disabledFilledBackgroundColor;
                } else if (z3 && !z2) {
                    this.boxBackgroundColor = this.hoveredFilledBackgroundColor;
                } else if (z2) {
                    this.boxBackgroundColor = this.focusedFilledBackgroundColor;
                } else {
                    this.boxBackgroundColor = this.defaultFilledBackgroundColor;
                }
            }
            applyBoxAttributes();
        }
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.endIconView.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        this.endIconView.setImageDrawable(drawable);
        refreshEndIconDrawableState();
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.startIconView.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.startIconView.setImageDrawable(drawable);
        if (drawable != null) {
            setStartIconVisible(true);
            refreshIconDrawableState(this.startIconView, this.startIconTintList);
            return;
        }
        setStartIconVisible(false);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        setStartIconContentDescription((CharSequence) null);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.errorIconView.setImageDrawable(drawable);
        setErrorIconVisible(drawable != null && this.indicatorViewController.errorEnabled);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.endIconView.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.endIconView.setImageDrawable(drawable);
    }

    public void updateCounter(int i) {
        boolean z = this.counterOverflowed;
        int i2 = this.counterMaxLength;
        String str = null;
        if (i2 == -1) {
            this.counterView.setText(String.valueOf(i));
            this.counterView.setContentDescription(null);
            this.counterOverflowed = false;
        } else {
            this.counterOverflowed = i > i2;
            Context context = getContext();
            this.counterView.setContentDescription(context.getString(this.counterOverflowed ? R.string.character_counter_overflowed_content_description : R.string.character_counter_content_description, Integer.valueOf(i), Integer.valueOf(this.counterMaxLength)));
            if (z != this.counterOverflowed) {
                updateCounterTextAppearanceAndColor();
            }
            BidiFormatter instance = BidiFormatter.getInstance();
            TextView textView = this.counterView;
            String string = getContext().getString(R.string.character_counter_pattern, Integer.valueOf(i), Integer.valueOf(this.counterMaxLength));
            TextDirectionHeuristicCompat textDirectionHeuristicCompat = instance.mDefaultTextDirectionHeuristicCompat;
            if (string != null) {
                str = instance.unicodeWrap(string, textDirectionHeuristicCompat, true).toString();
            }
            textView.setText(str);
        }
        if (this.editText != null && z != this.counterOverflowed) {
            updateLabelState(false, false);
            updateTextInputBoxState();
            updateEditTextBackground();
        }
    }

    public void setHint(int i) {
        setHint(i != 0 ? getResources().getText(i) : null);
    }
}
