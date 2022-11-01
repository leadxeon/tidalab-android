package androidx.appcompat.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import androidx.appcompat.graphics.drawable.StateListDrawable;
import androidx.core.app.AppOpsManagerCompat;
import java.util.Objects;
/* loaded from: classes.dex */
public class DrawableContainer extends Drawable implements Drawable.Callback {
    public static final /* synthetic */ int $r8$clinit = 0;
    public Runnable mAnimationRunnable;
    public BlockInvalidateCallback mBlockInvalidateCallback;
    public Drawable mCurrDrawable;
    public DrawableContainerState mDrawableContainerState;
    public long mEnterAnimationEnd;
    public long mExitAnimationEnd;
    public boolean mHasAlpha;
    public Rect mHotspotBounds;
    public Drawable mLastDrawable;
    public boolean mMutated;
    public int mAlpha = 255;
    public int mCurIndex = -1;

    /* loaded from: classes.dex */
    public static class BlockInvalidateCallback implements Drawable.Callback {
        public Drawable.Callback mCallback;

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            Drawable.Callback callback = this.mCallback;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.mCallback;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class DrawableContainerState extends Drawable.ConstantState {
        public boolean mAutoMirrored;
        public boolean mCanConstantState;
        public int mChangingConfigurations;
        public boolean mCheckedConstantSize;
        public boolean mCheckedConstantState;
        public boolean mCheckedOpacity;
        public boolean mCheckedPadding;
        public boolean mCheckedStateful;
        public int mChildrenChangingConfigurations;
        public ColorFilter mColorFilter;
        public int mConstantHeight;
        public int mConstantMinimumHeight;
        public int mConstantMinimumWidth;
        public Rect mConstantPadding;
        public boolean mConstantSize;
        public int mConstantWidth;
        public int mDensity;
        public boolean mDither;
        public SparseArray<Drawable.ConstantState> mDrawableFutures;
        public Drawable[] mDrawables;
        public int mEnterFadeDuration;
        public int mExitFadeDuration;
        public boolean mHasColorFilter;
        public boolean mHasTintList;
        public boolean mHasTintMode;
        public int mLayoutDirection;
        public boolean mMutated;
        public int mNumChildren;
        public int mOpacity;
        public final DrawableContainer mOwner;
        public Resources mSourceRes;
        public boolean mStateful;
        public ColorStateList mTintList;
        public PorterDuff.Mode mTintMode;
        public boolean mVariablePadding;

        public DrawableContainerState(DrawableContainerState drawableContainerState, DrawableContainer drawableContainer, Resources resources) {
            Resources resources2;
            this.mVariablePadding = false;
            this.mConstantSize = false;
            this.mDither = true;
            this.mEnterFadeDuration = 0;
            this.mExitFadeDuration = 0;
            this.mOwner = drawableContainer;
            Rect rect = null;
            if (resources != null) {
                resources2 = resources;
            } else {
                resources2 = drawableContainerState != null ? drawableContainerState.mSourceRes : null;
            }
            this.mSourceRes = resources2;
            int i = drawableContainerState != null ? drawableContainerState.mDensity : 0;
            int i2 = DrawableContainer.$r8$clinit;
            i = resources != null ? resources.getDisplayMetrics().densityDpi : i;
            i = i == 0 ? 160 : i;
            this.mDensity = i;
            if (drawableContainerState != null) {
                this.mChangingConfigurations = drawableContainerState.mChangingConfigurations;
                this.mChildrenChangingConfigurations = drawableContainerState.mChildrenChangingConfigurations;
                this.mCheckedConstantState = true;
                this.mCanConstantState = true;
                this.mVariablePadding = drawableContainerState.mVariablePadding;
                this.mConstantSize = drawableContainerState.mConstantSize;
                this.mDither = drawableContainerState.mDither;
                this.mMutated = drawableContainerState.mMutated;
                this.mLayoutDirection = drawableContainerState.mLayoutDirection;
                this.mEnterFadeDuration = drawableContainerState.mEnterFadeDuration;
                this.mExitFadeDuration = drawableContainerState.mExitFadeDuration;
                this.mAutoMirrored = drawableContainerState.mAutoMirrored;
                this.mColorFilter = drawableContainerState.mColorFilter;
                this.mHasColorFilter = drawableContainerState.mHasColorFilter;
                this.mTintList = drawableContainerState.mTintList;
                this.mTintMode = drawableContainerState.mTintMode;
                this.mHasTintList = drawableContainerState.mHasTintList;
                this.mHasTintMode = drawableContainerState.mHasTintMode;
                if (drawableContainerState.mDensity == i) {
                    if (drawableContainerState.mCheckedPadding) {
                        this.mConstantPadding = drawableContainerState.mConstantPadding != null ? new Rect(drawableContainerState.mConstantPadding) : rect;
                        this.mCheckedPadding = true;
                    }
                    if (drawableContainerState.mCheckedConstantSize) {
                        this.mConstantWidth = drawableContainerState.mConstantWidth;
                        this.mConstantHeight = drawableContainerState.mConstantHeight;
                        this.mConstantMinimumWidth = drawableContainerState.mConstantMinimumWidth;
                        this.mConstantMinimumHeight = drawableContainerState.mConstantMinimumHeight;
                        this.mCheckedConstantSize = true;
                    }
                }
                if (drawableContainerState.mCheckedOpacity) {
                    this.mOpacity = drawableContainerState.mOpacity;
                    this.mCheckedOpacity = true;
                }
                if (drawableContainerState.mCheckedStateful) {
                    this.mStateful = drawableContainerState.mStateful;
                    this.mCheckedStateful = true;
                }
                Drawable[] drawableArr = drawableContainerState.mDrawables;
                this.mDrawables = new Drawable[drawableArr.length];
                this.mNumChildren = drawableContainerState.mNumChildren;
                SparseArray<Drawable.ConstantState> sparseArray = drawableContainerState.mDrawableFutures;
                if (sparseArray != null) {
                    this.mDrawableFutures = sparseArray.clone();
                } else {
                    this.mDrawableFutures = new SparseArray<>(this.mNumChildren);
                }
                int i3 = this.mNumChildren;
                for (int i4 = 0; i4 < i3; i4++) {
                    if (drawableArr[i4] != null) {
                        Drawable.ConstantState constantState = drawableArr[i4].getConstantState();
                        if (constantState != null) {
                            this.mDrawableFutures.put(i4, constantState);
                        } else {
                            this.mDrawables[i4] = drawableArr[i4];
                        }
                    }
                }
                return;
            }
            this.mDrawables = new Drawable[10];
            this.mNumChildren = 0;
        }

        public final int addChild(Drawable drawable) {
            int i = this.mNumChildren;
            if (i >= this.mDrawables.length) {
                int i2 = i + 10;
                StateListDrawable.StateListState stateListState = (StateListDrawable.StateListState) this;
                Drawable[] drawableArr = new Drawable[i2];
                Drawable[] drawableArr2 = stateListState.mDrawables;
                if (drawableArr2 != null) {
                    System.arraycopy(drawableArr2, 0, drawableArr, 0, i);
                }
                stateListState.mDrawables = drawableArr;
                int[][] iArr = new int[i2];
                System.arraycopy(stateListState.mStateSets, 0, iArr, 0, i);
                stateListState.mStateSets = iArr;
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.mOwner);
            this.mDrawables[i] = drawable;
            this.mNumChildren++;
            this.mChildrenChangingConfigurations = drawable.getChangingConfigurations() | this.mChildrenChangingConfigurations;
            this.mCheckedOpacity = false;
            this.mCheckedStateful = false;
            this.mConstantPadding = null;
            this.mCheckedPadding = false;
            this.mCheckedConstantSize = false;
            this.mCheckedConstantState = false;
            return i;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            int i = this.mNumChildren;
            Drawable[] drawableArr = this.mDrawables;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.mDrawableFutures.get(i2);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return true;
                    }
                } else if (drawable.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        public void computeConstantSize() {
            this.mCheckedConstantSize = true;
            createAllFutures();
            int i = this.mNumChildren;
            Drawable[] drawableArr = this.mDrawables;
            this.mConstantHeight = -1;
            this.mConstantWidth = -1;
            this.mConstantMinimumHeight = 0;
            this.mConstantMinimumWidth = 0;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.mConstantWidth) {
                    this.mConstantWidth = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.mConstantHeight) {
                    this.mConstantHeight = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.mConstantMinimumWidth) {
                    this.mConstantMinimumWidth = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.mConstantMinimumHeight) {
                    this.mConstantMinimumHeight = minimumHeight;
                }
            }
        }

