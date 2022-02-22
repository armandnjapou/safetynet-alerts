package com.safetynet.alerts.services.firestation;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FireStationServiceImplTest {

    private final FireStationService fireStationService = new FireStationServiceImpl();

    @Test
    public void should_return_persons_covered_by_fire_station_when_get_persons_covered_by_fire_station() throws IOException, ParseException {
        JSONObject jsonObject = fireStationService.getPersonsCoveredByFireStation(3);
        assertNotNull(jsonObject);
    }
}