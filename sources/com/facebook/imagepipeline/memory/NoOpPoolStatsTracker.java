package com.facebook.imagepipeline.memory;
/* loaded from: classes.dex */
public class NoOpPoolStatsTracker implements PoolStatsTracker {
    public static NoOpPoolStatsTracker sInstance;

    public static synchronized NoOpPoolStatsTracker getInstance() {
        NoOpPoolStatsTracker noOpPoolStatsTracker;
        synchronized (NoOpPoolStatsTracker.class) {
            if (sInstance == null) {
                sInstance = new NoOpPoolStatsTracker();
            }
            noOpPoolStatsTracker = sInstance;
        }
        return noOpPoolStatsTracker;
    }

    @Override // com.facebook.imagepipeline.memory.PoolStatsTracker
    public void onAlloc(int i) {
    }

    @Override // com.facebook.imagepipeline.memory.PoolStatsTracker
    public void onFree(int i) {
    }

    @Override // com.facebook.imagepipeline.memory.PoolStatsTracker
    public void onHardCapReached() {
    }

    @Override // com.facebook.imagepipeline.memory.PoolStatsTracker
    public void onSoftCapReached() {
    }

    @Override // com.facebook.imagepipeline.memory.PoolStatsTracker
    public void onValueRelease(int i) {
    }

    @Override // com.facebook.imagepipeline.memory.PoolStatsTracker
    public void onValueReuse(int i) {
    }

    @Override // com.facebook.imagepipeline.memory.PoolStatsTracker
    public void setBasePool(BasePool basePool) {
    }
}
