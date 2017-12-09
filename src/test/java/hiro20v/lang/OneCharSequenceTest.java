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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;
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
public class OneCharSequenceTest {

	private static final Map<String, Integer> SAMPLE_STRING_TO_LENGTH_MAP = OneCharSequenceTest.initializeLengthMap();

	private static Map<String, Integer> initializeLengthMap() {

		final Map<String, Integer> map = new HashMap<>();
		map.put(TestConstants.SAMPLE_U00C1, 1);
		map.put(TestConstants.SAMPLE_U0041_U0301, 1);
		map.put(TestConstants.SAMPLE_U3071, 1);
		map.put(TestConstants.SAMPLE_U306F_U309A, 1);
		map.put(TestConstants.SAMPLE_U0066_U0066_U0069, 3);
		map.put(TestConstants.SAMPLE_UFB03, 1);
		map.put(TestConstants.SAMPLE_U2F993, 1);
		map.put(TestConstants.SAMPLE_U2229_UFE00, 1);
		map.put(TestConstants.SAMPLE_U611B, 1);
		map.put(TestConstants.SAMPLE_U9089_UE010F, 1);
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
	 * Test method for {@link hiro20v.lang.OneCharSequence#hashCode()}.
	 */
	@Test
	public void testHashCode() {

		assertThat("", new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_STRING_VARIATION)).hashCode(), is(TestConstants.SAMPLE_STRING_VARIATION.hashCode()));

		assertThat("", new OneCharSequence(new UniCharSequence(SAMPLE_STRING)).hashCode(), is(SAMPLE_STRING.hashCode()));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#OneCharSequence(hiro20v.lang.UniCharSequence)}
	 * .
	 */
	@Test
	public void testOneCharSequenceUniCharSequence() {

		try {

			new OneCharSequence(new UniCharSequence(""));

			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.U0000).toString()));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.UFE00).toString()));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.UFE0F).toString()));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.UFFFF).toString()));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.U10000).toString()));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.UE0100).toString()));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.UE01EF).toString()));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.U10FFFF).toString()));

			assertTrue("", true);
		} catch (final Exception e) {

			fail("");
		}
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#OneCharSequence(hiro20v.lang.UniCharSequence)}
	 * .
	 */
	@Test
	public void testOneCharSequenceUniCharSequence_Exception() {

		try {

			new OneCharSequence(new UniCharSequence((String) null));
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#OneCharSequence(hiro20v.lang.OneChar[])}
	 * .
	 */
	@Test
	public void testOneCharSequenceOneCharArray() {

		try {

			new OneCharSequence(new UniCharSequence());

			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.U0000)));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.UFE00)));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.UFE0F)));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.UFFFF)));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.U10000)));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.UE0100)));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.UE01EF)));
			new OneCharSequence(new UniCharSequence(new UniChar(TestConstants.U10FFFF)));

			assertTrue("", true);
		} catch (final Exception e) {

			fail("");
		}
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#OneCharSequence(hiro20v.lang.OneChar[])}
	 * .
	 */
	@Test
	public void testOneCharSequenceOneCharArray_Exception() {

		try {

			new OneCharSequence((OneChar[]) null);
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneCharSequence(null, new OneChar(new UniChar("0")));
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneCharSequence(new OneChar(new UniChar("0")), null);
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneCharSequence((UniCharSequence) null);
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneCharSequence(new UniCharSequence((UniChar) null));
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneCharSequence(new UniCharSequence(new UniChar(null)));
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), is("uniCharString"));
		}

		try {

			new OneCharSequence(new UniCharSequence(new UniChar("")));
			fail("");
		} catch (final IllegalArgumentException e) {

			assertThat("", e.getMessage(), is("String is not 1 code point. : "));
		}

		try {

			new OneCharSequence(new UniCharSequence(new UniChar("01")));
			fail("");
		} catch (final IllegalArgumentException e) {

			assertThat("", e.getMessage(), is("String is not 1 code point. : 01"));
		}

		try {

			new OneCharSequence(new UniCharSequence((UniChar[]) null));
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneCharSequence(new UniCharSequence(null, new UniChar("0")));
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneCharSequence(new UniCharSequence(new UniChar("0"), null));
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}
	}

	/**
	 * Test method for {@link hiro20v.lang.OneCharSequence#length()}.
	 */
	@Test
	public void testLength() {

		OneCharSequence sequence = new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_STRING_VARIATION));
		assertThat("", sequence.length(), is(12));

		sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));
		assertThat("", sequence.length(), is(SAMPLE_STRING_LENGTH));

		sequence = new OneCharSequence(new UniCharSequence());
		assertThat("", sequence.length(), is(0));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneCharSequence#charAt(int)}.
	 */
	@Test
	public void testCharAt() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));

		assertThat("", sequence.charAt(0).toString(), is(TestConstants.SAMPLE_U0041_U0301));

		assertThat("", sequence.charAt(1).toString(), is(TestConstants.SAMPLE_U00C1));

		assertThat("", sequence.charAt(2).toString(), is(TestConstants.SAMPLE_U3071));

		assertThat("", sequence.charAt(3).toString(), is(TestConstants.SAMPLE_U9089_UE010F));

		assertThat("", sequence.charAt(4).toString(), is(TestConstants.SAMPLE_U2229_UFE00));

		assertThat("", sequence.charAt(5).toString(), is(TestConstants.SAMPLE_UFB03));

		assertThat("", sequence.charAt(6).toString(), is(TestConstants.SAMPLE_U0066));
		assertThat("", sequence.charAt(7).toString(), is(TestConstants.SAMPLE_U0066));
		assertThat("", sequence.charAt(8).toString(), is(TestConstants.SAMPLE_U0069));

		assertThat("", sequence.charAt(9).toString(), is(TestConstants.SAMPLE_U611B));

		assertThat("", sequence.charAt(10).toString(), is(TestConstants.SAMPLE_1F1EF));
		assertThat("", sequence.charAt(11).toString(), is(TestConstants.SAMPLE_1F1F5));

		assertThat("", sequence.charAt(12).toString(), is(TestConstants.SAMPLE_U306F_U309A));

		assertThat("", sequence.charAt(13).toString(), is(TestConstants.SAMPLE_U2F993));

		assertThat("", sequence.charAt(14).toString(), is(TestConstants.SAMPLE_UE0001));
		assertThat("", sequence.charAt(15).toString(), is(TestConstants.SAMPLE_UE006A));
		assertThat("", sequence.charAt(16).toString(), is(TestConstants.SAMPLE_UE0061));
		assertThat("", sequence.charAt(17).toString(), is(TestConstants.SAMPLE_U5E73));
		assertThat("", sequence.charAt(18).toString(), is(TestConstants.SAMPLE_UE007F));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneCharSequence#charAt(int)}.
	 */
	@Test
	public void testCharAt_Exception() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));

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
	 * Test method for {@link hiro20v.lang.OneCharSequence#subSequence(int)} .
	 */
	@Test
	public void testSubSequenceInt() {

		OneCharSequence sequence = new OneCharSequence(new UniCharSequence("01234"));
		assertThat("", sequence.subSequence(0).toString(), is("01234"));

		sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));
		assertThat("", sequence.subSequence(0).toString(), is(SAMPLE_STRING));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneCharSequence#subSequence(int, int)}.
	 */
	@Test
	public void testSubSequenceIntInt() {

		OneCharSequence sequence = new OneCharSequence(new UniCharSequence("01234"));
		assertThat("", sequence.subSequence(0, 5).toString(), is("01234"));

		sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));
		assertThat("", sequence.subSequence(0, SAMPLE_STRING_LENGTH).toString(), is(SAMPLE_STRING));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneCharSequence#subSequence(int, int)}.
	 */
	@Test
	public void testSubSequenceIntInt_Exception() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence("01234"));

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
	 * {@link hiro20v.lang.OneCharSequence#indexOf(hiro20v.lang.OneCharSequence)} .
	 */
	@Test
	public void testIndexOfOneCharSequence() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0041_U0301))), is(0));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U00C1))), is(1));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U3071))), is(2));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089_UE010F))), is(3));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U2229_UFE00))), is(4));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UFB03))), is(5));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066))), is(6));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066))), is(6));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0069))), is(8));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U611B))), is(9));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1EF))), is(10));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1F5))), is(11));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U306F_U309A))), is(12));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U2F993))), is(13));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0001))), is(14));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE006A))), is(15));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0061))), is(16));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U5E73))), is(17));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE007F))), is(18));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U1F6AB))), is(-1));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#indexOf(hiro20v.lang.OneCharSequence)} .
	 */
	@Test
	public void testIndexOfOneCharSequence2() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066_U0066_U0069))), is(6));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1EF_1F1F5))), is(10));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0001_UE006A_UE0061_U5E73_UE007F))), is(14));

	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#indexOf(hiro20v.lang.OneCharSequence, int)}
	 * .
	 */
	@Test
	public void testIndexOfOneCharSequenceInt() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0041_U0301)), 7), is(-1));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U00C1)), 7), is(-1));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U3071)), 7), is(-1));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089_UE010F)), 7), is(-1));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U2229_UFE00)), 7), is(-1));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UFB03)), 7), is(-1));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066)), 7), is(7));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066)), 7), is(7));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0069)), 7), is(8));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U611B)), 7), is(9));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1EF)), 7), is(10));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1F5)), 7), is(11));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U306F_U309A)), 7), is(12));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U2F993)), 7), is(13));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0001)), 7), is(14));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE006A)), 7), is(15));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0061)), 7), is(16));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U5E73)), 7), is(17));
		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE007F)), 7), is(18));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U1F6AB)), 7), is(-1));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#indexOf(hiro20v.lang.OneCharSequence, int)}
	 * .
	 */
	@Test
	public void testIndexOfOneCharSequenceInt2() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066_U0066_U0069)), 7), is(-1));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1EF_1F1F5)), 7), is(10));

		assertThat("", sequence.indexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0001_UE006A_UE0061_U5E73_UE007F)), 7), is(14));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#lastIndexOf(hiro20v.lang.OneCharSequence)}
	 * .
	 */
	@Test
	public void testLastIndexOfOneCharSequence() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0041_U0301))), is(0));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U00C1))), is(1));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U3071))), is(2));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089_UE010F))), is(3));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U2229_UFE00))), is(4));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UFB03))), is(5));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066))), is(7));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066))), is(7));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0069))), is(8));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U611B))), is(9));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1EF))), is(10));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1F5))), is(11));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U306F_U309A))), is(12));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U2F993))), is(13));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0001))), is(14));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE006A))), is(15));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0061))), is(16));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U5E73))), is(17));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE007F))), is(18));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U1F6AB))), is(-1));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#lastIndexOf(hiro20v.lang.OneCharSequence)}
	 * .
	 */
	@Test
	public void testLastIndexOfOneCharSequence2() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066_U0066_U0069))), is(6));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1EF_1F1F5))), is(10));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0001_UE006A_UE0061_U5E73_UE007F))), is(14));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#lastIndexOf(hiro20v.lang.OneCharSequence, int)}
	 * .
	 */
	@Test
	public void testLastIndexOfOneCharSequenceInt() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0041_U0301)), 7), is(0));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U00C1)), 7), is(1));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U3071)), 7), is(2));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089_UE010F)), 7), is(3));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U2229_UFE00)), 7), is(4));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UFB03)), 7), is(5));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066)), 7), is(7));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066)), 7), is(7));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0069)), 7), is(-1));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U611B)), 7), is(-1));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1EF)), 7), is(-1));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1F5)), 7), is(-1));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U306F_U309A)), 7), is(-1));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U2F993)), 7), is(-1));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0001)), 7), is(-1));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE006A)), 7), is(-1));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0061)), 7), is(-1));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U5E73)), 7), is(-1));
		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE007F)), 7), is(-1));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U1F6AB)), 7), is(-1));
		// assertThat("", sequence.lastIndexOf(new OneCharSequence(new
		// UniCharSequence(SAMPLE_STRING.substring(3))), 7), is(-1));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#lastIndexOf(hiro20v.lang.OneCharSequence, int)}
	 * .
	 */
	@Test
	public void testLastIndexOfOneCharSequenceInt2() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U0066_U0066_U0069)), 7), is(6));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_1F1EF_1F1F5)), 7), is(-1));

		assertThat("", sequence.lastIndexOf(new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_UE0001_UE006A_UE0061_U5E73_UE007F)), 7), is(-1));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneCharSequence#toCharList()}.
	 */
	@Test
	public void testToCharList() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));
		assertThat("", sequence.toCharList().size(), is(SAMPLE_STRING_LENGTH));

		assertThat("", sequence.toCharList().get(0).toString(), is(TestConstants.SAMPLE_U0041_U0301));

		assertThat("", sequence.toCharList().get(1).toString(), is(TestConstants.SAMPLE_U00C1));

		assertThat("", sequence.toCharList().get(2).toString(), is(TestConstants.SAMPLE_U3071));

		assertThat("", sequence.toCharList().get(3).toString(), is(TestConstants.SAMPLE_U9089_UE010F));

		assertThat("", sequence.toCharList().get(4).toString(), is(TestConstants.SAMPLE_U2229_UFE00));

		assertThat("", sequence.toCharList().get(5).toString(), is(TestConstants.SAMPLE_UFB03));

		assertThat("", sequence.toCharList().get(6).toString(), is(TestConstants.SAMPLE_U0066));
		assertThat("", sequence.toCharList().get(7).toString(), is(TestConstants.SAMPLE_U0066));
		assertThat("", sequence.toCharList().get(8).toString(), is(TestConstants.SAMPLE_U0069));

		assertThat("", sequence.toCharList().get(9).toString(), is(TestConstants.SAMPLE_U611B));

		assertThat("", sequence.toCharList().get(10).toString(), is(TestConstants.SAMPLE_1F1EF));
		assertThat("", sequence.toCharList().get(11).toString(), is(TestConstants.SAMPLE_1F1F5));

		assertThat("", sequence.toCharList().get(12).toString(), is(TestConstants.SAMPLE_U306F_U309A));

		assertThat("", sequence.toCharList().get(13).toString(), is(TestConstants.SAMPLE_U2F993));

		assertThat("", sequence.toCharList().get(14).toString(), is(TestConstants.SAMPLE_UE0001));
		assertThat("", sequence.toCharList().get(15).toString(), is(TestConstants.SAMPLE_UE006A));
		assertThat("", sequence.toCharList().get(16).toString(), is(TestConstants.SAMPLE_UE0061));
		assertThat("", sequence.toCharList().get(17).toString(), is(TestConstants.SAMPLE_U5E73));
		assertThat("", sequence.toCharList().get(18).toString(), is(TestConstants.SAMPLE_UE007F));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneCharSequence#toCharList()}.
	 */
	@Test
	public void testToCharList_Exception() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));
		try {

			sequence.toCharList().add(new OneChar(new UniChar("0")));
			fail("");
		} catch (final UnsupportedOperationException e) {

			assertThat("", e.getMessage(), nullValue());
		}
	}

	private final static String REGEX_STRING_VARIATION_SELECTOR = "[\uFE00-\uFEFF]|[\uDB40\uDD00-\uDB40\uDDEF]";
	private final static Pattern PATTERN_VARIATION_SELECTOR = Pattern.compile(REGEX_STRING_VARIATION_SELECTOR);

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#toOneCharSequenceIgnoreVariation()} .
	 */
	@Test
	public void testToOneCharSequenceIgnoreVariation() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));
		assertThat("", sequence.toOneCharSequenceIgnoreVariation(), is(new OneCharSequence(new UniCharSequence(PATTERN_VARIATION_SELECTOR.matcher(SAMPLE_STRING).replaceAll("")))));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#equalsIgnoreVariation(Object)} .
	 */
	@Test
	public void testEqualsIgnoreVariation() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089));
		assertThat("", sequence.equalsIgnoreVariation(sequence), is(true));

		final OneCharSequence sequence0 = new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE010F));
		assertThat("", sequence0.equalsIgnoreVariation(sequence0), is(true));

		final OneCharSequence sequence1 = new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089 + "\uDB40\uDD10"));
		assertThat("", sequence0.equalsIgnoreVariation(sequence), is(true));

		assertThat("", sequence0.equalsIgnoreVariation(null), is(false));
		assertThat("", sequence0.equalsIgnoreVariation(""), is(false));

		assertThat("", sequence0.equalsIgnoreVariation(sequence1), is(true));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneCharSequence#toUniCharSequence()}.
	 */
	@Test
	public void testToUniCharSequence() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(SAMPLE_STRING));
		assertThat("", sequence.toUniCharSequence(), is(new UniCharSequence(SAMPLE_STRING)));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#compareTo(hiro20v.lang.OneCharSequence)}
	 * .
	 */
	@Test
	public void testCompareTo() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089));
		assertThat("", sequence.compareTo(sequence), is(0));

		final OneCharSequence sequence0 = new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE010F));
		assertThat("", sequence.compareTo(sequence0), is(-1));

		final OneCharSequence sequence1 = new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE0110));
		assertThat("", sequence.compareTo(sequence1), is(-1));

		assertThat("", sequence0.compareTo(sequence1), is(-1));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#compareTo(hiro20v.lang.OneCharSequence)}
	 * .
	 */
	@Test
	public void testCompareTo2() {

		final String[] defaultOrder = new String[] { //
				"\u2F9E", // '車'
				"\uD83D\uDE00", // '😀'1F600
		};

		SortedSet<String> set = new TreeSet<>(Arrays.asList(defaultOrder));
		assertThat("", set.size(), is(defaultOrder.length));
		assertThat("", defaultOrder, is(set.toArray(new String[0])));

		final Comparator<String> comparator = (final String s1, final String s2) -> new OneCharSequence(new UniCharSequence(s1)).compareTo(new OneCharSequence(new UniCharSequence(s2)));

		String[] oneCharSequenceOrder = new String[] { //
				"\u2F9E", // '車'
				"\uD83D\uDE00", // '😀'(1F600)
		};

		set = new TreeSet<>(comparator);
		Collections.addAll(set, oneCharSequenceOrder);
		assertThat("", set.size(), is(oneCharSequenceOrder.length));
		assertThat("", oneCharSequenceOrder, is(set.toArray(new String[0])));

		oneCharSequenceOrder = new String[] { //
				"\u3402" + "\uDB40\uDD00", // '㐂'+VS17
				"\u3402" + "\u2F9E",// '㐂'+'車'
		};

		set = new TreeSet<>(comparator);
		Collections.addAll(set, oneCharSequenceOrder);
		assertThat("", set.size(), is(oneCharSequenceOrder.length));
		assertThat("", oneCharSequenceOrder, is(set.toArray(new String[0])));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#compareTo(hiro20v.lang.OneCharSequence)}
	 * .
	 */
	@Test
	public void testCompareTo3() {

		final Comparator<String> comparator = (final String s1, final String s2) -> new OneCharSequence(new UniCharSequence(s1)).compareTo(new OneCharSequence(new UniCharSequence(s2)));

		String[] order = new String[] { //
				TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE010F + TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE0110, // '邉'+VS17,'邉'+VS18
				TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE0110 + TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE010F, // '邉'+VS18,'邉'+VS17
		};

		SortedSet<String> set = new TreeSet<>(comparator);
		Collections.addAll(set, order);
		assertThat("", set.size(), is(order.length));
		assertThat("", order, is(set.toArray(new String[0])));

		order = new String[] { //
				TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE0110 + TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE010F, // '邉'+VS18,'邉'+VS17
				TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE010F + TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE0110 + TestConstants.SAMPLE_U0041, // '邉'+VS17,'邉'+VS18,'A'
		};

		set = new TreeSet<>(comparator);
		Collections.addAll(set, order);
		assertThat("", set.size(), is(order.length));
		assertThat("", order, is(set.toArray(new String[0])));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#compareTo(hiro20v.lang.OneCharSequence)}
	 * .
	 */
	@Test
	public void testCompareTo4() {

		final Comparator<String> comparator = (final String s1, final String s2) -> new OneCharSequence(new UniCharSequence(s1)).compareTo(new OneCharSequence(new UniCharSequence(s2)));

		final String[] order = new String[] { //
				"あ", //
				"あい", //
				"あい㐂", //
				"あい㐂\uDB40\uDD00", //
				"あい㐂\uDB40\uDD01", //
				"あい㐧", //
				"あい㐧\uDB40\uDD00", //
				"あい㐧\uDB40\uDD01", //
				"あ㐂", //
				"あ㐂\uDB40\uDD00", //
				"あ㐂\uDB40\uDD01", //
				"あ㐧", //
				"あ㐧\uDB40\uDD00", //
				"あ㐧\uDB40\uDD01", //
				"い", //
				"い㐂", //
				"い㐂\uDB40\uDD00", //
				"い㐂\uDB40\uDD01", //
				"い㐧", //
				"い㐧\uDB40\uDD00", //
				"い㐧\uDB40\uDD01", //
				"㐂", //
				"㐂\uDB40\uDD00", //
				"㐂\uDB40\uDD01", //
				"㐂㐧", //
				"㐂㐧\uDB40\uDD00", //
				"㐂㐧\uDB40\uDD01", //
				"㐂\uDB40\uDD00㐧", //
				"㐂\uDB40\uDD00㐧\uDB40\uDD00", //
				"㐂\uDB40\uDD00㐧\uDB40\uDD01", //
				"㐂\uDB40\uDD01㐧", //
				"㐂\uDB40\uDD01㐧\uDB40\uDD00", //
				"㐂\uDB40\uDD01㐧\uDB40\uDD01", //
				"㐂㐧あ", //
				"㐂㐧\uDB40\uDD00あ", //
				"㐂㐧\uDB40\uDD01あ", //
				"㐂\uDB40\uDD00㐧あ", //
				"㐂\uDB40\uDD00㐧\uDB40\uDD00あ", //
				"㐂\uDB40\uDD00㐧\uDB40\uDD01あ", //
				"㐂\uDB40\uDD01㐧あ", //
				"㐂\uDB40\uDD01㐧\uDB40\uDD00あ", //
				"㐂\uDB40\uDD01㐧\uDB40\uDD01あ", //
				"㐂㐧あい", //
				"㐂㐧\uDB40\uDD00あい", //
				"㐂㐧\uDB40\uDD01あい", //
				"㐂\uDB40\uDD00㐧あい", //
				"㐂\uDB40\uDD00㐧\uDB40\uDD00あい", //
				"㐂\uDB40\uDD00㐧\uDB40\uDD01あい", //
				"㐂\uDB40\uDD01㐧あい", //
				"㐂\uDB40\uDD01㐧\uDB40\uDD00あい", //
				"㐂\uDB40\uDD01㐧\uDB40\uDD01あい", //
				"㐂㐧い", //
				"㐂㐧\uDB40\uDD00い", //
				"㐂㐧\uDB40\uDD01い", //
				"㐂\uDB40\uDD00㐧い", //
				"㐂\uDB40\uDD00㐧\uDB40\uDD00い", //
				"㐂\uDB40\uDD00㐧\uDB40\uDD01い", //
				"㐂\uDB40\uDD01㐧い", //
				"㐂\uDB40\uDD01㐧\uDB40\uDD00い", //
				"㐂\uDB40\uDD01㐧\uDB40\uDD01い", //
				"㐧", //
				"㐧\uDB40\uDD00", //
				"㐧\uDB40\uDD01", //
		};

		final List<String> list = new ArrayList<>();
		Collections.addAll(list, order);
		list.sort(comparator);
		assertThat("", list.size(), is(order.length));
		assertThat("", order, is(list.toArray(new String[0])));
		//
		// order = new String[] { //
		// TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE0110 +
		// TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE010F, //
		// '邉'+VS18,'邉'+VS17
		// TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE010F +
		// TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE0110
		// + TestConstants.SAMPLE_U0041, // '邉'+VS17,'邉'+VS18,'A'
		// };
		//
		// list = new ArrayList<>();
		// Collections.addAll(list, order);
		// list.sort(comparator);
		// assertThat("", list.size(), is(order.length));
		// assertThat("", order, is(list.toArray(new String[0])));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#compareTo(hiro20v.lang.OneCharSequence)}
	 * .
	 */
	@Test
	public void testCompareTo_Exception() {

		try {

			new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089)).compareTo(null);
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneCharSequence#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {

		final OneCharSequence sequence = new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089));
		assertThat("", sequence.equals(sequence), is(true));

		final OneCharSequence sequence0 = new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE010F));
		assertThat("", sequence.equals(sequence0), is(false));

		final OneCharSequence sequence1 = new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_U9089 + TestConstants.SAMPLE_UE0110));
		assertThat("", sequence.equals(sequence1), is(false));

		assertThat("", sequence.equals(null), is(false));
		assertThat("", sequence.equals(""), is(false));

		assertThat("", sequence0.equals(sequence1), is(false));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneCharSequence#toString()}.
	 */
	@Test
	public void testToString() {

		assertThat("", new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_STRING_VARIATION)).toString(), is(TestConstants.SAMPLE_STRING_VARIATION));

		assertThat("", new OneCharSequence(new UniCharSequence(SAMPLE_STRING)).toString(), is(SAMPLE_STRING));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneCharSequence#getInformation()}.
	 */
	@Test
	public void testGetInformation() {

		assertThat("", new OneCharSequence(new UniCharSequence(TestConstants.SAMPLE_STRING_VARIATION)).getInformation(), notNullValue());
	}
}
