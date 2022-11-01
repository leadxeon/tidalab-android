package androidx.databinding;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseIntArray;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Observable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public abstract class ViewDataBinding extends BaseObservable {
    public static final CreateWeakListener CREATE_PROPERTY_LISTENER;
    public static final View.OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
    public static int SDK_INT;
    public static final boolean USE_CHOREOGRAPHER;
    public static final ReferenceQueue<ViewDataBinding> sReferenceQueue;
    public final DataBindingComponent mBindingComponent;
    public Choreographer mChoreographer;
    public ViewDataBinding mContainingBinding;
    public final Choreographer.FrameCallback mFrameCallback;
    public boolean mIsExecutingPendingBindings;
    public WeakListener[] mLocalFieldObservers;
    public final View mRoot;
    public Handler mUIThreadHandler;
    public final Runnable mRebindRunnable = new Runnable() { // from class: androidx.databinding.ViewDataBinding.7
        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                ViewDataBinding.this.mPendingRebind = false;
            }
            while (true) {
                Reference<? extends ViewDataBinding> poll = ViewDataBinding.sReferenceQueue.poll();
                if (poll == null) {
                    break;
                } else if (poll instanceof WeakListener) {
                    ((WeakListener) poll).unregister();
                }
            }
            if (!ViewDataBinding.this.mRoot.isAttachedToWindow()) {
                View view = ViewDataBinding.this.mRoot;
                View.OnAttachStateChangeListener onAttachStateChangeListener = ViewDataBinding.ROOT_REATTACHED_LISTENER;
                view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
                ViewDataBinding.this.mRoot.addOnAttachStateChangeListener(onAttachStateChangeListener);
                return;
            }
            ViewDataBinding.this.executePendingBindings();
        }
    };
    public boolean mPendingRebind = false;
    public boolean mRebindHalted = false;

    /* loaded from: classes.dex */
    public interface CreateWeakListener {
        WeakListener create(ViewDataBinding viewDataBinding, int i);
    }

    /* loaded from: classes.dex */
    public static class IncludedLayouts {
        public final int[][] indexes;
        public final int[][] layoutIds;
        public final String[][] layouts;

        public IncludedLayouts(int i) {
            this.layouts = new String[i];
            this.indexes = new int[i];
            this.layoutIds = new int[i];
        }

        public void setIncludes(int i, String[] strArr, int[] iArr, int[] iArr2) {
            this.layouts[i] = strArr;
            this.indexes[i] = iArr;
            this.layoutIds[i] = iArr2;
        }
    }

    /* loaded from: classes.dex */
    public interface ObservableReference<T> {
        void addListener(T t);

        void removeListener(T t);
    }

    /* loaded from: classes.dex */
    public static class OnStartListener implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            throw null;
        }
    }

    /* loaded from: classes.dex */
    public static class WeakListener<T> extends WeakReference<ViewDataBinding> {
        public final int mLocalFieldId;
        public final ObservableReference<T> mObservable;
        public T mTarget;

        public WeakListener(ViewDataBinding viewDataBinding, int i, ObservableReference<T> observableReference) {
            super(viewDataBinding, ViewDataBinding.sReferenceQueue);
            this.mLocalFieldId = i;
            this.mObservable = observableReference;
        }

        public boolean unregister() {
            boolean z;
            T t = this.mTarget;
            if (t != null) {
                this.mObservable.removeListener(t);
                z = true;
            } else {
                z = false;
            }
            this.mTarget = null;
            return z;
        }
    }

    /* loaded from: classes.dex */
    public static class WeakPropertyListener extends Observable.OnPropertyChangedCallback implements ObservableReference<Observable> {
        public final WeakListener<Observable> mListener;

        public WeakPropertyListener(ViewDataBinding viewDataBinding, int i) {
            this.mListener = new WeakListener<>(viewDataBinding, i, this);
        }

        @Override // androidx.databinding.ViewDataBinding.ObservableReference
        public void addListener(Observable observable) {
            observable.addOnPropertyChangedCallback(this);
        }

        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            WeakListener<Observable> weakListener = this.mListener;
            ViewDataBinding viewDataBinding = (ViewDataBinding) weakListener.get();
            if (viewDataBinding == null) {
                weakListener.unregister();
            }
            if (viewDataBinding != null) {
                WeakListener<Observable> weakListener2 = this.mListener;
                if (weakListener2.mTarget == observable) {
                    int i2 = weakListener2.mLocalFieldId;
                    int i3 = ViewDataBinding.SDK_INT;
                    if (viewDataBinding.onFieldChange(i2, observable, i)) {
                        viewDataBinding.requestRebind();
                    }
                }
            }
        }

        @Override // androidx.databinding.ViewDataBinding.ObservableReference
        public void removeListener(Observable observable) {
            observable.removeOnPropertyChangedCallback(this);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        SDK_INT = i;
        USE_CHOREOGRAPHER = i >= 16;
        CREATE_PROPERTY_LISTENER = new CreateWeakListener() { // from class: androidx.databinding.ViewDataBinding.1
            @Override // androidx.databinding.ViewDataBinding.CreateWeakListener
            public WeakListener create(ViewDataBinding viewDataBinding, int i2) {
                return new WeakPropertyListener(viewDataBinding, i2).mListener;
            }
        };
        sReferenceQueue = new ReferenceQueue<>();
        ROOT_REATTACHED_LISTENER = new View.OnAttachStateChangeListener() { // from class: androidx.databinding.ViewDataBinding.6
            @Override // android.view.View.OnAttachStateChangeListener
            @TargetApi(19)
            public void onViewAttachedToWindow(View view) {
                (view != null ? (ViewDataBinding) view.getTag(R.id.dataBinding) : null).mRebindRunnable.run();
                view.removeOnAttachStateChangeListener(this);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }
        };
    }

    public ViewDataBinding(Object obj, View view, int i) {
        DataBindingComponent checkAndCastToBindingComponent = checkAndCastToBindingComponent(obj);
        this.mBindingComponent = checkAndCastToBindingComponent;
        this.mLocalFieldObservers = new WeakListener[i];
        this.mRoot = view;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        } else if (USE_CHOREOGRAPHER) {
            this.mChoreographer = Choreographer.getInstance();
            this.mFrameCallback = new Choreographer.FrameCallback() { // from class: androidx.databinding.ViewDataBinding.8
                @Override // android.view.Choreographer.FrameCallback
                public void doFrame(long j) {
                    ViewDataBinding.this.mRebindRunnable.run();
                }
            };
        } else {
            this.mFrameCallback = null;
            this.mUIThreadHandler = new Handler(Looper.myLooper());
        }
    }

    public static DataBindingComponent checkAndCastToBindingComponent(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof DataBindingComponent) {
            return (DataBindingComponent) obj;
        }
        throw new IllegalArgumentException("The provided bindingComponent parameter must be an instance of DataBindingComponent. See  https://issuetracker.google.com/issues/116541301 for details of why this parameter is not defined as DataBindingComponent");
    }

    public static <T extends ViewDataBinding> T inflateInternal(LayoutInflater layoutInflater, int i, ViewGroup viewGroup, boolean z, Object obj) {
        DataBindingComponent checkAndCastToBindingComponent = checkAndCastToBindingComponent(obj);
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        boolean z2 = viewGroup != null && z;
        int childCount = z2 ? viewGroup.getChildCount() : 0;
        View inflate = layoutInflater.inflate(i, viewGroup, z);
        if (!z2) {
            return (T) DataBindingUtil.bind(checkAndCastToBindingComponent, inflate, i);
        }
        int childCount2 = viewGroup.getChildCount();
        int i2 = childCount2 - childCount;
        if (i2 == 1) {
            return (T) DataBindingUtil.bind(checkAndCastToBindingComponent, viewGroup.getChildAt(childCount2 - 1), i);
        }
        View[] viewArr = new View[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            viewArr[i3] = viewGroup.getChildAt(i3 + childCount);
        }
        return (T) DataBindingUtil.sMapper.getDataBinder(checkAndCastToBindingComponent, viewArr, i);
    }

    public static boolean isNumeric(String str, int i) {
        int length = str.length();
        if (length == i) {
            return false;
        }
        while (i < length) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:97:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void mapBindings(androidx.databinding.DataBindingComponent r18, android.view.View r19, java.lang.Object[] r20, androidx.databinding.ViewDataBinding.IncludedLayouts r21, android.util.SparseIntArray r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 448
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.databinding.ViewDataBinding.mapBindings(androidx.databinding.DataBindingComponent, android.view.View, java.lang.Object[], androidx.databinding.ViewDataBinding$IncludedLayouts, android.util.SparseIntArray, boolean):void");
    }

    public static int parseTagInt(String str, int i) {
        int length = str.length();
        int i2 = 0;
        while (i < length) {
            i2 = (i2 * 10) + (str.charAt(i) - '0');
            i++;
        }
        return i2;
    }

    public abstract void executeBindings();

    public final void executeBindingsInternal() {
        if (this.mIsExecutingPendingBindings) {
            requestRebind();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            executeBindings();
            this.mIsExecutingPendingBindings = false;
        }
    }

    public void executePendingBindings() {
        ViewDataBinding viewDataBinding = this.mContainingBinding;
        if (viewDataBinding == null) {
            executeBindingsInternal();
        } else {
            viewDataBinding.executePendingBindings();
        }
    }

    public abstract boolean hasPendingBindings();

    public abstract void invalidateAll();

    public abstract boolean onFieldChange(int i, Object obj, int i2);

    /* JADX WARN: Multi-variable type inference failed */
    public void registerTo(int i, Object obj, CreateWeakListener createWeakListener) {
        WeakListener weakListener = this.mLocalFieldObservers[i];
        if (weakListener == null) {
            weakListener = createWeakListener.create(this, i);
            this.mLocalFieldObservers[i] = weakListener;
        }
        weakListener.unregister();
        weakListener.mTarget = obj;
        weakListener.mObservable.addListener(obj);
    }

    public void requestRebind() {
        ViewDataBinding viewDataBinding = this.mContainingBinding;
        if (viewDataBinding != null) {
            viewDataBinding.requestRebind();
            return;
        }
        synchronized (this) {
            if (!this.mPendingRebind) {
                this.mPendingRebind = true;
                if (USE_CHOREOGRAPHER) {
                    this.mChoreographer.postFrameCallback(this.mFrameCallback);
                } else {
                    this.mUIThreadHandler.post(this.mRebindRunnable);
                }
            }
        }
    }

    public boolean updateRegistration(int i, Observable observable) {
        CreateWeakListener createWeakListener = CREATE_PROPERTY_LISTENER;
        if (observable == null) {
            WeakListener weakListener = this.mLocalFieldObservers[i];
            if (weakListener != null) {
                return weakListener.unregister();
            }
        } else {
            WeakListener[] weakListenerArr = this.mLocalFieldObservers;
            WeakListener weakListener2 = weakListenerArr[i];
            if (weakListener2 == null) {
                registerTo(i, observable, createWeakListener);
            } else if (weakListener2.mTarget != observable) {
                WeakListener weakListener3 = weakListenerArr[i];
                if (weakListener3 != null) {
                    weakListener3.unregister();
                }
                registerTo(i, observable, createWeakListener);
            }
            return true;
        }
        return false;
    }

    public static Object[] mapBindings(DataBindingComponent dataBindingComponent, View view, int i, IncludedLayouts includedLayouts, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i];
        mapBindings(dataBindingComponent, view, objArr, includedLayouts, sparseIntArray, true);
        return objArr;
    }
}
