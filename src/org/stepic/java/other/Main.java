package org.stepic.java.other;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//        if (stackTraceElements.length <= 3) {
//            return null;
//        }
        for (StackTraceElement element : stackTraceElements) {
            System.out.println(element.getClassName() +"#"+ element.getMethodName());
        }
        return "KUKU";
    }

    public static double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Expected non-negative number, got " + x);
        }
        return Math.sqrt(x);
    }

    public static int[] mergeArrays(int[] a1, int[] a2) {
        int[] result = new int[a1.length + a2.length];
        int resultIndex = 0;
        for (int value : a1) {
            result[resultIndex++] = value;
        }
        for (int value : a2) {
            result[resultIndex++] = value;
        }
        Arrays.sort(result);
        return result;
    }

    public static BigInteger factorial(int value) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= value; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Checks if given <code>text</code> is a palindrome.
     *
     * @param text any string
     * @return <code>true</code> when <code>text</code> is a palindrome, <code>false</code> otherwise
     */
    public static boolean isPalindrome(String text) {
        String textStriped = text.replaceAll("[^a-zA-Z0-9]", "");
        return textStriped.equalsIgnoreCase((new StringBuilder(textStriped)).reverse().toString());
    }

    /**
     * Checks if given <code>value</code> is a power of two.
     *
     * @param value any number
     * @return <code>true</code> when <code>value</code> is power of two, <code>false</code> otherwise
     */
    public static boolean isPowerOfTwo(int value) {
        return Integer.bitCount(Math.abs(value)) == 1;
    }

    public static char charExpression(int a) {
        return (char)((int)'\\' + a);
    }

    /**
     * Flips one bit of the given <code>value</code>.
     *
     * @param value any number
     * @param bitIndex index of the bit to flip, 1 <= bitIndex <= 32
     * @return new value with one bit flipped
     */
    public static int flipBit(int value, int bitIndex) {
        return value ^ (1 << (bitIndex-1));
    }

    public static boolean doubleExpression(double a, double b, double c) {
        return Math.abs(c - (a + b)) <= 1E-4;
    }

    public static boolean booleanExpression(boolean a, boolean b, boolean c, boolean d) {
        int ai = a ? 1 : 0;
        int bi = b ? 1 : 0;
        int ci = c ? 1 : 0;
        int di = d ? 1 : 0;
        return ai + bi + ci + di == 2;
    }

    public static int leapYearCount(int year) {
        return (year / 4 ) - (year / 100)  + (year /400);
    }
}
