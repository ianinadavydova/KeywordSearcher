package main.java;

import main.java.Keywords;

import javax.lang.model.SourceVersion;
import java.io.*;
import java.util.*;

public class TextKeywordSearcher {
    public static void searchKeywords(String inputFile) throws IOException {
        FileReader inputFileReader = new FileReader(inputFile);
        BufferedReader inputBufferedReader = new BufferedReader(inputFileReader);

        String inputString;

        HashMap<String, Integer> countedKeywords = new HashMap<String, Integer>();

        while ((inputString = inputBufferedReader.readLine()) != null) {
            StringTokenizer words = new StringTokenizer(inputString, " \t(){}[];.<>");
            while (words.hasMoreTokens()) {
                String word = words.nextToken();
                if (SourceVersion.isKeyword(word)) {
                    Integer oldValue = countedKeywords.getOrDefault(word, 0);
                    countedKeywords.put(word, oldValue + 1);
                }
            }
        }

        inputBufferedReader.close();

        List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(countedKeywords.entrySet());
        Collections.sort(sortedItems, (a, b) -> b.getValue().compareTo(a.getValue()));

        PrintWriter outputWriter = new PrintWriter(inputFile + ".searchKeywords");
        for (Map.Entry<String, Integer> countedKeyword: sortedItems) {
            outputWriter.write(String.format("%s" + " " + "%s\n", countedKeyword.getKey(), countedKeyword.getValue()));
        }
        outputWriter.close();
    }
}
