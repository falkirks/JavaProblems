package com.falkirks.fractions;

import java.lang.reflect.Constructor;

public class Fraction {
    final private int numerator;
    final private int denominator;

    final static char fractionDivider = '/';
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int numerator) {
        this(numerator, 1);
    }
    public Fraction(String fractionString) throws IllegalArgumentException{
        String[] fractionArray = fractionString.split(Fraction.fractionDivider + "");
        if(fractionArray.length != 2){
            throw new IllegalArgumentException("Argument 'fractionString' is not a fraction");
        }
        else {
            try {
                this.numerator = Integer.parseInt(fractionArray[0]);
                int denominator = Integer.parseInt(fractionArray[1]);
                checkDenominator(denominator);
                this.denominator = denominator;
            }
            catch(NumberFormatException e){
                throw new IllegalArgumentException("Argument 'fractionString' is not a fraction");
            }
        }
    }
    public Fraction(Fraction fraction){
        this(fraction.getNumerator(), fraction.getDenominator());
    }
    public Fraction() {
        this(1);
    }

    @Override
    public String toString() {
        return this.numerator + "" + Fraction.fractionDivider + this.denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Fraction){
            Fraction fraction = (Fraction) obj;
            return (fraction.getDenominator() == denominator && fraction.getNumerator() == numerator) || (fraction.getValue() == getValue());
        }
        return false;
    }
    public double toDouble(){
        return this.getValue();
    }
    public int getRoundedValue(){
        return Math.round(getValue());
    }
    public float getValue(){
        return numerator / denominator;
    }
    public Fraction reduce(){
        int gcf = gcf(numerator, denominator);
        return new Fraction(numerator / gcf, denominator / gcf);
    }
    public Fraction inverse(){
        return new Fraction(denominator, numerator);
    }
    public Fraction add(Object ...objects) throws IllegalArgumentException{
        Fraction fraction = fetchFraction(objects);
        if(fraction != null) {
            int lcm = Fraction.lcm(fraction.getDenominator(), denominator);
            return new Fraction(getNumerator() * (lcm / getDenominator()) + fraction.getNumerator() * (lcm / fraction.getDenominator()), lcm);
        }
        throw new IllegalArgumentException("Argument 'objects' is not a fraction");
    }
    public Fraction subtract(Object ...objects) throws IllegalArgumentException{
        Fraction fraction = fetchFraction(objects);
        if(fraction != null) {
            int lcm = Fraction.lcm(fraction.getDenominator(), denominator);
            return new Fraction(getNumerator()*(lcm/getDenominator()) - fraction.getNumerator()*(lcm/fraction.getDenominator()), lcm); //TODO make a proper fraction
        }
        throw new IllegalArgumentException("Argument 'objects' is not a fraction");
    }
    public Fraction multiply(Object ...objects) throws IllegalArgumentException{
        Fraction fraction = fetchFraction(objects);
        if(fraction != null) {
            return new Fraction(numerator * fraction.getNumerator(), denominator * fraction.getDenominator());
        }
        throw new IllegalArgumentException("Argument 'objects' is not a fraction");
    }
    public Fraction divide(Object... objects) throws IllegalArgumentException{
        Fraction fraction = fetchFraction(objects);
        if(fraction != null) {
            return multiply(fraction.inverse());
        }
        throw new IllegalArgumentException("Argument 'objects' is not a fraction");
    }
    private static int gcf(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    private static int lcm(int a, int b) {
        return a * (b / Fraction.gcf(a, b));
    }
    public int getNumerator() {
        return numerator;
    }
    public Fraction setNumerator(int numerator) {
        return new Fraction(numerator, this.denominator);
    }

    public int getDenominator() {
        return denominator;
    }
    public Fraction setDenominator(int denominator) throws IllegalArgumentException{
        checkDenominator(denominator);
        return new Fraction(this.numerator, denominator);
    }
    private void checkDenominator(int denominator) throws IllegalArgumentException{
        if(denominator == 0){
            throw new IllegalArgumentException("Argument 'denominator' is not a denominator.");
        }
    }
    private Fraction fetchFraction(Object[] args) throws IllegalArgumentException{
        Class[] argTypes = new Class[args.length];
        for(int i = 0; i < args.length; i++){
            argTypes[i] = args[i].getClass();
        }
        try {
            Constructor constructor = getClass().getDeclaredConstructor(argTypes);
            return (Fraction) constructor.newInstance(args);
        }
        catch(Exception e){
            return null;
        }
    }
}
