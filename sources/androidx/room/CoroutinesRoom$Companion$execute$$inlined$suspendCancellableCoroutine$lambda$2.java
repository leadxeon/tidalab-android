package androidx.room;

import android.os.CancellationSignal;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;
/* compiled from: CoroutinesRoom.kt */
/* loaded from: classes.dex */
public final class CoroutinesRoom$Companion$execute$$inlined$suspendCancellableCoroutine$lambda$2 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ CancellationSignal $cancellationSignal$inlined;
    public final /* synthetic */ ContinuationInterceptor $context$inlined;
    public final /* synthetic */ Job $job;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutinesRoom$Companion$execute$$inlined$suspendCancellableCoroutine$lambda$2(Job job, ContinuationInterceptor continuationInterceptor, Callable callable, CancellationSignal cancellationSignal) {
        super(1);
        this.$job = job;
        this.$context$inlined = continuationInterceptor;
        this.$cancellationSignal$inlined = cancellationSignal;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.$cancellationSignal$inlined.cancel();
        InputKt.cancel$default(this.$job, null, 1, null);
        return Unit.INSTANCE;
    }
}
