package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.Objects;
/* loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(VersionedParcel versionedParcel) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        VersionedParcelable versionedParcelable = remoteActionCompat.mIcon;
        if (versionedParcel.readField(1)) {
            versionedParcelable = versionedParcel.readVersionedParcelable();
        }
        remoteActionCompat.mIcon = (IconCompat) versionedParcelable;
        CharSequence charSequence = remoteActionCompat.mTitle;
        if (versionedParcel.readField(2)) {
            charSequence = versionedParcel.readCharSequence();
        }
        remoteActionCompat.mTitle = charSequence;
        CharSequence charSequence2 = remoteActionCompat.mContentDescription;
        if (versionedParcel.readField(3)) {
            charSequence2 = versionedParcel.readCharSequence();
        }
        remoteActionCompat.mContentDescription = charSequence2;
        remoteActionCompat.mActionIntent = (PendingIntent) versionedParcel.readParcelable(remoteActionCompat.mActionIntent, 4);
        boolean z = remoteActionCompat.mEnabled;
        if (versionedParcel.readField(5)) {
            z = versionedParcel.readBoolean();
        }
        remoteActionCompat.mEnabled = z;
        boolean z2 = remoteActionCompat.mShouldShowIcon;
        if (versionedParcel.readField(6)) {
            z2 = versionedParcel.readBoolean();
        }
        remoteActionCompat.mShouldShowIcon = z2;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, VersionedParcel versionedParcel) {
        Objects.requireNonNull(versionedParcel);
        IconCompat iconCompat = remoteActionCompat.mIcon;
        versionedParcel.setOutputField(1);
        versionedParcel.writeVersionedParcelable(iconCompat);
        CharSequence charSequence = remoteActionCompat.mTitle;
        versionedParcel.setOutputField(2);
        versionedParcel.writeCharSequence(charSequence);
        CharSequence charSequence2 = remoteActionCompat.mContentDescription;
        versionedParcel.setOutputField(3);
        versionedParcel.writeCharSequence(charSequence2);
        PendingIntent pendingIntent = remoteActionCompat.mActionIntent;
        versionedParcel.setOutputField(4);
        versionedParcel.writeParcelable(pendingIntent);
        boolean z = remoteActionCompat.mEnabled;
        versionedParcel.setOutputField(5);
        versionedParcel.writeBoolean(z);
        boolean z2 = remoteActionCompat.mShouldShowIcon;
        versionedParcel.setOutputField(6);
        versionedParcel.writeBoolean(z2);
    }
}
