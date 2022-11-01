package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.concurrent.Future;
import kotlin.Unit;
/* compiled from: Future.kt */
/* loaded from: classes.dex */
public final class CancelFutureOnCancel extends CancelHandler {
    public final Future<?> future;

    public CancelFutureOnCancel(Future<?> future) {
        this.future = future;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.future.cancel(false);
        return Unit.INSTANCE;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("CancelFutureOnCancel[");
        outline13.append(this.future);
        outline13.append(']');
        return outline13.toString();
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        this.future.cancel(false);
    }
}
