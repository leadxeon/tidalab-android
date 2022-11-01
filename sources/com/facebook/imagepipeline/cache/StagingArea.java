package com.facebook.imagepipeline.cache;

import androidx.recyclerview.R$dimen;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public class StagingArea {
    public Map<CacheKey, EncodedImage> mMap = new HashMap();

    public void clearAll() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.mMap.values());
            this.mMap.clear();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            EncodedImage encodedImage = (EncodedImage) arrayList.get(i);
            if (encodedImage != null) {
                encodedImage.close();
            }
        }
    }

    public synchronized EncodedImage get(CacheKey cacheKey) {
        Objects.requireNonNull(cacheKey);
        EncodedImage encodedImage = this.mMap.get(cacheKey);
        if (encodedImage != null) {
            synchronized (encodedImage) {
                if (!EncodedImage.isValid(encodedImage)) {
                    this.mMap.remove(cacheKey);
                    FLog.w(StagingArea.class, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(encodedImage)), cacheKey.getUriString(), Integer.valueOf(System.identityHashCode(cacheKey)));
                    return null;
                }
                encodedImage = EncodedImage.cloneOrNull(encodedImage);
            }
        }
        return encodedImage;
    }

    public synchronized void put(CacheKey cacheKey, EncodedImage encodedImage) {
        R$dimen.checkArgument(EncodedImage.isValid(encodedImage));
        EncodedImage put = this.mMap.put(cacheKey, EncodedImage.cloneOrNull(encodedImage));
        if (put != null) {
            put.close();
        }
        synchronized (this) {
            this.mMap.size();
            int i = FLog.$r8$clinit;
        }
    }

    public boolean remove(CacheKey cacheKey) {
        EncodedImage remove;
        Objects.requireNonNull(cacheKey);
        synchronized (this) {
            remove = this.mMap.remove(cacheKey);
        }
        if (remove == null) {
            return false;
        }
        try {
            return remove.isValid();
        } finally {
            remove.close();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005c, code lost:
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean remove(com.facebook.cache.common.CacheKey r6, com.facebook.imagepipeline.image.EncodedImage r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.Objects.requireNonNull(r6)     // Catch: all -> 0x0069
            java.util.Objects.requireNonNull(r7)     // Catch: all -> 0x0069
            boolean r0 = com.facebook.imagepipeline.image.EncodedImage.isValid(r7)     // Catch: all -> 0x0069
            androidx.recyclerview.R$dimen.checkArgument(r0)     // Catch: all -> 0x0069
            java.util.Map<com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage> r0 = r5.mMap     // Catch: all -> 0x0069
            java.lang.Object r0 = r0.get(r6)     // Catch: all -> 0x0069
            com.facebook.imagepipeline.image.EncodedImage r0 = (com.facebook.imagepipeline.image.EncodedImage) r0     // Catch: all -> 0x0069
            r1 = 0
            if (r0 != 0) goto L_0x001b
            monitor-exit(r5)
            return r1
        L_0x001b:
            com.facebook.common.references.CloseableReference r2 = r0.getByteBufferRef()     // Catch: all -> 0x0069
            com.facebook.common.references.CloseableReference r7 = r7.getByteBufferRef()     // Catch: all -> 0x0069
            if (r2 == 0) goto L_0x005a
            if (r7 == 0) goto L_0x005a
            java.lang.Object r3 = r2.get()     // Catch: all -> 0x004f
            java.lang.Object r4 = r7.get()     // Catch: all -> 0x004f
            if (r3 == r4) goto L_0x0032
            goto L_0x005a
        L_0x0032:
            java.util.Map<com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage> r1 = r5.mMap     // Catch: all -> 0x004f
            r1.remove(r6)     // Catch: all -> 0x004f
            r7.close()     // Catch: all -> 0x0069
            r2.close()     // Catch: all -> 0x0069
            r0.close()     // Catch: all -> 0x0069
            monitor-enter(r5)     // Catch: all -> 0x0069
            java.util.Map<com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage> r6 = r5.mMap     // Catch: all -> 0x004c
            r6.size()     // Catch: all -> 0x004c
            int r6 = com.facebook.common.logging.FLog.$r8$clinit     // Catch: all -> 0x004c
            monitor-exit(r5)     // Catch: all -> 0x0069
            r6 = 1
            monitor-exit(r5)
            return r6
        L_0x004c:
            r6 = move-exception
            monitor-exit(r5)     // Catch: all -> 0x0069
            throw r6     // Catch: all -> 0x0069
        L_0x004f:
            r6 = move-exception
            r7.close()     // Catch: all -> 0x0069
            r2.close()     // Catch: all -> 0x0069
            r0.close()     // Catch: all -> 0x0069
            throw r6     // Catch: all -> 0x0069
        L_0x005a:
            if (r7 == 0) goto L_0x005f
            r7.close()     // Catch: all -> 0x0069
        L_0x005f:
            if (r2 == 0) goto L_0x0064
            r2.close()     // Catch: all -> 0x0069
        L_0x0064:
            r0.close()     // Catch: all -> 0x0069
            monitor-exit(r5)
            return r1
        L_0x0069:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.StagingArea.remove(com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage):boolean");
    }
}
