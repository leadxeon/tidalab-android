package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
/* compiled from: Builders.common.kt */
/* loaded from: classes.dex */
public final class DispatchedCoroutine<T> extends ScopeCoroutine<T> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater _decision$FU = AtomicIntegerFieldUpdater.newUpdater(DispatchedCoroutine.class, "_decision");
    private volatile /* synthetic */ int _decision = 0;

    public DispatchedCoroutine(CoroutineContext coroutineContext, Continuation<? super T> continuation) {
        super(coroutineContext, continuation);
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    public void afterCompletion(Object obj) {
        afterResume(obj);
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    public void afterResume(Object obj) {
        boolean z;
        while (true) {
            int i = this._decision;
            z = false;
            if (i == 0) {
                if (_decision$FU.compareAndSet(this, 0, 2)) {
                    z = true;
                    break;
                }
            } else if (i != 1) {
                throw new IllegalStateException("Already resumed".toString());
            }
        }
        if (!z) {
            DispatchedContinuationKt.resumeCancellableWith$default(InputKt.intercepted(this.uCont), InputKt.recoverResult(obj, this.uCont), null, 2);
        }
    }

    public final Object getResult() {
        boolean z;
        while (true) {
            int i = this._decision;
            z = false;
            if (i == 0) {
                if (_decision$FU.compareAndSet(this, 0, 1)) {
                    z = true;
                    break;
                }
            } else if (i != 2) {
                throw new IllegalStateException("Already suspended".toString());
            }
        }
        if (z) {
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        Object unboxState = JobSupportKt.unboxState(getState$kotlinx_coroutines_core());
        if (!(unboxState instanceof CompletedExceptionally)) {
            return unboxState;
        }
        throw ((CompletedExceptionally) unboxState).cause;
    }
}
