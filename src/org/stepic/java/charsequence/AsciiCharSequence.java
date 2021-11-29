package org.stepic.java.charsequence;

import java.lang.CharSequence;
import java.util.Arrays;

public class AsciiCharSequence implements CharSequence {
    private final byte[] contentData;

    public AsciiCharSequence(byte[] contentData) {
        this.contentData = contentData;
    }

    @Override
    public int length() {
        return contentData.length;
    }

    @Override
    public char charAt(int idx) {
        return (char)contentData[idx];
    }

    @Override
    public CharSequence subSequence(int posBegin, int posEnd) {
        return new AsciiCharSequence(Arrays.copyOfRange(contentData, posBegin, posEnd));
    }

    @Override
    public String toString() {
        return new String(contentData);
    }
}
