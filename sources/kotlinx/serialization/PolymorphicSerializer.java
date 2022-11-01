package kotlinx.serialization;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.reflect.KClass;
import kotlinx.serialization.descriptors.ContextDescriptor;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
/* compiled from: PolymorphicSerializer.kt */
/* loaded from: classes.dex */
public final class PolymorphicSerializer<T> extends AbstractPolymorphicSerializer<T> {
    public final KClass<T> baseClass;
    public final SerialDescriptor descriptor;

    public PolymorphicSerializer(KClass<T> kClass) {
        this.baseClass = kClass;
        this.descriptor = new ContextDescriptor(InputKt.buildSerialDescriptor("kotlinx.serialization.Polymorphic", PolymorphicKind.OPEN.INSTANCE, new SerialDescriptor[0], new PolymorphicSerializer$descriptor$1(this)), kClass);
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    public KClass<T> getBaseClass() {
        return this.baseClass;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("kotlinx.serialization.PolymorphicSerializer(baseClass: ");
        outline13.append(this.baseClass);
        outline13.append(')');
        return outline13.toString();
    }
}
