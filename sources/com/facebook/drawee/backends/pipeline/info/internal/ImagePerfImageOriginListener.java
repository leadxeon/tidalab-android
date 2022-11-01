package com.facebook.drawee.backends.pipeline.info.internal;

import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImagePerfMonitor;
import com.facebook.drawee.backends.pipeline.info.ImagePerfState;
/* loaded from: classes.dex */
public class ImagePerfImageOriginListener implements ImageOriginListener {
    public final ImagePerfMonitor mImagePerfMonitor;
    public final ImagePerfState mImagePerfState;

    public ImagePerfImageOriginListener(ImagePerfState imagePerfState, ImagePerfMonitor imagePerfMonitor) {
        this.mImagePerfState = imagePerfState;
        this.mImagePerfMonitor = imagePerfMonitor;
    }

    @Override // com.facebook.drawee.backends.pipeline.info.ImageOriginListener
    public void onImageLoaded(String str, int i, boolean z, String str2) {
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.mImageOrigin = i;
        imagePerfState.mUltimateProducerName = str2;
        this.mImagePerfMonitor.notifyStatusUpdated(imagePerfState, 1);
    }
}
