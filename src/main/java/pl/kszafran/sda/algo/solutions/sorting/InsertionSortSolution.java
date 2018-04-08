package pl.kszafran.sda.algo.solutions.sorting;

import pl.kszafran.sda.algo.exercises.sorting.IntSortingAlgorithm;

public class InsertionSortSolution implements IntSortingAlgorithm {

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int current = i; current > 0 && array[current] < array[current - 1]; current--) {
                swap(array, current, current - 1);
            }
        }
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
