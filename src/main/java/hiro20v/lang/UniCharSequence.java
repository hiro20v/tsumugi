package hiro20v.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 単一文字列.<br>
 * {@link UniChar}の並びを表現します。<br>
 * {@link UniCharSequence}オブジェクトは不変です。
 * 
 * @author hiro
 *
 */
public class UniCharSequence implements Comparable<UniCharSequence> {

    private final List<UniChar> uniCharList;
    private final String uniCharSequenceString;

    /**
     * 指定された{@link java.lang.String}を使用して、新しい{@link UniCharSequence}を構築します。.
     * 
     * @param string
     *            {@link java.lang.String}
     */
    public UniCharSequence(final String string) {

        final List<UniChar> uniCharList = new ArrayList<UniChar>();

        for (int i = 0; i < string.length(); i = string.offsetByCodePoints(i, 1)) {

            uniCharList.add(new UniChar(string.codePointAt(i)));
        }

        this.uniCharList = Collections.unmodifiableList(uniCharList);
        this.uniCharSequenceString = string;
    }

    /**
     * 指定された{@link UniChar}の配列を使用して、新しい{@link UniCharSequence}を構築します。.
     * 
     * @param uniChars
     *            {@link UniChar}の配列
     */
    public UniCharSequence(final UniChar... uniChars) {

        this.uniCharList = Collections.unmodifiableList(Arrays.asList(uniChars));
        this.uniCharSequenceString = this.uniCharList.stream().sequential().map(uniChar -> uniChar.toString()).collect(Collectors.joining());
    }

    /**
     * この{@link UniCharSequence}の長さを返します。長さは{@link UniCharSequence}
     * 内のUnicodeコード単位の数に等しくなります。.
     * 
     * @return このオブジェクトによって表される{@link UniCharSequence}の長さ。
     */
    public int length() {

        return this.uniCharList.size();
    }

    /**
     * 指定されたインデックスの{@link UniChar}値を返します。インデックスの範囲は0からlength() -
     * 1までです。配列のインデックス付けの場合と同じように、シーケンスの最初の{@link UniChar}のインデックスは0、次の
     * {@link UniChar}のインデックスは1と続きます。.
     * 
     * @param index
     *            {@link UniChar}値のインデックス。
     * @return 文字列内の指定されたインデックス位置にある{@link UniChar}値。最初の{@link UniChar}
     *         値のインデックスが0になる。
     */
    public UniChar charAt(final int index) {

        if (index < 0 || index >= length()) {

            throw new StringIndexOutOfBoundsException(index);
        }

        return this.uniCharList.get(index);
    }

    /**
     * このシーケンスのサブシーケンスである{@link UniCharSequence}を返します。.
     * 
     * @param beginIndex
     *            開始インデックス(この値を含む)。
     * @return 指定されたサブシーケンス。
     */
    public UniCharSequence subSequence(final int beginIndex) {

        return subSequence(beginIndex, this.uniCharList.size());
    }

    /**
     * このシーケンスのサブシーケンスである{@link UniCharSequence}を返します。.
     * 
     * @param beginIndex
     *            開始インデックス(この値を含む)。
     * @param endIndex
     *            終了インデックス(この値を含まない)。
     * @return 指定されたサブシーケンス。
     */
    public UniCharSequence subSequence(final int beginIndex, final int endIndex) {

        if (beginIndex < 0) {

            throw new StringIndexOutOfBoundsException(beginIndex);
        }

        if (endIndex > this.uniCharList.size()) {

            throw new StringIndexOutOfBoundsException(endIndex);
        }

        final int subLength = endIndex - beginIndex;
        if (subLength < 0) {

            throw new StringIndexOutOfBoundsException(subLength);
        }

        return new UniCharSequence(this.uniCharList.subList(beginIndex, endIndex).toArray(new UniChar[0]));
    }

    /**
     * この{@link UniCharSequence}内で、指定された部分{@link UniCharSequence}
     * が最初に出現する位置のインデックスを返します。.
     * 
     * @param uniCharSequence
     *            検索対象の部分{@link UniCharSequence}。
     * @return 指定された部分{@link UniCharSequence}
     *         が最初に出現する位置のインデックス。そのような出現箇所がない場合は-1。
     */
    public int indexOf(final UniCharSequence uniCharSequence) {

        return indexOf(uniCharSequence, 0);
    }

    /**
     * 指定されたインデックス以降で、指定された部分{@link UniCharSequence}がこの{@link UniCharSequence}
     * で最初に出現する位置のインデックスを返します。.
     * 
     * @param uniCharSequence
     *            検索対象の部分{@link UniCharSequence}。
     * @param fromIndex
     *            検索開始位置のインデックス。
     * @return 指定されたインデックス以降で、指定された部分{@link UniCharSequence}
     *         が最初に出現する位置のインデックス。そのような出現箇所がない場合は-1。
     */
    public int indexOf(final UniCharSequence uniCharSequence, final int fromIndex) {

        final List<UniChar> searchUniChars = uniCharSequence.toCharList();

        for (int i = fromIndex; i < this.length(); i++) {

            final int restLength = this.length() - i;
            if (restLength < searchUniChars.size()) {

                return -1;
            }

            for (int j = 0; j < searchUniChars.size(); j++) {

                final UniChar uniChar = this.uniCharList.get(i + j);
                final UniChar searchUniChar = searchUniChars.get(j);

                if (searchUniChar.equals(uniChar)) {

                    if (j == searchUniChars.size() - 1) {

                        return i;
                    }

                    continue;
                }
            }
        }

        return -1;
    }

