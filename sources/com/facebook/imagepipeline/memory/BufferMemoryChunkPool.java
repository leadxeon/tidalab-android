package com.facebook.imagepipeline.memory;

import com.facebook.common.memory.MemoryTrimmableRegistry;
/* loaded from: classes.dex */
public class BufferMemoryChunkPool extends MemoryChunkPool {
    public BufferMemoryChunkPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public MemoryChunk alloc(int i) {
        return new BufferMemoryChunk(i);
    }
}
