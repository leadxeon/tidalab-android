package com.tidalab.v2board.clash.util;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
/* compiled from: Activity.kt */
/* loaded from: classes.dex */
public final class ActivityResultLifecycle implements LifecycleOwner {
    public final LifecycleRegistry lifecycle;

    public ActivityResultLifecycle() {
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycle = lifecycleRegistry;
        Lifecycle.State state = Lifecycle.State.INITIALIZED;
        lifecycleRegistry.enforceMainThreadIfNeeded("setCurrentState");
        lifecycleRegistry.moveToState(state);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.lifecycle;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0094 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> java.lang.Object use(kotlin.jvm.functions.Function3<? super com.tidalab.v2board.clash.util.ActivityResultLifecycle, ? super kotlin.jvm.functions.Function0<kotlin.Unit>, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r9, kotlin.coroutines.Continuation<? super T> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$1 r0 = (com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$1 r0 = new com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 == r6) goto L_0x0040
            if (r2 == r5) goto L_0x003a
            if (r2 == r4) goto L_0x0032
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0032:
            java.lang.Object r9 = r0.L$0
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            goto L_0x0096
        L_0x003a:
            java.lang.Object r9 = r0.L$0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            goto L_0x007f
        L_0x0040:
            java.lang.Object r9 = r0.L$0
            com.tidalab.v2board.clash.util.ActivityResultLifecycle r9 = (com.tidalab.v2board.clash.util.ActivityResultLifecycle) r9
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)     // Catch: all -> 0x0048
            goto L_0x006c
        L_0x0048:
            r10 = move-exception
            goto L_0x0083
        L_0x004a:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            androidx.lifecycle.LifecycleRegistry r10 = r8.lifecycle     // Catch: all -> 0x0080
            androidx.lifecycle.Lifecycle$State r2 = androidx.lifecycle.Lifecycle.State.CREATED     // Catch: all -> 0x0080
            java.lang.String r7 = "setCurrentState"
            r10.enforceMainThreadIfNeeded(r7)     // Catch: all -> 0x0080
            r10.moveToState(r2)     // Catch: all -> 0x0080
            com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$2 r10 = new com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$2     // Catch: all -> 0x0080
            r10.<init>(r8)     // Catch: all -> 0x0080
            r0.L$0 = r8     // Catch: all -> 0x0080
            r0.label = r6     // Catch: all -> 0x0080
            com.tidalab.v2board.clash.BaseActivity$startActivityForResult$2$1 r9 = (com.tidalab.v2board.clash.BaseActivity$startActivityForResult$2.AnonymousClass1) r9     // Catch: all -> 0x0080
            java.lang.Object r10 = r9.invoke(r8, r10, r0)     // Catch: all -> 0x0080
            if (r10 != r1) goto L_0x006b
            return r1
        L_0x006b:
            r9 = r8
        L_0x006c:
            kotlinx.coroutines.NonCancellable r2 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$3 r4 = new com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$3
            r4.<init>(r9, r3)
            r0.L$0 = r10
            r0.label = r5
            java.lang.Object r9 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r2, r4, r0)
            if (r9 != r1) goto L_0x007e
            return r1
        L_0x007e:
            r9 = r10
        L_0x007f:
            return r9
        L_0x0080:
            r9 = move-exception
            r10 = r9
            r9 = r8
        L_0x0083:
            kotlinx.coroutines.NonCancellable r2 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$3 r5 = new com.tidalab.v2board.clash.util.ActivityResultLifecycle$use$3
            r5.<init>(r9, r3)
            r0.L$0 = r10
            r0.label = r4
            java.lang.Object r9 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r2, r5, r0)
            if (r9 != r1) goto L_0x0095
            return r1
        L_0x0095:
            r9 = r10
        L_0x0096:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.util.ActivityResultLifecycle.use(kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
