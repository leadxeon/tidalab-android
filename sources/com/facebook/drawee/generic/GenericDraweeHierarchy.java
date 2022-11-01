package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.recyclerview.R$dimen;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.FadeDrawable;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.MatrixDrawable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.drawable.ScalingUtils$ScaleTypeFitXY;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes.dex */
public class GenericDraweeHierarchy implements SettableDraweeHierarchy {
    public final ForwardingDrawable mActualImageWrapper;
    public final Drawable mEmptyActualImageDrawable;
    public final FadeDrawable mFadeDrawable;
    public final Resources mResources;
    public RoundingParams mRoundingParams;
    public final RootDrawable mTopLevelDrawable;

    public GenericDraweeHierarchy(GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder) {
        ColorDrawable colorDrawable = new ColorDrawable(0);
        this.mEmptyActualImageDrawable = colorDrawable;
        FrescoSystrace.isTracing();
        this.mResources = genericDraweeHierarchyBuilder.mResources;
        this.mRoundingParams = genericDraweeHierarchyBuilder.mRoundingParams;
        ForwardingDrawable forwardingDrawable = new ForwardingDrawable(colorDrawable);
        this.mActualImageWrapper = forwardingDrawable;
        Drawable[] drawableArr = new Drawable[7];
        drawableArr[0] = buildBranch(null, null);
        drawableArr[1] = buildBranch(null, genericDraweeHierarchyBuilder.mPlaceholderImageScaleType);
        ScalingUtils$ScaleType scalingUtils$ScaleType = genericDraweeHierarchyBuilder.mActualImageScaleType;
        forwardingDrawable.setColorFilter(null);
        drawableArr[2] = WrappingUtils.maybeWrapWithScaleType(forwardingDrawable, scalingUtils$ScaleType, null);
        drawableArr[3] = buildBranch(null, genericDraweeHierarchyBuilder.mProgressBarImageScaleType);
        drawableArr[4] = buildBranch(null, genericDraweeHierarchyBuilder.mRetryImageScaleType);
        drawableArr[5] = buildBranch(null, genericDraweeHierarchyBuilder.mFailureImageScaleType);
        FadeDrawable fadeDrawable = new FadeDrawable(drawableArr);
        this.mFadeDrawable = fadeDrawable;
        fadeDrawable.mDurationMs = genericDraweeHierarchyBuilder.mFadeDuration;
        if (fadeDrawable.mTransitionState == 1) {
            fadeDrawable.mTransitionState = 0;
        }
        RootDrawable rootDrawable = new RootDrawable(WrappingUtils.maybeWrapWithRoundedOverlayColor(fadeDrawable, this.mRoundingParams));
        this.mTopLevelDrawable = rootDrawable;
        rootDrawable.mutate();
        resetFade();
        FrescoSystrace.isTracing();
    }

    public final Drawable buildBranch(Drawable drawable, ScalingUtils$ScaleType scalingUtils$ScaleType) {
        return WrappingUtils.maybeWrapWithScaleType(WrappingUtils.maybeApplyLeafRounding(null, this.mRoundingParams, this.mResources), scalingUtils$ScaleType, null);
    }

    public final void fadeInLayer(int i) {
        if (i >= 0) {
            FadeDrawable fadeDrawable = this.mFadeDrawable;
            fadeDrawable.mTransitionState = 0;
            fadeDrawable.mIsLayerOn[i] = true;
            fadeDrawable.invalidateSelf();
        }
    }

    public final void fadeOutBranches() {
        fadeOutLayer(1);
        fadeOutLayer(2);
        fadeOutLayer(3);
        fadeOutLayer(4);
        fadeOutLayer(5);
    }

    public final void fadeOutLayer(int i) {
        if (i >= 0) {
            FadeDrawable fadeDrawable = this.mFadeDrawable;
            fadeDrawable.mTransitionState = 0;
            fadeDrawable.mIsLayerOn[i] = false;
            fadeDrawable.invalidateSelf();
        }
    }

