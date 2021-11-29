package org.stepic.java.stream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCountWord {
    static boolean isDevMode = false;

    public static void main(String[] args) {
        final int ENTRIES_LIMIT = 10;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamFactory(), StandardCharsets.UTF_8));
        Stream<String> stream = bufferedReader.lines();

        Map<String, Long> countedMap =
                stream.map(line -> line.toLowerCase().split("[\\p{Blank}\\p{Punct}]+")).flatMap(Arrays::stream)
                        .collect(
                                Collectors.groupingBy(
                                        Function.identity(),
                                        Collectors.counting()
                                )
                        );
        countedMap.entrySet().stream()
                .sorted((elem1, elem2) -> {
                    int compResult = elem2.getValue().compareTo(elem1.getValue());
                    if (compResult == 0) {
                        return -1 * elem2.getKey().compareTo(elem1.getKey());
                    }
                    return compResult;
                })
                .limit(ENTRIES_LIMIT)
                .forEach(stringLongEntry -> System.out.println(stringLongEntry.getKey()));
    }

    static InputStream streamFactory() {
        String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus " +
                "at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit " +
                "blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget " +
                "vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer " +
                "vel odio nec mi tempor dignissim.";
        return isDevMode ? new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8)) : System.in;
    }
}
