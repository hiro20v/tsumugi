/**
 * 
 */
package hiro20v.lang;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author hiro
 *
 */
public class UniCharSequenceTest {

    private static final Map<String, Integer> SAMPLE_STRING_TO_LENGTH_MAP = UniCharSequenceTest.initializeLengthMap();

    private static Map<String, Integer> initializeLengthMap() {

        final Map<String, Integer> map = new HashMap<>();
        map.put(TestConstants.SAMPLE_U00C1, 1);
        map.put(TestConstants.SAMPLE_U0041_U0301, 2);
        map.put(TestConstants.SAMPLE_U3071, 1);
        map.put(TestConstants.SAMPLE_U306F_U309A, 2);
        map.put(TestConstants.SAMPLE_U0066_U0066_U0069, 3);
        map.put(TestConstants.SAMPLE_UFB03, 1);
        map.put(TestConstants.SAMPLE_U2F993, 1);
        map.put(TestConstants.SAMPLE_U2229_UFE00, 2);
        map.put(TestConstants.SAMPLE_U611B, 1);
        map.put(TestConstants.SAMPLE_U9089_UE010F, 2);
        map.put(TestConstants.SAMPLE_1F1EF_1F1F5, 2);
        map.put(TestConstants.SAMPLE_UE0001_UE006A_UE0061_U5E73_UE007F, 5);

        return Collections.unmodifiableMap(map);
    }

