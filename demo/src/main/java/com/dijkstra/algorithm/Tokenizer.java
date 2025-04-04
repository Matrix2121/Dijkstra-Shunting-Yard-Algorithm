package com.dijkstra.algorithm;

import java.util.Set;

public final class Tokenizer {
    private static final Set<Character> OPERATORS = Set.of(
        '#', 'y', '(', ')', '+', '-', '*', '/', '%', '^'
    );

    public static boolean isOperator(Character token) {
        return token != null && OPERATORS.contains(token);
    }

    public static Integer operatorsCount(String infix) {
        Integer count = 0;
        for(Character token : infix.toCharArray()){
            if (isOperator(token)){
                count++;
            }
        }
        return count;
    }
}