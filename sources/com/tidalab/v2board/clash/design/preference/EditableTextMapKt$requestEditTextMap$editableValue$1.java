package com.tidalab.v2board.clash.design.preference;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: EditableTextMap.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$editableValue$1", f = "EditableTextMap.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class EditableTextMapKt$requestEditTextMap$editableValue$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<Pair<? extends K, ? extends V>>>, Object> {
    public final /* synthetic */ Map<K, V> $initialValue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public EditableTextMapKt$requestEditTextMap$editableValue$1(Map<K, ? extends V> map, Continuation<? super EditableTextMapKt$requestEditTextMap$editableValue$1> continuation) {
        super(2, continuation);
        this.$initialValue = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EditableTextMapKt$requestEditTextMap$editableValue$1(this.$initialValue, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Object obj) {
        ArrayList arrayList;
        Continuation continuation = (Continuation) obj;
        Map<K, V> map = this.$initialValue;
        if (continuation != null) {
            continuation.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        if (map == 0) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(map.size());
            for (Map.Entry entry : map.entrySet()) {
                arrayList2.add(new Pair(entry.getKey(), entry.getValue()));
            }
            arrayList = new ArrayList(arrayList2);
        }
        return arrayList == null ? new ArrayList() : arrayList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ArrayList arrayList;
        InputKt.throwOnFailure(obj);
        Map<K, V> map = this.$initialValue;
        if (map == 0) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(map.size());
            for (Map.Entry entry : map.entrySet()) {
                arrayList2.add(new Pair(entry.getKey(), entry.getValue()));
            }
            arrayList = new ArrayList(arrayList2);
        }
        return arrayList == null ? new ArrayList() : arrayList;
    }
}
