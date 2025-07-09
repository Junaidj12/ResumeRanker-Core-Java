package com.resume.ranker;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ResumeParser {

    public static Map<String, String> extractResumes(String folderPath) {
        Map<String, String> resumeData = new HashMap<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (files == null || files.length == 0) {
            System.out.println("No PDF files found in: " + folderPath);
            return resumeData;
        }

        for (File file : files) {
            try (PDDocument document = PDDocument.load(file)) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                resumeData.put(file.getName(), text);
            } catch (IOException e) {
                System.err.println("Failed to read: " + file.getName());
            }
        }

        return resumeData;
    }
}