    private static String SAMPLE_STRING = SAMPLE_STRING_TO_LENGTH_MAP.entrySet().stream().map(e -> e.getKey()).collect(Collectors.joining());
    private static int SAMPLE_STRING_LENGTH = SAMPLE_STRING_TO_LENGTH_MAP.entrySet().stream().mapToInt(e -> e.getValue()).sum();

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * Test method for {@link hiro20v.lang.UniCharSequence#hashCode()}.
     */
    @Test
    public void testHashCode() {

        assertThat("", new UniCharSequence(TestConstants.SAMPLE_STRING_VARIATION).hashCode(), is(TestConstants.SAMPLE_STRING_VARIATION.hashCode()));

        assertThat("", new UniCharSequence(SAMPLE_STRING).hashCode(), is(SAMPLE_STRING.hashCode()));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#UniCharSequence(java.lang.String)} .
     */
    @Test
    public void testUniCharSequenceString() {

        try {

            new UniCharSequence("");

            new UniCharSequence(new UniChar(TestConstants.U0000).toString());
            new UniCharSequence(new UniChar(TestConstants.UFE00).toString());
            new UniCharSequence(new UniChar(TestConstants.UFE0F).toString());
            new UniCharSequence(new UniChar(TestConstants.UFFFF).toString());
            new UniCharSequence(new UniChar(TestConstants.U10000).toString());
            new UniCharSequence(new UniChar(TestConstants.UE0100).toString());
            new UniCharSequence(new UniChar(TestConstants.UE01EF).toString());
            new UniCharSequence(new UniChar(TestConstants.U10FFFF).toString());

            assertTrue("", true);
        } catch (final Exception e) {

            fail("");
        }
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#UniCharSequence(java.lang.String)} .
     */
    @Test
    public void testUniCharSequenceString_Exception() {

        try {

            new UniCharSequence((String) null);
            fail("");
        } catch (final NullPointerException e) {

            assertThat("", e.getMessage(), nullValue());
        }
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#UniCharSequence(hiro20v.lang.UniChar[])}
     * .
     */
    @Test
    public void testUniCharSequenceUniCharArray() {

        try {

            new UniCharSequence();

            new UniCharSequence(new UniChar(TestConstants.U0000));
            new UniCharSequence(new UniChar(TestConstants.UFE00));
            new UniCharSequence(new UniChar(TestConstants.UFE0F));
            new UniCharSequence(new UniChar(TestConstants.UFFFF));
            new UniCharSequence(new UniChar(TestConstants.U10000));
            new UniCharSequence(new UniChar(TestConstants.UE0100));
            new UniCharSequence(new UniChar(TestConstants.UE01EF));
            new UniCharSequence(new UniChar(TestConstants.U10FFFF));

            new UniCharSequence(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F));

            assertTrue("", true);
        } catch (final Exception e) {

            fail("");
        }
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#UniCharSequence(hiro20v.lang.UniChar[])}
     * .
     */
    @Test
    public void testUniCharSequenceUniCharArray_Exception() {

        try {

            new UniCharSequence((UniChar) null);
            fail("");
        } catch (final NullPointerException e) {

            assertThat("", e.getMessage(), nullValue());
        }

        try {

            new UniCharSequence(new UniChar(null));
            fail("");
        } catch (final NullPointerException e) {

            assertThat("", e.getMessage(), is("uniCharString"));
        }

        try {

            new UniCharSequence(new UniChar(""));
            fail("");
        } catch (final IllegalArgumentException e) {

            assertThat("", e.getMessage(), is("String is not 1 code point. : "));
        }

        try {

            new UniCharSequence(new UniChar("01"));
            fail("");
        } catch (final IllegalArgumentException e) {

            assertThat("", e.getMessage(), is("String is not 1 code point. : 01"));
        }

        try {

            new UniCharSequence((UniChar[]) null);
            fail("");
        } catch (final NullPointerException e) {

            assertThat("", e.getMessage(), nullValue());
        }

        try {

            new UniCharSequence(null, new UniChar("0"));
            fail("");
        } catch (final NullPointerException e) {

            assertThat("", e.getMessage(), nullValue());
        }

        try {

            new UniCharSequence(new UniChar("0"), null);
            fail("");
        } catch (final NullPointerException e) {

            assertThat("", e.getMessage(), nullValue());
        }
    }

    /**
     * Test method for {@link hiro20v.lang.UniCharSequence#length()}.
     */
    @Test
    public void testLength() {

        UniCharSequence sequence = new UniCharSequence(TestConstants.SAMPLE_STRING_VARIATION);
        assertThat("", sequence.length(), is(13));

        sequence = new UniCharSequence(SAMPLE_STRING);
        assertThat("", sequence.length(), is(SAMPLE_STRING_LENGTH));

        sequence = new UniCharSequence();
        assertThat("", sequence.length(), is(0));
    }

    /**
     * Test method for {@link hiro20v.lang.UniCharSequence#charAt(int)}.
     */
    @Test
    public void testCharAt() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);

        assertThat("", sequence.charAt(0).toString(), is(TestConstants.SAMPLE_U0041));
        assertThat("", sequence.charAt(1).toString(), is(TestConstants.SAMPLE_U0301));

        assertThat("", sequence.charAt(2).toString(), is(TestConstants.SAMPLE_U00C1));

        assertThat("", sequence.charAt(3).toString(), is(TestConstants.SAMPLE_U3071));

        assertThat("", sequence.charAt(4).toString(), is(TestConstants.SAMPLE_U9089));
        assertThat("", sequence.charAt(5).toString(), is(TestConstants.SAMPLE_UE010F));

        assertThat("", sequence.charAt(6).toString(), is(TestConstants.SAMPLE_U2229));
        assertThat("", sequence.charAt(7).toString(), is(TestConstants.SAMPLE_UFE00));
        assertThat("", sequence.charAt(8).toString(), is(TestConstants.SAMPLE_UFB03));

        assertThat("", sequence.charAt(9).toString(), is(TestConstants.SAMPLE_U0066));
        assertThat("", sequence.charAt(10).toString(), is(TestConstants.SAMPLE_U0066));
        assertThat("", sequence.charAt(11).toString(), is(TestConstants.SAMPLE_U0069));

        assertThat("", sequence.charAt(12).toString(), is(TestConstants.SAMPLE_U611B));

        assertThat("", sequence.charAt(13).toString(), is(TestConstants.SAMPLE_1F1EF));
        assertThat("", sequence.charAt(14).toString(), is(TestConstants.SAMPLE_1F1F5));

        assertThat("", sequence.charAt(15).toString(), is(TestConstants.SAMPLE_U306F));
        assertThat("", sequence.charAt(16).toString(), is(TestConstants.SAMPLE_U309A));

        assertThat("", sequence.charAt(17).toString(), is(TestConstants.SAMPLE_U2F993));

        assertThat("", sequence.charAt(18).toString(), is(TestConstants.SAMPLE_UE0001));
        assertThat("", sequence.charAt(19).toString(), is(TestConstants.SAMPLE_UE006A));
        assertThat("", sequence.charAt(20).toString(), is(TestConstants.SAMPLE_UE0061));
        assertThat("", sequence.charAt(21).toString(), is(TestConstants.SAMPLE_U5E73));
        assertThat("", sequence.charAt(22).toString(), is(TestConstants.SAMPLE_UE007F));
    }

    /**
     * Test method for {@link hiro20v.lang.UniCharSequence#charAt(int)}.
     */
    @Test
    public void testCharAt_Exception() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);

        try {

            sequence.charAt(-1);
            fail("");
        } catch (final StringIndexOutOfBoundsException e) {

            assertThat("", e.getMessage(), is("String index out of range: -1"));
        }

        try {

            sequence.charAt(SAMPLE_STRING_LENGTH);
            fail("");
        } catch (final StringIndexOutOfBoundsException e) {

            assertThat("", e.getMessage(), is("String index out of range: " + SAMPLE_STRING_LENGTH));
        }
    }

