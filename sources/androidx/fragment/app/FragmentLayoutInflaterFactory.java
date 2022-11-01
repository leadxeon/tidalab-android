package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.SimpleArrayMap;
import androidx.fragment.R$styleable;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {
    public final FragmentManager mFragmentManager;

    public FragmentLayoutInflaterFactory(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        final FragmentStateManager fragmentStateManager;
        if (FragmentContainerView.class.getName().equals(str)) {
            return new FragmentContainerView(context, attributeSet, this.mFragmentManager);
        }
        Fragment fragment = null;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Fragment);
        int i = 0;
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (attributeValue != null) {
            ClassLoader classLoader = context.getClassLoader();
            SimpleArrayMap<ClassLoader, SimpleArrayMap<String, Class<?>>> simpleArrayMap = FragmentFactory.sClassCacheMap;
            try {
                z = Fragment.class.isAssignableFrom(FragmentFactory.loadClass(classLoader, attributeValue));
            } catch (ClassNotFoundException unused) {
                z = false;
            }
            if (z) {
                if (view != null) {
                    i = view.getId();
                }
                if (i == -1 && resourceId == -1 && string == null) {
                    throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                }
                if (resourceId != -1) {
                    fragment = this.mFragmentManager.findFragmentById(resourceId);
                }
                if (fragment == null && string != null) {
                    fragment = this.mFragmentManager.findFragmentByTag(string);
                }
                if (fragment == null && i != -1) {
                    fragment = this.mFragmentManager.findFragmentById(i);
                }
                if (fragment == null) {
                    fragment = this.mFragmentManager.getFragmentFactory().instantiate(context.getClassLoader(), attributeValue);
                    fragment.mFromLayout = true;
                    fragment.mFragmentId = resourceId != 0 ? resourceId : i;
                    fragment.mContainerId = i;
                    fragment.mTag = string;
                    fragment.mInLayout = true;
                    FragmentManager fragmentManager = this.mFragmentManager;
                    fragment.mFragmentManager = fragmentManager;
                    FragmentHostCallback<?> fragmentHostCallback = fragmentManager.mHost;
                    fragment.mHost = fragmentHostCallback;
                    Context context2 = fragmentHostCallback.mContext;
                    fragment.onInflate(attributeSet, fragment.mSavedFragmentState);
                    fragmentStateManager = this.mFragmentManager.addFragment(fragment);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Fragment " + fragment + " has been inflated via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
                    }
                } else if (!fragment.mInLayout) {
                    fragment.mInLayout = true;
                    FragmentManager fragmentManager2 = this.mFragmentManager;
                    fragment.mFragmentManager = fragmentManager2;
                    FragmentHostCallback<?> fragmentHostCallback2 = fragmentManager2.mHost;
                    fragment.mHost = fragmentHostCallback2;
                    Context context3 = fragmentHostCallback2.mContext;
                    fragment.onInflate(attributeSet, fragment.mSavedFragmentState);
                    fragmentStateManager = this.mFragmentManager.createOrGetFragmentStateManager(fragment);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Retained Fragment " + fragment + " has been re-attached via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
                    }
                } else {
                    throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + attributeValue);
                }
                fragment.mContainer = (ViewGroup) view;
                fragmentStateManager.moveToExpectedState();
                fragmentStateManager.ensureInflatedView();
                View view2 = fragment.mView;
                if (view2 != null) {
                    if (resourceId != 0) {
                        view2.setId(resourceId);
                    }
                    if (fragment.mView.getTag() == null) {
                        fragment.mView.setTag(string);
                    }
                    fragment.mView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.fragment.app.FragmentLayoutInflaterFactory.1
                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewAttachedToWindow(View view3) {
                            FragmentStateManager fragmentStateManager2 = fragmentStateManager;
                            Fragment fragment2 = fragmentStateManager2.mFragment;
                            fragmentStateManager2.moveToExpectedState();
                            SpecialEffectsController.getOrCreateController((ViewGroup) fragment2.mView.getParent(), FragmentLayoutInflaterFactory.this.mFragmentManager).forceCompleteAllOperations();
                        }

                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewDetachedFromWindow(View view3) {
                        }
                    });
                    return fragment.mView;
                }
                throw new IllegalStateException(GeneratedOutlineSupport.outline9("Fragment ", attributeValue, " did not create a view."));
            }
        }
        return null;
    }
}
