package com.tidalab.v2board.clash.core.model;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
/* compiled from: UiConfiguration.kt */
/* loaded from: classes.dex */
public final class UiConfiguration$$serializer implements GeneratedSerializer<UiConfiguration> {
    public static final UiConfiguration$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        UiConfiguration$$serializer uiConfiguration$$serializer = new UiConfiguration$$serializer();
        INSTANCE = uiConfiguration$$serializer;
        descriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.UiConfiguration", uiConfiguration$$serializer, 0);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[0];
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        if (!beginStructure.decodeSequentially()) {
            for (boolean z = true; z; z = false) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex != -1) {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
        }
        beginStructure.endStructure(serialDescriptor);
        return new UiConfiguration(0);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        UiConfiguration uiConfiguration = (UiConfiguration) obj;
        SerialDescriptor serialDescriptor = descriptor;
        encoder.beginStructure(serialDescriptor).endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
