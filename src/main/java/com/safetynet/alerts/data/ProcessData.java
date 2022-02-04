package com.safetynet.alerts.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface ProcessData {
    JSONObject readDatafromJsonFile(String path) throws IOException, ParseException;
    void writeDataInJsonFile(String path, JSONObject json);
}
