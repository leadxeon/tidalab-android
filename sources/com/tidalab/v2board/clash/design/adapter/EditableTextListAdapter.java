package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.databinding.AdapterEditableTextListBinding;
import com.tidalab.v2board.clash.design.preference.TextAdapter;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
/* compiled from: EditableTextListAdapter.kt */
/* loaded from: classes.dex */
public final class EditableTextListAdapter<T> extends RecyclerView.Adapter<Holder> {
    public final TextAdapter<T> adapter;
    public final Context context;
    public final List<T> values;

    /* compiled from: EditableTextListAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final AdapterEditableTextListBinding binding;

        public Holder(AdapterEditableTextListBinding adapterEditableTextListBinding) {
            super(adapterEditableTextListBinding.mRoot);
            this.binding = adapterEditableTextListBinding;
        }
    }

    public EditableTextListAdapter(Context context, List<T> list, TextAdapter<T> textAdapter) {
        this.context = context;
        this.values = list;
        this.adapter = textAdapter;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.values.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        Holder holder2 = holder;
        final T t = this.values.get(i);
        holder2.binding.textView.setText(this.adapter.from(t));
        holder2.binding.deleteView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$EditableTextListAdapter$S25d1dQwjN3DgFBfzgh1fvj6Ht0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditableTextListAdapter editableTextListAdapter = EditableTextListAdapter.this;
                int indexOf = editableTextListAdapter.values.indexOf(t);
                if (indexOf >= 0) {
                    editableTextListAdapter.values.remove(indexOf);
                    editableTextListAdapter.mObservable.notifyItemRangeRemoved(indexOf, 1);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        int i2 = AdapterEditableTextListBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        return new Holder((AdapterEditableTextListBinding) ViewDataBinding.inflateInternal(from, R.layout.adapter_editable_text_list, viewGroup, false, null));
    }
}
