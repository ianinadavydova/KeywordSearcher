package main.java;

import java.io.*;
import java.util.*;

public class TextKeywordSearcher {
    public static void searchKeywords(String inputFile) throws IOException {
        Map<String, Integer> countedKeywords = new HashMap<>();

        try (BufferedReader inputBufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String inputString;

            while ((inputString = inputBufferedReader.readLine()) != null) {
                StringTokenizer words = new StringTokenizer(inputString, " \t(){}[];.<>");
                while (words.hasMoreTokens()) {
                    KeywordUtil.recognizeWord(countedKeywords, words.nextToken());
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(countedKeywords.entrySet());
        Collections.sort(sortedItems, (a, b) -> b.getValue().compareTo(a.getValue()));

        writeResult(sortedItems, inputFile + ".searchKeywords");
    }

    private static void writeResult(List<Map.Entry<String, Integer>> items, String path) throws IOException {
        try (PrintWriter outputWriter = new PrintWriter(path)) {
            for (Map.Entry<String, Integer> countedKeyword: items) {
                outputWriter.write(String.format("%s" + " " + "%s\n", countedKeyword.getKey(), countedKeyword.getValue()));
            }
        }
    }
}
