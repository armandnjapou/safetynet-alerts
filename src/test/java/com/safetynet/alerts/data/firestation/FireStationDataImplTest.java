package com.safetynet.alerts.data.firestation;

import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.FireStation;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

class FireStationDataImplTest {
    private static FireStationData fireStationData;
    private static Throwable throwable;

    @BeforeAll
    private static void setUp() {
        fireStationData = new FireStationDataImpl();
    }

    @AfterAll
    public static void tearDown() {
        FireStation fireStation = new FireStation();
        fireStation.setStation(3);
        fireStation.setAddress("56 Bd Foch");
        fireStationData.delete(fireStation);
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

    @Test
    public void should_return_non_null_fire_station_when_find_fire_station_with_address_489_manchester_st_and_station_number_4() throws IOException, ParseException {
        FireStation expected = new FireStation();
        expected.setAddress("489 Manchester St");
        expected.setStation(4);
        Assertions.assertNotNull(fireStationData.find(expected));
    }

    @Test
    public void should_return_null_fire_station_when_find_fire_station_with_address_1509_culver_st_and_station_number_6() throws IOException, ParseException {
        FireStation expected = new FireStation();
        expected.setAddress("1509 Culver St");
        expected.setStation(6);
        Assertions.assertNull(fireStationData.find(expected));
    }

    @Test
    public void should_add_fire_station_when_add_fire_station_with_address_33_av_Stan_and_station_number_1() throws AlreadyExistingException, IOException, ParseException {
        FireStation fireStation = new FireStation();
        fireStation.setStation(1);
        fireStation.setAddress("33 av Stan");
        fireStationData.add(fireStation);
        Assertions.assertEquals(fireStationData.find(fireStation), fireStation);
    }

    @Test
    public void should_throw_already_existing_exception_when_add_existing_fire_station() throws AlreadyExistingException {
        FireStation fireStation = new FireStation();
        fireStation.setStation(3);
        fireStation.setAddress("56 Bd Foch");
        fireStationData.add(fireStation);
        try {
            fireStationData.add(fireStation);
        } catch (Throwable t) {
            throwable = t;
        }
        Assertions.assertTrue(throwable instanceof AlreadyExistingException);
    }

    @Test
    public void should_delete_fire_station_when_delete_fire_station_with_address_33_av_Stan_and_station_number_1() throws IOException, ParseException {
        FireStation fireStation = new FireStation();
        fireStation.setStation(1);
        fireStation.setAddress("33 av Stan");
        FireStation existing = fireStationData.find(fireStation);
        fireStationData.delete(existing);
    }

    @Test
    public void should_update_station_number_to_10_when_update_fire_station_with_address__1509_culver_st_and_station_number_10() throws IOException, ParseException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("1509 Culver St");
        fireStation.setStation(10);
        fireStationData.update(fireStation);
        FireStation f = fireStationData.find(fireStation);
        Assertions.assertNotNull(f);
    }
}