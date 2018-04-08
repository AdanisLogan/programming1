package pl.kszafran.sda.algo.solutions.sorting;

import pl.kszafran.sda.algo.exercises.sorting.IntSortingAlgorithm;

public class MergeSortSolution implements IntSortingAlgorithm {

    @Override
    public void sort(int[] array) {
        sort(array.clone(), array, 0, array.length);
    }

    private void sort(int[] src, int[] dest, int from, int to) {
        if (to - from <= 1) {
            return;
        }
        int mid = (from + to) >>> 1; // http://research.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html
        sort(dest, src, from, mid);
        sort(dest, src, mid, to);
        merge(src, dest, from, mid, to);
    }

    private void merge(int[] src, int[] dest, int from, int mid, int to) {
        int head1 = from;
        int head2 = mid;
        for (int i = from; i < to; i++) {
            if (head1 < mid && (head2 == to || src[head1] <= src[head2])) {
                dest[i] = src[head1++];
            } else {
                dest[i] = src[head2++];
            }
        }
    }
}
