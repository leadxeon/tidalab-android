package com.tidalab.v2board.clash.design.preference;

import android.content.Context;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: EditableText.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextKt$editableText$2", f = "EditableText.kt", l = {59}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class EditableTextKt$editableText$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ NullableTextAdapter<T> $adapter;
    public final /* synthetic */ EditableTextKt$editableText$impl$1 $impl;
    public final /* synthetic */ PreferenceScreen $this_editableText;
    public final /* synthetic */ KMutableProperty0<T> $value;
    public Object L$0;
    public int label;

    /* compiled from: EditableText.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextKt$editableText$2$1", f = "EditableText.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.design.preference.EditableTextKt$editableText$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        public final /* synthetic */ NullableTextAdapter<T> $adapter;
        public final /* synthetic */ KMutableProperty0<T> $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NullableTextAdapter<T> nullableTextAdapter, KMutableProperty0<T> kMutableProperty0, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$adapter = nullableTextAdapter;
            this.$value = kMutableProperty0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$adapter, this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            Continuation<? super String> continuation2 = continuation;
            NullableTextAdapter<T> nullableTextAdapter = this.$adapter;
            KMutableProperty0<T> kMutableProperty0 = this.$value;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            InputKt.throwOnFailure(Unit.INSTANCE);
            return nullableTextAdapter.from(kMutableProperty0.get());
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            return this.$adapter.from(this.$value.get());
        }
    }

    /* compiled from: EditableText.kt */
    /* renamed from: com.tidalab.v2board.clash.design.preference.EditableTextKt$editableText$2$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends Lambda implements Function0<Unit> {
        public final /* synthetic */ NullableTextAdapter<T> $adapter;
        public final /* synthetic */ EditableTextKt$editableText$impl$1 $impl;
        public final /* synthetic */ PreferenceScreen $this_editableText;
        public final /* synthetic */ KMutableProperty0<T> $value;

        /* compiled from: EditableText.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextKt$editableText$2$2$1", f = "EditableText.kt", l = {65, 72}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.design.preference.EditableTextKt$editableText$2$2$1  reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ NullableTextAdapter<T> $adapter;
            public final /* synthetic */ EditableTextKt$editableText$impl$1 $impl;
            public final /* synthetic */ PreferenceScreen $this_editableText;
            public final /* synthetic */ KMutableProperty0<T> $value;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(PreferenceScreen preferenceScreen, EditableTextKt$editableText$impl$1 editableTextKt$editableText$impl$1, NullableTextAdapter<T> nullableTextAdapter, KMutableProperty0<T> kMutableProperty0, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$this_editableText = preferenceScreen;
                this.$impl = editableTextKt$editableText$impl$1;
                this.$adapter = nullableTextAdapter;
                this.$value = kMutableProperty0;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$this_editableText, this.$impl, this.$adapter, this.$value, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new AnonymousClass1(this.$this_editableText, this.$impl, this.$adapter, this.$value, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    InputKt.throwOnFailure(obj);
                    Context context = this.$this_editableText.getContext();
                    EditableTextKt$editableText$impl$1 editableTextKt$editableText$impl$1 = this.$impl;
                    String str = editableTextKt$editableText$impl$1.text;
                    CharSequence title = editableTextKt$editableText$impl$1.getTitle();
                    CharSequence text = this.$this_editableText.getContext().getText(R.string.reset);
                    CharSequence title2 = this.$impl.getTitle();
                    this.label = 1;
                    obj = InputKt.requestModelTextInput(context, str, title, text, title2, null, $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM.INSTANCE$0, this);
                    if (obj == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else if (i == 1) {
                    InputKt.throwOnFailure(obj);
                } else if (i == 2) {
                    InputKt.throwOnFailure(obj);
                    this.$impl.setText(this.$adapter.from(obj));
                    return Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Dispatchers dispatchers = Dispatchers.INSTANCE;
                CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
                EditableTextKt$editableText$2$2$1$newValue$1 editableTextKt$editableText$2$2$1$newValue$1 = new EditableTextKt$editableText$2$2$1$newValue$1(this.$adapter, (String) obj, this.$value, null);
                this.label = 2;
                obj = InputKt.withContext(coroutineDispatcher, editableTextKt$editableText$2$2$1$newValue$1, this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
                this.$impl.setText(this.$adapter.from(obj));
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(PreferenceScreen preferenceScreen, EditableTextKt$editableText$impl$1 editableTextKt$editableText$impl$1, NullableTextAdapter<T> nullableTextAdapter, KMutableProperty0<T> kMutableProperty0) {
            super(0);
            this.$this_editableText = preferenceScreen;
            this.$impl = editableTextKt$editableText$impl$1;
            this.$adapter = nullableTextAdapter;
            this.$value = kMutableProperty0;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            PreferenceScreen preferenceScreen = this.$this_editableText;
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            InputKt.launch$default(preferenceScreen, MainDispatcherLoader.dispatcher, null, new AnonymousClass1(preferenceScreen, this.$impl, this.$adapter, this.$value, null), 2, null);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditableTextKt$editableText$2(EditableTextKt$editableText$impl$1 editableTextKt$editableText$impl$1, NullableTextAdapter<T> nullableTextAdapter, KMutableProperty0<T> kMutableProperty0, PreferenceScreen preferenceScreen, Continuation<? super EditableTextKt$editableText$2> continuation) {
        super(2, continuation);
        this.$impl = editableTextKt$editableText$impl$1;
        this.$adapter = nullableTextAdapter;
        this.$value = kMutableProperty0;
        this.$this_editableText = preferenceScreen;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EditableTextKt$editableText$2(this.$impl, this.$adapter, this.$value, this.$this_editableText, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new EditableTextKt$editableText$2(this.$impl, this.$adapter, this.$value, this.$this_editableText, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        EditableTextKt$editableText$impl$1 editableTextKt$editableText$impl$1;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            EditableTextKt$editableText$impl$1 editableTextKt$editableText$impl$12 = this.$impl;
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
            AnonymousClass1 r3 = new AnonymousClass1(this.$adapter, this.$value, null);
            this.L$0 = editableTextKt$editableText$impl$12;
            this.label = 1;
            Object withContext = InputKt.withContext(coroutineDispatcher, r3, this);
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
            editableTextKt$editableText$impl$1 = editableTextKt$editableText$impl$12;
            obj = withContext;
        } else if (i == 1) {
            editableTextKt$editableText$impl$1 = (EditableTextKt$editableText$impl$1) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        editableTextKt$editableText$impl$1.setText((String) obj);
        EditableTextKt$editableText$impl$1 editableTextKt$editableText$impl$13 = this.$impl;
        editableTextKt$editableText$impl$13.$$delegate_0.clicked(new AnonymousClass2(this.$this_editableText, editableTextKt$editableText$impl$13, this.$adapter, this.$value));
        return Unit.INSTANCE;
    }
}
