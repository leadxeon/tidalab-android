package com.facebook.react.animated;

import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.GuardedFrameCallback;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModuleListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
@ReactModule(name = NativeAnimatedModule.NAME)
/* loaded from: classes.dex */
public class NativeAnimatedModule extends ReactContextBaseJavaModule implements LifecycleEventListener, UIManagerModuleListener {
    public static final String NAME = "NativeAnimatedModule";
    private final GuardedFrameCallback mAnimatedFrameCallback;
    private NativeAnimatedNodesManager mNodesManager;
    private ArrayList<UIThreadOperation> mOperations = new ArrayList<>();
    private ArrayList<UIThreadOperation> mPreOperations = new ArrayList<>();
    private final ReactChoreographer mReactChoreographer = ReactChoreographer.getInstance();

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$5  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass5 implements AnimatedNodeValueListener {
        public final /* synthetic */ int val$tag;

        public AnonymousClass5(int i) {
            this.val$tag = i;
        }
    }

    /* loaded from: classes.dex */
    public interface UIThreadOperation {
        void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager);
    }

    public NativeAnimatedModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mAnimatedFrameCallback = new GuardedFrameCallback(reactApplicationContext) { // from class: com.facebook.react.animated.NativeAnimatedModule.1
            /* JADX WARN: Removed duplicated region for block: B:10:0x001c A[Catch: Exception -> 0x0033, TryCatch #0 {Exception -> 0x0033, blocks: (B:2:0x0000, B:4:0x000e, B:10:0x001c, B:11:0x001f), top: B:16:0x0000 }] */
            @Override // com.facebook.react.uimanager.GuardedFrameCallback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void doFrameGuarded(long r3) {
                /*
                    r2 = this;
                    com.facebook.react.animated.NativeAnimatedModule r0 = com.facebook.react.animated.NativeAnimatedModule.this     // Catch: Exception -> 0x0033
                    com.facebook.react.animated.NativeAnimatedNodesManager r0 = com.facebook.react.animated.NativeAnimatedModule.access$000(r0)     // Catch: Exception -> 0x0033
                    android.util.SparseArray<com.facebook.react.animated.AnimationDriver> r1 = r0.mActiveAnimations     // Catch: Exception -> 0x0033
                    int r1 = r1.size()     // Catch: Exception -> 0x0033
                    if (r1 > 0) goto L_0x0019
                    android.util.SparseArray<com.facebook.react.animated.AnimatedNode> r1 = r0.mUpdatedNodes     // Catch: Exception -> 0x0033
                    int r1 = r1.size()     // Catch: Exception -> 0x0033
                    if (r1 <= 0) goto L_0x0017
                    goto L_0x0019
                L_0x0017:
                    r1 = 0
                    goto L_0x001a
                L_0x0019:
                    r1 = 1
                L_0x001a:
                    if (r1 == 0) goto L_0x001f
                    r0.runUpdates(r3)     // Catch: Exception -> 0x0033
                L_0x001f:
                    com.facebook.react.animated.NativeAnimatedModule r3 = com.facebook.react.animated.NativeAnimatedModule.this     // Catch: Exception -> 0x0033
                    com.facebook.react.modules.core.ReactChoreographer r3 = com.facebook.react.animated.NativeAnimatedModule.access$200(r3)     // Catch: Exception -> 0x0033
                    androidx.recyclerview.R$dimen.assertNotNull(r3)     // Catch: Exception -> 0x0033
                    r4 = 3
                    com.facebook.react.animated.NativeAnimatedModule r0 = com.facebook.react.animated.NativeAnimatedModule.this     // Catch: Exception -> 0x0033
                    com.facebook.react.uimanager.GuardedFrameCallback r0 = com.facebook.react.animated.NativeAnimatedModule.access$100(r0)     // Catch: Exception -> 0x0033
                    r3.postFrameCallback$enumunboxing$(r4, r0)     // Catch: Exception -> 0x0033
                    return
                L_0x0033:
                    r3 = move-exception
                    java.lang.String r4 = "ReactNative"
                    java.lang.String r0 = "Exception while executing animated frame callback."
                    com.facebook.common.logging.FLog.e(r4, r0, r3)
                    java.lang.RuntimeException r4 = new java.lang.RuntimeException
                    r4.<init>(r3)
                    throw r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.NativeAnimatedModule.AnonymousClass1.doFrameGuarded(long):void");
            }
        };
    }

    private void clearFrameCallback() {
        ReactChoreographer reactChoreographer = this.mReactChoreographer;
        R$dimen.assertNotNull(reactChoreographer);
        reactChoreographer.removeFrameCallback$enumunboxing$(3, this.mAnimatedFrameCallback);
    }

    private void enqueueFrameCallback() {
        ReactChoreographer reactChoreographer = this.mReactChoreographer;
        R$dimen.assertNotNull(reactChoreographer);
        reactChoreographer.postFrameCallback$enumunboxing$(3, this.mAnimatedFrameCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NativeAnimatedNodesManager getNodesManager() {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn;
        if (this.mNodesManager == null && (reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn()) != null) {
            this.mNodesManager = new NativeAnimatedNodesManager((UIManagerModule) reactApplicationContextIfActiveOrWarn.getNativeModule(UIManagerModule.class));
        }
        return this.mNodesManager;
    }

    @ReactMethod
    public void addAnimatedEventToView(final int i, final String str, final ReadableMap readableMap) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.20
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i2 = i;
                String str2 = str;
                ReadableMap readableMap2 = readableMap;
                Objects.requireNonNull(nativeAnimatedNodesManager);
                int i3 = readableMap2.getInt("animatedValueTag");
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i3);
                if (animatedNode == null) {
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i3, " does not exists"));
                } else if (animatedNode instanceof ValueAnimatedNode) {
                    ReadableArray array = readableMap2.getArray("nativeEventPath");
                    ArrayList arrayList = new ArrayList(array.size());
                    for (int i4 = 0; i4 < array.size(); i4++) {
                        arrayList.add(array.getString(i4));
                    }
                    EventAnimationDriver eventAnimationDriver = new EventAnimationDriver(arrayList, (ValueAnimatedNode) animatedNode);
                    String str3 = i2 + str2;
                    if (nativeAnimatedNodesManager.mEventDrivers.containsKey(str3)) {
                        nativeAnimatedNodesManager.mEventDrivers.get(str3).add(eventAnimationDriver);
                        return;
                    }
                    ArrayList arrayList2 = new ArrayList(1);
                    arrayList2.add(eventAnimationDriver);
                    nativeAnimatedNodesManager.mEventDrivers.put(str3, arrayList2);
                } else {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Animated node connected to event should beof type ");
                    outline13.append(ValueAnimatedNode.class.getName());
                    throw new JSApplicationIllegalArgumentException(outline13.toString());
                }
            }
        });
    }

    @ReactMethod
    public void connectAnimatedNodeToView(final int i, final int i2) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.17
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i3 = i;
                int i4 = i2;
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i3);
                if (animatedNode == null) {
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i3, " does not exists"));
                } else if (animatedNode instanceof PropsAnimatedNode) {
                    PropsAnimatedNode propsAnimatedNode = (PropsAnimatedNode) animatedNode;
                    if (propsAnimatedNode.mConnectedViewTag == -1) {
                        propsAnimatedNode.mConnectedViewTag = i4;
                        nativeAnimatedNodesManager.mUpdatedNodes.put(i3, animatedNode);
                        return;
                    }
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline10(GeneratedOutlineSupport.outline13("Animated node "), propsAnimatedNode.mTag, " is already attached to a view"));
                } else {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Animated node connected to view should beof type ");
                    outline13.append(PropsAnimatedNode.class.getName());
                    throw new JSApplicationIllegalArgumentException(outline13.toString());
                }
            }
        });
    }

    @ReactMethod
    public void connectAnimatedNodes(final int i, final int i2) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.15
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i3 = i;
                int i4 = i2;
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i3);
                if (animatedNode != null) {
                    AnimatedNode animatedNode2 = nativeAnimatedNodesManager.mAnimatedNodes.get(i4);
                    if (animatedNode2 != null) {
                        if (animatedNode.mChildren == null) {
                            animatedNode.mChildren = new ArrayList(1);
                        }
                        List<AnimatedNode> list = animatedNode.mChildren;
                        R$dimen.assertNotNull(list);
                        list.add(animatedNode2);
                        animatedNode2.onAttachedToNode(animatedNode);
                        nativeAnimatedNodesManager.mUpdatedNodes.put(i4, animatedNode2);
                        return;
                    }
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i4, " does not exists"));
                }
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i3, " does not exists"));
            }
        });
    }

    @ReactMethod
    public void createAnimatedNode(final int i, final ReadableMap readableMap) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.4
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                AnimatedNode animatedNode;
                int i2 = i;
                ReadableMap readableMap2 = readableMap;
                if (nativeAnimatedNodesManager.mAnimatedNodes.get(i2) == null) {
                    String string = readableMap2.getString("type");
                    if ("style".equals(string)) {
                        animatedNode = new StyleAnimatedNode(readableMap2, nativeAnimatedNodesManager);
                    } else if ("value".equals(string)) {
                        animatedNode = new ValueAnimatedNode(readableMap2);
                    } else if ("props".equals(string)) {
                        animatedNode = new PropsAnimatedNode(readableMap2, nativeAnimatedNodesManager, nativeAnimatedNodesManager.mUIManagerModule);
                    } else if ("interpolation".equals(string)) {
                        animatedNode = new InterpolationAnimatedNode(readableMap2);
                    } else if ("addition".equals(string)) {
                        animatedNode = new AdditionAnimatedNode(readableMap2, nativeAnimatedNodesManager);
                    } else if ("subtraction".equals(string)) {
                        animatedNode = new SubtractionAnimatedNode(readableMap2, nativeAnimatedNodesManager);
                    } else if ("division".equals(string)) {
                        animatedNode = new DivisionAnimatedNode(readableMap2, nativeAnimatedNodesManager);
                    } else if ("multiplication".equals(string)) {
                        animatedNode = new MultiplicationAnimatedNode(readableMap2, nativeAnimatedNodesManager);
                    } else if ("modulus".equals(string)) {
                        animatedNode = new ModulusAnimatedNode(readableMap2, nativeAnimatedNodesManager);
                    } else if ("diffclamp".equals(string)) {
                        animatedNode = new DiffClampAnimatedNode(readableMap2, nativeAnimatedNodesManager);
                    } else if ("transform".equals(string)) {
                        animatedNode = new TransformAnimatedNode(readableMap2, nativeAnimatedNodesManager);
                    } else if ("tracking".equals(string)) {
                        animatedNode = new TrackingAnimatedNode(readableMap2, nativeAnimatedNodesManager);
                    } else {
                        throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Unsupported node type: ", string));
                    }
                    animatedNode.mTag = i2;
                    nativeAnimatedNodesManager.mAnimatedNodes.put(i2, animatedNode);
                    nativeAnimatedNodesManager.mUpdatedNodes.put(i2, animatedNode);
                    return;
                }
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i2, " already exists"));
            }
        });
    }

    @ReactMethod
    public void disconnectAnimatedNodeFromView(final int i, final int i2) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.18
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i3 = i;
                int i4 = i2;
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i3);
                if (animatedNode == null) {
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i3, " does not exists"));
                } else if (animatedNode instanceof PropsAnimatedNode) {
                    PropsAnimatedNode propsAnimatedNode = (PropsAnimatedNode) animatedNode;
                    if (propsAnimatedNode.mConnectedViewTag == i4) {
                        propsAnimatedNode.mConnectedViewTag = -1;
                        return;
                    }
                    throw new JSApplicationIllegalArgumentException("Attempting to disconnect view that has not been connected with the given animated node");
                } else {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Animated node connected to view should beof type ");
                    outline13.append(PropsAnimatedNode.class.getName());
                    throw new JSApplicationIllegalArgumentException(outline13.toString());
                }
            }
        });
    }

    @ReactMethod
    public void disconnectAnimatedNodes(final int i, final int i2) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.16
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i3 = i;
                int i4 = i2;
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i3);
                if (animatedNode != null) {
                    AnimatedNode animatedNode2 = nativeAnimatedNodesManager.mAnimatedNodes.get(i4);
                    if (animatedNode2 != null) {
                        if (animatedNode.mChildren != null) {
                            animatedNode2.onDetachedFromNode(animatedNode);
                            animatedNode.mChildren.remove(animatedNode2);
                        }
                        nativeAnimatedNodesManager.mUpdatedNodes.put(i4, animatedNode2);
                        return;
                    }
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i4, " does not exists"));
                }
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i3, " does not exists"));
            }
        });
    }

    @ReactMethod
    public void dropAnimatedNode(final int i) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.8
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i2 = i;
                nativeAnimatedNodesManager.mAnimatedNodes.remove(i2);
                nativeAnimatedNodesManager.mUpdatedNodes.remove(i2);
            }
        });
    }

    @ReactMethod
    public void extractAnimatedNodeOffset(final int i) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.12
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i2 = i;
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i2);
                if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i2, " does not exists or is not a 'value' node"));
                }
                ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) animatedNode;
                valueAnimatedNode.mOffset += valueAnimatedNode.mValue;
                valueAnimatedNode.mValue = 0.0d;
            }
        });
    }

    @ReactMethod
    public void flattenAnimatedNodeOffset(final int i) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.11
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i2 = i;
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i2);
                if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i2, " does not exists or is not a 'value' node"));
                }
                ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) animatedNode;
                valueAnimatedNode.mValue += valueAnimatedNode.mOffset;
                valueAnimatedNode.mOffset = 0.0d;
            }
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.addLifecycleEventListener(this);
            ((UIManagerModule) reactApplicationContextIfActiveOrWarn.getNativeModule(UIManagerModule.class)).addUIManagerListener(this);
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        clearFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        enqueueFrameCallback();
    }

    @ReactMethod
    public void removeAnimatedEventFromView(final int i, final String str, final int i2) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.21
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i3 = i;
                String str2 = str;
                int i4 = i2;
                Objects.requireNonNull(nativeAnimatedNodesManager);
                String str3 = i3 + str2;
                if (nativeAnimatedNodesManager.mEventDrivers.containsKey(str3)) {
                    List<EventAnimationDriver> list = nativeAnimatedNodesManager.mEventDrivers.get(str3);
                    if (list.size() == 1) {
                        nativeAnimatedNodesManager.mEventDrivers.remove(i3 + str2);
                        return;
                    }
                    ListIterator<EventAnimationDriver> listIterator = list.listIterator();
                    while (listIterator.hasNext()) {
                        if (listIterator.next().mValueNode.mTag == i4) {
                            listIterator.remove();
                            return;
                        }
                    }
                }
            }
        });
    }

    @ReactMethod
    public void restoreDefaultValues(final int i) {
        this.mPreOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.19
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i);
                if (animatedNode != null) {
                    if (animatedNode instanceof PropsAnimatedNode) {
                        PropsAnimatedNode propsAnimatedNode = (PropsAnimatedNode) animatedNode;
                        ReadableMapKeySetIterator keySetIterator = propsAnimatedNode.mPropMap.keySetIterator();
                        while (keySetIterator.hasNextKey()) {
                            propsAnimatedNode.mPropMap.putNull(keySetIterator.nextKey());
                        }
                        propsAnimatedNode.mUIManager.synchronouslyUpdateViewOnUIThread(propsAnimatedNode.mConnectedViewTag, propsAnimatedNode.mPropMap);
                        return;
                    }
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Animated node connected to view should beof type ");
                    outline13.append(PropsAnimatedNode.class.getName());
                    throw new JSApplicationIllegalArgumentException(outline13.toString());
                }
            }
        });
    }

    @ReactMethod
    public void setAnimatedNodeOffset(final int i, final double d) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.10
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i2 = i;
                double d2 = d;
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i2);
                if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i2, " does not exists or is not a 'value' node"));
                }
                ((ValueAnimatedNode) animatedNode).mOffset = d2;
                nativeAnimatedNodesManager.mUpdatedNodes.put(i2, animatedNode);
            }
        });
    }

    @ReactMethod
    public void setAnimatedNodeValue(final int i, final double d) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.9
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i2 = i;
                double d2 = d;
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i2);
                if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i2, " does not exists or is not a 'value' node"));
                }
                nativeAnimatedNodesManager.stopAnimationsForNode(animatedNode);
                ((ValueAnimatedNode) animatedNode).mValue = d2;
                nativeAnimatedNodesManager.mUpdatedNodes.put(i2, animatedNode);
            }
        });
    }

    public void setNodesManager(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNodesManager = nativeAnimatedNodesManager;
    }

    @ReactMethod
    public void startAnimatingNode(final int i, final int i2, final ReadableMap readableMap, final Callback callback) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.13
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.startAnimatingNode(i, i2, readableMap, callback);
            }
        });
    }

    @ReactMethod
    public void startListeningToAnimatedNodeValue(final int i) {
        final AnonymousClass5 r0 = new AnonymousClass5(i);
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.6
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i2 = i;
                AnimatedNodeValueListener animatedNodeValueListener = r0;
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i2);
                if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i2, " does not exists or is not a 'value' node"));
                }
                ((ValueAnimatedNode) animatedNode).mValueListener = animatedNodeValueListener;
            }
        });
    }

    @ReactMethod
    public void stopAnimation(final int i) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.14
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i2 = i;
                for (int i3 = 0; i3 < nativeAnimatedNodesManager.mActiveAnimations.size(); i3++) {
                    AnimationDriver valueAt = nativeAnimatedNodesManager.mActiveAnimations.valueAt(i3);
                    if (valueAt.mId == i2) {
                        if (valueAt.mEndCallback != null) {
                            WritableMap createMap = Arguments.createMap();
                            createMap.putBoolean("finished", false);
                            valueAt.mEndCallback.invoke(createMap);
                        }
                        nativeAnimatedNodesManager.mActiveAnimations.removeAt(i3);
                        return;
                    }
                }
            }
        });
    }

    @ReactMethod
    public void stopListeningToAnimatedNodeValue(final int i) {
        this.mOperations.add(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.7
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int i2 = i;
                AnimatedNode animatedNode = nativeAnimatedNodesManager.mAnimatedNodes.get(i2);
                if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("Animated node with tag ", i2, " does not exists or is not a 'value' node"));
                }
                ((ValueAnimatedNode) animatedNode).mValueListener = null;
            }
        });
    }

    @Override // com.facebook.react.uimanager.UIManagerModuleListener
    public void willDispatchViewUpdates(UIManagerModule uIManagerModule) {
        if (!this.mOperations.isEmpty() || !this.mPreOperations.isEmpty()) {
            final ArrayList<UIThreadOperation> arrayList = this.mPreOperations;
            final ArrayList<UIThreadOperation> arrayList2 = this.mOperations;
            this.mPreOperations = new ArrayList<>();
            this.mOperations = new ArrayList<>();
            uIManagerModule.prependUIBlock(new UIBlock() { // from class: com.facebook.react.animated.NativeAnimatedModule.2
                @Override // com.facebook.react.uimanager.UIBlock
                public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                    NativeAnimatedNodesManager nodesManager = NativeAnimatedModule.this.getNodesManager();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((UIThreadOperation) it.next()).execute(nodesManager);
                    }
                }
            });
            uIManagerModule.addUIBlock(new UIBlock() { // from class: com.facebook.react.animated.NativeAnimatedModule.3
                @Override // com.facebook.react.uimanager.UIBlock
                public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                    NativeAnimatedNodesManager nodesManager = NativeAnimatedModule.this.getNodesManager();
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        ((UIThreadOperation) it.next()).execute(nodesManager);
                    }
                }
            });
        }
    }
}
