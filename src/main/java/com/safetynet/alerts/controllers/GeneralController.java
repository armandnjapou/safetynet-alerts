package com.safetynet.alerts.controllers;

import com.safetynet.alerts.services.GeneralService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GeneralController {

    @Autowired
    private GeneralService service;

    @GetMapping(value = "/childAlert", params = "address")
    public JSONObject getChildFromAddress(@RequestParam("address") String address) throws IOException, ParseException {
        return service.getChildrenFromAddress(address);
    }

    @GetMapping(value = "/phoneAlert", params = "firestation")
    public List<String> getPhoneNumbersByFireStationNumber(@RequestParam("firestation") int stationNumber) throws IOException, ParseException {
        return service.getPhoneNumbersByStationNumber(stationNumber);
    }

    @GetMapping(value = "/flood/stations", params = "stations")
    public JSONArray getFloodByStations(@RequestParam List<Integer> stations) throws IOException, ParseException {
        return service.getFloodByStations(stations);
    }
}
