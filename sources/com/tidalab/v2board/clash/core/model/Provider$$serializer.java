package com.tidalab.v2board.clash.core.model;

import com.tidalab.v2board.clash.core.model.Provider;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.StringSerializer;
/* compiled from: Provider.kt */
/* loaded from: classes.dex */
public final class Provider$$serializer implements GeneratedSerializer<Provider> {
    public static final Provider$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        Provider$$serializer provider$$serializer = new Provider$$serializer();
        INSTANCE = provider$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.Provider", provider$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("name", false);
        pluginGeneratedSerialDescriptor.addElement("type", false);
        pluginGeneratedSerialDescriptor.addElement("vehicleType", false);
        pluginGeneratedSerialDescriptor.addElement("updatedAt", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, new EnumSerializer("com.tidalab.v2board.clash.core.model.Provider.Type", Provider.Type.values()), new EnumSerializer("com.tidalab.v2board.clash.core.model.Provider.VehicleType", Provider.VehicleType.values()), LongSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        long j;
        String str;
        int i;
        Object obj;
        Object obj2;
        SerialDescriptor serialDescriptor = descriptor;
        long j2 = 0;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        String str2 = null;
        if (beginStructure.decodeSequentially()) {
            str = beginStructure.decodeStringElement(serialDescriptor, 0);
            obj2 = beginStructure.decodeSerializableElement(serialDescriptor, 1, new EnumSerializer("com.tidalab.v2board.clash.core.model.Provider.Type", Provider.Type.values()), null);
            obj = beginStructure.decodeSerializableElement(serialDescriptor, 2, new EnumSerializer("com.tidalab.v2board.clash.core.model.Provider.VehicleType", Provider.VehicleType.values()), null);
            j = beginStructure.decodeLongElement(serialDescriptor, 3);
            i = 15;
        } else {
            Object obj3 = null;
            Object obj4 = null;
            int i2 = 0;
            boolean z = true;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    z = false;
                } else if (decodeElementIndex == 0) {
                    str2 = beginStructure.decodeStringElement(serialDescriptor, 0);
                    i2 |= 1;
                } else if (decodeElementIndex == 1) {
                    obj3 = beginStructure.decodeSerializableElement(serialDescriptor, 1, new EnumSerializer("com.tidalab.v2board.clash.core.model.Provider.Type", Provider.Type.values()), obj3);
                    i2 |= 2;
                } else if (decodeElementIndex == 2) {
                    obj4 = beginStructure.decodeSerializableElement(serialDescriptor, 2, new EnumSerializer("com.tidalab.v2board.clash.core.model.Provider.VehicleType", Provider.VehicleType.values()), obj4);
                    i2 |= 4;
                } else if (decodeElementIndex == 3) {
                    j2 = beginStructure.decodeLongElement(serialDescriptor, 3);
                    i2 |= 8;
                } else {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
            j = j2;
            i = i2;
            str = str2;
            obj2 = obj3;
            obj = obj4;
        }
        beginStructure.endStructure(serialDescriptor);
        return new Provider(i, str, (Provider.Type) obj2, (Provider.VehicleType) obj, j);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        Provider provider = (Provider) obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        beginStructure.encodeStringElement(serialDescriptor, 0, provider.name);
        beginStructure.encodeSerializableElement(serialDescriptor, 1, new EnumSerializer("com.tidalab.v2board.clash.core.model.Provider.Type", Provider.Type.values()), provider.type);
        beginStructure.encodeSerializableElement(serialDescriptor, 2, new EnumSerializer("com.tidalab.v2board.clash.core.model.Provider.VehicleType", Provider.VehicleType.values()), provider.vehicleType);
        beginStructure.encodeLongElement(serialDescriptor, 3, provider.updatedAt);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
