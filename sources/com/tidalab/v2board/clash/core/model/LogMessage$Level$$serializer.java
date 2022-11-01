package com.tidalab.v2board.clash.core.model;

import com.tidalab.v2board.clash.core.model.LogMessage;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.EnumDescriptor;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
/* compiled from: LogMessage.kt */
/* loaded from: classes.dex */
public final class LogMessage$Level$$serializer implements GeneratedSerializer<LogMessage.Level> {
    public static final LogMessage$Level$$serializer INSTANCE = new LogMessage$Level$$serializer();
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        EnumDescriptor enumDescriptor = new EnumDescriptor("com.tidalab.v2board.clash.core.model.LogMessage.Level", 6);
        enumDescriptor.addElement("debug", false);
        enumDescriptor.addElement("info", false);
        enumDescriptor.addElement("warning", false);
        enumDescriptor.addElement("error", false);
        enumDescriptor.addElement("silent", false);
        enumDescriptor.addElement("unknown", false);
        descriptor = enumDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[0];
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return LogMessage.Level.values()[decoder.decodeEnum(descriptor)];
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        encoder.encodeEnum(descriptor, ((LogMessage.Level) obj).ordinal());
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
