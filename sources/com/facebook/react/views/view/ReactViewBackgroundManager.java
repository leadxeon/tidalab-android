package com.facebook.react.views.view;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.facebook.react.R$style;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class ReactViewBackgroundManager {
    public ReactViewBackgroundDrawable mReactBackgroundDrawable;
    public View mView;

    public ReactViewBackgroundManager(View view) {
        this.mView = view;
    }

    public final ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable(this.mView.getContext());
            Drawable background = this.mView.getBackground();
            View view = this.mView;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            view.setBackground(null);
            if (background == null) {
                this.mView.setBackground(this.mReactBackgroundDrawable);
            } else {
                this.mView.setBackground(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, background}));
            }
        }
        return this.mReactBackgroundDrawable;
    }

    public void setBackgroundColor(int i) {
        if (i != 0 || this.mReactBackgroundDrawable != null) {
            ReactViewBackgroundDrawable orCreateReactViewBackground = getOrCreateReactViewBackground();
            orCreateReactViewBackground.mColor = i;
            orCreateReactViewBackground.invalidateSelf();
        }
    }

    public void setBorderRadius(float f) {
        ReactViewBackgroundDrawable orCreateReactViewBackground = getOrCreateReactViewBackground();
        if (!R$style.floatsEqual(orCreateReactViewBackground.mBorderRadius, f)) {
            orCreateReactViewBackground.mBorderRadius = f;
            orCreateReactViewBackground.mNeedUpdatePathForBorderRadius = true;
            orCreateReactViewBackground.invalidateSelf();
        }
    }
}
