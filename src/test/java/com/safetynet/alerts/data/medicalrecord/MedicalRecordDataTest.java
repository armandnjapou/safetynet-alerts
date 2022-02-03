package com.safetynet.alerts.data.medicalrecord;

import com.safetynet.alerts.models.MedicalRecord;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

class MedicalRecordDataTest {

    private static IMedicalRecordData medicalRecordData;
    private static Throwable throwable;

    @BeforeAll
    public static void setUp() {
        medicalRecordData = new MedicalRecordData();
    }

    @Test
    public void should_return_list_of_23_medical_records_when_read_medical_records_from_json() throws IOException, ParseException {
        List<MedicalRecord> medicalRecords = medicalRecordData.findAll();
        Assertions.assertEquals(23, medicalRecords.size());
    }

    @Test
    public void should_return_true_when_verify_if_all_read_medical_records_are_not_null() throws IOException, ParseException {
        List<MedicalRecord> medicalRecords = medicalRecordData.findAll();
        Assertions.assertTrue(medicalRecords.stream().allMatch(Objects::nonNull));
    }

    @Test
    public void should_return_true_when_verify_if_all_read_medical_records_contains_non_null_values() throws IOException, ParseException {
        List<MedicalRecord> medicalRecords = medicalRecordData.findAll();
        Assertions.assertTrue(medicalRecords.stream().allMatch(medicalRecord -> medicalRecord.getFirstName() != null && medicalRecord.getLastName() != null
                && medicalRecord.getBirthdate() != null && medicalRecord.getMedications() != null && medicalRecord.getAllergies() != null));
    }
}