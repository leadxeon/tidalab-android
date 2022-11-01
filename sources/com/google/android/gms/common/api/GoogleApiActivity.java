package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.internal.GoogleApiManager;
@KeepName
/* loaded from: classes.dex */
public class GoogleApiActivity extends Activity implements DialogInterface.OnCancelListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int zabp = 0;

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        GoogleApiManager googleApiManager;
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            boolean booleanExtra = getIntent().getBooleanExtra("notify_manager", true);
            this.zabp = 0;
            setResult(i2, intent);
            if (booleanExtra) {
                synchronized (GoogleApiManager.lock) {
                    if (GoogleApiManager.zaic == null) {
                        HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                        handlerThread.start();
                        Looper looper = handlerThread.getLooper();
                        Context applicationContext = getApplicationContext();
                        Object obj = GoogleApiAvailability.mLock;
                        GoogleApiManager.zaic = new GoogleApiManager(applicationContext, looper, GoogleApiAvailability.zaao);
                    }
                    googleApiManager = GoogleApiManager.zaic;
                }
                if (i2 == -1) {
                    Handler handler = googleApiManager.handler;
                    handler.sendMessage(handler.obtainMessage(3));
                } else if (i2 == 0) {
                    ConnectionResult connectionResult = new ConnectionResult(13, null);
                    int intExtra = getIntent().getIntExtra("failing_client_id", -1);
                    if (!googleApiManager.zac(connectionResult, intExtra)) {
                        Handler handler2 = googleApiManager.handler;
                        handler2.sendMessage(handler2.obtainMessage(5, intExtra, 0, connectionResult));
                    }
                }
            }
        } else if (i == 2) {
            this.zabp = 0;
            setResult(i2, intent);
        }
        finish();
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.zabp = 0;
        setResult(0);
        finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zabp = bundle.getInt("resolution");
        }
        if (this.zabp != 1) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Log.e("GoogleApiActivity", "Activity started without extras");
                finish();
                return;
            }
            PendingIntent pendingIntent = (PendingIntent) extras.get("pending_intent");
            Integer num = (Integer) extras.get("error_code");
            if (pendingIntent == null && num == null) {
                Log.e("GoogleApiActivity", "Activity started without resolution");
                finish();
            } else if (pendingIntent != null) {
                try {
                    startIntentSenderForResult(pendingIntent.getIntentSender(), 1, null, 0, 0, 0);
                    this.zabp = 1;
                } catch (IntentSender.SendIntentException e) {
                    Log.e("GoogleApiActivity", "Failed to launch pendingIntent", e);
                    finish();
                }
            } else {
                Object obj = GoogleApiAvailability.mLock;
                GoogleApiAvailability.zaao.showErrorDialogFragment(this, num.intValue(), 2, this);
                this.zabp = 1;
            }
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("resolution", this.zabp);
        super.onSaveInstanceState(bundle);
    }
}
