package com.facebook.imagepipeline.cache;

import android.net.Uri;
import androidx.recyclerview.R$dimen;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import java.util.Objects;
/* loaded from: classes.dex */
public class BitmapMemoryCacheKey implements CacheKey {
    public final Object mCallerContext;
    public final int mHash;
    public final ImageDecodeOptions mImageDecodeOptions;
    public final CacheKey mPostprocessorCacheKey;
    public final String mPostprocessorName;
    public final ResizeOptions mResizeOptions;
    public final RotationOptions mRotationOptions;
    public final String mSourceString;

    public BitmapMemoryCacheKey(String str, ResizeOptions resizeOptions, RotationOptions rotationOptions, ImageDecodeOptions imageDecodeOptions, CacheKey cacheKey, String str2, Object obj) {
        Objects.requireNonNull(str);
        this.mSourceString = str;
        this.mResizeOptions = resizeOptions;
        this.mRotationOptions = rotationOptions;
        this.mImageDecodeOptions = imageDecodeOptions;
        this.mPostprocessorCacheKey = cacheKey;
        this.mPostprocessorName = str2;
        Integer valueOf = Integer.valueOf(str.hashCode());
        int i = 0;
        Integer valueOf2 = Integer.valueOf(resizeOptions != null ? resizeOptions.hashCode() : 0);
        Integer valueOf3 = Integer.valueOf(rotationOptions.hashCode());
        int hashCode = valueOf == null ? 0 : valueOf.hashCode();
        int hashCode2 = valueOf2 == null ? 0 : valueOf2.hashCode();
        int hashCode3 = valueOf3 == null ? 0 : valueOf3.hashCode();
        int hashCode4 = imageDecodeOptions == null ? 0 : imageDecodeOptions.hashCode();
        this.mHash = ((((((((((hashCode + 31) * 31) + hashCode2) * 31) + hashCode3) * 31) + hashCode4) * 31) + (cacheKey == null ? 0 : cacheKey.hashCode())) * 31) + (str2 != null ? str2.hashCode() : i);
        this.mCallerContext = obj;
        RealtimeSinceBootClock.get().now();
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean containsUri(Uri uri) {
        return this.mSourceString.contains(uri.toString());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitmapMemoryCacheKey)) {
            return false;
        }
        BitmapMemoryCacheKey bitmapMemoryCacheKey = (BitmapMemoryCacheKey) obj;
        return this.mHash == bitmapMemoryCacheKey.mHash && this.mSourceString.equals(bitmapMemoryCacheKey.mSourceString) && R$dimen.equal(this.mResizeOptions, bitmapMemoryCacheKey.mResizeOptions) && R$dimen.equal(this.mRotationOptions, bitmapMemoryCacheKey.mRotationOptions) && R$dimen.equal(this.mImageDecodeOptions, bitmapMemoryCacheKey.mImageDecodeOptions) && R$dimen.equal(this.mPostprocessorCacheKey, bitmapMemoryCacheKey.mPostprocessorCacheKey) && R$dimen.equal(this.mPostprocessorName, bitmapMemoryCacheKey.mPostprocessorName);
    }

    @Override // com.facebook.cache.common.CacheKey
    public String getUriString() {
        return this.mSourceString;
    }

    public int hashCode() {
        return this.mHash;
    }

    public String toString() {
        return String.format(null, "%s_%s_%s_%s_%s_%s_%d", this.mSourceString, this.mResizeOptions, this.mRotationOptions, this.mImageDecodeOptions, this.mPostprocessorCacheKey, this.mPostprocessorName, Integer.valueOf(this.mHash));
    }
}
