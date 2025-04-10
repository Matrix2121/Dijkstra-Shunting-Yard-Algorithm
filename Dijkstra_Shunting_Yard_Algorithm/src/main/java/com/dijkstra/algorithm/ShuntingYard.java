package com.dijkstra.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class ShuntingYard {
    private static Visualizer visualizer = new Visualizer();
    private static Scanner scanner = new Scanner(System.in);

    private static String input = new String();
    private static Deque<Character> stack = new ArrayDeque<>();
    private static StringBuilder output = new StringBuilder();

    public static String infixToPostfix(String infix) {
        resetState();

        if (infix == null || infix.trim().isEmpty()) {
            throw new IllegalArgumentException("Input expression cannot be null or empty");
        }

        input = infix.replaceAll("\\s+", "");
        List<Character> expressionTokens = new LinkedList<>();

        for (Character c : input.toCharArray()) {
            expressionTokens.add(c);
        }

        visualizer.setConstants(expressionTokens);
        visualizer.print(input, stack, output.toString());
        scanner.nextLine();

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

            input = input.substring(1);
            visualizer.print(input, stack, output.toString());
            scanner.nextLine();
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop());
            visualizer.print(input, stack, output.toString());
            scanner.nextLine();
        }

        visualizer.printFinal(infix, output.toString());
        return output.toString();
    }

    private static void handleOperand(Character operand) {
        output.append(operand);
    }

    private static void handleClosingParenthesis() {
        while (!stack.isEmpty() && stack.peek() != '(') {
            output.append(stack.pop());
            visualizer.print(input, stack, output.toString());
            scanner.nextLine();
        }
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Mismatched parentheses");
        }
        stack.pop();
    }

    private static void handleOperator(Character operator) {
        while (!stack.isEmpty() && stack.peek() != '(' && hasHigherOrEqualPriority(stack.peek(), operator)) {
            output.append(stack.pop());
        }
        stack.push(operator);
    }

    private static boolean hasHigherOrEqualPriority(Character stackOp, Character inputOp) {
        int stackPriority = Priorities.getStackPriority(stackOp);
        int inputPriority = Priorities.getInputPriority(inputOp);

        if (stackPriority == inputPriority) {
            return true;
        }
        return stackPriority > inputPriority;
    }

    private static void resetState() {
        input = "";
        stack.clear();
        output.setLength(0);
    }
}