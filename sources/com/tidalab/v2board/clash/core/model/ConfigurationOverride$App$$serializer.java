package com.tidalab.v2board.clash.core.model;

import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.NullableSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
/* compiled from: ConfigurationOverride.kt */
/* loaded from: classes.dex */
public final class ConfigurationOverride$App$$serializer implements GeneratedSerializer<ConfigurationOverride.App> {
    public static final ConfigurationOverride$App$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        ConfigurationOverride$App$$serializer configurationOverride$App$$serializer = new ConfigurationOverride$App$$serializer();
        INSTANCE = configurationOverride$App$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.ConfigurationOverride.App", configurationOverride$App$$serializer, 1);
        pluginGeneratedSerialDescriptor.addElement("append-system-dns", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{new NullableSerializer(BooleanSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        SerialDescriptor serialDescriptor = descriptor;
        Object obj = null;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        int i = 1;
        if (beginStructure.decodeSequentially()) {
            obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, BooleanSerializer.INSTANCE, null);
        } else {
            int i2 = 0;
            while (i != 0) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    i = 0;
                } else if (decodeElementIndex == 0) {
                    obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, BooleanSerializer.INSTANCE, obj);
                    i2 |= 1;
                } else {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
            i = i2;
        }
        beginStructure.endStructure(serialDescriptor);
        return new ConfigurationOverride.App(i, (Boolean) obj);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        ConfigurationOverride.App app = (ConfigurationOverride.App) obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        boolean z = true;
        if (!beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) && app.appendSystemDns == null) {
            z = false;
        }
        if (z) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, BooleanSerializer.INSTANCE, app.appendSystemDns);
        }
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
