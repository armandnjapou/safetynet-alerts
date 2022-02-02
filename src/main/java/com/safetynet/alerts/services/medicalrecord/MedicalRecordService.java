package com.safetynet.alerts.services.medicalrecord;

import com.safetynet.alerts.models.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecord> findAll();
    MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);
    MedicalRecord add(MedicalRecord person);
    MedicalRecord update(MedicalRecord person);
    void delete(MedicalRecord person);
}
