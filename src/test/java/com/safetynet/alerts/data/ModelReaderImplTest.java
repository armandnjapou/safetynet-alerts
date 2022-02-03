package com.safetynet.alerts.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import java.io.IOException;

class ModelReaderImplTest {

    private static ModelReader modelReader;
    private Throwable throwable;
    private JSONObject json;

    @BeforeAll
    private static void setUp() {
        modelReader = new ModelReaderImpl();
    }

    @Test
    public void should_return_json_object_of_size_3_when_read_data_from_local_json() throws IOException, ParseException {
        json = modelReader.readDatafromJsonFile("src/test/resources/data.json");
        int expectedSize = 3;
        Assertions.assertEquals(expectedSize, json.size());
    }

    @Test
    public void should_throw_io_exception_when_read_data_from_unexisting_file() {
        try {
            json = modelReader.readDatafromJsonFile("src/test/resources/file.json");
        } catch (Throwable throwable) {
            this.throwable = throwable;
        }
        Assertions.assertTrue(throwable instanceof IOException);
    }

    @Test
    public void should_throw_parse_exception_when_read_data_from_non_json_file() {
        try {
            json = modelReader.readDatafromJsonFile("src/main/java/com/safetynet/alerts/SafetyNetAlertsApplication.java");
        } catch (Throwable throwable) {
            this.throwable = throwable;
        }
        Assertions.assertTrue(throwable instanceof ParseException);
    }
}