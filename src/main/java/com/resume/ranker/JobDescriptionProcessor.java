package com.resume.ranker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JobDescriptionProcessor {

    public static Set<String> extractKeywords(String filePath) {
        Set<String> keywords = new HashSet<>();

        try {
            String content = Files.readString(Paths.get(filePath));
            content = content.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", " "); // remove punctuation

            String[] words = content.split("\\s+");

            for (String word : words) {
                if (!isCommonWord(word)) {
                    keywords.add(word);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading job description: " + e.getMessage());
        }

        return keywords;
    }

    // Simple stop words filter
    private static boolean isCommonWord(String word) {
        List<String> stopWords = Arrays.asList("the", "and", "a", "an", "to", "in", "of", "on", "with", "for", "is", "are", "this", "that");
        return stopWords.contains(word) || word.length() < 2;
    }
}
