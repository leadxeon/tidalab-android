package com.tidalab.v2board.clash.design.generated.callback;

import android.view.View;
/* loaded from: classes.dex */
public final class OnClickListener implements View.OnClickListener {
    public final Listener mListener;
    public final int mSourceId;

    /* loaded from: classes.dex */
    public interface Listener {
        void _internalCallbackOnClick(int i, View view);
    }

    public OnClickListener(Listener listener, int i) {
        this.mListener = listener;
        this.mSourceId = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.mListener._internalCallbackOnClick(this.mSourceId, view);
    }
}
