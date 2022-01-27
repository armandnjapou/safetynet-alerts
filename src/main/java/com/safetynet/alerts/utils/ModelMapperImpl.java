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
import java.util.List;

public class ModelMapperImpl implements ModelMapper {

    @Override
    public List<Person> mapPersonsFromJson(JSONArray persons) {
        return null;
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
    public JSONObject mapDatafromJsonFileToJsonObject(String path) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(new FileReader(path));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
