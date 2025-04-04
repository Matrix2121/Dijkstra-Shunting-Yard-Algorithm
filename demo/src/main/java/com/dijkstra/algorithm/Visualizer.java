package com.dijkstra.algorithm;

import java.util.Deque;
import java.util.List;

public class Visualizer {
    Integer inputLenght;
    Integer step = 0;

    public void print(String input, Deque<Character> stack, String output){
        char[] inputArray = input.toCharArray();
        char[] outputArray = output.toCharArray();

        System.out.println("--------------------------------");
        System.out.print("Step #");
        System.out.println(step++);
        System.out.println();

        printInput(inputArray);
        printStack(stack);
        printOutput(outputArray);
    }

    public void printFinal(String infix, String postfix){;
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
    }

    public void setConstants(List<Character> input){
        step = 0;
        this.inputLenght = input.size();
        for (Character token : input) {
            if(token == '(' || token == ')'){
                inputLenght--;
            }
        }
    }

    private void printInput(char[] input){
        System.out.println("Input:");
        for (char c : input) {
            System.out.print('[');
            System.out.print(c);
            System.out.print(']');
        }
        System.out.println();
        System.out.println();
    }

    private void printStack(Deque<Character> stack){
        System.out.println("Stack:");
        System.out.println();
        Deque<Character> currStack = stack;
        System.out.println("| |");

        for (Character currCharacter : currStack) {
            System.out.print("|");
            System.out.print(currCharacter);
            System.out.println("|");
        }
        
        System.out.println("|_|");
        System.out.println();
    }

    private void printOutput(char[] output){
        System.out.println("Output:");
        for (char c : output) {
            System.out.print('[');
            System.out.print(c);
            System.out.print(']');
        }
        for(int i = 0; i < inputLenght - output.length; i++){
            System.out.print('[');
            System.out.print(']');
        }
        System.out.println();
        System.out.println();
    }
}