package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.R$styleable;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.ViewCompat;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class AppCompatSeekBarHelper extends AppCompatProgressBarHelper {
    public Drawable mTickMark;
    public final SeekBar mView;
    public ColorStateList mTickMarkTintList = null;
    public PorterDuff.Mode mTickMarkTintMode = null;
    public boolean mHasTickMarkTint = false;
    public boolean mHasTickMarkTintMode = false;

    public AppCompatSeekBarHelper(SeekBar seekBar) {
        super(seekBar);
        this.mView = seekBar;
    }

    public final void applyTickMarkTint() {
        Drawable drawable = this.mTickMark;
        if (drawable == null) {
            return;
        }
        if (this.mHasTickMarkTint || this.mHasTickMarkTintMode) {
            Drawable wrap = AppOpsManagerCompat.wrap(drawable.mutate());
            this.mTickMark = wrap;
            if (this.mHasTickMarkTint) {
                wrap.setTintList(this.mTickMarkTintList);
            }
            if (this.mHasTickMarkTintMode) {
                this.mTickMark.setTintMode(this.mTickMarkTintMode);
            }
            if (this.mTickMark.isStateful()) {
                this.mTickMark.setState(this.mView.getDrawableState());
            }
        }
    }

    public void drawTickMarks(Canvas canvas) {
        if (this.mTickMark != null) {
            int max = this.mView.getMax();
            int i = 1;
            if (max > 1) {
                int intrinsicWidth = this.mTickMark.getIntrinsicWidth();
                int intrinsicHeight = this.mTickMark.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                if (intrinsicHeight >= 0) {
                    i = intrinsicHeight / 2;
                }
                this.mTickMark.setBounds(-i2, -i, i2, i);
                float width = ((this.mView.getWidth() - this.mView.getPaddingLeft()) - this.mView.getPaddingRight()) / max;
                int save = canvas.save();
                canvas.translate(this.mView.getPaddingLeft(), this.mView.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.mTickMark.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatProgressBarHelper
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        super.loadFromAttributes(attributeSet, i);
        Context context = this.mView.getContext();
        int[] iArr = R$styleable.AppCompatSeekBar;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i, 0);
        SeekBar seekBar = this.mView;
        ViewCompat.saveAttributeDataForStyleable(seekBar, seekBar.getContext(), iArr, attributeSet, obtainStyledAttributes.mWrapped, i, 0);
        Drawable drawableIfKnown = obtainStyledAttributes.getDrawableIfKnown(0);
        if (drawableIfKnown != null) {
            this.mView.setThumb(drawableIfKnown);
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        Drawable drawable2 = this.mTickMark;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mTickMark = drawable;
        if (drawable != null) {
            drawable.setCallback(this.mView);
            SeekBar seekBar2 = this.mView;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            AppOpsManagerCompat.setLayoutDirection(drawable, seekBar2.getLayoutDirection());
            if (drawable.isStateful()) {
                drawable.setState(this.mView.getDrawableState());
            }
            applyTickMarkTint();
        }
        this.mView.invalidate();
        if (obtainStyledAttributes.hasValue(3)) {
            this.mTickMarkTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(3, -1), this.mTickMarkTintMode);
            this.mHasTickMarkTintMode = true;
        }
        if (obtainStyledAttributes.hasValue(2)) {
            this.mTickMarkTintList = obtainStyledAttributes.getColorStateList(2);
            this.mHasTickMarkTint = true;
        }
        obtainStyledAttributes.mWrapped.recycle();
        applyTickMarkTint();
    }
}
