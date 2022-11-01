package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.tidalab.v2board.clash.design.ProxyDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
/* loaded from: classes.dex */
public abstract class DesignProxyBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActivityBarLayout activityBarLayout;
    public final View elevationView;
    public final TextView emptyView;
    public ProxyDesign mSelf;
    public final ImageView menuView;
    public final ViewPager2 pagesView;
    public final TabLayout tabLayoutView;
    public final FloatingActionButton urlTestFloatView;
    public final FrameLayout urlTestLayout;
    public final ProgressBar urlTestProgressView;
    public final ImageView urlTestView;

    public DesignProxyBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, View view2, TextView textView, ImageView imageView, ViewPager2 viewPager2, TabLayout tabLayout, FloatingActionButton floatingActionButton, FrameLayout frameLayout, ProgressBar progressBar, ImageView imageView2) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.elevationView = view2;
        this.emptyView = textView;
        this.menuView = imageView;
        this.pagesView = viewPager2;
        this.tabLayoutView = tabLayout;
        this.urlTestFloatView = floatingActionButton;
        this.urlTestLayout = frameLayout;
        this.urlTestProgressView = progressBar;
        this.urlTestView = imageView2;
    }

    public abstract void setSelf(ProxyDesign proxyDesign);
}
