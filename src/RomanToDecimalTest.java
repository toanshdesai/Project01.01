import static org.junit.Assert.*;

/**
 * JUnit4 Test class for RomanToDecimal
 * @author @toanshdesai
 * @version 09.30.2025
 */
public class RomanToDecimalTest {

    /**
     * JUnit test method for romanToDecimal method
     */
    @org.junit.Test
    public void romanToDecimalTest() {
        assertEquals(RomanToDecimal.romanToDecimal("I"), 1);
        assertEquals(RomanToDecimal.romanToDecimal("IV"), 4);
        assertEquals(RomanToDecimal.romanToDecimal("VI"), 6);
        assertEquals(RomanToDecimal.romanToDecimal("IX"), 9);
        assertEquals(RomanToDecimal.romanToDecimal("XI"), 11);
        assertEquals(RomanToDecimal.romanToDecimal("XL"), 40);
        assertEquals(RomanToDecimal.romanToDecimal("LX"), 60);
        assertEquals(RomanToDecimal.romanToDecimal("XC"), 90);
        assertEquals(RomanToDecimal.romanToDecimal("CX"), 110);
        assertEquals(RomanToDecimal.romanToDecimal("CD"), 400);
        assertEquals(RomanToDecimal.romanToDecimal("DC"), 600);
        assertEquals(RomanToDecimal.romanToDecimal("CM"), 900);
        assertEquals(RomanToDecimal.romanToDecimal("MC"), 1100);
        assertEquals(RomanToDecimal.romanToDecimal("string"), -1);
        assertEquals(RomanToDecimal.romanToDecimal("LXVII"), 67);
        assertEquals(RomanToDecimal.romanToDecimal("XLI"), 41);
        assertEquals(RomanToDecimal.romanToDecimal("LXIX"), 69);
        assertEquals(RomanToDecimal.romanToDecimal("MDCCXXXVIII"), 1738);

        assertNotEquals(RomanToDecimal.romanToDecimal("IV"), 6);
        assertNotEquals(RomanToDecimal.romanToDecimal("XIV"), 16);
    }
}