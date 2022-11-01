package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.StrictMode;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes.dex */
public class SoLoader {
    public static boolean isSystemApp;
    public static ApplicationSoSource sApplicationSoSource;
    public static UnpackingSoSource[] sBackupSoSources;
    public static int sFlags;
    public static SoFileLoader sSoFileLoader;
    public static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();
    public static SoSource[] sSoSources = null;
    public static volatile int sSoSourcesVersion = 0;
    public static final HashSet<String> sLoadedLibraries = new HashSet<>();
    public static final Map<String, Object> sLoadingLibraries = new HashMap();
    public static final Set<String> sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());
    public static final boolean SYSTRACE_LIBRARY_LOADING = true;

    /* renamed from: com.facebook.soloader.SoLoader$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static class AnonymousClass1 implements SoFileLoader {
        public final /* synthetic */ boolean val$hasNativeLoadMethod;
        public final /* synthetic */ String val$localLdLibraryPath;
        public final /* synthetic */ String val$localLdLibraryPathNoZips;
        public final /* synthetic */ Method val$nativeLoadRuntimeMethod;
        public final /* synthetic */ Runtime val$runtime;

        public AnonymousClass1(boolean z, String str, String str2, Runtime runtime, Method method) {
            this.val$hasNativeLoadMethod = z;
            this.val$localLdLibraryPath = str;
            this.val$localLdLibraryPathNoZips = str2;
            this.val$runtime = runtime;
            this.val$nativeLoadRuntimeMethod = method;
        }

        public final String getLibHash(String str) {
            try {
                File file = new File(str);
                MessageDigest instance = MessageDigest.getInstance("MD5");
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read > 0) {
                            instance.update(bArr, 0, read);
                        } else {
                            String format = String.format("%32x", new BigInteger(1, instance.digest()));
                            fileInputStream.close();
                            return format;
                        }
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            } catch (IOException e) {
                return e.toString();
            } catch (SecurityException e2) {
                return e2.toString();
            } catch (NoSuchAlgorithmException e3) {
                return e3.toString();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x0094  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void load(java.lang.String r9, int r10) {
            /*
                r8 = this;
                boolean r0 = r8.val$hasNativeLoadMethod
                if (r0 == 0) goto L_0x00b5
                r0 = 4
                r10 = r10 & r0
                r1 = 1
                r2 = 0
                if (r10 != r0) goto L_0x000c
                r10 = 1
                goto L_0x000d
            L_0x000c:
                r10 = 0
            L_0x000d:
                if (r10 == 0) goto L_0x0012
                java.lang.String r10 = r8.val$localLdLibraryPath
                goto L_0x0014
            L_0x0012:
                java.lang.String r10 = r8.val$localLdLibraryPathNoZips
            L_0x0014:
                r0 = 0
                java.lang.Runtime r3 = r8.val$runtime     // Catch: all -> 0x0071, InvocationTargetException -> 0x0074, IllegalArgumentException -> 0x0076, IllegalAccessException -> 0x0078
                monitor-enter(r3)     // Catch: all -> 0x0071, InvocationTargetException -> 0x0074, IllegalArgumentException -> 0x0076, IllegalAccessException -> 0x0078
                java.lang.reflect.Method r4 = r8.val$nativeLoadRuntimeMethod     // Catch: all -> 0x0065
                java.lang.Runtime r5 = r8.val$runtime     // Catch: all -> 0x0065
                r6 = 3
                java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: all -> 0x0065
                r6[r2] = r9     // Catch: all -> 0x0065
                java.lang.Class<com.facebook.soloader.SoLoader> r2 = com.facebook.soloader.SoLoader.class
                java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch: all -> 0x0065
                r6[r1] = r2     // Catch: all -> 0x0065
                r1 = 2
                r6[r1] = r10     // Catch: all -> 0x0065
                java.lang.Object r1 = r4.invoke(r5, r6)     // Catch: all -> 0x0065
                java.lang.String r1 = (java.lang.String) r1     // Catch: all -> 0x0065
                if (r1 != 0) goto L_0x0059
                monitor-exit(r3)     // Catch: all -> 0x005f
                if (r1 == 0) goto L_0x00b8
                java.lang.String r0 = "SoLoader"
                java.lang.String r2 = "Error when loading lib: "
                java.lang.String r3 = " lib hash: "
                java.lang.StringBuilder r1 = com.android.tools.r8.GeneratedOutlineSupport.outline16(r2, r1, r3)
                java.lang.String r9 = r8.getLibHash(r9)
                r1.append(r9)
                java.lang.String r9 = " search path is "
                r1.append(r9)
                r1.append(r10)
                java.lang.String r9 = r1.toString()
                android.util.Log.e(r0, r9)
                goto L_0x00b8
            L_0x0059:
                java.lang.UnsatisfiedLinkError r0 = new java.lang.UnsatisfiedLinkError     // Catch: all -> 0x005f
                r0.<init>(r1)     // Catch: all -> 0x005f
                throw r0     // Catch: all -> 0x005f
            L_0x005f:
                r0 = move-exception
                r2 = r8
                r7 = r1
                r1 = r0
                r0 = r7
                goto L_0x0067
            L_0x0065:
                r1 = move-exception
                r2 = r8
            L_0x0067:
                monitor-exit(r3)     // Catch: all -> 0x006f
                throw r1     // Catch: InvocationTargetException -> 0x0069, IllegalArgumentException -> 0x006b, IllegalAccessException -> 0x006d, all -> 0x0091
            L_0x0069:
                r1 = move-exception
                goto L_0x007a
            L_0x006b:
                r1 = move-exception
                goto L_0x007a
            L_0x006d:
                r1 = move-exception
                goto L_0x007a
            L_0x006f:
                r1 = move-exception
                goto L_0x0067
            L_0x0071:
                r1 = move-exception
                r2 = r8
                goto L_0x0092
            L_0x0074:
                r1 = move-exception
                goto L_0x0079
            L_0x0076:
                r1 = move-exception
                goto L_0x0079
            L_0x0078:
                r1 = move-exception
            L_0x0079:
                r2 = r8
            L_0x007a:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x0091
                r3.<init>()     // Catch: all -> 0x0091
                java.lang.String r4 = "Error: Cannot load "
                r3.append(r4)     // Catch: all -> 0x0091
                r3.append(r9)     // Catch: all -> 0x0091
                java.lang.String r0 = r3.toString()     // Catch: all -> 0x0091
                java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch: all -> 0x0091
                r3.<init>(r0, r1)     // Catch: all -> 0x0091
                throw r3     // Catch: all -> 0x0091
            L_0x0091:
                r1 = move-exception
            L_0x0092:
                if (r0 == 0) goto L_0x00b4
                java.lang.String r3 = "SoLoader"
                java.lang.String r4 = "Error when loading lib: "
                java.lang.String r5 = " lib hash: "
                java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport.outline16(r4, r0, r5)
                java.lang.String r9 = r2.getLibHash(r9)
                r0.append(r9)
                java.lang.String r9 = " search path is "
                r0.append(r9)
                r0.append(r10)
                java.lang.String r9 = r0.toString()
                android.util.Log.e(r3, r9)
            L_0x00b4:
                throw r1
            L_0x00b5:
                java.lang.System.load(r9)
            L_0x00b8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.AnonymousClass1.load(java.lang.String, int):void");
        }
    }

    @DoNotOptimize
    @TargetApi(14)
    /* loaded from: classes.dex */
    public static class Api14Utils {
        private Api14Utils() {
        }

        public static String getClassLoaderLdLoadLibrary() {
            ClassLoader classLoader = SoLoader.class.getClassLoader();
            if (classLoader instanceof BaseDexClassLoader) {
                try {
                    return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
                } catch (Exception e) {
                    throw new RuntimeException("Cannot call getLdLibraryPath", e);
                }
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("ClassLoader ");
                outline13.append(classLoader.getClass().getName());
                outline13.append(" should be of type BaseDexClassLoader");
                throw new IllegalStateException(outline13.toString());
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class WrongAbiError extends UnsatisfiedLinkError {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public WrongAbiError(java.lang.Throwable r3, java.lang.String r4) {
            /*
                r2 = this;
                java.lang.String r0 = "APK was built for a different platform. Supported ABIs: "
                java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r0)
                java.lang.String[] r1 = com.facebook.soloader.SysUtil$LollipopSysdeps.getSupportedAbis()
                java.lang.String r1 = java.util.Arrays.toString(r1)
                r0.append(r1)
                java.lang.String r1 = " error: "
                r0.append(r1)
                r0.append(r4)
                java.lang.String r4 = r0.toString()
                r2.<init>(r4)
                r2.initCause(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.WrongAbiError.<init>(java.lang.Throwable, java.lang.String):void");
        }
    }

    public static void assertInitialized() {
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources != null) {
                reentrantReadWriteLock.readLock().unlock();
                return;
            }
            throw new RuntimeException("SoLoader.init() not yet called");
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    public static void doLoadLibraryBySoName(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        boolean z;
        UnsatisfiedLinkError unsatisfiedLinkError;
        boolean z2;
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources != null) {
                reentrantReadWriteLock.readLock().unlock();
                if (threadPolicy == null) {
                    threadPolicy = StrictMode.allowThreadDiskReads();
                    z = true;
                } else {
                    z = false;
                }
                if (SYSTRACE_LIBRARY_LOADING) {
                    Api18TraceUtils.beginTraceSection("SoLoader.loadLibrary[", str, "]");
                }
                int i2 = 0;
                do {
                    try {
                        sSoSourcesLock.readLock().lock();
                        int i3 = sSoSourcesVersion;
                        int i4 = 0;
                        while (true) {
                            if (i2 != 0) {
                                break;
                            }
                            SoSource[] soSourceArr = sSoSources;
                            if (i4 < soSourceArr.length) {
                                i2 = soSourceArr[i4].loadLibrary(str, i, threadPolicy);
                                if (i2 == 3 && sBackupSoSources != null) {
                                    Log.d("SoLoader", "Trying backup SoSource for " + str);
                                    UnpackingSoSource[] unpackingSoSourceArr = sBackupSoSources;
                                    int length = unpackingSoSourceArr.length;
                                    int i5 = 0;
                                    while (true) {
                                        if (i5 >= length) {
                                            break;
                                        }
                                        UnpackingSoSource unpackingSoSource = unpackingSoSourceArr[i5];
                                        synchronized (unpackingSoSource) {
                                            synchronized (unpackingSoSource.getLibraryLock(str)) {
                                                unpackingSoSource.mCorruptedLib = str;
                                                unpackingSoSource.prepare(2);
                                            }
                                        }
                                        int loadLibrary = unpackingSoSource.loadLibrary(str, i, threadPolicy);
                                        if (loadLibrary == 1) {
                                            i2 = loadLibrary;
                                            break;
                                        }
                                        i5++;
                                    }
                                } else {
                                    i4++;
                                }
                            } else {
                                break;
                            }
                        }
                        ReentrantReadWriteLock reentrantReadWriteLock2 = sSoSourcesLock;
                        reentrantReadWriteLock2.readLock().unlock();
                        if ((i & 2) == 2 && i2 == 0) {
                            reentrantReadWriteLock2.writeLock().lock();
                            ApplicationSoSource applicationSoSource = sApplicationSoSource;
                            if (applicationSoSource != null && applicationSoSource.checkAndMaybeUpdate()) {
                                sSoSourcesVersion++;
                            }
                            z2 = sSoSourcesVersion != i3;
                            reentrantReadWriteLock2.writeLock().unlock();
                            continue;
                        } else {
                            z2 = false;
                            continue;
                        }
                    } finally {
                        if (i2 == 0 || i2 == r3) {
                        }
                    }
                } while (z2);
                if (SYSTRACE_LIBRARY_LOADING) {
                    Trace.endSection();
                }
                if (z) {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
                if (i2 == 0 || i2 == 3) {
                    String str2 = GeneratedOutlineSupport.outline8("couldn't find DSO to load: ", str) + " result: " + i2;
                    Log.e("SoLoader", str2);
                    throw new UnsatisfiedLinkError(str2);
                }
                return;
            }
            Log.e("SoLoader", "Could not load: " + str + " because no SO source exists");
            throw new UnsatisfiedLinkError("couldn't find DSO to load: " + str);
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0023 A[Catch: all -> 0x0048, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0016, blocks: (B:4:0x000a, B:10:0x0019, B:11:0x0022, B:15:0x0028, B:17:0x002b, B:18:0x0030, B:22:0x0037, B:19:0x0031, B:21:0x0035, B:23:0x0039, B:24:0x0040, B:12:0x0023), top: B:29:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void init(android.content.Context r5, int r6) throws java.io.IOException {
        /*
            java.lang.Class<com.facebook.soloader.nativeloader.NativeLoader> r0 = com.facebook.soloader.nativeloader.NativeLoader.class
            android.os.StrictMode$ThreadPolicy r1 = android.os.StrictMode.allowThreadDiskWrites()
            r2 = 0
            r3 = 1
            if (r5 == 0) goto L_0x0018
            android.content.pm.ApplicationInfo r4 = r5.getApplicationInfo()     // Catch: all -> 0x0016
            int r4 = r4.flags     // Catch: all -> 0x0016
            r4 = r4 & 129(0x81, float:1.81E-43)
            if (r4 == 0) goto L_0x0018
            r4 = 1
            goto L_0x0019
        L_0x0016:
            r5 = move-exception
            goto L_0x004b
        L_0x0018:
            r4 = 0
        L_0x0019:
            com.facebook.soloader.SoLoader.isSystemApp = r4     // Catch: all -> 0x0016
            r4 = 0
            initSoLoader(r4)     // Catch: all -> 0x0016
            initSoSources(r5, r6)     // Catch: all -> 0x0016
            monitor-enter(r0)     // Catch: all -> 0x0016
            com.facebook.soloader.NativeLoaderToSoLoaderDelegate r5 = com.facebook.soloader.nativeloader.NativeLoader.sDelegate     // Catch: all -> 0x0048
            if (r5 == 0) goto L_0x0028
            r2 = 1
        L_0x0028:
            monitor-exit(r0)     // Catch: all -> 0x0016
            if (r2 != 0) goto L_0x0044
            com.facebook.soloader.NativeLoaderToSoLoaderDelegate r5 = new com.facebook.soloader.NativeLoaderToSoLoaderDelegate     // Catch: all -> 0x0016
            r5.<init>()     // Catch: all -> 0x0016
            monitor-enter(r0)     // Catch: all -> 0x0016
            com.facebook.soloader.NativeLoaderToSoLoaderDelegate r6 = com.facebook.soloader.nativeloader.NativeLoader.sDelegate     // Catch: all -> 0x0041
            if (r6 != 0) goto L_0x0039
            com.facebook.soloader.nativeloader.NativeLoader.sDelegate = r5     // Catch: all -> 0x0041
            monitor-exit(r0)     // Catch: all -> 0x0016
            goto L_0x0044
        L_0x0039:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch: all -> 0x0041
            java.lang.String r6 = "Cannot re-initialize NativeLoader."
            r5.<init>(r6)     // Catch: all -> 0x0041
            throw r5     // Catch: all -> 0x0041
        L_0x0041:
            r5 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x0016
            throw r5     // Catch: all -> 0x0016
        L_0x0044:
            android.os.StrictMode.setThreadPolicy(r1)
            return
        L_0x0048:
            r5 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x0016
            throw r5     // Catch: all -> 0x0016
        L_0x004b:
            android.os.StrictMode.setThreadPolicy(r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.init(android.content.Context, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0047 A[Catch: all -> 0x0082, TryCatch #0 {, blocks: (B:5:0x0005, B:8:0x0009, B:13:0x001d, B:15:0x0038, B:21:0x0047, B:25:0x0052, B:27:0x0061, B:30:0x006c, B:31:0x006f, B:32:0x0072, B:33:0x0077), top: B:38:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0052 A[Catch: all -> 0x0082, TryCatch #0 {, blocks: (B:5:0x0005, B:8:0x0009, B:13:0x001d, B:15:0x0038, B:21:0x0047, B:25:0x0052, B:27:0x0061, B:30:0x006c, B:31:0x006f, B:32:0x0072, B:33:0x0077), top: B:38:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized void initSoLoader(com.facebook.soloader.SoFileLoader r11) {
        /*
            java.lang.Class<com.facebook.soloader.SoLoader> r0 = com.facebook.soloader.SoLoader.class
            monitor-enter(r0)
            if (r11 == 0) goto L_0x0009
            com.facebook.soloader.SoLoader.sSoFileLoader = r11     // Catch: all -> 0x0082
            monitor-exit(r0)
            return
        L_0x0009:
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()     // Catch: all -> 0x0082
            java.lang.Class<java.lang.String> r11 = java.lang.String.class
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: all -> 0x0082
            r2 = 23
            r3 = 1
            r4 = 0
            r6 = 0
            if (r1 < r2) goto L_0x003f
            r2 = 27
            if (r1 <= r2) goto L_0x001d
            goto L_0x003f
        L_0x001d:
            java.lang.Class<java.lang.Runtime> r1 = java.lang.Runtime.class
            java.lang.String r2 = "nativeLoad"
            r7 = 3
            java.lang.Class[] r7 = new java.lang.Class[r7]     // Catch: SecurityException -> 0x0035, NoSuchMethodException -> 0x0037, all -> 0x0082
            r7[r4] = r11     // Catch: SecurityException -> 0x0035, NoSuchMethodException -> 0x0037, all -> 0x0082
            java.lang.Class<java.lang.ClassLoader> r8 = java.lang.ClassLoader.class
            r7[r3] = r8     // Catch: SecurityException -> 0x0035, NoSuchMethodException -> 0x0037, all -> 0x0082
            r8 = 2
            r7[r8] = r11     // Catch: SecurityException -> 0x0035, NoSuchMethodException -> 0x0037, all -> 0x0082
            java.lang.reflect.Method r11 = r1.getDeclaredMethod(r2, r7)     // Catch: SecurityException -> 0x0035, NoSuchMethodException -> 0x0037, all -> 0x0082
            r11.setAccessible(r3)     // Catch: SecurityException -> 0x0035, NoSuchMethodException -> 0x0037, all -> 0x0082
            goto L_0x0040
        L_0x0035:
            r11 = move-exception
            goto L_0x0038
        L_0x0037:
            r11 = move-exception
        L_0x0038:
            java.lang.String r1 = "SoLoader"
            java.lang.String r2 = "Cannot get nativeLoad method"
            android.util.Log.w(r1, r2, r11)     // Catch: all -> 0x0082
        L_0x003f:
            r11 = r6
        L_0x0040:
            if (r11 == 0) goto L_0x0044
            r2 = 1
            goto L_0x0045
        L_0x0044:
            r2 = 0
        L_0x0045:
            if (r2 == 0) goto L_0x004d
            java.lang.String r1 = com.facebook.soloader.SoLoader.Api14Utils.getClassLoaderLdLoadLibrary()     // Catch: all -> 0x0082
            r3 = r1
            goto L_0x004e
        L_0x004d:
            r3 = r6
        L_0x004e:
            if (r3 != 0) goto L_0x0052
            r4 = r6
            goto L_0x0077
        L_0x0052:
            java.lang.String r1 = ":"
            java.lang.String[] r6 = r3.split(r1)     // Catch: all -> 0x0082
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch: all -> 0x0082
            int r8 = r6.length     // Catch: all -> 0x0082
            r7.<init>(r8)     // Catch: all -> 0x0082
            int r8 = r6.length     // Catch: all -> 0x0082
        L_0x005f:
            if (r4 >= r8) goto L_0x0072
            r9 = r6[r4]     // Catch: all -> 0x0082
            java.lang.String r10 = "!"
            boolean r10 = r9.contains(r10)     // Catch: all -> 0x0082
            if (r10 == 0) goto L_0x006c
            goto L_0x006f
        L_0x006c:
            r7.add(r9)     // Catch: all -> 0x0082
        L_0x006f:
            int r4 = r4 + 1
            goto L_0x005f
        L_0x0072:
            java.lang.String r1 = android.text.TextUtils.join(r1, r7)     // Catch: all -> 0x0082
            r4 = r1
        L_0x0077:
            com.facebook.soloader.SoLoader$1 r7 = new com.facebook.soloader.SoLoader$1     // Catch: all -> 0x0082
            r1 = r7
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch: all -> 0x0082
            com.facebook.soloader.SoLoader.sSoFileLoader = r7     // Catch: all -> 0x0082
            monitor-exit(r0)
            return
        L_0x0082:
            r11 = move-exception
            monitor-exit(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.initSoLoader(com.facebook.soloader.SoFileLoader):void");
    }

    public static void initSoSources(Context context, int i) throws IOException {
        int i2;
        ApkSoSource apkSoSource;
        sSoSourcesLock.writeLock().lock();
        try {
            if (sSoSources == null) {
                Log.d("SoLoader", "init start");
                sFlags = i;
                ArrayList arrayList = new ArrayList();
                String str = System.getenv("LD_LIBRARY_PATH");
                if (str == null) {
                    str = "/vendor/lib:/system/lib";
                }
                String[] split = str.split(":");
                int i3 = 0;
                for (int i4 = 0; i4 < split.length; i4++) {
                    Log.d("SoLoader", "adding system library source: " + split[i4]);
                    arrayList.add(new DirectorySoSource(new File(split[i4]), 2));
                }
                if (context != null) {
                    if ((i & 1) != 0) {
                        sBackupSoSources = null;
                        Log.d("SoLoader", "adding exo package source: lib-main");
                        arrayList.add(0, new ExoSoSource(context, "lib-main"));
                    } else {
                        if (isSystemApp) {
                            i2 = 0;
                        } else {
                            sApplicationSoSource = new ApplicationSoSource(context, 0);
                            Log.d("SoLoader", "adding application source: " + sApplicationSoSource.toString());
                            arrayList.add(0, sApplicationSoSource);
                            i2 = 1;
                        }
                        if ((sFlags & 8) != 0) {
                            sBackupSoSources = null;
                        } else {
                            File file = new File(context.getApplicationInfo().sourceDir);
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(new ApkSoSource(context, file, "lib-main", i2));
                            Log.d("SoLoader", "adding backup source from : " + apkSoSource.toString());
                            if (context.getApplicationInfo().splitSourceDirs != null) {
                                Log.d("SoLoader", "adding backup sources from split apks");
                                int i5 = 0;
                                for (String str2 : context.getApplicationInfo().splitSourceDirs) {
                                    File file2 = new File(str2);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("lib-");
                                    i5++;
                                    sb.append(i5);
                                    ApkSoSource apkSoSource2 = new ApkSoSource(context, file2, sb.toString(), i2);
                                    Log.d("SoLoader", "adding backup source: " + apkSoSource2.toString());
                                    arrayList2.add(apkSoSource2);
                                }
                            }
                            sBackupSoSources = (UnpackingSoSource[]) arrayList2.toArray(new UnpackingSoSource[arrayList2.size()]);
                            arrayList.addAll(0, arrayList2);
                        }
                    }
                }
                SoSource[] soSourceArr = (SoSource[]) arrayList.toArray(new SoSource[arrayList.size()]);
                ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
                reentrantReadWriteLock.writeLock().lock();
                if ((sFlags & 2) != 0) {
                    i3 = 1;
                }
                reentrantReadWriteLock.writeLock().unlock();
                int length = soSourceArr.length;
                while (true) {
                    int i6 = length - 1;
                    if (length <= 0) {
                        break;
                    }
                    Log.d("SoLoader", "Preparing SO source: " + soSourceArr[i6]);
                    soSourceArr[i6].prepare(i3);
                    length = i6;
                }
                sSoSources = soSourceArr;
                sSoSourcesVersion++;
                Log.d("SoLoader", "init finish: " + sSoSources.length + " SO sources prepared");
            }
        } finally {
            Log.d("SoLoader", "init exiting");
            sSoSourcesLock.writeLock().unlock();
        }
    }

    public static boolean loadLibrary(String str) {
        boolean z;
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    assertInitialized();
                } else {
                    synchronized (SoLoader.class) {
                        z = !sLoadedLibraries.contains(str);
                        if (z) {
                            System.loadLibrary(str);
                        }
                    }
                    reentrantReadWriteLock.readLock().unlock();
                    return z;
                }
            }
            reentrantReadWriteLock.readLock().unlock();
            return loadLibraryBySoName(System.mapLibraryName(str), str, null, 2, null);
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    public static boolean loadLibraryBySoName(String str, String str2, String str3, int i, StrictMode.ThreadPolicy threadPolicy) {
        Object obj;
        if (!TextUtils.isEmpty(str2) && sLoadedAndMergedLibraries.contains(str2)) {
            return false;
        }
        synchronized (SoLoader.class) {
            HashSet<String> hashSet = sLoadedLibraries;
            if (hashSet.contains(str)) {
                return false;
            }
            Map<String, Object> map = sLoadingLibraries;
            if (map.containsKey(str)) {
                obj = map.get(str);
            } else {
                Object obj2 = new Object();
                map.put(str, obj2);
                obj = obj2;
            }
            synchronized (obj) {
                synchronized (SoLoader.class) {
                    if (hashSet.contains(str)) {
                        return false;
                    }
                    try {
                        Log.d("SoLoader", "About to load: " + str);
                        doLoadLibraryBySoName(str, i, threadPolicy);
                        synchronized (SoLoader.class) {
                            Log.d("SoLoader", "Loaded: " + str);
                            hashSet.add(str);
                        }
                        if ((i & 16) == 0 && !TextUtils.isEmpty(str2)) {
                            sLoadedAndMergedLibraries.contains(str2);
                        }
                        return true;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (UnsatisfiedLinkError e2) {
                        String message = e2.getMessage();
                        if (message == null || !message.contains("unexpected e_machine:")) {
                            throw e2;
                        }
                        throw new WrongAbiError(e2, message.substring(message.lastIndexOf("unexpected e_machine:")));
                    }
                }
            }
        }
    }

    public static File unpackLibraryBySoName(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        int i = 0;
        while (true) {
            try {
                SoSource[] soSourceArr = sSoSources;
                if (i < soSourceArr.length) {
                    File unpackLibrary = soSourceArr[i].unpackLibrary(str);
                    if (unpackLibrary != null) {
                        return unpackLibrary;
                    }
                    i++;
                } else {
                    sSoSourcesLock.readLock().unlock();
                    throw new FileNotFoundException(str);
                }
            } finally {
                sSoSourcesLock.readLock().unlock();
            }
        }
    }
}
