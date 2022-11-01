package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
/* loaded from: classes.dex */
public class TintContextWrapper extends ContextWrapper {
    public static final Object CACHE_LOCK = new Object();
    public final Resources mResources;

    public static Context wrap(Context context) {
        if (!(context instanceof TintContextWrapper) && !(context.getResources() instanceof TintResources)) {
            context.getResources();
            int i = VectorEnabledTintResources.$r8$clinit;
        }
        return context;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.mResources.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.mResources;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        return super.getTheme();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
    }
}
