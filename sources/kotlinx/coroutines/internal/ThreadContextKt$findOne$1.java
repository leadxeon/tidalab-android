package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;
/* compiled from: ThreadContext.kt */
/* loaded from: classes.dex */
public final class ThreadContextKt$findOne$1 extends Lambda implements Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>> {
    public static final ThreadContextKt$findOne$1 INSTANCE = new ThreadContextKt$findOne$1();

    public ThreadContextKt$findOne$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public ThreadContextElement<?> invoke(ThreadContextElement<?> threadContextElement, CoroutineContext.Element element) {
        ThreadContextElement<?> threadContextElement2 = threadContextElement;
        CoroutineContext.Element element2 = element;
        if (threadContextElement2 != null) {
            return threadContextElement2;
        }
        if (element2 instanceof ThreadContextElement) {
            return (ThreadContextElement) element2;
        }
        return null;
    }
}
