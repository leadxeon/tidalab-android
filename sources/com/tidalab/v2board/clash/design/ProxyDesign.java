package com.tidalab.v2board.clash.design;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tidalab.v2board.clash.core.model.TunnelState;
import com.tidalab.v2board.clash.design.adapter.ProxyAdapter;
import com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter;
import com.tidalab.v2board.clash.design.component.ProxyMenu;
import com.tidalab.v2board.clash.design.component.ProxyViewConfig;
import com.tidalab.v2board.clash.design.databinding.DesignProxyBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.store.UiStore;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ProxyDesign.kt */
/* loaded from: classes.dex */
public final class ProxyDesign extends Design<Request> {
    public final DesignProxyBinding binding;
    public final ProxyViewConfig config;
    public boolean horizontalScrolling;
    public final Lazy menu$delegate;
    public final View root;

    /* compiled from: ProxyDesign.kt */
    /* loaded from: classes.dex */
    public static abstract class Request {

        /* compiled from: ProxyDesign.kt */
        /* loaded from: classes.dex */
        public static final class PatchMode extends Request {
            public final TunnelState.Mode mode;

            public PatchMode(TunnelState.Mode mode) {
                super(null);
                this.mode = mode;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof PatchMode) && this.mode == ((PatchMode) obj).mode;
            }

