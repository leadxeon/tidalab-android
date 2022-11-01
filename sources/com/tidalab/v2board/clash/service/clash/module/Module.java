package com.tidalab.v2board.clash.service.clash.module;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tidalab.v2board.clash.common.constants.Permissions;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.selects.SelectClause1;
/* compiled from: Module.kt */
/* loaded from: classes.dex */
public abstract class Module<E> {
    public final Channel<E> events = InputKt.Channel$default(Integer.MAX_VALUE, null, null, 6);
    public final List<BroadcastReceiver> receivers = new ArrayList();
    public final Service service;

    public Module(Service service) {
        this.service = service;
    }

    public static /* synthetic */ ReceiveChannel receiveBroadcast$default(Module module, boolean z, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = true;
        }
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return module.receiveBroadcast(z, i, function1);
    }

    public final Object enqueueEvent(E e, Continuation<? super Unit> continuation) {
        Object send = this.events.send(e, continuation);
        return send == CoroutineSingletons.COROUTINE_SUSPENDED ? send : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0083 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object execute(kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.tidalab.v2board.clash.service.clash.module.Module$execute$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.tidalab.v2board.clash.service.clash.module.Module$execute$1 r0 = (com.tidalab.v2board.clash.service.clash.module.Module$execute$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.clash.module.Module$execute$1 r0 = new com.tidalab.v2board.clash.service.clash.module.Module$execute$1
            r0.<init>(r8, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 3
            r4 = 1
            r5 = 2
            r6 = 0
            if (r2 == 0) goto L_0x004c
            if (r2 == r4) goto L_0x003e
            if (r2 == r5) goto L_0x003a
            if (r2 == r3) goto L_0x0032
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0032:
            java.lang.Object r0 = r0.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            goto L_0x009e
        L_0x003a:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            goto L_0x0084
        L_0x003e:
            java.lang.Object r2 = r0.L$1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r4 = r0.L$0
            com.tidalab.v2board.clash.service.clash.module.Module r4 = (com.tidalab.v2board.clash.service.clash.module.Module) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)     // Catch: all -> 0x004a
            goto L_0x0070
        L_0x004a:
            r9 = move-exception
            goto L_0x0089
        L_0x004c:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            java.lang.Class r9 = r8.getClass()
            java.lang.String r2 = r9.getSimpleName()
            java.lang.String r9 = ": initialize"
            java.lang.String r9 = kotlin.jvm.internal.Intrinsics.stringPlus(r2, r9)     // Catch: all -> 0x0087
            java.lang.String r7 = "ClashForAndroid"
            android.util.Log.d(r7, r9, r6)     // Catch: all -> 0x0087
            r0.L$0 = r8     // Catch: all -> 0x0087
            r0.L$1 = r2     // Catch: all -> 0x0087
            r0.label = r4     // Catch: all -> 0x0087
            java.lang.Object r9 = r8.run(r0)     // Catch: all -> 0x0087
            if (r9 != r1) goto L_0x006f
            return r1
        L_0x006f:
            r4 = r8
        L_0x0070:
            kotlinx.coroutines.NonCancellable r9 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.service.clash.module.Module$execute$2 r3 = new com.tidalab.v2board.clash.service.clash.module.Module$execute$2
            r3.<init>(r4, r2, r6)
            r0.L$0 = r6
            r0.L$1 = r6
            r0.label = r5
            java.lang.Object r9 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r9, r3, r0)
            if (r9 != r1) goto L_0x0084
            return r1
        L_0x0084:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x0087:
            r9 = move-exception
            r4 = r8
        L_0x0089:
            kotlinx.coroutines.NonCancellable r5 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.service.clash.module.Module$execute$2 r7 = new com.tidalab.v2board.clash.service.clash.module.Module$execute$2
            r7.<init>(r4, r2, r6)
            r0.L$0 = r9
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r0 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r5, r7, r0)
            if (r0 != r1) goto L_0x009d
            return r1
        L_0x009d:
            r0 = r9
        L_0x009e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.clash.module.Module.execute(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final SelectClause1<E> getOnEvent() {
        return this.events.getOnReceive();
    }

    public final ReceiveChannel<Intent> receiveBroadcast(boolean z, int i, Function1<? super IntentFilter, Unit> function1) {
        IntentFilter intentFilter = new IntentFilter();
        function1.invoke(intentFilter);
        final Channel Channel$default = InputKt.Channel$default(i, null, null, 6);
        BroadcastReceiver module$receiveBroadcast$receiver$1 = new BroadcastReceiver() { // from class: com.tidalab.v2board.clash.service.clash.module.Module$receiveBroadcast$receiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (context == null || intent == null) {
                    Channel$default.close(null);
                } else {
                    Channel$default.mo14trySendJP2dKIU(intent);
                }
            }
        };
        if (z) {
            Service service = this.service;
            Permissions permissions = Permissions.INSTANCE;
            service.registerReceiver(module$receiveBroadcast$receiver$1, intentFilter, Permissions.RECEIVE_SELF_BROADCASTS, null);
        } else {
            this.service.registerReceiver(module$receiveBroadcast$receiver$1, intentFilter);
        }
        this.receivers.add(module$receiveBroadcast$receiver$1);
        return Channel$default;
    }

    public abstract Object run(Continuation<? super Unit> continuation);
}
