package com.safetynet.alerts.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface GeneralService {
    JSONObject getChildrenFromAddress(String address) throws IOException, ParseException;

    List<String> getPhoneNumbersByStationNumber(int stationNumber) throws IOException, ParseException;

    JSONArray getFloodByStations(List<Integer> stations) throws IOException, ParseException;

    JSONObject getPersonInfo(String firstName, String lastName) throws IOException, ParseException;

    List<String> getCommunityEmail(String city) throws IOException, ParseException;
}
