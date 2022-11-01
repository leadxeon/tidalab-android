package com.tidalab.v2board.clash;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.tidalab.v2board.clash.design.NewProfileDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.ProfileProvider;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: NewProfileActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NewProfileActivity$main$2$2", f = "NewProfileActivity.kt", l = {41}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NewProfileActivity$main$2$2 extends SuspendLambda implements Function2<NewProfileDesign.Request, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ NewProfileActivity this$0;

    /* compiled from: NewProfileActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.NewProfileActivity$main$2$2$1", f = "NewProfileActivity.kt", l = {46, 48, 50, 55, 67}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.NewProfileActivity$main$2$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ NewProfileDesign.Request $it;
        public /* synthetic */ Object L$0;
        public Object L$1;
        public int label;
        public final /* synthetic */ NewProfileActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NewProfileActivity newProfileActivity, NewProfileDesign.Request request, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = newProfileActivity;
            this.$it = request;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, this.$it, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, this.$it, continuation);
            r0.L$0 = iProfileManager;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x00a6  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00c8  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00cb  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                Method dump skipped, instructions count: 227
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.NewProfileActivity$main$2$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewProfileActivity$main$2$2(NewProfileActivity newProfileActivity, Continuation<? super NewProfileActivity$main$2$2> continuation) {
        super(2, continuation);
        this.this$0 = newProfileActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NewProfileActivity$main$2$2 newProfileActivity$main$2$2 = new NewProfileActivity$main$2$2(this.this$0, continuation);
        newProfileActivity$main$2$2.L$0 = obj;
        return newProfileActivity$main$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(NewProfileDesign.Request request, Continuation<? super Unit> continuation) {
        NewProfileActivity$main$2$2 newProfileActivity$main$2$2 = new NewProfileActivity$main$2$2(this.this$0, continuation);
        newProfileActivity$main$2$2.L$0 = request;
        return newProfileActivity$main$2$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            NewProfileDesign.Request request = (NewProfileDesign.Request) this.L$0;
            if (request instanceof NewProfileDesign.Request.Create) {
                AnonymousClass1 r1 = new AnonymousClass1(this.this$0, request, null);
                this.label = 1;
                if (InputKt.withProfile$default(null, r1, this, 1) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (request instanceof NewProfileDesign.Request.OpenDetail) {
                NewProfileActivity newProfileActivity = this.this$0;
                ProfileProvider.External external = ((NewProfileDesign.Request.OpenDetail) request).provider;
                int i2 = NewProfileActivity.$r8$clinit;
                Objects.requireNonNull(newProfileActivity);
                ComponentName component = external.intent.getComponent();
                String packageName = component == null ? null : component.getPackageName();
                if (packageName != null) {
                    newProfileActivity.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", packageName, null)));
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
