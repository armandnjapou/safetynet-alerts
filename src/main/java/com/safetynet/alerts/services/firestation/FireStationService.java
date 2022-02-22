package com.safetynet.alerts.services.firestation;

import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.models.Person;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface FireStationService {
    List<FireStation> findAll() throws IOException, ParseException;
    void add(FireStation fireStation) throws AlreadyExistingException;
    void update(FireStation fireStation);
    void delete(FireStation fireStation);
    JSONObject getPersonsCoveredByFireStation(int id) throws IOException, ParseException;
}
