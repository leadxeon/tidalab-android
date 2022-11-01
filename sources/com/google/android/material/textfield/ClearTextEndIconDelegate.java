package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.content.res.AppCompatResources;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.textfield.TextInputLayout;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class ClearTextEndIconDelegate extends EndIconDelegate {
    public AnimatorSet iconInAnim;
    public ValueAnimator iconOutAnim;
    public final TextWatcher clearTextEndIconTextWatcher = new TextWatcher() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.1
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (ClearTextEndIconDelegate.this.textInputLayout.getSuffixText() == null) {
                ClearTextEndIconDelegate.this.animateIcon(editable.length() > 0);
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };
    public final View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.2
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            boolean z2 = true;
            boolean z3 = !TextUtils.isEmpty(((EditText) view).getText());
            ClearTextEndIconDelegate clearTextEndIconDelegate = ClearTextEndIconDelegate.this;
            if (!z3 || !z) {
                z2 = false;
            }
            clearTextEndIconDelegate.animateIcon(z2);
        }
    };
    public final TextInputLayout.OnEditTextAttachedListener clearTextOnEditTextAttachedListener = new TextInputLayout.OnEditTextAttachedListener() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.3
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
            if ((r0.getText().length() > 0) != false) goto L_0x001d;
         */
        @Override // com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onEditTextAttached(com.google.android.material.textfield.TextInputLayout r5) {
            /*
                r4 = this;
                android.widget.EditText r0 = r5.getEditText()
                boolean r1 = r0.hasFocus()
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L_0x001c
                android.text.Editable r1 = r0.getText()
                int r1 = r1.length()
                if (r1 <= 0) goto L_0x0018
                r1 = 1
                goto L_0x0019
            L_0x0018:
                r1 = 0
            L_0x0019:
                if (r1 == 0) goto L_0x001c
                goto L_0x001d
            L_0x001c:
                r2 = 0
            L_0x001d:
                r5.setEndIconVisible(r2)
                r5.setEndIconCheckable(r3)
                com.google.android.material.textfield.ClearTextEndIconDelegate r5 = com.google.android.material.textfield.ClearTextEndIconDelegate.this
                android.view.View$OnFocusChangeListener r5 = r5.onFocusChangeListener
                r0.setOnFocusChangeListener(r5)
                com.google.android.material.textfield.ClearTextEndIconDelegate r5 = com.google.android.material.textfield.ClearTextEndIconDelegate.this
                android.text.TextWatcher r5 = r5.clearTextEndIconTextWatcher
                r0.removeTextChangedListener(r5)
                com.google.android.material.textfield.ClearTextEndIconDelegate r5 = com.google.android.material.textfield.ClearTextEndIconDelegate.this
                android.text.TextWatcher r5 = r5.clearTextEndIconTextWatcher
                r0.addTextChangedListener(r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.ClearTextEndIconDelegate.AnonymousClass3.onEditTextAttached(com.google.android.material.textfield.TextInputLayout):void");
        }
    };
    public final TextInputLayout.OnEndIconChangedListener endIconChangedListener = new TextInputLayout.OnEndIconChangedListener() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.4
        @Override // com.google.android.material.textfield.TextInputLayout.OnEndIconChangedListener
        public void onEndIconChanged(TextInputLayout textInputLayout, int i) {
            final EditText editText = textInputLayout.getEditText();
            if (editText != null && i == 2) {
                editText.post(new Runnable() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        editText.removeTextChangedListener(ClearTextEndIconDelegate.this.clearTextEndIconTextWatcher);
                    }
                });
                if (editText.getOnFocusChangeListener() == ClearTextEndIconDelegate.this.onFocusChangeListener) {
                    editText.setOnFocusChangeListener(null);
                }
            }
        }
    };

    public ClearTextEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    public final void animateIcon(boolean z) {
        boolean z2 = this.textInputLayout.isEndIconVisible() == z;
        if (z && !this.iconInAnim.isRunning()) {
            this.iconOutAnim.cancel();
            this.iconInAnim.start();
            if (z2) {
                this.iconInAnim.end();
            }
        } else if (!z) {
            this.iconInAnim.cancel();
            this.iconOutAnim.start();
            if (z2) {
                this.iconOutAnim.end();
            }
        }
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void initialize() {
        this.textInputLayout.setEndIconDrawable(AppCompatResources.getDrawable(this.context, R.drawable.mtrl_ic_cancel));
        TextInputLayout textInputLayout = this.textInputLayout;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R.string.clear_text_end_icon_content_description));
        this.textInputLayout.setEndIconOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Editable text = ClearTextEndIconDelegate.this.textInputLayout.getEditText().getText();
                if (text != null) {
                    text.clear();
                }
                ClearTextEndIconDelegate.this.textInputLayout.refreshEndIconDrawableState();
            }
        });
        this.textInputLayout.addOnEditTextAttachedListener(this.clearTextOnEditTextAttachedListener);
        this.textInputLayout.endIconChangedListeners.add(this.endIconChangedListener);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        ofFloat.setDuration(150L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.9
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ClearTextEndIconDelegate.this.endIconView.setScaleX(floatValue);
                ClearTextEndIconDelegate.this.endIconView.setScaleY(floatValue);
            }
        });
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        TimeInterpolator timeInterpolator = AnimationUtils.LINEAR_INTERPOLATOR;
        ofFloat2.setInterpolator(timeInterpolator);
        ofFloat2.setDuration(100L);
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ClearTextEndIconDelegate.this.endIconView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        this.iconInAnim = animatorSet;
        animatorSet.playTogether(ofFloat, ofFloat2);
        this.iconInAnim.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                ClearTextEndIconDelegate.this.textInputLayout.setEndIconVisible(true);
            }
        });
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat3.setInterpolator(timeInterpolator);
        ofFloat3.setDuration(100L);
        ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ClearTextEndIconDelegate.this.endIconView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.iconOutAnim = ofFloat3;
        ofFloat3.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.7
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ClearTextEndIconDelegate.this.textInputLayout.setEndIconVisible(false);
            }
        });
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void onSuffixVisibilityChanged(boolean z) {
        if (this.textInputLayout.getSuffixText() != null) {
            animateIcon(z);
        }
    }
}
