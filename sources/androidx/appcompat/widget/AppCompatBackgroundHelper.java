package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class AppCompatBackgroundHelper {
    public TintInfo mBackgroundTint;
    public TintInfo mInternalBackgroundTint;
    public TintInfo mTmpInfo;
    public final View mView;
    public int mBackgroundResId = -1;
    public final AppCompatDrawableManager mDrawableManager = AppCompatDrawableManager.get();

    public AppCompatBackgroundHelper(View view) {
        this.mView = view;
    }

    public void applySupportBackgroundTint() {
        Drawable background = this.mView.getBackground();
        if (background != null) {
            int i = Build.VERSION.SDK_INT;
            boolean z = true;
            if (i <= 21 ? i == 21 : this.mInternalBackgroundTint != null) {
                if (this.mTmpInfo == null) {
                    this.mTmpInfo = new TintInfo();
                }
                TintInfo tintInfo = this.mTmpInfo;
                tintInfo.mTintList = null;
                tintInfo.mHasTintList = false;
                tintInfo.mTintMode = null;
                tintInfo.mHasTintMode = false;
                View view = this.mView;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                ColorStateList backgroundTintList = view.getBackgroundTintList();
                if (backgroundTintList != null) {
                    tintInfo.mHasTintList = true;
                    tintInfo.mTintList = backgroundTintList;
                }
                PorterDuff.Mode backgroundTintMode = this.mView.getBackgroundTintMode();
                if (backgroundTintMode != null) {
                    tintInfo.mHasTintMode = true;
                    tintInfo.mTintMode = backgroundTintMode;
                }
                if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
                    AppCompatDrawableManager.tintDrawable(background, tintInfo, this.mView.getDrawableState());
                } else {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            TintInfo tintInfo2 = this.mBackgroundTint;
            if (tintInfo2 != null) {
                AppCompatDrawableManager.tintDrawable(background, tintInfo2, this.mView.getDrawableState());
                return;
            }
            TintInfo tintInfo3 = this.mInternalBackgroundTint;
            if (tintInfo3 != null) {
                AppCompatDrawableManager.tintDrawable(background, tintInfo3, this.mView.getDrawableState());
            }
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        TintInfo tintInfo = this.mBackgroundTint;
        if (tintInfo != null) {
            return tintInfo.mTintList;
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        TintInfo tintInfo = this.mBackgroundTint;
        if (tintInfo != null) {
            return tintInfo.mTintMode;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006f A[Catch: all -> 0x0077, TryCatch #0 {all -> 0x0077, blocks: (B:3:0x001b, B:5:0x0022, B:7:0x0038, B:8:0x003b, B:10:0x0044, B:12:0x0051, B:14:0x005b, B:21:0x0069, B:23:0x006f, B:26:0x0079, B:27:0x007c, B:29:0x0083, B:31:0x0095, B:33:0x009f, B:38:0x00aa, B:40:0x00b0, B:41:0x00b7), top: B:46:0x001b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void loadFromAttributes(android.util.AttributeSet r10, int r11) {
        /*
            r9 = this;
            android.view.View r0 = r9.mView
            android.content.Context r0 = r0.getContext()
            int[] r3 = androidx.appcompat.R$styleable.ViewBackgroundHelper
            r8 = 0
            androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r0, r10, r3, r11, r8)
            android.view.View r1 = r9.mView
            android.content.Context r2 = r1.getContext()
            android.content.res.TypedArray r5 = r0.mWrapped
            r7 = 0
            r4 = r10
            r6 = r11
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r1, r2, r3, r4, r5, r6, r7)
            boolean r10 = r0.hasValue(r8)     // Catch: all -> 0x0077
            r11 = -1
            if (r10 == 0) goto L_0x003b
            int r10 = r0.getResourceId(r8, r11)     // Catch: all -> 0x0077
            r9.mBackgroundResId = r10     // Catch: all -> 0x0077
            androidx.appcompat.widget.AppCompatDrawableManager r10 = r9.mDrawableManager     // Catch: all -> 0x0077
            android.view.View r1 = r9.mView     // Catch: all -> 0x0077
            android.content.Context r1 = r1.getContext()     // Catch: all -> 0x0077
            int r2 = r9.mBackgroundResId     // Catch: all -> 0x0077
            android.content.res.ColorStateList r10 = r10.getTintList(r1, r2)     // Catch: all -> 0x0077
            if (r10 == 0) goto L_0x003b
            r9.setInternalBackgroundTint(r10)     // Catch: all -> 0x0077
        L_0x003b:
            r10 = 1
            boolean r1 = r0.hasValue(r10)     // Catch: all -> 0x0077
            r2 = 21
            if (r1 == 0) goto L_0x007c
            android.view.View r1 = r9.mView     // Catch: all -> 0x0077
            android.content.res.ColorStateList r3 = r0.getColorStateList(r10)     // Catch: all -> 0x0077
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: all -> 0x0077
            r1.setBackgroundTintList(r3)     // Catch: all -> 0x0077
            if (r4 != r2) goto L_0x007c
            android.graphics.drawable.Drawable r3 = r1.getBackground()     // Catch: all -> 0x0077
            android.content.res.ColorStateList r4 = r1.getBackgroundTintList()     // Catch: all -> 0x0077
            if (r4 != 0) goto L_0x0064
            android.graphics.PorterDuff$Mode r4 = r1.getBackgroundTintMode()     // Catch: all -> 0x0077
            if (r4 == 0) goto L_0x0062
            goto L_0x0064
        L_0x0062:
            r4 = 0
            goto L_0x0065
        L_0x0064:
            r4 = 1
        L_0x0065:
            if (r3 == 0) goto L_0x007c
            if (r4 == 0) goto L_0x007c
            boolean r4 = r3.isStateful()     // Catch: all -> 0x0077
            if (r4 == 0) goto L_0x0079
            int[] r4 = r1.getDrawableState()     // Catch: all -> 0x0077
            r3.setState(r4)     // Catch: all -> 0x0077
            goto L_0x0079
        L_0x0077:
            r10 = move-exception
            goto L_0x00c0
        L_0x0079:
            r1.setBackground(r3)     // Catch: all -> 0x0077
        L_0x007c:
            r1 = 2
            boolean r3 = r0.hasValue(r1)     // Catch: all -> 0x0077
            if (r3 == 0) goto L_0x00ba
            android.view.View r3 = r9.mView     // Catch: all -> 0x0077
            int r11 = r0.getInt(r1, r11)     // Catch: all -> 0x0077
            r1 = 0
            android.graphics.PorterDuff$Mode r11 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r11, r1)     // Catch: all -> 0x0077
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: all -> 0x0077
            r3.setBackgroundTintMode(r11)     // Catch: all -> 0x0077
            if (r1 != r2) goto L_0x00ba
            android.graphics.drawable.Drawable r11 = r3.getBackground()     // Catch: all -> 0x0077
            android.content.res.ColorStateList r1 = r3.getBackgroundTintList()     // Catch: all -> 0x0077
            if (r1 != 0) goto L_0x00a5
            android.graphics.PorterDuff$Mode r1 = r3.getBackgroundTintMode()     // Catch: all -> 0x0077
            if (r1 == 0) goto L_0x00a6
        L_0x00a5:
            r8 = 1
        L_0x00a6:
            if (r11 == 0) goto L_0x00ba
            if (r8 == 0) goto L_0x00ba
            boolean r10 = r11.isStateful()     // Catch: all -> 0x0077
            if (r10 == 0) goto L_0x00b7
            int[] r10 = r3.getDrawableState()     // Catch: all -> 0x0077
            r11.setState(r10)     // Catch: all -> 0x0077
        L_0x00b7:
            r3.setBackground(r11)     // Catch: all -> 0x0077
        L_0x00ba:
            android.content.res.TypedArray r10 = r0.mWrapped
            r10.recycle()
            return
        L_0x00c0:
            android.content.res.TypedArray r11 = r0.mWrapped
            r11.recycle()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatBackgroundHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    public void onSetBackgroundDrawable() {
        this.mBackgroundResId = -1;
        setInternalBackgroundTint(null);
        applySupportBackgroundTint();
    }

    public void onSetBackgroundResource(int i) {
        this.mBackgroundResId = i;
        AppCompatDrawableManager appCompatDrawableManager = this.mDrawableManager;
        setInternalBackgroundTint(appCompatDrawableManager != null ? appCompatDrawableManager.getTintList(this.mView.getContext(), i) : null);
        applySupportBackgroundTint();
    }

    public void setInternalBackgroundTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.mInternalBackgroundTint == null) {
                this.mInternalBackgroundTint = new TintInfo();
            }
            TintInfo tintInfo = this.mInternalBackgroundTint;
            tintInfo.mTintList = colorStateList;
            tintInfo.mHasTintList = true;
        } else {
            this.mInternalBackgroundTint = null;
        }
        applySupportBackgroundTint();
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        TintInfo tintInfo = this.mBackgroundTint;
        tintInfo.mTintList = colorStateList;
        tintInfo.mHasTintList = true;
        applySupportBackgroundTint();
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        TintInfo tintInfo = this.mBackgroundTint;
        tintInfo.mTintMode = mode;
        tintInfo.mHasTintMode = true;
        applySupportBackgroundTint();
    }
}
