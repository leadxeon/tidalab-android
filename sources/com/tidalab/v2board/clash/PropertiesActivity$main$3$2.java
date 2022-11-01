package com.tidalab.v2board.clash;

import android.content.Intent;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.PropertiesDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
/* compiled from: PropertiesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity$main$3$2", f = "PropertiesActivity.kt", l = {62}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class PropertiesActivity$main$3$2 extends SuspendLambda implements Function2<PropertiesDesign.Request, Continuation<? super Unit>, Object> {
    public final /* synthetic */ PropertiesDesign $design;
    public final /* synthetic */ UUID $uuid;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ PropertiesActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesActivity$main$3$2(PropertiesActivity propertiesActivity, UUID uuid, PropertiesDesign propertiesDesign, Continuation<? super PropertiesActivity$main$3$2> continuation) {
        super(2, continuation);
        this.this$0 = propertiesActivity;
        this.$uuid = uuid;
        this.$design = propertiesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PropertiesActivity$main$3$2 propertiesActivity$main$3$2 = new PropertiesActivity$main$3$2(this.this$0, this.$uuid, this.$design, continuation);
        propertiesActivity$main$3$2.L$0 = obj;
        return propertiesActivity$main$3$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(PropertiesDesign.Request request, Continuation<? super Unit> continuation) {
        PropertiesActivity$main$3$2 propertiesActivity$main$3$2 = new PropertiesActivity$main$3$2(this.this$0, this.$uuid, this.$design, continuation);
        propertiesActivity$main$3$2.L$0 = request;
        return propertiesActivity$main$3$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            PropertiesDesign.Request request = (PropertiesDesign.Request) this.L$0;
            if (Intrinsics.areEqual(request, PropertiesDesign.Request.BrowseFiles.INSTANCE)) {
                PropertiesActivity propertiesActivity = this.this$0;
                Intent intent = PathParser.getIntent(Reflection.getOrCreateKotlinClass(FilesActivity.class));
                PathParser.setUUID(intent, this.$uuid);
                propertiesActivity.startActivity(intent);
            } else if (Intrinsics.areEqual(request, PropertiesDesign.Request.Commit.INSTANCE)) {
                PropertiesActivity propertiesActivity2 = this.this$0;
                PropertiesDesign propertiesDesign = this.$design;
                this.label = 1;
                if (PropertiesActivity.access$verifyAndCommit(propertiesActivity2, propertiesDesign, this) == coroutineSingletons) {
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
