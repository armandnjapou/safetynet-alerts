package com.safetynet.alerts.services.firestation;

import com.safetynet.alerts.models.FireStation;

import java.util.List;

public interface FireStationService {
    List<FireStation> findAll();
    FireStation findByAddress(String address);
    FireStation add(FireStation fireStation);
    FireStation updateStationNumber(int stationNumber);
    void delete(FireStation fireStation);
}
