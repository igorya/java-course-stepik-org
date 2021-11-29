package org.stepic.java.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamRandomSeed {
    public static void main(String[] args) {
        IntStream result = pseudoRandomStream(13);

        System.out.println(
                Arrays.toString(result.limit(10).toArray())
        );
    }

    public static IntStream pseudoRandomStream(int seed) {
        return IntStream.iterate(seed, i -> (i * i) / 10 % 1000);
    }
}
