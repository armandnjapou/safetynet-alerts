package com.safetynet.alerts.data.person;

import com.safetynet.alerts.models.Person;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface IPersonData {
    List<Person> findAll() throws IOException, ParseException;
}
