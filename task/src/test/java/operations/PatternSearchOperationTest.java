package operations;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PatternSearchOperationTest {

    /**
     * Tests if the tokenizeText method of PatternSearchOperation correctly
     * finds a given pattern in a file.
     */
    @Test
    public void tokenizeTextWordContainedTest(){
        final String fileName = "/test.txt";
        final String patternToSearch = "test";

        PatternSearchOperation patternSearchOperation = new PatternSearchOperation();
        URL resource = PatternSearchOperationTest.class.getResource(fileName);
        try {
            String filePath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            boolean patternFound = patternSearchOperation.tokenizeText(new File(filePath), patternToSearch);
            assertEquals(true, patternFound);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the tokenizeText method of PatternSearchOperation correctly
     * does not find a pattern not present in the given file.
     */
    @Test
    public void tokenizeTextNoWordTest(){
        final String fileName = "/test.txt";
        final String patternToSearch = "em";

        PatternSearchOperation patternSearchOperation = new PatternSearchOperation();
        URL resource = PatternSearchOperationTest.class.getResource(fileName);
        try {
            String filePath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            boolean patternFound = patternSearchOperation.tokenizeText(new File(filePath), patternToSearch);
            assertEquals(false, patternFound);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}