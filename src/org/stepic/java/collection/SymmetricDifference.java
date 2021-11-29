package org.stepic.java.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SymmetricDifference {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(0, 1, 2));
        System.out.println(set1);
        System.out.println(set2);

        System.out.println(symmetricDifference(set1, set2));
    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> set1copy = new HashSet<>(set1);
        Set<T> set2copy = new HashSet<>(set2);

        set1copy.removeAll(set2);
        set2copy.removeAll(set1);
        set1copy.addAll(set2copy);
        return  set1copy;
    }
}

