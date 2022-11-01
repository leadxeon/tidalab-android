package kotlinx.coroutines.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.MainCoroutineDispatcher;
/* compiled from: MainDispatchers.kt */
/* loaded from: classes.dex */
public final class MainDispatcherLoader {
    public static final boolean FAST_SERVICE_LOADER_ENABLED;
    public static final MainDispatcherLoader INSTANCE;
    public static final MainCoroutineDispatcher dispatcher;

    static {
        MainDispatcherLoader mainDispatcherLoader = new MainDispatcherLoader();
        INSTANCE = mainDispatcherLoader;
        String systemProp = InputKt.systemProp("kotlinx.coroutines.fast.service.loader");
        FAST_SERVICE_LOADER_ENABLED = systemProp == null ? true : Boolean.parseBoolean(systemProp);
        dispatcher = mainDispatcherLoader.loadMainDispatcher();
    }

    public final MainCoroutineDispatcher loadMainDispatcher() {
        Object obj;
        List<? extends MainDispatcherFactory> list = InputKt.toList(InputKt.asSequence(C$$ServiceLoaderMethods.$load$32763()));
        Iterator it = list.iterator();
        if (!it.hasNext()) {
            obj = null;
        } else {
            obj = it.next();
            if (it.hasNext()) {
                int loadPriority = ((MainDispatcherFactory) obj).getLoadPriority();
                do {
                    Object next = it.next();
                    int loadPriority2 = ((MainDispatcherFactory) next).getLoadPriority();
                    if (loadPriority < loadPriority2) {
                        obj = next;
                        loadPriority = loadPriority2;
                    }
                } while (it.hasNext());
            }
        }
        MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) obj;
        if (mainDispatcherFactory != null) {
            return mainDispatcherFactory.createDispatcher(list);
        }
        throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'");
    }
}
