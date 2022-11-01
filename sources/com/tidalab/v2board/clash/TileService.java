package com.tidalab.v2board.clash;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.util.Log;
import com.tidalab.v2board.clash.common.constants.Authorities;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.common.constants.Permissions;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
/* compiled from: TileService.kt */
/* loaded from: classes.dex */
public final class TileService extends android.service.quicksettings.TileService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean clashRunning;
    public String currentProfile = HttpUrl.FRAGMENT_ENCODE_SET;
    public final TileService$receiver$1 receiver = new BroadcastReceiver() { // from class: com.tidalab.v2board.clash.TileService$receiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str = null;
            String action = intent == null ? null : intent.getAction();
            Intents intents = Intents.INSTANCE;
            boolean areEqual = Intrinsics.areEqual(action, Intents.ACTION_CLASH_STARTED);
            boolean z = true;
            String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
            if (areEqual) {
                TileService tileService = TileService.this;
                tileService.clashRunning = true;
                tileService.currentProfile = str2;
            } else {
                if (!Intrinsics.areEqual(action, Intents.ACTION_CLASH_STOPPED)) {
                    z = Intrinsics.areEqual(action, Intents.ACTION_SERVICE_RECREATED);
                }
                if (z) {
                    TileService tileService2 = TileService.this;
                    tileService2.clashRunning = false;
                    tileService2.currentProfile = str2;
                } else if (Intrinsics.areEqual(action, Intents.ACTION_PROFILE_LOADED)) {
                    TileService tileService3 = TileService.this;
                    try {
                        ContentResolver contentResolver = tileService3.getContentResolver();
                        Uri.Builder scheme = new Uri.Builder().scheme("content");
                        Authorities authorities = Authorities.INSTANCE;
                        Bundle call = contentResolver.call(scheme.authority(Authorities.STATUS_PROVIDER).build(), "currentProfile", (String) null, (Bundle) null);
                        if (call != null) {
                            str = call.getString("name");
                        }
                    } catch (Exception e) {
                        Log.w("ClashForAndroid", Intrinsics.stringPlus("Query current profile: ", e), e);
                    }
                    if (str != null) {
                        str2 = str;
                    }
                    tileService3.currentProfile = str2;
                }
            }
            TileService tileService4 = TileService.this;
            int i = TileService.$r8$clinit;
            tileService4.updateTile();
        }
    };

    @Override // android.service.quicksettings.TileService
    public void onClick() {
        Tile qsTile = getQsTile();
        if (qsTile != null) {
            int state = qsTile.getState();
            if (state == 1) {
                InputKt.startClashService(this);
            } else if (state == 2) {
                InputKt.stopClashService(this);
            }
        }
    }

    @Override // android.service.quicksettings.TileService
    public void onStartListening() {
        super.onStartListening();
        TileService$receiver$1 tileService$receiver$1 = this.receiver;
        IntentFilter intentFilter = new IntentFilter();
        Intents intents = Intents.INSTANCE;
        intentFilter.addAction(Intents.ACTION_CLASH_STARTED);
        intentFilter.addAction(Intents.ACTION_CLASH_STOPPED);
        intentFilter.addAction(Intents.ACTION_PROFILE_LOADED);
        intentFilter.addAction(Intents.ACTION_SERVICE_RECREATED);
        Unit unit = Unit.INSTANCE;
        Permissions permissions = Permissions.INSTANCE;
        String str = null;
        registerReceiver(tileService$receiver$1, intentFilter, Permissions.RECEIVE_SELF_BROADCASTS, null);
        try {
            ContentResolver contentResolver = getContentResolver();
            Uri.Builder scheme = new Uri.Builder().scheme("content");
            Authorities authorities = Authorities.INSTANCE;
            Bundle call = contentResolver.call(scheme.authority(Authorities.STATUS_PROVIDER).build(), "currentProfile", (String) null, (Bundle) null);
            if (call != null) {
                str = call.getString("name");
            }
        } catch (Exception e) {
            Log.w("ClashForAndroid", Intrinsics.stringPlus("Query current profile: ", e), e);
        }
        this.clashRunning = str != null;
        if (str == null) {
            str = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        this.currentProfile = str;
        updateTile();
    }

    @Override // android.service.quicksettings.TileService
    public void onStopListening() {
        super.onStopListening();
        unregisterReceiver(this.receiver);
    }

    public final void updateTile() {
        CharSequence charSequence;
        Tile qsTile = getQsTile();
        if (qsTile != null) {
            boolean z = true;
            qsTile.setState(this.clashRunning ? 2 : 1);
            if (this.currentProfile.length() != 0) {
                z = false;
            }
            if (z) {
                charSequence = getText(R.string.launch_name);
            } else {
                charSequence = this.currentProfile;
            }
            qsTile.setLabel(charSequence);
            qsTile.setIcon(Icon.createWithResource(this, (int) R.drawable.ic_logo_service));
            qsTile.updateTile();
        }
    }
}
