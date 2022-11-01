package com.tidalab.v2board.clash.design;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import androidx.core.content.ContextCompat;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.ui.ToastDuration;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: LogcatDesign.kt */
/* loaded from: classes.dex */
public final class LogcatDesign$adapter$1 extends Lambda implements Function1<LogMessage, Unit> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ LogcatDesign this$0;

    /* compiled from: LogcatDesign.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.design.LogcatDesign$adapter$1$1", f = "LogcatDesign.kt", l = {34}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.design.LogcatDesign$adapter$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Context $context;
        public final /* synthetic */ LogMessage $it;
        public int label;
        public final /* synthetic */ LogcatDesign this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LogMessage logMessage, Context context, LogcatDesign logcatDesign, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$it = logMessage;
            this.$context = context;
            this.this$0 = logcatDesign;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$it, this.$context, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$it, this.$context, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object showToast;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                ClipData newPlainText = ClipData.newPlainText("log_message", this.$it.message);
                ClipboardManager clipboardManager = (ClipboardManager) ContextCompat.getSystemService(this.$context, ClipboardManager.class);
                if (clipboardManager != null) {
                    clipboardManager.setPrimaryClip(newPlainText);
                }
                LogcatDesign logcatDesign = this.this$0;
                ToastDuration toastDuration = ToastDuration.Short;
                this.label = 1;
                showToast = logcatDesign.showToast((int) R.string.copied, toastDuration, (r5 & 4) != 0 ? Design$showToast$2.INSTANCE : null, this);
                if (showToast == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (i == 1) {
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatDesign$adapter$1(LogcatDesign logcatDesign, Context context) {
        super(1);
        this.this$0 = logcatDesign;
        this.$context = context;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(LogMessage logMessage) {
        LogcatDesign logcatDesign = this.this$0;
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        InputKt.launch$default(logcatDesign, Dispatchers.IO, null, new AnonymousClass1(logMessage, this.$context, logcatDesign, null), 2, null);
        return Unit.INSTANCE;
    }
}
