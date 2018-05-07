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
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ tablicy i zwraca jego indeks.
     * Zwraca -1 jeśli element nie znajduje się w tablicy.
     */
    public int binarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        if (array.length < 1) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            int mid = (low + high) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else if (array[mid] > value) {
                high = mid;
            }
        }
        return -1;
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ liście i zwraca jego indeks.
     */
    public <T> Optional<Integer> indexOf(List<T> list, T value, Comparator<T> comparator) {
        int low = 0;
        int high = list.size() - 1;
        for (int i = 0; i < list.size(); i++) {
            int mid = (low + high) / 2;
            if (comparator.compare(list.get(mid), value) > 0) {
                high = mid;
            } else {
                if (list.get(mid) == value) {
                    return Optional.ofNullable(mid);
                }
                low = mid + 1;
            }
        }
        return Optional.empty();
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    /**
     * Wyszukuje wszystkie elementy o wartości value w podanej POSORTOWANEJ tablicy i zwraca zakres ich indeksów.
     * <p>
     * Uwaga: istnieją dwie możliwe implementacje, jedna o stałej złożoności O(log n)
     * oraz druga, która potrafi zdegradować się do złożoności O(n) w najgorszym przypadku.
     */
    public Optional<IntRange> binarySearchRange(int[] array, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ tablicy i zwraca jego indeks.
     * Zwraca -1 jeśli element nie znajduje się w tablicy.
     * <p>
     * Uwaga: należy zastosować wyszukiwanie interpolacyjne (interpolation search).
     */
    public int interpolationSearch(int[] array, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static class IntRange {

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
