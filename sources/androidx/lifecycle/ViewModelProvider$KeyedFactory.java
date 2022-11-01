package androidx.lifecycle;
/* loaded from: classes.dex */
public abstract class ViewModelProvider$KeyedFactory extends ViewModelProvider$OnRequeryFactory implements ViewModelProvider$Factory {
    public abstract <T extends ViewModel> T create(String str, Class<T> cls);
}
