package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;
import pl.kszafran.sda.algo.solutions.Solutions5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.*;

public class Exercises5Test {

    private Exercises5 exercises = new Solutions5();

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

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    @Test
    void test_reverseStack() {
        testReversal(exercises::reverseStack);
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
}
