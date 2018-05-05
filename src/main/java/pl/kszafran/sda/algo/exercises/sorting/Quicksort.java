package pl.kszafran.sda.algo.exercises.sorting;

public class Quicksort implements IntSortingAlgorithm {

    @Override
    public void sort(int[] array) {
        quickSortArray(0,array.length,array);
    }


    public void quickSortArray(int startingPoint,int endingPoint,int[] arr){
        if(endingPoint-startingPoint<=1){
            return;
        }
        int diff = 1;

        for (int i = startingPoint; i < endingPoint; i++) {
            if (arr[startingPoint]>arr[i]){
                swap(i,startingPoint+diff,arr);
                diff++;
            }
        }
        int pivot = startingPoint + diff - 1;
        swap(startingPoint,pivot,arr);
        quickSortArray(startingPoint,pivot,arr);
        quickSortArray(pivot+1,endingPoint,arr);
    }

    public void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
