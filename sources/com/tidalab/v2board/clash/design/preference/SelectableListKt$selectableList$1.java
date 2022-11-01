package com.tidalab.v2board.clash.design.preference;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: SelectableList.kt */
/* loaded from: classes.dex */
public final class SelectableListKt$selectableList$1 extends Lambda implements Function1<SelectableListPreference<T>, Unit> {
    public static final SelectableListKt$selectableList$1 INSTANCE = new SelectableListKt$selectableList$1();

    public SelectableListKt$selectableList$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Object obj) {
        SelectableListPreference selectableListPreference = (SelectableListPreference) obj;
        return Unit.INSTANCE;
    }
}
