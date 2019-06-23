package test.java;

import main.java.TextKeywordSearcher;
import java.io.IOException;

public class TextKeywordSearcherTest extends AbstractKeywordSearcherTest {
    @Override
    protected void searchKeywords(String inputTestFile) throws IOException {
        TextKeywordSearcher.searchKeywords(inputTestFile);
    }
}
