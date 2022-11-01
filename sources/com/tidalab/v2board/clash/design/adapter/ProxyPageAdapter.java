package com.tidalab.v2board.clash.design.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt$iterator$1;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.component.ProxyPageFactory;
import com.tidalab.v2board.clash.design.component.ProxyViewConfig;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.ProxyPageState;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.design.view.VerticalScrollableHost;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ProxyPageAdapter.kt */
/* loaded from: classes.dex */
public final class ProxyPageAdapter extends RecyclerView.Adapter<ProxyPageFactory.Holder> {
    public final List<ProxyAdapter> adapters;
    public final ProxyViewConfig config;
    public final ProxyPageFactory factory;
    public RecyclerView parent;
    public final Function1<Integer, Unit> stateChanged;
    public final List<ProxyPageState> states;
    public final Surface surface;

    /* JADX WARN: Multi-variable type inference failed */
    public ProxyPageAdapter(Surface surface, ProxyViewConfig proxyViewConfig, List<ProxyAdapter> list, Function1<? super Integer, Unit> function1) {
        this.surface = surface;
        this.config = proxyViewConfig;
        this.adapters = list;
        this.stateChanged = function1;
        this.factory = new ProxyPageFactory(proxyViewConfig);
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new ProxyPageState());
        }
        this.states = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.adapters.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.parent = recyclerView;
        recyclerView.setFocusable(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ProxyPageFactory.Holder holder, int i) {
        this.states.get(i).bottom = false;
        RecyclerView recyclerView = holder.recyclerView;
        recyclerView.setTag(Integer.valueOf(i));
        recyclerView.setLayoutFrozen(false);
        recyclerView.setAdapterInternal(this.adapters.get(i), true, false);
        recyclerView.processDataSetCompletelyChanged(true);
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ProxyPageFactory.Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final ProxyPageFactory proxyPageFactory = this.factory;
        Objects.requireNonNull(proxyPageFactory);
        VerticalScrollableHost verticalScrollableHost = new VerticalScrollableHost(proxyPageFactory.config.context, null, 0, 0, 14);
        verticalScrollableHost.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        RecyclerView recyclerView = new RecyclerView(proxyPageFactory.config.context, null);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        verticalScrollableHost.addView(recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(proxyPageFactory.config.context, 2);
        gridLayoutManager.mSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() { // from class: com.tidalab.v2board.clash.design.component.ProxyPageFactory$newInstance$1$1$1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i2) {
                return ProxyPageFactory.this.config.singleLine ? 2 : 1;
            }
        };
        Unit unit = Unit.INSTANCE;
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setRecycledViewPool(proxyPageFactory.childrenPool);
        recyclerView.setClipToPadding(false);
        ProxyPageFactory.Holder holder = new ProxyPageFactory.Holder(recyclerView, verticalScrollableHost);
        verticalScrollableHost.setTag(holder);
        int pixels = InputKt.getPixels(this.config.context, R.dimen.toolbar_height);
        int pixels2 = InputKt.getPixels(this.config.context, R.dimen.tab_layout_height);
        final RecyclerView recyclerView2 = holder.recyclerView;
        final Surface surface = this.surface;
        final int i2 = pixels + pixels2;
        surface.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.tidalab.v2board.clash.design.util.RecyclerViewKt$bindInsets$1
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i3) {
                if (i3 == 14) {
                    Surface surface2 = Surface.this;
                    int i4 = i2;
                    int i5 = r3;
                    RecyclerView recyclerView3 = recyclerView2;
                    Insets insets = surface2.insets;
                    recyclerView3.setPaddingRelative(0, insets.top + i4, 0, insets.bottom + i5);
                }
            }
        });
        Insets insets = surface.insets;
        recyclerView2.setPaddingRelative(0, insets.top + i2, 0, insets.bottom + 0);
        final RecyclerView recyclerView3 = holder.recyclerView;
        final ProxyPageAdapter$onCreateViewHolder$1 proxyPageAdapter$onCreateViewHolder$1 = new ProxyPageAdapter$onCreateViewHolder$1(this);
        recyclerView3.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.tidalab.v2board.clash.design.util.RecyclerViewKt$addScrolledToBottomObserver$observer$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView4, int i3) {
                if (i3 == 0) {
                    Function2<RecyclerView, Boolean, Unit> function2 = proxyPageAdapter$onCreateViewHolder$1;
                    RecyclerView recyclerView5 = recyclerView3;
                    RecyclerView.LayoutManager layoutManager = recyclerView4.getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager) {
                        GridLayoutManager gridLayoutManager2 = (GridLayoutManager) layoutManager;
                        boolean z = true;
                        if (gridLayoutManager2.findFirstVisibleItemPosition() == 0 || gridLayoutManager2.findLastVisibleItemPosition() != recyclerView4.getAdapter().getItemCount() - 1) {
                            z = false;
                        }
                        function2.invoke(recyclerView5, Boolean.valueOf(z));
                        return;
                    }
                    throw new UnsupportedOperationException("unsupported layout manager");
                }
            }
        });
        return holder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.parent = null;
    }

    public final void requestRedrawVisible() {
        View view;
        ProxyPageFactory proxyPageFactory = this.factory;
        RecyclerView recyclerView = this.parent;
        if (recyclerView == null) {
            view = null;
        } else {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                view = linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition());
            } else {
                throw new UnsupportedOperationException(Intrinsics.stringPlus("unsupported manager: ", layoutManager));
            }
        }
        if (view != null) {
            Objects.requireNonNull(proxyPageFactory);
            ViewGroupKt$iterator$1 viewGroupKt$iterator$1 = new ViewGroupKt$iterator$1(((ProxyPageFactory.Holder) view.getTag()).recyclerView);
            while (viewGroupKt$iterator$1.hasNext()) {
                ((View) viewGroupKt$iterator$1.next()).postInvalidate();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0090 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object updateAdapter(int r15, java.util.List<com.tidalab.v2board.clash.core.model.Proxy> r16, boolean r17, com.tidalab.v2board.clash.design.model.ProxyState r18, java.util.Map<java.lang.String, com.tidalab.v2board.clash.design.model.ProxyState> r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r14 = this;
            r6 = r14
            r0 = r20
            boolean r1 = r0 instanceof com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$1 r1 = (com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0016
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001b
        L_0x0016:
            com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$1 r1 = new com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$1
            r1.<init>(r14, r0)
        L_0x001b:
            r7 = r1
            java.lang.Object r0 = r7.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r8 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            r9 = 2
            r10 = 1
            if (r1 == 0) goto L_0x0046
            if (r1 == r10) goto L_0x0037
            if (r1 != r9) goto L_0x002f
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r0)
            goto L_0x0091
        L_0x002f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0037:
            boolean r1 = r7.Z$0
            int r2 = r7.I$0
            java.lang.Object r3 = r7.L$0
            com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter r3 = (com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter) r3
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r0)
            r13 = r2
            r2 = r0
            r0 = r13
            goto L_0x006e
        L_0x0046:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r0)
            kotlinx.coroutines.Dispatchers r0 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.Default
            com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$states$1 r12 = new com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$states$1
            r5 = 0
            r0 = r12
            r1 = r16
            r2 = r19
            r3 = r14
            r4 = r18
            r0.<init>(r1, r2, r3, r4, r5)
            r7.L$0 = r6
            r0 = r15
            r7.I$0 = r0
            r1 = r17
            r7.Z$0 = r1
            r7.label = r10
            java.lang.Object r2 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r11, r12, r7)
            if (r2 != r8) goto L_0x006d
            return r8
        L_0x006d:
            r3 = r6
        L_0x006e:
            java.util.List r2 = (java.util.List) r2
            kotlinx.coroutines.Dispatchers r4 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.MainCoroutineDispatcher r4 = kotlinx.coroutines.internal.MainDispatcherLoader.dispatcher
            com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$2 r5 = new com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter$updateAdapter$2
            r10 = 0
            r15 = r5
            r16 = r3
            r17 = r0
            r18 = r1
            r19 = r2
            r20 = r10
            r15.<init>(r16, r17, r18, r19, r20)
            r0 = 0
            r7.L$0 = r0
            r7.label = r9
            java.lang.Object r0 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r4, r5, r7)
            if (r0 != r8) goto L_0x0091
            return r8
        L_0x0091:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.adapter.ProxyPageAdapter.updateAdapter(int, java.util.List, boolean, com.tidalab.v2board.clash.design.model.ProxyState, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
