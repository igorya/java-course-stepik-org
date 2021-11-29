package org.stepic.java.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMinMax {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 4, 8, 10, 12, 6);
        // Case with empty stream
//        list = Collections.emptyList();

        findMinMax(
                list.stream(),
                Integer::compareTo,
                (v1, v2) -> System.out.printf("Min: %d Max: %d", v1, v2)
        );
    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<T> localList = stream.collect(Collectors.toList());

        if (!localList.isEmpty()) {
            localList.sort(order);
            minMaxConsumer.accept(localList.get(0), localList.get(localList.size() - 1));
        } else {
            minMaxConsumer.accept(null, null);
        }
    }
}
