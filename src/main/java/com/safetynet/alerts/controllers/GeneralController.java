package com.safetynet.alerts.controllers;

import com.safetynet.alerts.services.GeneralService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GeneralController {

    @Autowired
    private GeneralService service;

    @GetMapping(value = "/childAlert", params = "address")
    public JSONObject getChildFromAddress(@RequestParam("address") String address) throws IOException, ParseException {
        return service.getChildrenFromAddress(address);
    }
}
