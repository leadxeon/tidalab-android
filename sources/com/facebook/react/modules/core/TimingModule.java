package com.facebook.react.modules.core;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.JavaTimerManager;
import java.util.Objects;
@ReactModule(name = TimingModule.NAME)
/* loaded from: classes.dex */
public final class TimingModule extends ReactContextBaseJavaModule implements LifecycleEventListener, HeadlessJsTaskEventListener {
    public static final String NAME = "Timing";
    private final JavaTimerManager mJavaTimerManager;

    /* loaded from: classes.dex */
    public class BridgeTimerManager implements JavaScriptTimerManager {
        public BridgeTimerManager() {
        }
    }

    public TimingModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager) {
        super(reactApplicationContext);
        this.mJavaTimerManager = new JavaTimerManager(reactApplicationContext, new BridgeTimerManager(), ReactChoreographer.getInstance(), devSupportManager);
    }

    @ReactMethod
    public void createTimer(int i, int i2, double d, boolean z) {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn;
        JavaTimerManager javaTimerManager = this.mJavaTimerManager;
        Objects.requireNonNull(javaTimerManager);
        long currentTimeMillis = System.currentTimeMillis();
        long j = (long) d;
        if (javaTimerManager.mDevSupportManager.getDevSupportEnabled() && Math.abs(j - currentTimeMillis) > 60000 && (reactApplicationContextIfActiveOrWarn = TimingModule.this.getReactApplicationContextIfActiveOrWarn()) != null) {
            ((JSTimers) reactApplicationContextIfActiveOrWarn.getJSModule(JSTimers.class)).emitTimeDriftWarning("Debugger and device times have drifted by more than 60s. Please correct this by running adb shell \"date `date +%m%d%H%M%Y.%S`\" on your debugger machine.");
        }
        long max = Math.max(0L, (j - currentTimeMillis) + i2);
        if (i2 != 0 || z) {
            JavaTimerManager.Timer timer = new JavaTimerManager.Timer(i, (System.nanoTime() / 1000000) + max, (int) max, z, null);
            synchronized (javaTimerManager.mTimerGuard) {
                javaTimerManager.mTimers.add(timer);
                javaTimerManager.mTimerIdsToTimers.put(i, timer);
            }
            return;
        }
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        ReactApplicationContext reactApplicationContextIfActiveOrWarn2 = TimingModule.this.getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn2 != null) {
            ((JSTimers) reactApplicationContextIfActiveOrWarn2.getJSModule(JSTimers.class)).callTimers(createArray);
        }
    }

    @ReactMethod
    public void deleteTimer(int i) {
        JavaTimerManager javaTimerManager = this.mJavaTimerManager;
        synchronized (javaTimerManager.mTimerGuard) {
            JavaTimerManager.Timer timer = javaTimerManager.mTimerIdsToTimers.get(i);
            if (timer != null) {
                javaTimerManager.mTimerIdsToTimers.remove(i);
                javaTimerManager.mTimers.remove(timer);
            }
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
        HeadlessJsTaskContext.getInstance(getReactApplicationContext()).mHeadlessJsTaskEventListeners.add(this);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        HeadlessJsTaskContext.getInstance(getReactApplicationContext()).mHeadlessJsTaskEventListeners.remove(this);
        JavaTimerManager javaTimerManager = this.mJavaTimerManager;
        javaTimerManager.clearFrameCallback();
        if (javaTimerManager.mFrameIdleCallbackPosted) {
            javaTimerManager.mReactChoreographer.removeFrameCallback$enumunboxing$(5, javaTimerManager.mIdleFrameCallback);
            javaTimerManager.mFrameIdleCallbackPosted = false;
        }
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskEventListener
    public void onHeadlessJsTaskFinish(int i) {
        JavaTimerManager javaTimerManager = this.mJavaTimerManager;
        if (!(HeadlessJsTaskContext.getInstance(javaTimerManager.mReactApplicationContext).mActiveTasks.size() > 0)) {
            javaTimerManager.isRunningTasks.set(false);
            javaTimerManager.clearFrameCallback();
            javaTimerManager.maybeIdleCallback();
        }
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskEventListener
    public void onHeadlessJsTaskStart(int i) {
        JavaTimerManager javaTimerManager = this.mJavaTimerManager;
        if (!javaTimerManager.isRunningTasks.getAndSet(true)) {
            if (!javaTimerManager.mFrameCallbackPosted) {
                javaTimerManager.mReactChoreographer.postFrameCallback$enumunboxing$(4, javaTimerManager.mTimerFrameCallback);
                javaTimerManager.mFrameCallbackPosted = true;
            }
            javaTimerManager.maybeSetChoreographerIdleCallback();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        JavaTimerManager javaTimerManager = this.mJavaTimerManager;
        javaTimerManager.clearFrameCallback();
        javaTimerManager.maybeIdleCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        JavaTimerManager javaTimerManager = this.mJavaTimerManager;
        javaTimerManager.isPaused.set(true);
        javaTimerManager.clearFrameCallback();
        javaTimerManager.maybeIdleCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        JavaTimerManager javaTimerManager = this.mJavaTimerManager;
        javaTimerManager.isPaused.set(false);
        if (!javaTimerManager.mFrameCallbackPosted) {
            javaTimerManager.mReactChoreographer.postFrameCallback$enumunboxing$(4, javaTimerManager.mTimerFrameCallback);
            javaTimerManager.mFrameCallbackPosted = true;
        }
        javaTimerManager.maybeSetChoreographerIdleCallback();
    }

    @ReactMethod
    public void setSendIdleEvents(final boolean z) {
        final JavaTimerManager javaTimerManager = this.mJavaTimerManager;
        synchronized (javaTimerManager.mIdleCallbackGuard) {
            javaTimerManager.mSendIdleEvents = z;
        }
        UiThreadUtil.runOnUiThread(
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: INVOKE  
              (wrap: java.lang.Runnable : 0x000a: CONSTRUCTOR  (r1v1 java.lang.Runnable A[REMOVE]) = 
              (r0v0 'javaTimerManager' com.facebook.react.modules.core.JavaTimerManager A[DONT_INLINE])
              (r3v0 'z' boolean A[DONT_INLINE])
             call: com.facebook.react.modules.core.JavaTimerManager.2.<init>(com.facebook.react.modules.core.JavaTimerManager, boolean):void type: CONSTRUCTOR)
             type: STATIC call: com.facebook.react.bridge.UiThreadUtil.runOnUiThread(java.lang.Runnable):void in method: com.facebook.react.modules.core.TimingModule.setSendIdleEvents(boolean):void, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:278)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:241)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:90)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:267)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:260)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:369)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:304)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:270)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.facebook.react.modules.core.JavaTimerManager, state: NOT_LOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:299)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:676)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:386)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:140)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:116)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:996)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:807)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:390)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:271)
            	... 15 more
            */
        /*
            this = this;
            com.facebook.react.modules.core.JavaTimerManager r0 = r2.mJavaTimerManager
            java.lang.Object r1 = r0.mIdleCallbackGuard
            monitor-enter(r1)
            r0.mSendIdleEvents = r3     // Catch: all -> 0x0011
            monitor-exit(r1)     // Catch: all -> 0x0011
            com.facebook.react.modules.core.JavaTimerManager$2 r1 = new com.facebook.react.modules.core.JavaTimerManager$2
            r1.<init>(r0, r3)
            com.facebook.react.bridge.UiThreadUtil.runOnUiThread(r1)
            return
        L_0x0011:
            r3 = move-exception
            monitor-exit(r1)     // Catch: all -> 0x0011
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.core.TimingModule.setSendIdleEvents(boolean):void");
    }
}
