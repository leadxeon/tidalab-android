package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.ReactContext;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class PathView extends RenderableView {
    public Path mPath = new Path();

    public PathView(ReactContext reactContext) {
        super(reactContext);
        PathParser.mScale = this.mScale;
    }

    @Override // com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        return this.mPath;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00c9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0294 A[SYNTHETIC] */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "d")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setD(java.lang.String r30) {
        /*
            Method dump skipped, instructions count: 888
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PathView.setD(java.lang.String):void");
    }
}
