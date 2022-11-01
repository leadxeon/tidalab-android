package kotlinx.coroutines;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: Unconfined.kt */
/* loaded from: classes.dex */
public final class YieldContext extends AbstractCoroutineContextElement {
    public static final Key Key = new Key(null);

    /* compiled from: Unconfined.kt */
    /* loaded from: classes.dex */
    public static final class Key implements CoroutineContext.Key<YieldContext> {
        public Key(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public YieldContext() {
        super(Key);
    }
}
