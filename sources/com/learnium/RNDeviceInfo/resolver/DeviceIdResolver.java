package com.learnium.RNDeviceInfo.resolver;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes.dex */
public class DeviceIdResolver {
    public final Context context;

    public DeviceIdResolver(Context context) {
        this.context = context;
    }

    public String getFirebaseInstanceId() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object invoke = Class.forName("com.google.firebase.iid.FirebaseInstanceId").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        return (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
    }

    public String getGmsInstanceId() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object invoke = Class.forName("com.google.android.gms.iid.InstanceID").getDeclaredMethod("getInstance", Context.class).invoke(null, this.context);
        return (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
    }
}
