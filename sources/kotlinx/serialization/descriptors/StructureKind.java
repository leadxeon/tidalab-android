package kotlinx.serialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: SerialKinds.kt */
/* loaded from: classes.dex */
public abstract class StructureKind extends SerialKind {

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class CLASS extends StructureKind {
        public static final CLASS INSTANCE = new CLASS();

        public CLASS() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class LIST extends StructureKind {
        public static final LIST INSTANCE = new LIST();

        public LIST() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class MAP extends StructureKind {
        public static final MAP INSTANCE = new MAP();

        public MAP() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    /* loaded from: classes.dex */
    public static final class OBJECT extends StructureKind {
        public static final OBJECT INSTANCE = new OBJECT();

        public OBJECT() {
            super(null);
        }
    }

    public StructureKind(DefaultConstructorMarker defaultConstructorMarker) {
        super(null);
    }
}
