package kotlinx.serialization.json;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.ReflectionFactory;
import kotlin.jvm.internal.TypeReference;
import kotlin.reflect.KClass;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
/* compiled from: JsonElementSerializers.kt */
/* loaded from: classes.dex */
public final class JsonObjectSerializer implements KSerializer<JsonObject> {
    public static final JsonObjectSerializer INSTANCE = new JsonObjectSerializer();
    public static final SerialDescriptor descriptor = JsonObjectDescriptor.INSTANCE;

    /* compiled from: JsonElementSerializers.kt */
    /* loaded from: classes.dex */
    public static final class JsonObjectDescriptor implements SerialDescriptor {
        public static final JsonObjectDescriptor INSTANCE = new JsonObjectDescriptor();
        public static final String serialName = "kotlinx.serialization.json.JsonObject";
        public final /* synthetic */ SerialDescriptor $$delegate_0;

        public JsonObjectDescriptor() {
            KTypeProjection.Companion companion = KTypeProjection.Companion;
            KTypeProjection invariant = companion.invariant(Reflection.typeOf(String.class));
            KTypeProjection invariant2 = companion.invariant(Reflection.typeOf(JsonElement.class));
            ReflectionFactory reflectionFactory = Reflection.factory;
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(HashMap.class);
            List asList = Arrays.asList(invariant, invariant2);
            Objects.requireNonNull(reflectionFactory);
            TypeReference typeReference = new TypeReference(orCreateKotlinClass, asList, false);
            SerializersModule serializersModule = SerializersModuleKt.EmptySerializersModule;
            this.$$delegate_0 = InputKt.serializer(SerializersModuleKt.EmptySerializersModule, typeReference).getDescriptor();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public List<Annotation> getElementAnnotations(int i) {
            return this.$$delegate_0.getElementAnnotations(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public SerialDescriptor getElementDescriptor(int i) {
            return this.$$delegate_0.getElementDescriptor(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public int getElementIndex(String str) {
            return this.$$delegate_0.getElementIndex(str);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public String getElementName(int i) {
            return this.$$delegate_0.getElementName(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public int getElementsCount() {
            return this.$$delegate_0.getElementsCount();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public SerialKind getKind() {
            return this.$$delegate_0.getKind();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public String getSerialName() {
            return serialName;
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isInline() {
            return this.$$delegate_0.isInline();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isNullable() {
            return this.$$delegate_0.isNullable();
        }
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        InputKt.asJsonDecoder(decoder);
        return new JsonObject(new LinkedHashMapSerializer(StringSerializer.INSTANCE, JsonElementSerializer.INSTANCE).merge(decoder, null));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        InputKt.access$verify(encoder);
        new LinkedHashMapSerializer(StringSerializer.INSTANCE, JsonElementSerializer.INSTANCE).serialize(encoder, (JsonObject) obj);
    }
}
