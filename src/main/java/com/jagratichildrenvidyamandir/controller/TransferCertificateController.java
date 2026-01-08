package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.entity.TransferCertificate;
import com.jagratichildrenvidyamandir.service.TransferCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tc")
public class TransferCertificateController {

    @Autowired
    private TransferCertificateService tcService;

    /* =======================
       1️⃣ GENERATE TC
       ======================= */
    @PostMapping("/generate/{userId}")
    public ResponseEntity<?> generateTC(@PathVariable Integer userId) {
        tcService.generateTC(userId);
        return ResponseEntity.ok("TC generated successfully");
    }


    @GetMapping("/all")
    public ResponseEntity<List<TransferCertificate>> getAllTC() {
        return ResponseEntity.ok(tcService.getAllTC());
    }

    @GetMapping("/{tcId}")
    public ResponseEntity<TransferCertificate> getTCById(
            @PathVariable Integer tcId) {

        return ResponseEntity.ok(tcService.getTCById(tcId));
    }
}
