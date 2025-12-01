package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.UploadSummaryDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.ZoneId;
import java.util.*;

@Service
public class UserExcelService {

    private final Logger log = LoggerFactory.getLogger(UserExcelService.class);
    private final UserService userService;

    public UserExcelService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Import users from a .xlsx file. Skip duplicates (by the rules in userService.createUser).
     * Returns summary of import results.
     */
    public UploadSummaryDTO importFromExcel(MultipartFile file) {
        UploadSummaryDTO summary = new UploadSummaryDTO();
        if (file == null || file.isEmpty()) {
            summary.addError("No file uploaded or file is empty");
            return summary;
        }

        try (InputStream in = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(in)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                summary.addError("Excel has no sheets");
                return summary;
            }

            Iterator<Row> it = sheet.rowIterator();
            if (!it.hasNext()) {
                summary.addError("Excel sheet is empty");
                return summary;
            }

            Row headerRow = it.next();
            Map<String, Integer> headerMap = buildHeaderMap(headerRow);
            log.info("Excel headers found: {}", headerMap.keySet());

            int excelRowNum = 1; // header is row 1
            while (it.hasNext()) {
                Row row = it.next();
                excelRowNum++;

                // skip completely empty rows
                if (isRowEmpty(row)) continue;

                summary.setTotalRows(summary.getTotalRows() + 1);

                try {
                    UserDTO dto = rowToDto(row, headerMap, excelRowNum);

                    // minimal validation: name and (studentPhone or admissionNo)
                    if (isBlank(dto.getName()) ||
                        (isBlank(dto.getStudentPhone()) && isBlank(dto.getAdmissionNo()))) {
                        summary.setSkipped(summary.getSkipped() + 1);
                        summary.addError("Row " + excelRowNum + ": missing required (name and studentPhone/admissionNo)");
                        continue;
                    }

                    UserDTO created = userService.createUser(dto);
                    if (created == null) {
                        summary.setSkipped(summary.getSkipped() + 1);
                        summary.addError("Row " + excelRowNum + ": duplicate/exists (admissionNo/email/phone/aadhar)");
                        continue;
                    }

                    summary.setInserted(summary.getInserted() + 1);

                } catch (Exception ex) {
                    log.warn("Row {} parsing error: {}", excelRowNum, ex.getMessage(), ex);
                    summary.setSkipped(summary.getSkipped() + 1);
                    summary.addError("Row " + excelRowNum + ": " + ex.getMessage());
                }
            }

        } catch (Exception ex) {
            log.error("Failed to import Excel: {}", ex.getMessage(), ex);
            summary.addError("Failed to parse Excel: " + ex.getMessage());
        }

        return summary;
    }

    // build header map: lower-case trimmed header -> column index
    private Map<String, Integer> buildHeaderMap(Row header) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Cell c : header) {
            String raw = safeCellString(c);
            if (raw == null) continue;
            String key = raw.trim().toLowerCase();
            map.put(key, c.getColumnIndex());
        }
        return map;
    }

    private String safeCellString(Cell c) {
        if (c == null) return null;
        try {
            if (c.getCellType() == CellType.STRING) {
                return c.getStringCellValue();
            } else if (c.getCellType() == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(c)) {
                    return c.getLocalDateTimeCellValue().toLocalDate().toString();
                } else {
                    double d = c.getNumericCellValue();
                    long lv = (long) d;
                    if (Math.abs(d - lv) < 0.000001) return String.valueOf(lv);
                    return String.valueOf(d);
                }
            } else if (c.getCellType() == CellType.BOOLEAN) {
                return String.valueOf(c.getBooleanCellValue());
            } else if (c.getCellType() == CellType.FORMULA) {
                // evaluate formula result
                FormulaEvaluator evaluator = c.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                CellValue cv = evaluator.evaluate(c);
                if (cv == null) return null;
                switch (cv.getCellType()) {
                    case STRING: return cv.getStringValue();
                    case NUMERIC:
                        double d = cv.getNumberValue();
                        long lv = (long) d;
                        if (Math.abs(d - lv) < 0.000001) return String.valueOf(lv);
                        return String.valueOf(d);
                    case BOOLEAN: return String.valueOf(cv.getBooleanValue());
                    default: return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            log.warn("Unable to read cell at row {}, col {}: {}", c.getRowIndex()+1, c.getColumnIndex()+1, e.getMessage());
            return null;
        }
    }

    private boolean isRowEmpty(Row row) {
        if (row == null) return true;
        for (Cell c : row) {
            String s = safeCellString(c);
            if (s != null && !s.isBlank()) return false;
        }
        return true;
    }

    private UserDTO rowToDto(Row row, Map<String, Integer> headerMap, int rowNum) {
        UserDTO dto = new UserDTO();

        java.util.function.Function<String, String> read = (headerName) -> {
            Integer idx = headerMap.get(headerName.toLowerCase());
            if (idx == null) return null;
            Cell c = row.getCell(idx);
            return safeCellString(c);
        };

        dto.setName(read.apply("name"));
        dto.setAdmissionNo(read.apply("admissionno"));
        dto.setAdmissionDate(read.apply("admissiondate"));
        dto.setPassword(read.apply("password"));
        dto.setFatherName(read.apply("fathername"));
        dto.setMotherName(read.apply("mothername"));
        dto.setDob(read.apply("dob"));
        dto.setStudentPhone(read.apply("studentphone"));
        dto.setEmail(read.apply("email"));
        dto.setParentPhone(read.apply("parentphone"));
        dto.setAddress(read.apply("address"));
        dto.setGender(read.apply("gender"));
        dto.setStudentAadharNo(read.apply("studentaadharno"));
        dto.setParentAadharNo(read.apply("parentaadharno"));
        dto.setRte(read.apply("rte"));
        dto.setTcNumber(read.apply("tcnumber"));
        dto.setSsmId(read.apply("ssmid"));
        dto.setPassoutClass(read.apply("passoutclass"));

        String classId = read.apply("studentclassid");
        if (!isBlank(classId)) {
            try {
                dto.setStudentClassId(Integer.valueOf(classId));
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Invalid studentClassId value: " + classId + " at row " + rowNum);
            }
        }
        return dto;
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
