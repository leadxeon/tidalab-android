package com.tidalab.v2board.clash.core.model;

import kotlin.jvm.internal.PropertyReference1Impl;
/* compiled from: Provider.kt */
/* loaded from: classes.dex */
public final /* synthetic */ class Provider$compareTo$1 extends PropertyReference1Impl {
    public static final Provider$compareTo$1 INSTANCE = new Provider$compareTo$1();

    public Provider$compareTo$1() {
        super(Provider.class, "type", "getType()Lcom/tidalab/v2board/clash/core/model/Provider$Type;", 0);
    }

    @Override // kotlin.reflect.KProperty1
    public Object get(Object obj) {
        return ((Provider) obj).type;
    }
}
