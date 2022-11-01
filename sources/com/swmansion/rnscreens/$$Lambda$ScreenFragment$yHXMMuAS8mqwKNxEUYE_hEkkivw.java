package com.swmansion.rnscreens;

import com.swmansion.rnscreens.ScreenFragment;
/* compiled from: lambda */
/* renamed from: com.swmansion.rnscreens.-$$Lambda$ScreenFragment$yHXMMuAS8mqwKNxEUYE_hEkkivw  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ScreenFragment$yHXMMuAS8mqwKNxEUYE_hEkkivw implements Runnable {
    public final /* synthetic */ ScreenFragment f$0;

    public /* synthetic */ $$Lambda$ScreenFragment$yHXMMuAS8mqwKNxEUYE_hEkkivw(ScreenFragment screenFragment) {
        this.f$0 = screenFragment;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ScreenFragment screenFragment = this.f$0;
        int i = ScreenFragment.$r8$clinit;
        screenFragment.dispatchEvent(ScreenFragment.ScreenLifecycleEvent.WillAppear, screenFragment);
    }
}
