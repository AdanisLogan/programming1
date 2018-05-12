package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;
import pl.kszafran.sda.algo.exercises.Exercises4.SdaList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class Exercises4Test {

    private Exercises4 exercises = new Exercises4();

    @Test
    void test_isEmpty() {
        assertTrue(exercises.createList().isEmpty());
        assertFalse(exercises.createList(1, 2).isEmpty());
    }

    @Test
    void test_size() {
        assertEquals(0, exercises.createList().size());
        assertEquals(1, exercises.createList(3).size());
        assertEquals(3, exercises.createList(3, 8, -1).size());
    }

    @Test
    void test_getFirst() {
        assertEquals(3, (int) exercises.createList(3).getFirst());
        assertEquals(3, (int) exercises.createList(3, 6, 1).getFirst());
        assertThrows(NoSuchElementException.class, () -> exercises.createList().getFirst());
    }

    @Test
    void test_getLast() {
        assertEquals(3, (int) exercises.createList(3).getLast());
        assertEquals(1, (int) exercises.createList(3, 6, 1).getLast());
        assertThrows(NoSuchElementException.class, () -> exercises.createList().getLast());
    }

    @Test
    void test_get() {
        SdaList<Integer> list = exercises.createList(3, 6, 1);
        assertEquals(3, (int) list.get(0));
        assertEquals(6, (int) list.get(1));
        assertEquals(1, (int) list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> exercises.createList().get(-1));
    }

    @Test
    void test_clear() {
        SdaList<Integer> list = exercises.createList(3, 6, 1);
        list.clear();
        assertTrue(list.isEmpty());

        exercises.createList().clear(); // pusta lista nie powoduje wyjątku
    }

    @Test
    void test_addFirst() {
        SdaList<String> list = exercises.createList();
        list.addFirst("yksi");
        list.addFirst("kaksi");
        list.addFirst("kolme");

        assertEquals("kolme", list.get(0));
        assertEquals("kaksi", list.get(1));
        assertEquals("yksi", list.get(2));
    }

    @Test
    void test_addLast() {
        SdaList<String> list = exercises.createList();
        list.addLast("yksi");
        list.addLast("kaksi");
        list.addLast("kolme");

        assertEquals("yksi", list.get(0));
        assertEquals("kaksi", list.get(1));
        assertEquals("kolme", list.get(2));
    }

    @Test
    void test_removeFirst() {
        SdaList<Integer> list = exercises.createList(3, 9, -20);

        list.removeFirst();
        assertEquals(9, (int) list.get(0));
        assertEquals(-20, (int) list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));

        list.removeFirst();
        list.removeFirst();
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    void test_removeLast() {
        SdaList<Integer> list = exercises.createList(3, 9, -20);

        list.removeLast();
        assertEquals(3, (int) list.get(0));
        assertEquals(9, (int) list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));

        list.removeLast();
        list.removeLast();
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, list::removeLast);
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    @Test
    void test_iterator() {
        Iterator<Integer> iterator = exercises.createList(4, 1, 9).iterator();
        assertTrue(iterator.hasNext());
        assertEquals(4, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(1, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(9, (int) iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);

        Iterator<?> empty = exercises.createList().iterator();
        assertFalse(empty.hasNext());
        assertThrows(NoSuchElementException.class, empty::next);

        // Uwaga: dzięki temu, że nasz interfejs rozszerza Iterable, możemy używać pętli for-each:
        for (String element : exercises.createList("yksi", "kaksi", "kolme")) {
            // ...
        }
    }

    @Test
    void test_setAt() {
        SdaList<String> list = exercises.createList("yksi", "kaksi", "kolme");
        list.setAt(0, "jeden"); // pierwszy indeks
        list.setAt(1, "dwa"); // środkowy indeks
        list.setAt(2, "trzy"); // ostatni indeks
        assertEquals("jeden", list.get(0));
        assertEquals("dwa", list.get(1));
        assertEquals("trzy", list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.setAt(-1, "zero"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.setAt(3, "cztery"));
        assertThrows(IndexOutOfBoundsException.class, () -> exercises.createList().setAt(0, "jeden"));
    }

    @Test
    void test_addAt() {
        SdaList<String> list = exercises.createList();
        list.addAt(0, "kolme"); // pusta lista
        list.addAt(0, "yksi"); // pierwszy indeks
        list.addAt(1, "kaksi"); // środkowy indeks
        list.addAt(3, "neljä"); // ostatni indeks

        assertEquals("yksi", list.get(0));
        assertEquals("kaksi", list.get(1));
        assertEquals("kolme", list.get(2));
        assertEquals("neljä", list.get(3));

        assertThrows(IndexOutOfBoundsException.class, () -> list.addAt(-1, "älä"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAt(5, "älä"));
        assertThrows(IndexOutOfBoundsException.class, () -> exercises.createList().addAt(-1, "älä"));
    }

    @Test
    void test_removeAt() {
        SdaList<String> list = exercises.createList("yksi", "kaksi", "kolme", "neljä");

        list.removeAt(1); // środkowy element
        assertEquals("yksi", list.get(0));
        assertEquals("kolme", list.get(1));
        assertEquals("neljä", list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));

        list.removeAt(2); // ostatni element
        assertEquals("yksi", list.get(0));
        assertEquals("kolme", list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));

        list.removeAt(0); // pierwszy element
        assertEquals("kolme", list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(-1));

        list.removeAt(0);
        assertTrue(list.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> exercises.createList().removeAt(0)); // pusta lista
    }
}
