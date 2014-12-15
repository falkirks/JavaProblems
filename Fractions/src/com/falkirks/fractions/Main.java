package com.falkirks.fractions;

public class Main {

    public static void main(String[] args) {
	    System.out.println(new Fraction(2, 3).add(new Fraction(1)).subtract(new Fraction(2)));
        System.out.println(new Fraction("6/2").reduce());
    }
}
