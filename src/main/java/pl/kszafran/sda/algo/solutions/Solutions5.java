package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises5;

import java.util.ArrayDeque;
import java.util.Deque;

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
}
