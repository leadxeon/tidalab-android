package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.adapter.AppAdapter;
import com.tidalab.v2board.clash.design.databinding.AdapterAppBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.AppInfo;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
import java.util.Set;
import kotlin.collections.EmptyList;
/* compiled from: AppAdapter.kt */
/* loaded from: classes.dex */
public final class AppAdapter extends RecyclerView.Adapter<Holder> {
    public List<AppInfo> apps = EmptyList.INSTANCE;
    public final Context context;
    public final Set<String> selected;

    /* compiled from: AppAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final AdapterAppBinding binding;

        public Holder(AdapterAppBinding adapterAppBinding) {
            super(adapterAppBinding.mRoot);
            this.binding = adapterAppBinding;
        }
    }

    public AppAdapter(Context context, Set<String> set) {
        this.context = context;
        this.selected = set;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.apps.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        final Holder holder2 = holder;
        final AppInfo appInfo = this.apps.get(i);
        holder2.binding.setApp(appInfo);
        holder2.binding.setSelected(this.selected.contains(appInfo.packageName));
        holder2.binding.mRoot.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$AppAdapter$USONkuoJWeqdF9wOd5xXpxNsD-Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AppAdapter.Holder holder3 = AppAdapter.Holder.this;
                AppAdapter appAdapter = this;
                AppInfo appInfo2 = appInfo;
                if (holder3.binding.mSelected) {
                    appAdapter.selected.remove(appInfo2.packageName);
                    holder3.binding.setSelected(false);
                    return;
                }
                appAdapter.selected.add(appInfo2.packageName);
                holder3.binding.setSelected(true);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        ViewGroup root = InputKt.getRoot(this.context);
        int i2 = AdapterAppBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        return new Holder((AdapterAppBinding) ViewDataBinding.inflateInternal(from, R.layout.adapter_app, root, false, null));
    }
}
