package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ConstraintLayoutStates {
    public SparseArray<State> mStateList = new SparseArray<>();
    public SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();

    /* loaded from: classes.dex */
    public static class State {
        public int mConstraintID;
        public ConstraintSet mConstraintSet;
        public int mId;
        public ArrayList<Variant> mVariants = new ArrayList<>();

        public State(Context context, XmlPullParser xmlPullParser) {
            this.mConstraintID = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 0) {
                    this.mId = obtainStyledAttributes.getResourceId(index, this.mId);
                } else if (index == 1) {
                    this.mConstraintID = obtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.clone((ConstraintLayout) LayoutInflater.from(context).inflate(this.mConstraintID, (ViewGroup) null));
                    }
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* loaded from: classes.dex */
    public static class Variant {
        public int mConstraintID;
        public ConstraintSet mConstraintSet;
        public float mMaxHeight;
        public float mMaxWidth;
        public float mMinHeight;
        public float mMinWidth;

        public Variant(Context context, XmlPullParser xmlPullParser) {
            this.mMinWidth = Float.NaN;
            this.mMinHeight = Float.NaN;
            this.mMaxWidth = Float.NaN;
            this.mMaxHeight = Float.NaN;
            this.mConstraintID = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 0) {
                    this.mConstraintID = obtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.clone((ConstraintLayout) LayoutInflater.from(context).inflate(this.mConstraintID, (ViewGroup) null));
                    }
                } else if (index == 1) {
                    this.mMaxHeight = obtainStyledAttributes.getDimension(index, this.mMaxHeight);
                } else if (index == 2) {
                    this.mMinHeight = obtainStyledAttributes.getDimension(index, this.mMinHeight);
                } else if (index == 3) {
                    this.mMaxWidth = obtainStyledAttributes.getDimension(index, this.mMaxWidth);
                } else if (index == 4) {
                    this.mMinWidth = obtainStyledAttributes.getDimension(index, this.mMinWidth);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public ConstraintLayoutStates(Context context, ConstraintLayout constraintLayout, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        State state = null;
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    char c = 65535;
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                c = 3;
                                break;
                            }
                            break;
                    }
                    if (!(c == 0 || c == 1)) {
                        if (c == 2) {
                            state = new State(context, xml);
                            this.mStateList.put(state.mId, state);
                        } else if (c == 3) {
                            Variant variant = new Variant(context, xml);
                            if (state != null) {
                                state.mVariants.add(variant);
                            }
                        } else if (c != 4) {
                            Log.v("ConstraintLayoutStates", "unknown tag " + name);
                        } else {
                            parseConstraintSet(context, xml);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x01d0, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void parseConstraintSet(android.content.Context r13, org.xmlpull.v1.XmlPullParser r14) {
        /*
            Method dump skipped, instructions count: 544
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayoutStates.parseConstraintSet(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }
}
