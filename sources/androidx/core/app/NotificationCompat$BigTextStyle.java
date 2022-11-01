package androidx.core.app;

import android.app.Notification;
import android.os.Bundle;
/* loaded from: classes.dex */
public class NotificationCompat$BigTextStyle extends NotificationCompat$Style {
    public CharSequence mBigText;

    @Override // androidx.core.app.NotificationCompat$Style
    public void addCompatExtras(Bundle bundle) {
        bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", "androidx.core.app.NotificationCompat$BigTextStyle");
    }

    @Override // androidx.core.app.NotificationCompat$Style
    public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        new Notification.BigTextStyle(((NotificationCompatBuilder) notificationBuilderWithBuilderAccessor).mBuilder).setBigContentTitle(null).bigText(this.mBigText);
    }
}
