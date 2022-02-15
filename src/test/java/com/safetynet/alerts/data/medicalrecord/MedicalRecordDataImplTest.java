package com.safetynet.alerts.data.medicalrecord;

import com.safetynet.alerts.data.ProcessData;
import com.safetynet.alerts.data.ProcessDataImpl;
import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.utils.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class MedicalRecordDataImplTest {

    private static MedicalRecordData medicalRecordData;
    private static Throwable throwable;
    private static JSONObject data;
    private static final ProcessData processData = new ProcessDataImpl();

    @BeforeAll
    public static void setUp() throws IOException, ParseException {
        medicalRecordData = new MedicalRecordDataImpl();
        data = processData.readDatafromJsonFile(Constants.JSON_PATH);
    }

    @AfterAll
    public static void tearDown() {
        processData.writeDataInJsonFile(Constants.JSON_PATH, data);
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

    @Test
    public void should_return_medical_record_when_find_by_firstname_allison_and_lastname_boyd() {
        Assertions.assertNotNull(medicalRecordData.findByFirstNameAndLastName("Allison", "Boyd"));
    }

    @Test
    public void should_return_null_when_find_by_firstname_allison_and_lastname_boyden() {
        Assertions.assertNull(medicalRecordData.findByFirstNameAndLastName("Allison", "Boyden"));
    }

    @Test
    public void should_add_medical_record_to_data_store_when_add_medical_record() throws AlreadyExistingException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Jonas");
        medicalRecord.setLastName("Bard");
        medicalRecord.setBirthdate("03/06/1994");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecordData.add(medicalRecord);
        Assertions.assertEquals(medicalRecordData.findByFirstNameAndLastName("Jonas", "Bard"), medicalRecord);
    }

    @Test
    public void should_throw_already_existing_exception_when_add_existing_medical_record() throws AlreadyExistingException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Aiden");
        medicalRecord.setLastName("Hunt");
        medicalRecord.setBirthdate("03/06/1964");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecordData.add(medicalRecord);
        try {
            medicalRecordData.add(medicalRecord);
        } catch (Throwable t) {
            throwable = t;
        }
        Assertions.assertTrue(throwable instanceof AlreadyExistingException);
    }

    @Test
    public void should_update_birthdate_when_update_medical_record() {
        MedicalRecord existing = medicalRecordData.findByFirstNameAndLastName("Allison", "Boyd");
        existing.setBirthdate("03/06/1999");
        medicalRecordData.update(existing);
        Assertions.assertEquals(medicalRecordData.findByFirstNameAndLastName("Allison", "Boyd").getBirthdate(), existing.getBirthdate());
    }

    @Test
    public void should_delete_existing_medical_record_when_delete_medical_record() throws AlreadyExistingException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Tom");
        medicalRecord.setLastName("Eagan");
        medicalRecord.setBirthdate("03/06/1974");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecordData.add(medicalRecord);
        medicalRecordData.delete(medicalRecord);
        Assertions.assertNull(medicalRecordData.findByFirstNameAndLastName("Tom", "Eagan"));
    }
}