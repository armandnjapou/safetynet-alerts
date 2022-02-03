package com.safetynet.alerts.services.person;

import com.safetynet.alerts.data.person.PersonData;
import com.safetynet.alerts.data.person.PersonDataImpl;
import com.safetynet.alerts.models.Person;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonData personData = new PersonDataImpl();

    @Override
    public List<Person> findAll() throws IOException, ParseException {
        return personData.findAll();
    }
}
