package pl.kszafran.sda.algo.exercises;

import pl.kszafran.sda.algo.exercises.sorting.IntSortingAlgorithm;
import pl.kszafran.sda.algo.exercises.sorting.SelectionSort;
import pl.kszafran.sda.algo.exercises.sorting.SortingAlgorithm;

import java.util.Comparator;

/**
 * Zaimplementuj poniższe algorytmy sortowania.
 *
 * Uwaga: quicksort oraz merge sort najłatwiej zaimplementować z wykorzystaniem rekurencji.
 */
public class Exercises2 {

    public IntSortingAlgorithm createSelectionSort() {
        return new SelectionSort();
    }

    public IntSortingAlgorithm createInsertionSort() {
//        return new InsertionSort();
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public IntSortingAlgorithm createQuicksort() {
//        return new Quicksort();
        throw new UnsupportedOperationException("Not implemented yet");
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    public IntSortingAlgorithm createMergeSort() {
//        return new MergeSort();
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     *  Tworzy object pozwalający sortować Listy dowolnego typu.
     *
     *  Uwaga: Implementacja może wykorzystywać dowolny algorytm sortowania.
     */
    public <T> SortingAlgorithm<T> createSortingAlgorithm(Comparator<T> comparator) {
//        return new SortingAlgorithmImpl<>(comparator);
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
