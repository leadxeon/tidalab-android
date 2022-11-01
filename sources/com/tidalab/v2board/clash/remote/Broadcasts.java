package com.tidalab.v2board.clash.remote;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.remote.Broadcasts;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Broadcasts.kt */
/* loaded from: classes.dex */
public final class Broadcasts {
    public boolean clashRunning;
    public final Application context;
    public final List<Observer> receivers = new ArrayList();
    public final Broadcasts$broadcastReceiver$1 broadcastReceiver = new BroadcastReceiver() { // from class: com.tidalab.v2board.clash.remote.Broadcasts$broadcastReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str = null;
            if (Intrinsics.areEqual(intent == null ? null : intent.getPackage(), context == null ? null : context.getPackageName())) {
                if (intent != null) {
                    str = intent.getAction();
                }
                Intents intents = Intents.INSTANCE;
                if (Intrinsics.areEqual(str, Intents.ACTION_SERVICE_RECREATED)) {
                    Broadcasts broadcasts = Broadcasts.this;
                    broadcasts.clashRunning = false;
                    for (Broadcasts.Observer observer : broadcasts.receivers) {
                        observer.onServiceRecreated();
                    }
                } else if (Intrinsics.areEqual(str, Intents.ACTION_CLASH_STARTED)) {
                    Broadcasts broadcasts2 = Broadcasts.this;
                    broadcasts2.clashRunning = true;
                    for (Broadcasts.Observer observer2 : broadcasts2.receivers) {
                        observer2.onStarted();
                    }
                } else if (Intrinsics.areEqual(str, Intents.ACTION_CLASH_STOPPED)) {
                    Broadcasts broadcasts3 = Broadcasts.this;
                    broadcasts3.clashRunning = false;
                    for (Broadcasts.Observer observer3 : broadcasts3.receivers) {
                        observer3.onStopped(intent.getStringExtra("stop_reason"));
                    }
                } else if (Intrinsics.areEqual(str, Intents.ACTION_PROFILE_CHANGED)) {
                    for (Broadcasts.Observer observer4 : Broadcasts.this.receivers) {
                        observer4.onProfileChanged();
                    }
                } else if (Intrinsics.areEqual(str, Intents.ACTION_PROFILE_LOADED)) {
                    for (Broadcasts.Observer observer5 : Broadcasts.this.receivers) {
                        observer5.onProfileLoaded();
                    }
                }
            }
        }
    };

    /* compiled from: Broadcasts.kt */
    /* loaded from: classes.dex */
    public interface Observer {
        void onProfileChanged();

        void onProfileLoaded();

        void onServiceRecreated();

        void onStarted();

        void onStopped(String str);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.tidalab.v2board.clash.remote.Broadcasts$broadcastReceiver$1] */
    public Broadcasts(Application application) {
        this.context = application;
    }
}
