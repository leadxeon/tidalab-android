package com.facebook.react.views.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import androidx.recyclerview.R$dimen;
import com.facebook.common.logging.FLog;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ReactZIndexedViewGroup;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.ViewGroupDrawingOrderHelper;
import com.facebook.react.uimanager.ViewGroupManager;
import java.util.Objects;
/* loaded from: classes.dex */
public class ReactViewGroup extends ViewGroup implements ReactInterceptingViewGroup, ReactClippingViewGroup, ReactPointerEventsView, ReactHitSlopView, ReactZIndexedViewGroup {
    public static final ViewGroup.LayoutParams sDefaultLayoutParam = new ViewGroup.LayoutParams(0, 0);
    public static final Rect sHelperRect = new Rect();
    public int mAllChildrenCount;
    public ChildrenLayoutChangeListener mChildrenLayoutChangeListener;
    public Rect mClippingRect;
    public Rect mHitSlopRect;
    public int mLayoutDirection;
    public OnInterceptTouchEventListener mOnInterceptTouchEventListener;
    public String mOverflow;
    public Path mPath;
    public ReactViewBackgroundDrawable mReactBackgroundDrawable;
    public boolean mRemoveClippedSubviews = false;
    public View[] mAllChildren = null;
    public PointerEvents mPointerEvents = PointerEvents.AUTO;
    public boolean mNeedsOffscreenAlphaCompositing = false;
    public float mBackfaceOpacity = 1.0f;
    public String mBackfaceVisibility = "visible";
    public final ViewGroupDrawingOrderHelper mDrawingOrderHelper = new ViewGroupDrawingOrderHelper(this);

    /* loaded from: classes.dex */
    public static final class ChildrenLayoutChangeListener implements View.OnLayoutChangeListener {
        public final ReactViewGroup mParent;

