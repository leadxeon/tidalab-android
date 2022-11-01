package com.google.android.material.tabs;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.tidalab.v2board.clash.design.$$Lambda$ProxyDesign$FVyRz8ut_dh7ahqXNdN3xkrGvhc;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public final class TabLayoutMediator {
    public RecyclerView.Adapter<?> adapter;
    public boolean attached;
    public TabLayoutOnPageChangeCallback onPageChangeCallback;
    public TabLayout.OnTabSelectedListener onTabSelectedListener;
    public RecyclerView.AdapterDataObserver pagerAdapterObserver;
    public final TabConfigurationStrategy tabConfigurationStrategy;
    public final TabLayout tabLayout;
    public final ViewPager2 viewPager;

    /* loaded from: classes.dex */
    public class PagerAdapterObserver extends RecyclerView.AdapterDataObserver {
        public PagerAdapterObserver() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, Object obj) {
            TabLayoutMediator.this.populateTabsFromPagerAdapter();
        }
    }

    /* loaded from: classes.dex */
    public interface TabConfigurationStrategy {
    }

    /* loaded from: classes.dex */
    public static class TabLayoutOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {
        public final WeakReference<TabLayout> tabLayoutRef;
        public int scrollState = 0;
        public int previousScrollState = 0;

        public TabLayoutOnPageChangeCallback(TabLayout tabLayout) {
            this.tabLayoutRef = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrollStateChanged(int i) {
            this.previousScrollState = this.scrollState;
            this.scrollState = i;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
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

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageSelected(int i) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != i && i < tabLayout.getTabCount()) {
                int i2 = this.scrollState;
                tabLayout.selectTab(tabLayout.getTabAt(i), i2 == 0 || (i2 == 2 && this.previousScrollState == 0));
            }
        }
    }

    /* loaded from: classes.dex */
    public static class ViewPagerOnTabSelectedListener implements TabLayout.OnTabSelectedListener {
        public final boolean smoothScroll;
        public final ViewPager2 viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager2 viewPager2, boolean z) {
            this.viewPager = viewPager2;
            this.smoothScroll = z;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            this.viewPager.setCurrentItem(tab.position, this.smoothScroll);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }
    }

    public TabLayoutMediator(TabLayout tabLayout, ViewPager2 viewPager2, TabConfigurationStrategy tabConfigurationStrategy) {
        this.tabLayout = tabLayout;
        this.viewPager = viewPager2;
        this.tabConfigurationStrategy = tabConfigurationStrategy;
    }

    public void populateTabsFromPagerAdapter() {
        this.tabLayout.removeAllTabs();
        RecyclerView.Adapter<?> adapter = this.adapter;
        if (adapter != null) {
            int itemCount = adapter.getItemCount();
            for (int i = 0; i < itemCount; i++) {
                TabLayout.Tab newTab = this.tabLayout.newTab();
                newTab.setText((CharSequence) (($$Lambda$ProxyDesign$FVyRz8ut_dh7ahqXNdN3xkrGvhc) this.tabConfigurationStrategy).f$0.get(i));
                this.tabLayout.addTab(newTab, false);
            }
            if (itemCount > 0) {
                int min = Math.min(this.viewPager.getCurrentItem(), this.tabLayout.getTabCount() - 1);
                if (min != this.tabLayout.getSelectedTabPosition()) {
                    TabLayout tabLayout = this.tabLayout;
                    tabLayout.selectTab(tabLayout.getTabAt(min), true);
                }
            }
        }
    }
}
