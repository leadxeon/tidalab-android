package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.R$style;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.Arrays;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public final class Status extends AbstractSafeParcelable implements ReflectedParcelable {
    public final int zzg;
    public final int zzh;
    public final PendingIntent zzi;
    public final String zzj;
    public static final Status RESULT_SUCCESS = new Status(0, null);
    public static final Status RESULT_TIMEOUT = new Status(15, null);
    public static final Status RESULT_CANCELED = new Status(16, null);
    public static final Parcelable.Creator<Status> CREATOR = new zzb();

    static {
        new Status(14, null);
        new Status(8, null);
        new Status(17, null);
        new Status(18, null);
    }

    public Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.zzg = i;
        this.zzh = i2;
        this.zzj = str;
        this.zzi = pendingIntent;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.zzg == status.zzg && this.zzh == status.zzh && R$style.equal(this.zzj, status.zzj) && R$style.equal(this.zzi, status.zzi);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzg), Integer.valueOf(this.zzh), this.zzj, this.zzi});
    }

    public final String toString() {
        Objects$ToStringHelper objects$ToStringHelper = new Objects$ToStringHelper(this, null);
        String str = this.zzj;
        if (str == null) {
            int i = this.zzh;
            switch (i) {
                case -1:
                    str = "SUCCESS_CACHE";
                    break;
                case 0:
                    str = "SUCCESS";
                    break;
                case 1:
                case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                case 11:
                case 12:
                default:
                    StringBuilder sb = new StringBuilder(32);
                    sb.append("unknown status code: ");
                    sb.append(i);
                    str = sb.toString();
                    break;
                case 2:
                    str = "SERVICE_VERSION_UPDATE_REQUIRED";
                    break;
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    str = "SERVICE_DISABLED";
                    break;
                case 4:
                    str = "SIGN_IN_REQUIRED";
                    break;
                case 5:
                    str = "INVALID_ACCOUNT";
                    break;
                case 6:
                    str = "RESOLUTION_REQUIRED";
                    break;
                case 7:
                    str = "NETWORK_ERROR";
                    break;
                case 8:
                    str = "INTERNAL_ERROR";
                    break;
                case 10:
                    str = "DEVELOPER_ERROR";
                    break;
                case 13:
                    str = "ERROR";
                    break;
                case 14:
                    str = "INTERRUPTED";
                    break;
                case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                    str = "TIMEOUT";
                    break;
                case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                    str = "CANCELED";
                    break;
                case 17:
                    str = "API_NOT_CONNECTED";
                    break;
                case 18:
                    str = "DEAD_CLIENT";
                    break;
            }
        }
        objects$ToStringHelper.add("statusCode", str);
        objects$ToStringHelper.add("resolution", this.zzi);
        return objects$ToStringHelper.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int zza = R$style.zza(parcel, 20293);
        int i2 = this.zzh;
        R$style.zzb(parcel, 1, 4);
        parcel.writeInt(i2);
        R$style.writeString(parcel, 2, this.zzj, false);
        R$style.writeParcelable(parcel, 3, this.zzi, i, false);
        int i3 = this.zzg;
        R$style.zzb(parcel, RNCWebViewManager.COMMAND_CLEAR_FORM_DATA, 4);
        parcel.writeInt(i3);
        R$style.zzb(parcel, zza);
    }

    public Status(int i, String str) {
        this.zzg = 1;
        this.zzh = i;
        this.zzj = str;
        this.zzi = null;
    }
}
