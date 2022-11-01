package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;
/* loaded from: classes.dex */
public final class PageTransformerAdapter extends ViewPager2.OnPageChangeCallback {
    public final LinearLayoutManager mLayoutManager;
    public ViewPager2.PageTransformer mPageTransformer;

    public PageTransformerAdapter(LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i, float f, int i2) {
        if (this.mPageTransformer != null) {
            float f2 = -f;
            for (int i3 = 0; i3 < this.mLayoutManager.getChildCount(); i3++) {
                View childAt = this.mLayoutManager.getChildAt(i3);
                if (childAt != null) {
                    this.mPageTransformer.transformPage(childAt, (this.mLayoutManager.getPosition(childAt) - i) + f2);
                } else {
                    throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i3), Integer.valueOf(this.mLayoutManager.getChildCount())));
                }
            }
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageSelected(int i) {
    }
}
