package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.reactnativecommunity.webview.RNCWebViewManager;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public class ImageOriginRequestListener extends BaseRequestListener {
    public String mControllerId;
    public final ImageOriginListener mImageOriginLister;

    public ImageOriginRequestListener(String str, ImageOriginListener imageOriginListener) {
        this.mImageOriginLister = imageOriginListener;
        this.mControllerId = str;
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onUltimateProducerReached(String str, String str2, boolean z) {
        ImageOriginListener imageOriginListener = this.mImageOriginLister;
        if (imageOriginListener != null) {
            String str3 = this.mControllerId;
            str2.hashCode();
            char c = 65535;
            int i = 1;
            switch (str2.hashCode()) {
                case -1917159454:
                    if (str2.equals("QualifiedResourceFetchProducer")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1914072202:
                    if (str2.equals("BitmapMemoryCacheGetProducer")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1683996557:
                    if (str2.equals("LocalResourceFetchProducer")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1579985851:
                    if (str2.equals("LocalFileFetchProducer")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1307634203:
                    if (str2.equals("EncodedMemoryCacheProducer")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1224383234:
                    if (str2.equals("NetworkFetchProducer")) {
                        c = 5;
                        break;
                    }
                    break;
                case 473552259:
                    if (str2.equals("VideoThumbnailProducer")) {
                        c = 6;
                        break;
                    }
                    break;
                case 656304759:
                    if (str2.equals("DiskCacheProducer")) {
                        c = 7;
                        break;
                    }
                    break;
                case 957714404:
                    if (str2.equals("BitmapMemoryCacheProducer")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 1019542023:
                    if (str2.equals("LocalAssetFetchProducer")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 1023071510:
                    if (str2.equals("PostprocessedBitmapMemoryCacheProducer")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 1721672898:
                    if (str2.equals("DataFetchProducer")) {
                        c = 11;
                        break;
                    }
                    break;
                case 1793127518:
                    if (str2.equals("LocalContentUriThumbnailFetchProducer")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 2109593398:
                    if (str2.equals("PartialDiskCacheProducer")) {
                        c = '\r';
                        break;
                    }
                    break;
                case 2113652014:
                    if (str2.equals("LocalContentUriFetchProducer")) {
                        c = 14;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 2:
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                case 6:
                case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                case 11:
                case '\f':
                case 14:
                    i = 6;
                    break;
                case 1:
                case '\b':
                case '\n':
                    i = 5;
                    break;
                case 4:
                    i = 4;
                    break;
                case 5:
                    i = 2;
                    break;
                case 7:
                case '\r':
                    i = 3;
                    break;
            }
            imageOriginListener.onImageLoaded(str3, i, z, str2);
        }
    }
}
