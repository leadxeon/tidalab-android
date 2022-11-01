package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: SerialKinds.kt */
/* loaded from: classes.dex */
public abstract class PrimitiveKind extends SerialKind {

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class BOOLEAN extends PrimitiveKind {
        public static final BOOLEAN INSTANCE = new BOOLEAN();

        public BOOLEAN() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class BYTE extends PrimitiveKind {
        public static final BYTE INSTANCE = new BYTE();

        public BYTE() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class CHAR extends PrimitiveKind {
        public static final CHAR INSTANCE = new CHAR();

        public CHAR() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class DOUBLE extends PrimitiveKind {
        public static final DOUBLE INSTANCE = new DOUBLE();

        public DOUBLE() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class FLOAT extends PrimitiveKind {
        public static final FLOAT INSTANCE = new FLOAT();

        public FLOAT() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class INT extends PrimitiveKind {
        public static final INT INSTANCE = new INT();

        public INT() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class LONG extends PrimitiveKind {
        public static final LONG INSTANCE = new LONG();

        public LONG() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class SHORT extends PrimitiveKind {
        public static final SHORT INSTANCE = new SHORT();

        public SHORT() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class STRING extends PrimitiveKind {
        public static final STRING INSTANCE = new STRING();

        public STRING() {
            super(null);
        }
    }

    public PrimitiveKind(DefaultConstructorMarker defaultConstructorMarker) {
        super(null);
    }
}
