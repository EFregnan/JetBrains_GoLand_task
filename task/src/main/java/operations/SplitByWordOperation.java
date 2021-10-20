package operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SplitByWordOperation implements Operation {

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
