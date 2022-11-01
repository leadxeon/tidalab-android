package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.MainCoroutineDispatcher;
/* compiled from: HandlerDispatcher.kt */
/* loaded from: classes.dex */
public final class HandlerContext extends HandlerDispatcher implements Delay {
    private volatile HandlerContext _immediate;
    public final Handler handler;
    public final HandlerContext immediate;
    public final boolean invokeImmediately;
    public final String name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerContext(Handler handler, String str, boolean z) {
        super(null);
        HandlerContext handlerContext = null;
        this.handler = handler;
        this.name = str;
        this.invokeImmediately = z;
        this._immediate = z ? this : handlerContext;
        HandlerContext handlerContext2 = this._immediate;
        if (handlerContext2 == null) {
            handlerContext2 = new HandlerContext(handler, str, true);
            this._immediate = handlerContext2;
            Unit unit = Unit.INSTANCE;
        }
        this.immediate = handlerContext2;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        this.handler.post(runnable);
    }

    public boolean equals(Object obj) {
        return (obj instanceof HandlerContext) && ((HandlerContext) obj).handler == this.handler;
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher
    public MainCoroutineDispatcher getImmediate() {
        return this.immediate;
    }

    public int hashCode() {
        return System.identityHashCode(this.handler);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        return !this.invokeImmediately || (Intrinsics.areEqual(Looper.myLooper(), this.handler.getLooper()) ^ true);
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long j, final CancellableContinuation<? super Unit> cancellableContinuation) {
        Runnable handlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1 = new Runnable() { // from class: kotlinx.coroutines.android.HandlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                cancellableContinuation.resumeUndispatched(HandlerContext.this, Unit.INSTANCE);
            }
        };
        Handler handler = this.handler;
        if (j > 4611686018427387903L) {
            j = 4611686018427387903L;
        }
        handler.postDelayed(handlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1, j);
        ((CancellableContinuationImpl) cancellableContinuation).invokeOnCancellation(new HandlerContext$scheduleResumeAfterDelay$1(this, handlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1));
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String stringInternalImpl = toStringInternalImpl();
        if (stringInternalImpl != null) {
            return stringInternalImpl;
        }
        String str = this.name;
        if (str == null) {
            str = this.handler.toString();
        }
        return this.invokeImmediately ? GeneratedOutlineSupport.outline8(str, ".immediate") : str;
    }
}
