package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Pair;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: Tuples.kt */
/* loaded from: classes.dex */
public final class PairSerializer<K, V> extends KeyValueSerializer<K, V, Pair<? extends K, ? extends V>> {
    public final SerialDescriptor descriptor;

    public PairSerializer(KSerializer<K> kSerializer, KSerializer<V> kSerializer2) {
        super(kSerializer, kSerializer2, null);
        this.descriptor = InputKt.buildClassSerialDescriptor("kotlin.Pair", new SerialDescriptor[0], new PairSerializer$descriptor$1(kSerializer, kSerializer2));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public Object getKey(Object obj) {
        return ((Pair) obj).first;
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public Object getValue(Object obj) {
        return ((Pair) obj).second;
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public Object toResult(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }
}
