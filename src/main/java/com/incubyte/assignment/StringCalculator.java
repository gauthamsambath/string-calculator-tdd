package com.incubyte.assignment;

import com.incubyte.assignment.exceptions.NegativeNumberException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * The type String calculator.
 *
 * @author gautham sambath .
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
            // In case of empty value in string we return 0 .
            return 0;
        } else {
            // At this place delimiter is extracted to further check if there is a custom delimiter
            // present or we should take the default delimiter which is comma .
            String delimiter = numbers.split("\\n")[0];

            // If block determines the presence of custom delimiter and assigns default delimiter in case
            // of absence ,this is done by checking if the dlimiter starts with the patterns
            // '//[' or '//' .
            if (!(delimiter.startsWith("//[") || delimiter.startsWith("//"))) {
                delimiter = ",";
            } else {
                // In case of the presence of custom delimiter we are determining the length of
                // custom delimiter pattern portion and will remove it from the complete string .
                int delimiterLength = delimiter.split("").length;

                // Custom delimiter portion is extracted with extractDelimiter() function .
                delimiter = extractDelimiter(delimiter);

                //Custom Delimiter is removed from the mainString and reassigned .
                numbers = numbers.substring(delimiterLength);
            }

            // In case of the presence of multiple custom delimiters, we use the function
            // proccessDelimiterAndReturnSplitter() to extract each delimiters separately
            // append '//' and conjoin together with '|' in between to form the perfect regex
            // for splitting
            String splitter = proccessDelimiterAndReturnSplitter(delimiter);

            // List of numbers are extracted in case any number is more than 1000 it is taken as zero .
            List<Integer> listOfIntegersExtracted = Arrays.stream(numbers.split(splitter)).filter(a -> !a.equals("")).map(a -> {
                int value = Integer.parseInt(a);
                return value > 1000 ? 0 : value;
            }).collect(Collectors.toList());

            // Finding all the negative numbers to discard them as by requirement negative addition
            // should throw an exception showing all the negatives .
            List<Integer> negativeList = findNegatives(listOfIntegersExtracted);

            // If block to throw Negative Numbers not allowed exception .
            if (negativeList.size() > 0) {
                throw new NegativeNumberException("Negative numbers not allowed " + negativeList);
            }

            // Finally, after all the processing, filtering and splitting we return the sum .
            return listOfIntegersExtracted.stream().reduce(Integer::sum).orElseThrow();
        }
    }

    /**
     * Proccess delimiter and return splitter string.
     *
     * @param delimiters the delimiters
     * @return the string
     */
    private String proccessDelimiterAndReturnSplitter(String delimiters) {
        // extracting the list of all the custom delimiters .
        List<String> listOfDelimiters = Arrays.stream(delimiters.split("[\\[\\]]")).filter(a -> !a.equals("")).collect(Collectors.toList());

        // escape sequence to append beside every individual character of all delimiters as
        // some special characters have other wild card functionalities in regex
        // and therefore by prepending with '\\' we are essentialy removing the ambiguity .
        String escapeSequence = "\\";

        StringBuilder finalDelimiter = new StringBuilder();

        // for loop for the construction of the final regex pattern with all the additions .
        for (String delimiter :
                listOfDelimiters) {
            List<String> delimiterPartsModified = Arrays.stream(delimiter.split("")).map(a -> escapeSequence + a).collect(Collectors.toList());
            String conjoinedDelimiter = String.join("", delimiterPartsModified);
            finalDelimiter.append(conjoinedDelimiter).append("|");
        }

        // returning final delimiter splitter pattern after all the processing .
        return finalDelimiter + "\\n";
    }

    /**
     * Find negatives list.
     *
     * @param integerList the integer list
     * @return the list
     */
    private List<Integer> findNegatives(List<Integer> integerList) {
        // returns a list of all negatives iin our main string
        return integerList.stream().filter(a -> a < 0).collect(Collectors.toList());
    }

    /**
     * Extract delimiter string.
     *
     * @param delimiter the delimiter
     * @return the string
     */
    private String extractDelimiter(String delimiter) {
        // first basic step of extracting mutliple delimiters from the main string .
        List<String> listOfStringsExtracted = Arrays.stream(delimiter.split("")).filter(a -> !Objects.equals(a, "/")).collect(Collectors.toList());
        delimiter = String.join("", listOfStringsExtracted);
        return delimiter;
    }


}
