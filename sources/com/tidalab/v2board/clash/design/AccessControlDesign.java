package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.adapter.AppAdapter;
import com.tidalab.v2board.clash.design.component.AccessControlMenu;
import com.tidalab.v2board.clash.design.databinding.DesignAccessControlBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.store.UiStore;
import com.tidalab.v2board.clash.design.view.AppRecyclerView;
import com.tidalab.v2board.clash.foss.R;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: AccessControlDesign.kt */
/* loaded from: classes.dex */
public final class AccessControlDesign extends Design<Request> {
    public final AppAdapter adapter;
    public final DesignAccessControlBinding binding;
    public final Lazy menu$delegate;
    public final Set<String> selected;

    /* compiled from: AccessControlDesign.kt */
    /* loaded from: classes.dex */
    public enum Request {
        ReloadApps,
        SelectAll,
        SelectNone,
        SelectInvert,
        Import,
        Export
    }

    public AccessControlDesign(Context context, UiStore uiStore, Set<String> set) {
        super(context);
        this.selected = set;
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignAccessControlBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignAccessControlBinding designAccessControlBinding = (DesignAccessControlBinding) ViewDataBinding.inflateInternal(from, R.layout.design_access_control, root, false, null);
        this.binding = designAccessControlBinding;
        AppAdapter appAdapter = new AppAdapter(context, set);
        this.adapter = appAdapter;
        this.menu$delegate = InputKt.lazy(new AccessControlDesign$menu$2(context, this, uiStore));
        designAccessControlBinding.setSelf(this);
        InputKt.applyFrom(designAccessControlBinding.activityBarLayout, context);
        AppRecyclerView appRecyclerView = designAccessControlBinding.mainList.recyclerList;
        InputKt.bindAppBarElevation(appRecyclerView, designAccessControlBinding.activityBarLayout);
        InputKt.applyLinearAdapter(appRecyclerView, context, appAdapter);
        designAccessControlBinding.menuView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.-$$Lambda$AccessControlDesign$gZBCvJXGJ3NwytlH0pqoDEWk_wg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (!((AccessControlMenu) AccessControlDesign.this.menu$delegate.getValue()).menu.mPopup.tryShow()) {
                    throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
                }
            }
        });
        designAccessControlBinding.searchView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.-$$Lambda$AccessControlDesign$YyAiZS5qAYoraPWWOm9vHN11Mkk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccessControlDesign accessControlDesign = AccessControlDesign.this;
                InputKt.launch$default(accessControlDesign, null, null, new AccessControlDesign$3$1(accessControlDesign, null), 3, null);
            }
        });
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }

    public final Object rebindAll(Continuation<? super Unit> continuation) {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        Object withContext = InputKt.withContext(MainDispatcherLoader.dispatcher, new AccessControlDesign$rebindAll$2(this, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
