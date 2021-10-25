package operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implements a "split by word" operation, where the text is divided in words
 * and a specific word (provided by the user) is queried
 */
public class SplitByWordOperation implements Operation {

    /**
     * Splits the given file into words and search for a word
     * @param fileToInspect - the file to inspect
     * @param wordToQuery - the word to be searched for in the file
     * @return wordFound - true, if the file contains wordToQuery, false otherwise.
     */
    public boolean tokenizeText(File fileToInspect, String wordToQuery) {
        boolean wordFound = false;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileToInspect));
            String fileLine;
            while (((fileLine = fileReader.readLine()) != null) && !wordFound) {
                String[] wordsInLine = fileLine.split(" ");
                for (String word : wordsInLine) {
                    if (word.equals(wordToQuery)) {
                        wordFound = true;
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return wordFound;
    }

}
