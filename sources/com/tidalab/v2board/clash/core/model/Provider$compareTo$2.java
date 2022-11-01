package com.tidalab.v2board.clash.core.model;

import kotlin.jvm.internal.PropertyReference1Impl;
/* compiled from: Provider.kt */
/* loaded from: classes.dex */
public final /* synthetic */ class Provider$compareTo$2 extends PropertyReference1Impl {
    public static final Provider$compareTo$2 INSTANCE = new Provider$compareTo$2();

    public Provider$compareTo$2() {
        super(Provider.class, "name", "getName()Ljava/lang/String;", 0);
    }

    @Override // kotlin.reflect.KProperty1
    public Object get(Object obj) {
        return ((Provider) obj).name;
    }
}
