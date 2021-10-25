package operations;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PatternSearchOperationTest {

    /**
     * Tests if the toxenizeText method of PatternSearchOperation correctly
     * finds a given pattern in a file.
     */
    @Test
    public void tokenizeTextWordContainedTest(){
        final String fileName = "/test.txt";
        final String patternToSearch = "test";

        PatternSearchOperation patternSearchOperation = new PatternSearchOperation();
        URL resource = SplitByWordOperationTest.class.getResource(fileName);
        try {
            String filePath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            boolean wordFound = patternSearchOperation.tokenizeText(new File(filePath), patternToSearch);
            assertEquals(true, wordFound);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}