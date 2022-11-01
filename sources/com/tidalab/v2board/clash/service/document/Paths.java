package com.tidalab.v2board.clash.service.document;

import com.tidalab.v2board.clash.service.document.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
/* compiled from: Paths.kt */
/* loaded from: classes.dex */
public final class Paths {
    public static final Path resolve(String str) {
        Path.Scope scope = Path.Scope.Providers;
        Path.Scope scope2 = Path.Scope.Configuration;
        List split$default = StringsKt__IndentKt.split$default(str, new String[]{"/"}, false, 0, 6);
        ArrayList arrayList = new ArrayList();
        Iterator it = split$default.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            String str2 = (String) next;
            if (!(!StringsKt__IndentKt.isBlank(str2)) || Intrinsics.areEqual(str2, ".") || Intrinsics.areEqual(str2, "..")) {
                z = false;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return new Path(null, null, null);
        }
        if (size == 1) {
            return new Path(UUID.fromString((String) arrayList.get(0)), null, null);
        }
        if (size != 2) {
            UUID fromString = UUID.fromString((String) arrayList.get(0));
            String str3 = (String) arrayList.get(1);
            if (Intrinsics.areEqual(str3, "config.yaml")) {
                scope = scope2;
            } else if (!Intrinsics.areEqual(str3, "providers")) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("unknown scope ", arrayList.get(1)));
            }
            return new Path(fromString, scope, ArraysKt___ArraysKt.drop(arrayList, 2));
        }
        UUID fromString2 = UUID.fromString((String) arrayList.get(0));
        String str4 = (String) arrayList.get(1);
        if (Intrinsics.areEqual(str4, "config.yaml")) {
            scope = scope2;
        } else if (!Intrinsics.areEqual(str4, "providers")) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("unknown scope ", arrayList.get(1)));
        }
        return new Path(fromString2, scope, null);
    }
}
