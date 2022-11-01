package com.tidalab.v2board.clash.design.preference;

import android.content.Context;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Map;
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
/* compiled from: EditableTextMap.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextMapKt$editableTextMap$2", f = "EditableTextMap.kt", l = {58}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class EditableTextMapKt$editableTextMap$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ EditableTextMapKt$editableTextMap$impl$1 $impl;
    public final /* synthetic */ TextAdapter<K> $keyAdapter;
    public final /* synthetic */ PreferenceScreen $this_editableTextMap;
    public final /* synthetic */ KMutableProperty0<Map<K, V>> $value;
    public final /* synthetic */ TextAdapter<V> $valueAdapter;
    public int label;

    /* compiled from: EditableTextMap.kt */
    /* renamed from: com.tidalab.v2board.clash.design.preference.EditableTextMapKt$editableTextMap$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        public final /* synthetic */ EditableTextMapKt$editableTextMap$impl$1 $impl;
        public final /* synthetic */ TextAdapter<K> $keyAdapter;
        public final /* synthetic */ PreferenceScreen $this_editableTextMap;
        public final /* synthetic */ KMutableProperty0<Map<K, V>> $value;
        public final /* synthetic */ TextAdapter<V> $valueAdapter;

        /* compiled from: EditableTextMap.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextMapKt$editableTextMap$2$1$1", f = "EditableTextMap.kt", l = {66, 74}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.design.preference.EditableTextMapKt$editableTextMap$2$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public final class C00171 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ EditableTextMapKt$editableTextMap$impl$1 $impl;
            public final /* synthetic */ TextAdapter<K> $keyAdapter;
            public final /* synthetic */ PreferenceScreen $this_editableTextMap;
            public final /* synthetic */ KMutableProperty0<Map<K, V>> $value;
            public final /* synthetic */ TextAdapter<V> $valueAdapter;
            public Object L$0;
            public int label;

            /* compiled from: EditableTextMap.kt */
            @DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.EditableTextMapKt$editableTextMap$2$1$1$1", f = "EditableTextMap.kt", l = {}, m = "invokeSuspend")
            /* renamed from: com.tidalab.v2board.clash.design.preference.EditableTextMapKt$editableTextMap$2$1$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C00181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ Map<K, V> $newMap;
                public final /* synthetic */ KMutableProperty0<Map<K, V>> $value;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00181(KMutableProperty0<Map<K, V>> kMutableProperty0, Map<K, ? extends V> map, Continuation<? super C00181> continuation) {
                    super(2, continuation);
                    this.$value = kMutableProperty0;
                    this.$newMap = map;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00181(this.$value, this.$newMap, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    Continuation<? super Unit> continuation2 = continuation;
                    KMutableProperty0<Map<K, V>> kMutableProperty0 = this.$value;
                    Object obj = this.$newMap;
                    if (continuation2 != null) {
                        continuation2.getContext();
                    }
                    Unit unit = Unit.INSTANCE;
                    InputKt.throwOnFailure(unit);
                    kMutableProperty0.set(obj);
                    return unit;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    InputKt.throwOnFailure(obj);
                    this.$value.set(this.$newMap);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00171(EditableTextMapKt$editableTextMap$impl$1 editableTextMapKt$editableTextMap$impl$1, PreferenceScreen preferenceScreen, TextAdapter<K> textAdapter, TextAdapter<V> textAdapter2, KMutableProperty0<Map<K, V>> kMutableProperty0, Continuation<? super C00171> continuation) {
                super(2, continuation);
                this.$impl = editableTextMapKt$editableTextMap$impl$1;
                this.$this_editableTextMap = preferenceScreen;
                this.$keyAdapter = textAdapter;
                this.$valueAdapter = textAdapter2;
                this.$value = kMutableProperty0;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00171(this.$impl, this.$this_editableTextMap, this.$keyAdapter, this.$valueAdapter, this.$value, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new C00171(this.$impl, this.$this_editableTextMap, this.$keyAdapter, this.$valueAdapter, this.$value, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Map map;
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    InputKt.throwOnFailure(obj);
                    Map<K, ? extends V> map2 = this.$impl.map;
                    Context context = this.$this_editableTextMap.getContext();
                    TextAdapter<K> textAdapter = this.$keyAdapter;
                    TextAdapter<V> textAdapter2 = this.$valueAdapter;
                    CharSequence title = this.$impl.getTitle();
                    this.label = 1;
                    obj = InputKt.access$requestEditTextMap(map2, context, textAdapter, textAdapter2, title, this);
                    if (obj == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else if (i == 1) {
                    InputKt.throwOnFailure(obj);
                } else if (i == 2) {
                    map = (Map) this.L$0;
                    InputKt.throwOnFailure(obj);
                    this.$impl.setMap(map);
                    return Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Map map3 = (Map) obj;
                Dispatchers dispatchers = Dispatchers.INSTANCE;
                CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
                C00181 r3 = new C00181(this.$value, map3, null);
                this.L$0 = map3;
                this.label = 2;
                if (InputKt.withContext(coroutineDispatcher, r3, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                map = map3;
                this.$impl.setMap(map);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(PreferenceScreen preferenceScreen, EditableTextMapKt$editableTextMap$impl$1 editableTextMapKt$editableTextMap$impl$1, TextAdapter<K> textAdapter, TextAdapter<V> textAdapter2, KMutableProperty0<Map<K, V>> kMutableProperty0) {
            super(0);
            this.$this_editableTextMap = preferenceScreen;
            this.$impl = editableTextMapKt$editableTextMap$impl$1;
            this.$keyAdapter = textAdapter;
            this.$valueAdapter = textAdapter2;
            this.$value = kMutableProperty0;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            PreferenceScreen preferenceScreen = this.$this_editableTextMap;
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            InputKt.launch$default(preferenceScreen, MainDispatcherLoader.dispatcher, null, new C00171(this.$impl, preferenceScreen, this.$keyAdapter, this.$valueAdapter, this.$value, null), 2, null);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditableTextMapKt$editableTextMap$2(EditableTextMapKt$editableTextMap$impl$1 editableTextMapKt$editableTextMap$impl$1, KMutableProperty0<Map<K, V>> kMutableProperty0, PreferenceScreen preferenceScreen, TextAdapter<K> textAdapter, TextAdapter<V> textAdapter2, Continuation<? super EditableTextMapKt$editableTextMap$2> continuation) {
        super(2, continuation);
        this.$impl = editableTextMapKt$editableTextMap$impl$1;
        this.$value = kMutableProperty0;
        this.$this_editableTextMap = preferenceScreen;
        this.$keyAdapter = textAdapter;
        this.$valueAdapter = textAdapter2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EditableTextMapKt$editableTextMap$2(this.$impl, this.$value, this.$this_editableTextMap, this.$keyAdapter, this.$valueAdapter, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new EditableTextMapKt$editableTextMap$2(this.$impl, this.$value, this.$this_editableTextMap, this.$keyAdapter, this.$valueAdapter, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
            EditableTextMapKt$editableTextMap$2$v$1 editableTextMapKt$editableTextMap$2$v$1 = new EditableTextMapKt$editableTextMap$2$v$1(this.$value, null);
            this.label = 1;
            obj = InputKt.withContext(coroutineDispatcher, editableTextMapKt$editableTextMap$2$v$1, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$impl.setMap((Map) obj);
        EditableTextMapKt$editableTextMap$impl$1 editableTextMapKt$editableTextMap$impl$1 = this.$impl;
        editableTextMapKt$editableTextMap$impl$1.$$delegate_0.clicked(new AnonymousClass1(this.$this_editableTextMap, editableTextMapKt$editableTextMap$impl$1, this.$keyAdapter, this.$valueAdapter, this.$value));
        return Unit.INSTANCE;
    }
}
