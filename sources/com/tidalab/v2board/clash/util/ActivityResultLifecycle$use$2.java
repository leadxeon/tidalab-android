package com.tidalab.v2board.clash.util;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: Activity.kt */
/* loaded from: classes.dex */
public /* synthetic */ class ActivityResultLifecycle$use$2 extends FunctionReferenceImpl implements Function0<Unit> {
    public ActivityResultLifecycle$use$2(ActivityResultLifecycle activityResultLifecycle) {
        super(0, activityResultLifecycle, ActivityResultLifecycle.class, "markStarted", "markStarted()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public Unit invoke() {
        ActivityResultLifecycle activityResultLifecycle = (ActivityResultLifecycle) this.receiver;
        LifecycleRegistry lifecycleRegistry = activityResultLifecycle.lifecycle;
        Lifecycle.State state = Lifecycle.State.STARTED;
        lifecycleRegistry.enforceMainThreadIfNeeded("setCurrentState");
        lifecycleRegistry.moveToState(state);
        LifecycleRegistry lifecycleRegistry2 = activityResultLifecycle.lifecycle;
        Lifecycle.State state2 = Lifecycle.State.RESUMED;
        lifecycleRegistry2.enforceMainThreadIfNeeded("setCurrentState");
        lifecycleRegistry2.moveToState(state2);
        return Unit.INSTANCE;
    }
}
