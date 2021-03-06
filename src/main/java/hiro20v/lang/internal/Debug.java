package hiro20v.lang.internal;

import hiro20v.lang.OneCharSequence;
import hiro20v.lang.UniCharSequence;

class Debug {

	// MEMO String#compareTo サロゲートが考慮されない
	// MEMO BreakIterator Variationが考慮される

	public static void main(final String[] args) {

		final OneCharSequence oneCharSequence = new OneCharSequence(new UniCharSequence("芦田さんは芦\uDB40\uDD01屋のお嬢様だ"));
		System.out.println(oneCharSequence.printStructure());
		System.out.println();

		final UniCharSequence uniCharSequence = new UniCharSequence("芦田さんは芦\uDB40\uDD01屋のお嬢様だ");
		System.out.println(uniCharSequence.printStructure());
		System.out.println();
	}
}
