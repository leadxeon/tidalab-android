package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class Lifecycling {
    public static Map<Class<?>, Integer> sCallbackCache = new HashMap();
    public static Map<Class<?>, List<Constructor<? extends GeneratedAdapter>>> sClassToAdapters = new HashMap();

    /* renamed from: androidx.lifecycle.Lifecycling$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements LifecycleEventObserver {
        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            throw null;
        }
    }

    public static GeneratedAdapter createGeneratedAdapter(Constructor<? extends GeneratedAdapter> constructor, Object obj) {
        try {
            return (GeneratedAdapter) constructor.newInstance(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static String getAdapterName(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }

    public static int getObserverConstructorType(Class<?> cls) {
        Constructor<?> constructor;
        boolean z;
        Integer num = sCallbackCache.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int i = 1;
        if (cls.getCanonicalName() != null) {
            ArrayList arrayList = null;
            try {
                Package r4 = cls.getPackage();
                String canonicalName = cls.getCanonicalName();
                String name = r4 != null ? r4.getName() : HttpUrl.FRAGMENT_ENCODE_SET;
                if (!name.isEmpty()) {
                    canonicalName = canonicalName.substring(name.length() + 1);
                }
                String adapterName = getAdapterName(canonicalName);
                if (!name.isEmpty()) {
                    adapterName = name + "." + adapterName;
                }
                constructor = Class.forName(adapterName).getDeclaredConstructor(cls);
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
            } catch (ClassNotFoundException unused) {
                constructor = null;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            if (constructor != null) {
                sClassToAdapters.put(cls, Collections.singletonList(constructor));
            } else {
                ClassesInfoCache classesInfoCache = ClassesInfoCache.sInstance;
                Boolean bool = classesInfoCache.mHasLifecycleMethods.get(cls);
                if (bool != null) {
                    z = bool.booleanValue();
                } else {
                    try {
                        Method[] declaredMethods = cls.getDeclaredMethods();
                        int length = declaredMethods.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                classesInfoCache.mHasLifecycleMethods.put(cls, Boolean.FALSE);
                                z = false;
                                break;
                            } else if (((OnLifecycleEvent) declaredMethods[i2].getAnnotation(OnLifecycleEvent.class)) != null) {
                                classesInfoCache.createInfo(cls, declaredMethods);
                                z = true;
                                break;
                            } else {
                                i2++;
                            }
                        }
                    } catch (NoClassDefFoundError e2) {
                        throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
                    }
                }
                if (!z) {
                    Class<? super Object> superclass = cls.getSuperclass();
                    if (superclass != null && LifecycleObserver.class.isAssignableFrom(superclass)) {
                        if (getObserverConstructorType(superclass) != 1) {
                            arrayList = new ArrayList(sClassToAdapters.get(superclass));
                        }
                    }
                    Class<?>[] interfaces = cls.getInterfaces();
                    int length2 = interfaces.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 < length2) {
                            Class<?> cls2 = interfaces[i3];
                            if (cls2 != null && LifecycleObserver.class.isAssignableFrom(cls2)) {
                                if (getObserverConstructorType(cls2) == 1) {
                                    break;
                                }
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                arrayList.addAll(sClassToAdapters.get(cls2));
                            }
                            i3++;
                        } else if (arrayList != null) {
                            sClassToAdapters.put(cls, arrayList);
                        }
                    }
                }
            }
            i = 2;
        }
        sCallbackCache.put(cls, Integer.valueOf(i));
        return i;
    }
}
