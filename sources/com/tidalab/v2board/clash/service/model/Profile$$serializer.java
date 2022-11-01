package com.tidalab.v2board.clash.service.model;

import com.facebook.react.modules.appstate.AppStateModule;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.service.model.Profile;
import com.tidalab.v2board.clash.service.util.UUIDSerializer;
import java.util.UUID;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.PluginHelperInterfacesKt;
import kotlinx.serialization.internal.StringSerializer;
/* compiled from: Profile.kt */
/* loaded from: classes.dex */
public final class Profile$$serializer implements GeneratedSerializer<Profile> {
    public static final Profile$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    static {
        Profile$$serializer profile$$serializer = new Profile$$serializer();
        INSTANCE = profile$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.tidalab.v2board.clash.service.model.Profile", profile$$serializer, 9);
        pluginGeneratedSerialDescriptor.addElement("uuid", false);
        pluginGeneratedSerialDescriptor.addElement("name", false);
        pluginGeneratedSerialDescriptor.addElement("type", false);
        pluginGeneratedSerialDescriptor.addElement("source", false);
        pluginGeneratedSerialDescriptor.addElement(AppStateModule.APP_STATE_ACTIVE, false);
        pluginGeneratedSerialDescriptor.addElement("interval", false);
        pluginGeneratedSerialDescriptor.addElement("updatedAt", false);
        pluginGeneratedSerialDescriptor.addElement("imported", false);
        pluginGeneratedSerialDescriptor.addElement("pending", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
        LongSerializer longSerializer = LongSerializer.INSTANCE;
        return new KSerializer[]{new UUIDSerializer(), stringSerializer, new EnumSerializer("com.tidalab.v2board.clash.service.model.Profile.Type", Profile.Type.values()), stringSerializer, booleanSerializer, longSerializer, longSerializer, booleanSerializer, booleanSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        long j;
        boolean z;
        boolean z2;
        long j2;
        int i;
        boolean z3;
        String str;
        String str2;
        Object obj;
        Object obj2;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        int i2 = 5;
        Object obj3 = null;
        int i3 = 4;
        if (beginStructure.decodeSequentially()) {
            obj2 = beginStructure.decodeSerializableElement(serialDescriptor, 0, new UUIDSerializer(), null);
            str2 = beginStructure.decodeStringElement(serialDescriptor, 1);
            obj = beginStructure.decodeSerializableElement(serialDescriptor, 2, new EnumSerializer("com.tidalab.v2board.clash.service.model.Profile.Type", Profile.Type.values()), null);
            str = beginStructure.decodeStringElement(serialDescriptor, 3);
            z3 = beginStructure.decodeBooleanElement(serialDescriptor, 4);
            j = beginStructure.decodeLongElement(serialDescriptor, 5);
            j2 = beginStructure.decodeLongElement(serialDescriptor, 6);
            z2 = beginStructure.decodeBooleanElement(serialDescriptor, 7);
            z = beginStructure.decodeBooleanElement(serialDescriptor, 8);
            i = 511;
        } else {
            long j3 = 0;
            Object obj4 = null;
            j = 0;
            boolean z4 = false;
            int i4 = 0;
            boolean z5 = false;
            boolean z6 = false;
            boolean z7 = true;
            String str3 = null;
            String str4 = null;
            while (z7) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                switch (decodeElementIndex) {
                    case -1:
                        i3 = 4;
                        z7 = false;
                        continue;
                    case 0:
                        obj4 = beginStructure.decodeSerializableElement(serialDescriptor, 0, new UUIDSerializer(), obj4);
                        i4 |= 1;
                        i2 = 5;
                        i3 = 4;
                        break;
                    case 1:
                        str3 = beginStructure.decodeStringElement(serialDescriptor, 1);
                        i4 |= 2;
                        i2 = 5;
                        i3 = 4;
                        break;
                    case 2:
                        obj3 = beginStructure.decodeSerializableElement(serialDescriptor, 2, new EnumSerializer("com.tidalab.v2board.clash.service.model.Profile.Type", Profile.Type.values()), obj3);
                        i4 |= 4;
                        i2 = 5;
                        i3 = 4;
                        break;
                    case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                        str4 = beginStructure.decodeStringElement(serialDescriptor, 3);
                        i4 |= 8;
                        break;
                    case 4:
                        z5 = beginStructure.decodeBooleanElement(serialDescriptor, i3);
                        i4 |= 16;
                        break;
                    case 5:
                        j = beginStructure.decodeLongElement(serialDescriptor, i2);
                        i4 |= 32;
                        break;
                    case 6:
                        j3 = beginStructure.decodeLongElement(serialDescriptor, 6);
                        i4 |= 64;
                        break;
                    case 7:
                        z4 = beginStructure.decodeBooleanElement(serialDescriptor, 7);
                        i4 |= 128;
                        break;
                    case 8:
                        z6 = beginStructure.decodeBooleanElement(serialDescriptor, 8);
                        i4 |= 256;
                        break;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            obj = obj3;
            i = i4;
            str2 = str3;
            str = str4;
            z3 = z5;
            z = z6;
            z2 = z4;
            obj2 = obj4;
            j2 = j3;
        }
        beginStructure.endStructure(serialDescriptor);
        return new Profile(i, (UUID) obj2, str2, (Profile.Type) obj, str, z3, j, j2, z2, z);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        Profile profile = (Profile) obj;
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        beginStructure.encodeSerializableElement(serialDescriptor, 0, new UUIDSerializer(), profile.uuid);
        beginStructure.encodeStringElement(serialDescriptor, 1, profile.name);
        beginStructure.encodeSerializableElement(serialDescriptor, 2, new EnumSerializer("com.tidalab.v2board.clash.service.model.Profile.Type", Profile.Type.values()), profile.type);
        beginStructure.encodeStringElement(serialDescriptor, 3, profile.source);
        beginStructure.encodeBooleanElement(serialDescriptor, 4, profile.active);
        beginStructure.encodeLongElement(serialDescriptor, 5, profile.interval);
        beginStructure.encodeLongElement(serialDescriptor, 6, profile.updatedAt);
        beginStructure.encodeBooleanElement(serialDescriptor, 7, profile.imported);
        beginStructure.encodeBooleanElement(serialDescriptor, 8, profile.pending);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }
}
