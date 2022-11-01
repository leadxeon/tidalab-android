package com.google.android.material.appbar;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.view.View;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class ViewUtilsLollipop {
    public static final int[] STATE_LIST_ANIM_ATTRS = {16843848};

    public static void setDefaultAppBarLayoutStateListAnimator(View view, float f) {
        int integer = view.getResources().getInteger(R.integer.app_bar_elevation_anim_duration);
        StateListAnimator stateListAnimator = new StateListAnimator();
        long j = integer;
        stateListAnimator.addState(new int[]{16842766, R.attr.state_liftable, -2130969339}, ObjectAnimator.ofFloat(view, "elevation", 0.0f).setDuration(j));
        stateListAnimator.addState(new int[]{16842766}, ObjectAnimator.ofFloat(view, "elevation", f).setDuration(j));
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(view, "elevation", 0.0f).setDuration(0L));
        view.setStateListAnimator(stateListAnimator);
    }
}
