package test.java;

import main.java.BinaryKeywordSearcher;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class BinaryKeywordSearcherTest {

    private static boolean areFilesEqual(BufferedReader result, BufferedReader correct) throws IOException {
        while (true) {
            String resultString = result.readLine();
            String correctString = correct.readLine();
            if (resultString == null && correctString == null)
                return true;
            if (resultString != null && correctString != null) {
                if (!resultString.equals(correctString))
                    return false;
            }
            else
                return false;
        }
    }

    private static void testImplementation(String inputTestFile) throws IOException {
        String resultTestFile = inputTestFile + ".searchKeywords";
        String correctFile = resultTestFile + ".correct";

        BinaryKeywordSearcher.searchKeywords(inputTestFile);

        FileReader inputFileReader = new FileReader(resultTestFile);
        FileReader inputFileReaderCorrect = new FileReader(correctFile);

        BufferedReader inputBufferedReader = new BufferedReader(inputFileReader);
        BufferedReader inputBufferedReaderCorrect = new BufferedReader(inputFileReaderCorrect);
        assertTrue(areFilesEqual(inputBufferedReader, inputBufferedReaderCorrect));
        inputBufferedReader.close();
        inputBufferedReaderCorrect.close();
    }

    @Test
    public void allKeywords() throws IOException {
        String inputTestFile = "test_data\\keywords.java";
        testImplementation(inputTestFile);
    }

    @Test
    public void notepadKeywords() throws IOException {
        String inputTestFile = "test_data\\Notepad.java";
        testImplementation(inputTestFile);
    }

    @Test
    public void emptyFile() throws IOException {
        String inputTestFile = "test_data\\Empty.java";
        testImplementation(inputTestFile);
    }

    @Test
    public void intBrace() throws IOException {
        String inputTestFile = "test_data\\IntBrace.java";
        testImplementation(inputTestFile);
    }

    @Test
    public void Dot() throws IOException {
        String inputTestFile = "test_data\\Dot.java";
        testImplementation(inputTestFile);
    }
}
