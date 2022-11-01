package com.facebook.react.bridge;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@DoNotStrip
/* loaded from: classes.dex */
public class Inspector {
    private final HybridData mHybridData;

    @DoNotStrip
    /* loaded from: classes.dex */
    public static class LocalConnection {
        private final HybridData mHybridData;

        private LocalConnection(HybridData hybridData) {
            this.mHybridData = hybridData;
        }

        public native void disconnect();

        public native void sendMessage(String str);
    }

    @DoNotStrip
    /* loaded from: classes.dex */
    public static class Page {
        private final int mId;
        private final String mTitle;
        private final String mVM;

        @DoNotStrip
        private Page(int i, String str, String str2) {
            this.mId = i;
            this.mTitle = str;
            this.mVM = str2;
        }

        public int getId() {
            return this.mId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getVM() {
            return this.mVM;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Page{mId=");
            outline13.append(this.mId);
            outline13.append(", mTitle='");
            outline13.append(this.mTitle);
            outline13.append('\'');
            outline13.append('}');
            return outline13.toString();
        }
    }

    @DoNotStrip
    /* loaded from: classes.dex */
    public interface RemoteConnection {
        @DoNotStrip
        void onDisconnect();

        @DoNotStrip
        void onMessage(String str);
    }

    static {
        ReactBridge.staticInit();
    }

    private Inspector(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public static LocalConnection connect(int i, RemoteConnection remoteConnection) {
        try {
            return instance().connectNative(i, remoteConnection);
        } catch (UnsatisfiedLinkError e) {
            FLog.e("ReactNative", "Inspector doesn't work in open source yet", e);
            throw new RuntimeException(e);
        }
    }

    private native LocalConnection connectNative(int i, RemoteConnection remoteConnection);

    public static List<Page> getPages() {
        try {
            return Arrays.asList(instance().getPagesNative());
        } catch (UnsatisfiedLinkError e) {
            FLog.e("ReactNative", "Inspector doesn't work in open source yet", e);
            return Collections.emptyList();
        }
    }

    private native Page[] getPagesNative();

    private static native Inspector instance();
}
