package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.util.Xml;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: classes.dex */
public class ConstraintAttribute {
    public boolean mBooleanValue;
    public int mColorValue;
    public float mFloatValue;
    public int mIntegerValue;
    public String mName;
    public String mStringValue;
    public int mType;

    public ConstraintAttribute(String str, int i, Object obj) {
        this.mName = str;
        this.mType = i;
        setValue(obj);
    }

    public static void parse(Context context, XmlPullParser xmlPullParser, HashMap<String, ConstraintAttribute> hashMap) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.CustomAttribute);
        int indexCount = obtainStyledAttributes.getIndexCount();
        String str = null;
        Object obj = null;
        int i = 0;
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == 0) {
                str = obtainStyledAttributes.getString(index);
                if (str != null && str.length() > 0) {
                    str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
                }
            } else if (index == 1) {
                obj = Boolean.valueOf(obtainStyledAttributes.getBoolean(index, false));
                i = 6;
            } else if (index == 3) {
                obj = Integer.valueOf(obtainStyledAttributes.getColor(index, 0));
                i = 3;
            } else if (index == 2) {
                obj = Integer.valueOf(obtainStyledAttributes.getColor(index, 0));
                i = 4;
            } else {
                if (index == 7) {
                    obj = Float.valueOf(TypedValue.applyDimension(1, obtainStyledAttributes.getDimension(index, 0.0f), context.getResources().getDisplayMetrics()));
                } else if (index == 4) {
                    obj = Float.valueOf(obtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == 5) {
                    obj = Float.valueOf(obtainStyledAttributes.getFloat(index, Float.NaN));
                    i = 2;
                } else if (index == 6) {
                    obj = Integer.valueOf(obtainStyledAttributes.getInteger(index, -1));
                    i = 1;
                } else if (index == 8) {
                    obj = obtainStyledAttributes.getString(index);
                    i = 5;
                }
                i = 7;
            }
        }
        if (!(str == null || obj == null)) {
            hashMap.put(str, new ConstraintAttribute(str, i, obj));
        }
        obtainStyledAttributes.recycle();
    }

    public void setValue(Object obj) {
        switch (SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mType)) {
            case 0:
                this.mIntegerValue = ((Integer) obj).intValue();
                return;
            case 1:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            case 2:
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                this.mColorValue = ((Integer) obj).intValue();
                return;
            case 4:
                this.mStringValue = (String) obj;
                return;
            case 5:
                this.mBooleanValue = ((Boolean) obj).booleanValue();
                return;
            case 6:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            default:
                return;
        }
    }

    public ConstraintAttribute(ConstraintAttribute constraintAttribute, Object obj) {
        this.mName = constraintAttribute.mName;
        this.mType = constraintAttribute.mType;
        setValue(obj);
    }
}
