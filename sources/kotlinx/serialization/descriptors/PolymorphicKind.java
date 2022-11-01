package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: SerialKinds.kt */
/* loaded from: classes.dex */
public abstract class PolymorphicKind extends SerialKind {

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class OPEN extends PolymorphicKind {
        public static final OPEN INSTANCE = new OPEN();

        public OPEN() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class SEALED extends PolymorphicKind {
        public static final SEALED INSTANCE = new SEALED();

        public SEALED() {
            super(null);
        }
    }

    public PolymorphicKind(DefaultConstructorMarker defaultConstructorMarker) {
        super(null);
    }
}
