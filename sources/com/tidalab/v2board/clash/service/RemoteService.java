package com.tidalab.v2board.clash.service;

import android.content.Intent;
import android.os.IBinder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import com.tidalab.v2board.clash.service.remote.IClashManagerDelegate;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate;
import com.tidalab.v2board.clash.service.remote.IRemoteService;
import com.tidalab.v2board.clash.service.remote.IRemoteServiceDelegate;
/* compiled from: RemoteService.kt */
/* loaded from: classes.dex */
public final class RemoteService extends BaseService implements IRemoteService {
    public final IBinder binder;
    public ClashManager clash;
    public IClashManager clashBinder;
    public ProfileManager profile;
    public IProfileManager profileBinder;

    public RemoteService() {
        IBinder iBinder;
        if (this instanceof IBinder) {
            iBinder = (IBinder) this;
        } else {
            iBinder = new IRemoteServiceDelegate(this);
        }
        this.binder = iBinder;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IRemoteService
    public IClashManager clash() {
        return this.clashBinder;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Override // android.app.Service
    public void onCreate() {
        IBinder iBinder;
        super.onCreate();
        this.clash = new ClashManager(this);
        this.profile = new ProfileManager(this);
        ClashManager clashManager = this.clash;
        Object obj = null;
        if (clashManager == null) {
            iBinder = null;
        } else if (clashManager instanceof IBinder) {
            iBinder = (IBinder) clashManager;
        } else {
            iBinder = new IClashManagerDelegate(clashManager);
        }
        this.clashBinder = (IClashManager) iBinder;
        ProfileManager profileManager = this.profile;
        if (profileManager != null) {
            if (profileManager instanceof IBinder) {
                obj = (IBinder) profileManager;
            } else {
                obj = new IProfileManagerDelegate(profileManager);
            }
        }
        this.profileBinder = (IProfileManager) obj;
    }

    @Override // com.tidalab.v2board.clash.service.BaseService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ClashManager clashManager = this.clash;
        if (clashManager != null) {
            InputKt.cancelAndJoinBlocking(clashManager);
        }
        ProfileManager profileManager = this.profile;
        if (profileManager != null) {
            InputKt.cancelAndJoinBlocking(profileManager);
        }
    }

    @Override // com.tidalab.v2board.clash.service.remote.IRemoteService
    public IProfileManager profile() {
        return this.profileBinder;
    }
}
