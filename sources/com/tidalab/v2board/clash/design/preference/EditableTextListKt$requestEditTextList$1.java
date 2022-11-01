package com.tidalab.v2board.clash.design.preference;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: EditableTextList.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextListKt", f = "EditableTextList.kt", l = {93}, m = "requestEditTextList")
/* loaded from: classes.dex */
public final class EditableTextListKt$requestEditTextList$1<T> extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;

    public EditableTextListKt$requestEditTextList$1(Continuation<? super EditableTextListKt$requestEditTextList$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return InputKt.access$requestEditTextList(null, null, null, null, this);
    }
}
