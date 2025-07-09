package com.resume.ranker;

import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        // 1. Extract resume text from all PDFs in folder
    	Map<String, String> resumes = ResumeParser.extractResumes("src/main/resources/resumes");


        // 2. Extract keywords from job description file
    	Set<String> keywords = JobDescriptionProcessor.extractKeywords("src/main/resources/resumes/job_description.txt");

        // 3. Score and rank resumes
        Map<String, Integer> scoredResumes = ResumeScorer.scoreResumes(resumes, keywords);

        // 4. Print results
        System.out.println("📊 Resume Ranking Results:");
        int rank = 1;
        for (Map.Entry<String, Integer> entry : scoredResumes.entrySet()) {
            System.out.printf("%d. %s ➜ %d keyword matches\n", rank++, entry.getKey(), entry.getValue());
        }
    }
}
