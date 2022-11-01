package com.tidalab.v2board.clash.log;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__IndentKt;
/* compiled from: LogcatReader.kt */
/* loaded from: classes.dex */
public final class LogcatReader$readAll$2 extends Lambda implements Function1<String, Boolean> {
    public static final LogcatReader$readAll$2 INSTANCE = new LogcatReader$readAll$2();

    public LogcatReader$readAll$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Boolean invoke(String str) {
        return Boolean.valueOf(!StringsKt__IndentKt.startsWith$default(str, "#", false, 2));
    }
}
