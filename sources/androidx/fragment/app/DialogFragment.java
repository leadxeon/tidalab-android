package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
/* loaded from: classes.dex */
public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    public boolean mCreatingDialog;
    public Dialog mDialog;
    public boolean mDismissed;
    public Handler mHandler;
    public boolean mShownByMe;
    public boolean mViewDestroyed;
    public Runnable mDismissRunnable = new Runnable() { // from class: androidx.fragment.app.DialogFragment.1
        @Override // java.lang.Runnable
        @SuppressLint({"SyntheticAccessor"})
        public void run() {
            DialogFragment dialogFragment = DialogFragment.this;
            dialogFragment.mOnDismissListener.onDismiss(dialogFragment.mDialog);
        }
    };
    public DialogInterface.OnCancelListener mOnCancelListener = new DialogInterface.OnCancelListener() { // from class: androidx.fragment.app.DialogFragment.2
        @Override // android.content.DialogInterface.OnCancelListener
        @SuppressLint({"SyntheticAccessor"})
        public void onCancel(DialogInterface dialogInterface) {
            DialogFragment dialogFragment = DialogFragment.this;
            Dialog dialog = dialogFragment.mDialog;
            if (dialog != null) {
                dialogFragment.onCancel(dialog);
            }
        }
    };
    public DialogInterface.OnDismissListener mOnDismissListener = new DialogInterface.OnDismissListener() { // from class: androidx.fragment.app.DialogFragment.3
        @Override // android.content.DialogInterface.OnDismissListener
        @SuppressLint({"SyntheticAccessor"})
        public void onDismiss(DialogInterface dialogInterface) {
            DialogFragment dialogFragment = DialogFragment.this;
            Dialog dialog = dialogFragment.mDialog;
            if (dialog != null) {
                dialogFragment.onDismiss(dialog);
            }
        }
    };
    public int mStyle = 0;
    public int mTheme = 0;
    public boolean mCancelable = true;
    public boolean mShowsDialog = true;
    public int mBackStackId = -1;
    public Observer<LifecycleOwner> mObserver = new Observer<LifecycleOwner>() { // from class: androidx.fragment.app.DialogFragment.4
        @Override // androidx.lifecycle.Observer
        @SuppressLint({"SyntheticAccessor"})
        public void onChanged(LifecycleOwner lifecycleOwner) {
            if (lifecycleOwner != null) {
                DialogFragment dialogFragment = DialogFragment.this;
                if (dialogFragment.mShowsDialog) {
                    View requireView = dialogFragment.requireView();
                    if (requireView.getParent() != null) {
                        throw new IllegalStateException("DialogFragment can not be attached to a container view");
                    } else if (DialogFragment.this.mDialog != null) {
                        if (FragmentManager.isLoggingEnabled(3)) {
                            Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + DialogFragment.this.mDialog);
                        }
                        DialogFragment.this.mDialog.setContentView(requireView);
                    }
                }
            }
        }
    };
    public boolean mDialogCreated = false;

    @Override // androidx.fragment.app.Fragment
    public FragmentContainer createFragmentContainer() {
        final Fragment.AnonymousClass4 r0 = new Fragment.AnonymousClass4();
        return new FragmentContainer() { // from class: androidx.fragment.app.DialogFragment.5
            @Override // androidx.fragment.app.FragmentContainer
            public View onFindViewById(int i) {
                if (r0.onHasView()) {
                    return r0.onFindViewById(i);
                }
                Dialog dialog = DialogFragment.this.mDialog;
                if (dialog != null) {
                    return dialog.findViewById(i);
                }
                return null;
            }

            @Override // androidx.fragment.app.FragmentContainer
            public boolean onHasView() {
                return r0.onHasView() || DialogFragment.this.mDialogCreated;
            }
        };
    }

    public final void dismissInternal(boolean z, boolean z2) {
        if (!this.mDismissed) {
            this.mDismissed = true;
            this.mShownByMe = false;
            Dialog dialog = this.mDialog;
            if (dialog != null) {
                dialog.setOnDismissListener(null);
                this.mDialog.dismiss();
                if (!z2) {
                    if (Looper.myLooper() == this.mHandler.getLooper()) {
                        onDismiss(this.mDialog);
                    } else {
                        this.mHandler.post(this.mDismissRunnable);
                    }
                }
            }
            this.mViewDestroyed = true;
            if (this.mBackStackId >= 0) {
                FragmentManager parentFragmentManager = getParentFragmentManager();
                int i = this.mBackStackId;
                if (i >= 0) {
                    parentFragmentManager.enqueueAction(new FragmentManager.PopBackStackState(null, i, 1), false);
                    this.mBackStackId = -1;
                    return;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("Bad id: ", i));
            }
            BackStackRecord backStackRecord = new BackStackRecord(getParentFragmentManager());
            backStackRecord.remove(this);
            if (z) {
                backStackRecord.commitAllowingStateLoss();
            } else {
                backStackRecord.commitInternal(false);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        MutableLiveData<LifecycleOwner> mutableLiveData = this.mViewLifecycleOwnerLiveData;
        Observer<LifecycleOwner> observer = this.mObserver;
        Objects.requireNonNull(mutableLiveData);
        LiveData.assertMainThread("observeForever");
        LiveData.AlwaysActiveObserver alwaysActiveObserver = new LiveData.AlwaysActiveObserver(mutableLiveData, observer);
        LiveData<LifecycleOwner>.ObserverWrapper putIfAbsent = mutableLiveData.mObservers.putIfAbsent(observer, alwaysActiveObserver);
        if (!(putIfAbsent instanceof LiveData.LifecycleBoundObserver)) {
            if (putIfAbsent == null) {
                alwaysActiveObserver.activeStateChanged(true);
            }
            if (!this.mShownByMe) {
                this.mDismissed = false;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler = new Handler();
        this.mShowsDialog = this.mContainerId == 0;
        if (bundle != null) {
            this.mStyle = bundle.getInt("android:style", 0);
            this.mTheme = bundle.getInt("android:theme", 0);
            this.mCancelable = bundle.getBoolean("android:cancelable", true);
            this.mShowsDialog = bundle.getBoolean("android:showsDialog", this.mShowsDialog);
            this.mBackStackId = bundle.getInt("android:backStackId", -1);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
        }
        return new Dialog(requireContext(), this.mTheme);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mCalled = true;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = true;
            dialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            if (!this.mDismissed) {
                onDismiss(this.mDialog);
            }
            this.mDialog = null;
            this.mDialogCreated = false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        this.mCalled = true;
        if (!this.mShownByMe && !this.mDismissed) {
            this.mDismissed = true;
        }
        this.mViewLifecycleOwnerLiveData.removeObserver(this.mObserver);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.mViewDestroyed) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
            }
            dismissInternal(true, true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0046 A[Catch: all -> 0x006b, TryCatch #0 {all -> 0x006b, blocks: (B:10:0x001a, B:12:0x0026, B:18:0x0030, B:20:0x0036, B:21:0x003b, B:22:0x003e, B:24:0x0046, B:25:0x004d, B:26:0x0065), top: B:45:0x001a }] */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.LayoutInflater onGetLayoutInflater(android.os.Bundle r8) {
        /*
            r7 = this;
            android.view.LayoutInflater r0 = r7.getLayoutInflater()
            boolean r1 = r7.mShowsDialog
            java.lang.String r2 = "FragmentManager"
            r3 = 2
            if (r1 == 0) goto L_0x009b
            boolean r4 = r7.mCreatingDialog
            if (r4 == 0) goto L_0x0011
            goto L_0x009b
        L_0x0011:
            if (r1 != 0) goto L_0x0014
            goto L_0x006f
        L_0x0014:
            boolean r1 = r7.mDialogCreated
            if (r1 != 0) goto L_0x006f
            r1 = 0
            r4 = 1
            r7.mCreatingDialog = r4     // Catch: all -> 0x006b
            android.app.Dialog r8 = r7.onCreateDialog(r8)     // Catch: all -> 0x006b
            r7.mDialog = r8     // Catch: all -> 0x006b
            boolean r5 = r7.mShowsDialog     // Catch: all -> 0x006b
            if (r5 == 0) goto L_0x0065
            int r5 = r7.mStyle     // Catch: all -> 0x006b
            if (r5 == r4) goto L_0x003b
            if (r5 == r3) goto L_0x003b
            r6 = 3
            if (r5 == r6) goto L_0x0030
            goto L_0x003e
        L_0x0030:
            android.view.Window r5 = r8.getWindow()     // Catch: all -> 0x006b
            if (r5 == 0) goto L_0x003b
            r6 = 24
            r5.addFlags(r6)     // Catch: all -> 0x006b
        L_0x003b:
            r8.requestWindowFeature(r4)     // Catch: all -> 0x006b
        L_0x003e:
            android.content.Context r8 = r7.getContext()     // Catch: all -> 0x006b
            boolean r5 = r8 instanceof android.app.Activity     // Catch: all -> 0x006b
            if (r5 == 0) goto L_0x004d
            android.app.Dialog r5 = r7.mDialog     // Catch: all -> 0x006b
            android.app.Activity r8 = (android.app.Activity) r8     // Catch: all -> 0x006b
            r5.setOwnerActivity(r8)     // Catch: all -> 0x006b
        L_0x004d:
            android.app.Dialog r8 = r7.mDialog     // Catch: all -> 0x006b
            boolean r5 = r7.mCancelable     // Catch: all -> 0x006b
            r8.setCancelable(r5)     // Catch: all -> 0x006b
            android.app.Dialog r8 = r7.mDialog     // Catch: all -> 0x006b
            android.content.DialogInterface$OnCancelListener r5 = r7.mOnCancelListener     // Catch: all -> 0x006b
            r8.setOnCancelListener(r5)     // Catch: all -> 0x006b
            android.app.Dialog r8 = r7.mDialog     // Catch: all -> 0x006b
            android.content.DialogInterface$OnDismissListener r5 = r7.mOnDismissListener     // Catch: all -> 0x006b
            r8.setOnDismissListener(r5)     // Catch: all -> 0x006b
            r7.mDialogCreated = r4     // Catch: all -> 0x006b
            goto L_0x0068
        L_0x0065:
            r8 = 0
            r7.mDialog = r8     // Catch: all -> 0x006b
        L_0x0068:
            r7.mCreatingDialog = r1
            goto L_0x006f
        L_0x006b:
            r8 = move-exception
            r7.mCreatingDialog = r1
            throw r8
        L_0x006f:
            boolean r8 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r3)
            if (r8 == 0) goto L_0x008e
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "get layout inflater for DialogFragment "
            r8.append(r1)
            r8.append(r7)
            java.lang.String r1 = " from dialog context"
            r8.append(r1)
            java.lang.String r8 = r8.toString()
            android.util.Log.d(r2, r8)
        L_0x008e:
            android.app.Dialog r8 = r7.mDialog
            if (r8 == 0) goto L_0x009a
            android.content.Context r8 = r8.getContext()
            android.view.LayoutInflater r0 = r0.cloneInContext(r8)
        L_0x009a:
            return r0
        L_0x009b:
            boolean r8 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r3)
            if (r8 == 0) goto L_0x00df
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "getting layout inflater for DialogFragment "
            r8.append(r1)
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            boolean r1 = r7.mShowsDialog
            if (r1 != 0) goto L_0x00cb
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "mShowsDialog = false: "
            r1.append(r3)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            android.util.Log.d(r2, r8)
            goto L_0x00df
        L_0x00cb:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "mCreatingDialog = true: "
            r1.append(r3)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            android.util.Log.d(r2, r8)
        L_0x00df:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DialogFragment.onGetLayoutInflater(android.os.Bundle):android.view.LayoutInflater");
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            Bundle onSaveInstanceState = dialog.onSaveInstanceState();
            onSaveInstanceState.putBoolean("android:dialogShowing", false);
            bundle.putBundle("android:savedDialogState", onSaveInstanceState);
        }
        int i = this.mStyle;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.mTheme;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.mCancelable;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.mShowsDialog;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.mBackStackId;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        this.mCalled = true;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = false;
            dialog.show();
            View decorView = this.mDialog.getWindow().getDecorView();
            decorView.setTag(R.id.view_tree_lifecycle_owner, this);
            decorView.setTag(R.id.view_tree_view_model_store_owner, this);
            decorView.setTag(R.id.view_tree_saved_state_registry_owner, this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        this.mCalled = true;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        Bundle bundle2;
        this.mCalled = true;
        if (this.mDialog != null && bundle != null && (bundle2 = bundle.getBundle("android:savedDialogState")) != null) {
            this.mDialog.onRestoreInstanceState(bundle2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.performCreateView(layoutInflater, viewGroup, bundle);
        if (this.mView == null && this.mDialog != null && bundle != null && (bundle2 = bundle.getBundle("android:savedDialogState")) != null) {
            this.mDialog.onRestoreInstanceState(bundle2);
        }
    }

    public final Dialog requireDialog() {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            return dialog;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public void show(FragmentManager fragmentManager, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
        backStackRecord.doAddOp(0, this, str, 1);
        backStackRecord.commitInternal(false);
    }
}
