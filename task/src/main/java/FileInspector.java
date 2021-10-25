import operations.Operation;
import operations.SplitByWordOperation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Console console = new Console();
        String[] elementsToIndex = console.parseUserListOfFiles();
        String wordToQuery = console.parseWordToQuery();
        Operation operationOnFile = new SplitByWordOperation();
        try {
            List<String> queryResults = queryElements(elementsToIndex, wordToQuery, operationOnFile);
            console.printQueryResults(queryResults);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Applies the operation passed as parameter (e.g., split by word) to all files specified by the user and
     * searches for the given word.
     * Returns a list of the paths to all files containing the given word.
     * @param elementsToIndex - an array of the files/directories specified by the user
     * @param wordToQuery - the word to search for in the files
     * @param operationOnFile - the operation to perform on the content of each file
     * @return queryResults - a List of all paths of the files containing the given word
     * @throws FileNotFoundException
     */
    public static List<String> queryElements(String[] elementsToIndex, String wordToQuery, Operation operationOnFile) throws FileNotFoundException {
        List<String> queryResults = new ArrayList<>();
        for(String path : elementsToIndex) {
            File file = new File(path);
            if(!file.exists()){
                throw new FileNotFoundException();
            } else {
                if(file.isFile()){
                    String resultPath = queryFile(file, wordToQuery, operationOnFile);
                    if(resultPath!=null){
                        queryResults.add(resultPath);
                    }
                }
                if(file.isDirectory()){
                    queryResults.addAll(inspectFilesInDirectory(file, wordToQuery, operationOnFile));
                }
            }
        }
        return queryResults;
    }

    /**
     * Applies the specified operation to a file and search for the given word.
     * Returns the path to the file, if this contains the given word, or
     * null if the file does not contain the specified word.
     * @param fileToInspect - the file to analyse
     * @param wordToQuery - the word to search for in the file
     * @param operationToExecute - the operation to execute on the text contained in the file
     * @return path of the file containing the specified word or null
     */
    private static String queryFile(File fileToInspect, String wordToQuery, Operation operationToExecute) {
        boolean wordFound = operationToExecute.tokenizeText(fileToInspect, wordToQuery);
        if(wordFound) {
            return fileToInspect.getPath();
        } else {
            return null;
        }
    }

    /**
     * Iterates over the content of a directory.
     * If the element is a file it queries it for a given word applying the specified tokenization operation.
     * If the element is a directory, it recursively iterates over the elements contained in it.
     * @param directoryToInspect - the directory to inspect
     * @param wordToQuery - the word to search in the files as specified by the user
     * @param operationOnFile - the operation to perform on the text of each file
     * @return resultPaths - a list of the paths of all files containing the selected word
     */
    private static List<String> inspectFilesInDirectory(File directoryToInspect, String wordToQuery, Operation operationOnFile){
        List<String> resultPaths = new ArrayList<>();
        for(File file : directoryToInspect.listFiles()){
            if(file.isDirectory()){
                resultPaths.addAll(inspectFilesInDirectory(file, wordToQuery, operationOnFile));
            } else {
                String resultPath = queryFile(file, wordToQuery, operationOnFile);
                if(resultPath != null){
                    resultPaths.add(resultPath);
                }
            }
        }
        return resultPaths;
    }

}
