package com.tidalab.v2board.clash.design.preference;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: EditableTextMap.kt */
/* loaded from: classes.dex */
public final class EditableTextMapKt$editableTextMap$1 extends Lambda implements Function1<EditableTextMapPreference<K, V>, Unit> {
    public static final EditableTextMapKt$editableTextMap$1 INSTANCE = new EditableTextMapKt$editableTextMap$1();

    public EditableTextMapKt$editableTextMap$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Object obj) {
        EditableTextMapPreference editableTextMapPreference = (EditableTextMapPreference) obj;
        return Unit.INSTANCE;
    }
}
