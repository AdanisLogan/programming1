package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises2;
import pl.kszafran.sda.algo.exercises.sorting.IntSortingAlgorithm;
import pl.kszafran.sda.algo.exercises.sorting.SortingAlgorithm;
import pl.kszafran.sda.algo.solutions.sorting.*;

import java.util.Comparator;

public class Solutions2 extends Exercises2 {

    @Override
    public IntSortingAlgorithm createSelectionSort() {
        return new SelectionSortSolution();
    }

    @Override
    public IntSortingAlgorithm createInsertionSort() {
        return new InsertionSortSolution();
    }

    @Override
    public IntSortingAlgorithm createQuicksort() {
        return new QuicksortSolution();
    }

    @Override
    public IntSortingAlgorithm createMergeSort() {
        return new MergeSortSolution();
    }

    @Override
    public <T> SortingAlgorithm<T> createSortingAlgorithm(Comparator<T> comparator) {
        return new SortingAlgorithmImpl<>(comparator);
    }
}
