package androidx.activity.contextaware;

import android.content.Context;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes.dex */
public final class ContextAwareHelper {
    public volatile Context mContext;
    public final Set<OnContextAvailableListener> mListeners = new CopyOnWriteArraySet();
}
