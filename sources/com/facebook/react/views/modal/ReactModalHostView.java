package com.facebook.react.views.modal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import androidx.recyclerview.R$dimen;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;
import okhttp3.internal.http2.Http2Connection;
/* loaded from: classes.dex */
public class ReactModalHostView extends ViewGroup implements LifecycleEventListener {
    public String mAnimationType;
    public Dialog mDialog;
    public boolean mHardwareAccelerated;
    public DialogRootViewGroup mHostView;
    public OnRequestCloseListener mOnRequestCloseListener;
    public DialogInterface.OnShowListener mOnShowListener;
    public boolean mPropertyRequiresNewDialog;
    public boolean mStatusBarTranslucent;
    public boolean mTransparent;

    /* loaded from: classes.dex */
    public static class DialogRootViewGroup extends ReactViewGroup implements RootView {
        public boolean hasAdjustedSize = false;
        public final JSTouchDispatcher mJSTouchDispatcher = new JSTouchDispatcher(this);
        public StateWrapper mStateWrapper;
        public int viewHeight;
        public int viewWidth;

        public DialogRootViewGroup(Context context) {
            super(context);
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
            super.addView(view, i, layoutParams);
            if (this.hasAdjustedSize) {
                updateFirstChildView();
            }
        }

        public final EventDispatcher getEventDispatcher() {
            return ((UIManagerModule) getReactContext().getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }

        public final ReactContext getReactContext() {
            return (ReactContext) getContext();
        }

        @Override // com.facebook.react.uimanager.RootView
        public void handleException(Throwable th) {
            getReactContext().handleException(new RuntimeException(th));
        }

        @Override // com.facebook.react.uimanager.RootView
        public void onChildStartedNativeGesture(MotionEvent motionEvent) {
            JSTouchDispatcher jSTouchDispatcher = this.mJSTouchDispatcher;
            EventDispatcher eventDispatcher = getEventDispatcher();
            if (!jSTouchDispatcher.mChildIsHandlingNativeGesture) {
                jSTouchDispatcher.dispatchCancelEvent(motionEvent, eventDispatcher);
                jSTouchDispatcher.mChildIsHandlingNativeGesture = true;
                jSTouchDispatcher.mTargetTag = -1;
            }
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, getEventDispatcher());
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        public void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            this.viewWidth = i;
            this.viewHeight = i2;
            updateFirstChildView();
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, getEventDispatcher());
            return true;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean z) {
        }

