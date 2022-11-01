package com.facebook.react.jstasks;
/* loaded from: classes.dex */
public interface HeadlessJsTaskRetryPolicy {
    boolean canRetry();

    HeadlessJsTaskRetryPolicy copy();

    int getDelay();

    HeadlessJsTaskRetryPolicy update();
}
