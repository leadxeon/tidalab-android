package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class ViewModelStore {
    public final HashMap<String, ViewModel> mMap = new HashMap<>();

    public final void clear() {
        for (ViewModel viewModel : this.mMap.values()) {
            Map<String, Object> map = viewModel.mBagOfTags;
            if (map != null) {
                synchronized (map) {
                    for (Object obj : viewModel.mBagOfTags.values()) {
                        if (obj instanceof Closeable) {
                            try {
                                ((Closeable) obj).close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
            viewModel.onCleared();
        }
        this.mMap.clear();
    }
}
