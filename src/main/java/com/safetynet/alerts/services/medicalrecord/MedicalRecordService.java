package com.safetynet.alerts.services.medicalrecord;

import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.MedicalRecord;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecord> findAll() throws IOException, ParseException;
    void add(MedicalRecord medicalRecord) throws AlreadyExistingException;
    void update(MedicalRecord medicalRecord);
    void delete(MedicalRecord medicalRecord);
}
