package com.facebook.react.bridge;

import androidx.recyclerview.R$dimen;
import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Provider;
@DoNotStrip
/* loaded from: classes.dex */
public class ModuleHolder {
    private static final AtomicInteger sInstanceKeyCounter = new AtomicInteger(1);
    private boolean mInitializable;
    private final int mInstanceKey = sInstanceKeyCounter.getAndIncrement();
    private boolean mIsCreating;
    private boolean mIsInitializing;
    private NativeModule mModule;
    private final String mName;
    private Provider<? extends NativeModule> mProvider;
    private final ReactModuleInfo mReactModuleInfo;

    public ModuleHolder(ReactModuleInfo reactModuleInfo, Provider<? extends NativeModule> provider) {
        this.mName = reactModuleInfo.mName;
        this.mProvider = provider;
        this.mReactModuleInfo = reactModuleInfo;
        if (reactModuleInfo.mNeedsEagerInit) {
            this.mModule = create();
        }
    }

    private NativeModule create() {
        boolean z = true;
        SoftAssertions.assertCondition(this.mModule == null, "Creating an already created module.");
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, this.mName, this.mInstanceKey);
        int i = PrinterHolder.$r8$clinit;
        DebugOverlayTag debugOverlayTag = ReactDebugOverlayTags.NATIVE_MODULE;
        try {
            Provider<? extends NativeModule> provider = this.mProvider;
            R$dimen.assertNotNull(provider);
            NativeModule nativeModule = (NativeModule) provider.get();
            this.mProvider = null;
            synchronized (this) {
                this.mModule = nativeModule;
                if (!this.mInitializable || this.mIsInitializing) {
                    z = false;
                }
            }
            if (z) {
                doInitialize(nativeModule);
            }
            return nativeModule;
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    private void doInitialize(NativeModule nativeModule) {
        boolean z;
        ReactMarker.logMarker(ReactMarkerConstants.INITIALIZE_MODULE_START, this.mName, this.mInstanceKey);
        try {
            synchronized (this) {
                z = true;
                if (!this.mInitializable || this.mIsInitializing) {
                    z = false;
                } else {
                    this.mIsInitializing = true;
                }
            }
            if (z) {
                nativeModule.initialize();
                synchronized (this) {
                    this.mIsInitializing = false;
                }
            }
        } finally {
            ReactMarker.logMarker(ReactMarkerConstants.INITIALIZE_MODULE_END, this.mName, this.mInstanceKey);
        }
    }

    public synchronized void destroy() {
        NativeModule nativeModule = this.mModule;
        if (nativeModule != null) {
            nativeModule.onCatalystInstanceDestroy();
        }
    }

    public boolean getCanOverrideExistingModule() {
        return this.mReactModuleInfo.mCanOverrideExistingModule;
    }

    public String getClassName() {
        return this.mReactModuleInfo.mClassName;
    }

    public boolean getHasConstants() {
        return this.mReactModuleInfo.mHasConstants;
    }

    @DoNotStrip
    public NativeModule getModule() {
        NativeModule nativeModule;
        NativeModule nativeModule2;
        synchronized (this) {
            NativeModule nativeModule3 = this.mModule;
            if (nativeModule3 != null) {
                return nativeModule3;
            }
            boolean z = true;
            if (!this.mIsCreating) {
                this.mIsCreating = true;
            } else {
                z = false;
            }
            if (z) {
                NativeModule create = create();
                synchronized (this) {
                    this.mIsCreating = false;
                    notifyAll();
                }
                return create;
            }
            synchronized (this) {
                while (true) {
                    nativeModule = this.mModule;
                    if (nativeModule != null || !this.mIsCreating) {
                        break;
                    }
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                }
                R$dimen.assertNotNull(nativeModule);
                nativeModule2 = nativeModule;
            }
            return nativeModule2;
        }
    }

    @DoNotStrip
    public String getName() {
        return this.mName;
    }

    public synchronized boolean hasInstance() {
        return this.mModule != null;
    }

    public boolean isCxxModule() {
        return this.mReactModuleInfo.mIsCxxModule;
    }

    public boolean isTurboModule() {
        return this.mReactModuleInfo.mIsTurboModule;
    }

    public void markInitializable() {
        boolean z;
        NativeModule nativeModule;
        synchronized (this) {
            z = true;
            this.mInitializable = true;
            boolean z2 = false;
            if (this.mModule != null) {
                if (!this.mIsInitializing) {
                    z2 = true;
                }
                R$dimen.assertCondition(z2);
                nativeModule = this.mModule;
            } else {
                nativeModule = null;
                z = false;
            }
        }
        if (z) {
            doInitialize(nativeModule);
        }
    }

    public ModuleHolder(NativeModule nativeModule) {
        this.mName = nativeModule.getName();
        this.mReactModuleInfo = new ReactModuleInfo(nativeModule.getName(), nativeModule.getClass().getSimpleName(), nativeModule.canOverrideExistingModule(), true, true, CxxModuleWrapper.class.isAssignableFrom(nativeModule.getClass()), TurboModule.class.isAssignableFrom(nativeModule.getClass()));
        this.mModule = nativeModule;
        int i = PrinterHolder.$r8$clinit;
        DebugOverlayTag debugOverlayTag = ReactDebugOverlayTags.NATIVE_MODULE;
    }
}
