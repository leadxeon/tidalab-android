package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* compiled from: CancellableContinuationImpl.kt */
/* loaded from: classes.dex */
public final class InvokeOnCancel extends CancelHandler {
    public final Function1<Throwable, Unit> handler;

    /* JADX WARN: Multi-variable type inference failed */
    public InvokeOnCancel(Function1<? super Throwable, Unit> function1) {
        this.handler = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.handler.invoke(th);
        return Unit.INSTANCE;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("InvokeOnCancel[");
        outline13.append(InputKt.getClassSimpleName(this.handler));
        outline13.append('@');
        outline13.append(InputKt.getHexAddress(this));
        outline13.append(']');
        return outline13.toString();
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        this.handler.invoke(th);
    }
}
