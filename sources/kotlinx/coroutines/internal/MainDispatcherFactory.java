package kotlinx.coroutines.internal;

import java.util.List;
import kotlinx.coroutines.MainCoroutineDispatcher;
/* compiled from: MainDispatcherFactory.kt */
/* loaded from: classes.dex */
public interface MainDispatcherFactory {
    MainCoroutineDispatcher createDispatcher(List<? extends MainDispatcherFactory> list);

    int getLoadPriority();

    String hintOnError();
}
