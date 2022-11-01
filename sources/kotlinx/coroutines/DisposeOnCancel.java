package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.Unit;
/* compiled from: CancellableContinuation.kt */
/* loaded from: classes.dex */
public final class DisposeOnCancel extends CancelHandler {
    public final DisposableHandle handle;

    public DisposeOnCancel(DisposableHandle disposableHandle) {
        this.handle = disposableHandle;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.handle.dispose();
        return Unit.INSTANCE;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("DisposeOnCancel[");
        outline13.append(this.handle);
        outline13.append(']');
        return outline13.toString();
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        this.handle.dispose();
    }
}
