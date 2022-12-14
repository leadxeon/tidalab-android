package com.facebook.react.views.image;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.MultiCacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.Postprocessor;
import java.util.LinkedList;
import java.util.List;
/* loaded from: classes.dex */
public class MultiPostprocessor implements Postprocessor {
    public final List<Postprocessor> mPostprocessors;

    public MultiPostprocessor(List<Postprocessor> list) {
        this.mPostprocessors = new LinkedList(list);
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (Postprocessor postprocessor : this.mPostprocessors) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(postprocessor.getName());
        }
        sb.insert(0, "MultiPostProcessor (");
        sb.append(")");
        return sb.toString();
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public CacheKey getPostprocessorCacheKey() {
        LinkedList linkedList = new LinkedList();
        for (Postprocessor postprocessor : this.mPostprocessors) {
            linkedList.push(postprocessor.getPostprocessorCacheKey());
        }
        return new MultiCacheKey(linkedList);
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
        CloseableReference<Bitmap> closeableReference = null;
        try {
            CloseableReference<Bitmap> closeableReference2 = null;
            for (Postprocessor postprocessor : this.mPostprocessors) {
                closeableReference = postprocessor.process(closeableReference2 != null ? closeableReference2.get() : bitmap, platformBitmapFactory);
                Class<CloseableReference> cls = CloseableReference.TAG;
                if (closeableReference2 != null) {
                    closeableReference2.close();
                }
                closeableReference2 = closeableReference.clone();
            }
            CloseableReference<Bitmap> clone = closeableReference.clone();
            closeableReference.close();
            return clone;
        } catch (Throwable th) {
            Class<CloseableReference> cls2 = CloseableReference.TAG;
            if (closeableReference != null) {
                closeableReference.close();
            }
            throw th;
        }
    }
}
