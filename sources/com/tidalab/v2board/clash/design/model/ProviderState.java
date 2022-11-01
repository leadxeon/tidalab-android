package com.tidalab.v2board.clash.design.model;

import androidx.databinding.BaseObservable;
import com.tidalab.v2board.clash.core.model.Provider;
/* compiled from: ProviderState.kt */
/* loaded from: classes.dex */
public final class ProviderState extends BaseObservable {
    public final Provider provider;
    public long updatedAt;
    public boolean updating;

    public ProviderState(Provider provider, long j, boolean z) {
        this.provider = provider;
        this.updatedAt = j;
        this.updating = z;
    }

    public final void setUpdating(boolean z) {
        this.updating = z;
        notifyPropertyChanged(32);
    }
}
