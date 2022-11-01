package com.tidalab.v2board.clash.core.model;

import com.facebook.react.modules.dialog.DialogModule;
import com.tidalab.v2board.clash.core.model.Proxy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.StringSerializer;
/* compiled from: Proxy.kt */
/* loaded from: classes.dex */
public final class Proxy$$serializer implements GeneratedSerializer<Proxy> {
    public static final Proxy$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        Proxy$$serializer proxy$$serializer = new Proxy$$serializer();
        INSTANCE = proxy$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.Proxy", proxy$$serializer, 5);
        pluginGeneratedSerialDescriptor.addElement("name", false);
        pluginGeneratedSerialDescriptor.addElement(DialogModule.KEY_TITLE, false);
        pluginGeneratedSerialDescriptor.addElement("subtitle", false);
        pluginGeneratedSerialDescriptor.addElement("type", false);
        pluginGeneratedSerialDescriptor.addElement("delay", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{stringSerializer, stringSerializer, stringSerializer, new EnumSerializer("com.tidalab.v2board.clash.core.model.Proxy.Type", Proxy.Type.values()), IntSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        int i;
        String str;
        String str2;
        String str3;
        int i2;
        Object obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        if (beginStructure.decodeSequentially()) {
            str3 = beginStructure.decodeStringElement(serialDescriptor, 0);
            str2 = beginStructure.decodeStringElement(serialDescriptor, 1);
            str = beginStructure.decodeStringElement(serialDescriptor, 2);
            obj = beginStructure.decodeSerializableElement(serialDescriptor, 3, new EnumSerializer("com.tidalab.v2board.clash.core.model.Proxy.Type", Proxy.Type.values()), null);
            i = beginStructure.decodeIntElement(serialDescriptor, 4);
            i2 = 31;
        } else {
            String str4 = null;
            String str5 = null;
            String str6 = null;
            Object obj2 = null;
            int i3 = 0;
            int i4 = 0;
            boolean z = true;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    z = false;
                } else if (decodeElementIndex == 0) {
                    str4 = beginStructure.decodeStringElement(serialDescriptor, 0);
                    i4 |= 1;
                } else if (decodeElementIndex == 1) {
                    str5 = beginStructure.decodeStringElement(serialDescriptor, 1);
                    i4 |= 2;
                } else if (decodeElementIndex == 2) {
                    str6 = beginStructure.decodeStringElement(serialDescriptor, 2);
                    i4 |= 4;
                } else if (decodeElementIndex == 3) {
                    obj2 = beginStructure.decodeSerializableElement(serialDescriptor, 3, new EnumSerializer("com.tidalab.v2board.clash.core.model.Proxy.Type", Proxy.Type.values()), obj2);
                    i4 |= 8;
                } else if (decodeElementIndex == 4) {
                    i3 = beginStructure.decodeIntElement(serialDescriptor, 4);
                    i4 |= 16;
                } else {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
            i = i3;
            i2 = i4;
            str3 = str4;
            str2 = str5;
            str = str6;
            obj = obj2;
        }
        beginStructure.endStructure(serialDescriptor);
        return new Proxy(i2, str3, str2, str, (Proxy.Type) obj, i);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        Proxy proxy = (Proxy) obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        beginStructure.encodeStringElement(serialDescriptor, 0, proxy.name);
        beginStructure.encodeStringElement(serialDescriptor, 1, proxy.title);
        beginStructure.encodeStringElement(serialDescriptor, 2, proxy.subtitle);
        beginStructure.encodeSerializableElement(serialDescriptor, 3, new EnumSerializer("com.tidalab.v2board.clash.core.model.Proxy.Type", Proxy.Type.values()), proxy.type);
        beginStructure.encodeIntElement(serialDescriptor, 4, proxy.delay);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
