package com.facebook.react.views.progressbar;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.AndroidProgressBarManagerDelegate;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.Objects;
@ReactModule(name = ReactProgressBarViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactProgressBarViewManager extends BaseViewManager<ProgressBarContainerView, ProgressBarShadowNode> {
    public static final String DEFAULT_STYLE = "Normal";
    public static final String PROP_ANIMATING = "animating";
    public static final String PROP_INDETERMINATE = "indeterminate";
    public static final String PROP_PROGRESS = "progress";
    public static final String PROP_STYLE = "styleAttr";
    public static final String REACT_CLASS = "AndroidProgressBar";
    private static Object sProgressBarCtorLock = new Object();
    private final ViewManagerDelegate<ProgressBarContainerView> mDelegate = new AndroidProgressBarManagerDelegate(this);

    public static ProgressBar createProgressBar(Context context, int i) {
        ProgressBar progressBar;
        synchronized (sProgressBarCtorLock) {
            progressBar = new ProgressBar(context, null, i);
        }
        return progressBar;
    }

    public static int getStyleFromString(String str) {
        if (str == null) {
            throw new JSApplicationIllegalArgumentException("ProgressBar needs to have a style, null received");
        } else if (str.equals("Horizontal")) {
            return 16842872;
        } else {
            if (str.equals("Small")) {
                return 16842873;
            }
            if (str.equals("Large")) {
                return 16842874;
            }
            if (str.equals("Inverse")) {
                return 16843399;
            }
            if (str.equals("SmallInverse")) {
                return 16843400;
            }
            if (str.equals("LargeInverse")) {
                return 16843401;
            }
            if (str.equals(DEFAULT_STYLE)) {
                return 16842871;
            }
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Unknown ProgressBar style: ", str));
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate<ProgressBarContainerView> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<ProgressBarShadowNode> getShadowNodeClass() {
        return ProgressBarShadowNode.class;
    }

    @ReactProp(name = PROP_STYLE)
    public void setStyleAttr(ProgressBarContainerView progressBarContainerView, String str) {
        Objects.requireNonNull(progressBarContainerView);
        ProgressBar createProgressBar = createProgressBar(progressBarContainerView.getContext(), getStyleFromString(str));
        progressBarContainerView.mProgressBar = createProgressBar;
        createProgressBar.setMax(RNCWebViewManager.COMMAND_CLEAR_FORM_DATA);
        progressBarContainerView.removeAllViews();
        progressBarContainerView.addView(progressBarContainerView.mProgressBar, new ViewGroup.LayoutParams(-1, -1));
    }

    public void setTypeAttr(ProgressBarContainerView progressBarContainerView, String str) {
    }

    public void updateExtraData(ProgressBarContainerView progressBarContainerView, Object obj) {
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ProgressBarShadowNode createShadowNodeInstance() {
        return new ProgressBarShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ProgressBarContainerView createViewInstance(ThemedReactContext themedReactContext) {
        return new ProgressBarContainerView(themedReactContext);
    }

    public void onAfterUpdateTransaction(ProgressBarContainerView progressBarContainerView) {
        Drawable drawable;
        ProgressBar progressBar = progressBarContainerView.mProgressBar;
        if (progressBar != null) {
            progressBar.setIndeterminate(progressBarContainerView.mIndeterminate);
            ProgressBar progressBar2 = progressBarContainerView.mProgressBar;
            if (progressBar2.isIndeterminate()) {
                drawable = progressBar2.getIndeterminateDrawable();
            } else {
                drawable = progressBar2.getProgressDrawable();
            }
            if (drawable != null) {
                Integer num = progressBarContainerView.mColor;
                if (num != null) {
                    drawable.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
                } else {
                    drawable.clearColorFilter();
                }
            }
            progressBarContainerView.mProgressBar.setProgress((int) (progressBarContainerView.mProgress * 1000.0d));
            if (progressBarContainerView.mAnimating) {
                progressBarContainerView.mProgressBar.setVisibility(0);
            } else {
                progressBarContainerView.mProgressBar.setVisibility(4);
            }
        } else {
            throw new JSApplicationIllegalArgumentException("setStyle() not called");
        }
    }

    @ReactProp(name = PROP_ANIMATING)
    public void setAnimating(ProgressBarContainerView progressBarContainerView, boolean z) {
        progressBarContainerView.mAnimating = z;
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ProgressBarContainerView progressBarContainerView, Integer num) {
        progressBarContainerView.mColor = num;
    }

    @ReactProp(name = PROP_INDETERMINATE)
    public void setIndeterminate(ProgressBarContainerView progressBarContainerView, boolean z) {
        progressBarContainerView.mIndeterminate = z;
    }

    @ReactProp(name = PROP_PROGRESS)
    public void setProgress(ProgressBarContainerView progressBarContainerView, double d) {
        progressBarContainerView.mProgress = d;
    }

    public void setTestID(ProgressBarContainerView progressBarContainerView, String str) {
        super.setTestId(progressBarContainerView, str);
    }
}
