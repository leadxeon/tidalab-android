package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.adapter.FileAdapter;
import com.tidalab.v2board.clash.design.databinding.DesignFilesBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.File;
import com.tidalab.v2board.clash.design.view.AppRecyclerView;
import com.tidalab.v2board.clash.foss.R;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FilesDesign.kt */
/* loaded from: classes.dex */
public final class FilesDesign extends Design<Request> {
    public final FileAdapter adapter;
    public final DesignFilesBinding binding;

    /* compiled from: FilesDesign.kt */
    /* loaded from: classes.dex */
    public static abstract class Request {

        /* compiled from: FilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class DeleteFile extends Request {
            public final File file;

            public DeleteFile(File file) {
                super(null);
                this.file = file;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof DeleteFile) && Intrinsics.areEqual(this.file, ((DeleteFile) obj).file);
            }

            public int hashCode() {
                return this.file.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("DeleteFile(file=");
                outline13.append(this.file);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: FilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class ExportFile extends Request {
            public final File file;

            public ExportFile(File file) {
                super(null);
                this.file = file;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof ExportFile) && Intrinsics.areEqual(this.file, ((ExportFile) obj).file);
            }

            public int hashCode() {
                return this.file.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("ExportFile(file=");
                outline13.append(this.file);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: FilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class ImportFile extends Request {
            public final File file;

            public ImportFile(File file) {
                super(null);
                this.file = file;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof ImportFile) && Intrinsics.areEqual(this.file, ((ImportFile) obj).file);
            }

            public int hashCode() {
                File file = this.file;
                if (file == null) {
                    return 0;
                }
                return file.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("ImportFile(file=");
                outline13.append(this.file);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: FilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class OpenDirectory extends Request {
            public final File file;

            public OpenDirectory(File file) {
                super(null);
                this.file = file;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof OpenDirectory) && Intrinsics.areEqual(this.file, ((OpenDirectory) obj).file);
            }

            public int hashCode() {
                return this.file.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("OpenDirectory(file=");
                outline13.append(this.file);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: FilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class OpenFile extends Request {
            public final File file;

            public OpenFile(File file) {
                super(null);
                this.file = file;
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

        /* compiled from: FilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class PopStack extends Request {
            public static final PopStack INSTANCE = new PopStack();

            public PopStack() {
                super(null);
            }
        }

        /* compiled from: FilesDesign.kt */
        /* loaded from: classes.dex */
        public static final class RenameFile extends Request {
            public final File file;

            public RenameFile(File file) {
                super(null);
                this.file = file;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof RenameFile) && Intrinsics.areEqual(this.file, ((RenameFile) obj).file);
            }

            public int hashCode() {
                return this.file.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("RenameFile(file=");
                outline13.append(this.file);
                outline13.append(')');
                return outline13.toString();
            }
        }

        public Request() {
        }

        public Request(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public FilesDesign(Context context) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignFilesBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignFilesBinding designFilesBinding = (DesignFilesBinding) ViewDataBinding.inflateInternal(from, R.layout.design_files, root, false, null);
        this.binding = designFilesBinding;
        FileAdapter fileAdapter = new FileAdapter(context, new FilesDesign$adapter$1(this), new FilesDesign$adapter$2(this));
        this.adapter = fileAdapter;
        designFilesBinding.setSelf(this);
        InputKt.applyFrom(designFilesBinding.activityBarLayout, context);
        AppRecyclerView appRecyclerView = designFilesBinding.mainList.recyclerList;
        InputKt.applyLinearAdapter(appRecyclerView, context, fileAdapter);
        InputKt.bindAppBarElevation(appRecyclerView, designFilesBinding.activityBarLayout);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }

    public final Object requestFileName(String str, Continuation<? super String> continuation) {
        Context context = this.context;
        return InputKt.requestModelTextInput(context, str, context.getText(R.string.file_name), this.context.getText(R.string.file_name), this.context.getText(R.string.invalid_file_name), $$LambdaGroup$ks$io6Jg2G5Gb__x5iYf6soyn4mDM.INSTANCE$2, continuation);
    }
}
