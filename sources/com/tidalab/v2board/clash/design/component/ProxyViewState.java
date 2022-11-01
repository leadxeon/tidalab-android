package com.tidalab.v2board.clash.design.component;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import com.tidalab.v2board.clash.core.model.Proxy;
import com.tidalab.v2board.clash.design.model.ProxyState;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import okhttp3.HttpUrl;
/* compiled from: ProxyViewState.kt */
/* loaded from: classes.dex */
public final class ProxyViewState {
    public int background;
    public final ProxyViewConfig config;
    public int controls;
    public int delay;
    public final ProxyState link;
    public String linkNow;
    public final ProxyState parent;
    public final Proxy proxy;
    public boolean selected;
    public final Paint paint = new Paint();
    public final Rect rect = new Rect();
    public final Path path = new Path();
    public String title = HttpUrl.FRAGMENT_ENCODE_SET;
    public String subtitle = HttpUrl.FRAGMENT_ENCODE_SET;
    public String delayText = HttpUrl.FRAGMENT_ENCODE_SET;
    public String parentNow = HttpUrl.FRAGMENT_ENCODE_SET;
    public long lastFrameTime = System.currentTimeMillis();

    public ProxyViewState(ProxyViewConfig proxyViewConfig, Proxy proxy, ProxyState proxyState, ProxyState proxyState2) {
        this.config = proxyViewConfig;
        this.proxy = proxy;
        this.parent = proxyState;
        this.link = proxyState2;
        this.background = proxyViewConfig.getUnselectedBackground();
        this.controls = proxyViewConfig.unselectedControl;
    }

    public final boolean update(boolean z) {
        int i;
        int i2;
        int i3;
        long currentTimeMillis = System.currentTimeMillis();
        Proxy proxy = this.proxy;
        Proxy.Type type = proxy.type;
        boolean z2 = false;
        if (type.group) {
            this.title = proxy.name;
            ProxyState proxyState = this.link;
            if (proxyState == null) {
                this.subtitle = type.name();
            } else {
                String str = this.linkNow;
                String str2 = proxyState.now;
                if (str != str2) {
                    this.linkNow = str2;
                    Object[] objArr = new Object[2];
                    objArr[0] = type.name();
                    String str3 = this.link.now;
                    if (str3.length() == 0) {
                        str3 = "*";
                    }
                    objArr[1] = str3;
                    this.subtitle = String.format("%s(%s)", Arrays.copyOf(objArr, 2));
                }
            }
        } else {
            this.title = proxy.title;
            this.subtitle = proxy.subtitle;
        }
        int i4 = this.delay;
        int i5 = this.proxy.delay;
        if (i4 != i5) {
            this.delay = i5;
            this.delayText = i5 >= 0 && i5 <= 32767 ? String.valueOf(i5) : HttpUrl.FRAGMENT_ENCODE_SET;
        }
        String str4 = this.parentNow;
        String str5 = this.parent.now;
        if (str4 != str5) {
            this.parentNow = str5;
            this.selected = Intrinsics.areEqual(this.proxy.name, str5);
        }
        boolean z3 = this.selected;
        ProxyViewConfig proxyViewConfig = this.config;
        if (z3) {
            i = proxyViewConfig.selectedControl;
        } else {
            i = proxyViewConfig.unselectedControl;
        }
        this.controls = i;
        if (z) {
            if (z3) {
                i3 = proxyViewConfig.selectedBackground;
            } else {
                i3 = proxyViewConfig.getUnselectedBackground();
            }
            this.background = i3;
        } else {
            if (z3) {
                i2 = proxyViewConfig.selectedBackground;
            } else {
                i2 = proxyViewConfig.getUnselectedBackground();
            }
            int i6 = this.background;
            if (i6 != i2) {
                int alpha = Color.alpha(i6);
                int red = Color.red(this.background);
                int green = Color.green(this.background);
                int blue = Color.blue(this.background);
                int alpha2 = Color.alpha(i2);
                int i7 = alpha2 - alpha;
                int red2 = Color.red(i2) - red;
                int green2 = Color.green(i2) - green;
                int blue2 = Color.blue(i2) - blue;
                float coerceAtLeast = ((float) (currentTimeMillis - this.lastFrameTime)) / RangesKt___RangesKt.coerceAtLeast(Math.max(Math.abs(i7), Math.max(Math.abs(red2), Math.max(Math.abs(green2), Math.abs(blue2)))), 0.001f);
                if (coerceAtLeast < 0.0f) {
                    coerceAtLeast = 0.0f;
                } else if (coerceAtLeast > 1.0f) {
                    coerceAtLeast = 1.0f;
                }
                if (coerceAtLeast <= 0.999f) {
                    i2 = Color.argb((int) ((i7 * coerceAtLeast) + alpha), (int) ((red2 * coerceAtLeast) + red), (int) ((green2 * coerceAtLeast) + green), (int) ((blue2 * coerceAtLeast) + blue));
                }
                this.background = i2;
                z2 = true;
            }
        }
        this.lastFrameTime = currentTimeMillis;
        return z2;
    }
}
