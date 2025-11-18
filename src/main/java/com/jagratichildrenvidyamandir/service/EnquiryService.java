package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.EnquiryDTO;
import com.jagratichildrenvidyamandir.mapper.EnquiryMapper;
import com.jagratichildrenvidyamandir.entity.Enquiry;
import com.jagratichildrenvidyamandir.repository.EnquiryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnquiryService {

    private final EnquiryRepository repository;
    private final EnquiryMapper mapper;

    public EnquiryService(EnquiryRepository repository, EnquiryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EnquiryDTO createEnquiry(EnquiryDTO dto) {
        Enquiry entity = mapper.toEntity(dto);
        entity.setEnquiryId(null); // ensure auto-increment
        Enquiry saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public EnquiryDTO getEnquiryById(Integer id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    public List<EnquiryDTO> getAllEnquiries() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public EnquiryDTO updateEnquiry(Integer id, EnquiryDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    mapper.updateEntityFromDto(dto, existing);
                    Enquiry updated = repository.save(existing);
                    return mapper.toDto(updated);
                })
                .orElse(null);
    }

    public boolean deleteEnquiry(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
