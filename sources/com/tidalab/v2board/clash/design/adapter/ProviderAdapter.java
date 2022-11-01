package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.core.model.Provider;
import com.tidalab.v2board.clash.design.databinding.AdapterProviderBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.ProviderState;
import com.tidalab.v2board.clash.design.ui.ObservableCurrentTime;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
/* compiled from: ProviderAdapter.kt */
/* loaded from: classes.dex */
public final class ProviderAdapter extends RecyclerView.Adapter<Holder> {
    public final Context context;
    public final ObservableCurrentTime currentTime = new ObservableCurrentTime();
    public final Function2<Integer, Provider, Unit> requestUpdate;
    public final List<ProviderState> states;

    /* compiled from: ProviderAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final AdapterProviderBinding binding;

        public Holder(AdapterProviderBinding adapterProviderBinding) {
            super(adapterProviderBinding.mRoot);
            this.binding = adapterProviderBinding;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ProviderAdapter(Context context, List<Provider> list, Function2<? super Integer, ? super Provider, Unit> function2) {
        this.context = context;
        this.requestUpdate = function2;
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(list, 10));
        for (Provider provider : list) {
            arrayList.add(new ProviderState(provider, provider.updatedAt, false));
        }
        this.states = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.states.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, final int i) {
        Holder holder2 = holder;
        final ProviderState providerState = this.states.get(i);
        holder2.binding.setProvider(providerState.provider);
        holder2.binding.setState(providerState);
        holder2.binding.setUpdate(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$ProviderAdapter$DRDa1UN-aqNhESsYEGk58Hk_4sI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProviderState providerState2 = ProviderState.this;
                ProviderAdapter providerAdapter = this;
                int i2 = i;
                providerState2.setUpdating(true);
                providerAdapter.requestUpdate.invoke(Integer.valueOf(i2), providerState2.provider);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        int i2 = AdapterProviderBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        AdapterProviderBinding adapterProviderBinding = (AdapterProviderBinding) ViewDataBinding.inflateInternal(from, R.layout.adapter_provider, viewGroup, false, null);
        adapterProviderBinding.setCurrentTime(this.currentTime);
        Unit unit = Unit.INSTANCE;
        return new Holder(adapterProviderBinding);
    }
}
