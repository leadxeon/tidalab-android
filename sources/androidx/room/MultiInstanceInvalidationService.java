package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import java.util.HashMap;
/* loaded from: classes.dex */
public class MultiInstanceInvalidationService extends Service {
    public int mMaxClientId = 0;
    public final HashMap<Integer, String> mClientNames = new HashMap<>();
    public final RemoteCallbackList<IMultiInstanceInvalidationCallback> mCallbackList = new RemoteCallbackList<IMultiInstanceInvalidationCallback>() { // from class: androidx.room.MultiInstanceInvalidationService.1
        @Override // android.os.RemoteCallbackList
        public void onCallbackDied(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, Object obj) {
            MultiInstanceInvalidationService.this.mClientNames.remove(Integer.valueOf(((Integer) obj).intValue()));
        }
    };
    public final IMultiInstanceInvalidationService$Stub mBinder = new AnonymousClass2();

    /* renamed from: androidx.room.MultiInstanceInvalidationService$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 extends IMultiInstanceInvalidationService$Stub {
        public AnonymousClass2() {
        }

        public void broadcastInvalidation(int i, String[] strArr) {
            synchronized (MultiInstanceInvalidationService.this.mCallbackList) {
                String str = MultiInstanceInvalidationService.this.mClientNames.get(Integer.valueOf(i));
                if (str == null) {
                    Log.w("ROOM", "Remote invalidation client ID not registered");
                    return;
                }
                int beginBroadcast = MultiInstanceInvalidationService.this.mCallbackList.beginBroadcast();
                for (int i2 = 0; i2 < beginBroadcast; i2++) {
                    int intValue = ((Integer) MultiInstanceInvalidationService.this.mCallbackList.getBroadcastCookie(i2)).intValue();
                    String str2 = MultiInstanceInvalidationService.this.mClientNames.get(Integer.valueOf(intValue));
                    if (i != intValue && str.equals(str2)) {
                        try {
                            MultiInstanceInvalidationService.this.mCallbackList.getBroadcastItem(i2).onInvalidation(strArr);
                        } catch (RemoteException e) {
                            Log.w("ROOM", "Error invoking a remote callback", e);
                        }
                    }
                }
                MultiInstanceInvalidationService.this.mCallbackList.finishBroadcast();
            }
        }

        public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) {
            if (str == null) {
                return 0;
            }
            synchronized (MultiInstanceInvalidationService.this.mCallbackList) {
                MultiInstanceInvalidationService multiInstanceInvalidationService = MultiInstanceInvalidationService.this;
                int i = multiInstanceInvalidationService.mMaxClientId + 1;
                multiInstanceInvalidationService.mMaxClientId = i;
                if (multiInstanceInvalidationService.mCallbackList.register(iMultiInstanceInvalidationCallback, Integer.valueOf(i))) {
                    MultiInstanceInvalidationService.this.mClientNames.put(Integer.valueOf(i), str);
                    return i;
                }
                MultiInstanceInvalidationService multiInstanceInvalidationService2 = MultiInstanceInvalidationService.this;
                multiInstanceInvalidationService2.mMaxClientId--;
                return 0;
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
