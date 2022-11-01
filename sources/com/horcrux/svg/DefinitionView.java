package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.ReactContext;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class DefinitionView extends VirtualView {
    public DefinitionView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
    }

    @Override // com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        return null;
    }

    @Override // com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        return -1;
    }

    @Override // com.horcrux.svg.VirtualView
    public boolean isResponsible() {
        return false;
    }
}
