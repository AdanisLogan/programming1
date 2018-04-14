package pl.kszafran.sda.algo.exercises;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Zaimplementuj poniższe algorytmy wyszukiwania.
 */
public class Exercises3 {

    /**
     * Wyszukuje element o wartości value w podanej tablicy i zwraca jego indeks.
     * Zwraca -1 jeśli element nie znajduje się w tablicy.
     */
    public int linearSearch(int[] array, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ tablicy i zwraca jego indeks.
     * Zwraca -1 jeśli element nie znajduje się w tablicy.
     */
    public int binarySearch(int[] array, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ liście i zwraca jego indeks.
     */
    public <T> Optional<Integer> indexOf(List<T> list, T value, Comparator<T> comparator) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    /**
     * Wyszukuje wszystkie elementy o wartości value w podanej POSORTOWANEJ tablicy i zwraca zakres ich indeksów.
     *
     * Uwaga: istnieją dwie możliwe implementacje, jedna o stałej złożoności O(log n)
     * oraz druga, która potrafi zdegradować się do złożoności O(n) w najgorszym przypadku.
     */
    public Optional<IntRange> binarySearchRange(int[] array, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public class IntRange {

        private final int start;
        private final int end;

        public IntRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
