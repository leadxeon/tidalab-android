package androidx.loader.app;

import androidx.collection.SparseArrayCompat;
import androidx.core.app.AppOpsManagerCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider$Factory;
import androidx.lifecycle.ViewModelProvider$KeyedFactory;
import androidx.lifecycle.ViewModelProvider$OnRequeryFactory;
import androidx.lifecycle.ViewModelStore;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Objects;
/* loaded from: classes.dex */
public class LoaderManagerImpl extends LoaderManager {
    public final LifecycleOwner mLifecycleOwner;
    public final LoaderViewModel mLoaderViewModel;

    /* loaded from: classes.dex */
    public static class LoaderInfo<D> extends MutableLiveData<D> {
        @Override // androidx.lifecycle.LiveData
        public void onActive() {
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        public void onInactive() {
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void removeObserver(Observer<? super D> observer) {
            super.removeObserver(observer);
        }

        @Override // androidx.lifecycle.MutableLiveData
        public void setValue(D d) {
            super.setValue(d);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(0);
            sb.append(" : ");
            AppOpsManagerCompat.buildShortClassTag(null, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class LoaderViewModel extends ViewModel {
        public static final ViewModelProvider$Factory FACTORY = new AnonymousClass1();
        public SparseArrayCompat<LoaderInfo> mLoaders = new SparseArrayCompat<>();

        /* renamed from: androidx.loader.app.LoaderManagerImpl$LoaderViewModel$1  reason: invalid class name */
        /* loaded from: classes.dex */
        public static class AnonymousClass1 implements ViewModelProvider$Factory {
            public <T extends ViewModel> T create(Class<T> cls) {
                return new LoaderViewModel();
            }
        }

        @Override // androidx.lifecycle.ViewModel
        public void onCleared() {
            if (this.mLoaders.size() <= 0) {
                SparseArrayCompat<LoaderInfo> sparseArrayCompat = this.mLoaders;
                int i = sparseArrayCompat.mSize;
                Object[] objArr = sparseArrayCompat.mValues;
                for (int i2 = 0; i2 < i; i2++) {
                    objArr[i2] = null;
                }
                sparseArrayCompat.mSize = 0;
                return;
            }
            Objects.requireNonNull(this.mLoaders.valueAt(0));
            throw null;
        }
    }

    public LoaderManagerImpl(LifecycleOwner lifecycleOwner, ViewModelStore viewModelStore) {
        this.mLifecycleOwner = lifecycleOwner;
        ViewModelProvider$Factory viewModelProvider$Factory = LoaderViewModel.FACTORY;
        String canonicalName = LoaderViewModel.class.getCanonicalName();
        if (canonicalName != null) {
            String outline8 = GeneratedOutlineSupport.outline8("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            ViewModel viewModel = viewModelStore.mMap.get(outline8);
            if (!LoaderViewModel.class.isInstance(viewModel)) {
                if (viewModelProvider$Factory instanceof ViewModelProvider$KeyedFactory) {
                    viewModel = ((ViewModelProvider$KeyedFactory) viewModelProvider$Factory).create(outline8, LoaderViewModel.class);
                } else {
                    viewModel = ((LoaderViewModel.AnonymousClass1) viewModelProvider$Factory).create(LoaderViewModel.class);
                }
                ViewModel put = viewModelStore.mMap.put(outline8, viewModel);
                if (put != null) {
                    put.onCleared();
                }
            } else if (viewModelProvider$Factory instanceof ViewModelProvider$OnRequeryFactory) {
                ViewModelProvider$OnRequeryFactory viewModelProvider$OnRequeryFactory = (ViewModelProvider$OnRequeryFactory) viewModelProvider$Factory;
            }
            this.mLoaderViewModel = (LoaderViewModel) viewModel;
            return;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.loader.app.LoaderManager
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        LoaderViewModel loaderViewModel = this.mLoaderViewModel;
        if (loaderViewModel.mLoaders.size() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            String str2 = str + "    ";
            if (loaderViewModel.mLoaders.size() > 0) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(loaderViewModel.mLoaders.keyAt(0));
                printWriter.print(": ");
                printWriter.println(loaderViewModel.mLoaders.valueAt(0).toString());
                printWriter.print(str2);
                printWriter.print("mId=");
                printWriter.print(0);
                printWriter.print(" mArgs=");
                printWriter.println((Object) null);
                printWriter.print(str2);
                printWriter.print("mLoader=");
                printWriter.println((Object) null);
                throw null;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        AppOpsManagerCompat.buildShortClassTag(this.mLifecycleOwner, sb);
        sb.append("}}");
        return sb.toString();
    }
}
