package com.tidalab.v2board.clash.design.preference;

import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ListPopupWindow;
import com.tidalab.v2board.clash.design.adapter.PopupListAdapter;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KMutableProperty0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: SelectableList.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.preference.SelectableListKt$selectableList$2", f = "SelectableList.kt", l = {42}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SelectableListKt$selectableList$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ SelectableListKt$selectableList$impl$1 $impl;
    public final /* synthetic */ PreferenceScreen $this_selectableList;
    public final /* synthetic */ KMutableProperty0<T> $value;
    public final /* synthetic */ T[] $values;
    public final /* synthetic */ Integer[] $valuesText;
    public int label;

    /* compiled from: SelectableList.kt */
    /* renamed from: com.tidalab.v2board.clash.design.preference.SelectableListKt$selectableList$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        public final /* synthetic */ SelectableListKt$selectableList$impl$1 $impl;
        public final /* synthetic */ PreferenceScreen $this_selectableList;
        public final /* synthetic */ KMutableProperty0<T> $value;
        public final /* synthetic */ T[] $values;
        public final /* synthetic */ Integer[] $valuesText;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(PreferenceScreen preferenceScreen, SelectableListKt$selectableList$impl$1 selectableListKt$selectableList$impl$1, KMutableProperty0<T> kMutableProperty0, Integer[] numArr, T[] tArr) {
            super(0);
            this.$this_selectableList = preferenceScreen;
            this.$impl = selectableListKt$selectableList$impl$1;
            this.$value = kMutableProperty0;
            this.$valuesText = numArr;
            this.$values = tArr;
        }

        @Override // kotlin.jvm.functions.Function0
        public Unit invoke() {
            final PreferenceScreen preferenceScreen = this.$this_selectableList;
            final SelectableListKt$selectableList$impl$1 selectableListKt$selectableList$impl$1 = this.$impl;
            final KMutableProperty0<T> kMutableProperty0 = this.$value;
            Integer[] numArr = this.$valuesText;
            ArrayList arrayList = new ArrayList(numArr.length);
            int i = 0;
            for (Integer num : numArr) {
                arrayList.add(preferenceScreen.getContext().getText(num.intValue()));
            }
            final T[] tArr = this.$values;
            final ListPopupWindow listPopupWindow = new ListPopupWindow(preferenceScreen.getContext(), null, R.attr.listPopupWindowStyle, 0);
            PopupListAdapter popupListAdapter = new PopupListAdapter(preferenceScreen.getContext(), arrayList, selectableListKt$selectableList$impl$1.getSelected());
            listPopupWindow.setAdapter(popupListAdapter);
            listPopupWindow.mDropDownAnchorView = selectableListKt$selectableList$impl$1.getView();
            FrameLayout frameLayout = new FrameLayout(preferenceScreen.getContext());
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = popupListAdapter.getCount();
            if (count > 0) {
                View view = null;
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    int i4 = i + 1;
                    int itemViewType = popupListAdapter.getItemViewType(i);
                    if (itemViewType != i2) {
                        i2 = itemViewType;
                        view = null;
                    }
                    view = popupListAdapter.getView(i, view, frameLayout);
                    view.measure(makeMeasureSpec, makeMeasureSpec2);
                    int measuredWidth = view.getMeasuredWidth();
                    if (measuredWidth > i3) {
                        i3 = measuredWidth;
                    }
                    if (i4 >= count) {
                        break;
                    }
                    i = i4;
                }
                i = i3;
            }
            int pixels = InputKt.getPixels(preferenceScreen.getContext(), R.dimen.dialog_menu_min_width);
            if (i < pixels) {
                i = pixels;
            }
            listPopupWindow.mDropDownWidth = i;
            listPopupWindow.setModal(true);
            listPopupWindow.mDropDownHorizontalOffset = (InputKt.getPixels(preferenceScreen.getContext(), R.dimen.item_header_margin) * 2) + InputKt.getPixels(preferenceScreen.getContext(), R.dimen.item_header_component_size);
            listPopupWindow.mItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.tidalab.v2board.clash.design.preference.-$$Lambda$SelectableListKt$QMIWyqfiMlT1jCXpDsVifv2P8HE
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view2, int i5, long j) {
                    ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
                    PreferenceScreen preferenceScreen2 = preferenceScreen;
                    SelectableListPreference selectableListPreference = selectableListKt$selectableList$impl$1;
                    KMutableProperty0 kMutableProperty02 = kMutableProperty0;
                    Object[] objArr = tArr;
                    listPopupWindow2.dismiss();
                    Dispatchers dispatchers = Dispatchers.INSTANCE;
                    InputKt.launch$default(preferenceScreen2, MainDispatcherLoader.dispatcher, null, new SelectableListKt$popupSelectMenu$1$1$1(selectableListPreference, i5, kMutableProperty02, objArr, null), 2, null);
                }
            };
            listPopupWindow.show();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectableListKt$selectableList$2(SelectableListKt$selectableList$impl$1 selectableListKt$selectableList$impl$1, T[] tArr, KMutableProperty0<T> kMutableProperty0, PreferenceScreen preferenceScreen, Integer[] numArr, Continuation<? super SelectableListKt$selectableList$2> continuation) {
        super(2, continuation);
        this.$impl = selectableListKt$selectableList$impl$1;
        this.$values = tArr;
        this.$value = kMutableProperty0;
        this.$this_selectableList = preferenceScreen;
        this.$valuesText = numArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SelectableListKt$selectableList$2(this.$impl, this.$values, this.$value, this.$this_selectableList, this.$valuesText, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SelectableListKt$selectableList$2(this.$impl, this.$values, this.$value, this.$this_selectableList, this.$valuesText, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            CoroutineDispatcher coroutineDispatcher = Dispatchers.IO;
            SelectableListKt$selectableList$2$initial$1 selectableListKt$selectableList$2$initial$1 = new SelectableListKt$selectableList$2$initial$1(this.$value, null);
            this.label = 1;
            obj = InputKt.withContext(coroutineDispatcher, selectableListKt$selectableList$2$initial$1, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$impl.setSelected(ArraysKt___ArraysKt.indexOf(this.$values, obj));
        SelectableListKt$selectableList$impl$1 selectableListKt$selectableList$impl$1 = this.$impl;
        selectableListKt$selectableList$impl$1.$$delegate_0.clicked(new AnonymousClass1(this.$this_selectableList, selectableListKt$selectableList$impl$1, this.$value, this.$valuesText, this.$values));
        return Unit.INSTANCE;
    }
}
