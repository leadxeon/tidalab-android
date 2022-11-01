package com.tidalab.v2board.clash.remote;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.AppCrashedActivity;
import com.tidalab.v2board.clash.common.Global;
import com.tidalab.v2board.clash.log.SystemLogcat;
import com.tidalab.v2board.clash.service.remote.IRemoteService;
import com.tidalab.v2board.clash.service.remote.IRemoteServiceProxy;
import com.tidalab.v2board.clash.util.ApplicationObserver;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
/* compiled from: Service.kt */
/* loaded from: classes.dex */
public final class Service {
    public static final long TOGGLE_CRASHED_INTERVAL = TimeUnit.SECONDS.toMillis(10);
    public final Application context;
    public final Resource<IRemoteService> remote = new Resource<>();
    public final Service$connection$1 connection = new ServiceConnection() { // from class: com.tidalab.v2board.clash.remote.Service$connection$1
        public long lastCrashed = -1;

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IRemoteService iRemoteService;
            Resource<IRemoteService> resource = Service.this.remote;
            Reflection.getOrCreateKotlinClass(IRemoteService.class);
            if (iBinder instanceof IRemoteService) {
                iRemoteService = (IRemoteService) iBinder;
            } else {
                iRemoteService = new IRemoteServiceProxy(iBinder);
            }
            resource.set(iRemoteService);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Service.this.remote.set(null);
            SystemLogcat.dumpCrash();
            if (System.currentTimeMillis() - this.lastCrashed < Service.TOGGLE_CRASHED_INTERVAL) {
                Service.this.unbind();
                Objects.requireNonNull(Service.this);
                ApplicationObserver applicationObserver = ApplicationObserver.INSTANCE;
                for (Activity activity : ApplicationObserver.activities) {
                    activity.finish();
                }
                Global.INSTANCE.getApplication().startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(AppCrashedActivity.class)).addFlags(268435456));
                Unit unit = Unit.INSTANCE;
            }
            this.lastCrashed = System.currentTimeMillis();
            Log.w("ClashForAndroid", "RemoteManager crashed", null);
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.tidalab.v2board.clash.remote.Service$connection$1] */
    public Service(Application application, Function0<Unit> function0) {
        this.context = application;
    }

    public final void unbind() {
        try {
            this.context.unbindService(this.connection);
        } catch (Exception unused) {
        }
        this.remote.set(null);
    }
}
