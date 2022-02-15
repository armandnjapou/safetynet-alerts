package com.safetynet.alerts.data.medicalrecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.data.ProcessData;
import com.safetynet.alerts.data.ProcessDataImpl;
import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.valueobjects.Allergy;
import com.safetynet.alerts.models.valueobjects.Medication;
import com.safetynet.alerts.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDataImpl implements MedicalRecordData {

    private static final ProcessData processData = new ProcessDataImpl();
    private static final Logger LOGGER = LogManager.getLogger(MedicalRecordDataImpl.class);

    @Override
    public List<MedicalRecord> findAll() throws IOException, ParseException {
        JSONObject json = processData.readDatafromJsonFile(Constants.JSON_PATH);
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

    @Override
    public MedicalRecord findByFirstNameAndLastName(String firstname, String lastName) {
        String methodName = "findByFirstNameAndLastName";
        LOGGER.info("Start method : {}", methodName);
        MedicalRecord found = null;
        try {
            List<MedicalRecord> medicalRecords = findAll();
            found = medicalRecords.stream()
                    .filter(m -> m.getFirstName().equals(firstname) && m.getLastName().equals(lastName))
                    .findAny()
                    .orElse(null);
        } catch (IOException e) {
            LOGGER.error("IO exception occured. Trace : " + e);
        } catch (ParseException e) {
            LOGGER.error("Parse Exception occured. Trace : " + e);
        }
        LOGGER.info("End of method : {}", methodName);
        return found;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(MedicalRecord medicalRecord) throws AlreadyExistingException {
        String methodName = "add";
        LOGGER.info("Start method : {}", methodName);
        if (medicalRecord != null) {
            MedicalRecord existing = findByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());
            if (existing == null) {
                try {
                    List<MedicalRecord> medicalRecords = findAll();
                    JSONArray medicalRecordsArray = (JSONArray) JSONValue.parse(new ObjectMapper().writeValueAsString(medicalRecords));
                    medicalRecordsArray.add(JSONValue.parse(new ObjectMapper().writeValueAsString(medicalRecord)));
                    JSONObject json = processData.buildJSONObject(Constants.MEDICAL_RECORDS, medicalRecordsArray);
                    processData.writeDataInJsonFile(Constants.JSON_PATH, json);
                } catch (IOException | ParseException e) {
                    LOGGER.error("Trace : {}", e.toString());
                }
            } else throw new AlreadyExistingException("Already existing object in data store...");
        } else LOGGER.error("Null parameter found...");
        LOGGER.info("End of method : {}", methodName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(MedicalRecord medicalRecord) {
        String methodName = "update";
        LOGGER.info("Start method : {}", methodName);
        if (medicalRecord != null) {
            MedicalRecord existing =  findByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());
            if (existing != null) {
                existing.setFirstName(medicalRecord.getFirstName());
                existing.setLastName(medicalRecord.getLastName());
                existing.setBirthdate(medicalRecord.getBirthdate());
                existing.setMedications(medicalRecord.getMedications());
                existing.setAllergies(medicalRecord.getAllergies());
                try {
                    List<MedicalRecord> medicalRecords = findAll();
                    JSONArray medicalRecordsArray = new JSONArray();
                    for (MedicalRecord m : medicalRecords) {
                        if (m.getFirstName().equals(existing.getFirstName()) && m.getLastName().equals(existing.getLastName()))
                            medicalRecordsArray.add(JSONValue.parse(new ObjectMapper().writeValueAsString(existing)));
                        else medicalRecordsArray.add(JSONValue.parse(new ObjectMapper().writeValueAsString(medicalRecord)));
                    }
                    JSONObject json = processData.buildJSONObject(Constants.MEDICAL_RECORDS, medicalRecordsArray);
                    processData.writeDataInJsonFile(Constants.JSON_PATH, json);
                } catch (IOException | ParseException e) {
                    LOGGER.error("Trace : {}", e.toString());
                }
            }
        } else LOGGER.error("Null parameter found...");
        LOGGER.info("End of method : {}", methodName);
    }

    @Override
    public void delete(MedicalRecord medicalRecord) {
        String methodName = "delete";
        LOGGER.info("Start method : {}", methodName);
        if (medicalRecord != null) {
            try {
                List<MedicalRecord> medicalRecords = findAll();
                List <MedicalRecord> medicalRecordList = medicalRecords.stream()
                        .filter(m -> m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName())).toList();
                medicalRecords.removeAll(medicalRecordList);
                JSONArray medicalRecordsArray = (JSONArray) JSONValue.parse(new ObjectMapper().writeValueAsString(medicalRecords));
                JSONObject json = processData.buildJSONObject(Constants.MEDICAL_RECORDS, medicalRecordsArray);
                processData.writeDataInJsonFile(Constants.JSON_PATH, json);
            } catch (IOException | ParseException e) {
                LOGGER.error("Trace : {}", e.toString());
            }
        } else LOGGER.error("Null parameter found...");
        LOGGER.info("End of method : {}", methodName);
    }
}
