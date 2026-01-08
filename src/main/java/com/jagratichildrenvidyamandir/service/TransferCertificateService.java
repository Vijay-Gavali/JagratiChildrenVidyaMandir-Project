package com.jagratichildrenvidyamandir.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jagratichildrenvidyamandir.entity.TransferCertificate;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.repository.TransferCertificateRepository;
import com.jagratichildrenvidyamandir.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferCertificateService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransferCertificateRepository tcRepository;

	@Transactional
	public void generateTC(Integer userId) {

		User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Student not found"));

		TransferCertificate tc = new TransferCertificate();

		// 🔁 COPY DATA
		tc.setUserId(u.getUserId());
		tc.setTcNumber(u.getTcNumber());

		tc.setName(u.getName());
		tc.setAdmissionNo(u.getAdmissionNo());
		tc.setAdmissionDate(u.getAdmissionDate());

		tc.setFatherName(u.getFatherName());
		tc.setMotherName(u.getMotherName());
		tc.setDob(u.getDob());

		tc.setStudentPhone(u.getStudentPhone());
		tc.setParentPhone(u.getParentPhone());
		tc.setAddress(u.getAddress());
		tc.setGender(u.getGender());

		tc.setStudentAadharNo(u.getStudentAadharNo());
		tc.setParentAadharNo(u.getParentAadharNo());

		tc.setRte(u.getRte());
		tc.setSsmId(u.getSsmId());
		tc.setPassoutClass(u.getPassoutClass());

		tc.setCaste(u.getCaste());
		tc.setSubCaste(u.getSubCaste());
		tc.setReligion(u.getReligion());

		tc.setApaarId(u.getApaarId());
		tc.setPanNo(u.getPanNo());

		tc.setClassName(u.getStudentClass() != null ? u.getStudentClass().getClassName() : "");

		tc.setSessionName(u.getSession() != null ? u.getSession().getName() : "");

		tc.setTcIssueDate(LocalDate.now());

		// ✅ SAVE TC
		tcRepository.save(tc);

		// ❌ DELETE USER (MOVE COMPLETE)
		userRepository.delete(u);
	}

	public List<TransferCertificate> getAllTC() {
		return tcRepository.findAll();
	}

	public TransferCertificate getTCById(Integer tcId) {
		return tcRepository.findById(tcId).orElseThrow(() -> new RuntimeException("TC not found"));
	}

}
