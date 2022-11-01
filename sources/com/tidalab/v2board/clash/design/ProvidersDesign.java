package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.model.Provider;
import com.tidalab.v2board.clash.design.adapter.ProviderAdapter;
import com.tidalab.v2board.clash.design.databinding.DesignProvidersBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ProvidersDesign.kt */
/* loaded from: classes.dex */
public final class ProvidersDesign extends Design<Request> {
    public final ProviderAdapter adapter;
    public final DesignProvidersBinding binding;

    /* compiled from: ProvidersDesign.kt */
    /* loaded from: classes.dex */
    public static abstract class Request {

        /* compiled from: ProvidersDesign.kt */
        /* loaded from: classes.dex */
        public static final class Update extends Request {
            public final int index;
            public final Provider provider;

            public Update(int i, Provider provider) {
                super(null);
                this.index = i;
                this.provider = provider;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Update)) {
                    return false;
                }
                Update update = (Update) obj;
                return this.index == update.index && Intrinsics.areEqual(this.provider, update.provider);
            }

            public int hashCode() {
                return this.provider.hashCode() + (this.index * 31);
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Update(index=");
                outline13.append(this.index);
                outline13.append(", provider=");
                outline13.append(this.provider);
                outline13.append(')');
                return outline13.toString();
            }
        }

        public Request() {
        }

        public Request(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public ProvidersDesign(Context context, List<Provider> list) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignProvidersBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignProvidersBinding designProvidersBinding = (DesignProvidersBinding) ViewDataBinding.inflateInternal(from, R.layout.design_providers, root, false, null);
        this.binding = designProvidersBinding;
        ProviderAdapter providerAdapter = new ProviderAdapter(context, list, new ProvidersDesign$adapter$1(this));
        this.adapter = providerAdapter;
        designProvidersBinding.setSelf(this);
        InputKt.applyFrom(designProvidersBinding.activityBarLayout, context);
        InputKt.bindAppBarElevation(designProvidersBinding.mainList.recyclerList, designProvidersBinding.activityBarLayout);
        InputKt.applyLinearAdapter(designProvidersBinding.mainList.recyclerList, context, providerAdapter);
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.binding.mRoot;
    }
}
