import operations.Operation;
import operations.PatternSearchOperation;
import operations.SplitByWordOperation;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Handles the input and output from/to the terminal.
 */
public class Console {

    private Scanner consoleScanner;

    private final String insertDirectoryMessage = "Please insert the list of files or directories to index separated by a ';'";
    private final String wordMessage = "Please insert the word to query";
    private final String patternMessage = "Please insert the pattern to query";
    private final String operationMessage = "Please select the operation to perform: \nInsert '1' to search a word or '2' to search a pattern";

    private static final int searchWordCode = 1;
    private static final int searchPatternCode = 2;
    private final List<Integer> operationCodesList = Arrays.asList(searchWordCode, searchPatternCode);

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
     * Asks the user to insert a word to search in the selected files/directories
     * and return the user input.
     * @return WordToQuery - the word the user want to search for
     */
    public String parseWordToQuery() {
        System.out.println(wordMessage);
        String wordToQuery = consoleScanner.nextLine();
        return wordToQuery;
    }

    /**
     * Asks the user to insert a pattern to search in the selected files/directories
     * and return the user input.
     * @return patternToQuery - the pattern the user want to search for
     */
    public String parsePatternToQuery() {
        System.out.println(patternMessage);
        String patternToQuery = consoleScanner.nextLine();
        return patternToQuery;
    }

    /**
     * Asks the user to insert the operation to perform on the listed files/directories
     * and return the user input.
     * If the user selects an invalid code, it keeps asking the user for a code until a valid one is selected.
     * @return operation - the operation to perform
     */
    public Operation parseOperation() {
        boolean isOperationValid = false;
        int operationCode = 0;
        while(!isOperationValid) {
            System.out.println(operationMessage);
            try {
                operationCode = consoleScanner.nextInt();
                if (operationCodesList.contains(operationCode)) {
                    isOperationValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("The input format was not valid");
                consoleScanner.nextLine();
            }
        }
        consoleScanner.nextLine(); //consumes the '\n' character to avoid issues with further reading on the console
        switch (operationCode) {
            case searchWordCode: return new SplitByWordOperation();
            case searchPatternCode: return new PatternSearchOperation();
        }
        return null;
    }

    /**
     * Prints to the console each result of the query.
     * @param queryResults - results of the query
     */
    public void printQueryResults(@NotNull List<String> queryResults) {
        for(String result: queryResults){
            System.out.println("Query result: "+ result);
        }
    }

}
