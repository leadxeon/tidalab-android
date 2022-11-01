package com.tidalab.v2board.clash.remote;

import android.content.ContentResolver;
import android.net.Uri;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: FilesClient.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.remote.FilesClient$importDocument$2", f = "FilesClient.kt", l = {60}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesClient$importDocument$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $name;
    public final /* synthetic */ String $parentDocumentId;
    public final /* synthetic */ Uri $source;
    public int label;
    public final /* synthetic */ FilesClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesClient$importDocument$2(FilesClient filesClient, String str, String str2, Uri uri, Continuation<? super FilesClient$importDocument$2> continuation) {
        super(2, continuation);
        this.this$0 = filesClient;
        this.$parentDocumentId = str;
        this.$name = str2;
        this.$source = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesClient$importDocument$2(this.this$0, this.$parentDocumentId, this.$name, this.$source, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new FilesClient$importDocument$2(this.this$0, this.$parentDocumentId, this.$name, this.$source, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            FilesClient filesClient = this.this$0;
            Uri buildDocumentUri = filesClient.buildDocumentUri(this.$parentDocumentId + '/' + this.$name);
            ContentResolver contentResolver = this.this$0.context.getContentResolver();
            Uri uri = this.$source;
            this.label = 1;
            if (InputKt.copyContentTo(contentResolver, uri, buildDocumentUri, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
