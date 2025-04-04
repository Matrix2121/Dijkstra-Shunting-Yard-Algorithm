package com.dijkstra;

import com.dijkstra.algorithm.ShuntingYard;

public class Main {
    public static void main(String[] args) {
        String infix = "a + b ^ c * d / (e + f * g)";
        ShuntingYard.infixToPostfix(infix);
        ShuntingYard.infixToPostfix("j + b + c * d / (e + f * g)");
        ShuntingYard.infixToPostfix("o + b ^ c * d - (e + f * g)");
    }
}