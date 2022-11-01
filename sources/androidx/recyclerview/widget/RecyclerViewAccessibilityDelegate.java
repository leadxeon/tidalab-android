package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class RecyclerViewAccessibilityDelegate extends AccessibilityDelegateCompat {
    public final ItemDelegate mItemDelegate;
    public final RecyclerView mRecyclerView;

    /* loaded from: classes.dex */
    public static class ItemDelegate extends AccessibilityDelegateCompat {
        public Map<View, AccessibilityDelegateCompat> mOriginalItemDelegates = new WeakHashMap();
        public final RecyclerViewAccessibilityDelegate mRecyclerViewDelegate;

        public ItemDelegate(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
            this.mRecyclerViewDelegate = recyclerViewAccessibilityDelegate;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mOriginalItemDelegates.get(view);
            if (accessibilityDelegateCompat != null) {
                return accessibilityDelegateCompat.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            return this.mOriginalDelegate.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mOriginalItemDelegates.get(view);
            if (accessibilityDelegateCompat != null) {
                return accessibilityDelegateCompat.getAccessibilityNodeProvider(view);
            }
            return super.getAccessibilityNodeProvider(view);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mOriginalItemDelegates.get(view);
            if (accessibilityDelegateCompat != null) {
                accessibilityDelegateCompat.onInitializeAccessibilityEvent(view, accessibilityEvent);
            } else {
                this.mOriginalDelegate.onInitializeAccessibilityEvent(view, accessibilityEvent);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.mRecyclerViewDelegate.shouldIgnore() || this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager() == null) {
                this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                return;
            }
            this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mOriginalItemDelegates.get(view);
            if (accessibilityDelegateCompat != null) {
                accessibilityDelegateCompat.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            } else {
                this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mOriginalItemDelegates.get(view);
            if (accessibilityDelegateCompat != null) {
                accessibilityDelegateCompat.onPopulateAccessibilityEvent(view, accessibilityEvent);
            } else {
                this.mOriginalDelegate.onPopulateAccessibilityEvent(view, accessibilityEvent);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mOriginalItemDelegates.get(viewGroup);
            if (accessibilityDelegateCompat != null) {
                return accessibilityDelegateCompat.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return this.mOriginalDelegate.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (this.mRecyclerViewDelegate.shouldIgnore() || this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager() == null) {
                return super.performAccessibilityAction(view, i, bundle);
            }
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mOriginalItemDelegates.get(view);
            if (accessibilityDelegateCompat != null) {
                if (accessibilityDelegateCompat.performAccessibilityAction(view, i, bundle)) {
                    return true;
                }
            } else if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            RecyclerView.LayoutManager layoutManager = this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager();
            RecyclerView.Recycler recycler = layoutManager.mRecyclerView.mRecycler;
            return layoutManager.performAccessibilityActionForItem();
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void sendAccessibilityEvent(View view, int i) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mOriginalItemDelegates.get(view);
            if (accessibilityDelegateCompat != null) {
                accessibilityDelegateCompat.sendAccessibilityEvent(view, i);
            } else {
                this.mOriginalDelegate.sendAccessibilityEvent(view, i);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mOriginalItemDelegates.get(view);
            if (accessibilityDelegateCompat != null) {
                accessibilityDelegateCompat.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            } else {
                this.mOriginalDelegate.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            }
        }
    }

    public RecyclerViewAccessibilityDelegate(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        ItemDelegate itemDelegate = this.mItemDelegate;
        if (itemDelegate != null) {
            this.mItemDelegate = itemDelegate;
        } else {
            this.mItemDelegate = new ItemDelegate(this);
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.mOriginalDelegate.onInitializeAccessibilityEvent(view, accessibilityEvent);
        if ((view instanceof RecyclerView) && !shouldIgnore()) {
            RecyclerView recyclerView = (RecyclerView) view;
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().onInitializeAccessibilityEvent(accessibilityEvent);
            }
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
        if (!shouldIgnore() && this.mRecyclerView.getLayoutManager() != null) {
            RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
            RecyclerView recyclerView = layoutManager.mRecyclerView;
            layoutManager.onInitializeAccessibilityNodeInfo(recyclerView.mRecycler, recyclerView.mState, accessibilityNodeInfoCompat);
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        if (shouldIgnore() || this.mRecyclerView.getLayoutManager() == null) {
            return false;
        }
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        RecyclerView recyclerView = layoutManager.mRecyclerView;
        return layoutManager.performAccessibilityAction(recyclerView.mRecycler, recyclerView.mState, i, bundle);
    }

    public boolean shouldIgnore() {
        return this.mRecyclerView.hasPendingAdapterUpdates();
    }
}
