package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.LogFile;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: LogsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogsActivity$main$2$1$files$1", f = "LogsActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogsActivity$main$2$1$files$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends LogFile>>, Object> {
    public final /* synthetic */ LogsActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogsActivity$main$2$1$files$1(LogsActivity logsActivity, Continuation<? super LogsActivity$main$2$1$files$1> continuation) {
        super(2, continuation);
        this.this$0 = logsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LogsActivity$main$2$1$files$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends LogFile>> continuation) {
        Continuation<? super List<? extends LogFile>> continuation2 = continuation;
        LogsActivity logsActivity = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        int i = LogsActivity.$r8$clinit;
        File[] listFiles = FilesKt__UtilsKt.resolve(logsActivity.getCacheDir(), "logs").listFiles();
        Iterable<File> list = listFiles == null ? null : ArraysKt___ArraysKt.toList(listFiles);
        if (list == null) {
            list = EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        for (File file : list) {
            LogFile logFile = LogFile.Companion;
            LogFile parseFromFileName = LogFile.parseFromFileName(file.getName());
            if (parseFromFileName != null) {
                arrayList.add(parseFromFileName);
            }
        }
        return arrayList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        LogsActivity logsActivity = this.this$0;
        int i = LogsActivity.$r8$clinit;
        File[] listFiles = FilesKt__UtilsKt.resolve(logsActivity.getCacheDir(), "logs").listFiles();
        Iterable<File> list = listFiles == null ? null : ArraysKt___ArraysKt.toList(listFiles);
        if (list == null) {
            list = EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        for (File file : list) {
            LogFile logFile = LogFile.Companion;
            LogFile parseFromFileName = LogFile.parseFromFileName(file.getName());
            if (parseFromFileName != null) {
                arrayList.add(parseFromFileName);
            }
        }
        return arrayList;
    }
}
