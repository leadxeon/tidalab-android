package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;
/* compiled from: ThreadContext.kt */
/* loaded from: classes.dex */
public final class ThreadContextKt$updateState$1 extends Lambda implements Function2<ThreadState, CoroutineContext.Element, ThreadState> {
    public static final ThreadContextKt$updateState$1 INSTANCE = new ThreadContextKt$updateState$1();

    public ThreadContextKt$updateState$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public ThreadState invoke(ThreadState threadState, CoroutineContext.Element element) {
        ThreadState threadState2 = threadState;
        CoroutineContext.Element element2 = element;
        if (element2 instanceof ThreadContextElement) {
            ThreadContextElement<Object> threadContextElement = (ThreadContextElement) element2;
            Object updateThreadContext = threadContextElement.updateThreadContext(threadState2.context);
            Object[] objArr = threadState2.values;
            int i = threadState2.i;
            objArr[i] = updateThreadContext;
            ThreadContextElement<Object>[] threadContextElementArr = threadState2.elements;
            threadState2.i = i + 1;
            threadContextElementArr[i] = threadContextElement;
        }
        return threadState2;
    }
}
