package kotlinx.serialization;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.HashMapSerializer;
import kotlinx.serialization.internal.HashSetSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.MapEntrySerializer;
import kotlinx.serialization.internal.NullableSerializer;
import kotlinx.serialization.internal.PairSerializer;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.TripleSerializer;
import kotlinx.serialization.modules.SerializersModule;
/* compiled from: Serializers.kt */
/* loaded from: classes.dex */
public final /* synthetic */ class SerializersKt__SerializersKt {
    public static final KSerializer<Object> serializerByKTypeImpl$SerializersKt__SerializersKt(SerializersModule serializersModule, KType kType, boolean z) {
        KSerializer<Object> kSerializer;
        ArrayList arrayList;
        KClass<Object> kclass = Platform_commonKt.kclass(kType);
        boolean isMarkedNullable = kType.isMarkedNullable();
        List<KTypeProjection> arguments = kType.getArguments();
        ArrayList<KType> arrayList2 = new ArrayList(InputKt.collectionSizeOrDefault(arguments, 10));
        for (KTypeProjection kTypeProjection : arguments) {
            KType kType2 = kTypeProjection.type;
            if (kType2 != null) {
                arrayList2.add(kType2);
            } else {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Star projections in type arguments are not allowed, but had ", kType).toString());
            }
        }
        if (arrayList2.isEmpty()) {
            kSerializer = InputKt.serializerOrNull(kclass);
            if (kSerializer == null) {
                kSerializer = serializersModule.getContextual(kclass, EmptyList.INSTANCE);
            }
        } else {
            if (z) {
                arrayList = new ArrayList(InputKt.collectionSizeOrDefault(arrayList2, 10));
                for (KType kType3 : arrayList2) {
                    arrayList.add(InputKt.serializer(serializersModule, kType3));
                }
            } else {
                arrayList = new ArrayList(InputKt.collectionSizeOrDefault(arrayList2, 10));
                for (KType kType4 : arrayList2) {
                    KSerializer<Object> serializerByKTypeImpl$SerializersKt__SerializersKt = serializerByKTypeImpl$SerializersKt__SerializersKt(serializersModule, kType4, false);
                    if (serializerByKTypeImpl$SerializersKt__SerializersKt == null) {
                        kSerializer = null;
                        break;
                    }
                    arrayList.add(serializerByKTypeImpl$SerializersKt__SerializersKt);
                }
            }
            if (Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(Collection.class)) ? true : Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(List.class)) ? true : Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(List.class)) ? true : Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(ArrayList.class))) {
                kSerializer = new ArrayListSerializer<>((KSerializer) arrayList.get(0));
            } else if (Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(HashSet.class))) {
                kSerializer = new HashSetSerializer<>((KSerializer) arrayList.get(0));
            } else if (Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(Set.class)) ? true : Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(Set.class)) ? true : Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(LinkedHashSet.class))) {
                kSerializer = new LinkedHashSetSerializer<>((KSerializer) arrayList.get(0));
            } else if (Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(HashMap.class))) {
                kSerializer = new HashMapSerializer<>((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1));
            } else if (Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(Map.class)) ? true : Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(Map.class)) ? true : Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(LinkedHashMap.class))) {
                kSerializer = new LinkedHashMapSerializer<>((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1));
            } else if (Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(Map.Entry.class))) {
                kSerializer = new MapEntrySerializer<>((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1));
            } else if (Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(Pair.class))) {
                kSerializer = new PairSerializer<>((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1));
            } else if (Intrinsics.areEqual(kclass, Reflection.getOrCreateKotlinClass(Triple.class))) {
                kSerializer = new TripleSerializer((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1), (KSerializer) arrayList.get(2));
            } else if (InputKt.getJavaClass(kclass).isArray()) {
                KClass classifier = ((KType) arrayList2.get(0)).getClassifier();
                Objects.requireNonNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                kSerializer = new ReferenceArraySerializer(classifier, (KSerializer) arrayList.get(0));
            } else {
                Object[] array = arrayList.toArray(new KSerializer[0]);
                Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
                KSerializer[] kSerializerArr = (KSerializer[]) array;
                kSerializer = InputKt.constructSerializerForGivenTypeArgs(kclass, (KSerializer[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
                if (kSerializer == null && (kSerializer = InputKt.serializerOrNull(kclass)) == null) {
                    kSerializer = serializersModule.getContextual(kclass, arrayList);
                }
            }
        }
        if (kSerializer == null) {
            kSerializer = null;
        }
        if (kSerializer == null) {
            return null;
        }
        return (!isMarkedNullable || kSerializer.getDescriptor().isNullable()) ? kSerializer : new NullableSerializer(kSerializer);
    }
}
