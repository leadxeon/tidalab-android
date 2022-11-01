package kotlinx.coroutines.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Scopes.kt */
/* loaded from: classes.dex */
public final class ContextScope implements CoroutineScope {
    public final CoroutineContext coroutineContext;

    public ContextScope(CoroutineContext coroutineContext) {
        this.coroutineContext = coroutineContext;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("CoroutineScope(coroutineContext=");
        outline13.append(this.coroutineContext);
        outline13.append(')');
        return outline13.toString();
    }
}
