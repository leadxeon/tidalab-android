package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes.dex */
public abstract class ConstraintHelper extends View {
    public int mCount;
    public Helper mHelperWidget;
    public int[] mIds;
    public HashMap<Integer, String> mMap;
    public String mReferenceIds;
    public Context myContext;

    public ConstraintHelper(Context context) {
        super(context);
        this.mIds = new int[32];
        this.mMap = new HashMap<>();
        this.myContext = context;
        init(null);
    }

    public final void addID(String str) {
        if (str != null && str.length() != 0 && this.myContext != null) {
            String trim = str.trim();
            if (getParent() instanceof ConstraintLayout) {
                ConstraintLayout constraintLayout = (ConstraintLayout) getParent();
            }
            ConstraintLayout constraintLayout2 = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
            int i = 0;
            if (isInEditMode() && constraintLayout2 != null) {
                Object designInformation = constraintLayout2.getDesignInformation(0, trim);
                if (designInformation instanceof Integer) {
                    i = ((Integer) designInformation).intValue();
                }
            }
            if (i == 0 && constraintLayout2 != null) {
                i = findId(constraintLayout2, trim);
            }
            if (i == 0) {
                try {
                    i = R$id.class.getField(trim).getInt(null);
                } catch (Exception unused) {
                }
            }
            if (i == 0) {
                i = this.myContext.getResources().getIdentifier(trim, "id", this.myContext.getPackageName());
            }
            if (i != 0) {
                this.mMap.put(Integer.valueOf(i), trim);
                addRscID(i);
                return;
            }
            Log.w("ConstraintHelper", "Could not find id of \"" + trim + "\"");
        }
    }

    public final void addRscID(int i) {
        if (i != getId()) {
            int i2 = this.mCount + 1;
            int[] iArr = this.mIds;
            if (i2 > iArr.length) {
                this.mIds = Arrays.copyOf(iArr, iArr.length * 2);
            }
            int[] iArr2 = this.mIds;
            int i3 = this.mCount;
            iArr2[i3] = i;
            this.mCount = i3 + 1;
        }
    }

    public void applyLayoutFeatures() {
        ViewParent parent = getParent();
        if (parent != null && (parent instanceof ConstraintLayout)) {
            ConstraintLayout constraintLayout = (ConstraintLayout) parent;
            int visibility = getVisibility();
            float elevation = getElevation();
            for (int i = 0; i < this.mCount; i++) {
                View viewById = constraintLayout.getViewById(this.mIds[i]);
                if (viewById != null) {
                    viewById.setVisibility(visibility);
                    if (elevation > 0.0f) {
                        viewById.setTranslationZ(viewById.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    public final int findId(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (str == null || (resources = this.myContext.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            if (childAt.getId() != -1) {
                String str2 = null;
                try {
                    str2 = resources.getResourceEntryName(childAt.getId());
                } catch (Resources.NotFoundException unused) {
                }
                if (str.equals(str2)) {
                    return childAt.getId();
                }
            }
        }
        return 0;
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.mIds, this.mCount);
    }

    public void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 19) {
                    String string = obtainStyledAttributes.getString(index);
                    this.mReferenceIds = string;
                    setIds(string);
                }
            }
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.mReferenceIds;
        if (str != null) {
            setIds(str);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void resolveRtl(ConstraintWidget constraintWidget, boolean z) {
    }

    public void setIds(String str) {
        this.mReferenceIds = str;
        if (str != null) {
            int i = 0;
            this.mCount = 0;
            while (true) {
                int indexOf = str.indexOf(44, i);
                if (indexOf == -1) {
                    addID(str.substring(i));
                    return;
                } else {
                    addID(str.substring(i, indexOf));
                    i = indexOf + 1;
                }
            }
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.mReferenceIds = null;
        this.mCount = 0;
        for (int i : iArr) {
            addRscID(i);
        }
    }

    public void updatePostLayout() {
    }

    public void updatePostMeasure() {
    }

    public void updatePreDraw() {
    }

    public void validateParams() {
        if (this.mHelperWidget != null) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams instanceof ConstraintLayout.LayoutParams) {
                ((ConstraintLayout.LayoutParams) layoutParams).widget = (ConstraintWidget) this.mHelperWidget;
            }
        }
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIds = new int[32];
        this.mMap = new HashMap<>();
        this.myContext = context;
        init(attributeSet);
    }
}
