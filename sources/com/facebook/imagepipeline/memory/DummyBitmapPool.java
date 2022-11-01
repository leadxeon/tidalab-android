package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import java.util.Objects;
/* loaded from: classes.dex */
public class DummyBitmapPool implements BitmapPool {
    @Override // com.facebook.common.memory.Pool
    public Bitmap get(int i) {
        return Bitmap.createBitmap(1, (int) Math.ceil(i / 2.0d), Bitmap.Config.RGB_565);
    }

    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    public void release(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        Objects.requireNonNull(bitmap);
        bitmap.recycle();
    }
}
