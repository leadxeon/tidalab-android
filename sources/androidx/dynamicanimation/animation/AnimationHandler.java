package androidx.dynamicanimation.animation;

import android.view.Choreographer;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class AnimationHandler {
    public static final ThreadLocal<AnimationHandler> sAnimatorHandler = new ThreadLocal<>();
    public AnimationFrameCallbackProvider mProvider;
    public final SimpleArrayMap<AnimationFrameCallback, Long> mDelayedCallbackStartTime = new SimpleArrayMap<>();
    public final ArrayList<AnimationFrameCallback> mAnimationCallbacks = new ArrayList<>();
    public final AnimationCallbackDispatcher mCallbackDispatcher = new AnimationCallbackDispatcher();
    public long mCurrentFrameTime = 0;
    public boolean mListDirty = false;

    /* loaded from: classes.dex */
    public class AnimationCallbackDispatcher {
        public AnimationCallbackDispatcher() {
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0046 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void dispatchAnimationFrame() {
            /*
                r11 = this;
                androidx.dynamicanimation.animation.AnimationHandler r0 = androidx.dynamicanimation.animation.AnimationHandler.this
                long r1 = android.os.SystemClock.uptimeMillis()
                r0.mCurrentFrameTime = r1
                androidx.dynamicanimation.animation.AnimationHandler r0 = androidx.dynamicanimation.animation.AnimationHandler.this
                long r1 = r0.mCurrentFrameTime
                long r3 = android.os.SystemClock.uptimeMillis()
                r5 = 0
                r6 = 0
            L_0x0012:
                java.util.ArrayList<androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallback> r7 = r0.mAnimationCallbacks
                int r7 = r7.size()
                if (r6 >= r7) goto L_0x0049
                java.util.ArrayList<androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallback> r7 = r0.mAnimationCallbacks
                java.lang.Object r7 = r7.get(r6)
                androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallback r7 = (androidx.dynamicanimation.animation.AnimationHandler.AnimationFrameCallback) r7
                if (r7 != 0) goto L_0x0025
                goto L_0x0046
            L_0x0025:
                androidx.collection.SimpleArrayMap<androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallback, java.lang.Long> r8 = r0.mDelayedCallbackStartTime
                r9 = 0
                java.lang.Object r8 = r8.getOrDefault(r7, r9)
                java.lang.Long r8 = (java.lang.Long) r8
                if (r8 != 0) goto L_0x0031
                goto L_0x003e
            L_0x0031:
                long r8 = r8.longValue()
                int r10 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
                if (r10 >= 0) goto L_0x0040
                androidx.collection.SimpleArrayMap<androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallback, java.lang.Long> r8 = r0.mDelayedCallbackStartTime
                r8.remove(r7)
            L_0x003e:
                r8 = 1
                goto L_0x0041
            L_0x0040:
                r8 = 0
            L_0x0041:
                if (r8 == 0) goto L_0x0046
                r7.doAnimationFrame(r1)
            L_0x0046:
                int r6 = r6 + 1
                goto L_0x0012
            L_0x0049:
                boolean r1 = r0.mListDirty
                if (r1 == 0) goto L_0x0067
                java.util.ArrayList<androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallback> r1 = r0.mAnimationCallbacks
                int r1 = r1.size()
            L_0x0053:
                int r1 = r1 + (-1)
                if (r1 < 0) goto L_0x0065
                java.util.ArrayList<androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallback> r2 = r0.mAnimationCallbacks
                java.lang.Object r2 = r2.get(r1)
                if (r2 != 0) goto L_0x0053
                java.util.ArrayList<androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallback> r2 = r0.mAnimationCallbacks
                r2.remove(r1)
                goto L_0x0053
            L_0x0065:
                r0.mListDirty = r5
            L_0x0067:
                androidx.dynamicanimation.animation.AnimationHandler r0 = androidx.dynamicanimation.animation.AnimationHandler.this
                java.util.ArrayList<androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallback> r0 = r0.mAnimationCallbacks
                int r0 = r0.size()
                if (r0 <= 0) goto L_0x008b
                androidx.dynamicanimation.animation.AnimationHandler r0 = androidx.dynamicanimation.animation.AnimationHandler.this
                androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallbackProvider r1 = r0.mProvider
                if (r1 != 0) goto L_0x0080
                androidx.dynamicanimation.animation.AnimationHandler$FrameCallbackProvider16 r1 = new androidx.dynamicanimation.animation.AnimationHandler$FrameCallbackProvider16
                androidx.dynamicanimation.animation.AnimationHandler$AnimationCallbackDispatcher r2 = r0.mCallbackDispatcher
                r1.<init>(r2)
                r0.mProvider = r1
            L_0x0080:
                androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallbackProvider r0 = r0.mProvider
                androidx.dynamicanimation.animation.AnimationHandler$FrameCallbackProvider16 r0 = (androidx.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider16) r0
                android.view.Choreographer r1 = r0.mChoreographer
                android.view.Choreographer$FrameCallback r0 = r0.mChoreographerCallback
                r1.postFrameCallback(r0)
            L_0x008b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.dynamicanimation.animation.AnimationHandler.AnimationCallbackDispatcher.dispatchAnimationFrame():void");
        }
    }

    /* loaded from: classes.dex */
    public interface AnimationFrameCallback {
        boolean doAnimationFrame(long j);
    }

    /* loaded from: classes.dex */
    public static abstract class AnimationFrameCallbackProvider {
        public final AnimationCallbackDispatcher mDispatcher;

        public AnimationFrameCallbackProvider(AnimationCallbackDispatcher animationCallbackDispatcher) {
            this.mDispatcher = animationCallbackDispatcher;
        }
    }

    /* loaded from: classes.dex */
    public static class FrameCallbackProvider16 extends AnimationFrameCallbackProvider {
        public final Choreographer mChoreographer = Choreographer.getInstance();
        public final Choreographer.FrameCallback mChoreographerCallback = new Choreographer.FrameCallback() { // from class: androidx.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider16.1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                FrameCallbackProvider16.this.mDispatcher.dispatchAnimationFrame();
            }
        };

        public FrameCallbackProvider16(AnimationCallbackDispatcher animationCallbackDispatcher) {
            super(animationCallbackDispatcher);
        }
    }

    public static AnimationHandler getInstance() {
        ThreadLocal<AnimationHandler> threadLocal = sAnimatorHandler;
        if (threadLocal.get() == null) {
            threadLocal.set(new AnimationHandler());
        }
        return threadLocal.get();
    }
}
