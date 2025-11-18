package com.jagratichildrenvidyamandir.mapper;

import com.jagratichildrenvidyamandir.dto.EnquiryDTO;
import com.jagratichildrenvidyamandir.entity.Enquiry;
import org.springframework.stereotype.Component;

@Component
public class EnquiryMapper {

    public EnquiryDTO toDto(Enquiry entity) {
        if (entity == null) return null;
        EnquiryDTO dto = new EnquiryDTO();
        dto.setEnquiryId(entity.getEnquiryId());
        dto.setParentName(entity.getParentName());
        dto.setContactNo(entity.getContactNo());
        dto.setEnquiryDate(entity.getEnquiryDate());
        dto.setEnquiryMessage(entity.getEnquiryMessage());
        return dto;
    }

    public Enquiry toEntity(EnquiryDTO dto) {
        if (dto == null) return null;
        Enquiry e = new Enquiry();
        e.setEnquiryId(dto.getEnquiryId());
        e.setParentName(dto.getParentName());
        e.setContactNo(dto.getContactNo());
        e.setEnquiryDate(dto.getEnquiryDate());
        e.setEnquiryMessage(dto.getEnquiryMessage());
        return e;
    }

    public void updateEntityFromDto(EnquiryDTO dto, Enquiry entity) {
        if (dto == null || entity == null) return;
        entity.setParentName(dto.getParentName());
        entity.setContactNo(dto.getContactNo());
        entity.setEnquiryDate(dto.getEnquiryDate());
        entity.setEnquiryMessage(dto.getEnquiryMessage());
        // do not overwrite enquiryId here
    }
}
