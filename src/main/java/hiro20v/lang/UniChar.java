package hiro20v.lang;

import java.lang.Character.UnicodeBlock;
import java.lang.Character.UnicodeScript;
import java.util.Objects;

/**
 * 単一文字.<br>
 * Unicode の単一文字を表現します。<br>
 * {@link UniChar}オブジェクトは不変です。
 * 
 * @author hiro
 *
 */
public class UniChar implements Comparable<UniChar> {

	private final String uniCharString;

	/**
	 * 指定された{@link java.lang.String}を使用して、新しい{@link UniChar}を構築します。.
	 * 
	 * @param uniCharString
	 *            {@link java.lang.String}
	 */
	public UniChar(final String uniCharString) {

		Objects.requireNonNull(uniCharString, "uniCharString");

		if (1 != uniCharString.codePointCount(0, uniCharString.length())) {

			throw new IllegalArgumentException("String is not 1 code point. : " + uniCharString);
		}

		this.uniCharString = uniCharString;
	}

	/**
	 * 指定されたUnicodeコード単位を使用して、新しい{@link UniChar}を構築します。.
	 * 
	 * @param codePoint
	 *            Unicodeコード単位
	 */
	public UniChar(final int codePoint) {

		this.uniCharString = new String(new int[] { codePoint }, 0, 1);
	}

	/**
	 * この{@link UniChar}のUnicodeコード単位を取得します。.
	 * 
	 * @return Unicodeコード単位
	 */
	public int getCodePoint() {

		return this.uniCharString.codePointAt(0);
	}

	/**
	 * この{@link UniChar}の{@link UnicodeBlock}を取得します。.
	 * 
	 * @return {@link UnicodeBlock}
	 */
	public UnicodeBlock getUnicodeBlock() {

		return UnicodeBlock.of(this.getCodePoint());
	}

	/**
	 * この{@link UniChar}の{@link UnicodeScript}を取得します。.
	 * 
	 * @return {@link UnicodeScript}
	 */
	public UnicodeScript getUnicodeScript() {

		return UnicodeScript.of(this.getCodePoint());
	}

	/**
	 * 2つの{@link UniChar}オブジェクトを数値的に比較します。.<br>
	 * 
	 * @param anotherUniChar
	 *            比較対象の{@link UniChar}
	 * @return 引数{@link UniChar}がこの{@link UniChar}と等しい場合は値0、この{@link UniChar}が
	 *         {@link UniChar}引数より小さい数値の場合は0より小さい値、この{@link UniChar}が
	 *         {@link UniChar}
	 *         引数より大きい数値の場合は0より大きい値(符号なしの比較)。これは完全に数値の比較なので、ロケールに依存しない
	 */
	@Override
	public int compareTo(final UniChar anotherUniChar) {

		final int codePoint = this.getCodePoint();
		final int anotherCodePoint = anotherUniChar.getCodePoint();

		return Integer.compare(codePoint, anotherCodePoint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		return this.uniCharString.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object anotherObject) {

		if (this == anotherObject) {
			return true;
		}

		if (anotherObject == null) {
			return false;
		}

		if (getClass() != anotherObject.getClass()) {
			return false;
		}

		final UniChar anotherUniCharString = (UniChar) anotherObject;

		assert (this.toString() != null);
		return this.toString().equals(anotherUniCharString.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return this.uniCharString;
	}

	Information getInformation() {

		final Information information = new Information();

		information.setName(UniChar.class.getSimpleName());

		information.getProperties().put(String.class.getSimpleName(), "'" + this.toString() + "'");
		information.getProperties().put("Code point", String.valueOf(this.getCodePoint()));
		information.getProperties().put("Hex", Integer.toHexString(this.getCodePoint()).toUpperCase());
		information.getProperties().put("Plane", String.valueOf(this.getCodePoint() / Character.MAX_VALUE));
		information.getProperties().put("Char count", String.valueOf(this.toString().toCharArray().length));
		information.getProperties().put("Unicode block", this.getUnicodeBlock().toString());
		information.getProperties().put("Unicode script", this.getUnicodeScript().toString());

		this.toString().chars().forEach(codePoint -> {

			final Information childInformation = new Information();

			childInformation.setName(Character.class.getSimpleName());

			final char c = (char) codePoint;

			childInformation.getProperties().put(String.class.getSimpleName(), "'" + Character.toString(c) + "'");
			childInformation.getProperties().put("Char", String.valueOf(codePoint));
			childInformation.getProperties().put("Hex", Integer.toHexString(codePoint).toUpperCase());
			childInformation.getProperties().put("Bmp", String.valueOf(Character.isBmpCodePoint(codePoint)));
			childInformation.getProperties().put("Supplementary", String.valueOf(Character.isSupplementaryCodePoint(codePoint)));
			childInformation.getProperties().put("High surrogate", String.valueOf(Character.isHighSurrogate(c)));
			childInformation.getProperties().put("Low surrogate", String.valueOf(Character.isLowSurrogate(c)));

			information.getChildInformations().add(childInformation);
		});

		return information;
	}
}
