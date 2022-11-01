package androidx.databinding;

import android.view.View;
/* loaded from: classes.dex */
public class DataBindingUtil {
    public static DataBinderMapper sMapper = new DataBinderMapperImpl();

    public static <T extends ViewDataBinding> T bind(DataBindingComponent dataBindingComponent, View view, int i) {
        return (T) sMapper.getDataBinder(dataBindingComponent, view, i);
    }
}
