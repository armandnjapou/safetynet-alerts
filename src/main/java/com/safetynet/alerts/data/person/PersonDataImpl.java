package com.safetynet.alerts.data.person;

import com.safetynet.alerts.data.ProcessData;
import com.safetynet.alerts.data.ProcessDataImpl;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDataImpl implements PersonData {

    private static final ProcessData processData = new ProcessDataImpl();
    private static final Logger LOGGER = LogManager.getLogger(PersonDataImpl.class);

    @Override
    public List<Person> findAll() throws IOException, ParseException {
        JSONObject json = processData.readDatafromJsonFile(Constants.JSON_PATH);
        JSONArray persons = (JSONArray) json.get(Constants.PERSONS);
        List<Person> personList = new ArrayList<>();
        for(Object object : persons){
            JSONObject personObject = (JSONObject) object;
            Person person = new Person();
            person.setFirstName(personObject.get(Constants.FIRSTNAME).toString());
            person.setLastName(personObject.get(Constants.LASTNAME).toString());
            person.setAddress(personObject.get(Constants.ADDRESS).toString());
            person.setCity(personObject.get(Constants.CITY).toString());
            person.setZip(personObject.get(Constants.ZIP).toString());
            person.setPhone(personObject.get(Constants.PHONE).toString());
            person.setEmail(personObject.get(Constants.EMAIL).toString());
            personList.add(person);
        }
        return personList;
    }

    @Override
    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        Person person = null;
        try {
            List<Person> persons = findAll();
            person = persons.stream()
                    .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                    .findAny()
                    .orElse(null);
        } catch (IOException e) {
            LOGGER.error("IO Exception while finding all persons. Trace : {}", e.toString());
        } catch (ParseException e) {
            LOGGER.error("Parse exception while finding all persons. Trace : {}", e.toString());
        }
        return person;
    }
}
