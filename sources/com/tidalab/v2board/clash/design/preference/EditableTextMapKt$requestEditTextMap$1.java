package com.tidalab.v2board.clash.design.preference;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: EditableTextMap.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextMapKt", f = "EditableTextMap.kt", l = {93, 104}, m = "requestEditTextMap")
/* loaded from: classes.dex */
public final class EditableTextMapKt$requestEditTextMap$1<K, V> extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public int label;
    public /* synthetic */ Object result;

    public EditableTextMapKt$requestEditTextMap$1(Continuation<? super EditableTextMapKt$requestEditTextMap$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return InputKt.access$requestEditTextMap(null, null, null, null, null, this);
    }
}
