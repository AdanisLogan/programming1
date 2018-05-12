package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;
import pl.kszafran.sda.algo.exercises.Exercises0.Book;
import pl.kszafran.sda.algo.exercises.Exercises0.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Exercises0Test {

    private Exercises0 exercises = new Exercises0();

    private Book effectiveJava   = new Book("Effective Java", "Joshua Bloch", 3);
    private Book finnishGrammar  = new Book("Finnish: An Essential Grammar", "Fred Karlsson", 3);
    private Book cleanCode       = new Book("Clean Code", "Robert C. Martin", 1);
    private Book cleanCoder      = new Book("The Clean Coder", "Robert C. Martin", 1);
    private Book javaConcurrency = new Book("Java Concurrency in Practice", "Brian Goetz", 1);

    @Test
    void test_indexOf() {
        assertEquals(6, exercises.indexOf("To jest test", 't').getAsInt());
        assertFalse(exercises.indexOf("To jest test", 'Z').isPresent());
        assertFalse(exercises.indexOf("", 'X').isPresent());
    }

    @Test
    void test_findAuthorByTitle() {
        Map<String, Book> books = Map.of(
                effectiveJava.getTitle(), effectiveJava,
                cleanCode.getTitle(), cleanCode,
                cleanCoder.getTitle(), cleanCoder,
                javaConcurrency.getTitle(), javaConcurrency);

        BookRepository repository = title -> Optional.ofNullable(books.get(title));

        assertEquals("Robert C. Martin", exercises.findAuthorByTitle(repository, "Clean Code").get());
        assertFalse(exercises.findAuthorByTitle(repository, "Programming in Scala").isPresent());
    }

    @Test
    void test_numPositive() {
        assertEquals(5, exercises.numPositive(List.of(-9, 40, 2, 0, 4, -5, 98, 40, -5)));
        assertEquals(0, exercises.numPositive(emptyList()));
        assertEquals(0, exercises.numPositive(List.of(-20)));
    }

    @Test
    void test_titlesOf() {
        assertEquals(
                List.of("Brian Goetz", "Joshua Bloch", "Robert C. Martin"),
                exercises.authorsOf(effectiveJava, cleanCode, cleanCoder, javaConcurrency));

        assertEquals(emptyList(), exercises.authorsOf());
    }

    @Test
    void test_keywordsIn() {
        assertEquals(
                Set.of("effective", "java", "clean", "code", "coder", "the", "finnish", "an", "essential", "grammar"),
                exercises.keywordsIn(effectiveJava, cleanCode, cleanCoder, finnishGrammar));

        assertEquals(Set.of("fake", "title"), exercises.keywordsIn(new Book("Fake - \t Title ", "Me", 0)));
    }

    @Test
    void test_byTitle() {
        assertEquals(
                Map.of(
                        effectiveJava.getTitle(), effectiveJava,
                        cleanCode.getTitle(), cleanCode,
                        cleanCoder.getTitle(), cleanCoder,
                        javaConcurrency.getTitle(), javaConcurrency),
                exercises.byTitle(List.of(effectiveJava, cleanCode, cleanCoder, javaConcurrency)));
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    @Test
    void test_byAuthor() {
        assertEquals(
                Map.of(
                        "Joshua Bloch", Set.of(effectiveJava),
                        "Robert C. Martin", Set.of(cleanCode, cleanCoder),
                        "Brian Goetz", Set.of(javaConcurrency)),
                exercises.byAuthor(List.of(effectiveJava, cleanCode, cleanCoder, javaConcurrency)));
    }

    @Test
    void test_findMostEditions() {
        assertEquals(effectiveJava, exercises.findMostEditions(cleanCode, effectiveJava, finnishGrammar, cleanCoder).get());
        assertEquals(finnishGrammar, exercises.findMostEditions(cleanCode, finnishGrammar, effectiveJava, cleanCoder).get());
        assertEquals(javaConcurrency, exercises.findMostEditions(javaConcurrency).get());
        assertFalse(exercises.findMostEditions().isPresent());
    }
}
