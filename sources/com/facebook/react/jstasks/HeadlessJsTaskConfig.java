package com.facebook.react.jstasks;

import com.facebook.react.bridge.WritableMap;
/* loaded from: classes.dex */
public class HeadlessJsTaskConfig {
    public final boolean mAllowedInForeground;
    public final WritableMap mData;
    public final HeadlessJsTaskRetryPolicy mRetryPolicy;
    public final String mTaskKey;
    public final long mTimeout;

    public HeadlessJsTaskConfig(String str, WritableMap writableMap, long j, boolean z, HeadlessJsTaskRetryPolicy headlessJsTaskRetryPolicy) {
        this.mTaskKey = str;
        this.mData = writableMap;
        this.mTimeout = j;
        this.mAllowedInForeground = z;
        this.mRetryPolicy = headlessJsTaskRetryPolicy;
    }

    public HeadlessJsTaskConfig(HeadlessJsTaskConfig headlessJsTaskConfig) {
        this.mTaskKey = headlessJsTaskConfig.mTaskKey;
        this.mData = headlessJsTaskConfig.mData.copy();
        this.mTimeout = headlessJsTaskConfig.mTimeout;
        this.mAllowedInForeground = headlessJsTaskConfig.mAllowedInForeground;
        HeadlessJsTaskRetryPolicy headlessJsTaskRetryPolicy = headlessJsTaskConfig.mRetryPolicy;
        if (headlessJsTaskRetryPolicy != null) {
            this.mRetryPolicy = headlessJsTaskRetryPolicy.copy();
        } else {
            this.mRetryPolicy = null;
        }
    }
}
