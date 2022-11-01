package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.adapter.LogFileAdapter;
import com.tidalab.v2board.clash.design.databinding.DesignLogsBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.LogFile;
import com.tidalab.v2board.clash.foss.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LogsDesign.kt */
/* loaded from: classes.dex */
public final class LogsDesign extends Design<Request> {
    public final LogFileAdapter adapter;
    public final DesignLogsBinding binding;

    /* compiled from: LogsDesign.kt */
    /* loaded from: classes.dex */
    public static abstract class Request {

        /* compiled from: LogsDesign.kt */
        /* loaded from: classes.dex */
        public static final class DeleteAll extends Request {
            public static final DeleteAll INSTANCE = new DeleteAll();

            public DeleteAll() {
                super(null);
            }
        }

        /* compiled from: LogsDesign.kt */
        /* loaded from: classes.dex */
        public static final class OpenFile extends Request {
            public final LogFile file;

            public OpenFile(LogFile logFile) {
                super(null);
                this.file = logFile;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof OpenFile) && Intrinsics.areEqual(this.file, ((OpenFile) obj).file);
            }

            public int hashCode() {
                return this.file.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("OpenFile(file=");
                outline13.append(this.file);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: LogsDesign.kt */
        /* loaded from: classes.dex */
        public static final class StartLogcat extends Request {
            public static final StartLogcat INSTANCE = new StartLogcat();

            public StartLogcat() {
                super(null);
            }
        }

        public Request() {
        }

        public Request(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public LogsDesign(Context context) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignLogsBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignLogsBinding designLogsBinding = (DesignLogsBinding) ViewDataBinding.inflateInternal(from, R.layout.design_logs, root, false, null);
        this.binding = designLogsBinding;
        LogFileAdapter logFileAdapter = new LogFileAdapter(context, new LogsDesign$adapter$1(this));
        this.adapter = logFileAdapter;
        designLogsBinding.setSelf(this);
        InputKt.applyFrom(designLogsBinding.activityBarLayout, context);
        InputKt.applyLinearAdapter(designLogsBinding.recyclerList, context, logFileAdapter);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }
}
