package androidx.transition;

import android.view.View;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class TransitionValues {
    public View view;
    public final Map<String, Object> values = new HashMap();
    public final ArrayList<Transition> mTargetedTransitions = new ArrayList<>();

    @Deprecated
    public TransitionValues() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TransitionValues)) {
            return false;
        }
        TransitionValues transitionValues = (TransitionValues) obj;
        return this.view == transitionValues.view && this.values.equals(transitionValues.values);
    }

    public int hashCode() {
        return this.values.hashCode() + (this.view.hashCode() * 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("TransitionValues@");
        outline13.append(Integer.toHexString(hashCode()));
        outline13.append(":\n");
        String outline8 = GeneratedOutlineSupport.outline8(outline13.toString() + "    view = " + this.view + "\n", "    values:");
        for (String str : this.values.keySet()) {
            outline8 = outline8 + "    " + str + ": " + this.values.get(str) + "\n";
        }
        return outline8;
    }

    public TransitionValues(View view) {
        this.view = view;
    }
}
