package com.safetynet.alerts.data.medicalrecord;

import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.MedicalRecord;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordData {
    List<MedicalRecord> findAll() throws IOException, ParseException;
    MedicalRecord findByFirstNameAndLastName(String firstname, String lastName);
    void add(MedicalRecord medicalRecord) throws AlreadyExistingException;
    void update(MedicalRecord medicalRecord);
    void delete(MedicalRecord medicalRecord);
}
