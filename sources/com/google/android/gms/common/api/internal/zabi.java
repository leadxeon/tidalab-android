package com.google.android.gms.common.api.internal;

import android.os.Handler;
import com.google.android.gms.common.api.internal.BackgroundDetector;
/* loaded from: classes.dex */
public final class zabi implements BackgroundDetector.BackgroundStateChangeListener {
    public final /* synthetic */ GoogleApiManager zaim;

    public zabi(GoogleApiManager googleApiManager) {
        this.zaim = googleApiManager;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        Handler handler = this.zaim.handler;
        handler.sendMessage(handler.obtainMessage(1, Boolean.valueOf(z)));
    }
}
