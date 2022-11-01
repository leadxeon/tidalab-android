package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.NinePatchDrawable;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
/* loaded from: classes.dex */
public class RoundedNinePatchDrawable extends RoundedDrawable {
    public RoundedNinePatchDrawable(NinePatchDrawable ninePatchDrawable) {
        super(ninePatchDrawable);
    }

    @Override // com.facebook.drawee.drawable.RoundedDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        FrescoSystrace.isTracing();
        if (!shouldRound()) {
            super.draw(canvas);
            FrescoSystrace.isTracing();
            return;
        }
        updateTransform();
        updatePath();
        canvas.clipPath(this.mPath);
        super.draw(canvas);
        FrescoSystrace.isTracing();
    }
}
