package com.safetynet.alerts.controllers;

import com.safetynet.alerts.services.GeneralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Api("General controller to handle cross model request")
@RestController
public class GeneralController {

    @Autowired
    private GeneralService service;

    @ApiOperation("Get name, age of all child (person under 18) and other persons living at the given address")
    @GetMapping(value = "/childAlert", params = "address")
    public JSONObject getChildFromAddress(@RequestParam("address") String address) throws IOException, ParseException {
        return service.getChildrenFromAddress(address);
    }

    @ApiOperation("Get phone numbers of all persons living around the given fire station")
    @GetMapping(value = "/phoneAlert", params = "firestation")
    public List<String> getPhoneNumbersByFireStationNumber(@RequestParam("firestation") int stationNumber) throws IOException, ParseException {
        return service.getPhoneNumbersByStationNumber(stationNumber);
    }

    @ApiOperation("Get all home closed to fire stations")
    @GetMapping(value = "/flood/stations", params = "stations")
    public JSONArray getFloodByStations(@RequestParam List<Integer> stations) throws IOException, ParseException {
        return service.getFloodByStations(stations);
    }

    @ApiOperation("Get personal info (name, address, age, email, medications, allergies) from given firstname and lastname")
    @GetMapping(value = "/personInfo", params = {"firstName","lastName"})
    public JSONObject getPersonalInfo(@RequestParam String firstName, @RequestParam String lastName) throws IOException, ParseException {
        return service.getPersonInfo(firstName, lastName);
    }

    @ApiOperation("Get emails of persons living in the given city")
    @GetMapping(value = "/communityEmail", params = "city")
    public List<String> getCommunityEmail(@RequestParam String city) throws IOException, ParseException {
        return service.getCommunityEmail(city);
    }
}
