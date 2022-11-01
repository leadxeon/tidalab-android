package com.tidalab.v2board.clash.design;

import android.view.LayoutInflater;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.databinding.DialogFilesMenuBinding;
import com.tidalab.v2board.clash.design.dialog.AppBottomSheetDialog;
import com.tidalab.v2board.clash.design.model.File;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: FilesDesign.kt */
/* loaded from: classes.dex */
public /* synthetic */ class FilesDesign$adapter$2 extends FunctionReferenceImpl implements Function1<File, Unit> {
    public FilesDesign$adapter$2(FilesDesign filesDesign) {
        super(1, filesDesign, FilesDesign.class, "requestMore", "requestMore(Lcom/tidalab/v2board/clash/design/model/File;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(File file) {
        FilesDesign filesDesign = (FilesDesign) this.receiver;
        Objects.requireNonNull(filesDesign);
        AppBottomSheetDialog appBottomSheetDialog = new AppBottomSheetDialog(filesDesign.context);
        LayoutInflater from = LayoutInflater.from(filesDesign.context);
        int i = DialogFilesMenuBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DialogFilesMenuBinding dialogFilesMenuBinding = (DialogFilesMenuBinding) ViewDataBinding.inflateInternal(from, R.layout.dialog_files_menu, null, false, null);
        dialogFilesMenuBinding.setMaster(filesDesign);
        dialogFilesMenuBinding.setSelf(appBottomSheetDialog);
        dialogFilesMenuBinding.setFile(file);
        dialogFilesMenuBinding.setCurrentInBase(filesDesign.binding.mCurrentInBaseDir);
        dialogFilesMenuBinding.setConfigurationEditable(filesDesign.binding.mConfigurationEditable);
        appBottomSheetDialog.setContentView(dialogFilesMenuBinding.mRoot);
        appBottomSheetDialog.show();
        return Unit.INSTANCE;
    }
}
