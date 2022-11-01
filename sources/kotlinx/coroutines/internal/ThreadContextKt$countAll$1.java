package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.ThreadContextElement;
/* compiled from: ThreadContext.kt */
/* loaded from: classes.dex */
public final class ThreadContextKt$countAll$1 extends Lambda implements Function2<Object, CoroutineContext.Element, Object> {
    public static final ThreadContextKt$countAll$1 INSTANCE = new ThreadContextKt$countAll$1();

    public ThreadContextKt$countAll$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Object obj, CoroutineContext.Element element) {
        CoroutineContext.Element element2 = element;
        if (!(element2 instanceof ThreadContextElement)) {
            return obj;
        }
        Integer num = obj instanceof Integer ? (Integer) obj : null;
        int intValue = num == null ? 1 : num.intValue();
        return intValue == 0 ? element2 : Integer.valueOf(intValue + 1);
    }
}
