package kotlinx.coroutines.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: OnUndeliveredElement.kt */
/* loaded from: classes.dex */
public final class OnUndeliveredElementKt$bindCancellationFun$1 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ CoroutineContext $context;
    public final /* synthetic */ E $element;
    public final /* synthetic */ Function1<E, Unit> $this_bindCancellationFun;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public OnUndeliveredElementKt$bindCancellationFun$1(Function1<? super E, Unit> function1, E e, CoroutineContext coroutineContext) {
        super(1);
        this.$this_bindCancellationFun = function1;
        this.$element = e;
        this.$context = coroutineContext;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        Function1<E, Unit> function1 = this.$this_bindCancellationFun;
        E e = this.$element;
        CoroutineContext coroutineContext = this.$context;
        UndeliveredElementException callUndeliveredElementCatchingException = InputKt.callUndeliveredElementCatchingException(function1, e, null);
        if (callUndeliveredElementCatchingException != null) {
            InputKt.handleCoroutineException(coroutineContext, callUndeliveredElementCatchingException);
        }
        return Unit.INSTANCE;
    }
}
