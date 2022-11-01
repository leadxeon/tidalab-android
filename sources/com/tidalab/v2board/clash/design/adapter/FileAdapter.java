package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.databinding.AdapterFileBinding;
import com.tidalab.v2board.clash.design.model.File;
import com.tidalab.v2board.clash.design.ui.ObservableCurrentTime;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
/* compiled from: FileAdapter.kt */
/* loaded from: classes.dex */
public final class FileAdapter extends RecyclerView.Adapter<Holder> {
    public final Context context;
    public final ObservableCurrentTime currentTime = new ObservableCurrentTime();
    public List<File> files = EmptyList.INSTANCE;
    public final Function1<File, Unit> more;
    public final Function1<File, Unit> open;

    /* compiled from: FileAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final AdapterFileBinding binding;

        public Holder(AdapterFileBinding adapterFileBinding) {
            super(adapterFileBinding.mRoot);
            this.binding = adapterFileBinding;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FileAdapter(Context context, Function1<? super File, Unit> function1, Function1<? super File, Unit> function12) {
        this.context = context;
        this.open = function1;
        this.more = function12;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.files.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        final File file = this.files.get(i);
        AdapterFileBinding adapterFileBinding = holder.binding;
        adapterFileBinding.setFile(file);
        adapterFileBinding.setOpen(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$FileAdapter$bEhps84wOKClz6EgadVrS9eCIlc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FileAdapter fileAdapter = FileAdapter.this;
                fileAdapter.open.invoke(file);
            }
        });
        adapterFileBinding.setMore(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$FileAdapter$4PMFjyydp3Yd61StD-pa2Z5BNOs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FileAdapter fileAdapter = FileAdapter.this;
                fileAdapter.more.invoke(file);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        int i2 = AdapterFileBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        AdapterFileBinding adapterFileBinding = (AdapterFileBinding) ViewDataBinding.inflateInternal(from, R.layout.adapter_file, viewGroup, false, null);
        adapterFileBinding.setCurrentTime(this.currentTime);
        Unit unit = Unit.INSTANCE;
        return new Holder(adapterFileBinding);
    }
}
