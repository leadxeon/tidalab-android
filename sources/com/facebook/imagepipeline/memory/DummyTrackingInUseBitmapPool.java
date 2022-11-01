package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public class DummyTrackingInUseBitmapPool implements BitmapPool {
    public final Set<Bitmap> mInUseValues = Collections.newSetFromMap(new IdentityHashMap());

    @Override // com.facebook.common.memory.Pool
    public Bitmap get(int i) {
        Bitmap createBitmap = Bitmap.createBitmap(1, (int) Math.ceil(i / 2.0d), Bitmap.Config.RGB_565);
        this.mInUseValues.add(createBitmap);
        return createBitmap;
    }

    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    public void release(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        Objects.requireNonNull(bitmap);
        this.mInUseValues.remove(bitmap);
        bitmap.recycle();
    }
}
