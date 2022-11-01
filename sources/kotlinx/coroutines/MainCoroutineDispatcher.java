package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: MainCoroutineDispatcher.kt */
/* loaded from: classes.dex */
public abstract class MainCoroutineDispatcher extends CoroutineDispatcher {
    public abstract MainCoroutineDispatcher getImmediate();

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String stringInternalImpl = toStringInternalImpl();
        if (stringInternalImpl != null) {
            return stringInternalImpl;
        }
        return getClass().getSimpleName() + '@' + InputKt.getHexAddress(this);
    }

    public final String toStringInternalImpl() {
        MainCoroutineDispatcher mainCoroutineDispatcher;
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        MainCoroutineDispatcher mainCoroutineDispatcher2 = MainDispatcherLoader.dispatcher;
        if (this == mainCoroutineDispatcher2) {
            return "Dispatchers.Main";
        }
        try {
            mainCoroutineDispatcher = mainCoroutineDispatcher2.getImmediate();
        } catch (UnsupportedOperationException unused) {
            mainCoroutineDispatcher = null;
        }
        if (this == mainCoroutineDispatcher) {
            return "Dispatchers.Main.immediate";
        }
        return null;
    }
}
