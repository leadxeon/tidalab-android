package androidx.appcompat.graphics.drawable;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import androidx.appcompat.graphics.drawable.DrawableContainer;
@SuppressLint({"RestrictedAPI"})
/* loaded from: classes.dex */
public class StateListDrawable extends DrawableContainer {
    public boolean mMutated;
    public StateListState mStateListState;

    /* loaded from: classes.dex */
    public static class StateListState extends DrawableContainer.DrawableContainerState {
        public int[][] mStateSets;

        public StateListState(StateListState stateListState, StateListDrawable stateListDrawable, Resources resources) {
            super(stateListState, stateListDrawable, resources);
            if (stateListState != null) {
                this.mStateSets = stateListState.mStateSets;
            } else {
                this.mStateSets = new int[this.mDrawables.length];
            }
        }

        public int indexOfStateSet(int[] iArr) {
            int[][] iArr2 = this.mStateSets;
            int i = this.mNumChildren;
            for (int i2 = 0; i2 < i; i2++) {
                if (StateSet.stateSetMatches(iArr2[i2], iArr)) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableContainer.DrawableContainerState
        public void mutate() {
            int[][] iArr = this.mStateSets;
            int[][] iArr2 = new int[iArr.length];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[][] iArr3 = this.mStateSets;
                iArr2[length] = iArr3[length] != null ? (int[]) iArr3[length].clone() : null;
            }
            this.mStateSets = iArr2;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new StateListDrawable(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new StateListDrawable(this, resources);
        }
    }

    public StateListDrawable(StateListState stateListState, Resources resources) {
        setConstantState(new StateListState(stateListState, this, resources));
        onStateChange(getState());
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated) {
            super.mutate();
            if (this == this) {
                this.mStateListState.mutate();
                this.mMutated = true;
            }
        }
        return this;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        int indexOfStateSet = this.mStateListState.indexOfStateSet(iArr);
        if (indexOfStateSet < 0) {
            indexOfStateSet = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD);
        }
        return selectDrawable(indexOfStateSet) || onStateChange;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainer
    public void setConstantState(DrawableContainer.DrawableContainerState drawableContainerState) {
        this.mDrawableContainerState = drawableContainerState;
        int i = this.mCurIndex;
        if (i >= 0) {
            Drawable child = drawableContainerState.getChild(i);
            this.mCurrDrawable = child;
            if (child != null) {
                initializeDrawableForDisplay(child);
            }
        }
        this.mLastDrawable = null;
        if (drawableContainerState instanceof StateListState) {
            this.mStateListState = (StateListState) drawableContainerState;
        }
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableContainer
    /* renamed from: cloneConstantState */
    public StateListState mo0cloneConstantState() {
        return new StateListState(this.mStateListState, this, null);
    }

    public StateListDrawable(StateListState stateListState) {
    }
}
