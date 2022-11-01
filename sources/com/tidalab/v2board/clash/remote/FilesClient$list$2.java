package com.tidalab.v2board.clash.remote;

import android.database.Cursor;
import android.provider.DocumentsContract;
import com.tidalab.v2board.clash.common.constants.Authorities;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: FilesClient.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.remote.FilesClient$list$2", f = "FilesClient.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesClient$list$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends File>>, Object> {
    public final /* synthetic */ String $parentDocumentId;
    public final /* synthetic */ FilesClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesClient$list$2(String str, FilesClient filesClient, Continuation<? super FilesClient$list$2> continuation) {
        super(2, continuation);
        this.$parentDocumentId = str;
        this.this$0 = filesClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesClient$list$2(this.$parentDocumentId, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends File>> continuation) {
        return new FilesClient$list$2(this.$parentDocumentId, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List asList;
        InputKt.throwOnFailure(obj);
        Authorities authorities = Authorities.INSTANCE;
        Cursor query = this.this$0.context.getContentResolver().query(DocumentsContract.buildChildDocumentsUri(Authorities.FILES_PROVIDER, this.$parentDocumentId), FilesClient.FilesProjection, null, null, null);
        if (query == null) {
            asList = null;
        } else {
            try {
                int columnIndex = query.getColumnIndex("document_id");
                int columnIndex2 = query.getColumnIndex("_display_name");
                int columnIndex3 = query.getColumnIndex("_size");
                int columnIndex4 = query.getColumnIndex("last_modified");
                int columnIndex5 = query.getColumnIndex("mime_type");
                query.moveToFirst();
                int count = query.getCount();
                ArrayList arrayList = new ArrayList(count);
                for (int i = 0; i < count; i++) {
                    new Integer(i).intValue();
                    File file = new File(query.getString(columnIndex), query.getString(columnIndex2), query.getLong(columnIndex3), query.getLong(columnIndex4), Intrinsics.areEqual(query.getString(columnIndex5), "vnd.android.document/directory"));
                    query.moveToNext();
                    arrayList.add(file);
                }
                final Function1[] function1Arr = {$$LambdaGroup$ks$IOk5rH0fHHNpvMEcusEGASpMnlA.INSTANCE$0, $$LambdaGroup$ks$IOk5rH0fHHNpvMEcusEGASpMnlA.INSTANCE$1};
                Comparator<T> comparisonsKt__ComparisonsKt$compareBy$1 = new Comparator<T>() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt$compareBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return InputKt.compareValuesByImpl$ComparisonsKt__ComparisonsKt(t, t2, function1Arr);
                    }
                };
                if (arrayList.size() <= 1) {
                    asList = ArraysKt___ArraysKt.toList(arrayList);
                } else {
                    Object[] array = arrayList.toArray(new Object[0]);
                    Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
                    if (array.length > 1) {
                        Arrays.sort(array, comparisonsKt__ComparisonsKt$compareBy$1);
                    }
                    asList = Arrays.asList(array);
                }
                th = null;
            } catch (Throwable th) {
                try {
                    throw th;
                } finally {
                    InputKt.closeFinally(query, th);
                }
            }
        }
        return asList == null ? EmptyList.INSTANCE : asList;
    }
}
