package com.tidalab.v2board.clash.service.clash.module;

import android.content.Intent;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SideloadDatabaseModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.SideloadDatabaseModule$run$2$1", f = "SideloadDatabaseModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SideloadDatabaseModule$run$2$1 extends SuspendLambda implements Function2<Intent, Continuation<? super Pair<? extends Boolean, ? extends Boolean>>, Object> {
    public /* synthetic */ Object L$0;
    public final /* synthetic */ SideloadDatabaseModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SideloadDatabaseModule$run$2$1(SideloadDatabaseModule sideloadDatabaseModule, Continuation<? super SideloadDatabaseModule$run$2$1> continuation) {
        super(2, continuation);
        this.this$0 = sideloadDatabaseModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SideloadDatabaseModule$run$2$1 sideloadDatabaseModule$run$2$1 = new SideloadDatabaseModule$run$2$1(this.this$0, continuation);
        sideloadDatabaseModule$run$2$1.L$0 = obj;
        return sideloadDatabaseModule$run$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Intent intent, Continuation<? super Pair<? extends Boolean, ? extends Boolean>> continuation) {
        SideloadDatabaseModule$run$2$1 sideloadDatabaseModule$run$2$1 = new SideloadDatabaseModule$run$2$1(this.this$0, continuation);
        sideloadDatabaseModule$run$2$1.L$0 = intent;
        return sideloadDatabaseModule$run$2$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        Intent intent = (Intent) this.L$0;
        String action = intent.getAction();
        if (action != null) {
            int hashCode = action.hashCode();
            if (hashCode != -810471698) {
                if (hashCode != 1544582882) {
                    if (hashCode == 1580442797 && action.equals("android.intent.action.PACKAGE_FULLY_REMOVED")) {
                        return new Pair(Boolean.valueOf(Intrinsics.areEqual(InputKt.getPackageName(intent), this.this$0.current)), Boolean.TRUE);
                    }
                } else if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                    return new Pair(Boolean.valueOf(Intrinsics.areEqual(InputKt.getPackageName(intent), this.this$0.store.getSideloadGeoip())), Boolean.TRUE);
                }
            } else if (action.equals("android.intent.action.PACKAGE_REPLACED")) {
                return new Pair(Boolean.valueOf(Intrinsics.areEqual(InputKt.getPackageName(intent), this.this$0.current)), Boolean.TRUE);
            }
        }
        Boolean bool = Boolean.FALSE;
        return new Pair(bool, bool);
    }
}
