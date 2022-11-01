package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class FragmentTransaction {
    public boolean mAddToBackStack;
    public int mBreadCrumbShortTitleRes;
    public CharSequence mBreadCrumbShortTitleText;
    public int mBreadCrumbTitleRes;
    public CharSequence mBreadCrumbTitleText;
    public ArrayList<Runnable> mCommitRunnables;
    public int mEnterAnim;
    public int mExitAnim;
    public String mName;
    public int mPopEnterAnim;
    public int mPopExitAnim;
    public ArrayList<String> mSharedElementSourceNames;
    public ArrayList<String> mSharedElementTargetNames;
    public int mTransition;
    public ArrayList<Op> mOps = new ArrayList<>();
    public boolean mAllowAddToBackStack = true;
    public boolean mReorderingAllowed = false;

    /* loaded from: classes.dex */
    public static final class Op {
        public int mCmd;
        public Lifecycle.State mCurrentMaxState;
        public int mEnterAnim;
        public int mExitAnim;
        public Fragment mFragment;
        public Lifecycle.State mOldMaxState;
        public int mPopEnterAnim;
        public int mPopExitAnim;

        public Op() {
        }

        public Op(int i, Fragment fragment) {
            this.mCmd = i;
            this.mFragment = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.mOldMaxState = state;
            this.mCurrentMaxState = state;
        }
    }

    public FragmentTransaction(FragmentFactory fragmentFactory, ClassLoader classLoader) {
    }

    public FragmentTransaction add(int i, Fragment fragment) {
        doAddOp(i, fragment, null, 1);
        return this;
    }

    public void addOp(Op op) {
        this.mOps.add(op);
        op.mEnterAnim = this.mEnterAnim;
        op.mExitAnim = this.mExitAnim;
        op.mPopEnterAnim = this.mPopEnterAnim;
        op.mPopExitAnim = this.mPopExitAnim;
    }

    public abstract int commitAllowingStateLoss();

    public FragmentTransaction disallowAddToBackStack() {
        if (!this.mAddToBackStack) {
            this.mAllowAddToBackStack = false;
            return this;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public abstract void doAddOp(int i, Fragment fragment, String str, int i2);

    public abstract FragmentTransaction remove(Fragment fragment);

    public FragmentTransaction setCustomAnimations(int i, int i2) {
        this.mEnterAnim = i;
        this.mExitAnim = i2;
        this.mPopEnterAnim = 0;
        this.mPopExitAnim = 0;
        return this;
    }
}
