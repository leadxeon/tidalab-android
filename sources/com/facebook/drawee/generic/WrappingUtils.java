package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import androidx.recyclerview.R$dimen;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.Rounded;
import com.facebook.drawee.drawable.RoundedBitmapDrawable;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import com.facebook.drawee.drawable.RoundedCornersDrawable;
import com.facebook.drawee.drawable.RoundedNinePatchDrawable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Objects;
/* loaded from: classes.dex */
public class WrappingUtils {
    public static final Drawable sEmptyDrawable = new ColorDrawable(0);

    public static Drawable applyLeafRounding(Drawable drawable, RoundingParams roundingParams, Resources resources) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            RoundedBitmapDrawable roundedBitmapDrawable = new RoundedBitmapDrawable(resources, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint());
            applyRoundingParams(roundedBitmapDrawable, roundingParams);
            return roundedBitmapDrawable;
        } else if (drawable instanceof NinePatchDrawable) {
            RoundedNinePatchDrawable roundedNinePatchDrawable = new RoundedNinePatchDrawable((NinePatchDrawable) drawable);
            applyRoundingParams(roundedNinePatchDrawable, roundingParams);
            return roundedNinePatchDrawable;
        } else if (drawable instanceof ColorDrawable) {
            RoundedColorDrawable roundedColorDrawable = new RoundedColorDrawable(((ColorDrawable) drawable).getColor());
            applyRoundingParams(roundedColorDrawable, roundingParams);
            return roundedColorDrawable;
        } else {
            FLog.w("WrappingUtils", "Don't know how to round that drawable: %s", drawable);
            return drawable;
        }
    }

    public static void applyRoundingParams(Rounded rounded, RoundingParams roundingParams) {
        Objects.requireNonNull(roundingParams);
        rounded.setCircle(false);
        rounded.setRadii(roundingParams.mCornersRadii);
        rounded.setBorder(roundingParams.mBorderColor, roundingParams.mBorderWidth);
        rounded.setPadding(0.0f);
        rounded.setScaleDownInsideBorders(false);
        rounded.setPaintFilterBitmap(false);
    }

    public static Drawable maybeApplyLeafRounding(Drawable drawable, RoundingParams roundingParams, Resources resources) {
        try {
            FrescoSystrace.isTracing();
            if (!(drawable == null || roundingParams == null || roundingParams.mRoundingMethod != 2)) {
                if (!(drawable instanceof ForwardingDrawable)) {
                    return applyLeafRounding(drawable, roundingParams, resources);
                }
                DrawableParent drawableParent = (ForwardingDrawable) drawable;
                while (true) {
                    Drawable drawable2 = drawableParent.getDrawable();
                    if (drawable2 == drawableParent || !(drawable2 instanceof DrawableParent)) {
                        break;
                    }
                    drawableParent = (DrawableParent) drawable2;
                }
                drawableParent.setDrawable(applyLeafRounding(drawableParent.setDrawable(sEmptyDrawable), roundingParams, resources));
                return drawable;
            }
            return drawable;
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    public static Drawable maybeWrapWithRoundedOverlayColor(Drawable drawable, RoundingParams roundingParams) {
        try {
            FrescoSystrace.isTracing();
            if (!(drawable == null || roundingParams == null || roundingParams.mRoundingMethod != 1)) {
                RoundedCornersDrawable roundedCornersDrawable = new RoundedCornersDrawable(drawable);
                applyRoundingParams(roundedCornersDrawable, roundingParams);
                roundedCornersDrawable.mOverlayColor = roundingParams.mOverlayColor;
                roundedCornersDrawable.invalidateSelf();
                return roundedCornersDrawable;
            }
            return drawable;
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    public static Drawable maybeWrapWithScaleType(Drawable drawable, ScalingUtils$ScaleType scalingUtils$ScaleType, PointF pointF) {
        FrescoSystrace.isTracing();
        if (drawable == null || scalingUtils$ScaleType == null) {
            FrescoSystrace.isTracing();
            return drawable;
        }
        ScaleTypeDrawable scaleTypeDrawable = new ScaleTypeDrawable(drawable, scalingUtils$ScaleType);
        if (pointF != null && !R$dimen.equal(scaleTypeDrawable.mFocusPoint, pointF)) {
            if (scaleTypeDrawable.mFocusPoint == null) {
                scaleTypeDrawable.mFocusPoint = new PointF();
            }
            scaleTypeDrawable.mFocusPoint.set(pointF);
            scaleTypeDrawable.configureBounds();
            scaleTypeDrawable.invalidateSelf();
        }
        FrescoSystrace.isTracing();
        return scaleTypeDrawable;
    }
}
