package com.safetynet.alerts.data.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.data.ProcessData;
import com.safetynet.alerts.data.ProcessDataImpl;
import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonDataImpl implements PersonData {

    private static final ProcessData processData = new ProcessDataImpl();
    private static final Logger LOGGER = LogManager.getLogger(PersonDataImpl.class);
    private String methodName = null;

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

    @Override
    @SuppressWarnings("unchecked")
    public void update(Person person) {
        methodName = "updatePerson";
        LOGGER.info("Start of method : {}", methodName);
        if (person != null) {
            Person foundPerson = findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            if (foundPerson != null) {
                foundPerson.setAddress(person.getAddress());
                foundPerson.setZip(person.getZip());
                foundPerson.setCity(person.getCity());
                foundPerson.setEmail(person.getEmail());
                foundPerson.setPhone(person.getPhone());
                try {
                    List<Person> persons = findAll();
                    JSONArray personsArray = new JSONArray();
                    for (Person p : persons) {
                        if (p.getLastName().equals(foundPerson.getLastName()) && p.getFirstName().equals(foundPerson.getFirstName()))
                            personsArray.add(JSONValue.parse(new ObjectMapper().writeValueAsString(foundPerson)));
                        else personsArray.add(JSONValue.parse(new ObjectMapper().writeValueAsString(p)));
                    }
                    JSONObject json = processData.buildJSONObject(Constants.PERSONS, personsArray);
                    processData.writeDataInJsonFile(Constants.JSON_PATH, json);
                } catch (IOException e) {
                    LOGGER.error("IO Exception while finding all persons. Trace : {}", e.toString());
                } catch (ParseException e) {
                    LOGGER.error("Parse exception while finding all persons. Trace : {}", e.toString());
                }
            } else LOGGER.error("No person found with firstname {} and lastname {}", person.getFirstName(), person.getLastName());
        } else LOGGER.error("Null parameter for method : {}", methodName);
        LOGGER.info("End of method : {}", methodName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(Person person) throws AlreadyExistingException {
        methodName = "addPerson";
        LOGGER.info("Start of method : {}", methodName);
        if (person != null) {
            Person p = findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            if (p == null) {
                try {
                    List<Person> persons = findAll();
                    JSONArray personsArray = (JSONArray) JSONValue.parse(new ObjectMapper().writeValueAsString(persons));
                    personsArray.add(JSONValue.parse(new ObjectMapper().writeValueAsString(person)));
                    JSONObject json = processData.buildJSONObject(Constants.PERSONS, personsArray);
                    processData.writeDataInJsonFile(Constants.JSON_PATH, json);
                } catch (IOException e) {
                    LOGGER.error("IO Exception while finding all persons. Trace : {}", e.toString());
                } catch (ParseException e) {
                    LOGGER.error("Parse exception while finding all persons. Trace : {}", e.toString());
                }
            } else throw new AlreadyExistingException("Already existing object in data store...");
        } else LOGGER.error("Null parameter for method : {}", methodName);
        LOGGER.info("End of method : {}", methodName);
    }
}
