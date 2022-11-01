package com.facebook.cache.common;

import android.net.Uri;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.List;
/* loaded from: classes.dex */
public class MultiCacheKey implements CacheKey {
    public final List<CacheKey> mCacheKeys;

    public MultiCacheKey(List<CacheKey> list) {
        this.mCacheKeys = list;
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean containsUri(Uri uri) {
        for (int i = 0; i < this.mCacheKeys.size(); i++) {
            if (this.mCacheKeys.get(i).containsUri(uri)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MultiCacheKey) {
            return this.mCacheKeys.equals(((MultiCacheKey) obj).mCacheKeys);
        }
        return false;
    }

    @Override // com.facebook.cache.common.CacheKey
    public String getUriString() {
        return this.mCacheKeys.get(0).getUriString();
    }

    public int hashCode() {
        return this.mCacheKeys.hashCode();
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("MultiCacheKey:");
        outline13.append(this.mCacheKeys.toString());
        return outline13.toString();
    }
}
