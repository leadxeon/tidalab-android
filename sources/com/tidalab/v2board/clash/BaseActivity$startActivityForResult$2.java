package com.tidalab.v2board.clash;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.util.ActivityResultLifecycle;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
/*  JADX ERROR: JadxRuntimeException in pass: ClassModifier
    jadx.core.utils.exceptions.JadxRuntimeException: Not class type: I
    	at jadx.core.dex.info.ClassInfo.checkClassType(ClassInfo.java:53)
    	at jadx.core.dex.info.ClassInfo.fromType(ClassInfo.java:31)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticFields(ClassModifier.java:80)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:58)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:52)
    */
/* compiled from: BaseActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.BaseActivity$startActivityForResult$2", f = "BaseActivity.kt", l = {79}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class BaseActivity$startActivityForResult$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super O>, Object> {
    public final /* synthetic */ ActivityResultContract<I, O> $contracts;
    public final /* synthetic */ I $input;
    public int label;
    public final /* synthetic */ BaseActivity<D> this$0;

    /* compiled from: BaseActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.BaseActivity$startActivityForResult$2$1", f = "BaseActivity.kt", l = {80}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.BaseActivity$startActivityForResult$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function3<ActivityResultLifecycle, Function0<? extends Unit>, Continuation<? super O>, Object> {
        public final /* synthetic */ ActivityResultContract<I, O> $contracts;
        public final /* synthetic */ I $input;
        public final /* synthetic */ String $requestKey;
        public /* synthetic */ Object L$0;
        public /* synthetic */ Object L$1;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public int label;
        public final /* synthetic */ BaseActivity<D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BaseActivity<D> baseActivity, String str, ActivityResultContract<I, O> activityResultContract, I i, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.this$0 = baseActivity;
            this.$requestKey = str;
            this.$contracts = activityResultContract;
            this.$input = i;
        }

        @Override // kotlin.jvm.functions.Function3
        public Object invoke(ActivityResultLifecycle activityResultLifecycle, Function0<? extends Unit> function0, Object obj) {
            AnonymousClass1 r9 = new AnonymousClass1(this.this$0, this.$requestKey, this.$contracts, this.$input, (Continuation) obj);
            r9.L$0 = activityResultLifecycle;
            r9.L$1 = function0;
            return r9.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            boolean z = true;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                ActivityResultLifecycle activityResultLifecycle = (ActivityResultLifecycle) this.L$0;
                Function0 function0 = (Function0) this.L$1;
                ComponentActivity componentActivity = this.this$0;
                final String str = this.$requestKey;
                final ActivityResultContract<I, O> activityResultContract = this.$contracts;
                I i2 = this.$input;
                this.L$0 = activityResultLifecycle;
                this.L$1 = function0;
                this.L$2 = componentActivity;
                this.L$3 = str;
                this.L$4 = activityResultContract;
                this.L$5 = i2;
                this.label = 1;
                final SafeContinuation safeContinuation = new SafeContinuation(InputKt.intercepted(this));
                final ActivityResultRegistry activityResultRegistry = componentActivity.mActivityResultRegistry;
                final ActivityResultCallback baseActivity$startActivityForResult$2$1$1$1 = new ActivityResultCallback() { // from class: com.tidalab.v2board.clash.BaseActivity$startActivityForResult$2$1$1$1
                    @Override // androidx.activity.result.ActivityResultCallback
                    public final void onActivityResult(O o) {
                        safeContinuation.resumeWith(o);
                    }
                };
                Objects.requireNonNull(activityResultRegistry);
                Lifecycle lifecycle = activityResultLifecycle.getLifecycle();
                if (lifecycle.getCurrentState().compareTo(Lifecycle.State.STARTED) < 0) {
                    z = false;
                }
                if (!z) {
                    int registerKey = activityResultRegistry.registerKey(str);
                    ActivityResultRegistry.LifecycleContainer lifecycleContainer = activityResultRegistry.mKeyToLifecycleContainers.get(str);
                    if (lifecycleContainer == null) {
                        lifecycleContainer = new ActivityResultRegistry.LifecycleContainer(lifecycle);
                    }
                    LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry.1
                        @Override // androidx.lifecycle.LifecycleEventObserver
                        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                            if (Lifecycle.Event.ON_START.equals(event)) {
                                activityResultRegistry.mKeyToCallback.put(str, new CallbackAndContract<>(baseActivity$startActivityForResult$2$1$1$1, activityResultContract));
                                if (activityResultRegistry.mParsedPendingResults.containsKey(str)) {
                                    Object obj2 = activityResultRegistry.mParsedPendingResults.get(str);
                                    activityResultRegistry.mParsedPendingResults.remove(str);
                                    baseActivity$startActivityForResult$2$1$1$1.onActivityResult(obj2);
                                }
                                ActivityResult activityResult = (ActivityResult) activityResultRegistry.mPendingResults.getParcelable(str);
                                if (activityResult != null) {
                                    activityResultRegistry.mPendingResults.remove(str);
                                    baseActivity$startActivityForResult$2$1$1$1.onActivityResult(activityResultContract.parseResult(activityResult.mResultCode, activityResult.mData));
                                }
                            } else if (Lifecycle.Event.ON_STOP.equals(event)) {
                                activityResultRegistry.mKeyToCallback.remove(str);
                            } else if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                                activityResultRegistry.unregister(str);
                            }
                        }
                    };
                    lifecycleContainer.mLifecycle.addObserver(lifecycleEventObserver);
                    lifecycleContainer.mObservers.add(lifecycleEventObserver);
                    activityResultRegistry.mKeyToLifecycleContainers.put(str, lifecycleContainer);
                    function0.invoke();
                    activityResultRegistry.mLaunchedKeys.add(str);
                    activityResultRegistry.onLaunch(registerKey, activityResultContract, i2, null);
                    obj = safeContinuation.getOrThrow();
                    if (obj == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    throw new IllegalStateException("LifecycleOwner " + activityResultLifecycle + " is attempting to register while current state is " + lifecycle.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.");
                }
            } else if (i == 1) {
                ActivityResultContract activityResultContract2 = (ActivityResultContract) this.L$4;
                String str2 = (String) this.L$3;
                BaseActivity baseActivity = (BaseActivity) this.L$2;
                Function0 function02 = (Function0) this.L$1;
                ActivityResultLifecycle activityResultLifecycle2 = (ActivityResultLifecycle) this.L$0;
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return obj;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseActivity$startActivityForResult$2(BaseActivity<D> baseActivity, ActivityResultContract<I, O> activityResultContract, I i, Continuation<? super BaseActivity$startActivityForResult$2> continuation) {
        super(2, continuation);
        this.this$0 = baseActivity;
        this.$contracts = activityResultContract;
        this.$input = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseActivity$startActivityForResult$2(this.this$0, this.$contracts, this.$input, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Object obj) {
        return new BaseActivity$startActivityForResult$2(this.this$0, this.$contracts, this.$input, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            String valueOf = String.valueOf(this.this$0.nextRequestKey.getAndIncrement());
            ActivityResultLifecycle activityResultLifecycle = new ActivityResultLifecycle();
            AnonymousClass1 r1 = new AnonymousClass1(this.this$0, valueOf, this.$contracts, this.$input, null);
            this.label = 1;
            obj = activityResultLifecycle.use(r1, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
