package com.safetynet.alerts.controllers;

import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.services.firestation.FireStationService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
public class FireStationController {

    @Autowired
    private FireStationService fireStationService;

    @GetMapping("/firestation")
    public List<FireStation> getFireStations() throws IOException, ParseException {
        return fireStationService.findAll();
    }

    @PostMapping("/firestation")
    public ResponseEntity<Object> addFireStation(@RequestBody FireStation fireStation) throws AlreadyExistingException {
        fireStationService.add(fireStation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(fireStation.getAddress())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/firestation")
    public void updateFireStation(@RequestBody FireStation fireStation) {
        fireStationService.update(fireStation);
    }

    @DeleteMapping("/firestation")
    public void deleteFireStation(@RequestBody FireStation fireStation) {
        fireStationService.delete(fireStation);
    }

    @GetMapping(value = "/firestation", params = "stationNumber")
    public JSONObject getPersonsCoveredByFireStation(@RequestParam("stationNumber") int stationNumber) throws IOException, ParseException {
        return fireStationService.getPersonsCoveredByFireStation(stationNumber);
    }
}
