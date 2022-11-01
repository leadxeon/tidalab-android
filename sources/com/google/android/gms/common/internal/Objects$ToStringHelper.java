package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Objects$ToStringHelper {
    public final List<String> zzer = new ArrayList();
    public final Object zzes;

    public Objects$ToStringHelper(Object obj, zzq zzqVar) {
        Objects.requireNonNull(obj, "null reference");
        this.zzes = obj;
    }

    public final Objects$ToStringHelper add(String str, Object obj) {
        List<String> list = this.zzer;
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(valueOf.length() + str.length() + 1);
        sb.append(str);
        sb.append("=");
        sb.append(valueOf);
        list.add(sb.toString());
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(this.zzes.getClass().getSimpleName());
        sb.append('{');
        int size = this.zzer.size();
        for (int i = 0; i < size; i++) {
            sb.append(this.zzer.get(i));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
