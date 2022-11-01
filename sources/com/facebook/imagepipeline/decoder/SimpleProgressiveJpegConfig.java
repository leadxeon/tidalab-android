package com.facebook.imagepipeline.decoder;

import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class SimpleProgressiveJpegConfig implements ProgressiveJpegConfig {
    public final DynamicValueConfig mDynamicValueConfig = new DefaultDynamicValueConfig(null);

    /* loaded from: classes.dex */
    public static class DefaultDynamicValueConfig implements DynamicValueConfig {
        public DefaultDynamicValueConfig(AnonymousClass1 r1) {
        }

        @Override // com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig.DynamicValueConfig
        public int getGoodEnoughScanNumber() {
            return 0;
        }

        @Override // com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig.DynamicValueConfig
        public List<Integer> getScansToDecode() {
            return Collections.EMPTY_LIST;
        }
    }

    /* loaded from: classes.dex */
    public interface DynamicValueConfig {
        int getGoodEnoughScanNumber();

        List<Integer> getScansToDecode();
    }

    @Override // com.facebook.imagepipeline.decoder.ProgressiveJpegConfig
    public int getNextScanNumberToDecode(int i) {
        List<Integer> scansToDecode = this.mDynamicValueConfig.getScansToDecode();
        if (scansToDecode == null || scansToDecode.isEmpty()) {
            return i + 1;
        }
        for (int i2 = 0; i2 < scansToDecode.size(); i2++) {
            if (scansToDecode.get(i2).intValue() > i) {
                return scansToDecode.get(i2).intValue();
            }
        }
        return Integer.MAX_VALUE;
    }

    @Override // com.facebook.imagepipeline.decoder.ProgressiveJpegConfig
    public QualityInfo getQualityInfo(int i) {
        return new ImmutableQualityInfo(i, i >= this.mDynamicValueConfig.getGoodEnoughScanNumber(), false);
    }
}
