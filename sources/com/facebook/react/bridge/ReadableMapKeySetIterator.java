package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public interface ReadableMapKeySetIterator {
    boolean hasNextKey();

    String nextKey();
}
