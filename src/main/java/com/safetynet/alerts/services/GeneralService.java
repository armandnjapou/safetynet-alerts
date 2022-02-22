package com.safetynet.alerts.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface GeneralService {
    JSONObject getChildrenFromAddress(String address) throws IOException, ParseException;
}
