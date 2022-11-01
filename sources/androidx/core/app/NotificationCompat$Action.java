package androidx.core.app;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class NotificationCompat$Action {
    public PendingIntent actionIntent;
    @Deprecated
    public int icon;
    public boolean mAllowGeneratedReplies;
    public final RemoteInput[] mDataOnlyRemoteInputs;
    public final Bundle mExtras;
    public IconCompat mIcon;
    public final boolean mIsContextual;
    public final RemoteInput[] mRemoteInputs;
    public final int mSemanticAction;
    public boolean mShowsUserInterface;
    public CharSequence title;

    /* JADX WARN: Removed duplicated region for block: B:25:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NotificationCompat$Action(int r12, java.lang.CharSequence r13, android.app.PendingIntent r14) {
        /*
            r11 = this;
            r0 = 0
            if (r12 != 0) goto L_0x0005
            r12 = r0
            goto L_0x000b
        L_0x0005:
            java.lang.String r1 = ""
            androidx.core.graphics.drawable.IconCompat r12 = androidx.core.graphics.drawable.IconCompat.createWithResource(r0, r1, r12)
        L_0x000b:
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r11.<init>()
            r2 = 1
            r11.mShowsUserInterface = r2
            r11.mIcon = r12
            r3 = 0
            if (r12 == 0) goto L_0x0095
            int r4 = r12.mType
            r5 = -1
            if (r4 != r5) goto L_0x008c
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 23
            if (r6 < r7) goto L_0x008c
            java.lang.Object r4 = r12.mObj1
            android.graphics.drawable.Icon r4 = (android.graphics.drawable.Icon) r4
            java.lang.String r7 = "Unable to get icon type "
            java.lang.String r8 = "IconCompat"
            r9 = 28
            if (r6 < r9) goto L_0x0037
            int r4 = r4.getType()
            goto L_0x008c
        L_0x0037:
            java.lang.Class r6 = r4.getClass()     // Catch: NoSuchMethodException -> 0x0050, InvocationTargetException -> 0x0064, IllegalAccessException -> 0x0078
            java.lang.String r9 = "getType"
            java.lang.Class[] r10 = new java.lang.Class[r3]     // Catch: NoSuchMethodException -> 0x0050, InvocationTargetException -> 0x0064, IllegalAccessException -> 0x0078
            java.lang.reflect.Method r6 = r6.getMethod(r9, r10)     // Catch: NoSuchMethodException -> 0x0050, InvocationTargetException -> 0x0064, IllegalAccessException -> 0x0078
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch: NoSuchMethodException -> 0x0050, InvocationTargetException -> 0x0064, IllegalAccessException -> 0x0078
            java.lang.Object r6 = r6.invoke(r4, r9)     // Catch: NoSuchMethodException -> 0x0050, InvocationTargetException -> 0x0064, IllegalAccessException -> 0x0078
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch: NoSuchMethodException -> 0x0050, InvocationTargetException -> 0x0064, IllegalAccessException -> 0x0078
            int r4 = r6.intValue()     // Catch: NoSuchMethodException -> 0x0050, InvocationTargetException -> 0x0064, IllegalAccessException -> 0x0078
            goto L_0x008c
        L_0x0050:
            r6 = move-exception
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r7)
            r9.append(r4)
            java.lang.String r4 = r9.toString()
            android.util.Log.e(r8, r4, r6)
            goto L_0x008b
        L_0x0064:
            r6 = move-exception
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r7)
            r9.append(r4)
            java.lang.String r4 = r9.toString()
            android.util.Log.e(r8, r4, r6)
            goto L_0x008b
        L_0x0078:
            r6 = move-exception
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r7)
            r9.append(r4)
            java.lang.String r4 = r9.toString()
            android.util.Log.e(r8, r4, r6)
        L_0x008b:
            r4 = -1
        L_0x008c:
            r5 = 2
            if (r4 != r5) goto L_0x0095
            int r12 = r12.getResId()
            r11.icon = r12
        L_0x0095:
            java.lang.CharSequence r12 = androidx.core.app.NotificationCompat$Builder.limitCharSequenceLength(r13)
            r11.title = r12
            r11.actionIntent = r14
            r11.mExtras = r1
            r11.mRemoteInputs = r0
            r11.mDataOnlyRemoteInputs = r0
            r11.mAllowGeneratedReplies = r2
            r11.mSemanticAction = r3
            r11.mShowsUserInterface = r2
            r11.mIsContextual = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat$Action.<init>(int, java.lang.CharSequence, android.app.PendingIntent):void");
    }

    public IconCompat getIconCompat() {
        int i;
        if (this.mIcon == null && (i = this.icon) != 0) {
            this.mIcon = IconCompat.createWithResource(null, HttpUrl.FRAGMENT_ENCODE_SET, i);
        }
        return this.mIcon;
    }
}
