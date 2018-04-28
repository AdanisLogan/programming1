package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Exercises1Test {

    private Exercises1 exercises = new Exercises1();

    @Test
    void test_factorial() {
        assertEquals(1, exercises.factorial(0));
        assertEquals(1, exercises.factorial(1));
        assertEquals(2, exercises.factorial(2));
        assertEquals(6, exercises.factorial(3));
        assertEquals(24, exercises.factorial(4));
        assertEquals(120, exercises.factorial(5));
    }

    @Test
    void test_sum() {
        assertEquals(0, exercises.sum(new int[0]));
        assertEquals(32, exercises.sum(new int[]{3, 5, 8, 0, 19, -3}));
    }

    @Test
    void test_reverse() {
        assertEquals("", exercises.reverse(""));
        assertEquals("tset", exercises.reverse("test"));
        assertEquals("uclap myłam w ajcneruker", exercises.reverse("rekurencja w małym palcu"));
    }

    @Test
    void test_lcm() {
        assertEquals(0, exercises.lcm(0));
        assertEquals(5, exercises.lcm(5));
        assertEquals(0, exercises.lcm(0, 0));
        assertEquals(1, exercises.lcm(1, 0));
        assertEquals(1, exercises.lcm(0, 1));
        assertEquals(1, exercises.lcm(1, 1));
        assertEquals(5, exercises.lcm(5, 1));
        assertEquals(10, exercises.lcm(5, 2));
        assertEquals(6, exercises.lcm(6, 2));
        assertEquals(280, exercises.lcm(40, 35));
        assertEquals(40, exercises.lcm(5, 8, 2));
        assertEquals(2040, exercises.lcm(5, 8, 2, 3, 17));
        assertThrows(IllegalArgumentException.class, () -> exercises.lcm());
        assertThrows(IllegalArgumentException.class, () -> exercises.lcm(-5));
        assertThrows(IllegalArgumentException.class, () -> exercises.lcm(-2, 5));
        assertThrows(IllegalArgumentException.class, () -> exercises.lcm(2, -5));
        assertThrows(IllegalArgumentException.class, () -> exercises.lcm(2, 2, -5));
        assertThrows(IllegalArgumentException.class, () -> exercises.lcm(2, 2, 2, -5));
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    @Test
    void test_permutations() {
        assertEquals(Set.of(""), exercises.permutations(""));
        assertEquals(Set.of("1"), exercises.permutations("1"));
        assertEquals(Set.of("12", "21"), exercises.permutations("12"));
        assertEquals(Set.of("123", "132", "213", "231", "312", "321"), exercises.permutations("123"));
    }
}