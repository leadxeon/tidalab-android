package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
/* compiled from: Builders.common.kt */
/* loaded from: classes.dex */
public class StandaloneCoroutine extends AbstractCoroutine<Unit> {
    public StandaloneCoroutine(CoroutineContext coroutineContext, boolean z) {
        super(coroutineContext, true, z);
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean handleJobException(Throwable th) {
        InputKt.handleCoroutineException(this.context, th);
        return true;
    }
}
