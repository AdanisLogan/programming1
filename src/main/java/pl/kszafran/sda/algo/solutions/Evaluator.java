package pl.kszafran.sda.algo.solutions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.toMap;

public class Evaluator {

    public int evaluate(String expression) {
        return evaluatePostfix(convertToPostfix(expression));
    }

    private Deque<Object> convertToPostfix(String expression) {
        Deque<Object> output = new ArrayDeque<>();
        Deque<Object> stack = new ArrayDeque<>();

        for (String token : expression.split("\\s+")) {
            if (isNumber(token)) {
                handleNumber(output, Integer.valueOf(token));
            } else if (isLeftParen(token)) {
                handleLeftParen(stack);
            } else if (isRightParen(token)) {
                handleRightParen(output, stack);
            } else if (isSeparator(token)) {
                handleSeparator(output, stack);
            } else if (isOperator(token)) {
                handleOperator(output, stack, Operator.bySymbol(token));
            } else if (isFunction(token)) {
                handleFunction(stack, Function.byName(token));
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        while (!stack.isEmpty()) {
            Object object = stack.pop();
            if (object == Punctuation.LEFT_PAREN) {
                throw new IllegalArgumentException("Unbalanced parentheses");
            }
            output.offer(object);
        }

        return output;
    }

    private int evaluatePostfix(Deque<Object> output) {
        Deque<Integer> stack = new ArrayDeque<>();

        while (!output.isEmpty()) {
            Object object = output.poll();
            if (object instanceof Integer) {
                stack.push((Integer) object);
            } else if (object instanceof Operator) {
                stack.push(evalOperator(stack, (Operator) object));
            } else {
                stack.push(evalFunction(stack, (Function) object));
            }
        }

        int result = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Too many arguments");
        }
        return result;
    }

    private void handleNumber(Deque<Object> output, Integer number) {
        output.offer(number);
    }

    private void handleLeftParen(Deque<Object> stack) {
        stack.push(Punctuation.LEFT_PAREN);
    }

    private void handleRightParen(Deque<Object> output, Deque<Object> stack) {
        while (!stack.isEmpty() && stack.peek() != Punctuation.LEFT_PAREN) {
            output.offer(stack.pop());
        }
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Unbalanced parentheses");
        }
        stack.pop();
        if (stack.peek() instanceof Function) {
            output.offer(stack.pop());
        }
    }

    private void handleSeparator(Deque<Object> output, Deque<Object> stack) {
        while (!stack.isEmpty() && stack.peek() != Punctuation.LEFT_PAREN) {
            output.offer(stack.pop());
        }
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Unbalanced parentheses or misplaced separator");
        }
    }

    private void handleOperator(Deque<Object> output, Deque<Object> stack, Operator operator) {
        while (!stack.isEmpty() && operator.hasLowerOrEqualPrecedence(stack.peek())) {
            output.offer(stack.pop());
        }
        stack.push(operator);
    }

    private void handleFunction(Deque<Object> stack, Function function) {
        stack.push(function);
    }

    private int evalOperator(Deque<Integer> stack, Operator token) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        // zauważ, że argumenty pobieramy ze stosu w odwrotnej kolejności
        int b = stack.pop();
        int a = stack.pop();
        return token.calc(a, b);
    }

    private int evalFunction(Deque<Integer> stack, Function function) {
        if (stack.size() < function.getArgCount()) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        // zauważ, że argumenty pobieramy ze stosu w odwrotnej kolejności
        int[] args = new int[function.getArgCount()];
        for (int i = args.length - 1; i >= 0; i--) {
            args[i] = stack.pop();
        }
        return function.calc(args);
    }

    private boolean isNumber(String token) {
        return token.matches("-?\\d+");
    }

    private boolean isLeftParen(String token) {
        return Punctuation.LEFT_PAREN.matches(token);
    }

    private boolean isRightParen(String token) {
        return Punctuation.RIGHT_PAREN.matches(token);
    }

    private boolean isSeparator(String token) {
        return Punctuation.SEPARATOR.matches(token);
    }

    private boolean isOperator(String token) {
        return Operator.bySymbol(token) != null;
    }

    private boolean isFunction(String token) {
        return Function.byName(token) != null;
    }

    private enum Punctuation {
        LEFT_PAREN("("),
        RIGHT_PAREN(")"),
        SEPARATOR(",");

        private final String symbol;

        Punctuation(String symbol) {
            this.symbol = symbol;
        }

        public boolean matches(String token) {
            return symbol.equals(token);
        }
    }

    private enum Operator {
        PLUS("+", 1, (a, b) -> a + b),
        MINUS("-", 1, (a, b) -> a - b),
        TIMES("*", 2, (a, b) -> a * b),
        DIVIDE("/", 2, (a, b) -> a / b);

        // W Javie 10 możemy napisać bezpośrednio: .collect(toUnmodifiableMap(...))
        // Z wykorzystaniem Guavy, możemy napisać: Maps.uniqueIndex(Arrays.asList(values()), Operator::getSymbol))
        private static final Map<String, Operator> BY_SYMBOL =
                unmodifiableMap(Arrays.stream(values()).collect(toMap(
                        operator -> operator.symbol,
                        operator -> operator)));

        private final String symbol;
        private final int precedence;
        private final IntBiFunction operation;

        Operator(String symbol, int precedence, IntBiFunction operation) {
            this.symbol = symbol;
            this.precedence = precedence;
            this.operation = operation;
        }

        public int calc(int a, int b) {
            return operation.apply(a, b);
        }

        public static Operator bySymbol(String token) {
            return BY_SYMBOL.get(token);
        }

        public boolean hasLowerOrEqualPrecedence(Object object) {
            return object instanceof Operator && precedence <= ((Operator) object).precedence;
        }
    }

    private enum Function {
        MIN("min", 2, args -> Math.min(args[0], args[1])),
        MAX("max", 2, args -> Math.max(args[0], args[1]));

        private static final Map<String, Function> BY_NAME =
                unmodifiableMap(Arrays.stream(values()).collect(toMap(
                        function -> function.name,
                        function -> function)));

        private final String name;
        private final int argCount;
        private final IntVarargsFunction function;

        Function(String name, int argCount, IntVarargsFunction function) {
            this.name = name;
            this.argCount = argCount;
            this.function = function;
        }

        public int getArgCount() {
            return argCount;
        }

        public int calc(int[] args) {
            return function.apply(args);
        }

        public static Function byName(String token) {
            return BY_NAME.get(token);
        }
    }

    private interface IntBiFunction {
        int apply(int a, int b);
    }

    private interface IntVarargsFunction {
        int apply(int[] args);
    }
}
