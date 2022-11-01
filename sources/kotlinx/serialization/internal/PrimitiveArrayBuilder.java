package kotlinx.serialization.internal;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public abstract class PrimitiveArrayBuilder<Array> {
    public static /* synthetic */ void ensureCapacity$kotlinx_serialization_core$default(PrimitiveArrayBuilder primitiveArrayBuilder, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = primitiveArrayBuilder.getPosition$kotlinx_serialization_core() + 1;
        }
        primitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core(i);
    }

    public abstract Array build$kotlinx_serialization_core();

    public abstract void ensureCapacity$kotlinx_serialization_core(int i);

    public abstract int getPosition$kotlinx_serialization_core();
}
