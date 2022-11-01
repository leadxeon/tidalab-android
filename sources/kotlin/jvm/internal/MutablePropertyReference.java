package kotlin.jvm.internal;

import kotlin.reflect.KProperty;
/* loaded from: classes.dex */
public abstract class MutablePropertyReference extends PropertyReference implements KProperty {
    public MutablePropertyReference(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
