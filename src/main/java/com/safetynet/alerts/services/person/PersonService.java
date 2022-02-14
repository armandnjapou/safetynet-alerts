package com.safetynet.alerts.services.person;

import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.Person;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface PersonService {
    List<Person> findAll() throws IOException, ParseException;
    void add(Person person) throws AlreadyExistingException;
    void update(Person person);
    void delete(Person person);
}
