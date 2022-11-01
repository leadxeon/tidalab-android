package androidx.lifecycle;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class ViewModel {
    public final Map<String, Object> mBagOfTags = new HashMap();

    public void onCleared() {
    }
}
