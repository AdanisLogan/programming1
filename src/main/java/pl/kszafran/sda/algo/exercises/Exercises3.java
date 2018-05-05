package pl.kszafran.sda.algo.exercises;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercises3 {

    /**
     * Znajduje wszystkie pliki o nazwie pasującej do podanego wyrażenia regularnego
     * w podanym katalogu i wszystkich jego podkatalogach.
     *
     * Zwrócone pliki są posortowane alfabetycznie po pełnej ścieżce (nie tylko nazwie pliku).
     *
     * Jeśli directory wskazuje na plik, zwrócona lista ma zawierać tylko ten plik
     * (jeśli jego nazwa pasuje do wyrażenia regularnego).
     *
     * Uwaga: należy skupić się na klasach z pakietu java.io.
     */
    public List<File> findFiles(File directory, String regex) throws IOException  {
        List<File> filesFinal = new ArrayList<>();
        if(!directory.exists()){
            return filesFinal;
        }
        if (directory.isDirectory()){
            List<File> files1 = Arrays.asList(directory.listFiles());
            for (File file : files1) {
                if (file.isFile()) {
                    if(file.getName().matches(regex)){
                        filesFinal.add(file);
                    }
                } else {
                    filesFinal.addAll(findFiles(file, regex));
                }
            }

        } else if (directory.isFile()){
            filesFinal.add(directory);
        }


        return filesFinal;
    }

    /**
     * Działa tak samo jak findFiles().
     *
     * Uwaga: należy korzystac z dobrodziejstw pakietu java.nio.file.
     */
    public Stream<Path> findFilesNIO(Path directory, Pattern regex) throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

