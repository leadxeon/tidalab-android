package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.databinding.AdapterEditableTextMapBinding;
import com.tidalab.v2board.clash.design.preference.TextAdapter;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
import kotlin.Pair;
/* compiled from: EditableTextMapAdapter.kt */
/* loaded from: classes.dex */
public final class EditableTextMapAdapter<K, V> extends RecyclerView.Adapter<Holder> {
    public final Context context;
    public final TextAdapter<K> keyAdapter;
    public final TextAdapter<V> valueAdapter;
    public final List<Pair<K, V>> values;

    /* compiled from: EditableTextMapAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final AdapterEditableTextMapBinding binding;

        public Holder(AdapterEditableTextMapBinding adapterEditableTextMapBinding) {
            super(adapterEditableTextMapBinding.mRoot);
            this.binding = adapterEditableTextMapBinding;
        }
    }

    public EditableTextMapAdapter(Context context, List<Pair<K, V>> list, TextAdapter<K> textAdapter, TextAdapter<V> textAdapter2) {
        this.context = context;
        this.values = list;
        this.keyAdapter = textAdapter;
        this.valueAdapter = textAdapter2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.values.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        Holder holder2 = holder;
        final Pair<K, V> pair = this.values.get(i);
        holder2.binding.keyView.setText(this.keyAdapter.from(pair.first));
        holder2.binding.valueView.setText(this.valueAdapter.from(pair.second));
        holder2.binding.deleteView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$EditableTextMapAdapter$4UQKghln0Qcvu-j6MkDvNTFqhEU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditableTextMapAdapter editableTextMapAdapter = EditableTextMapAdapter.this;
                int indexOf = editableTextMapAdapter.values.indexOf(pair);
                if (indexOf >= 0) {
                    editableTextMapAdapter.values.remove(indexOf);
                    editableTextMapAdapter.mObservable.notifyItemRangeRemoved(indexOf, 1);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        int i2 = AdapterEditableTextMapBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        return new Holder((AdapterEditableTextMapBinding) ViewDataBinding.inflateInternal(from, R.layout.adapter_editable_text_map, viewGroup, false, null));
    }
}
