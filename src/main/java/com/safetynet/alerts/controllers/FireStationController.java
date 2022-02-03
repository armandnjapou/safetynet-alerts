package com.safetynet.alerts.controllers;

import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.services.firestation.FireStationService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FireStationController {

    @Autowired
    private FireStationService fireStationService;

    @GetMapping("/firestations")
    public List<FireStation> getFireStations() throws IOException, ParseException {
        return fireStationService.findAll();
    }
}
