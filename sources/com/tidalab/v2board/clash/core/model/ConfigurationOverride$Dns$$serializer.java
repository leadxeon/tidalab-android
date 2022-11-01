package com.tidalab.v2board.clash.core.model;

import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
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
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.NullableSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.StringSerializer;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: ConfigurationOverride.kt */
/* loaded from: classes.dex */
public final class ConfigurationOverride$Dns$$serializer implements GeneratedSerializer<ConfigurationOverride.Dns> {
    public static final ConfigurationOverride$Dns$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        ConfigurationOverride$Dns$$serializer configurationOverride$Dns$$serializer = new ConfigurationOverride$Dns$$serializer();
        INSTANCE = configurationOverride$Dns$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.ConfigurationOverride.Dns", configurationOverride$Dns$$serializer, 11);
        pluginGeneratedSerialDescriptor.addElement("enable", true);
        pluginGeneratedSerialDescriptor.addElement("listen", true);
        pluginGeneratedSerialDescriptor.addElement("ipv6", true);
        pluginGeneratedSerialDescriptor.addElement("use-hosts", true);
        pluginGeneratedSerialDescriptor.addElement("enhanced-mode", true);
        pluginGeneratedSerialDescriptor.addElement("nameserver", true);
        pluginGeneratedSerialDescriptor.addElement("fallback", true);
        pluginGeneratedSerialDescriptor.addElement("default-nameserver", true);
        pluginGeneratedSerialDescriptor.addElement("fake-ip-filter", true);
        pluginGeneratedSerialDescriptor.addElement("fallback-filter", true);
        pluginGeneratedSerialDescriptor.addElement("nameserver-policy", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{new NullableSerializer(booleanSerializer), new NullableSerializer(stringSerializer), new NullableSerializer(booleanSerializer), new NullableSerializer(booleanSerializer), new NullableSerializer(ConfigurationOverride$DnsEnhancedMode$$serializer.INSTANCE), new NullableSerializer(new ArrayListSerializer(stringSerializer)), new NullableSerializer(new ArrayListSerializer(stringSerializer)), new NullableSerializer(new ArrayListSerializer(stringSerializer)), new NullableSerializer(new ArrayListSerializer(stringSerializer)), ConfigurationOverride$DnsFallbackFilter$$serializer.INSTANCE, new NullableSerializer(new LinkedHashMapSerializer(stringSerializer, stringSerializer))};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        Object obj;
        Object obj2;
        Object obj3;
        int i;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        Object obj12 = null;
        int i2 = 9;
        int i3 = 10;
        if (beginStructure.decodeSequentially()) {
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, booleanSerializer, null);
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, stringSerializer, null);
            obj4 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, booleanSerializer, null);
            obj11 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 3, booleanSerializer, null);
            obj9 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 4, ConfigurationOverride$DnsEnhancedMode$$serializer.INSTANCE, null);
            obj8 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 5, new ArrayListSerializer(stringSerializer), null);
            obj7 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 6, new ArrayListSerializer(stringSerializer), null);
            obj6 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 7, new ArrayListSerializer(stringSerializer), null);
            obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 8, new ArrayListSerializer(stringSerializer), null);
            obj5 = beginStructure.decodeSerializableElement(serialDescriptor, 9, ConfigurationOverride$DnsFallbackFilter$$serializer.INSTANCE, null);
            obj10 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 10, new LinkedHashMapSerializer(stringSerializer, stringSerializer), null);
            i = 2047;
        } else {
            Object obj13 = null;
            Object obj14 = null;
            Object obj15 = null;
            Object obj16 = null;
            Object obj17 = null;
            Object obj18 = null;
            Object obj19 = null;
            Object obj20 = null;
            Object obj21 = null;
            Object obj22 = null;
            int i4 = 0;
            boolean z = true;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                switch (decodeElementIndex) {
                    case -1:
                        i2 = 9;
                        i3 = 10;
                        z = false;
                        continue;
                    case 0:
                        obj21 = obj21;
                        obj22 = obj22;
                        obj12 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, BooleanSerializer.INSTANCE, obj12);
                        i4 |= 1;
                        break;
                    case 1:
                        obj22 = obj22;
                        obj21 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, obj21);
                        i4 |= 2;
                        break;
                    case 2:
                        obj22 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, BooleanSerializer.INSTANCE, obj22);
                        i4 |= 4;
                        break;
                    case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                        obj16 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 3, BooleanSerializer.INSTANCE, obj16);
                        i4 |= 8;
                        break;
                    case 4:
                        obj18 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 4, ConfigurationOverride$DnsEnhancedMode$$serializer.INSTANCE, obj18);
                        i4 |= 16;
                        break;
                    case 5:
                        obj19 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 5, new ArrayListSerializer(StringSerializer.INSTANCE), obj19);
                        i4 |= 32;
                        break;
                    case 6:
                        obj14 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 6, new ArrayListSerializer(StringSerializer.INSTANCE), obj14);
                        i4 |= 64;
                        break;
                    case 7:
                        obj20 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 7, new ArrayListSerializer(StringSerializer.INSTANCE), obj20);
                        i4 |= 128;
                        break;
                    case 8:
                        obj15 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 8, new ArrayListSerializer(StringSerializer.INSTANCE), obj15);
                        i4 |= 256;
                        break;
                    case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                        obj13 = beginStructure.decodeSerializableElement(serialDescriptor, i2, ConfigurationOverride$DnsFallbackFilter$$serializer.INSTANCE, obj13);
                        i4 |= 512;
                        obj21 = obj21;
                        obj22 = obj22;
                        break;
                    case 10:
                        StringSerializer stringSerializer2 = StringSerializer.INSTANCE;
                        obj17 = beginStructure.decodeNullableSerializableElement(serialDescriptor, i3, new LinkedHashMapSerializer(stringSerializer2, stringSerializer2), obj17);
                        i4 |= 1024;
                        obj21 = obj21;
                        obj22 = obj22;
                        break;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
                i2 = 9;
                i3 = 10;
            }
            obj6 = obj20;
            obj5 = obj13;
            obj11 = obj16;
            i = i4;
            obj7 = obj14;
            obj9 = obj18;
            obj2 = obj21;
            obj4 = obj22;
            obj = obj12;
            obj10 = obj17;
            obj3 = obj15;
            obj8 = obj19;
        }
        beginStructure.endStructure(serialDescriptor);
        return new ConfigurationOverride.Dns(i, (Boolean) obj, (String) obj2, (Boolean) obj4, (Boolean) obj11, (ConfigurationOverride.DnsEnhancedMode) obj9, (List) obj8, (List) obj7, (List) obj6, (List) obj3, (ConfigurationOverride.DnsFallbackFilter) obj5, (Map) obj10);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        ConfigurationOverride.Dns dns = (ConfigurationOverride.Dns) obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        boolean z = false;
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || dns.enable != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, BooleanSerializer.INSTANCE, dns.enable);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || dns.listen != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, dns.listen);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || dns.ipv6 != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, BooleanSerializer.INSTANCE, dns.ipv6);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || dns.useHosts != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, BooleanSerializer.INSTANCE, dns.useHosts);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || dns.enhancedMode != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, ConfigurationOverride$DnsEnhancedMode$$serializer.INSTANCE, dns.enhancedMode);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || dns.nameServer != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 5, new ArrayListSerializer(StringSerializer.INSTANCE), dns.nameServer);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || dns.fallback != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 6, new ArrayListSerializer(StringSerializer.INSTANCE), dns.fallback);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || dns.defaultServer != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 7, new ArrayListSerializer(StringSerializer.INSTANCE), dns.defaultServer);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || dns.fakeIpFilter != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 8, new ArrayListSerializer(StringSerializer.INSTANCE), dns.fakeIpFilter);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || !Intrinsics.areEqual(dns.fallbackFilter, new ConfigurationOverride.DnsFallbackFilter((Boolean) null, (List) null, (List) null, 7))) {
            beginStructure.encodeSerializableElement(serialDescriptor, 9, ConfigurationOverride$DnsFallbackFilter$$serializer.INSTANCE, dns.fallbackFilter);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || dns.nameserverPolicy != null) {
            z = true;
        }
        if (z) {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 10, new LinkedHashMapSerializer(stringSerializer, stringSerializer), dns.nameserverPolicy);
        }
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
