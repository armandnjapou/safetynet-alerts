package com.safetynet.alerts.data;

import com.safetynet.alerts.utils.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import java.io.IOException;

class ProcessDataImplTest {

    private static ProcessData processData;
    private Throwable throwable;
    private JSONObject json;
    private static final String PATH = "src/test/resources/test.json";
    private static final String WRONG_PATH = "src/test/resources/file.json";
    private static final String BAD_PATH = "src/main/java/com/safetynet/alerts/SafetyNetAlertsApplication.java";

    @BeforeAll
    private static void
    setUp() {
        processData = new ProcessDataImpl();
    }

    @Test
    public void should_return_json_object_of_size_3_when_read_data_from_local_json() throws IOException, ParseException {
        json = processData.readDatafromJsonFile(PATH);
        int expectedSize = 3;
        Assertions.assertEquals(expectedSize, json.size());
    }

    @Test
    public void should_throw_io_exception_when_read_data_from_unexisting_file() {
        try {
            json = processData.readDatafromJsonFile(WRONG_PATH);
        } catch (Throwable throwable) {
            this.throwable = throwable;
        }
        Assertions.assertTrue(throwable instanceof IOException);
    }

    @Test
    public void should_throw_parse_exception_when_read_data_from_non_json_file() {
        try {
            json = processData.readDatafromJsonFile(BAD_PATH);
        } catch (Throwable throwable) {
            this.throwable = throwable;
        }
        Assertions.assertTrue(throwable instanceof ParseException);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void should_add_json_object_to_file_when_write_in_json_file() throws IOException, ParseException {
        JSONObject jsonObject = new JSONObject();
        JSONArray persons = new JSONArray();
        JSONObject person = new JSONObject();
        person.put("firstName", "Toto");
        person.put("lastName", "Jojo");
        person.put("address", "1675 Road Street");
        person.put("city", "Kansas");
        person.put("zip", "78435");
        person.put("phone", "841-675-6543");
        person.put("email", "toto@spin.com");
        persons.add(person);
        jsonObject.put("persons", persons);
        processData.writeDataInJsonFile(PATH, jsonObject);

        json = processData.readDatafromJsonFile(PATH);
        JSONArray expectedArray = (JSONArray) json.get("persons");
        JSONObject expectedObject = (JSONObject) expectedArray.get(0);

        Assertions.assertEquals("Toto", expectedObject.get("firstName"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void should_return_json_object_with_all_types_when_build_json_object_with_fire_stations() {
        JSONArray fireStations = new JSONArray();
        JSONObject fireStationObject = new JSONObject();
        fireStationObject.put("address", "rue de beauvoir");
        fireStationObject.put("station", "2");
        fireStations.add(fireStationObject);
        JSONObject expected = processData.buildJSONObject(Constants.FIRE_STATIONS, fireStations);
        Assertions.assertTrue(expected.get(Constants.PERSONS) != null && expected.get(Constants.FIRE_STATIONS) != null && expected.get(Constants.MEDICAL_RECORDS) != null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void should_throw_null_pointer_exception_when_build_json_object_with_null_object_type_and_fire_stations() {
        try {
            JSONArray fireStations = new JSONArray();
            JSONObject fireStationObject = new JSONObject();
            fireStationObject.put("address", "rue de beauvoir");
            fireStationObject.put("station", "2");
            fireStations.add(fireStationObject);
            JSONObject expected = processData.buildJSONObject(null, fireStations);
        } catch (Throwable t) {
            throwable = t;
        }
        Assertions.assertTrue(throwable instanceof NullPointerException);
    }

    @Test
    public void should_return_null_when_build_json_object_with_fire_stations_and_null_json_array() {
        JSONObject expected = processData.buildJSONObject(Constants.FIRE_STATIONS, null);
        Assertions.assertNull(expected);
    }
}