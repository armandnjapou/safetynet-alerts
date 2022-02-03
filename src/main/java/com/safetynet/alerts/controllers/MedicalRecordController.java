package com.safetynet.alerts.controllers;

import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.services.medicalrecord.MedicalRecordService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("medicalRecord")
    public List<MedicalRecord> getMedicalRecords() throws IOException, ParseException {
        return medicalRecordService.findAll();
    }
}
