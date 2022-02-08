package com.safetynet.alerts.data;

import com.safetynet.alerts.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProcessDataImpl implements ProcessData {

    private final Logger LOGGER = LogManager.getLogger(ProcessDataImpl.class);

    @Override
    public JSONObject readDatafromJsonFile(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(new FileReader(path));
    }

    @Override
    public void writeDataInJsonFile(String path, JSONObject json) {
        try {
            LOGGER.info("Trying to write json in file : {}", path);
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(json.toJSONString());
            fileWriter.flush();
            LOGGER.info("Write in file : {} succeeded", path);
        } catch (IOException e) {
            LOGGER.error("Parse exception while finding all persons. Trace : {}", e.toString());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject buildJSONObject(String objectType, JSONArray jsonArray) {
        LOGGER.info("Execute buildJSONObject with for object type : {}", objectType);
        JSONObject json = null;
        if (jsonArray != null) {
            try {
                JSONObject jsonObject = readDatafromJsonFile(Constants.JSON_PATH);
                JSONObject returnJSON = new JSONObject();
                switch (objectType) {
                    case Constants.PERSONS -> {
                        JSONArray fireStations = (JSONArray) jsonObject.get(Constants.FIRE_STATIONS);
                        JSONArray medicalRecords = (JSONArray) jsonObject.get(Constants.MEDICAL_RECORDS);
                        returnJSON.put(Constants.PERSONS, jsonArray);
                        returnJSON.put(Constants.FIRE_STATIONS, fireStations);
                        returnJSON.put(Constants.MEDICAL_RECORDS, medicalRecords);
                        json = returnJSON;
                    }
                    case Constants.FIRE_STATIONS -> {
                        JSONArray persons = (JSONArray) jsonObject.get(Constants.PERSONS);
                        JSONArray medicalRecords = (JSONArray) jsonObject.get(Constants.MEDICAL_RECORDS);
                        returnJSON.put(Constants.PERSONS, persons);
                        returnJSON.put(Constants.FIRE_STATIONS, jsonArray);
                        returnJSON.put(Constants.MEDICAL_RECORDS, medicalRecords);
                        json = returnJSON;
                    }
                    case Constants.MEDICAL_RECORDS -> {
                        JSONArray fireStations = (JSONArray) jsonObject.get(Constants.FIRE_STATIONS);
                        JSONArray persons = (JSONArray) jsonObject.get(Constants.PERSONS);
                        returnJSON.put(Constants.PERSONS, persons);
                        returnJSON.put(Constants.FIRE_STATIONS, fireStations);
                        returnJSON.put(Constants.MEDICAL_RECORDS, jsonArray);
                        json = returnJSON;
                    }
                    default -> LOGGER.error("Object type not recognized !");
                }
            } catch (IOException e) {
                LOGGER.error("IO Exception while finding all persons. Trace : {}", e.toString());
            } catch (ParseException e) {
                LOGGER.error("Parse exception while finding all persons. Trace : {}", e.toString());
            }
        }
        LOGGER.info("End of execution, method : buildJSONObject");
        return json;
    }
}
