package com.facebook.imagepipeline.datasource;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.horcrux.svg.ImageView;
import com.horcrux.svg.SvgView;
/* loaded from: classes.dex */
public abstract class BaseBitmapDataSubscriber extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
    @Override // com.facebook.datasource.BaseDataSubscriber
    public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (dataSource.isFinished()) {
            CloseableReference<CloseableImage> result = dataSource.getResult();
            if (result != null && (result.get() instanceof CloseableBitmap)) {
                ((CloseableBitmap) result.get()).getUnderlyingBitmap();
            }
            try {
                ImageView.AnonymousClass1 r0 = (ImageView.AnonymousClass1) this;
                ImageView.this.mLoading.set(false);
                SvgView svgView = ImageView.this.getSvgView();
                if (svgView != null) {
                    svgView.invalidate();
                }
            } finally {
                Class<CloseableReference> cls = CloseableReference.TAG;
                if (result != null) {
                    result.close();
                }
            }
        }
    }
}
