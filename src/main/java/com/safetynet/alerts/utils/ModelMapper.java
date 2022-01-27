package com.safetynet.alerts.utils;

import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.util.List;

public interface ModelMapper {
    List<Person> mapPersonsFromJson(JSONArray persons);
    List<MedicalRecord> mapMedicalRecordsFromJson(JSONArray medicalRecords);
    List<FireStation> mapFireStationFromJson(JSONArray fireStations);

    JSONObject mapDatafromJsonFileToJsonObject(String path) throws IOException, ParseException;
}
