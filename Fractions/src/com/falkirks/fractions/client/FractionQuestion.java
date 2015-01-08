package com.falkirks.fractions.client;


import com.falkirks.fractions.Fraction;

import java.util.Random;

public class FractionQuestion {
    private Fraction a;
    private Fraction b;
    private FractionOperation operation;
    private Fraction correctAnswer;
    private boolean isCorrect;

    private static Random questionRandom = new Random();

    final static public int RANDOM_MAX = -1;
    final static public int RANDOM_MIN = -5;
    public FractionQuestion(Fraction a, Fraction b) {
        this.isCorrect = false;
        this.a = a;
        this.b = b;
        this.operation = FractionOperation.nextOperation();
        this.correctAnswer = operation.apply(a, b);
    }
    public FractionQuestion(){
        this(new Fraction(FractionQuestion.nextInt(), FractionQuestion.nextInt()), new Fraction(FractionQuestion.nextInt(), FractionQuestion.nextInt()));
    }
    public boolean checkAnswer(Fraction test){
        isCorrect = test.equals(correctAnswer);
        return isCorrect;
    }
    public boolean checkAnswer(String s){
        return checkAnswer(new Fraction(s));
    }
    public boolean isCorrect() {
        return isCorrect;
    }

    public Fraction getCorrectAnswer() {
        return correctAnswer;
    }

    public FractionOperation getOperation() {
        return operation;
    }

    public Fraction getB() {
        return b;
    }

    public Fraction getA() {
        return a;
    }

    public static int nextInt(){
        return FractionQuestion.questionRandom.nextInt(FractionQuestion.RANDOM_MAX - FractionQuestion.RANDOM_MIN + 1) + FractionQuestion.RANDOM_MIN;
    }

    @Override
    public String toString() {
        return "(" + this.a.toString() + ") " + this.operation.toString() + " (" + this.b.toString() + ")";
    }
}
