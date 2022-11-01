package kotlinx.serialization.json;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.lang.annotation.Annotation;
import java.util.Collections;
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
import kotlinx.serialization.internal.ArrayListSerializer;
/* compiled from: JsonElementSerializers.kt */
/* loaded from: classes.dex */
public final class JsonArraySerializer implements KSerializer<JsonArray> {
    public static final JsonArraySerializer INSTANCE = new JsonArraySerializer();
    public static final SerialDescriptor descriptor = JsonArrayDescriptor.INSTANCE;

    /* compiled from: JsonElementSerializers.kt */
    /* loaded from: classes.dex */
    public static final class JsonArrayDescriptor implements SerialDescriptor {
        public static final JsonArrayDescriptor INSTANCE = new JsonArrayDescriptor();
        public static final String serialName = "kotlinx.serialization.json.JsonArray";
        public final /* synthetic */ SerialDescriptor $$delegate_0;

        public JsonArrayDescriptor() {
            KTypeProjection invariant = KTypeProjection.Companion.invariant(Reflection.typeOf(JsonElement.class));
            ReflectionFactory reflectionFactory = Reflection.factory;
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(List.class);
            List singletonList = Collections.singletonList(invariant);
            Objects.requireNonNull(reflectionFactory);
            this.$$delegate_0 = InputKt.serializer(new TypeReference(orCreateKotlinClass, singletonList, false)).getDescriptor();
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
        return new JsonArray(new ArrayListSerializer(JsonElementSerializer.INSTANCE).merge(decoder, null));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        InputKt.access$verify(encoder);
        new ArrayListSerializer(JsonElementSerializer.INSTANCE).serialize(encoder, (JsonArray) obj);
    }
}
