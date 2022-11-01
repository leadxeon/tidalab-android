package com.tidalab.v2board.clash.log;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__IndentKt;
/* compiled from: LogcatReader.kt */
/* loaded from: classes.dex */
public final class LogcatReader$readAll$1 extends Lambda implements Function1<String, String> {
    public static final LogcatReader$readAll$1 INSTANCE = new LogcatReader$readAll$1();

    public LogcatReader$readAll$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public String invoke(String str) {
        return StringsKt__IndentKt.trim(str).toString();
    }
}
