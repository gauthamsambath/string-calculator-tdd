package com.incubyte.assignment;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * The type String calculator test.
 */
public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        stringCalculator = new StringCalculator();
    }

    /**
     * Test add method for empty string.
     */
    @Test
    public void testAddMethodForEmptyString() {
        assertEquals(0,stringCalculator.add(""));
    }

    /**
     * Test add method for single number.
     */
    @Test
    public void testAddMethodForSingleNumber() {
        assertEquals(1,stringCalculator.add("1"));
        assertEquals(2,stringCalculator.add("2"));
        assertEquals(3,stringCalculator.add("3"));
        assertEquals(4,stringCalculator.add("4"));
    }

    /**
     * Test add method for multiple numbers.
     */
    @Test
    public void testAddMethodForMultipleNumbers() {
        assertEquals(10,stringCalculator.add("1,4,5"));
        assertEquals(10,stringCalculator.add("11,5,-6"));
        assertEquals(21,stringCalculator.add("1,2,3,4,5,6"));
    }

    /**
     * Test add method with new line in between.
     */
    @Test
    public void testAddMethodWithNewLineInBetween() {
        assertEquals(6,stringCalculator.add("1\n2,3"));
        assertEquals(6,stringCalculator.add("1\n2\n3"));
        assertEquals(12,stringCalculator.add("1\n2,3\n5\n1"));
        assertEquals(1,stringCalculator.add("1\n"));
    }

}

