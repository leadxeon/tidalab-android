package com.tidalab.v2board.clash.service;

import android.os.ParcelFileDescriptor;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.document.Document;
import com.tidalab.v2board.clash.service.document.FileDocument;
import com.tidalab.v2board.clash.service.document.Path;
import com.tidalab.v2board.clash.service.document.Paths;
import com.tidalab.v2board.clash.service.document.Picker;
import java.io.FileNotFoundException;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: FilesProvider.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.FilesProvider$openDocument$1", f = "FilesProvider.kt", l = {55}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesProvider$openDocument$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ParcelFileDescriptor>, Object> {
    public final /* synthetic */ String $documentId;
    public final /* synthetic */ int $m;
    public final /* synthetic */ String $mode;
    public int label;
    public final /* synthetic */ FilesProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesProvider$openDocument$1(String str, FilesProvider filesProvider, String str2, int i, Continuation<? super FilesProvider$openDocument$1> continuation) {
        super(2, continuation);
        this.$documentId = str;
        this.this$0 = filesProvider;
        this.$mode = str2;
        this.$m = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesProvider$openDocument$1(this.$documentId, this.this$0, this.$mode, this.$m, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super ParcelFileDescriptor> continuation) {
        return new FilesProvider$openDocument$1(this.$documentId, this.this$0, this.$mode, this.$m, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            String str = this.$documentId;
            if (str == null) {
                str = "/";
            }
            Path resolve = Paths.resolve(str);
            Picker access$getPicker = FilesProvider.access$getPicker(this.this$0);
            String str2 = this.$mode;
            if (str2 == null) {
                z = true;
            } else {
                Objects.requireNonNull(this.this$0);
                z = StringsKt__IndentKt.contains(str2, "w", true);
            }
            this.label = 1;
            obj = access$getPicker.pick(resolve, z, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Document document = (Document) obj;
        boolean z2 = document instanceof FileDocument;
        String str3 = this.$documentId;
        if (z2) {
            return ParcelFileDescriptor.open(((FileDocument) document).file, this.$m);
        }
        throw new FileNotFoundException(Intrinsics.stringPlus("invalid path ", str3));
    }
}
