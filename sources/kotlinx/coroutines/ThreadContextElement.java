package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
/* compiled from: ThreadContextElement.kt */
/* loaded from: classes.dex */
public interface ThreadContextElement<S> extends CoroutineContext.Element {
    void restoreThreadContext(CoroutineContext coroutineContext, S s);

    S updateThreadContext(CoroutineContext coroutineContext);
}
