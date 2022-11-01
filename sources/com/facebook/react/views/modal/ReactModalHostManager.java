package com.facebook.react.views.modal;

import android.content.DialogInterface;
import android.graphics.Point;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder$Builder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.ModalHostViewManagerDelegate;
import com.facebook.react.views.modal.ReactModalHostView;
import java.util.Map;
@ReactModule(name = ReactModalHostManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactModalHostManager extends ViewGroupManager<ReactModalHostView> {
    public static final String REACT_CLASS = "RCTModalHostView";
    private final ViewManagerDelegate<ReactModalHostView> mDelegate = new ModalHostViewManagerDelegate(this);

    /* renamed from: com.facebook.react.views.modal.ReactModalHostManager$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements ReactModalHostView.OnRequestCloseListener {
        public final /* synthetic */ EventDispatcher val$dispatcher;
        public final /* synthetic */ ReactModalHostView val$view;

        public AnonymousClass1(ReactModalHostManager reactModalHostManager, EventDispatcher eventDispatcher, ReactModalHostView reactModalHostView) {
            this.val$dispatcher = eventDispatcher;
            this.val$view = reactModalHostView;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate<ReactModalHostView> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder$Builder builder = R$style.builder();
        builder.put("topRequestClose", R$style.of("registrationName", "onRequestClose"));
        builder.put("topShow", R$style.of("registrationName", "onShow"));
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ModalHostShadowNode.class;
    }

    public void setAnimated(ReactModalHostView reactModalHostView, boolean z) {
    }

    public void setIdentifier(ReactModalHostView reactModalHostView, int i) {
    }

    public void setPresentationStyle(ReactModalHostView reactModalHostView, String str) {
    }

    public void setSupportedOrientations(ReactModalHostView reactModalHostView, ReadableArray readableArray) {
    }

    public void addEventEmitters(ThemedReactContext themedReactContext, final ReactModalHostView reactModalHostView) {
        final EventDispatcher eventDispatcher = ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        reactModalHostView.setOnRequestCloseListener(new AnonymousClass1(this, eventDispatcher, reactModalHostView));
        reactModalHostView.setOnShowListener(new DialogInterface.OnShowListener(this) { // from class: com.facebook.react.views.modal.ReactModalHostManager.2
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                eventDispatcher.dispatchEvent(new ShowEvent(reactModalHostView.getId()));
            }
        });
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new ModalHostShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactModalHostView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactModalHostView(themedReactContext);
    }

    public void onAfterUpdateTransaction(ReactModalHostView reactModalHostView) {
        super.onAfterUpdateTransaction((ReactModalHostManager) reactModalHostView);
        reactModalHostView.showOrUpdate();
    }

    public void onDropViewInstance(ReactModalHostView reactModalHostView) {
        super.onDropViewInstance((ReactModalHostManager) reactModalHostView);
        ((ReactContext) reactModalHostView.getContext()).removeLifecycleEventListener(reactModalHostView);
        reactModalHostView.dismiss();
    }

    @ReactProp(name = "animationType")
    public void setAnimationType(ReactModalHostView reactModalHostView, String str) {
        if (str != null) {
            reactModalHostView.setAnimationType(str);
        }
    }

    @ReactProp(name = "hardwareAccelerated")
    public void setHardwareAccelerated(ReactModalHostView reactModalHostView, boolean z) {
        reactModalHostView.setHardwareAccelerated(z);
    }

    @ReactProp(name = "statusBarTranslucent")
    public void setStatusBarTranslucent(ReactModalHostView reactModalHostView, boolean z) {
        reactModalHostView.setStatusBarTranslucent(z);
    }

    @ReactProp(name = "transparent")
    public void setTransparent(ReactModalHostView reactModalHostView, boolean z) {
        reactModalHostView.setTransparent(z);
    }

    public Object updateState(ReactModalHostView reactModalHostView, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        Point modalHostSize = ModalHostHelper.getModalHostSize(reactModalHostView.getContext());
        reactModalHostView.mHostView.updateState(stateWrapper, modalHostSize.x, modalHostSize.y);
        return null;
    }
}
