package kotlinx.coroutines.android;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: HandlerDispatcher.kt */
/* loaded from: classes.dex */
public final class HandlerContext$scheduleResumeAfterDelay$1 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ Runnable $block;
    public final /* synthetic */ HandlerContext this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerContext$scheduleResumeAfterDelay$1(HandlerContext handlerContext, Runnable runnable) {
        super(1);
        this.this$0 = handlerContext;
        this.$block = runnable;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.this$0.handler.removeCallbacks(this.$block);
        return Unit.INSTANCE;
    }
}
