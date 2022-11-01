package com.facebook.react.modules.debug;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.common.LongArray;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class DidJSUpdateUiDuringFrameDetector implements NotThreadSafeBridgeIdleDebugListener, NotThreadSafeViewHierarchyUpdateDebugListener {
    public final LongArray mTransitionToIdleEvents = new LongArray(20);
    public final LongArray mTransitionToBusyEvents = new LongArray(20);
    public final LongArray mViewHierarchyUpdateEnqueuedEvents = new LongArray(20);
    public final LongArray mViewHierarchyUpdateFinishedEvents = new LongArray(20);
    public volatile boolean mWasIdleAtEndOfLastFrame = true;

    public static void cleanUp(LongArray longArray, long j) {
        int i = longArray.mLength;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (longArray.get(i3) < j) {
                i2++;
            }
        }
        if (i2 > 0) {
            for (int i4 = 0; i4 < i - i2; i4++) {
                long j2 = longArray.get(i4 + i2);
                if (i4 < longArray.mLength) {
                    longArray.mArray[i4] = j2;
                } else {
                    StringBuilder outline15 = GeneratedOutlineSupport.outline15(HttpUrl.FRAGMENT_ENCODE_SET, i4, " >= ");
                    outline15.append(longArray.mLength);
                    throw new IndexOutOfBoundsException(outline15.toString());
                }
            }
            int i5 = longArray.mLength;
            if (i2 <= i5) {
                longArray.mLength = i5 - i2;
                return;
            }
            StringBuilder outline152 = GeneratedOutlineSupport.outline15("Trying to drop ", i2, " items from array of length ");
            outline152.append(longArray.mLength);
            throw new IndexOutOfBoundsException(outline152.toString());
        }
    }

    public static long getLastEventBetweenTimestamps(LongArray longArray, long j, long j2) {
        long j3 = -1;
        for (int i = 0; i < longArray.mLength; i++) {
            long j4 = longArray.get(i);
            if (j4 >= j && j4 < j2) {
                j3 = j4;
            } else if (j4 >= j2) {
                break;
            }
        }
        return j3;
    }

    public static boolean hasEventBetweenTimestamps(LongArray longArray, long j, long j2) {
        for (int i = 0; i < longArray.mLength; i++) {
            long j3 = longArray.get(i);
            if (j3 >= j && j3 < j2) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onBridgeDestroyed() {
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onTransitionToBridgeBusy() {
        this.mTransitionToBusyEvents.add(System.nanoTime());
    }

    @Override // com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener
    public synchronized void onTransitionToBridgeIdle() {
        this.mTransitionToIdleEvents.add(System.nanoTime());
    }
}
