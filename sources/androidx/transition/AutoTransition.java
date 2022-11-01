package androidx.transition;
/* loaded from: classes.dex */
public class AutoTransition extends TransitionSet {
    public AutoTransition() {
        setOrdering(1);
        addTransition(new Fade(2));
        addTransition(new ChangeBounds());
        addTransition(new Fade(1));
    }
}
