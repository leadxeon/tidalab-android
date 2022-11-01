package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.core.model.Provider;
import com.tidalab.v2board.clash.design.model.ProviderState;
import com.tidalab.v2board.clash.design.ui.ObservableCurrentTime;
/* loaded from: classes.dex */
public abstract class AdapterProviderBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final TextView elapsedView;
    public final FrameLayout endView;
    public ObservableCurrentTime mCurrentTime;
    public Provider mProvider;
    public ProviderState mState;
    public View.OnClickListener mUpdate;
    public final ImageView updateView;

    public AdapterProviderBinding(Object obj, View view, int i, TextView textView, FrameLayout frameLayout, ImageView imageView) {
        super(obj, view, i);
        this.elapsedView = textView;
        this.endView = frameLayout;
        this.updateView = imageView;
    }

    public abstract void setCurrentTime(ObservableCurrentTime observableCurrentTime);

    public abstract void setProvider(Provider provider);

    public abstract void setState(ProviderState providerState);

    public abstract void setUpdate(View.OnClickListener onClickListener);
}
