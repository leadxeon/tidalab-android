package com.tidalab.v2board.clash;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowInsetsController;
import androidx.activity.result.contract.ActivityResultContract;
import com.facebook.react.ReactActivity;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.Design;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.DarkMode;
import com.tidalab.v2board.clash.design.store.UiStore;
import com.tidalab.v2board.clash.design.ui.DayNight;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.remote.Broadcasts;
import com.tidalab.v2board.clash.remote.Remote;
import com.tidalab.v2board.clash.util.ApplicationObserver;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.SupervisorJobImpl;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: BaseActivity.kt */
/* loaded from: classes.dex */
public abstract class BaseActivity<D extends Design<?>> extends ReactActivity implements CoroutineScope, Broadcasts.Observer {
    public final /* synthetic */ CoroutineScope $$delegate_0;
    public boolean activityStarted;
    public boolean deferRunning;
    public D design;
    public final Lazy uiStore$delegate = InputKt.lazy(new BaseActivity$uiStore$2(this));
    public final Channel<Event> events = InputKt.Channel$default(Integer.MAX_VALUE, null, null, 6);
    public Function1<? super Continuation<? super Unit>, ? extends Object> defer = new BaseActivity$defer$1(null);
    public final AtomicInteger nextRequestKey = new AtomicInteger(0);
    public DayNight dayNight = DayNight.Day;

    /* compiled from: BaseActivity.kt */
    /* loaded from: classes.dex */
    public enum Event {
        ServiceRecreated,
        ActivityStart,
        ActivityStop,
        ClashStop,
        ClashStart,
        ProfileLoaded,
        ProfileChanged
    }

    public BaseActivity() {
        SupervisorJobImpl supervisorJobImpl = new SupervisorJobImpl(null);
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        this.$$delegate_0 = new ContextScope(CoroutineContext.Element.DefaultImpls.plus(supervisorJobImpl, MainDispatcherLoader.dispatcher));
    }

    @Override // android.app.Activity
    public void finish() {
        if (!this.deferRunning) {
            this.deferRunning = true;
            InputKt.launch$default(this, null, null, new BaseActivity$finish$1(this, null), 3, null);
        }
    }

    public final boolean getClashRunning() {
        Remote remote = Remote.INSTANCE;
        return Remote.broadcasts.clashRunning;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public final UiStore getUiStore() {
        return (UiStore) this.uiStore$delegate.getValue();
    }

    public abstract Object main(Continuation<? super Unit> continuation);

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (queryDayNight(configuration) != this.dayNight) {
            ApplicationObserver applicationObserver = ApplicationObserver.INSTANCE;
            for (Activity activity : ApplicationObserver.activities) {
                activity.recreate();
            }
        }
    }

