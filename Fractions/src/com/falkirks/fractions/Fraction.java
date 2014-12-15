package com.falkirks.fractions;

public class Fraction {
    private int numerator;
    private int denominator;
    final static char fractionDivider = '/';
    public Fraction(int numerator, int denominator) {
        setNumerator(numerator);
        setDenominator(denominator);
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
                setNumerator(Integer.parseInt(fractionArray[0]));
                setDenominator(Integer.parseInt(fractionArray[1]));
            }
            catch(NumberFormatException e){
                throw new IllegalArgumentException("Argument 'fractionString' is not a fraction");
            }
        }
    }
    @Deprecated
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
            //TODO improve this to do an LCM
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
    public Fraction add(Fraction fraction){
        int lcm = Fraction.lcm(fraction.getDenominator(), denominator);
        return new Fraction(getNumerator()*(lcm/getDenominator()) + fraction.getNumerator()*(lcm/fraction.getDenominator()), lcm); //TODO make a proper fraction
    }
    public Fraction subtract(Fraction fraction){
        int lcm = Fraction.lcm(fraction.getDenominator(), denominator);
        return new Fraction(getNumerator()*(lcm/getDenominator()) - fraction.getNumerator()*(lcm/fraction.getDenominator()), lcm); //TODO make a proper fraction
    }
    public Fraction multiply(Fraction fraction){
        return new Fraction(numerator * fraction.getNumerator(), denominator * fraction.getDenominator());
    }
    public Fraction divide(Fraction fraction){
        return multiply(fraction.inverse());
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
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }
    public void setDenominator(int denominator) throws IllegalArgumentException{
        if(denominator == 0){
            throw new IllegalArgumentException("Argument 'denominator' is not a denominator.");
        }
        this.denominator = denominator;
    }
}
