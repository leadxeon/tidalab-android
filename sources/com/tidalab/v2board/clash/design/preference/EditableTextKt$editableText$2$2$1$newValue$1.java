package com.tidalab.v2board.clash.design.preference;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: EditableText.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextKt$editableText$2$2$1$newValue$1", f = "EditableText.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class EditableTextKt$editableText$2$2$1$newValue$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    public final /* synthetic */ NullableTextAdapter<T> $adapter;
    public final /* synthetic */ String $text;
    public final /* synthetic */ KMutableProperty0<T> $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditableTextKt$editableText$2$2$1$newValue$1(NullableTextAdapter<T> nullableTextAdapter, String str, KMutableProperty0<T> kMutableProperty0, Continuation<? super EditableTextKt$editableText$2$2$1$newValue$1> continuation) {
        super(2, continuation);
        this.$adapter = nullableTextAdapter;
        this.$text = str;
        this.$value = kMutableProperty0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EditableTextKt$editableText$2$2$1$newValue$1(this.$adapter, this.$text, this.$value, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Object obj) {
        Continuation continuation = (Continuation) obj;
        NullableTextAdapter<T> nullableTextAdapter = this.$adapter;
        String str = this.$text;
        KMutableProperty0<T> kMutableProperty0 = this.$value;
        if (continuation != null) {
            continuation.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        Object obj2 = nullableTextAdapter.to(str);
        kMutableProperty0.set(obj2);
        return obj2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        Object obj2 = this.$adapter.to(this.$text);
        this.$value.set(obj2);
        return obj2;
    }
}
