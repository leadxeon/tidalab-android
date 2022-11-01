package androidx.core.app;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashSet;
/* loaded from: classes.dex */
public final class NotificationManagerCompat {
    public final Context mContext;
    public final NotificationManager mNotificationManager;

    static {
        new HashSet();
    }

    public NotificationManagerCompat(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
    }
}
