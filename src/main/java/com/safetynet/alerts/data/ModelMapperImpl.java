package com.safetynet.alerts.data;

import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.models.valueobjects.Allergy;
import com.safetynet.alerts.models.valueobjects.Medication;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelMapperImpl implements ModelMapper {
    @Override
    public List<Person> mapPersonsFromJson(JSONArray persons) {
        List<Person> personList = new ArrayList<>();
        for(Object object : persons){
            JSONObject personObject = (JSONObject) object;
            Person person = new Person();
            person.setFirstName(personObject.get("firstName").toString());
            person.setLastName(personObject.get("lastName").toString());
            person.setAddress(personObject.get("address").toString());
            person.setCity(personObject.get("city").toString());
            person.setZip(personObject.get("zip").toString());
            person.setPhone(personObject.get("phone").toString());
            person.setEmail(personObject.get("email").toString());
            personList.add(person);
        }
        return personList;
    }

    @Override
    public List<MedicalRecord> mapMedicalRecordsFromJson(JSONArray medicalRecords) {
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

    @Override
    public List<FireStation> mapFireStationFromJson(JSONArray fireStations) {
        List<FireStation> fireStationList = new ArrayList<>();
        for (Object object : fireStations) {
            JSONObject fireStationObject = (JSONObject) object;
            FireStation fireStation = new FireStation();
            fireStation.setAddress(fireStationObject.get("address").toString());
            fireStation.setStation(Integer.parseInt(fireStationObject.get("station").toString()));
            fireStationList.add(fireStation);
        }
        return fireStationList;
    }

    @Override
    public JSONObject mapDatafromJsonFileToJsonObject(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(new FileReader(path));
    }
}
