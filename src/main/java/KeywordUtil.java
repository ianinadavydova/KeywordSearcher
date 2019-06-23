package main.java;

import javax.lang.model.SourceVersion;
import java.util.Map;

class KeywordUtil {
    static void recognizeWord(Map<String, Integer> countedKeywords, String word) {
        if (SourceVersion.isKeyword(word)) {
            Integer oldValue = countedKeywords.getOrDefault(word, 0);
            countedKeywords.put(word, oldValue + 1);
        }
    }
}
