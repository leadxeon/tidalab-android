package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.document.Document;
import com.tidalab.v2board.clash.service.document.FileDocument;
import com.tidalab.v2board.clash.service.document.Path;
import com.tidalab.v2board.clash.service.document.Paths;
import com.tidalab.v2board.clash.service.document.Picker;
import java.io.FileNotFoundException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: FilesProvider.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.FilesProvider$deleteDocument$1", f = "FilesProvider.kt", l = {74}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesProvider$deleteDocument$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    public final /* synthetic */ String $documentId;
    public final /* synthetic */ String $documentPath;
    public int label;
    public final /* synthetic */ FilesProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesProvider$deleteDocument$1(String str, String str2, FilesProvider filesProvider, Continuation<? super FilesProvider$deleteDocument$1> continuation) {
        super(2, continuation);
        this.$documentPath = str;
        this.$documentId = str2;
        this.this$0 = filesProvider;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesProvider$deleteDocument$1(this.$documentPath, this.$documentId, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return new FilesProvider$deleteDocument$1(this.$documentPath, this.$documentId, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Path resolve = Paths.resolve(this.$documentPath);
            if (resolve.relative != null) {
                Picker access$getPicker = FilesProvider.access$getPicker(this.this$0);
                this.label = 1;
                obj = access$getPicker.pick(resolve, true, this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                throw new IllegalArgumentException(Intrinsics.stringPlus("invalid path ", this.$documentId));
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Document document = (Document) obj;
        boolean z = document instanceof FileDocument;
        String str = this.$documentId;
        if (z) {
            return Boolean.valueOf(FilesKt__UtilsKt.deleteRecursively(((FileDocument) document).file));
        }
        throw new FileNotFoundException(Intrinsics.stringPlus("invalid path ", str));
    }
}
