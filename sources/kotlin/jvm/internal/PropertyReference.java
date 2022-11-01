package kotlin.jvm.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty;
/* loaded from: classes.dex */
public abstract class PropertyReference extends CallableReference implements KProperty {
    public PropertyReference() {
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PropertyReference) {
            PropertyReference propertyReference = (PropertyReference) obj;
            return getOwner().equals(propertyReference.getOwner()) && this.name.equals(propertyReference.name) && this.signature.equals(propertyReference.signature) && Intrinsics.areEqual(this.receiver, propertyReference.receiver);
        } else if (obj instanceof KProperty) {
            return obj.equals(compute());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.signature.hashCode() + GeneratedOutlineSupport.outline1(this.name, getOwner().hashCode() * 31, 31);
    }

    public String toString() {
        KCallable compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        return GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("property "), this.name, " (Kotlin reflection is not available)");
    }

    public PropertyReference(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, (i & 1) == 1);
    }
}
