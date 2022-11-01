package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.tidalab.v2board.clash.foss.R;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class SnackbarContentLayout extends LinearLayout implements ContentViewCallback {
    public Button actionView;
    public int maxInlineActionWidth;
    public int maxWidth;
    public TextView messageView;

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SnackbarLayout);
        this.maxWidth = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        this.maxInlineActionWidth = obtainStyledAttributes.getDimensionPixelSize(7, -1);
        obtainStyledAttributes.recycle();
    }

    public Button getActionView() {
        return this.actionView;
    }

    public TextView getMessageView() {
        return this.messageView;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.messageView = (TextView) findViewById(R.id.snackbar_text);
        this.actionView = (Button) findViewById(R.id.snackbar_action);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0055, code lost:
        if (updateViewsWithinLayout(1, r0, r0 - r1) != false) goto L_0x0062;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0060, code lost:
        if (updateViewsWithinLayout(0, r0, r0) != false) goto L_0x0062;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0062, code lost:
        r3 = true;
     */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            super.onMeasure(r8, r9)
            int r0 = r7.maxWidth
            if (r0 <= 0) goto L_0x0018
            int r0 = r7.getMeasuredWidth()
            int r1 = r7.maxWidth
            if (r0 <= r1) goto L_0x0018
            r8 = 1073741824(0x40000000, float:2.0)
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r8)
            super.onMeasure(r8, r9)
        L_0x0018:
            android.content.res.Resources r0 = r7.getResources()
            r1 = 2131165324(0x7f07008c, float:1.7944862E38)
            int r0 = r0.getDimensionPixelSize(r1)
            android.content.res.Resources r1 = r7.getResources()
            r2 = 2131165323(0x7f07008b, float:1.794486E38)
            int r1 = r1.getDimensionPixelSize(r2)
            android.widget.TextView r2 = r7.messageView
            android.text.Layout r2 = r2.getLayout()
            int r2 = r2.getLineCount()
            r3 = 0
            r4 = 1
            if (r2 <= r4) goto L_0x003e
            r2 = 1
            goto L_0x003f
        L_0x003e:
            r2 = 0
        L_0x003f:
            if (r2 == 0) goto L_0x0058
            int r5 = r7.maxInlineActionWidth
            if (r5 <= 0) goto L_0x0058
            android.widget.Button r5 = r7.actionView
            int r5 = r5.getMeasuredWidth()
            int r6 = r7.maxInlineActionWidth
            if (r5 <= r6) goto L_0x0058
            int r1 = r0 - r1
            boolean r0 = r7.updateViewsWithinLayout(r4, r0, r1)
            if (r0 == 0) goto L_0x0063
            goto L_0x0062
        L_0x0058:
            if (r2 == 0) goto L_0x005b
            goto L_0x005c
        L_0x005b:
            r0 = r1
        L_0x005c:
            boolean r0 = r7.updateViewsWithinLayout(r3, r0, r0)
            if (r0 == 0) goto L_0x0063
        L_0x0062:
            r3 = 1
        L_0x0063:
            if (r3 == 0) goto L_0x0068
            super.onMeasure(r8, r9)
        L_0x0068:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.snackbar.SnackbarContentLayout.onMeasure(int, int):void");
    }

    public void setMaxInlineActionWidth(int i) {
        this.maxInlineActionWidth = i;
    }

    public final boolean updateViewsWithinLayout(int i, int i2, int i3) {
        boolean z;
        if (i != getOrientation()) {
            setOrientation(i);
            z = true;
        } else {
            z = false;
        }
        if (this.messageView.getPaddingTop() == i2 && this.messageView.getPaddingBottom() == i3) {
            return z;
        }
        TextView textView = this.messageView;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if (textView.isPaddingRelative()) {
            textView.setPaddingRelative(textView.getPaddingStart(), i2, textView.getPaddingEnd(), i3);
            return true;
        }
        textView.setPadding(textView.getPaddingLeft(), i2, textView.getPaddingRight(), i3);
        return true;
    }
}
