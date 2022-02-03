package com.safetynet.alerts.data.firestation;

import com.safetynet.alerts.data.ModelReader;
import com.safetynet.alerts.data.ModelReaderImpl;
import com.safetynet.alerts.models.FireStation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FireStationData implements IFireStationData {

    private static final ModelReader modelReader = new ModelReaderImpl();

    @Override
    public List<FireStation> findAll() throws IOException, ParseException {
        JSONObject json = modelReader.readDatafromJsonFile("src/main/resources/static/data.json");
        JSONArray fireStations = (JSONArray) json.get("firestations");
        List<FireStation> fireStationList = new ArrayList<>();
        for (Object object : fireStations) {
            JSONObject fireStationObject = (JSONObject) object;
            FireStation fireStation = new FireStation();
            fireStation.setAddress(fireStationObject.get("address").toString());
            fireStation.setStation(Integer.parseInt(fireStationObject.get("station").toString()));
            fireStationList.add(fireStation);
        }
        return fireStationList;
    }
}
