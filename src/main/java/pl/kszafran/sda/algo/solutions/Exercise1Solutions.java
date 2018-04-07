package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercise1;

/**
 * Zaimplementuj poniższe metody z wykorzystaniem rekurencji.
 */
public class Exercise1Solutions extends Exercise1 {

    /**
     * Oblicza silnię argumentu n. Zwraca 1 dla n = 0.
     */
    public int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    /**
     * Odwraca podany napis, np. dla "test" zwraca "tset".
     */
    public String reverse(String text) {
        if (text.isEmpty()) {
            return "";
        }
        int split = text.length() - 1;
        return text.substring(split) + reverse(text.substring(0, split));
    }
}
