package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.collection.ContainerHelpers;
import androidx.collection.LongSparseArray;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public final class ResourceManagerInternal {
    public static ResourceManagerInternal INSTANCE;
    public SimpleArrayMap<String, InflateDelegate> mDelegates;
    public final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap<>(0);
    public boolean mHasCheckedVectorDrawableSetup;
    public ResourceManagerHooks mHooks;
    public SparseArrayCompat<String> mKnownDrawableIdTags;
    public WeakHashMap<Context, SparseArrayCompat<ColorStateList>> mTintLists;
    public TypedValue mTypedValue;
    public static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    public static final ColorFilterLruCache COLOR_FILTER_CACHE = new ColorFilterLruCache(6);

    /* loaded from: classes.dex */
    public static class AsldcInflateDelegate implements InflateDelegate {
        @Override // androidx.appcompat.widget.ResourceManagerInternal.InflateDelegate
        public Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return AnimatedStateListDrawableCompat.createFromXmlInner(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class AvdcInflateDelegate implements InflateDelegate {
        @Override // androidx.appcompat.widget.ResourceManagerInternal.InflateDelegate
        public Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                Resources resources = context.getResources();
                AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(context, null, null);
                animatedVectorDrawableCompat.inflate(resources, xmlPullParser, attributeSet, theme);
                return animatedVectorDrawableCompat;
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int i) {
            super(i);
        }
    }

    /* loaded from: classes.dex */
    public static class DrawableDelegate implements InflateDelegate {
        @Override // androidx.appcompat.widget.ResourceManagerInternal.InflateDelegate
        public Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            String classAttribute = attributeSet.getClassAttribute();
            if (classAttribute == null) {
                return null;
            }
            try {
                Drawable drawable = (Drawable) DrawableDelegate.class.getClassLoader().loadClass(classAttribute).asSubclass(Drawable.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                drawable.inflate(context.getResources(), xmlPullParser, attributeSet, theme);
                return drawable;
            } catch (Exception e) {
                Log.e("DrawableDelegate", "Exception while inflating <drawable>", e);
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public interface InflateDelegate {
        Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* loaded from: classes.dex */
    public interface ResourceManagerHooks {
    }

    /* loaded from: classes.dex */
    public static class VdcInflateDelegate implements InflateDelegate {
        @Override // androidx.appcompat.widget.ResourceManagerInternal.InflateDelegate
        public Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return VectorDrawableCompat.createFromXmlInner(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    public static synchronized ResourceManagerInternal get() {
        ResourceManagerInternal resourceManagerInternal;
        synchronized (ResourceManagerInternal.class) {
            if (INSTANCE == null) {
                ResourceManagerInternal resourceManagerInternal2 = new ResourceManagerInternal();
                INSTANCE = resourceManagerInternal2;
                installDefaultInflateDelegates(resourceManagerInternal2);
            }
            resourceManagerInternal = INSTANCE;
        }
        return resourceManagerInternal;
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        synchronized (ResourceManagerInternal.class) {
            ColorFilterLruCache colorFilterLruCache = COLOR_FILTER_CACHE;
            Objects.requireNonNull(colorFilterLruCache);
            int i2 = (i + 31) * 31;
            porterDuffColorFilter = colorFilterLruCache.get(Integer.valueOf(mode.hashCode() + i2));
            if (porterDuffColorFilter == null) {
                porterDuffColorFilter = new PorterDuffColorFilter(i, mode);
                Objects.requireNonNull(colorFilterLruCache);
                colorFilterLruCache.put(Integer.valueOf(mode.hashCode() + i2), porterDuffColorFilter);
            }
        }
        return porterDuffColorFilter;
    }

    public static void installDefaultInflateDelegates(ResourceManagerInternal resourceManagerInternal) {
        if (Build.VERSION.SDK_INT < 24) {
            resourceManagerInternal.addDelegate("vector", new VdcInflateDelegate());
            resourceManagerInternal.addDelegate("animated-vector", new AvdcInflateDelegate());
            resourceManagerInternal.addDelegate("animated-selector", new AsldcInflateDelegate());
            resourceManagerInternal.addDelegate("drawable", new DrawableDelegate());
        }
    }

    public final void addDelegate(String str, InflateDelegate inflateDelegate) {
        if (this.mDelegates == null) {
            this.mDelegates = new SimpleArrayMap<>();
        }
        this.mDelegates.put(str, inflateDelegate);
    }

    public final synchronized boolean addDrawableToCache(Context context, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        LongSparseArray<WeakReference<Drawable.ConstantState>> longSparseArray = this.mDrawableCaches.get(context);
        if (longSparseArray == null) {
            longSparseArray = new LongSparseArray<>();
            this.mDrawableCaches.put(context, longSparseArray);
        }
        longSparseArray.put(j, new WeakReference<>(constantState));
        return true;
    }

    public final Drawable createDrawableIfNeeded(Context context, int i) {
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        TypedValue typedValue = this.mTypedValue;
        context.getResources().getValue(i, typedValue, true);
        long j = (typedValue.assetCookie << 32) | typedValue.data;
        Drawable cachedDrawable = getCachedDrawable(context, j);
        if (cachedDrawable != null) {
            return cachedDrawable;
        }
        ResourceManagerHooks resourceManagerHooks = this.mHooks;
        LayerDrawable layerDrawable = null;
        if (resourceManagerHooks != null) {
            AppCompatDrawableManager.AnonymousClass1 r1 = (AppCompatDrawableManager.AnonymousClass1) resourceManagerHooks;
            if (i == R.drawable.abc_cab_background_top_material) {
                layerDrawable = new LayerDrawable(new Drawable[]{getDrawable(context, R.drawable.abc_cab_background_internal_bg), getDrawable(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
            } else if (i == R.drawable.abc_ratingbar_material) {
                layerDrawable = r1.getRatingBarLayerDrawable(this, context, R.dimen.abc_star_big);
            } else if (i == R.drawable.abc_ratingbar_indicator_material) {
                layerDrawable = r1.getRatingBarLayerDrawable(this, context, R.dimen.abc_star_medium);
            } else if (i == R.drawable.abc_ratingbar_small_material) {
                layerDrawable = r1.getRatingBarLayerDrawable(this, context, R.dimen.abc_star_small);
            }
        }
        if (layerDrawable != null) {
            layerDrawable.setChangingConfigurations(typedValue.changingConfigurations);
            addDrawableToCache(context, j, layerDrawable);
        }
        return layerDrawable;
    }

    public final synchronized Drawable getCachedDrawable(Context context, long j) {
        LongSparseArray<WeakReference<Drawable.ConstantState>> longSparseArray = this.mDrawableCaches.get(context);
        if (longSparseArray == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReference = longSparseArray.get(j, null);
        if (weakReference != null) {
            Drawable.ConstantState constantState = weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            int binarySearch = ContainerHelpers.binarySearch(longSparseArray.mKeys, longSparseArray.mSize, j);
            if (binarySearch >= 0) {
                Object[] objArr = longSparseArray.mValues;
                Object obj = objArr[binarySearch];
                Object obj2 = LongSparseArray.DELETED;
                if (obj != obj2) {
                    objArr[binarySearch] = obj2;
                    longSparseArray.mGarbage = true;
                }
            }
        }
        return null;
    }

    public synchronized Drawable getDrawable(Context context, int i) {
        return getDrawable(context, i, false);
    }

    public synchronized ColorStateList getTintList(Context context, int i) {
        ColorStateList colorStateList;
        SparseArrayCompat<ColorStateList> sparseArrayCompat;
        WeakHashMap<Context, SparseArrayCompat<ColorStateList>> weakHashMap = this.mTintLists;
        ColorStateList colorStateList2 = null;
        colorStateList = (weakHashMap == null || (sparseArrayCompat = weakHashMap.get(context)) == null) ? null : sparseArrayCompat.get(i, null);
        if (colorStateList == null) {
            ResourceManagerHooks resourceManagerHooks = this.mHooks;
            if (resourceManagerHooks != null) {
                colorStateList2 = ((AppCompatDrawableManager.AnonymousClass1) resourceManagerHooks).getTintListForDrawableRes(context, i);
            }
            if (colorStateList2 != null) {
                if (this.mTintLists == null) {
                    this.mTintLists = new WeakHashMap<>();
                }
                SparseArrayCompat<ColorStateList> sparseArrayCompat2 = this.mTintLists.get(context);
                if (sparseArrayCompat2 == null) {
                    sparseArrayCompat2 = new SparseArrayCompat<>();
                    this.mTintLists.put(context, sparseArrayCompat2);
                }
                sparseArrayCompat2.append(i, colorStateList2);
            }
            colorStateList = colorStateList2;
        }
        return colorStateList;
    }

    public final Drawable loadDrawableFromDelegates(Context context, int i) {
        int next;
        SimpleArrayMap<String, InflateDelegate> simpleArrayMap = this.mDelegates;
        if (simpleArrayMap == null || simpleArrayMap.isEmpty()) {
            return null;
        }
        SparseArrayCompat<String> sparseArrayCompat = this.mKnownDrawableIdTags;
        if (sparseArrayCompat != null) {
            String str = sparseArrayCompat.get(i, null);
            if ("appcompat_skip_skip".equals(str) || (str != null && this.mDelegates.getOrDefault(str, null) == null)) {
                return null;
            }
        } else {
            this.mKnownDrawableIdTags = new SparseArrayCompat<>();
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        TypedValue typedValue = this.mTypedValue;
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        long j = (typedValue.assetCookie << 32) | typedValue.data;
        Drawable cachedDrawable = getCachedDrawable(context, j);
        if (cachedDrawable != null) {
            return cachedDrawable;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                while (true) {
                    next = xml.next();
                    if (next == 2 || next == 1) {
                        break;
                    }
                }
                if (next == 2) {
                    String name = xml.getName();
                    this.mKnownDrawableIdTags.append(i, name);
                    InflateDelegate inflateDelegate = this.mDelegates.get(name);
                    if (inflateDelegate != null) {
                        cachedDrawable = inflateDelegate.createFromXmlInner(context, xml, asAttributeSet, context.getTheme());
                    }
                    if (cachedDrawable != null) {
                        cachedDrawable.setChangingConfigurations(typedValue.changingConfigurations);
                        addDrawableToCache(context, j, cachedDrawable);
                    }
                } else {
                    throw new XmlPullParserException("No start tag found");
                }
            } catch (Exception e) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e);
            }
        }
        if (cachedDrawable == null) {
            this.mKnownDrawableIdTags.append(i, "appcompat_skip_skip");
        }
        return cachedDrawable;
    }

    public final Drawable tintDrawable(Context context, int i, boolean z, Drawable drawable) {
        ColorStateList tintList = getTintList(context, i);
        PorterDuff.Mode mode = null;
        if (tintList != null) {
            if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable wrap = AppOpsManagerCompat.wrap(drawable);
            wrap.setTintList(tintList);
            ResourceManagerHooks resourceManagerHooks = this.mHooks;
            if (resourceManagerHooks != null) {
                AppCompatDrawableManager.AnonymousClass1 r12 = (AppCompatDrawableManager.AnonymousClass1) resourceManagerHooks;
                if (i == R.drawable.abc_switch_thumb_material) {
                    mode = PorterDuff.Mode.MULTIPLY;
                }
            }
            if (mode == null) {
                return wrap;
            }
            wrap.setTintMode(mode);
            return wrap;
        }
        ResourceManagerHooks resourceManagerHooks2 = this.mHooks;
        if (resourceManagerHooks2 != null) {
            AppCompatDrawableManager.AnonymousClass1 r0 = (AppCompatDrawableManager.AnonymousClass1) resourceManagerHooks2;
            boolean z2 = true;
            if (i == R.drawable.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908288);
                int themeAttrColor = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
                PorterDuff.Mode mode2 = AppCompatDrawableManager.DEFAULT_MODE;
                r0.setPorterDuffColorFilter(findDrawableByLayerId, themeAttrColor, mode2);
                r0.setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), mode2);
                r0.setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), mode2);
            } else if (i == R.drawable.abc_ratingbar_material || i == R.drawable.abc_ratingbar_indicator_material || i == R.drawable.abc_ratingbar_small_material) {
                LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
                Drawable findDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(16908288);
                int disabledThemeAttrColor = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
                PorterDuff.Mode mode3 = AppCompatDrawableManager.DEFAULT_MODE;
                r0.setPorterDuffColorFilter(findDrawableByLayerId2, disabledThemeAttrColor, mode3);
                r0.setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), mode3);
                r0.setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), mode3);
            } else {
                z2 = false;
            }
            if (z2) {
                return drawable;
            }
        }
        if (tintDrawableUsingColorFilter(context, i, drawable) || !z) {
            return drawable;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean tintDrawableUsingColorFilter(android.content.Context r8, int r9, android.graphics.drawable.Drawable r10) {
        /*
            r7 = this;
            androidx.appcompat.widget.ResourceManagerInternal$ResourceManagerHooks r0 = r7.mHooks
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0072
            androidx.appcompat.widget.AppCompatDrawableManager$1 r0 = (androidx.appcompat.widget.AppCompatDrawableManager.AnonymousClass1) r0
            java.util.Objects.requireNonNull(r0)
            android.graphics.PorterDuff$Mode r3 = androidx.appcompat.widget.AppCompatDrawableManager.DEFAULT_MODE
            int[] r4 = r0.COLORFILTER_TINT_COLOR_CONTROL_NORMAL
            boolean r4 = r0.arrayContains(r4, r9)
            r5 = 16842801(0x1010031, float:2.3693695E-38)
            r6 = -1
            if (r4 == 0) goto L_0x001d
            r5 = 2130968770(0x7f0400c2, float:1.7546203E38)
            goto L_0x0049
        L_0x001d:
            int[] r4 = r0.COLORFILTER_COLOR_CONTROL_ACTIVATED
            boolean r4 = r0.arrayContains(r4, r9)
            if (r4 == 0) goto L_0x0029
            r5 = 2130968767(0x7f0400bf, float:1.7546197E38)
            goto L_0x0049
        L_0x0029:
            int[] r4 = r0.COLORFILTER_COLOR_BACKGROUND_MULTIPLY
            boolean r0 = r0.arrayContains(r4, r9)
            if (r0 == 0) goto L_0x0034
            android.graphics.PorterDuff$Mode r3 = android.graphics.PorterDuff.Mode.MULTIPLY
            goto L_0x0049
        L_0x0034:
            r0 = 2131230763(0x7f08002b, float:1.8077588E38)
            if (r9 != r0) goto L_0x0044
            r9 = 16842800(0x1010030, float:2.3693693E-38)
            r0 = 1109603123(0x42233333, float:40.8)
            int r0 = java.lang.Math.round(r0)
            goto L_0x004b
        L_0x0044:
            r0 = 2131230745(0x7f080019, float:1.8077551E38)
            if (r9 != r0) goto L_0x004d
        L_0x0049:
            r9 = r5
            r0 = -1
        L_0x004b:
            r4 = 1
            goto L_0x0050
        L_0x004d:
            r9 = 0
            r0 = -1
            r4 = 0
        L_0x0050:
            if (r4 == 0) goto L_0x006e
            boolean r4 = androidx.appcompat.widget.DrawableUtils.canSafelyMutateDrawable(r10)
            if (r4 == 0) goto L_0x005c
            android.graphics.drawable.Drawable r10 = r10.mutate()
        L_0x005c:
            int r8 = androidx.appcompat.widget.ThemeUtils.getThemeAttrColor(r8, r9)
            android.graphics.PorterDuffColorFilter r8 = androidx.appcompat.widget.AppCompatDrawableManager.getPorterDuffColorFilter(r8, r3)
            r10.setColorFilter(r8)
            if (r0 == r6) goto L_0x006c
            r10.setAlpha(r0)
        L_0x006c:
            r8 = 1
            goto L_0x006f
        L_0x006e:
            r8 = 0
        L_0x006f:
            if (r8 == 0) goto L_0x0072
            goto L_0x0073
        L_0x0072:
            r1 = 0
        L_0x0073:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ResourceManagerInternal.tintDrawableUsingColorFilter(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    public synchronized Drawable getDrawable(Context context, int i, boolean z) {
        Drawable loadDrawableFromDelegates;
        if (!this.mHasCheckedVectorDrawableSetup) {
            boolean z2 = true;
            this.mHasCheckedVectorDrawableSetup = true;
            Drawable drawable = getDrawable(context, R.drawable.abc_vector_test);
            if (drawable != null) {
                if (!(drawable instanceof VectorDrawableCompat) && !"android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
                    z2 = false;
                }
            }
            this.mHasCheckedVectorDrawableSetup = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
        loadDrawableFromDelegates = loadDrawableFromDelegates(context, i);
        if (loadDrawableFromDelegates == null) {
            loadDrawableFromDelegates = createDrawableIfNeeded(context, i);
        }
        if (loadDrawableFromDelegates == null) {
            Object obj = ContextCompat.sLock;
            loadDrawableFromDelegates = ContextCompat.Api21Impl.getDrawable(context, i);
        }
        if (loadDrawableFromDelegates != null) {
            loadDrawableFromDelegates = tintDrawable(context, i, z, loadDrawableFromDelegates);
        }
        if (loadDrawableFromDelegates != null) {
            DrawableUtils.fixDrawable(loadDrawableFromDelegates);
        }
        return loadDrawableFromDelegates;
    }
}
