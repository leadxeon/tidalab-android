package com.tidalab.v2board.clash.core.model;

import com.tidalab.v2board.clash.core.model.TunnelState;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
/* compiled from: TunnelState.kt */
/* loaded from: classes.dex */
public final class TunnelState$$serializer implements GeneratedSerializer<TunnelState> {
    public static final TunnelState$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        TunnelState$$serializer tunnelState$$serializer = new TunnelState$$serializer();
        INSTANCE = tunnelState$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.TunnelState", tunnelState$$serializer, 1);
        pluginGeneratedSerialDescriptor.addElement("mode", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{TunnelState$Mode$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        SerialDescriptor serialDescriptor = descriptor;
        Object obj = null;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        int i = 1;
        if (beginStructure.decodeSequentially()) {
            obj = beginStructure.decodeSerializableElement(serialDescriptor, 0, TunnelState$Mode$$serializer.INSTANCE, null);
        } else {
            int i2 = 0;
            while (i != 0) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    i = 0;
                } else if (decodeElementIndex == 0) {
                    obj = beginStructure.decodeSerializableElement(serialDescriptor, 0, TunnelState$Mode$$serializer.INSTANCE, obj);
                    i2 |= 1;
                } else {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
            i = i2;
        }
        beginStructure.endStructure(serialDescriptor);
        return new TunnelState(i, (TunnelState.Mode) obj);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        beginStructure.encodeSerializableElement(serialDescriptor, 0, TunnelState$Mode$$serializer.INSTANCE, ((TunnelState) obj).mode);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
