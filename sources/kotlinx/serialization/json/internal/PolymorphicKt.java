package kotlinx.serialization.json.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
/* compiled from: Polymorphic.kt */
/* loaded from: classes.dex */
public final class PolymorphicKt {
    public static final <T> T decodeSerializableValuePolymorphic(JsonDecoder jsonDecoder, DeserializationStrategy<T> deserializationStrategy) {
        String str;
        if (!(deserializationStrategy instanceof AbstractPolymorphicSerializer) || jsonDecoder.getJson().configuration.useArrayPolymorphism) {
            return deserializationStrategy.deserialize(jsonDecoder);
        }
        JsonElement decodeJsonElement = jsonDecoder.decodeJsonElement();
        SerialDescriptor descriptor = deserializationStrategy.getDescriptor();
        if (decodeJsonElement instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) decodeJsonElement;
            String str2 = jsonDecoder.getJson().configuration.classDiscriminator;
            JsonElement jsonElement = (JsonElement) jsonObject.get(str2);
            String content = jsonElement == null ? null : InputKt.getJsonPrimitive(jsonElement).getContent();
            DeserializationStrategy<? extends T> polymorphic = jsonDecoder.getSerializersModule().getPolymorphic((KClass) ((AbstractPolymorphicSerializer) deserializationStrategy).getBaseClass(), content);
            if (polymorphic != null) {
                return (T) decodeSerializableValuePolymorphic(new JsonTreeDecoder(jsonDecoder.getJson(), jsonObject, str2, polymorphic.getDescriptor()), polymorphic);
            }
            if (content == null) {
                str = "missing class discriminator ('null')";
            } else {
                str = "class discriminator '" + ((Object) content) + '\'';
            }
            throw InputKt.JsonDecodingException(-1, Intrinsics.stringPlus("Polymorphic serializer was not found for ", str), jsonObject.toString());
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Expected ");
        outline13.append(Reflection.getOrCreateKotlinClass(JsonObject.class));
        outline13.append(" as the serialized body of ");
        outline13.append(descriptor.getSerialName());
        outline13.append(", but had ");
        outline13.append(Reflection.getOrCreateKotlinClass(decodeJsonElement.getClass()));
        throw InputKt.JsonDecodingException(-1, outline13.toString());
    }

    public static final WriteMode switchMode(Json json, SerialDescriptor serialDescriptor) {
        WriteMode writeMode = WriteMode.LIST;
        SerialKind kind = serialDescriptor.getKind();
        if (kind instanceof PolymorphicKind) {
            return WriteMode.POLY_OBJ;
        }
        if (Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE)) {
            return writeMode;
        }
        if (!Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE)) {
            return WriteMode.OBJ;
        }
        SerialDescriptor elementDescriptor = serialDescriptor.getElementDescriptor(0);
        if (elementDescriptor.isInline()) {
            elementDescriptor = elementDescriptor.getElementDescriptor(0);
        }
        SerialKind kind2 = elementDescriptor.getKind();
        if ((kind2 instanceof PrimitiveKind) || Intrinsics.areEqual(kind2, SerialKind.ENUM.INSTANCE)) {
            return WriteMode.MAP;
        }
        if (json.configuration.allowStructuredMapKeys) {
            return writeMode;
        }
        throw InputKt.InvalidKeyKindException(elementDescriptor);
    }
}
