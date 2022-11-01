package kotlinx.coroutines.internal;

import java.util.Objects;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ThreadContextElement;
/* compiled from: ThreadContext.kt */
/* loaded from: classes.dex */
public final class ThreadContextKt {
    public static final Symbol NO_THREAD_ELEMENTS = new Symbol("NO_THREAD_ELEMENTS");

    public static final void restoreThreadContext(CoroutineContext coroutineContext, Object obj) {
        if (obj != NO_THREAD_ELEMENTS) {
            if (obj instanceof ThreadState) {
                ThreadState threadState = (ThreadState) obj;
                int length = threadState.elements.length - 1;
                if (length >= 0) {
                    while (true) {
                        int i = length - 1;
                        threadState.elements[length].restoreThreadContext(coroutineContext, threadState.values[length]);
                        if (i >= 0) {
                            length = i;
                        } else {
                            return;
                        }
                    }
                }
            } else {
                Object fold = coroutineContext.fold(null, ThreadContextKt$findOne$1.INSTANCE);
                Objects.requireNonNull(fold, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
                ((ThreadContextElement) fold).restoreThreadContext(coroutineContext, obj);
            }
        }
    }

    public static final Object updateThreadContext(CoroutineContext coroutineContext, Object obj) {
        if (obj == null) {
            obj = coroutineContext.fold(0, ThreadContextKt$countAll$1.INSTANCE);
        }
        if (obj == 0) {
            return NO_THREAD_ELEMENTS;
        }
        if (obj instanceof Integer) {
            return coroutineContext.fold(new ThreadState(coroutineContext, ((Number) obj).intValue()), ThreadContextKt$updateState$1.INSTANCE);
        }
        return ((ThreadContextElement) obj).updateThreadContext(coroutineContext);
    }
}
