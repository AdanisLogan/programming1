package pl.kszafran.sda.algo.exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Zaimplementuj poniższe metody operujące na liście wiązanej jednokierunkowej.
 */
public class Exercises4 {

    /**
     * Tworzy nową listę zawierającą podane elementy.
     */
    public <T> SdaList<T> createList(T... elements) {
        return new SdaLinkedList<>(elements);
    }

    public interface SdaList<T> extends Iterable<T> {

        /**
         * Zwraca true jeśli lista jest pusta.
         */
        boolean isEmpty();

        /**
         * Zwraca rozmiar listy (ilość elementów).
         */
        int size();

        /**
         * Zwraca pierwszy element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        T getFirst();

        /**
         * Zwraca ostatni element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        T getLast();

        /**
         * Pobiera element listy pod podanym indeksem.
         *
         * @throws IndexOutOfBoundsException jeśli indeks leży poza zakresem listy
         */
        T get(int index);

        /**
         * Usuwa wszystkie elementy z listy.
         */
        void clear();

        /**
         * Dodaje nowy element na początku listy.
         */
        void addFirst(T element);

        /**
         * Dodaje nowy element na końcu listy.
         */
        void addLast(T element);

        /**
         * Usuwa pierwszy element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        void removeFirst();

        /**
         * Usuwa ostatni element listy.
         *
         * @throws NoSuchElementException jeśli lista jest pusta
         */
        void removeLast();

        ////////////////////////////////////////////
        //                                        //
        // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
        //                                        //
        ////////////////////////////////////////////

        /**
         * Zwraca iterator po elementach listy.
         *
         * @see java.lang.Iterable
         */
        @Override
        Iterator<T> iterator();

        /**
         * Zamienia element pod podanym indeksem.
         */
        void setAt(int index, T element);

        /**
         * Dodaje nowy element pod wskazanym indeksem.
         *
         * @throws IndexOutOfBoundsException jeśli indeks leży poza zakresem listy
         */
        void addAt(int index, T element);

        /**
         * Usuwa element pod wskazanym indeksem.
         *
         * @throws IndexOutOfBoundsException jeśli indeks leży poza zakresem listy
         */
        void removeAt(int index);
    }

    private static class SdaLinkedList<T> implements SdaList<T> {

        private Node<T> head;

        public SdaLinkedList(T[] elements) {
            for (int i = elements.length - 1; i >= 0; i--) {
                Node<T> node = new Node<>(elements[i], head);
                head = node;

            }
        }

        @Override
        public boolean isEmpty() {
            if (head == null) {
                return true;
            }
            return false;
        }

        @Override
        public int size() {
            int counter = 0;
            return checkSize(head, counter);
        }

        private int checkSize(Node<T> head, int counter) {
            if (head != null) {
                counter = checkSize(head.next, counter + 1);
                return counter;
            }
            return counter;
        }

        @Override
        public T getFirst() {
            if (head == null) {
                throw new java.util.NoSuchElementException();
            }
            return head.element;
        }

        @Override
        public T getLast() {
            return getLastElement(head).element;
        }

        private Node<T> getLastElement(Node<T> head) {
            if (head == null) {
                throw new java.util.NoSuchElementException();
            } else if (head.next == null) {
                return head;
            } else {
                return getLastElement(head.next);
            }
        }

        @Override
        public T get(int index) {
            if (getIndex(head, index).element != null) {
                return getIndex(head, index).element;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        private Node<T> getIndex(Node<T> head, int index) {
            if (head == null) {
                throw new IndexOutOfBoundsException();
            }
            if (index == 0) {
                return head;
            } else if (index > 0) {
                return getIndex(head.next, index - 1);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public void clear() {
            head = null;
        }

        @Override
        public void addFirst(T element) {
            Node<T> node = new Node<>(element, head);
            head = node;
        }

        @Override
        public void addLast(T element) {
            if (head == null) {
                addFirst(element);
            } else {
                Node<T> node = getIndex(head, size() - 1);
                node.next = new Node<>(element, null);
            }
        }


        @Override
        public void removeFirst() {
            if (head == null) {
                throw new NoSuchElementException();
            }
            Node<T> node = head;
            head = head.next;
            node = null;
        }

        @Override
        public void removeLast() {
            if (head == null) {
                throw new NoSuchElementException();
            } else if (head.next == null) {
                head = null;
            } else {
                Node<T> node = getIndex(head, size() - 2);
                if (node == head) {
                    removeFirst();
                } else {
                    node.next = null;
                }
            }
        }

        ////////////////////////////////////////////
        //                                        //
        // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
        //                                        //
        ////////////////////////////////////////////

        @Override
        public Iterator<T> iterator() {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public void setAt(int index, T element) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public void addAt(int index, T element) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public void removeAt(int index) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        private static class Node<T> {

            private T element;
            private Node<T> next;

            private Node(T element, Node<T> next) {
                this.element = element;
                this.next = next;
            }
        }
    }
}
