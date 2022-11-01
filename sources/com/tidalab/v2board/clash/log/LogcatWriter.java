package com.tidalab.v2board.clash.log;

import android.content.Context;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.LogFile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Date;
import kotlin.io.FilesKt__UtilsKt;
/* compiled from: LogcatWriter.kt */
/* loaded from: classes.dex */
public final class LogcatWriter implements AutoCloseable {
    public final LogFile file;
    public final BufferedWriter writer;

    public LogcatWriter(Context context) {
        LogFile logFile = LogFile.Companion;
        Date date = new Date();
        String format = String.format("clash-%d.log", Arrays.copyOf(new Object[]{Long.valueOf(date.getTime())}, 1));
        this.file = new LogFile(format, date);
        this.writer = new BufferedWriter(new FileWriter(FilesKt__UtilsKt.resolve(InputKt.getLogsDir(context), format)));
    }

    public final void appendMessage(LogMessage logMessage) {
        this.writer.append((CharSequence) String.format("%d:%s:%s", Arrays.copyOf(new Object[]{Long.valueOf(logMessage.time.getTime()), logMessage.level.name(), logMessage.message}, 3))).append('\n');
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.writer.close();
    }
}
