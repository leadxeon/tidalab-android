package com.google.android.gms.dynamic;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import java.lang.reflect.Field;
/* loaded from: classes.dex */
public final class ObjectWrapper<T> extends IObjectWrapper.Stub {
    public final T zzib;

    public ObjectWrapper(T t) {
        this.zzib = t;
    }

    public static <T> T unwrap(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper instanceof ObjectWrapper) {
            return ((ObjectWrapper) iObjectWrapper).zzib;
        }
        zzb zzbVar = (zzb) iObjectWrapper;
        Field[] declaredFields = zzbVar.getClass().getDeclaredFields();
        Field field = null;
        int i = 0;
        for (Field field2 : declaredFields) {
            if (!field2.isSynthetic()) {
                i++;
                field = field2;
            }
        }
        if (i != 1) {
            int length = declaredFields.length;
            StringBuilder sb = new StringBuilder(64);
            sb.append("Unexpected number of IObjectWrapper declared fields: ");
            sb.append(length);
            throw new IllegalArgumentException(sb.toString());
        } else if (!field.isAccessible()) {
            field.setAccessible(true);
            try {
                return (T) field.get(zzbVar);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Could not access the field in remoteBinder.", e);
            } catch (NullPointerException e2) {
                throw new IllegalArgumentException("Binder object is null.", e2);
            }
        } else {
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
    }
}
