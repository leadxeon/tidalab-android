package kotlin.jvm.internal;

import kotlin.jvm.internal.CallableReference;
import kotlin.reflect.KDeclarationContainer;
/* loaded from: classes.dex */
public class MutablePropertyReference1Impl extends MutablePropertyReference1 {
    public MutablePropertyReference1Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(CallableReference.NoReceiver.INSTANCE, ((ClassBasedDeclarationContainer) kDeclarationContainer).getJClass(), str, str2, 0);
    }

    @Override // kotlin.reflect.KProperty1
    public Object get(Object obj) {
        return getGetter().call(obj);
    }
}
