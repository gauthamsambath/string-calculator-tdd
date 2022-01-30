package com.incubyte.assignment;

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
     */
    public int add(String numbers) {
        if (StringUtils.isEmpty(numbers)) {
            return 0;
        } else {
            String delimiter = numbers.split(",|\\n")[0];
            if (StringUtils.isNumeric(delimiter)){
                delimiter = ",";
            }
            else {
                int delimiterLength = delimiter.split("").length;
                delimiter = extractDelimiter(delimiter);
                numbers = numbers.substring(delimiterLength);
            }
            String splitter = "["+delimiter+"]"+"|\\n";
            List<Integer> listOfIntegersExtracted = Arrays.stream(numbers.split(splitter)).filter(a->!a.equals("")).map(Integer::parseInt).collect(Collectors.toList());
            return listOfIntegersExtracted.stream().reduce(Integer::sum).orElseThrow();
        }
    }

    private String extractDelimiter(String delimiter) {
        List<String> listOfStringsExtracted = Arrays.stream(delimiter.split("")).filter(a-> !Objects.equals(a, "/")).collect(Collectors.toList());
        delimiter = String.join("",listOfStringsExtracted);
        return delimiter;
    }

}
