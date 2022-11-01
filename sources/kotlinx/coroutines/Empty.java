package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public final class Empty implements Incomplete {
    public final boolean isActive;

    public Empty(boolean z) {
        this.isActive = z;
    }

    @Override // kotlinx.coroutines.Incomplete
    public NodeList getList() {
        return null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return this.isActive;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Empty{");
        outline13.append(this.isActive ? "Active" : "New");
        outline13.append('}');
        return outline13.toString();
    }
}
