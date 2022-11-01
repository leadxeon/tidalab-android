package com.tidalab.v2board.clash.service;

import android.database.MatrixCursor;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.document.Document;
import com.tidalab.v2board.clash.service.document.Path;
import com.tidalab.v2board.clash.service.document.Paths;
import com.tidalab.v2board.clash.service.document.Picker;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: FilesProvider.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.FilesProvider$queryDocument$1", f = "FilesProvider.kt", l = {142}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesProvider$queryDocument$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MatrixCursor>, Object> {
    public final /* synthetic */ String $documentId;
    public final /* synthetic */ String[] $projection;
    public Object L$0;
    public int label;
    public final /* synthetic */ FilesProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesProvider$queryDocument$1(String str, FilesProvider filesProvider, String[] strArr, Continuation<? super FilesProvider$queryDocument$1> continuation) {
        super(2, continuation);
        this.$documentId = str;
        this.this$0 = filesProvider;
        this.$projection = strArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesProvider$queryDocument$1(this.$documentId, this.this$0, this.$projection, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super MatrixCursor> continuation) {
        return new FilesProvider$queryDocument$1(this.$documentId, this.this$0, this.$projection, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                String str2 = this.$documentId;
                if (str2 == null) {
                    str2 = "/";
                }
                Path resolve = Paths.resolve(str2);
                Picker access$getPicker = FilesProvider.access$getPicker(this.this$0);
                this.L$0 = str2;
                this.label = 1;
                Object pick = access$getPicker.pick(resolve, false, this);
                if (pick == coroutineSingletons) {
                    return coroutineSingletons;
                }
                str = str2;
                obj = pick;
            } else if (i == 1) {
                str = (String) this.L$0;
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            FilesProvider filesProvider = this.this$0;
            String[] strArr = this.$projection;
            MatrixCursor matrixCursor = new MatrixCursor(Objects.requireNonNull(filesProvider));
            FilesProvider filesProvider2 = this.this$0;
            MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
            FilesProvider.access$applyDocument(filesProvider2, newRow, (Document) obj);
            newRow.add("document_id", str);
            return matrixCursor;
        } catch (Exception unused) {
            FilesProvider filesProvider3 = this.this$0;
            String[] strArr2 = this.$projection;
            return new MatrixCursor(Objects.requireNonNull(filesProvider3));
        }
    }
}
