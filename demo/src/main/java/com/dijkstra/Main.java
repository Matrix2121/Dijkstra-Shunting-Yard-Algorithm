package com.dijkstra;

import com.dijkstra.algorithm.ShuntingYard;

public class Main {
    public static void main(String[] args) {
        ShuntingYard algorithm = new ShuntingYard();
        
        String infix = "3 / 4 * (2 - 1)";
        algorithm.infixToPostfix(infix);

        String infix1 = "3 + 4 * (2 - 1)";
        algorithm.infixToPostfix(infix1);
    }
}