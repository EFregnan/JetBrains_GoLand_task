import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testFileWithSingleWordQuery(){
        String fileName = "test.txt";
        URL resource = MainTest.class.getResource(fileName);
        try {
            String filePath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            List<String> queryResults = Main.queryElements(new String[]{filePath}, "test");
            assertEquals(1, queryResults.size());
            assertEquals(filePath, queryResults.get(0));
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDirectoryWithSingleWordQuery(){
        String directoryName = "test";
        URL resource = MainTest.class.getResource(directoryName);
        try {
            String filePath1 = Paths.get(MainTest.class.getResource("test/test1.txt").toURI()).toAbsolutePath().toString();
            String filePath2 = Paths.get(MainTest.class.getResource("test/test2.txt").toURI()).toAbsolutePath().toString();
            String directoryPath = Paths.get(resource.toURI()).toAbsolutePath().toString();
            List<String> queryResults = Main.queryElements(new String[]{directoryPath}, "test");
            assertEquals(2, queryResults.size());
            assertTrue(queryResults.contains(filePath1));
            assertTrue(queryResults.contains(filePath2));
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}