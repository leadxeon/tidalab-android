package com.google.android.material.tabs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$string;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
@ViewPager.DecorView
/* loaded from: classes.dex */
public class TabLayout extends HorizontalScrollView {
    public static final Pools$Pool<Tab> tabPool = new Pools$SynchronizedPool(16);
    public AdapterChangeListener adapterChangeListener;
    public int contentInsetStart;
    public BaseOnTabSelectedListener currentVpSelectedListener;
    public boolean inlineLabel;
    public int mode;
    public TabLayoutOnPageChangeListener pageChangeListener;
    public PagerAdapter pagerAdapter;
    public DataSetObserver pagerAdapterObserver;
    public final int requestedTabMaxWidth;
    public final int requestedTabMinWidth;
    public ValueAnimator scrollAnimator;
    public final int scrollableTabMinWidth;
    public BaseOnTabSelectedListener selectedListener;
    public Tab selectedTab;
    public boolean setupViewPagerImplicitly;
    public final SlidingTabIndicator slidingTabIndicator;
    public final int tabBackgroundResId;
    public int tabGravity;
    public ColorStateList tabIconTint;
    public PorterDuff.Mode tabIconTintMode;
    public int tabIndicatorAnimationDuration;
    public int tabIndicatorAnimationMode;
    public boolean tabIndicatorFullWidth;
    public int tabIndicatorGravity;
    public TabIndicatorInterpolator tabIndicatorInterpolator;
    public int tabPaddingBottom;
    public int tabPaddingEnd;
    public int tabPaddingStart;
    public int tabPaddingTop;
    public ColorStateList tabRippleColorStateList;
    public int tabTextAppearance;
    public ColorStateList tabTextColors;
    public float tabTextMultiLineSize;
    public float tabTextSize;
    public boolean unboundedRipple;
    public ViewPager viewPager;
    public final ArrayList<Tab> tabs = new ArrayList<>();
    public Drawable tabSelectedIndicator = new GradientDrawable();
    public int tabSelectedIndicatorColor = 0;
    public int tabMaxWidth = Integer.MAX_VALUE;
    public final ArrayList<BaseOnTabSelectedListener> selectedListeners = new ArrayList<>();
    public final Pools$Pool<TabView> tabViewPool = new Pools$SimplePool(12);

    /* loaded from: classes.dex */
    public class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {
        public boolean autoRefresh;

        public AdapterChangeListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
        public void onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.viewPager == viewPager) {
                tabLayout.setPagerAdapter(pagerAdapter2, this.autoRefresh);
            }
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface BaseOnTabSelectedListener<T extends Tab> {
        void onTabReselected(T t);

        void onTabSelected(T t);

        void onTabUnselected(T t);
    }

    /* loaded from: classes.dex */
    public interface OnTabSelectedListener extends BaseOnTabSelectedListener<Tab> {
    }

