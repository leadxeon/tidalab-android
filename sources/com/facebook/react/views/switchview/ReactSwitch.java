package com.facebook.react.views.switchview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.SwitchCompat;
/* loaded from: classes.dex */
public class ReactSwitch extends SwitchCompat {
    public boolean mAllowChange = true;
    public Integer mTrackColorForFalse = null;
    public Integer mTrackColorForTrue = null;

    public ReactSwitch(Context context) {
        super(context, null);
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (!this.mAllowChange || isChecked() == z) {
            super.setChecked(isChecked());
            return;
        }
        this.mAllowChange = false;
        super.setChecked(z);
        Integer num = this.mTrackColorForTrue;
        if (num != null || this.mTrackColorForFalse != null) {
            if (!z) {
                num = this.mTrackColorForFalse;
            }
            setTrackColor(num);
        }
    }

    public void setOn(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
            Integer num = this.mTrackColorForTrue;
            if (!(num == null && this.mTrackColorForFalse == null)) {
                if (!z) {
                    num = this.mTrackColorForFalse;
                }
                setTrackColor(num);
            }
        }
        this.mAllowChange = true;
    }

    public void setThumbColor(Integer num) {
        Drawable thumbDrawable = super.getThumbDrawable();
        if (num == null) {
            thumbDrawable.clearColorFilter();
        } else {
            thumbDrawable.setColorFilter(num.intValue(), PorterDuff.Mode.MULTIPLY);
        }
    }

    public void setTrackColor(Integer num) {
        Drawable trackDrawable = super.getTrackDrawable();
        if (num == null) {
            trackDrawable.clearColorFilter();
        } else {
            trackDrawable.setColorFilter(num.intValue(), PorterDuff.Mode.MULTIPLY);
        }
    }
}