        public final void updateFirstChildView() {
            if (getChildCount() > 0) {
                this.hasAdjustedSize = false;
                final int id = getChildAt(0).getId();
                StateWrapper stateWrapper = this.mStateWrapper;
                if (stateWrapper != null) {
                    updateState(stateWrapper, this.viewWidth, this.viewHeight);
                    return;
                }
                ReactContext reactContext = getReactContext();
                reactContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactContext) { // from class: com.facebook.react.views.modal.ReactModalHostView.DialogRootViewGroup.1
                    @Override // com.facebook.react.bridge.GuardedRunnable
                    public void runGuarded() {
                        int i = id;
                        DialogRootViewGroup dialogRootViewGroup = DialogRootViewGroup.this;
                        ((UIManagerModule) DialogRootViewGroup.this.getReactContext().getNativeModule(UIManagerModule.class)).updateNodeSize(i, dialogRootViewGroup.viewWidth, dialogRootViewGroup.viewHeight);
                    }
                });
                return;
            }
            this.hasAdjustedSize = true;
        }

        public void updateState(StateWrapper stateWrapper, int i, int i2) {
            this.mStateWrapper = stateWrapper;
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble("screenWidth", PixelUtil.toDIPFromPixel(i));
            writableNativeMap.putDouble("screenHeight", PixelUtil.toDIPFromPixel(i2));
            stateWrapper.updateState(writableNativeMap);
        }
    }

    /* loaded from: classes.dex */
    public interface OnRequestCloseListener {
    }

    public ReactModalHostView(Context context) {
        super(context);
        ((ReactContext) context).addLifecycleEventListener(this);
        this.mHostView = new DialogRootViewGroup(context);
    }

    private View getContentView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.mHostView);
        if (this.mStatusBarTranslucent) {
            frameLayout.setSystemUiVisibility(1024);
        } else {
            frameLayout.setFitsSystemWindows(true);
        }
        return frameLayout;
    }

    private Activity getCurrentActivity() {
        return ((ReactContext) getContext()).getCurrentActivity();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.addView(view, i);
    }

    public final void dismiss() {
        Context baseContext;
        UiThreadUtil.assertOnUiThread();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            if (dialog.isShowing()) {
                Context context = this.mDialog.getContext();
                while (!Activity.class.isInstance(context)) {
                    if (!(context instanceof ContextWrapper) || context == (baseContext = ((ContextWrapper) context).getBaseContext())) {
                        context = null;
                        break;
                    }
                    context = baseContext;
                }
                Activity activity = (Activity) context;
                if (activity == null || !activity.isFinishing()) {
                    this.mDialog.dismiss();
                }
            }
            this.mDialog = null;
            ((ViewGroup) this.mHostView.getParent()).removeViewAt(0);
        }
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(23)
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        this.mHostView.dispatchProvideStructure(viewStructure);
    }

    @Override // android.view.ViewGroup
    public View getChildAt(int i) {
        return this.mHostView.getChildAt(i);
    }

    @Override // android.view.ViewGroup
    public int getChildCount() {
        return this.mHostView.getChildCount();
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismiss();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        ((ReactContext) getContext()).removeLifecycleEventListener(this);
        dismiss();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        showOrUpdate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.removeView(this.mHostView.getChildAt(i));
    }

    public void setAnimationType(String str) {
        this.mAnimationType = str;
        this.mPropertyRequiresNewDialog = true;
    }

    public void setHardwareAccelerated(boolean z) {
        this.mHardwareAccelerated = z;
        this.mPropertyRequiresNewDialog = true;
    }

    public void setOnRequestCloseListener(OnRequestCloseListener onRequestCloseListener) {
        this.mOnRequestCloseListener = onRequestCloseListener;
    }

    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.mOnShowListener = onShowListener;
    }

    public void setStatusBarTranslucent(boolean z) {
        this.mStatusBarTranslucent = z;
        this.mPropertyRequiresNewDialog = true;
    }

    public void setTransparent(boolean z) {
        this.mTransparent = z;
    }

    public void showOrUpdate() {
        UiThreadUtil.assertOnUiThread();
        if (this.mDialog != null) {
            if (this.mPropertyRequiresNewDialog) {
                dismiss();
            } else {
                updateProperties();
                return;
            }
        }
        this.mPropertyRequiresNewDialog = false;
        int i = 2131886525;
        if (this.mAnimationType.equals("fade")) {
            i = 2131886526;
        } else if (this.mAnimationType.equals("slide")) {
            i = 2131886527;
        }
        Activity currentActivity = getCurrentActivity();
        Context context = currentActivity == null ? getContext() : currentActivity;
        Dialog dialog = new Dialog(context, i);
        this.mDialog = dialog;
        dialog.getWindow().setFlags(8, 8);
        this.mDialog.setContentView(getContentView());
        updateProperties();
        this.mDialog.setOnShowListener(this.mOnShowListener);
        this.mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.facebook.react.views.modal.ReactModalHostView.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 1) {
                    return false;
                }
                if (i2 == 4) {
                    R$dimen.assertNotNull(ReactModalHostView.this.mOnRequestCloseListener, "setOnRequestCloseListener must be called by the manager");
                    ReactModalHostManager.AnonymousClass1 r2 = (ReactModalHostManager.AnonymousClass1) ReactModalHostView.this.mOnRequestCloseListener;
                    r2.val$dispatcher.dispatchEvent(new RequestCloseEvent(r2.val$view.getId()));
                    return true;
                }
                Activity currentActivity2 = ((ReactContext) ReactModalHostView.this.getContext()).getCurrentActivity();
                if (currentActivity2 != null) {
                    return currentActivity2.onKeyUp(i2, keyEvent);
                }
                return false;
            }
        });
        this.mDialog.getWindow().setSoftInputMode(16);
        if (this.mHardwareAccelerated) {
            this.mDialog.getWindow().addFlags(Http2Connection.OKHTTP_CLIENT_WINDOW_SIZE);
        }
        if (currentActivity != null && !currentActivity.isFinishing()) {
            this.mDialog.show();
            if (context instanceof Activity) {
                this.mDialog.getWindow().getDecorView().setSystemUiVisibility(((Activity) context).getWindow().getDecorView().getSystemUiVisibility());
            }
            this.mDialog.getWindow().clearFlags(8);
        }
    }

    public final void updateProperties() {
        R$dimen.assertNotNull(this.mDialog, "mDialog must exist when we call updateProperties");
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            if ((currentActivity.getWindow().getAttributes().flags & 1024) != 0) {
                this.mDialog.getWindow().addFlags(1024);
            } else {
                this.mDialog.getWindow().clearFlags(1024);
            }
        }
        if (this.mTransparent) {
            this.mDialog.getWindow().clearFlags(2);
            return;
        }
        this.mDialog.getWindow().setDimAmount(0.5f);
        this.mDialog.getWindow().setFlags(2, 2);
    }
}
