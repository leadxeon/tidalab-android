package com.tidalab.v2board.clash.design.preference;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: EditableTextList.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextListKt$editableTextList$2$v$1", f = "EditableTextList.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class EditableTextListKt$editableTextList$2$v$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends T>>, Object> {
    public final /* synthetic */ KMutableProperty0<List<T>> $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditableTextListKt$editableTextList$2$v$1(KMutableProperty0<List<T>> kMutableProperty0, Continuation<? super EditableTextListKt$editableTextList$2$v$1> continuation) {
        super(2, continuation);
        this.$value = kMutableProperty0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EditableTextListKt$editableTextList$2$v$1(this.$value, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Object obj) {
        Continuation continuation = (Continuation) obj;
        KMutableProperty0<List<T>> kMutableProperty0 = this.$value;
        if (continuation != null) {
            continuation.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return kMutableProperty0.get();
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return this.$value.get();
    }
}
