package com.tidalab.v2board.clash.core.model;

import com.tidalab.v2board.clash.core.model.TunnelState;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.EnumDescriptor;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
/* compiled from: TunnelState.kt */
/* loaded from: classes.dex */
public final class TunnelState$Mode$$serializer implements GeneratedSerializer<TunnelState.Mode> {
    public static final TunnelState$Mode$$serializer INSTANCE = new TunnelState$Mode$$serializer();
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        EnumDescriptor enumDescriptor = new EnumDescriptor("com.tidalab.v2board.clash.core.model.TunnelState.Mode", 4);
        enumDescriptor.addElement("direct", false);
        enumDescriptor.addElement("global", false);
        enumDescriptor.addElement("rule", false);
        enumDescriptor.addElement("script", false);
        descriptor = enumDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[0];
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return TunnelState.Mode.values()[decoder.decodeEnum(descriptor)];
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        encoder.encodeEnum(descriptor, ((TunnelState.Mode) obj).ordinal());
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
