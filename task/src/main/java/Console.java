import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;

public class Console {

    private Scanner consoleScanner;

    private final String insertDirectoryMessage = "Please insert the list of files or directories to index separated by a ';'";
    private final String queryMessage = "Please insert the word to query";

    public Console() {
        this.consoleScanner = new Scanner(System.in);
    }

    /**
     * Asks the user to insert the list of files/directories they want to inspect.
     * Directories/files must be separated by a ";"
     * @return elementsToIndex - An array of the files/directories selected by the user.
     */
    public String[] parseUserListOfFiles() {
        System.out.println(insertDirectoryMessage);
        String s = consoleScanner.nextLine();
        s = s.replaceAll("\\s+","");
        String[] elementsToIndex = s.split(";");
        return elementsToIndex;
    }

    /**
     * Ask the user to insert a word to search in the selected files/directories
     * and return the user input
     * @return wordToQuery - the word the user want to search for
     */
    public String parseWordToQuery() {
        System.out.println(queryMessage);
        String wordToQuery = consoleScanner.nextLine();
        return wordToQuery;
    }

    /**
     * Prints to the console each result of the query
     * @param queryResults - results of the query
     */
    public void printQueryResults(@NotNull List<String> queryResults) {
        for(String result: queryResults){
            System.out.println("Query result: "+ result);
        }
    }

}
