package com.dijkstra.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public final class ShuntingYard {
    private final Deque<Character> stack = new ArrayDeque<>();
    private final StringBuilder output = new StringBuilder();

    public String infixToPostfix(String infix) {
        if (infix == null || infix.trim().isEmpty()) { // checks is the string is empty
            throw new IllegalArgumentException("Input expression cannot be null or empty");
        }

        String expression = infix.replaceAll("\\s+", ""); // removes all whitespaces
        List<Character> expressionTokens = new LinkedList<>();

        for (Character c : expression.toCharArray()) {
            expressionTokens.add(c); // inputs all the tokens into a list
        }

        for (Character inputToken : expressionTokens) { // iterates through the list of tokens
            if (!Tokenizer.isOperator(inputToken)) {
                handleOperand(inputToken); // if the token is operand
            } else if (inputToken == '(') {
                stack.push(inputToken); // if the token is '('
            } else if (inputToken == ')') {
                handleClosingParenthesis(); // if the token is ')'
            } else {
                handleOperator(inputToken); // if the token is operator
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop()); // empties the stack after the input line is empty
        }

        return output.toString(); // returns the output line as string
    }

    private void handleOperand(Character operand) {
        output.append(operand); // send the operand directly to output line
    }

    private void handleClosingParenthesis() {
        while (!stack.isEmpty() && stack.peek() != '(') { // iterates through the stack untill it is empty or untill it finds '(' in it
            output.append(stack.pop()); // moves the operand from the stack to the output line
        }
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Mismatched parentheses"); // throws an error if the ')' is missmatched
        }
        stack.pop(); // pops the '(' after everything over it is put into the output line
    }

    private void handleOperator(Character operator) {
        while (!stack.isEmpty() && stack.peek() != '(' && hasHigherOrEqualPriority(stack.peek(), operator)) { //pops all operand in the stack with higher priority
            output.append(stack.pop()); // moves the operand from the stack to the output line if conditions are met
        }
        stack.push(operator); //pushes the next operand into the stack
    }

    private boolean hasHigherOrEqualPriority(Character stackOp, Character inputOp) {
        int stackPriority = Priorities.getStackPriority(stackOp); //gets the stack priority for the last operand in the stack
        int inputPriority = Priorities.getInputPriority(inputOp); //gets the input priority for the selected operand

        if (stackPriority == inputPriority) { //if the priorities are equal the current operand is pushed and the last operand in the stack is popped
            return true;
        }
        return stackPriority > inputPriority; //if the stach operand's priority is higher the next operand is pushed into the stack
    }
}