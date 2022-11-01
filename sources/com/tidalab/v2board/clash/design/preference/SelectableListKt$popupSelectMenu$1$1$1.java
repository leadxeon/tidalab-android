package com.tidalab.v2board.clash.design.preference;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: SelectableList.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.SelectableListKt$popupSelectMenu$1$1$1", f = "SelectableList.kt", l = {85}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SelectableListKt$popupSelectMenu$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ SelectableListPreference<T> $impl;
    public final /* synthetic */ int $position;
    public final /* synthetic */ KMutableProperty0<T> $value;
    public final /* synthetic */ T[] $values;
    public int label;

    /* compiled from: SelectableList.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.SelectableListKt$popupSelectMenu$1$1$1$1", f = "SelectableList.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.design.preference.SelectableListKt$popupSelectMenu$1$1$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $position;
        public final /* synthetic */ KMutableProperty0<T> $value;
        public final /* synthetic */ T[] $values;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(KMutableProperty0<T> kMutableProperty0, T[] tArr, int i, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$value = kMutableProperty0;
            this.$values = tArr;
            this.$position = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$value, this.$values, this.$position, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            Continuation<? super Unit> continuation2 = continuation;
            KMutableProperty0<T> kMutableProperty0 = this.$value;
            Object[] objArr = this.$values;
            int i = this.$position;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            kMutableProperty0.set(objArr[i]);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            this.$value.set(this.$values[this.$position]);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectableListKt$popupSelectMenu$1$1$1(SelectableListPreference<T> selectableListPreference, int i, KMutableProperty0<T> kMutableProperty0, T[] tArr, Continuation<? super SelectableListKt$popupSelectMenu$1$1$1> continuation) {
        super(2, continuation);
        this.$impl = selectableListPreference;
        this.$position = i;
        this.$value = kMutableProperty0;
        this.$values = tArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SelectableListKt$popupSelectMenu$1$1$1(this.$impl, this.$position, this.$value, this.$values, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SelectableListKt$popupSelectMenu$1$1$1(this.$impl, this.$position, this.$value, this.$values, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
            AnonymousClass1 r1 = new AnonymousClass1(this.$value, this.$values, this.$position, null);
            this.label = 1;
            if (InputKt.withContext(coroutineDispatcher, r1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$impl.setSelected(this.$position);
        OnChangedListener listener = this.$impl.getListener();
        if (listener != null) {
            listener.onChanged();
        }
        return Unit.INSTANCE;
    }
}