    /**
     * この文字列内で、指定された部分{@link UniCharSequence}が最後に出現する位置のインデックスを返します。.
     * 
     * @param uniCharSequence
     *            検索対象の部分{@link UniCharSequence}。
     * @return 指定された部分{@link UniCharSequence}
     *         が最後に出現する位置のインデックス。そのような出現箇所がない場合は-1。
     */
    public int lastIndexOf(final UniCharSequence uniCharSequence) {

        return this.lastIndexOf(uniCharSequence, this.length() - 1);
    }

    /**
     * この文字列内で、指定された部分{@link UniCharSequence}
     * が最後に出現する位置のインデックスを返します(検索は指定されたインデックスから開始され、先頭方向に行われる)。.
     * 
     * @param uniCharSequence
     *            検索対象の部分{@link UniCharSequence}。
     * @param fromIndex
     *            検索開始位置のインデックス。
     * @return 指定された部分{@link UniCharSequence}
     *         が最後に出現する位置のインデックス(指定されたインデックスから逆方向に検索を行う)。そのような出現箇所がない場合は-1。
     */
    public int lastIndexOf(final UniCharSequence uniCharSequence, final int fromIndex) {

        final List<UniChar> searchUniChars = uniCharSequence.toCharList();

        for (int i = fromIndex; i >= 0; i--) {

            for (int j = 0; j < searchUniChars.size(); j++) {

                if (this.uniCharList.size() - 1 < (i + j)) {

                    break;
                }

                final UniChar uniChar = this.uniCharList.get(i + j);
                final UniChar searchUniChar = searchUniChars.get(j);

                if (searchUniChar.equals(uniChar)) {

                    if (j == searchUniChars.size() - 1) {

                        return i;
                    }

                    continue;
                }
            }
        }

        return -1;
    }

    /**
     * この文字列を{@link UniChar}リストに変換します。.
     * 
     * @return {@link UniChar}リスト。長さはこの{@link UniCharSequence}の長さと同じで、内容はこの
     *         {@link UniCharSequence}によって表される文字シーケンスが格納されている。
     */
    public List<UniChar> toCharList() {

        return this.uniCharList;
    }

    /**
     * 2つの文字列を辞書的に比較します。.<br>
     * 比較は文字列内のそれぞれの文字のUnicodeコード単位値に基づいて行われます。 この{@link UniCharSequence}
     * オブジェクトによって表される文字シーケンスが、引数文字列によって表される文字シーケンスと辞書的に比較されます。 この
     * {@link UniCharSequence}オブジェクトが辞書的に引数文字列より前にある場合は、結果は負の整数になります。 この
     * {@link UniCharSequence}オブジェクトが辞書的に引数文字列の後ろにある場合、結果は正の整数になります。
     * それらの文字列が等しい場合、結果はゼロになります。
     * compareToから0が返されるのは、equals(Object)メソッドからtrueが返される場合だけです。 辞書的の順序の定義を示します。
     * 2つの文字列が異なる場合、両方の文字列に対して有効なインデックスに位置する文字が異なるか、2つの文字列の長さが異なるか、
     * あるいはその両方が該当します。
     * 1つ以上のインデックス位置にある文字が異なる場合は、このうちのもっとも小さいインデックスをkとすると、&lt;演算子を使用して「より小さい」
     * 値と判定される、位置kにある文字を持つ文字列が、もう一方の文字列より辞書的に前になります。
     * この場合、compareToは2つの文字列で位置kにある2つの文字の値の差を返します。 これは次の式で表される値になります。
     * 
     * this.charAt(k).compareTo(anotherUniChar)<br>
     * 
     * 有効なすべてのインデックス位置における文字が同じ場合は、短い方の文字列が辞書的に前になります。
     * この場合は、compareToは文字列の長さの差を返します。これは次の式で表される値になります。
     * this.length()-anotherUniCharSequence.length()<br>
     * 
     * @param anotherUniCharSequence
     *            - 比較対象の{@link UniCharSequence}。
     * @return 引数文字列がこの文字列に等しい場合は、値0。<br>
     *         この文字列が文字列引数より辞書式に小さい場合は、0より小さい値。<br>
     *         この文字列が文字列引数より辞書式に大きい場合は、0より大きい値。
     */
    @Override
    public int compareTo(final UniCharSequence anotherUniCharSequence) {

        final int length1 = this.length();
        final int length2 = anotherUniCharSequence.length();
        final int limit = Math.min(length1, length2);
        final List<UniChar> uniChars = this.toCharList();
        final List<UniChar> anotherUniChars = anotherUniCharSequence.toCharList();

        int i = 0;
        while (i < limit) {

            final UniChar uniChar = uniChars.get(i);
            final UniChar anotherUniChar = anotherUniChars.get(i);
            if (!uniChar.equals(anotherUniChar)) {

                return uniChar.compareTo(anotherUniChar);
            }
            i++;
        }
        return length1 - length2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        return this.uniCharSequenceString.hashCode();
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

        final UniCharSequence anotherUniCharSequence = (UniCharSequence) anotherObject;

        assert(this.toString() != null);
        return this.toString().equals(anotherUniCharSequence.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return this.uniCharSequenceString;
    }

    Infomation getInfomation() {

        final Infomation infomation = new Infomation();

        infomation.setName(UniCharSequence.class.getSimpleName());

        infomation.getProperties().put(String.class.getSimpleName(), "'" + this.toString() + "'");
        infomation.getProperties().put("Length", String.valueOf(this.length()));

        this.toCharList().forEach(uniChar -> {

            final Infomation childInfomation = uniChar.getInfomation();

            infomation.getChildInfomations().add(childInfomation);
        });

        return infomation;
    }
}
