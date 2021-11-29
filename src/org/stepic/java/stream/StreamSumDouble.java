package org.stepic.java.stream;

import java.util.Scanner;

public class StreamSumDouble {
    static boolean isDevMode = false;

    public static void main(String[] args) {
        Scanner scanner = scannerFactory();
        double result = 0;

        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                result += scanner.nextDouble();
            } else {
                scanner.next();
            }
        }
        System.out.printf("%.6f", result);
    }

    static Scanner scannerFactory() {
        return isDevMode ? new Scanner("-1e3\n" + "18 .111 11bbb") : new Scanner(System.in);
    }
}
