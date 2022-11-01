package com.facebook.react.uimanager.events;

import android.os.Trace;
import android.util.LongSparseArray;
import androidx.recyclerview.R$dimen;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public class EventDispatcher implements LifecycleEventListener {
    public static final Comparator<Event> EVENT_COMPARATOR = new Comparator<Event>() { // from class: com.facebook.react.uimanager.events.EventDispatcher.1
        @Override // java.util.Comparator
        public int compare(Event event, Event event2) {
            Event event3 = event;
            Event event4 = event2;
            if (event3 == null && event4 == null) {
                return 0;
            }
            if (event3 != null) {
                if (event4 != null) {
                    int i = ((event3.mTimestampMs - event4.mTimestampMs) > 0L ? 1 : ((event3.mTimestampMs - event4.mTimestampMs) == 0L ? 0 : -1));
                    if (i == 0) {
                        return 0;
                    }
                    if (i < 0) {
                    }
                }
                return 1;
            }
            return -1;
        }
    };
    public final ReactApplicationContext mReactContext;
    public volatile ReactEventEmitter mReactEventEmitter;
    public final Object mEventsStagingLock = new Object();
    public final Object mEventsToDispatchLock = new Object();
    public final LongSparseArray<Integer> mEventCookieToLastEventIdx = new LongSparseArray<>();
    public final Map<String, Short> mEventNameToEventId = new HashMap();
    public final DispatchEventsRunnable mDispatchEventsRunnable = new DispatchEventsRunnable(null);
    public final ArrayList<Event> mEventStaging = new ArrayList<>();
    public final ArrayList<EventDispatcherListener> mListeners = new ArrayList<>();
    public final List<BatchEventDispatchedListener> mPostEventDispatchListeners = new ArrayList();
    public final ScheduleDispatchFrameCallback mCurrentFrameCallback = new ScheduleDispatchFrameCallback(null);
    public final AtomicInteger mHasDispatchScheduledCount = new AtomicInteger();
    public Event[] mEventsToDispatch = new Event[16];
    public int mEventsToDispatchSize = 0;
    public short mNextEventTypeId = 0;
    public volatile boolean mHasDispatchScheduled = false;

    /* loaded from: classes.dex */
    public class DispatchEventsRunnable implements Runnable {
        public DispatchEventsRunnable(AnonymousClass1 r2) {
        }

        @Override // java.lang.Runnable
        public void run() {
            EventDispatcher eventDispatcher;
            int i;
            Trace.beginSection("DispatchEventsRunnable");
            try {
                EventDispatcher.this.mHasDispatchScheduledCount.getAndIncrement();
                EventDispatcher.this.mHasDispatchScheduled = false;
                R$dimen.assertNotNull(EventDispatcher.this.mReactEventEmitter);
                synchronized (EventDispatcher.this.mEventsToDispatchLock) {
                    EventDispatcher eventDispatcher2 = EventDispatcher.this;
                    int i2 = eventDispatcher2.mEventsToDispatchSize;
                    if (i2 > 0) {
                        if (i2 > 1) {
                            Arrays.sort(eventDispatcher2.mEventsToDispatch, 0, i2, EventDispatcher.EVENT_COMPARATOR);
                        }
                        int i3 = 0;
                        while (true) {
                            eventDispatcher = EventDispatcher.this;
                            i = eventDispatcher.mEventsToDispatchSize;
                            if (i3 >= i) {
                                break;
                            }
                            Event event = eventDispatcher.mEventsToDispatch[i3];
                            if (event != null) {
                                event.getEventName();
                                event.dispatch(EventDispatcher.this.mReactEventEmitter);
                                event.mInitialized = false;
                                event.onDispose();
                            }
                            i3++;
                        }
                        Arrays.fill(eventDispatcher.mEventsToDispatch, 0, i, (Object) null);
                        eventDispatcher.mEventsToDispatchSize = 0;
                        EventDispatcher.this.mEventCookieToLastEventIdx.clear();
                    }
                }
                for (BatchEventDispatchedListener batchEventDispatchedListener : EventDispatcher.this.mPostEventDispatchListeners) {
                    batchEventDispatchedListener.onBatchEventDispatched();
                }
            } finally {
                Trace.endSection();
            }
        }
    }

    /* loaded from: classes.dex */
    public class ScheduleDispatchFrameCallback extends ChoreographerCompat.FrameCallback {
        public volatile boolean mIsPosted = false;
        public boolean mShouldStop = false;

        public ScheduleDispatchFrameCallback(AnonymousClass1 r2) {
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j) {
            UiThreadUtil.assertOnUiThread();
            if (this.mShouldStop) {
                this.mIsPosted = false;
            } else {
                ReactChoreographer.getInstance().postFrameCallback$enumunboxing$(4, EventDispatcher.this.mCurrentFrameCallback);
            }
            Trace.beginSection("ScheduleDispatchFrameCallback");
            try {
                EventDispatcher.access$300(EventDispatcher.this);
                if (!EventDispatcher.this.mHasDispatchScheduled) {
                    EventDispatcher.this.mHasDispatchScheduled = true;
                    EventDispatcher.this.mHasDispatchScheduledCount.get();
                    EventDispatcher eventDispatcher = EventDispatcher.this;
                    eventDispatcher.mReactContext.runOnJSQueueThread(eventDispatcher.mDispatchEventsRunnable);
                }
            } finally {
                Trace.endSection();
            }
        }

        public void maybePost() {
            if (!this.mIsPosted) {
                this.mIsPosted = true;
                ReactChoreographer.getInstance().postFrameCallback$enumunboxing$(4, EventDispatcher.this.mCurrentFrameCallback);
            }
        }
    }

    public EventDispatcher(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
        this.mReactEventEmitter = new ReactEventEmitter(reactApplicationContext);
    }

    public static void access$300(EventDispatcher eventDispatcher) {
        short s;
        synchronized (eventDispatcher.mEventsStagingLock) {
            synchronized (eventDispatcher.mEventsToDispatchLock) {
                for (int i = 0; i < eventDispatcher.mEventStaging.size(); i++) {
                    Event event = eventDispatcher.mEventStaging.get(i);
                    if (!event.canCoalesce()) {
                        eventDispatcher.addEventToEventsToDispatch(event);
                    } else {
                        int i2 = event.mViewTag;
                        String eventName = event.getEventName();
                        short coalescingKey = event.getCoalescingKey();
                        Short sh = eventDispatcher.mEventNameToEventId.get(eventName);
                        if (sh != null) {
                            s = sh.shortValue();
                        } else {
                            short s2 = eventDispatcher.mNextEventTypeId;
                            eventDispatcher.mNextEventTypeId = (short) (s2 + 1);
                            eventDispatcher.mEventNameToEventId.put(eventName, Short.valueOf(s2));
                            s = s2;
                        }
                        long j = ((s & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | i2 | ((coalescingKey & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48);
                        Integer num = eventDispatcher.mEventCookieToLastEventIdx.get(j);
                        Event event2 = null;
                        if (num == null) {
                            eventDispatcher.mEventCookieToLastEventIdx.put(j, Integer.valueOf(eventDispatcher.mEventsToDispatchSize));
                        } else {
                            Event event3 = eventDispatcher.mEventsToDispatch[num.intValue()];
                            Event event4 = event.mTimestampMs >= event3.mTimestampMs ? event : event3;
                            if (event4 != event3) {
                                eventDispatcher.mEventCookieToLastEventIdx.put(j, Integer.valueOf(eventDispatcher.mEventsToDispatchSize));
                                eventDispatcher.mEventsToDispatch[num.intValue()] = null;
                                event2 = event3;
                                event = event4;
                            } else {
                                event2 = event;
                                event = null;
                            }
                        }
                        if (event != null) {
                            eventDispatcher.addEventToEventsToDispatch(event);
                        }
                        if (event2 != null) {
                            event2.mInitialized = false;
                            event2.onDispose();
                        }
                    }
                }
            }
            eventDispatcher.mEventStaging.clear();
        }
    }

    public final void addEventToEventsToDispatch(Event event) {
        int i = this.mEventsToDispatchSize;
        Event[] eventArr = this.mEventsToDispatch;
        if (i == eventArr.length) {
            this.mEventsToDispatch = (Event[]) Arrays.copyOf(eventArr, eventArr.length * 2);
        }
        Event[] eventArr2 = this.mEventsToDispatch;
        int i2 = this.mEventsToDispatchSize;
        this.mEventsToDispatchSize = i2 + 1;
        eventArr2[i2] = event;
    }

    public void dispatchEvent(Event event) {
        R$dimen.assertCondition(event.mInitialized, "Dispatched event hasn't been initialized");
        Iterator<EventDispatcherListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onEventDispatch(event);
        }
        synchronized (this.mEventsStagingLock) {
            this.mEventStaging.add(event);
            event.getEventName();
        }
        maybePostFrameCallbackFromNonUI();
    }

    public final void maybePostFrameCallbackFromNonUI() {
        if (this.mReactEventEmitter != null) {
            final ScheduleDispatchFrameCallback scheduleDispatchFrameCallback = this.mCurrentFrameCallback;
            if (!scheduleDispatchFrameCallback.mIsPosted) {
                if (EventDispatcher.this.mReactContext.isOnUiQueueThread()) {
                    scheduleDispatchFrameCallback.maybePost();
                } else {
                    EventDispatcher.this.mReactContext.runOnUiQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.events.EventDispatcher.ScheduleDispatchFrameCallback.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ScheduleDispatchFrameCallback.this.maybePost();
                        }
                    });
                }
            }
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        stopFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        stopFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        maybePostFrameCallbackFromNonUI();
    }

    public final void stopFrameCallback() {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentFrameCallback.mShouldStop = true;
    }
}
