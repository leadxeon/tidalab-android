package com.tidalab.v2board.clash.log;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__IndentKt;
/* compiled from: LogcatReader.kt */
/* loaded from: classes.dex */
public final class LogcatReader$readAll$3 extends Lambda implements Function1<String, List<? extends String>> {
    public static final LogcatReader$readAll$3 INSTANCE = new LogcatReader$readAll$3();

    public LogcatReader$readAll$3() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public List<? extends String> invoke(String str) {
        return StringsKt__IndentKt.split$default(str, new String[]{":"}, false, 3, 2);
    }
}
