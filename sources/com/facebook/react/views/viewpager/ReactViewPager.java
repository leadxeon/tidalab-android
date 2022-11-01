package com.facebook.react.views.viewpager;

import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.facebook.common.logging.FLog;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ReactViewPager extends ViewPager {
    public final EventDispatcher mEventDispatcher;
    public boolean mScrollEnabled = true;
    public final Runnable measureAndLayout = new Runnable() { // from class: com.facebook.react.views.viewpager.ReactViewPager.1
        @Override // java.lang.Runnable
        public void run() {
            ReactViewPager reactViewPager = ReactViewPager.this;
            reactViewPager.measure(View.MeasureSpec.makeMeasureSpec(reactViewPager.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactViewPager.this.getHeight(), 1073741824));
            ReactViewPager reactViewPager2 = ReactViewPager.this;
            reactViewPager2.layout(reactViewPager2.getLeft(), ReactViewPager.this.getTop(), ReactViewPager.this.getRight(), ReactViewPager.this.getBottom());
        }
    };
    public boolean mIsCurrentItemFromJs = false;

    /* loaded from: classes.dex */
    public class Adapter extends PagerAdapter {
        public final List<View> mViews = new ArrayList();

        public Adapter(AnonymousClass1 r2) {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.mViews.size();
        }
    }

    /* loaded from: classes.dex */
    public class PageChangeListener implements ViewPager.OnPageChangeListener {
        public PageChangeListener(AnonymousClass1 r2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            String str;
            if (i == 0) {
                str = "idle";
            } else if (i == 1) {
                str = "dragging";
            } else if (i == 2) {
                str = "settling";
            } else {
                throw new IllegalStateException("Unsupported pageScrollState");
            }
            ReactViewPager reactViewPager = ReactViewPager.this;
            reactViewPager.mEventDispatcher.dispatchEvent(new PageScrollStateChangedEvent(reactViewPager.getId(), str));
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            ReactViewPager reactViewPager = ReactViewPager.this;
            reactViewPager.mEventDispatcher.dispatchEvent(new PageScrollEvent(reactViewPager.getId(), i, f));
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            ReactViewPager reactViewPager = ReactViewPager.this;
            if (!reactViewPager.mIsCurrentItemFromJs) {
                reactViewPager.mEventDispatcher.dispatchEvent(new PageSelectedEvent(reactViewPager.getId(), i));
            }
        }
    }

    public ReactViewPager(ReactContext reactContext) {
        super(reactContext);
        this.mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        setOnPageChangeListener(new PageChangeListener(null));
        setAdapter(new Adapter(null));
    }

    public int getViewCountInAdapter() {
        return getAdapter().getCount();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        requestLayout();
        post(this.measureAndLayout);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                R$style.notifyNativeGestureStarted(this, motionEvent);
                return true;
            }
        } catch (IllegalArgumentException e) {
            FLog.w("ReactNative", "Error intercepting touch event.", e);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            FLog.w("ReactNative", "Error handling touch event.", e);
            return false;
        }
    }

    public void setCurrentItemFromJs(int i, boolean z) {
        this.mIsCurrentItemFromJs = true;
        this.mPopulatePending = false;
        setCurrentItemInternal(i, z, false, 0);
        this.mIsCurrentItemFromJs = false;
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    public void setViews(List<View> list) {
        Adapter adapter = getAdapter();
        adapter.mViews.clear();
        adapter.mViews.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override // androidx.viewpager.widget.ViewPager
    public Adapter getAdapter() {
        return (Adapter) super.getAdapter();
    }
}
