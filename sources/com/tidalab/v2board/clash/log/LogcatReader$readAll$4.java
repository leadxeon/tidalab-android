package com.tidalab.v2board.clash.log;

import com.tidalab.v2board.clash.core.model.LogMessage;
import java.util.Date;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: LogcatReader.kt */
/* loaded from: classes.dex */
public final class LogcatReader$readAll$4 extends Lambda implements Function1<List<? extends String>, LogMessage> {
    public static final LogcatReader$readAll$4 INSTANCE = new LogcatReader$readAll$4();

    public LogcatReader$readAll$4() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public LogMessage invoke(List<? extends String> list) {
        List<? extends String> list2 = list;
        return new LogMessage(LogMessage.Level.valueOf((String) list2.get(1)), (String) list2.get(2), new Date(Long.parseLong((String) list2.get(0))));
    }
}
