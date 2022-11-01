package com.tidalab.v2board.clash.core.model;

import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.core.model.TunnelState;
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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.NullableSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.StringSerializer;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: ConfigurationOverride.kt */
/* loaded from: classes.dex */
public final class ConfigurationOverride$$serializer implements GeneratedSerializer<ConfigurationOverride> {
    public static final ConfigurationOverride$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        ConfigurationOverride$$serializer configurationOverride$$serializer = new ConfigurationOverride$$serializer();
        INSTANCE = configurationOverride$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.ConfigurationOverride", configurationOverride$$serializer, 14);
        pluginGeneratedSerialDescriptor.addElement("port", true);
        pluginGeneratedSerialDescriptor.addElement("socks-port", true);
        pluginGeneratedSerialDescriptor.addElement("redir-port", true);
        pluginGeneratedSerialDescriptor.addElement("tproxy-port", true);
        pluginGeneratedSerialDescriptor.addElement("mixed-port", true);
        pluginGeneratedSerialDescriptor.addElement("authentication", true);
        pluginGeneratedSerialDescriptor.addElement("allow-lan", true);
        pluginGeneratedSerialDescriptor.addElement("bind-address", true);
        pluginGeneratedSerialDescriptor.addElement("mode", true);
        pluginGeneratedSerialDescriptor.addElement("log-level", true);
        pluginGeneratedSerialDescriptor.addElement("ipv6", true);
        pluginGeneratedSerialDescriptor.addElement("hosts", true);
        pluginGeneratedSerialDescriptor.addElement("dns", true);
        pluginGeneratedSerialDescriptor.addElement("clash-for-android", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        IntSerializer intSerializer = IntSerializer.INSTANCE;
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
        return new KSerializer[]{new NullableSerializer(intSerializer), new NullableSerializer(intSerializer), new NullableSerializer(intSerializer), new NullableSerializer(intSerializer), new NullableSerializer(intSerializer), new NullableSerializer(new ArrayListSerializer(stringSerializer)), new NullableSerializer(booleanSerializer), new NullableSerializer(stringSerializer), new NullableSerializer(TunnelState$Mode$$serializer.INSTANCE), new NullableSerializer(LogMessage$Level$$serializer.INSTANCE), new NullableSerializer(booleanSerializer), new NullableSerializer(new LinkedHashMapSerializer(stringSerializer, stringSerializer)), ConfigurationOverride$Dns$$serializer.INSTANCE, ConfigurationOverride$App$$serializer.INSTANCE};
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
        Object obj12;
        Object obj13;
        Object obj14;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        Object obj15 = null;
        if (beginStructure.decodeSequentially()) {
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            obj11 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, intSerializer, null);
            obj12 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, intSerializer, null);
            obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, intSerializer, null);
            obj8 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 3, intSerializer, null);
            obj14 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 4, intSerializer, null);
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            obj7 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 5, new ArrayListSerializer(stringSerializer), null);
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            obj6 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 6, booleanSerializer, null);
            obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 7, stringSerializer, null);
            obj9 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 8, TunnelState$Mode$$serializer.INSTANCE, null);
            obj4 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 9, LogMessage$Level$$serializer.INSTANCE, null);
            obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 10, booleanSerializer, null);
            obj10 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 11, new LinkedHashMapSerializer(stringSerializer, stringSerializer), null);
            obj5 = beginStructure.decodeSerializableElement(serialDescriptor, 12, ConfigurationOverride$Dns$$serializer.INSTANCE, null);
            obj13 = beginStructure.decodeSerializableElement(serialDescriptor, 13, ConfigurationOverride$App$$serializer.INSTANCE, null);
            i = 16383;
        } else {
            Object obj16 = null;
            Object obj17 = null;
            Object obj18 = null;
            Object obj19 = null;
            Object obj20 = null;
            obj7 = null;
            Object obj21 = null;
            obj5 = null;
            Object obj22 = null;
            obj3 = null;
            Object obj23 = null;
            Object obj24 = null;
            Object obj25 = null;
            int i2 = 0;
            boolean z = true;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                switch (decodeElementIndex) {
                    case -1:
                        obj16 = obj16;
                        obj15 = obj15;
                        obj17 = obj17;
                        obj19 = obj19;
                        obj24 = obj24;
                        obj25 = obj25;
                        z = false;
                        break;
                    case 0:
                        obj15 = obj15;
                        obj17 = obj17;
                        obj24 = obj24;
                        obj25 = obj25;
                        obj16 = obj16;
                        obj19 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, IntSerializer.INSTANCE, obj19);
                        i2 |= 1;
                        break;
                    case 1:
                        obj15 = obj15;
                        obj17 = obj17;
                        obj19 = obj19;
                        obj16 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, obj16);
                        i2 |= 2;
                    case 2:
                        obj17 = obj17;
                        obj19 = obj19;
                        obj15 = obj15;
                        obj24 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, IntSerializer.INSTANCE, obj24);
                        i2 |= 4;
                    case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                        obj17 = obj17;
                        obj19 = obj19;
                        obj25 = obj25;
                        obj15 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, obj15);
                        i2 |= 8;
                    case 4:
                        obj17 = obj17;
                        obj19 = obj19;
                        obj25 = obj25;
                        obj18 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 4, IntSerializer.INSTANCE, obj18);
                        i2 |= 16;
                    case 5:
                        obj17 = obj17;
                        obj19 = obj19;
                        obj25 = obj25;
                        obj7 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 5, new ArrayListSerializer(StringSerializer.INSTANCE), obj7);
                        i2 |= 32;
                    case 6:
                        obj19 = obj19;
                        obj17 = obj17;
                        obj25 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 6, BooleanSerializer.INSTANCE, obj25);
                        i2 |= 64;
                    case 7:
                        obj19 = obj19;
                        obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, obj3);
                        i2 |= 128;
                        obj17 = obj17;
                        obj25 = obj25;
                    case 8:
                        obj19 = obj19;
                        obj21 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 8, TunnelState$Mode$$serializer.INSTANCE, obj21);
                        i2 |= 256;
                        obj17 = obj17;
                        obj25 = obj25;
                    case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                        obj19 = obj19;
                        obj23 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 9, LogMessage$Level$$serializer.INSTANCE, obj23);
                        i2 |= 512;
                        obj17 = obj17;
                        obj25 = obj25;
                    case 10:
                        obj19 = obj19;
                        obj20 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 10, BooleanSerializer.INSTANCE, obj20);
                        i2 |= 1024;
                        obj17 = obj17;
                        obj25 = obj25;
                    case 11:
                        obj19 = obj19;
                        StringSerializer stringSerializer2 = StringSerializer.INSTANCE;
                        obj17 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 11, new LinkedHashMapSerializer(stringSerializer2, stringSerializer2), obj17);
                        i2 |= 2048;
                        obj25 = obj25;
                    case 12:
                        obj19 = obj19;
                        obj5 = beginStructure.decodeSerializableElement(serialDescriptor, 12, ConfigurationOverride$Dns$$serializer.INSTANCE, obj5);
                        i2 |= 4096;
                        obj17 = obj17;
                        obj25 = obj25;
                    case 13:
                        obj19 = obj19;
                        obj22 = beginStructure.decodeSerializableElement(serialDescriptor, 13, ConfigurationOverride$App$$serializer.INSTANCE, obj22);
                        i2 |= 8192;
                        obj17 = obj17;
                        obj25 = obj25;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            obj11 = obj19;
            obj14 = obj18;
            obj = obj20;
            obj13 = obj22;
            obj8 = obj15;
            obj10 = obj17;
            i = i2;
            obj4 = obj23;
            obj2 = obj24;
            obj12 = obj16;
            obj6 = obj25;
            obj9 = obj21;
        }
        beginStructure.endStructure(serialDescriptor);
        return new ConfigurationOverride(i, (Integer) obj11, (Integer) obj12, (Integer) obj2, (Integer) obj8, (Integer) obj14, (List) obj7, (Boolean) obj6, (String) obj3, (TunnelState.Mode) obj9, (LogMessage.Level) obj4, (Boolean) obj, (Map) obj10, (ConfigurationOverride.Dns) obj5, (ConfigurationOverride.App) obj13);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        ConfigurationOverride configurationOverride = (ConfigurationOverride) obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        boolean z = true;
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || configurationOverride.httpPort != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, IntSerializer.INSTANCE, configurationOverride.httpPort);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || configurationOverride.socksPort != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, configurationOverride.socksPort);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || configurationOverride.redirectPort != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, IntSerializer.INSTANCE, configurationOverride.redirectPort);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || configurationOverride.tproxyPort != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, configurationOverride.tproxyPort);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || configurationOverride.mixedPort != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, IntSerializer.INSTANCE, configurationOverride.mixedPort);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || configurationOverride.authentication != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 5, new ArrayListSerializer(StringSerializer.INSTANCE), configurationOverride.authentication);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || configurationOverride.allowLan != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 6, BooleanSerializer.INSTANCE, configurationOverride.allowLan);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || configurationOverride.bindAddress != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, configurationOverride.bindAddress);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || configurationOverride.mode != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 8, TunnelState$Mode$$serializer.INSTANCE, configurationOverride.mode);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || configurationOverride.logLevel != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 9, LogMessage$Level$$serializer.INSTANCE, configurationOverride.logLevel);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || configurationOverride.ipv6 != null) {
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 10, BooleanSerializer.INSTANCE, configurationOverride.ipv6);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 11) || configurationOverride.hosts != null) {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 11, new LinkedHashMapSerializer(stringSerializer, stringSerializer), configurationOverride.hosts);
        }
        if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 12) || !Intrinsics.areEqual(configurationOverride.dns, new ConfigurationOverride.Dns((Boolean) null, (String) null, (Boolean) null, (Boolean) null, (ConfigurationOverride.DnsEnhancedMode) null, (List) null, (List) null, (List) null, (List) null, (ConfigurationOverride.DnsFallbackFilter) null, (Map) null, 2047))) {
            beginStructure.encodeSerializableElement(serialDescriptor, 12, ConfigurationOverride$Dns$$serializer.INSTANCE, configurationOverride.dns);
        }
        if (!beginStructure.shouldEncodeElementDefault(serialDescriptor, 13) && Intrinsics.areEqual(configurationOverride.app, new ConfigurationOverride.App((Boolean) null, 1))) {
            z = false;
        }
        if (z) {
            beginStructure.encodeSerializableElement(serialDescriptor, 13, ConfigurationOverride$App$$serializer.INSTANCE, configurationOverride.app);
        }
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