    /* loaded from: classes.dex */
    public class PagerAdapterObserver extends DataSetObserver {
        public PagerAdapterObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TabLayout.this.populateFromPagerAdapter();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayout.this.populateFromPagerAdapter();
        }
    }

    /* loaded from: classes.dex */
    public class SlidingTabIndicator extends LinearLayout {
        public ValueAnimator indicatorAnimator;
        public float selectionOffset;
        public int selectedPosition = -1;
        public int layoutDirection = -1;

        public SlidingTabIndicator(Context context) {
            super(context);
            setWillNotDraw(false);
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            int height = TabLayout.this.tabSelectedIndicator.getBounds().height();
            if (height < 0) {
                height = TabLayout.this.tabSelectedIndicator.getIntrinsicHeight();
            }
            int i = TabLayout.this.tabIndicatorGravity;
            int i2 = 0;
            if (i == 0) {
                i2 = getHeight() - height;
                height = getHeight();
            } else if (i == 1) {
                i2 = (getHeight() - height) / 2;
                height = (getHeight() + height) / 2;
            } else if (i != 2) {
                height = i != 3 ? 0 : getHeight();
            }
            if (TabLayout.this.tabSelectedIndicator.getBounds().width() > 0) {
                Rect bounds = TabLayout.this.tabSelectedIndicator.getBounds();
                TabLayout.this.tabSelectedIndicator.setBounds(bounds.left, i2, bounds.right, height);
                TabLayout tabLayout = TabLayout.this;
                Drawable drawable = tabLayout.tabSelectedIndicator;
                if (tabLayout.tabSelectedIndicatorColor != 0) {
                    drawable = AppOpsManagerCompat.wrap(drawable);
                    if (Build.VERSION.SDK_INT == 21) {
                        drawable.setColorFilter(TabLayout.this.tabSelectedIndicatorColor, PorterDuff.Mode.SRC_IN);
                    } else {
                        drawable.setTint(TabLayout.this.tabSelectedIndicatorColor);
                    }
                }
                drawable.draw(canvas);
            }
            super.draw(canvas);
        }

        public final void jumpIndicatorToSelectedPosition() {
            View childAt = getChildAt(this.selectedPosition);
            TabLayout tabLayout = TabLayout.this;
            TabIndicatorInterpolator tabIndicatorInterpolator = tabLayout.tabIndicatorInterpolator;
            Drawable drawable = tabLayout.tabSelectedIndicator;
            Objects.requireNonNull(tabIndicatorInterpolator);
            RectF calculateIndicatorWidthForTab = TabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout, childAt);
            drawable.setBounds((int) calculateIndicatorWidthForTab.left, drawable.getBounds().top, (int) calculateIndicatorWidthForTab.right, drawable.getBounds().bottom);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                jumpIndicatorToSelectedPosition();
            } else {
                updateOrRecreateIndicatorAnimation(false, this.selectedPosition, -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) == 1073741824) {
                TabLayout tabLayout = TabLayout.this;
                boolean z = true;
                if (tabLayout.tabGravity == 1 || tabLayout.mode == 2) {
                    int childCount = getChildCount();
                    int i3 = 0;
                    for (int i4 = 0; i4 < childCount; i4++) {
                        View childAt = getChildAt(i4);
                        if (childAt.getVisibility() == 0) {
                            i3 = Math.max(i3, childAt.getMeasuredWidth());
                        }
                    }
                    if (i3 > 0) {
                        if (i3 * childCount <= getMeasuredWidth() - (((int) R$style.dpToPx(getContext(), 16)) * 2)) {
                            boolean z2 = false;
                            for (int i5 = 0; i5 < childCount; i5++) {
                                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i5).getLayoutParams();
                                if (layoutParams.width != i3 || layoutParams.weight != 0.0f) {
                                    layoutParams.width = i3;
                                    layoutParams.weight = 0.0f;
                                    z2 = true;
                                }
                            }
                            z = z2;
                        } else {
                            TabLayout tabLayout2 = TabLayout.this;
                            tabLayout2.tabGravity = 0;
                            tabLayout2.updateTabViews(false);
                        }
                        if (z) {
                            super.onMeasure(i, i2);
                        }
                    }
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i) {
            super.onRtlPropertiesChanged(i);
            if (Build.VERSION.SDK_INT < 23 && this.layoutDirection != i) {
                requestLayout();
                this.layoutDirection = i;
            }
        }

        public void setSelectedIndicatorHeight(int i) {
            Rect bounds = TabLayout.this.tabSelectedIndicator.getBounds();
            TabLayout.this.tabSelectedIndicator.setBounds(bounds.left, 0, bounds.right, i);
            requestLayout();
        }

        public final void tweenIndicatorPosition(View view, View view2, float f) {
            if (view != null && view.getWidth() > 0) {
                TabLayout tabLayout = TabLayout.this;
                tabLayout.tabIndicatorInterpolator.setIndicatorBoundsForOffset(tabLayout, view, view2, f, tabLayout.tabSelectedIndicator);
            } else {
                Drawable drawable = TabLayout.this.tabSelectedIndicator;
                drawable.setBounds(-1, drawable.getBounds().top, -1, TabLayout.this.tabSelectedIndicator.getBounds().bottom);
            }
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            postInvalidateOnAnimation();
        }

        public final void updateOrRecreateIndicatorAnimation(boolean z, final int i, int i2) {
            final View childAt = getChildAt(this.selectedPosition);
            final View childAt2 = getChildAt(i);
            if (childAt2 == null) {
                jumpIndicatorToSelectedPosition();
                return;
            }
            ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.tabs.TabLayout.SlidingTabIndicator.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SlidingTabIndicator.this.tweenIndicatorPosition(childAt, childAt2, valueAnimator.getAnimatedFraction());
                }
            };
            if (z) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.indicatorAnimator = valueAnimator;
                valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                valueAnimator.setDuration(i2);
                valueAnimator.setFloatValues(0.0f, 1.0f);
                valueAnimator.addUpdateListener(animatorUpdateListener);
                valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.tabs.TabLayout.SlidingTabIndicator.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        SlidingTabIndicator.this.selectedPosition = i;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        SlidingTabIndicator.this.selectedPosition = i;
                    }
                });
                valueAnimator.start();
                return;
            }
            this.indicatorAnimator.removeAllUpdateListeners();
            this.indicatorAnimator.addUpdateListener(animatorUpdateListener);
        }
    }

    /* loaded from: classes.dex */
    public static class Tab {
        public CharSequence contentDesc;
        public View customView;
        public Drawable icon;
        public TabLayout parent;
        public CharSequence text;
        public TabView view;
        public int position = -1;
        public int id = -1;

        public Tab setText(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.contentDesc) && !TextUtils.isEmpty(charSequence)) {
                this.view.setContentDescription(charSequence);
            }
            this.text = charSequence;
            updateView();
            return this;
        }

        public void updateView() {
            TabView tabView = this.view;
            if (tabView != null) {
                tabView.update();
            }
        }
    }

    /* loaded from: classes.dex */
    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        public int previousScrollState;
        public int scrollState;
        public final WeakReference<TabLayout> tabLayoutRef;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
            this.tabLayoutRef = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            this.previousScrollState = this.scrollState;
            this.scrollState = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout != null) {
                int i3 = this.scrollState;
                boolean z = false;
                boolean z2 = i3 != 2 || this.previousScrollState == 1;
                if (!(i3 == 2 && this.previousScrollState == 0)) {
                    z = true;
                }
                tabLayout.setScrollPosition(i, f, z2, z);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != i && i < tabLayout.getTabCount()) {
                int i2 = this.scrollState;
                tabLayout.selectTab(tabLayout.getTabAt(i), i2 == 0 || (i2 == 2 && this.previousScrollState == 0));
            }
        }
    }

    /* loaded from: classes.dex */
    public final class TabView extends LinearLayout {
        public static final /* synthetic */ int $r8$clinit = 0;
        public View badgeAnchorView;
        public BadgeDrawable badgeDrawable;
        public Drawable baseBackgroundDrawable;
        public ImageView customIconView;
        public TextView customTextView;
        public View customView;
        public int defaultMaxLines = 2;
        public ImageView iconView;
        public Tab tab;
        public TextView textView;

        public TabView(Context context) {
            super(context);
            PointerIconCompat pointerIconCompat;
            updateBackgroundDrawable(context);
            int i = TabLayout.this.tabPaddingStart;
            int i2 = TabLayout.this.tabPaddingTop;
            int i3 = TabLayout.this.tabPaddingEnd;
            int i4 = TabLayout.this.tabPaddingBottom;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            setPaddingRelative(i, i2, i3, i4);
            setGravity(17);
            setOrientation(!TabLayout.this.inlineLabel ? 1 : 0);
            setClickable(true);
            Context context2 = getContext();
            int i5 = Build.VERSION.SDK_INT;
            if (i5 >= 24) {
                pointerIconCompat = new PointerIconCompat(PointerIcon.getSystemIcon(context2, RNCWebViewManager.COMMAND_CLEAR_HISTORY));
            } else {
                pointerIconCompat = new PointerIconCompat(null);
            }
            if (i5 >= 24) {
                setPointerIcon((PointerIcon) pointerIconCompat.mPointerIcon);
            }
        }

        private BadgeDrawable getBadge() {
            return this.badgeDrawable;
        }

        private BadgeDrawable getOrCreateBadge() {
            if (this.badgeDrawable == null) {
                Context context = getContext();
                BadgeDrawable badgeDrawable = new BadgeDrawable(context);
                int[] iArr = R$styleable.Badge;
                FrameLayout frameLayout = null;
                ThemeEnforcement.checkCompatibleTheme(context, null, R.attr.badgeStyle, 2131886728);
                ThemeEnforcement.checkTextAppearance(context, null, iArr, R.attr.badgeStyle, 2131886728, new int[0]);
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, iArr, R.attr.badgeStyle, 2131886728);
                int i = obtainStyledAttributes.getInt(4, 4);
                BadgeDrawable.SavedState savedState = badgeDrawable.savedState;
                if (savedState.maxCharacterCount != i) {
                    savedState.maxCharacterCount = i;
                    badgeDrawable.maxBadgeNumber = ((int) Math.pow(10.0d, i - 1.0d)) - 1;
                    badgeDrawable.textDrawableHelper.textWidthDirty = true;
                    badgeDrawable.updateCenterAndBounds();
                    badgeDrawable.invalidateSelf();
                }
                if (obtainStyledAttributes.hasValue(5)) {
                    int max = Math.max(0, obtainStyledAttributes.getInt(5, 0));
                    BadgeDrawable.SavedState savedState2 = badgeDrawable.savedState;
                    if (savedState2.number != max) {
                        savedState2.number = max;
                        badgeDrawable.textDrawableHelper.textWidthDirty = true;
                        badgeDrawable.updateCenterAndBounds();
                        badgeDrawable.invalidateSelf();
                    }
                }
                int defaultColor = R$style.getColorStateList(context, obtainStyledAttributes, 0).getDefaultColor();
                badgeDrawable.savedState.backgroundColor = defaultColor;
                ColorStateList valueOf = ColorStateList.valueOf(defaultColor);
                MaterialShapeDrawable materialShapeDrawable = badgeDrawable.shapeDrawable;
                if (materialShapeDrawable.drawableState.fillColor != valueOf) {
                    materialShapeDrawable.setFillColor(valueOf);
                    badgeDrawable.invalidateSelf();
                }
                if (obtainStyledAttributes.hasValue(2)) {
                    int defaultColor2 = R$style.getColorStateList(context, obtainStyledAttributes, 2).getDefaultColor();
                    badgeDrawable.savedState.badgeTextColor = defaultColor2;
                    if (badgeDrawable.textDrawableHelper.textPaint.getColor() != defaultColor2) {
                        badgeDrawable.textDrawableHelper.textPaint.setColor(defaultColor2);
                        badgeDrawable.invalidateSelf();
                    }
                }
                int i2 = obtainStyledAttributes.getInt(1, 8388661);
                BadgeDrawable.SavedState savedState3 = badgeDrawable.savedState;
                if (savedState3.badgeGravity != i2) {
                    savedState3.badgeGravity = i2;
                    WeakReference<View> weakReference = badgeDrawable.anchorViewRef;
                    if (!(weakReference == null || weakReference.get() == null)) {
                        View view = badgeDrawable.anchorViewRef.get();
                        WeakReference<FrameLayout> weakReference2 = badgeDrawable.customBadgeParentRef;
                        if (weakReference2 != null) {
                            frameLayout = weakReference2.get();
                        }
                        badgeDrawable.updateBadgeCoordinates(view, frameLayout);
                    }
                }
                badgeDrawable.savedState.horizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(3, 0);
                badgeDrawable.updateCenterAndBounds();
                badgeDrawable.savedState.verticalOffset = obtainStyledAttributes.getDimensionPixelOffset(6, 0);
                badgeDrawable.updateCenterAndBounds();
                obtainStyledAttributes.recycle();
                this.badgeDrawable = badgeDrawable;
            }
            tryUpdateBadgeAnchor();
            BadgeDrawable badgeDrawable2 = this.badgeDrawable;
            if (badgeDrawable2 != null) {
                return badgeDrawable2;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        @Override // android.view.ViewGroup, android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.baseBackgroundDrawable;
            boolean z = false;
            if (drawable != null && drawable.isStateful()) {
                z = false | this.baseBackgroundDrawable.setState(drawableState);
            }
            if (z) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public int getContentHeight() {
            View[] viewArr = {this.textView, this.iconView, this.customView};
            int i = 0;
            int i2 = 0;
            boolean z = false;
            for (int i3 = 0; i3 < 3; i3++) {
                View view = viewArr[i3];
                if (view != null && view.getVisibility() == 0) {
                    i2 = z ? Math.min(i2, view.getTop()) : view.getTop();
                    i = z ? Math.max(i, view.getBottom()) : view.getBottom();
                    z = true;
                }
            }
            return i - i2;
        }

        public int getContentWidth() {
            View[] viewArr = {this.textView, this.iconView, this.customView};
            int i = 0;
            int i2 = 0;
            boolean z = false;
            for (int i3 = 0; i3 < 3; i3++) {
                View view = viewArr[i3];
                if (view != null && view.getVisibility() == 0) {
                    i2 = z ? Math.min(i2, view.getLeft()) : view.getLeft();
                    i = z ? Math.max(i, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return i - i2;
        }

        public Tab getTab() {
            return this.tab;
        }

        public final boolean hasBadgeDrawable() {
            return this.badgeDrawable != null;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            Context context;
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            BadgeDrawable badgeDrawable = this.badgeDrawable;
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                CharSequence contentDescription = getContentDescription();
                StringBuilder sb = new StringBuilder();
                sb.append((Object) contentDescription);
                sb.append(", ");
                BadgeDrawable badgeDrawable2 = this.badgeDrawable;
                Object obj = null;
                if (badgeDrawable2.isVisible()) {
                    if (!badgeDrawable2.hasNumber()) {
                        obj = badgeDrawable2.savedState.contentDescriptionNumberless;
                    } else if (badgeDrawable2.savedState.contentDescriptionQuantityStrings > 0 && (context = badgeDrawable2.contextRef.get()) != null) {
                        int number = badgeDrawable2.getNumber();
                        int i = badgeDrawable2.maxBadgeNumber;
                        if (number <= i) {
                            obj = context.getResources().getQuantityString(badgeDrawable2.savedState.contentDescriptionQuantityStrings, badgeDrawable2.getNumber(), Integer.valueOf(badgeDrawable2.getNumber()));
                        } else {
                            obj = context.getString(badgeDrawable2.savedState.contentDescriptionExceedsMaxBadgeNumberRes, Integer.valueOf(i));
                        }
                    }
                }
                sb.append(obj);
                accessibilityNodeInfo.setContentDescription(sb.toString());
            }
            accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, this.tab.position, 1, false, isSelected()).mInfo);
            if (isSelected()) {
                accessibilityNodeInfo.setClickable(false);
                accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.mAction);
            }
            accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(R.string.item_view_role_description));
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x0094, code lost:
            if (((r0 / r2.getPaint().getTextSize()) * r2.getLineWidth(0)) > ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) goto L_0x0096;
         */
        @Override // android.widget.LinearLayout, android.view.View
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onMeasure(int r8, int r9) {
            /*
                r7 = this;
                int r0 = android.view.View.MeasureSpec.getSize(r8)
                int r1 = android.view.View.MeasureSpec.getMode(r8)
                com.google.android.material.tabs.TabLayout r2 = com.google.android.material.tabs.TabLayout.this
                int r2 = r2.getTabMaxWidth()
                if (r2 <= 0) goto L_0x001e
                if (r1 == 0) goto L_0x0014
                if (r0 <= r2) goto L_0x001e
            L_0x0014:
                com.google.android.material.tabs.TabLayout r8 = com.google.android.material.tabs.TabLayout.this
                int r8 = r8.tabMaxWidth
                r0 = -2147483648(0xffffffff80000000, float:-0.0)
                int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r0)
            L_0x001e:
                super.onMeasure(r8, r9)
                android.widget.TextView r0 = r7.textView
                if (r0 == 0) goto L_0x00a6
                com.google.android.material.tabs.TabLayout r0 = com.google.android.material.tabs.TabLayout.this
                float r0 = r0.tabTextSize
                int r1 = r7.defaultMaxLines
                android.widget.ImageView r2 = r7.iconView
                r3 = 1
                if (r2 == 0) goto L_0x0038
                int r2 = r2.getVisibility()
                if (r2 != 0) goto L_0x0038
                r1 = 1
                goto L_0x0046
            L_0x0038:
                android.widget.TextView r2 = r7.textView
                if (r2 == 0) goto L_0x0046
                int r2 = r2.getLineCount()
                if (r2 <= r3) goto L_0x0046
                com.google.android.material.tabs.TabLayout r0 = com.google.android.material.tabs.TabLayout.this
                float r0 = r0.tabTextMultiLineSize
            L_0x0046:
                android.widget.TextView r2 = r7.textView
                float r2 = r2.getTextSize()
                android.widget.TextView r4 = r7.textView
                int r4 = r4.getLineCount()
                android.widget.TextView r5 = r7.textView
                int r5 = r5.getMaxLines()
                int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r2 != 0) goto L_0x0060
                if (r5 < 0) goto L_0x00a6
                if (r1 == r5) goto L_0x00a6
            L_0x0060:
                com.google.android.material.tabs.TabLayout r5 = com.google.android.material.tabs.TabLayout.this
                int r5 = r5.mode
                r6 = 0
                if (r5 != r3) goto L_0x0097
                if (r2 <= 0) goto L_0x0097
                if (r4 != r3) goto L_0x0097
                android.widget.TextView r2 = r7.textView
                android.text.Layout r2 = r2.getLayout()
                if (r2 == 0) goto L_0x0096
                float r4 = r2.getLineWidth(r6)
                android.text.TextPaint r2 = r2.getPaint()
                float r2 = r2.getTextSize()
                float r2 = r0 / r2
                float r2 = r2 * r4
                int r4 = r7.getMeasuredWidth()
                int r5 = r7.getPaddingLeft()
                int r4 = r4 - r5
                int r5 = r7.getPaddingRight()
                int r4 = r4 - r5
                float r4 = (float) r4
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 <= 0) goto L_0x0097
            L_0x0096:
                r3 = 0
            L_0x0097:
                if (r3 == 0) goto L_0x00a6
                android.widget.TextView r2 = r7.textView
                r2.setTextSize(r6, r0)
                android.widget.TextView r0 = r7.textView
                r0.setMaxLines(r1)
                super.onMeasure(r8, r9)
            L_0x00a6:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.TabView.onMeasure(int, int):void");
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean performClick = super.performClick();
            if (this.tab == null) {
                return performClick;
            }
            if (!performClick) {
                playSoundEffect(0);
            }
            Tab tab = this.tab;
            TabLayout tabLayout = tab.parent;
            if (tabLayout != null) {
                tabLayout.selectTab(tab, true);
                return true;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            if (isSelected() != z) {
            }
            super.setSelected(z);
            TextView textView = this.textView;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.iconView;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.customView;
            if (view != null) {
                view.setSelected(z);
            }
        }

        public void setTab(Tab tab) {
            if (tab != this.tab) {
                this.tab = tab;
                update();
            }
        }

        public final void tryAttachBadgeToAnchor(View view) {
            if (hasBadgeDrawable() && view != null) {
                setClipChildren(false);
                setClipToPadding(false);
                ViewGroup viewGroup = (ViewGroup) getParent();
                if (viewGroup != null) {
                    viewGroup.setClipChildren(false);
                    viewGroup.setClipToPadding(false);
                }
                BadgeDrawable badgeDrawable = this.badgeDrawable;
                Rect rect = new Rect();
                view.getDrawingRect(rect);
                badgeDrawable.setBounds(rect);
                badgeDrawable.updateBadgeCoordinates(view, null);
                if (badgeDrawable.getCustomBadgeParent() != null) {
                    badgeDrawable.getCustomBadgeParent().setForeground(badgeDrawable);
                } else {
                    view.getOverlay().add(badgeDrawable);
                }
                this.badgeAnchorView = view;
            }
        }

        public final void tryRemoveBadgeFromAnchor() {
            if (hasBadgeDrawable()) {
                setClipChildren(true);
                setClipToPadding(true);
                ViewGroup viewGroup = (ViewGroup) getParent();
                if (viewGroup != null) {
                    viewGroup.setClipChildren(true);
                    viewGroup.setClipToPadding(true);
                }
                View view = this.badgeAnchorView;
                if (view != null) {
                    BadgeDrawable badgeDrawable = this.badgeDrawable;
                    if (badgeDrawable != null) {
                        if (badgeDrawable.getCustomBadgeParent() != null) {
                            badgeDrawable.getCustomBadgeParent().setForeground(null);
                        } else {
                            view.getOverlay().remove(badgeDrawable);
                        }
                    }
                    this.badgeAnchorView = null;
                }
            }
        }

        public final void tryUpdateBadgeAnchor() {
            Tab tab;
            Tab tab2;
            if (hasBadgeDrawable()) {
                if (this.customView != null) {
                    tryRemoveBadgeFromAnchor();
                    return;
                }
                ImageView imageView = this.iconView;
                if (imageView == null || (tab2 = this.tab) == null || tab2.icon == null) {
                    if (this.textView == null || (tab = this.tab) == null) {
                        tryRemoveBadgeFromAnchor();
                        return;
                    }
                    Objects.requireNonNull(tab);
                    View view = this.badgeAnchorView;
                    TextView textView = this.textView;
                    if (view != textView) {
                        tryRemoveBadgeFromAnchor();
                        tryAttachBadgeToAnchor(this.textView);
                        return;
                    }
                    tryUpdateBadgeDrawableBounds(textView);
                } else if (this.badgeAnchorView != imageView) {
                    tryRemoveBadgeFromAnchor();
                    tryAttachBadgeToAnchor(this.iconView);
                } else {
                    tryUpdateBadgeDrawableBounds(imageView);
                }
            }
        }

        public final void tryUpdateBadgeDrawableBounds(View view) {
            if (hasBadgeDrawable() && view == this.badgeAnchorView) {
                BadgeDrawable badgeDrawable = this.badgeDrawable;
                Rect rect = new Rect();
                view.getDrawingRect(rect);
                badgeDrawable.setBounds(rect);
                badgeDrawable.updateBadgeCoordinates(view, null);
            }
        }

        public final void update() {
            Drawable drawable;
            Tab tab = this.tab;
            Drawable drawable2 = null;
            View view = tab != null ? tab.customView : null;
            if (view != null) {
                ViewParent parent = view.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(view);
                    }
                    addView(view);
                }
                this.customView = view;
                TextView textView = this.textView;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.iconView;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.iconView.setImageDrawable(null);
                }
                TextView textView2 = (TextView) view.findViewById(16908308);
                this.customTextView = textView2;
                if (textView2 != null) {
                    this.defaultMaxLines = textView2.getMaxLines();
                }
                this.customIconView = (ImageView) view.findViewById(16908294);
            } else {
                View view2 = this.customView;
                if (view2 != null) {
                    removeView(view2);
                    this.customView = null;
                }
                this.customTextView = null;
                this.customIconView = null;
            }
            boolean z = false;
            if (this.customView == null) {
                if (this.iconView == null) {
                    ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_icon, (ViewGroup) this, false);
                    this.iconView = imageView2;
                    addView(imageView2, 0);
                }
                if (!(tab == null || (drawable = tab.icon) == null)) {
                    drawable2 = AppOpsManagerCompat.wrap(drawable).mutate();
                }
                if (drawable2 != null) {
                    drawable2.setTintList(TabLayout.this.tabIconTint);
                    PorterDuff.Mode mode = TabLayout.this.tabIconTintMode;
                    if (mode != null) {
                        drawable2.setTintMode(mode);
                    }
                }
                if (this.textView == null) {
                    TextView textView3 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_text, (ViewGroup) this, false);
                    this.textView = textView3;
                    addView(textView3);
                    this.defaultMaxLines = this.textView.getMaxLines();
                }
                AppOpsManagerCompat.setTextAppearance(this.textView, TabLayout.this.tabTextAppearance);
                ColorStateList colorStateList = TabLayout.this.tabTextColors;
                if (colorStateList != null) {
                    this.textView.setTextColor(colorStateList);
                }
                updateTextAndIcon(this.textView, this.iconView);
                tryUpdateBadgeAnchor();
                final ImageView imageView3 = this.iconView;
                if (imageView3 != null) {
                    imageView3.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.google.android.material.tabs.TabLayout.TabView.1
                        @Override // android.view.View.OnLayoutChangeListener
                        public void onLayoutChange(View view3, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                            if (imageView3.getVisibility() == 0) {
                                TabView.this.tryUpdateBadgeDrawableBounds(imageView3);
                            }
                        }
                    });
                }
                final TextView textView4 = this.textView;
                if (textView4 != null) {
                    textView4.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.google.android.material.tabs.TabLayout.TabView.1
                        @Override // android.view.View.OnLayoutChangeListener
                        public void onLayoutChange(View view3, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                            if (textView4.getVisibility() == 0) {
                                TabView.this.tryUpdateBadgeDrawableBounds(textView4);
                            }
                        }
                    });
                }
            } else {
                TextView textView5 = this.customTextView;
                if (!(textView5 == null && this.customIconView == null)) {
                    updateTextAndIcon(textView5, this.customIconView);
                }
            }
            if (tab != null && !TextUtils.isEmpty(tab.contentDesc)) {
                setContentDescription(tab.contentDesc);
            }
            if (tab != null) {
                TabLayout tabLayout = tab.parent;
                if (tabLayout != null) {
                    if (tabLayout.getSelectedTabPosition() == tab.position) {
                        z = true;
                    }
                } else {
                    throw new IllegalArgumentException("Tab not attached to a TabLayout");
                }
            }
            setSelected(z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v5, types: [android.graphics.drawable.RippleDrawable] */
        /* JADX WARN: Type inference failed for: r7v0, types: [android.widget.LinearLayout, android.view.View, com.google.android.material.tabs.TabLayout$TabView] */
        public final void updateBackgroundDrawable(Context context) {
            int i = TabLayout.this.tabBackgroundResId;
            GradientDrawable gradientDrawable = null;
            if (i != 0) {
                Drawable drawable = AppCompatResources.getDrawable(context, i);
                this.baseBackgroundDrawable = drawable;
                if (drawable != null && drawable.isStateful()) {
                    this.baseBackgroundDrawable.setState(getDrawableState());
                }
            } else {
                this.baseBackgroundDrawable = null;
            }
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setColor(0);
            if (TabLayout.this.tabRippleColorStateList != null) {
                GradientDrawable gradientDrawable3 = new GradientDrawable();
                gradientDrawable3.setCornerRadius(1.0E-5f);
                gradientDrawable3.setColor(-1);
                ColorStateList colorStateList = TabLayout.this.tabRippleColorStateList;
                ColorStateList colorStateList2 = new ColorStateList(new int[][]{RippleUtils.SELECTED_STATE_SET, StateSet.NOTHING}, new int[]{RippleUtils.getColorForState(colorStateList, RippleUtils.SELECTED_PRESSED_STATE_SET), RippleUtils.getColorForState(colorStateList, RippleUtils.PRESSED_STATE_SET)});
                boolean z = TabLayout.this.unboundedRipple;
                if (z) {
                    gradientDrawable2 = null;
                }
                if (!z) {
                    gradientDrawable = gradientDrawable3;
                }
                gradientDrawable2 = new RippleDrawable(colorStateList2, gradientDrawable2, gradientDrawable);
            }
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            setBackground(gradientDrawable2);
            TabLayout.this.invalidate();
        }

        public final void updateTextAndIcon(TextView textView, ImageView imageView) {
            Drawable drawable;
            Tab tab = this.tab;
            CharSequence charSequence = null;
            Drawable mutate = (tab == null || (drawable = tab.icon) == null) ? null : AppOpsManagerCompat.wrap(drawable).mutate();
            Tab tab2 = this.tab;
            charSequence = tab2 != null ? tab2.text : null;
            if (imageView != null) {
                if (mutate != null) {
                    imageView.setImageDrawable(mutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(charSequence);
            if (textView != null) {
                if (z) {
                    textView.setText(charSequence);
                    Objects.requireNonNull(this.tab);
                    textView.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence) null);
                }
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int dpToPx = (!z || imageView.getVisibility() != 0) ? 0 : (int) R$style.dpToPx(getContext(), 8);
                if (TabLayout.this.inlineLabel) {
                    if (dpToPx != marginLayoutParams.getMarginEnd()) {
                        marginLayoutParams.setMarginEnd(dpToPx);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (dpToPx != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = dpToPx;
                    marginLayoutParams.setMarginEnd(0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            Tab tab3 = this.tab;
            if (tab3 != null) {
                charSequence = tab3.contentDesc;
            }
            if (!z) {
            }
            R$string.setTooltipText(this, charSequence);
        }
    }

    /* loaded from: classes.dex */
    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        public final ViewPager viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(Tab tab) {
            this.viewPager.setCurrentItem(tab.position);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(Tab tab) {
        }
    }

    /* JADX WARN: Finally extract failed */
    public TabLayout(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, R.attr.tabStyle, 2131886714), attributeSet, R.attr.tabStyle);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        SlidingTabIndicator slidingTabIndicator = new SlidingTabIndicator(context2);
        this.slidingTabIndicator = slidingTabIndicator;
        super.addView(slidingTabIndicator, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.TabLayout, R.attr.tabStyle, 2131886714, 23);
        if (getBackground() instanceof ColorDrawable) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            materialShapeDrawable.setFillColor(ColorStateList.valueOf(((ColorDrawable) getBackground()).getColor()));
            materialShapeDrawable.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context2);
            materialShapeDrawable.updateZ();
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            materialShapeDrawable.setElevation(getElevation());
            setBackground(materialShapeDrawable);
        }
        setSelectedTabIndicator(R$style.getDrawable(context2, obtainStyledAttributes, 5));
        setSelectedTabIndicatorColor(obtainStyledAttributes.getColor(8, 0));
        slidingTabIndicator.setSelectedIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(11, -1));
        setSelectedTabIndicatorGravity(obtainStyledAttributes.getInt(10, 0));
        setTabIndicatorFullWidth(obtainStyledAttributes.getBoolean(9, true));
        setTabIndicatorAnimationMode(obtainStyledAttributes.getInt(7, 0));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(16, 0);
        this.tabPaddingBottom = dimensionPixelSize;
        this.tabPaddingEnd = dimensionPixelSize;
        this.tabPaddingTop = dimensionPixelSize;
        this.tabPaddingStart = dimensionPixelSize;
        this.tabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(19, dimensionPixelSize);
        this.tabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(20, this.tabPaddingTop);
        this.tabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(18, this.tabPaddingEnd);
        this.tabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(17, this.tabPaddingBottom);
        int resourceId = obtainStyledAttributes.getResourceId(23, 2131886472);
        this.tabTextAppearance = resourceId;
        TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(resourceId, androidx.appcompat.R$styleable.TextAppearance);
        try {
            this.tabTextSize = obtainStyledAttributes2.getDimensionPixelSize(0, 0);
            this.tabTextColors = R$style.getColorStateList(context2, obtainStyledAttributes2, 3);
            obtainStyledAttributes2.recycle();
            if (obtainStyledAttributes.hasValue(24)) {
                this.tabTextColors = R$style.getColorStateList(context2, obtainStyledAttributes, 24);
            }
            if (obtainStyledAttributes.hasValue(22)) {
                this.tabTextColors = new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{obtainStyledAttributes.getColor(22, 0), this.tabTextColors.getDefaultColor()});
            }
            this.tabIconTint = R$style.getColorStateList(context2, obtainStyledAttributes, 3);
            this.tabIconTintMode = R$style.parseTintMode(obtainStyledAttributes.getInt(4, -1), null);
            this.tabRippleColorStateList = R$style.getColorStateList(context2, obtainStyledAttributes, 21);
            this.tabIndicatorAnimationDuration = obtainStyledAttributes.getInt(6, 300);
            this.requestedTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(14, -1);
            this.requestedTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(13, -1);
            this.tabBackgroundResId = obtainStyledAttributes.getResourceId(0, 0);
            this.contentInsetStart = obtainStyledAttributes.getDimensionPixelSize(1, 0);
            this.mode = obtainStyledAttributes.getInt(15, 1);
            this.tabGravity = obtainStyledAttributes.getInt(2, 0);
            this.inlineLabel = obtainStyledAttributes.getBoolean(12, false);
            this.unboundedRipple = obtainStyledAttributes.getBoolean(25, false);
            obtainStyledAttributes.recycle();
            Resources resources = getResources();
            this.tabTextMultiLineSize = resources.getDimensionPixelSize(R.dimen.design_tab_text_size_2line);
            this.scrollableTabMinWidth = resources.getDimensionPixelSize(R.dimen.design_tab_scrollable_min_width);
            applyModeAndGravity();
        } catch (Throwable th) {
            obtainStyledAttributes2.recycle();
            throw th;
        }
    }

    private int getDefaultHeight() {
        int size = this.tabs.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i < size) {
                Tab tab = this.tabs.get(i);
                if (tab != null && tab.icon != null && !TextUtils.isEmpty(tab.text)) {
                    z = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        return (!z || this.inlineLabel) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i = this.requestedTabMinWidth;
        if (i != -1) {
            return i;
        }
        int i2 = this.mode;
        if (i2 == 0 || i2 == 2) {
            return this.scrollableTabMinWidth;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.slidingTabIndicator.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void setSelectedTabView(int i) {
        int childCount = this.slidingTabIndicator.getChildCount();
        if (i < childCount) {
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                boolean z = true;
                childAt.setSelected(i2 == i);
                if (i2 != i) {
                    z = false;
                }
                childAt.setActivated(z);
                i2++;
            }
        }
    }

    public void addTab(Tab tab, boolean z) {
        int size = this.tabs.size();
        if (tab.parent == this) {
            tab.position = size;
            this.tabs.add(size, tab);
            int size2 = this.tabs.size();
            while (true) {
                size++;
                if (size >= size2) {
                    break;
                }
                this.tabs.get(size).position = size;
            }
            TabView tabView = tab.view;
            tabView.setSelected(false);
            tabView.setActivated(false);
            SlidingTabIndicator slidingTabIndicator = this.slidingTabIndicator;
            int i = tab.position;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
            updateTabViewLayoutParams(layoutParams);
            slidingTabIndicator.addView(tabView, i, layoutParams);
            if (z) {
                TabLayout tabLayout = tab.parent;
                if (tabLayout != null) {
                    tabLayout.selectTab(tab, true);
                    return;
                }
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return;
        }
        throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        addViewInternal(view);
    }

    public final void addViewInternal(View view) {
        if (view instanceof TabItem) {
            TabItem tabItem = (TabItem) view;
            Tab newTab = newTab();
            Objects.requireNonNull(tabItem);
            if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
                newTab.contentDesc = tabItem.getContentDescription();
                newTab.updateView();
            }
            addTab(newTab, this.tabs.isEmpty());
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    public final void animateToTab(int i) {
        boolean z;
        if (i != -1) {
            if (getWindowToken() != null) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (isLaidOut()) {
                    SlidingTabIndicator slidingTabIndicator = this.slidingTabIndicator;
                    int childCount = slidingTabIndicator.getChildCount();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= childCount) {
                            z = false;
                            break;
                        } else if (slidingTabIndicator.getChildAt(i2).getWidth() <= 0) {
                            z = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z) {
                        int scrollX = getScrollX();
                        int calculateScrollXForTab = calculateScrollXForTab(i, 0.0f);
                        if (scrollX != calculateScrollXForTab) {
                            ensureScrollAnimator();
                            this.scrollAnimator.setIntValues(scrollX, calculateScrollXForTab);
                            this.scrollAnimator.start();
                        }
                        SlidingTabIndicator slidingTabIndicator2 = this.slidingTabIndicator;
                        int i3 = this.tabIndicatorAnimationDuration;
                        ValueAnimator valueAnimator = slidingTabIndicator2.indicatorAnimator;
                        if (valueAnimator != null && valueAnimator.isRunning()) {
                            slidingTabIndicator2.indicatorAnimator.cancel();
                        }
                        slidingTabIndicator2.updateOrRecreateIndicatorAnimation(true, i, i3);
                        return;
                    }
                }
            }
            setScrollPosition(i, 0.0f, true, true);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003c, code lost:
        if (r0 != 2) goto L_0x0052;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void applyModeAndGravity() {
        /*
            r5 = this;
            int r0 = r5.mode
            r1 = 2
            r2 = 0
            if (r0 == 0) goto L_0x000b
            if (r0 != r1) goto L_0x0009
            goto L_0x000b
        L_0x0009:
            r0 = 0
            goto L_0x0014
        L_0x000b:
            int r0 = r5.contentInsetStart
            int r3 = r5.tabPaddingStart
            int r0 = r0 - r3
            int r0 = java.lang.Math.max(r2, r0)
        L_0x0014:
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r3 = r5.slidingTabIndicator
            java.util.concurrent.atomic.AtomicInteger r4 = androidx.core.view.ViewCompat.sNextGeneratedId
            r3.setPaddingRelative(r0, r2, r2, r2)
            int r0 = r5.mode
            java.lang.String r2 = "TabLayout"
            r3 = 1
            if (r0 == 0) goto L_0x0036
            if (r0 == r3) goto L_0x0027
            if (r0 == r1) goto L_0x0027
            goto L_0x0052
        L_0x0027:
            int r0 = r5.tabGravity
            if (r0 != r1) goto L_0x0030
            java.lang.String r0 = "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead"
            android.util.Log.w(r2, r0)
        L_0x0030:
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r0 = r5.slidingTabIndicator
            r0.setGravity(r3)
            goto L_0x0052
        L_0x0036:
            int r0 = r5.tabGravity
            if (r0 == 0) goto L_0x0045
            if (r0 == r3) goto L_0x003f
            if (r0 == r1) goto L_0x004a
            goto L_0x0052
        L_0x003f:
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r0 = r5.slidingTabIndicator
            r0.setGravity(r3)
            goto L_0x0052
        L_0x0045:
            java.lang.String r0 = "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead"
            android.util.Log.w(r2, r0)
        L_0x004a:
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r0 = r5.slidingTabIndicator
            r1 = 8388611(0x800003, float:1.1754948E-38)
            r0.setGravity(r1)
        L_0x0052:
            r5.updateTabViews(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.applyModeAndGravity():void");
    }

    public final int calculateScrollXForTab(int i, float f) {
        int i2 = this.mode;
        int i3 = 0;
        if (i2 != 0 && i2 != 2) {
            return 0;
        }
        View childAt = this.slidingTabIndicator.getChildAt(i);
        int i4 = i + 1;
        View childAt2 = i4 < this.slidingTabIndicator.getChildCount() ? this.slidingTabIndicator.getChildAt(i4) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        if (childAt2 != null) {
            i3 = childAt2.getWidth();
        }
        int left = ((width / 2) + childAt.getLeft()) - (getWidth() / 2);
        int i5 = (int) ((width + i3) * 0.5f * f);
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        return getLayoutDirection() == 0 ? left + i5 : left - i5;
    }

    public final void ensureScrollAnimator() {
        if (this.scrollAnimator == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.scrollAnimator = valueAnimator;
            valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.scrollAnimator.setDuration(this.tabIndicatorAnimationDuration);
            this.scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.tabs.TabLayout.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    TabLayout.this.scrollTo(((Integer) valueAnimator2.getAnimatedValue()).intValue(), 0);
                }
            });
        }
    }

    public int getSelectedTabPosition() {
        Tab tab = this.selectedTab;
        if (tab != null) {
            return tab.position;
        }
        return -1;
    }

    public Tab getTabAt(int i) {
        if (i < 0 || i >= getTabCount()) {
            return null;
        }
        return this.tabs.get(i);
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    public int getTabGravity() {
        return this.tabGravity;
    }

    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    public int getTabIndicatorAnimationMode() {
        return this.tabIndicatorAnimationMode;
    }

    public int getTabIndicatorGravity() {
        return this.tabIndicatorGravity;
    }

    public int getTabMaxWidth() {
        return this.tabMaxWidth;
    }

    public int getTabMode() {
        return this.mode;
    }

    public ColorStateList getTabRippleColor() {
        return this.tabRippleColorStateList;
    }

    public Drawable getTabSelectedIndicator() {
        return this.tabSelectedIndicator;
    }

    public ColorStateList getTabTextColors() {
        return this.tabTextColors;
    }

    public Tab newTab() {
        Tab acquire = tabPool.acquire();
        if (acquire == null) {
            acquire = new Tab();
        }
        acquire.parent = this;
        Pools$Pool<TabView> pools$Pool = this.tabViewPool;
        TabView acquire2 = pools$Pool != null ? pools$Pool.acquire() : null;
        if (acquire2 == null) {
            acquire2 = new TabView(getContext());
        }
        acquire2.setTab(acquire);
        acquire2.setFocusable(true);
        acquire2.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(acquire.contentDesc)) {
            acquire2.setContentDescription(acquire.text);
        } else {
            acquire2.setContentDescription(acquire.contentDesc);
        }
        acquire.view = acquire2;
        int i = acquire.id;
        if (i != -1) {
            acquire2.setId(i);
        }
        return acquire;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof MaterialShapeDrawable) {
            R$style.setParentAbsoluteElevation(this, (MaterialShapeDrawable) background);
        }
        if (this.viewPager == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                setupWithViewPager((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.setupViewPagerImplicitly) {
            setupWithViewPager(null);
            this.setupViewPagerImplicitly = false;
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        TabView tabView;
        Drawable drawable;
        for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
            View childAt = this.slidingTabIndicator.getChildAt(i);
            if ((childAt instanceof TabView) && (drawable = (tabView = (TabView) childAt).baseBackgroundDrawable) != null) {
                drawable.setBounds(tabView.getLeft(), tabView.getTop(), tabView.getRight(), tabView.getBottom());
                tabView.baseBackgroundDrawable.draw(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, getTabCount(), false, 1).mInfo);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
        if (r0 != 2) goto L_0x008d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007e, code lost:
        if (r7.getMeasuredWidth() != getMeasuredWidth()) goto L_0x0080;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0080, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x008a, code lost:
        if (r7.getMeasuredWidth() < getMeasuredWidth()) goto L_0x0080;
     */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            int r1 = r6.getDefaultHeight()
            float r0 = com.google.android.material.R$style.dpToPx(r0, r1)
            int r0 = java.lang.Math.round(r0)
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 0
            r5 = 1
            if (r1 == r2) goto L_0x002e
            if (r1 == 0) goto L_0x001f
            goto L_0x0041
        L_0x001f:
            int r8 = r6.getPaddingTop()
            int r8 = r8 + r0
            int r0 = r6.getPaddingBottom()
            int r0 = r0 + r8
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            goto L_0x0041
        L_0x002e:
            int r1 = r6.getChildCount()
            if (r1 != r5) goto L_0x0041
            int r1 = android.view.View.MeasureSpec.getSize(r8)
            if (r1 < r0) goto L_0x0041
            android.view.View r1 = r6.getChildAt(r4)
            r1.setMinimumHeight(r0)
        L_0x0041:
            int r0 = android.view.View.MeasureSpec.getSize(r7)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            if (r1 == 0) goto L_0x005f
            int r1 = r6.requestedTabMaxWidth
            if (r1 <= 0) goto L_0x0050
            goto L_0x005d
        L_0x0050:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = com.google.android.material.R$style.dpToPx(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L_0x005d:
            r6.tabMaxWidth = r1
        L_0x005f:
            super.onMeasure(r7, r8)
            int r7 = r6.getChildCount()
            if (r7 != r5) goto L_0x00ad
            android.view.View r7 = r6.getChildAt(r4)
            int r0 = r6.mode
            if (r0 == 0) goto L_0x0082
            if (r0 == r5) goto L_0x0076
            r1 = 2
            if (r0 == r1) goto L_0x0082
            goto L_0x008d
        L_0x0076:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 == r1) goto L_0x008d
        L_0x0080:
            r4 = 1
            goto L_0x008d
        L_0x0082:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 >= r1) goto L_0x008d
            goto L_0x0080
        L_0x008d:
            if (r4 == 0) goto L_0x00ad
            int r0 = r6.getPaddingTop()
            int r1 = r6.getPaddingBottom()
            int r1 = r1 + r0
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            int r0 = r0.height
            int r8 = android.widget.HorizontalScrollView.getChildMeasureSpec(r8, r1, r0)
            int r0 = r6.getMeasuredWidth()
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            r7.measure(r0, r8)
        L_0x00ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onMeasure(int, int):void");
    }

    public void populateFromPagerAdapter() {
        int currentItem;
        removeAllTabs();
        PagerAdapter pagerAdapter = this.pagerAdapter;
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            for (int i = 0; i < count; i++) {
                Tab newTab = newTab();
                Objects.requireNonNull(this.pagerAdapter);
                newTab.setText(null);
                addTab(newTab, false);
            }
            ViewPager viewPager = this.viewPager;
            if (viewPager != null && count > 0 && (currentItem = viewPager.getCurrentItem()) != getSelectedTabPosition() && currentItem < getTabCount()) {
                selectTab(getTabAt(currentItem), true);
            }
        }
    }

    public void removeAllTabs() {
        int childCount = this.slidingTabIndicator.getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                break;
            }
            TabView tabView = (TabView) this.slidingTabIndicator.getChildAt(childCount);
            this.slidingTabIndicator.removeViewAt(childCount);
            if (tabView != null) {
                tabView.setTab(null);
                tabView.setSelected(false);
                this.tabViewPool.release(tabView);
            }
            requestLayout();
        }
        Iterator<Tab> it = this.tabs.iterator();
        while (it.hasNext()) {
            Tab next = it.next();
            it.remove();
            next.parent = null;
            next.view = null;
            next.icon = null;
            next.id = -1;
            next.text = null;
            next.contentDesc = null;
            next.position = -1;
            next.customView = null;
            tabPool.release(next);
        }
        this.selectedTab = null;
    }

    public void selectTab(Tab tab, boolean z) {
        Tab tab2 = this.selectedTab;
        if (tab2 != tab) {
            int i = tab != null ? tab.position : -1;
            if (z) {
                if ((tab2 == null || tab2.position == -1) && i != -1) {
                    setScrollPosition(i, 0.0f, true, true);
                } else {
                    animateToTab(i);
                }
                if (i != -1) {
                    setSelectedTabView(i);
                }
            }
            this.selectedTab = tab;
            if (tab2 != null) {
                for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
                    this.selectedListeners.get(size).onTabUnselected(tab2);
                }
            }
            if (tab != null) {
                for (int size2 = this.selectedListeners.size() - 1; size2 >= 0; size2--) {
                    this.selectedListeners.get(size2).onTabSelected(tab);
                }
            }
        } else if (tab2 != null) {
            for (int size3 = this.selectedListeners.size() - 1; size3 >= 0; size3--) {
                this.selectedListeners.get(size3).onTabReselected(tab);
            }
            animateToTab(tab.position);
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        R$style.setElevation(this, f);
    }

    public void setInlineLabel(boolean z) {
        if (this.inlineLabel != z) {
            this.inlineLabel = z;
            for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
                View childAt = this.slidingTabIndicator.getChildAt(i);
                if (childAt instanceof TabView) {
                    TabView tabView = (TabView) childAt;
                    tabView.setOrientation(!TabLayout.this.inlineLabel ? 1 : 0);
                    TextView textView = tabView.customTextView;
                    if (textView == null && tabView.customIconView == null) {
                        tabView.updateTextAndIcon(tabView.textView, tabView.iconView);
                    } else {
                        tabView.updateTextAndIcon(textView, tabView.customIconView);
                    }
                }
            }
            applyModeAndGravity();
        }
    }

    public void setInlineLabelResource(int i) {
        setInlineLabel(getResources().getBoolean(i));
    }

    @Deprecated
    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        setOnTabSelectedListener((BaseOnTabSelectedListener) onTabSelectedListener);
    }

    public void setPagerAdapter(PagerAdapter pagerAdapter, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter2 = this.pagerAdapter;
        if (!(pagerAdapter2 == null || (dataSetObserver = this.pagerAdapterObserver) == null)) {
            pagerAdapter2.mObservable.unregisterObserver(dataSetObserver);
        }
        this.pagerAdapter = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.pagerAdapterObserver == null) {
                this.pagerAdapterObserver = new PagerAdapterObserver();
            }
            pagerAdapter.mObservable.registerObserver(this.pagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        ensureScrollAnimator();
        this.scrollAnimator.addListener(animatorListener);
    }

    public void setScrollPosition(int i, float f, boolean z, boolean z2) {
        int round = Math.round(i + f);
        if (round >= 0 && round < this.slidingTabIndicator.getChildCount()) {
            if (z2) {
                SlidingTabIndicator slidingTabIndicator = this.slidingTabIndicator;
                ValueAnimator valueAnimator = slidingTabIndicator.indicatorAnimator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    slidingTabIndicator.indicatorAnimator.cancel();
                }
                slidingTabIndicator.selectedPosition = i;
                slidingTabIndicator.selectionOffset = f;
                slidingTabIndicator.tweenIndicatorPosition(slidingTabIndicator.getChildAt(i), slidingTabIndicator.getChildAt(slidingTabIndicator.selectedPosition + 1), slidingTabIndicator.selectionOffset);
            }
            ValueAnimator valueAnimator2 = this.scrollAnimator;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.scrollAnimator.cancel();
            }
            scrollTo(calculateScrollXForTab(i, f), 0);
            if (z) {
                setSelectedTabView(round);
            }
        }
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.tabSelectedIndicator != drawable) {
            if (drawable == null) {
                drawable = new GradientDrawable();
            }
            this.tabSelectedIndicator = drawable;
        }
    }

    public void setSelectedTabIndicatorColor(int i) {
        this.tabSelectedIndicatorColor = i;
    }

    public void setSelectedTabIndicatorGravity(int i) {
        if (this.tabIndicatorGravity != i) {
            this.tabIndicatorGravity = i;
            SlidingTabIndicator slidingTabIndicator = this.slidingTabIndicator;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            slidingTabIndicator.postInvalidateOnAnimation();
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i) {
        this.slidingTabIndicator.setSelectedIndicatorHeight(i);
    }

    public void setTabGravity(int i) {
        if (this.tabGravity != i) {
            this.tabGravity = i;
            applyModeAndGravity();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.tabIconTint != colorStateList) {
            this.tabIconTint = colorStateList;
            updateAllTabs();
        }
    }

    public void setTabIconTintResource(int i) {
        setTabIconTint(AppCompatResources.getColorStateList(getContext(), i));
    }

    public void setTabIndicatorAnimationMode(int i) {
        this.tabIndicatorAnimationMode = i;
        if (i == 0) {
            this.tabIndicatorInterpolator = new TabIndicatorInterpolator();
        } else if (i == 1) {
            this.tabIndicatorInterpolator = new ElasticTabIndicatorInterpolator();
        } else {
            throw new IllegalArgumentException(i + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.tabIndicatorFullWidth = z;
        SlidingTabIndicator slidingTabIndicator = this.slidingTabIndicator;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        slidingTabIndicator.postInvalidateOnAnimation();
    }

    public void setTabMode(int i) {
        if (i != this.mode) {
            this.mode = i;
            applyModeAndGravity();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.tabRippleColorStateList != colorStateList) {
            this.tabRippleColorStateList = colorStateList;
            for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
                View childAt = this.slidingTabIndicator.getChildAt(i);
                if (childAt instanceof TabView) {
                    Context context = getContext();
                    int i2 = TabView.$r8$clinit;
                    ((TabView) childAt).updateBackgroundDrawable(context);
                }
            }
        }
    }

    public void setTabRippleColorResource(int i) {
        setTabRippleColor(AppCompatResources.getColorStateList(getContext(), i));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.tabTextColors != colorStateList) {
            this.tabTextColors = colorStateList;
            updateAllTabs();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(PagerAdapter pagerAdapter) {
        setPagerAdapter(pagerAdapter, false);
    }

    public void setUnboundedRipple(boolean z) {
        if (this.unboundedRipple != z) {
            this.unboundedRipple = z;
            for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
                View childAt = this.slidingTabIndicator.getChildAt(i);
                if (childAt instanceof TabView) {
                    Context context = getContext();
                    int i2 = TabView.$r8$clinit;
                    ((TabView) childAt).updateBackgroundDrawable(context);
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i) {
        setUnboundedRipple(getResources().getBoolean(i));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        setupWithViewPager(viewPager, true, false);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public final void updateAllTabs() {
        int size = this.tabs.size();
        for (int i = 0; i < size; i++) {
            this.tabs.get(i).updateView();
        }
    }

    public final void updateTabViewLayoutParams(LinearLayout.LayoutParams layoutParams) {
        if (this.mode == 1 && this.tabGravity == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            return;
        }
        layoutParams.width = -2;
        layoutParams.weight = 0.0f;
    }

    public void updateTabViews(boolean z) {
        for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
            View childAt = this.slidingTabIndicator.getChildAt(i);
            childAt.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i) {
        addViewInternal(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void setOnTabSelectedListener(BaseOnTabSelectedListener baseOnTabSelectedListener) {
        BaseOnTabSelectedListener baseOnTabSelectedListener2 = this.selectedListener;
        if (baseOnTabSelectedListener2 != null) {
            this.selectedListeners.remove(baseOnTabSelectedListener2);
        }
        this.selectedListener = baseOnTabSelectedListener;
        if (baseOnTabSelectedListener != null && !this.selectedListeners.contains(baseOnTabSelectedListener)) {
            this.selectedListeners.add(baseOnTabSelectedListener);
        }
    }

    public final void setupWithViewPager(ViewPager viewPager, boolean z, boolean z2) {
        List<ViewPager.OnAdapterChangeListener> list;
        List<ViewPager.OnPageChangeListener> list2;
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 != null) {
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.pageChangeListener;
            if (!(tabLayoutOnPageChangeListener == null || (list2 = viewPager2.mOnPageChangeListeners) == null)) {
                list2.remove(tabLayoutOnPageChangeListener);
            }
            AdapterChangeListener adapterChangeListener = this.adapterChangeListener;
            if (!(adapterChangeListener == null || (list = this.viewPager.mAdapterChangeListeners) == null)) {
                list.remove(adapterChangeListener);
            }
        }
        BaseOnTabSelectedListener baseOnTabSelectedListener = this.currentVpSelectedListener;
        if (baseOnTabSelectedListener != null) {
            this.selectedListeners.remove(baseOnTabSelectedListener);
            this.currentVpSelectedListener = null;
        }
        if (viewPager != null) {
            this.viewPager = viewPager;
            if (this.pageChangeListener == null) {
                this.pageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener2 = this.pageChangeListener;
            tabLayoutOnPageChangeListener2.scrollState = 0;
            tabLayoutOnPageChangeListener2.previousScrollState = 0;
            if (viewPager.mOnPageChangeListeners == null) {
                viewPager.mOnPageChangeListeners = new ArrayList();
            }
            viewPager.mOnPageChangeListeners.add(tabLayoutOnPageChangeListener2);
            ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = new ViewPagerOnTabSelectedListener(viewPager);
            this.currentVpSelectedListener = viewPagerOnTabSelectedListener;
            if (!this.selectedListeners.contains(viewPagerOnTabSelectedListener)) {
                this.selectedListeners.add(viewPagerOnTabSelectedListener);
            }
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                setPagerAdapter(adapter, z);
            }
            if (this.adapterChangeListener == null) {
                this.adapterChangeListener = new AdapterChangeListener();
            }
            AdapterChangeListener adapterChangeListener2 = this.adapterChangeListener;
            adapterChangeListener2.autoRefresh = z;
            if (viewPager.mAdapterChangeListeners == null) {
                viewPager.mAdapterChangeListeners = new ArrayList();
            }
            viewPager.mAdapterChangeListeners.add(adapterChangeListener2);
            setScrollPosition(viewPager.getCurrentItem(), 0.0f, true, true);
        } else {
            this.viewPager = null;
            setPagerAdapter(null, false);
        }
        this.setupViewPagerImplicitly = z2;
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    public void setSelectedTabIndicator(int i) {
        if (i != 0) {
            setSelectedTabIndicator(AppCompatResources.getDrawable(getContext(), i));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }
}
