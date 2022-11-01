package kotlinx.coroutines.android;

import android.os.Looper;
import java.util.List;
import kotlinx.coroutines.internal.MainDispatcherFactory;
/* compiled from: HandlerDispatcher.kt */
/* loaded from: classes.dex */
public final class AndroidDispatcherFactory implements MainDispatcherFactory {
    @Override // kotlinx.coroutines.internal.MainDispatcherFactory
    public int getLoadPriority() {
        return 1073741823;
    }

    @Override // kotlinx.coroutines.internal.MainDispatcherFactory
    public String hintOnError() {
        return "For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used";
    }

    @Override // kotlinx.coroutines.internal.MainDispatcherFactory
    public HandlerContext createDispatcher(List<? extends MainDispatcherFactory> list) {
        return new HandlerContext(HandlerDispatcherKt.asHandler(Looper.getMainLooper(), true), null, false);
    }
}