    @Override // com.facebook.react.ReactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DayNight queryDayNight = queryDayNight(getResources().getConfiguration());
        int ordinal = queryDayNight.ordinal();
        if (ordinal == 0) {
            getTheme().applyStyle(R.style.AppThemeLight, true);
        } else if (ordinal == 1) {
            getTheme().applyStyle(R.style.AppThemeDark, true);
        }
        Window window = getWindow();
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            window.getDecorView().setForceDarkAllowed(false);
        }
        PathParser.setSystemBarsTranslucentCompat(getWindow(), true);
        getWindow().setStatusBarColor(InputKt.resolveThemedColor(this, 16843857));
        getWindow().setNavigationBarColor(InputKt.resolveThemedColor(this, 16843858));
        if (i >= 23) {
            Window window2 = getWindow();
            if (InputKt.resolveThemedBoolean(this, 16844000)) {
                if (i >= 30) {
                    WindowInsetsController windowInsetsController = window2.getDecorView().getWindowInsetsController();
                    if (windowInsetsController != null) {
                        windowInsetsController.setSystemBarsAppearance(8, 8);
                    }
                } else {
                    window2.getDecorView().setSystemUiVisibility(window2.getDecorView().getSystemUiVisibility() | 8192);
                }
            } else if (i >= 30) {
                WindowInsetsController windowInsetsController2 = window2.getDecorView().getWindowInsetsController();
                if (windowInsetsController2 != null) {
                    windowInsetsController2.setSystemBarsAppearance(0, 8);
                }
            } else {
                window2.getDecorView().setSystemUiVisibility(window2.getDecorView().getSystemUiVisibility() & (-8193));
            }
        }
        if (i >= 27) {
            Window window3 = getWindow();
            if (InputKt.resolveThemedBoolean(this, 16844140)) {
                if (i >= 30) {
                    WindowInsetsController windowInsetsController3 = window3.getDecorView().getWindowInsetsController();
                    if (windowInsetsController3 != null) {
                        windowInsetsController3.setSystemBarsAppearance(16, 16);
                    }
                } else {
                    window3.getDecorView().setSystemUiVisibility(window3.getDecorView().getSystemUiVisibility() | 16);
                }
            } else if (i >= 30) {
                WindowInsetsController windowInsetsController4 = window3.getDecorView().getWindowInsetsController();
                if (windowInsetsController4 != null) {
                    windowInsetsController4.setSystemBarsAppearance(0, 16);
                }
            } else {
                window3.getDecorView().setSystemUiVisibility(window3.getDecorView().getSystemUiVisibility() & (-17));
            }
        }
        this.dayNight = queryDayNight;
        InputKt.launch$default(this, null, null, new BaseActivity$onCreate$1(this, null), 3, null);
    }

    @Override // com.facebook.react.ReactActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        D d = this.design;
        if (d != null) {
            InputKt.cancel$default(d, null, 1);
        }
        InputKt.cancel$default(this, null, 1);
        super.onDestroy();
    }

    @Override // com.tidalab.v2board.clash.remote.Broadcasts.Observer
    public void onProfileChanged() {
        this.events.mo14trySendJP2dKIU(Event.ProfileChanged);
    }

    @Override // com.tidalab.v2board.clash.remote.Broadcasts.Observer
    public void onProfileLoaded() {
        this.events.mo14trySendJP2dKIU(Event.ProfileLoaded);
    }

    @Override // com.tidalab.v2board.clash.remote.Broadcasts.Observer
    public void onServiceRecreated() {
        this.events.mo14trySendJP2dKIU(Event.ServiceRecreated);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.activityStarted = true;
        Remote remote = Remote.INSTANCE;
        Remote.broadcasts.receivers.add(this);
        this.events.mo14trySendJP2dKIU(Event.ActivityStart);
    }

    @Override // com.tidalab.v2board.clash.remote.Broadcasts.Observer
    public void onStarted() {
        this.events.mo14trySendJP2dKIU(Event.ClashStart);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.activityStarted = false;
        Remote remote = Remote.INSTANCE;
        Remote.broadcasts.receivers.remove(this);
        this.events.mo14trySendJP2dKIU(Event.ActivityStop);
    }

    @Override // com.tidalab.v2board.clash.remote.Broadcasts.Observer
    public void onStopped(String str) {
        this.events.mo14trySendJP2dKIU(Event.ClashStop);
        if (str != null && this.activityStarted) {
            InputKt.launch$default(this, null, null, new BaseActivity$onStopped$1(this, str, null), 3, null);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public final DayNight queryDayNight(Configuration configuration) {
        DayNight dayNight = DayNight.Night;
        DayNight dayNight2 = DayNight.Day;
        UiStore uiStore = getUiStore();
        int ordinal = ((DarkMode) uiStore.darkMode$delegate.getValue(uiStore, UiStore.$$delegatedProperties[1])).ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    return dayNight;
                }
                throw new NoWhenBranchMatchedException();
            }
        } else if ((configuration.uiMode & 48) == 32) {
            return dayNight;
        }
        return dayNight2;
    }

    public final Object setContentDesign(final D d, Continuation<? super Unit> continuation) {
        final SafeContinuation safeContinuation = new SafeContinuation(InputKt.intercepted(continuation));
        getWindow().getDecorView().post(new Runnable() { // from class: com.tidalab.v2board.clash.BaseActivity$setContentDesign$2$1
            /* JADX WARN: Type inference failed for: r1v0, types: [D extends com.tidalab.v2board.clash.design.Design<?>, com.tidalab.v2board.clash.design.Design] */
            /* JADX WARN: Unknown variable types count: 1 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() {
                /*
                    r2 = this;
                    com.tidalab.v2board.clash.BaseActivity<D> r0 = com.tidalab.v2board.clash.BaseActivity.this
                    com.tidalab.v2board.clash.design.Design r1 = r2
                    r0.design = r1
                    if (r1 == 0) goto L_0x0010
                    android.view.View r1 = r1.getRoot()
                    r0.setContentView(r1)
                    goto L_0x0018
                L_0x0010:
                    android.view.View r1 = new android.view.View
                    r1.<init>(r0)
                    r0.setContentView(r1)
                L_0x0018:
                    kotlin.coroutines.Continuation<kotlin.Unit> r0 = r3
                    kotlin.Unit r1 = kotlin.Unit.INSTANCE
                    r0.resumeWith(r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.BaseActivity$setContentDesign$2$1.run():void");
            }
        });
        Object orThrow = safeContinuation.getOrThrow();
        return orThrow == CoroutineSingletons.COROUTINE_SUSPENDED ? orThrow : Unit.INSTANCE;
    }

    public final <I, O> Object startActivityForResult(ActivityResultContract<I, O> activityResultContract, I i, Continuation<? super O> continuation) {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        return InputKt.withContext(MainDispatcherLoader.dispatcher, new BaseActivity$startActivityForResult$2(this, activityResultContract, i, null), continuation);
    }
}
