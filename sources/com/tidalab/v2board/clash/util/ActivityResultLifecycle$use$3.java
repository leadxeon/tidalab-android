package com.tidalab.v2board.clash.util;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Activity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$3", f = "Activity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ActivityResultLifecycle$use$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ActivityResultLifecycle this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityResultLifecycle$use$3(ActivityResultLifecycle activityResultLifecycle, Continuation<? super ActivityResultLifecycle$use$3> continuation) {
        super(2, continuation);
        this.this$0 = activityResultLifecycle;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ActivityResultLifecycle$use$3(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        ActivityResultLifecycle activityResultLifecycle = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        LifecycleRegistry lifecycleRegistry = activityResultLifecycle.lifecycle;
        Lifecycle.State state = Lifecycle.State.DESTROYED;
        lifecycleRegistry.enforceMainThreadIfNeeded("setCurrentState");
        lifecycleRegistry.moveToState(state);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        LifecycleRegistry lifecycleRegistry = this.this$0.lifecycle;
        Lifecycle.State state = Lifecycle.State.DESTROYED;
        lifecycleRegistry.enforceMainThreadIfNeeded("setCurrentState");
        lifecycleRegistry.moveToState(state);
        return Unit.INSTANCE;
    }
}
