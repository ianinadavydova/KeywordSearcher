package main.java;

import javax.lang.model.SourceVersion;
import java.io.*;
import java.util.*;

public class BinaryKeywordSearcher {

    private static void recognizeWord(HashMap<String, Integer> countedKeywords, StringBuilder wordStringBuilder) {
        String word = wordStringBuilder.toString();
        if (SourceVersion.isKeyword(word)) {
            Integer oldValue = countedKeywords.getOrDefault(word, 0);
            countedKeywords.put(word, oldValue + 1);
        }
    }

    public static void searchKeywords(String inputFile) throws IOException {
        FileInputStream fileInput = new FileInputStream(inputFile);
        DataInputStream dataInput = new DataInputStream(fileInput);

        HashMap<String, Integer> countedKeywords = new HashMap<String, Integer>();

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
        dataInput.close();

        List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(countedKeywords.entrySet());
        Collections.sort(sortedItems, (a, b) -> b.getValue().compareTo(a.getValue()));

        FileOutputStream outputFileStream = new FileOutputStream(inputFile + ".searchKeywords");
        OutputStreamWriter outputWriter = new OutputStreamWriter(outputFileStream, "UTF8");

        for (Map.Entry<String, Integer> countedKeyword : sortedItems) {
            outputWriter.write(String.format("%s" + " " + "%s\n", countedKeyword.getKey(), countedKeyword.getValue()));
        }
        outputWriter.close();
    }

}

