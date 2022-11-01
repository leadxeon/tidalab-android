package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.model.LogFile;
import kotlin.jvm.internal.PropertyReference1Impl;
/* compiled from: LogsDesign.kt */
/* loaded from: classes.dex */
public final /* synthetic */ class LogsDesign$patchLogs$3 extends PropertyReference1Impl {
    public static final LogsDesign$patchLogs$3 INSTANCE = new LogsDesign$patchLogs$3();

    public LogsDesign$patchLogs$3() {
        super(LogFile.class, "fileName", "getFileName()Ljava/lang/String;", 0);
    }

    @Override // kotlin.reflect.KProperty1
    public Object get(Object obj) {
        return ((LogFile) obj).fileName;
    }
}
