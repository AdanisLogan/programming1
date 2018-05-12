package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;
import pl.kszafran.sda.algo.exercises.Exercises6.SdaTree;

import java.util.List;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercises6Test {

    private Exercises6 exercises = new Exercises6();

    // takie samo drzewo jak na prezentacji
    private SdaTree<String> exampleTree =
            SdaTree.of("F",
                    SdaTree.of("B",
                            SdaTree.leaf("A"),
                            SdaTree.of("D",
                                    SdaTree.leaf("C"),
                                    SdaTree.leaf("E"))),
                    SdaTree.of("G",
                            null,
                            SdaTree.of("I",
                                    SdaTree.leaf("H"),
                                    null)));

    @Test
    void test_traversePreOrder() {
        assertEquals(List.of("F", "B", "A", "D", "C", "E", "G", "I", "H"), exercises.traversePreOrder(exampleTree));
        assertEquals(List.of(20), exercises.traversePreOrder(SdaTree.leaf(20)));
    }

    @Test
    void test_traverseInOrder() {
        assertEquals(List.of("A", "B", "C", "D", "E", "F", "G", "H", "I"), exercises.traverseInOrder(exampleTree));
        assertEquals(List.of(20), exercises.traverseInOrder(SdaTree.leaf(20)));
    }

    @Test
    void test_traversePostOrder() {
        assertEquals(List.of("A", "C", "E", "D", "B", "H", "I", "G", "F"), exercises.traversePostOrder(exampleTree));
        assertEquals(List.of(20), exercises.traversePostOrder(SdaTree.leaf(20)));
    }

    @Test
    void test_traversePreOrderIterative() {
        assertEquals(List.of("F", "B", "A", "D", "C", "E", "G", "I", "H"), exercises.traversePreOrderIterative(exampleTree));
        assertEquals(List.of(20), exercises.traversePreOrderIterative(SdaTree.leaf(20)));
    }

    @Test
    void test_traverseLevelOrder() {
        assertEquals(List.of("F", "B", "G", "A", "D", "I", "C", "E", "H"), exercises.traverseLevelOrder(exampleTree));
        assertEquals(List.of(20), exercises.traverseLevelOrder(SdaTree.leaf(20)));
    }

    @Test
    void test_countLeaves() {
        assertEquals(4, exercises.countLeaves(exampleTree));
        assertEquals(3, exercises.countLeaves(exampleTree.getLeftChild().get()));
        assertEquals(1, exercises.countLeaves(exampleTree.getRightChild().get()));
        assertEquals(1, exercises.countLeaves(SdaTree.leaf(42)));
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    @Test
    void test_calcHeight() {
        assertEquals(3, exercises.calcHeight(exampleTree));
        assertEquals(2, exercises.calcHeight(exampleTree.getLeftChild().get()));
        assertEquals(0, exercises.calcHeight(SdaTree.leaf("test")));
    }

    @Test
    void test_findMax() {
        assertEquals("I", exercises.findMax(exampleTree, naturalOrder()));
        assertEquals(42, (int) exercises.findMax(SdaTree.leaf(42), naturalOrder()));

        SdaTree<Integer> tree = SdaTree.of(1,
                SdaTree.of(3,
                        null,
                        SdaTree.of(6,
                                null,
                                SdaTree.leaf(10))),
                SdaTree.of(2,
                        SdaTree.of(4,
                                SdaTree.leaf(7),
                                SdaTree.leaf(8)),
                        SdaTree.of(6,
                                null,
                                SdaTree.leaf(9))));

        assertEquals(10, (int) exercises.findMax(tree, naturalOrder()));
        assertEquals(1, (int) exercises.findMax(tree, reverseOrder()));
    }
}
