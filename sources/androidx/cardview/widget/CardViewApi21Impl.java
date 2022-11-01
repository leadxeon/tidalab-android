package androidx.cardview.widget;

import androidx.cardview.widget.CardView;
/* loaded from: classes.dex */
public class CardViewApi21Impl implements CardViewImpl {
    public final RoundRectDrawable getCardBackground(CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawable) ((CardView.AnonymousClass1) cardViewDelegate).mCardBackground;
    }

    public void setMaxElevation(CardViewDelegate cardViewDelegate, float f) {
        RoundRectDrawable cardBackground = getCardBackground(cardViewDelegate);
        CardView.AnonymousClass1 r1 = (CardView.AnonymousClass1) cardViewDelegate;
        boolean useCompatPadding = CardView.this.getUseCompatPadding();
        boolean preventCornerOverlap = r1.getPreventCornerOverlap();
        if (!(f == cardBackground.mPadding && cardBackground.mInsetForPadding == useCompatPadding && cardBackground.mInsetForRadius == preventCornerOverlap)) {
            cardBackground.mPadding = f;
            cardBackground.mInsetForPadding = useCompatPadding;
            cardBackground.mInsetForRadius = preventCornerOverlap;
            cardBackground.updateBounds(null);
            cardBackground.invalidateSelf();
        }
        updatePadding(cardViewDelegate);
    }

    public void updatePadding(CardViewDelegate cardViewDelegate) {
        CardView.AnonymousClass1 r0 = (CardView.AnonymousClass1) cardViewDelegate;
        if (!CardView.this.getUseCompatPadding()) {
            r0.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float f = getCardBackground(cardViewDelegate).mPadding;
        float f2 = getCardBackground(cardViewDelegate).mRadius;
        int ceil = (int) Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(f, f2, r0.getPreventCornerOverlap()));
        int ceil2 = (int) Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(f, f2, r0.getPreventCornerOverlap()));
        r0.setShadowPadding(ceil, ceil2, ceil, ceil2);
    }
}
