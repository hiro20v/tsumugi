package hiro20v.lang;

import java.lang.Character.UnicodeBlock;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 1文字.<br>
 * {@link BreakIterator#getCharacterInstance(Locale)}の文字境界による1文字を表現します。<br>
 * {@link OneChar}オブジェクトは不変です。<br>
 * このクラスは、{@link BreakIterator#getCharacterInstance(Locale)}
 * に依存しているため、Javaのバージョンにより、結果が異なる事があります。
 * 
 * @author hiro
 *
 */
public class OneChar implements Comparable<OneChar> {

    private final List<UniChar> uniCharList;
    private final String oneCharString;

    /**
     * 指定された{@link UniChar}の配列を使用して、新しい{@link OneChar}を構築します。.
     * 
     * @param uniChars
     *            {@link UniChar}の配列
     */
    public OneChar(final UniChar... uniChars) {

        this.uniCharList = Collections.unmodifiableList(Arrays.asList(uniChars));
        this.oneCharString = this.uniCharList.stream().map(uniChar -> uniChar.toString()).collect(Collectors.joining());

        final BreakIterator oneCharIterator = BreakIterator.getCharacterInstance(Locale.US);
        oneCharIterator.setText(this.oneCharString);
        int oneCharLength = 0;
        for (int end = oneCharIterator.next(); end != BreakIterator.DONE; end = oneCharIterator.next()) {

            oneCharLength++;
        }

        if (1 != oneCharLength) {

            throw new IllegalArgumentException("UniChar[] is not 1 one char. : " + oneCharString);
        }
    }

    /**
     * この{@link OneChar}を{@link UniChar}リストに変換します。.
     * 
     * @return {@link UniChar}
     */
    public List<UniChar> toUniCharList() {

        return this.uniCharList;
    }

    /**
     * この{@link OneChar}を異体字選択符号を無視した{@link OneChar}に変換します。.
     * 
     * @return {@link OneChar}
     */
    public OneChar toOneCharIgnoreVariation() {

        final List<UniChar> uniCharList = new ArrayList<>(this.toUniCharList());
        final UnicodeBlock unicodeBlock = uniCharList.get(uniCharList.size() - 1).getUnicodeBlock();
        if (UnicodeBlock.VARIATION_SELECTORS == unicodeBlock || UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT == unicodeBlock) {

            uniCharList.remove(uniCharList.size() - 1);
        }

        return new OneChar(uniCharList.toArray(new UniChar[0]));
    }

    /**
     * この{@link OneChar}と指定されたオブジェクトを異体字選択符号を無視して比較します。.<br>
     * 引数がnullではなく、このオブジェクトと同じ {@link OneChar}を表す{@link OneChar}
     * オブジェクトである場合にだけ、結果はtrueになります。
     * 
     * @param anotherObject
     *            この{@link OneChar}と比較するオブジェクト
     * @return 指定されたオブジェクトがこの{@link OneChar}に等しい{@link OneChar}
     *         を表す場合はtrue、それ以外の場合はfalse
     */
    public boolean equalsIgnoreVariation(final Object anotherObject) {

        if (this == anotherObject) {
            return true;
        }

        if (anotherObject == null) {
            return false;
        }

        if (getClass() != anotherObject.getClass()) {
            return false;
        }

        final OneChar anotherOneChar = (OneChar) anotherObject;

        return this.toOneCharIgnoreVariation().equals(anotherOneChar.toOneCharIgnoreVariation());
    }

    /**
     * 2つの{@link OneChar}オブジェクトを数値的に比較します。.<br>
     * 
     * @param anotherOneChar
     *            比較対象の{@link OneChar}
     * @return 引数{@link OneChar}がこの{@link OneChar}と等しい場合は値0、この{@link OneChar}が
     *         {@link OneChar}引数より小さい数値の場合は0より小さい値、この{@link OneChar}が
     *         {@link OneChar}
     *         引数より大きい数値の場合は0より大きい値(符号なしの比較)。これは完全に数値の比較なので、ロケールに依存しない
     */
    @Override
    public int compareTo(final OneChar anotherOneChar) {

        final List<UniChar> uniChars = this.toUniCharList();
        final List<UniChar> anotherUniChars = anotherOneChar.toUniCharList();
        final int uniCharsLength = uniChars.size();
        final int anotherUniCharsLength = anotherUniChars.size();

        final int minLength = Math.min(uniCharsLength, anotherUniCharsLength);
        for (int i = 0; i < minLength; i++) {

            final UniChar uniChar = uniChars.get(i);
            final UniChar anotherUniChar = anotherUniChars.get(i);

            final int uniCharCompareResult = uniChar.compareTo(anotherUniChar);
            if (0 != uniCharCompareResult) {

                return uniCharCompareResult;
            }
        }

        return uniCharsLength - anotherUniCharsLength;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        return this.oneCharString.hashCode();
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

        final OneChar anotherOneChar = (OneChar) anotherObject;

        assert(this.toString() != null);
        return this.toString().equals(anotherOneChar.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return this.oneCharString;
    }

    Infomation getInfomation() {

        final Infomation infomation = new Infomation();

        infomation.setName(OneChar.class.getSimpleName());

        infomation.getProperties().put(String.class.getSimpleName(), "'" + this.toString() + "'");
        infomation.getProperties().put("UniChar count", String.valueOf(this.toUniCharList().size()));

        this.toUniCharList().forEach(uniChar -> infomation.getChildInfomations().add(uniChar.getInfomation()));

        return infomation;
    }
}
