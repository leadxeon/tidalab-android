package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.design.adapter.LogMessageAdapter;
import com.tidalab.v2board.clash.design.databinding.DesignLogcatBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.view.AppRecyclerView;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: LogcatDesign.kt */
/* loaded from: classes.dex */
public final class LogcatDesign extends Design<Request> {
    public final LogMessageAdapter adapter;
    public final DesignLogcatBinding binding;
    public final boolean streaming;

    /* compiled from: LogcatDesign.kt */
    /* loaded from: classes.dex */
    public enum Request {
        Close,
        Delete,
        Export
    }

    public LogcatDesign(Context context, boolean z) {
        super(context);
        this.streaming = z;
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignLogcatBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignLogcatBinding designLogcatBinding = (DesignLogcatBinding) ViewDataBinding.inflateInternal(from, R.layout.design_logcat, root, false, null);
        this.binding = designLogcatBinding;
        LogMessageAdapter logMessageAdapter = new LogMessageAdapter(context, new LogcatDesign$adapter$1(this, context));
        this.adapter = logMessageAdapter;
        designLogcatBinding.setSelf(this);
        designLogcatBinding.setStreaming(z);
        InputKt.applyFrom(designLogcatBinding.activityBarLayout, context);
        InputKt.bindAppBarElevation(designLogcatBinding.recyclerList, designLogcatBinding.activityBarLayout);
        AppRecyclerView appRecyclerView = designLogcatBinding.recyclerList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(1, false);
        if (z) {
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
        }
        Unit unit = Unit.INSTANCE;
        appRecyclerView.setLayoutManager(linearLayoutManager);
        designLogcatBinding.recyclerList.setAdapter(logMessageAdapter);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }

    public final Object patchMessages(List<LogMessage> list, int i, int i2, Continuation<? super Unit> continuation) {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        Object withContext = InputKt.withContext(MainDispatcherLoader.dispatcher, new LogcatDesign$patchMessages$2(this, list, i2, i, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
