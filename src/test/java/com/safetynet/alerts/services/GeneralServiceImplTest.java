package com.safetynet.alerts.services;

import com.safetynet.alerts.models.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneralServiceImplTest {

    private static final GeneralService service = new GeneralServiceImpl();

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

    @Test
    public void should_return_list_of_size_2_when_get_flood_from_station_1_and_2() throws IOException, ParseException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        JSONArray expected = service.getFloodByStations(list);
        assertEquals(2, expected.size());
    }

    @Test
    public void should_return_list_of_one_person_when_get_person_info_with_firstname_clive_and_lastname_ferguson() throws IOException, ParseException {
        JSONObject expected = service.getPersonInfo("Clive", "Ferguson");
        assertEquals(1, expected.size());
    }

    @Test
    public void should_return_list_of_one_person_with_firstname_clive_and_lastname_ferguson_when_get_person_info_with_firstname_clive_and_lastname_ferguson() throws IOException, ParseException {
        JSONObject object = service.getPersonInfo("Clive", "Ferguson");
        JSONArray array = (JSONArray) object.get("persons");
        JSONObject clive = (JSONObject) array.get(0);
        assertTrue(clive.get("firstName").equals("Clive") && clive.get("lastName").equals("Ferguson"));
    }

    @Test
    public void should_return_list_of_23_emails_when_get_community_email_with_city_culver() throws IOException, ParseException {
        List<String> expected =  service.getCommunityEmail("Culver");
        assertEquals(23, expected.size());
    }

    @Test
    public void should_return_empty_list_when_get_community_email_with_city_Manchester() throws IOException, ParseException {
        List<String> expected =  service.getCommunityEmail("Manchester");
        assertTrue(expected.isEmpty());
    }
}