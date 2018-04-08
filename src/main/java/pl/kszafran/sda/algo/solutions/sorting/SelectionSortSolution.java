package pl.kszafran.sda.algo.solutions.sorting;

import pl.kszafran.sda.algo.exercises.sorting.IntSortingAlgorithm;

public class SelectionSortSolution implements IntSortingAlgorithm {

    @Override
    public void sort(int[] array) {
        for (int offset = 0; offset < array.length - 1; offset++) {
            int min = offset;
            for (int i = offset; i < array.length; i++) {
                if (array[i] < array[min]) {
                    min = i;
                }
            }
            swap(array, offset, min);
        }
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
