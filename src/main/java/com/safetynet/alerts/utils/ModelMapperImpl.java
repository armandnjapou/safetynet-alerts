package com.safetynet.alerts.utils;

import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
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
        return null;
    }

    @Override
    public List<FireStation> mapFireStationFromJson(JSONArray fireStations) {
        return null;
    }

    @Override
    public JSONObject mapDatafromJsonFileToJsonObject(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(new FileReader(path));
    }
}
