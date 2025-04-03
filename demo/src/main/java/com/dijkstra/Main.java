package com.dijkstra;

import com.dijkstra.algorithm.ShuntingYard;

public class Main {
    public static void main(String[] args) {
        ShuntingYard algorithm = new ShuntingYard();
        String infix = "3 + 4 * (2 - 1)";
        String postfix = algorithm.infixToPostfix(infix);
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
    }
}