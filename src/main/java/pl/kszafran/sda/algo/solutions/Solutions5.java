package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises5;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.NoSuchElementException;

public class Solutions5 extends Exercises5 {

    @Override
    public boolean balancedParens(String input) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : input.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    @Override
    public boolean balancedAnyParens(String input) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : input.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                return false;
            } else if (c == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                return false;
            } else if (c == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    @Override
    public <T> void reverseQueue(Deque<T> queue) {
        if (!queue.isEmpty()) {
            T element = queue.poll();
            reverseQueue(queue);
            queue.offer(element);
        }
    }

    @Override
    public <T> SdaQueue<T> createQueue(int capacity, T... elements) {
        return new SdaCircularBuffer<T>(capacity, elements);
    }

    @Override
    public <T> void reverseStack(Deque<T> stack) {
        if (!stack.isEmpty()) {
            T top = stack.pop();
            reverseStack(stack);
            pushToBottom(stack, top);
        }
    }

    private <T> void pushToBottom(Deque<T> stack, T bottom) {
        if (stack.isEmpty()) {
            stack.push(bottom);
        } else {
            T top = stack.pop();
            pushToBottom(stack, bottom);
            stack.push(top);
        }
    }

    @Override
    public int evaluate(String expression) {
        return new Evaluator().evaluate(expression);
    }

    private static class SdaCircularBuffer<T> implements SdaQueue<T> {

        private final T[] elements;
        private int offset;
        private int size;

        public SdaCircularBuffer(int capacity, T[] elements) {
            if (elements.length > capacity) {
                throw new IllegalArgumentException("Too many elements: " + elements.length + ", capacity: " + capacity);
            }
            this.elements = Arrays.copyOf(elements, capacity);
            this.size = elements.length;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public boolean isFull() {
            return size == elements.length;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            return elements[offset];
        }

        @Override
        public T dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            T element = elements[offset];
            elements[offset] = null; // pozwólmy garbage collectorowi robić swoje
            offset = (offset + 1) % elements.length;
            size--;
            return element;
        }

        @Override
        public void enqueue(T element) {
            if (isFull()) {
                throw new IllegalStateException("Queue is full: " + size);
            }
            elements[(offset + size) % elements.length] = element;
            size++;
        }
    }
}
