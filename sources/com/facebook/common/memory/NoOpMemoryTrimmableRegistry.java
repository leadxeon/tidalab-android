package com.facebook.common.memory;
/* loaded from: classes.dex */
public class NoOpMemoryTrimmableRegistry implements MemoryTrimmableRegistry {
    public static NoOpMemoryTrimmableRegistry sInstance;

    public static synchronized NoOpMemoryTrimmableRegistry getInstance() {
        NoOpMemoryTrimmableRegistry noOpMemoryTrimmableRegistry;
        synchronized (NoOpMemoryTrimmableRegistry.class) {
            if (sInstance == null) {
                sInstance = new NoOpMemoryTrimmableRegistry();
            }
            noOpMemoryTrimmableRegistry = sInstance;
        }
        return noOpMemoryTrimmableRegistry;
    }

    @Override // com.facebook.common.memory.MemoryTrimmableRegistry
    public void registerMemoryTrimmable(MemoryTrimmable memoryTrimmable) {
    }
}
