package pl.kszafran.sda.algo.solutions.sorting;

import pl.kszafran.sda.algo.exercises.sorting.IntSortingAlgorithm;

public class QuicksortSolution implements IntSortingAlgorithm {

    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length);
    }

    private void sort(int[] array, int from, int to) {
        if (from == to) {
            return;
        }
        int pivot = array[from];
        int store = from;
        for (int i = from + 1; i < to; i++) {
            if (array[i] < pivot) {
                swap(array, i, ++store);
            }
        }
        swap(array, from, store);
        sort(array, from, store);
        sort(array, store + 1, to);
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