    /**
     * Test method for {@link hiro20v.lang.UniCharSequence#subSequence(int)} .
     */
    @Test
    public void testSubSequenceInt() {

        UniCharSequence sequence = new UniCharSequence("01234");
        assertThat("", sequence.subSequence(0).toString(), is("01234"));

        sequence = new UniCharSequence(SAMPLE_STRING);

        assertThat("", sequence.subSequence(0).toString(), is(SAMPLE_STRING));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#subSequence(int, int)}.
     */
    @Test
    public void testSubSequenceIntInt() {

        UniCharSequence sequence = new UniCharSequence("01234");
        assertThat("", sequence.subSequence(0, 5).toString(), is("01234"));

        sequence = new UniCharSequence(SAMPLE_STRING);
        assertThat("", sequence.subSequence(0, SAMPLE_STRING_LENGTH).toString(), is(SAMPLE_STRING));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#subSequence(int, int)}.
     */
    @Test
    public void testSubSequenceIntInt_Exception() {

        final UniCharSequence sequence = new UniCharSequence("01234");

        try {

            sequence.subSequence(-1, 5);
            fail("");
        } catch (final StringIndexOutOfBoundsException e) {

            assertThat("", e.getMessage(), is("String index out of range: -1"));
        }

        try {

            sequence.subSequence(0, 6);
            fail("");
        } catch (final StringIndexOutOfBoundsException e) {

            assertThat("", e.getMessage(), is("String index out of range: 6"));
        }

        try {

            sequence.subSequence(4, 3);
            fail("");
        } catch (final StringIndexOutOfBoundsException e) {

            assertThat("", e.getMessage(), is("String index out of range: -1"));
        }
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#indexOf(hiro20v.lang.UniCharSequence)}
     * .
     */
    @Test
    public void testIndexOfUniCharSequence() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0041)), is(0));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0301)), is(1));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U00C1)), is(2));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U3071)), is(3));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U9089)), is(4));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE010F)), is(5));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U2229)), is(6));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UFE00)), is(7));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UFB03)), is(8));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0066)), is(9));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0066)), is(9));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0069)), is(11));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U611B)), is(12));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_1F1EF)), is(13));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_1F1F5)), is(14));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U306F)), is(15));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U309A)), is(16));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U2F993)), is(17));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE0001)), is(18));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE006A)), is(19));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE0061)), is(20));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U5E73)), is(21));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE007F)), is(22));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U1F6AB)), is(-1));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#indexOf(hiro20v.lang.UniCharSequence)}
     * .
     */
    @Test
    public void testIndexOfUniCharSequence2() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0041_U0301)), is(0));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U9089_UE010F)), is(4));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U2229_UFE00)), is(6));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0066_U0066_U0069)), is(9));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_1F1EF_1F1F5)), is(13));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U306F_U309A)), is(15));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE0001_UE006A_UE0061_U5E73_UE007F)), is(18));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#indexOf(hiro20v.lang.UniCharSequence, int)}
     * .
     */
    @Test
    public void testIndexOfUniCharSequenceInt() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0041), 10), is(-1));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0301), 10), is(-1));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U00C1), 10), is(-1));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U3071), 10), is(-1));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U9089), 10), is(-1));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE010F), 10), is(-1));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U2229), 10), is(-1));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UFE00), 10), is(-1));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UFB03), 10), is(-1));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0066), 10), is(10));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0066), 10), is(10));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0069), 10), is(11));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U611B), 10), is(12));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_1F1EF), 10), is(13));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_1F1F5), 10), is(14));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U306F), 10), is(15));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U309A), 10), is(16));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U2F993), 10), is(17));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE0001), 10), is(18));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE006A), 10), is(19));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE0061), 10), is(20));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U5E73), 10), is(21));
        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE007F), 10), is(22));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U1F6AB), 10), is(-1));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#indexOf(hiro20v.lang.UniCharSequence, int)}
     * .
     */
    @Test
    public void testIndexOfUniCharSequenceInt2() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0041_U0301), 10), is(-1));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U9089_UE010F), 10), is(-1));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U2229_UFE00), 10), is(-1));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U0066_U0066_U0069), 10), is(-1));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_1F1EF_1F1F5), 10), is(13));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_U306F_U309A), 10), is(15));

        assertThat("", sequence.indexOf(new UniCharSequence(TestConstants.SAMPLE_UE0001_UE006A_UE0061_U5E73_UE007F), 10), is(18));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#lastIndexOf(hiro20v.lang.UniCharSequence)}
     * .
     */
    @Test
    public void testLastIndexOfUniCharSequence() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0041)), is(0));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0301)), is(1));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U00C1)), is(2));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U3071)), is(3));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U9089)), is(4));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE010F)), is(5));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U2229)), is(6));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UFE00)), is(7));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UFB03)), is(8));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0066)), is(10));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0066)), is(10));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0069)), is(11));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U611B)), is(12));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_1F1EF)), is(13));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_1F1F5)), is(14));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U306F)), is(15));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U309A)), is(16));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U2F993)), is(17));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE0001)), is(18));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE006A)), is(19));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE0061)), is(20));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U5E73)), is(21));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE007F)), is(22));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U1F6AB)), is(-1));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#lastIndexOf(hiro20v.lang.UniCharSequence)}
     * .
     */
    @Test
    public void testLastIndexOfUniCharSequence2() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0041_U0301)), is(0));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U9089_UE010F)), is(4));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U2229_UFE00)), is(6));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0066_U0066_U0069)), is(9));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_1F1EF_1F1F5)), is(13));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U306F_U309A)), is(15));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE0001_UE006A_UE0061_U5E73_UE007F)), is(18));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#lastIndexOf(hiro20v.lang.UniCharSequence, int)}
     * .
     */
    @Test
    public void testLastIndexOfUniCharSequenceInt() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0041), 10), is(0));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0301), 10), is(1));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U00C1), 10), is(2));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U3071), 10), is(3));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U9089), 10), is(4));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE010F), 10), is(5));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U2229), 10), is(6));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UFE00), 10), is(7));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UFB03), 10), is(8));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0066), 10), is(10));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0066), 10), is(10));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0069), 10), is(-1));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U611B), 10), is(-1));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_1F1EF), 10), is(-1));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_1F1F5), 10), is(-1));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U306F), 10), is(-1));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U309A), 10), is(-1));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U2F993), 10), is(-1));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE0001), 10), is(-1));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE006A), 10), is(-1));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE0061), 10), is(-1));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U5E73), 10), is(-1));
        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE007F), 10), is(-1));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U1F6AB), 10), is(-1));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#lastIndexOf(hiro20v.lang.UniCharSequence, int)}
     * .
     */
    @Test
    public void testLastIndexOfUniCharSequenceInt2() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0041_U0301), 10), is(0));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U9089_UE010F), 10), is(4));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U2229_UFE00), 10), is(6));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U0066_U0066_U0069), 10), is(9));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_1F1EF_1F1F5), 10), is(-1));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_U306F_U309A), 10), is(-1));

        assertThat("", sequence.lastIndexOf(new UniCharSequence(TestConstants.SAMPLE_UE0001_UE006A_UE0061_U5E73_UE007F), 10), is(-1));
    }

    /**
     * Test method for {@link hiro20v.lang.UniCharSequence#toCharList()}.
     */
    @Test
    public void testToCharList() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);
        assertThat("", sequence.toCharList().size(), is(SAMPLE_STRING_LENGTH));

        assertThat("", sequence.toCharList().get(0).toString(), is(TestConstants.SAMPLE_U0041));
        assertThat("", sequence.toCharList().get(1).toString(), is(TestConstants.SAMPLE_U0301));

        assertThat("", sequence.toCharList().get(2).toString(), is(TestConstants.SAMPLE_U00C1));

        assertThat("", sequence.toCharList().get(3).toString(), is(TestConstants.SAMPLE_U3071));

        assertThat("", sequence.toCharList().get(4).toString(), is(TestConstants.SAMPLE_U9089));
        assertThat("", sequence.toCharList().get(5).toString(), is(TestConstants.SAMPLE_UE010F));

        assertThat("", sequence.toCharList().get(6).toString(), is(TestConstants.SAMPLE_U2229));
        assertThat("", sequence.toCharList().get(7).toString(), is(TestConstants.SAMPLE_UFE00));
        assertThat("", sequence.toCharList().get(8).toString(), is(TestConstants.SAMPLE_UFB03));

        assertThat("", sequence.toCharList().get(9).toString(), is(TestConstants.SAMPLE_U0066));
        assertThat("", sequence.toCharList().get(10).toString(), is(TestConstants.SAMPLE_U0066));
        assertThat("", sequence.toCharList().get(11).toString(), is(TestConstants.SAMPLE_U0069));

        assertThat("", sequence.toCharList().get(12).toString(), is(TestConstants.SAMPLE_U611B));

        assertThat("", sequence.toCharList().get(13).toString(), is(TestConstants.SAMPLE_1F1EF));
        assertThat("", sequence.toCharList().get(14).toString(), is(TestConstants.SAMPLE_1F1F5));

        assertThat("", sequence.toCharList().get(15).toString(), is(TestConstants.SAMPLE_U306F));
        assertThat("", sequence.toCharList().get(16).toString(), is(TestConstants.SAMPLE_U309A));

        assertThat("", sequence.toCharList().get(17).toString(), is(TestConstants.SAMPLE_U2F993));

        assertThat("", sequence.toCharList().get(18).toString(), is(TestConstants.SAMPLE_UE0001));
        assertThat("", sequence.toCharList().get(19).toString(), is(TestConstants.SAMPLE_UE006A));
        assertThat("", sequence.toCharList().get(20).toString(), is(TestConstants.SAMPLE_UE0061));
        assertThat("", sequence.toCharList().get(21).toString(), is(TestConstants.SAMPLE_U5E73));
        assertThat("", sequence.toCharList().get(22).toString(), is(TestConstants.SAMPLE_UE007F));
    }

    /**
     * Test method for {@link hiro20v.lang.UniCharSequence#toCharList()}.
     */
    @Test
    public void testToCharList_Exception() {

        final UniCharSequence sequence = new UniCharSequence(SAMPLE_STRING);
        try {

            sequence.toCharList().add(new UniChar("0"));
            fail("");
        } catch (final UnsupportedOperationException e) {

            assertThat("", e.getMessage(), nullValue());
        }
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#compareTo(hiro20v.lang.UniCharSequence)}
     * .
     */
    @Test
    public void testCompareTo() {

        final UniCharSequence sequence = new UniCharSequence(TestConstants.SAMPLE_U82B1);
        assertThat("", sequence.compareTo(sequence), is(0));

        final UniCharSequence sequence0 = new UniCharSequence(TestConstants.SAMPLE_U82B2);
        assertThat("", sequence.compareTo(sequence0), is(-1));

        final UniCharSequence sequence1 = new UniCharSequence(TestConstants.SAMPLE_U2F993);
        assertThat("", sequence.compareTo(sequence1), is(-1));

        assertThat("", sequence0.compareTo(sequence1), is(-1));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#compareTo(hiro20v.lang.UniCharSequence)}
     * .
     */
    @Test
    public void testCompareTo2() {

        // final String[] defaultOrder =
        // IntStream.range(Character.MIN_CODE_POINT,
        // Character.MAX_CODE_POINT).mapToObj(codePoint->new String(new
        // int[]{codePoint},0,1)).collect(Collectors.toList()).toArray(new
        // String[0]);
        final String[] defaultOrder = new String[] { //
                new String(new int[] { Character.MIN_CODE_POINT }, 0, 1), new String(new int[] { TestConstants.U10000 }, 0, 1),
                new String(new int[] { TestConstants.UE0100 }, 0, 1), new String(new int[] { TestConstants.UE01EF }, 0, 1),
                new String(new int[] { Character.MAX_CODE_POINT }, 0, 1), new String(new int[] { TestConstants.UFE00 }, 0, 1),
                new String(new int[] { TestConstants.UFE0F }, 0, 1), new String(new int[] { TestConstants.UFFFF }, 0, 1), };

        SortedSet<String> set = new TreeSet<>(Arrays.asList(defaultOrder));
        assertThat("", set.size(), is(defaultOrder.length));
        assertThat("", defaultOrder, is(set.toArray(new String[0])));

        final Comparator<String> comparator = (final String s1, final String s2) -> new UniCharSequence(s1).compareTo(new UniCharSequence(s2));

        // final String[] uniCharSequenceOrder =
        // IntStream.range(Character.MIN_CODE_POINT,
        // Character.MAX_CODE_POINT).mapToObj(codePoint->new String(new
        // int[]{codePoint},0,1)).collect(Collectors.toList()).toArray(new
        // String[0]);
        final String[] uniCharSequenceOrder = new String[] { //
                new String(new int[] { Character.MIN_CODE_POINT }, 0, 1), new String(new int[] { TestConstants.UFE00 }, 0, 1),
                new String(new int[] { TestConstants.UFE0F }, 0, 1), new String(new int[] { TestConstants.UFFFF }, 0, 1),
                new String(new int[] { TestConstants.U10000 }, 0, 1), new String(new int[] { TestConstants.UE0100 }, 0, 1),
                new String(new int[] { TestConstants.UE01EF }, 0, 1), new String(new int[] { Character.MAX_CODE_POINT }, 0, 1), };

        set = new TreeSet<>(comparator);
        Collections.addAll(set, uniCharSequenceOrder);
        assertThat("", set.size(), is(uniCharSequenceOrder.length));
        assertThat("", uniCharSequenceOrder, is(set.toArray(new String[0])));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#compareTo(hiro20v.lang.UniCharSequence)}
     * .
     */
    @Test
    public void testCompareTo_Exception() {

        try {

            new UniCharSequence(TestConstants.SAMPLE_U82B1).compareTo(null);
            fail("");
        } catch (final NullPointerException e) {

            assertThat("", e.getMessage(), nullValue());
        }
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniCharSequence#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {

        final UniCharSequence sequence = new UniCharSequence(TestConstants.SAMPLE_U82B1);
        assertThat("", sequence.equals(sequence), is(true));

        final UniCharSequence sequence0 = new UniCharSequence(TestConstants.SAMPLE_U82B2);
        assertThat("", sequence.equals(sequence0), is(false));

        final UniCharSequence sequence1 = new UniCharSequence(TestConstants.SAMPLE_U2F993);
        assertThat("", sequence.equals(sequence1), is(false));

        assertThat("", sequence.equals(null), is(false));
        assertThat("", sequence.equals(""), is(false));

        assertThat("", sequence0.equals(sequence1), is(false));
    }

    /**
     * Test method for {@link hiro20v.lang.UniCharSequence#toString()}.
     */
    @Test
    public void testToString() {

        assertThat("", new UniCharSequence(TestConstants.SAMPLE_STRING_VARIATION).toString(), is(TestConstants.SAMPLE_STRING_VARIATION));

        assertThat("", new UniCharSequence(SAMPLE_STRING).toString(), is(SAMPLE_STRING));
    }

    /**
     * Test method for {@link hiro20v.lang.UniCharSequence#getInfomation()}.
     */
    @Test
    public void testGetInfomation() {

        assertThat("", new UniCharSequence(TestConstants.SAMPLE_STRING_VARIATION).getInfomation(), notNullValue());
    }
}
