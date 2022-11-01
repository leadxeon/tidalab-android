package kotlin.text;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
/* compiled from: Regex.kt */
/* loaded from: classes.dex */
public final class MatchGroup {
    public final IntRange range;
    public final String value;

    public MatchGroup(String str, IntRange intRange) {
        this.value = str;
        this.range = intRange;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MatchGroup)) {
            return false;
        }
        MatchGroup matchGroup = (MatchGroup) obj;
        return Intrinsics.areEqual(this.value, matchGroup.value) && Intrinsics.areEqual(this.range, matchGroup.range);
    }

    public int hashCode() {
        String str = this.value;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        IntRange intRange = this.range;
        if (intRange != null) {
            i = intRange.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("MatchGroup(value=");
        outline13.append(this.value);
        outline13.append(", range=");
        outline13.append(this.range);
        outline13.append(")");
        return outline13.toString();
    }
}
