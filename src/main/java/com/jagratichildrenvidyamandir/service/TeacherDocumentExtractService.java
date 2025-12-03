package com.jagratichildrenvidyamandir.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TeacherDocumentExtractService {

    // Inner class to hold extracted data
    public static class ExtractedData {
        private String aadharNo;
        private LocalDate dob;
        private String address;

        public ExtractedData(String aadharNo, LocalDate dob, String address) {
            this.aadharNo = aadharNo;
            this.dob = dob;
            this.address = address;
        }

        public String getAadharNo() { return aadharNo; }
        public LocalDate getDob() { return dob; }
        public String getAddress() { return address; }
    }

    // Method to extract text from the document
    public ExtractedData processDocument(String filePath) throws TesseractException {

        ITesseract tesseract = new Tesseract();

        // Set path to Tesseract OCR tessdata
        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata"); // update if needed
        tesseract.setLanguage("eng");  // use "eng+hin" if Hindi required

        // Perform OCR
        String text = tesseract.doOCR(new File(filePath));
        System.out.println("Extracted Text:\n" + text);

        // Extract Aadhaar number (12 digits, possibly with spaces)
        String aadharNo = "";
        try {
            aadharNo = text.replaceAll(".*(\\d{4}\\s?\\d{4}\\s?\\d{4}).*", "$1");
        } catch (Exception e) {
            aadharNo = "";
        }

        // Extract DOB in format dd/MM/yyyy
        LocalDate dob = null;
        try {
            String dobStr = text.replaceAll(".*(\\d{2}/\\d{2}/\\d{4}).*", "$1");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dob = LocalDate.parse(dobStr, formatter);
        } catch (Exception e) {
            dob = null;
        }

        // Extract address (simple rule: line containing "Address" or nearby)
        String address = "";
        String[] lines = text.split("\n");
        for (String line : lines) {
            if (line.toLowerCase().contains("address")) {
                address = line;
                break;
            }
        }

        return new ExtractedData(aadharNo, dob, address);
    }
}
