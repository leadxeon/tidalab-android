package com.tidalab.v2board.clash;

import android.content.Intent;
import android.net.Uri;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ExternalImportActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ExternalImportActivity$onCreate$1", f = "ExternalImportActivity.kt", l = {26}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ExternalImportActivity$onCreate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Uri $uri;
    public final /* synthetic */ String $url;
    public int label;
    public final /* synthetic */ ExternalImportActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExternalImportActivity$onCreate$1(ExternalImportActivity externalImportActivity, Uri uri, String str, Continuation<? super ExternalImportActivity$onCreate$1> continuation) {
        super(2, continuation);
        this.this$0 = externalImportActivity;
        this.$uri = uri;
        this.$url = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExternalImportActivity$onCreate$1(this.this$0, this.$uri, this.$url, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ExternalImportActivity$onCreate$1(this.this$0, this.$uri, this.$url, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            ExternalImportActivity$onCreate$1$uuid$1 externalImportActivity$onCreate$1$uuid$1 = new ExternalImportActivity$onCreate$1$uuid$1(this.$uri, this.this$0, this.$url, null);
            this.label = 1;
            obj = InputKt.withProfile$default(null, externalImportActivity$onCreate$1$uuid$1, this, 1);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ExternalImportActivity externalImportActivity = this.this$0;
        Intent intent = PathParser.getIntent(Reflection.getOrCreateKotlinClass(PropertiesActivity.class));
        PathParser.setUUID(intent, (UUID) obj);
        externalImportActivity.startActivity(intent);
        this.this$0.finish();
        return Unit.INSTANCE;
    }
}
