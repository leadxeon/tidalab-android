package com.tidalab.v2board.clash;

import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.SettingsDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
/* compiled from: SettingsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.SettingsActivity$main$2$2", f = "SettingsActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SettingsActivity$main$2$2 extends SuspendLambda implements Function2<SettingsDesign.Request, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public final /* synthetic */ SettingsActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsActivity$main$2$2(SettingsActivity settingsActivity, Continuation<? super SettingsActivity$main$2$2> continuation) {
        super(2, continuation);
        this.this$0 = settingsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SettingsActivity$main$2$2 settingsActivity$main$2$2 = new SettingsActivity$main$2$2(this.this$0, continuation);
        settingsActivity$main$2$2.L$0 = obj;
        return settingsActivity$main$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(SettingsDesign.Request request, Continuation<? super Unit> continuation) {
        SettingsDesign.Request request2 = request;
        Continuation<? super Unit> continuation2 = continuation;
        SettingsActivity settingsActivity = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        int ordinal = request2.ordinal();
        if (ordinal == 0) {
            settingsActivity.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(AppSettingsActivity.class)));
        } else if (ordinal == 1) {
            settingsActivity.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(NetworkSettingsActivity.class)));
        } else if (ordinal == 2) {
            settingsActivity.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(OverrideSettingsActivity.class)));
        }
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        int ordinal = ((SettingsDesign.Request) this.L$0).ordinal();
        if (ordinal == 0) {
            this.this$0.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(AppSettingsActivity.class)));
        } else if (ordinal == 1) {
            this.this$0.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(NetworkSettingsActivity.class)));
        } else if (ordinal == 2) {
            this.this$0.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(OverrideSettingsActivity.class)));
        }
        return Unit.INSTANCE;
    }
}
