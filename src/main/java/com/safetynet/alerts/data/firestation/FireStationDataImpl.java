package com.safetynet.alerts.data.firestation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.data.ProcessData;
import com.safetynet.alerts.data.ProcessDataImpl;
import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FireStationDataImpl implements FireStationData {

    private static final ProcessData processData = new ProcessDataImpl();
    private static final Logger LOGGER = LogManager.getLogger(FireStationDataImpl.class);
    private String methodName = null;

    @Override
    public List<FireStation> findAll() throws IOException, ParseException {
        JSONObject json = processData.readDatafromJsonFile(Constants.JSON_PATH);
        JSONArray fireStations = (JSONArray) json.get(Constants.FIRE_STATIONS);
        List<FireStation> fireStationList = new ArrayList<>();
        for (Object object : fireStations) {
            JSONObject fireStationObject = (JSONObject) object;
            FireStation fireStation = new FireStation();
            fireStation.setAddress(fireStationObject.get(Constants.ADDRESS).toString());
            fireStation.setStation(Integer.parseInt(fireStationObject.get(Constants.STATION).toString()));
            fireStationList.add(fireStation);
        }
        return fireStationList;
    }

    @Override
    public FireStation find(FireStation fireStation) throws IOException, ParseException {
        List<FireStation> fireStations = findAll();
        return fireStations.stream()
                .filter(f -> f.getAddress().equals(fireStation.getAddress()) && f.getStation() == fireStation.getStation())
                .findAny()
                .orElse(null);
    }

    @Override
    public FireStation findByAddress(String address) throws IOException, ParseException {
        List<FireStation> fireStations = findAll();
        return fireStations.stream()
                .filter(f -> f.getAddress().equals(address))
                .findAny()
                .orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(FireStation fireStation) throws AlreadyExistingException {
        methodName = "add::FireStation";
        LOGGER.info("Start method {}", methodName);
        if (fireStation != null) {
            try {
                List<FireStation> fireStations = findAll();
                FireStation existing = find(fireStation);
                if (existing == null) {
                    JSONArray fireStationsArray = (JSONArray) JSONValue.parse(new ObjectMapper().writeValueAsString(fireStations));
                    fireStationsArray.add(JSONValue.parse(new ObjectMapper().writeValueAsString(fireStation)));
                    JSONObject json = processData.buildJSONObject(Constants.FIRE_STATIONS, fireStationsArray);
                    processData.writeDataInJsonFile(Constants.JSON_PATH, json);
                } else throw new AlreadyExistingException("Fire station already exists in data storage...");
            } catch (IOException e) {
                LOGGER.error("IO Exception while finding fire station. Trace : {}", e.toString());
            } catch (ParseException e) {
                LOGGER.error("Parse exception while finding fire station. Trace : {}", e.toString());
            }
        } else LOGGER.error("Null parameter passed...");
        LOGGER.info("End method {}", methodName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(FireStation fireStation) {
        methodName = "update::FireStation";
        LOGGER.info("Start method {}", methodName);
        if (fireStation != null) {
            try {
                FireStation existing = findByAddress(fireStation.getAddress());
                if (existing != null) {
                    existing.setAddress(fireStation.getAddress());
                    existing.setStation(fireStation.getStation());
                    List<FireStation> fireStations = findAll();
                    JSONArray fireStationArray = new JSONArray();
                    for (FireStation f : fireStations) {
                        if (f.getAddress().equals(existing.getAddress()))
                            fireStationArray.add(JSONValue.parse(new ObjectMapper().writeValueAsString(existing)));
                        else fireStationArray.add(JSONValue.parse(new ObjectMapper().writeValueAsString(f)));
                    }
                    JSONObject json = processData.buildJSONObject(Constants.FIRE_STATIONS, fireStationArray);
                    processData.writeDataInJsonFile(Constants.JSON_PATH, json);
                }
            } catch (IOException e) {
                LOGGER.error("IO Exception while finding fire station. Trace : {}", e.toString());
            } catch (ParseException e) {
                LOGGER.error("Parse exception while finding fire station. Trace : {}", e.toString());
            }
        } else LOGGER.error("Null parameter passed...");
        LOGGER.info("End method {}", methodName);
    }

    @Override
    public void delete(FireStation fireStation) {
        methodName = "delete::FireStation";
        LOGGER.info("Start method {}", methodName);
        if (fireStation != null) {
            try {
                List<FireStation> fireStations = findAll();
                List<FireStation> fireStationList = fireStations.stream()
                        .filter(f -> f.getStation() == fireStation.getStation() && f.getAddress().equals(fireStation.getAddress())).toList();
                fireStations.removeAll(fireStationList);
                JSONArray fireStationsArray = (JSONArray) JSONValue.parse(new ObjectMapper().writeValueAsString(fireStations));
                JSONObject json = processData.buildJSONObject(Constants.FIRE_STATIONS, fireStationsArray);
                processData.writeDataInJsonFile(Constants.JSON_PATH, json);
            } catch (IOException e) {
                LOGGER.error("IO Exception while finding fire station. Trace : {}", e.toString());
            } catch (ParseException e) {
                LOGGER.error("Parse exception while finding fire station. Trace : {}", e.toString());
            }
        } else LOGGER.error("Null parameter passed...");
        LOGGER.info("End method {}", methodName);
    }

    @Override
    public FireStation findByStationNumber(int stationNumber) throws IOException, ParseException {
        List<FireStation> fireStations = findAll();
        return fireStations.stream()
                .filter(f -> f.getStation() == stationNumber)
                .findAny()
                .orElse(null);
    }
}
