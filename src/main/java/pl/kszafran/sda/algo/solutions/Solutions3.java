package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises3;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Comparator.comparing;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Solutions3 extends Exercises3 {

    @Override
    public List<File> findFiles(File directory, String regex) throws IOException {
        if (directory.isFile()) {
            return directory.getName().matches(regex) ? singletonList(directory) : emptyList();
        }

        File[] files = directory.listFiles();
        if (files == null) {
            return emptyList();
        }

        List<File> found = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                found.addAll(findFiles(file, regex));
            } else if (file.getName().matches(regex)) {
                found.add(file);
            }
        }
        found.sort(comparing(File::getAbsolutePath));
        return found;
    }

    @Override
    public Stream<Path> findFilesNIO(Path directory, Pattern regex) throws IOException {
        if (!Files.exists(directory)) {
            return Stream.empty();
        }
        return Files.walk(directory)
                .filter(path -> regex.matcher(path.getFileName().toString()).matches())
                .sorted();
    }
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

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1; // http://research.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html
            if (array[mid] > value) {
                high = mid - 1;
            } else if (array[mid] < value) {
                low = mid + 1;
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

        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (comparator.compare(list.get(mid), value) > 0) {
                high = mid - 1;
            } else if (comparator.compare(list.get(mid), value) < 0) {
                low = mid + 1;
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

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (array[mid] > value) {
                high = mid - 1;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else if (boundary == Boundary.START && mid > 0 && array[mid - 1] == array[mid]) {
                high = mid - 1;
            } else if (boundary == Boundary.END && mid < array.length - 1 && array[mid + 1] == array[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Override
    public int interpolationSearch(int[] array, int value) {
        if (array.length == 0) {
            return -1;
        }

        int low = 0;
        int high = array.length - 1;

        while (low < high && value >= array[low] && value <= array[high]) {
            int mid = low + (high - low) * (value - array[low]) / (array[high] - array[low]);
            if (array[mid] > value) {
                high = mid - 1;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return value == array[low] ? low : -1;
    }

    private enum Boundary {
        START, END
    }
}
