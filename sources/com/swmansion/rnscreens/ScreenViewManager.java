package com.swmansion.rnscreens;

import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.swmansion.rnscreens.Screen;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ScreenViewManager.kt */
@ReactModule(name = ScreenViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public final class ScreenViewManager extends ViewGroupManager<Screen> {
    public static final Companion Companion = new Companion(null);
    public static final String REACT_CLASS = "RNSScreen";

    /* compiled from: ScreenViewManager.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map of = R$style.of("registrationName", "onDismissed");
        Map of2 = R$style.of("registrationName", "onWillAppear");
        Map of3 = R$style.of("registrationName", "onAppear");
        Map of4 = R$style.of("registrationName", "onWillDisappear");
        Map of5 = R$style.of("registrationName", "onDisappear");
        Map of6 = R$style.of("registrationName", "onFinishTransitioning");
        HashMap hashMap = new HashMap();
        hashMap.put("topDismissed", of);
        hashMap.put("topWillAppear", of2);
        hashMap.put("topAppear", of3);
        hashMap.put("topWillDisappear", of4);
        hashMap.put("topDisappear", of5);
        hashMap.put("topFinishTransitioning", of6);
        return hashMap;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "activityState")
    public final void setActivityState(Screen screen, Integer num) {
        if (num != null) {
            int intValue = num.intValue();
            if (intValue == 0) {
                screen.setActivityState(Screen.ActivityState.INACTIVE);
            } else if (intValue == 1) {
                screen.setActivityState(Screen.ActivityState.TRANSITIONING_OR_BELOW_TOP);
            } else if (intValue == 2) {
                screen.setActivityState(Screen.ActivityState.ON_TOP);
            }
        }
    }

    @ReactProp(defaultBoolean = true, name = "gestureEnabled")
    public final void setGestureEnabled(Screen screen, boolean z) {
        screen.setGestureEnabled(z);
    }

    @ReactProp(name = "replaceAnimation")
    public final void setReplaceAnimation(Screen screen, String str) {
        Screen.ReplaceAnimation replaceAnimation;
        if (str == null ? true : Intrinsics.areEqual(str, "pop")) {
            replaceAnimation = Screen.ReplaceAnimation.POP;
        } else if (Intrinsics.areEqual(str, "push")) {
            replaceAnimation = Screen.ReplaceAnimation.PUSH;
        } else {
            throw new JSApplicationIllegalArgumentException(Intrinsics.stringPlus("Unknown replace animation type ", str));
        }
        screen.setReplaceAnimation(replaceAnimation);
    }

    @ReactProp(name = "screenOrientation")
    public final void setScreenOrientation(Screen screen, String str) {
        screen.setScreenOrientation(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
        if (r3.equals("flip") != false) goto L_0x0073;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
        if (r3.equals("simple_push") != false) goto L_0x0073;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (r3.equals("default") != false) goto L_0x0073;
     */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "stackAnimation")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setStackAnimation(com.swmansion.rnscreens.Screen r2, java.lang.String r3) {
        /*
            r1 = this;
            if (r3 == 0) goto L_0x0073
            int r0 = r3.hashCode()
            switch(r0) {
                case -1418955385: goto L_0x005e;
                case -427095442: goto L_0x0053;
                case -349395819: goto L_0x0048;
                case 3135100: goto L_0x003d;
                case 3145837: goto L_0x0034;
                case 3387192: goto L_0x0029;
                case 182437661: goto L_0x001e;
                case 1544803905: goto L_0x0015;
                case 1601504978: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0067
        L_0x000a:
            java.lang.String r0 = "slide_from_bottom"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0067
            com.swmansion.rnscreens.Screen$StackAnimation r3 = com.swmansion.rnscreens.Screen.StackAnimation.SLIDE_FROM_BOTTOM
            goto L_0x0075
        L_0x0015:
            java.lang.String r0 = "default"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0067
            goto L_0x0073
        L_0x001e:
            java.lang.String r0 = "fade_from_bottom"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0067
            com.swmansion.rnscreens.Screen$StackAnimation r3 = com.swmansion.rnscreens.Screen.StackAnimation.FADE_FROM_BOTTOM
            goto L_0x0075
        L_0x0029:
            java.lang.String r0 = "none"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0067
            com.swmansion.rnscreens.Screen$StackAnimation r3 = com.swmansion.rnscreens.Screen.StackAnimation.NONE
            goto L_0x0075
        L_0x0034:
            java.lang.String r0 = "flip"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0067
            goto L_0x0073
        L_0x003d:
            java.lang.String r0 = "fade"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0067
            com.swmansion.rnscreens.Screen$StackAnimation r3 = com.swmansion.rnscreens.Screen.StackAnimation.FADE
            goto L_0x0075
        L_0x0048:
            java.lang.String r0 = "slide_from_right"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0067
            com.swmansion.rnscreens.Screen$StackAnimation r3 = com.swmansion.rnscreens.Screen.StackAnimation.SLIDE_FROM_RIGHT
            goto L_0x0075
        L_0x0053:
            java.lang.String r0 = "slide_from_left"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0067
            com.swmansion.rnscreens.Screen$StackAnimation r3 = com.swmansion.rnscreens.Screen.StackAnimation.SLIDE_FROM_LEFT
            goto L_0x0075
        L_0x005e:
            java.lang.String r0 = "simple_push"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0067
            goto L_0x0073
        L_0x0067:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r2 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.String r0 = "Unknown animation type "
            java.lang.String r3 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r3)
            r2.<init>(r3)
            throw r2
        L_0x0073:
            com.swmansion.rnscreens.Screen$StackAnimation r3 = com.swmansion.rnscreens.Screen.StackAnimation.DEFAULT
        L_0x0075:
            r2.setStackAnimation(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenViewManager.setStackAnimation(com.swmansion.rnscreens.Screen, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        if (r3.equals("containedModal") != false) goto L_0x0034;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0032, code lost:
        if (r3.equals("modal") != false) goto L_0x0034;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
        r3 = com.swmansion.rnscreens.Screen.StackPresentation.MODAL;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0048, code lost:
        if (r3.equals("transparentModal") != false) goto L_0x004a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004a, code lost:
        r3 = com.swmansion.rnscreens.Screen.StackPresentation.TRANSPARENT_MODAL;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
        if (r3.equals("formSheet") != false) goto L_0x0034;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r3.equals("fullScreenModal") != false) goto L_0x0034;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r3.equals("containedTransparentModal") != false) goto L_0x004a;
     */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "stackPresentation")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setStackPresentation(com.swmansion.rnscreens.Screen r2, java.lang.String r3) {
        /*
            r1 = this;
            int r0 = r3.hashCode()
            switch(r0) {
                case -76271493: goto L_0x0042;
                case 3452698: goto L_0x0037;
                case 104069805: goto L_0x002c;
                case 438078970: goto L_0x0023;
                case 955284238: goto L_0x001a;
                case 1171936146: goto L_0x0011;
                case 1798290171: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0050
        L_0x0008:
            java.lang.String r0 = "formSheet"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0050
            goto L_0x0034
        L_0x0011:
            java.lang.String r0 = "fullScreenModal"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0050
            goto L_0x0034
        L_0x001a:
            java.lang.String r0 = "containedTransparentModal"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0050
            goto L_0x004a
        L_0x0023:
            java.lang.String r0 = "containedModal"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0050
            goto L_0x0034
        L_0x002c:
            java.lang.String r0 = "modal"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0050
        L_0x0034:
            com.swmansion.rnscreens.Screen$StackPresentation r3 = com.swmansion.rnscreens.Screen.StackPresentation.MODAL
            goto L_0x004c
        L_0x0037:
            java.lang.String r0 = "push"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0050
            com.swmansion.rnscreens.Screen$StackPresentation r3 = com.swmansion.rnscreens.Screen.StackPresentation.PUSH
            goto L_0x004c
        L_0x0042:
            java.lang.String r0 = "transparentModal"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0050
        L_0x004a:
            com.swmansion.rnscreens.Screen$StackPresentation r3 = com.swmansion.rnscreens.Screen.StackPresentation.TRANSPARENT_MODAL
        L_0x004c:
            r2.setStackPresentation(r3)
            return
        L_0x0050:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r2 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.String r0 = "Unknown presentation type "
            java.lang.String r3 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r3)
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenViewManager.setStackPresentation(com.swmansion.rnscreens.Screen, java.lang.String):void");
    }

    @ReactProp(name = "statusBarAnimation")
    public final void setStatusBarAnimation(Screen screen, String str) {
        screen.setStatusBarAnimated(Boolean.valueOf(str != null && !Intrinsics.areEqual("none", str)));
    }

    @ReactProp(name = "statusBarColor")
    public final void setStatusBarColor(Screen screen, Integer num) {
        screen.setStatusBarColor(num);
    }

    @ReactProp(name = "statusBarHidden")
    public final void setStatusBarHidden(Screen screen, Boolean bool) {
        screen.setStatusBarHidden(bool);
    }

    @ReactProp(name = "statusBarStyle")
    public final void setStatusBarStyle(Screen screen, String str) {
        screen.setStatusBarStyle(str);
    }

    @ReactProp(name = "statusBarTranslucent")
    public final void setStatusBarTranslucent(Screen screen, Boolean bool) {
        screen.setStatusBarTranslucent(bool);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Screen createViewInstance(ThemedReactContext themedReactContext) {
        return new Screen(themedReactContext);
    }
}
