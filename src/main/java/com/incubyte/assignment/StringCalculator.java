package com.incubyte.assignment;

import com.incubyte.assignment.exceptions.NegativeNumberException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * The type String calculator.
 */
@SuppressWarnings("checkstyle:Indentation")
public class StringCalculator {

    /**
     * Add int.
     *
     * @param numbers the numbers
     * @return the int
     * @throws NegativeNumberException the negative number exception
     */
    public int add(String numbers) throws NegativeNumberException {
        if (StringUtils.isEmpty(numbers)) {
            return 0;
        } else {
            String delimiter = numbers.split(",|\\n")[0];
            if (StringUtils.isNumeric(delimiter)) {
                delimiter = ",";
            } else {
                int delimiterLength = delimiter.split("").length;
                delimiter = extractDelimiter(delimiter);
                numbers = numbers.substring(delimiterLength);
            }
            String splitter = "[" + delimiter + "]" + "|\\n";
            List<Integer> listOfIntegersExtracted = Arrays.stream(numbers.split(splitter)).filter(a -> !a.equals("")).map(a -> {
                int value = Integer.parseInt(a);
                return value > 1000 ? 0 : value;
            }).collect(Collectors.toList());
            List<Integer> negativeList = findNegatives(listOfIntegersExtracted);
            if (negativeList.size() > 0) {
                throw new NegativeNumberException("Negative numbers not allowed " + negativeList);
            }

            return listOfIntegersExtracted.stream().reduce(Integer::sum).orElseThrow();
        }
    }

    /**
     * Find negatives list.
     *
     * @param integerList the integer list
     * @return the list
     */
    private List<Integer> findNegatives(List<Integer> integerList) {
        return integerList.stream().filter(a -> a < 0).collect(Collectors.toList());
    }

    /**
     * Extract delimiter string.
     *
     * @param delimiter the delimiter
     * @return the string
     */
    private String extractDelimiter(String delimiter) {
        List<String> listOfStringsExtracted = Arrays.stream(delimiter.split("")).filter(a -> !Objects.equals(a, "/")).collect(Collectors.toList());
        delimiter = String.join("", listOfStringsExtracted);
        return delimiter;
    }

}
