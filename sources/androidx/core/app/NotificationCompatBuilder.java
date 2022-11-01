package androidx.core.app;

import android.app.Notification;
import android.app.Person;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import androidx.core.graphics.drawable.IconCompat;
import com.facebook.react.modules.dialog.DialogModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    public final Notification.Builder mBuilder;
    public final NotificationCompat$Builder mBuilderCompat;
    public final Context mContext;
    public final List<Bundle> mActionExtrasList = new ArrayList();
    public final Bundle mExtras = new Bundle();

    public NotificationCompatBuilder(NotificationCompat$Builder notificationCompat$Builder) {
        List<String> list;
        Bundle bundle;
        Notification.Action.Builder builder;
        Bundle bundle2;
        this.mBuilderCompat = notificationCompat$Builder;
        this.mContext = notificationCompat$Builder.mContext;
        if (Build.VERSION.SDK_INT >= 26) {
            this.mBuilder = new Notification.Builder(notificationCompat$Builder.mContext, notificationCompat$Builder.mChannelId);
        } else {
            this.mBuilder = new Notification.Builder(notificationCompat$Builder.mContext);
        }
        Notification notification = notificationCompat$Builder.mNotification;
        this.mBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(notificationCompat$Builder.mContentTitle).setContentText(notificationCompat$Builder.mContentText).setContentInfo(null).setContentIntent(notificationCompat$Builder.mContentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) == 0 ? false : true).setLargeIcon((Bitmap) null).setNumber(0).setProgress(0, 0, false);
        this.mBuilder.setSubText(notificationCompat$Builder.mSubText).setUsesChronometer(false).setPriority(notificationCompat$Builder.mPriority);
        Iterator<NotificationCompat$Action> it = notificationCompat$Builder.mActions.iterator();
        while (it.hasNext()) {
            NotificationCompat$Action next = it.next();
            int i = Build.VERSION.SDK_INT;
            IconCompat iconCompat = next.getIconCompat();
            if (i >= 23) {
                builder = new Notification.Action.Builder(iconCompat != null ? iconCompat.toIcon() : null, next.title, next.actionIntent);
            } else {
                builder = new Notification.Action.Builder(iconCompat != null ? iconCompat.getResId() : 0, next.title, next.actionIntent);
            }
            RemoteInput[] remoteInputArr = next.mRemoteInputs;
            if (remoteInputArr != null) {
                int length = remoteInputArr.length;
                RemoteInput[] remoteInputArr2 = new RemoteInput[length];
                if (remoteInputArr.length <= 0) {
                    for (int i2 = 0; i2 < length; i2++) {
                        builder.addRemoteInput(remoteInputArr2[i2]);
                    }
                } else {
                    RemoteInput remoteInput = remoteInputArr[0];
                    throw null;
                }
            }
            if (next.mExtras != null) {
                bundle2 = new Bundle(next.mExtras);
            } else {
                bundle2 = new Bundle();
            }
            bundle2.putBoolean("android.support.allowGeneratedReplies", next.mAllowGeneratedReplies);
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 24) {
                builder.setAllowGeneratedReplies(next.mAllowGeneratedReplies);
            }
            bundle2.putInt("android.support.action.semanticAction", next.mSemanticAction);
            if (i3 >= 28) {
                builder.setSemanticAction(next.mSemanticAction);
            }
            if (i3 >= 29) {
                builder.setContextual(next.mIsContextual);
            }
            bundle2.putBoolean("android.support.action.showsUserInterface", next.mShowsUserInterface);
            builder.addExtras(bundle2);
            this.mBuilder.addAction(builder.build());
        }
        Bundle bundle3 = notificationCompat$Builder.mExtras;
        if (bundle3 != null) {
            this.mExtras.putAll(bundle3);
        }
        int i4 = Build.VERSION.SDK_INT;
        this.mBuilder.setShowWhen(notificationCompat$Builder.mShowWhen);
        this.mBuilder.setLocalOnly(notificationCompat$Builder.mLocalOnly).setGroup(notificationCompat$Builder.mGroupKey).setGroupSummary(false).setSortKey(null);
        this.mBuilder.setCategory(null).setColor(notificationCompat$Builder.mColor).setVisibility(0).setPublicVersion(null).setSound(notification.sound, notification.audioAttributes);
        if (i4 < 28) {
            list = combineLists(getPeople(notificationCompat$Builder.mPersonList), notificationCompat$Builder.mPeople);
        } else {
            list = notificationCompat$Builder.mPeople;
        }
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                this.mBuilder.addPerson(str);
            }
        }
        if (notificationCompat$Builder.mInvisibleActions.size() > 0) {
            if (notificationCompat$Builder.mExtras == null) {
                notificationCompat$Builder.mExtras = new Bundle();
            }
            Bundle bundle4 = notificationCompat$Builder.mExtras.getBundle("android.car.EXTENSIONS");
            bundle4 = bundle4 == null ? new Bundle() : bundle4;
            Bundle bundle5 = new Bundle(bundle4);
            Bundle bundle6 = new Bundle();
            for (int i5 = 0; i5 < notificationCompat$Builder.mInvisibleActions.size(); i5++) {
                String num = Integer.toString(i5);
                NotificationCompat$Action notificationCompat$Action = notificationCompat$Builder.mInvisibleActions.get(i5);
                Object obj = NotificationCompatJellybean.sExtrasLock;
                Bundle bundle7 = new Bundle();
                IconCompat iconCompat2 = notificationCompat$Action.getIconCompat();
                bundle7.putInt("icon", iconCompat2 != null ? iconCompat2.getResId() : 0);
                bundle7.putCharSequence(DialogModule.KEY_TITLE, notificationCompat$Action.title);
                bundle7.putParcelable("actionIntent", notificationCompat$Action.actionIntent);
                if (notificationCompat$Action.mExtras != null) {
                    bundle = new Bundle(notificationCompat$Action.mExtras);
                } else {
                    bundle = new Bundle();
                }
                bundle.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action.mAllowGeneratedReplies);
                bundle7.putBundle("extras", bundle);
                bundle7.putParcelableArray("remoteInputs", NotificationCompatJellybean.toBundleArray(notificationCompat$Action.mRemoteInputs));
                bundle7.putBoolean("showsUserInterface", notificationCompat$Action.mShowsUserInterface);
                bundle7.putInt("semanticAction", notificationCompat$Action.mSemanticAction);
                bundle6.putBundle(num, bundle7);
            }
            bundle4.putBundle("invisible_actions", bundle6);
            bundle5.putBundle("invisible_actions", bundle6);
            if (notificationCompat$Builder.mExtras == null) {
                notificationCompat$Builder.mExtras = new Bundle();
            }
            notificationCompat$Builder.mExtras.putBundle("android.car.EXTENSIONS", bundle4);
            this.mExtras.putBundle("android.car.EXTENSIONS", bundle5);
        }
        int i6 = Build.VERSION.SDK_INT;
        if (i6 >= 24) {
            this.mBuilder.setExtras(notificationCompat$Builder.mExtras).setRemoteInputHistory(null);
        }
        if (i6 >= 26) {
            this.mBuilder.setBadgeIconType(0).setSettingsText(null).setShortcutId(null).setTimeoutAfter(0L).setGroupAlertBehavior(0);
            if (!TextUtils.isEmpty(notificationCompat$Builder.mChannelId)) {
                this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i6 >= 28) {
            Iterator<Person> it2 = notificationCompat$Builder.mPersonList.iterator();
            while (it2.hasNext()) {
                Notification.Builder builder2 = this.mBuilder;
                Objects.requireNonNull(it2.next());
                builder2.addPerson(new Person.Builder().setName(null).setIcon(null).setUri(null).setKey(null).setBot(false).setImportant(false).build());
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.mBuilder.setAllowSystemGeneratedContextualActions(notificationCompat$Builder.mAllowSystemGeneratedContextualActions);
            this.mBuilder.setBubbleMetadata(null);
        }
    }

    public static List<String> combineLists(List<String> list, List<String> list2) {
        if (list == null) {
            return list2;
        }
        if (list2 == null) {
            return list;
        }
        ArraySet arraySet = new ArraySet(list2.size() + list.size());
        arraySet.addAll(list);
        arraySet.addAll(list2);
        return new ArrayList(arraySet);
    }

    public static List<String> getPeople(List<Person> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Person person : list) {
            Objects.requireNonNull(person);
            arrayList.add(HttpUrl.FRAGMENT_ENCODE_SET);
        }
        return arrayList;
    }
}
