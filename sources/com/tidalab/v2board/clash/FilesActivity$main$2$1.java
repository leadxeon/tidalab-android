package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.BaseActivity;
import com.tidalab.v2board.clash.design.FilesDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.remote.FilesClient;
import java.util.Stack;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: FilesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.FilesActivity$main$2$1", f = "FilesActivity.kt", l = {48}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesActivity$main$2$1 extends SuspendLambda implements Function2<BaseActivity.Event, Continuation<? super Unit>, Object> {
    public final /* synthetic */ FilesClient $client;
    public final /* synthetic */ FilesDesign $design;
    public final /* synthetic */ String $root;
    public final /* synthetic */ Stack<String> $stack;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ FilesActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesActivity$main$2$1(FilesActivity filesActivity, FilesDesign filesDesign, FilesClient filesClient, Stack<String> stack, String str, Continuation<? super FilesActivity$main$2$1> continuation) {
        super(2, continuation);
        this.this$0 = filesActivity;
        this.$design = filesDesign;
        this.$client = filesClient;
        this.$stack = stack;
        this.$root = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FilesActivity$main$2$1 filesActivity$main$2$1 = new FilesActivity$main$2$1(this.this$0, this.$design, this.$client, this.$stack, this.$root, continuation);
        filesActivity$main$2$1.L$0 = obj;
        return filesActivity$main$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(BaseActivity.Event event, Continuation<? super Unit> continuation) {
        return ((FilesActivity$main$2$1) create(event, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            int ordinal = ((BaseActivity.Event) this.L$0).ordinal();
            if (ordinal == 1 || ordinal == 2) {
                FilesActivity filesActivity = this.this$0;
                FilesDesign filesDesign = this.$design;
                FilesClient filesClient = this.$client;
                Stack<String> stack = this.$stack;
                String str = this.$root;
                this.label = 1;
                int i2 = FilesActivity.$r8$clinit;
                if (filesActivity.fetch(filesDesign, filesClient, stack, str, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
