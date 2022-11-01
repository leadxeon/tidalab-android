package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$style;
import com.google.android.material.animation.AnimationUtils;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class IndicatorViewController {
    public Animator captionAnimator;
    public FrameLayout captionArea;
    public int captionDisplayed;
    public int captionToShow;
    public final float captionTranslationYPx;
    public final Context context;
    public boolean errorEnabled;
    public CharSequence errorText;
    public int errorTextAppearance;
    public TextView errorView;
    public CharSequence errorViewContentDescription;
    public ColorStateList errorViewTextColor;
    public CharSequence helperText;
    public boolean helperTextEnabled;
    public int helperTextTextAppearance;
    public TextView helperTextView;
    public ColorStateList helperTextViewTextColor;
    public LinearLayout indicatorArea;
    public int indicatorsAdded;
    public final TextInputLayout textInputView;
    public Typeface typeface;

    public IndicatorViewController(TextInputLayout textInputLayout) {
        Context context = textInputLayout.getContext();
        this.context = context;
        this.textInputView = textInputLayout;
        this.captionTranslationYPx = context.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
    }

    public void addIndicator(TextView textView, int i) {
        if (this.indicatorArea == null && this.captionArea == null) {
            LinearLayout linearLayout = new LinearLayout(this.context);
            this.indicatorArea = linearLayout;
            linearLayout.setOrientation(0);
            this.textInputView.addView(this.indicatorArea, -1, -2);
            this.captionArea = new FrameLayout(this.context);
            this.indicatorArea.addView(this.captionArea, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.textInputView.getEditText() != null) {
                adjustIndicatorPadding();
            }
        }
        if (i == 0 || i == 1) {
            this.captionArea.setVisibility(0);
            this.captionArea.addView(textView);
        } else {
            this.indicatorArea.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.indicatorArea.setVisibility(0);
        this.indicatorsAdded++;
    }

    public void adjustIndicatorPadding() {
        if ((this.indicatorArea == null || this.textInputView.getEditText() == null) ? false : true) {
            EditText editText = this.textInputView.getEditText();
            boolean isFontScaleAtLeast1_3 = R$style.isFontScaleAtLeast1_3(this.context);
            LinearLayout linearLayout = this.indicatorArea;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            linearLayout.setPaddingRelative(getIndicatorPadding(isFontScaleAtLeast1_3, R.dimen.material_helper_text_font_1_3_padding_horizontal, editText.getPaddingStart()), getIndicatorPadding(isFontScaleAtLeast1_3, R.dimen.material_helper_text_font_1_3_padding_top, this.context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_default_padding_top)), getIndicatorPadding(isFontScaleAtLeast1_3, R.dimen.material_helper_text_font_1_3_padding_horizontal, editText.getPaddingEnd()), 0);
        }
    }

    public void cancelCaptionAnimator() {
        Animator animator = this.captionAnimator;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void createCaptionAnimators(List<Animator> list, boolean z, TextView textView, int i, int i2, int i3) {
        if (textView != null && z) {
            if (i == i3 || i == i2) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, View.ALPHA, i3 == i ? 1.0f : 0.0f);
                ofFloat.setDuration(167L);
                ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
                list.add(ofFloat);
                if (i3 == i) {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, -this.captionTranslationYPx, 0.0f);
                    ofFloat2.setDuration(217L);
                    ofFloat2.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
                    list.add(ofFloat2);
                }
            }
        }
    }

    public boolean errorShouldBeShown() {
        return this.captionToShow == 1 && this.errorView != null && !TextUtils.isEmpty(this.errorText);
    }

    public final TextView getCaptionViewFromDisplayState(int i) {
        if (i == 1) {
            return this.errorView;
        }
        if (i != 2) {
            return null;
        }
        return this.helperTextView;
    }

    public int getErrorViewCurrentTextColor() {
        TextView textView = this.errorView;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public final int getIndicatorPadding(boolean z, int i, int i2) {
        return z ? this.context.getResources().getDimensionPixelSize(i) : i2;
    }

    public void hideError() {
        this.errorText = null;
        cancelCaptionAnimator();
        if (this.captionDisplayed == 1) {
            if (!this.helperTextEnabled || TextUtils.isEmpty(this.helperText)) {
                this.captionToShow = 0;
            } else {
                this.captionToShow = 2;
            }
        }
        updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, null));
    }

    public void removeIndicator(TextView textView, int i) {
        FrameLayout frameLayout;
        LinearLayout linearLayout = this.indicatorArea;
        if (linearLayout != null) {
            if (!(i == 0 || i == 1) || (frameLayout = this.captionArea) == null) {
                linearLayout.removeView(textView);
            } else {
                frameLayout.removeView(textView);
            }
            int i2 = this.indicatorsAdded - 1;
            this.indicatorsAdded = i2;
            LinearLayout linearLayout2 = this.indicatorArea;
            if (i2 == 0) {
                linearLayout2.setVisibility(8);
            }
        }
    }

    public final boolean shouldAnimateCaptionView(TextView textView, CharSequence charSequence) {
        TextInputLayout textInputLayout = this.textInputView;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        return textInputLayout.isLaidOut() && this.textInputView.isEnabled() && (this.captionToShow != this.captionDisplayed || textView == null || !TextUtils.equals(textView.getText(), charSequence));
    }

    public final void updateCaptionViewsVisibility(final int i, final int i2, boolean z) {
        TextView captionViewFromDisplayState;
        TextView captionViewFromDisplayState2;
        if (i != i2) {
            if (z) {
                AnimatorSet animatorSet = new AnimatorSet();
                this.captionAnimator = animatorSet;
                ArrayList arrayList = new ArrayList();
                createCaptionAnimators(arrayList, this.helperTextEnabled, this.helperTextView, 2, i, i2);
                createCaptionAnimators(arrayList, this.errorEnabled, this.errorView, 1, i, i2);
                R$style.playTogether(animatorSet, arrayList);
                final TextView captionViewFromDisplayState3 = getCaptionViewFromDisplayState(i);
                final TextView captionViewFromDisplayState4 = getCaptionViewFromDisplayState(i2);
                animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.IndicatorViewController.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        TextView textView;
                        IndicatorViewController indicatorViewController = IndicatorViewController.this;
                        indicatorViewController.captionDisplayed = i2;
                        indicatorViewController.captionAnimator = null;
                        TextView textView2 = captionViewFromDisplayState3;
                        if (textView2 != null) {
                            textView2.setVisibility(4);
                            if (i == 1 && (textView = IndicatorViewController.this.errorView) != null) {
                                textView.setText((CharSequence) null);
                            }
                        }
                        TextView textView3 = captionViewFromDisplayState4;
                        if (textView3 != null) {
                            textView3.setTranslationY(0.0f);
                            captionViewFromDisplayState4.setAlpha(1.0f);
                        }
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        TextView textView = captionViewFromDisplayState4;
                        if (textView != null) {
                            textView.setVisibility(0);
                        }
                    }
                });
                animatorSet.start();
            } else if (i != i2) {
                if (!(i2 == 0 || (captionViewFromDisplayState2 = getCaptionViewFromDisplayState(i2)) == null)) {
                    captionViewFromDisplayState2.setVisibility(0);
                    captionViewFromDisplayState2.setAlpha(1.0f);
                }
                if (!(i == 0 || (captionViewFromDisplayState = getCaptionViewFromDisplayState(i)) == null)) {
                    captionViewFromDisplayState.setVisibility(4);
                    if (i == 1) {
                        captionViewFromDisplayState.setText((CharSequence) null);
                    }
                }
                this.captionDisplayed = i2;
            }
            this.textInputView.updateEditTextBackground();
            this.textInputView.updateLabelState(z, false);
            this.textInputView.updateTextInputBoxState();
        }
    }
}
