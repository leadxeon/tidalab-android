package com.facebook.imagepipeline.core;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class PriorityThreadFactory implements ThreadFactory {
    public final boolean mAddThreadNumber;
    public final String mPrefix;
    public final AtomicInteger mThreadNumber = new AtomicInteger(1);
    public final int mThreadPriority;

    public PriorityThreadFactory(int i, String str, boolean z) {
        this.mThreadPriority = i;
        this.mPrefix = str;
        this.mAddThreadNumber = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(final Runnable runnable) {
        String str;
        Runnable runnable2 = new Runnable() { // from class: com.facebook.imagepipeline.core.PriorityThreadFactory.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Process.setThreadPriority(PriorityThreadFactory.this.mThreadPriority);
                } catch (Throwable unused) {
                }
                runnable.run();
            }
        };
        if (this.mAddThreadNumber) {
            str = this.mPrefix + "-" + this.mThreadNumber.getAndIncrement();
        } else {
            str = this.mPrefix;
        }
        return new Thread(runnable2, str);
    }
}
