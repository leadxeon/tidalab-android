package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;
import com.tidalab.v2board.clash.foss.R;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {
    public View mActionBarView;
    public Drawable mBackground;
    public View mContextView;
    public int mHeight;
    public boolean mIsSplit;
    public boolean mIsStacked;
    public boolean mIsTransitioning;
    public Drawable mSplitBackground;
    public Drawable mStackedBackground;
    public View mTabContainer;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ActionBarBackgroundDrawable actionBarBackgroundDrawable = new ActionBarBackgroundDrawable(this);
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        setBackground(actionBarBackgroundDrawable);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionBar);
        boolean z = false;
        this.mBackground = obtainStyledAttributes.getDrawable(0);
        this.mStackedBackground = obtainStyledAttributes.getDrawable(2);
        this.mHeight = obtainStyledAttributes.getDimensionPixelSize(13, -1);
        if (getId() == R.id.split_action_bar) {
            this.mIsSplit = true;
            this.mSplitBackground = obtainStyledAttributes.getDrawable(1);
        }
        obtainStyledAttributes.recycle();
        if (!this.mIsSplit ? this.mBackground == null && this.mStackedBackground == null : this.mSplitBackground == null) {
            z = true;
        }
        setWillNotDraw(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mBackground;
        if (drawable != null && drawable.isStateful()) {
            this.mBackground.setState(getDrawableState());
        }
        Drawable drawable2 = this.mStackedBackground;
        if (drawable2 != null && drawable2.isStateful()) {
            this.mStackedBackground.setState(getDrawableState());
        }
        Drawable drawable3 = this.mSplitBackground;
        if (drawable3 != null && drawable3.isStateful()) {
            this.mSplitBackground.setState(getDrawableState());
        }
    }

    public final int getMeasuredHeightWithMargins(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    public final boolean isCollapsed(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.mStackedBackground;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.mSplitBackground;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = findViewById(R.id.action_bar);
        this.mContextView = findViewById(R.id.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mIsTransitioning || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Drawable drawable;
        super.onLayout(z, i, i2, i3, i4);
        View view = this.mTabContainer;
        boolean z2 = true;
        z2 = false;
        boolean z3 = (view == null || view.getVisibility() == 8) ? false : true;
        if (!(view == null || view.getVisibility() == 8)) {
            int measuredHeight = getMeasuredHeight();
            int i5 = ((FrameLayout.LayoutParams) view.getLayoutParams()).bottomMargin;
            view.layout(i, (measuredHeight - view.getMeasuredHeight()) - i5, i3, measuredHeight - i5);
        }
        if (this.mIsSplit) {
            Drawable drawable2 = this.mSplitBackground;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z2 = false;
            }
        } else {
            if (this.mBackground != null) {
                if (this.mActionBarView.getVisibility() == 0) {
                    this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
                } else {
                    View view2 = this.mContextView;
                    if (view2 == null || view2.getVisibility() != 0) {
                        this.mBackground.setBounds(0, 0, 0, 0);
                    } else {
                        this.mBackground.setBounds(this.mContextView.getLeft(), this.mContextView.getTop(), this.mContextView.getRight(), this.mContextView.getBottom());
                    }
                }
                z2 = true;
            }
            this.mIsStacked = z3;
            if (z3 && (drawable = this.mStackedBackground) != null) {
                drawable.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
        }
        if (z2) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        if (this.mActionBarView == null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (i4 = this.mHeight) >= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i4, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        if (this.mActionBarView != null) {
            int mode = View.MeasureSpec.getMode(i2);
            View view = this.mTabContainer;
            if (view != null && view.getVisibility() != 8 && mode != 1073741824) {
                if (!isCollapsed(this.mActionBarView)) {
                    i3 = getMeasuredHeightWithMargins(this.mActionBarView);
                } else {
                    i3 = !isCollapsed(this.mContextView) ? getMeasuredHeightWithMargins(this.mContextView) : 0;
                }
                setMeasuredDimension(getMeasuredWidth(), Math.min(getMeasuredHeightWithMargins(this.mTabContainer) + i3, mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i2) : Integer.MAX_VALUE));
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.mBackground;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.mBackground);
        }
        this.mBackground = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.mActionBarView;
            if (view != null) {
                this.mBackground.setBounds(view.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
            }
        }
        boolean z = true;
        if (!this.mIsSplit ? !(this.mBackground == null && this.mStackedBackground == null) : this.mSplitBackground != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        invalidateOutline();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.mSplitBackground;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.mSplitBackground);
        }
        this.mSplitBackground = drawable;
        boolean z = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.mIsSplit && (drawable2 = this.mSplitBackground) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.mIsSplit ? this.mBackground == null && this.mStackedBackground == null : this.mSplitBackground == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
        invalidateOutline();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.mStackedBackground;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.mStackedBackground);
        }
        this.mStackedBackground = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.mIsStacked && (drawable2 = this.mStackedBackground) != null) {
                drawable2.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
            }
        }
        boolean z = true;
        if (!this.mIsSplit ? !(this.mBackground == null && this.mStackedBackground == null) : this.mSplitBackground != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
        invalidateOutline();
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.mTabContainer;
        if (view != null) {
            removeView(view);
        }
        this.mTabContainer = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.mIsTransitioning = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.mStackedBackground;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.mSplitBackground;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.mBackground && !this.mIsSplit) || (drawable == this.mStackedBackground && this.mIsStacked) || ((drawable == this.mSplitBackground && this.mIsSplit) || super.verifyDrawable(drawable));
    }
}
