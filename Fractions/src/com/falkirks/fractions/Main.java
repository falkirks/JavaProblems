package com.falkirks.fractions;

public class Main {

    public static void main(String[] args) {
	    System.out.println(new Fraction(2, 3).add());
        System.out.println(new Fraction("6/2").reduce());
        System.out.println(new Fraction(2, 1).setNumerator(3));
    }
}
