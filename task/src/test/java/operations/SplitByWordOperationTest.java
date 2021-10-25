package operations;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SplitByWordOperationTest {

    /**
     * Tests if the tokenizeText method of SplitByWordOperation correctly
     * finds a given word in a file.
     */
    @Test
    public void tokenizeTextWordContainedTest(){

        final String fileName = "/test.txt";
        final String wordToSearch = "test";

        SplitByWordOperation splitByWordOperation = new SplitByWordOperation();
        URL resource = SplitByWordOperationTest.class.getResource(fileName);
        try {
            String filePath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            boolean wordFound = splitByWordOperation.tokenizeText(new File(filePath), wordToSearch);
            assertEquals(true, wordFound);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the tokenizeText method of SplitByWordOperation correctly
     * does not find a word not present in the given file.
     */
    @Test
    public void tokenizeTextNoWordTest(){

        final String fileName = "/test.txt";
        final String wordToSearch = "empty";

        SplitByWordOperation splitByWordOperation = new SplitByWordOperation();
        URL resource = SplitByWordOperationTest.class.getResource(fileName);
        try {
            String filePath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            boolean wordFound = splitByWordOperation.tokenizeText(new File(filePath), wordToSearch);
            assertEquals(false, wordFound);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}