package com.tidalab.v2board.clash.design.preference;

import android.content.Context;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Collection;
import java.util.List;
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
/* compiled from: EditableTextList.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextListKt$editableTextList$2", f = "EditableTextList.kt", l = {54}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class EditableTextListKt$editableTextList$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ TextAdapter<T> $adapter;
    public final /* synthetic */ EditableTextListKt$editableTextList$impl$1 $impl;
    public final /* synthetic */ PreferenceScreen $this_editableTextList;
    public final /* synthetic */ KMutableProperty0<List<T>> $value;
    public int label;

    /* compiled from: EditableTextList.kt */
    /* renamed from: com.tidalab.v2board.clash.design.preference.EditableTextListKt$editableTextList$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        public final /* synthetic */ TextAdapter<T> $adapter;
        public final /* synthetic */ EditableTextListKt$editableTextList$impl$1 $impl;
        public final /* synthetic */ PreferenceScreen $this_editableTextList;
        public final /* synthetic */ KMutableProperty0<List<T>> $value;

        /* compiled from: EditableTextList.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextListKt$editableTextList$2$1$1", f = "EditableTextList.kt", l = {62, 69}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.design.preference.EditableTextListKt$editableTextList$2$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public final class C00151 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ TextAdapter<T> $adapter;
            public final /* synthetic */ EditableTextListKt$editableTextList$impl$1 $impl;
            public final /* synthetic */ PreferenceScreen $this_editableTextList;
            public final /* synthetic */ KMutableProperty0<List<T>> $value;
            public Object L$0;
            public int label;

            /* compiled from: EditableTextList.kt */
            @DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextListKt$editableTextList$2$1$1$1", f = "EditableTextList.kt", l = {}, m = "invokeSuspend")
            /* renamed from: com.tidalab.v2board.clash.design.preference.EditableTextListKt$editableTextList$2$1$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C00161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ List<T> $newList;
                public final /* synthetic */ KMutableProperty0<List<T>> $value;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00161(KMutableProperty0<List<T>> kMutableProperty0, List<? extends T> list, Continuation<? super C00161> continuation) {
                    super(2, continuation);
                    this.$value = kMutableProperty0;
                    this.$newList = list;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00161(this.$value, this.$newList, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    Continuation<? super Unit> continuation2 = continuation;
                    KMutableProperty0<List<T>> kMutableProperty0 = this.$value;
                    Collection collection = this.$newList;
                    if (continuation2 != null) {
                        continuation2.getContext();
                    }
                    Unit unit = Unit.INSTANCE;
                    InputKt.throwOnFailure(unit);
                    kMutableProperty0.set(collection);
                    return unit;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    InputKt.throwOnFailure(obj);
                    this.$value.set(this.$newList);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00151(EditableTextListKt$editableTextList$impl$1 editableTextListKt$editableTextList$impl$1, PreferenceScreen preferenceScreen, TextAdapter<T> textAdapter, KMutableProperty0<List<T>> kMutableProperty0, Continuation<? super C00151> continuation) {
                super(2, continuation);
                this.$impl = editableTextListKt$editableTextList$impl$1;
                this.$this_editableTextList = preferenceScreen;
                this.$adapter = textAdapter;
                this.$value = kMutableProperty0;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00151(this.$impl, this.$this_editableTextList, this.$adapter, this.$value, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new C00151(this.$impl, this.$this_editableTextList, this.$adapter, this.$value, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                List list;
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    InputKt.throwOnFailure(obj);
                    List<? extends T> list2 = this.$impl.list;
                    Context context = this.$this_editableTextList.getContext();
                    TextAdapter<T> textAdapter = this.$adapter;
                    CharSequence title = this.$impl.getTitle();
                    this.label = 1;
                    obj = InputKt.access$requestEditTextList(list2, context, textAdapter, title, this);
                    if (obj == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else if (i == 1) {
                    InputKt.throwOnFailure(obj);
                } else if (i == 2) {
                    list = (List) this.L$0;
                    InputKt.throwOnFailure(obj);
                    this.$impl.setList(list);
                    return Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                List list3 = (List) obj;
                Dispatchers dispatchers = Dispatchers.INSTANCE;
                CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
                C00161 r3 = new C00161(this.$value, list3, null);
                this.L$0 = list3;
                this.label = 2;
                if (InputKt.withContext(coroutineDispatcher, r3, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                list = list3;
                this.$impl.setList(list);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(PreferenceScreen preferenceScreen, EditableTextListKt$editableTextList$impl$1 editableTextListKt$editableTextList$impl$1, TextAdapter<T> textAdapter, KMutableProperty0<List<T>> kMutableProperty0) {
            super(0);
            this.$this_editableTextList = preferenceScreen;
            this.$impl = editableTextListKt$editableTextList$impl$1;
            this.$adapter = textAdapter;
            this.$value = kMutableProperty0;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            PreferenceScreen preferenceScreen = this.$this_editableTextList;
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            InputKt.launch$default(preferenceScreen, MainDispatcherLoader.dispatcher, null, new C00151(this.$impl, preferenceScreen, this.$adapter, this.$value, null), 2, null);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditableTextListKt$editableTextList$2(EditableTextListKt$editableTextList$impl$1 editableTextListKt$editableTextList$impl$1, KMutableProperty0<List<T>> kMutableProperty0, PreferenceScreen preferenceScreen, TextAdapter<T> textAdapter, Continuation<? super EditableTextListKt$editableTextList$2> continuation) {
        super(2, continuation);
        this.$impl = editableTextListKt$editableTextList$impl$1;
        this.$value = kMutableProperty0;
        this.$this_editableTextList = preferenceScreen;
        this.$adapter = textAdapter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EditableTextListKt$editableTextList$2(this.$impl, this.$value, this.$this_editableTextList, this.$adapter, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new EditableTextListKt$editableTextList$2(this.$impl, this.$value, this.$this_editableTextList, this.$adapter, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
            EditableTextListKt$editableTextList$2$v$1 editableTextListKt$editableTextList$2$v$1 = new EditableTextListKt$editableTextList$2$v$1(this.$value, null);
            this.label = 1;
            obj = InputKt.withContext(coroutineDispatcher, editableTextListKt$editableTextList$2$v$1, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$impl.setList((List) obj);
        EditableTextListKt$editableTextList$impl$1 editableTextListKt$editableTextList$impl$1 = this.$impl;
        editableTextListKt$editableTextList$impl$1.$$delegate_0.clicked(new AnonymousClass1(this.$this_editableTextList, editableTextListKt$editableTextList$impl$1, this.$adapter, this.$value));
        return Unit.INSTANCE;
    }
}
