package androidx.room;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: CoroutinesRoom.kt */
@DebugMetadata(c = "androidx.room.CoroutinesRoom$Companion$execute$2", f = "CoroutinesRoom.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class CoroutinesRoom$Companion$execute$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
    public final /* synthetic */ Callable $callable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutinesRoom$Companion$execute$2(Callable callable, Continuation continuation) {
        super(2, continuation);
        this.$callable = callable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CoroutinesRoom$Companion$execute$2(this.$callable, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Object obj) {
        Continuation continuation = (Continuation) obj;
        Callable callable = this.$callable;
        if (continuation != null) {
            continuation.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return callable.call();
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return this.$callable.call();
    }
}
