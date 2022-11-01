package com.facebook.cache.common;

import android.net.Uri;
/* loaded from: classes.dex */
public interface CacheKey {
    boolean containsUri(Uri uri);

    String getUriString();
}
