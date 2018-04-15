package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;
import pl.kszafran.sda.algo.exercises.Exercises3.IntRange;
import pl.kszafran.sda.algo.solutions.Solutions3;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Comparator.naturalOrder;
import static org.junit.jupiter.api.Assertions.*;

public class Exercises3Test {

    private Exercises3 exercises = new Solutions3();

    @Test
    void test_linearSearch() {
        int[] array = {5, 2, 9, 10, -43};
        assertEquals(0, exercises.linearSearch(array, 5));
        assertEquals(1, exercises.linearSearch(array, 2));
        assertEquals(2, exercises.linearSearch(array, 9));
        assertEquals(3, exercises.linearSearch(array, 10));
        assertEquals(4, exercises.linearSearch(array, -43));
        assertEquals(-1, exercises.linearSearch(array, 6));

        assertEquals(-1, exercises.linearSearch(new int[]{}, 4));
        assertEquals(0, exercises.linearSearch(new int[]{4}, 4));
        assertEquals(0, exercises.linearSearch(new int[]{4, 5}, 4));
    }

    @Test
    void test_binarySearch() {
        int[] array = {-43, 2, 5, 9, 10};
        assertEquals(0, exercises.binarySearch(array, -43));
        assertEquals(1, exercises.binarySearch(array, 2));
        assertEquals(2, exercises.binarySearch(array, 5));
        assertEquals(3, exercises.binarySearch(array, 9));
        assertEquals(4, exercises.binarySearch(array, 10));
        assertEquals(-1, exercises.binarySearch(array, 6));

        assertEquals(-1, exercises.binarySearch(new int[]{}, 4));
        assertEquals(-1, exercises.binarySearch(new int[]{4}, 6));
        assertEquals(0, exercises.binarySearch(new int[]{4}, 4));
        assertEquals(0, exercises.binarySearch(new int[]{4, 5}, 4));
    }

    @Test
    void test_indexOf() {
        List<String> list = List.of("kaksi", "kolme", "neljä", "yksi");
        assertEquals(Optional.of(0), exercises.indexOf(list, "kaksi", naturalOrder()));
        assertEquals(Optional.of(1), exercises.indexOf(list, "kolme", naturalOrder()));
        assertEquals(Optional.of(2), exercises.indexOf(list, "neljä", naturalOrder()));
        assertEquals(Optional.of(3), exercises.indexOf(list, "yksi", naturalOrder()));
        assertEquals(Optional.empty(), exercises.indexOf(list, "olematon", naturalOrder()));

        assertEquals(Optional.empty(), exercises.indexOf(emptyList(), "yksi", naturalOrder()));
        assertEquals(Optional.empty(), exercises.indexOf(List.of("kaksi"), "yksi", naturalOrder()));
        assertEquals(Optional.of(0), exercises.indexOf(List.of("kaksi"), "kaksi", naturalOrder()));
        assertEquals(Optional.of(0), exercises.indexOf(List.of("kaksi", "yksi"), "kaksi", naturalOrder()));
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    @Test
    void test_binarySearchRange() {
        int[] array = {3, 3, 3, 5, 7, 7, 7, 10, 10, 21};
        assertRange(0, 2, exercises.binarySearchRange(array, 3));
        assertRange(3, 3, exercises.binarySearchRange(array, 5));
        assertRange(4, 6, exercises.binarySearchRange(array, 7));
        assertRange(7, 8, exercises.binarySearchRange(array, 10));
        assertRange(9, 9, exercises.binarySearchRange(array, 21));
        assertFalse(exercises.binarySearchRange(array, 6).isPresent());
        assertFalse(exercises.binarySearchRange(array, 30).isPresent());
        assertFalse(exercises.binarySearchRange(array, -10).isPresent());

        assertFalse(exercises.binarySearchRange(new int[]{}, 4).isPresent());
        assertFalse(exercises.binarySearchRange(new int[]{4}, 6).isPresent());
        assertRange(0, 0, exercises.binarySearchRange(new int[]{4}, 4));
        assertRange(0, 0, exercises.binarySearchRange(new int[]{4, 5}, 4));
    }

    private void assertRange(int start, int end, Optional<IntRange> range) {
        assertTrue(range.isPresent());
        assertEquals(start, range.get().getStart());
        assertEquals(end, range.get().getEnd());
    }

    @Test
    void test_interpolationSearch() {
        int[] array = {-43, 2, 5, 9, 10};
        assertEquals(0, exercises.interpolationSearch(array, -43));
        assertEquals(1, exercises.interpolationSearch(array, 2));
        assertEquals(2, exercises.interpolationSearch(array, 5));
        assertEquals(3, exercises.interpolationSearch(array, 9));
        assertEquals(4, exercises.interpolationSearch(array, 10));
        assertEquals(-1, exercises.interpolationSearch(array, 6));

        assertEquals(-1, exercises.interpolationSearch(new int[]{}, 4));
        assertEquals(-1, exercises.interpolationSearch(new int[]{4}, 6));
        assertEquals(0, exercises.interpolationSearch(new int[]{4}, 4));
        assertEquals(0, exercises.interpolationSearch(new int[]{4, 5}, 4));
    }
}
