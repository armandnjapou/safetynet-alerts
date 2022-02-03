package com.safetynet.alerts.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ModelReaderImpl implements ModelReader {

    @Override
    public JSONObject readDatafromJsonFile(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(new FileReader(path));
    }
}
