package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
/* compiled from: CompletionHandler.kt */
/* loaded from: classes.dex */
public abstract class CompletionHandlerBase extends LockFreeLinkedListNode implements Function1<Throwable, Unit> {
    public abstract void invoke(Throwable th);
}
