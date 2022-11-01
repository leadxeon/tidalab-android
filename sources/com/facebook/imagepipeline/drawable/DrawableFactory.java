package com.facebook.imagepipeline.drawable;

import android.graphics.drawable.Drawable;
import com.facebook.imagepipeline.image.CloseableImage;
/* loaded from: classes.dex */
public interface DrawableFactory {
    Drawable createDrawable(CloseableImage closeableImage);

    boolean supportsImageType(CloseableImage closeableImage);
}
