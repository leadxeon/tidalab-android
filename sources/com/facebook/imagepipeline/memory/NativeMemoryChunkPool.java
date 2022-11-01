package com.facebook.imagepipeline.memory;

import com.facebook.common.memory.MemoryTrimmableRegistry;
/* loaded from: classes.dex */
public class NativeMemoryChunkPool extends MemoryChunkPool {
    public NativeMemoryChunkPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public MemoryChunk alloc(int i) {
        return new NativeMemoryChunk(i);
    }
}
