package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
/* loaded from: classes.dex */
public class ARTSurfaceViewShadowNode extends LayoutShadowNode implements TextureView.SurfaceTextureListener, LifecycleEventListener {
    public Integer mBackgroundColor;
    public Surface mSurface;

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void dispose() {
        super.dispose();
        if (Build.VERSION.SDK_INT > 24) {
            getThemedContext().mReactApplicationContext.removeLifecycleEventListener(this);
        }
    }

    public final void drawOutput(boolean z) {
        Surface surface = this.mSurface;
        if (surface == null || !surface.isValid()) {
            markChildrenUpdatesSeen(this);
            return;
        }
        try {
            Canvas lockCanvas = this.mSurface.lockCanvas(null);
            lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            Integer num = this.mBackgroundColor;
            if (num != null) {
                lockCanvas.drawColor(num.intValue());
            }
            Paint paint = new Paint();
            for (int i = 0; i < getChildCount(); i++) {
                ARTVirtualNode aRTVirtualNode = (ARTVirtualNode) getChildAt(i);
                aRTVirtualNode.draw(lockCanvas, paint, 1.0f);
                if (z) {
                    aRTVirtualNode.markUpdated();
                } else {
                    aRTVirtualNode.markUpdateSeen();
                }
            }
            Surface surface2 = this.mSurface;
            if (surface2 != null) {
                surface2.unlockCanvasAndPost(lockCanvas);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            FLog.e("ReactNative", e.getClass().getSimpleName() + " in Surface.unlockCanvasAndPost");
        }
    }

    public final void markChildrenUpdatesSeen(ReactShadowNode reactShadowNode) {
        for (int i = 0; i < reactShadowNode.getChildCount(); i++) {
            ReactShadowNode childAt = reactShadowNode.getChildAt(i);
            childAt.markUpdateSeen();
            markChildrenUpdatesSeen(childAt);
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public void onCollectExtraUpdates(UIViewOperationQueue uIViewOperationQueue) {
        drawOutput(false);
        uIViewOperationQueue.enqueueUpdateExtraData(this.mReactTag, this);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        drawOutput(false);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mSurface = new Surface(surfaceTexture);
        drawOutput(false);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mSurface.release();
        this.mSurface = null;
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @ReactProp(customType = "Color", name = "backgroundColor")
    public void setBackgroundColor(Integer num) {
        this.mBackgroundColor = num;
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setThemedContext(ThemedReactContext themedReactContext) {
        this.mThemedContext = themedReactContext;
        if (Build.VERSION.SDK_INT > 24) {
            themedReactContext.mReactApplicationContext.addLifecycleEventListener(this);
        }
    }
}
