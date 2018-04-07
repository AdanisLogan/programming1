package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise1Test {

    private Exercise1 exercise = new Exercise1();

    @Test
    void testFactorial() {
        assertEquals(1, exercise.factorial(0));
        assertEquals(1, exercise.factorial(1));
        assertEquals(2, exercise.factorial(2));
        assertEquals(6, exercise.factorial(3));
        assertEquals(24, exercise.factorial(4));
        assertEquals(120, exercise.factorial(5));
    }

    @Test
    void testReverse() {
        assertEquals(exercise.reverse("test"), "tset");
        assertEquals(exercise.reverse(""), "");
        assertEquals(exercise.reverse("rekurencja w małym palcu"), "uclap myłam w ajcneruker");
    }
}