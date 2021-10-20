package operations;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SplitByWordOperationTest {

    @Test
    public void tokenizeTextTest(){

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

}