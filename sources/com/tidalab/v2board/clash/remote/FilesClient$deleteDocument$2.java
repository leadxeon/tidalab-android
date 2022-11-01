package com.tidalab.v2board.clash.remote;

import android.provider.DocumentsContract;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: FilesClient.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.remote.FilesClient$deleteDocument$2", f = "FilesClient.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesClient$deleteDocument$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    public final /* synthetic */ String $documentId;
    public final /* synthetic */ FilesClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesClient$deleteDocument$2(FilesClient filesClient, String str, Continuation<? super FilesClient$deleteDocument$2> continuation) {
        super(2, continuation);
        this.this$0 = filesClient;
        this.$documentId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesClient$deleteDocument$2(this.this$0, this.$documentId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        Continuation<? super Boolean> continuation2 = continuation;
        FilesClient filesClient = this.this$0;
        String str = this.$documentId;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return Boolean.valueOf(DocumentsContract.deleteDocument(filesClient.context.getContentResolver(), filesClient.buildDocumentUri(str)));
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return Boolean.valueOf(DocumentsContract.deleteDocument(this.this$0.context.getContentResolver(), this.this$0.buildDocumentUri(this.$documentId)));
    }
}
