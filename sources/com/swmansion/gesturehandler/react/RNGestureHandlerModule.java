package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.gesturehandler.FlingGestureHandler;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.LongPressGestureHandler;
import com.swmansion.gesturehandler.NativeViewGestureHandler;
import com.swmansion.gesturehandler.OnTouchEventListener;
import com.swmansion.gesturehandler.PanGestureHandler;
import com.swmansion.gesturehandler.PinchGestureHandler;
import com.swmansion.gesturehandler.RotationGestureDetector;
import com.swmansion.gesturehandler.RotationGestureHandler;
import com.swmansion.gesturehandler.TapGestureHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ReactModule(name = RNGestureHandlerModule.MODULE_NAME)
/* loaded from: classes.dex */
public class RNGestureHandlerModule extends ReactContextBaseJavaModule {
    private static final String KEY_DIRECTION = "direction";
    private static final String KEY_ENABLED = "enabled";
    private static final String KEY_HIT_SLOP = "hitSlop";
    private static final String KEY_HIT_SLOP_BOTTOM = "bottom";
    private static final String KEY_HIT_SLOP_HEIGHT = "height";
    private static final String KEY_HIT_SLOP_HORIZONTAL = "horizontal";
    private static final String KEY_HIT_SLOP_LEFT = "left";
    private static final String KEY_HIT_SLOP_RIGHT = "right";
    private static final String KEY_HIT_SLOP_TOP = "top";
    private static final String KEY_HIT_SLOP_VERTICAL = "vertical";
    private static final String KEY_HIT_SLOP_WIDTH = "width";
    private static final String KEY_LONG_PRESS_MAX_DIST = "maxDist";
    private static final String KEY_LONG_PRESS_MIN_DURATION_MS = "minDurationMs";
    private static final String KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION = "disallowInterruption";
    private static final String KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START = "shouldActivateOnStart";
    private static final String KEY_NUMBER_OF_POINTERS = "numberOfPointers";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_END = "activeOffsetXEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_START = "activeOffsetXStart";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_END = "activeOffsetYEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_START = "activeOffsetYStart";
    private static final String KEY_PAN_AVG_TOUCHES = "avgTouches";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_END = "failOffsetXEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_START = "failOffsetXStart";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_END = "failOffsetYEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_START = "failOffsetYStart";
    private static final String KEY_PAN_MAX_POINTERS = "maxPointers";
    private static final String KEY_PAN_MIN_DIST = "minDist";
    private static final String KEY_PAN_MIN_POINTERS = "minPointers";
    private static final String KEY_PAN_MIN_VELOCITY = "minVelocity";
    private static final String KEY_PAN_MIN_VELOCITY_X = "minVelocityX";
    private static final String KEY_PAN_MIN_VELOCITY_Y = "minVelocityY";
    private static final String KEY_SHOULD_CANCEL_WHEN_OUTSIDE = "shouldCancelWhenOutside";
    private static final String KEY_TAP_MAX_DELAY_MS = "maxDelayMs";
    private static final String KEY_TAP_MAX_DELTA_X = "maxDeltaX";
    private static final String KEY_TAP_MAX_DELTA_Y = "maxDeltaY";
    private static final String KEY_TAP_MAX_DIST = "maxDist";
    private static final String KEY_TAP_MAX_DURATION_MS = "maxDurationMs";
    private static final String KEY_TAP_MIN_POINTERS = "minPointers";
    private static final String KEY_TAP_NUMBER_OF_TAPS = "numberOfTaps";
    public static final String MODULE_NAME = "RNGestureHandlerModule";
    private OnTouchEventListener mEventListener = new AnonymousClass1();
    private HandlerFactory[] mHandlerFactories = {new NativeViewGestureHandlerFactory(null), new TapGestureHandlerFactory(null), new LongPressGestureHandlerFactory(null), new PanGestureHandlerFactory(null), new PinchGestureHandlerFactory(null), new RotationGestureHandlerFactory(null), new FlingGestureHandlerFactory(null)};
    private final RNGestureHandlerRegistry mRegistry = new RNGestureHandlerRegistry();
    private RNGestureHandlerInteractionManager mInteractionManager = new RNGestureHandlerInteractionManager();
    private List<RNGestureHandlerRootHelper> mRoots = new ArrayList();
    private List<Integer> mEnqueuedRootViewInit = new ArrayList();