            public int hashCode() {
                TunnelState.Mode mode = this.mode;
                if (mode == null) {
                    return 0;
                }
                return mode.hashCode();
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("PatchMode(mode=");
                outline13.append(this.mode);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: ProxyDesign.kt */
        /* loaded from: classes.dex */
        public static final class ReLaunch extends Request {
            public static final ReLaunch INSTANCE = new ReLaunch();

            public ReLaunch() {
                super(null);
            }
        }

        /* compiled from: ProxyDesign.kt */
        /* loaded from: classes.dex */
        public static final class Reload extends Request {
            public final int index;

            public Reload(int i) {
                super(null);
                this.index = i;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Reload) && this.index == ((Reload) obj).index;
            }

            public int hashCode() {
                return this.index;
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Reload(index=");
                outline13.append(this.index);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: ProxyDesign.kt */
        /* loaded from: classes.dex */
        public static final class ReloadAll extends Request {
            public static final ReloadAll INSTANCE = new ReloadAll();

            public ReloadAll() {
                super(null);
            }
        }

        /* compiled from: ProxyDesign.kt */
        /* loaded from: classes.dex */
        public static final class Select extends Request {
            public final int index;
            public final String name;

            public Select(int i, String str) {
                super(null);
                this.index = i;
                this.name = str;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Select)) {
                    return false;
                }
                Select select = (Select) obj;
                return this.index == select.index && Intrinsics.areEqual(this.name, select.name);
            }

            public int hashCode() {
                return this.name.hashCode() + (this.index * 31);
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Select(index=");
                outline13.append(this.index);
                outline13.append(", name=");
                outline13.append(this.name);
                outline13.append(')');
                return outline13.toString();
            }
        }

        /* compiled from: ProxyDesign.kt */
        /* loaded from: classes.dex */
        public static final class UrlTest extends Request {
            public final int index;

            public UrlTest(int i) {
                super(null);
                this.index = i;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UrlTest) && this.index == ((UrlTest) obj).index;
            }

            public int hashCode() {
                return this.index;
            }

            public String toString() {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("UrlTest(index=");
                outline13.append(this.index);
                outline13.append(')');
                return outline13.toString();
            }
        }

        public Request() {
        }

        public Request(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public ProxyDesign(Context context, TunnelState.Mode mode, final List<String> list, final UiStore uiStore) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = InputKt.getRoot(context);
        int i = DesignProxyBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        DesignProxyBinding designProxyBinding = (DesignProxyBinding) ViewDataBinding.inflateInternal(from, R.layout.design_proxy, root, false, null);
        this.binding = designProxyBinding;
        ProxyViewConfig proxyViewConfig = new ProxyViewConfig(context, uiStore.getProxySingleLine());
        this.config = proxyViewConfig;
        this.menu$delegate = InputKt.lazy(new ProxyDesign$menu$2(context, this, mode, uiStore));
        this.root = designProxyBinding.mRoot;
        designProxyBinding.setSelf(this);
        InputKt.applyFrom(designProxyBinding.activityBarLayout, context);
        designProxyBinding.menuView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.-$$Lambda$ProxyDesign$Gu9kUz5G5Eq1-Vo33DuMo1_PeR0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (!((ProxyMenu) ProxyDesign.this.menu$delegate.getValue()).menu.mPopup.tryShow()) {
                    throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
                }
            }
        });
        if (list.isEmpty()) {
            designProxyBinding.emptyView.setVisibility(0);
            designProxyBinding.urlTestView.setVisibility(8);
            designProxyBinding.tabLayoutView.setVisibility(8);
            designProxyBinding.elevationView.setVisibility(8);
            designProxyBinding.pagesView.setVisibility(8);
            designProxyBinding.urlTestFloatView.setVisibility(8);
            return;
        }
        designProxyBinding.urlTestFloatView.setSupportImageTintList(ColorStateList.valueOf(InputKt.resolveThemedColor(context, R.attr.colorOnPrimary)));
        ViewPager2 viewPager2 = designProxyBinding.pagesView;
        Surface surface = this.surface;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new ProxyAdapter(this.config, new ProxyDesign$2$1$1(this, i2)));
        }
        viewPager2.setAdapter(new ProxyPageAdapter(surface, proxyViewConfig, arrayList, new ProxyDesign$2$2(viewPager2, this)));
        viewPager2.mExternalPageChangeCallbacks.mCallbacks.add(new ViewPager2.OnPageChangeCallback() { // from class: com.tidalab.v2board.clash.design.ProxyDesign$2$3
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i3) {
                ProxyDesign proxyDesign = ProxyDesign.this;
                proxyDesign.horizontalScrolling = i3 != 0;
                proxyDesign.updateUrlTestButtonStatus();
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i3) {
                UiStore uiStore2 = uiStore;
                uiStore2.proxyLastGroup$delegate.setValue(uiStore2, UiStore.$$delegatedProperties[5], list.get(i3));
            }
        });
        DesignProxyBinding designProxyBinding2 = this.binding;
        TabLayout tabLayout = designProxyBinding2.tabLayoutView;
        ViewPager2 viewPager22 = designProxyBinding2.pagesView;
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager22, new $$Lambda$ProxyDesign$FVyRz8ut_dh7ahqXNdN3xkrGvhc(list));
        if (!tabLayoutMediator.attached) {
            RecyclerView.Adapter<?> adapter = viewPager22.getAdapter();
            tabLayoutMediator.adapter = adapter;
            if (adapter != null) {
                tabLayoutMediator.attached = true;
                TabLayoutMediator.TabLayoutOnPageChangeCallback tabLayoutOnPageChangeCallback = new TabLayoutMediator.TabLayoutOnPageChangeCallback(tabLayout);
                tabLayoutMediator.onPageChangeCallback = tabLayoutOnPageChangeCallback;
                viewPager22.mExternalPageChangeCallbacks.mCallbacks.add(tabLayoutOnPageChangeCallback);
                TabLayoutMediator.ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = new TabLayoutMediator.ViewPagerOnTabSelectedListener(viewPager22, true);
                tabLayoutMediator.onTabSelectedListener = viewPagerOnTabSelectedListener;
                if (!tabLayout.selectedListeners.contains(viewPagerOnTabSelectedListener)) {
                    tabLayout.selectedListeners.add(viewPagerOnTabSelectedListener);
                }
                TabLayoutMediator.PagerAdapterObserver pagerAdapterObserver = new TabLayoutMediator.PagerAdapterObserver();
                tabLayoutMediator.pagerAdapterObserver = pagerAdapterObserver;
                tabLayoutMediator.adapter.mObservable.registerObserver(pagerAdapterObserver);
                tabLayoutMediator.populateTabsFromPagerAdapter();
                tabLayout.setScrollPosition(viewPager22.getCurrentItem(), 0.0f, true, true);
                final int indexOf = list.indexOf((String) uiStore.proxyLastGroup$delegate.getValue(uiStore, UiStore.$$delegatedProperties[5]));
                this.binding.pagesView.post(new Runnable() { // from class: com.tidalab.v2board.clash.design.-$$Lambda$ProxyDesign$B_nx4OlqmqR6x6dfs0Yu8yEOwGI
                    @Override // java.lang.Runnable
                    public final void run() {
                        int i3 = indexOf;
                        ProxyDesign proxyDesign = this;
                        if (i3 > 0) {
                            proxyDesign.binding.pagesView.setCurrentItem(i3, false);
                        }
                    }
                });
                return;
            }
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        throw new IllegalStateException("TabLayoutMediator is already attached");
    }

    public final ProxyPageAdapter getAdapter() {
        return (ProxyPageAdapter) this.binding.pagesView.getAdapter();
    }

    @Override // com.tidalab.v2board.clash.design.Design
    public View getRoot() {
        return this.root;
    }

    public final boolean getUrlTesting() {
        return getAdapter().states.get(this.binding.pagesView.getCurrentItem()).urlTesting;
    }

    public final void requestUrlTesting() {
        getAdapter().states.get(this.binding.pagesView.getCurrentItem()).urlTesting = true;
        this.requests.mo14trySendJP2dKIU(new Request.UrlTest(this.binding.pagesView.getCurrentItem()));
        updateUrlTestButtonStatus();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object updateGroup(int r9, java.util.List<com.tidalab.v2board.clash.core.model.Proxy> r10, boolean r11, com.tidalab.v2board.clash.design.model.ProxyState r12, java.util.Map<java.lang.String, com.tidalab.v2board.clash.design.model.ProxyState> r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r8 = this;
            boolean r0 = r14 instanceof com.tidalab.v2board.clash.design.ProxyDesign$updateGroup$1
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.tidalab.v2board.clash.design.ProxyDesign$updateGroup$1 r0 = (com.tidalab.v2board.clash.design.ProxyDesign$updateGroup$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.design.ProxyDesign$updateGroup$1 r0 = new com.tidalab.v2board.clash.design.ProxyDesign$updateGroup$1
            r0.<init>(r8, r14)
        L_0x0018:
            r7 = r0
            java.lang.Object r14 = r7.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L_0x0036
            if (r1 != r2) goto L_0x002e
            int r9 = r7.I$0
            java.lang.Object r10 = r7.L$0
            com.tidalab.v2board.clash.design.ProxyDesign r10 = (com.tidalab.v2board.clash.design.ProxyDesign) r10
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
            goto L_0x0050
        L_0x002e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0036:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r14)
            com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter r1 = r8.getAdapter()
            r7.L$0 = r8
            r7.I$0 = r9
            r7.label = r2
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r6 = r13
            java.lang.Object r10 = r1.updateAdapter(r2, r3, r4, r5, r6, r7)
            if (r10 != r0) goto L_0x004f
            return r0
        L_0x004f:
            r10 = r8
        L_0x0050:
            com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter r11 = r10.getAdapter()
            java.util.List<com.tidalab.v2board.clash.design.model.ProxyPageState> r11 = r11.states
            java.lang.Object r9 = r11.get(r9)
            com.tidalab.v2board.clash.design.model.ProxyPageState r9 = (com.tidalab.v2board.clash.design.model.ProxyPageState) r9
            r11 = 0
            r9.urlTesting = r11
            r10.updateUrlTestButtonStatus()
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.ProxyDesign.updateGroup(int, java.util.List, boolean, com.tidalab.v2board.clash.design.model.ProxyState, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void updateUrlTestButtonStatus() {
        if (getAdapter().states.get(this.binding.pagesView.getCurrentItem()).bottom || this.horizontalScrolling || getUrlTesting()) {
            this.binding.urlTestFloatView.hide(null, true);
        } else {
            this.binding.urlTestFloatView.show(null, true);
        }
        if (getUrlTesting()) {
            this.binding.urlTestView.setVisibility(8);
            this.binding.urlTestProgressView.setVisibility(0);
            return;
        }
        this.binding.urlTestView.setVisibility(0);
        this.binding.urlTestProgressView.setVisibility(8);
    }
}
