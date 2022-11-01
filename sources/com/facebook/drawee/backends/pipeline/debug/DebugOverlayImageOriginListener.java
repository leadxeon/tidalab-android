package com.facebook.drawee.backends.pipeline.debug;

import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;
/* loaded from: classes.dex */
public class DebugOverlayImageOriginListener implements ImageOriginListener {
    public int mImageOrigin = 1;

    @Override // com.facebook.drawee.backends.pipeline.info.ImageOriginListener
    public void onImageLoaded(String str, int i, boolean z, String str2) {
        this.mImageOrigin = i;
    }
}
