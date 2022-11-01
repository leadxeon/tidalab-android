package androidx.core.util;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
/* loaded from: classes.dex */
public class Pair<F, S> {
    public final F first;
    public final S second;

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return Objects.equals(pair.first, this.first) && Objects.equals(pair.second, this.second);
    }

    public int hashCode() {
        F f = this.first;
        int i = 0;
        int hashCode = f == null ? 0 : f.hashCode();
        S s = this.second;
        if (s != null) {
            i = s.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Pair{");
        outline13.append(this.first);
        outline13.append(" ");
        outline13.append(this.second);
        outline13.append("}");
        return outline13.toString();
    }
}
