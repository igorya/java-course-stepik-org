package org.stepic.java.generic;

import java.util.Objects;
import java.util.Optional;

public class MainPair {
    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        System.out.printf("%s %s\n", pair.getFirst(), pair.getSecond());

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        System.out.printf("%s %s\n", pair.equals(pair2), pair.hashCode() == pair2.hashCode());
    }
}

class Pair<F, S> {
    private F first;
    private S second;

    private Pair() {
    }

    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public static <F, S> Pair<F, S> of(F first, S second) {
        return new Pair(first, second);
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
