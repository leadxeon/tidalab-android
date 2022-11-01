package com.tidalab.v2board.clash.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelDecoder;
import com.tidalab.v2board.clash.core.util.Parcelizer$ParcelEncoder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
/* compiled from: LogMessage.kt */
@Serializable
/* loaded from: classes.dex */
public final class LogMessage implements Parcelable {
    public static final Parcelable.Creator<LogMessage> CREATOR = new Parcelable.Creator<LogMessage>() { // from class: com.tidalab.v2board.clash.core.model.LogMessage$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public LogMessage createFromParcel(Parcel parcel) {
            return (LogMessage) LogMessage$$serializer.INSTANCE.deserialize(new Parcelizer$ParcelDecoder(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public LogMessage[] newArray(int i) {
            return new LogMessage[i];
        }
    };
    public final Level level;
    public final String message;
    public final Date time;

    /* compiled from: LogMessage.kt */
    @Serializable
    /* loaded from: classes.dex */
    public enum Level {
        Debug,
        Info,
        Warning,
        Error,
        Silent,
        Unknown
    }

    public LogMessage(int i, Level level, String str, Date date) {
        if (7 != (i & 7)) {
            LogMessage$$serializer logMessage$$serializer = LogMessage$$serializer.INSTANCE;
            InputKt.throwMissingFieldException(i, 7, LogMessage$$serializer.descriptor);
        }
        this.level = level;
        this.message = str;
        this.time = date;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogMessage)) {
            return false;
        }
        LogMessage logMessage = (LogMessage) obj;
        return this.level == logMessage.level && Intrinsics.areEqual(this.message, logMessage.message) && Intrinsics.areEqual(this.time, logMessage.time);
    }

    public int hashCode() {
        return this.time.hashCode() + GeneratedOutlineSupport.outline1(this.message, this.level.hashCode() * 31, 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("LogMessage(level=");
        outline13.append(this.level);
        outline13.append(", message=");
        outline13.append(this.message);
        outline13.append(", time=");
        outline13.append(this.time);
        outline13.append(')');
        return outline13.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        LogMessage$$serializer.INSTANCE.serialize(new Parcelizer$ParcelEncoder(parcel), this);
    }

    public LogMessage(Level level, String str, Date date) {
        this.level = level;
        this.message = str;
        this.time = date;
    }
}
