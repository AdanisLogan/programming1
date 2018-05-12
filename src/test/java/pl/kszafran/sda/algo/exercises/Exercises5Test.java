package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;
import pl.kszafran.sda.algo.exercises.Exercises5.SdaQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.*;

public class Exercises5Test {

    private Exercises5 exercises = new Exercises5();

    @Test
    void test_balancedParens() {
        for (String input : List.of("", "()", "(())", "(()(())())", "(a * (b + c))", "(a+b*(c-d)-(e-f/(g+h)*(k-i))/(l-j+k))")) {
            assertTrue(exercises.balancedParens(input), "Expected true for input: " + input);
        }
        for (String input : List.of("(", ")", ")(", "())(", "(a+b*(c-d)-(e-f/(g+h*(k-i)/(l-j+k")) {
            assertFalse(exercises.balancedParens(input), "Expected false for input: " + input);
        }
    }

    @Test
    void test_balancedAnyParens() {
        for (String input : List.of("", "[(){}]", "[()]{}{[()()]()}", "(a+b*[c-d]-(e-f/{g+h}*(k-i))/[l-j+k])")) {
            assertTrue(exercises.balancedAnyParens(input), "Expected true for input: " + input);
        }
        for (String input : List.of("[", "}", "[[", "}}", "(]", "[(])", "[()]{}[{()()]()}", "(a+b*[c-d]-(e-f/{g+h*(k-i)/[l-j+k")) {
            assertFalse(exercises.balancedAnyParens(input), "Expected false for input: " + input);
        }
    }

    @Test
    void test_reverseQueue() {
        testReversal(exercises::reverseQueue);
    }

    private void testReversal(Consumer<Deque<?>> method) {
        Deque<String> deque = new ArrayDeque<>(List.of("yksi", "kaksi", "kolme", "neljä"));
        method.accept(deque);
        assertArrayEquals(new String[]{"neljä", "kolme", "kaksi", "yksi"}, deque.toArray());

        Deque<String> single = new ArrayDeque<>(singleton("jeden"));
        method.accept(single);
        assertArrayEquals(new String[]{"jeden"}, single.toArray());

        method.accept(new ArrayDeque<>()); // pusta kolekcja nie powoduje błędu
    }

    @Test
    void test_createQueue() {
        assertThrows(IllegalArgumentException.class, () -> exercises.createQueue(2, "alpha", "beta", "gamma"));
        assertNotNull(exercises.createQueue(2, "alpha", "beta"));
    }

    @Test
    void test_isEmpty() {
        assertTrue(exercises.createQueue(16).isEmpty());
        assertFalse(exercises.createQueue(16, "alpha", "beta").isEmpty());
    }

    @Test
    void test_isFull() {
        assertTrue(exercises.createQueue(0).isFull());
        assertFalse(exercises.createQueue(4, "alpha", "beta", "gamma", "delta").isEmpty());
    }

    @Test
    void test_size() {
        assertEquals(0, exercises.createQueue(16).size());
        assertEquals(1, exercises.createQueue(16, "alpha").size());
        assertEquals(3, exercises.createQueue(16, "alpha", "beta", "gamma").size());
    }

    @Test
    void test_peek() {
        assertEquals("alpha", exercises.createQueue(16, "alpha").peek());
        assertEquals("alpha", exercises.createQueue(16, "alpha", "beta", "gamma").peek());
        assertThrows(NoSuchElementException.class, () -> exercises.createQueue(16).peek());
    }

    @Test
    void test_dequeue() {
        assertEquals("alpha", exercises.createQueue(16, "alpha").dequeue());

        SdaQueue<String> queue = exercises.createQueue(16, "alpha", "beta", "gamma");
        assertEquals("alpha", queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals("beta", queue.peek());

        assertEquals("beta", queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals("gamma", queue.peek());

        assertEquals("gamma", queue.dequeue());
        assertEquals(0, queue.size());
        assertThrows(NoSuchElementException.class, queue::peek);
        assertThrows(NoSuchElementException.class, queue::dequeue);

        assertThrows(NoSuchElementException.class, () -> exercises.createQueue(16).peek());
    }

    @Test
    void test_enqueue() {
        SdaQueue<String> queue = exercises.createQueue(4, "alpha");
        queue.enqueue("beta");
        assertEquals("alpha", queue.peek());
        queue.enqueue("gamma");
        queue.enqueue("delta");
        assertTrue(queue.isFull());
        assertThrows(IllegalStateException.class, () -> queue.enqueue("epsilon"));

        assertEquals("alpha", queue.dequeue());
        assertFalse(queue.isFull());
        queue.enqueue("epsilon");
        assertEquals("beta", queue.dequeue());
        assertEquals("gamma", queue.dequeue());
        assertEquals("delta", queue.dequeue());
        assertEquals("epsilon", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    @Test
    void test_reverseStack() {
        testReversal(exercises::reverseStack);
    }

    @Test
    void test_evaluate() {
        assertEquals(4, exercises.evaluate("10 - 6"));
        assertEquals(62, exercises.evaluate("2 * 3 + 7 * 8"));
        assertEquals(10, exercises.evaluate("( 2 + 6 ) * 10 / 8"));
        assertThrows(IllegalArgumentException.class, () -> exercises.evaluate("( 2 + 6 ) * 10 /"));
        assertThrows(IllegalArgumentException.class, () -> exercises.evaluate("( 2 + 6 * 10 / 8"));
        assertThrows(IllegalArgumentException.class, () -> exercises.evaluate("2 + 6 ) * 10 / 8"));
        assertThrows(IllegalArgumentException.class, () -> exercises.evaluate("(2 + 6) * 10 / 8"));
        assertThrows(IllegalArgumentException.class, () -> exercises.evaluate("( 2 + 6 ) * 10 8"));

        assertEquals(120, exercises.evaluate("4 * min ( 30 , max ( 10 , 50 ) )"));
        assertThrows(IllegalArgumentException.class, () -> exercises.evaluate("* min ( 30 , max ( 10 , 50 ) )"));
        assertThrows(IllegalArgumentException.class, () -> exercises.evaluate("4* min ( 30 , max ( 10 , 50 ) )"));
        assertThrows(IllegalArgumentException.class, () -> exercises.evaluate("4 * min ( 30 , 10 , 50 )"));
        assertThrows(IllegalArgumentException.class, () -> exercises.evaluate("4 * min ( 30 , max ( 10 , 50 )"));
        assertThrows(IllegalArgumentException.class, () -> exercises.evaluate("4 * min 30 , max ( 10 , 50 ) )"));
    }
}
