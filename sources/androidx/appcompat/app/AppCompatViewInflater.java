package androidx.appcompat.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.collection.SimpleArrayMap;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class AppCompatViewInflater {
    public final Object[] mConstructorArgs = new Object[2];
    public static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    public static final int[] sOnClickAttrs = {16843375};
    public static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    public static final SimpleArrayMap<String, Constructor<? extends View>> sConstructorMap = new SimpleArrayMap<>();

    /* loaded from: classes.dex */
    public static class DeclaredOnClickListener implements View.OnClickListener {
        public final View mHostView;
        public final String mMethodName;
        public Context mResolvedContext;
        public Method mResolvedMethod;

        public DeclaredOnClickListener(View view, String str) {
            this.mHostView = view;
            this.mMethodName = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str;
            Method method;
            if (this.mResolvedMethod == null) {
                Context context = this.mHostView.getContext();
                while (context != null) {
                    try {
                        if (!context.isRestricted() && (method = context.getClass().getMethod(this.mMethodName, View.class)) != null) {
                            this.mResolvedMethod = method;
                            this.mResolvedContext = context;
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                    context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
                }
                int id = this.mHostView.getId();
                if (id == -1) {
                    str = HttpUrl.FRAGMENT_ENCODE_SET;
                } else {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13(" with id '");
                    outline13.append(this.mHostView.getContext().getResources().getResourceEntryName(id));
                    outline13.append("'");
                    str = outline13.toString();
                }
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Could not find method ");
                outline132.append(this.mMethodName);
                outline132.append("(View) in a parent or ancestor Context for android:onClick attribute defined on view ");
                outline132.append(this.mHostView.getClass());
                outline132.append(str);
                throw new IllegalStateException(outline132.toString());
            }
            try {
                this.mResolvedMethod.invoke(this.mResolvedContext, view);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }
    }

    public AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatAutoCompleteTextView(context, attributeSet);
    }

    public AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new AppCompatButton(context, attributeSet);
    }

    public AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckBox(context, attributeSet);
    }

    public AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        return new AppCompatRadioButton(context, attributeSet);
    }

    public AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatTextView(context, attributeSet);
    }

    public View createView() {
        return null;
    }

    public final View createViewByPrefix(Context context, String str, String str2) throws ClassNotFoundException, InflateException {
        String str3;
        SimpleArrayMap<String, Constructor<? extends View>> simpleArrayMap = sConstructorMap;
        Constructor<? extends View> orDefault = simpleArrayMap.getOrDefault(str, null);
        if (orDefault == null) {
            if (str2 != null) {
                try {
                    str3 = str2 + str;
                } catch (Exception unused) {
                    return null;
                }
            } else {
                str3 = str;
            }
            orDefault = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(sConstructorSignature);
            simpleArrayMap.put(str, orDefault);
        }
        orDefault.setAccessible(true);
        return (View) orDefault.newInstance(this.mConstructorArgs);
    }

    public final void verifyNotNull(View view, String str) {
        if (view == null) {
            throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
        }
    }
}
