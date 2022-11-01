package com.tidalab.v2board.clash.core.model;

import com.tidalab.v2board.clash.core.model.Proxy;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.StringSerializer;
/* compiled from: ProxyGroup.kt */
/* loaded from: classes.dex */
public final class ProxyGroup$$serializer implements GeneratedSerializer<ProxyGroup> {
    public static final ProxyGroup$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        ProxyGroup$$serializer proxyGroup$$serializer = new ProxyGroup$$serializer();
        INSTANCE = proxyGroup$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.ProxyGroup", proxyGroup$$serializer, 3);
        pluginGeneratedSerialDescriptor.addElement("type", false);
        pluginGeneratedSerialDescriptor.addElement("proxies", false);
        pluginGeneratedSerialDescriptor.addElement("now", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{new EnumSerializer("com.tidalab.v2board.clash.core.model.Proxy.Type", Proxy.Type.values()), new ArrayListSerializer(Proxy$$serializer.INSTANCE), StringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        int i;
        String str;
        Object obj;
        Object obj2;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        Object obj3 = null;
        if (beginStructure.decodeSequentially()) {
            obj2 = beginStructure.decodeSerializableElement(serialDescriptor, 0, new EnumSerializer("com.tidalab.v2board.clash.core.model.Proxy.Type", Proxy.Type.values()), null);
            obj = beginStructure.decodeSerializableElement(serialDescriptor, 1, new ArrayListSerializer(Proxy$$serializer.INSTANCE), null);
            str = beginStructure.decodeStringElement(serialDescriptor, 2);
            i = 7;
        } else {
            obj2 = null;
            String str2 = null;
            int i2 = 0;
            boolean z = true;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    z = false;
                } else if (decodeElementIndex == 0) {
                    obj2 = beginStructure.decodeSerializableElement(serialDescriptor, 0, new EnumSerializer("com.tidalab.v2board.clash.core.model.Proxy.Type", Proxy.Type.values()), obj2);
                    i2 |= 1;
                } else if (decodeElementIndex == 1) {
                    obj3 = beginStructure.decodeSerializableElement(serialDescriptor, 1, new ArrayListSerializer(Proxy$$serializer.INSTANCE), obj3);
                    i2 |= 2;
                } else if (decodeElementIndex == 2) {
                    str2 = beginStructure.decodeStringElement(serialDescriptor, 2);
                    i2 |= 4;
                } else {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
            obj = obj3;
            str = str2;
            i = i2;
        }
        beginStructure.endStructure(serialDescriptor);
        return new ProxyGroup(i, (Proxy.Type) obj2, (List) obj, str);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        ProxyGroup proxyGroup = (ProxyGroup) obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        beginStructure.encodeSerializableElement(serialDescriptor, 0, new EnumSerializer("com.tidalab.v2board.clash.core.model.Proxy.Type", Proxy.Type.values()), proxyGroup.type);
        beginStructure.encodeSerializableElement(serialDescriptor, 1, new ArrayListSerializer(Proxy$$serializer.INSTANCE), proxyGroup.proxies);
        beginStructure.encodeStringElement(serialDescriptor, 2, proxyGroup.now);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
