package com.falkirks.fractions.client;

import com.falkirks.fractions.Fraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FractionClient {
    private ArrayList<FractionQuestion> questions;
    private BufferedReader br;

    public static final boolean IS_DEBUG = false;

    public FractionClient() {
        questions = new ArrayList<FractionQuestion>();
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public void start(){
        while(true){
            FractionQuestion question = new FractionQuestion();
            askQuestion(question);
            printScore();
        }
    }
    public void askQuestion(FractionQuestion question){
        System.out.print(question + " = ");
        if(IS_DEBUG) System.out.println(question.getCorrectAnswer()); //Testing code
        try {
            Fraction fraction = new Fraction(br.readLine());
            if(question.checkAnswer(fraction)){
                System.out.println("Correct!");
            }
            else{
                System.out.println("Incorrect, the correct answer is " + question.getCorrectAnswer());
            }

        }
        catch (IOException e){
            System.out.println("Your computer appears to be screwed up. For having such a sucky input pipe, you get a free point.");
            question.setCorrect();
        }
        catch (IllegalArgumentException e) {
            System.out.println("Ouch, you should learn how to type a fraction.");
        }
        finally {
            questions.add(question);
        }
    }
    public void printScore(){
        int correct = 0;
        for(FractionQuestion fractionQuestion : questions){
            if(fractionQuestion.isCorrect()){
                correct++;
            }
        }
        System.out.println("Your current score is " + correct + "/" + questions.size());
    }
}
