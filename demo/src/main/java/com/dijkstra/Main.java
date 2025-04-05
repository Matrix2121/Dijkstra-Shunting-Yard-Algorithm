package com.dijkstra;

import com.dijkstra.algorithm.ShuntingYard;

public class Main {
    public static void main(String[] args) {
        ShuntingYard.infixToPostfix("a + b ^ c * d / (e + f * g)");
    }
}