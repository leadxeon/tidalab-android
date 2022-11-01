package com.tidalab.v2board.clash;

import android.net.Uri;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.Profile;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.Locale;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
/* compiled from: ExternalImportActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ExternalImportActivity$onCreate$1$uuid$1", f = "ExternalImportActivity.kt", l = {34, 35}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ExternalImportActivity$onCreate$1$uuid$1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super UUID>, Object> {
    public final /* synthetic */ Uri $uri;
    public final /* synthetic */ String $url;
    public /* synthetic */ Object L$0;
    public Object L$1;
    public int label;
    public final /* synthetic */ ExternalImportActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExternalImportActivity$onCreate$1$uuid$1(Uri uri, ExternalImportActivity externalImportActivity, String str, Continuation<? super ExternalImportActivity$onCreate$1$uuid$1> continuation) {
        super(2, continuation);
        this.$uri = uri;
        this.this$0 = externalImportActivity;
        this.$url = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ExternalImportActivity$onCreate$1$uuid$1 externalImportActivity$onCreate$1$uuid$1 = new ExternalImportActivity$onCreate$1$uuid$1(this.$uri, this.this$0, this.$url, continuation);
        externalImportActivity$onCreate$1$uuid$1.L$0 = obj;
        return externalImportActivity$onCreate$1$uuid$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IProfileManager iProfileManager, Continuation<? super UUID> continuation) {
        ExternalImportActivity$onCreate$1$uuid$1 externalImportActivity$onCreate$1$uuid$1 = new ExternalImportActivity$onCreate$1$uuid$1(this.$uri, this.this$0, this.$url, continuation);
        externalImportActivity$onCreate$1$uuid$1.L$0 = iProfileManager;
        return externalImportActivity$onCreate$1$uuid$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IProfileManager iProfileManager;
        String queryParameter;
        Profile.Type type = Profile.Type.Url;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            iProfileManager = (IProfileManager) this.L$0;
            String queryParameter2 = this.$uri.getQueryParameter("type");
            String lowerCase = queryParameter2 == null ? null : queryParameter2.toLowerCase(Locale.getDefault());
            if (!Intrinsics.areEqual(lowerCase, "url") && Intrinsics.areEqual(lowerCase, "file")) {
                type = Profile.Type.File;
            }
            queryParameter = this.$uri.getQueryParameter("name");
            if (queryParameter == null) {
                queryParameter = this.this$0.getString(R.string.new_profile);
            }
            this.L$0 = iProfileManager;
            this.L$1 = queryParameter;
            this.label = 1;
            obj = iProfileManager.create(type, queryParameter, (r5 & 4) != 0 ? HttpUrl.FRAGMENT_ENCODE_SET : null, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            queryParameter = (String) this.L$1;
            iProfileManager = (IProfileManager) this.L$0;
            InputKt.throwOnFailure(obj);
        } else if (i == 2) {
            Object obj2 = this.L$0;
            InputKt.throwOnFailure(obj);
            return obj2;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String str = this.$url;
        this.L$0 = obj;
        this.L$1 = null;
        this.label = 2;
        return iProfileManager.patch((UUID) obj, queryParameter, str, 0L, this) == coroutineSingletons ? coroutineSingletons : obj;
    }
}
