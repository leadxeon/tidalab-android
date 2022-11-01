package com.learnium.RNDeviceInfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/* loaded from: classes.dex */
public class RNInstallReferrerClient {
    public static Class<?> InstallReferrerClientClazz;
    public static Class<?> InstallReferrerStateListenerClazz;
    public static Class<?> ReferrerDetailsClazz;
    public Object installReferrerStateListener;
    public Object mReferrerClient;
    public final SharedPreferences sharedPreferences;

    /* loaded from: classes.dex */
    public class InstallReferrerStateListenerProxy implements InvocationHandler {
        public InstallReferrerStateListenerProxy(AnonymousClass1 r2) {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            try {
                if (name.equals("onInstallReferrerSetupFinished") && objArr != null && (objArr[0] instanceof Integer)) {
                    onInstallReferrerSetupFinished(((Integer) objArr[0]).intValue());
                    return null;
                } else if (!name.equals("onInstallReferrerServiceDisconnected")) {
                    return null;
                } else {
                    Log.d("RNInstallReferrerClient", "InstallReferrerService disconnected");
                    return null;
                }
            } catch (Exception e) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("unexpected invocation exception: ");
                outline13.append(e.getMessage());
                throw new RuntimeException(outline13.toString());
            }
        }

        public void onInstallReferrerSetupFinished(int i) {
            if (i == 0) {
                try {
                    Log.d("InstallReferrerState", "OK");
                    Object invoke = RNInstallReferrerClient.InstallReferrerClientClazz.getMethod("getInstallReferrer", new Class[0]).invoke(RNInstallReferrerClient.this.mReferrerClient, new Object[0]);
                    SharedPreferences.Editor edit = RNInstallReferrerClient.this.sharedPreferences.edit();
                    edit.putString("installReferrer", (String) RNInstallReferrerClient.ReferrerDetailsClazz.getMethod("getInstallReferrer", new Class[0]).invoke(invoke, new Object[0]));
                    edit.apply();
                    RNInstallReferrerClient.InstallReferrerClientClazz.getMethod("endConnection", new Class[0]).invoke(RNInstallReferrerClient.this.mReferrerClient, new Object[0]);
                } catch (Exception e) {
                    PrintStream printStream = System.err;
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("RNInstallReferrerClient exception. getInstallReferrer will be unavailable: ");
                    outline13.append(e.getMessage());
                    printStream.println(outline13.toString());
                    e.printStackTrace(System.err);
                }
            } else if (i == 1) {
                Log.d("InstallReferrerState", "SERVICE_UNAVAILABLE");
            } else if (i == 2) {
                Log.d("InstallReferrerState", "FEATURE_NOT_SUPPORTED");
            }
        }
    }

    static {
        try {
            InstallReferrerClientClazz = Class.forName("com.android.installreferrer.api.InstallReferrerClient");
            InstallReferrerStateListenerClazz = Class.forName("com.android.installreferrer.api.InstallReferrerStateListener");
            ReferrerDetailsClazz = Class.forName("com.android.installreferrer.api.ReferrerDetails");
        } catch (Exception unused) {
            System.err.println("RNInstallReferrerClient exception. 'installreferrer' APIs are unavailable.");
        }
    }

    public RNInstallReferrerClient(Context context) {
        this.sharedPreferences = context.getSharedPreferences("react-native-device-info", 0);
        Class<?> cls = InstallReferrerClientClazz;
        if (cls != null && InstallReferrerStateListenerClazz != null && ReferrerDetailsClazz != null) {
            try {
                Object invoke = cls.getMethod("newBuilder", Context.class).invoke(null, context);
                this.mReferrerClient = invoke.getClass().getMethod("build", new Class[0]).invoke(invoke, new Object[0]);
                this.installReferrerStateListener = Proxy.newProxyInstance(InstallReferrerStateListenerClazz.getClassLoader(), new Class[]{InstallReferrerStateListenerClazz}, new InstallReferrerStateListenerProxy(null));
                InstallReferrerClientClazz.getMethod("startConnection", InstallReferrerStateListenerClazz).invoke(this.mReferrerClient, this.installReferrerStateListener);
            } catch (Exception e) {
                PrintStream printStream = System.err;
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("RNInstallReferrerClient exception. getInstallReferrer will be unavailable: ");
                outline13.append(e.getMessage());
                printStream.println(outline13.toString());
                e.printStackTrace(System.err);
            }
        }
    }
}
