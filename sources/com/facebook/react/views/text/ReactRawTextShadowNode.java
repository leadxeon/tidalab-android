package com.facebook.react.views.text;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.annotations.ReactProp;
/* loaded from: classes.dex */
public class ReactRawTextShadowNode extends ReactShadowNodeImpl {
    public String mText = null;

    @ReactProp(name = "text")
    public void setText(String str) {
        this.mText = str;
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getViewClass());
        sb.append(" [text: ");
        return GeneratedOutlineSupport.outline11(sb, this.mText, "]");
    }
}
