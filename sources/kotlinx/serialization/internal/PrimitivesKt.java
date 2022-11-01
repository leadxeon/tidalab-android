package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
/* compiled from: Primitives.kt */
/* loaded from: classes.dex */
public final class PrimitivesKt {
    public static final Map<KClass<? extends Object>, KSerializer<? extends Object>> BUILTIN_SERIALIZERS;

    static {
        Pair pair = new Pair(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Unit.class);
        Unit unit = Unit.INSTANCE;
        Pair[] pairArr = {pair, new Pair(Reflection.getOrCreateKotlinClass(Character.TYPE), CharSerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(char[].class), CharArraySerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(Double.TYPE), DoubleSerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(double[].class), DoubleArraySerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(Float.TYPE), FloatSerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(float[].class), FloatArraySerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(Long.TYPE), LongSerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(long[].class), LongArraySerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(Integer.TYPE), IntSerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(int[].class), IntArraySerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(Short.TYPE), ShortSerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(short[].class), ShortArraySerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(Byte.TYPE), ByteSerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(byte[].class), ByteArraySerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(Boolean.TYPE), BooleanSerializer.INSTANCE), new Pair(Reflection.getOrCreateKotlinClass(boolean[].class), BooleanArraySerializer.INSTANCE), new Pair(orCreateKotlinClass, UnitSerializer.INSTANCE)};
        LinkedHashMap linkedHashMap = new LinkedHashMap(InputKt.mapCapacity(18));
        for (int i = 0; i < 18; i++) {
            Pair pair2 = pairArr[i];
            linkedHashMap.put(pair2.first, pair2.second);
        }
        BUILTIN_SERIALIZERS = linkedHashMap;
    }
}
