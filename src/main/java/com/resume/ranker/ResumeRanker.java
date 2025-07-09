package com.resume.ranker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ResumeRanker {

    public static void main(String[] args) {
        String resumeFolder = "src/main/resources/resumes/";
        String jobDescPath = resumeFolder + "job_description.txt";

        // 1. Extract job description text and keywords
        String jobDescription = readFile(jobDescPath);
        Set<String> keywords = extractKeywords(jobDescription);

        // 2. Extract resumes
        Map<String, String> resumes = ResumeParser.extractResumes(resumeFolder);

        // 3. Rank resumes
        List<Map.Entry<String, Integer>> ranked = rankResumes(resumes, keywords);

        // 4. Display result
        System.out.println("\n=== Resume Ranking ===");
        int rank = 1;
        for (Map.Entry<String, Integer> entry : ranked) {
            System.out.println(rank++ + ". " + entry.getKey() + " - Score: " + entry.getValue());
        }
    }

    private static String readFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path))).toLowerCase();
        } catch (IOException e) {
            System.err.println("Error reading job description.");
            return "";
        }
    }

    private static Set<String> extractKeywords(String text) {
        // Extract words (excluding common stop words if needed)
        String[] words = text.toLowerCase().split("\\W+");
        return new HashSet<>(Arrays.asList(words));
    }

    private static List<Map.Entry<String, Integer>> rankResumes(Map<String, String> resumes, Set<String> keywords) {
        Map<String, Integer> scores = new HashMap<>();

        for (Map.Entry<String, String> entry : resumes.entrySet()) {
            String resumeText = entry.getValue().toLowerCase();
            int score = 0;

            for (String keyword : keywords) {
                if (resumeText.contains(keyword)) {
                    score++;
                }
            }

            scores.put(entry.getKey(), score);
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(scores.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue()); // Descending
        return sorted;
    }
}
