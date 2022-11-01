package androidx.core.view;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
/* loaded from: classes.dex */
public final class DisplayCutoutCompat {
    public final Object mDisplayCutout;

    public DisplayCutoutCompat(Object obj) {
        this.mDisplayCutout = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DisplayCutoutCompat.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.mDisplayCutout, ((DisplayCutoutCompat) obj).mDisplayCutout);
    }

    public int hashCode() {
        Object obj = this.mDisplayCutout;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("DisplayCutoutCompat{");
        outline13.append(this.mDisplayCutout);
        outline13.append("}");
        return outline13.toString();
    }
}
