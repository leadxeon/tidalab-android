package kotlin.jvm.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;
/* loaded from: classes.dex */
public class FunctionReference extends CallableReference implements FunctionBase, KFunction {
    public final int arity;
    public final int flags;

    public FunctionReference(int i, Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, (i2 & 1) == 1);
        this.arity = i;
        this.flags = i2 >> 1;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KCallable computeReflected() {
        Objects.requireNonNull(Reflection.factory);
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReference) {
            FunctionReference functionReference = (FunctionReference) obj;
            return Intrinsics.areEqual(getOwner(), functionReference.getOwner()) && this.name.equals(functionReference.name) && this.signature.equals(functionReference.signature) && this.flags == functionReference.flags && this.arity == functionReference.arity && Intrinsics.areEqual(this.receiver, functionReference.receiver);
        } else if (obj instanceof KFunction) {
            return obj.equals(compute());
        } else {
            return false;
        }
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.arity;
    }

    public int hashCode() {
        return this.signature.hashCode() + GeneratedOutlineSupport.outline1(this.name, getOwner() == null ? 0 : getOwner().hashCode() * 31, 31);
    }

    public String toString() {
        KCallable compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        return "<init>".equals(this.name) ? "constructor (Kotlin reflection is not available)" : GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("function "), this.name, " (Kotlin reflection is not available)");
    }
}
