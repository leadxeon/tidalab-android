package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.design.ui.ToastDuration;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: Design.kt */
/* loaded from: classes.dex */
public abstract class Design<R> implements CoroutineScope {
    public final Context context;
    public final /* synthetic */ CoroutineScope $$delegate_0 = InputKt.CoroutineScope(Dispatchers.Unconfined);
    public final Surface surface = new Surface();
    public final Channel<R> requests = InputKt.Channel$default(Integer.MAX_VALUE, null, null, 6);

    /* compiled from: Design.kt */
    /* renamed from: com.tidalab.v2board.clash.design.Design$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<Insets, Unit> {
        public final /* synthetic */ Design<R> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Design<R> design) {
            super(1);
            this.this$0 = design;
        }

        @Override // kotlin.jvm.functions.Function1
        public Unit invoke(Insets insets) {
            Insets insets2 = insets;
            if (!Intrinsics.areEqual(this.this$0.surface.insets, insets2)) {
                Surface surface = this.this$0.surface;
                surface.insets = insets2;
                surface.notifyPropertyChanged(14);
            }
            return Unit.INSTANCE;
        }
    }

    public Design(Context context) {
        this.context = context;
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        if (context instanceof AppCompatActivity) {
            InputKt.setOnInsertsChangedListener$default(((AppCompatActivity) context).getWindow().getDecorView(), false, new AnonymousClass1(this), 1);
        }
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public abstract View getRoot();

    public final Object showToast(int i, ToastDuration toastDuration, Function1<? super Snackbar, Unit> function1, Continuation<? super Unit> continuation) {
        Object showToast = showToast(this.context.getString(i), toastDuration, function1, continuation);
        return showToast == CoroutineSingletons.COROUTINE_SUSPENDED ? showToast : Unit.INSTANCE;
    }

    public final Object showToast(CharSequence charSequence, ToastDuration toastDuration, Function1<? super Snackbar, Unit> function1, Continuation<? super Unit> continuation) {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        Object withContext = InputKt.withContext(MainDispatcherLoader.dispatcher, new Design$showToast$5(this, charSequence, toastDuration, function1, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
