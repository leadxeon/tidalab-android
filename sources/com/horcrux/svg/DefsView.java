package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class DefsView extends DefinitionView {
    public DefsView(ReactContext reactContext) {
        super(reactContext);
    }

    @Override // com.horcrux.svg.DefinitionView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
    }

    @Override // com.horcrux.svg.VirtualView
    public void saveDefinition() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).saveDefinition();
            }
        }
    }
}
