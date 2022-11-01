package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import androidx.core.app.AppOpsManagerCompat;
/* loaded from: classes.dex */
public class AppCompatCompoundButtonHelper {
    public ColorStateList mButtonTintList = null;
    public PorterDuff.Mode mButtonTintMode = null;
    public boolean mHasButtonTint = false;
    public boolean mHasButtonTintMode = false;
    public boolean mSkipNextApply;
    public final CompoundButton mView;

    public AppCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    public void applyButtonTint() {
        Drawable buttonDrawable = AppOpsManagerCompat.getButtonDrawable(this.mView);
        if (buttonDrawable == null) {
            return;
        }
        if (this.mHasButtonTint || this.mHasButtonTintMode) {
            Drawable mutate = AppOpsManagerCompat.wrap(buttonDrawable).mutate();
            if (this.mHasButtonTint) {
                mutate.setTintList(this.mButtonTintList);
            }
            if (this.mHasButtonTintMode) {
                mutate.setTintMode(this.mButtonTintMode);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.mView.getDrawableState());
            }
            this.mView.setButtonDrawable(mutate);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0059 A[Catch: all -> 0x0063, TryCatch #1 {all -> 0x0063, blocks: (B:3:0x001c, B:5:0x0022, B:7:0x0028, B:10:0x0039, B:12:0x003f, B:14:0x0045, B:15:0x0052, B:17:0x0059, B:20:0x0065, B:22:0x006c), top: B:30:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006c A[Catch: all -> 0x0063, TRY_LEAVE, TryCatch #1 {all -> 0x0063, blocks: (B:3:0x001c, B:5:0x0022, B:7:0x0028, B:10:0x0039, B:12:0x003f, B:14:0x0045, B:15:0x0052, B:17:0x0059, B:20:0x0065, B:22:0x006c), top: B:30:0x001c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void loadFromAttributes(android.util.AttributeSet r10, int r11) {
        /*
            r9 = this;
            android.widget.CompoundButton r0 = r9.mView
            android.content.Context r0 = r0.getContext()
            int[] r3 = androidx.appcompat.R$styleable.CompoundButton
            r8 = 0
            androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r0, r10, r3, r11, r8)
            android.widget.CompoundButton r1 = r9.mView
            android.content.Context r2 = r1.getContext()
            android.content.res.TypedArray r5 = r0.mWrapped
            r7 = 0
            r4 = r10
            r6 = r11
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r1, r2, r3, r4, r5, r6, r7)
            r10 = 1
            boolean r11 = r0.hasValue(r10)     // Catch: all -> 0x0063
            if (r11 == 0) goto L_0x0036
            int r11 = r0.getResourceId(r10, r8)     // Catch: all -> 0x0063
            if (r11 == 0) goto L_0x0036
            android.widget.CompoundButton r1 = r9.mView     // Catch: NotFoundException -> 0x0036, all -> 0x0063
            android.content.Context r2 = r1.getContext()     // Catch: NotFoundException -> 0x0036, all -> 0x0063
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r2, r11)     // Catch: NotFoundException -> 0x0036, all -> 0x0063
            r1.setButtonDrawable(r11)     // Catch: NotFoundException -> 0x0036, all -> 0x0063
            goto L_0x0037
        L_0x0036:
            r10 = 0
        L_0x0037:
            if (r10 != 0) goto L_0x0052
            boolean r10 = r0.hasValue(r8)     // Catch: all -> 0x0063
            if (r10 == 0) goto L_0x0052
            int r10 = r0.getResourceId(r8, r8)     // Catch: all -> 0x0063
            if (r10 == 0) goto L_0x0052
            android.widget.CompoundButton r11 = r9.mView     // Catch: all -> 0x0063
            android.content.Context r1 = r11.getContext()     // Catch: all -> 0x0063
            android.graphics.drawable.Drawable r10 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r1, r10)     // Catch: all -> 0x0063
            r11.setButtonDrawable(r10)     // Catch: all -> 0x0063
        L_0x0052:
            r10 = 2
            boolean r11 = r0.hasValue(r10)     // Catch: all -> 0x0063
            if (r11 == 0) goto L_0x0065
            android.widget.CompoundButton r11 = r9.mView     // Catch: all -> 0x0063
            android.content.res.ColorStateList r10 = r0.getColorStateList(r10)     // Catch: all -> 0x0063
            r11.setButtonTintList(r10)     // Catch: all -> 0x0063
            goto L_0x0065
        L_0x0063:
            r10 = move-exception
            goto L_0x0081
        L_0x0065:
            r10 = 3
            boolean r11 = r0.hasValue(r10)     // Catch: all -> 0x0063
            if (r11 == 0) goto L_0x007b
            android.widget.CompoundButton r11 = r9.mView     // Catch: all -> 0x0063
            r1 = -1
            int r10 = r0.getInt(r10, r1)     // Catch: all -> 0x0063
            r1 = 0
            android.graphics.PorterDuff$Mode r10 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r10, r1)     // Catch: all -> 0x0063
            r11.setButtonTintMode(r10)     // Catch: all -> 0x0063
        L_0x007b:
            android.content.res.TypedArray r10 = r0.mWrapped
            r10.recycle()
            return
        L_0x0081:
            android.content.res.TypedArray r11 = r0.mWrapped
            r11.recycle()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCompoundButtonHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }
}
