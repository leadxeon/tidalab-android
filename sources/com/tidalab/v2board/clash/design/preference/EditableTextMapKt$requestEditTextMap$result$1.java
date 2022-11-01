package com.tidalab.v2board.clash.design.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tidalab.v2board.clash.design.adapter.EditableTextMapAdapter;
import com.tidalab.v2board.clash.design.databinding.DialogEditableMapTextFieldBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.HttpUrl;
/* compiled from: EditableTextMap.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$result$1", f = "EditableTextMap.kt", l = {105}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class EditableTextMapKt$requestEditTextMap$result$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ EditableTextMapAdapter<K, V> $recyclerAdapter;
    public final /* synthetic */ CharSequence $title;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditableTextMapKt$requestEditTextMap$result$1(Context context, CharSequence charSequence, EditableTextMapAdapter<K, V> editableTextMapAdapter, Continuation<? super EditableTextMapKt$requestEditTextMap$result$1> continuation) {
        super(1, continuation);
        this.$context = context;
        this.$title = charSequence;
        this.$recyclerAdapter = editableTextMapAdapter;
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Continuation<? super Unit> continuation) {
        return new EditableTextMapKt$requestEditTextMap$result$1(this.$context, this.$title, this.$recyclerAdapter, continuation).invokeSuspend(Unit.INSTANCE);
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
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(InputKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            LayoutInflater from = LayoutInflater.from(context);
            ViewGroup root = InputKt.getRoot(context);
            int i2 = DialogEditableMapTextFieldBinding.$r8$clinit;
            DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
            final DialogEditableMapTextFieldBinding dialogEditableMapTextFieldBinding = (DialogEditableMapTextFieldBinding) ViewDataBinding.inflateInternal(from, R.layout.dialog_editable_map_text_field, root, false, null);
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
            materialAlertDialogBuilder.P.mTitle = charSequence;
            materialAlertDialogBuilder.setNegativeButton(R.string.cancel, EditableTextMapKt$requestModelInputEntry$2$dialog$1.INSTANCE);
            materialAlertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestModelInputEntry$2$dialog$2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    String str;
                    String obj2;
                    Editable text = DialogEditableMapTextFieldBinding.this.keyView.getText();
                    String str2 = null;
                    String obj3 = text == null ? null : text.toString();
                    String str3 = HttpUrl.FRAGMENT_ENCODE_SET;
                    if (obj3 == null || (str = StringsKt__IndentKt.trim(obj3).toString()) == null) {
                        str = str3;
                    }
                    Editable text2 = DialogEditableMapTextFieldBinding.this.valueView.getText();
                    if (text2 != null) {
                        str2 = text2.toString();
                    }
                    if (!(str2 == null || (obj2 = StringsKt__IndentKt.trim(str2).toString()) == null)) {
                        str3 = obj2;
                    }
                    boolean z = true;
                    if (str.length() > 0) {
                        if (str3.length() <= 0) {
                            z = false;
                        }
                        if (z) {
                            cancellableContinuationImpl.resumeWith(new Pair(str, str3));
                        }
                    }
                }
            });
            materialAlertDialogBuilder.setView(dialogEditableMapTextFieldBinding.mRoot);
            AlertDialog create = materialAlertDialogBuilder.create();
            create.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestModelInputEntry$2$1
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    if (!cancellableContinuationImpl.isCompleted()) {
                        cancellableContinuationImpl.resumeWith(null);
                    }
                }
            });
            create.show();
            obj = cancellableContinuationImpl.getResult();
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Pair pair = (Pair) obj;
        if (pair != null) {
            EditableTextMapAdapter<K, V> editableTextMapAdapter = this.$recyclerAdapter;
            Object obj2 = editableTextMapAdapter.keyAdapter.to((String) pair.first);
            Object obj3 = editableTextMapAdapter.valueAdapter.to((String) pair.second);
            editableTextMapAdapter.mObservable.notifyItemRangeInserted(editableTextMapAdapter.values.size(), 1);
            editableTextMapAdapter.values.add(new Pair(obj2, obj3));
        }
        return Unit.INSTANCE;
    }
}
