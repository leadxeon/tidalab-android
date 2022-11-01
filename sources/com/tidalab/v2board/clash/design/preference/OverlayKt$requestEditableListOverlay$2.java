package com.tidalab.v2board.clash.design.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.databinding.DialogPreferenceListBinding;
import com.tidalab.v2board.clash.design.dialog.FullScreenDialog;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Overlay.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.OverlayKt$requestEditableListOverlay$2", f = "Overlay.kt", l = {73}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class OverlayKt$requestEditableListOverlay$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EditableListOverlayResult>, Object> {
    public final /* synthetic */ RecyclerView.Adapter<?> $adapter;
    public final /* synthetic */ Function1<Continuation<? super Unit>, Object> $addNewItem;
    public final /* synthetic */ Context $context;
    public final /* synthetic */ CharSequence $title;
    public /* synthetic */ Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public OverlayKt$requestEditableListOverlay$2(Context context, RecyclerView.Adapter<?> adapter, CharSequence charSequence, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super OverlayKt$requestEditableListOverlay$2> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$adapter = adapter;
        this.$title = charSequence;
        this.$addNewItem = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        OverlayKt$requestEditableListOverlay$2 overlayKt$requestEditableListOverlay$2 = new OverlayKt$requestEditableListOverlay$2(this.$context, this.$adapter, this.$title, this.$addNewItem, continuation);
        overlayKt$requestEditableListOverlay$2.L$0 = obj;
        return overlayKt$requestEditableListOverlay$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super EditableListOverlayResult> continuation) {
        OverlayKt$requestEditableListOverlay$2 overlayKt$requestEditableListOverlay$2 = new OverlayKt$requestEditableListOverlay$2(this.$context, this.$adapter, this.$title, this.$addNewItem, continuation);
        overlayKt$requestEditableListOverlay$2.L$0 = coroutineScope;
        return overlayKt$requestEditableListOverlay$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Context context = this.$context;
            RecyclerView.Adapter<?> adapter = this.$adapter;
            CharSequence charSequence = this.$title;
            final Function1<Continuation<? super Unit>, Object> function1 = this.$addNewItem;
            this.L$0 = coroutineScope;
            this.L$1 = context;
            this.L$2 = adapter;
            this.L$3 = charSequence;
            this.L$4 = function1;
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(InputKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            final FullScreenDialog fullScreenDialog = new FullScreenDialog(context);
            LayoutInflater from = LayoutInflater.from(context);
            ViewGroup root = InputKt.getRoot(context);
            int i2 = DialogPreferenceListBinding.$r8$clinit;
            DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
            DialogPreferenceListBinding dialogPreferenceListBinding = (DialogPreferenceListBinding) ViewDataBinding.inflateInternal(from, R.layout.dialog_preference_list, root, false, null);
            dialogPreferenceListBinding.setSurface(fullScreenDialog.surface);
            InputKt.applyLinearAdapter(dialogPreferenceListBinding.mainList, context, adapter);
            dialogPreferenceListBinding.titleView.setText(charSequence);
            dialogPreferenceListBinding.newView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.preference.OverlayKt$requestEditableListOverlay$2$1$1

                /* compiled from: Overlay.kt */
                @DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.OverlayKt$requestEditableListOverlay$2$1$1$1", f = "Overlay.kt", l = {37}, m = "invokeSuspend")
                /* renamed from: com.tidalab.v2board.clash.design.preference.OverlayKt$requestEditableListOverlay$2$1$1$1  reason: invalid class name */
                /* loaded from: classes.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ Function1<Continuation<? super Unit>, Object> $addNewItem;
                    public int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public AnonymousClass1(Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$addNewItem = function1;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$addNewItem, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return new AnonymousClass1(this.$addNewItem, continuation).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                        int i = this.label;
                        if (i == 0) {
                            InputKt.throwOnFailure(obj);
                            Function1<Continuation<? super Unit>, Object> function1 = this.$addNewItem;
                            this.label = 1;
                            if (function1.invoke(this) == coroutineSingletons) {
                                return coroutineSingletons;
                            }
                        } else if (i == 1) {
                            InputKt.throwOnFailure(obj);
                        } else {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    InputKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(function1, null), 3, null);
                }
            });
            dialogPreferenceListBinding.resetView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.preference.OverlayKt$requestEditableListOverlay$2$1$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cancellableContinuationImpl.resumeWith(EditableListOverlayResult.Reset);
                    fullScreenDialog.dismiss();
                }
            });
            dialogPreferenceListBinding.cancelView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.preference.OverlayKt$requestEditableListOverlay$2$1$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FullScreenDialog.this.dismiss();
                }
            });
            dialogPreferenceListBinding.okView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.preference.OverlayKt$requestEditableListOverlay$2$1$4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cancellableContinuationImpl.resumeWith(EditableListOverlayResult.Apply);
                    fullScreenDialog.dismiss();
                }
            });
            fullScreenDialog.setContentView(dialogPreferenceListBinding.mRoot);
            fullScreenDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tidalab.v2board.clash.design.preference.OverlayKt$requestEditableListOverlay$2$1$5
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    if (!cancellableContinuationImpl.isCompleted()) {
                        cancellableContinuationImpl.resumeWith(EditableListOverlayResult.Cancel);
                    }
                }
            });
            cancellableContinuationImpl.invokeOnCancellation(new OverlayKt$requestEditableListOverlay$2$1$6(fullScreenDialog));
            fullScreenDialog.show();
            obj = cancellableContinuationImpl.getResult();
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            Function1 function12 = (Function1) this.L$4;
            CharSequence charSequence2 = (CharSequence) this.L$3;
            RecyclerView.Adapter adapter2 = (RecyclerView.Adapter) this.L$2;
            Context context2 = (Context) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
