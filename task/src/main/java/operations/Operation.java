package operations;

import java.io.File;

public interface Operation {

    public abstract boolean tokenizeText(File fileToInspect, String wordToQuery);

}
