package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import okhttp3.internal.ws.WebSocketProtocol;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ConstraintSet {
    public static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    public static SparseIntArray mapToConstant;
    public HashMap<String, ConstraintAttribute> mSavedAttributes = new HashMap<>();
    public boolean mForceId = true;
    public HashMap<Integer, Constraint> mConstraints = new HashMap<>();

    /* loaded from: classes.dex */
    public static class Constraint {
        public int mViewId;
        public final PropertySet propertySet = new PropertySet();
        public final Motion motion = new Motion();
        public final Layout layout = new Layout();
        public final Transform transform = new Transform();
        public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap<>();

        public void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            Layout layout = this.layout;
            layoutParams.leftToLeft = layout.leftToLeft;
            layoutParams.leftToRight = layout.leftToRight;
            layoutParams.rightToLeft = layout.rightToLeft;
            layoutParams.rightToRight = layout.rightToRight;
            layoutParams.topToTop = layout.topToTop;
            layoutParams.topToBottom = layout.topToBottom;
            layoutParams.bottomToTop = layout.bottomToTop;
            layoutParams.bottomToBottom = layout.bottomToBottom;
            layoutParams.baselineToBaseline = layout.baselineToBaseline;
            layoutParams.startToEnd = layout.startToEnd;
            layoutParams.startToStart = layout.startToStart;
            layoutParams.endToStart = layout.endToStart;
            layoutParams.endToEnd = layout.endToEnd;
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = layout.leftMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = layout.rightMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = layout.topMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = layout.bottomMargin;
            layoutParams.goneStartMargin = layout.goneStartMargin;
            layoutParams.goneEndMargin = layout.goneEndMargin;
            layoutParams.goneTopMargin = layout.goneTopMargin;
            layoutParams.goneBottomMargin = layout.goneBottomMargin;
            layoutParams.horizontalBias = layout.horizontalBias;
            layoutParams.verticalBias = layout.verticalBias;
            layoutParams.circleConstraint = layout.circleConstraint;
            layoutParams.circleRadius = layout.circleRadius;
            Layout layout2 = this.layout;
            layoutParams.circleAngle = layout2.circleAngle;
            layoutParams.dimensionRatio = layout2.dimensionRatio;
            layoutParams.editorAbsoluteX = layout2.editorAbsoluteX;
            layoutParams.editorAbsoluteY = layout2.editorAbsoluteY;
            layoutParams.verticalWeight = layout2.verticalWeight;
            layoutParams.horizontalWeight = layout2.horizontalWeight;
            layoutParams.verticalChainStyle = layout2.verticalChainStyle;
            layoutParams.horizontalChainStyle = layout2.horizontalChainStyle;
            layoutParams.constrainedWidth = layout2.constrainedWidth;
            layoutParams.constrainedHeight = layout2.constrainedHeight;
            layoutParams.matchConstraintDefaultWidth = layout2.widthDefault;
            layoutParams.matchConstraintDefaultHeight = layout2.heightDefault;
            layoutParams.matchConstraintMaxWidth = layout2.widthMax;
            layoutParams.matchConstraintMaxHeight = layout2.heightMax;
            layoutParams.matchConstraintMinWidth = layout2.widthMin;
            layoutParams.matchConstraintMinHeight = layout2.heightMin;
            layoutParams.matchConstraintPercentWidth = layout2.widthPercent;
            layoutParams.matchConstraintPercentHeight = layout2.heightPercent;
            layoutParams.orientation = layout2.orientation;
            layoutParams.guidePercent = layout2.guidePercent;
            layoutParams.guideBegin = layout2.guideBegin;
            layoutParams.guideEnd = layout2.guideEnd;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = layout2.mWidth;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = layout2.mHeight;
            String str = layout2.mConstraintTag;
            if (str != null) {
                layoutParams.constraintTag = str;
            }
            layoutParams.setMarginStart(this.layout.startMargin);
            layoutParams.setMarginEnd(this.layout.endMargin);
            layoutParams.validate();
        }

        public Object clone() throws CloneNotSupportedException {
            Constraint constraint = new Constraint();
            Layout layout = constraint.layout;
            Layout layout2 = this.layout;
            Objects.requireNonNull(layout);
            layout.mIsGuideline = layout2.mIsGuideline;
            layout.mWidth = layout2.mWidth;
            layout.mApply = layout2.mApply;
            layout.mHeight = layout2.mHeight;
            layout.guideBegin = layout2.guideBegin;
            layout.guideEnd = layout2.guideEnd;
            layout.guidePercent = layout2.guidePercent;
            layout.leftToLeft = layout2.leftToLeft;
            layout.leftToRight = layout2.leftToRight;
            layout.rightToLeft = layout2.rightToLeft;
            layout.rightToRight = layout2.rightToRight;
            layout.topToTop = layout2.topToTop;
            layout.topToBottom = layout2.topToBottom;
            layout.bottomToTop = layout2.bottomToTop;
            layout.bottomToBottom = layout2.bottomToBottom;
            layout.baselineToBaseline = layout2.baselineToBaseline;
            layout.startToEnd = layout2.startToEnd;
            layout.startToStart = layout2.startToStart;
            layout.endToStart = layout2.endToStart;
            layout.endToEnd = layout2.endToEnd;
            layout.horizontalBias = layout2.horizontalBias;
            layout.verticalBias = layout2.verticalBias;
            layout.dimensionRatio = layout2.dimensionRatio;
            layout.circleConstraint = layout2.circleConstraint;
            layout.circleRadius = layout2.circleRadius;
            layout.circleAngle = layout2.circleAngle;
            layout.editorAbsoluteX = layout2.editorAbsoluteX;
            layout.editorAbsoluteY = layout2.editorAbsoluteY;
            layout.orientation = layout2.orientation;
            layout.leftMargin = layout2.leftMargin;
            layout.rightMargin = layout2.rightMargin;
            layout.topMargin = layout2.topMargin;
            layout.bottomMargin = layout2.bottomMargin;
            layout.endMargin = layout2.endMargin;
            layout.startMargin = layout2.startMargin;
            layout.goneLeftMargin = layout2.goneLeftMargin;
            layout.goneTopMargin = layout2.goneTopMargin;
            layout.goneRightMargin = layout2.goneRightMargin;
            layout.goneBottomMargin = layout2.goneBottomMargin;
            layout.goneEndMargin = layout2.goneEndMargin;
            layout.goneStartMargin = layout2.goneStartMargin;
            layout.verticalWeight = layout2.verticalWeight;
            layout.horizontalWeight = layout2.horizontalWeight;
            layout.horizontalChainStyle = layout2.horizontalChainStyle;
            layout.verticalChainStyle = layout2.verticalChainStyle;
            layout.widthDefault = layout2.widthDefault;
            layout.heightDefault = layout2.heightDefault;
            layout.widthMax = layout2.widthMax;
            layout.heightMax = layout2.heightMax;
            layout.widthMin = layout2.widthMin;
            layout.heightMin = layout2.heightMin;
            layout.widthPercent = layout2.widthPercent;
            layout.heightPercent = layout2.heightPercent;
            layout.mBarrierDirection = layout2.mBarrierDirection;
            layout.mBarrierMargin = layout2.mBarrierMargin;
            layout.mHelperType = layout2.mHelperType;
            layout.mConstraintTag = layout2.mConstraintTag;
            int[] iArr = layout2.mReferenceIds;
            if (iArr != null) {
                layout.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            } else {
                layout.mReferenceIds = null;
            }
            layout.mReferenceIdString = layout2.mReferenceIdString;
            layout.constrainedWidth = layout2.constrainedWidth;
            layout.constrainedHeight = layout2.constrainedHeight;
            layout.mBarrierAllowsGoneWidgets = layout2.mBarrierAllowsGoneWidgets;
            Motion motion = constraint.motion;
            Motion motion2 = this.motion;
            Objects.requireNonNull(motion);
            motion.mApply = motion2.mApply;
            motion.mAnimateRelativeTo = motion2.mAnimateRelativeTo;
            motion.mTransitionEasing = motion2.mTransitionEasing;
            motion.mPathMotionArc = motion2.mPathMotionArc;
            motion.mDrawPath = motion2.mDrawPath;
            motion.mPathRotate = motion2.mPathRotate;
            motion.mMotionStagger = motion2.mMotionStagger;
            PropertySet propertySet = constraint.propertySet;
            PropertySet propertySet2 = this.propertySet;
            Objects.requireNonNull(propertySet);
            propertySet.mApply = propertySet2.mApply;
            propertySet.visibility = propertySet2.visibility;
            propertySet.alpha = propertySet2.alpha;
            propertySet.mProgress = propertySet2.mProgress;
            propertySet.mVisibilityMode = propertySet2.mVisibilityMode;
            Transform transform = constraint.transform;
            Transform transform2 = this.transform;
            Objects.requireNonNull(transform);
            transform.mApply = transform2.mApply;
            transform.rotation = transform2.rotation;
            transform.rotationX = transform2.rotationX;
            transform.rotationY = transform2.rotationY;
            transform.scaleX = transform2.scaleX;
            transform.scaleY = transform2.scaleY;
            transform.transformPivotX = transform2.transformPivotX;
            transform.transformPivotY = transform2.transformPivotY;
            transform.translationX = transform2.translationX;
            transform.translationY = transform2.translationY;
            transform.translationZ = transform2.translationZ;
            transform.applyElevation = transform2.applyElevation;
            transform.elevation = transform2.elevation;
            constraint.mViewId = this.mViewId;
            return constraint;
        }

        public final void fillFrom(int i, ConstraintLayout.LayoutParams layoutParams) {
            this.mViewId = i;
            Layout layout = this.layout;
            layout.leftToLeft = layoutParams.leftToLeft;
            layout.leftToRight = layoutParams.leftToRight;
            layout.rightToLeft = layoutParams.rightToLeft;
            layout.rightToRight = layoutParams.rightToRight;
            layout.topToTop = layoutParams.topToTop;
            layout.topToBottom = layoutParams.topToBottom;
            layout.bottomToTop = layoutParams.bottomToTop;
            layout.bottomToBottom = layoutParams.bottomToBottom;
            layout.baselineToBaseline = layoutParams.baselineToBaseline;
            layout.startToEnd = layoutParams.startToEnd;
            layout.startToStart = layoutParams.startToStart;
            layout.endToStart = layoutParams.endToStart;
            layout.endToEnd = layoutParams.endToEnd;
            layout.horizontalBias = layoutParams.horizontalBias;
            layout.verticalBias = layoutParams.verticalBias;
            layout.dimensionRatio = layoutParams.dimensionRatio;
            layout.circleConstraint = layoutParams.circleConstraint;
            layout.circleRadius = layoutParams.circleRadius;
            layout.circleAngle = layoutParams.circleAngle;
            layout.editorAbsoluteX = layoutParams.editorAbsoluteX;
            layout.editorAbsoluteY = layoutParams.editorAbsoluteY;
            layout.orientation = layoutParams.orientation;
            layout.guidePercent = layoutParams.guidePercent;
            layout.guideBegin = layoutParams.guideBegin;
            layout.guideEnd = layoutParams.guideEnd;
            Layout layout2 = this.layout;
            layout2.mWidth = ((ViewGroup.MarginLayoutParams) layoutParams).width;
            layout2.mHeight = ((ViewGroup.MarginLayoutParams) layoutParams).height;
            layout2.leftMargin = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            layout2.rightMargin = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            layout2.topMargin = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            layout2.bottomMargin = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            layout2.verticalWeight = layoutParams.verticalWeight;
            layout2.horizontalWeight = layoutParams.horizontalWeight;
            layout2.verticalChainStyle = layoutParams.verticalChainStyle;
            layout2.horizontalChainStyle = layoutParams.horizontalChainStyle;
            layout2.constrainedWidth = layoutParams.constrainedWidth;
            layout2.constrainedHeight = layoutParams.constrainedHeight;
            layout2.widthDefault = layoutParams.matchConstraintDefaultWidth;
            layout2.heightDefault = layoutParams.matchConstraintDefaultHeight;
            layout2.widthMax = layoutParams.matchConstraintMaxWidth;
            layout2.heightMax = layoutParams.matchConstraintMaxHeight;
            layout2.widthMin = layoutParams.matchConstraintMinWidth;
            layout2.heightMin = layoutParams.matchConstraintMinHeight;
            layout2.widthPercent = layoutParams.matchConstraintPercentWidth;
            layout2.heightPercent = layoutParams.matchConstraintPercentHeight;
            layout2.mConstraintTag = layoutParams.constraintTag;
            layout2.goneTopMargin = layoutParams.goneTopMargin;
            layout2.goneBottomMargin = layoutParams.goneBottomMargin;
            layout2.goneLeftMargin = layoutParams.goneLeftMargin;
            layout2.goneRightMargin = layoutParams.goneRightMargin;
            Layout layout3 = this.layout;
            layout3.goneStartMargin = layoutParams.goneStartMargin;
            layout3.goneEndMargin = layoutParams.goneEndMargin;
            layout3.endMargin = layoutParams.getMarginEnd();
            this.layout.startMargin = layoutParams.getMarginStart();
        }

        public final void fillFromConstraints(int i, Constraints.LayoutParams layoutParams) {
            fillFrom(i, layoutParams);
            this.propertySet.alpha = layoutParams.alpha;
            Transform transform = this.transform;
            transform.rotation = layoutParams.rotation;
            transform.rotationX = layoutParams.rotationX;
            transform.rotationY = layoutParams.rotationY;
            transform.scaleX = layoutParams.scaleX;
            transform.scaleY = layoutParams.scaleY;
            transform.transformPivotX = layoutParams.transformPivotX;
            transform.transformPivotY = layoutParams.transformPivotY;
            transform.translationX = layoutParams.translationX;
            transform.translationY = layoutParams.translationY;
            transform.translationZ = layoutParams.translationZ;
            transform.elevation = layoutParams.elevation;
            transform.applyElevation = layoutParams.applyElevation;
        }
    }

    /* loaded from: classes.dex */
    public static class Layout {
        public static SparseIntArray mapToConstant;
        public String mConstraintTag;
        public int mHeight;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public boolean mIsGuideline = false;
        public boolean mApply = false;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int topToTop = -1;
        public int topToBottom = -1;
        public int bottomToTop = -1;
        public int bottomToBottom = -1;
        public int baselineToBaseline = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int endToStart = -1;
        public int endToEnd = -1;
        public float horizontalBias = 0.5f;
        public float verticalBias = 0.5f;
        public String dimensionRatio = null;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public float circleAngle = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int orientation = -1;
        public int leftMargin = -1;
        public int rightMargin = -1;
        public int topMargin = -1;
        public int bottomMargin = -1;
        public int endMargin = -1;
        public int startMargin = -1;
        public int goneLeftMargin = -1;
        public int goneTopMargin = -1;
        public int goneRightMargin = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneStartMargin = -1;
        public float verticalWeight = -1.0f;
        public float horizontalWeight = -1.0f;
        public int horizontalChainStyle = 0;
        public int verticalChainStyle = 0;
        public int widthDefault = 0;
        public int heightDefault = 0;
        public int widthMax = -1;
        public int heightMax = -1;
        public int widthMin = -1;
        public int heightMin = -1;
        public float widthPercent = 1.0f;
        public float heightPercent = 1.0f;
        public int mBarrierDirection = -1;
        public int mBarrierMargin = 0;
        public int mHelperType = -1;
        public boolean constrainedWidth = false;
        public boolean constrainedHeight = false;
        public boolean mBarrierAllowsGoneWidgets = true;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(38, 24);
            mapToConstant.append(39, 25);
            mapToConstant.append(41, 28);
            mapToConstant.append(42, 29);
            mapToConstant.append(47, 35);
            mapToConstant.append(46, 34);
            mapToConstant.append(20, 4);
            mapToConstant.append(19, 3);
            mapToConstant.append(17, 1);
            mapToConstant.append(55, 6);
            mapToConstant.append(56, 7);
            mapToConstant.append(27, 17);
            mapToConstant.append(28, 18);
            mapToConstant.append(29, 19);
            mapToConstant.append(0, 26);
            mapToConstant.append(43, 31);
            mapToConstant.append(44, 32);
            mapToConstant.append(26, 10);
            mapToConstant.append(25, 9);
            mapToConstant.append(59, 13);
            mapToConstant.append(62, 16);
            mapToConstant.append(60, 14);
            mapToConstant.append(57, 11);
            mapToConstant.append(61, 15);
            mapToConstant.append(58, 12);
            mapToConstant.append(50, 38);
            mapToConstant.append(36, 37);
            mapToConstant.append(35, 39);
            mapToConstant.append(49, 40);
            mapToConstant.append(34, 20);
            mapToConstant.append(48, 36);
            mapToConstant.append(24, 5);
            mapToConstant.append(37, 76);
            mapToConstant.append(45, 76);
            mapToConstant.append(40, 76);
            mapToConstant.append(18, 76);
            mapToConstant.append(16, 76);
            mapToConstant.append(3, 23);
            mapToConstant.append(5, 27);
            mapToConstant.append(7, 30);
            mapToConstant.append(8, 8);
            mapToConstant.append(4, 33);
            mapToConstant.append(6, 2);
            mapToConstant.append(1, 22);
            mapToConstant.append(2, 21);
            mapToConstant.append(21, 61);
            mapToConstant.append(23, 62);
            mapToConstant.append(22, 63);
            mapToConstant.append(54, 69);
            mapToConstant.append(33, 70);
            mapToConstant.append(12, 71);
            mapToConstant.append(10, 72);
            mapToConstant.append(11, 73);
            mapToConstant.append(13, 74);
            mapToConstant.append(9, 75);
        }

        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Layout);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                int i2 = mapToConstant.get(index);
                if (i2 == 80) {
                    this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                } else if (i2 != 81) {
                    switch (i2) {
                        case 1:
                            int i3 = this.baselineToBaseline;
                            int[] iArr = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId = obtainStyledAttributes.getResourceId(index, i3);
                            if (resourceId == -1) {
                                resourceId = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.baselineToBaseline = resourceId;
                            continue;
                        case 2:
                            this.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.bottomMargin);
                            continue;
                        case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                            int i4 = this.bottomToBottom;
                            int[] iArr2 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId2 = obtainStyledAttributes.getResourceId(index, i4);
                            if (resourceId2 == -1) {
                                resourceId2 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.bottomToBottom = resourceId2;
                            continue;
                        case 4:
                            int i5 = this.bottomToTop;
                            int[] iArr3 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId3 = obtainStyledAttributes.getResourceId(index, i5);
                            if (resourceId3 == -1) {
                                resourceId3 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.bottomToTop = resourceId3;
                            continue;
                        case 5:
                            this.dimensionRatio = obtainStyledAttributes.getString(index);
                            continue;
                        case 6:
                            this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                            continue;
                        case 7:
                            this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                            continue;
                        case 8:
                            this.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.endMargin);
                            continue;
                        case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                            int i6 = this.endToEnd;
                            int[] iArr4 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId4 = obtainStyledAttributes.getResourceId(index, i6);
                            if (resourceId4 == -1) {
                                resourceId4 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.endToEnd = resourceId4;
                            continue;
                        case 10:
                            int i7 = this.endToStart;
                            int[] iArr5 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId5 = obtainStyledAttributes.getResourceId(index, i7);
                            if (resourceId5 == -1) {
                                resourceId5 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.endToStart = resourceId5;
                            continue;
                        case 11:
                            this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                            continue;
                        case 12:
                            this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                            continue;
                        case 13:
                            this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                            continue;
                        case 14:
                            this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                            continue;
                        case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                            this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                            continue;
                        case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                            this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                            continue;
                        case 17:
                            this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                            continue;
                        case 18:
                            this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                            continue;
                        case 19:
                            this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                            continue;
                        case 20:
                            this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                            continue;
                        case 21:
                            this.mHeight = obtainStyledAttributes.getLayoutDimension(index, this.mHeight);
                            continue;
                        case 22:
                            this.mWidth = obtainStyledAttributes.getLayoutDimension(index, this.mWidth);
                            continue;
                        case 23:
                            this.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.leftMargin);
                            continue;
                        case 24:
                            int i8 = this.leftToLeft;
                            int[] iArr6 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId6 = obtainStyledAttributes.getResourceId(index, i8);
                            if (resourceId6 == -1) {
                                resourceId6 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.leftToLeft = resourceId6;
                            continue;
                        case 25:
                            int i9 = this.leftToRight;
                            int[] iArr7 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId7 = obtainStyledAttributes.getResourceId(index, i9);
                            if (resourceId7 == -1) {
                                resourceId7 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.leftToRight = resourceId7;
                            continue;
                        case 26:
                            this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                            continue;
                        case 27:
                            this.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.rightMargin);
                            continue;
                        case 28:
                            int i10 = this.rightToLeft;
                            int[] iArr8 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId8 = obtainStyledAttributes.getResourceId(index, i10);
                            if (resourceId8 == -1) {
                                resourceId8 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.rightToLeft = resourceId8;
                            continue;
                        case 29:
                            int i11 = this.rightToRight;
                            int[] iArr9 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId9 = obtainStyledAttributes.getResourceId(index, i11);
                            if (resourceId9 == -1) {
                                resourceId9 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.rightToRight = resourceId9;
                            continue;
                        case 30:
                            this.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.startMargin);
                            continue;
                        case 31:
                            int i12 = this.startToEnd;
                            int[] iArr10 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId10 = obtainStyledAttributes.getResourceId(index, i12);
                            if (resourceId10 == -1) {
                                resourceId10 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.startToEnd = resourceId10;
                            continue;
                        case WebSocketProtocol.B0_FLAG_RSV2 /* 32 */:
                            int i13 = this.startToStart;
                            int[] iArr11 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId11 = obtainStyledAttributes.getResourceId(index, i13);
                            if (resourceId11 == -1) {
                                resourceId11 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.startToStart = resourceId11;
                            continue;
                        case 33:
                            this.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.topMargin);
                            continue;
                        case 34:
                            int i14 = this.topToBottom;
                            int[] iArr12 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId12 = obtainStyledAttributes.getResourceId(index, i14);
                            if (resourceId12 == -1) {
                                resourceId12 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.topToBottom = resourceId12;
                            continue;
                        case 35:
                            int i15 = this.topToTop;
                            int[] iArr13 = ConstraintSet.VISIBILITY_FLAGS;
                            int resourceId13 = obtainStyledAttributes.getResourceId(index, i15);
                            if (resourceId13 == -1) {
                                resourceId13 = obtainStyledAttributes.getInt(index, -1);
                            }
                            this.topToTop = resourceId13;
                            continue;
                        case 36:
                            this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                            continue;
                        case 37:
                            this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                            continue;
                        case 38:
                            this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                            continue;
                        case 39:
                            this.horizontalChainStyle = obtainStyledAttributes.getInt(index, this.horizontalChainStyle);
                            continue;
                        case 40:
                            this.verticalChainStyle = obtainStyledAttributes.getInt(index, this.verticalChainStyle);
                            continue;
                        default:
                            switch (i2) {
                                case 54:
                                    this.widthDefault = obtainStyledAttributes.getInt(index, this.widthDefault);
                                    continue;
                                case 55:
                                    this.heightDefault = obtainStyledAttributes.getInt(index, this.heightDefault);
                                    continue;
                                case 56:
                                    this.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMax);
                                    continue;
                                case 57:
                                    this.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMax);
                                    continue;
                                case 58:
                                    this.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMin);
                                    continue;
                                case 59:
                                    this.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMin);
                                    continue;
                                default:
                                    switch (i2) {
                                        case 61:
                                            int i16 = this.circleConstraint;
                                            int[] iArr14 = ConstraintSet.VISIBILITY_FLAGS;
                                            int resourceId14 = obtainStyledAttributes.getResourceId(index, i16);
                                            if (resourceId14 == -1) {
                                                resourceId14 = obtainStyledAttributes.getInt(index, -1);
                                            }
                                            this.circleConstraint = resourceId14;
                                            continue;
                                        case 62:
                                            this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                                            continue;
                                        case 63:
                                            this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle);
                                            continue;
                                        default:
                                            switch (i2) {
                                                case 69:
                                                    this.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                                    continue;
                                                case 70:
                                                    this.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                                    continue;
                                                case 71:
                                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                    continue;
                                                case 72:
                                                    this.mBarrierDirection = obtainStyledAttributes.getInt(index, this.mBarrierDirection);
                                                    continue;
                                                case 73:
                                                    this.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.mBarrierMargin);
                                                    continue;
                                                case 74:
                                                    this.mReferenceIdString = obtainStyledAttributes.getString(index);
                                                    continue;
                                                case 75:
                                                    this.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, this.mBarrierAllowsGoneWidgets);
                                                    continue;
                                                case 76:
                                                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("unused attribute 0x");
                                                    outline13.append(Integer.toHexString(index));
                                                    outline13.append("   ");
                                                    outline13.append(mapToConstant.get(index));
                                                    Log.w("ConstraintSet", outline13.toString());
                                                    continue;
                                                case 77:
                                                    this.mConstraintTag = obtainStyledAttributes.getString(index);
                                                    continue;
                                                default:
                                                    StringBuilder outline132 = GeneratedOutlineSupport.outline13("Unknown attribute 0x");
                                                    outline132.append(Integer.toHexString(index));
                                                    outline132.append("   ");
                                                    outline132.append(mapToConstant.get(index));
                                                    Log.w("ConstraintSet", outline132.toString());
                                                    continue;
                                                    continue;
                                                    continue;
                                                    continue;
                                            }
                                    }
                            }
                    }
                } else {
                    this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* loaded from: classes.dex */
    public static class Motion {
        public static SparseIntArray mapToConstant;
        public boolean mApply = false;
        public int mAnimateRelativeTo = -1;
        public String mTransitionEasing = null;
        public int mPathMotionArc = -1;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public float mPathRotate = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(2, 1);
            mapToConstant.append(4, 2);
            mapToConstant.append(5, 3);
            mapToConstant.append(1, 4);
            mapToConstant.append(0, 5);
            mapToConstant.append(3, 6);
        }

        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Motion);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (mapToConstant.get(index)) {
                    case 1:
                        this.mPathRotate = obtainStyledAttributes.getFloat(index, this.mPathRotate);
                        break;
                    case 2:
                        this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
                        break;
                    case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                        if (obtainStyledAttributes.peekValue(index).type == 3) {
                            this.mTransitionEasing = obtainStyledAttributes.getString(index);
                            break;
                        } else {
                            this.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                            break;
                        }
                    case 4:
                        this.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        int i2 = this.mAnimateRelativeTo;
                        int[] iArr = ConstraintSet.VISIBILITY_FLAGS;
                        int resourceId = obtainStyledAttributes.getResourceId(index, i2);
                        if (resourceId == -1) {
                            resourceId = obtainStyledAttributes.getInt(index, -1);
                        }
                        this.mAnimateRelativeTo = resourceId;
                        break;
                    case 6:
                        this.mMotionStagger = obtainStyledAttributes.getFloat(index, this.mMotionStagger);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* loaded from: classes.dex */
    public static class PropertySet {
        public boolean mApply = false;
        public int visibility = 0;
        public int mVisibilityMode = 0;
        public float alpha = 1.0f;
        public float mProgress = Float.NaN;

        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PropertySet);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 1) {
                    this.alpha = obtainStyledAttributes.getFloat(index, this.alpha);
                } else if (index == 0) {
                    int i2 = obtainStyledAttributes.getInt(index, this.visibility);
                    this.visibility = i2;
                    int[] iArr = ConstraintSet.VISIBILITY_FLAGS;
                    this.visibility = ConstraintSet.VISIBILITY_FLAGS[i2];
                } else if (index == 4) {
                    this.mVisibilityMode = obtainStyledAttributes.getInt(index, this.mVisibilityMode);
                } else if (index == 3) {
                    this.mProgress = obtainStyledAttributes.getFloat(index, this.mProgress);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* loaded from: classes.dex */
    public static class Transform {
        public static SparseIntArray mapToConstant;
        public boolean mApply = false;
        public float rotation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float transformPivotX = Float.NaN;
        public float transformPivotY = Float.NaN;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;
        public boolean applyElevation = false;
        public float elevation = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(6, 1);
            mapToConstant.append(7, 2);
            mapToConstant.append(8, 3);
            mapToConstant.append(4, 4);
            mapToConstant.append(5, 5);
            mapToConstant.append(0, 6);
            mapToConstant.append(1, 7);
            mapToConstant.append(2, 8);
            mapToConstant.append(3, 9);
            mapToConstant.append(9, 10);
            mapToConstant.append(10, 11);
        }

        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Transform);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (mapToConstant.get(index)) {
                    case 1:
                        this.rotation = obtainStyledAttributes.getFloat(index, this.rotation);
                        break;
                    case 2:
                        this.rotationX = obtainStyledAttributes.getFloat(index, this.rotationX);
                        break;
                    case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                        this.rotationY = obtainStyledAttributes.getFloat(index, this.rotationY);
                        break;
                    case 4:
                        this.scaleX = obtainStyledAttributes.getFloat(index, this.scaleX);
                        break;
                    case 5:
                        this.scaleY = obtainStyledAttributes.getFloat(index, this.scaleY);
                        break;
                    case 6:
                        this.transformPivotX = obtainStyledAttributes.getDimension(index, this.transformPivotX);
                        break;
                    case 7:
                        this.transformPivotY = obtainStyledAttributes.getDimension(index, this.transformPivotY);
                        break;
                    case 8:
                        this.translationX = obtainStyledAttributes.getDimension(index, this.translationX);
                        break;
                    case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                        this.translationY = obtainStyledAttributes.getDimension(index, this.translationY);
                        break;
                    case 10:
                        this.translationZ = obtainStyledAttributes.getDimension(index, this.translationZ);
                        break;
                    case 11:
                        this.applyElevation = true;
                        this.elevation = obtainStyledAttributes.getDimension(index, this.elevation);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        mapToConstant = sparseIntArray;
        sparseIntArray.append(76, 25);
        mapToConstant.append(77, 26);
        mapToConstant.append(79, 29);
        mapToConstant.append(80, 30);
        mapToConstant.append(86, 36);
        mapToConstant.append(85, 35);
        mapToConstant.append(58, 4);
        mapToConstant.append(57, 3);
        mapToConstant.append(55, 1);
        mapToConstant.append(94, 6);
        mapToConstant.append(95, 7);
        mapToConstant.append(65, 17);
        mapToConstant.append(66, 18);
        mapToConstant.append(67, 19);
        mapToConstant.append(0, 27);
        mapToConstant.append(81, 32);
        mapToConstant.append(82, 33);
        mapToConstant.append(64, 10);
        mapToConstant.append(63, 9);
        mapToConstant.append(98, 13);
        mapToConstant.append(101, 16);
        mapToConstant.append(99, 14);
        mapToConstant.append(96, 11);
        mapToConstant.append(100, 15);
        mapToConstant.append(97, 12);
        mapToConstant.append(89, 40);
        mapToConstant.append(74, 39);
        mapToConstant.append(73, 41);
        mapToConstant.append(88, 42);
        mapToConstant.append(72, 20);
        mapToConstant.append(87, 37);
        mapToConstant.append(62, 5);
        mapToConstant.append(75, 82);
        mapToConstant.append(84, 82);
        mapToConstant.append(78, 82);
        mapToConstant.append(56, 82);
        mapToConstant.append(54, 82);
        mapToConstant.append(5, 24);
        mapToConstant.append(7, 28);
        mapToConstant.append(23, 31);
        mapToConstant.append(24, 8);
        mapToConstant.append(6, 34);
        mapToConstant.append(8, 2);
        mapToConstant.append(3, 23);
        mapToConstant.append(4, 21);
        mapToConstant.append(2, 22);
        mapToConstant.append(13, 43);
        mapToConstant.append(26, 44);
        mapToConstant.append(21, 45);
        mapToConstant.append(22, 46);
        mapToConstant.append(20, 60);
        mapToConstant.append(18, 47);
        mapToConstant.append(19, 48);
        mapToConstant.append(14, 49);
        mapToConstant.append(15, 50);
        mapToConstant.append(16, 51);
        mapToConstant.append(17, 52);
        mapToConstant.append(25, 53);
        mapToConstant.append(90, 54);
        mapToConstant.append(68, 55);
        mapToConstant.append(91, 56);
        mapToConstant.append(69, 57);
        mapToConstant.append(92, 58);
        mapToConstant.append(70, 59);
        mapToConstant.append(59, 61);
        mapToConstant.append(61, 62);
        mapToConstant.append(60, 63);
        mapToConstant.append(27, 64);
        mapToConstant.append(106, 65);
        mapToConstant.append(33, 66);
        mapToConstant.append(107, 67);
        mapToConstant.append(103, 79);
        mapToConstant.append(1, 38);
        mapToConstant.append(102, 68);
        mapToConstant.append(93, 69);
        mapToConstant.append(71, 70);
        mapToConstant.append(31, 71);
        mapToConstant.append(29, 72);
        mapToConstant.append(30, 73);
        mapToConstant.append(32, 74);
        mapToConstant.append(28, 75);
        mapToConstant.append(104, 76);
        mapToConstant.append(83, 77);
        mapToConstant.append(108, 78);
        mapToConstant.append(53, 80);
        mapToConstant.append(52, 81);
    }

    public void applyToInternal(ConstraintLayout constraintLayout, boolean z) {
        NoSuchMethodException e;
        IllegalAccessException e2;
        InvocationTargetException e3;
        String str;
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.mConstraints.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("id unknown ");
                try {
                    str = childAt.getContext().getResources().getResourceEntryName(childAt.getId());
                } catch (Exception unused) {
                    str = "UNKNOWN";
                }
                outline13.append(str);
                Log.w("ConstraintSet", outline13.toString());
            } else if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else if (id != -1) {
                if (this.mConstraints.containsKey(Integer.valueOf(id))) {
                    hashSet.remove(Integer.valueOf(id));
                    Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                    if (childAt instanceof Barrier) {
                        constraint.layout.mHelperType = 1;
                    }
                    int i2 = constraint.layout.mHelperType;
                    if (i2 != -1 && i2 == 1) {
                        Barrier barrier = (Barrier) childAt;
                        barrier.setId(id);
                        barrier.setType(constraint.layout.mBarrierDirection);
                        barrier.setMargin(constraint.layout.mBarrierMargin);
                        barrier.setAllowsGoneWidget(constraint.layout.mBarrierAllowsGoneWidgets);
                        Layout layout = constraint.layout;
                        int[] iArr = layout.mReferenceIds;
                        if (iArr != null) {
                            barrier.setReferencedIds(iArr);
                        } else {
                            String str2 = layout.mReferenceIdString;
                            if (str2 != null) {
                                layout.mReferenceIds = convertReferenceString(barrier, str2);
                                barrier.setReferencedIds(constraint.layout.mReferenceIds);
                            }
                        }
                    }
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                    layoutParams.validate();
                    constraint.applyTo(layoutParams);
                    if (z) {
                        HashMap<String, ConstraintAttribute> hashMap = constraint.mCustomConstraints;
                        Class<?> cls = childAt.getClass();
                        Iterator<String> it = hashMap.keySet().iterator();
                        while (it.hasNext()) {
                            String next = it.next();
                            ConstraintAttribute constraintAttribute = hashMap.get(next);
                            String outline8 = GeneratedOutlineSupport.outline8("set", next);
                            try {
                                switch (SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(constraintAttribute.mType)) {
                                    case 0:
                                        it = it;
                                        cls.getMethod(outline8, Integer.TYPE).invoke(childAt, Integer.valueOf(constraintAttribute.mIntegerValue));
                                        break;
                                    case 1:
                                        it = it;
                                        cls.getMethod(outline8, Float.TYPE).invoke(childAt, Float.valueOf(constraintAttribute.mFloatValue));
                                        break;
                                    case 2:
                                        it = it;
                                        cls.getMethod(outline8, Integer.TYPE).invoke(childAt, Integer.valueOf(constraintAttribute.mColorValue));
                                        break;
                                    case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                                        it = it;
                                        Method method = cls.getMethod(outline8, Drawable.class);
                                        ColorDrawable colorDrawable = new ColorDrawable();
                                        colorDrawable.setColor(constraintAttribute.mColorValue);
                                        method.invoke(childAt, colorDrawable);
                                        break;
                                    case 4:
                                        it = it;
                                        cls.getMethod(outline8, CharSequence.class).invoke(childAt, constraintAttribute.mStringValue);
                                        break;
                                    case 5:
                                        it = it;
                                        cls.getMethod(outline8, Boolean.TYPE).invoke(childAt, Boolean.valueOf(constraintAttribute.mBooleanValue));
                                        break;
                                    case 6:
                                        it = it;
                                        try {
                                            cls.getMethod(outline8, Float.TYPE).invoke(childAt, Float.valueOf(constraintAttribute.mFloatValue));
                                        } catch (IllegalAccessException e4) {
                                            e2 = e4;
                                            StringBuilder outline16 = GeneratedOutlineSupport.outline16(" Custom Attribute \"", next, "\" not found on ");
                                            outline16.append(cls.getName());
                                            Log.e("TransitionLayout", outline16.toString());
                                            e2.printStackTrace();
                                            childCount = childCount;
                                            hashMap = hashMap;
                                        } catch (NoSuchMethodException e5) {
                                            e = e5;
                                            Log.e("TransitionLayout", e.getMessage());
                                            Log.e("TransitionLayout", " Custom Attribute \"" + next + "\" not found on " + cls.getName());
                                            StringBuilder sb = new StringBuilder();
                                            sb.append(cls.getName());
                                            sb.append(" must have a method ");
                                            sb.append(outline8);
                                            Log.e("TransitionLayout", sb.toString());
                                            childCount = childCount;
                                            hashMap = hashMap;
                                        } catch (InvocationTargetException e6) {
                                            e3 = e6;
                                            StringBuilder outline162 = GeneratedOutlineSupport.outline16(" Custom Attribute \"", next, "\" not found on ");
                                            outline162.append(cls.getName());
                                            Log.e("TransitionLayout", outline162.toString());
                                            e3.printStackTrace();
                                            childCount = childCount;
                                            hashMap = hashMap;
                                        }
                                    default:
                                        it = it;
                                        break;
                                }
                            } catch (IllegalAccessException e7) {
                                e2 = e7;
                                it = it;
                            } catch (NoSuchMethodException e8) {
                                e = e8;
                                it = it;
                            } catch (InvocationTargetException e9) {
                                e3 = e9;
                                it = it;
                            }
                            childCount = childCount;
                            hashMap = hashMap;
                        }
                    }
                    childAt.setLayoutParams(layoutParams);
                    PropertySet propertySet = constraint.propertySet;
                    if (propertySet.mVisibilityMode == 0) {
                        childAt.setVisibility(propertySet.visibility);
                    }
                    childAt.setAlpha(constraint.propertySet.alpha);
                    childAt.setRotation(constraint.transform.rotation);
                    childAt.setRotationX(constraint.transform.rotationX);
                    childAt.setRotationY(constraint.transform.rotationY);
                    childAt.setScaleX(constraint.transform.scaleX);
                    childAt.setScaleY(constraint.transform.scaleY);
                    if (!Float.isNaN(constraint.transform.transformPivotX)) {
                        childAt.setPivotX(constraint.transform.transformPivotX);
                    }
                    if (!Float.isNaN(constraint.transform.transformPivotY)) {
                        childAt.setPivotY(constraint.transform.transformPivotY);
                    }
                    childAt.setTranslationX(constraint.transform.translationX);
                    childAt.setTranslationY(constraint.transform.translationY);
                    childAt.setTranslationZ(constraint.transform.translationZ);
                    Transform transform = constraint.transform;
                    if (transform.applyElevation) {
                        childAt.setElevation(transform.elevation);
                    }
                } else {
                    childCount = childCount;
                    Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                }
            }
            childCount = childCount;
        }
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            Integer num = (Integer) it2.next();
            Constraint constraint2 = this.mConstraints.get(num);
            int i3 = constraint2.layout.mHelperType;
            if (i3 != -1 && i3 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                Layout layout2 = constraint2.layout;
                int[] iArr2 = layout2.mReferenceIds;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str3 = layout2.mReferenceIdString;
                    if (str3 != null) {
                        layout2.mReferenceIds = convertReferenceString(barrier2, str3);
                        barrier2.setReferencedIds(constraint2.layout.mReferenceIds);
                    }
                }
                barrier2.setType(constraint2.layout.mBarrierDirection);
                barrier2.setMargin(constraint2.layout.mBarrierMargin);
                ConstraintLayout.LayoutParams generateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.validateParams();
                constraint2.applyTo(generateDefaultLayoutParams);
                constraintLayout.addView(barrier2, generateDefaultLayoutParams);
            }
            if (constraint2.layout.mIsGuideline) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.LayoutParams generateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                constraint2.applyTo(generateDefaultLayoutParams2);
                constraintLayout.addView(guideline, generateDefaultLayoutParams2);
            }
        }
    }

    public void clone(ConstraintLayout constraintLayout) {
        NoSuchMethodException e;
        IllegalAccessException e2;
        InvocationTargetException e3;
        ConstraintSet constraintSet = this;
        int childCount = constraintLayout.getChildCount();
        constraintSet.mConstraints.clear();
        int i = 0;
        while (i < childCount) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (!constraintSet.mForceId || id != -1) {
                if (!constraintSet.mConstraints.containsKey(Integer.valueOf(id))) {
                    constraintSet.mConstraints.put(Integer.valueOf(id), new Constraint());
                }
                Constraint constraint = constraintSet.mConstraints.get(Integer.valueOf(id));
                HashMap<String, ConstraintAttribute> hashMap = constraintSet.mSavedAttributes;
                HashMap<String, ConstraintAttribute> hashMap2 = new HashMap<>();
                Class<?> cls = childAt.getClass();
                for (String str : hashMap.keySet()) {
                    ConstraintAttribute constraintAttribute = hashMap.get(str);
                    try {
                    } catch (IllegalAccessException e4) {
                        e2 = e4;
                    } catch (NoSuchMethodException e5) {
                        e = e5;
                    } catch (InvocationTargetException e6) {
                        e3 = e6;
                    }
                    if (str.equals("BackgroundColor")) {
                        hashMap2.put(str, new ConstraintAttribute(constraintAttribute, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                    } else {
                        try {
                            hashMap2.put(str, new ConstraintAttribute(constraintAttribute, cls.getMethod("getMap" + str, new Class[0]).invoke(childAt, new Object[0])));
                        } catch (IllegalAccessException e7) {
                            e2 = e7;
                            e2.printStackTrace();
                        } catch (NoSuchMethodException e8) {
                            e = e8;
                            e.printStackTrace();
                        } catch (InvocationTargetException e9) {
                            e3 = e9;
                            e3.printStackTrace();
                        }
                    }
                }
                constraint.mCustomConstraints = hashMap2;
                constraint.fillFrom(id, layoutParams);
                constraint.propertySet.visibility = childAt.getVisibility();
                constraint.propertySet.alpha = childAt.getAlpha();
                constraint.transform.rotation = childAt.getRotation();
                constraint.transform.rotationX = childAt.getRotationX();
                constraint.transform.rotationY = childAt.getRotationY();
                constraint.transform.scaleX = childAt.getScaleX();
                constraint.transform.scaleY = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (!(pivotX == 0.0d && pivotY == 0.0d)) {
                    Transform transform = constraint.transform;
                    transform.transformPivotX = pivotX;
                    transform.transformPivotY = pivotY;
                }
                constraint.transform.translationX = childAt.getTranslationX();
                constraint.transform.translationY = childAt.getTranslationY();
                constraint.transform.translationZ = childAt.getTranslationZ();
                Transform transform2 = constraint.transform;
                if (transform2.applyElevation) {
                    transform2.elevation = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    Layout layout = constraint.layout;
                    layout.mBarrierAllowsGoneWidgets = barrier.mBarrier.mAllowsGoneWidget;
                    layout.mReferenceIds = barrier.getReferencedIds();
                    constraint.layout.mBarrierDirection = barrier.getType();
                    constraint.layout.mBarrierMargin = barrier.getMargin();
                }
                i++;
                constraintSet = this;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public final int[] convertReferenceString(View view, String str) {
        int i;
        Object designInformation;
        String[] split = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[split.length];
        int i2 = 0;
        for (String str2 : split) {
            String trim = str2.trim();
            try {
                i = R$id.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i = 0;
            }
            if (i == 0) {
                i = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (designInformation = ((ConstraintLayout) view.getParent()).getDesignInformation(0, trim)) != null && (designInformation instanceof Integer)) {
                i = ((Integer) designInformation).intValue();
            }
            i2++;
            iArr[i2] = i;
        }
        return i2 != split.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    public final Constraint fillFromAttributeList(Context context, AttributeSet attributeSet) {
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Constraint);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (!(index == 1 || 23 == index || 24 == index)) {
                constraint.motion.mApply = true;
                constraint.layout.mApply = true;
                constraint.propertySet.mApply = true;
                constraint.transform.mApply = true;
            }
            switch (mapToConstant.get(index)) {
                case 1:
                    Layout layout = constraint.layout;
                    int resourceId = obtainStyledAttributes.getResourceId(index, layout.baselineToBaseline);
                    if (resourceId == -1) {
                        resourceId = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout.baselineToBaseline = resourceId;
                    break;
                case 2:
                    Layout layout2 = constraint.layout;
                    layout2.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout2.bottomMargin);
                    break;
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    Layout layout3 = constraint.layout;
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, layout3.bottomToBottom);
                    if (resourceId2 == -1) {
                        resourceId2 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout3.bottomToBottom = resourceId2;
                    break;
                case 4:
                    Layout layout4 = constraint.layout;
                    int resourceId3 = obtainStyledAttributes.getResourceId(index, layout4.bottomToTop);
                    if (resourceId3 == -1) {
                        resourceId3 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout4.bottomToTop = resourceId3;
                    break;
                case 5:
                    constraint.layout.dimensionRatio = obtainStyledAttributes.getString(index);
                    break;
                case 6:
                    Layout layout5 = constraint.layout;
                    layout5.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, layout5.editorAbsoluteX);
                    break;
                case 7:
                    Layout layout6 = constraint.layout;
                    layout6.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, layout6.editorAbsoluteY);
                    break;
                case 8:
                    Layout layout7 = constraint.layout;
                    layout7.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout7.endMargin);
                    break;
                case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                    Layout layout8 = constraint.layout;
                    int resourceId4 = obtainStyledAttributes.getResourceId(index, layout8.endToEnd);
                    if (resourceId4 == -1) {
                        resourceId4 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout8.endToEnd = resourceId4;
                    break;
                case 10:
                    Layout layout9 = constraint.layout;
                    int resourceId5 = obtainStyledAttributes.getResourceId(index, layout9.endToStart);
                    if (resourceId5 == -1) {
                        resourceId5 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout9.endToStart = resourceId5;
                    break;
                case 11:
                    Layout layout10 = constraint.layout;
                    layout10.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout10.goneBottomMargin);
                    break;
                case 12:
                    Layout layout11 = constraint.layout;
                    layout11.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout11.goneEndMargin);
                    break;
                case 13:
                    Layout layout12 = constraint.layout;
                    layout12.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout12.goneLeftMargin);
                    break;
                case 14:
                    Layout layout13 = constraint.layout;
                    layout13.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout13.goneRightMargin);
                    break;
                case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                    Layout layout14 = constraint.layout;
                    layout14.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout14.goneStartMargin);
                    break;
                case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                    Layout layout15 = constraint.layout;
                    layout15.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout15.goneTopMargin);
                    break;
                case 17:
                    Layout layout16 = constraint.layout;
                    layout16.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, layout16.guideBegin);
                    break;
                case 18:
                    Layout layout17 = constraint.layout;
                    layout17.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, layout17.guideEnd);
                    break;
                case 19:
                    Layout layout18 = constraint.layout;
                    layout18.guidePercent = obtainStyledAttributes.getFloat(index, layout18.guidePercent);
                    break;
                case 20:
                    Layout layout19 = constraint.layout;
                    layout19.horizontalBias = obtainStyledAttributes.getFloat(index, layout19.horizontalBias);
                    break;
                case 21:
                    Layout layout20 = constraint.layout;
                    layout20.mHeight = obtainStyledAttributes.getLayoutDimension(index, layout20.mHeight);
                    break;
                case 22:
                    PropertySet propertySet = constraint.propertySet;
                    propertySet.visibility = obtainStyledAttributes.getInt(index, propertySet.visibility);
                    PropertySet propertySet2 = constraint.propertySet;
                    propertySet2.visibility = VISIBILITY_FLAGS[propertySet2.visibility];
                    break;
                case 23:
                    Layout layout21 = constraint.layout;
                    layout21.mWidth = obtainStyledAttributes.getLayoutDimension(index, layout21.mWidth);
                    break;
                case 24:
                    Layout layout22 = constraint.layout;
                    layout22.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout22.leftMargin);
                    break;
                case 25:
                    Layout layout23 = constraint.layout;
                    int resourceId6 = obtainStyledAttributes.getResourceId(index, layout23.leftToLeft);
                    if (resourceId6 == -1) {
                        resourceId6 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout23.leftToLeft = resourceId6;
                    break;
                case 26:
                    Layout layout24 = constraint.layout;
                    int resourceId7 = obtainStyledAttributes.getResourceId(index, layout24.leftToRight);
                    if (resourceId7 == -1) {
                        resourceId7 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout24.leftToRight = resourceId7;
                    break;
                case 27:
                    Layout layout25 = constraint.layout;
                    layout25.orientation = obtainStyledAttributes.getInt(index, layout25.orientation);
                    break;
                case 28:
                    Layout layout26 = constraint.layout;
                    layout26.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout26.rightMargin);
                    break;
                case 29:
                    Layout layout27 = constraint.layout;
                    int resourceId8 = obtainStyledAttributes.getResourceId(index, layout27.rightToLeft);
                    if (resourceId8 == -1) {
                        resourceId8 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout27.rightToLeft = resourceId8;
                    break;
                case 30:
                    Layout layout28 = constraint.layout;
                    int resourceId9 = obtainStyledAttributes.getResourceId(index, layout28.rightToRight);
                    if (resourceId9 == -1) {
                        resourceId9 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout28.rightToRight = resourceId9;
                    break;
                case 31:
                    Layout layout29 = constraint.layout;
                    layout29.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout29.startMargin);
                    break;
                case WebSocketProtocol.B0_FLAG_RSV2 /* 32 */:
                    Layout layout30 = constraint.layout;
                    int resourceId10 = obtainStyledAttributes.getResourceId(index, layout30.startToEnd);
                    if (resourceId10 == -1) {
                        resourceId10 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout30.startToEnd = resourceId10;
                    break;
                case 33:
                    Layout layout31 = constraint.layout;
                    int resourceId11 = obtainStyledAttributes.getResourceId(index, layout31.startToStart);
                    if (resourceId11 == -1) {
                        resourceId11 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout31.startToStart = resourceId11;
                    break;
                case 34:
                    Layout layout32 = constraint.layout;
                    layout32.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout32.topMargin);
                    break;
                case 35:
                    Layout layout33 = constraint.layout;
                    int resourceId12 = obtainStyledAttributes.getResourceId(index, layout33.topToBottom);
                    if (resourceId12 == -1) {
                        resourceId12 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout33.topToBottom = resourceId12;
                    break;
                case 36:
                    Layout layout34 = constraint.layout;
                    int resourceId13 = obtainStyledAttributes.getResourceId(index, layout34.topToTop);
                    if (resourceId13 == -1) {
                        resourceId13 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout34.topToTop = resourceId13;
                    break;
                case 37:
                    Layout layout35 = constraint.layout;
                    layout35.verticalBias = obtainStyledAttributes.getFloat(index, layout35.verticalBias);
                    break;
                case 38:
                    constraint.mViewId = obtainStyledAttributes.getResourceId(index, constraint.mViewId);
                    break;
                case 39:
                    Layout layout36 = constraint.layout;
                    layout36.horizontalWeight = obtainStyledAttributes.getFloat(index, layout36.horizontalWeight);
                    break;
                case 40:
                    Layout layout37 = constraint.layout;
                    layout37.verticalWeight = obtainStyledAttributes.getFloat(index, layout37.verticalWeight);
                    break;
                case 41:
                    Layout layout38 = constraint.layout;
                    layout38.horizontalChainStyle = obtainStyledAttributes.getInt(index, layout38.horizontalChainStyle);
                    break;
                case 42:
                    Layout layout39 = constraint.layout;
                    layout39.verticalChainStyle = obtainStyledAttributes.getInt(index, layout39.verticalChainStyle);
                    break;
                case 43:
                    PropertySet propertySet3 = constraint.propertySet;
                    propertySet3.alpha = obtainStyledAttributes.getFloat(index, propertySet3.alpha);
                    break;
                case 44:
                    Transform transform = constraint.transform;
                    transform.applyElevation = true;
                    transform.elevation = obtainStyledAttributes.getDimension(index, transform.elevation);
                    break;
                case 45:
                    Transform transform2 = constraint.transform;
                    transform2.rotationX = obtainStyledAttributes.getFloat(index, transform2.rotationX);
                    break;
                case 46:
                    Transform transform3 = constraint.transform;
                    transform3.rotationY = obtainStyledAttributes.getFloat(index, transform3.rotationY);
                    break;
                case 47:
                    Transform transform4 = constraint.transform;
                    transform4.scaleX = obtainStyledAttributes.getFloat(index, transform4.scaleX);
                    break;
                case 48:
                    Transform transform5 = constraint.transform;
                    transform5.scaleY = obtainStyledAttributes.getFloat(index, transform5.scaleY);
                    break;
                case 49:
                    Transform transform6 = constraint.transform;
                    transform6.transformPivotX = obtainStyledAttributes.getDimension(index, transform6.transformPivotX);
                    break;
                case 50:
                    Transform transform7 = constraint.transform;
                    transform7.transformPivotY = obtainStyledAttributes.getDimension(index, transform7.transformPivotY);
                    break;
                case 51:
                    Transform transform8 = constraint.transform;
                    transform8.translationX = obtainStyledAttributes.getDimension(index, transform8.translationX);
                    break;
                case 52:
                    Transform transform9 = constraint.transform;
                    transform9.translationY = obtainStyledAttributes.getDimension(index, transform9.translationY);
                    break;
                case 53:
                    Transform transform10 = constraint.transform;
                    transform10.translationZ = obtainStyledAttributes.getDimension(index, transform10.translationZ);
                    break;
                case 54:
                    Layout layout40 = constraint.layout;
                    layout40.widthDefault = obtainStyledAttributes.getInt(index, layout40.widthDefault);
                    break;
                case 55:
                    Layout layout41 = constraint.layout;
                    layout41.heightDefault = obtainStyledAttributes.getInt(index, layout41.heightDefault);
                    break;
                case 56:
                    Layout layout42 = constraint.layout;
                    layout42.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, layout42.widthMax);
                    break;
                case 57:
                    Layout layout43 = constraint.layout;
                    layout43.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, layout43.heightMax);
                    break;
                case 58:
                    Layout layout44 = constraint.layout;
                    layout44.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, layout44.widthMin);
                    break;
                case 59:
                    Layout layout45 = constraint.layout;
                    layout45.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, layout45.heightMin);
                    break;
                case 60:
                    Transform transform11 = constraint.transform;
                    transform11.rotation = obtainStyledAttributes.getFloat(index, transform11.rotation);
                    break;
                case 61:
                    Layout layout46 = constraint.layout;
                    int resourceId14 = obtainStyledAttributes.getResourceId(index, layout46.circleConstraint);
                    if (resourceId14 == -1) {
                        resourceId14 = obtainStyledAttributes.getInt(index, -1);
                    }
                    layout46.circleConstraint = resourceId14;
                    break;
                case 62:
                    Layout layout47 = constraint.layout;
                    layout47.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, layout47.circleRadius);
                    break;
                case 63:
                    Layout layout48 = constraint.layout;
                    layout48.circleAngle = obtainStyledAttributes.getFloat(index, layout48.circleAngle);
                    break;
                case WebSocketProtocol.B0_FLAG_RSV1 /* 64 */:
                    Motion motion = constraint.motion;
                    int resourceId15 = obtainStyledAttributes.getResourceId(index, motion.mAnimateRelativeTo);
                    if (resourceId15 == -1) {
                        resourceId15 = obtainStyledAttributes.getInt(index, -1);
                    }
                    motion.mAnimateRelativeTo = resourceId15;
                    break;
                case 65:
                    if (obtainStyledAttributes.peekValue(index).type == 3) {
                        constraint.motion.mTransitionEasing = obtainStyledAttributes.getString(index);
                        break;
                    } else {
                        constraint.motion.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                        break;
                    }
                case 66:
                    constraint.motion.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                    break;
                case 67:
                    Motion motion2 = constraint.motion;
                    motion2.mPathRotate = obtainStyledAttributes.getFloat(index, motion2.mPathRotate);
                    break;
                case 68:
                    PropertySet propertySet4 = constraint.propertySet;
                    propertySet4.mProgress = obtainStyledAttributes.getFloat(index, propertySet4.mProgress);
                    break;
                case 69:
                    constraint.layout.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                    break;
                case 70:
                    constraint.layout.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    Layout layout49 = constraint.layout;
                    layout49.mBarrierDirection = obtainStyledAttributes.getInt(index, layout49.mBarrierDirection);
                    break;
                case 73:
                    Layout layout50 = constraint.layout;
                    layout50.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout50.mBarrierMargin);
                    break;
                case 74:
                    constraint.layout.mReferenceIdString = obtainStyledAttributes.getString(index);
                    break;
                case 75:
                    Layout layout51 = constraint.layout;
                    layout51.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, layout51.mBarrierAllowsGoneWidgets);
                    break;
                case 76:
                    Motion motion3 = constraint.motion;
                    motion3.mPathMotionArc = obtainStyledAttributes.getInt(index, motion3.mPathMotionArc);
                    break;
                case 77:
                    constraint.layout.mConstraintTag = obtainStyledAttributes.getString(index);
                    break;
                case 78:
                    PropertySet propertySet5 = constraint.propertySet;
                    propertySet5.mVisibilityMode = obtainStyledAttributes.getInt(index, propertySet5.mVisibilityMode);
                    break;
                case 79:
                    Motion motion4 = constraint.motion;
                    motion4.mMotionStagger = obtainStyledAttributes.getFloat(index, motion4.mMotionStagger);
                    break;
                case 80:
                    Layout layout52 = constraint.layout;
                    layout52.constrainedWidth = obtainStyledAttributes.getBoolean(index, layout52.constrainedWidth);
                    break;
                case 81:
                    Layout layout53 = constraint.layout;
                    layout53.constrainedHeight = obtainStyledAttributes.getBoolean(index, layout53.constrainedHeight);
                    break;
                case 82:
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("unused attribute 0x");
                    outline13.append(Integer.toHexString(index));
                    outline13.append("   ");
                    outline13.append(mapToConstant.get(index));
                    Log.w("ConstraintSet", outline13.toString());
                    break;
                default:
                    StringBuilder outline132 = GeneratedOutlineSupport.outline13("Unknown attribute 0x");
                    outline132.append(Integer.toHexString(index));
                    outline132.append("   ");
                    outline132.append(mapToConstant.get(index));
                    Log.w("ConstraintSet", outline132.toString());
                    break;
            }
        }
        obtainStyledAttributes.recycle();
        return constraint;
    }

    public void load(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    Constraint fillFromAttributeList = fillFromAttributeList(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        fillFromAttributeList.layout.mIsGuideline = true;
                    }
                    this.mConstraints.put(Integer.valueOf(fillFromAttributeList.mViewId), fillFromAttributeList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }
}
