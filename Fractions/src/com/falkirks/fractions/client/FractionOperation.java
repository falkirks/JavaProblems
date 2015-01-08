package com.falkirks.fractions.client;


import com.falkirks.fractions.Fraction;

import java.util.Random;

public class FractionOperation {
    static private Random operatorRandom = new Random();

    final static public int ADD = 0;
    final static public int SUBTRACT = 1;
    final static public int MULTIPLY = 2;
    final static public int DIVIDE = 3;
    private int operation;
    public FractionOperation(int operation){
        this.operation = operation;
    }
    public Fraction apply(Fraction a, Fraction b){
        switch(this.operation){
            case FractionOperation.ADD:
                return a.add(b);
            case FractionOperation.SUBTRACT:
                return a.subtract(b);
            case FractionOperation.MULTIPLY:
                return a.multiply(b);
            case FractionOperation.DIVIDE:
                return a.divide(b);
            default:
                return null;
        }
    }
    public String toString(){
        switch(this.operation){
            case FractionOperation.ADD:
                return "+";
            case FractionOperation.SUBTRACT:
                return "-";
            case FractionOperation.MULTIPLY:
                return "*";
            case FractionOperation.DIVIDE:
                return "/";
            default:
                return "";
        }
    }
    public static FractionOperation nextOperation(){
        return new FractionOperation(operatorRandom.nextInt((FractionOperation.DIVIDE - FractionOperation.ADD) + 1) + FractionOperation.ADD);
    }
}
