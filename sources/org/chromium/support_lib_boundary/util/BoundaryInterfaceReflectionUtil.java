package org.chromium.support_lib_boundary.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
/* loaded from: classes.dex */
public class BoundaryInterfaceReflectionUtil {
    public static <T> T castToSuppLibClass(Class<T> cls, InvocationHandler invocationHandler) {
        if (invocationHandler == null) {
            return null;
        }
        return cls.cast(Proxy.newProxyInstance(BoundaryInterfaceReflectionUtil.class.getClassLoader(), new Class[]{cls}, invocationHandler));
    }
}
