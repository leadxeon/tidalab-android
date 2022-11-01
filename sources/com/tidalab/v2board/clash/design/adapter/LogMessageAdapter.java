package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.design.databinding.AdapterLogMessageBinding;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
/* compiled from: LogMessageAdapter.kt */
/* loaded from: classes.dex */
public final class LogMessageAdapter extends RecyclerView.Adapter<Holder> {
    public final Context context;
    public final Function1<LogMessage, Unit> copy;
    public List<LogMessage> messages = EmptyList.INSTANCE;

    /* compiled from: LogMessageAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final AdapterLogMessageBinding binding;

        public Holder(AdapterLogMessageBinding adapterLogMessageBinding) {
            super(adapterLogMessageBinding.mRoot);
            this.binding = adapterLogMessageBinding;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LogMessageAdapter(Context context, Function1<? super LogMessage, Unit> function1) {
        this.context = context;
        this.copy = function1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.messages.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        Holder holder2 = holder;
        final LogMessage logMessage = this.messages.get(i);
        holder2.binding.setMessage(logMessage);
        holder2.binding.mRoot.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$LogMessageAdapter$cgldHrHIfsU2GtzES5X4Ox53DUg
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                LogMessageAdapter logMessageAdapter = LogMessageAdapter.this;
                logMessageAdapter.copy.invoke(logMessage);
                return true;
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        int i2 = AdapterLogMessageBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        return new Holder((AdapterLogMessageBinding) ViewDataBinding.inflateInternal(from, R.layout.adapter_log_message, viewGroup, false, null));
    }
}
