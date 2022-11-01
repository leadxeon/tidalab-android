package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ProvidersDesign;
import com.tidalab.v2board.clash.design.generated.callback.OnClickListener;
import com.tidalab.v2board.clash.design.model.ProviderState;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
/* loaded from: classes.dex */
public class DesignProvidersBindingImpl extends DesignProvidersBinding implements OnClickListener.Listener {
    public static final ViewDataBinding.IncludedLayouts sIncludes;
    public final View.OnClickListener mCallback8;
    public long mDirtyFlags = -1;
    public final CoordinatorLayout mboundView0;
    public final RelativeLayout mboundView2;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(5);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"common_recycler_list"}, new int[]{4}, new int[]{R.layout.common_recycler_list});
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesignProvidersBindingImpl(androidx.databinding.DataBindingComponent r12, android.view.View r13) {
        /*
            r11 = this;
            androidx.databinding.ViewDataBinding$IncludedLayouts r0 = com.tidalab.v2board.clash.design.databinding.DesignProvidersBindingImpl.sIncludes
            r1 = 5
            r2 = 0
            java.lang.Object[] r0 = androidx.databinding.ViewDataBinding.mapBindings(r12, r13, r1, r0, r2)
            r1 = 1
            r3 = r0[r1]
            r8 = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r8 = (com.tidalab.v2board.clash.design.view.ActivityBarLayout) r8
            r3 = 4
            r3 = r0[r3]
            r9 = r3
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r9 = (com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding) r9
            r3 = 3
            r3 = r0[r3]
            r10 = r3
            android.widget.ImageView r10 = (android.widget.ImageView) r10
            r7 = 2
            r4 = r11
            r5 = r12
            r6 = r13
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r3 = -1
            r11.mDirtyFlags = r3
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r12 = r11.activityBarLayout
            r12.setTag(r2)
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r12 = r11.mainList
            if (r12 == 0) goto L_0x0030
            r12.mContainingBinding = r11
        L_0x0030:
            r12 = 0
            r12 = r0[r12]
            androidx.coordinatorlayout.widget.CoordinatorLayout r12 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r12
            r11.mboundView0 = r12
            r12.setTag(r2)
            r12 = 2
            r12 = r0[r12]
            android.widget.RelativeLayout r12 = (android.widget.RelativeLayout) r12
            r11.mboundView2 = r12
            r12.setTag(r2)
            android.widget.ImageView r12 = r11.updateView
            r12.setTag(r2)
            r12 = 2131296397(0x7f09008d, float:1.821071E38)
            r13.setTag(r12, r11)
            com.tidalab.v2board.clash.design.generated.callback.OnClickListener r12 = new com.tidalab.v2board.clash.design.generated.callback.OnClickListener
            r12.<init>(r11, r1)
            r11.mCallback8 = r12
            r11.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignProvidersBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View):void");
    }

    @Override // com.tidalab.v2board.clash.design.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        ProvidersDesign providersDesign = this.mSelf;
        int i2 = 0;
        if (providersDesign != null) {
            List<ProviderState> list = providersDesign.adapter.states;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (!((ProviderState) obj).updating) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                i2++;
                if (i2 >= 0) {
                    ProviderState providerState = (ProviderState) next;
                    providerState.setUpdating(true);
                    providersDesign.requests.mo14trySendJP2dKIU(new ProvidersDesign.Request.Update(i2, providerState.provider));
                } else {
                    ArraysKt___ArraysKt.throwIndexOverflow();
                    throw null;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004e  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            r11 = this;
            monitor-enter(r11)
            long r0 = r11.mDirtyFlags     // Catch: all -> 0x005b
            r2 = 0
            r11.mDirtyFlags = r2     // Catch: all -> 0x005b
            monitor-exit(r11)     // Catch: all -> 0x005b
            com.tidalab.v2board.clash.design.ProvidersDesign r4 = r11.mSelf
            r5 = 29
            long r5 = r5 & r0
            r7 = 0
            r8 = 0
            int r9 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r9 == 0) goto L_0x002c
            if (r4 == 0) goto L_0x0018
            com.tidalab.v2board.clash.design.ui.Surface r4 = r4.surface
            goto L_0x0019
        L_0x0018:
            r4 = r7
        L_0x0019:
            r11.updateRegistration(r8, r4)
            if (r4 == 0) goto L_0x0020
            com.tidalab.v2board.clash.design.ui.Insets r7 = r4.insets
        L_0x0020:
            if (r7 == 0) goto L_0x002c
            int r8 = r7.end
            int r4 = r7.start
            int r5 = r7.top
            r10 = r8
            r8 = r5
            r5 = r10
            goto L_0x002e
        L_0x002c:
            r4 = 0
            r5 = 0
        L_0x002e:
            if (r9 == 0) goto L_0x0047
            com.tidalab.v2board.clash.design.view.ActivityBarLayout r6 = r11.activityBarLayout
            float r8 = (float) r8
            androidx.core.app.AppOpsManagerCompat.setPaddingTop(r6, r8)
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r6 = r11.mainList
            r6.setInsets(r7)
            androidx.coordinatorlayout.widget.CoordinatorLayout r6 = r11.mboundView0
            float r4 = (float) r4
            androidx.core.app.AppOpsManagerCompat.setPaddingStart(r6, r4)
            androidx.coordinatorlayout.widget.CoordinatorLayout r4 = r11.mboundView0
            float r5 = (float) r5
            androidx.core.app.AppOpsManagerCompat.setPaddingEnd(r4, r5)
        L_0x0047:
            r4 = 16
            long r0 = r0 & r4
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0055
            android.widget.ImageView r0 = r11.updateView
            android.view.View$OnClickListener r1 = r11.mCallback8
            r0.setOnClickListener(r1)
        L_0x0055:
            com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBinding r0 = r11.mainList
            r0.executeBindingsInternal()
            return
        L_0x005b:
            r0 = move-exception
            monitor-exit(r11)     // Catch: all -> 0x005b
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.databinding.DesignProvidersBindingImpl.executeBindings():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.mainList.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        this.mainList.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            Surface surface = (Surface) obj;
            if (i2 == 0) {
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
            } else if (i2 != 14) {
                return false;
            } else {
                synchronized (this) {
                    this.mDirtyFlags |= 8;
                }
            }
            return true;
        } else if (i != 1) {
            return false;
        } else {
            CommonRecyclerListBinding commonRecyclerListBinding = (CommonRecyclerListBinding) obj;
            if (i2 != 0) {
                return false;
            }
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
    }

    @Override // com.tidalab.v2board.clash.design.databinding.DesignProvidersBinding
    public void setSelf(ProvidersDesign providersDesign) {
        this.mSelf = providersDesign;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(26);
        requestRebind();
    }
}
