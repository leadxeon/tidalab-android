package com.facebook.cache.common;

import android.net.Uri;
import java.util.Objects;
/* loaded from: classes.dex */
public class SimpleCacheKey implements CacheKey {
    public final String mKey;

    public SimpleCacheKey(String str) {
        Objects.requireNonNull(str);
        this.mKey = str;
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean containsUri(Uri uri) {
        return this.mKey.contains(uri.toString());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SimpleCacheKey) {
            return this.mKey.equals(((SimpleCacheKey) obj).mKey);
        }
        return false;
    }

    @Override // com.facebook.cache.common.CacheKey
    public String getUriString() {
        return this.mKey;
    }

    public int hashCode() {
        return this.mKey.hashCode();
    }

    public String toString() {
        return this.mKey;
    }
}
