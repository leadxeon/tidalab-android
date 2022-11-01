package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.core.model.LogMessage;
/* loaded from: classes.dex */
public abstract class AdapterLogMessageBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public LogMessage mMessage;

    public AdapterLogMessageBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public abstract void setMessage(LogMessage logMessage);
}
