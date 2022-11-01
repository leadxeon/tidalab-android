package com.google.android.gms.common.internal;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
/* loaded from: classes.dex */
public abstract class DialogRedirect implements DialogInterface.OnClickListener {
    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            zac zacVar = (zac) this;
            Intent intent = zacVar.zaoh;
            if (intent != null) {
                zacVar.val$activity.startActivityForResult(intent, zacVar.val$requestCode);
            }
        } catch (ActivityNotFoundException e) {
            Log.e("DialogRedirect", "Failed to start resolution intent", e);
        } finally {
            dialogInterface.dismiss();
        }
    }
}
