package com.craziii.wordcounter;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;


public class WordCounter {

    Map<Integer, Long> wordsLengthMap = new HashMap<>();

    public WordCounter() {
    }

    public WordCounter(URL url) throws IOException {
        Scanner scanner = new Scanner(url.openStream());
        countWords(scanner);
    }

    void countWords(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Integer> lengths = wordLengthsOfLine(cleanseString(line));
            for (Integer length : lengths) {
                if (!wordsLengthMap.containsKey(length)) {
                    wordsLengthMap.put(length, 0L);
                }
                wordsLengthMap.put(length, wordsLengthMap.get(length) + 1);
            }
        }
    }

    public long getWordCount() {
        return getWordCount(wordsLengthMap);
    }

    public List<Integer> getLineLengths(String line) {
        return wordLengthsOfLine(line);
    }

    String cleanseString(String s) {
        // Using https://www.studytonight.com/java-examples/how-to-remove-punctuation-from-string-in-java and https://www.geeksforgeeks.org/string-punctuation-in-python/ as references for what classes as punctuation in programming
        // Space has been removed for the case of word delimiting
        final String PUNCTUATION = "[!\"#$%'\\(\\)*+,.:;<=>?@\\[\\]^_`{|}~]+";
        if (s.matches(PUNCTUATION)) {
            return s.replaceAll(PUNCTUATION, "");
        }
        return s;
    }

    List<Integer> getHighestCount(Map<Integer, Long> wordsLengthMap) {
        long currentMax = -1;
        for (Integer wordLength : wordsLengthMap.keySet()) {
            if (wordsLengthMap.get(wordLength) > currentMax) {
                currentMax = wordsLengthMap.get(wordLength);
            }
        }
        List<Integer> maxValues = new ArrayList<>();
        for (Integer wordLength : wordsLengthMap.keySet()) {
            if (wordsLengthMap.get(wordLength) == currentMax) {
                maxValues.add(wordLength);
            }
        }
        return maxValues;
    }

    private long getWordCount(Map<Integer, Long> wordsLengthMap) {
        long count = 0;
        for (Long numWords : wordsLengthMap.values()) {
            count += numWords;
        }
        return count;
    }

    private double getAverageLength(Map<Integer, Long> wordsLengthMap, long wordCount) {
        long scaledWordLength = 0;
        for (Integer wordLength : wordsLengthMap.keySet()) {
            scaledWordLength += wordLength * wordsLengthMap.get(wordLength);
        }

        return (double) scaledWordLength / wordCount;
    }

    List<Integer> wordLengthsOfLine(String line) {
        List<Integer> lengths = new ArrayList<>();
        String[] words = line.split(" ");
        for (String word : words) {
            if (word.length() > 0) {
                lengths.add(word.length());
            }
        }
        return lengths;
    }

    String getBody() {
        /*
        TEMPLATE:
        Word count = X
        Average word length = X.XXX (3 decimal places)
        Number of words of length X is X
        The most frequently occurring word length is X, for word lengths of X, X & X (Ampersand important)
         */
        StringBuilder stringBuilder = new StringBuilder();
        long wordCount = getWordCount(wordsLengthMap);
        stringBuilder.append("Word count = ").append(wordCount).append("\n");
        DecimalFormat df = new DecimalFormat("#.###");
        stringBuilder.append("Average word length = ").append(df.format(getAverageLength(wordsLengthMap, wordCount))).append("\n");
        for (Integer wordLength : wordsLengthMap.keySet()) {
            stringBuilder.append("Number of words of length ").append(wordLength).append(" is ").append(wordsLengthMap.get(wordLength)).append("\n");
        }
        List<Integer> highestCount = getHighestCount(wordsLengthMap);
        StringBuilder loggerHighestCountMessage = new StringBuilder();
        loggerHighestCountMessage.append("The most frequently occurring word length is ").append(wordsLengthMap.get(highestCount.get(0))).append(", for word lengths of ");
        for (Integer count : highestCount) {
            loggerHighestCountMessage.append(count).append(", ");
        }
        loggerHighestCountMessage.delete(loggerHighestCountMessage.length() - 2, loggerHighestCountMessage.length() - 1);
        if (highestCount.size() > 1) {
            loggerHighestCountMessage.replace(loggerHighestCountMessage.lastIndexOf(","), loggerHighestCountMessage.lastIndexOf(",") + 1, " &");
        }
        stringBuilder.append(loggerHighestCountMessage);
        return stringBuilder.toString();
    }

}