package com.tidalab.v2board.clash.design;

import android.content.Context;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.Profile;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: PropertiesDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.PropertiesDesign$inputUrl$1", f = "PropertiesDesign.kt", l = {110}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class PropertiesDesign$inputUrl$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ PropertiesDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesDesign$inputUrl$1(PropertiesDesign propertiesDesign, Continuation<? super PropertiesDesign$inputUrl$1> continuation) {
        super(2, continuation);
        this.this$0 = propertiesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PropertiesDesign$inputUrl$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new PropertiesDesign$inputUrl$1(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            PropertiesDesign propertiesDesign = this.this$0;
            Context context = propertiesDesign.context;
            String str = propertiesDesign.binding.mProfile.source;
            CharSequence text = context.getText(R.string.url);
            CharSequence text2 = this.this$0.context.getText(R.string.profile_url);
            CharSequence text3 = this.this$0.context.getText(R.string.accept_http_content);
            $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM r8 = $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM.INSTANCE$3;
            this.label = 1;
            obj = InputKt.requestModelTextInput(context, str, text, text2, text3, r8, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String str2 = (String) obj;
        if (!Intrinsics.areEqual(str2, this.this$0.binding.mProfile.source)) {
            PropertiesDesign propertiesDesign2 = this.this$0;
            propertiesDesign2.binding.setProfile(Profile.copy$default(propertiesDesign2.binding.mProfile, null, null, null, str2, false, 0L, 0L, false, false, 503));
        }
        return Unit.INSTANCE;
    }
}
