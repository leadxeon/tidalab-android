package kotlin.jvm.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import okhttp3.HttpUrl;
/* compiled from: TypeReference.kt */
/* loaded from: classes.dex */
public final class TypeReference implements KType {
    public final List<KTypeProjection> arguments;
    public final KClass classifier;
    public final boolean isMarkedNullable;

    public TypeReference(KClass kClass, List<KTypeProjection> list, boolean z) {
        this.classifier = kClass;
        this.arguments = list;
        this.isMarkedNullable = z;
    }

    public final String asString() {
        String str;
        KClass kClass = this.classifier;
        Class cls = null;
        if (!(kClass instanceof KClass)) {
            kClass = null;
        }
        if (kClass != null) {
            cls = InputKt.getJavaClass(kClass);
        }
        if (cls == null) {
            str = this.classifier.toString();
        } else if (!cls.isArray()) {
            str = cls.getName();
        } else if (Intrinsics.areEqual(cls, boolean[].class)) {
            str = "kotlin.BooleanArray";
        } else if (Intrinsics.areEqual(cls, char[].class)) {
            str = "kotlin.CharArray";
        } else if (Intrinsics.areEqual(cls, byte[].class)) {
            str = "kotlin.ByteArray";
        } else if (Intrinsics.areEqual(cls, short[].class)) {
            str = "kotlin.ShortArray";
        } else if (Intrinsics.areEqual(cls, int[].class)) {
            str = "kotlin.IntArray";
        } else if (Intrinsics.areEqual(cls, float[].class)) {
            str = "kotlin.FloatArray";
        } else if (Intrinsics.areEqual(cls, long[].class)) {
            str = "kotlin.LongArray";
        } else {
            str = Intrinsics.areEqual(cls, double[].class) ? "kotlin.DoubleArray" : "kotlin.Array";
        }
        boolean isEmpty = this.arguments.isEmpty();
        String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        String joinToString$default = isEmpty ? str2 : ArraysKt___ArraysKt.joinToString$default(this.arguments, ", ", "<", ">", 0, null, new TypeReference$asString$args$1(this), 24);
        if (this.isMarkedNullable) {
            str2 = "?";
        }
        return GeneratedOutlineSupport.outline9(str, joinToString$default, str2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof TypeReference) {
            TypeReference typeReference = (TypeReference) obj;
            if (Intrinsics.areEqual(this.classifier, typeReference.classifier) && Intrinsics.areEqual(this.arguments, typeReference.arguments) && this.isMarkedNullable == typeReference.isMarkedNullable) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.reflect.KType
    public List<KTypeProjection> getArguments() {
        return this.arguments;
    }

    @Override // kotlin.reflect.KType
    public KClass getClassifier() {
        return this.classifier;
    }

    public int hashCode() {
        int hashCode = this.arguments.hashCode();
        return Boolean.valueOf(this.isMarkedNullable).hashCode() + ((hashCode + (this.classifier.hashCode() * 31)) * 31);
    }

    @Override // kotlin.reflect.KType
    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    public String toString() {
        return asString() + " (Kotlin reflection is not available)";
    }
}