    public final DrawableParent getParentDrawableAtIndex(final int i) {
        final FadeDrawable fadeDrawable = this.mFadeDrawable;
        Objects.requireNonNull(fadeDrawable);
        boolean z = false;
        R$dimen.checkArgument(i >= 0);
        if (i < fadeDrawable.mDrawableParents.length) {
            z = true;
        }
        R$dimen.checkArgument(z);
        DrawableParent[] drawableParentArr = fadeDrawable.mDrawableParents;
        if (drawableParentArr[i] == null) {
            drawableParentArr[i] = new DrawableParent() { // from class: com.facebook.drawee.drawable.ArrayDrawable.1
                @Override // com.facebook.drawee.drawable.DrawableParent
                public Drawable getDrawable() {
                    return ArrayDrawable.this.getDrawable(i);
                }

                @Override // com.facebook.drawee.drawable.DrawableParent
                public Drawable setDrawable(Drawable drawable) {
                    return ArrayDrawable.this.setDrawable(i, drawable);
                }
            };
        }
        DrawableParent drawableParent = drawableParentArr[i];
        if (drawableParent.getDrawable() instanceof MatrixDrawable) {
            drawableParent = (MatrixDrawable) drawableParent.getDrawable();
        }
        return drawableParent.getDrawable() instanceof ScaleTypeDrawable ? (ScaleTypeDrawable) drawableParent.getDrawable() : drawableParent;
    }

    public final ScaleTypeDrawable getScaleTypeDrawableAtIndex(int i) {
        DrawableParent parentDrawableAtIndex = getParentDrawableAtIndex(i);
        if (parentDrawableAtIndex instanceof ScaleTypeDrawable) {
            return (ScaleTypeDrawable) parentDrawableAtIndex;
        }
        int i2 = ScalingUtils$ScaleType.$r8$clinit;
        Drawable maybeWrapWithScaleType = WrappingUtils.maybeWrapWithScaleType(parentDrawableAtIndex.setDrawable(WrappingUtils.sEmptyDrawable), ScalingUtils$ScaleTypeFitXY.INSTANCE, null);
        parentDrawableAtIndex.setDrawable(maybeWrapWithScaleType);
        R$dimen.checkNotNull(maybeWrapWithScaleType, "Parent has no child drawable!");
        return (ScaleTypeDrawable) maybeWrapWithScaleType;
    }

    @Override // com.facebook.drawee.interfaces.DraweeHierarchy
    public Drawable getTopLevelDrawable() {
        return this.mTopLevelDrawable;
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void reset() {
        this.mActualImageWrapper.setCurrent(this.mEmptyActualImageDrawable);
        resetFade();
    }

    public final void resetFade() {
        FadeDrawable fadeDrawable = this.mFadeDrawable;
        if (fadeDrawable != null) {
            fadeDrawable.beginBatchMode();
            FadeDrawable fadeDrawable2 = this.mFadeDrawable;
            fadeDrawable2.mTransitionState = 0;
            Arrays.fill(fadeDrawable2.mIsLayerOn, true);
            fadeDrawable2.invalidateSelf();
            fadeOutBranches();
            fadeInLayer(1);
            this.mFadeDrawable.finishTransitionImmediately();
            this.mFadeDrawable.endBatchMode();
        }
    }

    public final void setChildDrawableAtIndex(int i, Drawable drawable) {
        if (drawable == null) {
            this.mFadeDrawable.setDrawable(i, null);
            return;
        }
        getParentDrawableAtIndex(i).setDrawable(WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources));
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setControllerOverlay(Drawable drawable) {
        RootDrawable rootDrawable = this.mTopLevelDrawable;
        rootDrawable.mControllerOverlay = drawable;
        rootDrawable.invalidateSelf();
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setFailure(Throwable th) {
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(5) != null) {
            fadeInLayer(5);
        } else {
            fadeInLayer(1);
        }
        this.mFadeDrawable.endBatchMode();
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setImage(Drawable drawable, float f, boolean z) {
        Drawable maybeApplyLeafRounding = WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources);
        maybeApplyLeafRounding.mutate();
        this.mActualImageWrapper.setCurrent(maybeApplyLeafRounding);
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        fadeInLayer(2);
        setProgress(f);
        if (z) {
            this.mFadeDrawable.finishTransitionImmediately();
        }
        this.mFadeDrawable.endBatchMode();
    }

    public final void setProgress(float f) {
        Drawable drawable = this.mFadeDrawable.getDrawable(3);
        if (drawable != null) {
            if (f >= 0.999f) {
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).stop();
                }
                fadeOutLayer(3);
            } else {
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
                fadeInLayer(3);
            }
            drawable.setLevel(Math.round(f * 10000.0f));
        }
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setRetry(Throwable th) {
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(4) != null) {
            fadeInLayer(4);
        } else {
            fadeInLayer(1);
        }
        this.mFadeDrawable.endBatchMode();
    }

    @Override // com.facebook.drawee.interfaces.SettableDraweeHierarchy
    public void setProgress(float f, boolean z) {
        if (this.mFadeDrawable.getDrawable(3) != null) {
            this.mFadeDrawable.beginBatchMode();
            setProgress(f);
            if (z) {
                this.mFadeDrawable.finishTransitionImmediately();
            }
            this.mFadeDrawable.endBatchMode();
        }
    }
}
