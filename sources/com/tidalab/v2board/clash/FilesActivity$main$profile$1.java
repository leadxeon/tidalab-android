package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.model.Profile;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: FilesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.FilesActivity$main$profile$1", f = "FilesActivity.kt", l = {29}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesActivity$main$profile$1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Profile>, Object> {
    public final /* synthetic */ UUID $uuid;
    public /* synthetic */ Object L$0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesActivity$main$profile$1(UUID uuid, Continuation<? super FilesActivity$main$profile$1> continuation) {
        super(2, continuation);
        this.$uuid = uuid;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FilesActivity$main$profile$1 filesActivity$main$profile$1 = new FilesActivity$main$profile$1(this.$uuid, continuation);
        filesActivity$main$profile$1.L$0 = obj;
        return filesActivity$main$profile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IProfileManager iProfileManager, Continuation<? super Profile> continuation) {
        FilesActivity$main$profile$1 filesActivity$main$profile$1 = new FilesActivity$main$profile$1(this.$uuid, continuation);
        filesActivity$main$profile$1.L$0 = iProfileManager;
        return filesActivity$main$profile$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            UUID uuid = this.$uuid;
            this.label = 1;
            obj = ((IProfileManager) this.L$0).queryByUUID(uuid, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
