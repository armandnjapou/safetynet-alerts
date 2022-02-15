package com.safetynet.alerts.controllers;

import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.services.medicalrecord.MedicalRecordService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("medicalRecord")
    public List<MedicalRecord> getMedicalRecords() throws IOException, ParseException {
        return medicalRecordService.findAll();
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<Object> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws AlreadyExistingException {
        medicalRecordService.add(medicalRecord);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(medicalRecord.getFirstName())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("medicalRecord")
    public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.update(medicalRecord);
    }

    @DeleteMapping("medicalRecord")
    public void deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.delete(medicalRecord);
    }
}
