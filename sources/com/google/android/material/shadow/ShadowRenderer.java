package com.google.android.material.shadow;

import android.graphics.Paint;
import android.graphics.Path;
import androidx.core.graphics.ColorUtils;
/* loaded from: classes.dex */
public class ShadowRenderer {
    public final Paint cornerShadowPaint;
    public final Paint edgeShadowPaint;
    public int shadowEndColor;
    public int shadowMiddleColor;
    public int shadowStartColor;
    public static final int[] edgeColors = new int[3];
    public static final float[] edgePositions = {0.0f, 0.5f, 1.0f};
    public static final int[] cornerColors = new int[4];
    public static final float[] cornerPositions = {0.0f, 0.0f, 0.5f, 1.0f};
    public final Path scratch = new Path();
    public Paint transparentPaint = new Paint();
    public final Paint shadowPaint = new Paint();

    public ShadowRenderer() {
        setShadowColor(-16777216);
        this.transparentPaint.setColor(0);
        Paint paint = new Paint(4);
        this.cornerShadowPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.edgeShadowPaint = new Paint(paint);
    }

    public void setShadowColor(int i) {
        this.shadowStartColor = ColorUtils.setAlphaComponent(i, 68);
        this.shadowMiddleColor = ColorUtils.setAlphaComponent(i, 20);
        this.shadowEndColor = ColorUtils.setAlphaComponent(i, 0);
        this.shadowPaint.setColor(this.shadowStartColor);
    }
}
