import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Please insert the list of files or directories to index separated by a ';'");
        Scanner consoleScanner = new Scanner(System.in);
        String s = consoleScanner.nextLine();
        s = s.replaceAll("\\s+","");
        String[] elementsToIndex = s.split(";");
        for(String s1 : elementsToIndex){
            System.out.println(s1);
        }
        System.out.println("Please insert the word to query");
        String wordToQuery = consoleScanner.nextLine();
        List<String> queryResults = queryElements(elementsToIndex, wordToQuery);
        for(String result: queryResults){
            System.out.println("Query result: "+ queryResults);
        }
    }

    public static List<String> queryElements(String[] elementsToIndex, String wordToQuery) throws FileNotFoundException {
        List<String> queryResults = new ArrayList<>();
        for(String path : elementsToIndex) {
            File file = new File(path);
            if(!file.exists()){
                throw new FileNotFoundException();
            } else {
                if(file.isFile()){
                    boolean wordFound = false;
                    try {
                        String resultPath = queryFile(file, wordToQuery);
                        if(resultPath!=null){
                            queryResults.add(resultPath);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(file.isDirectory()){
                    queryResults.addAll(inspectFilesInDirectory(file, wordToQuery));
                }
            }
        }
        return queryResults;
    }

    private static String queryFile(File fileToInspect, String wordToQuery) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileToInspect));
        String fileLine;
        boolean wordFound = false;
        while(((fileLine = fileReader.readLine()) != null) && !wordFound) {
            String[] wordsInLine = fileLine.split(" ");
            for(String word : wordsInLine) {
                if(word.equals(wordToQuery)) {
                    wordFound = true;
                }
            }
        }
        if(wordFound) {
            return fileToInspect.getPath();
        } else {
            return null;
        }
    }

    private static List<String> inspectFilesInDirectory(File directoryToInspect, String wordToQuery){
        List<String> resultPaths = new ArrayList<>();
        for(File file : directoryToInspect.listFiles()){
            if(file.isDirectory()){
                resultPaths.addAll(inspectFilesInDirectory(file, wordToQuery));
            } else {
                try {
                    String resultPath = queryFile(file, wordToQuery);
                    if(resultPath != null){
                        resultPaths.add(resultPath);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultPaths;
    }

}
