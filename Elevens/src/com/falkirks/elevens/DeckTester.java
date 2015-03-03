package com.falkirks.elevens;

/**
 * This is a class that tests the Deck class.
 */
public class DeckTester {

	/**
	 * The main method in this class checks the Deck operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		Deck deck = new Deck(
				new String[]{"ten", "nine", "eigth", "two"},
				new String[]{"diamonds", "clubs", "spades", "spades"},
				new int[]{10, 9, 8, 2}
		);
		System.out.println(deck.toString());
		deck.shuffle();
		System.out.println(deck.toString());

	}
}
