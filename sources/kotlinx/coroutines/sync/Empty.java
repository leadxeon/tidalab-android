package kotlinx.coroutines.sync;

import com.android.tools.r8.GeneratedOutlineSupport;
/* compiled from: Mutex.kt */
/* loaded from: classes.dex */
public final class Empty {
    public final Object locked;

    public Empty(Object obj) {
        this.locked = obj;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Empty[");
        outline13.append(this.locked);
        outline13.append(']');
        return outline13.toString();
    }
}
