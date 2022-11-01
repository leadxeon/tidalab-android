package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
/* loaded from: classes.dex */
public abstract class ActivityResultRegistry {
    public Random mRandom = new Random();
    public final Map<Integer, String> mRcToKey = new HashMap();
    public final Map<String, Integer> mKeyToRc = new HashMap();
    public final Map<String, LifecycleContainer> mKeyToLifecycleContainers = new HashMap();
    public ArrayList<String> mLaunchedKeys = new ArrayList<>();
    public final transient Map<String, CallbackAndContract<?>> mKeyToCallback = new HashMap();
    public final Map<String, Object> mParsedPendingResults = new HashMap();
    public final Bundle mPendingResults = new Bundle();

    /* loaded from: classes.dex */
    public static class CallbackAndContract<O> {
        public final ActivityResultCallback<O> mCallback;
        public final ActivityResultContract<?, O> mContract;

        public CallbackAndContract(ActivityResultCallback<O> activityResultCallback, ActivityResultContract<?, O> activityResultContract) {
            this.mCallback = activityResultCallback;
            this.mContract = activityResultContract;
        }
    }

    /* loaded from: classes.dex */
    public static class LifecycleContainer {
        public final Lifecycle mLifecycle;
        public final ArrayList<LifecycleEventObserver> mObservers = new ArrayList<>();

        public LifecycleContainer(Lifecycle lifecycle) {
            this.mLifecycle = lifecycle;
        }
    }

    public final boolean dispatchResult(int i, int i2, Intent intent) {
        ActivityResultCallback<?> activityResultCallback;
        String str = this.mRcToKey.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        this.mLaunchedKeys.remove(str);
        CallbackAndContract<?> callbackAndContract = this.mKeyToCallback.get(str);
        if (callbackAndContract == null || (activityResultCallback = callbackAndContract.mCallback) == null) {
            this.mParsedPendingResults.remove(str);
            this.mPendingResults.putParcelable(str, new ActivityResult(i2, intent));
            return true;
        }
        activityResultCallback.onActivityResult(callbackAndContract.mContract.parseResult(i2, intent));
        return true;
    }

    public abstract <I, O> void onLaunch(int i, ActivityResultContract<I, O> activityResultContract, @SuppressLint({"UnknownNullness"}) I i2, ActivityOptionsCompat activityOptionsCompat);

    /* JADX WARN: Multi-variable type inference failed */
    public final <I, O> ActivityResultLauncher<I> register(final String str, final ActivityResultContract<I, O> activityResultContract, ActivityResultCallback<O> activityResultCallback) {
        final int registerKey = registerKey(str);
        this.mKeyToCallback.put(str, new CallbackAndContract<>(activityResultCallback, activityResultContract));
        if (this.mParsedPendingResults.containsKey(str)) {
            Object obj = this.mParsedPendingResults.get(str);
            this.mParsedPendingResults.remove(str);
            activityResultCallback.onActivityResult(obj);
        }
        ActivityResult activityResult = (ActivityResult) this.mPendingResults.getParcelable(str);
        if (activityResult != null) {
            this.mPendingResults.remove(str);
            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.mResultCode, activityResult.mData));
        }
        return new ActivityResultLauncher<I>() { // from class: androidx.activity.result.ActivityResultRegistry.3
            @Override // androidx.activity.result.ActivityResultLauncher
            public void unregister() {
                ActivityResultRegistry.this.unregister(str);
            }
        };
    }

    public final int registerKey(String str) {
        Integer num = this.mKeyToRc.get(str);
        if (num != null) {
            return num.intValue();
        }
        int nextInt = this.mRandom.nextInt(2147418112);
        while (true) {
            int i = nextInt + 65536;
            if (this.mRcToKey.containsKey(Integer.valueOf(i))) {
                nextInt = this.mRandom.nextInt(2147418112);
            } else {
                this.mRcToKey.put(Integer.valueOf(i), str);
                this.mKeyToRc.put(str, Integer.valueOf(i));
                return i;
            }
        }
    }

    public final void unregister(String str) {
        Integer remove;
        if (!this.mLaunchedKeys.contains(str) && (remove = this.mKeyToRc.remove(str)) != null) {
            this.mRcToKey.remove(remove);
        }
        this.mKeyToCallback.remove(str);
        if (this.mParsedPendingResults.containsKey(str)) {
            StringBuilder outline16 = GeneratedOutlineSupport.outline16("Dropping pending result for request ", str, ": ");
            outline16.append(this.mParsedPendingResults.get(str));
            Log.w("ActivityResultRegistry", outline16.toString());
            this.mParsedPendingResults.remove(str);
        }
        if (this.mPendingResults.containsKey(str)) {
            StringBuilder outline162 = GeneratedOutlineSupport.outline16("Dropping pending result for request ", str, ": ");
            outline162.append(this.mPendingResults.getParcelable(str));
            Log.w("ActivityResultRegistry", outline162.toString());
            this.mPendingResults.remove(str);
        }
        LifecycleContainer lifecycleContainer = this.mKeyToLifecycleContainers.get(str);
        if (lifecycleContainer != null) {
            Iterator<LifecycleEventObserver> it = lifecycleContainer.mObservers.iterator();
            while (it.hasNext()) {
                lifecycleContainer.mLifecycle.removeObserver(it.next());
            }
            lifecycleContainer.mObservers.clear();
            this.mKeyToLifecycleContainers.remove(str);
        }
    }
}
