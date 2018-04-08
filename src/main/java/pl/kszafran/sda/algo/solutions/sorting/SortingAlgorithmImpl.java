package pl.kszafran.sda.algo.solutions.sorting;

import pl.kszafran.sda.algo.exercises.sorting.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class SortingAlgorithmImpl<T> implements SortingAlgorithm<T> {

    private final Comparator<T> comparator;

    public SortingAlgorithmImpl(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<T> sort(List<T> list) {
        T[] src = (T[]) list.toArray();
        T[] dest = src.clone();
        sort(src, dest, 0, src.length);
        return List.of(dest);
    }

    private void sort(T[] src, T[] dest, int from, int to) {
        if (to - from <= 1) {
            return;
        }
        int mid = (from + to) >>> 1; // http://research.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html
        sort(dest, src, from, mid);
        sort(dest, src, mid, to);
        merge(src, dest, from, mid, to);
    }

    private void merge(T[] src, T[] dest, int from, int mid, int to) {
        int head1 = from;
        int head2 = mid;
        for (int i = from; i < to; i++) {
            if (head1 < mid && (head2 == to || comparator.compare(src[head1], src[head2]) <= 0)) {
                dest[i] = src[head1++];
            } else {
                dest[i] = src[head2++];
            }
        }
    }
}