    /* renamed from: com.swmansion.gesturehandler.react.RNGestureHandlerModule$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements OnTouchEventListener {
        public AnonymousClass1() {
        }
    }

    /* loaded from: classes.dex */
    public static class FlingGestureHandlerFactory extends HandlerFactory<FlingGestureHandler> {
        public FlingGestureHandlerFactory(AnonymousClass1 r1) {
            super(null);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(FlingGestureHandler flingGestureHandler, ReadableMap readableMap) {
            FlingGestureHandler flingGestureHandler2 = flingGestureHandler;
            super.configure(flingGestureHandler2, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS)) {
                flingGestureHandler2.mNumberOfPointersRequired = readableMap.getInt(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS);
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_DIRECTION)) {
                flingGestureHandler2.mDirection = readableMap.getInt(RNGestureHandlerModule.KEY_DIRECTION);
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public FlingGestureHandler create(Context context) {
            return new FlingGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(GestureHandler gestureHandler, WritableMap writableMap) {
            FlingGestureHandler flingGestureHandler = (FlingGestureHandler) gestureHandler;
            writableMap.putDouble(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS, flingGestureHandler.mNumberOfPointers);
            writableMap.putDouble("x", PixelUtil.toDIPFromPixel(flingGestureHandler.getLastRelativePositionX()));
            writableMap.putDouble("y", PixelUtil.toDIPFromPixel(flingGestureHandler.getLastRelativePositionY()));
            writableMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(flingGestureHandler.mLastX));
            writableMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(flingGestureHandler.mLastY));
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "FlingGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<FlingGestureHandler> getType() {
            return FlingGestureHandler.class;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class HandlerFactory<T extends GestureHandler> implements RNGestureHandlerEventDataExtractor<T> {
        public HandlerFactory(AnonymousClass1 r1) {
        }

        public void configure(final T t, ReadableMap readableMap) {
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE)) {
                t.mShouldCancelWhenOutside = readableMap.getBoolean(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE);
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_ENABLED)) {
                boolean z = readableMap.getBoolean(RNGestureHandlerModule.KEY_ENABLED);
                if (t.mView != null) {
                    UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.gesturehandler.GestureHandler.1
                        @Override // java.lang.Runnable
                        public void run() {
                            t.cancel();
                        }
                    });
                }
                t.mEnabled = z;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP)) {
                RNGestureHandlerModule.handleHitSlopProperty(t, readableMap);
            }
        }

        public abstract T create(Context context);

        public abstract String getName();

        public abstract Class<T> getType();
    }

    /* loaded from: classes.dex */
    public static class LongPressGestureHandlerFactory extends HandlerFactory<LongPressGestureHandler> {
        public LongPressGestureHandlerFactory(AnonymousClass1 r1) {
            super(null);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(LongPressGestureHandler longPressGestureHandler, ReadableMap readableMap) {
            LongPressGestureHandler longPressGestureHandler2 = longPressGestureHandler;
            super.configure(longPressGestureHandler2, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS)) {
                longPressGestureHandler2.mMinDurationMs = readableMap.getInt(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS);
            }
            if (readableMap.hasKey("maxDist")) {
                float pixelFromDIP = PixelUtil.toPixelFromDIP(readableMap.getDouble("maxDist"));
                longPressGestureHandler2.mMaxDistSq = pixelFromDIP * pixelFromDIP;
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public LongPressGestureHandler create(Context context) {
            return new LongPressGestureHandler(context);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(GestureHandler gestureHandler, WritableMap writableMap) {
            LongPressGestureHandler longPressGestureHandler = (LongPressGestureHandler) gestureHandler;
            writableMap.putDouble(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS, longPressGestureHandler.mNumberOfPointers);
            writableMap.putDouble("x", PixelUtil.toDIPFromPixel(longPressGestureHandler.getLastRelativePositionX()));
            writableMap.putDouble("y", PixelUtil.toDIPFromPixel(longPressGestureHandler.getLastRelativePositionY()));
            writableMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(longPressGestureHandler.mLastX));
            writableMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(longPressGestureHandler.mLastY));
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "LongPressGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<LongPressGestureHandler> getType() {
            return LongPressGestureHandler.class;
        }
    }

    /* loaded from: classes.dex */
    public static class NativeViewGestureHandlerFactory extends HandlerFactory<NativeViewGestureHandler> {
        public NativeViewGestureHandlerFactory(AnonymousClass1 r1) {
            super(null);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(NativeViewGestureHandler nativeViewGestureHandler, ReadableMap readableMap) {
            NativeViewGestureHandler nativeViewGestureHandler2 = nativeViewGestureHandler;
            super.configure(nativeViewGestureHandler2, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START)) {
                nativeViewGestureHandler2.mShouldActivateOnStart = readableMap.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START);
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION)) {
                nativeViewGestureHandler2.mDisallowInterruption = readableMap.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION);
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public NativeViewGestureHandler create(Context context) {
            return new NativeViewGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(GestureHandler gestureHandler, WritableMap writableMap) {
            NativeViewGestureHandler nativeViewGestureHandler = (NativeViewGestureHandler) gestureHandler;
            writableMap.putDouble(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS, nativeViewGestureHandler.mNumberOfPointers);
            writableMap.putBoolean("pointerInside", nativeViewGestureHandler.mWithinBounds);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "NativeViewGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<NativeViewGestureHandler> getType() {
            return NativeViewGestureHandler.class;
        }
    }

    /* loaded from: classes.dex */
    public static class PanGestureHandlerFactory extends HandlerFactory<PanGestureHandler> {
        public PanGestureHandlerFactory(AnonymousClass1 r1) {
            super(null);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(PanGestureHandler panGestureHandler, ReadableMap readableMap) {
            PanGestureHandler panGestureHandler2 = panGestureHandler;
            super.configure(panGestureHandler2, readableMap);
            boolean z = true;
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)) {
                panGestureHandler2.mActiveOffsetXStart = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START));
                z = true;
            } else {
                z = false;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)) {
                panGestureHandler2.mActiveOffsetXEnd = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)) {
                panGestureHandler2.mFailOffsetXStart = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)) {
                panGestureHandler2.mFailOffsetXEnd = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)) {
                panGestureHandler2.mActiveOffsetYStart = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)) {
                panGestureHandler2.mActiveOffsetYEnd = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)) {
                panGestureHandler2.mFailOffsetYStart = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)) {
                panGestureHandler2.mFailOffsetYEnd = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)) {
                float pixelFromDIP = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY));
                panGestureHandler2.mMinVelocitySq = pixelFromDIP * pixelFromDIP;
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)) {
                panGestureHandler2.mMinVelocityX = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)) {
                panGestureHandler2.mMinVelocityY = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_DIST)) {
                float pixelFromDIP2 = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_DIST));
                panGestureHandler2.mMinDistSq = pixelFromDIP2 * pixelFromDIP2;
            } else if (z) {
                panGestureHandler2.mMinDistSq = Float.POSITIVE_INFINITY;
            }
            if (readableMap.hasKey("minPointers")) {
                panGestureHandler2.mMinPointers = readableMap.getInt("minPointers");
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS)) {
                panGestureHandler2.mMaxPointers = readableMap.getInt(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS);
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES)) {
                panGestureHandler2.mAverageTouches = readableMap.getBoolean(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES);
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public PanGestureHandler create(Context context) {
            return new PanGestureHandler(context);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(GestureHandler gestureHandler, WritableMap writableMap) {
            PanGestureHandler panGestureHandler = (PanGestureHandler) gestureHandler;
            writableMap.putDouble(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS, panGestureHandler.mNumberOfPointers);
            writableMap.putDouble("x", PixelUtil.toDIPFromPixel(panGestureHandler.getLastRelativePositionX()));
            writableMap.putDouble("y", PixelUtil.toDIPFromPixel(panGestureHandler.getLastRelativePositionY()));
            writableMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(((GestureHandler) panGestureHandler).mLastX));
            writableMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(((GestureHandler) panGestureHandler).mLastY));
            writableMap.putDouble("translationX", PixelUtil.toDIPFromPixel((panGestureHandler.mLastX - panGestureHandler.mStartX) + panGestureHandler.mOffsetX));
            writableMap.putDouble("translationY", PixelUtil.toDIPFromPixel((panGestureHandler.mLastY - panGestureHandler.mStartY) + panGestureHandler.mOffsetY));
            writableMap.putDouble("velocityX", PixelUtil.toDIPFromPixel(panGestureHandler.mLastVelocityX));
            writableMap.putDouble("velocityY", PixelUtil.toDIPFromPixel(panGestureHandler.mLastVelocityY));
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "PanGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<PanGestureHandler> getType() {
            return PanGestureHandler.class;
        }
    }

    /* loaded from: classes.dex */
    public static class PinchGestureHandlerFactory extends HandlerFactory<PinchGestureHandler> {
        public PinchGestureHandlerFactory(AnonymousClass1 r1) {
            super(null);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public PinchGestureHandler create(Context context) {
            return new PinchGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(GestureHandler gestureHandler, WritableMap writableMap) {
            PinchGestureHandler pinchGestureHandler = (PinchGestureHandler) gestureHandler;
            writableMap.putDouble(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS, pinchGestureHandler.mNumberOfPointers);
            writableMap.putDouble("scale", pinchGestureHandler.mLastScaleFactor);
            ScaleGestureDetector scaleGestureDetector = pinchGestureHandler.mScaleGestureDetector;
            float f = Float.NaN;
            writableMap.putDouble("focalX", PixelUtil.toDIPFromPixel(scaleGestureDetector == null ? Float.NaN : scaleGestureDetector.getFocusX()));
            ScaleGestureDetector scaleGestureDetector2 = pinchGestureHandler.mScaleGestureDetector;
            if (scaleGestureDetector2 != null) {
                f = scaleGestureDetector2.getFocusY();
            }
            writableMap.putDouble("focalY", PixelUtil.toDIPFromPixel(f));
            writableMap.putDouble("velocity", pinchGestureHandler.mLastVelocity);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "PinchGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<PinchGestureHandler> getType() {
            return PinchGestureHandler.class;
        }
    }

    /* loaded from: classes.dex */
    public static class RotationGestureHandlerFactory extends HandlerFactory<RotationGestureHandler> {
        public RotationGestureHandlerFactory(AnonymousClass1 r1) {
            super(null);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public RotationGestureHandler create(Context context) {
            return new RotationGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(GestureHandler gestureHandler, WritableMap writableMap) {
            RotationGestureHandler rotationGestureHandler = (RotationGestureHandler) gestureHandler;
            writableMap.putDouble(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS, rotationGestureHandler.mNumberOfPointers);
            writableMap.putDouble("rotation", rotationGestureHandler.mLastRotation);
            RotationGestureDetector rotationGestureDetector = rotationGestureHandler.mRotationGestureDetector;
            float f = Float.NaN;
            writableMap.putDouble("anchorX", PixelUtil.toDIPFromPixel(rotationGestureDetector == null ? Float.NaN : rotationGestureDetector.mAnchorX));
            RotationGestureDetector rotationGestureDetector2 = rotationGestureHandler.mRotationGestureDetector;
            if (rotationGestureDetector2 != null) {
                f = rotationGestureDetector2.mAnchorY;
            }
            writableMap.putDouble("anchorY", PixelUtil.toDIPFromPixel(f));
            writableMap.putDouble("velocity", rotationGestureHandler.mLastVelocity);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "RotationGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<RotationGestureHandler> getType() {
            return RotationGestureHandler.class;
        }
    }

    /* loaded from: classes.dex */
    public static class TapGestureHandlerFactory extends HandlerFactory<TapGestureHandler> {
        public TapGestureHandlerFactory(AnonymousClass1 r1) {
            super(null);
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public void configure(TapGestureHandler tapGestureHandler, ReadableMap readableMap) {
            TapGestureHandler tapGestureHandler2 = tapGestureHandler;
            super.configure(tapGestureHandler2, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS)) {
                tapGestureHandler2.mNumberOfTaps = readableMap.getInt(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS);
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS)) {
                tapGestureHandler2.mMaxDurationMs = readableMap.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS);
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS)) {
                tapGestureHandler2.mMaxDelayMs = readableMap.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS);
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)) {
                tapGestureHandler2.mMaxDeltaX = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)) {
                tapGestureHandler2.mMaxDeltaY = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y));
            }
            if (readableMap.hasKey("maxDist")) {
                float pixelFromDIP = PixelUtil.toPixelFromDIP(readableMap.getDouble("maxDist"));
                tapGestureHandler2.mMaxDistSq = pixelFromDIP * pixelFromDIP;
            }
            if (readableMap.hasKey("minPointers")) {
                tapGestureHandler2.mMinNumberOfPointers = readableMap.getInt("minPointers");
            }
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public TapGestureHandler create(Context context) {
            return new TapGestureHandler();
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerEventDataExtractor
        public void extractEventData(GestureHandler gestureHandler, WritableMap writableMap) {
            TapGestureHandler tapGestureHandler = (TapGestureHandler) gestureHandler;
            writableMap.putDouble(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS, ((GestureHandler) tapGestureHandler).mNumberOfPointers);
            writableMap.putDouble("x", PixelUtil.toDIPFromPixel(tapGestureHandler.getLastRelativePositionX()));
            writableMap.putDouble("y", PixelUtil.toDIPFromPixel(tapGestureHandler.getLastRelativePositionY()));
            writableMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(((GestureHandler) tapGestureHandler).mLastX));
            writableMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(((GestureHandler) tapGestureHandler).mLastY));
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public String getName() {
            return "TapGestureHandler";
        }

        @Override // com.swmansion.gesturehandler.react.RNGestureHandlerModule.HandlerFactory
        public Class<TapGestureHandler> getType() {
            return TapGestureHandler.class;
        }
    }

    public RNGestureHandlerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private HandlerFactory findFactoryForHandler(GestureHandler gestureHandler) {
        int i = 0;
        while (true) {
            HandlerFactory[] handlerFactoryArr = this.mHandlerFactories;
            if (i >= handlerFactoryArr.length) {
                return null;
            }
            HandlerFactory handlerFactory = handlerFactoryArr[i];
            if (handlerFactory.getType().equals(gestureHandler.getClass())) {
                return handlerFactory;
            }
            i++;
        }
    }

    private RNGestureHandlerRootHelper findRootHelperForViewAncestor(int i) {
        int resolveRootTagFromReactTag = ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).resolveRootTagFromReactTag(i);
        if (resolveRootTagFromReactTag < 1) {
            return null;
        }
        synchronized (this.mRoots) {
            for (int i2 = 0; i2 < this.mRoots.size(); i2++) {
                RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mRoots.get(i2);
                ViewGroup viewGroup = rNGestureHandlerRootHelper.mRootView;
                if ((viewGroup instanceof ReactRootView) && ((ReactRootView) viewGroup).getRootViewTag() == resolveRootTagFromReactTag) {
                    return rNGestureHandlerRootHelper;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleHitSlopProperty(GestureHandler gestureHandler, ReadableMap readableMap) {
        float f;
        float f2;
        float f3;
        float f4;
        if (readableMap.getType(KEY_HIT_SLOP) == ReadableType.Number) {
            float pixelFromDIP = PixelUtil.toPixelFromDIP(readableMap.getDouble(KEY_HIT_SLOP));
            gestureHandler.setHitSlop(pixelFromDIP, pixelFromDIP, pixelFromDIP, pixelFromDIP, Float.NaN, Float.NaN);
            return;
        }
        ReadableMap map = readableMap.getMap(KEY_HIT_SLOP);
        if (map.hasKey(KEY_HIT_SLOP_HORIZONTAL)) {
            f2 = PixelUtil.toPixelFromDIP(map.getDouble(KEY_HIT_SLOP_HORIZONTAL));
            f = f2;
        } else {
            f2 = Float.NaN;
            f = Float.NaN;
        }
        if (map.hasKey(KEY_HIT_SLOP_VERTICAL)) {
            f4 = PixelUtil.toPixelFromDIP(map.getDouble(KEY_HIT_SLOP_VERTICAL));
            f3 = f4;
        } else {
            f4 = Float.NaN;
            f3 = Float.NaN;
        }
        if (map.hasKey(KEY_HIT_SLOP_LEFT)) {
            f2 = PixelUtil.toPixelFromDIP(map.getDouble(KEY_HIT_SLOP_LEFT));
        }
        if (map.hasKey(KEY_HIT_SLOP_TOP)) {
            f4 = PixelUtil.toPixelFromDIP(map.getDouble(KEY_HIT_SLOP_TOP));
        }
        if (map.hasKey(KEY_HIT_SLOP_RIGHT)) {
            f = PixelUtil.toPixelFromDIP(map.getDouble(KEY_HIT_SLOP_RIGHT));
        }
        if (map.hasKey(KEY_HIT_SLOP_BOTTOM)) {
            f3 = PixelUtil.toPixelFromDIP(map.getDouble(KEY_HIT_SLOP_BOTTOM));
        }
        gestureHandler.setHitSlop(f2, f4, f, f3, map.hasKey(KEY_HIT_SLOP_WIDTH) ? PixelUtil.toPixelFromDIP(map.getDouble(KEY_HIT_SLOP_WIDTH)) : Float.NaN, map.hasKey(KEY_HIT_SLOP_HEIGHT) ? PixelUtil.toPixelFromDIP(map.getDouble(KEY_HIT_SLOP_HEIGHT)) : Float.NaN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStateChange(GestureHandler gestureHandler, int i, int i2) {
        if (gestureHandler.mTag >= 0) {
            HandlerFactory findFactoryForHandler = findFactoryForHandler(gestureHandler);
            EventDispatcher eventDispatcher = ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).getEventDispatcher();
            RNGestureHandlerStateChangeEvent acquire = RNGestureHandlerStateChangeEvent.EVENTS_POOL.acquire();
            if (acquire == null) {
                acquire = new RNGestureHandlerStateChangeEvent();
            }
            acquire.init(gestureHandler.mView.getId());
            WritableMap createMap = Arguments.createMap();
            acquire.mExtraData = createMap;
            if (findFactoryForHandler != null) {
                findFactoryForHandler.extractEventData(gestureHandler, createMap);
            }
            acquire.mExtraData.putInt("handlerTag", gestureHandler.mTag);
            acquire.mExtraData.putInt("state", i);
            acquire.mExtraData.putInt("oldState", i2);
            eventDispatcher.dispatchEvent(acquire);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTouchEvent(GestureHandler gestureHandler, MotionEvent motionEvent) {
        if (gestureHandler.mTag >= 0 && gestureHandler.mState == 4) {
            HandlerFactory findFactoryForHandler = findFactoryForHandler(gestureHandler);
            EventDispatcher eventDispatcher = ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).getEventDispatcher();
            RNGestureHandlerEvent acquire = RNGestureHandlerEvent.EVENTS_POOL.acquire();
            if (acquire == null) {
                acquire = new RNGestureHandlerEvent();
            }
            acquire.init(gestureHandler.mView.getId());
            WritableMap createMap = Arguments.createMap();
            acquire.mExtraData = createMap;
            if (findFactoryForHandler != null) {
                findFactoryForHandler.extractEventData(gestureHandler, createMap);
            }
            acquire.mExtraData.putInt("handlerTag", gestureHandler.mTag);
            acquire.mExtraData.putInt("state", gestureHandler.mState);
            acquire.mCoalescingKey = gestureHandler.mEventCoalescingKey;
            eventDispatcher.dispatchEvent(acquire);
        }
    }

    private void tryInitializeHandlerForReactRootView(int i) {
        UIManagerModule uIManagerModule = (UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class);
        final int resolveRootTagFromReactTag = uIManagerModule.resolveRootTagFromReactTag(i);
        if (resolveRootTagFromReactTag >= 1) {
            synchronized (this.mRoots) {
                for (int i2 = 0; i2 < this.mRoots.size(); i2++) {
                    ViewGroup viewGroup = this.mRoots.get(i2).mRootView;
                    if ((viewGroup instanceof ReactRootView) && ((ReactRootView) viewGroup).getRootViewTag() == resolveRootTagFromReactTag) {
                        return;
                    }
                }
                synchronized (this.mEnqueuedRootViewInit) {
                    if (!this.mEnqueuedRootViewInit.contains(Integer.valueOf(resolveRootTagFromReactTag))) {
                        this.mEnqueuedRootViewInit.add(Integer.valueOf(resolveRootTagFromReactTag));
                        uIManagerModule.addUIBlock(new UIBlock() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerModule.2
                            @Override // com.facebook.react.uimanager.UIBlock
                            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                                View resolveView = nativeViewHierarchyManager.resolveView(resolveRootTagFromReactTag);
                                if (resolveView instanceof RNGestureHandlerEnabledRootView) {
                                    RNGestureHandlerEnabledRootView rNGestureHandlerEnabledRootView = (RNGestureHandlerEnabledRootView) resolveView;
                                    if (rNGestureHandlerEnabledRootView.mGestureRootHelper == null) {
                                        rNGestureHandlerEnabledRootView.mGestureRootHelper = new RNGestureHandlerRootHelper(rNGestureHandlerEnabledRootView.mReactInstanceManager.getCurrentReactContext(), rNGestureHandlerEnabledRootView);
                                    } else {
                                        throw new IllegalStateException("GestureHandler already initialized for root view " + rNGestureHandlerEnabledRootView);
                                    }
                                }
                                synchronized (RNGestureHandlerModule.this.mEnqueuedRootViewInit) {
                                    RNGestureHandlerModule.this.mEnqueuedRootViewInit.remove(new Integer(resolveRootTagFromReactTag));
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
            }
        }
        throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline3("Could find root view for a given ancestor with tag ", i));
    }

    @ReactMethod
    public void attachGestureHandler(int i, int i2) {
        tryInitializeHandlerForReactRootView(i2);
        if (!this.mRegistry.attachHandlerToView(i, i2)) {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Handler with tag ", i, " does not exists"));
        }
    }

    @ReactMethod
    public void createGestureHandler(String str, int i, ReadableMap readableMap) {
        int i2 = 0;
        while (true) {
            HandlerFactory[] handlerFactoryArr = this.mHandlerFactories;
            if (i2 < handlerFactoryArr.length) {
                HandlerFactory handlerFactory = handlerFactoryArr[i2];
                if (handlerFactory.getName().equals(str)) {
                    GestureHandler create = handlerFactory.create(getReactApplicationContext());
                    create.mTag = i;
                    create.mListener = this.mEventListener;
                    RNGestureHandlerRegistry rNGestureHandlerRegistry = this.mRegistry;
                    synchronized (rNGestureHandlerRegistry) {
                        rNGestureHandlerRegistry.mHandlers.put(create.mTag, create);
                    }
                    this.mInteractionManager.configureInteractions(create, readableMap);
                    handlerFactory.configure(create, readableMap);
                    return;
                }
                i2++;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid handler name ", str));
            }
        }
    }

    @ReactMethod
    public void dropGestureHandler(int i) {
        RNGestureHandlerInteractionManager rNGestureHandlerInteractionManager = this.mInteractionManager;
        rNGestureHandlerInteractionManager.mWaitForRelations.remove(i);
        rNGestureHandlerInteractionManager.mSimultaneousRelations.remove(i);
        this.mRegistry.dropHandler(i);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("UNDETERMINED", 0);
        hashMap.put("BEGAN", 2);
        hashMap.put("ACTIVE", 4);
        hashMap.put("CANCELLED", 3);
        hashMap.put("FAILED", 1);
        hashMap.put("END", 5);
        return R$style.of("State", hashMap, "Direction", R$style.of("RIGHT", 1, "LEFT", 2, "UP", 4, "DOWN", 8));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    public RNGestureHandlerRegistry getRegistry() {
        return this.mRegistry;
    }

    @ReactMethod
    public void handleClearJSResponder() {
    }

    @ReactMethod
    public void handleSetJSResponder(int i, boolean z) {
        final RNGestureHandlerRootHelper findRootHelperForViewAncestor;
        if (this.mRegistry != null && (findRootHelperForViewAncestor = findRootHelperForViewAncestor(i)) != null && z) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    RNGestureHandlerRootHelper rNGestureHandlerRootHelper = RNGestureHandlerRootHelper.this;
                    GestureHandler gestureHandler = rNGestureHandlerRootHelper.mJSGestureHandler;
                    if (gestureHandler != null && gestureHandler.mState == 2) {
                        gestureHandler.activate();
                        rNGestureHandlerRootHelper.mJSGestureHandler.end();
                    }
                }
            });
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        RNGestureHandlerRegistry rNGestureHandlerRegistry = this.mRegistry;
        synchronized (rNGestureHandlerRegistry) {
            rNGestureHandlerRegistry.mHandlers.clear();
            rNGestureHandlerRegistry.mAttachedTo.clear();
            rNGestureHandlerRegistry.mHandlersForView.clear();
        }
        RNGestureHandlerInteractionManager rNGestureHandlerInteractionManager = this.mInteractionManager;
        rNGestureHandlerInteractionManager.mWaitForRelations.clear();
        rNGestureHandlerInteractionManager.mSimultaneousRelations.clear();
        synchronized (this.mRoots) {
            while (!this.mRoots.isEmpty()) {
                int size = this.mRoots.size();
                RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mRoots.get(0);
                ViewGroup viewGroup = rNGestureHandlerRootHelper.mRootView;
                if (viewGroup instanceof RNGestureHandlerEnabledRootView) {
                    RNGestureHandlerEnabledRootView rNGestureHandlerEnabledRootView = (RNGestureHandlerEnabledRootView) viewGroup;
                    RNGestureHandlerRootHelper rNGestureHandlerRootHelper2 = rNGestureHandlerEnabledRootView.mGestureRootHelper;
                    if (rNGestureHandlerRootHelper2 != null) {
                        rNGestureHandlerRootHelper2.tearDown();
                        rNGestureHandlerEnabledRootView.mGestureRootHelper = null;
                    }
                } else {
                    rNGestureHandlerRootHelper.tearDown();
                }
                if (this.mRoots.size() >= size) {
                    throw new IllegalStateException("Expected root helper to get unregistered while tearing down");
                }
            }
        }
        super.onCatalystInstanceDestroy();
    }

    public void registerRootHelper(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        synchronized (this.mRoots) {
            if (!this.mRoots.contains(rNGestureHandlerRootHelper)) {
                this.mRoots.add(rNGestureHandlerRootHelper);
            } else {
                throw new IllegalStateException("Root helper" + rNGestureHandlerRootHelper + " already registered");
            }
        }
    }

    public void unregisterRootHelper(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        synchronized (this.mRoots) {
            this.mRoots.remove(rNGestureHandlerRootHelper);
        }
    }

    @ReactMethod
    public void updateGestureHandler(int i, ReadableMap readableMap) {
        GestureHandler gestureHandler;
        HandlerFactory findFactoryForHandler;
        RNGestureHandlerRegistry rNGestureHandlerRegistry = this.mRegistry;
        synchronized (rNGestureHandlerRegistry) {
            gestureHandler = rNGestureHandlerRegistry.mHandlers.get(i);
        }
        if (gestureHandler != null && (findFactoryForHandler = findFactoryForHandler(gestureHandler)) != null) {
            RNGestureHandlerInteractionManager rNGestureHandlerInteractionManager = this.mInteractionManager;
            rNGestureHandlerInteractionManager.mWaitForRelations.remove(i);
            rNGestureHandlerInteractionManager.mSimultaneousRelations.remove(i);
            this.mInteractionManager.configureInteractions(gestureHandler, readableMap);
            findFactoryForHandler.configure(gestureHandler, readableMap);
        }
    }
}
