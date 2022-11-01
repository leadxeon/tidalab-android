package com.tidalab.v2board.clash.remote;

import android.net.Uri;
import android.provider.DocumentsContract;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: FilesClient.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.remote.FilesClient$renameDocument$2", f = "FilesClient.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesClient$renameDocument$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Uri>, Object> {
    public final /* synthetic */ String $documentId;
    public final /* synthetic */ String $name;
    public final /* synthetic */ FilesClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesClient$renameDocument$2(FilesClient filesClient, String str, String str2, Continuation<? super FilesClient$renameDocument$2> continuation) {
        super(2, continuation);
        this.this$0 = filesClient;
        this.$documentId = str;
        this.$name = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesClient$renameDocument$2(this.this$0, this.$documentId, this.$name, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Uri> continuation) {
        Continuation<? super Uri> continuation2 = continuation;
        FilesClient filesClient = this.this$0;
        String str = this.$documentId;
        String str2 = this.$name;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return DocumentsContract.renameDocument(filesClient.context.getContentResolver(), filesClient.buildDocumentUri(str), str2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return DocumentsContract.renameDocument(this.this$0.context.getContentResolver(), this.this$0.buildDocumentUri(this.$documentId), this.$name);
    }
}
