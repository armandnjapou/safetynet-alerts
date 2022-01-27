package com.safetynet.alerts.utils;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ModelMapperImplTest {

    private static ModelMapper modelMapper;

    @BeforeAll
    private static void setUp() {
        modelMapper = new ModelMapperImpl();
    }

    @Test
    public void should_return_json_object_of_size_3_when_map_data_from_local_json() {
        JSONObject json = modelMapper.mapDatafromJsonFileToJsonObject("src/test/resources/data.json");
        int expectedSize = 3;
        Assertions.assertEquals(expectedSize, json.size());
    }

    @Test
    public void should() {

    }

}