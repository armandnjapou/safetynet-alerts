package com.safetynet.alerts.data.firestation;

import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.FireStation;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface FireStationData {
    List<FireStation> findAll() throws IOException, ParseException;
    FireStation find(FireStation fireStation) throws IOException, ParseException;
    FireStation findByAddress(String address) throws IOException, ParseException;
    void add(FireStation fireStation) throws AlreadyExistingException;
    void update(FireStation fireStation);
    void delete(FireStation fireStation);
}
