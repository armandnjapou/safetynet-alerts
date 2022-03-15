package com.safetynet.alerts.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneralServiceImplTest {

    private static final GeneralService service = new GeneralServiceImpl();
    private Throwable throwable;

    @Test
    public void should_return_2_children_when_get_children_from_address_1509_culver_st() throws IOException, ParseException {
        JSONObject expected = service.getChildrenFromAddress("1509 Culver St");
        JSONArray children = (JSONArray) expected.get("children");
        assertEquals(2, children.size());
    }

    @Test
    public void should_return_null_when_get_children_from_unknown_address() throws IOException, ParseException {
        JSONObject expected = service.getChildrenFromAddress("Unknown address");
        assertNull(expected);
    }

    @Test
    public void should_return_list_of_size_1_when_get_phone_numbers_by_fire_station_number_2() throws IOException, ParseException {
        List<String> phoneNumbers = service.getPhoneNumbersByStationNumber(2);
        assertEquals(1, phoneNumbers.size());
    }
}