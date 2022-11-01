package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.databinding.AdapterProfileProviderBinding;
import com.tidalab.v2board.clash.design.model.ProfileProvider;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
/* compiled from: ProfileProviderAdapter.kt */
/* loaded from: classes.dex */
public final class ProfileProviderAdapter extends RecyclerView.Adapter<Holder> {
    public final Context context;
    public final Function1<ProfileProvider, Boolean> detail;
    public List<? extends ProfileProvider> providers = EmptyList.INSTANCE;
    public final Function1<ProfileProvider, Unit> select;

    /* compiled from: ProfileProviderAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final AdapterProfileProviderBinding binding;

        public Holder(AdapterProfileProviderBinding adapterProfileProviderBinding) {
            super(adapterProfileProviderBinding.mRoot);
            this.binding = adapterProfileProviderBinding;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ProfileProviderAdapter(Context context, Function1<? super ProfileProvider, Unit> function1, Function1<? super ProfileProvider, Boolean> function12) {
        this.context = context;
        this.select = function1;
        this.detail = function12;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.providers.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        final ProfileProvider profileProvider = (ProfileProvider) this.providers.get(i);
        AdapterProfileProviderBinding adapterProfileProviderBinding = holder.binding;
        adapterProfileProviderBinding.setProvider(profileProvider);
        View view = adapterProfileProviderBinding.mRoot;
        view.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$ProfileProviderAdapter$Z5szwTsUbS2yoX2naOPlDUNeTvQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ProfileProviderAdapter profileProviderAdapter = ProfileProviderAdapter.this;
                profileProviderAdapter.select.invoke(profileProvider);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$ProfileProviderAdapter$8oR7w1Aoh-mQLxOvp8DlLUFRLLc
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                ProfileProviderAdapter profileProviderAdapter = ProfileProviderAdapter.this;
                return profileProviderAdapter.detail.invoke(profileProvider).booleanValue();
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        int i2 = AdapterProfileProviderBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        return new Holder((AdapterProfileProviderBinding) ViewDataBinding.inflateInternal(from, R.layout.adapter_profile_provider, viewGroup, false, null));
    }
}
