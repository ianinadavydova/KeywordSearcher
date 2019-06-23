package main.java;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TextKeywordSearcher.searchKeywords("test_data\\keywords.java");
        BinaryKeywordSearcher.searchKeywords("test_data\\keywords.java");
    }
}
