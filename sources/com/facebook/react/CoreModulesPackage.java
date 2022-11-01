package com.facebook.react;

import android.os.Trace;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.devsupport.LogBoxModule;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.core.HeadlessJsTaskSupportModule;
import com.facebook.react.modules.core.TimingModule;
import com.facebook.react.modules.debug.DevSettingsModule;
import com.facebook.react.modules.debug.SourceCodeModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.modules.systeminfo.AndroidInfoModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.HashMap;
import java.util.Map;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public class CoreModulesPackage extends TurboReactPackage implements ReactPackageLogger {
    public final DefaultHardwareBackBtnHandler mHardwareBackBtnHandler;
    public final boolean mLazyViewManagersEnabled;
    public final int mMinTimeLeftInFrameForNonBatchedOperationMs;
    public final ReactInstanceManager mReactInstanceManager;

    /* renamed from: com.facebook.react.CoreModulesPackage$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements UIManagerModule.ViewManagerResolver {
        public AnonymousClass2() {
        }
    }

    public CoreModulesPackage(ReactInstanceManager reactInstanceManager, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, boolean z, int i) {
        this.mReactInstanceManager = reactInstanceManager;
        this.mHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
        this.mLazyViewManagersEnabled = z;
        this.mMinTimeLeftInFrameForNonBatchedOperationMs = i;
    }

    public final UIManagerModule createUIManager(ReactApplicationContext reactApplicationContext) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_START);
        Trace.beginSection("createUIManagerModule");
        try {
            if (this.mLazyViewManagersEnabled) {
                return new UIManagerModule(reactApplicationContext, new AnonymousClass2(), this.mMinTimeLeftInFrameForNonBatchedOperationMs);
            }
            return new UIManagerModule(reactApplicationContext, this.mReactInstanceManager.getOrCreateViewManagers(reactApplicationContext), this.mMinTimeLeftInFrameForNonBatchedOperationMs);
        } finally {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_END);
        }
    }

    @Override // com.facebook.react.ReactPackageLogger
    public void endProcessPackage() {
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_END);
    }

    @Override // com.facebook.react.TurboReactPackage
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2013505529:
                if (str.equals(LogBoxModule.NAME)) {
                    c = 0;
                    break;
                }
                break;
            case -1789797270:
                if (str.equals(TimingModule.NAME)) {
                    c = 1;
                    break;
                }
                break;
            case -1633589448:
                if (str.equals(DevSettingsModule.NAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1520650172:
                if (str.equals(DeviceInfoModule.NAME)) {
                    c = 3;
                    break;
                }
                break;
            case -1037217463:
                if (str.equals(DeviceEventManagerModule.NAME)) {
                    c = 4;
                    break;
                }
                break;
            case -790603268:
                if (str.equals(AndroidInfoModule.NAME)) {
                    c = 5;
                    break;
                }
                break;
            case 512434409:
                if (str.equals(ExceptionsManagerModule.NAME)) {
                    c = 6;
                    break;
                }
                break;
            case 881516744:
                if (str.equals(SourceCodeModule.NAME)) {
                    c = 7;
                    break;
                }
                break;
            case 1256514152:
                if (str.equals(HeadlessJsTaskSupportModule.NAME)) {
                    c = '\b';
                    break;
                }
                break;
            case 1861242489:
                if (str.equals(UIManagerModule.NAME)) {
                    c = '\t';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new LogBoxModule(reactApplicationContext, this.mReactInstanceManager.mDevSupportManager);
            case 1:
                return new TimingModule(reactApplicationContext, this.mReactInstanceManager.mDevSupportManager);
            case 2:
                return new DevSettingsModule(reactApplicationContext, this.mReactInstanceManager.mDevSupportManager);
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                return new DeviceInfoModule(reactApplicationContext);
            case 4:
                return new DeviceEventManagerModule(reactApplicationContext, this.mHardwareBackBtnHandler);
            case 5:
                return new AndroidInfoModule(reactApplicationContext);
            case 6:
                return new ExceptionsManagerModule(this.mReactInstanceManager.mDevSupportManager);
            case 7:
                return new SourceCodeModule(reactApplicationContext);
            case '\b':
                return new HeadlessJsTaskSupportModule(reactApplicationContext);
            case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                return createUIManager(reactApplicationContext);
            default:
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("In CoreModulesPackage, could not find Native module for ", str));
        }
    }

    @Override // com.facebook.react.TurboReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            return (ReactModuleInfoProvider) Class.forName("com.facebook.react.CoreModulesPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            Class[] clsArr = {AndroidInfoModule.class, DeviceEventManagerModule.class, DeviceInfoModule.class, DevSettingsModule.class, ExceptionsManagerModule.class, LogBoxModule.class, HeadlessJsTaskSupportModule.class, SourceCodeModule.class, TimingModule.class, UIManagerModule.class};
            final HashMap hashMap = new HashMap();
            for (int i = 0; i < 10; i++) {
                Class cls = clsArr[i];
                ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                hashMap.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.hasConstants(), reactModule.isCxxModule(), false));
            }
            return new ReactModuleInfoProvider(this) { // from class: com.facebook.react.CoreModulesPackage.1
                @Override // com.facebook.react.module.model.ReactModuleInfoProvider
                public Map<String, ReactModuleInfo> getReactModuleInfos() {
                    return hashMap;
                }
            };
        } catch (IllegalAccessException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e2);
        }
    }

    @Override // com.facebook.react.ReactPackageLogger
    public void startProcessPackage() {
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_START);
    }
}
