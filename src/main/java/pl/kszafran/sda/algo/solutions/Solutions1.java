package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises1;

import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Solutions1 extends Exercises1 {

    @Override
    public int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    @Override
    public int sum(int[] numbers) {
        return sum(numbers, 0);
    }

    private int sum(int[] numbers, int offset) {
        return offset == numbers.length ? 0 : numbers[offset] + sum(numbers, offset + 1);
    }

    @Override
    public String reverse(String text) {
        if (text.isEmpty()) {
            return "";
        }
        int split = text.length() - 1;
        return text.substring(split) + reverse(text.substring(0, split));
    }

    @Override
    public int lcm(int... numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("At least one argument expected");
        }
        return lcm(numbers, 0);
    }

    private int lcm(int[] numbers, int i) {
        if (i == numbers.length - 1) { // ostatni element tablicy
            return lcm1(numbers[i]);
        }
        return lcm2(numbers[i], lcm(numbers, i + 1));
    }

    private int lcm1(int a) {
        if (a < 0) {
            throw new IllegalArgumentException("Arguments must be non-negative");
        }
        return a;
    }

    private int lcm2(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Arguments must be non-negative");
        }
        return a == 0 || b == 0 ? a + b : a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Override
    public Set<String> permutations(String string) {
        return streamPermutations(string).collect(toSet());
    }

    private Stream<String> streamPermutations(String string) {
        if (string.isEmpty()) {
            return Stream.of("");
        }

        return IntStream.range(0, string.length()).boxed().flatMap(i ->
                streamPermutations(string.substring(0, i) + string.substring(i + 1))
                        .map(subPermutation -> string.charAt(i) + subPermutation));
    }
}
