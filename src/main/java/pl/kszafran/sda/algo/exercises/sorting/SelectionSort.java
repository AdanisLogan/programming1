package pl.kszafran.sda.algo.exercises.sorting;

public class SelectionSort implements IntSortingAlgorithm {

    @Override
    public void sort(int[] array) {
        for (int j = 0; j < array.length; j++) {
            int min = array[j];
            int minInd = j;
            for (int i = j; i < array.length; i++) {
                if (array[i] < min) {
                    minInd = i;
                    min = array[i];
                }
            }
            swap(j, minInd, array);
        }
    }


    public void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
