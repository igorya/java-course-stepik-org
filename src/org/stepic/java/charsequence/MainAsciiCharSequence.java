package org.stepic.java.charsequence;

public class MainAsciiCharSequence {
    public static void main(String[] args) {
        AsciiCharSequence asciiSeq = new AsciiCharSequence(new byte[]{65,66,67,68,69,70,71});

        System.out.println(asciiSeq.length());
        System.out.println(asciiSeq.charAt(3));
        System.out.println(asciiSeq.subSequence(3,5));
        System.out.println(asciiSeq);
    }
}
