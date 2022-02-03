package com.safetynet.alerts.services.firestation;

import com.safetynet.alerts.models.FireStation;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface FireStationService {
    List<FireStation> findAll() throws IOException, ParseException;
}
