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

import java.lang.Character.UnicodeBlock;
import java.lang.Character.UnicodeScript;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author hiro
 *
 */
public class UniCharTest {

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
     * Test method for {@link hiro20v.lang.UniChar#hashCode()}.
     */
    @Test
    public void testHashCode() {

        assertThat("", new UniChar(TestConstants.U0030).hashCode(), is("0".hashCode()));
    }

    /**
     * Test method for {@link hiro20v.lang.UniChar#UniChar(java.lang.String)}.
     */
    @Test
    public void testUniCharString() {

        try {

            new UniChar(new String(new int[] { TestConstants.U0000 }, 0, 1));
            new UniChar(new String(new int[] { TestConstants.U0001 }, 0, 1));
            new UniChar(new String(new int[] { TestConstants.UFE00 }, 0, 1));
            new UniChar(new String(new int[] { TestConstants.UFFFF }, 0, 1));
            new UniChar(new String(new int[] { TestConstants.U10000 }, 0, 1));
            new UniChar(new String(new int[] { TestConstants.U10FFFF }, 0, 1));

            assertTrue("", true);
        } catch (final Exception e) {

            fail("");
        }
    }

    @Test
    public void testUniCharString_Exception() {

        try {

            new UniChar(null);
            fail("");
        } catch (final NullPointerException e) {

            assertThat("", e.getMessage(), is("uniCharString"));
        }

        try {

            new UniChar("");
            fail("");
        } catch (final IllegalArgumentException e) {

            assertThat("", e.getMessage(), is("String is not 1 code point. : "));
        }

        try {

            new UniChar("01");
            fail("");
        } catch (final IllegalArgumentException e) {

            assertThat("", e.getMessage(), is("String is not 1 code point. : 01"));
        }
    }

    /**
     * Test method for {@link hiro20v.lang.UniChar#UniChar(int)}.
     */
    @Test
    public void testUniCharInt() {

        try {

            new UniChar(TestConstants.U0000);
            new UniChar(TestConstants.UFE00);
            new UniChar(TestConstants.UFE0F);
            new UniChar(TestConstants.UFFFF);
            new UniChar(TestConstants.U10000);
            new UniChar(TestConstants.UE0100);
            new UniChar(TestConstants.UE01EF);
            new UniChar(TestConstants.U10FFFF);

            assertTrue("", true);
        } catch (final Exception e) {

            fail("");
        }
    }

    @Test
    public void testUniCharInt_Exception() {

        try {

            new UniChar(TestConstants.U_MINUS);
            fail("");
        } catch (final IllegalArgumentException e) {

            assertThat("", e.getMessage(), is("-1"));
        }

        try {

            new UniChar(TestConstants.U110000);
            fail("");
        } catch (final IllegalArgumentException e) {

            assertThat("", e.getMessage(), is("1114112"));
        }
    }

    /**
     * Test method for {@link hiro20v.lang.UniChar#getCodePoint()}.
     */
    @Test
    public void testGetCodePoint() {

        assertThat("", new UniChar(TestConstants.U0000).getCodePoint(), is(TestConstants.U0000));
        assertThat("", new UniChar(TestConstants.UFE00).getCodePoint(), is(TestConstants.UFE00));
        assertThat("", new UniChar(TestConstants.UFE0F).getCodePoint(), is(TestConstants.UFE0F));
        assertThat("", new UniChar(TestConstants.UFFFF).getCodePoint(), is(TestConstants.UFFFF));
        assertThat("", new UniChar(TestConstants.U10000).getCodePoint(), is(TestConstants.U10000));
        assertThat("", new UniChar(TestConstants.UE0100).getCodePoint(), is(TestConstants.UE0100));
        assertThat("", new UniChar(TestConstants.UE01EF).getCodePoint(), is(TestConstants.UE01EF));
        assertThat("", new UniChar(TestConstants.U10FFFF).getCodePoint(), is(TestConstants.U10FFFF));
    }

    /**
     * Test method for {@link hiro20v.lang.UniChar#getUnicodeBlock()}.
     */
    @Test
    public void testGetUnicodeBlock() {

        assertThat("", new UniChar(TestConstants.U0000).getUnicodeBlock(), is(UnicodeBlock.BASIC_LATIN));
        assertThat("", new UniChar(TestConstants.UFE00).getUnicodeBlock(), is(UnicodeBlock.VARIATION_SELECTORS));
        assertThat("", new UniChar(TestConstants.UFE0F).getUnicodeBlock(), is(UnicodeBlock.VARIATION_SELECTORS));
        assertThat("", new UniChar(TestConstants.UFFFF).getUnicodeBlock(), is(UnicodeBlock.SPECIALS));
        assertThat("", new UniChar(TestConstants.U10000).getUnicodeBlock(), is(UnicodeBlock.LINEAR_B_SYLLABARY));
        assertThat("", new UniChar(TestConstants.UE0100).getUnicodeBlock(), is(UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT));
        assertThat("", new UniChar(TestConstants.UE01EF).getUnicodeBlock(), is(UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT));
        assertThat("", new UniChar(TestConstants.U10FFFF).getUnicodeBlock(), is(UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B));
    }

    /**
     * Test method for {@link hiro20v.lang.UniChar#getUnicodeScript()}.
     */
    @Test
    public void testGetUnicodeScript() {

        assertThat("", new UniChar(TestConstants.U0000).getUnicodeScript(), is(UnicodeScript.COMMON));
        assertThat("", new UniChar(TestConstants.UFE00).getUnicodeScript(), is(UnicodeScript.INHERITED));
        assertThat("", new UniChar(TestConstants.UFE0F).getUnicodeScript(), is(UnicodeScript.INHERITED));
        assertThat("", new UniChar(TestConstants.UFFFF).getUnicodeScript(), is(UnicodeScript.UNKNOWN));
        assertThat("", new UniChar(TestConstants.U10000).getUnicodeScript(), is(UnicodeScript.LINEAR_B));
        assertThat("", new UniChar(TestConstants.UE0100).getUnicodeScript(), is(UnicodeScript.INHERITED));
        assertThat("", new UniChar(TestConstants.UE01EF).getUnicodeScript(), is(UnicodeScript.INHERITED));
        assertThat("", new UniChar(TestConstants.U10FFFF).getUnicodeScript(), is(UnicodeScript.UNKNOWN));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniChar#compareTo(hiro20v.lang.UniChar)}.
     */
    @Test
    public void testCompareTo() {

        final UniChar uniChar = new UniChar("月");
        assertThat("", uniChar.compareTo(uniChar), is(0));

        final UniChar uniChar0 = new UniChar("🈷");
        assertThat("", uniChar.compareTo(uniChar0), is(-1));

        final UniChar uniChar1 = new UniChar("🌙");
        assertThat("", uniChar.compareTo(uniChar1), is(-1));

        assertThat("", uniChar0.compareTo(uniChar1), is(-1));
    }

    /**
     * Test method for
     * {@link hiro20v.lang.UniChar#compareTo(hiro20v.lang.UniChar)}.
     */
    @Test
    public void testCompareTo_Exception() {

        try {

            new UniChar("月").compareTo(null);
            fail("");
        } catch (final NullPointerException e) {

            assertThat("", e.getMessage(), nullValue());
        }
    }

    /**
     * Test method for {@link hiro20v.lang.UniChar#equals(java.lang.Object)} .
     */
    @Test
    public void testEqualsObject() {

        final UniChar uniChar = new UniChar("月");
        assertThat("", uniChar.equals(uniChar), is(true));

        final UniChar uniChar0 = new UniChar("🈷");
        assertThat("", uniChar.equals(uniChar0), is(false));

        final UniChar uniChar1 = new UniChar("🌙");
        assertThat("", uniChar.equals(uniChar1), is(false));

        assertThat("", uniChar.equals(null), is(false));
        assertThat("", uniChar.equals(""), is(false));

        assertThat("", uniChar0.equals(uniChar1), is(false));
    }

    /**
     * Test method for {@link hiro20v.lang.UniChar#toString()}.
     */
    @Test
    public void testToString() {

        assertThat("", new UniChar(TestConstants.U0030).toString(), is("0"));
    }

    /**
     * Test method for {@link hiro20v.lang.UniChar#getInfomation()}.
     */
    @Test
    public void testGetInfomation() {

        assertThat("", new UniChar(TestConstants.SAMPLE_U9089).getInfomation(), notNullValue());
    }
}
