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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author hiro
 *
 */
public class OneCharTest {

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
	 * Test method for {@link hiro20v.lang.OneChar#hashCode()}.
	 */
	@Test
	public void testHashCode() {

		assertThat("", new OneChar(new UniChar(TestConstants.U0030)).hashCode(), is("0".hashCode()));
		assertThat("", new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F)).hashCode(), is(TestConstants.SAMPLE_U9089_UE010F.hashCode()));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneChar#OneChar(hiro20v.lang.UniChar[])}.
	 */
	@Test
	public void testOneChar() {

		try {

			new OneChar(new UniChar(TestConstants.U0000));
			new OneChar(new UniChar(TestConstants.UFE00));
			new OneChar(new UniChar(TestConstants.UFE0F));
			new OneChar(new UniChar(TestConstants.UFFFF));
			new OneChar(new UniChar(TestConstants.U10000));
			new OneChar(new UniChar(TestConstants.UE0100));
			new OneChar(new UniChar(TestConstants.UE01EF));
			new OneChar(new UniChar(TestConstants.U10FFFF));

			new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F));

			assertTrue("", true);
		} catch (final Exception e) {

			fail("");
		}
	}

	/**
	 * Test method for {@link hiro20v.lang.OneChar#OneChar(hiro20v.lang.UniChar[])}.
	 */
	@Test
	public void testOneChar_Exception() {

		try {

			new OneChar((UniChar) null);
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneChar(new UniChar(null));
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), is("uniCharString"));
		}

		try {

			new OneChar(new UniChar(""));
			fail("");
		} catch (final IllegalArgumentException e) {

			assertThat("", e.getMessage(), is("String is not 1 code point. : "));
		}

		try {

			new OneChar(new UniChar("01"));
			fail("");
		} catch (final IllegalArgumentException e) {

			assertThat("", e.getMessage(), is("String is not 1 code point. : 01"));
		}

		try {

			new OneChar((UniChar[]) null);
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneChar(null, new UniChar("0"));
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneChar(new UniChar("0"), null);
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}

		try {

			new OneChar(new UniChar("0"), new UniChar(TestConstants.SAMPLE_U1F6AB));
			fail("");
		} catch (final IllegalArgumentException e) {

			assertThat("", e.getMessage(), is("UniChar[] is not 1 one char. : 0🚫"));
		}

		try {

			new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F), new UniChar(TestConstants.SAMPLE_U1F6AB));
			fail("");
		} catch (final IllegalArgumentException e) {

			assertThat("", e.getMessage(), is("UniChar[] is not 1 one char. : 邉\uDB40\uDD0F🚫"));
		}
	}

	/**
	 * Test method for {@link hiro20v.lang.OneChar#toUniCharList()}.
	 */
	@Test
	public void testToUniCharList() {

		OneChar oneChar = new OneChar(new UniChar(TestConstants.SAMPLE_U9089));
		assertThat("", oneChar.toUniCharList().size(), is(1));
		assertThat("", oneChar.toUniCharList().get(0).toString(), is(TestConstants.SAMPLE_U9089));

		oneChar = new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F));
		assertThat("", oneChar.toUniCharList().size(), is(2));
		assertThat("", oneChar.toUniCharList().get(0).toString(), is(TestConstants.SAMPLE_U9089));
		assertThat("", oneChar.toUniCharList().get(1).toString(), is(TestConstants.SAMPLE_UE010F));

		oneChar = new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE0110));
		assertThat("", oneChar.toUniCharList().size(), is(2));
		assertThat("", oneChar.toUniCharList().get(0).toString(), is(TestConstants.SAMPLE_U9089));
		assertThat("", oneChar.toUniCharList().get(1).toString(), is(TestConstants.SAMPLE_UE0110));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneChar#toUniCharList()}.
	 */
	@Test
	public void testToUniCharList_Exception() {

		final OneChar oneChar = new OneChar(new UniChar(TestConstants.SAMPLE_U9089));
		try {

			oneChar.toUniCharList().add(new UniChar("0"));
			fail("");
		} catch (final UnsupportedOperationException e) {

			assertThat("", e.getMessage(), nullValue());
		}
	}

	/**
	 * Test method for {@link hiro20v.lang.OneChar#toOneCharIgnoreVariation()}.
	 */
	@Test
	public void testToOneCharIgnoreVariation() {

		final OneChar oneChar = new OneChar(new UniChar(TestConstants.SAMPLE_U9089));
		assertThat("", oneChar.equals(oneChar.toOneCharIgnoreVariation()), is(true));

		final OneChar oneChar0 = new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F));
		assertThat("", oneChar.equals(oneChar0.toOneCharIgnoreVariation()), is(true));

		final OneChar oneChar1 = new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE0110));
		assertThat("", oneChar.equals(oneChar1.toOneCharIgnoreVariation()), is(true));
	}

	/**
	 * Test method for
	 * {@link hiro20v.lang.OneChar#equalsIgnoreVariation(java.lang.Object)}.
	 */
	@Test
	public void testEqualsIgnoreVariation() {

		final OneChar oneChar = new OneChar(new UniChar(TestConstants.SAMPLE_U9089));
		assertThat("", oneChar.equalsIgnoreVariation(oneChar), is(true));

		final OneChar oneChar0 = new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F));
		assertThat("", oneChar.equalsIgnoreVariation(oneChar0), is(true));

		final OneChar oneChar1 = new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE0110));
		assertThat("", oneChar.equalsIgnoreVariation(oneChar1), is(true));

		assertThat("", oneChar.equalsIgnoreVariation(null), is(false));
		assertThat("", oneChar.equalsIgnoreVariation(""), is(false));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneChar#compareTo(hiro20v.lang.OneChar)}.
	 */
	@Test
	public void testCompareTo() {

		final OneChar oneChar = new OneChar(new UniChar(TestConstants.SAMPLE_U9089));
		assertThat("", oneChar.compareTo(oneChar), is(0));

		final OneChar oneChar0 = new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F));
		assertThat("", oneChar.compareTo(oneChar0), is(-1));

		final OneChar oneChar1 = new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE0110));
		assertThat("", oneChar.compareTo(oneChar1), is(-1));

		assertThat("", oneChar0.compareTo(oneChar1), is(-1));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneChar#compareTo(hiro20v.lang.OneChar)}.
	 */
	@Test
	public void testCompareTo_Exception() {

		try {

			new OneChar(new UniChar(TestConstants.SAMPLE_U9089)).compareTo(null);
			fail("");
		} catch (final NullPointerException e) {

			assertThat("", e.getMessage(), nullValue());
		}
	}

	/**
	 * Test method for {@link hiro20v.lang.OneChar#equals(java.lang.Object)} .
	 */
	@Test
	public void testEqualsObject() {

		final OneChar oneChar = new OneChar(new UniChar(TestConstants.SAMPLE_U9089));
		assertThat("", oneChar.equals(oneChar), is(true));

		final OneChar oneChar0 = new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F));
		assertThat("", oneChar.equals(oneChar0), is(false));

		final OneChar oneChar1 = new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE0110));
		assertThat("", oneChar.equals(oneChar1), is(false));

		assertThat("", oneChar.equals(null), is(false));
		assertThat("", oneChar.equals(""), is(false));

		assertThat("", oneChar0.equals(oneChar1), is(false));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneChar#toString()}.
	 */
	@Test
	public void testToString() {

		assertThat("", new OneChar(new UniChar(TestConstants.U0030)).toString(), is("0"));
		assertThat("", new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F)).toString(), is(TestConstants.SAMPLE_U9089_UE010F));
	}

	/**
	 * Test method for {@link hiro20v.lang.OneChar#getInformation()}.
	 */
	@Test
	public void testGetInformation() {

		assertThat("", new OneChar(new UniChar(TestConstants.SAMPLE_U9089), new UniChar(TestConstants.SAMPLE_UE010F)).getInformation(), notNullValue());
	}
}
