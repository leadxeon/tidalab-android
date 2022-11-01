package com.tidalab.v2board.clash.core.model;

import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.NullableSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.StringSerializer;
/* compiled from: ConfigurationOverride.kt */
/* loaded from: classes.dex */
public final class ConfigurationOverride$DnsFallbackFilter$$serializer implements GeneratedSerializer<ConfigurationOverride.DnsFallbackFilter> {
    public static final ConfigurationOverride$DnsFallbackFilter$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        ConfigurationOverride$DnsFallbackFilter$$serializer configurationOverride$DnsFallbackFilter$$serializer = new ConfigurationOverride$DnsFallbackFilter$$serializer();
        INSTANCE = configurationOverride$DnsFallbackFilter$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.ConfigurationOverride.DnsFallbackFilter", configurationOverride$DnsFallbackFilter$$serializer, 3);
        pluginGeneratedSerialDescriptor.addElement("geoip", true);
        pluginGeneratedSerialDescriptor.addElement("ipcidr", true);
        pluginGeneratedSerialDescriptor.addElement("domain", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{new NullableSerializer(BooleanSerializer.INSTANCE), new NullableSerializer(new ArrayListSerializer(stringSerializer)), new NullableSerializer(new ArrayListSerializer(stringSerializer))};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        Object obj;
        int i;
        Object obj2;
        Object obj3;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        Object obj4 = null;
        if (beginStructure.decodeSequentially()) {
            obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, BooleanSerializer.INSTANCE, null);
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, new ArrayListSerializer(stringSerializer), null);
            obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, new ArrayListSerializer(stringSerializer), null);
            i = 7;
        } else {
            obj3 = null;
            Object obj5 = null;
            int i2 = 0;
            boolean z = true;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    z = false;
                } else if (decodeElementIndex == 0) {
                    obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, BooleanSerializer.INSTANCE, obj3);
                    i2 |= 1;
                } else if (decodeElementIndex == 1) {
                    obj4 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, new ArrayListSerializer(StringSerializer.INSTANCE), obj4);
                    i2 |= 2;
                } else if (decodeElementIndex == 2) {
                    obj5 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, new ArrayListSerializer(StringSerializer.INSTANCE), obj5);
                    i2 |= 4;
                } else {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
            obj = obj4;
            obj2 = obj5;
            i = i2;
        }
        beginStructure.endStructure(serialDescriptor);
        return new ConfigurationOverride.DnsFallbackFilter(i, (Boolean) obj3, (List) obj, (List) obj2);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        ConfigurationOverride.DnsFallbackFilter dnsFallbackFilter = (ConfigurationOverride.DnsFallbackFilter) obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        boolean z = false;
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || dnsFallbackFilter.geoIp != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, BooleanSerializer.INSTANCE, dnsFallbackFilter.geoIp);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || dnsFallbackFilter.ipcidr != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, new ArrayListSerializer(StringSerializer.INSTANCE), dnsFallbackFilter.ipcidr);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || dnsFallbackFilter.domain != null) {
            z = true;
        }
        if (z) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, new ArrayListSerializer(StringSerializer.INSTANCE), dnsFallbackFilter.domain);
        }
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
