package com.safetynet.alerts.data.person;

import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.Person;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface PersonData {
    List<Person> findAll() throws IOException, ParseException;
    Person findByFirstNameAndLastName(String firstName, String lastName);
    void update(Person person);
    void add(Person person) throws AlreadyExistingException;
    void delete(Person person);
}
