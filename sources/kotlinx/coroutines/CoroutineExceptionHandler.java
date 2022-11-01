package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
/* compiled from: CoroutineExceptionHandler.kt */
/* loaded from: classes.dex */
public interface CoroutineExceptionHandler extends CoroutineContext.Element {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* compiled from: CoroutineExceptionHandler.kt */
    /* loaded from: classes.dex */
    public static final class Key implements CoroutineContext.Key<CoroutineExceptionHandler> {
        public static final /* synthetic */ Key $$INSTANCE = new Key();
    }

    void handleException(CoroutineContext coroutineContext, Throwable th);
}
