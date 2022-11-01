package com.tidalab.v2board.clash.design.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import com.horcrux.svg.PathParser;
import kotlin.ranges.RangesKt___RangesKt;
/* compiled from: ProxyView.kt */
/* loaded from: classes.dex */
public final class ProxyView extends View {
    public ProxyViewState state;

    public ProxyView(Context context, ProxyViewConfig proxyViewConfig) {
        super(context);
        setBackground(PathParser.getDrawableCompat(context, proxyViewConfig.clickableBackground));
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        ProxyViewState proxyViewState = this.state;
        if (proxyViewState == null) {
            super.draw(canvas);
            return;
        }
        if (proxyViewState.update(false)) {
            postInvalidate();
        }
        float width = getWidth();
        float height = getHeight();
        Paint paint = proxyViewState.paint;
        paint.reset();
        paint.setColor(proxyViewState.background);
        paint.setStyle(Paint.Style.FILL);
        if (proxyViewState.config.singleLine) {
            canvas.drawRect(0.0f, 0.0f, width, height, paint);
        } else {
            Path path = proxyViewState.path;
            path.reset();
            ProxyViewConfig proxyViewConfig = proxyViewState.config;
            float f = proxyViewConfig.layoutPadding;
            float f2 = proxyViewConfig.cardRadius;
            path.addRoundRect(f, f, width - f, height - f, f2, f2, Path.Direction.CW);
            ProxyViewConfig proxyViewConfig2 = proxyViewState.config;
            float f3 = proxyViewConfig2.cardRadius;
            float f4 = proxyViewConfig2.cardOffset;
            paint.setShadowLayer(f3, f4, f4, proxyViewConfig2.shadow);
            canvas.drawPath(path, paint);
            canvas.clipPath(path);
        }
        super.draw(canvas);
    }

    public final ProxyViewState getState() {
        return this.state;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ProxyViewState proxyViewState = this.state;
        if (proxyViewState != null) {
            Paint paint = proxyViewState.paint;
            float width = getWidth();
            float height = getHeight();
            paint.setTextSize(proxyViewState.config.textSize);
            String str = proxyViewState.delayText;
            ProxyViewConfig proxyViewConfig = proxyViewState.config;
            float f = 2;
            int breakText = paint.breakText(str, false, RangesKt___RangesKt.coerceAtLeast((width - (proxyViewConfig.layoutPadding * f)) - (proxyViewConfig.contentPadding * f), 0.0f), null);
            proxyViewState.paint.getTextBounds(proxyViewState.delayText, 0, breakText, proxyViewState.rect);
            int width2 = proxyViewState.rect.width();
            ProxyViewConfig proxyViewConfig2 = proxyViewState.config;
            float f2 = width2;
            float coerceAtLeast = RangesKt___RangesKt.coerceAtLeast((((width - (proxyViewConfig2.layoutPadding * f)) - (proxyViewConfig2.contentPadding * f)) - f2) - (proxyViewConfig2.textMargin * 2), 0.0f);
            int breakText2 = paint.breakText(proxyViewState.title, false, coerceAtLeast, null);
            int breakText3 = paint.breakText(proxyViewState.subtitle, false, coerceAtLeast, null);
            float ascent = (paint.ascent() + paint.descent()) / f;
            paint.reset();
            paint.setTextSize(proxyViewState.config.textSize);
            paint.setAntiAlias(true);
            paint.setColor(proxyViewState.controls);
            ProxyViewConfig proxyViewConfig3 = proxyViewState.config;
            canvas.drawText(proxyViewState.delayText, 0, breakText, ((width - proxyViewConfig3.layoutPadding) - proxyViewConfig3.contentPadding) - f2, (height / 2.0f) - ascent, paint);
            ProxyViewConfig proxyViewConfig4 = proxyViewState.config;
            float f3 = proxyViewConfig4.layoutPadding;
            canvas.drawText(proxyViewState.title, 0, breakText2, f3 + proxyViewConfig4.contentPadding, (((height - (f3 * f)) / 3.0f) + f3) - ascent, paint);
            ProxyViewConfig proxyViewConfig5 = proxyViewState.config;
            float f4 = proxyViewConfig5.layoutPadding;
            canvas.drawText(proxyViewState.subtitle, 0, breakText3, f4 + proxyViewConfig5.contentPadding, ((((height - (f4 * f)) / 3.0f) * f) + f4) - ascent, paint);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0076  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r10, int r11) {
        /*
            r9 = this;
            com.tidalab.v2board.clash.design.component.ProxyViewState r0 = r9.state
            if (r0 != 0) goto L_0x0008
            super.onMeasure(r10, r11)
            return
        L_0x0008:
            int r1 = android.view.View.MeasureSpec.getMode(r10)
            java.lang.String r2 = "invalid measure spec"
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r4) goto L_0x002a
            if (r1 == 0) goto L_0x001f
            if (r1 != r3) goto L_0x0019
            goto L_0x002a
        L_0x0019:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r10.<init>(r2)
            throw r10
        L_0x001f:
            android.content.res.Resources r10 = r9.getResources()
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()
            int r10 = r10.widthPixels
            goto L_0x002e
        L_0x002a:
            int r10 = android.view.View.MeasureSpec.getSize(r10)
        L_0x002e:
            android.graphics.Paint r1 = r0.paint
            r1.reset()
            com.tidalab.v2board.clash.design.component.ProxyViewConfig r5 = r0.config
            float r5 = r5.textSize
            r1.setTextSize(r5)
            r5 = 0
            r6 = 1
            android.graphics.Rect r7 = r0.rect
            java.lang.String r8 = "Stub!"
            r1.getTextBounds(r8, r5, r6, r7)
            android.graphics.Rect r1 = r0.rect
            int r1 = r1.height()
            com.tidalab.v2board.clash.design.component.ProxyViewConfig r0 = r0.config
            float r5 = r0.layoutPadding
            r6 = 2
            float r7 = (float) r6
            float r5 = r5 * r7
            float r8 = r0.contentPadding
            float r8 = r8 * r7
            float r8 = r8 + r5
            int r1 = r1 * 2
            float r1 = (float) r1
            float r8 = r8 + r1
            int r0 = r0.textMargin
            float r0 = (float) r0
            float r8 = r8 + r0
            int r0 = (int) r8
            int r1 = android.view.View.MeasureSpec.getMode(r11)
            if (r1 == r4) goto L_0x0070
            if (r1 == 0) goto L_0x0077
            if (r1 != r3) goto L_0x006a
            goto L_0x0070
        L_0x006a:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r10.<init>(r2)
            throw r10
        L_0x0070:
            int r11 = android.view.View.MeasureSpec.getSize(r11)
            if (r0 <= r11) goto L_0x0077
            r0 = r11
        L_0x0077:
            r9.setMeasuredDimension(r10, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.component.ProxyView.onMeasure(int, int):void");
    }

    public final void setState(ProxyViewState proxyViewState) {
        this.state = proxyViewState;
    }
}
