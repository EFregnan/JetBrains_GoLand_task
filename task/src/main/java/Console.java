import java.util.Scanner;

public class Console {

    private Scanner consoleScanner;

    public Console() {
        this.consoleScanner = new Scanner(System.in);
    }

    public String[] parseUserListOfFiles() {
        System.out.println("Please insert the list of files or directories to index separated by a ';'");
        String s = consoleScanner.nextLine();
        s = s.replaceAll("\\s+","");
        String[] elementsToIndex = s.split(";");
        return elementsToIndex;
    }

    public String parseWordToQuery() {
        System.out.println("Please insert the word to query");
        String wordToQuery = consoleScanner.nextLine();
        return wordToQuery;
    }

}
