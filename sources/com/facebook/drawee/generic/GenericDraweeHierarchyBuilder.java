package com.facebook.drawee.generic;

import android.content.res.Resources;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.drawable.ScalingUtils$ScaleTypeCenterCrop;
import com.facebook.drawee.drawable.ScalingUtils$ScaleTypeCenterInside;
/* loaded from: classes.dex */
public class GenericDraweeHierarchyBuilder {
    public ScalingUtils$ScaleType mFailureImageScaleType;
    public ScalingUtils$ScaleType mPlaceholderImageScaleType;
    public ScalingUtils$ScaleType mProgressBarImageScaleType;
    public Resources mResources;
    public ScalingUtils$ScaleType mRetryImageScaleType;
    public static final ScalingUtils$ScaleType DEFAULT_SCALE_TYPE = ScalingUtils$ScaleTypeCenterInside.INSTANCE;
    public static final ScalingUtils$ScaleType DEFAULT_ACTUAL_IMAGE_SCALE_TYPE = ScalingUtils$ScaleTypeCenterCrop.INSTANCE;
    public int mFadeDuration = 300;
    public ScalingUtils$ScaleType mActualImageScaleType = DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
    public RoundingParams mRoundingParams = null;

    static {
        int i = ScalingUtils$ScaleType.$r8$clinit;
    }

    public GenericDraweeHierarchyBuilder(Resources resources) {
        this.mResources = resources;
        ScalingUtils$ScaleType scalingUtils$ScaleType = DEFAULT_SCALE_TYPE;
        this.mPlaceholderImageScaleType = scalingUtils$ScaleType;
        this.mRetryImageScaleType = scalingUtils$ScaleType;
        this.mFailureImageScaleType = scalingUtils$ScaleType;
        this.mProgressBarImageScaleType = scalingUtils$ScaleType;
    }
}
