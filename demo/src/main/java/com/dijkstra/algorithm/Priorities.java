package com.dijkstra.algorithm;

import java.util.Map;
import java.util.HashMap;

public final class Priorities {
    private static final Map<Character, Integer> INPUT_PRIORITIES;
    private static final Map<Character, Integer> STACK_PRIORITIES;
    
    static {
        INPUT_PRIORITIES = Map.of(
            '+', 2,
            '-', 2,
            '*', 3, 
            '/', 3,
            '%', 3,
            '^', 4
        );
        
        STACK_PRIORITIES = new HashMap<>(INPUT_PRIORITIES);
        STACK_PRIORITIES.put('^', 3);
    }

    public static int getInputPriority(Character operator) {
        return INPUT_PRIORITIES.getOrDefault(operator, -1);
    }

    public static int getStackPriority(Character operator) {
        return STACK_PRIORITIES.getOrDefault(operator, -1);
    }
    
    public static boolean isLeftAssociative(Character operator) {
        return operator != '^';
    }
}