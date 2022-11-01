package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
/* compiled from: SerialKinds.kt */
/* loaded from: classes.dex */
public abstract class SerialKind {

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class CONTEXTUAL extends SerialKind {
        public static final CONTEXTUAL INSTANCE = new CONTEXTUAL();

        public CONTEXTUAL() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class ENUM extends SerialKind {
        public static final ENUM INSTANCE = new ENUM();

        public ENUM() {
            super(null);
        }
    }

    public SerialKind(DefaultConstructorMarker defaultConstructorMarker) {
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        return ((ClassReference) Reflection.getOrCreateKotlinClass(getClass())).getSimpleName();
    }
}
