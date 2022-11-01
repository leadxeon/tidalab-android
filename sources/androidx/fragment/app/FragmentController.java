package androidx.fragment.app;
/* loaded from: classes.dex */
public class FragmentController {
    public final FragmentHostCallback<?> mHost;

    public FragmentController(FragmentHostCallback<?> fragmentHostCallback) {
        this.mHost = fragmentHostCallback;
    }

    public void noteStateNotSaved() {
        this.mHost.mFragmentManager.noteStateNotSaved();
    }
}
