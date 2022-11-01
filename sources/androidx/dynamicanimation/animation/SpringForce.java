package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;
/* loaded from: classes.dex */
public final class SpringForce {
    public double mDampedFreq;
    public double mDampingRatio;
    public double mFinalPosition;
    public double mGammaMinus;
    public double mGammaPlus;
    public boolean mInitialized;
    public final DynamicAnimation.MassState mMassState;
    public double mNaturalFreq;
    public double mValueThreshold;
    public double mVelocityThreshold;

    public SpringForce() {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = Double.MAX_VALUE;
        this.mMassState = new DynamicAnimation.MassState();
    }

    public SpringForce setStiffness(float f) {
        if (f > 0.0f) {
            this.mNaturalFreq = Math.sqrt(f);
            this.mInitialized = false;
            return this;
        }
        throw new IllegalArgumentException("Spring stiffness constant must be positive.");
    }

    public DynamicAnimation.MassState updateValues(double d, double d2, long j) {
        double d3;
        double d4;
        if (!this.mInitialized) {
            if (this.mFinalPosition != Double.MAX_VALUE) {
                double d5 = this.mDampingRatio;
                if (d5 > 1.0d) {
                    double d6 = this.mNaturalFreq;
                    this.mGammaPlus = (Math.sqrt((d5 * d5) - 1.0d) * d6) + ((-d5) * d6);
                    double d7 = this.mDampingRatio;
                    double d8 = this.mNaturalFreq;
                    this.mGammaMinus = ((-d7) * d8) - (Math.sqrt((d7 * d7) - 1.0d) * d8);
                } else if (d5 >= 0.0d && d5 < 1.0d) {
                    this.mDampedFreq = Math.sqrt(1.0d - (d5 * d5)) * this.mNaturalFreq;
                }
                this.mInitialized = true;
            } else {
                throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
            }
        }
        double d9 = j / 1000.0d;
        double d10 = d - this.mFinalPosition;
        double d11 = this.mDampingRatio;
        if (d11 > 1.0d) {
            double d12 = this.mGammaMinus;
            double d13 = this.mGammaPlus;
            double d14 = d10 - (((d12 * d10) - d2) / (d12 - d13));
            double d15 = ((d10 * d12) - d2) / (d12 - d13);
            d3 = (Math.pow(2.718281828459045d, this.mGammaPlus * d9) * d15) + (Math.pow(2.718281828459045d, d12 * d9) * d14);
            double d16 = this.mGammaMinus;
            double pow = Math.pow(2.718281828459045d, d16 * d9) * d14 * d16;
            double d17 = this.mGammaPlus;
            d4 = (Math.pow(2.718281828459045d, d17 * d9) * d15 * d17) + pow;
        } else if (d11 == 1.0d) {
            double d18 = this.mNaturalFreq;
            double d19 = (d18 * d10) + d2;
            double d20 = (d19 * d9) + d10;
            d3 = Math.pow(2.718281828459045d, (-d18) * d9) * d20;
            double pow2 = Math.pow(2.718281828459045d, (-this.mNaturalFreq) * d9) * d20;
            double d21 = this.mNaturalFreq;
            d4 = (Math.pow(2.718281828459045d, (-d21) * d9) * d19) + (pow2 * (-d21));
        } else {
            double d22 = 1.0d / this.mDampedFreq;
            double d23 = this.mNaturalFreq;
            double d24 = ((d11 * d23 * d10) + d2) * d22;
            double sin = ((Math.sin(this.mDampedFreq * d9) * d24) + (Math.cos(this.mDampedFreq * d9) * d10)) * Math.pow(2.718281828459045d, (-d11) * d23 * d9);
            double d25 = this.mNaturalFreq;
            double d26 = this.mDampingRatio;
            double d27 = (-d25) * sin * d26;
            double pow3 = Math.pow(2.718281828459045d, (-d26) * d25 * d9);
            double d28 = this.mDampedFreq;
            double d29 = (-d28) * d10;
            double d30 = this.mDampedFreq;
            d4 = (((Math.cos(d30 * d9) * d24 * d30) + (Math.sin(d28 * d9) * d29)) * pow3) + d27;
            d3 = sin;
        }
        DynamicAnimation.MassState massState = this.mMassState;
        massState.mValue = (float) (d3 + this.mFinalPosition);
        massState.mVelocity = (float) d4;
        return massState;
    }

    public SpringForce(float f) {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = Double.MAX_VALUE;
        this.mMassState = new DynamicAnimation.MassState();
        this.mFinalPosition = f;
    }
}
