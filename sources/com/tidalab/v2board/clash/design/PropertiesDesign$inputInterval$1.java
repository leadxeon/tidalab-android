package com.tidalab.v2board.clash.design;

import android.content.Context;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.Profile;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.HttpUrl;
/* compiled from: PropertiesDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.PropertiesDesign$inputInterval$1", f = "PropertiesDesign.kt", l = {128}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class PropertiesDesign$inputInterval$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ PropertiesDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesDesign$inputInterval$1(PropertiesDesign propertiesDesign, Continuation<? super PropertiesDesign$inputInterval$1> continuation) {
        super(2, continuation);
        this.this$0 = propertiesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PropertiesDesign$inputInterval$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new PropertiesDesign$inputInterval$1(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        long j = 0;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(this.this$0.binding.mProfile.interval);
            Context context = this.this$0.context;
            String valueOf = minutes == 0 ? HttpUrl.FRAGMENT_ENCODE_SET : String.valueOf(minutes);
            CharSequence text = this.this$0.context.getText(R.string.auto_update);
            CharSequence text2 = this.this$0.context.getText(R.string.auto_update_minutes);
            CharSequence text3 = this.this$0.context.getText(R.string.at_least_15_minutes);
            $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM r6 = $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM.INSTANCE$1;
            this.label = 1;
            obj2 = InputKt.requestModelTextInput(context, valueOf, text, text2, text3, r6, this);
            if (obj2 == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
            obj2 = obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Long longOrNull = StringsKt__IndentKt.toLongOrNull((String) obj2);
        if (longOrNull != null) {
            j = longOrNull.longValue();
        }
        long millis = TimeUnit.MINUTES.toMillis(j);
        PropertiesDesign propertiesDesign = this.this$0;
        Profile profile = propertiesDesign.binding.mProfile;
        if (millis != profile.interval) {
            propertiesDesign.binding.setProfile(Profile.copy$default(profile, null, null, null, null, false, millis, 0L, false, false, 479));
        }
        return Unit.INSTANCE;
    }
}
