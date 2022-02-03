package com.safetynet.alerts.services.firestation;

import com.safetynet.alerts.data.firestation.FireStationData;
import com.safetynet.alerts.data.firestation.FireStationDataImpl;
import com.safetynet.alerts.models.FireStation;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class FireStationServiceImpl implements FireStationService{

    private final FireStationData fireStationData = new FireStationDataImpl();

    @Override
    public List<FireStation> findAll() throws IOException, ParseException {
        return fireStationData.findAll();
    }
}
