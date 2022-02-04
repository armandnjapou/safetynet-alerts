package com.safetynet.alerts.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
            LOGGER.error("An error occured : " + e);
        }
    }
}
