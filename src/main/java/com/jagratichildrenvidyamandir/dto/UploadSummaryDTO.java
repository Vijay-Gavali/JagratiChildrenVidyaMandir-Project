package com.jagratichildrenvidyamandir.dto;

import java.util.ArrayList;
import java.util.List;

public class UploadSummaryDTO {
    private int totalRows;
    private int inserted;
    private int skipped;
    private List<String> errors = new ArrayList<>();

    public UploadSummaryDTO() {}

    public int getTotalRows() { return totalRows; }
    public void setTotalRows(int totalRows) { this.totalRows = totalRows; }

    public int getInserted() { return inserted; }
    public void setInserted(int inserted) { this.inserted = inserted; }

    public int getSkipped() { return skipped; }
    public void setSkipped(int skipped) { this.skipped = skipped; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }

    public void addError(String msg) { this.errors.add(msg); }
}
