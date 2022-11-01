package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public class NotificationCompat$Builder {
    public String mChannelId;
    public PendingIntent mContentIntent;
    public CharSequence mContentText;
    public CharSequence mContentTitle;
    public Context mContext;
    public Bundle mExtras;
    public String mGroupKey;
    public Notification mNotification;
    public NotificationCompat$Style mStyle;
    public CharSequence mSubText;
    public ArrayList<NotificationCompat$Action> mActions = new ArrayList<>();
    public ArrayList<Person> mPersonList = new ArrayList<>();
    public ArrayList<NotificationCompat$Action> mInvisibleActions = new ArrayList<>();
    public boolean mShowWhen = true;
    public boolean mLocalOnly = false;
    public int mColor = 0;
    public int mPriority = 0;
    @Deprecated
    public ArrayList<String> mPeople = new ArrayList<>();
    public boolean mAllowSystemGeneratedContextualActions = true;

    public NotificationCompat$Builder(Context context, String str) {
        Notification notification = new Notification();
        this.mNotification = notification;
        this.mContext = context;
        this.mChannelId = str;
        notification.when = System.currentTimeMillis();
        this.mNotification.audioStreamType = -1;
    }

    public static CharSequence limitCharSequenceLength(CharSequence charSequence) {
        return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
    }

    public Notification build() {
        Notification notification;
        Bundle bundle;
        NotificationCompatBuilder notificationCompatBuilder = new NotificationCompatBuilder(this);
        NotificationCompat$Style notificationCompat$Style = notificationCompatBuilder.mBuilderCompat.mStyle;
        if (notificationCompat$Style != null) {
            notificationCompat$Style.apply(notificationCompatBuilder);
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            notification = notificationCompatBuilder.mBuilder.build();
        } else if (i >= 24) {
            notification = notificationCompatBuilder.mBuilder.build();
        } else {
            notificationCompatBuilder.mBuilder.setExtras(notificationCompatBuilder.mExtras);
            notification = notificationCompatBuilder.mBuilder.build();
        }
        Objects.requireNonNull(notificationCompatBuilder.mBuilderCompat);
        if (notificationCompat$Style != null) {
            Objects.requireNonNull(notificationCompatBuilder.mBuilderCompat.mStyle);
        }
        if (!(notificationCompat$Style == null || (bundle = notification.extras) == null)) {
            notificationCompat$Style.addCompatExtras(bundle);
        }
        return notification;
    }

    public NotificationCompat$Builder setContentText(CharSequence charSequence) {
        this.mContentText = limitCharSequenceLength(charSequence);
        return this;
    }

    public NotificationCompat$Builder setContentTitle(CharSequence charSequence) {
        this.mContentTitle = limitCharSequenceLength(charSequence);
        return this;
    }

    public final void setFlag(int i, boolean z) {
        if (z) {
            Notification notification = this.mNotification;
            notification.flags = i | notification.flags;
            return;
        }
        Notification notification2 = this.mNotification;
        notification2.flags = (~i) & notification2.flags;
    }

    public NotificationCompat$Builder setStyle(NotificationCompat$Style notificationCompat$Style) {
        if (this.mStyle != notificationCompat$Style) {
            this.mStyle = notificationCompat$Style;
            if (notificationCompat$Style.mBuilder != this) {
                notificationCompat$Style.mBuilder = this;
                setStyle(notificationCompat$Style);
            }
        }
        return this;
    }
}
