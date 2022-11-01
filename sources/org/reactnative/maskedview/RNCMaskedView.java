package org.reactnative.maskedview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.View;
import com.facebook.react.views.view.ReactViewGroup;
/* loaded from: classes.dex */
public class RNCMaskedView extends ReactViewGroup {
    public Bitmap mBitmapMask = null;
    public Paint mPaint = new Paint(1);
    public PorterDuffXfermode mPorterDuffXferMode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    public RNCMaskedView(Context context) {
        super(context);
        setLayerType(1, null);
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        updateBitmapMask();
        if (this.mBitmapMask != null) {
            this.mPaint.setXfermode(this.mPorterDuffXferMode);
            canvas.drawBitmap(this.mBitmapMask, 0.0f, 0.0f, this.mPaint);
            this.mPaint.setXfermode(null);
        }
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            updateBitmapMask();
        }
    }

    public final void updateBitmapMask() {
        Bitmap bitmap;
        Bitmap bitmap2 = this.mBitmapMask;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        View childAt = getChildAt(0);
        childAt.setVisibility(0);
        childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
        if (childAt.getMeasuredWidth() <= 0 || childAt.getMeasuredHeight() <= 0) {
            bitmap = null;
        } else {
            bitmap = Bitmap.createBitmap(childAt.getMeasuredWidth(), childAt.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            childAt.draw(new Canvas(bitmap));
        }
        this.mBitmapMask = bitmap;
        childAt.setVisibility(4);
    }
}
