package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcelable;
import androidx.versionedparcelable.VersionedParcel;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(VersionedParcel versionedParcel) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.mType = versionedParcel.readInt(iconCompat.mType, 1);
        byte[] bArr = iconCompat.mData;
        if (versionedParcel.readField(2)) {
            bArr = versionedParcel.readByteArray();
        }
        iconCompat.mData = bArr;
        iconCompat.mParcelable = versionedParcel.readParcelable(iconCompat.mParcelable, 3);
        iconCompat.mInt1 = versionedParcel.readInt(iconCompat.mInt1, 4);
        iconCompat.mInt2 = versionedParcel.readInt(iconCompat.mInt2, 5);
        iconCompat.mTintList = (ColorStateList) versionedParcel.readParcelable(iconCompat.mTintList, 6);
        String str = iconCompat.mTintModeStr;
        if (versionedParcel.readField(7)) {
            str = versionedParcel.readString();
        }
        iconCompat.mTintModeStr = str;
        String str2 = iconCompat.mString1;
        if (versionedParcel.readField(8)) {
            str2 = versionedParcel.readString();
        }
        iconCompat.mString1 = str2;
        iconCompat.mTintMode = PorterDuff.Mode.valueOf(iconCompat.mTintModeStr);
        switch (iconCompat.mType) {
            case -1:
                Parcelable parcelable = iconCompat.mParcelable;
                if (parcelable != null) {
                    iconCompat.mObj1 = parcelable;
                    break;
                } else {
                    throw new IllegalArgumentException("Invalid icon");
                }
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.mParcelable;
                if (parcelable2 == null) {
                    byte[] bArr2 = iconCompat.mData;
                    iconCompat.mObj1 = bArr2;
                    iconCompat.mType = 3;
                    iconCompat.mInt1 = 0;
                    iconCompat.mInt2 = bArr2.length;
                    break;
                } else {
                    iconCompat.mObj1 = parcelable2;
                    break;
                }
            case 2:
            case 4:
            case 6:
                String str3 = new String(iconCompat.mData, Charset.forName("UTF-16"));
                iconCompat.mObj1 = str3;
                if (iconCompat.mType == 2 && iconCompat.mString1 == null) {
                    iconCompat.mString1 = str3.split(":", -1)[0];
                    break;
                }
                break;
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                iconCompat.mObj1 = iconCompat.mData;
                break;
        }
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, VersionedParcel versionedParcel) {
        Objects.requireNonNull(versionedParcel);
        iconCompat.mTintModeStr = iconCompat.mTintMode.name();
        switch (iconCompat.mType) {
            case -1:
                iconCompat.mParcelable = (Parcelable) iconCompat.mObj1;
                break;
            case 1:
            case 5:
                iconCompat.mParcelable = (Parcelable) iconCompat.mObj1;
                break;
            case 2:
                iconCompat.mData = ((String) iconCompat.mObj1).getBytes(Charset.forName("UTF-16"));
                break;
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                iconCompat.mData = (byte[]) iconCompat.mObj1;
                break;
            case 4:
            case 6:
                iconCompat.mData = iconCompat.mObj1.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i = iconCompat.mType;
        if (-1 != i) {
            versionedParcel.setOutputField(1);
            versionedParcel.writeInt(i);
        }
        byte[] bArr = iconCompat.mData;
        if (bArr != null) {
            versionedParcel.setOutputField(2);
            versionedParcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.mParcelable;
        if (parcelable != null) {
            versionedParcel.setOutputField(3);
            versionedParcel.writeParcelable(parcelable);
        }
        int i2 = iconCompat.mInt1;
        if (i2 != 0) {
            versionedParcel.setOutputField(4);
            versionedParcel.writeInt(i2);
        }
        int i3 = iconCompat.mInt2;
        if (i3 != 0) {
            versionedParcel.setOutputField(5);
            versionedParcel.writeInt(i3);
        }
        ColorStateList colorStateList = iconCompat.mTintList;
        if (colorStateList != null) {
            versionedParcel.setOutputField(6);
            versionedParcel.writeParcelable(colorStateList);
        }
        String str = iconCompat.mTintModeStr;
        if (str != null) {
            versionedParcel.setOutputField(7);
            versionedParcel.writeString(str);
        }
        String str2 = iconCompat.mString1;
        if (str2 != null) {
            versionedParcel.setOutputField(8);
            versionedParcel.writeString(str2);
        }
    }
}
