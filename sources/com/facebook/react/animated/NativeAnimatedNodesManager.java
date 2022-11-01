package com.facebook.react.animated;

import android.util.SparseArray;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcherListener;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class NativeAnimatedNodesManager implements EventDispatcherListener {
    public final UIManagerModule.CustomEventNamesResolver mCustomEventNamesResolver;
    public final UIManagerModule mUIManagerModule;
    public final SparseArray<AnimatedNode> mAnimatedNodes = new SparseArray<>();
    public final SparseArray<AnimationDriver> mActiveAnimations = new SparseArray<>();
    public final SparseArray<AnimatedNode> mUpdatedNodes = new SparseArray<>();
    public final Map<String, List<EventAnimationDriver>> mEventDrivers = new HashMap();
    public int mAnimatedGraphBFSColor = 0;
    public final List<AnimatedNode> mRunUpdateNodeList = new LinkedList();

    public NativeAnimatedNodesManager(UIManagerModule uIManagerModule) {
        this.mUIManagerModule = uIManagerModule;
        uIManagerModule.getEventDispatcher().mListeners.add(this);
        this.mCustomEventNamesResolver = uIManagerModule.getDirectEventNamesResolver();
    }

    public AnimatedNode getNodeById(int i) {
        return this.mAnimatedNodes.get(i);
    }

    public final void handleEvent(Event event) {
        Map map;
        if (!this.mEventDrivers.isEmpty()) {
            UIManagerModule.CustomEventNamesResolver customEventNamesResolver = this.mCustomEventNamesResolver;
            String eventName = event.getEventName();
            map = UIManagerModule.this.mCustomDirectEvents;
            Map map2 = (Map) map.get(eventName);
            if (map2 != null) {
                eventName = (String) map2.get("registrationName");
            }
            Map<String, List<EventAnimationDriver>> map3 = this.mEventDrivers;
            List<EventAnimationDriver> list = map3.get(event.mViewTag + eventName);
            if (list != null) {
                for (EventAnimationDriver eventAnimationDriver : list) {
                    stopAnimationsForNode(eventAnimationDriver.mValueNode);
                    event.dispatch(eventAnimationDriver);
                    this.mRunUpdateNodeList.add(eventAnimationDriver.mValueNode);
                }
                updateNodes(this.mRunUpdateNodeList);
                this.mRunUpdateNodeList.clear();
            }
        }
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcherListener
    public void onEventDispatch(final Event event) {
        if (UiThreadUtil.isOnUiThread()) {
            handleEvent(event);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.animated.NativeAnimatedNodesManager.1
                @Override // java.lang.Runnable
                public void run() {
                    NativeAnimatedNodesManager.this.handleEvent(event);
                }
            });
        }
    }

    public void runUpdates(long j) {
        UiThreadUtil.assertOnUiThread();
        for (int i = 0; i < this.mUpdatedNodes.size(); i++) {
            this.mRunUpdateNodeList.add(this.mUpdatedNodes.valueAt(i));
        }
        this.mUpdatedNodes.clear();
        boolean z = false;
        for (int i2 = 0; i2 < this.mActiveAnimations.size(); i2++) {
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(i2);
            valueAt.runAnimationStep(j);
            this.mRunUpdateNodeList.add(valueAt.mAnimatedValue);
            if (valueAt.mHasFinished) {
                z = true;
            }
        }
        updateNodes(this.mRunUpdateNodeList);
        this.mRunUpdateNodeList.clear();
        if (z) {
            for (int size = this.mActiveAnimations.size() - 1; size >= 0; size--) {
                AnimationDriver valueAt2 = this.mActiveAnimations.valueAt(size);
                if (valueAt2.mHasFinished) {
                    if (valueAt2.mEndCallback != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putBoolean("finished", true);
                        valueAt2.mEndCallback.invoke(createMap);
                    }
                    this.mActiveAnimations.removeAt(size);
                }
            }
        }
    }

    public void startAnimatingNode(int i, int i2, ReadableMap readableMap, Callback callback) {
        AnimationDriver animationDriver;
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i2, " does not exists"));
        } else if (animatedNode instanceof ValueAnimatedNode) {
            AnimationDriver animationDriver2 = this.mActiveAnimations.get(i);
            if (animationDriver2 != null) {
                animationDriver2.resetConfig(readableMap);
                return;
            }
            String string = readableMap.getString("type");
            if ("frames".equals(string)) {
                animationDriver = new FrameBasedAnimationDriver(readableMap);
            } else if ("spring".equals(string)) {
                animationDriver = new SpringAnimation(readableMap);
            } else if ("decay".equals(string)) {
                animationDriver = new DecayAnimation(readableMap);
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Unsupported animation type: ", string));
            }
            animationDriver.mId = i;
            animationDriver.mEndCallback = callback;
            animationDriver.mAnimatedValue = (ValueAnimatedNode) animatedNode;
            this.mActiveAnimations.put(i, animationDriver);
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Animated node should be of type ");
            outline13.append(ValueAnimatedNode.class.getName());
            throw new JSApplicationIllegalArgumentException(outline13.toString());
        }
    }

    public final void stopAnimationsForNode(AnimatedNode animatedNode) {
        int i = 0;
        while (i < this.mActiveAnimations.size()) {
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(i);
            if (animatedNode.equals(valueAt.mAnimatedValue)) {
                if (valueAt.mEndCallback != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("finished", false);
                    valueAt.mEndCallback.invoke(createMap);
                }
                this.mActiveAnimations.removeAt(i);
                i--;
            }
            i++;
        }
    }

    public final void updateNodes(List<AnimatedNode> list) {
        ValueAnimatedNode valueAnimatedNode;
        AnimatedNodeValueListener animatedNodeValueListener;
        ReactApplicationContext reactApplicationContextIfActiveOrWarn;
        int i = this.mAnimatedGraphBFSColor + 1;
        this.mAnimatedGraphBFSColor = i;
        if (i == 0) {
            this.mAnimatedGraphBFSColor = i + 1;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i2 = 0;
        for (AnimatedNode animatedNode : list) {
            int i3 = animatedNode.mBFSColor;
            int i4 = this.mAnimatedGraphBFSColor;
            if (i3 != i4) {
                animatedNode.mBFSColor = i4;
                i2++;
                arrayDeque.add(animatedNode);
            }
        }
        while (!arrayDeque.isEmpty()) {
            AnimatedNode animatedNode2 = (AnimatedNode) arrayDeque.poll();
            if (animatedNode2.mChildren != null) {
                for (int i5 = 0; i5 < animatedNode2.mChildren.size(); i5++) {
                    AnimatedNode animatedNode3 = animatedNode2.mChildren.get(i5);
                    animatedNode3.mActiveIncomingNodes++;
                    int i6 = animatedNode3.mBFSColor;
                    int i7 = this.mAnimatedGraphBFSColor;
                    if (i6 != i7) {
                        animatedNode3.mBFSColor = i7;
                        i2++;
                        arrayDeque.add(animatedNode3);
                    }
                }
            }
        }
        int i8 = this.mAnimatedGraphBFSColor + 1;
        this.mAnimatedGraphBFSColor = i8;
        if (i8 == 0) {
            this.mAnimatedGraphBFSColor = i8 + 1;
        }
        int i9 = 0;
        for (AnimatedNode animatedNode4 : list) {
            if (animatedNode4.mActiveIncomingNodes == 0) {
                int i10 = animatedNode4.mBFSColor;
                int i11 = this.mAnimatedGraphBFSColor;
                if (i10 != i11) {
                    animatedNode4.mBFSColor = i11;
                    i9++;
                    arrayDeque.add(animatedNode4);
                }
            }
        }
        while (!arrayDeque.isEmpty()) {
            AnimatedNode animatedNode5 = (AnimatedNode) arrayDeque.poll();
            animatedNode5.update();
            if (animatedNode5 instanceof PropsAnimatedNode) {
                try {
                    ((PropsAnimatedNode) animatedNode5).updateView();
                } catch (IllegalViewOperationException e) {
                    FLog.e("ReactNative", "Native animation workaround, frame lost as result of race condition", e);
                }
            }
            if ((animatedNode5 instanceof ValueAnimatedNode) && (animatedNodeValueListener = (valueAnimatedNode = (ValueAnimatedNode) animatedNode5).mValueListener) != null) {
                double value = valueAnimatedNode.getValue();
                NativeAnimatedModule.AnonymousClass5 r4 = (NativeAnimatedModule.AnonymousClass5) animatedNodeValueListener;
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("tag", r4.val$tag);
                createMap.putDouble("value", value);
                reactApplicationContextIfActiveOrWarn = NativeAnimatedModule.this.getReactApplicationContextIfActiveOrWarn();
                if (reactApplicationContextIfActiveOrWarn != null) {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContextIfActiveOrWarn.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onAnimatedValueUpdate", createMap);
                }
            }
            if (animatedNode5.mChildren != null) {
                for (int i12 = 0; i12 < animatedNode5.mChildren.size(); i12++) {
                    AnimatedNode animatedNode6 = animatedNode5.mChildren.get(i12);
                    int i13 = animatedNode6.mActiveIncomingNodes - 1;
                    animatedNode6.mActiveIncomingNodes = i13;
                    int i14 = animatedNode6.mBFSColor;
                    int i15 = this.mAnimatedGraphBFSColor;
                    if (i14 != i15 && i13 == 0) {
                        animatedNode6.mBFSColor = i15;
                        i9++;
                        arrayDeque.add(animatedNode6);
                    }
                }
            }
        }
        if (i2 != i9) {
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("Looks like animated nodes graph has cycles, there are ", i2, " but toposort visited only ", i9));
        }
    }
}
