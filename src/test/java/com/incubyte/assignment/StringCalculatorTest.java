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

}

