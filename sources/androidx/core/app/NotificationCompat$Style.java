package androidx.core.app;

import android.os.Bundle;
/* loaded from: classes.dex */
public abstract class NotificationCompat$Style {
    public NotificationCompat$Builder mBuilder;

    public abstract void addCompatExtras(Bundle bundle);

    public abstract void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor);
}
