package com.dijkstra.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public final class ShuntingYard {
    private final Deque<Character> stack = new ArrayDeque<>();
    private final StringBuilder output = new StringBuilder();

    public String infixToPostfix(String infix) {
        if (infix == null || infix.trim().isEmpty()) {
            throw new IllegalArgumentException("Input expression cannot be null or empty");
        }

        String expression = infix.replaceAll("\\s+", "");
        List<Character> expressionTokens = new LinkedList<>();

        for (Character c : expression.toCharArray()) {
            expressionTokens.add(c);
        }

        for (Character inputToken : expressionTokens) {
            if (!Tokenizer.isOperator(inputToken)) {
                handleOperand(inputToken);
            } else if (inputToken == '(') {
                stack.push(inputToken);
            } else if (inputToken == ')') {
                handleClosingParenthesis();
            } else {
                handleOperator(inputToken);
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        return output.toString();
    }

    private void handleOperand(Character operand) {
        output.append(operand);
    }

    private void handleClosingParenthesis() {
        while (!stack.isEmpty() && stack.peek() != '(') {
            output.append(stack.pop());
        }
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Mismatched parentheses");
        }
        stack.pop();
    }

    private void handleOperator(Character operator) {
        while (!stack.isEmpty() &&
                stack.peek() != '(' &&
                hasHigherOrEqualPrecedence(stack.peek(), operator)) {
            output.append(stack.pop());
        }
        stack.push(operator);
    }

    private boolean hasHigherOrEqualPrecedence(Character stackOp, Character inputOp) {
        int stackPriority = Priorities.getStackPriority(stackOp);
        int inputPriority = Priorities.getInputPriority(inputOp);

        if (stackPriority == inputPriority) {
            return Priorities.isLeftAssociative(stackOp);
        }
        return stackPriority > inputPriority;
    }
}