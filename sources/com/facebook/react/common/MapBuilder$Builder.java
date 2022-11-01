package com.facebook.react.common;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class MapBuilder$Builder<K, V> {
    public Map mMap = new HashMap();
    public boolean mUnderConstruction = true;

    public MapBuilder$Builder(MapBuilder$1 mapBuilder$1) {
    }

    public Map<K, V> build() {
        if (this.mUnderConstruction) {
            this.mUnderConstruction = false;
            return this.mMap;
        }
        throw new IllegalStateException("Underlying map has already been built");
    }

    public MapBuilder$Builder<K, V> put(K k, V v) {
        if (this.mUnderConstruction) {
            this.mMap.put(k, v);
            return this;
        }
        throw new IllegalStateException("Underlying map has already been built");
    }
}
