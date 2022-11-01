package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectInstance;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public final class SelectAwaitOnCompletion<T, R> extends JobNode {
    public final Function2<T, Continuation<? super R>, Object> block;
    public final SelectInstance<R> select;

    /* JADX WARN: Multi-variable type inference failed */
    public SelectAwaitOnCompletion(SelectInstance<? super R> selectInstance, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        this.select = selectInstance;
        this.block = function2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        if (this.select.trySelect()) {
            JobSupport job = getJob();
            SelectInstance<R> selectInstance = this.select;
            Function2<T, Continuation<? super R>, Object> function2 = this.block;
            Object state$kotlinx_coroutines_core = job.getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                selectInstance.resumeSelectWithException(((CompletedExceptionally) state$kotlinx_coroutines_core).cause);
            } else {
                InputKt.startCoroutineCancellable$default(function2, JobSupportKt.unboxState(state$kotlinx_coroutines_core), selectInstance.getCompletion(), null, 4);
            }
        }
    }
}
