package operations;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Random;

/**
 * Implements an operation to search for a pattern in a given text using
 * Rabin Karp algorithm.
 */
public class PatternSearchOperation implements Operation {

    private static final int numberOfAlphabetCharacters = 256;

    /**
     * Searches for a pattern in the content of a file
     * @param fileToInspect - the file to inspect
     * @param wordToQuery - the pattern to be searched for in the file
     * @return patternFound - true, if the file contains wordToQuery, false otherwise.
     */
    @Override
    public boolean tokenizeText(File fileToInspect, String wordToQuery) {
        boolean patternFound = false;
        try {
            String content = Files.readString(fileToInspect.toPath(), StandardCharsets.US_ASCII);
            patternFound = rabinKarpPatternSearch(wordToQuery.toCharArray(), content.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patternFound;
    }


    /**
     * Implements the Rabin Karp algorithm to identify the given pattern in the content of a file.
     * The implementation of this algorithm is from https://www.baeldung.com/java-full-text-search-algorithms.
     * @param pattern - the pattern to find
     * @param text - the content of a file where to search the pattern
     * @return true if the pattern is found, false otherwise
     */
    private boolean rabinKarpPatternSearch(char[] pattern, char[] text) {
        int patternSize = pattern.length;
        int textSize = text.length;
        long prime = BigInteger.probablePrime(getNumberOfBits(patternSize) + 1, new Random()).longValue();
        long r = 1;
        for (int i = 0; i < patternSize - 1; i++) {
            r *= 2;
            r = r % prime;
        }

        long[] t = new long[textSize];
        t[0] = 0;
        long pfinger = 0;
        for (int j = 0; j < patternSize; j++) {
            t[0] = (2 * t[0] + text[j]) % prime;
            pfinger = (2 * pfinger + pattern[j]) % prime;
        }
        int i = 0;
        boolean passed = false;
        int diff = textSize - patternSize;
        for (i = 0; i <= diff; i++) {
            if (t[i] == pfinger) {
                passed = true;
                for (int k = 0; k < patternSize; k++) {
                    if (text[i + k] != pattern[k]) {
                        passed = false;
                        break;
                    }
                }
                if (passed) {
                    return true;
                }
            }
            if (i < diff) {
                long value = 2 * (t[i] - r * text[i]) + text[i + patternSize];
                t[i + 1] = ((value % prime) + prime) % prime;
            }
        }
        return false;
    }

    private int getNumberOfBits(int number) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(number);
    }

}
