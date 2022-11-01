package kotlin.jvm.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__IndentKt;
/* compiled from: ClassReference.kt */
/* loaded from: classes.dex */
public final class ClassReference implements KClass<Object>, ClassBasedDeclarationContainer {
    public static final Companion Companion = new Companion(null);
    public static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;
    public static final HashMap<String, String> classFqNames;
    public static final HashMap<String, String> primitiveFqNames;
    public static final HashMap<String, String> primitiveWrapperFqNames;
    public static final Map<String, String> simpleNames;
    public final Class<?> jClass;

    /* compiled from: ClassReference.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        int i = 0;
        List listOf = ArraysKt___ArraysKt.listOf(Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class);
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(listOf, 10));
        for (Object obj : listOf) {
            i++;
            if (i >= 0) {
                arrayList.add(new Pair((Class) obj, Integer.valueOf(i)));
            } else {
                ArraysKt___ArraysKt.throwIndexOverflow();
                throw null;
            }
        }
        FUNCTION_CLASSES = ArraysKt___ArraysKt.toMap(arrayList);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("boolean", "kotlin.Boolean");
        hashMap.put("char", "kotlin.Char");
        hashMap.put("byte", "kotlin.Byte");
        hashMap.put("short", "kotlin.Short");
        hashMap.put("int", "kotlin.Int");
        hashMap.put("float", "kotlin.Float");
        hashMap.put("long", "kotlin.Long");
        hashMap.put("double", "kotlin.Double");
        primitiveFqNames = hashMap;
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("java.lang.Boolean", "kotlin.Boolean");
        hashMap2.put("java.lang.Character", "kotlin.Char");
        hashMap2.put("java.lang.Byte", "kotlin.Byte");
        hashMap2.put("java.lang.Short", "kotlin.Short");
        hashMap2.put("java.lang.Integer", "kotlin.Int");
        hashMap2.put("java.lang.Float", "kotlin.Float");
        hashMap2.put("java.lang.Long", "kotlin.Long");
        hashMap2.put("java.lang.Double", "kotlin.Double");
        primitiveWrapperFqNames = hashMap2;
        HashMap<String, String> hashMap3 = new HashMap<>();
        hashMap3.put("java.lang.Object", "kotlin.Any");
        hashMap3.put("java.lang.String", "kotlin.String");
        hashMap3.put("java.lang.CharSequence", "kotlin.CharSequence");
        hashMap3.put("java.lang.Throwable", "kotlin.Throwable");
        hashMap3.put("java.lang.Cloneable", "kotlin.Cloneable");
        hashMap3.put("java.lang.Number", "kotlin.Number");
        hashMap3.put("java.lang.Comparable", "kotlin.Comparable");
        hashMap3.put("java.lang.Enum", "kotlin.Enum");
        hashMap3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        hashMap3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        hashMap3.put("java.util.Iterator", "kotlin.collections.Iterator");
        hashMap3.put("java.util.Collection", "kotlin.collections.Collection");
        hashMap3.put("java.util.List", "kotlin.collections.List");
        hashMap3.put("java.util.Set", "kotlin.collections.Set");
        hashMap3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        hashMap3.put("java.util.Map", "kotlin.collections.Map");
        hashMap3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        hashMap3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        hashMap3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        hashMap3.putAll(hashMap);
        hashMap3.putAll(hashMap2);
        for (String str : hashMap.values()) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("kotlin.jvm.internal.");
            outline13.append(StringsKt__IndentKt.substringAfterLast$default(str, '.', null, 2));
            outline13.append("CompanionObject");
            hashMap3.put(outline13.toString(), str + ".Companion");
        }
        for (Map.Entry<Class<? extends Function<?>>, Integer> entry : FUNCTION_CLASSES.entrySet()) {
            int intValue = entry.getValue().intValue();
            hashMap3.put(entry.getKey().getName(), "kotlin.Function" + intValue);
        }
        classFqNames = hashMap3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(InputKt.mapCapacity(hashMap3.size()));
        Iterator<T> it = hashMap3.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it.next();
            linkedHashMap.put(entry2.getKey(), StringsKt__IndentKt.substringAfterLast$default((String) entry2.getValue(), '.', null, 2));
        }
        simpleNames = linkedHashMap;
    }

    public ClassReference(Class<?> cls) {
        this.jClass = cls;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ClassReference) && Intrinsics.areEqual(InputKt.getJavaObjectType(this), InputKt.getJavaObjectType((KClass) obj));
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class<?> getJClass() {
        return this.jClass;
    }

    @Override // kotlin.reflect.KClass
    public String getSimpleName() {
        String str;
        Method enclosingMethod;
        String str2;
        Constructor<?> enclosingConstructor;
        Class<?> cls = this.jClass;
        String str3 = null;
        if (!cls.isAnonymousClass()) {
            if (cls.isLocalClass()) {
                String simpleName = cls.getSimpleName();
                if (cls.getEnclosingMethod() != null) {
                    str2 = StringsKt__IndentKt.substringAfter$default(simpleName, enclosingMethod.getName() + "$", null, 2);
                } else {
                    if (cls.getEnclosingConstructor() != null) {
                        str2 = StringsKt__IndentKt.substringAfter$default(simpleName, enclosingConstructor.getName() + "$", null, 2);
                    } else {
                        str2 = null;
                    }
                }
                if (str2 != null) {
                    return str2;
                }
                int indexOf$default = StringsKt__IndentKt.indexOf$default((CharSequence) simpleName, '$', 0, false, 6);
                return indexOf$default == -1 ? simpleName : simpleName.substring(indexOf$default + 1, simpleName.length());
            } else if (cls.isArray()) {
                Class<?> componentType = cls.getComponentType();
                if (componentType.isPrimitive() && (str = simpleNames.get(componentType.getName())) != null) {
                    str3 = GeneratedOutlineSupport.outline8(str, "Array");
                }
                if (str3 == null) {
                    return "Array";
                }
            } else {
                String str4 = simpleNames.get(cls.getName());
                return str4 != null ? str4 : cls.getSimpleName();
            }
        }
        return str3;
    }

    @Override // kotlin.reflect.KClass
    public int hashCode() {
        return InputKt.getJavaObjectType(this).hashCode();
    }

    public String toString() {
        return this.jClass.toString() + " (Kotlin reflection is not available)";
    }
}
