package operations;

import java.io.File;

/**
 * It allows to extend the set of operations supported by the program.
 * New operations must implement this interface and the method tokenizeText.
 */
public interface Operation {

    boolean tokenizeText(File fileToInspect, String wordToQuery);

}
