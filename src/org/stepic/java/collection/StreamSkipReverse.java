package org.stepic.java.collection;

import java.util.*;
import java.util.stream.Collectors;

public class StreamSkipReverse {
    static boolean isDevMode = false;

    public static void main(String[] args) {
        Scanner scanner = scannerFactory();
        List<Integer> list = new LinkedList<>();
        int idx = 0, inValue;

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                inValue = scanner.nextInt();
                if (idx++ % 2 != 0) {
                    list.add(inValue);
                }
            } else {
                scanner.next();
            }
        }
        Collections.reverse(list);
        String listString = list.stream().map(Object::toString).collect(Collectors.joining(" "));
        System.out.print(listString);
    }

    static Scanner scannerFactory() {
        return isDevMode ? new Scanner("1 2 3 4 5 6 7") : new Scanner(System.in);
    }
}
