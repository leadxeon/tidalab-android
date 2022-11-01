package com.facebook.drawee.debug.listener;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.debug.DebugControllerOverlayDrawable;
/* loaded from: classes.dex */
public class ImageLoadingTimeControllerListener extends BaseControllerListener {
    public ImageLoadingTimeListener mImageLoadingTimeListener;
    public long mRequestSubmitTimeMs = -1;
    public long mFinalImageSetTimeMs = -1;

    public ImageLoadingTimeControllerListener(ImageLoadingTimeListener imageLoadingTimeListener) {
        this.mImageLoadingTimeListener = imageLoadingTimeListener;
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onFinalImageSet(String str, Object obj, Animatable animatable) {
        long currentTimeMillis = System.currentTimeMillis();
        this.mFinalImageSetTimeMs = currentTimeMillis;
        ImageLoadingTimeListener imageLoadingTimeListener = this.mImageLoadingTimeListener;
        if (imageLoadingTimeListener != null) {
            DebugControllerOverlayDrawable debugControllerOverlayDrawable = (DebugControllerOverlayDrawable) imageLoadingTimeListener;
            debugControllerOverlayDrawable.mFinalImageTimeMs = currentTimeMillis - this.mRequestSubmitTimeMs;
            debugControllerOverlayDrawable.invalidateSelf();
        }
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onSubmit(String str, Object obj) {
        this.mRequestSubmitTimeMs = System.currentTimeMillis();
    }
}
