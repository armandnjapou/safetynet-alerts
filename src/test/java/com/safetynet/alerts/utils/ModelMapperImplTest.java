package com.safetynet.alerts.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ModelMapperImplTest {

    private static ModelMapper modelMapper;
    private Throwable throwable;

    @BeforeAll
    private static void setUp() {
        modelMapper = new ModelMapperImpl();
    }

    @Test
    public void should_return_json_object_of_size_3_when_map_data_from_local_json() throws IOException, ParseException {
        JSONObject json = modelMapper.mapDatafromJsonFileToJsonObject("src/test/resources/data.json");
        int expectedSize = 3;
        Assertions.assertEquals(expectedSize, json.size());
    }

    @Test
    public void should_throw_io_exception_when_map_data_from_unexisting_file() {
        try {
            JSONObject json = modelMapper.mapDatafromJsonFileToJsonObject("src/test/resources/file.json");
        } catch (Throwable throwable) {
            this.throwable = throwable;
        }
        Assertions.assertTrue(throwable instanceof IOException);
    }

    @Test
    public void should_throw_parse_exception_when_map_data_from_non_json_file() {
        try {
            JSONObject json = modelMapper.mapDatafromJsonFileToJsonObject("src/main/java/com/safetynet/alerts/SafetyNetAlertsApplication.java");
        } catch (Throwable throwable) {
            this.throwable = throwable;
        }
        Assertions.assertTrue(throwable instanceof ParseException);
    }
}