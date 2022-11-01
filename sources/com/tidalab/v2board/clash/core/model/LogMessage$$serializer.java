package com.tidalab.v2board.clash.core.model;

import com.facebook.react.modules.dialog.DialogModule;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.core.util.DateSerializer;
import java.util.Date;
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
import kotlinx.serialization.internal.StringSerializer;
/* compiled from: LogMessage.kt */
/* loaded from: classes.dex */
public final class LogMessage$$serializer implements GeneratedSerializer<LogMessage> {
    public static final LogMessage$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        LogMessage$$serializer logMessage$$serializer = new LogMessage$$serializer();
        INSTANCE = logMessage$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.core.model.LogMessage", logMessage$$serializer, 3);
        pluginGeneratedSerialDescriptor.addElement("level", false);
        pluginGeneratedSerialDescriptor.addElement(DialogModule.KEY_MESSAGE, false);
        pluginGeneratedSerialDescriptor.addElement("time", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{LogMessage$Level$$serializer.INSTANCE, StringSerializer.INSTANCE, DateSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        String str;
        int i;
        Object obj;
        Object obj2;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        String str2 = null;
        if (beginStructure.decodeSequentially()) {
            obj2 = beginStructure.decodeSerializableElement(serialDescriptor, 0, LogMessage$Level$$serializer.INSTANCE, null);
            str = beginStructure.decodeStringElement(serialDescriptor, 1);
            obj = beginStructure.decodeSerializableElement(serialDescriptor, 2, DateSerializer.INSTANCE, null);
            i = 7;
        } else {
            obj2 = null;
            Object obj3 = null;
            int i2 = 0;
            boolean z = true;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    z = false;
                } else if (decodeElementIndex == 0) {
                    obj2 = beginStructure.decodeSerializableElement(serialDescriptor, 0, LogMessage$Level$$serializer.INSTANCE, obj2);
                    i2 |= 1;
                } else if (decodeElementIndex == 1) {
                    str2 = beginStructure.decodeStringElement(serialDescriptor, 1);
                    i2 |= 2;
                } else if (decodeElementIndex == 2) {
                    obj3 = beginStructure.decodeSerializableElement(serialDescriptor, 2, DateSerializer.INSTANCE, obj3);
                    i2 |= 4;
                } else {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
            str = str2;
            obj = obj3;
            i = i2;
        }
        beginStructure.endStructure(serialDescriptor);
        return new LogMessage(i, (LogMessage.Level) obj2, str, (Date) obj);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        beginStructure.encodeSerializableElement(serialDescriptor, 0, LogMessage$Level$$serializer.INSTANCE, logMessage.level);
        beginStructure.encodeStringElement(serialDescriptor, 1, logMessage.message);
        beginStructure.encodeSerializableElement(serialDescriptor, 2, DateSerializer.INSTANCE, logMessage.time);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
