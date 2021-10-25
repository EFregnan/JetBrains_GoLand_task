import operations.Operation;
import operations.SplitByWordOperation;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    /**
     * Tests if the query correctly returns the file test.txt
     * which contains the word "test"
     */
    @Test
    public void testFileWithSingleWordQuery(){
        final String fileName = "test.txt";
        final String wordToQuery = "test";
        Operation splitByWordOperation = new SplitByWordOperation();
        URL resource = MainTest.class.getResource(fileName);
        try {
            String filePath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            List<String> queryResults = Main.queryElements(new String[]{filePath}, wordToQuery, splitByWordOperation);
            assertEquals(1, queryResults.size());
            assertEquals(filePath, queryResults.get(0));
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the query correctly returns no result
     * as the file "test.txt" does not contain the word "notFound"
     */
    @Test
    public void testFileWordNotFoundQuery(){
        final String fileName = "test.txt";
        final String wordToQuery = "notFound";
        Operation splitByWordOperation = new SplitByWordOperation();
        URL resource = MainTest.class.getResource(fileName);
        try {
            String filePath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            List<String> queryResults = Main.queryElements(new String[]{filePath}, wordToQuery, splitByWordOperation);
            assertEquals(0, queryResults.size());
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the query correctly returns the files containing a given word
     * when they are contained in a given directory.
     */
    @Test
    public void testDirectoryWithSingleWordQuery(){
        final String directoryName = "test";
        final String fileName1 = "test/test1.txt";
        final String fileName2 = "test/test2.txt";
        final String wordToQuery = "test";
        Operation splitByWordOperation = new SplitByWordOperation();

        URL resource = MainTest.class.getResource(directoryName);
        try {
            String filePath1 = Paths.get(MainTest.class.getResource(fileName1).toURI()).toAbsolutePath().toString();
            String filePath2 = Paths.get(MainTest.class.getResource(fileName2).toURI()).toAbsolutePath().toString();
            String directoryPath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            List<String> queryResults = Main.queryElements(new String[]{directoryPath}, wordToQuery, splitByWordOperation);
            assertEquals(2, queryResults.size());
            assertTrue(queryResults.contains(filePath1));
            assertTrue(queryResults.contains(filePath2));
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the query correctly returns the files containing a given word
     * when both a file and a directory are specified by the user.
     */
    @Test
    public void testDirectoryAndFileWithSingleWordQuery(){
        final String fileName = "test.txt";
        final String directoryName = "test";
        final String fileName1 = "test/test1.txt";
        final String fileName2 = "test/test2.txt";
        final String wordToQuery = "test";
        Operation splitByWordOperation = new SplitByWordOperation();

        URL fileResource = MainTest.class.getResource(fileName);
        URL directoryResource = MainTest.class.getResource(directoryName);
        try {
            String filePath1 = Paths.get(MainTest.class.getResource(fileName1).toURI()).toAbsolutePath().toString();
            String filePath2 = Paths.get(MainTest.class.getResource(fileName2).toURI()).toAbsolutePath().toString();

            String filePath = Paths.get(fileResource.toURI()).toAbsolutePath().toString();
            String directoryPath = Paths.get(directoryResource.toURI()).toAbsolutePath().toString();
            List<String> queryResults = Main.queryElements(new String[]{directoryPath, filePath}, wordToQuery, splitByWordOperation);
            assertEquals(3, queryResults.size());
            assertTrue(queryResults.contains(filePath));
            assertTrue(queryResults.contains(filePath1));
            assertTrue(queryResults.contains(filePath2));
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the query correctly returns the file containing a given word
     * when the specified directory contains another directory.
     */
    @Test
    public void testRecursiveDirectoryWithSingleWordQuery(){
        final String directoryName = "test1";
        final String fileName = "test1/test2/test4.txt";
        final String wordToQuery = "test";
        Operation splitByWordOperation = new SplitByWordOperation();

        URL resource = MainTest.class.getResource(directoryName);
        try {
            String filePath = Paths.get(MainTest.class.getResource(fileName).toURI()).toAbsolutePath().toString();
            String directoryPath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            List<String> queryResults = Main.queryElements(new String[]{directoryPath}, wordToQuery, splitByWordOperation);
            assertEquals(1, queryResults.size());
            assertTrue(queryResults.contains(filePath));
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests if the method correctly throws an exception when a file that non exists is specified
     */
    @Test
    public void testFileNotExistsQuery(){
        final String fileName = "noFile.txt";
        final String wordToQuery = "test";
        Operation splitByWordOperation = new SplitByWordOperation();
        assertThrows(FileNotFoundException.class, () -> {
            Main.queryElements(new String[]{fileName}, wordToQuery, splitByWordOperation);
        });
    }

}