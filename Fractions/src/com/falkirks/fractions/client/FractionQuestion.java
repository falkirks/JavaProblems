package com.falkirks.fractions.client;


import com.falkirks.fractions.Fraction;

import java.util.Random;

public class FractionQuestion {
    protected Fraction a;
    protected Fraction b;
    protected FractionOperation operation;
    protected Fraction correctAnswer;
    protected boolean isCorrect;

    protected static final Random questionRandom = new Random();

    final static public int RANDOM_MAX = 5;
    final static public int RANDOM_MIN = -5;
    public FractionQuestion(Fraction a, Fraction b) {
        this.isCorrect = false;
        this.a = a;
        this.b = b;
        this.operation = FractionOperation.nextOperation();
        this.correctAnswer = operation.apply(a, b).reduce();
    }
    public FractionQuestion(int min, int max){
        this(new Fraction(FractionQuestion.nextInt(min, max), FractionQuestion.nextInt(min, max)), new Fraction(FractionQuestion.nextInt(min, max), FractionQuestion.nextInt(min, max)));
    }
    public FractionQuestion(){
        this(RANDOM_MIN, RANDOM_MAX);
    }
    public FractionQuestion(int difficulty){
        this(Math.abs(difficulty), Math.abs(difficulty)*-1);
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

    public void setA(Fraction a) {
        this.a = a;
    }

    public void setB(Fraction b) {
        this.b = b;
    }

    public void setCorrect() {
        this.isCorrect = true;
    }
    public static int nextInt(int min, int max){
        int ret = FractionQuestion.questionRandom.nextInt(max - min + 1) + min;
        return ret != 0 ? ret : nextInt(min, max);
    }

    @Override
    public String toString() {
        return "(" + this.a + ") " + this.operation.toString() + " (" + this.b + ")";
    }
}
