package com.google.android.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import androidx.core.graphics.ColorUtils;
/* loaded from: classes.dex */
public class RippleUtils {
    public static final int[] PRESSED_STATE_SET = {16842919};
    public static final int[] HOVERED_FOCUSED_STATE_SET = {16843623, 16842908};
    public static final int[] FOCUSED_STATE_SET = {16842908};
    public static final int[] HOVERED_STATE_SET = {16843623};
    public static final int[] SELECTED_PRESSED_STATE_SET = {16842913, 16842919};
    public static final int[] SELECTED_HOVERED_FOCUSED_STATE_SET = {16842913, 16843623, 16842908};
    public static final int[] SELECTED_FOCUSED_STATE_SET = {16842913, 16842908};
    public static final int[] SELECTED_HOVERED_STATE_SET = {16842913, 16843623};
    public static final int[] SELECTED_STATE_SET = {16842913};
    public static final int[] ENABLED_PRESSED_STATE_SET = {16842910, 16842919};
    public static final String LOG_TAG = RippleUtils.class.getSimpleName();

    public static int getColorForState(ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return ColorUtils.setAlphaComponent(colorForState, Math.min(Color.alpha(colorForState) * 2, 255));
    }

    public static ColorStateList sanitizeRippleDrawableColor(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return ColorStateList.valueOf(0);
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 22 && i <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0 && Color.alpha(colorStateList.getColorForState(ENABLED_PRESSED_STATE_SET, 0)) != 0) {
            Log.w(LOG_TAG, "Use a non-transparent color for the default color as it will be used to finish ripple animations.");
        }
        return colorStateList;
    }

    public static boolean shouldDrawRippleCompat(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i : iArr) {
            if (i == 16842910) {
                z = true;
            } else if (i == 16842908 || i == 16842919 || i == 16843623) {
                z2 = true;
            }
        }
        return z && z2;
    }
}
