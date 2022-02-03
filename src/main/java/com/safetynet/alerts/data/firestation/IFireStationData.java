package com.safetynet.alerts.data.firestation;

import com.safetynet.alerts.models.FireStation;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface IFireStationData {
    List<FireStation> findAll() throws IOException, ParseException;
}
