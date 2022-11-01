package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
/* loaded from: classes.dex */
public class IllegalViewOperationException extends JSApplicationCausedNativeException {
    public View mView;

    public IllegalViewOperationException(String str) {
        super(str);
    }

    public IllegalViewOperationException(String str, View view, Throwable th) {
        super(str, th);
        this.mView = view;
    }
}
