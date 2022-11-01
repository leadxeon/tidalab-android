package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
/* compiled from: Builders.common.kt */
/* loaded from: classes.dex */
public final class LazyStandaloneCoroutine extends StandaloneCoroutine {
    public final Continuation<Unit> continuation;

    public LazyStandaloneCoroutine(CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        super(coroutineContext, false);
        this.continuation = InputKt.createCoroutineUnintercepted(function2, this, this);
    }

    @Override // kotlinx.coroutines.JobSupport
    public void onStart() {
        try {
            DispatchedContinuationKt.resumeCancellableWith$default(InputKt.intercepted(this.continuation), Unit.INSTANCE, null, 2);
        } catch (Throwable th) {
            resumeWith(new Result.Failure(th));
        }
    }
}
