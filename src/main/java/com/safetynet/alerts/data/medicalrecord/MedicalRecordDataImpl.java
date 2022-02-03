package com.safetynet.alerts.data.medicalrecord;

import com.safetynet.alerts.data.ModelReader;
import com.safetynet.alerts.data.ModelReaderImpl;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.valueobjects.Allergy;
import com.safetynet.alerts.models.valueobjects.Medication;
import com.safetynet.alerts.utils.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDataImpl implements MedicalRecordData {

    private static final ModelReader modelReader = new ModelReaderImpl();

    @Override
    public List<MedicalRecord> findAll() throws IOException, ParseException {
        JSONObject json = modelReader.readDatafromJsonFile(Constants.JSON_PATH);
        JSONArray medicalRecords = (JSONArray) json.get(Constants.MEDICAL_RECORDS);
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        for (Object object : medicalRecords) {
            JSONObject medicalRecordObject = (JSONObject) object;
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setFirstName(medicalRecordObject.get(Constants.FIRSTNAME).toString());
            medicalRecord.setLastName(medicalRecordObject.get(Constants.LASTNAME).toString());
            medicalRecord.setBirthdate(medicalRecordObject.get(Constants.BIRTHDATE).toString());

            List<Medication> medications = new ArrayList<>();
            JSONArray medicationsFromObject = (JSONArray) medicalRecordObject.get(Constants.MEDICATIONS);
            if (medicationsFromObject.size() > 0) {
                for (Object medicationObject : medicationsFromObject) {
                    Medication medication = new Medication(medicationObject.toString());
                    medications.add(medication);
                }
            }
            medicalRecord.setMedications(medications);

            List<Allergy> allergies = new ArrayList<>();
            JSONArray allergiesFromObject = (JSONArray) medicalRecordObject.get(Constants.ALLERGIES);
            if (allergiesFromObject.size() > 0) {
                for (Object allergyObject : allergiesFromObject) {
                    Allergy allergy = new Allergy(allergyObject.toString());
                    allergies.add(allergy);
                }
            }
            medicalRecord.setAllergies(allergies);
            medicalRecordList.add(medicalRecord);
        }
        return medicalRecordList;
    }
}
