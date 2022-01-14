package com.craziii.wordcounter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterTests {

    @Test
    void wordLengthsTest() {
        WordCounter wordCounter = new WordCounter();
        String line = "You will rejoice to hear that no disaster has accompanied the commencement of an enterprise which you have regarded with such evil forebodings.";
        List<Integer> lengths = wordCounter.wordLengthsOfLine(line);
        assertEquals(23, lengths.size());
    }

    @Test
    void wordCountTest() throws IOException {
        WordCounter wordCounter = new WordCounter(new URL("https://janelwashere.com/files/bible_daily.txt"));
        assertEquals(793826, wordCounter.getWordCount());
    }

}
