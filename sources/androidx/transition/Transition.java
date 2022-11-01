package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.collection.ArrayMap;
import androidx.collection.ContainerHelpers;
import androidx.collection.LongSparseArray;
import androidx.core.view.ViewCompat;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    public static final int[] DEFAULT_MATCH_ORDER = {2, 1, 3, 4};
    public static final PathMotion STRAIGHT_PATH_MOTION = new PathMotion() { // from class: androidx.transition.Transition.1
        @Override // androidx.transition.PathMotion
        public Path getPath(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    };
    public static ThreadLocal<ArrayMap<Animator, AnimationInfo>> sRunningAnimators = new ThreadLocal<>();
    public ArrayList<TransitionValues> mEndValuesList;
    public EpicenterCallback mEpicenterCallback;
    public ArrayList<TransitionValues> mStartValuesList;
    public String mName = getClass().getName();
    public long mStartDelay = -1;
    public long mDuration = -1;
    public TimeInterpolator mInterpolator = null;
    public ArrayList<Integer> mTargetIds = new ArrayList<>();
    public ArrayList<View> mTargets = new ArrayList<>();
    public TransitionValuesMaps mStartValues = new TransitionValuesMaps();
    public TransitionValuesMaps mEndValues = new TransitionValuesMaps();
    public TransitionSet mParent = null;
    public int[] mMatchOrder = DEFAULT_MATCH_ORDER;
    public ArrayList<Animator> mCurrentAnimators = new ArrayList<>();
    public int mNumInstances = 0;
    public boolean mPaused = false;
    public boolean mEnded = false;
    public ArrayList<TransitionListener> mListeners = null;
    public ArrayList<Animator> mAnimators = new ArrayList<>();
    public PathMotion mPathMotion = STRAIGHT_PATH_MOTION;

    /* loaded from: classes.dex */
    public static class AnimationInfo {
        public String mName;
        public Transition mTransition;
        public TransitionValues mValues;
        public View mView;
        public WindowIdImpl mWindowId;

        public AnimationInfo(View view, String str, Transition transition, WindowIdImpl windowIdImpl, TransitionValues transitionValues) {
            this.mView = view;
            this.mName = str;
            this.mValues = transitionValues;
            this.mWindowId = windowIdImpl;
            this.mTransition = transition;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class EpicenterCallback {
    }

    /* loaded from: classes.dex */
    public interface TransitionListener {
        void onTransitionCancel(Transition transition);

        void onTransitionEnd(Transition transition);

        void onTransitionPause(Transition transition);

        void onTransitionResume(Transition transition);

        void onTransitionStart(Transition transition);
    }

    public static void addViewValues(TransitionValuesMaps transitionValuesMaps, View view, TransitionValues transitionValues) {
        transitionValuesMaps.mViewValues.put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (transitionValuesMaps.mIdValues.indexOfKey(id) >= 0) {
                transitionValuesMaps.mIdValues.put(id, null);
            } else {
                transitionValuesMaps.mIdValues.put(id, view);
            }
        }
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        String transitionName = view.getTransitionName();
        if (transitionName != null) {
            if (transitionValuesMaps.mNameValues.indexOfKey(transitionName) >= 0) {
                transitionValuesMaps.mNameValues.put(transitionName, null);
            } else {
                transitionValuesMaps.mNameValues.put(transitionName, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                LongSparseArray<View> longSparseArray = transitionValuesMaps.mItemIdValues;
                if (longSparseArray.mGarbage) {
                    longSparseArray.gc();
                }
                if (ContainerHelpers.binarySearch(longSparseArray.mKeys, longSparseArray.mSize, itemIdAtPosition) >= 0) {
                    View view2 = transitionValuesMaps.mItemIdValues.get(itemIdAtPosition);
                    if (view2 != null) {
                        view2.setHasTransientState(false);
                        transitionValuesMaps.mItemIdValues.put(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                view.setHasTransientState(true);
                transitionValuesMaps.mItemIdValues.put(itemIdAtPosition, view);
            }
        }
    }

    public static ArrayMap<Animator, AnimationInfo> getRunningAnimators() {
        ArrayMap<Animator, AnimationInfo> arrayMap = sRunningAnimators.get();
        if (arrayMap != null) {
            return arrayMap;
        }
        ArrayMap<Animator, AnimationInfo> arrayMap2 = new ArrayMap<>();
        sRunningAnimators.set(arrayMap2);
        return arrayMap2;
    }

    public static boolean isValueChanged(TransitionValues transitionValues, TransitionValues transitionValues2, String str) {
        Object obj = transitionValues.values.get(str);
        Object obj2 = transitionValues2.values.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public Transition addListener(TransitionListener transitionListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(transitionListener);
        return this;
    }

    public Transition addTarget(View view) {
        this.mTargets.add(view);
        return this;
    }

    public abstract void captureEndValues(TransitionValues transitionValues);

    public final void captureHierarchy(View view, boolean z) {
        if (view != null) {
            view.getId();
            if (view.getParent() instanceof ViewGroup) {
                TransitionValues transitionValues = new TransitionValues(view);
                if (z) {
                    captureStartValues(transitionValues);
                } else {
                    captureEndValues(transitionValues);
                }
                transitionValues.mTargetedTransitions.add(this);
                capturePropagationValues(transitionValues);
                if (z) {
                    addViewValues(this.mStartValues, view, transitionValues);
                } else {
                    addViewValues(this.mEndValues, view, transitionValues);
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    captureHierarchy(viewGroup.getChildAt(i), z);
                }
            }
        }
    }

    public void capturePropagationValues(TransitionValues transitionValues) {
    }

    public abstract void captureStartValues(TransitionValues transitionValues);

    public void captureValues(ViewGroup viewGroup, boolean z) {
        clearValues(z);
        if (this.mTargetIds.size() > 0 || this.mTargets.size() > 0) {
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                View findViewById = viewGroup.findViewById(this.mTargetIds.get(i).intValue());
                if (findViewById != null) {
                    TransitionValues transitionValues = new TransitionValues(findViewById);
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    transitionValues.mTargetedTransitions.add(this);
                    capturePropagationValues(transitionValues);
                    if (z) {
                        addViewValues(this.mStartValues, findViewById, transitionValues);
                    } else {
                        addViewValues(this.mEndValues, findViewById, transitionValues);
                    }
                }
            }
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                View view = this.mTargets.get(i2);
                TransitionValues transitionValues2 = new TransitionValues(view);
                if (z) {
                    captureStartValues(transitionValues2);
                } else {
                    captureEndValues(transitionValues2);
                }
                transitionValues2.mTargetedTransitions.add(this);
                capturePropagationValues(transitionValues2);
                if (z) {
                    addViewValues(this.mStartValues, view, transitionValues2);
                } else {
                    addViewValues(this.mEndValues, view, transitionValues2);
                }
            }
            return;
        }
        captureHierarchy(viewGroup, z);
    }

    public void clearValues(boolean z) {
        if (z) {
            this.mStartValues.mViewValues.clear();
            this.mStartValues.mIdValues.clear();
            this.mStartValues.mItemIdValues.clear();
            return;
        }
        this.mEndValues.mViewValues.clear();
        this.mEndValues.mIdValues.clear();
        this.mEndValues.mItemIdValues.clear();
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        Animator createAnimator;
        TransitionValues transitionValues;
        View view;
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            TransitionValues transitionValues2 = arrayList.get(i);
            TransitionValues transitionValues3 = arrayList2.get(i);
            if (transitionValues2 != null && !transitionValues2.mTargetedTransitions.contains(this)) {
                transitionValues2 = null;
            }
            if (transitionValues3 != null && !transitionValues3.mTargetedTransitions.contains(this)) {
                transitionValues3 = null;
            }
            if (!(transitionValues2 == null && transitionValues3 == null)) {
                if ((transitionValues2 == null || transitionValues3 == null || isTransitionRequired(transitionValues2, transitionValues3)) && (createAnimator = createAnimator(viewGroup, transitionValues2, transitionValues3)) != null) {
                    if (transitionValues3 != null) {
                        View view2 = transitionValues3.view;
                        String[] transitionProperties = getTransitionProperties();
                        if (transitionProperties != null && transitionProperties.length > 0) {
                            transitionValues = new TransitionValues(view2);
                            TransitionValues transitionValues4 = transitionValuesMaps2.mViewValues.get(view2);
                            if (transitionValues4 != null) {
                                int i2 = 0;
                                while (i2 < transitionProperties.length) {
                                    transitionValues.values.put(transitionProperties[i2], transitionValues4.values.get(transitionProperties[i2]));
                                    i2++;
                                    createAnimator = createAnimator;
                                    size = size;
                                    transitionValues4 = transitionValues4;
                                }
                            }
                            int i3 = runningAnimators.mSize;
                            int i4 = 0;
                            while (true) {
                                if (i4 >= i3) {
                                    break;
                                }
                                AnimationInfo animationInfo = runningAnimators.get(runningAnimators.keyAt(i4));
                                if (animationInfo.mValues != null && animationInfo.mView == view2 && animationInfo.mName.equals(this.mName) && animationInfo.mValues.equals(transitionValues)) {
                                    createAnimator = null;
                                    break;
                                }
                                i4++;
                            }
                        } else {
                            size = size;
                            createAnimator = createAnimator;
                            transitionValues = null;
                        }
                        view = view2;
                    } else {
                        size = size;
                        view = transitionValues2.view;
                        createAnimator = createAnimator;
                        transitionValues = null;
                    }
                    if (createAnimator != null) {
                        String str = this.mName;
                        ViewUtilsBase viewUtilsBase = ViewUtils.IMPL;
                        runningAnimators.put(createAnimator, new AnimationInfo(view, str, this, new WindowIdApi18(viewGroup), transitionValues));
                        this.mAnimators.add(createAnimator);
                    }
                }
            }
            size = size;
        }
        if (sparseIntArray.size() != 0) {
            for (int i5 = 0; i5 < sparseIntArray.size(); i5++) {
                Animator animator = this.mAnimators.get(sparseIntArray.keyAt(i5));
                animator.setStartDelay(animator.getStartDelay() + (sparseIntArray.valueAt(i5) - Long.MAX_VALUE));
            }
        }
    }

    public void end() {
        int i = this.mNumInstances - 1;
        this.mNumInstances = i;
        if (i == 0) {
            ArrayList<TransitionListener> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((TransitionListener) arrayList2.get(i2)).onTransitionEnd(this);
                }
            }
            for (int i3 = 0; i3 < this.mStartValues.mItemIdValues.size(); i3++) {
                View valueAt = this.mStartValues.mItemIdValues.valueAt(i3);
                if (valueAt != null) {
                    AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                    valueAt.setHasTransientState(false);
                }
            }
            for (int i4 = 0; i4 < this.mEndValues.mItemIdValues.size(); i4++) {
                View valueAt2 = this.mEndValues.mItemIdValues.valueAt(i4);
                if (valueAt2 != null) {
                    AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                    valueAt2.setHasTransientState(false);
                }
            }
            this.mEnded = true;
        }
    }

    public TransitionValues getMatchedTransitionValues(View view, boolean z) {
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getMatchedTransitionValues(view, z);
        }
        ArrayList<TransitionValues> arrayList = z ? this.mStartValuesList : this.mEndValuesList;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i = -1;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            TransitionValues transitionValues = arrayList.get(i2);
            if (transitionValues == null) {
                return null;
            }
            if (transitionValues.view == view) {
                i = i2;
                break;
            }
            i2++;
        }
        if (i < 0) {
            return null;
        }
        return (z ? this.mEndValuesList : this.mStartValuesList).get(i);
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public TransitionValues getTransitionValues(View view, boolean z) {
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getTransitionValues(view, z);
        }
        return (z ? this.mStartValues : this.mEndValues).mViewValues.getOrDefault(view, null);
    }

    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return false;
        }
        String[] transitionProperties = getTransitionProperties();
        if (transitionProperties != null) {
            for (String str : transitionProperties) {
                if (!isValueChanged(transitionValues, transitionValues2, str)) {
                }
            }
            return false;
        }
        for (String str2 : transitionValues.values.keySet()) {
            if (isValueChanged(transitionValues, transitionValues2, str2)) {
            }
        }
        return false;
        return true;
    }

    public boolean isValidTarget(View view) {
        return (this.mTargetIds.size() == 0 && this.mTargets.size() == 0) || this.mTargetIds.contains(Integer.valueOf(view.getId())) || this.mTargets.contains(view);
    }

    public void pause(View view) {
        if (!this.mEnded) {
            ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
            int i = runningAnimators.mSize;
            ViewUtilsBase viewUtilsBase = ViewUtils.IMPL;
            WindowIdApi18 windowIdApi18 = new WindowIdApi18(view);
            for (int i2 = i - 1; i2 >= 0; i2--) {
                AnimationInfo valueAt = runningAnimators.valueAt(i2);
                if (valueAt.mView != null && windowIdApi18.equals(valueAt.mWindowId)) {
                    runningAnimators.keyAt(i2).pause();
                }
            }
            ArrayList<TransitionListener> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i3 = 0; i3 < size; i3++) {
                    ((TransitionListener) arrayList2.get(i3)).onTransitionPause(this);
                }
            }
            this.mPaused = true;
        }
    }

    public Transition removeListener(TransitionListener transitionListener) {
        ArrayList<TransitionListener> arrayList = this.mListeners;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(transitionListener);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
        return this;
    }

    public Transition removeTarget(View view) {
        this.mTargets.remove(view);
        return this;
    }

    public void resume(View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
                int i = runningAnimators.mSize;
                ViewUtilsBase viewUtilsBase = ViewUtils.IMPL;
                WindowIdApi18 windowIdApi18 = new WindowIdApi18(view);
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    AnimationInfo valueAt = runningAnimators.valueAt(i2);
                    if (valueAt.mView != null && windowIdApi18.equals(valueAt.mWindowId)) {
                        runningAnimators.keyAt(i2).resume();
                    }
                }
                ArrayList<TransitionListener> arrayList = this.mListeners;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                    int size = arrayList2.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        ((TransitionListener) arrayList2.get(i3)).onTransitionResume(this);
                    }
                }
            }
            this.mPaused = false;
        }
    }

    public void runAnimators() {
        start();
        final ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        Iterator<Animator> it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (runningAnimators.containsKey(next)) {
                start();
                if (next != null) {
                    next.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.Transition.2
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            runningAnimators.remove(animator);
                            Transition.this.mCurrentAnimators.remove(animator);
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                            Transition.this.mCurrentAnimators.add(animator);
                        }
                    });
                    long j = this.mDuration;
                    if (j >= 0) {
                        next.setDuration(j);
                    }
                    long j2 = this.mStartDelay;
                    if (j2 >= 0) {
                        next.setStartDelay(next.getStartDelay() + j2);
                    }
                    TimeInterpolator timeInterpolator = this.mInterpolator;
                    if (timeInterpolator != null) {
                        next.setInterpolator(timeInterpolator);
                    }
                    next.addListener(new AnimatorListenerAdapter() { // from class: androidx.transition.Transition.3
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            Transition.this.end();
                            animator.removeListener(this);
                        }
                    });
                    next.start();
                }
            }
        }
        this.mAnimators.clear();
        end();
    }

    public Transition setDuration(long j) {
        this.mDuration = j;
        return this;
    }

    public void setEpicenterCallback(EpicenterCallback epicenterCallback) {
        this.mEpicenterCallback = epicenterCallback;
    }

    public Transition setInterpolator(TimeInterpolator timeInterpolator) {
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public void setPathMotion(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.mPathMotion = STRAIGHT_PATH_MOTION;
        } else {
            this.mPathMotion = pathMotion;
        }
    }

    public void setPropagation(TransitionPropagation transitionPropagation) {
    }

    public Transition setStartDelay(long j) {
        this.mStartDelay = j;
        return this;
    }

    public void start() {
        if (this.mNumInstances == 0) {
            ArrayList<TransitionListener> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((TransitionListener) arrayList2.get(i)).onTransitionStart(this);
                }
            }
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    public String toString() {
        return toString(HttpUrl.FRAGMENT_ENCODE_SET);
    }

    public Transition clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.mAnimators = new ArrayList<>();
            transition.mStartValues = new TransitionValuesMaps();
            transition.mEndValues = new TransitionValuesMaps();
            transition.mStartValuesList = null;
            transition.mEndValuesList = null;
            return transition;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public String toString(String str) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13(str);
        outline13.append(getClass().getSimpleName());
        outline13.append("@");
        outline13.append(Integer.toHexString(hashCode()));
        outline13.append(": ");
        String sb = outline13.toString();
        if (this.mDuration != -1) {
            sb = sb + "dur(" + this.mDuration + ") ";
        }
        if (this.mStartDelay != -1) {
            sb = sb + "dly(" + this.mStartDelay + ") ";
        }
        if (this.mInterpolator != null) {
            sb = sb + "interp(" + this.mInterpolator + ") ";
        }
        if (this.mTargetIds.size() <= 0 && this.mTargets.size() <= 0) {
            return sb;
        }
        String outline8 = GeneratedOutlineSupport.outline8(sb, "tgts(");
        if (this.mTargetIds.size() > 0) {
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                if (i > 0) {
                    outline8 = GeneratedOutlineSupport.outline8(outline8, ", ");
                }
                StringBuilder outline132 = GeneratedOutlineSupport.outline13(outline8);
                outline132.append(this.mTargetIds.get(i));
                outline8 = outline132.toString();
            }
        }
        if (this.mTargets.size() > 0) {
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                if (i2 > 0) {
                    outline8 = GeneratedOutlineSupport.outline8(outline8, ", ");
                }
                StringBuilder outline133 = GeneratedOutlineSupport.outline13(outline8);
                outline133.append(this.mTargets.get(i2));
                outline8 = outline133.toString();
            }
        }
        return GeneratedOutlineSupport.outline8(outline8, ")");
    }
}
