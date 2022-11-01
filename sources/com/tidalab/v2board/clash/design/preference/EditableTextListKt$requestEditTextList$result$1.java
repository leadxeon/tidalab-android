package com.tidalab.v2board.clash.design.preference;

import android.content.Context;
import com.tidalab.v2board.clash.design.adapter.EditableTextListAdapter;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt__IndentKt;
import okhttp3.HttpUrl;
/* compiled from: EditableTextList.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextListKt$requestEditTextList$result$1", f = "EditableTextList.kt", l = {94}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class EditableTextListKt$requestEditTextList$result$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ EditableTextListAdapter<T> $recyclerAdapter;
    public final /* synthetic */ CharSequence $title;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditableTextListKt$requestEditTextList$result$1(Context context, CharSequence charSequence, EditableTextListAdapter<T> editableTextListAdapter, Continuation<? super EditableTextListKt$requestEditTextList$result$1> continuation) {
        super(1, continuation);
        this.$context = context;
        this.$title = charSequence;
        this.$recyclerAdapter = editableTextListAdapter;
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Continuation<? super Unit> continuation) {
        return new EditableTextListKt$requestEditTextList$result$1(this.$context, this.$title, this.$recyclerAdapter, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Context context = this.$context;
            CharSequence charSequence = this.$title;
            this.label = 1;
            obj = InputKt.requestModelTextInput(context, HttpUrl.FRAGMENT_ENCODE_SET, charSequence, charSequence, null, $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM.INSTANCE$0, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String str = (String) obj;
        if (!StringsKt__IndentKt.isBlank(str)) {
            EditableTextListAdapter<T> editableTextListAdapter = this.$recyclerAdapter;
            Object obj2 = editableTextListAdapter.adapter.to(str);
            editableTextListAdapter.mObservable.notifyItemRangeInserted(editableTextListAdapter.values.size(), 1);
            editableTextListAdapter.values.add(obj2);
        }
        return Unit.INSTANCE;
    }
}
