package com.facebook.drawee.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.Objects$ToStringHelper;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Objects;
/* loaded from: classes.dex */
public class DraweeView<DH extends DraweeHierarchy> extends ImageView {
    public static boolean sGlobalLegacyVisibilityHandlingEnabled = false;
    public DraweeHolder<DH> mDraweeHolder;
    public final AspectRatioMeasure$Spec mMeasureSpec = new AspectRatioMeasure$Spec();
    public float mAspectRatio = 0.0f;
    public boolean mInitialised = false;
    public boolean mLegacyVisibilityHandlingEnabled = false;

    public DraweeView(Context context) {
        super(context);
        init(context);
    }

    public static void setGlobalLegacyVisibilityHandlingEnabled(boolean z) {
        sGlobalLegacyVisibilityHandlingEnabled = z;
    }

    public float getAspectRatio() {
        return this.mAspectRatio;
    }

    public DraweeController getController() {
        return this.mDraweeHolder.mController;
    }

    public DH getHierarchy() {
        DH dh = this.mDraweeHolder.mHierarchy;
        Objects.requireNonNull(dh);
        return dh;
    }

    public Drawable getTopLevelDrawable() {
        return this.mDraweeHolder.getTopLevelDrawable();
    }

    public final void init(Context context) {
        try {
            FrescoSystrace.isTracing();
            if (!this.mInitialised) {
                boolean z = true;
                this.mInitialised = true;
                this.mDraweeHolder = new DraweeHolder<>(null);
                ColorStateList imageTintList = getImageTintList();
                if (imageTintList != null) {
                    setColorFilter(imageTintList.getDefaultColor());
                    if (!sGlobalLegacyVisibilityHandlingEnabled || context.getApplicationInfo().targetSdkVersion < 24) {
                        z = false;
                    }
                    this.mLegacyVisibilityHandlingEnabled = z;
                }
            }
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    public final void maybeOverrideVisibilityHandling() {
        Drawable drawable;
        if (this.mLegacyVisibilityHandlingEnabled && (drawable = getDrawable()) != null) {
            drawable.setVisible(getVisibility() == 0, false);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        maybeOverrideVisibilityHandling();
        this.mDraweeHolder.onAttach();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        maybeOverrideVisibilityHandling();
        this.mDraweeHolder.onDetach();
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        maybeOverrideVisibilityHandling();
        this.mDraweeHolder.onAttach();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        AspectRatioMeasure$Spec aspectRatioMeasure$Spec = this.mMeasureSpec;
        aspectRatioMeasure$Spec.width = i;
        aspectRatioMeasure$Spec.height = i2;
        float f = this.mAspectRatio;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (f > 0.0f && layoutParams != null) {
            int i3 = layoutParams.height;
            boolean z = true;
            if (i3 == 0 || i3 == -2) {
                aspectRatioMeasure$Spec.height = View.MeasureSpec.makeMeasureSpec(View.resolveSize((int) (((View.MeasureSpec.getSize(aspectRatioMeasure$Spec.width) - paddingRight) / f) + paddingBottom), aspectRatioMeasure$Spec.height), 1073741824);
            } else {
                int i4 = layoutParams.width;
                if (!(i4 == 0 || i4 == -2)) {
                    z = false;
                }
                if (z) {
                    aspectRatioMeasure$Spec.width = View.MeasureSpec.makeMeasureSpec(View.resolveSize((int) (((View.MeasureSpec.getSize(aspectRatioMeasure$Spec.height) - paddingBottom) * f) + paddingRight), aspectRatioMeasure$Spec.width), 1073741824);
                }
            }
        }
        AspectRatioMeasure$Spec aspectRatioMeasure$Spec2 = this.mMeasureSpec;
        super.onMeasure(aspectRatioMeasure$Spec2.width, aspectRatioMeasure$Spec2.height);
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        maybeOverrideVisibilityHandling();
        this.mDraweeHolder.onDetach();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        GestureDetector.ClickListener clickListener;
        DraweeHolder<DH> draweeHolder = this.mDraweeHolder;
        boolean z = false;
        if (draweeHolder.isControllerValid()) {
            AbstractDraweeController abstractDraweeController = (AbstractDraweeController) draweeHolder.mController;
            Objects.requireNonNull(abstractDraweeController);
            boolean isLoggable = FLog.isLoggable(2);
            if (isLoggable) {
                FLog.v(AbstractDraweeController.class, "controller %x %s: onTouchEvent %s", Integer.valueOf(System.identityHashCode(abstractDraweeController)), abstractDraweeController.mId, motionEvent);
            }
            GestureDetector gestureDetector = abstractDraweeController.mGestureDetector;
            if (gestureDetector != null && (gestureDetector.mIsCapturingGesture || abstractDraweeController.shouldRetryOnTap())) {
                GestureDetector gestureDetector2 = abstractDraweeController.mGestureDetector;
                Objects.requireNonNull(gestureDetector2);
                int action = motionEvent.getAction();
                if (action == 0) {
                    gestureDetector2.mIsCapturingGesture = true;
                    gestureDetector2.mIsClickCandidate = true;
                    gestureDetector2.mActionDownTime = motionEvent.getEventTime();
                    gestureDetector2.mActionDownX = motionEvent.getX();
                    gestureDetector2.mActionDownY = motionEvent.getY();
                } else if (action == 1) {
                    gestureDetector2.mIsCapturingGesture = false;
                    if (Math.abs(motionEvent.getX() - gestureDetector2.mActionDownX) > gestureDetector2.mSingleTapSlopPx || Math.abs(motionEvent.getY() - gestureDetector2.mActionDownY) > gestureDetector2.mSingleTapSlopPx) {
                        gestureDetector2.mIsClickCandidate = false;
                    }
                    if (gestureDetector2.mIsClickCandidate && motionEvent.getEventTime() - gestureDetector2.mActionDownTime <= ViewConfiguration.getLongPressTimeout() && (clickListener = gestureDetector2.mClickListener) != null) {
                        AbstractDraweeController abstractDraweeController2 = (AbstractDraweeController) clickListener;
                        if (isLoggable) {
                            System.identityHashCode(abstractDraweeController2);
                        }
                        if (abstractDraweeController2.shouldRetryOnTap()) {
                            abstractDraweeController2.mRetryManager.mTapToRetryAttempts++;
                            abstractDraweeController2.mSettableDraweeHierarchy.reset();
                            abstractDraweeController2.submitRequest();
                        }
                    }
                    gestureDetector2.mIsClickCandidate = false;
                } else if (action != 2) {
                    if (action == 3) {
                        gestureDetector2.mIsCapturingGesture = false;
                        gestureDetector2.mIsClickCandidate = false;
                    }
                } else if (Math.abs(motionEvent.getX() - gestureDetector2.mActionDownX) > gestureDetector2.mSingleTapSlopPx || Math.abs(motionEvent.getY() - gestureDetector2.mActionDownY) > gestureDetector2.mSingleTapSlopPx) {
                    gestureDetector2.mIsClickCandidate = false;
                }
                z = true;
            }
        }
        if (z) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        maybeOverrideVisibilityHandling();
    }

    public void setAspectRatio(float f) {
        if (f != this.mAspectRatio) {
            this.mAspectRatio = f;
            requestLayout();
        }
    }

    public void setController(DraweeController draweeController) {
        this.mDraweeHolder.setController(draweeController);
        super.setImageDrawable(this.mDraweeHolder.getTopLevelDrawable());
    }

    public void setHierarchy(DH dh) {
        this.mDraweeHolder.setHierarchy(dh);
        super.setImageDrawable(this.mDraweeHolder.getTopLevelDrawable());
    }

    @Override // android.widget.ImageView
    @Deprecated
    public void setImageBitmap(Bitmap bitmap) {
        init(getContext());
        this.mDraweeHolder.setController(null);
        super.setImageBitmap(bitmap);
    }

    @Override // android.widget.ImageView
    @Deprecated
    public void setImageDrawable(Drawable drawable) {
        init(getContext());
        this.mDraweeHolder.setController(null);
        super.setImageDrawable(drawable);
    }

    @Override // android.widget.ImageView
    @Deprecated
    public void setImageResource(int i) {
        init(getContext());
        this.mDraweeHolder.setController(null);
        super.setImageResource(i);
    }

    @Override // android.widget.ImageView
    @Deprecated
    public void setImageURI(Uri uri) {
        init(getContext());
        this.mDraweeHolder.setController(null);
        super.setImageURI(uri);
    }

    public void setLegacyVisibilityHandlingEnabled(boolean z) {
        this.mLegacyVisibilityHandlingEnabled = z;
    }

    @Override // android.view.View
    public String toString() {
        Objects$ToStringHelper stringHelper = R$dimen.toStringHelper(this);
        DraweeHolder<DH> draweeHolder = this.mDraweeHolder;
        stringHelper.addHolder("holder", draweeHolder != null ? draweeHolder.toString() : "<no holder set>");
        return stringHelper.toString();
    }
}
