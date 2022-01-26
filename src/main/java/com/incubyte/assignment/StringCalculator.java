package com.incubyte.assignment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * The type String calculator.
 */
public class StringCalculator {

    /**
     * Add int.
     *
     * @param numbers the numbers
     * @return the int
     */
    public int add(String numbers) {
        if (StringUtils.isEmpty(numbers)) {
            return 0;
        } else {
            List<Integer> listOfIntegersExtracted = Arrays.stream(numbers.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            return listOfIntegersExtracted.stream().reduce(Integer::sum).orElseThrow();
        }
    }
}
