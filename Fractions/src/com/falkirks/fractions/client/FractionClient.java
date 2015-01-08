package com.falkirks.fractions.client;

import com.falkirks.fractions.Fraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FractionClient {
    private ArrayList<FractionQuestion> questions;
    BufferedReader br;
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
        System.out.println(question);
        System.out.println("Answer is: " + question.getCorrectAnswer()); //Testing code
        try {
            Fraction fraction = new Fraction(br.readLine());
            if(question.checkAnswer(fraction)){
                System.out.println("Correct!");
            }
            else{
                System.out.println("Incorrect, the correct answer was " + question.getCorrectAnswer());
            }
            questions.add(question);

        }
        catch (IOException e) {
            e.printStackTrace();
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
