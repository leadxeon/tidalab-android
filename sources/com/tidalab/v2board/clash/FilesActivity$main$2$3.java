package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.FilesDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: FilesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.FilesActivity$main$2$3", f = "FilesActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesActivity$main$2$3 extends SuspendLambda implements Function2<Long, Continuation<? super Unit>, Object> {
    public final /* synthetic */ FilesDesign $design;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesActivity$main$2$3(FilesDesign filesDesign, Continuation<? super FilesActivity$main$2$3> continuation) {
        super(2, continuation);
        this.$design = filesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesActivity$main$2$3(this.$design, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Long l, Continuation<? super Unit> continuation) {
        l.longValue();
        Continuation<? super Unit> continuation2 = continuation;
        FilesDesign filesDesign = this.$design;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        filesDesign.adapter.currentTime.update();
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        this.$design.adapter.currentTime.update();
        return Unit.INSTANCE;
    }
}
