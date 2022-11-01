package com.tidalab.v2board.clash;

import android.content.Intent;
import android.net.Uri;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.BaseActivity;
import com.tidalab.v2board.clash.design.LogsDesign;
import com.tidalab.v2board.clash.design.LogsDesign$requestDeleteAll$2;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: LogsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogsActivity$main$2$2", f = "LogsActivity.kt", l = {45, 46}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogsActivity$main$2$2 extends SuspendLambda implements Function2<LogsDesign.Request, Continuation<? super Unit>, Object> {
    public final /* synthetic */ LogsDesign $design;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ LogsActivity this$0;

    /* compiled from: LogsActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.LogsActivity$main$2$2$1", f = "LogsActivity.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.LogsActivity$main$2$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ LogsActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LogsActivity logsActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = logsActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            Continuation<? super Unit> continuation2 = continuation;
            LogsActivity logsActivity = this.this$0;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            int i = LogsActivity.$r8$clinit;
            Objects.requireNonNull(logsActivity);
            FilesKt__UtilsKt.deleteRecursively(InputKt.getLogsDir(logsActivity));
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            LogsActivity logsActivity = this.this$0;
            int i = LogsActivity.$r8$clinit;
            Objects.requireNonNull(logsActivity);
            FilesKt__UtilsKt.deleteRecursively(InputKt.getLogsDir(logsActivity));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogsActivity$main$2$2(LogsActivity logsActivity, LogsDesign logsDesign, Continuation<? super LogsActivity$main$2$2> continuation) {
        super(2, continuation);
        this.this$0 = logsActivity;
        this.$design = logsDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        LogsActivity$main$2$2 logsActivity$main$2$2 = new LogsActivity$main$2$2(this.this$0, this.$design, continuation);
        logsActivity$main$2$2.L$0 = obj;
        return logsActivity$main$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(LogsDesign.Request request, Continuation<? super Unit> continuation) {
        LogsActivity$main$2$2 logsActivity$main$2$2 = new LogsActivity$main$2$2(this.this$0, this.$design, continuation);
        logsActivity$main$2$2.L$0 = request;
        return logsActivity$main$2$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            LogsDesign.Request request = (LogsDesign.Request) this.L$0;
            if (Intrinsics.areEqual(request, LogsDesign.Request.StartLogcat.INSTANCE)) {
                this.this$0.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(LogcatActivity.class)));
                this.this$0.finish();
            } else if (Intrinsics.areEqual(request, LogsDesign.Request.DeleteAll.INSTANCE)) {
                LogsDesign logsDesign = this.$design;
                this.label = 1;
                Objects.requireNonNull(logsDesign);
                Dispatchers dispatchers = Dispatchers.INSTANCE;
                obj = InputKt.withContext(MainDispatcherLoader.dispatcher, new LogsDesign$requestDeleteAll$2(logsDesign, null), this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (request instanceof LogsDesign.Request.OpenFile) {
                LogsActivity logsActivity = this.this$0;
                Intent intent = PathParser.getIntent(Reflection.getOrCreateKotlinClass(LogcatActivity.class));
                intent.setData(Uri.fromParts("file", ((LogsDesign.Request.OpenFile) request).file.fileName, null));
                logsActivity.startActivity(intent);
            }
            return Unit.INSTANCE;
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else if (i == 2) {
            InputKt.throwOnFailure(obj);
            this.this$0.events.mo14trySendJP2dKIU(BaseActivity.Event.ActivityStart);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (((Boolean) obj).booleanValue()) {
            Dispatchers dispatchers2 = Dispatchers.INSTANCE;
            CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
            AnonymousClass1 r1 = new AnonymousClass1(this.this$0, null);
            this.label = 2;
            if (InputKt.withContext(coroutineDispatcher, r1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            this.this$0.events.mo14trySendJP2dKIU(BaseActivity.Event.ActivityStart);
        }
        return Unit.INSTANCE;
    }
}
