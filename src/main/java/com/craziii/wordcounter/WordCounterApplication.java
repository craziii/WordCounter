package com.craziii.wordcounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@SpringBootApplication
public class WordCounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordCounterApplication.class, args);
    }

    @RequestMapping("/")
    @ResponseBody
    String helloWorld() {
        return "Word Counter up and running.";
    }

    @RequestMapping(value = "/countWords", method = RequestMethod.POST)
    @ResponseBody
    String countWords(@RequestParam("filePath") String filePath) {
        try {
            URL url = new URL(filePath);
            WordCounter wordCounter = new WordCounter(url);
            return wordCounter.getBody();
        } catch (FileNotFoundException e) {
            return "Failed to find valid file at: " + filePath;
        } catch (MalformedURLException e) {
            return "URL supplied is malformed: " + filePath;
        } catch (IOException ignored) {

        }
        return "no filepath specified";
    }

}
