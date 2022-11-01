package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
/* compiled from: CoroutineScope.kt */
/* loaded from: classes.dex */
public final class GlobalScope implements CoroutineScope {
    public static final GlobalScope INSTANCE = new GlobalScope();

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
