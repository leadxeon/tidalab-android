package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.cardview.R$styleable;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class CardView extends FrameLayout {
    public static final int[] COLOR_BACKGROUND_ATTR = {16842801};
    public static final CardViewImpl IMPL = new CardViewApi21Impl();
    public final CardViewDelegate mCardViewDelegate;
    public boolean mCompatPadding;
    public final Rect mContentPadding;
    public boolean mPreventCornerOverlap;
    public final Rect mShadowBounds;
    public int mUserSetMinHeight;
    public int mUserSetMinWidth;

    /* renamed from: androidx.cardview.widget.CardView$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements CardViewDelegate {
        public Drawable mCardBackground;

        public AnonymousClass1() {
        }

        public boolean getPreventCornerOverlap() {
            return CardView.this.getPreventCornerOverlap();
        }

        public void setShadowPadding(int i, int i2, int i3, int i4) {
            CardView.this.mShadowBounds.set(i, i2, i3, i4);
            CardView cardView = CardView.this;
            Rect rect = cardView.mContentPadding;
            CardView.super.setPadding(i + rect.left, i2 + rect.top, i3 + rect.right, i4 + rect.bottom);
        }
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.cardViewStyle);
    }

    public ColorStateList getCardBackgroundColor() {
        return ((RoundRectDrawable) ((AnonymousClass1) this.mCardViewDelegate).mCardBackground).mBackground;
    }

    public float getCardElevation() {
        return CardView.this.getElevation();
    }

    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }

    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }

    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }

    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }

    public float getMaxCardElevation() {
        return ((RoundRectDrawable) ((AnonymousClass1) this.mCardViewDelegate).mCardBackground).mPadding;
    }

    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public float getRadius() {
        return ((RoundRectDrawable) ((AnonymousClass1) this.mCardViewDelegate).mCardBackground).mRadius;
    }

    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void setCardBackgroundColor(int i) {
        CardViewDelegate cardViewDelegate = this.mCardViewDelegate;
        ColorStateList valueOf = ColorStateList.valueOf(i);
        RoundRectDrawable roundRectDrawable = (RoundRectDrawable) ((AnonymousClass1) cardViewDelegate).mCardBackground;
        roundRectDrawable.setBackground(valueOf);
        roundRectDrawable.invalidateSelf();
    }

    public void setCardElevation(float f) {
        CardView.this.setElevation(f);
    }

    public void setMaxCardElevation(float f) {
        ((CardViewApi21Impl) IMPL).setMaxElevation(this.mCardViewDelegate, f);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i) {
        this.mUserSetMinHeight = i;
        super.setMinimumHeight(i);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i) {
        this.mUserSetMinWidth = i;
        super.setMinimumWidth(i);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.mPreventCornerOverlap) {
            this.mPreventCornerOverlap = z;
            CardViewImpl cardViewImpl = IMPL;
            CardViewDelegate cardViewDelegate = this.mCardViewDelegate;
            CardViewApi21Impl cardViewApi21Impl = (CardViewApi21Impl) cardViewImpl;
            cardViewApi21Impl.setMaxElevation(cardViewDelegate, cardViewApi21Impl.getCardBackground(cardViewDelegate).mPadding);
        }
    }

    public void setRadius(float f) {
        RoundRectDrawable roundRectDrawable = (RoundRectDrawable) ((AnonymousClass1) this.mCardViewDelegate).mCardBackground;
        if (f != roundRectDrawable.mRadius) {
            roundRectDrawable.mRadius = f;
            roundRectDrawable.updateBounds(null);
            roundRectDrawable.invalidateSelf();
        }
    }

    public void setUseCompatPadding(boolean z) {
        if (this.mCompatPadding != z) {
            this.mCompatPadding = z;
            CardViewImpl cardViewImpl = IMPL;
            CardViewDelegate cardViewDelegate = this.mCardViewDelegate;
            CardViewApi21Impl cardViewApi21Impl = (CardViewApi21Impl) cardViewImpl;
            cardViewApi21Impl.setMaxElevation(cardViewDelegate, cardViewApi21Impl.getCardBackground(cardViewDelegate).mPadding);
        }
    }

    public CardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ColorStateList colorStateList;
        int i2;
        Rect rect = new Rect();
        this.mContentPadding = rect;
        this.mShadowBounds = new Rect();
        AnonymousClass1 r1 = new AnonymousClass1();
        this.mCardViewDelegate = r1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R.style.CardView);
        if (obtainStyledAttributes.hasValue(2)) {
            colorStateList = obtainStyledAttributes.getColorStateList(2);
        } else {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            int color = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color, fArr);
            if (fArr[2] > 0.5f) {
                i2 = getResources().getColor(R.color.cardview_light_background);
            } else {
                i2 = getResources().getColor(R.color.cardview_dark_background);
            }
            colorStateList = ColorStateList.valueOf(i2);
        }
        float dimension = obtainStyledAttributes.getDimension(3, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(4, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(5, 0.0f);
        this.mCompatPadding = obtainStyledAttributes.getBoolean(7, false);
        this.mPreventCornerOverlap = obtainStyledAttributes.getBoolean(6, true);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(8, 0);
        rect.left = obtainStyledAttributes.getDimensionPixelSize(10, dimensionPixelSize);
        rect.top = obtainStyledAttributes.getDimensionPixelSize(12, dimensionPixelSize);
        rect.right = obtainStyledAttributes.getDimensionPixelSize(11, dimensionPixelSize);
        rect.bottom = obtainStyledAttributes.getDimensionPixelSize(9, dimensionPixelSize);
        dimension3 = dimension2 > dimension3 ? dimension2 : dimension3;
        this.mUserSetMinWidth = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.mUserSetMinHeight = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        RoundRectDrawable roundRectDrawable = new RoundRectDrawable(colorStateList, dimension);
        AnonymousClass1 r10 = r1;
        r10.mCardBackground = roundRectDrawable;
        CardView.this.setBackgroundDrawable(roundRectDrawable);
        CardView cardView = CardView.this;
        cardView.setClipToOutline(true);
        cardView.setElevation(dimension2);
        ((CardViewApi21Impl) IMPL).setMaxElevation(r1, dimension3);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        RoundRectDrawable roundRectDrawable = (RoundRectDrawable) ((AnonymousClass1) this.mCardViewDelegate).mCardBackground;
        roundRectDrawable.setBackground(colorStateList);
        roundRectDrawable.invalidateSelf();
    }
}
