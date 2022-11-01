package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.R$style;
import com.facebook.react.modules.dialog.DialogModule;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.Arrays;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public final class ConnectionResult extends AbstractSafeParcelable {
    public final int zzg;
    public final int zzh;
    public final PendingIntent zzi;
    public final String zzj;
    public static final ConnectionResult RESULT_SUCCESS = new ConnectionResult(0);
    public static final Parcelable.Creator<ConnectionResult> CREATOR = new zza();

    public ConnectionResult(int i) {
        this.zzg = 1;
        this.zzh = i;
        this.zzi = null;
        this.zzj = null;
    }

    public static String zza(int i) {
        if (i == 99) {
            return "UNFINISHED";
        }
        if (i == 1500) {
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        switch (i) {
            case -1:
                return "UNKNOWN";
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            default:
                switch (i) {
                    case 13:
                        return "CANCELED";
                    case 14:
                        return "TIMEOUT";
                    case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                        return "INTERRUPTED";
                    case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                        return "API_UNAVAILABLE";
                    case 17:
                        return "SIGN_IN_FAILED";
                    case 18:
                        return "SERVICE_UPDATING";
                    case 19:
                        return "SERVICE_MISSING_PERMISSION";
                    case 20:
                        return "RESTRICTED_PROFILE";
                    case 21:
                        return "API_VERSION_UPDATE_REQUIRED";
                    default:
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("UNKNOWN_ERROR_CODE(");
                        sb.append(i);
                        sb.append(")");
                        return sb.toString();
                }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionResult)) {
            return false;
        }
        ConnectionResult connectionResult = (ConnectionResult) obj;
        return this.zzh == connectionResult.zzh && R$style.equal(this.zzi, connectionResult.zzi) && R$style.equal(this.zzj, connectionResult.zzj);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzh), this.zzi, this.zzj});
    }

    public final String toString() {
        Objects$ToStringHelper objects$ToStringHelper = new Objects$ToStringHelper(this, null);
        objects$ToStringHelper.add("statusCode", zza(this.zzh));
        objects$ToStringHelper.add("resolution", this.zzi);
        objects$ToStringHelper.add(DialogModule.KEY_MESSAGE, this.zzj);
        return objects$ToStringHelper.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int zza = R$style.zza(parcel, 20293);
        int i2 = this.zzg;
        R$style.zzb(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.zzh;
        R$style.zzb(parcel, 2, 4);
        parcel.writeInt(i3);
        R$style.writeParcelable(parcel, 3, this.zzi, i, false);
        R$style.writeString(parcel, 4, this.zzj, false);
        R$style.zzb(parcel, zza);
    }

    public ConnectionResult(int i, int i2, PendingIntent pendingIntent, String str) {
        this.zzg = i;
        this.zzh = i2;
        this.zzi = pendingIntent;
        this.zzj = str;
    }

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this.zzg = 1;
        this.zzh = i;
        this.zzi = pendingIntent;
        this.zzj = null;
    }
}
