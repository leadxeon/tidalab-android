package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.AppOpsManagerCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import java.util.Date;
/* loaded from: classes.dex */
public class AdapterLogMessageBindingImpl extends AdapterLogMessageBinding {
    public long mDirtyFlags = -1;
    public final LinearLayout mboundView0;
    public final TextView mboundView1;
    public final TextView mboundView2;
    public final TextView mboundView3;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdapterLogMessageBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = ViewDataBinding.mapBindings(dataBindingComponent, view, 4, null, null);
        LinearLayout linearLayout = (LinearLayout) mapBindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) mapBindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) mapBindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) mapBindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        view.setTag(R.id.dataBinding, this);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        String str;
        String str2;
        Date date;
        LogMessage.Level level;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LogMessage logMessage = this.mMessage;
        String str3 = null;
        int i = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        if (i != 0) {
            if (logMessage != null) {
                level = logMessage.level;
                date = logMessage.time;
                str = logMessage.message;
            } else {
                level = null;
                date = null;
                str = null;
            }
            if (level != null) {
                str3 = level.name();
            }
            str2 = InputKt.format(date, this.mRoot.getContext(), false, true);
        } else {
            str2 = null;
            str = null;
        }
        if (i != 0) {
            AppOpsManagerCompat.setText(this.mboundView1, str3);
            AppOpsManagerCompat.setText(this.mboundView2, str2);
            AppOpsManagerCompat.setText(this.mboundView3, str);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.tidalab.v2board.clash.design.databinding.AdapterLogMessageBinding
    public void setMessage(LogMessage logMessage) {
        this.mMessage = logMessage;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(17);
        requestRebind();
    }
}
