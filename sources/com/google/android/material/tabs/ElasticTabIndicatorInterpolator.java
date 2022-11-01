package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;
/* loaded from: classes.dex */
public class ElasticTabIndicatorInterpolator extends TabIndicatorInterpolator {
    public static float accInterp(float f) {
        return (float) (1.0d - Math.cos((f * 3.141592653589793d) / 2.0d));
    }

    @Override // com.google.android.material.tabs.TabIndicatorInterpolator
    public void setIndicatorBoundsForOffset(TabLayout tabLayout, View view, View view2, float f, Drawable drawable) {
        float f2;
        float f3;
        RectF calculateIndicatorWidthForTab = TabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout, view);
        RectF calculateIndicatorWidthForTab2 = TabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout, view2);
        if (calculateIndicatorWidthForTab.left < calculateIndicatorWidthForTab2.left) {
            f3 = accInterp(f);
            f2 = (float) Math.sin((f * 3.141592653589793d) / 2.0d);
        } else {
            f3 = (float) Math.sin((f * 3.141592653589793d) / 2.0d);
            f2 = accInterp(f);
        }
        drawable.setBounds(AnimationUtils.lerp((int) calculateIndicatorWidthForTab.left, (int) calculateIndicatorWidthForTab2.left, f3), drawable.getBounds().top, AnimationUtils.lerp((int) calculateIndicatorWidthForTab.right, (int) calculateIndicatorWidthForTab2.right, f2), drawable.getBounds().bottom);
    }
}
