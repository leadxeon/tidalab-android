package com.facebook.react.views.modal;

import android.graphics.Point;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
/* loaded from: classes.dex */
public class ModalHostShadowNode extends LayoutShadowNode {
    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public void addChildAt(ReactShadowNodeImpl reactShadowNodeImpl, int i) {
        super.addChildAt(reactShadowNodeImpl, i);
        Point modalHostSize = ModalHostHelper.getModalHostSize(getThemedContext());
        reactShadowNodeImpl.mYogaNode.setWidth(modalHostSize.x);
        reactShadowNodeImpl.mYogaNode.setHeight(modalHostSize.y);
    }
}
