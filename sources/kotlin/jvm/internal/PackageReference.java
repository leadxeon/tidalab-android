package kotlin.jvm.internal;
/* compiled from: PackageReference.kt */
/* loaded from: classes.dex */
public final class PackageReference implements ClassBasedDeclarationContainer {
    public final Class<?> jClass;

    public PackageReference(Class<?> cls, String str) {
        this.jClass = cls;
    }

    public boolean equals(Object obj) {
        return (obj instanceof PackageReference) && Intrinsics.areEqual(this.jClass, ((PackageReference) obj).jClass);
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class<?> getJClass() {
        return this.jClass;
    }

    public int hashCode() {
        return this.jClass.hashCode();
    }

    public String toString() {
        return this.jClass.toString() + " (Kotlin reflection is not available)";
    }
}
