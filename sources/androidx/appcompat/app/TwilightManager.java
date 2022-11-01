package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
/* loaded from: classes.dex */
public class TwilightManager {
    public static TwilightManager sInstance;
    public final Context mContext;
    public final LocationManager mLocationManager;
    public final TwilightState mTwilightState = new TwilightState();

    /* loaded from: classes.dex */
    public static class TwilightState {
        public boolean isNight;
        public long nextUpdate;
    }

    public TwilightManager(Context context, LocationManager locationManager) {
        this.mContext = context;
        this.mLocationManager = locationManager;
    }

    public final Location getLastKnownLocationForProvider(String str) {
        try {
            if (this.mLocationManager.isProviderEnabled(str)) {
                return this.mLocationManager.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
            return null;
        }
    }
}
