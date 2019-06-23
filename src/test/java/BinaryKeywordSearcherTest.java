package test.java;

import main.java.BinaryKeywordSearcher;
import java.io.IOException;

public class BinaryKeywordSearcherTest extends AbstractKeywordSearcherTest {
    @Override
    protected void searchKeywords(String inputTestFile) throws IOException {
        BinaryKeywordSearcher.searchKeywords(inputTestFile);
    }
}
