package com.resume.ranker;

import java.util.*;

public class ResumeScorer {

    public static Map<String, Integer> scoreResumes(Map<String, String> resumes, Set<String> keywords) {
        Map<String, Integer> scores = new HashMap<>();

        for (Map.Entry<String, String> entry : resumes.entrySet()) {
            String fileName = entry.getKey();
            String content = entry.getValue().toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", " ");
            String[] words = content.split("\\s+");

            int matchCount = 0;
            for (String word : words) {
                if (keywords.contains(word)) {
                    matchCount++;
                }
            }

            scores.put(fileName, matchCount);
        }

        return sortByValueDescending(scores);
    }

    private static Map<String, Integer> sortByValueDescending(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
