package com.swmansion.rnscreens;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewParent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.view.ReactViewGroup;
/* compiled from: ScreenStackHeaderSubview.kt */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public final class ScreenStackHeaderSubview extends ReactViewGroup {
    public int mReactHeight;
    public int mReactWidth;
    public Type type = Type.RIGHT;

    /* compiled from: ScreenStackHeaderSubview.kt */
    /* loaded from: classes.dex */
    public enum Type {
        LEFT,
        CENTER,
        RIGHT,
        BACK
    }

    public ScreenStackHeaderSubview(ReactContext reactContext) {
        super(reactContext);
    }

    public final Type getType() {
        return this.type;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
    public void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == 1073741824 && View.MeasureSpec.getMode(i2) == 1073741824) {
            this.mReactWidth = View.MeasureSpec.getSize(i);
            this.mReactHeight = View.MeasureSpec.getSize(i2);
            ViewParent parent = getParent();
            if (parent != null) {
                forceLayout();
                ((View) parent).requestLayout();
            }
        }
        setMeasuredDimension(this.mReactWidth, this.mReactHeight);
    }

    public final void setType(Type type) {
        this.type = type;
    }
}
