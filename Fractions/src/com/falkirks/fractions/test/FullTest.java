package com.falkirks.fractions.test;


import com.falkirks.fractions.Fraction;
import com.falkirks.fractions.client.FractionQuestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FullTest {
    private BufferedReader br;
    private static final String CONTINUE_MESSAGE = "Set completed. Hit ENTER to continue.";

    public FullTest() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public void start(){
        System.out.println("### FullTest for Fraction class");
        System.out.println("Tests use fraction client and run in sets of a million.");
        System.out.println("###\n");
        System.out.println("Tests are now running...");
        while(shouldContinue()) runTestSet();
    }
    public void runTestSet(){
        try {
            for (int i = 0; i < 100000; i++) {
                FractionQuestion question = new FractionQuestion(-100, 100);
                if (!testQuestion(question)) {
                    System.out.println("Test failed for " + question + " fraction was calculated to be " + question.getCorrectAnswer());
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private boolean testQuestion(FractionQuestion question){
        int res = Math.round(question.getOperation().apply(question.getA().getValue(), question.getB().getValue()));
        return areClose(res, question.getCorrectAnswer().getRoundedValue());
    }
    private boolean shouldContinue(){
        System.out.print(FullTest.CONTINUE_MESSAGE);
        try{
            return !br.readLine().equals("exit");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            System.out.print("\u008D");
            for(int i = 0; i < FullTest.CONTINUE_MESSAGE.length(); i++){
                System.out.print("\b");
            }
        }
        return false;
    }
    private boolean areClose(int a, int b){
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        return max - min <= 1;
    }
}
