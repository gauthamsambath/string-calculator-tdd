package com.incubyte.assignment;

import static org.junit.Assert.assertEquals;

import com.incubyte.assignment.exceptions.NegativeNumberException;
import org.junit.Before;
import org.junit.Test;

/**
 * The type String calculator test.
 *
 * @author gautham sambath .
 */
public class StringCalculatorTest {

    /**
     * The String calculator.
     */
    private StringCalculator stringCalculator;


    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        stringCalculator = new StringCalculator();
    }

    /**
     * Test add method for empty string.
     *
     * @throws NegativeNumberException .
     */
    @Test
    public void testAddMethodForEmptyString() throws NegativeNumberException {
        assertEquals(0, stringCalculator.add(""));
    }

    /**
     * Test add method for single number.
     *
     * @throws NegativeNumberException .
     */
    @Test
    public void testAddMethodForSingleNumber() throws NegativeNumberException {
        assertEquals(1, stringCalculator.add("1"));
        assertEquals(2, stringCalculator.add("2"));
        assertEquals(3, stringCalculator.add("3"));
        assertEquals(4, stringCalculator.add("4"));
    }

    /**
     * Test add method for multiple numbers.
     *
     * @throws NegativeNumberException .
     */
    @Test
    public void testAddMethodForMultipleNumbers() throws NegativeNumberException {
        assertEquals(10, stringCalculator.add("1,4,5"));
        assertEquals(22, stringCalculator.add("11,5,6"));
        assertEquals(21, stringCalculator.add("1,2,3,4,5,6"));
    }

    /**
     * Test add method with new line in between.
     *
     * @throws NegativeNumberException .
     */
    @Test
    public void testAddMethodWithNewLineInBetween() throws NegativeNumberException {
        assertEquals(6, stringCalculator.add("1\n2,3"));
        assertEquals(6, stringCalculator.add("1\n2\n3"));
        assertEquals(12, stringCalculator.add("1\n2,3\n5\n1"));
        assertEquals(1, stringCalculator.add("1\n"));
    }

    /**
     * Test add method with custom delimiters.
     *
     * @throws NegativeNumberException .
     */
    @Test
    public void testAddMethodWithCustomDelimiters() throws NegativeNumberException {
        assertEquals(3, stringCalculator.add("//;\n1;2"));
        assertEquals(3, stringCalculator.add("//$\n1$2"));
        assertEquals(3, stringCalculator.add("//*\n1*2"));
        assertEquals(3, stringCalculator.add("//$#*\n1$#*2"));
        assertEquals(6, stringCalculator.add("//$#*\n1$#*2$#*3"));
    }

    /**
     * Test add method with negative numbers.
     */
    @Test
    public void testAddMethodWithNegativeNumbers() {
        try {
            stringCalculator.add("//$#*\n-1$#*-2$#*-3");
        } catch (NegativeNumberException e) {
            assertEquals("Negative numbers not allowed [-1, -2, -3]", e.getMessage());
            assertEquals(NegativeNumberException.class, e.getClass());
        }
    }

    /**
     * Test to check ignoring of numbers greater than thousand.
     *
     * @throws NegativeNumberException .
     */
    @Test
    public void testToCheckIgnoringOfNumbersGreaterThanThousand() throws NegativeNumberException {
        assertEquals(4, stringCalculator.add("1\n1001,3"));
        assertEquals(5, stringCalculator.add("//$#*\n1001$#*2$#*3"));
    }

    /**
     * Test to check if add method works for multiple custom delimiters.
     *
     * @throws NegativeNumberException .
     */
    @Test
    public void testToCheckIfAddMethodWorksForMultipleCustomDelimiters() throws NegativeNumberException {
        assertEquals(6, stringCalculator.add("//[*][%]\n1*2%3"));
        assertEquals(10, stringCalculator.add("//[***][%$#][@!&&]\n1***2%$#3@!&&4"));
    }

}

