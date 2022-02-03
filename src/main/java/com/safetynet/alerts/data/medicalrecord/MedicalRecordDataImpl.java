package com.safetynet.alerts.data.medicalrecord;

import com.safetynet.alerts.data.ModelReader;
import com.safetynet.alerts.data.ModelReaderImpl;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.valueobjects.Allergy;
import com.safetynet.alerts.models.valueobjects.Medication;
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
        JSONObject json = modelReader.readDatafromJsonFile("src/main/resources/static/data.json");
        JSONArray medicalRecords = (JSONArray) json.get("medicalrecords");
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        for (Object object : medicalRecords) {
            JSONObject medicalRecordObject = (JSONObject) object;
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setFirstName(medicalRecordObject.get("firstName").toString());
            medicalRecord.setLastName(medicalRecordObject.get("lastName").toString());
            medicalRecord.setBirthdate(medicalRecordObject.get("birthdate").toString());

            List<Medication> medications = new ArrayList<>();
            JSONArray medicationsFromObject = (JSONArray) medicalRecordObject.get("medications");
            if (medicationsFromObject.size() > 0) {
                for (Object medicationObject : medicationsFromObject) {
                    Medication medication = new Medication(medicationObject.toString());
                    medications.add(medication);
                }
            }
            medicalRecord.setMedications(medications);

            List<Allergy> allergies = new ArrayList<>();
            JSONArray allergiesFromObject = (JSONArray) medicalRecordObject.get("allergies");
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
