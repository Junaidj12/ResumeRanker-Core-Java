 📊 Resume Ranker - Core Java Project

A Core Java project that ranks resumes by matching keywords from a job description. It extracts text from PDF files, compares them with the job requirements, and sorts resumes based on relevance scores.

## 🚀 Features

- 📄 Parses multiple resumes (PDF format)
- 📝 Accepts a job description as plain text
- 🔍 Uses keyword matching and scoring logic
- 📊 Outputs sorted resume list based on match percentage
- ✅ Easily extendable to NLP or ML-based ranking

## 🛠️ Technologies Used

- Java (Core)
- Apache PDFBox (for PDF parsing)
- Collections (Map, List, Set)
- String & Regex utilities

## 📌 How to Use

1. 🧾 Add resumes in `src/main/resources/Resumes/`  
2. 🧾 Add job description in `job_description.txt`  
3. ▶️ Run `Main.java`  
4. ✅ Console will print ranked resume list

## 📄 Sample Output

Resume: Junaid_Resume.pdf → Score: 82%
Resume: Alex_Profile.pdf → Score: 64%
Resume: Sam_CV.pdf → Score: 50%
