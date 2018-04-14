package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises3;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Solutions3 extends Exercises3 {

    @Override
    public int linearSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int binarySearch(int[] array, int value) {
        if (array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (array[mid] > value) {
                right = mid - 1;
            } else if (array[mid] < value) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Override
    public <T> Optional<Integer> indexOf(List<T> list, T value, Comparator<T> comparator) {
        if (list.isEmpty()) {
            return Optional.empty();
        }

        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (comparator.compare(list.get(mid), value) > 0) {
                right = mid - 1;
            } else if (comparator.compare(list.get(mid), value) < 0) {
                left = mid + 1;
            } else {
                return Optional.of(mid);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<IntRange> binarySearchRange(int[] array, int value) {
        int start = findBoundary(array, value, Boundary.START);
        return start == -1
                ? Optional.empty()
                : Optional.of(new IntRange(start, findBoundary(array, value, Boundary.END)));
    }

    private int findBoundary(int[] array, int value, Boundary boundary) {
        if (array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (array[mid] > value) {
                right = mid - 1;
            } else if (array[mid] < value) {
                left = mid + 1;
            } else if (boundary == Boundary.START && mid > 0 && array[mid - 1] == array[mid]) {
                right = mid - 1;
            } else if (boundary == Boundary.END && mid < array.length - 1 && array[mid + 1] == array[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private enum Boundary {
        START, END
    }
}
