package com.facebook.react.shell;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule;
import com.facebook.react.modules.appearance.AppearanceModule;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.blob.BlobModule;
import com.facebook.react.modules.blob.FileReaderModule;
import com.facebook.react.modules.camera.CameraRollManager;
import com.facebook.react.modules.camera.ImageEditingManager;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.datepicker.DatePickerDialogModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.modules.i18nmanager.I18nManagerModule;
import com.facebook.react.modules.image.ImageLoaderModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.share.ShareModule;
import com.facebook.react.modules.sound.SoundManagerModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.storage.AsyncStorageModule;
import com.facebook.react.modules.timepicker.TimePickerDialogModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.art.ARTRenderableViewManager;
import com.facebook.react.views.art.ARTSurfaceViewManager;
import com.facebook.react.views.checkbox.ReactCheckBoxManager;
import com.facebook.react.views.drawer.ReactDrawerLayoutManager;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.picker.ReactDialogPickerManager;
import com.facebook.react.views.picker.ReactDropdownPickerManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollContainerViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.slider.ReactSliderManager;
import com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager;
import com.facebook.react.views.switchview.ReactSwitchManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.text.ReactVirtualTextViewManager;
import com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageViewManager;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.view.ReactViewManager;
import com.facebook.react.views.viewpager.ReactViewPagerManager;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public class MainReactPackage extends TurboReactPackage {
    @Override // com.facebook.react.TurboReactPackage, com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ARTRenderableViewManager.createARTGroupViewManager());
        arrayList.add(ARTRenderableViewManager.createARTShapeViewManager());
        arrayList.add(ARTRenderableViewManager.createARTTextViewManager());
        arrayList.add(new ReactCheckBoxManager());
        arrayList.add(new ReactDialogPickerManager());
        arrayList.add(new ReactDrawerLayoutManager());
        arrayList.add(new ReactDropdownPickerManager());
        arrayList.add(new ReactHorizontalScrollViewManager());
        arrayList.add(new ReactHorizontalScrollContainerViewManager());
        arrayList.add(new ReactProgressBarViewManager());
        arrayList.add(new ReactScrollViewManager());
        arrayList.add(new ReactSliderManager());
        arrayList.add(new ReactSwitchManager());
        arrayList.add(new SwipeRefreshLayoutManager());
        arrayList.add(new ARTSurfaceViewManager());
        arrayList.add(new FrescoBasedReactTextInlineImageViewManager());
        arrayList.add(new ReactImageManager());
        arrayList.add(new ReactModalHostManager());
        arrayList.add(new ReactRawTextManager());
        arrayList.add(new ReactTextInputManager());
        arrayList.add(new ReactTextViewManager());
        arrayList.add(new ReactViewManager());
        arrayList.add(new ReactViewPagerManager());
        arrayList.add(new ReactVirtualTextViewManager());
        return arrayList;
    }

    @Override // com.facebook.react.TurboReactPackage
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2115067288:
                if (str.equals(ToastModule.NAME)) {
                    c = 0;
                    break;
                }
                break;
            case -2033388651:
                if (str.equals(AsyncStorageModule.NAME)) {
                    c = 1;
                    break;
                }
                break;
            case -1962922905:
                if (str.equals(ImageStoreManager.NAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1850625090:
                if (str.equals(SoundManagerModule.NAME)) {
                    c = 3;
                    break;
                }
                break;
            case -1654566518:
                if (str.equals(DialogModule.NAME)) {
                    c = 4;
                    break;
                }
                break;
            case -1505215509:
                if (str.equals(CameraRollManager.NAME)) {
                    c = 5;
                    break;
                }
                break;
            case -1399423980:
                if (str.equals(TimePickerDialogModule.FRAGMENT_TAG)) {
                    c = 6;
                    break;
                }
                break;
            case -1344126773:
                if (str.equals(FileReaderModule.NAME)) {
                    c = 7;
                    break;
                }
                break;
            case -1062061717:
                if (str.equals(PermissionsModule.NAME)) {
                    c = '\b';
                    break;
                }
                break;
            case -657277650:
                if (str.equals(ImageLoaderModule.NAME)) {
                    c = '\t';
                    break;
                }
                break;
            case -570370161:
                if (str.equals(I18nManagerModule.NAME)) {
                    c = '\n';
                    break;
                }
                break;
            case -504784764:
                if (str.equals(AppearanceModule.NAME)) {
                    c = 11;
                    break;
                }
                break;
            case -457866500:
                if (str.equals(AccessibilityInfoModule.NAME)) {
                    c = '\f';
                    break;
                }
                break;
            case -382654004:
                if (str.equals(StatusBarModule.NAME)) {
                    c = '\r';
                    break;
                }
                break;
            case -254310125:
                if (str.equals("WebSocketModule")) {
                    c = 14;
                    break;
                }
                break;
            case 163245714:
                if (str.equals(FrescoModule.NAME)) {
                    c = 15;
                    break;
                }
                break;
            case 174691539:
                if (str.equals(DatePickerDialogModule.FRAGMENT_TAG)) {
                    c = 16;
                    break;
                }
                break;
            case 283572496:
                if (str.equals(ImageEditingManager.NAME)) {
                    c = 17;
                    break;
                }
                break;
            case 403570038:
                if (str.equals(ClipboardModule.NAME)) {
                    c = 18;
                    break;
                }
                break;
            case 563961875:
                if (str.equals(IntentModule.NAME)) {
                    c = 19;
                    break;
                }
                break;
            case 1221389072:
                if (str.equals(AppStateModule.NAME)) {
                    c = 20;
                    break;
                }
                break;
            case 1515242260:
                if (str.equals(NetworkingModule.NAME)) {
                    c = 21;
                    break;
                }
                break;
            case 1547941001:
                if (str.equals(BlobModule.NAME)) {
                    c = 22;
                    break;
                }
                break;
            case 1555425035:
                if (str.equals(ShareModule.NAME)) {
                    c = 23;
                    break;
                }
                break;
            case 1721274886:
                if (str.equals(NativeAnimatedModule.NAME)) {
                    c = 24;
                    break;
                }
                break;
            case 1922110066:
                if (str.equals(VibrationModule.NAME)) {
                    c = 25;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new ToastModule(reactApplicationContext);
            case 1:
                return new AsyncStorageModule(reactApplicationContext);
            case 2:
                return new ImageStoreManager(reactApplicationContext);
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                return new SoundManagerModule(reactApplicationContext);
            case 4:
                return new DialogModule(reactApplicationContext);
            case 5:
                return new CameraRollManager(reactApplicationContext);
            case 6:
                return new TimePickerDialogModule(reactApplicationContext);
            case 7:
                return new FileReaderModule(reactApplicationContext);
            case '\b':
                return new PermissionsModule(reactApplicationContext);
            case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                return new ImageLoaderModule(reactApplicationContext);
            case '\n':
                return new I18nManagerModule(reactApplicationContext);
            case 11:
                return new AppearanceModule(reactApplicationContext);
            case '\f':
                return new AccessibilityInfoModule(reactApplicationContext);
            case '\r':
                return new StatusBarModule(reactApplicationContext);
            case 14:
                return new WebSocketModule(reactApplicationContext);
            case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                return new FrescoModule(reactApplicationContext, true, null);
            case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                return new DatePickerDialogModule(reactApplicationContext);
            case 17:
                return new ImageEditingManager(reactApplicationContext);
            case 18:
                return new ClipboardModule(reactApplicationContext);
            case 19:
                return new IntentModule(reactApplicationContext);
            case 20:
                return new AppStateModule(reactApplicationContext);
            case 21:
                return new NetworkingModule(reactApplicationContext);
            case 22:
                return new BlobModule(reactApplicationContext);
            case 23:
                return new ShareModule(reactApplicationContext);
            case 24:
                return new NativeAnimatedModule(reactApplicationContext);
            case 25:
                return new VibrationModule(reactApplicationContext);
            default:
                return null;
        }
    }

    @Override // com.facebook.react.TurboReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            return (ReactModuleInfoProvider) Class.forName("com.facebook.react.MainReactPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            Class[] clsArr = {AccessibilityInfoModule.class, AppearanceModule.class, AppStateModule.class, BlobModule.class, FileReaderModule.class, AsyncStorageModule.class, CameraRollManager.class, ClipboardModule.class, DatePickerDialogModule.class, DialogModule.class, FrescoModule.class, I18nManagerModule.class, ImageEditingManager.class, ImageLoaderModule.class, ImageStoreManager.class, IntentModule.class, NativeAnimatedModule.class, NetworkingModule.class, PermissionsModule.class, ShareModule.class, StatusBarModule.class, SoundManagerModule.class, TimePickerDialogModule.class, ToastModule.class, VibrationModule.class, WebSocketModule.class};
            final HashMap hashMap = new HashMap();
            for (int i = 0; i < 26; i++) {
                Class cls = clsArr[i];
                ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                hashMap.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.hasConstants(), reactModule.isCxxModule(), false));
            }
            return new ReactModuleInfoProvider(this) { // from class: com.facebook.react.shell.MainReactPackage.1
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
}
