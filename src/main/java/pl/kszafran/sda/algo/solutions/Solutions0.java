package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises0;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Solutions0 extends Exercises0 {

    @Override
    public OptionalInt indexOf(String string, char c) {
        int index = string.indexOf(c);
        return index == -1 ? OptionalInt.empty() : OptionalInt.of(index);
    }

    @Override
    public Optional<String> findAuthorByTitle(BookRepository repository, String title) {
        return repository.findByTitle(title).map(Book::getAuthor);
    }

    @Override
    public long numPositive(List<Integer> numbers) {
        return numbers.stream().filter(x -> x > 0).count();
    }

    @Override
    public List<String> authorsOf(Book... books) {
        return Arrays.stream(books)
                .map(Book::getAuthor)
                .sorted()
                .distinct()
                .collect(toList());
    }

    @Override
    public Set<String> keywordsIn(Book... books) {
        return Arrays.stream(books)
                .flatMap(book -> Arrays.stream(book.getTitle().split("\\s+")))
                .map(word -> word.replaceAll("\\W", "").toLowerCase())
                .filter(word -> !word.isEmpty())
                .collect(toSet());
    }

    @Override
    public Map<String, Book> byTitle(List<Book> books) {
        return books.stream().collect(toMap(
                book -> book.getTitle(),
                book -> book));
    }

    @Override
    public Map<String, Set<Book>> byAuthor(List<Book> books) {
        return books.stream().collect(groupingBy(Book::getAuthor, toSet()));
    }

    @Override
    public Optional<Book> findMostEditions(Book... books) {
        return Arrays.stream(books)
                .reduce((book1, book2) -> book2.getNumEditions() > book1.getNumEditions() ? book2 : book1);
    }
}
