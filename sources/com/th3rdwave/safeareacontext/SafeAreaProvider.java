package com.th3rdwave.safeareacontext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.recyclerview.R$dimen;
import com.facebook.react.views.view.ReactViewGroup;
import com.horcrux.svg.PathParser;
import java.util.Objects;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class SafeAreaProvider extends ReactViewGroup implements ViewTreeObserver.OnPreDrawListener {
    public OnInsetsChangeListener mInsetsChangeListener;
    public Rect mLastFrame;
    public EdgeInsets mLastInsets;

    /* loaded from: classes.dex */
    public interface OnInsetsChangeListener {
        void onInsetsChange(SafeAreaProvider safeAreaProvider, EdgeInsets edgeInsets, Rect rect);
    }

    public SafeAreaProvider(Context context) {
        super(context);
    }

    public final void maybeUpdateInsets() {
        EdgeInsets safeAreaInsets = PathParser.getSafeAreaInsets(this);
        Rect frame = PathParser.getFrame((ViewGroup) getRootView(), this);
        if (safeAreaInsets != null && frame != null) {
            EdgeInsets edgeInsets = this.mLastInsets;
            if (!(edgeInsets == null || this.mLastFrame == null || !edgeInsets.equalsToEdgeInsets(safeAreaInsets))) {
                Rect rect = this.mLastFrame;
                Objects.requireNonNull(rect);
                boolean z = true;
                if (!(rect == frame || (rect.x == frame.x && rect.y == frame.y && rect.width == frame.width && rect.height == frame.height))) {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            OnInsetsChangeListener onInsetsChangeListener = this.mInsetsChangeListener;
            R$dimen.assertNotNull(onInsetsChangeListener);
            onInsetsChangeListener.onInsetsChange(this, safeAreaInsets, frame);
            this.mLastInsets = safeAreaInsets;
            this.mLastFrame = frame;
        }
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnPreDrawListener(this);
        maybeUpdateInsets();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnPreDrawListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        maybeUpdateInsets();
        return true;
    }

    public void setOnInsetsChangeListener(OnInsetsChangeListener onInsetsChangeListener) {
        this.mInsetsChangeListener = onInsetsChangeListener;
    }
}
