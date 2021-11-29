package org.stepic.java.other;

import java.util.Objects;

public class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object cmpObj) {
        if (this == cmpObj) {
            return true;
        }
        if (cmpObj == null || getClass() != cmpObj.getClass()) {
            return false;
        }
        ComplexNumber cmpObjCast = (ComplexNumber) cmpObj;
        return Double.compare(cmpObjCast.re, re) == 0 && Double.compare(cmpObjCast.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }
}
