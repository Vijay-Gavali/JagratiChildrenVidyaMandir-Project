package com.jagratichildrenvidyamandir.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enquiry")
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enquiry_id")
    private Integer enquiryId;

    @Column(name = "parent_name", length = 100)
    private String parentName;

    @Column(name = "contact_no", length = 15)
    private String contactNo;

    @Column(name = "enquiry_date")
    private LocalDate enquiryDate;

    @Column(name = "enquiry_message", length = 500)
    private String enquiryMessage;

    // Constructors
    public Enquiry() {}

    public Enquiry(Integer enquiryId, String parentName, String contactNo, LocalDate enquiryDate, String enquiryMessage) {
        this.enquiryId = enquiryId;
        this.parentName = parentName;
        this.contactNo = contactNo;
        this.enquiryDate = enquiryDate;
        this.enquiryMessage = enquiryMessage;
    }

    // Getters and Setters
    public Integer getEnquiryId() {
        return enquiryId;
    }

    public void setEnquiryId(Integer enquiryId) {
        this.enquiryId = enquiryId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public LocalDate getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(LocalDate enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public String getEnquiryMessage() {
        return enquiryMessage;
    }

    public void setEnquiryMessage(String enquiryMessage) {
        this.enquiryMessage = enquiryMessage;
    }
}
