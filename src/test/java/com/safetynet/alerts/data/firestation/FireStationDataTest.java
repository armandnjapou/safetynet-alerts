package com.safetynet.alerts.data.firestation;

import com.safetynet.alerts.models.FireStation;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

class FireStationDataTest {
    private static IFireStationData fireStationData;

    @BeforeAll
    private static void setUp() {
        fireStationData = new FireStationData();
    }

    @Test
    public void should_return_list_of_13_fire_stations_when_find_all() throws IOException, ParseException {
        List<FireStation> fireStations =  fireStationData.findAll();
        Assertions.assertEquals(13, fireStations.size());
    }

    @Test
    public void should_return_true_when_verify_if_all_found_fire_stations_are_not_null() throws IOException, ParseException {
        List<FireStation> fireStations = fireStationData.findAll();
        Assertions.assertTrue(fireStations.stream().allMatch(Objects::nonNull));
    }

    @Test
    public void should_return_true_when_verify_if_all_found_fire_stations_contains_non_null_values() throws IOException, ParseException {
        List<FireStation> fireStations = fireStationData.findAll();
        Assertions.assertTrue(fireStations.stream().allMatch(fireStation -> fireStation.getAddress() != null && fireStation.getStation() > 0));
    }
}