package com.google.android.gms.common.api.internal;

import android.app.ActivityManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.collection.ArraySet;
import com.facebook.react.R$style;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.internal.base.zap;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public class GoogleApiManager implements Handler.Callback {
    public static GoogleApiManager zaic;
    public final Handler handler;
    public final Context zaid;
    public final GoogleApiAvailability zaie;
    public final GoogleApiAvailabilityCache zaif;
    public static final Status zahx = new Status(4, "Sign-out occurred while this API call was in progress.");
    public static final Status zahy = new Status(4, "The user must be signed in to make this API call.");
    public static final Object lock = new Object();
    public long zaib = 10000;
    public final AtomicInteger zaih = new AtomicInteger(0);
    public final Map<zai<?>, zaa<?>> zaii = new ConcurrentHashMap(5, 0.75f, 1);
    public final Set<zai<?>> zaik = new ArraySet(0);
    public final Set<zai<?>> zail = new ArraySet(0);

    /* loaded from: classes.dex */
    public class zaa<O> {
        public final /* synthetic */ GoogleApiManager zaim;
        public final Queue<zab> zain;
        public boolean zaiv;
        public final List<zab> zaiw;
        public ConnectionResult zaix;

        public final void connect() {
            R$style.checkHandlerThread(this.zaim.handler);
            throw null;
        }

        public final void zabl() {
            R$style.checkHandlerThread(this.zaim.handler);
            this.zaix = null;
        }

        public final void zabn() {
            if (this.zaiv) {
                this.zaim.handler.removeMessages(11, null);
                this.zaim.handler.removeMessages(9, null);
                this.zaiv = false;
            }
        }

        public final void zac(Status status) {
            R$style.checkHandlerThread(this.zaim.handler);
            for (zab zabVar : this.zain) {
                zabVar.zaa(status);
            }
            this.zain.clear();
        }
    }

    /* loaded from: classes.dex */
    public static class zab {
        public final zai<?> zajb;
        public final Feature zajc;

        public final boolean equals(Object obj) {
            if (obj != null && (obj instanceof zab)) {
                zab zabVar = (zab) obj;
                if (R$style.equal(this.zajb, zabVar.zajb) && R$style.equal(this.zajc, zabVar.zajc)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.zajb, this.zajc});
        }

        public final String toString() {
            Objects$ToStringHelper objects$ToStringHelper = new Objects$ToStringHelper(this, null);
            objects$ToStringHelper.add("key", this.zajb);
            objects$ToStringHelper.add("feature", this.zajc);
            return objects$ToStringHelper.toString();
        }
    }

    public GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        new AtomicInteger(1);
        this.zaid = context;
        zap zapVar = new zap(looper, this);
        this.handler = zapVar;
        this.zaie = googleApiAvailability;
        this.zaif = new GoogleApiAvailabilityCache(googleApiAvailability);
        zapVar.sendMessage(zapVar.obtainMessage(6));
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Status status;
        Feature[] zab2;
        int i = message.what;
        int i2 = 0;
        zaa<?> zaaVar = null;
        long j = 300000;
        switch (i) {
            case 1:
                if (((Boolean) message.obj).booleanValue()) {
                    j = 10000;
                }
                this.zaib = j;
                this.handler.removeMessages(12);
                for (zai<?> zaiVar : this.zaii.keySet()) {
                    Handler handler = this.handler;
                    handler.sendMessageDelayed(handler.obtainMessage(12, zaiVar), this.zaib);
                }
                break;
            case 2:
                Objects.requireNonNull((zak) message.obj);
                throw null;
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                Iterator<zaa<?>> it = this.zaii.values().iterator();
                if (it.hasNext()) {
                    zaa<?> next = it.next();
                    next.zabl();
                    next.connect();
                    throw null;
                }
                break;
            case 4:
            case 8:
            case 13:
                Objects.requireNonNull((zabv) message.obj);
                Objects.requireNonNull(null);
                throw null;
            case 5:
                int i3 = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator<zaa<?>> it2 = this.zaii.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        zaa<?> next2 = it2.next();
                        Objects.requireNonNull(next2);
                        if (i3 == 0) {
                            zaaVar = next2;
                        }
                    }
                }
                if (zaaVar != null) {
                    GoogleApiAvailability googleApiAvailability = this.zaie;
                    int i4 = connectionResult.zzh;
                    Objects.requireNonNull(googleApiAvailability);
                    AtomicBoolean atomicBoolean = GooglePlayServicesUtilLight.sCanceledAvailabilityNotification;
                    String zza = ConnectionResult.zza(i4);
                    String str = connectionResult.zzj;
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + String.valueOf(zza).length() + 69);
                    sb.append("Error resolution was canceled by the user, original error message: ");
                    sb.append(zza);
                    sb.append(": ");
                    sb.append(str);
                    zaaVar.zac(new Status(17, sb.toString()));
                    break;
                } else {
                    StringBuilder sb2 = new StringBuilder(76);
                    sb2.append("Could not find API instance ");
                    sb2.append(i3);
                    sb2.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb2.toString(), new Exception());
                    break;
                }
            case 6:
                if (this.zaid.getApplicationContext() instanceof Application) {
                    Application application = (Application) this.zaid.getApplicationContext();
                    BackgroundDetector backgroundDetector = BackgroundDetector.zzat;
                    synchronized (backgroundDetector) {
                        if (!backgroundDetector.zzax) {
                            application.registerActivityLifecycleCallbacks(backgroundDetector);
                            application.registerComponentCallbacks(backgroundDetector);
                            backgroundDetector.zzax = true;
                        }
                    }
                    zabi zabiVar = new zabi(this);
                    synchronized (backgroundDetector) {
                        backgroundDetector.zzaw.add(zabiVar);
                    }
                    if (!backgroundDetector.zzav.get()) {
                        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                        ActivityManager.getMyMemoryState(runningAppProcessInfo);
                        if (!backgroundDetector.zzav.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                            backgroundDetector.zzau.set(true);
                        }
                    }
                    if (!backgroundDetector.zzau.get()) {
                        this.zaib = 300000L;
                        break;
                    }
                }
                break;
            case 7:
                zab((GoogleApi) message.obj);
                throw null;
            case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                if (this.zaii.containsKey(message.obj)) {
                    zaa<?> zaaVar2 = this.zaii.get(message.obj);
                    R$style.checkHandlerThread(zaaVar2.zaim.handler);
                    if (zaaVar2.zaiv) {
                        zaaVar2.connect();
                        throw null;
                    }
                }
                break;
            case 10:
                Iterator<zai<?>> it3 = this.zail.iterator();
                if (!it3.hasNext()) {
                    this.zail.clear();
                    break;
                } else {
                    zaa<?> remove = this.zaii.remove(it3.next());
                    R$style.checkHandlerThread(remove.zaim.handler);
                    remove.zac(zahx);
                    throw null;
                }
            case 11:
                if (this.zaii.containsKey(message.obj)) {
                    zaa<?> zaaVar3 = this.zaii.get(message.obj);
                    R$style.checkHandlerThread(zaaVar3.zaim.handler);
                    if (zaaVar3.zaiv) {
                        zaaVar3.zabn();
                        GoogleApiManager googleApiManager = zaaVar3.zaim;
                        if (googleApiManager.zaie.isGooglePlayServicesAvailable(googleApiManager.zaid, GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) == 18) {
                            status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                        } else {
                            status = new Status(8, "API failed to connect while resuming due to an unknown error.");
                        }
                        zaaVar3.zac(status);
                        throw null;
                    }
                }
                break;
            case 12:
                if (this.zaii.containsKey(message.obj)) {
                    R$style.checkHandlerThread(this.zaii.get(message.obj).zaim.handler);
                    throw null;
                }
                break;
            case 14:
                Objects.requireNonNull((zaaf) message.obj);
                if (!this.zaii.containsKey(null)) {
                    throw null;
                }
                R$style.checkHandlerThread(this.zaii.get(null).zaim.handler);
                throw null;
            case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                zab zabVar = (zab) message.obj;
                if (this.zaii.containsKey(zabVar.zajb)) {
                    zaa<?> zaaVar4 = this.zaii.get(zabVar.zajb);
                    if (zaaVar4.zaiw.contains(zabVar) && !zaaVar4.zaiv) {
                        throw null;
                    }
                }
                break;
            case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                zab zabVar2 = (zab) message.obj;
                if (this.zaii.containsKey(zabVar2.zajb)) {
                    zaa<?> zaaVar5 = this.zaii.get(zabVar2.zajb);
                    if (zaaVar5.zaiw.remove(zabVar2)) {
                        zaaVar5.zaim.handler.removeMessages(15, zabVar2);
                        zaaVar5.zaim.handler.removeMessages(16, zabVar2);
                        Feature feature = zabVar2.zajc;
                        ArrayList arrayList = new ArrayList(zaaVar5.zain.size());
                        for (zab zabVar3 : zaaVar5.zain) {
                            if ((zabVar3 instanceof zac) && (zab2 = ((zac) zabVar3).zab(zaaVar5)) != null) {
                                int length = zab2.length;
                                int i5 = 0;
                                while (true) {
                                    if (i5 >= length) {
                                        i5 = -1;
                                    } else if (!R$style.equal(zab2[i5], feature)) {
                                        i5++;
                                    }
                                }
                                if (i5 >= 0) {
                                    arrayList.add(zabVar3);
                                }
                            }
                        }
                        int size = arrayList.size();
                        while (i2 < size) {
                            Object obj = arrayList.get(i2);
                            i2++;
                            zab zabVar4 = (zab) obj;
                            zaaVar5.zain.remove(zabVar4);
                            zabVar4.zaa(new UnsupportedApiCallException(feature));
                        }
                        break;
                    }
                }
                break;
            default:
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
        return true;
    }

    public final void zab(GoogleApi<?> googleApi) {
        Objects.requireNonNull(googleApi);
        if (this.zaii.get(null) == null) {
            new LinkedList();
            new HashSet();
            new HashMap();
            new ArrayList();
            this.handler.getLooper();
            Objects.requireNonNull(googleApi);
            new ArraySet(0).addAll(Collections.emptySet());
            throw null;
        }
        throw null;
    }

    public final boolean zac(ConnectionResult connectionResult, int i) {
        PendingIntent pendingIntent;
        GoogleApiAvailability googleApiAvailability = this.zaie;
        Context context = this.zaid;
        Objects.requireNonNull(googleApiAvailability);
        int i2 = connectionResult.zzh;
        if ((i2 == 0 || connectionResult.zzi == null) ? false : true) {
            pendingIntent = connectionResult.zzi;
        } else {
            Intent errorResolutionIntent = googleApiAvailability.getErrorResolutionIntent(context, i2, null);
            pendingIntent = errorResolutionIntent == null ? null : PendingIntent.getActivity(context, 0, errorResolutionIntent, 134217728);
        }
        if (pendingIntent == null) {
            return false;
        }
        int i3 = connectionResult.zzh;
        int i4 = GoogleApiActivity.$r8$clinit;
        Intent intent = new Intent(context, GoogleApiActivity.class);
        intent.putExtra("pending_intent", pendingIntent);
        intent.putExtra("failing_client_id", i);
        intent.putExtra("notify_manager", true);
        googleApiAvailability.zaa(context, i3, PendingIntent.getActivity(context, 0, intent, 134217728));
        return true;
    }
}
