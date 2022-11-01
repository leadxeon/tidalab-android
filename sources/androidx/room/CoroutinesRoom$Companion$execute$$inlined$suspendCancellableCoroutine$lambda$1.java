package androidx.room;

import android.os.CancellationSignal;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.concurrent.Callable;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: CoroutinesRoom.kt */
@DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$execute$4$job$1", f = "CoroutinesRoom.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class CoroutinesRoom$Companion$execute$$inlined$suspendCancellableCoroutine$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Callable $callable$inlined;
    public final /* synthetic */ CancellationSignal $cancellationSignal$inlined;
    public final /* synthetic */ ContinuationInterceptor $context$inlined;
    public final /* synthetic */ CancellableContinuation $continuation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutinesRoom$Companion$execute$$inlined$suspendCancellableCoroutine$lambda$1(CancellableContinuation cancellableContinuation, Continuation continuation, ContinuationInterceptor continuationInterceptor, Callable callable, CancellationSignal cancellationSignal) {
        super(2, continuation);
        this.$continuation = cancellableContinuation;
        this.$context$inlined = continuationInterceptor;
        this.$callable$inlined = callable;
        this.$cancellationSignal$inlined = cancellationSignal;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CoroutinesRoom$Companion$execute$$inlined$suspendCancellableCoroutine$lambda$1(this.$continuation, continuation, this.$context$inlined, this.$callable$inlined, this.$cancellationSignal$inlined);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        CancellableContinuation cancellableContinuation = this.$continuation;
        Callable callable = this.$callable$inlined;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        try {
            cancellableContinuation.resumeWith(callable.call());
        } catch (Throwable th) {
            cancellableContinuation.resumeWith(new Result.Failure(th));
        }
        return Unit.INSTANCE;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        try {
            this.$continuation.resumeWith(this.$callable$inlined.call());
        } catch (Throwable th) {
            this.$continuation.resumeWith(new Result.Failure(th));
        }
        return Unit.INSTANCE;
    }
}
