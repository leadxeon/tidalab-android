package com.tidalab.v2board.clash.design.ui;

import androidx.databinding.BaseObservable;
/* compiled from: ObservableCurrentTime.kt */
/* loaded from: classes.dex */
public final class ObservableCurrentTime extends BaseObservable {
    public long value = System.currentTimeMillis();

    public final void update() {
        this.value = System.currentTimeMillis();
        notifyPropertyChanged(33);
    }
}
