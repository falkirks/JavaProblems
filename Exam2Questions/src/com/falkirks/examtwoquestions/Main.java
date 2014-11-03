package com.falkirks.examtwoquestions;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Problem #:");
        switch (scanner.nextInt()){
            case 1:
                int sum = 0;
                for(int i = 1; i < 1000; i++){
                    if(i % 3 == 0 || i % 5 == 0){
                        sum += i;
                    }
                }
                System.out.println(sum);
                break;
            case 2:
                System.out.println(fibonacciProblem(1, 2, 0));
                break;
            case 3:
                int sumOfSquares = 0;
                int squareOfSum = 0;
                for(int i = 1; i <= 100; i++){
                    squareOfSum += i;
                    sumOfSquares += Math.pow(i, 2);
                }
                System.out.println((int) Math.pow(squareOfSum, 2) - sumOfSquares);
                break;
            case 4:
                for(int a = 1; a <= 1000; a++){
                    for(int b = 1; b <= 1000; b++){
                        for(int c = 1; c <= 1000; c++){
                            if(a + b + c == 1000){
                                if(Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
                                    System.out.println(a * b * c);
                                    return;
                                }
                            }
                        }
                    }
                }
                System.out.println("Failed to find triplet.");
                break;
            default:
                System.out.println("Unrecognized input.");
                break;
        }

    }
    public static int fibonacciProblem(int p, int c, int sum){
        if(c >= 4000000){
            return sum;
        }
        if(c % 2 == 0){
            return fibonacciProblem(c, p + c, sum + c);
        }
        return fibonacciProblem(c, p + c, sum);
    }
}