        public ChildrenLayoutChangeListener(ReactViewGroup reactViewGroup, AnonymousClass1 r2) {
            this.mParent = reactViewGroup;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.mParent.getRemoveClippedSubviews()) {
                ReactViewGroup reactViewGroup = this.mParent;
                if (reactViewGroup.mRemoveClippedSubviews && reactViewGroup.getParent() != null) {
                    R$dimen.assertNotNull(reactViewGroup.mClippingRect);
                    R$dimen.assertNotNull(reactViewGroup.mAllChildren);
                    Rect rect = ReactViewGroup.sHelperRect;
                    rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                    if (reactViewGroup.mClippingRect.intersects(rect.left, rect.top, rect.right, rect.bottom) != (view.getParent() != null)) {
                        int i9 = 0;
                        for (int i10 = 0; i10 < reactViewGroup.mAllChildrenCount; i10++) {
                            View[] viewArr = reactViewGroup.mAllChildren;
                            if (viewArr[i10] == view) {
                                reactViewGroup.updateSubviewClipStatus(reactViewGroup.mClippingRect, i10, i9);
                                return;
                            }
                            if (viewArr[i10].getParent() == null) {
                                i9++;
                            }
                        }
                    }
                }
            }
        }
    }

    public ReactViewGroup(Context context) {
        super(context);
        setClipChildren(false);
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [int, boolean] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.facebook.react.views.view.ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        /*
            r5 = this;
            com.facebook.react.views.view.ReactViewBackgroundDrawable r0 = r5.mReactBackgroundDrawable
            if (r0 != 0) goto L_0x0048
            com.facebook.react.views.view.ReactViewBackgroundDrawable r0 = new com.facebook.react.views.view.ReactViewBackgroundDrawable
            android.content.Context r1 = r5.getContext()
            r0.<init>(r1)
            r5.mReactBackgroundDrawable = r0
            android.graphics.drawable.Drawable r0 = r5.getBackground()
            r1 = 0
            super.setBackground(r1)
            if (r0 != 0) goto L_0x001f
            com.facebook.react.views.view.ReactViewBackgroundDrawable r0 = r5.mReactBackgroundDrawable
            super.setBackground(r0)
            goto L_0x0032
        L_0x001f:
            android.graphics.drawable.LayerDrawable r1 = new android.graphics.drawable.LayerDrawable
            r2 = 2
            android.graphics.drawable.Drawable[] r2 = new android.graphics.drawable.Drawable[r2]
            r3 = 0
            com.facebook.react.views.view.ReactViewBackgroundDrawable r4 = r5.mReactBackgroundDrawable
            r2[r3] = r4
            r3 = 1
            r2[r3] = r0
            r1.<init>(r2)
            super.setBackground(r1)
        L_0x0032:
            com.facebook.react.modules.i18nmanager.I18nUtil r0 = com.facebook.react.modules.i18nmanager.I18nUtil.getInstance()
            android.content.Context r1 = r5.getContext()
            boolean r0 = r0.isRTL(r1)
            r5.mLayoutDirection = r0
            com.facebook.react.views.view.ReactViewBackgroundDrawable r1 = r5.mReactBackgroundDrawable
            int r2 = r1.mLayoutDirection
            if (r2 == r0) goto L_0x0048
            r1.mLayoutDirection = r0
        L_0x0048:
            com.facebook.react.views.view.ReactViewBackgroundDrawable r0 = r5.mReactBackgroundDrawable
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewGroup.getOrCreateReactViewBackground():com.facebook.react.views.view.ReactViewBackgroundDrawable");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        ViewGroupDrawingOrderHelper viewGroupDrawingOrderHelper = this.mDrawingOrderHelper;
        Objects.requireNonNull(viewGroupDrawingOrderHelper);
        if (ViewGroupManager.getViewZIndex(view) != null) {
            viewGroupDrawingOrderHelper.mNumberOfChildrenWithZIndex++;
        }
        viewGroupDrawingOrderHelper.mDrawingOrderIndices = null;
        setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        super.addView(view, i, layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        try {
            dispatchOverflowDraw(canvas);
            super.dispatchDraw(canvas);
        } catch (NullPointerException e) {
            FLog.e("ReactNative", "NullPointerException when executing ViewGroup.dispatchDraw method", e);
        } catch (StackOverflowError e2) {
            RootView rootView = R$style.getRootView(this);
            if (rootView != null) {
                rootView.handleException(e2);
            } else if (getContext() instanceof ReactContext) {
                ((ReactContext) getContext()).handleException(new IllegalViewOperationException("StackOverflowException", this, e2));
            } else {
                throw e2;
            }
        }
    }

    public final void dispatchOverflowDraw(Canvas canvas) {
        float f;
        float f2;
        boolean z;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        Path path;
        String str = this.mOverflow;
        if (str == null) {
            return;
        }
        if (str.equals("hidden")) {
            float width = getWidth();
            float height = getHeight();
            ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.mReactBackgroundDrawable;
            if (reactViewBackgroundDrawable != null) {
                RectF directionAwareBorderInsets = reactViewBackgroundDrawable.getDirectionAwareBorderInsets();
                float f8 = directionAwareBorderInsets.top;
                if (f8 > 0.0f || directionAwareBorderInsets.left > 0.0f || directionAwareBorderInsets.bottom > 0.0f || directionAwareBorderInsets.right > 0.0f) {
                    f3 = directionAwareBorderInsets.left + 0.0f;
                    f = f8 + 0.0f;
                    width -= directionAwareBorderInsets.right;
                    height -= directionAwareBorderInsets.bottom;
                } else {
                    f = 0.0f;
                    f3 = 0.0f;
                }
                ReactViewBackgroundDrawable reactViewBackgroundDrawable2 = this.mReactBackgroundDrawable;
                float f9 = R$style.isUndefined(reactViewBackgroundDrawable2.mBorderRadius) ? 0.0f : reactViewBackgroundDrawable2.mBorderRadius;
                float borderRadiusOrDefaultTo$enumunboxing$ = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo$enumunboxing$(f9, 1);
                float borderRadiusOrDefaultTo$enumunboxing$2 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo$enumunboxing$(f9, 2);
                float borderRadiusOrDefaultTo$enumunboxing$3 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo$enumunboxing$(f9, 4);
                float borderRadiusOrDefaultTo$enumunboxing$4 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo$enumunboxing$(f9, 3);
                boolean z2 = this.mLayoutDirection == 1;
                float borderRadiusOrDefaultTo$enumunboxing$5 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo$enumunboxing$(Float.NaN, 5);
                float borderRadiusOrDefaultTo$enumunboxing$6 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo$enumunboxing$(Float.NaN, 6);
                float borderRadiusOrDefaultTo$enumunboxing$7 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo$enumunboxing$(Float.NaN, 7);
                float borderRadiusOrDefaultTo$enumunboxing$8 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo$enumunboxing$(Float.NaN, 8);
                if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(getContext())) {
                    f4 = R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$5) ? borderRadiusOrDefaultTo$enumunboxing$ : borderRadiusOrDefaultTo$enumunboxing$5;
                    if (!R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$6)) {
                        borderRadiusOrDefaultTo$enumunboxing$2 = borderRadiusOrDefaultTo$enumunboxing$6;
                    }
                    if (!R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$7)) {
                        borderRadiusOrDefaultTo$enumunboxing$3 = borderRadiusOrDefaultTo$enumunboxing$7;
                    }
                    f5 = R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$8) ? borderRadiusOrDefaultTo$enumunboxing$4 : borderRadiusOrDefaultTo$enumunboxing$8;
                    f7 = z2 ? borderRadiusOrDefaultTo$enumunboxing$2 : f4;
                    if (!z2) {
                        f4 = borderRadiusOrDefaultTo$enumunboxing$2;
                    }
                    f6 = z2 ? f5 : borderRadiusOrDefaultTo$enumunboxing$3;
                    if (z2) {
                        f5 = borderRadiusOrDefaultTo$enumunboxing$3;
                    }
                } else {
                    float f10 = z2 ? borderRadiusOrDefaultTo$enumunboxing$6 : borderRadiusOrDefaultTo$enumunboxing$5;
                    if (!z2) {
                        borderRadiusOrDefaultTo$enumunboxing$5 = borderRadiusOrDefaultTo$enumunboxing$6;
                    }
                    float f11 = z2 ? borderRadiusOrDefaultTo$enumunboxing$8 : borderRadiusOrDefaultTo$enumunboxing$7;
                    if (!z2) {
                        borderRadiusOrDefaultTo$enumunboxing$7 = borderRadiusOrDefaultTo$enumunboxing$8;
                    }
                    float f12 = !R$style.isUndefined(f10) ? f10 : borderRadiusOrDefaultTo$enumunboxing$;
                    if (!R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$5)) {
                        borderRadiusOrDefaultTo$enumunboxing$2 = borderRadiusOrDefaultTo$enumunboxing$5;
                    }
                    if (!R$style.isUndefined(f11)) {
                        borderRadiusOrDefaultTo$enumunboxing$3 = f11;
                    }
                    if (!R$style.isUndefined(borderRadiusOrDefaultTo$enumunboxing$7)) {
                        f5 = borderRadiusOrDefaultTo$enumunboxing$7;
                        f7 = f12;
                        f4 = borderRadiusOrDefaultTo$enumunboxing$2;
                        f6 = borderRadiusOrDefaultTo$enumunboxing$3;
                    } else {
                        f7 = f12;
                        f4 = borderRadiusOrDefaultTo$enumunboxing$2;
                        f6 = borderRadiusOrDefaultTo$enumunboxing$3;
                        f5 = borderRadiusOrDefaultTo$enumunboxing$4;
                    }
                }
                if (f7 > 0.0f || f4 > 0.0f || f5 > 0.0f || f6 > 0.0f) {
                    if (this.mPath == null) {
                        this.mPath = new Path();
                    }
                    this.mPath.rewind();
                    this.mPath.addRoundRect(new RectF(f3, f, width, height), new float[]{Math.max(f7 - directionAwareBorderInsets.left, 0.0f), Math.max(f7 - directionAwareBorderInsets.top, 0.0f), Math.max(f4 - directionAwareBorderInsets.right, 0.0f), Math.max(f4 - directionAwareBorderInsets.top, 0.0f), Math.max(f5 - directionAwareBorderInsets.right, 0.0f), Math.max(f5 - directionAwareBorderInsets.bottom, 0.0f), Math.max(f6 - directionAwareBorderInsets.left, 0.0f), Math.max(f6 - directionAwareBorderInsets.bottom, 0.0f)}, Path.Direction.CW);
                    canvas.clipPath(this.mPath);
                    f2 = f3;
                    z = true;
                } else {
                    f2 = f3;
                    z = false;
                }
            } else {
                z = false;
                f2 = 0.0f;
                f = 0.0f;
            }
            if (!z) {
                canvas.clipRect(new RectF(f2, f, width, height));
            }
        } else if (str.equals("visible") && (path = this.mPath) != null) {
            path.rewind();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(23)
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        try {
            super.dispatchProvideStructure(viewStructure);
        } catch (NullPointerException e) {
            FLog.e("ReactNative", "NullPointerException when executing dispatchProvideStructure", e);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSetPressed(boolean z) {
    }

    public int getAllChildrenCount() {
        return this.mAllChildrenCount;
    }

    public int getBackgroundColor() {
        if (getBackground() != null) {
            return ((ReactViewBackgroundDrawable) getBackground()).mColor;
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i, int i2) {
        return this.mDrawingOrderHelper.getChildDrawingOrder(i, i2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean getChildVisibleRect(View view, Rect rect, Point point) {
        return super.getChildVisibleRect(view, rect, point);
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void getClippingRect(Rect rect) {
        rect.set(this.mClippingRect);
    }

    @Override // com.facebook.react.touch.ReactHitSlopView
    public Rect getHitSlopRect() {
        return this.mHitSlopRect;
    }

    public String getOverflow() {
        return this.mOverflow;
    }

    @Override // com.facebook.react.uimanager.ReactPointerEventsView
    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    @Override // com.facebook.react.uimanager.ReactZIndexedViewGroup
    public int getZIndexMappedChildIndex(int i) {
        return this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder() ? this.mDrawingOrderHelper.getChildDrawingOrder(getChildCount(), i) : i;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return this.mNeedsOffscreenAlphaCompositing;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        OnInterceptTouchEventListener onInterceptTouchEventListener = this.mOnInterceptTouchEventListener;
        if (onInterceptTouchEventListener != null) {
            int i = ((JSResponderHandler) onInterceptTouchEventListener).mCurrentJSResponder;
            boolean z = false;
            if (!(i == -1 || motionEvent.getAction() == 1 || getId() != i)) {
                z = true;
            }
            if (z) {
                return true;
            }
        }
        PointerEvents pointerEvents = this.mPointerEvents;
        if (pointerEvents == PointerEvents.NONE || pointerEvents == PointerEvents.BOX_ONLY) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        R$style.assertExplicitMeasureSpec(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        int i2;
        ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.mReactBackgroundDrawable;
        if (reactViewBackgroundDrawable != null && reactViewBackgroundDrawable.mLayoutDirection != (i2 = this.mLayoutDirection)) {
            reactViewBackgroundDrawable.mLayoutDirection = i2;
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        PointerEvents pointerEvents = this.mPointerEvents;
        return (pointerEvents == PointerEvents.NONE || pointerEvents == PointerEvents.BOX_NONE) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        UiThreadUtil.assertOnUiThread();
        ViewGroupDrawingOrderHelper viewGroupDrawingOrderHelper = this.mDrawingOrderHelper;
        Objects.requireNonNull(viewGroupDrawingOrderHelper);
        if (ViewGroupManager.getViewZIndex(view) != null) {
            viewGroupDrawingOrderHelper.mNumberOfChildrenWithZIndex--;
        }
        viewGroupDrawingOrderHelper.mDrawingOrderIndices = null;
        setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i) {
        UiThreadUtil.assertOnUiThread();
        ViewGroupDrawingOrderHelper viewGroupDrawingOrderHelper = this.mDrawingOrderHelper;
        View childAt = getChildAt(i);
        Objects.requireNonNull(viewGroupDrawingOrderHelper);
        if (ViewGroupManager.getViewZIndex(childAt) != null) {
            viewGroupDrawingOrderHelper.mNumberOfChildrenWithZIndex--;
        }
        viewGroupDrawingOrderHelper.mDrawingOrderIndices = null;
        setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        super.removeViewAt(i);
    }

    public void removeViewWithSubviewClippingEnabled(View view) {
        UiThreadUtil.assertOnUiThread();
        R$dimen.assertCondition(this.mRemoveClippedSubviews);
        R$dimen.assertNotNull(this.mClippingRect);
        R$dimen.assertNotNull(this.mAllChildren);
        view.removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        int i = this.mAllChildrenCount;
        View[] viewArr = this.mAllChildren;
        R$dimen.assertNotNull(viewArr);
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                i2 = -1;
                break;
            } else if (viewArr[i2] == view) {
                break;
            } else {
                i2++;
            }
        }
        if (this.mAllChildren[i2].getParent() != null) {
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                if (this.mAllChildren[i4].getParent() == null) {
                    i3++;
                }
            }
            super.removeViewsInLayout(i2 - i3, 1);
        }
        View[] viewArr2 = this.mAllChildren;
        R$dimen.assertNotNull(viewArr2);
        int i5 = this.mAllChildrenCount;
        int i6 = i5 - 1;
        if (i2 == i6) {
            this.mAllChildrenCount = i6;
            viewArr2[i6] = null;
        } else if (i2 < 0 || i2 >= i5) {
            throw new IndexOutOfBoundsException();
        } else {
            System.arraycopy(viewArr2, i2 + 1, viewArr2, i2, (i5 - i2) - 1);
            int i7 = this.mAllChildrenCount - 1;
            this.mAllChildrenCount = i7;
            viewArr2[i7] = null;
        }
    }

    @Override // android.view.View, android.view.ViewParent
    @SuppressLint({"MissingSuperCall"})
    public void requestLayout() {
    }

    public void setBackfaceVisibility(String str) {
        this.mBackfaceVisibility = str;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setBackfaceVisibilityDependantOpacity() {
        if (this.mBackfaceVisibility.equals("visible")) {
            setAlpha(this.mBackfaceOpacity);
            return;
        }
        float rotationX = getRotationX();
        float rotationY = getRotationY();
        if (rotationX >= -90.0f && rotationX < 90.0f && rotationY >= -90.0f && rotationY < 90.0f) {
            setAlpha(this.mBackfaceOpacity);
        } else {
            setAlpha(0.0f);
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        throw new UnsupportedOperationException("This method is not supported for ReactViewGroup instances");
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (i != 0 || this.mReactBackgroundDrawable != null) {
            ReactViewBackgroundDrawable orCreateReactViewBackground = getOrCreateReactViewBackground();
            orCreateReactViewBackground.mColor = i;
            orCreateReactViewBackground.invalidateSelf();
        }
    }

    public void setBorderColor(int i, float f, float f2) {
        getOrCreateReactViewBackground().setBorderColor(i, f, f2);
    }

    public void setBorderRadius(float f) {
        ReactViewBackgroundDrawable orCreateReactViewBackground = getOrCreateReactViewBackground();
        if (!R$style.floatsEqual(orCreateReactViewBackground.mBorderRadius, f)) {
            orCreateReactViewBackground.mBorderRadius = f;
            orCreateReactViewBackground.mNeedUpdatePathForBorderRadius = true;
            orCreateReactViewBackground.invalidateSelf();
        }
    }

    public void setBorderStyle(String str) {
        getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setBorderWidth(int i, float f) {
        getOrCreateReactViewBackground().setBorderWidth(i, f);
    }

    public void setHitSlopRect(Rect rect) {
        this.mHitSlopRect = rect;
    }

    public void setNeedsOffscreenAlphaCompositing(boolean z) {
        this.mNeedsOffscreenAlphaCompositing = z;
    }

    @Override // com.facebook.react.touch.ReactInterceptingViewGroup
    public void setOnInterceptTouchEventListener(OnInterceptTouchEventListener onInterceptTouchEventListener) {
        this.mOnInterceptTouchEventListener = onInterceptTouchEventListener;
    }

    public void setOpacityIfPossible(float f) {
        this.mBackfaceOpacity = f;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setOverflow(String str) {
        this.mOverflow = str;
        invalidate();
    }

    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z != this.mRemoveClippedSubviews) {
            this.mRemoveClippedSubviews = z;
            if (z) {
                Rect rect = new Rect();
                this.mClippingRect = rect;
                ReactClippingViewGroupHelper.calculateClippingRect(this, rect);
                int childCount = getChildCount();
                this.mAllChildrenCount = childCount;
                this.mAllChildren = new View[Math.max(12, childCount)];
                this.mChildrenLayoutChangeListener = new ChildrenLayoutChangeListener(this, null);
                for (int i = 0; i < this.mAllChildrenCount; i++) {
                    View childAt = getChildAt(i);
                    this.mAllChildren[i] = childAt;
                    childAt.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
                }
                updateClippingRect();
                return;
            }
            R$dimen.assertNotNull(this.mClippingRect);
            R$dimen.assertNotNull(this.mAllChildren);
            R$dimen.assertNotNull(this.mChildrenLayoutChangeListener);
            for (int i2 = 0; i2 < this.mAllChildrenCount; i2++) {
                this.mAllChildren[i2].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
            }
            getDrawingRect(this.mClippingRect);
            updateClippingToRect(this.mClippingRect);
            this.mAllChildren = null;
            this.mClippingRect = null;
            this.mAllChildrenCount = 0;
            this.mChildrenLayoutChangeListener = null;
        }
    }

    public void setTranslucentBackgroundDrawable(Drawable drawable) {
        super.setBackground(null);
        if (this.mReactBackgroundDrawable != null && drawable != null) {
            super.setBackground(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, drawable}));
        } else if (drawable != null) {
            super.setBackground(drawable);
        }
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            R$dimen.assertNotNull(this.mClippingRect);
            R$dimen.assertNotNull(this.mAllChildren);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            updateClippingToRect(this.mClippingRect);
        }
    }

    public final void updateClippingToRect(Rect rect) {
        R$dimen.assertNotNull(this.mAllChildren);
        int i = 0;
        for (int i2 = 0; i2 < this.mAllChildrenCount; i2++) {
            updateSubviewClipStatus(rect, i2, i);
            if (this.mAllChildren[i2].getParent() == null) {
                i++;
            }
        }
    }

    @Override // com.facebook.react.uimanager.ReactZIndexedViewGroup
    public void updateDrawingOrder() {
        ViewGroupDrawingOrderHelper viewGroupDrawingOrderHelper = this.mDrawingOrderHelper;
        viewGroupDrawingOrderHelper.mNumberOfChildrenWithZIndex = 0;
        for (int i = 0; i < viewGroupDrawingOrderHelper.mViewGroup.getChildCount(); i++) {
            if (ViewGroupManager.getViewZIndex(viewGroupDrawingOrderHelper.mViewGroup.getChildAt(i)) != null) {
                viewGroupDrawingOrderHelper.mNumberOfChildrenWithZIndex++;
            }
        }
        viewGroupDrawingOrderHelper.mDrawingOrderIndices = null;
        setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        invalidate();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005f, code lost:
        if (r7 != false) goto L_0x0061;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateSubviewClipStatus(android.graphics.Rect r7, int r8, int r9) {
        /*
            r6 = this;
            com.facebook.react.bridge.UiThreadUtil.assertOnUiThread()
            android.view.View[] r0 = r6.mAllChildren
            androidx.recyclerview.R$dimen.assertNotNull(r0)
            android.view.View[] r0 = (android.view.View[]) r0
            r0 = r0[r8]
            android.graphics.Rect r1 = com.facebook.react.views.view.ReactViewGroup.sHelperRect
            int r2 = r0.getLeft()
            int r3 = r0.getTop()
            int r4 = r0.getRight()
            int r5 = r0.getBottom()
            r1.set(r2, r3, r4, r5)
            int r2 = r1.left
            int r3 = r1.top
            int r4 = r1.right
            int r1 = r1.bottom
            boolean r7 = r7.intersects(r2, r3, r4, r1)
            android.view.animation.Animation r1 = r0.getAnimation()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x003d
            boolean r1 = r1.hasEnded()
            if (r1 != 0) goto L_0x003d
            r1 = 1
            goto L_0x003e
        L_0x003d:
            r1 = 0
        L_0x003e:
            if (r7 != 0) goto L_0x004d
            android.view.ViewParent r4 = r0.getParent()
            if (r4 == 0) goto L_0x004d
            if (r1 != 0) goto L_0x004d
            int r8 = r8 - r9
            super.removeViewsInLayout(r8, r3)
            goto L_0x0061
        L_0x004d:
            if (r7 == 0) goto L_0x005f
            android.view.ViewParent r1 = r0.getParent()
            if (r1 != 0) goto L_0x005f
            int r8 = r8 - r9
            android.view.ViewGroup$LayoutParams r7 = com.facebook.react.views.view.ReactViewGroup.sDefaultLayoutParam
            super.addViewInLayout(r0, r8, r7, r3)
            r6.invalidate()
            goto L_0x0061
        L_0x005f:
            if (r7 == 0) goto L_0x0062
        L_0x0061:
            r2 = 1
        L_0x0062:
            if (r2 == 0) goto L_0x0073
            boolean r7 = r0 instanceof com.facebook.react.uimanager.ReactClippingViewGroup
            if (r7 == 0) goto L_0x0073
            com.facebook.react.uimanager.ReactClippingViewGroup r0 = (com.facebook.react.uimanager.ReactClippingViewGroup) r0
            boolean r7 = r0.getRemoveClippedSubviews()
            if (r7 == 0) goto L_0x0073
            r0.updateClippingRect()
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewGroup.updateSubviewClipStatus(android.graphics.Rect, int, int):void");
    }

    public void setBorderRadius(float f, int i) {
        getOrCreateReactViewBackground().setRadius(f, i);
    }
}
