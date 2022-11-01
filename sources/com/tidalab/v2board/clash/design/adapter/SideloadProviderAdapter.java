package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.databinding.AdapterSideloadProviderBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.AppInfo;
import com.tidalab.v2board.clash.foss.R;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SideloadProviderAdapter.kt */
/* loaded from: classes.dex */
public final class SideloadProviderAdapter extends RecyclerView.Adapter<Holder> {
    public final List<AppInfo> apps;
    public final Context context;
    public String selectedPackageName;

    /* compiled from: SideloadProviderAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final AdapterSideloadProviderBinding binding;

        public Holder(AdapterSideloadProviderBinding adapterSideloadProviderBinding) {
            super(adapterSideloadProviderBinding.mRoot);
            this.binding = adapterSideloadProviderBinding;
        }
    }

    public SideloadProviderAdapter(Context context, List<AppInfo> list, String str) {
        this.context = context;
        this.apps = list;
        this.selectedPackageName = str;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.apps.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, final int i) {
        Holder holder2 = holder;
        final AppInfo appInfo = this.apps.get(i);
        holder2.binding.setAppInfo(appInfo);
        holder2.binding.setSelected(Intrinsics.areEqual(this.selectedPackageName, appInfo.packageName));
        holder2.binding.mRoot.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$SideloadProviderAdapter$roviO8Ow5zJfKt9SI8JEoRDJMCc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SideloadProviderAdapter sideloadProviderAdapter = SideloadProviderAdapter.this;
                AppInfo appInfo2 = appInfo;
                int i2 = i;
                Iterator<AppInfo> it = sideloadProviderAdapter.apps.iterator();
                int i3 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i3 = -1;
                        break;
                    } else if (Intrinsics.areEqual(it.next().packageName, sideloadProviderAdapter.selectedPackageName)) {
                        break;
                    } else {
                        i3++;
                    }
                }
                sideloadProviderAdapter.selectedPackageName = appInfo2.packageName;
                if (i3 >= 0) {
                    sideloadProviderAdapter.notifyItemChanged(i3);
                }
                sideloadProviderAdapter.mObservable.notifyItemRangeChanged(i2, 1, null);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        ViewGroup root = InputKt.getRoot(this.context);
        int i2 = AdapterSideloadProviderBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        return new Holder((AdapterSideloadProviderBinding) ViewDataBinding.inflateInternal(from, R.layout.adapter_sideload_provider, root, false, null));
    }
}