        public final void createAllFutures() {
            SparseArray<Drawable.ConstantState> sparseArray = this.mDrawableFutures;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    int keyAt = this.mDrawableFutures.keyAt(i);
                    Drawable[] drawableArr = this.mDrawables;
                    Drawable newDrawable = this.mDrawableFutures.valueAt(i).newDrawable(this.mSourceRes);
                    if (Build.VERSION.SDK_INT >= 23) {
                        AppOpsManagerCompat.setLayoutDirection(newDrawable, this.mLayoutDirection);
                    }
                    Drawable mutate = newDrawable.mutate();
                    mutate.setCallback(this.mOwner);
                    drawableArr[keyAt] = mutate;
                }
                this.mDrawableFutures = null;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
        }

        public final Drawable getChild(int i) {
            int indexOfKey;
            Drawable drawable = this.mDrawables[i];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.mDrawableFutures;
            if (sparseArray == null || (indexOfKey = sparseArray.indexOfKey(i)) < 0) {
                return null;
            }
            Drawable newDrawable = this.mDrawableFutures.valueAt(indexOfKey).newDrawable(this.mSourceRes);
            if (Build.VERSION.SDK_INT >= 23) {
                AppOpsManagerCompat.setLayoutDirection(newDrawable, this.mLayoutDirection);
            }
            Drawable mutate = newDrawable.mutate();
            mutate.setCallback(this.mOwner);
            this.mDrawables[i] = mutate;
            this.mDrawableFutures.removeAt(indexOfKey);
            if (this.mDrawableFutures.size() == 0) {
                this.mDrawableFutures = null;
            }
            return mutate;
        }

        public abstract void mutate();

        public final void updateDensity(Resources resources) {
            if (resources != null) {
                this.mSourceRes = resources;
                int i = DrawableContainer.$r8$clinit;
                int i2 = resources.getDisplayMetrics().densityDpi;
                if (i2 == 0) {
                    i2 = 160;
                }
                int i3 = this.mDensity;
                this.mDensity = i2;
                if (i3 != i2) {
                    this.mCheckedConstantSize = false;
                    this.mCheckedPadding = false;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void animate(boolean r14) {
        /*
            r13 = this;
            r0 = 1
            r13.mHasAlpha = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.mCurrDrawable
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L_0x0038
            long r9 = r13.mEnterAnimationEnd
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L_0x003a
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L_0x0022
            int r9 = r13.mAlpha
            r3.setAlpha(r9)
            r13.mEnterAnimationEnd = r7
            goto L_0x003a
        L_0x0022:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r10 = (int) r9
            androidx.appcompat.graphics.drawable.DrawableContainer$DrawableContainerState r9 = r13.mDrawableContainerState
            int r9 = r9.mEnterFadeDuration
            int r10 = r10 / r9
            int r9 = 255 - r10
            int r10 = r13.mAlpha
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = 1
            goto L_0x003b
        L_0x0038:
            r13.mEnterAnimationEnd = r7
        L_0x003a:
            r3 = 0
        L_0x003b:
            android.graphics.drawable.Drawable r9 = r13.mLastDrawable
            if (r9 == 0) goto L_0x0065
            long r10 = r13.mExitAnimationEnd
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x0067
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L_0x0052
            r9.setVisible(r6, r6)
            r0 = 0
            r13.mLastDrawable = r0
            r13.mExitAnimationEnd = r7
            goto L_0x0067
        L_0x0052:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            androidx.appcompat.graphics.drawable.DrawableContainer$DrawableContainerState r4 = r13.mDrawableContainerState
            int r4 = r4.mExitFadeDuration
            int r3 = r3 / r4
            int r4 = r13.mAlpha
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L_0x0068
        L_0x0065:
            r13.mExitAnimationEnd = r7
        L_0x0067:
            r0 = r3
        L_0x0068:
            if (r14 == 0) goto L_0x0074
            if (r0 == 0) goto L_0x0074
            java.lang.Runnable r14 = r13.mAnimationRunnable
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.graphics.drawable.DrawableContainer.animate(boolean):void");
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        Objects.requireNonNull(drawableContainerState);
        if (theme != null) {
            drawableContainerState.createAllFutures();
            int i = drawableContainerState.mNumChildren;
            Drawable[] drawableArr = drawableContainerState.mDrawables;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2] != null && drawableArr[i2].canApplyTheme()) {
                    drawableArr[i2].applyTheme(theme);
                    drawableContainerState.mChildrenChangingConfigurations |= drawableArr[i2].getChangingConfigurations();
                }
            }
            drawableContainerState.updateDensity(theme.getResources());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.mDrawableContainerState.canApplyTheme();
    }

    /* renamed from: cloneConstantState */
    public DrawableContainerState mo0cloneConstantState() {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.mLastDrawable;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mDrawableContainerState.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        boolean z = false;
        if (!drawableContainerState.mCheckedConstantState) {
            drawableContainerState.createAllFutures();
            drawableContainerState.mCheckedConstantState = true;
            int i = drawableContainerState.mNumChildren;
            Drawable[] drawableArr = drawableContainerState.mDrawables;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    drawableContainerState.mCanConstantState = true;
                    z = true;
                    break;
                } else if (drawableArr[i2].getConstantState() == null) {
                    drawableContainerState.mCanConstantState = false;
                    break;
                } else {
                    i2++;
                }
            }
        } else {
            z = drawableContainerState.mCanConstantState;
        }
        if (!z) {
            return null;
        }
        this.mDrawableContainerState.mChangingConfigurations = getChangingConfigurations();
        return this.mDrawableContainerState;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.mCurrDrawable;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.mHotspotBounds;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState.mConstantSize) {
            if (!drawableContainerState.mCheckedConstantSize) {
                drawableContainerState.computeConstantSize();
            }
            return drawableContainerState.mConstantHeight;
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState.mConstantSize) {
            if (!drawableContainerState.mCheckedConstantSize) {
                drawableContainerState.computeConstantSize();
            }
            return drawableContainerState.mConstantWidth;
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState.mConstantSize) {
            if (!drawableContainerState.mCheckedConstantSize) {
                drawableContainerState.computeConstantSize();
            }
            return drawableContainerState.mConstantMinimumHeight;
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState.mConstantSize) {
            if (!drawableContainerState.mCheckedConstantSize) {
                drawableContainerState.computeConstantSize();
            }
            return drawableContainerState.mConstantMinimumWidth;
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.mCurrDrawable;
        int i = -2;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState.mCheckedOpacity) {
            return drawableContainerState.mOpacity;
        }
        drawableContainerState.createAllFutures();
        int i2 = drawableContainerState.mNumChildren;
        Drawable[] drawableArr = drawableContainerState.mDrawables;
        if (i2 > 0) {
            i = drawableArr[0].getOpacity();
        }
        for (int i3 = 1; i3 < i2; i3++) {
            i = Drawable.resolveOpacity(i, drawableArr[i3].getOpacity());
        }
        drawableContainerState.mOpacity = i;
        drawableContainerState.mCheckedOpacity = true;
        return i;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        boolean z;
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        Rect rect2 = null;
        boolean z2 = true;
        if (!drawableContainerState.mVariablePadding) {
            Rect rect3 = drawableContainerState.mConstantPadding;
            if (rect3 != null || drawableContainerState.mCheckedPadding) {
                rect2 = rect3;
            } else {
                drawableContainerState.createAllFutures();
                Rect rect4 = new Rect();
                int i = drawableContainerState.mNumChildren;
                Drawable[] drawableArr = drawableContainerState.mDrawables;
                for (int i2 = 0; i2 < i; i2++) {
                    if (drawableArr[i2].getPadding(rect4)) {
                        if (rect2 == null) {
                            rect2 = new Rect(0, 0, 0, 0);
                        }
                        int i3 = rect4.left;
                        if (i3 > rect2.left) {
                            rect2.left = i3;
                        }
                        int i4 = rect4.top;
                        if (i4 > rect2.top) {
                            rect2.top = i4;
                        }
                        int i5 = rect4.right;
                        if (i5 > rect2.right) {
                            rect2.right = i5;
                        }
                        int i6 = rect4.bottom;
                        if (i6 > rect2.bottom) {
                            rect2.bottom = i6;
                        }
                    }
                }
                drawableContainerState.mCheckedPadding = true;
                drawableContainerState.mConstantPadding = rect2;
            }
        }
        if (rect2 != null) {
            rect.set(rect2);
            z = (((rect2.left | rect2.top) | rect2.bottom) | rect2.right) != 0;
        } else {
            Drawable drawable = this.mCurrDrawable;
            if (drawable != null) {
                z = drawable.getPadding(rect);
            } else {
                z = super.getPadding(rect);
            }
        }
        if (!this.mDrawableContainerState.mAutoMirrored || AppOpsManagerCompat.getLayoutDirection(this) != 1) {
            z2 = false;
        }
        if (z2) {
            int i7 = rect.left;
            rect.left = rect.right;
            rect.right = i7;
        }
        return z;
    }

    public final void initializeDrawableForDisplay(Drawable drawable) {
        if (this.mBlockInvalidateCallback == null) {
            this.mBlockInvalidateCallback = new BlockInvalidateCallback();
        }
        BlockInvalidateCallback blockInvalidateCallback = this.mBlockInvalidateCallback;
        blockInvalidateCallback.mCallback = drawable.getCallback();
        drawable.setCallback(blockInvalidateCallback);
        try {
            if (this.mDrawableContainerState.mEnterFadeDuration <= 0 && this.mHasAlpha) {
                drawable.setAlpha(this.mAlpha);
            }
            DrawableContainerState drawableContainerState = this.mDrawableContainerState;
            if (drawableContainerState.mHasColorFilter) {
                drawable.setColorFilter(drawableContainerState.mColorFilter);
            } else {
                if (drawableContainerState.mHasTintList) {
                    drawable.setTintList(drawableContainerState.mTintList);
                }
                DrawableContainerState drawableContainerState2 = this.mDrawableContainerState;
                if (drawableContainerState2.mHasTintMode) {
                    drawable.setTintMode(drawableContainerState2.mTintMode);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.mDrawableContainerState.mDither);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (Build.VERSION.SDK_INT >= 23) {
                AppOpsManagerCompat.setLayoutDirection(drawable, AppOpsManagerCompat.getLayoutDirection(this));
            }
            drawable.setAutoMirrored(this.mDrawableContainerState.mAutoMirrored);
            Rect rect = this.mHotspotBounds;
            if (rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            BlockInvalidateCallback blockInvalidateCallback2 = this.mBlockInvalidateCallback;
            Drawable.Callback callback = blockInvalidateCallback2.mCallback;
            blockInvalidateCallback2.mCallback = null;
            drawable.setCallback(callback);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState != null) {
            drawableContainerState.mCheckedOpacity = false;
            drawableContainerState.mCheckedStateful = false;
        }
        if (drawable == this.mCurrDrawable && getCallback() != null) {
            getCallback().invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.mDrawableContainerState.mAutoMirrored;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        Drawable drawable = this.mLastDrawable;
        boolean z = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.mLastDrawable = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.mHasAlpha) {
                this.mCurrDrawable.setAlpha(this.mAlpha);
            }
        }
        if (this.mExitAnimationEnd != 0) {
            this.mExitAnimationEnd = 0L;
            z = true;
        }
        if (this.mEnterAnimationEnd != 0) {
            this.mEnterAnimationEnd = 0L;
        }
        if (z) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            DrawableContainerState cloneConstantState = mo0cloneConstantState();
            cloneConstantState.mutate();
            setConstantState(cloneConstantState);
            this.mMutated = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        int i2 = this.mCurIndex;
        int i3 = drawableContainerState.mNumChildren;
        Drawable[] drawableArr = drawableContainerState.mDrawables;
        boolean z = false;
        for (int i4 = 0; i4 < i3; i4++) {
            if (drawableArr[i4] != null) {
                z = Build.VERSION.SDK_INT >= 23 ? AppOpsManagerCompat.setLayoutDirection(drawableArr[i4], i) : false;
                if (i4 == i2) {
                }
            }
        }
        drawableContainerState.mLayoutDirection = i;
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            return drawable2.setLevel(i);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable == this.mCurrDrawable && getCallback() != null) {
            getCallback().scheduleDrawable(this, runnable, j);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean selectDrawable(int r10) {
        /*
            r9 = this;
            int r0 = r9.mCurIndex
            r1 = 0
            if (r10 != r0) goto L_0x0006
            return r1
        L_0x0006:
            long r2 = android.os.SystemClock.uptimeMillis()
            androidx.appcompat.graphics.drawable.DrawableContainer$DrawableContainerState r0 = r9.mDrawableContainerState
            int r0 = r0.mExitFadeDuration
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L_0x002e
            android.graphics.drawable.Drawable r0 = r9.mLastDrawable
            if (r0 == 0) goto L_0x001a
            r0.setVisible(r1, r1)
        L_0x001a:
            android.graphics.drawable.Drawable r0 = r9.mCurrDrawable
            if (r0 == 0) goto L_0x0029
            r9.mLastDrawable = r0
            androidx.appcompat.graphics.drawable.DrawableContainer$DrawableContainerState r0 = r9.mDrawableContainerState
            int r0 = r0.mExitFadeDuration
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.mExitAnimationEnd = r0
            goto L_0x0035
        L_0x0029:
            r9.mLastDrawable = r4
            r9.mExitAnimationEnd = r5
            goto L_0x0035
        L_0x002e:
            android.graphics.drawable.Drawable r0 = r9.mCurrDrawable
            if (r0 == 0) goto L_0x0035
            r0.setVisible(r1, r1)
        L_0x0035:
            if (r10 < 0) goto L_0x0055
            androidx.appcompat.graphics.drawable.DrawableContainer$DrawableContainerState r0 = r9.mDrawableContainerState
            int r1 = r0.mNumChildren
            if (r10 >= r1) goto L_0x0055
            android.graphics.drawable.Drawable r0 = r0.getChild(r10)
            r9.mCurrDrawable = r0
            r9.mCurIndex = r10
            if (r0 == 0) goto L_0x005a
            androidx.appcompat.graphics.drawable.DrawableContainer$DrawableContainerState r10 = r9.mDrawableContainerState
            int r10 = r10.mEnterFadeDuration
            if (r10 <= 0) goto L_0x0051
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.mEnterAnimationEnd = r2
        L_0x0051:
            r9.initializeDrawableForDisplay(r0)
            goto L_0x005a
        L_0x0055:
            r9.mCurrDrawable = r4
            r10 = -1
            r9.mCurIndex = r10
        L_0x005a:
            long r0 = r9.mEnterAnimationEnd
            r10 = 1
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 != 0) goto L_0x0067
            long r0 = r9.mExitAnimationEnd
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 == 0) goto L_0x0079
        L_0x0067:
            java.lang.Runnable r0 = r9.mAnimationRunnable
            if (r0 != 0) goto L_0x0073
            androidx.appcompat.graphics.drawable.DrawableContainer$1 r0 = new androidx.appcompat.graphics.drawable.DrawableContainer$1
            r0.<init>()
            r9.mAnimationRunnable = r0
            goto L_0x0076
        L_0x0073:
            r9.unscheduleSelf(r0)
        L_0x0076:
            r9.animate(r10)
        L_0x0079:
            r9.invalidateSelf()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.graphics.drawable.DrawableContainer.selectDrawable(int):boolean");
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (!this.mHasAlpha || this.mAlpha != i) {
            this.mHasAlpha = true;
            this.mAlpha = i;
            Drawable drawable = this.mCurrDrawable;
            if (drawable == null) {
                return;
            }
            if (this.mEnterAnimationEnd == 0) {
                drawable.setAlpha(i);
            } else {
                animate(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState.mAutoMirrored != z) {
            drawableContainerState.mAutoMirrored = z;
            Drawable drawable = this.mCurrDrawable;
            if (drawable != null) {
                drawable.setAutoMirrored(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        drawableContainerState.mHasColorFilter = true;
        if (drawableContainerState.mColorFilter != colorFilter) {
            drawableContainerState.mColorFilter = colorFilter;
            Drawable drawable = this.mCurrDrawable;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    public void setConstantState(DrawableContainerState drawableContainerState) {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        if (drawableContainerState.mDither != z) {
            drawableContainerState.mDither = z;
            Drawable drawable = this.mCurrDrawable;
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect = this.mHotspotBounds;
        if (rect == null) {
            this.mHotspotBounds = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
        Drawable drawable = this.mCurrDrawable;
        if (drawable != null) {
            drawable.setHotspotBounds(i, i2, i3, i4);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        drawableContainerState.mHasTintList = true;
        if (drawableContainerState.mTintList != colorStateList) {
            drawableContainerState.mTintList = colorStateList;
            AppOpsManagerCompat.setTintList(this.mCurrDrawable, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        DrawableContainerState drawableContainerState = this.mDrawableContainerState;
        drawableContainerState.mHasTintMode = true;
        if (drawableContainerState.mTintMode != mode) {
            drawableContainerState.mTintMode = mode;
            AppOpsManagerCompat.setTintMode(this.mCurrDrawable, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.mLastDrawable;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.mCurrDrawable;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable == this.mCurrDrawable && getCallback() != null) {
            getCallback().unscheduleDrawable(this, runnable);
        }
    }
}
