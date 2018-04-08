package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;
import pl.kszafran.sda.algo.exercises.sorting.IntSortingAlgorithm;
import pl.kszafran.sda.algo.exercises.sorting.SortingAlgorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercises2Test {

    private Exercises2 exercises = new Exercises2();
    private Random random = new Random();

    @Test
    void testSelectionSort() {
        testSorting(exercises.createSelectionSort());
    }

    @Test
    void testInsertionSort() {
        testSorting(exercises.createInsertionSort());
    }

    @Test
    void testQuicksort() {
        testSorting(exercises.createQuicksort());
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    @Test
    void testMergeSort() {
        testSorting(exercises.createMergeSort());
    }

    @Test
    void testGenericSortingAlgorithm() {
        assertEquals(
                List.of(-23, 1, 3, 48),
                exercises.<Integer>createSortingAlgorithm(naturalOrder()).sort(List.of(48, 1, -23, 3)));

        // Uwaga: od Javy 8 można łatwo budować Comparatory
        // dzięki metodom statycznym na interfejsie Comparator.
        // Np. poniższy Comparator porównuje najpierw długość Stringów,
        // a następnie ich kolejność w porządku leksykograficznym.
        SortingAlgorithm<String> algorithm = exercises.createSortingAlgorithm(
                comparing(String::length).thenComparing(naturalOrder()));

        assertEquals(
                emptyList(),
                algorithm.sort(emptyList()));

        assertEquals(
                List.of("yksi", "kaksi", "kolme", "neljä"),
                algorithm.sort(List.of("yksi", "kaksi", "kolme", "neljä")));

        assertEquals(
                List.of("dwa", "pięć", "trzy", "jeden", "cztery"),
                algorithm.sort(List.of("jeden", "dwa", "trzy", "cztery", "pięć")));
    }

    private void testSorting(IntSortingAlgorithm algorithm) {
        testEmptyArray(algorithm);
        testSingletonArray(algorithm);
        testSortedArray(algorithm);
        for (int i = 0; i < 50; i++) {
            testRandomArray(algorithm, random.nextInt(50));
        }
    }

    private void testEmptyArray(IntSortingAlgorithm algorithm) {
        algorithm.sort(new int[0]); // pusta tablica nie powoduje wyjątku
    }

    private void testSingletonArray(IntSortingAlgorithm algorithm) {
        int[] singleton = {78};
        algorithm.sort(singleton);
        assertEquals(78, singleton[0]);
    }

    private void testSortedArray(IntSortingAlgorithm algorithm) {
        int[] array = {-30, 0, 3, 9, 10, 27};
        int[] original = array.clone();
        algorithm.sort(array);
        assertArrayEquals(original, array); // tablica jest nadal posortowana
    }

    private void testRandomArray(IntSortingAlgorithm algorithm, int length) {
        int[] array = random.ints(length).map(x -> x % 100).toArray();
        int[] original = array.clone();
        int[] javaSorted = array.clone();
        algorithm.sort(array);
        Arrays.sort(javaSorted);
        assertArrayEquals(javaSorted, array, "Sorting failed for input: " + Arrays.toString(original));
    }
}
