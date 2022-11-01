package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.google.android.material.snackbar.SnackbarManager;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.ui.ToastDuration;
import com.tidalab.v2board.clash.foss.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Design.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.Design$showToast$5", f = "Design.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class Design$showToast$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Function1<Snackbar, Unit> $configure;
    public final /* synthetic */ ToastDuration $duration;
    public final /* synthetic */ CharSequence $message;
    public final /* synthetic */ Design<R> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Design$showToast$5(Design<R> design, CharSequence charSequence, ToastDuration toastDuration, Function1<? super Snackbar, Unit> function1, Continuation<? super Design$showToast$5> continuation) {
        super(2, continuation);
        this.this$0 = design;
        this.$message = charSequence;
        this.$duration = toastDuration;
        this.$configure = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Design$showToast$5(this.this$0, this.$message, this.$duration, this.$configure, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new Design$showToast$5(this.this$0, this.$message, this.$duration, this.$configure, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        ViewGroup viewGroup;
        InputKt.throwOnFailure(obj);
        View root = this.this$0.getRoot();
        CharSequence charSequence = this.$message;
        int ordinal = this.$duration.ordinal();
        boolean z = true;
        if (ordinal == 0) {
            i = -1;
        } else if (ordinal == 1) {
            i = 0;
        } else if (ordinal == 2) {
            i = -2;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        int[] iArr = Snackbar.SNACKBAR_CONTENT_STYLE_ATTRS;
        ViewGroup viewGroup2 = null;
        while (true) {
            if (!(root instanceof CoordinatorLayout)) {
                if (root instanceof FrameLayout) {
                    if (root.getId() == 16908290) {
                        viewGroup = (ViewGroup) root;
                        break;
                    }
                    viewGroup2 = (ViewGroup) root;
                }
                if (root != null) {
                    ViewParent parent = root.getParent();
                    if (parent instanceof View) {
                        root = (View) parent;
                        continue;
                    } else {
                        root = null;
                        continue;
                    }
                }
                if (root == null) {
                    viewGroup = viewGroup2;
                    break;
                }
            } else {
                viewGroup = (ViewGroup) root;
                break;
            }
        }
        if (viewGroup != null) {
            Context context = viewGroup.getContext();
            LayoutInflater from = LayoutInflater.from(context);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Snackbar.SNACKBAR_CONTENT_STYLE_ATTRS);
            int resourceId = obtainStyledAttributes.getResourceId(0, -1);
            int resourceId2 = obtainStyledAttributes.getResourceId(1, -1);
            obtainStyledAttributes.recycle();
            if (resourceId == -1 || resourceId2 == -1) {
                z = false;
            }
            SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) from.inflate(z ? R.layout.mtrl_layout_snackbar_include : R.layout.design_layout_snackbar_include, viewGroup, false);
            Snackbar snackbar = new Snackbar(context, viewGroup, snackbarContentLayout, snackbarContentLayout);
            ((SnackbarContentLayout) snackbar.view.getChildAt(0)).getMessageView().setText(charSequence);
            snackbar.duration = i;
            this.$configure.invoke(snackbar);
            SnackbarManager instance = SnackbarManager.getInstance();
            int duration = snackbar.getDuration();
            SnackbarManager.Callback callback = snackbar.managerCallback;
            synchronized (instance.lock) {
                if (instance.isCurrentSnackbarLocked(callback)) {
                    SnackbarManager.SnackbarRecord snackbarRecord = instance.currentSnackbar;
                    snackbarRecord.duration = duration;
                    instance.handler.removeCallbacksAndMessages(snackbarRecord);
                    instance.scheduleTimeoutLocked(instance.currentSnackbar);
                } else {
                    if (instance.isNextSnackbarLocked(callback)) {
                        instance.nextSnackbar.duration = duration;
                    } else {
                        instance.nextSnackbar = new SnackbarManager.SnackbarRecord(duration, callback);
                    }
                    SnackbarManager.SnackbarRecord snackbarRecord2 = instance.currentSnackbar;
                    if (snackbarRecord2 == null || !instance.cancelSnackbarLocked(snackbarRecord2, 4)) {
                        instance.currentSnackbar = null;
                        instance.showNextSnackbarLocked();
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
    }
}
