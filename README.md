# JetBrains_GoLand_task

This documents provides some usage examples of the indexing tool.

### Example 1 - Search for a word in a list of files or directories.

When launched the program will perform the following steps:

1) Ask the user to insert the path of one or more files (or directories) they wish to include in the search.
2) Ask the user to specify the operation they want to perform: (1) search a word or (2) search a pattern. A check is performed on the user input to ensure its validity.
3) If the user selects _search a word_, the program asks the user to insert the word to search for in the specified files/directories.
4) The program iterates over all the files specified by the user. If the user specified a directory the program will recursively inspect all files/directories included in it.
5) The program reads line-by-line the content of each file and, if the file contains the given word, it adds the path to the file to the list of query results.
6) The program prints the path to all files containing the word specified by the user.

### Example 2 - Search for a pattern in a list of files or directories 

By changing the Operation class from SplitByWordOperation to PatternSearchOperation, the program can look for a pattern in the files specified by the user.
To do this, the tool relies on an implementation of the Rabin Karp algorithm.

The steps performed by the tool in this example are the following:

1) Ask the user to insert the path of one or more files (or directories) they wish to include in the search.
2) Ask the user to specify the operation they want to perform: (1) search a word or (2) search a pattern. A check is performed on the user input to ensure its validity.
3) If the user selects _search a pattern_, the program asks the user to insert the pattern to search for in the specified files/directories.
4) The program iterates over all the files specified by the user. If the user specified a directory the program will recursively inspect all files/directories included in it.
5) The program inspect the text contained in each file using the Rabin Karp algorithm. If the file contains the given patter, it adds its path to the list of query results.
6) The program prints the path to all files containing the pattern specified by the user.
