package kotlin.collections;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import okhttp3.HttpUrl;
/* compiled from: _Arrays.kt */
/* loaded from: classes.dex */
public class ArraysKt___ArraysKt extends ArraysKt___ArraysJvmKt {
    public static final <T> boolean addAll(Collection<? super T> collection, Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        boolean z = false;
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (collection.add((Object) it.next())) {
                z = true;
            }
        }
        return z;
    }

    public static final <T> boolean contains(Iterable<? extends T> iterable, T t) {
        int i;
        if (iterable instanceof Collection) {
            return ((Collection) iterable).contains(t);
        }
        if (!(iterable instanceof List)) {
            Iterator<? extends T> it = iterable.iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                Object next = it.next();
                if (i2 < 0) {
                    throwIndexOverflow();
                    throw null;
                } else if (Intrinsics.areEqual(t, next)) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
        } else {
            i = ((List) iterable).indexOf(t);
        }
        return i >= 0;
    }

    public static final <T> List<T> drop(Iterable<? extends T> iterable, int i) {
        ArrayList arrayList;
        Object obj;
        int i2 = 0;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline4("Requested element count ", i, " is less than zero.").toString());
        } else if (i == 0) {
            return toList(iterable);
        } else {
            if (iterable instanceof Collection) {
                Collection collection = (Collection) iterable;
                int size = collection.size() - i;
                if (size <= 0) {
                    return EmptyList.INSTANCE;
                }
                if (size == 1) {
                    if (iterable instanceof List) {
                        obj = last((List) iterable);
                    } else {
                        Iterator<? extends T> it = iterable.iterator();
                        if (it.hasNext()) {
                            obj = it.next();
                            while (it.hasNext()) {
                                obj = it.next();
                            }
                        } else {
                            throw new NoSuchElementException("Collection is empty.");
                        }
                    }
                    return Collections.singletonList(obj);
                }
                arrayList = new ArrayList(size);
                if (iterable instanceof List) {
                    if (iterable instanceof RandomAccess) {
                        int size2 = collection.size();
                        while (i < size2) {
                            arrayList.add(((List) iterable).get(i));
                            i++;
                        }
                    } else {
                        ListIterator listIterator = ((List) iterable).listIterator(i);
                        while (listIterator.hasNext()) {
                            arrayList.add(listIterator.next());
                        }
                    }
                    return arrayList;
                }
            } else {
                arrayList = new ArrayList();
            }
            for (Object obj2 : iterable) {
                if (i2 >= i) {
                    arrayList.add(obj2);
                } else {
                    i2++;
                }
            }
            return optimizeReadOnlyList(arrayList);
        }
    }

    public static final IntRange getIndices(Collection<?> collection) {
        return new IntRange(0, collection.size() - 1);
    }

    public static final <T> int getLastIndex(List<? extends T> list) {
        return list.size() - 1;
    }

    public static final <K, V> V getValue(Map<K, ? extends V> map, K k) {
        if (map instanceof MapWithDefault) {
            return (V) ((MapWithDefault) map).getOrImplicitDefault(k);
        }
        V v = (V) map.get(k);
        if (v != null || map.containsKey(k)) {
            return v;
        }
        throw new NoSuchElementException("Key " + k + " is missing in the map.");
    }

    public static final <T> int indexOf(T[] tArr, T t) {
        int i = 0;
        if (t == null) {
            int length = tArr.length;
            while (i < length) {
                if (tArr[i] == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        int length2 = tArr.length;
        while (i < length2) {
            if (Intrinsics.areEqual(t, tArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final <T, A extends Appendable> A joinTo(Iterable<? extends T> iterable, A a, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1<? super T, ? extends CharSequence> function1) {
        a.append(charSequence2);
        Iterator<? extends T> it = iterable.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (Object) it.next();
            i2++;
            boolean z = true;
            if (i2 > 1) {
                a.append(charSequence);
            }
            if (i >= 0 && i2 > i) {
                break;
            } else if (function1 != null) {
                a.append((CharSequence) function1.invoke(obj));
            } else {
                if (obj != 0) {
                    z = obj instanceof CharSequence;
                }
                if (z) {
                    a.append((CharSequence) obj);
                } else if (obj instanceof Character) {
                    a.append(((Character) obj).charValue());
                } else {
                    a.append(String.valueOf(obj));
                }
            }
        }
        if (i >= 0 && i2 > i) {
            a.append(charSequence4);
        }
        a.append(charSequence3);
        return a;
    }

    public static /* synthetic */ Appendable joinTo$default(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2) {
        int i3 = i2 & 64;
        joinTo(iterable, appendable, (i2 & 2) != 0 ? ", " : charSequence, (i2 & 4) != 0 ? HttpUrl.FRAGMENT_ENCODE_SET : null, (i2 & 8) != 0 ? HttpUrl.FRAGMENT_ENCODE_SET : null, (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : null, null);
        return appendable;
    }

    public static String joinToString$default(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2) {
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = (i2 & 2) != 0 ? HttpUrl.FRAGMENT_ENCODE_SET : charSequence2;
        CharSequence charSequence6 = (i2 & 4) != 0 ? HttpUrl.FRAGMENT_ENCODE_SET : charSequence3;
        int i3 = (i2 & 8) != 0 ? -1 : i;
        String str = (i2 & 16) != 0 ? "..." : null;
        Function1 function12 = (i2 & 32) != 0 ? null : function1;
        StringBuilder sb = new StringBuilder();
        joinTo(iterable, sb, charSequence, charSequence5, charSequence6, i3, str, function12);
        return sb.toString();
    }

    public static final <T> T last(List<? extends T> list) {
        if (!list.isEmpty()) {
            return (T) list.get(getLastIndex(list));
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static final <T> T lastOrNull(List<? extends T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return (T) list.get(list.size() - 1);
    }

    public static final <T> List<T> listOf(T... tArr) {
        if (tArr.length > 0) {
            return Arrays.asList(tArr);
        }
        return EmptyList.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> List<T> optimizeReadOnlyList(List<? extends T> list) {
        int size = list.size();
        if (size != 0) {
            return size != 1 ? list : Collections.singletonList(list.get(0));
        }
        return EmptyList.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Set<T> optimizeReadOnlySet(Set<? extends T> set) {
        int size = set.size();
        if (size != 0) {
            return size != 1 ? set : Collections.singleton(set.iterator().next());
        }
        return EmptySet.INSTANCE;
    }

    public static final <T> List<T> plus(Collection<? extends T> collection, Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            Collection collection2 = (Collection) iterable;
            ArrayList arrayList = new ArrayList(collection2.size() + collection.size());
            arrayList.addAll(collection);
            arrayList.addAll(collection2);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(collection);
        addAll(arrayList2, iterable);
        return arrayList2;
    }

    public static final <T> T removeLast(List<T> list) {
        if (!list.isEmpty()) {
            return list.remove(getLastIndex(list));
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static final char single(char[] cArr) {
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        } else if (length == 1) {
            return cArr[0];
        } else {
            throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    public static final <T extends Comparable<? super T>> List<T> sorted(Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.size() <= 1) {
                return toList(iterable);
            }
            Object[] array = collection.toArray(new Comparable[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            Comparable[] comparableArr = (Comparable[]) array;
            if (comparableArr.length > 1) {
                Arrays.sort(comparableArr);
            }
            return Arrays.asList(comparableArr);
        }
        List<T> mutableList = toMutableList(iterable);
        if (mutableList.size() > 1) {
            Collections.sort(mutableList);
        }
        return mutableList;
    }

    public static final void throwIndexOverflow() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    public static final <T, C extends Collection<? super T>> C toCollection(Iterable<? extends T> iterable, C c) {
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            c.add(it.next());
        }
        return c;
    }

    public static final <T> HashSet<T> toHashSet(Iterable<? extends T> iterable) {
        HashSet<T> hashSet = new HashSet<>(InputKt.mapCapacity(InputKt.collectionSizeOrDefault(iterable, 12)));
        toCollection(iterable, hashSet);
        return hashSet;
    }

    public static final <T> List<T> toList(Iterable<? extends T> iterable) {
        if (!(iterable instanceof Collection)) {
            return optimizeReadOnlyList(toMutableList(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return EmptyList.INSTANCE;
        }
        if (size != 1) {
            return new ArrayList(collection);
        }
        return Collections.singletonList(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(Iterable<? extends Pair<? extends K, ? extends V>> iterable, M m) {
        Iterator<? extends Pair<? extends K, ? extends V>> it = iterable.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            m.put(pair.first, pair.second);
        }
        return m;
    }

    public static final <T> List<T> toMutableList(Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return new ArrayList((Collection) iterable);
        }
        ArrayList arrayList = new ArrayList();
        toCollection(iterable, arrayList);
        return arrayList;
    }

    public static final <T> Set<T> toMutableSet(Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return new LinkedHashSet((Collection) iterable);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        toCollection(iterable, linkedHashSet);
        return linkedHashSet;
    }

    public static final <T> Set<T> toSet(Iterable<? extends T> iterable) {
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return EmptySet.INSTANCE;
        }
        if (size != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(InputKt.mapCapacity(collection.size()));
            toCollection(iterable, linkedHashSet);
            return linkedHashSet;
        }
        return Collections.singleton(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static final <K, V> Map<K, V> toMap(Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size == 0) {
                return EmptyMap.INSTANCE;
            }
            if (size != 1) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(InputKt.mapCapacity(collection.size()));
                toMap(iterable, linkedHashMap);
                return linkedHashMap;
            }
            Pair pair = (Pair) (iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
            return Collections.singletonMap(pair.first, pair.second);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        toMap(iterable, linkedHashMap2);
        int size2 = linkedHashMap2.size();
        if (size2 == 0) {
            return EmptyMap.INSTANCE;
        }
        if (size2 != 1) {
            return linkedHashMap2;
        }
        Map.Entry<K, V> next = linkedHashMap2.entrySet().iterator().next();
        return Collections.singletonMap(next.getKey(), next.getValue());
    }

    public static final <T> Set<T> toSet(T[] tArr) {
        int length = tArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length == 1) {
            return Collections.singleton(tArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(InputKt.mapCapacity(tArr.length));
        for (T t : tArr) {
            linkedHashSet.add(t);
        }
        return linkedHashSet;
    }

    public static final <T> List<T> toList(T[] tArr) {
        int length = tArr.length;
        if (length == 0) {
            return EmptyList.INSTANCE;
        }
        if (length != 1) {
            return new ArrayList(new ArrayAsCollection(tArr, false));
        }
        return Collections.singletonList(tArr[0]);
    }
}
