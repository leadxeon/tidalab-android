package com.tidalab.v2board.clash.log;

import android.content.Context;
import java.io.BufferedWriter;
import java.io.Writer;
/* compiled from: LogcatFilter.kt */
/* loaded from: classes.dex */
public final class LogcatFilter extends BufferedWriter {
    public final Context context;

    public LogcatFilter(Writer writer, Context context) {
        super(writer);
        this.context = context;
    }
}
