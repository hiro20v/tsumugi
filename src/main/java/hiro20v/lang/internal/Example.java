package hiro20v.lang.internal;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import hiro20v.lang.OneCharSequence;
import hiro20v.lang.UniCharSequence;

class Example {

	private static String EXAMPLE_STRING = "芦田さんは芦\uDB40\uDD01屋のお嬢様だ";
	private static String[] EXAMPLE_ARRAY = { "A", "あ", "㐀", "㐂", "㐂\uDB40\uDD00", "㐂豈", "一", "豈", "😀", "𠀀" };

	public static void main(final String[] args) {

		// String Sample
		System.out.println(String.class.getSimpleName());
		System.out.println(EXAMPLE_STRING.length());// 14
		System.out.println(EXAMPLE_STRING.chars().mapToObj(codePoint -> Character.valueOf((char) codePoint).toString()).collect(Collectors.joining(", ")));
		// 芦, 田, さ, ん, は, 芦, ?, ?, 屋, の, お, 嬢, 様, だ,
		SortedSet<String> set = new TreeSet<>();
		Collections.addAll(set, EXAMPLE_ARRAY);
		System.out.println(set.stream().collect(Collectors.joining(", ")));
		// A, あ, 㐀, 㐂, 㐂󠄀, 㐂豈, 一, 😀, 𠀀, 豈,
		System.out.println(EXAMPLE_STRING.subSequence(5, 9)/* 芦\uDB40\uDD01屋 */);
		System.out.println();

		// UniCharSequence Sample
		System.out.println(UniCharSequence.class.getSimpleName());
		final UniCharSequence uniCharSequence = new UniCharSequence(EXAMPLE_STRING);
		System.out.println(uniCharSequence.length());// 13
		System.out.println(uniCharSequence.toCharList().stream().map(uniChar -> uniChar.toString()).collect(Collectors.joining(", ")));
		// 芦, 田, さ, ん, は, 芦, 󠄁, 屋, の, お, 嬢, 様, だ,
		set = new TreeSet<>((final String s1, final String s2) -> new UniCharSequence(s1).compareTo(new UniCharSequence(s2)));
		Collections.addAll(set, EXAMPLE_ARRAY);
		System.out.println(set.stream().map(oneChar -> oneChar.toString()).collect(Collectors.joining(", ")));
		// A, あ, 㐀, 㐂, 㐂豈, 㐂󠄀, 一, 豈, 😀, 𠀀,
		System.out.println(uniCharSequence.subSequence(5, 8)/* 芦\uDB40\uDD01屋 */);
		System.out.println();

		// OneCharSequence Sample
		System.out.println(OneCharSequence.class.getSimpleName());
		final OneCharSequence oneCharSequence = new OneCharSequence(new UniCharSequence(EXAMPLE_STRING));
		System.out.println(oneCharSequence.length());// 12
		System.out.println(oneCharSequence.toCharList().stream().map(oneChar -> oneChar.toString()).collect(Collectors.joining(", ")));
		// 芦, 田, さ, ん, は, 芦󠄁, 屋, の, お, 嬢, 様, だ,
		set = new TreeSet<>((final String s1, final String s2) -> new OneCharSequence(new UniCharSequence(s1)).compareTo(new OneCharSequence(new UniCharSequence(s2))));
		Collections.addAll(set, EXAMPLE_ARRAY);
		System.out.println(set.stream().map(oneChar -> oneChar.toString()).collect(Collectors.joining(", ")));
		// A, あ, 㐀, 㐂, 㐂󠄀, 㐂豈, 一, 豈, 😀, 𠀀,
		System.out.println(oneCharSequence.subSequence(5, 7)/* 芦\uDB40\uDD01屋 */);
		System.out.println();
	}
}
