package pl.kszafran.sda.algo.exercises.sorting;

import java.util.List;

public interface SortingAlgorithm<T> {

    /**
     * Zwraca posortowaną listę. Oryginalna lista nie jest modyfikowana.
     */
    List<T> sort(List<T> list);
}
