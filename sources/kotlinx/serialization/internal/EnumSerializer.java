package kotlinx.serialization.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.lang.Enum;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: Enums.kt */
/* loaded from: classes.dex */
public final class EnumSerializer<T extends Enum<T>> implements KSerializer<T> {
    public final SerialDescriptor descriptor;
    public final T[] values;

    public EnumSerializer(String str, T[] tArr) {
        this.values = tArr;
        this.descriptor = InputKt.buildSerialDescriptor(str, SerialKind.ENUM.INSTANCE, new SerialDescriptor[0], new EnumSerializer$descriptor$1(this, str));
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        int decodeEnum = decoder.decodeEnum(this.descriptor);
        boolean z = false;
        if (decodeEnum >= 0 && decodeEnum <= this.values.length - 1) {
            z = true;
        }
        if (z) {
            return this.values[decodeEnum];
        }
        throw new SerializationException(decodeEnum + " is not among valid " + this.descriptor.getSerialName() + " enum values, values size is " + this.values.length);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        Enum r4 = (Enum) obj;
        int indexOf = ArraysKt___ArraysKt.indexOf(this.values, r4);
        if (indexOf != -1) {
            encoder.encodeEnum(this.descriptor, indexOf);
            return;
        }
        throw new SerializationException(r4 + " is not a valid enum " + this.descriptor.getSerialName() + ", must be one of " + Arrays.toString(this.values));
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("kotlinx.serialization.internal.EnumSerializer<");
        outline13.append(this.descriptor.getSerialName());
        outline13.append('>');
        return outline13.toString();
    }
}
