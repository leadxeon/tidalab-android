package com.tidalab.v2board.clash.core.model;

import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.tidalab.v2board.clash.core.model.FetchStatus;
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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.StringSerializer;
/* compiled from: FetchStatus.kt */
/* loaded from: classes.dex */
public final class FetchStatus$$serializer implements GeneratedSerializer<FetchStatus> {
    public static final FetchStatus$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        FetchStatus$$serializer fetchStatus$$serializer = new FetchStatus$$serializer();
        INSTANCE = fetchStatus$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.FetchStatus", fetchStatus$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("action", false);
        pluginGeneratedSerialDescriptor.addElement("args", false);
        pluginGeneratedSerialDescriptor.addElement(ReactProgressBarViewManager.PROP_PROGRESS, false);
        pluginGeneratedSerialDescriptor.addElement("max", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        IntSerializer intSerializer = IntSerializer.INSTANCE;
        return new KSerializer[]{new EnumSerializer("com.tidalab.v2board.clash.core.model.FetchStatus.Action", FetchStatus.Action.values()), new ArrayListSerializer(StringSerializer.INSTANCE), intSerializer, intSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        int i;
        int i2;
        int i3;
        Object obj;
        Object obj2;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        Object obj3 = null;
        if (beginStructure.decodeSequentially()) {
            obj2 = beginStructure.decodeSerializableElement(serialDescriptor, 0, new EnumSerializer("com.tidalab.v2board.clash.core.model.FetchStatus.Action", FetchStatus.Action.values()), null);
            obj = beginStructure.decodeSerializableElement(serialDescriptor, 1, new ArrayListSerializer(StringSerializer.INSTANCE), null);
            i2 = beginStructure.decodeIntElement(serialDescriptor, 2);
            i = beginStructure.decodeIntElement(serialDescriptor, 3);
            i3 = 15;
        } else {
            obj2 = null;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            boolean z = true;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    z = false;
                } else if (decodeElementIndex == 0) {
                    obj2 = beginStructure.decodeSerializableElement(serialDescriptor, 0, new EnumSerializer("com.tidalab.v2board.clash.core.model.FetchStatus.Action", FetchStatus.Action.values()), obj2);
                    i6 |= 1;
                } else if (decodeElementIndex == 1) {
                    obj3 = beginStructure.decodeSerializableElement(serialDescriptor, 1, new ArrayListSerializer(StringSerializer.INSTANCE), obj3);
                    i6 |= 2;
                } else if (decodeElementIndex == 2) {
                    i4 = beginStructure.decodeIntElement(serialDescriptor, 2);
                    i6 |= 4;
                } else if (decodeElementIndex == 3) {
                    i5 = beginStructure.decodeIntElement(serialDescriptor, 3);
                    i6 |= 8;
                } else {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
            obj = obj3;
            i2 = i4;
            i = i5;
            i3 = i6;
        }
        beginStructure.endStructure(serialDescriptor);
        return new FetchStatus(i3, (FetchStatus.Action) obj2, (List) obj, i2, i);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        FetchStatus fetchStatus = (FetchStatus) obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        beginStructure.encodeSerializableElement(serialDescriptor, 0, new EnumSerializer("com.tidalab.v2board.clash.core.model.FetchStatus.Action", FetchStatus.Action.values()), fetchStatus.action);
        beginStructure.encodeSerializableElement(serialDescriptor, 1, new ArrayListSerializer(StringSerializer.INSTANCE), fetchStatus.args);
        beginStructure.encodeIntElement(serialDescriptor, 2, fetchStatus.progress);
        beginStructure.encodeIntElement(serialDescriptor, 3, fetchStatus.max);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
