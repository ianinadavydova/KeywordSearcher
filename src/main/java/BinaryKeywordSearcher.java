package main.java;

import java.io.*;
import java.util.*;

public class BinaryKeywordSearcher {

    public static void searchKeywords(String inputFile) throws IOException {
        Map<String, Integer> countedKeywords = new HashMap<>();

        try (DataInputStream dataInput = new DataInputStream(new FileInputStream(inputFile))) {
            StringBuilder wordStringBuilder = new StringBuilder();
            while (dataInput.available() > 0) {
                char character = (char) dataInput.readByte();
                if (Character.isLetterOrDigit(character)) {
                    wordStringBuilder.append(character);
                } else {
                    recognizeWord(countedKeywords, wordStringBuilder);
                    wordStringBuilder.setLength(0);
                }
            }
            recognizeWord(countedKeywords, wordStringBuilder);
        }

        List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(countedKeywords.entrySet());
        Collections.sort(sortedItems, (a, b) -> b.getValue().compareTo(a.getValue()));

        writeResult(sortedItems, inputFile + ".searchKeywords");
    }

    private static void recognizeWord(Map<String, Integer> countedKeywords, StringBuilder wordStringBuilder) {
        KeywordUtil.recognizeWord(countedKeywords, wordStringBuilder.toString());
    }

    private static void writeResult(List<Map.Entry<String, Integer>> items, String path) throws IOException {
        try (OutputStreamWriter outputWriter = new OutputStreamWriter(new FileOutputStream(path), "UTF8")) {
            for (Map.Entry<String, Integer> countedKeyword : items) {
                outputWriter.write(String.format("%s" + " " + "%s\n", countedKeyword.getKey(), countedKeyword.getValue()));
            }
        }
    }
}

