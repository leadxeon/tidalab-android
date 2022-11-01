package com.tidalab.v2board.clash.core.model;

import com.tidalab.v2board.clash.core.model.ConfigurationOverride;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.EnumDescriptor;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
/* compiled from: ConfigurationOverride.kt */
/* loaded from: classes.dex */
public final class ConfigurationOverride$DnsEnhancedMode$$serializer implements GeneratedSerializer<ConfigurationOverride.DnsEnhancedMode> {
    public static final ConfigurationOverride$DnsEnhancedMode$$serializer INSTANCE = new ConfigurationOverride$DnsEnhancedMode$$serializer();
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        EnumDescriptor enumDescriptor = new EnumDescriptor("com.tidalab.v2board.clash.core.model.ConfigurationOverride.DnsEnhancedMode", 3);
        enumDescriptor.addElement("normal", false);
        enumDescriptor.addElement("redir-host", false);
        enumDescriptor.addElement("fake-ip", false);
        descriptor = enumDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[0];
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return ConfigurationOverride.DnsEnhancedMode.values()[decoder.decodeEnum(descriptor)];
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        encoder.encodeEnum(descriptor, ((ConfigurationOverride.DnsEnhancedMode) obj).ordinal());
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
