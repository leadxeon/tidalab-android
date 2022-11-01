package kotlinx.serialization.internal;

import java.util.Objects;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.DeserializationStrategy;
/* compiled from: Tagged.kt */
/* loaded from: classes.dex */
public final class TaggedDecoder$decodeNullableSerializableElement$1 extends Lambda implements Function0<T> {
    public final /* synthetic */ DeserializationStrategy<T> $deserializer;
    public final /* synthetic */ T $previousValue;
    public final /* synthetic */ TaggedDecoder<Tag> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaggedDecoder$decodeNullableSerializableElement$1(TaggedDecoder<Tag> taggedDecoder, DeserializationStrategy<T> deserializationStrategy, T t) {
        super(0);
        this.this$0 = taggedDecoder;
        this.$deserializer = deserializationStrategy;
        this.$previousValue = t;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    public final T invoke() {
        if (this.this$0.decodeNotNullMark()) {
            return this.this$0.decodeSerializableValue(this.$deserializer);
        }
        Objects.requireNonNull(this.this$0);
        return null;
    }
}
