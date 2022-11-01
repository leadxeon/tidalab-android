package com.facebook.react.common;

import androidx.recyclerview.R$dimen;
/* loaded from: classes.dex */
public class SingleThreadAsserter {
    public Thread mThread = null;

    public void assertNow() {
        Thread currentThread = Thread.currentThread();
        if (this.mThread == null) {
            this.mThread = currentThread;
        }
        R$dimen.assertCondition(this.mThread == currentThread);
    }
}
