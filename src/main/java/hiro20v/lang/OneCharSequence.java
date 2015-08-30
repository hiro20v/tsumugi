package hiro20v.lang;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 1文字列.<br>
 * {@link OneChar}の並びを表現します。<br>
 * {@link OneCharSequence}オブジェクトは不変です。<br>
 * このクラスは、{@link BreakIterator#getCharacterInstance(Locale)}
 * に依存しているため、Javaのバージョンにより、結果が異なる事があります。
 * 
 * @author hiro
 *
 */
public class OneCharSequence implements Comparable<OneCharSequence> {

    private final List<OneChar> oneCharList;
    private final UniCharSequence uniCharSequence;
    private final String oneCharSequenceString;

    /**
     * 指定された{@link UniCharSequence}を使用して、新しい{@link OneCharSequence}を構築します。.
     * 
     * @param uniCharSequence
     *            {@link UniCharSequence}
     */
    public OneCharSequence(final UniCharSequence uniCharSequence) {

        final List<OneChar> oneCharList = new ArrayList<OneChar>();

        final BreakIterator oneCharIterator = BreakIterator.getCharacterInstance();
        this.oneCharSequenceString = uniCharSequence.toString();
        oneCharIterator.setText(this.oneCharSequenceString);
        int start = oneCharIterator.first();
        for (int end = oneCharIterator.next(); end != BreakIterator.DONE; start = end, end = oneCharIterator.next()) {

            final String oneCharString = this.oneCharSequenceString.substring(start, end);
            final UniChar[] uniChars = new UniChar[oneCharString.codePointCount(0, oneCharString.length())];
            for (int i = 0; i < oneCharString.length(); i = oneCharString.offsetByCodePoints(i, 1)) {

                uniChars[i] = new UniChar(oneCharString.codePointAt(i));
            }

            oneCharList.add(new OneChar(uniChars));
        }

        this.oneCharList = Collections.unmodifiableList(oneCharList);

        this.uniCharSequence = uniCharSequence;
    }

    /**
     * 指定された{@link OneChar}の配列を使用して、新しい{@link OneCharSequence}を構築します。.
     * 
     * @param oneChars
     *            {@link OneChar}の配列
     */
    public OneCharSequence(final OneChar... oneChars) {

        this.oneCharList = Collections.unmodifiableList(Arrays.asList(oneChars));
        this.oneCharSequenceString = this.oneCharList.stream().sequential().map(oneChar -> oneChar.toString()).collect(Collectors.joining());
        this.uniCharSequence = new UniCharSequence(this.oneCharSequenceString);
    }

    /**
     * この{@link OneCharSequence}の長さを返します。長さは{@link OneCharSequence}内の
     * {@link BreakIterator#getCharacterInstance(Locale)}の文字境界の数に等しくなります。.
     * 
     * @return このオブジェクトによって表される{@link OneCharSequence}の長さ。
     */
    public int length() {

        return this.oneCharList.size();
    }

    /**
     * 指定されたインデックスの{@link OneChar}値を返します。インデックスの範囲は0からlength() -
     * 1までです。配列のインデックス付けの場合と同じように、シーケンスの最初の{@link OneChar}のインデックスは0、次の
     * {@link OneChar}のインデックスは1と続きます。.
     * 
     * @param index
     *            {@link OneChar}値のインデックス。
     * @return {@link OneCharSequence}内の指定されたインデックス位置にある{@link OneChar}値。最初の
     *         {@link OneChar}値のインデックスが0になる。
     */
    public OneChar charAt(final int index) {

        if (index < 0 || index >= length()) {

            throw new StringIndexOutOfBoundsException(index);
        }

        return this.oneCharList.get(index);
    }

    /**
     * このシーケンスのサブシーケンスである{@link OneCharSequence}を返します。.
     * 
     * @param beginIndex
     *            開始インデックス(この値を含む)。
     * @return 指定されたサブシーケンス。
     */
    public OneCharSequence subSequence(final int beginIndex) {

        return subSequence(beginIndex, this.oneCharList.size());
    }

    /**
     * このシーケンスのサブシーケンスである{@link OneCharSequence}を返します。.
     * 
     * @param beginIndex
     *            開始インデックス(この値を含む)。
     * @param endIndex
     *            終了インデックス(この値を含まない)。
     * @return 指定されたサブシーケンス。
     */
    public OneCharSequence subSequence(final int beginIndex, final int endIndex) {

        if (beginIndex < 0) {

            throw new StringIndexOutOfBoundsException(beginIndex);
        }

        if (endIndex > this.oneCharList.size()) {

            throw new StringIndexOutOfBoundsException(endIndex);
        }

        final int subLength = endIndex - beginIndex;
        if (subLength < 0) {

            throw new StringIndexOutOfBoundsException(subLength);
        }

        return new OneCharSequence(this.oneCharList.subList(beginIndex, endIndex).toArray(new OneChar[0]));
    }

    /**
     * この{@link OneCharSequence}内で、指定された部分{@link OneCharSequence}
     * が最初に出現する位置のインデックスを返します。.
     * 
     * @param oneCharSequence
     *            検索対象の部分{@link OneCharSequence}。
     * @return 指定された部分{@link OneCharSequence}
     *         が最初に出現する位置のインデックス。そのような出現箇所がない場合は-1。
     */
    public int indexOf(final OneCharSequence oneCharSequence) {

        return indexOf(oneCharSequence, 0);
    }

    /**
     * 指定されたインデックス以降で、指定された部分{@link OneCharSequence}
     * がこの文字列内で最初に出現する位置のインデックスを返します。.
     * 
     * @param oneCharSequence
     *            検索対象の部分{@link OneCharSequence}。
     * @param fromIndex
     *            検索開始位置のインデックス。
     * @return 指定されたインデックス以降で、指定された部分{@link OneCharSequence}
     *         が最初に出現する位置のインデックス。そのような出現箇所がない場合は-1。
     */
    public int indexOf(final OneCharSequence oneCharSequence, final int fromIndex) {

        final List<OneChar> searchOneChars = oneCharSequence.toCharList();

        for (int i = fromIndex; i < this.length(); i++) {

            final int restLength = this.length() - i;
            if (restLength < searchOneChars.size()) {

                return -1;
            }

            for (int j = 0; j < searchOneChars.size(); j++) {

                final OneChar oneChar = this.oneCharList.get(i + j);
                final OneChar searchOneChar = searchOneChars.get(j);

                if (searchOneChar.equals(oneChar)) {

                    if (j == searchOneChars.size() - 1) {

                        return i;
                    }

                    continue;
                }
            }
        }

        return -1;
    }

    /**
     * この{@link OneCharSequence}内で、指定された部分{@link OneCharSequence}
     * が最後に出現する位置のインデックスを返します。.
     * 
     * @param oneCharSequence
     *            検索対象の部分{@link OneCharSequence}。
     * @return 指定された部分{@link OneCharSequence}
     *         が最後に出現する位置のインデックス。そのような出現箇所がない場合は-1。
     */
    public int lastIndexOf(final OneCharSequence oneCharSequence) {

        return this.lastIndexOf(oneCharSequence, this.length() - 1);
    }

    /**
     * この{@link OneCharSequence}内で、指定された部分{@link OneCharSequence}
     * が最後に出現する位置のインデックスを返します(検索は指定されたインデックスから開始され、先頭方向に行われる)。.
     * 
     * @param oneCharSequence
     *            検索対象の部分{@link OneCharSequence}。
     * @param fromIndex
     *            検索開始位置のインデックス。
     * @return 指定された部分{@link OneCharSequence}
     *         が最後に出現する位置のインデックス(指定されたインデックスから逆方向に検索を行う)。そのような出現箇所がない場合は-1。
     */
    public int lastIndexOf(final OneCharSequence oneCharSequence, final int fromIndex) {

        final List<OneChar> searchOneChars = oneCharSequence.toCharList();

        for (int i = fromIndex; i >= 0; i--) {

            for (int j = 0; j < searchOneChars.size(); j++) {

                if (this.oneCharList.size() - 1 < (i + j)) {

                    break;
                }

                final OneChar oneChar = this.oneCharList.get(i + j);
                final OneChar searchOneChar = searchOneChars.get(j);

                if (searchOneChar.equals(oneChar)) {

                    if (j == searchOneChars.size() - 1) {

                        return i;
                    }

                    continue;
                }
            }
        }

        return -1;
    }

    /**
     * この文字列を{@link OneChar}リストに変換します。.
     * 
     * @return {@link OneChar}リスト。長さはこの{@link OneCharSequence}の長さと同じで、内容はこの
     *         {@link OneCharSequence}によって表される文字シーケンスが格納されている。
     */
    public List<OneChar> toCharList() {

        return this.oneCharList;
    }

    /**
     * この文字列を異体字選択符号を無視した{@link OneCharSequence}に変換します。.
     * 
     * @return {@link OneCharSequence}
     */
    public OneCharSequence toOneCharSequenceIgnoreVariation() {

        final List<OneChar> oneCharListIgnoreVariation = this.oneCharList.stream().sequential().map(oneChar -> oneChar.toOneCharIgnoreVariation())
                .collect(Collectors.toList());
        return new OneCharSequence(oneCharListIgnoreVariation.toArray(new OneChar[0]));
    }

    /**
     * この{@link OneCharSequence}
     * と指定されたオブジェクトを異体字選択符号を無視して比較します。引数がnullではなく、このオブジェクトと同じ
     * {@link OneCharSequence}を表す{@link OneCharSequence}
     * オブジェクトである場合にだけ、結果はtrueになります。.
     * 
     * @param anotherObject
     *            この{@link OneCharSequence}と比較するオブジェクト
     * @return 指定されたオブジェクトがこの{@link OneCharSequence}に等しい{@link OneCharSequence}
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

        final OneCharSequence anotherOneCharSequence = (OneCharSequence) anotherObject;

        assert(this.toString() != null);
        return this.toOneCharSequenceIgnoreVariation().equals(anotherOneCharSequence.toOneCharSequenceIgnoreVariation());
    }

    /**
     * この文字列を{@link UniCharSequence}に変換します。.
     * 
     * @return {@link UniCharSequence}
     */
    public UniCharSequence toUniCharSequence() {

        return this.uniCharSequence;
    }

    /**
     * 2つの文字列を辞書的に比較します。.<br>
     * 比較は文字列内のそれぞれの文字のUnicodeコード単位値に基づいて行われます。 この{@link OneCharSequence}
     * オブジェクトによって表される文字シーケンスが、引数文字列によって表される文字シーケンスと辞書的に比較されます。 この
     * {@link OneCharSequence}オブジェクトが辞書的に引数文字列より前にある場合は、結果は負の整数になります。 この
     * {@link OneCharSequence}オブジェクトが辞書的に引数文字列の後ろにある場合、結果は正の整数になります。
     * それらの文字列が等しい場合、結果はゼロになります。
     * compareToから0が返されるのは、equals(Object)メソッドからtrueが返される場合だけです。 辞書的の順序の定義を示します。
     * 2つの文字列が異なる場合、両方の文字列に対して有効なインデックスに位置する文字が異なるか、2つの文字列の長さが異なるか、
     * あるいはその両方が該当します。
     * 1つ以上のインデックス位置にある(異体字選択符号を除いた)文字が異なる場合は、このうちのもっとも小さいインデックスをkとすると、
     * 「より小さい」値と判定される、位置kにある文字を持つ文字列が、もう一方の文字列より辞書的に前になります。
     * この場合、compareToは2つの文字列で位置kにある2つの(異体字選択符号を除いた)文字の値の差を返します。
     * これは次の式で表される値になります。
     * 
     * this.charAt(k).equalsIgnoreVariation().compareTo(anotherOneChar.
     * equalsIgnoreVariation())<br>
     * 
     * 有効なすべてのインデックス位置における(異体字選択符号を除いた)文字が同じ場合は、短い方の文字列が辞書的に前になります。
     * この場合は、compareToは文字列の長さの差を返します。これは次の式で表される値になります。
     * 
     * this.length()-anotherOneCharSequence.length()<br>
     * 
     * 有効なすべてのインデックス位置における(異体字選択符号を除いた)文字が同じで長さも同じ場合は、
     * compareToは2つの文字列で位置kにある2つの文字の値の差を返します。
     * この場合、compareToは2つの文字列で位置kにある2つの文字の値の差を返します。これは次の式で表される値になります。
     * 
     * this.charAt(k).compareTo(anotherOneChar)<br>
     * 
     * @param anotherOneCharSequence
     *            - 比較対象の{@link OneCharSequence}。
     * @return 引数文字列がこの文字列に等しい場合は、値0。<br>
     *         この文字列が文字列引数より辞書式に小さい場合は、0より小さい値。<br>
     *         この文字列が文字列引数より辞書式に大きい場合は、0より大きい値。
     */
    @Override
    public int compareTo(final OneCharSequence anotherOneCharSequence) {

        final int length1 = this.length();
        final int length2 = anotherOneCharSequence.length();
        final int limit = Math.min(length1, length2);
        final List<OneChar> oneChars = this.toCharList();
        final List<OneChar> anotherOneChars = anotherOneCharSequence.toCharList();

        int i = 0;
        int oneCharCompareResult = 0;
        while (i < limit) {

            final OneChar oneChar = oneChars.get(i);
            final OneChar anotherOneChar = anotherOneChars.get(i);
            if (!oneChar.equalsIgnoreVariation(anotherOneChar)) {
                // 異体字でない
                return oneChar.compareTo(anotherOneChar);
            } else {
                // (同じ文字の)異体字
                if (oneCharCompareResult == 0) {

                    // 最初に(バリエーションのみが、)異なった、比較結果を保持
                    oneCharCompareResult = oneChar.compareTo(anotherOneChar);
                }
            }
            i++;
        }

        if (length1 == length2) {
            // (異体かもしれないが、)全て同じ文字

            return oneCharCompareResult;
        } else {
            // 短い方のOneCharSequenceまでは、(異体かもしれないが、)全て同じ文字

            return length1 - length2;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        return this.uniCharSequence.toString().hashCode();
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

        final OneCharSequence anotherOneCharSequence = (OneCharSequence) anotherObject;

        assert(this.toString() != null);
        return this.toString().equals(anotherOneCharSequence.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return this.uniCharSequence.toString();
    }

    Infomation getInfomation() {

        final Infomation infomation = new Infomation();

        infomation.setName(OneCharSequence.class.getSimpleName());

        infomation.getProperties().put(String.class.getSimpleName(), "'" + this.toString() + "'");
        infomation.getProperties().put("Length", String.valueOf(this.length()));

        this.toCharList().forEach(oneChar -> {

            final Infomation childInfomation = oneChar.getInfomation();

            infomation.getChildInfomations().add(childInfomation);
        });

        return infomation;
    }
}
