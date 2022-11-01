package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.databinding.AdapterProfileBinding;
import com.tidalab.v2board.clash.design.ui.ObservableCurrentTime;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.Profile;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
/* compiled from: ProfileAdapter.kt */
/* loaded from: classes.dex */
public final class ProfileAdapter extends RecyclerView.Adapter<Holder> {
    public final Context context;
    public final Function1<Profile, Unit> onClicked;
    public final Function1<Profile, Unit> onMenuClicked;
    public final ObservableCurrentTime currentTime = new ObservableCurrentTime();
    public List<Profile> profiles = EmptyList.INSTANCE;

    /* compiled from: ProfileAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final AdapterProfileBinding binding;

        public Holder(AdapterProfileBinding adapterProfileBinding) {
            super(adapterProfileBinding.mRoot);
            this.binding = adapterProfileBinding;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ProfileAdapter(Context context, Function1<? super Profile, Unit> function1, Function1<? super Profile, Unit> function12) {
        this.context = context;
        this.onClicked = function1;
        this.onMenuClicked = function12;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.profiles.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        final Profile profile = this.profiles.get(i);
        AdapterProfileBinding adapterProfileBinding = holder.binding;
        if (profile != adapterProfileBinding.mProfile) {
            adapterProfileBinding.setProfile(profile);
            adapterProfileBinding.setClicked(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$ProfileAdapter$AYRO40ebYSwd8-nG3MpdhLMvWng
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProfileAdapter profileAdapter = ProfileAdapter.this;
                    profileAdapter.onClicked.invoke(profile);
                }
            });
            adapterProfileBinding.setMenu(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$ProfileAdapter$-YRNK7WhB0jTjquLC2w4grTrzao
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProfileAdapter profileAdapter = ProfileAdapter.this;
                    profileAdapter.onMenuClicked.invoke(profile);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        int i2 = AdapterProfileBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        AdapterProfileBinding adapterProfileBinding = (AdapterProfileBinding) ViewDataBinding.inflateInternal(from, R.layout.adapter_profile, viewGroup, false, null);
        adapterProfileBinding.setCurrentTime(this.currentTime);
        Unit unit = Unit.INSTANCE;
        return new Holder(adapterProfileBinding);
    }
}
