package com.tidalab.v2board.clash.design.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.LogFile;
import com.tidalab.v2board.clash.design.view.ActionLabel;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
/* compiled from: LogFileAdapter.kt */
/* loaded from: classes.dex */
public final class LogFileAdapter extends RecyclerView.Adapter<Holder> {
    public final Context context;
    public List<LogFile> logs = EmptyList.INSTANCE;
    public final Function1<LogFile, Unit> open;

    /* compiled from: LogFileAdapter.kt */
    /* loaded from: classes.dex */
    public static final class Holder extends RecyclerView.ViewHolder {
        public final ActionLabel label;

        public Holder(ActionLabel actionLabel) {
            super(actionLabel);
            this.label = actionLabel;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LogFileAdapter(Context context, Function1<? super LogFile, Unit> function1) {
        this.context = context;
        this.open = function1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.logs.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        Holder holder2 = holder;
        final LogFile logFile = this.logs.get(i);
        holder2.label.setText(logFile.fileName);
        holder2.label.setSubtext(InputKt.format$default(logFile.date, this.context, false, false, 6));
        holder2.label.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.adapter.-$$Lambda$LogFileAdapter$jRiFLq3gV9vvFdYKSxnqDsTgf5s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LogFileAdapter logFileAdapter = LogFileAdapter.this;
                logFileAdapter.open.invoke(logFile);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ActionLabel actionLabel = new ActionLabel(this.context, null, 0, 0, 14);
        actionLabel.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        Unit unit = Unit.INSTANCE;
        return new Holder(actionLabel);
    }
}
